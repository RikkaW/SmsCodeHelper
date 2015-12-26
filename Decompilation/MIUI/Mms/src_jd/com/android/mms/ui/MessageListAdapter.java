package com.android.mms.ui;

import android.content.Context;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.CursorWrapper;
import android.os.Handler;
import android.provider.Telephony.Sms;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.RecyclerListener;
import android.widget.CursorAdapter;
import android.widget.ListView;
import com.android.mms.audio.AudioItemCache;
import com.android.mms.understand.UnderstandFactory;
import com.android.mms.understand.UnderstandMessage;
import com.android.mms.util.EditableListView.EditableListIdMapper;
import com.android.mms.util.MSimUtils;
import com.google.android.collect.Sets;
import com.google.android.mms.MmsException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class MessageListAdapter
  extends CursorAdapter
  implements EditableListView.EditableListIdMapper
{
  static final String[] GROUP_MSG_PROJECTION = { "transport_type", "_id", "thread_id", "address", "body", "date", "date_sent", "read", "type", "status", "locked", "error_code", "timed", "mx_status", "block_type", "sub", "sub_cs", "date", "date_sent", "read", "m_type", "msg_box", "d_rpt", "rr", "err_type", "locked", "ct_l", "m_size", "exp", "timed", "date_ms_part", "mx_status", "need_download", "snippet", "preview_type", "preview_data_ts", "sim_id", "st", "block_type", "mx_type", "mx_extension", "count(*)" };
  static final String[] PROJECTION = { "transport_type", "_id", "thread_id", "address", "body", "date", "date_sent", "read", "type", "status", "locked", "error_code", "timed", "mx_status", "block_type", "sub", "sub_cs", "date", "date_sent", "read", "m_type", "msg_box", "d_rpt", "rr", "err_type", "locked", "ct_l", "m_size", "exp", "timed", "date_ms_part", "mx_status", "need_download", "snippet", "preview_type", "preview_data_ts", "sim_id", "st", "block_type", "mx_type", "mx_extension" };
  private AudioItemCache mAudioItemCache = new AudioItemCache();
  private String mBodySubstitution;
  private HashSet<Long> mCheckedIds = Sets.newHashSet();
  private final ColumnsMap mColumnsMap;
  private Context mContext;
  private long mConversationThreadId;
  private int mDeliverReportMode;
  private List<UnderstandMessage> mEmptyList = new ArrayList();
  private GetMsgItem mGetMsgItem = new GetMsgItem()
  {
    public MessageItem getCurrMessageItem(int paramAnonymousInt)
    {
      return MessageListAdapter.this.getCachedMessageItem(mCursor, false);
    }
    
    public MessageItem getNextMessageItem(int paramAnonymousInt)
    {
      MessageItem localMessageItem = null;
      if (mCursor.moveToNext()) {
        localMessageItem = MessageListAdapter.this.getCachedMessageItem(mCursor, false);
      }
      mCursor.moveToPrevious();
      return localMessageItem;
    }
    
    public MessageItem getPreMessageItem(int paramAnonymousInt)
    {
      MessageItem localMessageItem = null;
      if (mCursor.moveToPrevious()) {
        localMessageItem = MessageListAdapter.this.getCachedMessageItem(mCursor, false);
      }
      mCursor.moveToNext();
      return localMessageItem;
    }
  };
  private boolean mGroupByTime;
  private String mHighlight;
  private long mHighlightId;
  protected LayoutInflater mInflater;
  private boolean mIsB2c;
  private boolean mIsCheckMode = false;
  private boolean mIsGroup;
  private boolean mIsPrivate;
  private boolean mIsReadOnly;
  private String mItemStyle;
  private final LruCache<Long, List<UnderstandMessage>> mItemUnderstandCache;
  private final LruCache<Long, MessageItem> mMessageItemCache;
  private Handler mMsgListItemHandler;
  private OnDataSetChangedListener mOnDataSetChangedListener;
  private float mTextSize;
  private String mThreadAddress;
  
  public MessageListAdapter(Context paramContext, Cursor paramCursor, ListView paramListView, boolean paramBoolean, String paramString1, String paramString2, long paramLong)
  {
    this(paramContext, paramCursor, paramListView, paramBoolean, false, false, 0L, paramString1, paramString2, paramLong, false, false);
    initLayoutStyle(MessageListItem.Style.list.toString(), false, 0);
  }
  
  public MessageListAdapter(Context paramContext, Cursor paramCursor, ListView paramListView, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, long paramLong1, String paramString1, String paramString2, long paramLong2, boolean paramBoolean4, boolean paramBoolean5)
  {
    super(paramContext, paramCursor, false);
    mContext = paramContext;
    mHighlight = paramString1;
    mBodySubstitution = paramString2;
    mHighlightId = paramLong2;
    mIsPrivate = paramBoolean4;
    mIsB2c = paramBoolean5;
    mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    mMessageItemCache = new LruCache(500);
    mItemUnderstandCache = new LruCache(500);
    if (paramBoolean1) {}
    for (mColumnsMap = new ColumnsMap();; mColumnsMap = new ColumnsMap(paramCursor))
    {
      mIsGroup = paramBoolean2;
      mIsReadOnly = paramBoolean3;
      mConversationThreadId = paramLong1;
      paramListView.setRecyclerListener(new AbsListView.RecyclerListener()
      {
        public void onMovedToScrapHeap(View paramAnonymousView)
        {
          if ((paramAnonymousView instanceof MessageListItem)) {
            ((MessageListItem)paramAnonymousView).unbind();
          }
        }
      });
      return;
    }
  }
  
  private void adjustCursorStartPosition(Cursor paramCursor, int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = 0;
    }
    if (!(paramCursor instanceof CursorWrapper)) {}
    CursorWindow localCursorWindow;
    do
    {
      do
      {
        return;
        paramCursor = ((CursorWrapper)paramCursor).getWrappedCursor();
      } while (!(paramCursor instanceof AbstractWindowedCursor));
      localCursorWindow = ((AbstractWindowedCursor)paramCursor).getWindow();
    } while ((localCursorWindow == null) || (i >= localCursorWindow.getStartPosition()));
    i -= 1000;
    paramInt = i;
    if (i < 0) {
      paramInt = 0;
    }
    paramCursor.moveToPosition(paramInt);
  }
  
  private List<UnderstandMessage> createCachedItemUnderstand(MessageItem paramMessageItem)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if ("sms".equals(paramMessageItem.getType()))
    {
      if (!Telephony.Sms.isOutgoingFolder(paramMessageItem.getBoxId())) {
        break label69;
      }
      localObject1 = localObject2;
      if (paramMessageItem.getContactMessage() == null) {
        localObject1 = UnderstandFactory.getUnderstandMessageList(paramMessageItem.getAddress(), "", paramMessageItem.getBody(), paramMessageItem.getDate(), 1);
      }
    }
    for (;;)
    {
      paramMessageItem = (MessageItem)localObject1;
      if (localObject1 == null) {
        paramMessageItem = mEmptyList;
      }
      return paramMessageItem;
      label69:
      localObject1 = localObject2;
      if (paramMessageItem.getContactMessage() == null) {
        localObject1 = UnderstandFactory.getUnderstandMessageList(paramMessageItem.getAddress(), "", paramMessageItem.getBody(), paramMessageItem.getDate());
      }
    }
  }
  
  private MessageItem createCachedMessageItem(String paramString, Cursor paramCursor)
  {
    if ((paramCursor != null) && (isCursorValid(paramCursor))) {
      try
      {
        paramString = new MessageItem(mContext, paramString, paramCursor, mColumnsMap, mIsGroup, mIsReadOnly, mConversationThreadId, mThreadAddress);
        return paramString;
      }
      catch (MmsException paramString)
      {
        Log.e("MessageListAdapter", "createCachedMessageItem :", paramString);
      }
    }
    return null;
  }
  
  private List<UnderstandMessage> getCachedItemUnderstand(Cursor paramCursor)
  {
    String str = paramCursor.getString(mColumnsMap.mColumnMsgType);
    long l = paramCursor.getLong(mColumnsMap.mColumnMsgId);
    return (List)mItemUnderstandCache.get(Long.valueOf(getKey(str, l)));
  }
  
  private MessageItem getCachedMessageItem(Cursor paramCursor, boolean paramBoolean)
  {
    return getCachedMessageItem(paramCursor.getString(mColumnsMap.mColumnMsgType), paramCursor.getLong(mColumnsMap.mColumnMsgId), paramCursor, paramBoolean);
  }
  
  public static int getItemLayoutStyle(Context paramContext, String paramString, boolean paramBoolean1, int paramInt1, boolean paramBoolean2, GetMsgItem paramGetMsgItem, int paramInt2)
  {
    paramContext = paramGetMsgItem.getCurrMessageItem(paramInt2);
    boolean bool4;
    boolean bool1;
    boolean bool3;
    boolean bool2;
    if (paramContext.getLayoutStyle() == -1)
    {
      MessageItem localMessageItem = paramGetMsgItem.getPreMessageItem(paramInt2);
      paramGetMsgItem = paramGetMsgItem.getNextMessageItem(paramInt2);
      bool4 = paramContext.isMiXin();
      if (paramBoolean1) {
        break label269;
      }
      bool1 = true;
      bool3 = true;
      bool2 = true;
      boolean bool5 = true;
      if (localMessageItem == null) {
        break label275;
      }
      long l1 = paramContext.getDisplayDate() - localMessageItem.getDisplayDate();
      if ((!paramBoolean1) || (MSimUtils.getSlotIdBySimInfoId(paramContext.getSimId()) != MSimUtils.getSlotIdBySimInfoId(localMessageItem.getSimId())) || (l1 >= 600000L)) {
        bool1 = true;
      }
      bool3 = bool2;
      if (!bool1)
      {
        bool3 = bool2;
        if (l1 < 20000L)
        {
          bool3 = bool2;
          if (isSameKindMessage(paramContext, localMessageItem)) {
            bool3 = false;
          }
        }
      }
      bool2 = bool4 ^ localMessageItem.isMiXin();
      label161:
      bool4 = bool5;
      if (paramGetMsgItem != null)
      {
        l1 = paramGetMsgItem.getDisplayDate();
        long l2 = paramContext.getDisplayDate();
        bool4 = bool5;
        if (paramBoolean1)
        {
          bool4 = bool5;
          if (l1 - l2 < 20000L)
          {
            bool4 = bool5;
            if (MSimUtils.getSlotIdBySimInfoId(paramGetMsgItem.getSimId()) == MSimUtils.getSlotIdBySimInfoId(paramContext.getSimId()))
            {
              bool4 = bool5;
              if (isSameKindMessage(paramGetMsgItem, paramContext)) {
                bool4 = false;
              }
            }
          }
        }
      }
      if (!paramBoolean2) {
        break label284;
      }
    }
    for (;;)
    {
      paramContext.initLayoutStyle(paramString, bool1, paramInt1, bool2, bool3, bool4);
      return paramContext.getLayoutStyle();
      label269:
      bool1 = false;
      break;
      label275:
      bool1 = true;
      bool2 = true;
      break label161;
      label284:
      bool2 = false;
    }
  }
  
  private static long getKey(String paramString, long paramLong)
  {
    long l = paramLong;
    if ("mms".equals(paramString)) {
      l = -paramLong;
    }
    return l;
  }
  
  private boolean isCursorValid(Cursor paramCursor)
  {
    return (!paramCursor.isClosed()) && (!paramCursor.isBeforeFirst()) && (!paramCursor.isAfterLast());
  }
  
  private static boolean isSameKindMessage(MessageItem paramMessageItem1, MessageItem paramMessageItem2)
  {
    return paramMessageItem1.isOutMessage() == paramMessageItem2.isOutMessage();
  }
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    boolean bool = false;
    if (mIsCheckMode)
    {
      long l = getKey(paramCursor.getString(mColumnsMap.mColumnMsgType), paramCursor.getLong(mColumnsMap.mColumnMsgId));
      bool = mCheckedIds.contains(Long.valueOf(l));
    }
    MessageItem localMessageItem = getCachedMessageItem(paramCursor, true);
    MessageListItem localMessageListItem = null;
    if ((!localMessageItem.isSms()) || (localMessageItem.getMsgId() != mHighlightId))
    {
      paramContext = localMessageListItem;
      if (localMessageItem.isMms())
      {
        paramContext = localMessageListItem;
        if (localMessageItem.getMsgId() != -mHighlightId) {}
      }
    }
    else
    {
      paramContext = mBodySubstitution;
    }
    localMessageListItem = (MessageListItem)paramView;
    paramCursor = getCachedItemUnderstand(paramCursor);
    if ((paramCursor != null) && (paramCursor.size() > 0)) {
      localMessageListItem.setUnderstandMessageList(paramCursor);
    }
    localMessageListItem.rebind(localMessageItem, mIsCheckMode, bool, mHighlight, paramContext, mIsPrivate);
    if (mTextSize != 0.0F) {
      ((MessageListItem)paramView).setBodyTextSize(mTextSize);
    }
  }
  
  public void cleanCache()
  {
    mItemUnderstandCache.evictAll();
    Log.v("MessageListAdapter", "cleanCache size is " + mItemUnderstandCache.size());
  }
  
  public void enterCheckMode()
  {
    mIsCheckMode = true;
  }
  
  public void exitCheckMode()
  {
    mIsCheckMode = false;
    mCheckedIds = Sets.newHashSet();
  }
  
  public AudioItemCache getAudioItemCache()
  {
    return mAudioItemCache;
  }
  
  public MessageItem getCachedMessageItem(String paramString, long paramLong, Cursor paramCursor, boolean paramBoolean)
  {
    paramLong = getKey(paramString, paramLong);
    MessageItem localMessageItem2 = (MessageItem)mMessageItemCache.get(Long.valueOf(paramLong));
    MessageItem localMessageItem1 = localMessageItem2;
    if (localMessageItem2 == null)
    {
      localMessageItem1 = createCachedMessageItem(paramString, paramCursor);
      if (localMessageItem1 == null) {
        return null;
      }
      mMessageItemCache.put(Long.valueOf(paramLong), localMessageItem1);
    }
    if ((paramBoolean) && ((List)mItemUnderstandCache.get(Long.valueOf(paramLong)) == null))
    {
      paramString = createCachedItemUnderstand(localMessageItem1);
      if (paramString == null) {
        break label110;
      }
      mItemUnderstandCache.put(Long.valueOf(paramLong), paramString);
    }
    for (;;)
    {
      return localMessageItem1;
      label110:
      Log.e("MessageListAdapter", "createCached item understand is null");
    }
  }
  
  public List<MessageItem> getCheckedItems(HashSet<Integer> paramHashSet)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramHashSet != null)
    {
      paramHashSet = paramHashSet.iterator();
      while (paramHashSet.hasNext()) {
        localArrayList.add(getCachedMessageItem((Cursor)getItem(((Integer)paramHashSet.next()).intValue()), false));
      }
    }
    return localArrayList;
  }
  
  public int getItemViewType(int paramInt)
  {
    adjustCursorStartPosition(mCursor, paramInt - 1);
    mCursor.moveToPosition(paramInt);
    Context localContext = mContext;
    String str = mItemStyle;
    boolean bool2 = mGroupByTime;
    int i = mDeliverReportMode;
    if (!mIsB2c) {}
    for (boolean bool1 = true;; bool1 = false) {
      return getItemLayoutStyle(localContext, str, bool2, i, bool1, mGetMsgItem, paramInt);
    }
  }
  
  public int getViewTypeCount()
  {
    return 12;
  }
  
  public void initLayoutStyle(String paramString, boolean paramBoolean, int paramInt)
  {
    mItemStyle = paramString;
    mGroupByTime = paramBoolean;
    mDeliverReportMode = paramInt;
  }
  
  public long mapPositionToId(int paramInt)
  {
    Cursor localCursor = (Cursor)getItem(paramInt);
    return getKey(localCursor.getString(mColumnsMap.mColumnMsgType), localCursor.getLong(mColumnsMap.mColumnMsgId));
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    paramCursor = getCachedMessageItem(paramCursor, false);
    if (paramCursor.isListLayoutStyle()) {}
    for (paramContext = (MessageListItem)mInflater.inflate(2130968661, paramViewGroup, false);; paramContext = (MessageListItem)mInflater.inflate(2130968659, paramViewGroup, false))
    {
      paramContext.setAudioItemCache(mAudioItemCache);
      paramContext.bind(paramCursor);
      paramContext.setMsgListItemHandler(mMsgListItemHandler);
      return paramContext;
    }
  }
  
  public void notifyDataSetChanged()
  {
    if ((mOnDataSetChangedListener == null) || (!mOnDataSetChangedListener.needHoldCache())) {
      mMessageItemCache.evictAll();
    }
    for (;;)
    {
      super.notifyDataSetChanged();
      if (mOnDataSetChangedListener != null) {
        mOnDataSetChangedListener.onDataSetChanged(this);
      }
      return;
      Log.v("MessageListAdapter", "hold cache item");
    }
  }
  
  protected void onContentChanged()
  {
    if ((getCursor() != null) && (!getCursor().isClosed()) && (mOnDataSetChangedListener != null)) {
      mOnDataSetChangedListener.onContentChanged(this);
    }
  }
  
  public void setCheckedItem(HashSet<Long> paramHashSet)
  {
    if (paramHashSet == null)
    {
      mCheckedIds = Sets.newHashSet();
      return;
    }
    mCheckedIds = paramHashSet;
  }
  
  public void setMsgListItemHandler(Handler paramHandler)
  {
    mMsgListItemHandler = paramHandler;
  }
  
  public void setOnDataSetChangedListener(OnDataSetChangedListener paramOnDataSetChangedListener)
  {
    mOnDataSetChangedListener = paramOnDataSetChangedListener;
  }
  
  public void setTextSize(float paramFloat)
  {
    mTextSize = paramFloat;
  }
  
  public void setThreadAddress(String paramString)
  {
    mThreadAddress = paramString;
  }
  
  public static class ColumnsMap
  {
    public int mColumnCount;
    public int mColumnMmsBlockType;
    public int mColumnMmsContentLocation;
    public int mColumnMmsDate;
    public int mColumnMmsDateMsPart;
    public int mColumnMmsDateSent;
    public int mColumnMmsDeliveryReport;
    public int mColumnMmsErrorType;
    public int mColumnMmsExpiry;
    public int mColumnMmsLocked;
    public int mColumnMmsMessageBox;
    public int mColumnMmsMessageSize;
    public int mColumnMmsMessageType;
    public int mColumnMmsMxType;
    public int mColumnMmsNeedDownload;
    public int mColumnMmsPreviewDataTs;
    public int mColumnMmsPreviewType;
    public int mColumnMmsReadReport;
    public int mColumnMmsSnippet;
    public int mColumnMmsStatus;
    public int mColumnMmsSubject;
    public int mColumnMmsSubjectCharset;
    public int mColumnMmsTimed;
    public int mColumnMsgId;
    public int mColumnMsgType;
    public int mColumnMx2Type;
    public int mColumnMxExtension;
    public int mColumnSimId;
    public int mColumnSmsAddress;
    public int mColumnSmsBlockType;
    public int mColumnSmsBody;
    public int mColumnSmsDate;
    public int mColumnSmsDateSent;
    public int mColumnSmsErrorCode;
    public int mColumnSmsLocked;
    public int mColumnSmsMxType;
    public int mColumnSmsStatus;
    public int mColumnSmsTimed;
    public int mColumnSmsType;
    public int mColumnThreadId;
    
    public ColumnsMap()
    {
      mColumnMsgType = 0;
      mColumnMsgId = 1;
      mColumnThreadId = 2;
      mColumnSmsAddress = 3;
      mColumnSmsBody = 4;
      mColumnSmsDate = 5;
      mColumnSmsDateSent = 6;
      mColumnSmsType = 8;
      mColumnSmsStatus = 9;
      mColumnSmsLocked = 10;
      mColumnSmsErrorCode = 11;
      mColumnSmsTimed = 12;
      mColumnSmsMxType = 13;
      mColumnSmsBlockType = 14;
      mColumnMmsSubject = 15;
      mColumnMmsSubjectCharset = 16;
      mColumnMmsDate = 17;
      mColumnMmsDateSent = 18;
      mColumnMmsMessageType = 20;
      mColumnMmsMessageBox = 21;
      mColumnMmsDeliveryReport = 22;
      mColumnMmsReadReport = 23;
      mColumnMmsErrorType = 24;
      mColumnMmsLocked = 25;
      mColumnMmsContentLocation = 26;
      mColumnMmsMessageSize = 27;
      mColumnMmsExpiry = 28;
      mColumnMmsTimed = 29;
      mColumnMmsDateMsPart = 30;
      mColumnMmsMxType = 31;
      mColumnMmsNeedDownload = 32;
      mColumnMmsSnippet = 33;
      mColumnMmsPreviewType = 34;
      mColumnMmsPreviewDataTs = 35;
      mColumnMmsBlockType = 38;
      mColumnSimId = 36;
      mColumnMmsStatus = 37;
      mColumnMx2Type = 39;
      mColumnCount = 41;
      mColumnMxExtension = 40;
    }
    
    public ColumnsMap(Cursor paramCursor)
    {
      try
      {
        mColumnMsgType = paramCursor.getColumnIndexOrThrow("transport_type");
      }
      catch (IllegalArgumentException localIllegalArgumentException37)
      {
        try
        {
          mColumnMsgId = paramCursor.getColumnIndexOrThrow("_id");
        }
        catch (IllegalArgumentException localIllegalArgumentException37)
        {
          try
          {
            mColumnThreadId = paramCursor.getColumnIndexOrThrow("thread_id");
          }
          catch (IllegalArgumentException localIllegalArgumentException37)
          {
            try
            {
              mColumnSmsAddress = paramCursor.getColumnIndexOrThrow("address");
            }
            catch (IllegalArgumentException localIllegalArgumentException37)
            {
              try
              {
                mColumnSmsBody = paramCursor.getColumnIndexOrThrow("body");
              }
              catch (IllegalArgumentException localIllegalArgumentException37)
              {
                try
                {
                  mColumnSmsDate = paramCursor.getColumnIndexOrThrow("date");
                }
                catch (IllegalArgumentException localIllegalArgumentException37)
                {
                  try
                  {
                    mColumnSmsDateSent = paramCursor.getColumnIndexOrThrow("date_sent");
                  }
                  catch (IllegalArgumentException localIllegalArgumentException37)
                  {
                    try
                    {
                      mColumnSmsType = paramCursor.getColumnIndexOrThrow("type");
                    }
                    catch (IllegalArgumentException localIllegalArgumentException37)
                    {
                      try
                      {
                        mColumnSmsStatus = paramCursor.getColumnIndexOrThrow("status");
                      }
                      catch (IllegalArgumentException localIllegalArgumentException37)
                      {
                        try
                        {
                          mColumnSmsLocked = paramCursor.getColumnIndexOrThrow("locked");
                        }
                        catch (IllegalArgumentException localIllegalArgumentException37)
                        {
                          try
                          {
                            mColumnSmsErrorCode = paramCursor.getColumnIndexOrThrow("error_code");
                          }
                          catch (IllegalArgumentException localIllegalArgumentException37)
                          {
                            try
                            {
                              mColumnSmsTimed = paramCursor.getColumnIndexOrThrow("timed");
                            }
                            catch (IllegalArgumentException localIllegalArgumentException37)
                            {
                              try
                              {
                                mColumnSmsMxType = paramCursor.getColumnIndexOrThrow("mx_status");
                              }
                              catch (IllegalArgumentException localIllegalArgumentException37)
                              {
                                try
                                {
                                  mColumnSmsBlockType = paramCursor.getColumnIndexOrThrow("block_type");
                                }
                                catch (IllegalArgumentException localIllegalArgumentException37)
                                {
                                  try
                                  {
                                    mColumnMmsSubject = paramCursor.getColumnIndexOrThrow("sub");
                                  }
                                  catch (IllegalArgumentException localIllegalArgumentException37)
                                  {
                                    try
                                    {
                                      mColumnMmsSubjectCharset = paramCursor.getColumnIndexOrThrow("sub_cs");
                                    }
                                    catch (IllegalArgumentException localIllegalArgumentException37)
                                    {
                                      try
                                      {
                                        mColumnMmsDate = paramCursor.getColumnIndexOrThrow("date");
                                      }
                                      catch (IllegalArgumentException localIllegalArgumentException37)
                                      {
                                        try
                                        {
                                          mColumnMmsDateSent = paramCursor.getColumnIndexOrThrow("date_sent");
                                        }
                                        catch (IllegalArgumentException localIllegalArgumentException37)
                                        {
                                          try
                                          {
                                            mColumnMmsMessageType = paramCursor.getColumnIndexOrThrow("m_type");
                                          }
                                          catch (IllegalArgumentException localIllegalArgumentException37)
                                          {
                                            try
                                            {
                                              mColumnMmsMessageBox = paramCursor.getColumnIndexOrThrow("msg_box");
                                            }
                                            catch (IllegalArgumentException localIllegalArgumentException37)
                                            {
                                              try
                                              {
                                                mColumnMmsDeliveryReport = paramCursor.getColumnIndexOrThrow("d_rpt");
                                              }
                                              catch (IllegalArgumentException localIllegalArgumentException37)
                                              {
                                                try
                                                {
                                                  mColumnMmsReadReport = paramCursor.getColumnIndexOrThrow("rr");
                                                }
                                                catch (IllegalArgumentException localIllegalArgumentException37)
                                                {
                                                  try
                                                  {
                                                    mColumnMmsErrorType = paramCursor.getColumnIndexOrThrow("err_type");
                                                  }
                                                  catch (IllegalArgumentException localIllegalArgumentException37)
                                                  {
                                                    try
                                                    {
                                                      mColumnMmsLocked = paramCursor.getColumnIndexOrThrow("locked");
                                                    }
                                                    catch (IllegalArgumentException localIllegalArgumentException37)
                                                    {
                                                      try
                                                      {
                                                        mColumnMmsContentLocation = paramCursor.getColumnIndexOrThrow("ct_l");
                                                      }
                                                      catch (IllegalArgumentException localIllegalArgumentException37)
                                                      {
                                                        try
                                                        {
                                                          mColumnMmsMessageSize = paramCursor.getColumnIndexOrThrow("m_size");
                                                        }
                                                        catch (IllegalArgumentException localIllegalArgumentException37)
                                                        {
                                                          try
                                                          {
                                                            mColumnMmsExpiry = paramCursor.getColumnIndexOrThrow("exp");
                                                          }
                                                          catch (IllegalArgumentException localIllegalArgumentException37)
                                                          {
                                                            try
                                                            {
                                                              mColumnMmsTimed = paramCursor.getColumnIndexOrThrow("timed");
                                                            }
                                                            catch (IllegalArgumentException localIllegalArgumentException37)
                                                            {
                                                              try
                                                              {
                                                                mColumnMmsDateMsPart = paramCursor.getColumnIndexOrThrow("date_ms_part");
                                                              }
                                                              catch (IllegalArgumentException localIllegalArgumentException37)
                                                              {
                                                                try
                                                                {
                                                                  mColumnMmsMxType = paramCursor.getColumnIndexOrThrow("mx_status");
                                                                }
                                                                catch (IllegalArgumentException localIllegalArgumentException37)
                                                                {
                                                                  try
                                                                  {
                                                                    mColumnMmsNeedDownload = paramCursor.getColumnIndexOrThrow("need_download");
                                                                  }
                                                                  catch (IllegalArgumentException localIllegalArgumentException37)
                                                                  {
                                                                    try
                                                                    {
                                                                      mColumnMmsSnippet = paramCursor.getColumnIndexOrThrow("snippet");
                                                                    }
                                                                    catch (IllegalArgumentException localIllegalArgumentException37)
                                                                    {
                                                                      try
                                                                      {
                                                                        mColumnMmsPreviewType = paramCursor.getColumnIndexOrThrow("preview_type");
                                                                      }
                                                                      catch (IllegalArgumentException localIllegalArgumentException37)
                                                                      {
                                                                        try
                                                                        {
                                                                          mColumnMmsPreviewDataTs = paramCursor.getColumnIndexOrThrow("preview_data_ts");
                                                                        }
                                                                        catch (IllegalArgumentException localIllegalArgumentException37)
                                                                        {
                                                                          try
                                                                          {
                                                                            mColumnMmsBlockType = paramCursor.getColumnIndexOrThrow("block_type");
                                                                          }
                                                                          catch (IllegalArgumentException localIllegalArgumentException37)
                                                                          {
                                                                            try
                                                                            {
                                                                              mColumnSimId = paramCursor.getColumnIndexOrThrow("sim_id");
                                                                            }
                                                                            catch (IllegalArgumentException localIllegalArgumentException37)
                                                                            {
                                                                              try
                                                                              {
                                                                                mColumnCount = paramCursor.getColumnIndexOrThrow("count(*)");
                                                                              }
                                                                              catch (IllegalArgumentException localIllegalArgumentException37)
                                                                              {
                                                                                try
                                                                                {
                                                                                  for (;;)
                                                                                  {
                                                                                    mColumnMx2Type = paramCursor.getColumnIndexOrThrow("mx_type");
                                                                                    try
                                                                                    {
                                                                                      mColumnMxExtension = paramCursor.getColumnIndexOrThrow("mx_extension");
                                                                                      return;
                                                                                    }
                                                                                    catch (IllegalArgumentException paramCursor)
                                                                                    {
                                                                                      Log.w("colsMap", paramCursor.getMessage());
                                                                                    }
                                                                                    localIllegalArgumentException1 = localIllegalArgumentException1;
                                                                                    Log.w("colsMap", localIllegalArgumentException1.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException2 = localIllegalArgumentException2;
                                                                                    Log.w("colsMap", localIllegalArgumentException2.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException3 = localIllegalArgumentException3;
                                                                                    Log.w("colsMap", localIllegalArgumentException3.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException4 = localIllegalArgumentException4;
                                                                                    Log.w("colsMap", localIllegalArgumentException4.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException5 = localIllegalArgumentException5;
                                                                                    Log.w("colsMap", localIllegalArgumentException5.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException6 = localIllegalArgumentException6;
                                                                                    Log.w("colsMap", localIllegalArgumentException6.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException7 = localIllegalArgumentException7;
                                                                                    Log.w("colsMap", localIllegalArgumentException7.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException8 = localIllegalArgumentException8;
                                                                                    Log.w("colsMap", localIllegalArgumentException8.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException9 = localIllegalArgumentException9;
                                                                                    Log.w("colsMap", localIllegalArgumentException9.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException10 = localIllegalArgumentException10;
                                                                                    Log.w("colsMap", localIllegalArgumentException10.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException11 = localIllegalArgumentException11;
                                                                                    Log.w("colsMap", localIllegalArgumentException11.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException12 = localIllegalArgumentException12;
                                                                                    Log.w("colsMap", localIllegalArgumentException12.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException13 = localIllegalArgumentException13;
                                                                                    Log.w("colsMap", localIllegalArgumentException13.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException14 = localIllegalArgumentException14;
                                                                                    Log.w("colsMap", localIllegalArgumentException14.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException15 = localIllegalArgumentException15;
                                                                                    Log.w("colsMap", localIllegalArgumentException15.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException16 = localIllegalArgumentException16;
                                                                                    Log.w("colsMap", localIllegalArgumentException16.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException17 = localIllegalArgumentException17;
                                                                                    Log.w("colsMap", localIllegalArgumentException17.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException18 = localIllegalArgumentException18;
                                                                                    Log.w("colsMap", localIllegalArgumentException18.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException19 = localIllegalArgumentException19;
                                                                                    Log.w("colsMap", localIllegalArgumentException19.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException20 = localIllegalArgumentException20;
                                                                                    Log.w("colsMap", localIllegalArgumentException20.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException21 = localIllegalArgumentException21;
                                                                                    Log.w("colsMap", localIllegalArgumentException21.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException22 = localIllegalArgumentException22;
                                                                                    Log.w("colsMap", localIllegalArgumentException22.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException23 = localIllegalArgumentException23;
                                                                                    Log.w("colsMap", localIllegalArgumentException23.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException24 = localIllegalArgumentException24;
                                                                                    Log.w("colsMap", localIllegalArgumentException24.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException25 = localIllegalArgumentException25;
                                                                                    Log.w("colsMap", localIllegalArgumentException25.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException26 = localIllegalArgumentException26;
                                                                                    Log.w("colsMap", localIllegalArgumentException26.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException27 = localIllegalArgumentException27;
                                                                                    Log.w("colsMap", localIllegalArgumentException27.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException28 = localIllegalArgumentException28;
                                                                                    Log.w("colsMap", localIllegalArgumentException28.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException29 = localIllegalArgumentException29;
                                                                                    Log.w("colsMap", localIllegalArgumentException29.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException30 = localIllegalArgumentException30;
                                                                                    Log.w("colsMap", localIllegalArgumentException30.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException31 = localIllegalArgumentException31;
                                                                                    Log.w("colsMap", localIllegalArgumentException31.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException32 = localIllegalArgumentException32;
                                                                                    Log.w("colsMap", localIllegalArgumentException32.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException33 = localIllegalArgumentException33;
                                                                                    Log.w("colsMap", localIllegalArgumentException33.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException34 = localIllegalArgumentException34;
                                                                                    Log.w("colsMap", localIllegalArgumentException34.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException35 = localIllegalArgumentException35;
                                                                                    Log.w("colsMap", localIllegalArgumentException35.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException36 = localIllegalArgumentException36;
                                                                                    Log.w("colsMap", localIllegalArgumentException36.getMessage());
                                                                                    continue;
                                                                                    localIllegalArgumentException37 = localIllegalArgumentException37;
                                                                                    Log.w("colsMap", localIllegalArgumentException37.getMessage());
                                                                                  }
                                                                                }
                                                                                catch (IllegalArgumentException localIllegalArgumentException38)
                                                                                {
                                                                                  for (;;)
                                                                                  {
                                                                                    Log.w("colsMap", localIllegalArgumentException38.getMessage());
                                                                                  }
                                                                                }
                                                                              }
                                                                            }
                                                                          }
                                                                        }
                                                                      }
                                                                    }
                                                                  }
                                                                }
                                                              }
                                                            }
                                                          }
                                                        }
                                                      }
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  public static abstract interface GetMsgItem
  {
    public abstract MessageItem getCurrMessageItem(int paramInt);
    
    public abstract MessageItem getNextMessageItem(int paramInt);
    
    public abstract MessageItem getPreMessageItem(int paramInt);
  }
  
  public static abstract interface OnDataSetChangedListener
  {
    public abstract boolean needHoldCache();
    
    public abstract void onContentChanged(MessageListAdapter paramMessageListAdapter);
    
    public abstract void onDataSetChanged(MessageListAdapter paramMessageListAdapter);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
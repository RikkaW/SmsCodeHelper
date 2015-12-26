package com.android.mms.ui;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.google.android.mms.MmsException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import miui.provider.ExtraTelephony.MmsSms;

public class ExpiredTimedMessageListAdapter
  extends CursorAdapter
{
  public static final Uri NO_PRIVATE_EXPIRED_URI = ExtraTelephony.MmsSms.CONTENT_EXPIRED_URI.buildUpon().appendQueryParameter("privacy_flag", "0").build();
  public static final Uri PRIVATE_EXPIRED_URI = ExtraTelephony.MmsSms.CONTENT_EXPIRED_URI.buildUpon().appendQueryParameter("privacy_flag", "1").build();
  static final String[] PROJECTION = MessageListAdapter.PROJECTION;
  private BackgroundQueryHandler mBackgroundQueryHandler;
  private SparseBooleanArray mCheckStates;
  private MessageListAdapter.ColumnsMap mColumnsMap;
  private Context mContext;
  private Handler mHandler = new Handler();
  private LayoutInflater mInflater;
  private boolean mIsStoped = false;
  private final LinkedHashMap<Long, MessageItem> mMessageItemCache;
  
  public ExpiredTimedMessageListAdapter(Context paramContext)
  {
    super(paramContext, null);
    mContext = paramContext;
    mBackgroundQueryHandler = new BackgroundQueryHandler(paramContext.getContentResolver());
    mColumnsMap = new MessageListAdapter.ColumnsMap();
    mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    mMessageItemCache = new LinkedHashMap(10, 1.0F, true)
    {
      protected boolean removeEldestEntry(Map.Entry paramAnonymousEntry)
      {
        return size() > 50;
      }
    };
  }
  
  public static long getKey(String paramString, long paramLong)
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
  
  private boolean isItemChecked(int paramInt)
  {
    if (mCheckStates != null) {
      return mCheckStates.get(paramInt);
    }
    return false;
  }
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    if ((paramView instanceof MessageListItem))
    {
      Object localObject = getCachedMessageItem(paramCursor);
      ((MessageListItem)paramView).rebind((MessageItem)localObject, false, false, null, null);
      TextView localTextView = (TextView)paramView.findViewById(2131820632);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(MessageUtils.getPreciseTimeStamp(((MessageItem)localObject).getDate(), true));
      localStringBuilder.append(" ");
      localStringBuilder.append(paramContext.getString(2131362159));
      localStringBuilder.append(" ");
      localObject = Conversation.get(((MessageItem)localObject).getThreadId());
      ((Conversation)localObject).loadRecipients(false, false);
      localObject = ((Conversation)localObject).getRecipients();
      int i = 0;
      while ((i < ((ContactList)localObject).size()) && (i < 3))
      {
        if (i != 0) {
          localStringBuilder.append(",");
        }
        localStringBuilder.append(((Contact)((ContactList)localObject).get(i)).getName());
        i += 1;
      }
      if (((ContactList)localObject).size() > 3) {
        localStringBuilder.append(paramContext.getString(2131362160, new Object[] { Integer.valueOf(((ContactList)localObject).size()) }));
      }
      localTextView.setText(localStringBuilder.toString());
      ((CheckBox)paramView.findViewById(2131820631)).setChecked(isItemChecked(paramCursor.getPosition()));
    }
  }
  
  public MessageItem getCachedMessageItem(int paramInt)
  {
    Cursor localCursor = (Cursor)getItem(paramInt);
    if (localCursor == null) {
      return null;
    }
    return getCachedMessageItem(localCursor);
  }
  
  public MessageItem getCachedMessageItem(Cursor paramCursor)
  {
    return getCachedMessageItem(paramCursor.getString(mColumnsMap.mColumnMsgType), paramCursor.getLong(mColumnsMap.mColumnMsgId), paramCursor);
  }
  
  public MessageItem getCachedMessageItem(String paramString, long paramLong, Cursor paramCursor)
  {
    MessageItem localMessageItem = (MessageItem)mMessageItemCache.get(Long.valueOf(getKey(paramString, paramLong)));
    if ((localMessageItem == null) && (paramCursor != null) && (isCursorValid(paramCursor))) {}
    for (;;)
    {
      try
      {
        paramString = new MessageItem(mContext, paramString, paramCursor, mColumnsMap, true);
        if (paramString.getMessage() != null) {}
      }
      catch (MmsException paramString)
      {
        try
        {
          mMessageItemCache.put(Long.valueOf(getKey(paramString.getType(), paramString.getMsgId())), paramString);
          return paramString;
        }
        catch (MmsException paramString)
        {
          for (;;) {}
        }
        paramString = paramString;
      }
      for (paramString = "Caught MmsException";; paramString = paramString.getMessage())
      {
        Log.e("ExpiredTimedMessageListAdapter", paramString);
        return null;
      }
      paramString = localMessageItem;
    }
  }
  
  public void load()
  {
    mIsStoped = false;
    mBackgroundQueryHandler.cancelOperation(1001);
    try
    {
      Uri localUri = Uri.withAppendedPath(NO_PRIVATE_EXPIRED_URI, Long.toString(MmsApp.getStartupTime()));
      mBackgroundQueryHandler.startQuery(1001, null, localUri, PROJECTION, null, null, null);
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      SqliteWrapper.checkSQLiteException(mContext, localSQLiteException);
    }
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    paramContext = getCachedMessageItem(paramCursor);
    paramCursor = (MessageListItem)mInflater.inflate(2130968605, paramViewGroup, false);
    paramCursor.bind(paramContext);
    paramCursor.setMsgListItemHandler(mHandler);
    return paramCursor;
  }
  
  public void notifyDataSetChanged()
  {
    super.notifyDataSetChanged();
    mMessageItemCache.clear();
  }
  
  public void setListViewCheckStates(SparseBooleanArray paramSparseBooleanArray)
  {
    mCheckStates = paramSparseBooleanArray;
  }
  
  public void stop()
  {
    mIsStoped = true;
  }
  
  private final class BackgroundQueryHandler
    extends AsyncQueryHandler
  {
    public BackgroundQueryHandler(ContentResolver paramContentResolver)
    {
      super();
    }
    
    protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
    {
      switch (paramInt)
      {
      default: 
        return;
      }
      if (mIsStoped)
      {
        if (paramCursor != null) {
          paramCursor.close();
        }
        Log.v("ExpiredTimedMessageListAdapter", "query cursor close for stop");
        return;
      }
      changeCursor(paramCursor);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ExpiredTimedMessageListAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
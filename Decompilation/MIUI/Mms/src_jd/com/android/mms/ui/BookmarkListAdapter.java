package com.android.mms.ui;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Sms;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.google.android.mms.MmsException;
import miui.provider.ExtraTelephony.MmsSms;

public class BookmarkListAdapter
  extends CursorAdapter
{
  private static final Uri CONTENT_ALL_LOCKED_URI = ExtraTelephony.MmsSms.CONTENT_ALL_LOCKED_URI.buildUpon().appendQueryParameter("privacy_flag", "0").build();
  static final String[] PROJECTION = MessageListAdapter.PROJECTION;
  private BackgroundQueryHandler mBackgroundQueryHandler;
  private MessageListAdapter.ColumnsMap mColumnsMap;
  private Context mContext;
  private Handler mHandler = new Handler();
  private LayoutInflater mInflater;
  private boolean mIsStoped = false;
  private final LruCache<Long, MessageItem> mMessageItemCache;
  private float mTextSize;
  
  public BookmarkListAdapter(Context paramContext)
  {
    super(paramContext, null);
    mContext = paramContext;
    mBackgroundQueryHandler = new BackgroundQueryHandler(paramContext.getContentResolver());
    mColumnsMap = new MessageListAdapter.ColumnsMap();
    mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    mMessageItemCache = new LruCache(500);
  }
  
  private Contact getContact(MessageItem paramMessageItem)
  {
    if ((paramMessageItem.isMms()) && (paramMessageItem.getAddress() == null))
    {
      paramMessageItem = Conversation.get(paramMessageItem.getThreadId());
      if (paramMessageItem != null)
      {
        paramMessageItem = paramMessageItem.getRecipients();
        if ((paramMessageItem != null) && (paramMessageItem.size() > 0)) {
          return (Contact)paramMessageItem.get(0);
        }
      }
    }
    return null;
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
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    if ((paramView instanceof MessageListItem))
    {
      paramContext = getCachedMessageItem(paramCursor);
      ((MessageListItem)paramView).rebind(paramContext, false, false, null, null);
      ((MessageListItem)paramView).setBodyTextSize(mTextSize);
    }
  }
  
  public void deleteBookmark(MessageItem paramMessageItem)
  {
    if (paramMessageItem.isSms()) {}
    for (Object localObject = Telephony.Sms.CONTENT_URI;; localObject = Telephony.Mms.CONTENT_URI)
    {
      paramMessageItem = ContentUris.withAppendedId((Uri)localObject, paramMessageItem.getMsgId());
      localObject = new ContentValues(1);
      ((ContentValues)localObject).put("locked", Integer.valueOf(0));
      mBackgroundQueryHandler.startUpdate(1000, null, paramMessageItem, (ContentValues)localObject, null, null);
      load();
      return;
    }
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
          if ((paramString.isMms()) && (paramString.getAddress() == null)) {
            paramString.setContact(getContact(paramString));
          }
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
        Log.e("BookmarkListAdapter", paramString);
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
      mBackgroundQueryHandler.startQuery(1001, null, CONTENT_ALL_LOCKED_URI, PROJECTION, null, null, null);
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
    paramCursor = (MessageListItem)mInflater.inflate(2130968589, paramViewGroup, false);
    paramCursor.bind(paramContext);
    paramCursor.setMsgListItemHandler(mHandler);
    return paramCursor;
  }
  
  public void notifyDataSetChanged()
  {
    super.notifyDataSetChanged();
    mMessageItemCache.evictAll();
  }
  
  public void setTextSize(float paramFloat)
  {
    mTextSize = paramFloat;
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
        Log.v("BookmarkListAdapter", "query cursor close for stop");
        return;
      }
      changeCursor(paramCursor);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BookmarkListAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
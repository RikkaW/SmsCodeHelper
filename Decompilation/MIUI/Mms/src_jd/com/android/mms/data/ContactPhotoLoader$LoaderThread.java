package com.android.mms.data;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.ContactsContract.Data;
import com.google.android.collect.Lists;
import java.util.ArrayList;

class ContactPhotoLoader$LoaderThread
  extends HandlerThread
  implements Handler.Callback
{
  private Handler mLoaderThreadHandler;
  private final ArrayList<String> mNameList = Lists.newArrayList();
  private final ArrayList<Long> mPhotoIds = Lists.newArrayList();
  private final ArrayList<String> mPhotoIdsAsStrings = Lists.newArrayList();
  private final ContentResolver mResolver;
  private final StringBuilder mStringBuilder = new StringBuilder();
  
  public ContactPhotoLoader$LoaderThread(ContactPhotoLoader paramContactPhotoLoader, ContentResolver paramContentResolver)
  {
    super("ContactPhotoLoader");
    mResolver = paramContentResolver;
  }
  
  private void loadContactNamesPhoto()
  {
    int j = mNameList.size();
    if (j == 0) {}
    for (;;)
    {
      return;
      int i = 0;
      while (i < j)
      {
        ContactPhotoLoader.access$700(this$0, (String)mNameList.get(i));
        i += 1;
      }
    }
  }
  
  private void loadContactPhotosFromDatabase()
  {
    int j = mPhotoIds.size();
    if (j == 0) {}
    for (;;)
    {
      return;
      mStringBuilder.setLength(0);
      mStringBuilder.append("_id IN(");
      int i = 0;
      while (i < j)
      {
        if (i != 0) {
          mStringBuilder.append(',');
        }
        mStringBuilder.append('?');
        i += 1;
      }
      mStringBuilder.append(')');
      Object localObject1 = null;
      try
      {
        Cursor localCursor = mResolver.query(ContactsContract.Data.CONTENT_URI, ContactPhotoLoader.access$400(this$0), mStringBuilder.toString(), (String[])mPhotoIdsAsStrings.toArray(ContactPhotoLoader.access$500()), null);
        if (localCursor != null) {
          for (;;)
          {
            localObject1 = localCursor;
            if (!localCursor.moveToNext()) {
              break;
            }
            localObject1 = localCursor;
            Long localLong = Long.valueOf(localCursor.getLong(0));
            localObject1 = localCursor;
            byte[] arrayOfByte = localCursor.getBlob(1);
            localObject1 = localCursor;
            ContactPhotoLoader.access$600(this$0, localLong.longValue(), arrayOfByte);
            localObject1 = localCursor;
            mPhotoIds.remove(localLong);
          }
        }
        if (localObject2 == null) {
          break label228;
        }
      }
      finally
      {
        if (localObject1 != null) {
          ((Cursor)localObject1).close();
        }
      }
      ((Cursor)localObject2).close();
      label228:
      j = mPhotoIds.size();
      i = 0;
      while (i < j)
      {
        ContactPhotoLoader.access$600(this$0, ((Long)mPhotoIds.get(i)).longValue(), null);
        i += 1;
      }
    }
  }
  
  public boolean handleMessage(Message paramMessage)
  {
    switch (what)
    {
    }
    for (;;)
    {
      return true;
      ContactPhotoLoader.access$100(this$0, mPhotoIds, mPhotoIdsAsStrings, mNameList);
      loadContactPhotosFromDatabase();
      loadContactNamesPhoto();
      ContactPhotoLoader.access$200(this$0).sendEmptyMessage(2);
      continue;
      boolean bool = ((Boolean)obj).booleanValue();
      ContactPhotoLoader.access$300(this$0, bool);
    }
  }
  
  public void requestClear(boolean paramBoolean)
  {
    if (mLoaderThreadHandler == null) {
      mLoaderThreadHandler = new Handler(getLooper(), this);
    }
    mLoaderThreadHandler.removeMessages(1);
    Message.obtain(mLoaderThreadHandler, 1, Boolean.valueOf(paramBoolean)).sendToTarget();
  }
  
  public void requestLoading()
  {
    if (mLoaderThreadHandler == null) {
      mLoaderThreadHandler = new Handler(getLooper(), this);
    }
    mLoaderThreadHandler.removeMessages(0);
    mLoaderThreadHandler.sendEmptyMessage(0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.ContactPhotoLoader.LoaderThread
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
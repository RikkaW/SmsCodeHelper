package com.android.mms.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Sms.Inbox;
import android.util.Log;
import com.android.mms.transaction.MessagingNotification;

final class Conversation$5
  implements Runnable
{
  Conversation$5(Context paramContext, int paramInt) {}
  
  public void run()
  {
    if (Thread.currentThread().isInterrupted()) {}
    for (;;)
    {
      return;
      ContentResolver localContentResolver = val$context.getContentResolver();
      long l = System.currentTimeMillis();
      int i = 0;
      Uri localUri = Telephony.Sms.Inbox.CONTENT_URI;
      if (val$category > 1) {
        localUri = ContentUris.withAppendedId(Conversation.access$900(), val$category);
      }
      localObject3 = localContentResolver.query(localUri, Conversation.access$700(), "(advanced_seen=1 OR advanced_seen=2)", null, null);
      if (localObject3 != null) {}
      try
      {
        if (((Cursor)localObject3).moveToFirst()) {
          i = ((Cursor)localObject3).getInt(0);
        }
        ((Cursor)localObject3).close();
        if (Thread.currentThread().isInterrupted()) {
          continue;
        }
        if (i > 0)
        {
          localObject3 = new ContentValues(2);
          ((ContentValues)localObject3).put("seen", Integer.valueOf(1));
          ((ContentValues)localObject3).put("advanced_seen", Integer.valueOf(3));
          localContentResolver.update(localUri, (ContentValues)localObject3, "(advanced_seen=1 OR advanced_seen=2)", null);
        }
        Log.d("Mms/conv", "count:" + i + ",update ad_seen sp sms:" + (System.currentTimeMillis() - l) + "ms");
        if (Thread.currentThread().isInterrupted()) {
          continue;
        }
        i = 0;
        l = System.currentTimeMillis();
        localUri = Telephony.Mms.Inbox.CONTENT_URI;
        if (val$category > 1) {
          localUri = ContentUris.withAppendedId(Conversation.access$1000(), val$category);
        }
        localObject3 = localContentResolver.query(localUri, Conversation.access$700(), "(advanced_seen=1 OR advanced_seen=2)", null, null);
        if (localObject3 == null) {}
      }
      finally
      {
        try
        {
          if (((Cursor)localObject3).moveToFirst()) {
            i = ((Cursor)localObject3).getInt(0);
          }
          ((Cursor)localObject3).close();
          if (Thread.currentThread().isInterrupted()) {
            continue;
          }
          if (i > 0)
          {
            localObject3 = new ContentValues(2);
            ((ContentValues)localObject3).put("seen", Integer.valueOf(1));
            ((ContentValues)localObject3).put("advanced_seen", Integer.valueOf(3));
            localContentResolver.update(localUri, (ContentValues)localObject3, "(advanced_seen=1 OR advanced_seen=2)", null);
          }
          Log.d("Mms/conv", "count:" + i + ",update ad_seen sp mms:" + (System.currentTimeMillis() - l) + "ms");
          if (Thread.currentThread().isInterrupted()) {
            continue;
          }
          l = System.currentTimeMillis();
          MessagingNotification.blockingUpdateAllNotifications(val$context);
          Log.d("Mms/conv", "update ad_seen sp update all notification:" + (System.currentTimeMillis() - l) + "ms");
          return;
        }
        finally
        {
          ((Cursor)localObject3).close();
        }
        localObject1 = finally;
        ((Cursor)localObject3).close();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.Conversation.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
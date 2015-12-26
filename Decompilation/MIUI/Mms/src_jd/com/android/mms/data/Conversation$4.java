package com.android.mms.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Sms.Inbox;
import android.util.Log;
import com.android.mms.transaction.MessagingNotification;

final class Conversation$4
  implements Runnable
{
  Conversation$4(Context paramContext) {}
  
  public void run()
  {
    if (Thread.currentThread().isInterrupted()) {}
    for (;;)
    {
      return;
      ContentResolver localContentResolver = val$context.getContentResolver();
      long l = System.currentTimeMillis();
      int i = 0;
      localObject5 = localContentResolver.query(Telephony.Sms.Inbox.CONTENT_URI, Conversation.access$700(), "seen=0 AND advanced_seen=0", null, null);
      if (localObject5 != null) {}
      try
      {
        if (((Cursor)localObject5).moveToFirst()) {
          i = ((Cursor)localObject5).getInt(0);
        }
        ((Cursor)localObject5).close();
        if (Thread.currentThread().isInterrupted()) {
          continue;
        }
        if (i > 0)
        {
          localObject5 = new ContentValues(2);
          ((ContentValues)localObject5).put("seen", Integer.valueOf(1));
          ((ContentValues)localObject5).put("advanced_seen", Integer.valueOf(3));
          localContentResolver.update(Telephony.Sms.Inbox.CONTENT_URI, (ContentValues)localObject5, "seen = 0 AND advanced_seen=0", null);
        }
        System.currentTimeMillis();
        Log.d("Mms/conv", "count:" + i + ",update notified non-sp sms:" + (System.currentTimeMillis() - l) + "ms");
        if (Thread.currentThread().isInterrupted()) {
          continue;
        }
        i = 0;
        l = System.currentTimeMillis();
        localObject5 = localContentResolver.query(Telephony.Mms.Inbox.CONTENT_URI, Conversation.access$700(), "seen=0 AND advanced_seen=0", null, null);
        if (localObject5 == null) {}
      }
      finally
      {
        try
        {
          if (((Cursor)localObject5).moveToFirst()) {
            i = ((Cursor)localObject5).getInt(0);
          }
          ((Cursor)localObject5).close();
          if (Thread.currentThread().isInterrupted()) {
            continue;
          }
          if (i > 0)
          {
            localObject5 = new ContentValues(2);
            ((ContentValues)localObject5).put("seen", Integer.valueOf(1));
            ((ContentValues)localObject5).put("advanced_seen", Integer.valueOf(3));
            localContentResolver.update(Telephony.Mms.Inbox.CONTENT_URI, (ContentValues)localObject5, "seen=0 AND advanced_seen=0", null);
          }
          Log.d("Mms/conv", "count:" + i + ",update notified non-sp mms:" + (System.currentTimeMillis() - l) + "ms");
          l = System.currentTimeMillis();
          i = 0;
          localObject5 = localContentResolver.query(Telephony.Sms.Inbox.CONTENT_URI, Conversation.access$700(), "seen=0 AND advanced_seen=1", null, null);
          if (localObject5 == null) {}
        }
        finally
        {
          ((Cursor)localObject5).close();
        }
        try
        {
          if (((Cursor)localObject5).moveToFirst()) {
            i = ((Cursor)localObject5).getInt(0);
          }
          ((Cursor)localObject5).close();
          if (i > 0)
          {
            localObject5 = new ContentValues(2);
            ((ContentValues)localObject5).put("seen", Integer.valueOf(1));
            ((ContentValues)localObject5).put("advanced_seen", Integer.valueOf(2));
            localContentResolver.update(Telephony.Sms.Inbox.CONTENT_URI, (ContentValues)localObject5, "seen=0 AND advanced_seen=1", null);
          }
          Log.d("Mms/conv", "count:" + i + ",update notified sp sms:" + (System.currentTimeMillis() - l) + "ms");
          if (Thread.currentThread().isInterrupted()) {
            continue;
          }
          l = System.currentTimeMillis();
          i = 0;
          localObject5 = localContentResolver.query(Telephony.Mms.Inbox.CONTENT_URI, Conversation.access$700(), "seen=0 AND advanced_seen=1", null, null);
          if (localObject5 == null) {}
        }
        finally
        {
          ((Cursor)localObject5).close();
        }
        try
        {
          if (((Cursor)localObject5).moveToFirst()) {
            i = ((Cursor)localObject5).getInt(0);
          }
          ((Cursor)localObject5).close();
          if (Thread.currentThread().isInterrupted()) {
            continue;
          }
          if (i > 0)
          {
            localObject5 = new ContentValues(2);
            ((ContentValues)localObject5).put("seen", Integer.valueOf(1));
            ((ContentValues)localObject5).put("advanced_seen", Integer.valueOf(2));
            localContentResolver.update(Telephony.Mms.Inbox.CONTENT_URI, (ContentValues)localObject5, "seen=0 AND advanced_seen=1", null);
          }
          Log.d("Mms/conv", "count:" + i + ",update notified sp mms:" + (System.currentTimeMillis() - l) + "ms");
          if (Thread.currentThread().isInterrupted()) {
            continue;
          }
          Conversation.access$800(val$context);
          if (Thread.currentThread().isInterrupted()) {
            continue;
          }
          l = System.currentTimeMillis();
          MessagingNotification.blockingUpdateAllNotifications(val$context);
          Log.d("Mms/conv", "update notified sp update all notification:" + (System.currentTimeMillis() - l) + "ms");
          return;
        }
        finally
        {
          ((Cursor)localObject5).close();
        }
        localObject1 = finally;
        ((Cursor)localObject5).close();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.Conversation.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
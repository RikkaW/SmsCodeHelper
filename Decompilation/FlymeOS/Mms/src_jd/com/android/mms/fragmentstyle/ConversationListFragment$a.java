package com.android.mms.fragmentstyle;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Threads;
import android.util.Log;
import com.android.mms.MmsApp;

class ConversationListFragment$a
  extends AsyncTask<Intent, Void, Void>
{
  private Context b;
  
  public ConversationListFragment$a(ConversationListFragment paramConversationListFragment, Context paramContext)
  {
    b = paramContext;
  }
  
  private boolean a(ContentResolver paramContentResolver, long paramLong, String[] paramArrayOfString)
  {
    String str = "thread_id = " + paramLong + " and " + "msg_box" + " = " + 3;
    paramArrayOfString = paramContentResolver.query(Telephony.Mms.CONTENT_URI, paramArrayOfString, str, null, null);
    if (paramArrayOfString != null)
    {
      boolean bool = false;
      try
      {
        while (paramArrayOfString.moveToNext())
        {
          if (paramContentResolver.delete(ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, paramArrayOfString.getInt(0)), null, null) > 0) {
            bool = true;
          }
          Log.v("ConversationList", "del draft threadId " + paramLong + "--- Mms " + paramArrayOfString.getInt(0));
        }
      }
      finally
      {
        paramArrayOfString.close();
      }
      return bool;
    }
    return false;
  }
  
  protected Void a(Intent... paramVarArgs)
  {
    Object localObject;
    int j;
    if (paramVarArgs[0].getAction().equals("com.meizu.delmsg"))
    {
      localObject = Telephony.Threads.CONTENT_URI.buildUpon().appendQueryParameter("simple", "true").build();
      paramVarArgs = b.getContentResolver();
      localObject = paramVarArgs.query((Uri)localObject, new String[] { "_id", "recipient_ids", "message_count" }, "recipient_ids = '' ", null, null);
      if (localObject == null) {
        break label311;
      }
      j = 0;
    }
    int k;
    for (int i = 0;; i = k)
    {
      for (;;)
      {
        try
        {
          if (((Cursor)localObject).moveToNext())
          {
            long l = ((Cursor)localObject).getLong(0);
            if (((Cursor)localObject).getInt(2) == 0)
            {
              paramVarArgs.delete(ContentUris.withAppendedId(Telephony.Threads.CONTENT_URI, l), null, null);
              Log.v("ConversationList", "del draft thread " + ((Cursor)localObject).getInt(0));
              k = i;
              break;
            }
            k = i;
            if (l <= 0L) {
              break;
            }
            boolean bool = a(paramVarArgs, l, new String[] { "_id", "thread_id", "msg_box" });
            k = i;
            if (!bool) {
              break;
            }
            k = 1;
            break;
          }
          ((Cursor)localObject).close();
          if (MmsApp.a)
          {
            if ((i != 0) || ((j > 0) && (i == 0))) {
              ConversationListFragment.f(a).run();
            }
            if (j > 0) {
              ConversationListFragment.c(a, false);
            }
            MmsApp.c().a(false);
            a.b = null;
            return null;
          }
        }
        finally
        {
          ((Cursor)localObject).close();
        }
        if (i != 0)
        {
          ConversationListFragment.f(a).run();
          continue;
          label311:
          j = 0;
          i = 0;
        }
      }
      j += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.ConversationListFragment.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
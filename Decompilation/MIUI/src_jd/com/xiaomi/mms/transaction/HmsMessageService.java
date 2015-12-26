package com.xiaomi.mms.transaction;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MiuiSettings.AntiSpam;
import android.provider.Telephony.MmsSms;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.util.VoiceReportUtils;
import java.util.HashSet;
import java.util.Set;
import miui.provider.ExtraTelephony.Hms;

public class HmsMessageService
  extends IntentService
{
  private Context mContext = null;
  
  public HmsMessageService()
  {
    super("HmsMessageService");
  }
  
  private void deleteAllHmsMessage(Context paramContext)
  {
    Log.d("HmsMessageService", "device login out , and start delete all hms message");
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Cursor localCursor = localContentResolver.query(ExtraTelephony.Hms.CONTENT_URI, new String[] { "thread_id" }, null, null, null);
    Object localObject = null;
    paramContext = (Context)localObject;
    if (localCursor != null) {
      paramContext = (Context)localObject;
    }
    try
    {
      if (localCursor.moveToFirst()) {
        paramContext = new HashSet();
      }
    }
    finally
    {
      try
      {
        boolean bool;
        do
        {
          paramContext.add(Integer.valueOf(localCursor.getInt(0)));
          bool = localCursor.moveToNext();
        } while (bool);
        if ((localCursor != null) && (!localCursor.isClosed())) {
          localCursor.close();
        }
        if (paramContext != null) {
          localContentResolver.delete(Uri.withAppendedPath(Telephony.MmsSms.CONTENT_URI, "hms"), "_id IN  ( " + TextUtils.join(",", paramContext) + " )", null);
        }
        return;
      }
      finally {}
      paramContext = finally;
    }
    if ((localCursor != null) && (!localCursor.isClosed())) {
      localCursor.close();
    }
    throw paramContext;
  }
  
  private void deleteEmptyHmsThread(long paramLong)
  {
    if (paramLong > 0L)
    {
      Uri localUri = Uri.withAppendedPath(Telephony.MmsSms.CONTENT_URI, "hms");
      getContentResolver().delete(localUri, "_id =" + paramLong + " AND " + "message_count=0", null);
    }
  }
  
  private void notifyHmsMessageArrivalAsync(Context paramContext, String paramString1, String paramString2)
  {
    if (!MiuiSettings.AntiSpam.isQuietModeEnable(paramContext)) {
      VoiceReportUtils.voiceReport(paramContext, paramString1, paramString2, 0);
    }
    MessagingNotification.blockingUpdateNewMessageIndicator(paramContext, true, false);
  }
  
  public void onCreate()
  {
    super.onCreate();
    mContext = getBaseContext();
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    if ("com.xiaomi.mms.action_hms_notification".equals(paramIntent.getAction()))
    {
      String str = paramIntent.getStringExtra("extra_hms_notification_address");
      paramIntent = paramIntent.getStringExtra("extra_hms_notification_body");
      notifyHmsMessageArrivalAsync(mContext, str, paramIntent);
    }
    do
    {
      return;
      if ("com.xiaomi.mms.action_del_hms_empty_thread".equals(paramIntent.getAction()))
      {
        deleteEmptyHmsThread(paramIntent.getLongExtra("extra_hms_thread_id", -1L));
        return;
      }
    } while (!"com.xiaomi.mms.action_delete_hms_message".equals(paramIntent.getAction()));
    deleteAllHmsMessage(mContext);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.HmsMessageService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
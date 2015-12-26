package com.xiaomi.mms.transaction;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.Telephony.Mms;
import android.text.TextUtils;
import com.android.mms.data.Contact;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.utils.MxMessagePduHelper;

public class MxMessageTrackService
  extends IntentService
{
  public MxMessageTrackService()
  {
    super("MxMessageTrackService");
  }
  
  private String getAddrForMsg(long paramLong)
  {
    Object localObject1 = null;
    Object localObject3 = null;
    Object localObject4 = Telephony.Mms.CONTENT_URI.buildUpon().appendPath(String.valueOf(paramLong)).appendPath("addr").build();
    localObject4 = SqliteWrapper.query(this, getContentResolver(), (Uri)localObject4, null, "type=151", null, null);
    if (localObject4 != null) {
      localObject1 = localObject3;
    }
    try
    {
      if (((Cursor)localObject4).moveToFirst()) {
        localObject1 = ((Cursor)localObject4).getString(((Cursor)localObject4).getColumnIndexOrThrow("address"));
      }
      return (String)localObject1;
    }
    finally
    {
      ((Cursor)localObject4).close();
    }
  }
  
  private void scheduleNext(long paramLong)
  {
    Object localObject = new Intent("com.xiaomi.mms.mx.ACTION_START_TRACK");
    ((Intent)localObject).setPackage(getPackageName());
    localObject = PendingIntent.getService(this, 0, (Intent)localObject, 268435456);
    ((AlarmManager)getSystemService("alarm")).set(0, paramLong, (PendingIntent)localObject);
  }
  
  public static void startTrack(Context paramContext)
  {
    Intent localIntent = new Intent("com.xiaomi.mms.mx.ACTION_START_TRACK");
    localIntent.setPackage(paramContext.getPackageName());
    paramContext.startService(localIntent);
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    if (paramIntent == null) {}
    while (!"com.xiaomi.mms.mx.ACTION_START_TRACK".equals(paramIntent.getAction())) {
      return;
    }
    paramIntent = MessageUtils.getExpiredMxSms(this);
    if (paramIntent != null) {}
    for (;;)
    {
      try
      {
        if (paramIntent.moveToFirst())
        {
          j = paramIntent.getColumnIndexOrThrow("mx_id");
          int k = paramIntent.getColumnIndexOrThrow("address");
          int m = paramIntent.getColumnIndexOrThrow("sim_id");
          i = paramIntent.getColumnIndexOrThrow("b2c_numbers");
          l1 = paramIntent.getLong(j);
          String str2 = paramIntent.getString(k);
          j = MSimUtils.getSlotIdBySimInfoId(paramIntent.getLong(m));
          str1 = paramIntent.getString(i);
          if (!TextUtils.isEmpty(str2))
          {
            str2 = Contact.get(str2).getMxPhoneNumber();
            if (str2 != null) {
              MxIdCache.offline(str2);
            }
          }
          if (!TextUtils.isEmpty(str1))
          {
            bool = true;
            MessageUtils.handleMxSmsFailed(this, l1, j, bool);
            bool = paramIntent.moveToNext();
            if (bool) {
              continue;
            }
          }
        }
        else
        {
          paramIntent.close();
          paramIntent = MxMessagePduHelper.getExpiredMxMms(this);
          if (paramIntent == null) {}
        }
      }
      finally
      {
        int j;
        int i;
        long l1;
        String str1;
        boolean bool;
        long l2;
        paramIntent.close();
      }
      try
      {
        if (paramIntent.moveToFirst())
        {
          i = paramIntent.getColumnIndexOrThrow("_id");
          j = paramIntent.getColumnIndexOrThrow("mx_id");
          l1 = paramIntent.getLong(i);
          paramIntent.getLong(j);
          str1 = getAddrForMsg(l1);
          if (!TextUtils.isEmpty(str1))
          {
            str1 = Contact.get(str1).getMxPhoneNumber();
            if (str1 != null) {
              MxIdCache.offline(str1);
            }
          }
          MxMessagePduHelper.setResponseStatus(this, ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, l1), 224);
          MxMessagePduHelper.handleMxMmsFailed(this, l1);
          bool = paramIntent.moveToNext();
          if (bool) {
            continue;
          }
        }
        paramIntent.close();
        paramIntent = MessageUtils.getUncompletedMxSms(this);
        l2 = Long.MAX_VALUE;
        l1 = l2;
        if (paramIntent != null) {
          l1 = l2;
        }
      }
      finally
      {
        paramIntent.close();
      }
      try
      {
        if (paramIntent.moveToFirst())
        {
          i = paramIntent.getColumnIndexOrThrow("mx_id");
          j = paramIntent.getColumnIndexOrThrow("out_time");
          paramIntent.getLong(i);
          l1 = paramIntent.getLong(j);
          l1 += 300000L;
        }
        paramIntent.close();
        paramIntent = MxMessagePduHelper.getIncompleteMxMms(this);
        l2 = l1;
        if (paramIntent != null) {
          l2 = l1;
        }
      }
      finally
      {
        paramIntent.close();
      }
      try
      {
        if (paramIntent.moveToFirst())
        {
          i = paramIntent.getColumnIndexOrThrow("mx_id");
          j = paramIntent.getColumnIndexOrThrow("out_time");
          paramIntent.getLong(i);
          l2 = paramIntent.getLong(j);
          long l3 = l2 + 300000L;
          l2 = l1;
          if (l3 < l1) {
            l2 = l3;
          }
        }
        paramIntent.close();
        if (l2 >= Long.MAX_VALUE) {
          break;
        }
        scheduleNext(l2);
        return;
      }
      finally
      {
        paramIntent.close();
      }
      bool = false;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MxMessageTrackService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
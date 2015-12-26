package com.android.mms.transaction.flyme;

import abl;
import abl.a;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Mms.Outbox;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.transaction.BackgroundService;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.ui.MessagingPreferenceActivity;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzEncodedStringValue;
import com.meizu.android.mms.pdu.MzGenericPdu;
import com.meizu.android.mms.pdu.MzNotificationInd;
import com.meizu.android.mms.pdu.MzPduParser;
import com.meizu.android.mms.pdu.MzPduPersister;
import ga;
import wd;
import zn;

class FlymePushReceiver$a
  extends AsyncTask<Intent, Void, Void>
{
  private Context b;
  
  public FlymePushReceiver$a(FlymePushReceiver paramFlymePushReceiver, Context paramContext)
  {
    b = paramContext;
  }
  
  protected Void a(Intent... paramVarArgs)
  {
    paramVarArgs = paramVarArgs[0];
    if ("application/vnd.flyme.mms-message".equals(paramVarArgs.getType())) {}
    Object localObject4;
    Object localObject1;
    long l1;
    for (int i = 2;; i = 1)
    {
      localObject4 = paramVarArgs.getStringExtra("filename");
      localObject1 = paramVarArgs.getStringExtra("mimetype");
      l1 = paramVarArgs.getLongExtra("mmssize", 0L);
      Log.e("FlymePushReceiver", "msgProtocol is " + i + "-- attachFileName = " + (String)localObject4 + "-- attachsize = " + l1 + "-- attachmimetype = " + (String)localObject1);
      paramVarArgs = new MzPduParser(paramVarArgs.getByteArrayExtra("data"), i).parse();
      if (paramVarArgs != null) {
        break;
      }
      Log.e("FlymePushReceiver", "Invalid PUSH data");
      return null;
    }
    Object localObject3 = MzPduPersister.getPduPersister(b);
    Object localObject5 = b.getContentResolver();
    int j = paramVarArgs.getMessageType();
    Log.d("FlymePushReceiver", "MessageType : " + j);
    switch (j)
    {
    default: 
    case 134: 
    case 136: 
      try
      {
        Log.e("FlymePushReceiver", "Received unrecognized PDU.");
      }
      catch (MmsException paramVarArgs)
      {
        Log.e("FlymePushReceiver", "Failed to save the data from PUSH: type=" + j, paramVarArgs);
      }
      catch (RuntimeException paramVarArgs)
      {
        try
        {
          int k;
          while ((paramVarArgs.getCount() == k) && (paramVarArgs.moveToNext())) {
            i = wd.a(i, paramVarArgs.getInt(1));
          }
          paramVarArgs.close();
          Log.d("FlymePushReceiver", "Delivery Status : " + i);
          if (i == 0) {
            break label1596;
          }
          paramVarArgs = new ContentValues(1);
          paramVarArgs.put("delivery_status", Integer.valueOf(i));
          ((ContentResolver)localObject5).update(Telephony.Mms.Outbox.CONTENT_URI, paramVarArgs, "_id=" + b, null);
        }
        finally
        {
          paramVarArgs.close();
        }
        paramVarArgs = paramVarArgs;
        Log.e("FlymePushReceiver", "Unexpected RuntimeException.", paramVarArgs);
      }
      localObject1 = FlymePushReceiver.a(b, paramVarArgs, j);
      l1 = a;
      if (l1 == -1L) {
        break label1596;
      }
      localObject4 = new ContentValues(3);
      ((ContentValues)localObject4).put("thread_id", Long.valueOf(l1));
      ((ContentValues)localObject4).put("read", Integer.valueOf(1));
      ((ContentValues)localObject4).put("protocol", Integer.valueOf(i));
      ((MzPduPersister)localObject3).persist(paramVarArgs, Telephony.Mms.Inbox.CONTENT_URI, true, MessagingPreferenceActivity.b(b), null, 2, (ContentValues)localObject4);
      if (134 != j) {
        break label1596;
      }
      paramVarArgs = ((ContentResolver)localObject5).query(Uri.withAppendedPath(Telephony.Mms.REPORT_REQUEST_URI, String.valueOf(b)), FlymePushReceiver.b, null, null, null);
      if (paramVarArgs == null) {
        return null;
      }
      try
      {
        k = paramVarArgs.getCount();
        paramVarArgs.close();
        paramVarArgs = ((ContentResolver)localObject5).query(Uri.withAppendedPath(Telephony.Mms.REPORT_STATUS_URI, String.valueOf(b)), FlymePushReceiver.a, null, null, null);
        if (paramVarArgs != null) {
          break label1591;
        }
        return null;
      }
      finally
      {
        paramVarArgs.close();
      }
    }
    ((MzNotificationInd)localObject5).setSubject(new MzEncodedStringValue(b.getString(2131493480)));
    Object localObject6;
    if (ga.h())
    {
      localObject6 = ((MzNotificationInd)localObject5).getContentLocation();
      if (61 == localObject6[(localObject6.length - 1)])
      {
        byte[] arrayOfByte1 = ((MzNotificationInd)localObject5).getTransactionId();
        byte[] arrayOfByte2 = new byte[localObject6.length + arrayOfByte1.length];
        System.arraycopy(localObject6, 0, arrayOfByte2, 0, localObject6.length);
        System.arraycopy(arrayOfByte1, 0, arrayOfByte2, localObject6.length, arrayOfByte1.length);
        ((MzNotificationInd)localObject5).setContentLocation(arrayOfByte2);
      }
    }
    boolean bool1;
    boolean bool3;
    if (!FlymePushReceiver.a(b, (MzNotificationInd)localObject5))
    {
      localObject5 = ((MzNotificationInd)localObject5).getFrom().getString();
      long l2 = System.currentTimeMillis();
      bool1 = wd.i(b);
      Log.d("FlymePushReceiver", "isOn : " + bool1);
      if (bool1)
      {
        bool1 = wd.f(b, (String)localObject5);
        Log.d("FlymePushReceiver", "isContactRecord : " + bool1);
        if (!bool1) {
          return null;
        }
      }
      localObject6 = abl.a((String)localObject5, null, l2);
      if (b)
      {
        paramVarArgs = new ContentValues();
        paramVarArgs.put("address", (String)localObject5);
        paramVarArgs.put("date", Long.valueOf(l2));
        paramVarArgs.put("association_id", Long.valueOf(l2));
        paramVarArgs.put("imsi", "-10");
        paramVarArgs.put("sim_id", Integer.valueOf(-10));
        paramVarArgs.put("protocol", Integer.valueOf(0));
        paramVarArgs.put("read", Integer.valueOf(0));
        paramVarArgs.put("reply_path_present", Integer.valueOf(0));
        paramVarArgs.put("body", "mms notify");
        paramVarArgs.put("locked", Integer.valueOf(1));
        abl.a(MmsApp.c(), paramVarArgs, (abl.a)localObject6);
        return null;
      }
      if (str.contains("file#")) {
        break label1598;
      }
      if (str.contains("#video"))
      {
        break label1598;
        boolean bool2 = bool1;
        if (bool1)
        {
          bool2 = bool1;
          if (wd.f())
          {
            bool2 = bool1;
            if (!wd.c(l1))
            {
              bool2 = false;
              MessagingNotification.g(b);
            }
          }
        }
        bool1 = wd.i("/sdcard/Download/FMessage/");
        bool3 = wd.f(b);
        wd.b(true);
        localObject5 = new ContentValues(3);
        ((ContentValues)localObject5).put("file_link", "/sdcard/Download/FMessage/" + (String)localObject4);
        ((ContentValues)localObject5).put("slideshow_description", str);
        ((ContentValues)localObject5).put("protocol", Integer.valueOf(i));
        if (l1 > 0L) {
          ((ContentValues)localObject5).put("m_size", Long.valueOf(l1));
        }
        ((ContentValues)localObject5).put("association_id", new Long(System.currentTimeMillis()));
        ((ContentValues)localObject5).put("imsi", "-10");
        ((ContentValues)localObject5).put("sim_id", Integer.valueOf(-10));
        if (!FlymePushReceiver.a(a, str, l1)) {
          break label1498;
        }
        ((ContentValues)localObject5).put("thread_id", Integer.valueOf(64536));
        ((ContentValues)localObject5).put("seen", Integer.valueOf(1));
        ((ContentValues)localObject5).put("read", Integer.valueOf(1));
        paramVarArgs = ((MzPduPersister)localObject3).persist(paramVarArgs, Telephony.Mms.Inbox.CONTENT_URI, false, MessagingPreferenceActivity.b(b), null, 2, (ContentValues)localObject5);
        label1244:
        wd.b(false);
        localObject3 = new Intent(b, FlymeTransactionService.class);
        ((Intent)localObject3).putExtra("bundle_uri", paramVarArgs.toString());
        ((Intent)localObject3).putExtra("type", 0);
        if ((bool2) && (bool3) && (bool1))
        {
          if (b.startService((Intent)localObject3) == null) {
            break label1523;
          }
          zn.a().a(paramVarArgs, 131, -1);
        }
        label1325:
        Log.d("FlymePushReceiver", "### haveNetwork : " + bool3 + " autoDownload : " + bool2 + " sdcardAvailable : " + bool1 + " ###");
        if ((FlymePushReceiver.a(a, str, l1)) || ((bool3) && (bool2) && (bool1))) {
          break label1604;
        }
        l1 = MessagingNotification.b(b, paramVarArgs);
        MessagingNotification.b(b, l1, false, paramVarArgs, false, false);
        break label1604;
      }
    }
    for (;;)
    {
      label1435:
      paramVarArgs = new Intent(b, BackgroundService.class);
      if (!bool3) {
        paramVarArgs.setAction("android.intent.action.LISTEN_NETWORK_STATUS");
      }
      for (;;)
      {
        Log.d("FlymePushReceiver", "Starting FlymeBackgroundService...");
        b.startService(paramVarArgs);
        break label1596;
        bool1 = wd.b(b, l1);
        break;
        label1498:
        paramVarArgs = ((MzPduPersister)localObject3).persist(paramVarArgs, Telephony.Mms.Inbox.CONTENT_URI, true, MessagingPreferenceActivity.b(b), null, 2, (ContentValues)localObject5);
        break label1244;
        label1523:
        Log.e("FlymePushReceiver", "can't start service transactionservice");
        break label1325;
        if (!bool1) {
          paramVarArgs.setAction("android.intent.action.LISTEN_SDCARD_STATUS");
        }
      }
      Log.v("FlymePushReceiver", "Skip downloading duplicate message: " + new String(((MzNotificationInd)localObject5).getContentLocation()));
      break label1596;
      label1591:
      i = 0;
      break;
      label1596:
      label1598:
      label1604:
      do
      {
        return null;
        bool1 = false;
        break;
        if (!bool3) {
          break label1435;
        }
      } while (bool1);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.flyme.FlymePushReceiver.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
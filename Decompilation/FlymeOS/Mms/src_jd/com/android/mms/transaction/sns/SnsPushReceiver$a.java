package com.android.mms.transaction.sns;

import abl;
import abl.a;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Telephony.Mms.Inbox;
import android.util.Log;
import com.android.mms.MmsApp;
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

class SnsPushReceiver$a
  extends AsyncTask<Intent, Void, Void>
{
  private Context b;
  
  public SnsPushReceiver$a(SnsPushReceiver paramSnsPushReceiver, Context paramContext)
  {
    b = paramContext;
  }
  
  protected Void a(Intent... paramVarArgs)
  {
    Object localObject2 = paramVarArgs[0];
    Object localObject3 = ((Intent)localObject2).getByteArrayExtra("data");
    paramVarArgs = ((Intent)localObject2).getStringExtra("filename");
    Object localObject1 = ((Intent)localObject2).getStringExtra("mimetype");
    long l1 = ((Intent)localObject2).getLongExtra("mmssize", 0L);
    Log.e("SnsPushReceiver", "attachFileName = " + paramVarArgs + "-- attachmimetype = " + (String)localObject1 + "-- attachsize = " + l1);
    localObject2 = new MzPduParser((byte[])localObject3).parse();
    if (localObject2 == null)
    {
      Log.e("SnsPushReceiver", "Invalid PUSH data");
      return null;
    }
    localObject3 = MzPduPersister.getPduPersister(b);
    b.getContentResolver();
    int i = ((MzGenericPdu)localObject2).getMessageType();
    Log.d("SnsPushReceiver", "MessageType : " + i);
    switch (i)
    {
    }
    for (;;)
    {
      boolean bool1;
      boolean bool2;
      boolean bool3;
      try
      {
        Log.e("SnsPushReceiver", "Received unrecognized PDU.");
        Log.v("SnsPushReceiver", "PUSH Intent processed.");
        return null;
        localObject4 = (MzNotificationInd)localObject2;
        Object localObject5;
        if (ga.h())
        {
          localObject5 = ((MzNotificationInd)localObject4).getContentLocation();
          if (61 == localObject5[(localObject5.length - 1)])
          {
            byte[] arrayOfByte1 = ((MzNotificationInd)localObject4).getTransactionId();
            byte[] arrayOfByte2 = new byte[localObject5.length + arrayOfByte1.length];
            System.arraycopy(localObject5, 0, arrayOfByte2, 0, localObject5.length);
            System.arraycopy(arrayOfByte1, 0, arrayOfByte2, localObject5.length, arrayOfByte1.length);
            ((MzNotificationInd)localObject4).setContentLocation(arrayOfByte2);
          }
        }
        if (!SnsPushReceiver.a(b, (MzNotificationInd)localObject4))
        {
          localObject4 = ((MzNotificationInd)localObject4).getFrom().getString();
          long l2 = System.currentTimeMillis();
          localObject5 = abl.a((String)localObject4, null, l2);
          if (b)
          {
            paramVarArgs = new ContentValues();
            paramVarArgs.put("address", (String)localObject4);
            paramVarArgs.put("date", Long.valueOf(l2));
            paramVarArgs.put("association_id", new Long(l2));
            paramVarArgs.put("protocol", Integer.valueOf(0));
            paramVarArgs.put("read", Integer.valueOf(0));
            paramVarArgs.put("reply_path_present", Integer.valueOf(0));
            paramVarArgs.put("body", "mms notify");
            paramVarArgs.put("locked", Integer.valueOf(1));
            abl.a(MmsApp.c(), paramVarArgs, (abl.a)localObject5);
            return null;
          }
          zn.a();
          bool1 = wd.h(b);
          if ((!bool1) || (!wd.f()) || (wd.c(l1))) {
            continue;
          }
          MessagingNotification.g(b);
          bool1 = false;
          bool2 = wd.i("/sdcard/Download/FMessage/");
          bool3 = wd.f(b);
          wd.b(true);
          localObject4 = new ContentValues(3);
          ((ContentValues)localObject4).put("file_link", paramVarArgs);
          ((ContentValues)localObject4).put("slideshow_description", (String)localObject1);
          ((ContentValues)localObject4).put("protocol", Integer.valueOf(3));
          if (l1 > 0L) {
            ((ContentValues)localObject4).put("m_size", Long.valueOf(l1));
          }
          ((ContentValues)localObject4).put("association_id", new Long(System.currentTimeMillis()));
          paramVarArgs = ((MzPduPersister)localObject3).persist((MzGenericPdu)localObject2, Telephony.Mms.Inbox.CONTENT_URI, true, MessagingPreferenceActivity.b(b), null, 3, (ContentValues)localObject4);
          wd.b(false);
          localObject1 = new Intent(b, SnsTransactionService.class);
          ((Intent)localObject1).putExtra("bundle_uri", paramVarArgs.toString());
          ((Intent)localObject1).putExtra("type", 0);
          Log.d("SnsPushReceiver", "### haveNetwork : " + bool3 + " autoDownload : " + bool1 + " sdcardAvailable : " + bool2 + " ###");
          if ((!bool1) || (!bool3) || (!bool2)) {
            break label851;
          }
          if (b.startService((Intent)localObject1) != null)
          {
            zn.a().a(paramVarArgs, 131, -1);
            break label851;
            l1 = MessagingNotification.b(b, paramVarArgs);
            MessagingNotification.b(b, l1, false, paramVarArgs, false, false);
            continue;
          }
        }
      }
      catch (MmsException paramVarArgs)
      {
        Log.e("SnsPushReceiver", "Failed to save the data from PUSH: type=" + i, paramVarArgs);
        continue;
        Log.e("SnsPushReceiver", "can't start service transactionservice");
      }
      catch (RuntimeException paramVarArgs)
      {
        Object localObject4;
        Log.e("SnsPushReceiver", "Unexpected RuntimeException.", paramVarArgs);
        continue;
        Log.v("SnsPushReceiver", "Skip downloading duplicate message: " + new String(((MzNotificationInd)localObject4).getContentLocation()));
        continue;
        continue;
      }
      label851:
      if ((bool1) && (bool3)) {
        if (bool2) {}
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.sns.SnsPushReceiver.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
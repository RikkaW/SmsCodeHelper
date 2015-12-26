package com.android.mms.transaction;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import com.android.mms.data.Contact;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.data.MidPhoneMap;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.MxIdCache.MxCacheStatusListener;
import com.xiaomi.mms.data.MxIdCache.MxIdCacheItem;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.MxTaskService;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.B2cMessageUtils;
import java.util.HashSet;
import miui.telephony.PhoneNumberUtils.PhoneNumber;

public class SendWebMessageService
  extends Service
{
  private MxIdCache.MxCacheStatusListener mListener = new MxIdCache.MxCacheStatusListener()
  {
    public void onMxIdAdded(String paramAnonymousString1, String paramAnonymousString2) {}
    
    public void onMxIdOffline(String paramAnonymousString1, final String paramAnonymousString2)
    {
      mServiceHandler.post(new Runnable()
      {
        public void run()
        {
          SendWebMessageService.this.sendAndRemovePendingAddress(paramAnonymousString2, false, 0);
          SendWebMessageService.this.sendAndRemovePendingAddress(paramAnonymousString2, false, 1);
        }
      });
    }
    
    public void onMxIdOnline(String paramAnonymousString1, final String paramAnonymousString2)
    {
      mServiceHandler.post(new Runnable()
      {
        public void run()
        {
          SendWebMessageService.this.sendAndRemovePendingAddress(paramAnonymousString2, true, 0);
          SendWebMessageService.this.sendAndRemovePendingAddress(paramAnonymousString2, true, 1);
        }
      });
    }
  };
  private HashSet<Pair<String, Integer>> mPendingAddresses = new HashSet();
  private volatile Handler mServiceHandler;
  private Looper mServiceLooper;
  
  private String getCompareKey(String paramString)
  {
    paramString = PhoneNumberUtils.PhoneNumber.parse(paramString);
    String str = paramString.getPrefix() + paramString.getEffectiveNumber();
    paramString.recycle();
    return str;
  }
  
  private void send(String paramString, boolean paramBoolean, int paramInt)
  {
    send(paramString, paramBoolean, paramInt, false);
  }
  
  private void send(String paramString, boolean paramBoolean1, int paramInt, boolean paramBoolean2)
  {
    Uri localUri = Uri.parse("content://sms/queued");
    ContentValues localContentValues = new ContentValues(1);
    long l = MSimUtils.getSimIdBySlotId(paramInt);
    if (paramBoolean1) {}
    for (int i = 1;; i = 0)
    {
      localContentValues.put("mx_status", Integer.valueOf(i));
      if (paramBoolean2) {
        localContentValues.put("b2c_numbers", paramString);
      }
      SqliteWrapper.update(this, getContentResolver(), localUri, localContentValues, "sim_id=" + l + " AND " + "mx_status" + "=" + 196609 + " AND PHONE_NUMBERS_EQUAL(address, ?, 1)", new String[] { paramString });
      MSimUtils.sendQueuedMessage(this, paramInt);
      return;
    }
  }
  
  private void sendAfterQueryStatus(final String paramString, final int paramInt)
  {
    mPendingAddresses.add(Pair.create(getCompareKey(paramString), Integer.valueOf(paramInt)));
    MxTaskService.queryStatus(this, paramString);
    mServiceHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        SendWebMessageService.this.sendAndRemovePendingAddress(paramString, false, paramInt);
      }
    }, 10000L);
  }
  
  private void sendAndRemovePendingAddress(String paramString, boolean paramBoolean, int paramInt)
  {
    if (mPendingAddresses.remove(Pair.create(getCompareKey(paramString), Integer.valueOf(paramInt)))) {
      send(paramString, paramBoolean, paramInt);
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    HandlerThread localHandlerThread = new HandlerThread("SendWebMessageService");
    localHandlerThread.start();
    mServiceLooper = localHandlerThread.getLooper();
    mServiceHandler = new Handler(mServiceLooper);
    MxIdCache.addStatusListener(mListener);
  }
  
  public void onDestroy()
  {
    MxIdCache.removeStatusListener(mListener);
    mServiceLooper.quit();
    super.onDestroy();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (paramIntent == null) {
      return super.onStartCommand(paramIntent, paramInt1, paramInt2);
    }
    if ("com.android.mms.transaction.ACTION_SEND_WEB_MESSAGE".equals(paramIntent.getAction()))
    {
      final String str1 = paramIntent.getStringExtra("extra_address");
      final String str2 = paramIntent.getStringExtra("extra_mid");
      final int i = paramIntent.getIntExtra("extra_slotId", 0);
      mServiceHandler.post(new Runnable()
      {
        public void run()
        {
          if (B2cMessageUtils.isB2cNumber(Contact.get(str1)))
          {
            SendWebMessageService.this.send(str1, true, i, true);
            return;
          }
          if ((!MxActivateService.isMxEnabled(SendWebMessageService.this)) || (!PushSession.getInstance(SendWebMessageService.this).isConnected(i)))
          {
            SendWebMessageService.this.send(str1, false, i);
            return;
          }
          MxIdCache.MxIdCacheItem localMxIdCacheItem = MxIdCache.get(str1, true);
          if (localMxIdCacheItem == null)
          {
            if (TextUtils.isEmpty(str2))
            {
              SendWebMessageService.this.send(str1, false, i);
              return;
            }
            MidPhoneMap.put(str2, str1);
            MxIdCache.put(str1, str2);
            SendWebMessageService.this.sendAfterQueryStatus(str1, i);
            return;
          }
          if (localMxIdCacheItem.isExpired())
          {
            SendWebMessageService.this.sendAfterQueryStatus(str1, i);
            return;
          }
          SendWebMessageService.this.send(str1, localMxIdCacheItem.allowSms(), i);
        }
      });
    }
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SendWebMessageService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
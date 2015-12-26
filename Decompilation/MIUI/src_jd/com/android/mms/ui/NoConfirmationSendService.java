package com.android.mms.ui;

import android.app.Application;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.android.mms.LogTag;
import com.android.mms.MmsApp;
import com.android.mms.data.Conversation;
import com.android.mms.transaction.SmsMessageSender;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.MxIdCache.MxCacheStatusListener;
import com.xiaomi.mms.data.MxIdCache.MxIdCacheItem;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.MxTaskService;
import java.util.HashSet;
import java.util.Iterator;
import miui.telephony.SubscriptionManager;

public class NoConfirmationSendService
  extends IntentService
{
  public static MxIdCache.MxCacheStatusListener mListener = new MxIdCache.MxCacheStatusListener()
  {
    public void onMxIdAdded(String paramAnonymousString1, String paramAnonymousString2) {}
    
    public void onMxIdOffline(String paramAnonymousString1, String paramAnonymousString2)
    {
      NoConfirmationSendService.startSendPendingMessage(paramAnonymousString2, false, 0);
      NoConfirmationSendService.startSendPendingMessage(paramAnonymousString2, false, 1);
    }
    
    public void onMxIdOnline(String paramAnonymousString1, String paramAnonymousString2)
    {
      NoConfirmationSendService.startSendPendingMessage(paramAnonymousString2, true, 0);
      NoConfirmationSendService.startSendPendingMessage(paramAnonymousString2, true, 1);
    }
  };
  public static HashSet<Pair<String, Pair<String, Integer>>> mPendingMessages = new HashSet();
  private Handler mUiHandler = new Handler();
  
  public NoConfirmationSendService()
  {
    super(NoConfirmationSendService.class.getName());
    setIntentRedelivery(true);
  }
  
  public NoConfirmationSendService(String paramString)
  {
    super(paramString);
    setIntentRedelivery(true);
  }
  
  private void handleSend(String paramString1, int paramInt, String paramString2)
  {
    boolean bool = true;
    MxIdCache.MxIdCacheItem localMxIdCacheItem = MxIdCache.get(paramString1, true);
    if (localMxIdCacheItem == null)
    {
      sendAfterQueryStatus(paramString1, paramInt, paramString2);
      return;
    }
    if (localMxIdCacheItem.isExpired())
    {
      sendAfterQueryStatus(paramString1, paramInt, paramString2);
      return;
    }
    if ((localMxIdCacheItem.allowSms()) && (MxActivateService.isMxEnabled(this, paramInt))) {}
    for (;;)
    {
      send(paramString1, bool, paramInt, paramString2);
      return;
      bool = false;
    }
  }
  
  public static void startSendPendingMessage(String paramString, boolean paramBoolean, int paramInt)
  {
    Intent localIntent = new Intent("android.intent.action.SEND_AND_REMOVE_PENNDING_MESSAGE");
    localIntent.putExtra("extra_address", paramString);
    localIntent.putExtra("extra_usemx", paramBoolean);
    SubscriptionManager.putSlotIdExtra(localIntent, paramInt);
    localIntent.setPackage(MmsApp.getApp().getPackageName());
    MmsApp.getApp().startService(localIntent);
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    LogTag.debug("NoConfirmationSendService onHandleIntent", new Object[0]);
    if (paramIntent == null) {
      LogTag.warn("Null intent. Bail.", new Object[0]);
    }
    for (;;)
    {
      return;
      String str1 = paramIntent.getAction();
      int i;
      if (("com.android.mms.intent.action.SENDTO_NO_CONFIRMATION".equals(str1)) || ("android.intent.action.RESPOND_VIA_MESSAGE".equals(str1)))
      {
        Bundle localBundle = paramIntent.getExtras();
        if (localBundle == null)
        {
          LogTag.warn("Called to send SMS but no extras", new Object[0]);
          return;
        }
        str1 = localBundle.getString("android.intent.extra.TEXT");
        int j = MSimUtils.getSlotIdFromBundle(localBundle);
        LogTag.debug("onHandleIntent  slotId = " + j, new Object[0]);
        if (j >= 0)
        {
          i = j;
          if (MSimUtils.isSimInserted(j)) {}
        }
        else
        {
          i = MSimUtils.getInsertedSlotId();
          LogTag.debug("inserted slotId = " + i, new Object[0]);
          if (i == -1)
          {
            LogTag.error("No usable sim card", new Object[0]);
            return;
          }
        }
        String str2 = Conversation.getRecipients(paramIntent.getData());
        if (TextUtils.isEmpty(str2))
        {
          LogTag.warn("Recipient(s) cannot be empty", new Object[0]);
          return;
        }
        if (localBundle.getBoolean("showUI", false))
        {
          paramIntent.addFlags(268435456);
          ComposeMessageRouterActivity.route(this, paramIntent);
          return;
        }
        if (TextUtils.isEmpty(str1))
        {
          LogTag.warn("Message cannot be empty", new Object[0]);
          return;
        }
        paramIntent = TextUtils.split(str2, ";");
        int k = paramIntent.length;
        j = 0;
        while (j < k)
        {
          handleSend(paramIntent[j], i, str1);
          j += 1;
        }
      }
      else if ("android.intent.action.SEND_AND_REMOVE_PENNDING_MESSAGE".equals(str1))
      {
        str1 = paramIntent.getStringExtra("extra_address");
        i = MSimUtils.getSlotIdFromIntent(paramIntent);
        if ((paramIntent.getBooleanExtra("extra_usemx", false)) && (MxActivateService.isMxEnabled(this, i))) {}
        for (boolean bool = true; (!TextUtils.isEmpty(str1)) && (mPendingMessages.size() > 0); bool = false)
        {
          sendAndRemovePendingMessage(str1, bool, i);
          return;
        }
      }
    }
  }
  
  protected void send(String paramString1, boolean paramBoolean, int paramInt, String paramString2)
  {
    paramString1 = new SmsMessageSender(getBaseContext(), new String[] { paramString1 }, paramString2, 0L, 0L, paramBoolean, paramInt);
    try
    {
      paramString1.sendMessage();
      return;
    }
    catch (Exception paramString1)
    {
      Log.e("Mms/NoConfirmationSendService", "Failed to send SMS message, threadId=" + 0L, paramString1);
    }
  }
  
  protected void sendAfterQueryStatus(final String paramString1, final int paramInt, String paramString2)
  {
    if (mPendingMessages.size() == 0) {
      MxIdCache.addStatusListener(mListener);
    }
    mPendingMessages.add(Pair.create(paramString1, Pair.create(paramString2, Integer.valueOf(paramInt))));
    MxTaskService.queryStatus(this, paramString1);
    mUiHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        NoConfirmationSendService.startSendPendingMessage(paramString1, false, paramInt);
      }
    }, 10000L);
  }
  
  protected void sendAndRemovePendingMessage(String paramString, boolean paramBoolean, int paramInt)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = mPendingMessages.iterator();
    while (localIterator.hasNext())
    {
      Pair localPair = (Pair)localIterator.next();
      if (((String)first).equals(paramString))
      {
        if (((Integer)second).second).intValue() == paramInt) {
          send((String)first, paramBoolean, ((Integer)second).second).intValue(), (String)second).first);
        } else {
          localHashSet.add(localPair);
        }
      }
      else {
        localHashSet.add(localPair);
      }
    }
    mPendingMessages.clear();
    if (!localHashSet.isEmpty())
    {
      mPendingMessages.addAll(localHashSet);
      return;
    }
    MxIdCache.removeStatusListener(mListener);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NoConfirmationSendService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
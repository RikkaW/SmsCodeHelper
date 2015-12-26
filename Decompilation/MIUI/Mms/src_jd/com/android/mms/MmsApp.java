package com.android.mms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.drm.DrmManagerClient;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemProperties;
import android.preference.PreferenceManager;
import android.security.ChooseLockSettingsHelper;
import com.android.mms.data.Contact;
import com.android.mms.data.Conversation;
import com.android.mms.layout.LayoutManager;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.MmsSystemEventReceiver;
import com.android.mms.transaction.TimedMessageReceiver;
import com.android.mms.transaction.TransactionService;
import com.android.mms.ui.MessageListItem;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.MessagingPreferenceActivity;
import com.android.mms.ui.PreviewImageLoader;
import com.android.mms.ui.SmsTextSizeAdjust;
import com.android.mms.ui.ThumbnailView;
import com.android.mms.ui.TimedMessageExpiredActivity;
import com.android.mms.understand.UnderstandLoader;
import com.android.mms.update.Push;
import com.android.mms.update.UpdateManager;
import com.android.mms.util.DownloadManager;
import com.android.mms.util.DraftCache;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.MiStatSdkHelper;
import com.android.mms.util.RateController;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.accountsdk.activate.ActivateStatusReceiver;
import com.xiaomi.accountsdk.activate.ActivateStatusReceiver.ActivateStatusListener;
import com.xiaomi.accountsdk.activate.ActivateStatusReceiver.Event;
import com.xiaomi.micloudsdk.request.Request;
import com.xiaomi.mms.mx.common.CommonApplication;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.MxMessageTrackService;
import com.xiaomi.mms.utils.MxConfigCompat;
import com.xiaomi.mms.utils.MxRequestEnv;
import com.xiaomi.mms.utils.logger.MiXinDebugLog;
import com.xiaomi.mms.utils.logger.MyLog;
import miui.external.ApplicationDelegate;

public class MmsApp
  extends ApplicationDelegate
  implements ActivateStatusReceiver.ActivateStatusListener
{
  private static long mStartupTime;
  private static volatile ChooseLockSettingsHelper sChooseLockSettingsHelper;
  private static MmsApp sMmsApp = null;
  private DrmManagerClient mDrmManagerClient;
  private Handler mHandler;
  private Runnable mMmsDelayedCallback = new Runnable()
  {
    public void run()
    {
      android.app.Application localApplication = MmsApp.getApp();
      Push.initiate(localApplication);
      UpdateManager.init();
      MiStatSdkHelper.start(localApplication);
      UnderstandLoader.prepare();
      if (MessageUtils.isMessagingTemplateAllowed(localApplication))
      {
        MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_setting", "1");
        UnderstandLoader.init();
      }
      for (;;)
      {
        new Thread()
        {
          public void run()
          {
            android.app.Application localApplication = MmsApp.getApp();
            TimedMessageReceiver.scheduleNextTimedMsg(localApplication);
            TimedMessageExpiredActivity.markPrivateExpiredMsgFailed(localApplication);
            TimedMessageExpiredActivity.raiseIfNecessary(localApplication);
          }
        }.start();
        startService(new Intent(localApplication, TransactionService.class));
        MSimUtils.sendQueuedMessageNoToast(localApplication, MSimUtils.SLOT_ID_ALL);
        MxActivateService.enableAll(localApplication, false);
        MxMessageTrackService.startTrack(localApplication);
        mHandler.postDelayed(mMmsDelayedCallback1, 800L);
        return;
        if (MessageUtils.isUnderstandSupported()) {
          MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_setting", "0");
        }
      }
    }
  };
  private Runnable mMmsDelayedCallback1 = new Runnable()
  {
    public void run()
    {
      MessageListItem.initDummy();
      ThumbnailView.initDummy();
    }
  };
  
  public MmsApp()
  {
    mStartupTime = System.currentTimeMillis();
  }
  
  public static android.app.Application getApp()
  {
    try
    {
      miui.external.Application localApplication = sMmsApp.getApplication();
      return localApplication;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static ChooseLockSettingsHelper getChooseLockSettingsHelper()
  {
    if (sChooseLockSettingsHelper == null) {
      sChooseLockSettingsHelper = new ChooseLockSettingsHelper(sMmsApp);
    }
    return sChooseLockSettingsHelper;
  }
  
  public static DrmManagerClient getDrmManagerClient()
  {
    if (sMmsAppmDrmManagerClient == null) {
      sMmsAppmDrmManagerClient = new DrmManagerClient(getApp().getApplicationContext());
    }
    return sMmsAppmDrmManagerClient;
  }
  
  public static long getStartupTime()
  {
    return mStartupTime;
  }
  
  private void initMyLog()
  {
    MiXinDebugLog localMiXinDebugLog = new MiXinDebugLog();
    localMiXinDebugLog.initialize(this, MmsConfig.getLogPath(), 5, "Mms");
    MyLog.setLogger(localMiXinDebugLog);
    MyLog.setLogLevel(0);
  }
  
  public void SetCTDefaultDeliveryreportValue(boolean paramBoolean)
  {
    if (SystemProperties.get("ro.product.model.real", "unknown").equals("MI-ONE C1"))
    {
      Object localObject = PreferenceManager.getDefaultSharedPreferences(this);
      if (!((SharedPreferences)localObject).getBoolean("deliveryReportDefaultValueSet", false))
      {
        localObject = ((SharedPreferences)localObject).edit();
        ((SharedPreferences.Editor)localObject).putBoolean("pref_key_delivery_reports", paramBoolean);
        ((SharedPreferences.Editor)localObject).putBoolean("deliveryReportDefaultValueSet", true);
        ((SharedPreferences.Editor)localObject).apply();
      }
    }
  }
  
  public void onActivateStatusChanged(int paramInt, ActivateStatusReceiver.Event paramEvent, Bundle paramBundle)
  {
    if ((paramBundle.getInt("extra_activate_feature_indices", 0) & 0x1) != 1) {
      return;
    }
    switch (paramEvent)
    {
    default: 
      return;
    case ???: 
      if (MxActivateService.getEnableAfterActivation(paramInt))
      {
        MxActivateService.setEnableAfterActivation(paramInt, false);
        MxActivateService.enableMx(this, paramInt, true, true);
        return;
      }
      MxActivateService.enableMx(this, paramInt, true, false);
      return;
    }
    MxActivateService.setEnableAfterActivation(paramInt, false);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    LayoutManager.getInstance().onConfigurationChanged(paramConfiguration);
    if (SmsTextSizeAdjust.hasInstance()) {
      SmsTextSizeAdjust.getInstance().onConfigurationChanged(paramConfiguration);
    }
  }
  
  public void onCreate()
  {
    super.onCreate();
    sMmsApp = this;
    ActivateManager.setActivateServiceHostPackage("com.xiaomi.xmsf");
    PreferenceManager.setDefaultValues(this, 2131099654, false);
    if (MxActivateService.isMxEnabled(sMmsApp)) {
      MxConfigCompat.init(sMmsApp);
    }
    SetCTDefaultDeliveryreportValue(false);
    MmsConfig.init(this);
    initMyLog();
    Request.init(this);
    Request.setRequestEnv(MxRequestEnv.getMxRequestEnv(this));
    Contact.init(this);
    DraftCache.init(this);
    Conversation.init(this);
    DownloadManager.init(this);
    RateController.init(this);
    LayoutManager.init(this);
    MessagingNotification.init(this);
    MmsSystemEventReceiver.init(this);
    MessagingPreferenceActivity.enableNotifications(true, this);
    ActivateStatusReceiver.register(this);
    ActivateStatusReceiver.updateActivateInfo(this, -1);
    ActivateStatusReceiver.addListener(this);
    mHandler = new Handler(getMainLooper());
    mHandler.postDelayed(mMmsDelayedCallback, 2500L);
    CommonApplication.initialize(this);
  }
  
  public void onLowMemory()
  {
    PreviewImageLoader.getInstance().onLowMemory();
  }
  
  public void onTerminate()
  {
    ActivateStatusReceiver.removeListener(this);
    ActivateStatusReceiver.unregister(this);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.MmsApp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
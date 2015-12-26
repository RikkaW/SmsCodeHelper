/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Application
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Configuration
 *  android.drm.DrmManagerClient
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.SystemProperties
 *  android.preference.PreferenceManager
 *  android.security.ChooseLockSettingsHelper
 *  com.xiaomi.accountsdk.activate.ActivateManager
 *  com.xiaomi.accountsdk.activate.ActivateStatusReceiver
 *  com.xiaomi.accountsdk.activate.ActivateStatusReceiver$ActivateStatusListener
 *  com.xiaomi.accountsdk.activate.ActivateStatusReceiver$Event
 *  com.xiaomi.micloudsdk.request.Request
 *  com.xiaomi.micloudsdk.request.Request$RequestEnv
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 */
package com.android.mms;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.drm.DrmManagerClient;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemProperties;
import android.preference.PreferenceManager;
import android.security.ChooseLockSettingsHelper;
import com.android.mms.MmsConfig;
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
import com.xiaomi.micloudsdk.request.Request;
import com.xiaomi.mms.mx.common.CommonApplication;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.MxMessageTrackService;
import com.xiaomi.mms.utils.MxConfigCompat;
import com.xiaomi.mms.utils.MxRequestEnv;
import com.xiaomi.mms.utils.logger.MiXinDebugLog;
import com.xiaomi.mms.utils.logger.MyLog;
import miui.external.Application;
import miui.external.ApplicationDelegate;

public class MmsApp
extends ApplicationDelegate
implements ActivateStatusReceiver.ActivateStatusListener {
    private static long mStartupTime;
    private static volatile ChooseLockSettingsHelper sChooseLockSettingsHelper;
    private static MmsApp sMmsApp;
    private DrmManagerClient mDrmManagerClient;
    private Handler mHandler;
    private Runnable mMmsDelayedCallback;
    private Runnable mMmsDelayedCallback1;

    static {
        sMmsApp = null;
    }

    public MmsApp() {
        this.mMmsDelayedCallback = new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                android.app.Application application = MmsApp.getApp();
                Push.initiate((Context)application);
                UpdateManager.init();
                MiStatSdkHelper.start((Context)application);
                UnderstandLoader.prepare();
                if (MessageUtils.isMessagingTemplateAllowed((Context)application)) {
                    MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_setting", "1");
                    UnderstandLoader.init();
                } else if (MessageUtils.isUnderstandSupported()) {
                    MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_setting", "0");
                }
                new Thread(){

                    public void run() {
                        android.app.Application application = MmsApp.getApp();
                        TimedMessageReceiver.scheduleNextTimedMsg((Context)application);
                        TimedMessageExpiredActivity.markPrivateExpiredMsgFailed((Context)application);
                        TimedMessageExpiredActivity.raiseIfNecessary((Context)application);
                    }
                }.start();
                MmsApp.this.startService(new Intent((Context)application, (Class)TransactionService.class));
                MSimUtils.sendQueuedMessageNoToast((Context)application, MSimUtils.SLOT_ID_ALL);
                MxActivateService.enableAll((Context)application, false);
                MxMessageTrackService.startTrack((Context)application);
                MmsApp.this.mHandler.postDelayed(MmsApp.this.mMmsDelayedCallback1, 800);
            }

        };
        this.mMmsDelayedCallback1 = new Runnable(){

            @Override
            public void run() {
                MessageListItem.initDummy();
                ThumbnailView.initDummy();
            }
        };
        mStartupTime = System.currentTimeMillis();
    }

    public static android.app.Application getApp() {
        synchronized (MmsApp.class) {
            Application application = sMmsApp.getApplication();
            return application;
        }
    }

    public static ChooseLockSettingsHelper getChooseLockSettingsHelper() {
        if (sChooseLockSettingsHelper == null) {
            sChooseLockSettingsHelper = new ChooseLockSettingsHelper((Context)sMmsApp);
        }
        return sChooseLockSettingsHelper;
    }

    public static DrmManagerClient getDrmManagerClient() {
        if (MmsApp.sMmsApp.mDrmManagerClient == null) {
            MmsApp.sMmsApp.mDrmManagerClient = new DrmManagerClient(MmsApp.getApp().getApplicationContext());
        }
        return MmsApp.sMmsApp.mDrmManagerClient;
    }

    public static long getStartupTime() {
        return mStartupTime;
    }

    private void initMyLog() {
        MiXinDebugLog miXinDebugLog = new MiXinDebugLog();
        miXinDebugLog.initialize((Context)this, MmsConfig.getLogPath(), 5, "Mms");
        MyLog.setLogger(miXinDebugLog);
        MyLog.setLogLevel(0);
    }

    public void SetCTDefaultDeliveryreportValue(boolean bl) {
        SharedPreferences sharedPreferences;
        if (SystemProperties.get((String)"ro.product.model.real", (String)"unknown").equals((Object)"MI-ONE C1") && !(sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)this)).getBoolean("deliveryReportDefaultValueSet", false)) {
            sharedPreferences = sharedPreferences.edit();
            sharedPreferences.putBoolean("pref_key_delivery_reports", bl);
            sharedPreferences.putBoolean("deliveryReportDefaultValueSet", true);
            sharedPreferences.apply();
        }
    }

    public void onActivateStatusChanged(int n, ActivateStatusReceiver.Event event, Bundle bundle) {
        if ((bundle.getInt("extra_activate_feature_indices", 0) & 1) != 1) {
            return;
        }
        switch (.$SwitchMap$com$xiaomi$accountsdk$activate$ActivateStatusReceiver$Event[event.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                if (MxActivateService.getEnableAfterActivation(n)) {
                    MxActivateService.setEnableAfterActivation(n, false);
                    MxActivateService.enableMx((Context)this, n, true, true);
                    return;
                }
                MxActivateService.enableMx((Context)this, n, true, false);
                return;
            }
            case 2: 
        }
        MxActivateService.setEnableAfterActivation(n, false);
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        LayoutManager.getInstance().onConfigurationChanged(configuration);
        if (SmsTextSizeAdjust.hasInstance()) {
            SmsTextSizeAdjust.getInstance().onConfigurationChanged(configuration);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sMmsApp = this;
        ActivateManager.setActivateServiceHostPackage((String)"com.xiaomi.xmsf");
        PreferenceManager.setDefaultValues((Context)this, (int)2131099654, (boolean)false);
        if (MxActivateService.isMxEnabled((Context)sMmsApp)) {
            MxConfigCompat.init((Context)sMmsApp);
        }
        this.SetCTDefaultDeliveryreportValue(false);
        MmsConfig.init((Context)this);
        this.initMyLog();
        Request.init((Context)this);
        Request.setRequestEnv((Request.RequestEnv)MxRequestEnv.getMxRequestEnv((Context)this));
        Contact.init((Context)this);
        DraftCache.init((Context)this);
        Conversation.init((Context)this);
        DownloadManager.init((Context)this);
        RateController.init((Context)this);
        LayoutManager.init((Context)this);
        MessagingNotification.init((Context)this);
        MmsSystemEventReceiver.init((Context)this);
        MessagingPreferenceActivity.enableNotifications(true, (Context)this);
        ActivateStatusReceiver.register((Context)this);
        ActivateStatusReceiver.updateActivateInfo((Context)this, (int)-1);
        ActivateStatusReceiver.addListener((ActivateStatusReceiver.ActivateStatusListener)this);
        this.mHandler = new Handler(this.getMainLooper());
        this.mHandler.postDelayed(this.mMmsDelayedCallback, 2500);
        CommonApplication.initialize((Context)this);
    }

    @Override
    public void onLowMemory() {
        PreviewImageLoader.getInstance().onLowMemory();
    }

    @Override
    public void onTerminate() {
        ActivateStatusReceiver.removeListener((ActivateStatusReceiver.ActivateStatusListener)this);
        ActivateStatusReceiver.unregister((Context)this);
    }

}


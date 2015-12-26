/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.database.ContentObserver
 *  android.net.Uri
 *  android.os.Handler
 *  android.os.Looper
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.string.MD5;
import com.xiaomi.mipush.sdk.AppInfoHolder;
import com.xiaomi.mipush.sdk.PushContainerHelper;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.PushProvision;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import com.xiaomi.xmpush.thrift.XmPushActionRegistration;
import com.xiaomi.xmpush.thrift.XmPushActionUnRegistration;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.thrift.TBase;

public class PushServiceClient {
    private static PushServiceClient sInstance;
    private static final ArrayList<BufferedRequest> sPendingRequest;
    private Context mContext;
    private Integer mDeviceProvisioned = null;
    private boolean mIsMiuiPushServiceEnabled = false;
    private String mSession;
    private Intent registerTask = null;

    static {
        sPendingRequest = new ArrayList();
    }

    private PushServiceClient(Context context) {
        this.mContext = context.getApplicationContext();
        this.mSession = null;
        this.mIsMiuiPushServiceEnabled = this.serviceInstalled();
    }

    private Intent createServiceIntent() {
        Intent intent = new Intent();
        String string2 = this.mContext.getPackageName();
        if (this.shouldUseMIUIPush() && !"com.xiaomi.xmsf".equals((Object)string2)) {
            intent.setPackage("com.xiaomi.xmsf");
            intent.setClassName("com.xiaomi.xmsf", this.getPushServiceName());
            intent.putExtra("mipush_app_package", string2);
            this.disableMyPushService();
            return intent;
        }
        this.enableMyPushService();
        intent.setComponent(new ComponentName(this.mContext, "com.xiaomi.push.service.XMPushService"));
        intent.putExtra("mipush_app_package", string2);
        return intent;
    }

    private void disableMyPushService() {
        try {
            this.mContext.getPackageManager().setComponentEnabledSetting(new ComponentName(this.mContext, "com.xiaomi.push.service.XMPushService"), 2, 1);
            return;
        }
        catch (Throwable var1_1) {
            return;
        }
    }

    private void enableMyPushService() {
        try {
            this.mContext.getPackageManager().setComponentEnabledSetting(new ComponentName(this.mContext, "com.xiaomi.push.service.XMPushService"), 1, 1);
            return;
        }
        catch (Throwable var1_1) {
            return;
        }
    }

    public static PushServiceClient getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PushServiceClient(context);
        }
        return sInstance;
    }

    private String getPushServiceName() {
        try {
            if (this.mContext.getPackageManager().getPackageInfo((String)"com.xiaomi.xmsf", (int)4).versionCode >= 106) {
                return "com.xiaomi.push.service.XMPushService";
            }
        }
        catch (Exception var1_1) {
            // empty catch block
        }
        return "com.xiaomi.xmsf.push.service.XMPushService";
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean isAutoTry() {
        String string2 = this.mContext.getPackageName();
        if (string2.contains((CharSequence)"miui") || string2.contains((CharSequence)"xiaomi") || (this.mContext.getApplicationInfo().flags & 1) != 0) {
            return true;
        }
        return false;
    }

    private boolean serviceInstalled() {
        block4 : {
            PackageManager packageManager = this.mContext.getPackageManager();
            packageManager = packageManager.getPackageInfo("com.xiaomi.xmsf", 4);
            if (packageManager != null) break block4;
            return false;
        }
        try {
            int n = packageManager.versionCode;
            if (n >= 105) {
                return true;
            }
        }
        catch (Exception var2_2) {
            // empty catch block
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public <T extends TBase<T, ?>> void addPendRequest(T object, ActionType actionType, boolean bl) {
        BufferedRequest bufferedRequest = new BufferedRequest();
        bufferedRequest.message = object;
        bufferedRequest.actionType = actionType;
        bufferedRequest.encrypt = bl;
        object = sPendingRequest;
        synchronized (object) {
            sPendingRequest.add(bufferedRequest);
            if (sPendingRequest.size() > 10) {
                sPendingRequest.remove(0);
            }
            return;
        }
    }

    public void awakePushService() {
        this.mContext.startService(this.createServiceIntent());
    }

    public void clearLocalNotificationType() {
        Intent intent = this.createServiceIntent();
        intent.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        intent.putExtra(PushConstants.EXTRA_PACKAGE_NAME, this.mContext.getPackageName());
        intent.putExtra(PushConstants.EXTRA_SIG, MD5.MD5_16(this.mContext.getPackageName()));
        this.mContext.startService(intent);
    }

    public void clearNotification(int n) {
        Intent intent = this.createServiceIntent();
        intent.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        intent.putExtra(PushConstants.EXTRA_PACKAGE_NAME, this.mContext.getPackageName());
        intent.putExtra(PushConstants.EXTRA_NOTIFY_ID, n);
        this.mContext.startService(intent);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isProvisioned() {
        if (!this.shouldUseMIUIPush() || !this.isAutoTry()) return true;
        if (this.mDeviceProvisioned == null) {
            this.mDeviceProvisioned = PushProvision.getInstance(this.mContext).getProvisioned();
            if (this.mDeviceProvisioned == 0) {
                ContentObserver contentObserver = new ContentObserver(new Handler(Looper.getMainLooper())){

                    public void onChange(boolean bl) {
                        PushServiceClient.this.mDeviceProvisioned = PushProvision.getInstance(PushServiceClient.this.mContext).getProvisioned();
                        if (PushServiceClient.this.mDeviceProvisioned != 0) {
                            PushServiceClient.this.mContext.getContentResolver().unregisterContentObserver((ContentObserver)this);
                            if (Network.hasNetwork(PushServiceClient.this.mContext)) {
                                PushServiceClient.this.processRegisterTask();
                            }
                        }
                    }
                };
                this.mContext.getContentResolver().registerContentObserver(PushProvision.getInstance(this.mContext).getProvisionedUri(), false, contentObserver);
            }
        }
        if (this.mDeviceProvisioned == 0) return false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void processPendRequest() {
        ArrayList<BufferedRequest> arrayList = sPendingRequest;
        synchronized (arrayList) {
            Iterator iterator = sPendingRequest.iterator();
            do {
                if (!iterator.hasNext()) {
                    sPendingRequest.clear();
                    return;
                }
                BufferedRequest bufferedRequest = (BufferedRequest)iterator.next();
                this.sendMessage(bufferedRequest.message, bufferedRequest.actionType, bufferedRequest.encrypt, false, null, true);
            } while (true);
        }
    }

    public void processRegisterTask() {
        if (this.registerTask != null) {
            this.mContext.startService(this.registerTask);
            this.registerTask = null;
        }
    }

    public final void register(XmPushActionRegistration xmPushActionRegistration, boolean bl) {
        this.registerTask = null;
        Intent intent = this.createServiceIntent();
        if ((xmPushActionRegistration = (XmPushActionRegistration)XmPushThriftSerializeUtils.convertThriftObjectToBytes(PushContainerHelper.generateRequestContainer(this.mContext, xmPushActionRegistration, ActionType.Registration))) == null) {
            MyLog.warn("register fail, because msgBytes is null.");
            return;
        }
        intent.setAction("com.xiaomi.mipush.REGISTER_APP");
        intent.putExtra("mipush_app_id", AppInfoHolder.getInstance(this.mContext).getAppID());
        intent.putExtra("mipush_payload", (byte[])xmPushActionRegistration);
        intent.putExtra("mipush_session", this.mSession);
        intent.putExtra("mipush_env_chanage", bl);
        intent.putExtra("mipush_env_type", AppInfoHolder.getInstance(this.mContext).getEnvType());
        if (Network.hasNetwork(this.mContext) && this.isProvisioned()) {
            this.mContext.startService(intent);
            return;
        }
        this.registerTask = intent;
    }

    /*
     * Enabled aggressive block sorting
     */
    public final <T extends TBase<T, ?>> void sendMessage(T t, ActionType actionType, PushMetaInfo pushMetaInfo) {
        boolean bl = !actionType.equals((Object)ActionType.Registration);
        this.sendMessage(t, actionType, bl, pushMetaInfo);
    }

    public final <T extends TBase<T, ?>> void sendMessage(T t, ActionType actionType, boolean bl, PushMetaInfo pushMetaInfo) {
        this.sendMessage(t, actionType, bl, true, pushMetaInfo, true);
    }

    public final <T extends TBase<T, ?>> void sendMessage(T t, ActionType actionType, boolean bl, PushMetaInfo pushMetaInfo, boolean bl2) {
        this.sendMessage(t, actionType, bl, true, pushMetaInfo, bl2);
    }

    public final <T extends TBase<T, ?>> void sendMessage(T t, ActionType actionType, boolean bl, boolean bl2, PushMetaInfo pushMetaInfo, boolean bl3) {
        this.sendMessage(t, actionType, bl, bl2, pushMetaInfo, bl3, this.mContext.getPackageName(), AppInfoHolder.getInstance(this.mContext).getAppID());
    }

    public final <T extends TBase<T, ?>> void sendMessage(T object, ActionType actionType, boolean bl, boolean bl2, PushMetaInfo pushMetaInfo, boolean bl3, String string2, String string3) {
        if (!AppInfoHolder.getInstance(this.mContext).appRegistered()) {
            if (bl2) {
                this.addPendRequest(object, actionType, bl);
                return;
            }
            MyLog.warn("drop the message before initialization.");
            return;
        }
        Intent intent = this.createServiceIntent();
        object = PushContainerHelper.generateRequestContainer(this.mContext, object, actionType, bl, string2, string3);
        if (pushMetaInfo != null) {
            object.setMetaInfo(pushMetaInfo);
        }
        if ((object = (Object)XmPushThriftSerializeUtils.convertThriftObjectToBytes(object)) == null) {
            MyLog.warn("send message fail, because msgBytes is null.");
            return;
        }
        intent.setAction("com.xiaomi.mipush.SEND_MESSAGE");
        intent.putExtra("mipush_payload", (byte[])object);
        intent.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", bl3);
        this.mContext.startService(intent);
    }

    public boolean shouldUseMIUIPush() {
        if (this.mIsMiuiPushServiceEnabled && 1 == AppInfoHolder.getInstance(this.mContext).getEnvType()) {
            return true;
        }
        return false;
    }

    public final void unregister(XmPushActionUnRegistration xmPushActionUnRegistration) {
        Intent intent = this.createServiceIntent();
        if ((xmPushActionUnRegistration = (XmPushActionUnRegistration)XmPushThriftSerializeUtils.convertThriftObjectToBytes(PushContainerHelper.generateRequestContainer(this.mContext, xmPushActionUnRegistration, ActionType.UnRegistration))) == null) {
            MyLog.warn("unregister fail, because msgBytes is null.");
            return;
        }
        intent.setAction("com.xiaomi.mipush.UNREGISTER_APP");
        intent.putExtra("mipush_app_id", AppInfoHolder.getInstance(this.mContext).getAppID());
        intent.putExtra("mipush_payload", (byte[])xmPushActionUnRegistration);
        this.mContext.startService(intent);
    }

    static class BufferedRequest<T extends TBase<T, ?>> {
        ActionType actionType;
        boolean encrypt;
        T message;

        BufferedRequest() {
        }
    }

}


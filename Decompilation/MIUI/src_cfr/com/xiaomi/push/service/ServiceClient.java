/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.os.Bundle
 *  android.text.TextUtils
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  org.apache.http.NameValuePair
 */
package com.xiaomi.push.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.BuildSettings;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.profile.MessageProfiling;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Message;
import java.util.List;
import org.apache.http.NameValuePair;

public class ServiceClient {
    private static ServiceClient sInstance;
    private static String sSession;
    private Context mContext;
    private boolean mIsMiuiPushServiceEnabled = false;

    static {
        sSession = null;
    }

    private ServiceClient(Context context) {
        this.mContext = context.getApplicationContext();
        if (this.serviceInstalled()) {
            MyLog.v("use miui push service");
            this.mIsMiuiPushServiceEnabled = true;
        }
    }

    private Intent createServiceIntent() {
        if (this.isMiuiPushServiceEnabled()) {
            Intent intent = new Intent();
            intent.setPackage("com.xiaomi.xmsf");
            intent.setClassName("com.xiaomi.xmsf", this.getPushServiceName());
            String string2 = this.mContext.getPackageName();
            intent.putExtra(PushConstants.EXTRA_PACKAGE_NAME, string2);
            this.disableMyPushService();
            return intent;
        }
        Intent intent = new Intent(this.mContext, (Class)XMPushService.class);
        String string3 = this.mContext.getPackageName();
        intent.putExtra(PushConstants.EXTRA_PACKAGE_NAME, string3);
        this.enableMyPushService();
        return intent;
    }

    private void disableMyPushService() {
        this.mContext.getPackageManager().setComponentEnabledSetting(new ComponentName(this.mContext, (Class)XMPushService.class), 2, 1);
    }

    private void enableMyPushService() {
        this.mContext.getPackageManager().setComponentEnabledSetting(new ComponentName(this.mContext, (Class)XMPushService.class), 1, 1);
    }

    public static ServiceClient getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ServiceClient(context);
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

    private String joinAttributes(List<NameValuePair> list) {
        StringBuilder stringBuilder = new StringBuilder();
        int n = 1;
        for (NameValuePair nameValuePair : list) {
            stringBuilder.append(nameValuePair.getName()).append(":").append(nameValuePair.getValue());
            if (n < list.size()) {
                stringBuilder.append(",");
            }
            ++n;
        }
        return stringBuilder.toString();
    }

    private void putOpenParamsIntoIntent(Intent intent, String string2, String string3, String string4, String string5, String string6, boolean bl, List<NameValuePair> list, List<NameValuePair> list2) {
        intent.putExtra(PushConstants.EXTRA_USER_ID, string2);
        intent.putExtra(PushConstants.EXTRA_CHANNEL_ID, string3);
        intent.putExtra(PushConstants.EXTRA_TOKEN, string4);
        intent.putExtra(PushConstants.EXTRA_SECURITY, string6);
        intent.putExtra(PushConstants.EXTRA_AUTH_METHOD, string5);
        intent.putExtra(PushConstants.EXTRA_KICK, bl);
        intent.putExtra(PushConstants.EXTRA_SESSION, sSession);
        if (list != null && !TextUtils.isEmpty((CharSequence)(string2 = this.joinAttributes(list)))) {
            intent.putExtra(PushConstants.EXTRA_CLIENT_ATTR, string2);
        }
        if (list2 != null && !TextUtils.isEmpty((CharSequence)(string2 = this.joinAttributes(list2)))) {
            intent.putExtra(PushConstants.EXTRA_CLOUD_ATTR, string2);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private boolean serviceInstalled() {
        if (BuildSettings.IsTestBuild) {
            return false;
        }
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            if ((packageManager = packageManager.getPackageInfo("com.xiaomi.xmsf", 4)) == null) return false;
            int n = packageManager.versionCode;
            if (n < 104) return false;
            return true;
        }
        catch (Exception var2_2) {
            return false;
        }
    }

    public boolean closeChannel(String string2) {
        Intent intent = this.createServiceIntent();
        intent.setAction(PushConstants.ACTION_CLOSE_CHANNEL);
        intent.putExtra(PushConstants.EXTRA_CHANNEL_ID, string2);
        this.mContext.startService(intent);
        return true;
    }

    public boolean isMiuiPushServiceEnabled() {
        return this.mIsMiuiPushServiceEnabled;
    }

    public int openChannel(String string2, String string3, String string4, String string5, String string6, boolean bl, List<NameValuePair> list, List<NameValuePair> list2) {
        Intent intent = this.createServiceIntent();
        intent.setAction(PushConstants.ACTION_OPEN_CHANNEL);
        this.putOpenParamsIntoIntent(intent, string2, string3, string4, string5, string6, bl, list, list2);
        this.mContext.startService(intent);
        return 0;
    }

    public boolean sendMessage(Message message, boolean bl) {
        if (!Network.hasNetwork(this.mContext)) {
            return false;
        }
        Intent intent = this.createServiceIntent();
        String string2 = MessageProfiling.getPrefString();
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            CommonPacketExtension commonPacketExtension = new CommonPacketExtension("pf", null, (String[])null, (String[])null);
            CommonPacketExtension commonPacketExtension2 = new CommonPacketExtension("sent", null, (String[])null, (String[])null);
            commonPacketExtension2.setText(string2);
            commonPacketExtension.appendChild(commonPacketExtension2);
            message.addExtension(commonPacketExtension);
        }
        if ((string2 = message.toBundle()) != null) {
            MyLog.v("SEND:" + message.toXML());
            intent.setAction(PushConstants.ACTION_SEND_MESSAGE);
            intent.putExtra(PushConstants.EXTRA_SESSION, sSession);
            intent.putExtra("ext_packet", (Bundle)string2);
            intent.putExtra("ext_encrypt", bl);
            this.mContext.startService(intent);
            return true;
        }
        return false;
    }
}


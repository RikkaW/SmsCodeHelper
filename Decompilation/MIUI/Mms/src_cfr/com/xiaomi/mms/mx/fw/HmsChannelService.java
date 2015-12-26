/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.IntentService
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.preference.PreferenceManager
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  miui.os.Build
 *  org.apache.http.NameValuePair
 *  org.apache.http.message.BasicNameValuePair
 */
package com.xiaomi.mms.mx.fw;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.xiaomi.mms.mx.fw.HmsPersister;
import com.xiaomi.mms.mx.fw.faccount.XiaoMiJIDUtils;
import com.xiaomi.mms.mx.fw.xmppmsg.AckMessage;
import com.xiaomi.mms.mx.utils.Log;
import com.xiaomi.push.service.ServiceClient;
import java.util.ArrayList;
import java.util.List;
import miui.os.Build;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class HmsChannelService
extends IntentService {
    private Context mContext;

    public HmsChannelService() {
        super("HmsChannelService");
    }

    private List<NameValuePair> bindCloudExtras() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("v", "2"));
        arrayList.add(new BasicNameValuePair("f", "1"));
        return arrayList;
    }

    private void closeChannel() {
        ServiceClient.getInstance(this.getApplicationContext()).closeChannel("8");
    }

    public static boolean isDeviceLoginOut(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("extra_flag_current_device_state", true);
    }

    public static boolean isHmsAvailable() {
        if (!Build.IS_INTERNATIONAL_BUILD && !Build.IS_MIONE) {
            return true;
        }
        return false;
    }

    private void openChannel() {
        if (!HmsChannelService.isHmsAvailable()) {
            Log.v("HmsChannelService", "hms not available");
            return;
        }
        if (!XiaoMiJIDUtils.hasAccount(this.mContext)) {
            Log.v("HmsChannelService", "trying start channel while no account, return");
            return;
        }
        String string2 = XiaoMiJIDUtils.getUserIDWithResId(this.mContext);
        String string3 = XiaoMiJIDUtils.tryGetServiceToken(this.mContext, "mipub");
        String string4 = XiaoMiJIDUtils.tryGetPSecurity(this.mContext, "mipub");
        ServiceClient.getInstance(this.mContext).openChannel(string2, "8", string3, "XIAOMI-PASS", string4, true, null, this.bindCloudExtras());
    }

    private void openChannel(String string2, String string3) {
        if (!HmsChannelService.isHmsAvailable()) {
            Log.v("HmsChannelService", "hms not available");
            return;
        }
        if (!XiaoMiJIDUtils.hasAccount(this.mContext)) {
            Log.v("HmsChannelService", "trying start channel while no account, return");
            return;
        }
        String string4 = XiaoMiJIDUtils.getUserIDWithResId(this.mContext);
        ServiceClient.getInstance(this.mContext).openChannel(string4, "8", string2, "XIAOMI-PASS", string3, true, null, this.bindCloudExtras());
    }

    public static void sendAckMessage(Context context, String string2, String string3, String string4, String string5, boolean bl) {
        new AckMessage(string2, String.valueOf((long)HmsPersister.generateNewId(context)), string3, string4, string5, bl).sendMessage(false);
    }

    public static void tryCloseChannel(Context context) {
        Intent intent = new Intent("com.xiaomi.mms.action_close_channel");
        intent.setPackage(context.getPackageName());
        context.startService(intent);
    }

    public static void tryOpenChannel(Context context) {
        Intent intent = new Intent("com.xiaomi.mms.action_open_channel");
        intent.setPackage(context.getPackageName());
        context.startService(intent);
    }

    public void onCreate() {
        super.onCreate();
        this.mContext = this.getBaseContext();
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void onHandleIntent(Intent object) {
        if (object == null) {
            return;
        }
        if (object.getAction().equalsIgnoreCase("com.xiaomi.mms.action_close_channel")) {
            this.closeChannel();
            return;
        }
        if (!object.getAction().equalsIgnoreCase("com.xiaomi.mms.action_open_channel")) return;
        String string2 = object.getStringExtra("auth_token");
        object = object.getStringExtra("auth_security");
        if (!TextUtils.isEmpty((CharSequence)string2) && !TextUtils.isEmpty((CharSequence)object)) {
            this.openChannel(string2, (String)object);
            return;
        }
        this.openChannel();
    }
}


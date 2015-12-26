/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.text.TextUtils
 *  com.xiaomi.accountsdk.account.XMPassport
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Map$Entry
 *  miui.cloud.CloudManager
 *  miui.telephony.exception.IllegalDeviceException
 */
package com.xiaomi.mms.net;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.android.mms.MmsApp;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.mms.net.HttpGetSimpleRequest;
import com.xiaomi.mms.net.SimpleRequest;
import com.xiaomi.mms.utils.EasyMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import miui.cloud.CloudManager;
import miui.telephony.exception.IllegalDeviceException;

public class CloudRequestFactory {
    public static final String ACCOUNT_URL_BASE = XMPassport.URL_ACCOUNT_BASE;
    public static final String API_URL_V2_BASE = XMPassport.URL_ACCOUNT_API_V2_BASE;
    public static final String URL_LOGIN = ACCOUNT_URL_BASE + "/serviceLogin";

    protected static String appendParams(String string2, Map<String, String> object) {
        void var2_7;
        String string32 = string2;
        if (object != null) {
            String string3 = string2;
            if (!object.isEmpty()) {
                string2 = Uri.parse((String)string2).buildUpon();
                for (Map.Entry entry : object.entrySet()) {
                    string2.appendQueryParameter((String)entry.getKey(), (String)entry.getValue());
                }
                String string4 = string2.build().toString();
            }
        }
        return var2_7;
    }

    public static SimpleRequest newGetIDCConfigRequest() {
        return new HttpGetSimpleRequest("http://api.account.xiaomi.com/pass/configuration?keys=idc");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static SimpleRequest newGetUserIdRequest(String string2) {
        HttpGetSimpleRequest httpGetSimpleRequest = new HttpGetSimpleRequest(CloudRequestFactory.appendParams("https://api.account.xiaomi.com/pass/v3/user@id", new EasyMap<String, String>("type", "MXPH").easyPut("externalId", string2)));
        string2 = null;
        try {
            String string3;
            string2 = string3 = CloudManager.getHashedDeviceId((Context)MmsApp.getApp());
        }
        catch (IllegalDeviceException var1_3) {
            var1_3.printStackTrace();
        }
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            httpGetSimpleRequest.addCookie("deviceId", string2);
        }
        return httpGetSimpleRequest;
    }
}


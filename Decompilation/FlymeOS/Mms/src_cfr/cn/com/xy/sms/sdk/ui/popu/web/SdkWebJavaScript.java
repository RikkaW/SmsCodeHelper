/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.webkit.JavascriptInterface
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.ui.popu.web;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.webkit.JavascriptInterface;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.net.NetWebUtil;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.util.ParseManager;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class SdkWebJavaScript {
    private dm mActivityParam;

    public SdkWebJavaScript(dm dm2) {
        this.mActivityParam = dm2;
    }

    public static /* synthetic */ dm access$000(SdkWebJavaScript sdkWebJavaScript) {
        return sdkWebJavaScript.mActivityParam;
    }

    @JavascriptInterface
    public void asyncRequest(String string2, String string3, String string4) {
        NetWebUtil.sendPostRequest(string2, string3, new ej(this, string4));
    }

    @JavascriptInterface
    public void asyncRequestByParamKey(String string2, String string3, String object) {
        object = new el(this, (String)object);
        NetWebUtil.sendPostRequest(string2, this.mActivityParam.a(string3), (XyCallBack)object);
    }

    @JavascriptInterface
    public boolean checkHasAppName(String string2) {
        try {
            this.mActivityParam.b().getPackageManager().getPackageInfo(string2, 1);
            return true;
        }
        catch (Exception var1_2) {
            return false;
        }
    }

    @JavascriptInterface
    public int checkOrientation() {
        return this.mActivityParam.c();
    }

    @JavascriptInterface
    public long closeDefService() {
        return ParseManager.setDefServiceSwitch((Context)this.mActivityParam.b(), "0");
    }

    @JavascriptInterface
    public void closeWebView() {
        this.mActivityParam.b().finish();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @JavascriptInterface
    public boolean doAction(String string2, String string3) {
        if (string3 == null) return false;
        try {
            string3 = new JSONObject(string3);
            Iterator iterator = string3.keys();
            if (iterator == null) return false;
            HashMap hashMap = new HashMap();
            while (iterator.hasNext()) {
                String string4 = (String)iterator.next();
                hashMap.put((Object)string4, (Object)((String)string3.get(string4)));
            }
            return DuoquUtils.doCustomAction(this.mActivityParam.b(), string2, hashMap);
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
        }
        return false;
    }

    @JavascriptInterface
    public String getConfigByKey(String string2) {
        return this.mActivityParam.a(string2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @JavascriptInterface
    public String getExtendValue(int n2, String string2) {
        string2 = new JSONObject(string2);
        string2 = DuoquUtils.getSdkDoAction().getExtendValue(n2, (JSONObject)string2);
        if (string2 == null) return null;
        try {
            return string2.toString();
        }
        catch (Exception var2_3) {
            var2_3.printStackTrace();
        }
        return null;
    }

    @JavascriptInterface
    public String getValueByKey(String string2) {
        return SysParamEntityManager.queryValueParamKey((Context)this.mActivityParam.b(), string2);
    }

    @JavascriptInterface
    public long openDefService() {
        return ParseManager.setDefServiceSwitch((Context)this.mActivityParam.b(), "1");
    }

    @JavascriptInterface
    public String queryDefServiceSwitch() {
        return ParseManager.queryDefService((Context)this.mActivityParam.b());
    }

    @JavascriptInterface
    public void runOnAndroidJavaScript(String string2) {
    }

    @JavascriptInterface
    public long saveValueByKey(String string2, String string3) {
        try {
            SysParamEntityManager.insertOrUpdateKeyValue((Context)this.mActivityParam.b(), string2, string3, null);
            return 0;
        }
        catch (Exception var1_2) {
            return -2;
        }
    }
}


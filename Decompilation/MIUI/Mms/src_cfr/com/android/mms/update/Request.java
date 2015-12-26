/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.net.HttpURLConnection
 *  java.net.URL
 *  java.net.URLConnection
 *  java.net.URLEncoder
 *  java.util.Arrays
 *  java.util.HashMap
 *  java.util.Locale
 *  java.util.Map$Entry
 *  miui.cloud.CloudManager
 *  miui.util.CoderUtils
 */
package com.android.mms.update;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.update.Network;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import miui.cloud.CloudManager;
import miui.util.CoderUtils;

public abstract class Request {
    protected int mConnectTimeout;
    protected Context mContext;
    protected boolean mDecryptDownloadData;
    protected int mNetworkAccess = 0;
    private HashMap<String, String> mParamsMap;
    protected int mReadTimeout;
    protected String mRequestMethod;
    protected String mRequestUrl;
    protected boolean mRequireLogin;

    public Request(Context context, String string2) {
        this.mContext = context;
        this.mRequestMethod = "GET";
        this.mRequestUrl = string2;
        this.mDecryptDownloadData = true;
        this.mRequireLogin = false;
    }

    public static String decryptData(String string2) {
        return CoderUtils.base6AesDecode((String)string2, (String)"d101b17c77ff93cs");
    }

    public static String encryptData(String string2) {
        return CoderUtils.base64AesEncode((String)string2, (String)"d101b17c77ff93cs");
    }

    private static String genUrlSign(HashMap<String, String> hashMap, String object2, String string2) {
        if (hashMap.isEmpty()) {
            return "";
        }
        Object[] arrobject = hashMap.keySet().toArray(new String[0]);
        Arrays.sort((Object[])arrobject);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((String)object2);
        for (Object object2 : arrobject) {
            stringBuilder.append((String)object2).append((String)hashMap.get(object2));
        }
        stringBuilder.append(string2);
        return CoderUtils.encodeSHA((String)stringBuilder.toString()).toUpperCase(Locale.US);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static HashMap<String, String> getEncryptedParam(HashMap<String, String> object) {
        HashMap hashMap = new HashMap();
        StringBuilder stringBuilder = new StringBuilder();
        Iterator iterator = object.entrySet().iterator();
        do {
            if (!iterator.hasNext()) {
                hashMap.put((Object)"_encparam", (Object)Request.encryptData(stringBuilder.toString()));
                return hashMap;
            }
            object = (Map.Entry)iterator.next();
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String)object.getKey());
            stringBuilder.append("=");
            object = TextUtils.isEmpty((CharSequence)((CharSequence)object.getValue())) ? "" : (String)object.getValue();
            stringBuilder.append(URLEncoder.encode((String)object));
        } while (true);
    }

    private static String getSignedUri(HashMap<String, String> object, String string22, String charSequence) {
        String string3 = Request.genUrlSign(object, string22, charSequence);
        if (string3.length() == 0) {
            return string3;
        }
        charSequence = new StringBuilder();
        charSequence.append("appkey=").append(string22).append("&sign=").append(string3);
        for (String string22 : object.entrySet()) {
            charSequence.append('&').append((String)string22.getKey()).append('=').append(URLEncoder.encode((String)((String)string22.getValue())));
        }
        return charSequence.toString();
    }

    public static String getUserAgent() {
        return CloudManager.getUserAgent();
    }

    public static String signUrlParams(HashMap<String, String> hashMap) {
        return Request.getSignedUri(Request.getEncryptedParam(hashMap), "yellowpage", "77eb2e8a5755abd016c0d69ba74b219c");
    }

    public void addParam(String string2, String string3) {
        if (this.mParamsMap == null) {
            this.mParamsMap = new HashMap();
        }
        if (!this.mParamsMap.containsKey((Object)string2)) {
            this.mParamsMap.put((Object)string2, (Object)string3);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected HttpURLConnection getConn() {
        String string2 = this.getRequestUrl();
        Log.d((String)"Request", (String)("The connection url is " + string2));
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return null;
        }
        String string3 = null;
        String string4 = null;
        try {
            int n;
            block12 : {
                string4 = string2 = (HttpURLConnection)new URL(string2).openConnection();
                string3 = string2;
                if (this.mReadTimeout > 0) {
                    string4 = string2;
                    string3 = string2;
                    string2.setReadTimeout(this.mReadTimeout);
                } else {
                    string4 = string2;
                    string3 = string2;
                    if (Network.isWifiConnected(this.mContext)) {
                        string4 = string2;
                        string3 = string2;
                        string2.setReadTimeout(10000);
                        break block12;
                    }
                    string4 = string2;
                    string3 = string2;
                    string2.setReadTimeout(30000);
                }
            }
            string4 = string2;
            string3 = string2;
            if (this.mConnectTimeout > 0) {
                string4 = string2;
                string3 = string2;
                n = this.mConnectTimeout;
            } else {
                n = 10000;
            }
            string4 = string2;
            string3 = string2;
            string2.setConnectTimeout(n);
            string4 = string2;
            string3 = string2;
            string2.setRequestMethod(this.mRequestMethod);
            string4 = string2;
            string3 = string2;
            if (TextUtils.equals((CharSequence)this.mRequestMethod, (CharSequence)"POST")) {
                string4 = string2;
                string3 = string2;
                string2.setDoOutput(true);
                string4 = string2;
                string3 = string2;
                string2.setUseCaches(false);
                string4 = string2;
                string3 = string2;
                string2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            }
            string4 = string2;
            string3 = string2;
            string2.setRequestProperty("User-Agent", Request.getUserAgent());
            return string2;
        }
        catch (MalformedURLException var3_3) {
            var3_3.printStackTrace();
            return string4;
        }
        catch (IOException var2_5) {
            var2_5.printStackTrace();
            return string3;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected String getParams() {
        HashMap hashMap;
        if (this.mParamsMap == null) {
            hashMap = new HashMap();
            do {
                return Request.signUrlParams(hashMap);
                break;
            } while (true);
        }
        hashMap = this.mParamsMap;
        return Request.signUrlParams(hashMap);
    }

    protected String getRequestUrl() {
        if (TextUtils.equals((CharSequence)this.mRequestMethod, (CharSequence)"POST")) {
            return this.mRequestUrl;
        }
        String string2 = this.getParams();
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return this.mRequestUrl;
        }
        return String.format((String)"%s?%s", (Object[])new Object[]{this.mRequestUrl, string2});
    }

    protected boolean isServerError(int n) {
        if (n == 400 || n == 401 || n == 403 || n == 406 || n / 100 == 5) {
            return true;
        }
        return false;
    }

    public void overwriteNetworkAccess(int n) {
        this.mNetworkAccess = n;
    }
}


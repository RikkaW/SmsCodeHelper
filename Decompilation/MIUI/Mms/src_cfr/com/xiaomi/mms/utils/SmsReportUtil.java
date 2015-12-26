/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  java.io.ByteArrayOutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.StatusLine
 *  org.apache.http.client.HttpClient
 *  org.apache.http.client.entity.UrlEncodedFormEntity
 *  org.apache.http.client.methods.HttpGet
 *  org.apache.http.client.methods.HttpPost
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.impl.client.DefaultHttpClient
 *  org.apache.http.message.BasicNameValuePair
 *  org.json.JSONObject
 */
package com.xiaomi.mms.utils;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import com.xiaomi.mms.utils.sec.AESUtil;
import com.xiaomi.mms.utils.sec.Base64Utils;
import com.xiaomi.mms.utils.sec.RSAUtils;
import com.xiaomi.mms.utils.sec.ReportConstants;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class SmsReportUtil {
    private static final String TAG = SmsReportUtil.class.getSimpleName();
    private static final SmsReportUtil mInstance = new SmsReportUtil();

    private SmsReportUtil() {
    }

    private static String getInfoFromServer(HttpClient object, String object2) throws Exception {
        Object object3 = object.execute((HttpUriRequest)new HttpGet((String)object2));
        if (object3.getStatusLine().getStatusCode() == 200) {
            block5 : {
                object = null;
                try {
                    object = object2 = object3.getEntity().getContent();
                }
                catch (Throwable var1_2) {
                    if (object != null) {
                        object.close();
                    }
                    throw var1_2;
                }
                object3 = SmsReportUtil.inStream2String((InputStream)object2);
                if (object2 == null) break block5;
                object2.close();
            }
            return object3;
        }
        MyLog.w(TAG, "Failed to get info from server. uri=" + object2.toString());
        throw new Exception("Failed to get info from server.");
    }

    private RSAPublicKey getPublicKey(HttpClient httpClient) throws Exception {
        return (RSAPublicKey)RSAUtils.loadPublicKey(this.parsePublicKeyResponse(SmsReportUtil.getInfoFromServer(httpClient, "http://mixin.xiaomi.net/report/pubkey")));
    }

    private static String inStream2String(InputStream inputStream) throws Exception {
        int n;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] arrby = new byte[1024];
        while ((n = inputStream.read(arrby)) != -1) {
            byteArrayOutputStream.write(arrby, 0, n);
        }
        return new String(byteArrayOutputStream.toByteArray());
    }

    public static SmsReportUtil instance() {
        return mInstance;
    }

    private JSONObject makeMsgJson(String string2, String string3, String string4, String string5, String string6) throws Exception {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("userId", (Object)string2);
        jSONObject.put("phoneNum", (Object)string3);
        jSONObject.put("resId", (Object)string4);
        jSONObject.put("reportedPhoneNum", (Object)string5);
        jSONObject.put("smsInfo", (Object)string6);
        return jSONObject;
    }

    private String makePostMsg(RSAPublicKey rSAPublicKey, String string2, String string3, String string4, String string5, String string6) throws Exception {
        String string7 = AESUtil.getAESKeyPlaintext();
        return Base64Utils.encrypt(this.makePostMsgJson(rSAPublicKey, string7, Base64Utils.encrypt(AESUtil.encrypt(this.makeMsgJson(string2, string3, string4, string5, string6).toString(), string7))).toString());
    }

    private JSONObject makePostMsgJson(RSAPublicKey rSAPublicKey, String string2, String string3) throws Exception {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("msgkey", (Object)Base64Utils.encrypt(RSAUtils.encryptByPublicKey(string2, rSAPublicKey)));
        jSONObject.put("msgtxt", (Object)string3);
        return jSONObject;
    }

    private boolean parsePostSmsResponse(String string2) throws Exception {
        JSONObject jSONObject = new JSONObject(string2);
        String string3 = jSONObject.getString("code");
        if (ReportConstants.ReturnCode.OK.equals((Object)string3)) {
            string2 = jSONObject.getJSONObject("info");
            if (!"0".equals((Object)string2.getString("status"))) {
                MyLog.w(TAG, "Failed to post sms with status not Ok. info=" + string2.toString());
                throw new Exception("Failed to post sms with status not Ok.");
            }
            return true;
        }
        MyLog.w(TAG, "Failed to post sms. res=" + string2.toString());
        throw new Exception("Failed to post sms.");
    }

    private String parsePublicKeyResponse(String string2) throws Exception {
        JSONObject jSONObject = new JSONObject(string2);
        String string3 = jSONObject.getString("code");
        if (ReportConstants.ReturnCode.OK.equals((Object)string3)) {
            return jSONObject.getJSONObject("info").getString(ReportConstants.ReturnRSA.JSON_KEY_PUBLIC_KEY);
        }
        MyLog.w(TAG, "Failed to get public key. res=" + string2);
        throw new Exception("Failed to get public key.");
    }

    private static String postInfoToServer(HttpClient object, String object2, String string2) throws Exception {
        HttpPost httpPost = new HttpPost((String)object2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("param", string2));
        httpPost.setEntity((HttpEntity)new UrlEncodedFormEntity((List)arrayList));
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Conteny-type", "application/x-www.form-urlencoded");
        string2 = object.execute((HttpUriRequest)httpPost);
        if (string2.getStatusLine().getStatusCode() == 200) {
            block5 : {
                object = null;
                try {
                    object = object2 = string2.getEntity().getContent();
                }
                catch (Throwable var1_2) {
                    if (object != null) {
                        object.close();
                    }
                    throw var1_2;
                }
                string2 = SmsReportUtil.inStream2String((InputStream)object2);
                if (object2 == null) break block5;
                object2.close();
            }
            return string2;
        }
        MyLog.w(TAG, "Failed to post info to server. uri=" + object2.toString());
        throw new Exception("Failed to post info to server.");
    }

    private boolean postSms(HttpClient httpClient, String string2) throws Exception {
        return this.parsePostSmsResponse(SmsReportUtil.postInfoToServer(httpClient, "http://mixin.xiaomi.net/report/postsms", string2));
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean postSms(Context object, String string2, String charSequence, String string3, String string4, String string5) throws Exception {
        void var6_7;
        void var4_5;
        void var5_6;
        if (TextUtils.isEmpty((CharSequence)var5_6) || TextUtils.isEmpty((CharSequence)var6_7)) {
            throw new Exception("Reported phone num or sms info is invalid.");
        }
        if (TextUtils.isEmpty((CharSequence)charSequence) && TextUtils.isEmpty((CharSequence)string2)) {
            throw new Exception("Should set informer's phone num or mxid.");
        }
        object = new DefaultHttpClient();
        MyLog.v(TAG, "Gets public key.");
        RSAPublicKey rSAPublicKey = this.getPublicKey((HttpClient)object);
        MyLog.v(TAG, "Makes post msg.");
        string2 = this.makePostMsg(rSAPublicKey, string2, (String)charSequence, (String)var4_5, (String)var5_6, (String)var6_7);
        MyLog.v(TAG, "Does posting msg.");
        boolean bl = this.postSms((HttpClient)object, string2);
        string2 = TAG;
        StringBuilder stringBuilder = new StringBuilder().append("Posts sms ");
        object = bl ? "successful" : "failed";
        MyLog.i(string2, stringBuilder.append((String)object).toString());
        return bl;
    }
}


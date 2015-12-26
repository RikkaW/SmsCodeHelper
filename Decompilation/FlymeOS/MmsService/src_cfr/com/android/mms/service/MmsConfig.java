/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.XmlResourceParser
 *  android.os.Bundle
 *  android.telephony.SubscriptionManager
 *  android.telephony.TelephonyManager
 *  android.text.TextUtils
 *  android.util.Base64
 *  android.util.Log
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Map$Entry
 *  java.util.concurrent.ConcurrentHashMap
 *  org.xmlpull.v1.XmlPullParser
 */
package com.android.mms.service;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.android.mms.service.MmsConfigXmlProcessor;
import com.android.mms.service.PhoneUtils;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.xmlpull.v1.XmlPullParser;

public class MmsConfig {
    private static final Map<String, Object> DEFAULTS = new ConcurrentHashMap();
    private final Map<String, Object> mKeyValues = new ConcurrentHashMap();
    private final int mSubId;
    private String mUaProfUrl = null;
    private String mUserAgent = null;

    static {
        DEFAULTS.put("enabledMMS", true);
        DEFAULTS.put("enabledTransID", false);
        DEFAULTS.put("enabledNotifyWapMMSC", false);
        DEFAULTS.put("aliasEnabled", false);
        DEFAULTS.put("allowAttachAudio", true);
        DEFAULTS.put("enableMultipartSMS", true);
        DEFAULTS.put("enableSMSDeliveryReports", true);
        DEFAULTS.put("enableGroupMms", true);
        DEFAULTS.put("supportMmsContentDisposition", true);
        DEFAULTS.put("config_cellBroadcastAppLinks", true);
        DEFAULTS.put("sendMultipartSmsAsSeparateMessages", false);
        DEFAULTS.put("enableMMSReadReports", false);
        DEFAULTS.put("enableMMSDeliveryReports", false);
        DEFAULTS.put("supportHttpCharsetHeader", false);
        DEFAULTS.put("maxMessageSize", 307200);
        DEFAULTS.put("maxImageHeight", 480);
        DEFAULTS.put("maxImageWidth", 640);
        DEFAULTS.put("recipientLimit", Integer.MAX_VALUE);
        DEFAULTS.put("httpSocketTimeout", 60000);
        DEFAULTS.put("aliasMinChars", 2);
        DEFAULTS.put("aliasMaxChars", 48);
        DEFAULTS.put("smsToMmsTextThreshold", -1);
        DEFAULTS.put("smsToMmsTextLengthThreshold", -1);
        DEFAULTS.put("maxMessageTextSize", -1);
        DEFAULTS.put("maxSubjectLength", 40);
        DEFAULTS.put("uaProfTagName", "x-wap-profile");
        DEFAULTS.put("userAgent", "");
        DEFAULTS.put("uaProfUrl", "");
        DEFAULTS.put("httpParams", "");
        DEFAULTS.put("emailGatewayNumber", "");
        DEFAULTS.put("naiSuffix", "");
    }

    public MmsConfig(Context context, int n) {
        this.mSubId = n;
        this.mKeyValues.clear();
        this.mKeyValues.putAll(DEFAULTS);
        this.loadDeviceUaSettings(context);
        Log.v((String)"MmsService", (String)("MmsConfig: mUserAgent=" + this.mUserAgent + ", mUaProfUrl=" + this.mUaProfUrl));
        this.loadFromResources(context);
        Log.v((String)"MmsService", (String)("MmsConfig: all settings -- " + this.mKeyValues));
    }

    private String getNullableStringValue(String object) {
        if ((object = this.mKeyValues.get(object)) != null) {
            return (String)object;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean isValidKey(String class_, String string) {
        if (TextUtils.isEmpty((CharSequence)class_) || !DEFAULTS.containsKey((Object)class_)) return false;
        class_ = (class_ = DEFAULTS.get((Object)class_)) != null ? class_.getClass() : String.class;
        if ("int".equals((Object)string)) {
            if (class_ == Integer.class) return true;
            return false;
        }
        if ("bool".equals((Object)string)) {
            if (class_ == Boolean.class) return true;
            return false;
        }
        if (!"string".equals((Object)string)) return false;
        {
            if (class_ != String.class) return false;
        }
        return true;
    }

    private void loadDeviceUaSettings(Context context) {
        context = (TelephonyManager)context.getSystemService("phone");
        this.mUserAgent = context.getMmsUserAgent();
        this.mUaProfUrl = context.getMmsUAProfUrl();
    }

    private void loadFromResources(Context context) {
        Log.d((String)"MmsService", (String)"MmsConfig.loadFromResources");
        context = context.getResources().getXml(2130837504);
        MmsConfigXmlProcessor mmsConfigXmlProcessor = MmsConfigXmlProcessor.get((XmlPullParser)context);
        mmsConfigXmlProcessor.setMmsConfigHandler(new MmsConfigXmlProcessor.MmsConfigHandler(){

            @Override
            public void process(String string, String string2, String string3) {
                MmsConfig.this.update(string, string2, string3);
            }
        });
        try {
            mmsConfigXmlProcessor.process();
            return;
        }
        finally {
            context.close();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void update(String string, String string2, String string3) {
        try {
            if ("int".equals((Object)string3)) {
                this.mKeyValues.put(string, Integer.parseInt((String)string2));
                return;
            }
            if ("bool".equals((Object)string3)) {
                this.mKeyValues.put(string, Boolean.parseBoolean((String)string2));
                return;
            }
            if ("string".equals((Object)string3)) {
                this.mKeyValues.put(string, string2);
            }
            return;
        }
        catch (NumberFormatException var4_4) {
            Log.e((String)"MmsService", (String)("MmsConfig.update: invalid " + string + "," + string2 + "," + string3));
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public Bundle getCarrierConfigValues() {
        Bundle bundle = new Bundle();
        Iterator<Map.Entry<String, Object>> iterator = this.mKeyValues.entrySet().iterator();
        while (iterator.hasNext()) {
            Class class_ = iterator.next();
            String string = (String)class_.getKey();
            Object object = class_.getValue();
            class_ = object != null ? object.getClass() : String.class;
            if (class_ == Integer.class) {
                bundle.putInt(string, ((Integer)object).intValue());
                continue;
            }
            if (class_ == Boolean.class) {
                bundle.putBoolean(string, ((Boolean)object).booleanValue());
                continue;
            }
            if (class_ != String.class) continue;
            bundle.putString(string, (String)object);
        }
        return bundle;
    }

    public int getSubId() {
        return this.mSubId;
    }

    public static class Overridden {
        private final MmsConfig mBase;
        private final Bundle mOverrides;

        public Overridden(MmsConfig mmsConfig, Bundle bundle) {
            this.mBase = mmsConfig;
            this.mOverrides = bundle;
        }

        private boolean getBoolean(String string) {
            Boolean bl = (Boolean)this.mBase.mKeyValues.get(string);
            if (this.mOverrides != null) {
                return this.mOverrides.getBoolean(string, bl.booleanValue());
            }
            return bl;
        }

        private int getInt(String string) {
            Integer n = (Integer)this.mBase.mKeyValues.get(string);
            if (this.mOverrides != null) {
                return this.mOverrides.getInt(string, n.intValue());
            }
            return n;
        }

        private static String getLine1(Context context, int n) {
            return ((TelephonyManager)context.getSystemService("phone")).getLine1NumberForSubscriber(n);
        }

        private static String getLine1NoCountryCode(Context context, int n) {
            context = (TelephonyManager)context.getSystemService("phone");
            return PhoneUtils.getNationalNumber((TelephonyManager)context, n, context.getLine1NumberForSubscriber(n));
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private String getNai(Context object, int n) {
            byte[] arrby;
            String string = ((TelephonyManager)object.getSystemService("phone")).getNai(SubscriptionManager.getSlotId((int)n));
            if (Log.isLoggable((String)"MmsService", (int)2)) {
                Log.v((String)"MmsService", (String)("MmsConfig.getNai: nai=" + string));
            }
            object = string;
            if (TextUtils.isEmpty((CharSequence)string)) return object;
            String string2 = this.getNaiSuffix();
            object = string;
            if (!TextUtils.isEmpty((CharSequence)string2)) {
                object = string + string2;
            }
            try {
                arrby = Base64.encode((byte[])object.getBytes("UTF-8"), (int)2);
            }
            catch (UnsupportedEncodingException var3_7) {
                object = Base64.encode((byte[])object.getBytes(), (int)2);
            }
            object = arrby;
            try {
                String string3 = new String((byte[])object, "UTF-8");
                return string3;
            }
            catch (UnsupportedEncodingException var3_8) {
                return new String((byte[])object);
            }
        }

        private String getString(String string) {
            if (this.mOverrides != null && this.mOverrides.containsKey(string)) {
                return this.mOverrides.getString(string);
            }
            return this.mBase.getNullableStringValue(string);
        }

        public String getHttpParamMacro(Context context, String string) {
            if ("LINE1".equals((Object)string)) {
                return Overridden.getLine1(context, this.mBase.getSubId());
            }
            if ("LINE1NOCOUNTRYCODE".equals((Object)string)) {
                return Overridden.getLine1NoCountryCode(context, this.mBase.getSubId());
            }
            if ("NAI".equals((Object)string)) {
                return this.getNai(context, this.mBase.getSubId());
            }
            return null;
        }

        public String getHttpParams() {
            return this.getString("httpParams");
        }

        public int getHttpSocketTimeout() {
            return this.getInt("httpSocketTimeout");
        }

        public int getMaxMessageSize() {
            return this.getInt("maxMessageSize");
        }

        public String getNaiSuffix() {
            return this.getString("naiSuffix");
        }

        public boolean getSupportHttpCharsetHeader() {
            return this.getBoolean("supportHttpCharsetHeader");
        }

        public String getUaProfTagName() {
            return this.getString("uaProfTagName");
        }

        public String getUaProfUrl() {
            if (this.mOverrides != null && this.mOverrides.containsKey("uaProfUrl")) {
                return this.mOverrides.getString("uaProfUrl");
            }
            if (!TextUtils.isEmpty((CharSequence)this.mBase.mUaProfUrl)) {
                return this.mBase.mUaProfUrl;
            }
            return this.mBase.getNullableStringValue("uaProfUrl");
        }

        public String getUserAgent() {
            if (this.mOverrides != null && this.mOverrides.containsKey("userAgent")) {
                return this.mOverrides.getString("userAgent");
            }
            if (!TextUtils.isEmpty((CharSequence)this.mBase.mUserAgent)) {
                return this.mBase.mUserAgent;
            }
            return this.mBase.getNullableStringValue("userAgent");
        }
    }

}


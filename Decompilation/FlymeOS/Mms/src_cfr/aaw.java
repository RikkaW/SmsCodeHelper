/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.database.Cursor
 *  android.net.Uri
 *  android.telephony.SmsMessage
 *  android.telephony.TelephonyManager
 *  android.text.TextUtils
 *  android.util.Log
 *  com.meizu.mpay.service.MzMPayHelper
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.lang.reflect.Method
 *  java.text.SimpleDateFormat
 *  java.util.ArrayList
 *  java.util.Date
 *  java.util.HashMap
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.ParseSmsToBubbleUtil;
import com.android.mms.MmsApp;
import com.android.mms.view.MessageListItemSms;
import com.meizu.mpay.service.MzMPayHelper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class aaw {
    private static final String[] a = new String[]{"address", "body", "association_id"};
    private static String b = "type = 5";

    public static a a(String string2) {
        if (!aaw.a()) {
            return null;
        }
        return a.a(string2);
    }

    public static CharSequence a(a a2, CharSequence charSequence) {
        return a2.b(charSequence.toString());
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String a(int var0) {
        var1_1 = (TelephonyManager)MmsApp.c().getSystemService("phone");
        try {
            return (String)TelephonyManager.class.getDeclaredMethod("getSimSerialNumber", new Class[]{Integer.TYPE}).invoke((Object)var1_1, new Object[]{(int)aac.b(var0)});
        }
        catch (NoSuchMethodException var2_3) {
            block4 : {
                var2_3.printStackTrace();
                ** GOTO lbl12
                catch (InvocationTargetException var2_4) {
                    var2_4.printStackTrace();
                    break block4;
                }
                catch (IllegalAccessException var2_5) {
                    var2_5.printStackTrace();
                }
            }
            Log.e((String)"", (String)"getSimSerialNumber error");
            return var1_1.getSimSerialNumber();
        }
    }

    public static String a(gr gr2) {
        if (aaw.a() && gr2.h() != null && gr2.h().size() == 1) {
            return ((gm)gr2.h().get(0)).d();
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(Activity activity, String string2, String string3) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            HashMap hashMap = new HashMap();
            hashMap.put((Object)"simIndex", (Object)"0");
            hashMap.put((Object)"address", (Object)string3);
            try {
                ParseManager.doAction(activity, string2, hashMap);
            }
            catch (Exception var1_2) {
                var1_2.printStackTrace();
            }
            aab.a((Context)activity, "BubbleClick", "ComposeMessageActivity");
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void a(Uri uri) {
        Cursor cursor;
        block4 : {
            MmsApp mmsApp = MmsApp.c();
            try {
                cursor = mmsApp.getContentResolver().query(uri, a, b, null, null);
                if (cursor == null) break block4;
            }
            catch (Throwable var0_1) {
                void var0_2;
                cursor = null;
                if (cursor == null) throw var0_2;
                cursor.close();
                throw var0_2;
            }
            if (cursor.getCount() <= 0 || !cursor.moveToFirst()) break block4;
            Object object = cursor.getString(cursor.getColumnIndex("address"));
            String string2 = cursor.getString(cursor.getColumnIndex("body"));
            if ((object = aaw.a((String)object)) == null || aaw.a((a)object, string2) == null) break block4;
            mmsApp.getContentResolver().delete(uri, null, null);
            {
                catch (Throwable throwable) {}
            }
        }
        if (cursor == null) return;
        cursor.close();
    }

    public static void a(Uri uri, SmsMessage[] arrsmsMessage, int n2) {
        if (aaw.a()) {
            new Thread((Runnable)new aax(uri, arrsmsMessage, n2)).start();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void a(MmsApp mmsApp, boolean bl2) {
        Log.v((String)"ServiceNumberHelper", (String)("init() --> iniSdk(): isOperatorOpen() = " + aaw.a()));
        if (!aaw.a()) return;
        try {
            HashMap hashMap = new HashMap();
            hashMap.put((Object)"ONLINE_UPDATE_SDK", (Object)"1");
            hashMap.put((Object)"SUPPORT_NETWORK_TYPE", (Object)"2");
            hashMap.put((Object)"SECRETKEY", (Object)"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKbSNrFtSCKL/NmEIwczrM8M29UpsfzDJfTTZjIHzF3K3y80+cwXRD7XLUIUiXAQ/YopPCa6hN71u/BM9vOFHsEmy5Ox9ao+NLawMYNNw68BFCQv2aVASYi33fqXfjLTqPHhdnCrVNedSeQQR21x8jayqbKM2DtLwVz8PqYpczw/AgMBAAECgYBoEhqGw8tNqhFazYFgu88h+D6ok/Ny4XerbbqCmbTYCnkDpUP1G8q6fVjBsbgwDplteN4Kty+vPJQ7jHg/YZvWF/o2tF8w/k6bNF2io4FKFOx04ga5yY59Wuu0l9pA1Y1NAE2ykDd/RN/H3yuQG13CR7iXCwuHkCczt9ntYXqKMQJBAM8HP6FFeYCW+IiaTyT6xGgGy4T3DtnwrxszRRBIqeuUXEqqJC58RXLRSziB7cHopJs8A4wCDH0C4dyr2GTiVWkCQQDOSDDI0EP917B/olSRWgwWz28n2pfAIORSoYH+KL0CalAW2mgdIgI2ZhvIbZxyboH4tEoTth0zzKBcJJAfBQdnAkEAp/FNYNonEnVl8AqdoXX71heNCbQRTCK/KeWRZQBNN1oG9FrJNxyAif/WcWSVJvQ+c99fUThoQRERgB23UT954QJAVNISXUBl9Mbv6EuTgoEIX4jEKBsWMwZTXDbVAPE3ZvrYG82K6g/F4SBzZCLOJa+S+fUIULqdn2MQvVK7gEXNGQJAWl55mMtiB5vrCNHjUD4wJkmbVLTlPOcD0w+Q6elYQ/iCPFDP40bVZ+oa3+WUSIQJmkoJYYfNI/Kkq2dAUd2xXQ==");
            LogManager.debug = false;
            LogManager.writeFileLog = true;
            ParseManager.initSdk((Context)mmsApp, "D6mKXM8MEIZU", "", true, true, hashMap);
            Constant.Test = false;
            ParseManager.deleteMatchCache("", "session_lasttime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-09-30 12:00:00").getTime());
            if (bl2) return;
        }
        catch (Exception var0_1) {
            Log.v((String)"ServiceNumberHelper", (String)"init() --> Exception");
            var0_1.printStackTrace();
            return;
        }
        Thread.sleep((long)500);
        ParseSmsToBubbleUtil.beforeHandParseReceiveSms(200, 3);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void a(MessageListItemSms.a var0, JSONArray var1_2) {
        if (var1_2 == null) {
            Log.d((String)"bubble_log", (String)"jsonArray = null");
            return;
        }
        Log.d((String)"bubble_log", (String)"jsonArray != null");
        if (var1_2 == null || var1_2.length() <= 0) ** GOTO lbl16
        var2_3 = var1_2.length();
        Log.d((String)"bubble_log", (String)("jsonArray.length() = " + var2_3));
        if (var2_3 != 1) ** GOTO lbl18
        try {
            var1_2 = var1_2.getJSONObject(0);
            var0.a = (String)JsonUtil.getValueFromJsonObject((JSONObject)var1_2, "btn_name");
            var0.b = (String)JsonUtil.getValueFromJsonObject((JSONObject)var1_2, "action");
            var0.c = (String)JsonUtil.getValueFromJsonObject((JSONObject)var1_2, "action_data");
            var0.g = 1;
            ** GOTO lbl29
lbl16: // 1 sources:
            Log.d((String)"bubble_log", (String)"jsonArray.length() = 0");
            return;
lbl18: // 1 sources:
            if (var2_3 >= 2) {
                var3_4 = var1_2.getJSONObject(0);
                var0.a = (String)JsonUtil.getValueFromJsonObject(var3_4, "btn_name");
                var0.b = (String)JsonUtil.getValueFromJsonObject(var3_4, "action");
                var0.c = (String)JsonUtil.getValueFromJsonObject(var3_4, "action_data");
                var1_2 = var1_2.getJSONObject(1);
                var0.d = (String)JsonUtil.getValueFromJsonObject((JSONObject)var1_2, "btn_name");
                var0.e = (String)JsonUtil.getValueFromJsonObject((JSONObject)var1_2, "action");
                var0.f = (String)JsonUtil.getValueFromJsonObject((JSONObject)var1_2, "action_data");
                var0.g = 2;
            }
lbl29: // 4 sources:
            Log.d((String)"bubble_log", (String)("setBubbleData() --> msgItem.mBubbleSize = " + var0.g));
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static boolean a() {
        if (!MmsApp.d && !wd.c(MmsApp.c().getContentResolver())) {
            return true;
        }
        return false;
    }

    private static int b(int n2) {
        String string2 = zv.c(n2);
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            if (string2.startsWith("46000") || string2.startsWith("46002") || string2.startsWith("46007")) {
                return 1;
            }
            if (string2.startsWith("46001")) {
                return 2;
            }
            if (string2.startsWith("46003")) {
                return 3;
            }
        }
        return -1;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static String b(int var0, String var1_1, Map<String, String> var2_2) {
        var3_3 = 0;
        if (zv.i != 2 || zv.e != 2) ** GOTO lbl20
        var5_4 = aaw.b(0);
        var6_5 = aaw.b(1);
        Log.d((String)"", (String)("operatorOne = " + var5_4 + ", operatorTwo = " + var6_5 + ", address = " + var1_1));
        if (var5_4 != var6_5) {
            var4_6 = ParseManager.getOperatorByNum(var1_1);
            var0 = var4_6 == var5_4 ? var3_3 : (var4_6 == var6_5 ? 1 : -1);
            Log.d((String)"", (String)("getSimIccidAndCenterNum(): address = " + var1_1 + ", operatorType = " + var4_6 + ", seletSlotId = " + var0));
        }
        switch (var0) {
            default: {
                var7_7 = "";
                var1_1 = "";
                ** GOTO lbl27
            }
            case 0: 
            case 1: 
        }
        var7_7 = aaw.a(var0);
        var1_1 = abe.a(var0);
        ** break;
lbl19: // 1 sources:
        ** GOTO lbl27
lbl20: // 1 sources:
        if (zv.e == 1) {
            var0 = zv.i();
            var7_7 = aaw.a(var0);
            var1_1 = abe.a(var0);
        } else {
            var7_7 = "";
            var1_1 = "";
        }
lbl27: // 4 sources:
        if (!TextUtils.isEmpty((CharSequence)var1_1)) {
            var2_2.put("cnum", var1_1);
        } else {
            var2_2.put("code", "CN");
        }
        Log.d((String)"", (String)("getSimIccidAndCenterNum(): centerNum = " + var1_1));
        return var7_7;
    }

    public static class a {
        static String f = "1";
        private static String g;
        public String a;
        String b;
        String c;
        public ArrayList<a> d;
        HashMap<String, String> e;

        public a() {
            this.e = new HashMap();
        }

        public a(String string2) {
            block13 : {
                this.e = new HashMap();
                if (string2 != null) {
                    int n2;
                    try {
                        if (string2.length() <= 0) break block13;
                        string2 = new JSONArray(string2);
                        String[] arrstring = new String[string2.length()];
                        n2 = 0;
                    }
                    catch (Exception var1_2) {
                        var1_2.printStackTrace();
                    }
                    do {
                        block14 : {
                            if (n2 >= string2.length()) break block13;
                            JSONObject jSONObject = string2.getJSONObject(n2);
                            a a2 = new a();
                            if (jSONObject.has("name")) {
                                a2.a = jSONObject.getString("name");
                            }
                            if (jSONObject.has("type")) {
                                a2.b = jSONObject.getString("type");
                            }
                            if (jSONObject.has("action_data")) {
                                a2.c = jSONObject.getString("action_data");
                            }
                            this.a(a2);
                            arrstring[n2] = jSONObject.getString("name");
                            if (!jSONObject.has("secondmenu") || jSONObject.getJSONArray("secondmenu").length() <= 0) break block14;
                            jSONObject = jSONObject.getJSONArray("secondmenu");
                            String[] arrstring = new String[jSONObject.length()];
                            int n3 = 0;
                            do {
                                if (n3 >= jSONObject.length()) break;
                                JSONObject jSONObject2 = jSONObject.getJSONObject(n3);
                                arrstring[n3] = jSONObject2.getString("name");
                                a a3 = new a();
                                if (jSONObject2.has("name")) {
                                    a3.a = jSONObject2.getString("name");
                                }
                                if (jSONObject2.has("type")) {
                                    a3.b = jSONObject2.getString("type");
                                }
                                if (jSONObject2.has("action_data")) {
                                    a3.c = jSONObject2.getString("action_data");
                                }
                                a2.a(a3);
                                ++n3;
                            } while (true);
                        }
                        ++n2;
                    } while (true);
                }
            }
        }

        public static a a(String string2) {
            if (!TextUtils.isEmpty((CharSequence)(string2 = a.a(string2, -1)))) {
                return new a(string2);
            }
            return null;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public static Intent a(String string2, String string3) {
            Intent intent = null;
            Log.d((String)"ServiceNumberHelper", (String)("getPaymentIntent(): address = " + string3));
            if ("repayment".equals((Object)string2)) {
                return MzMPayHelper.getAlipayCardPaymentIntent((String)string3, (String)null, (String)null, (int)0);
            }
            if (!"recharge".equals((Object)string2)) return intent;
            return MzMPayHelper.getAlipayRechargeIntent((String)string3, (String)null, (int)0);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public static String a(String string2, int n2) {
            int n3 = 1;
            g = string2;
            Log.d((String)"ServiceNumberHelper", (String)("getXYJsonByAddr(): mConvAddress = " + g));
            int n4 = n3;
            try {
                if (!TextUtils.isEmpty((CharSequence)f)) {
                    n4 = Integer.parseInt((String)f);
                }
            }
            catch (Exception var4_4) {
                var4_4.printStackTrace();
                n4 = n3;
            }
            Object object = new HashMap();
            String string3 = aaw.b(n2, string2, (Map)object);
            object = ParseManager.queryMenuByPhoneNum(MmsApp.c().getApplicationContext(), string2, n4, string3, object);
            Log.v((String)"", (String)("getXYJsonByAddr() --> address = " + string2 + ", simIccid = " + string3 + ", seletSlotId = " + n2 + ", TextUtils.isEmpty(json1) = " + TextUtils.isEmpty((CharSequence)object)));
            return object;
        }

        public void a(a a2) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            this.d.add((Object)a2);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void a(Activity activity) {
            if (!TextUtils.isEmpty((CharSequence)this.c)) {
                HashMap hashMap = new HashMap();
                hashMap.put((Object)"simIndex", (Object)"0");
                hashMap.put((Object)"address", (Object)g);
                try {
                    ParseManager.doAction(activity, this.c, hashMap);
                }
                catch (Exception var2_3) {
                    var2_3.printStackTrace();
                }
                aab.a((Context)activity, "MenuClick", "ComposeMessageActivity");
            }
        }

        public String b(String string2) {
            return (String)this.e.get((Object)string2);
        }
    }

}


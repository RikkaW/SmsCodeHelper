/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.database.Cursor
 *  android.graphics.drawable.Drawable
 *  android.location.Criteria
 *  android.location.Location
 *  android.location.LocationManager
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Message
 *  android.text.ClipboardManager
 *  android.view.View
 *  android.widget.Toast
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.Calendar
 *  java.util.HashMap
 *  java.util.HashSet
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.action;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.Toast;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.PopupUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.a;
import cn.com.xy.sms.sdk.util.y;
import cn.com.xy.sms.util.SdkCallBack;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbsSdkDoAction {
    public static final int DO_SEND_MAP_QUERY_URL = 4102;
    public static final int SDK_DOACTION_ERROR = -1;
    public static final int SDK_EVENT_INIT_COMPLETE = 0;

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static int a(String string2) {
        int n2 = -1;
        if (string2 == null) return n2;
        try {
            return Integer.valueOf((String)string2);
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
            return -1;
        }
    }

    private int a(Map<String, String> map) {
        if (map != null) {
            return AbsSdkDoAction.a(map.get("simIndex"));
        }
        return -1;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void a(Context context, String string2) {
        String string3 = string2;
        try {
            if (!StringUtils.isNull(string2)) {
                string3 = string2 = string2.trim();
                if (!string2.startsWith("http")) {
                    string3 = "http://" + string2;
                }
            }
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)string3)));
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    private void a(Context context, JSONObject jSONObject) {
        this.zfbRecharge(context, jSONObject, null);
    }

    private static void a(Context context, JSONObject jSONObject, String string2, Map<String, String> map) {
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject == null) {
            jSONObject2 = new JSONObject();
        }
        jSONObject2.put("extend", (Object)new JSONObject(map));
        jSONObject2.put("type", (Object)string2);
        if (map.containsKey("exphone")) {
            jSONObject2.put("exphone", (Object)map.get("exphone"));
        }
        if (map.containsKey("simIndex")) {
            jSONObject2.put("simIndex", (Object)map.get("simIndex"));
        }
        PopupUtil.startWebActivity(context, jSONObject2, string2, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static boolean a(String string2, String string3) {
        new StringBuilder("channel:").append(string2).append(" type:").append(string3);
        int n2 = DexUtil.isServiceChoose(string2, string3);
        if (n2 == 0) {
            new StringBuilder("DexUtil %%% SERVICE_TYPE_DISABLE channel:").append(string2).append(" type:").append(string3);
            return false;
        }
        if (n2 >= 0) return true;
        new StringBuilder("SDK ###  channel:").append(string2).append(" type:").append(string3);
        if ("VMhlWdEwVNEW_LENOVO".equals((Object)string2) || "1i1BDH2wONE+".equals((Object)string2) || "3GdfMSKwHUAWEI".equals((Object)string2) && !"repayment".equalsIgnoreCase(string3) && !"zfb_repayment".equalsIgnoreCase(string3)) return false;
        return true;
    }

    private static Map<String, Integer> b(String string2, String string3) {
        HashMap hashMap = new HashMap();
        try {
            int n2 = Integer.parseInt((String)string2.split("\u5e74")[0]);
            int n3 = Integer.parseInt((String)string2.split("\u6708")[0].split("\u5e74")[1]);
            int n4 = Integer.parseInt((String)string2.split("\u6708")[1].split("\u65e5")[0]);
            int n5 = Integer.parseInt((String)string3.split(":")[0]);
            int n6 = Integer.parseInt((String)string3.split(":")[1]);
            hashMap.put("company_meetingreminder_date_year", n2);
            hashMap.put("company_meetingreminder_date_month", n3);
            hashMap.put("company_meetingreminder_date_day", n4);
            hashMap.put("company_meetingreminder_time_hour", n5);
            hashMap.put("company_meetingreminder_time_minute", n6);
            return hashMap;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return hashMap;
        }
    }

    private static JSONObject b(String string2) {
        if (string2 == null) {
            return null;
        }
        try {
            string2 = new JSONObject(StringUtils.decode(string2));
            return string2;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String c(String string2) {
        if (string2 == null) return "";
        String[] arrstring = string2.split("__ARR__");
        int n2 = arrstring.length;
        int n3 = 0;
        while (n3 < n2) {
            String string3;
            string2 = string3 = arrstring[n3];
            if (!"null".equalsIgnoreCase(string3)) return string2;
            ++n3;
        }
        return "";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void startDisplaySceneActivity(Context context, JSONObject jSONObject, String string2) {
        try {
            Intent intent = new Intent();
            intent.putExtra("actionType", string2);
            if (jSONObject != null) {
                intent.putExtra("JSONDATA", jSONObject.toString());
            }
            intent.setClassName(context, "cn.com.xy.sms.sdk.ui.popu.web.SdkDisplaySceneActivity");
            intent.setFlags(268435456);
            context.startActivity(intent);
            return;
        }
        catch (Throwable var0_1) {
            return;
        }
    }

    public void callPhone(Context context, String string2, int n2) {
        this.callPhone(context, string2, n2, null);
    }

    public void callPhone(Context context, String string2, int n2, Map<String, String> intent) {
        string2 = string2.replace((CharSequence)"-", (CharSequence)"");
        intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse((String)("tel:" + string2)));
        try {
            context.startActivity(intent);
            return;
        }
        catch (Throwable var1_2) {
            return;
        }
    }

    public boolean checkHasAppName(Context context, String string2) {
        try {
            context.getPackageManager().getPackageInfo(string2, 1);
            return true;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return false;
        }
    }

    public void closePopupWindow() {
    }

    public void copyCode(Context context, String string2) {
        try {
            ((ClipboardManager)context.getSystemService("clipboard")).setText((CharSequence)string2);
            Toast.makeText((Context)context, (CharSequence)"\u9a8c\u8bc1\u7801\u5df2\u590d\u5236", (int)1).show();
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public void copyCode(Context context, String string2, Map<String, String> map) {
        ((ClipboardManager)context.getSystemService("clipboard")).setText((CharSequence)string2);
        Toast.makeText((Context)context, (CharSequence)"\u9a8c\u8bc1\u7801\u5df2\u590d\u5236", (int)1).show();
    }

    public long createCard(JSONObject jSONObject, int n2, Map<String, String> map) {
        return 0;
    }

    public abstract void deleteMsgForDatabase(Context var1, String var2);

    /*
     * Exception decompiling
     */
    public void doAction(Context var1_1, String var2_5, Map<String, String> var3_13) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Started 3 blocks at once
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:371)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:449)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    public void doExAction(Context context, String string2, JSONObject jSONObject, Map<String, String> map) {
    }

    public void doRemind(Activity activity, Map<String, Object> map) {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void doRemind(Context context, String string2, String string3, String string4, String string5, String string6, String string7, Map<String, String> map) {
        try {
            long l2;
            string2 = new Intent();
            string2.setType("vnd.android.cursor.item/event");
            string2.setAction("android.intent.action.EDIT");
            if (!StringUtils.isNull(string3)) {
                string2.putExtra("title", string3);
            }
            if (!StringUtils.isNull(string4)) {
                string2.putExtra("eventLocation", string4);
            }
            if (!StringUtils.isNull(string5)) {
                string2.putExtra("description", string5);
            }
            if ((l2 = StringUtils.getLongByString(string6)) != -1) {
                string2.putExtra("beginTime", l2);
            }
            if ((l2 = StringUtils.getLongByString(string7)) != -1) {
                string2.putExtra("endTime", l2);
            }
            string2.putExtra("accessLevel", 0);
            context.startActivity((Intent)string2);
            return;
        }
        catch (Throwable var1_2) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void doRemind(Context context, String string2, Map<String, Object> object) {
        if (context == null || object == null || object.size() == 0) {
            if (LogManager.debug) {
                LogManager.e("doRemind", "activity\u4e3anull\u6216extend\u4e3a\u7a7a", null);
            }
            return;
        }
        String string3 = object.containsKey("title_name") ? object.get("title_name").toString() : null;
        String string4 = object.containsKey("company_meetingreminder_add") ? object.get("company_meetingreminder_add").toString() : null;
        String string5 = object.containsKey("company_meetingreminder_Convener") ? "\u53ec\u96c6\u4eba:" + object.get("company_meetingreminder_Convener") + "  " : null;
        string2 = string5;
        if (object.containsKey("company_meetingreminder_theme")) {
            string2 = String.valueOf((Object)string5) + "\u4e3b\u9898:" + object.get("company_meetingreminder_theme").toString();
        }
        string5 = string2;
        if (object.containsKey("company_meetingreminder_date")) {
            string5 = String.valueOf((Object)string2) + "  \u65f6\u95f4:" + object.get("company_meetingreminder_date");
        }
        string2 = object.containsKey("company_meetingreminder_time") ? String.valueOf((Object)string5) + " " + object.get("company_meetingreminder_time") : string5;
        int n2 = (object = AbsSdkDoAction.b(object.get("company_meetingreminder_date").toString(), object.get("company_meetingreminder_time").toString())).containsKey("company_meetingreminder_date_year") ? (Integer)object.get("company_meetingreminder_date_year") : -1;
        int n3 = object.containsKey("company_meetingreminder_date_month") ? (Integer)object.get("company_meetingreminder_date_month") : -1;
        int n4 = object.containsKey("company_meetingreminder_date_day") ? (Integer)object.get("company_meetingreminder_date_day") : -1;
        int n5 = object.containsKey("company_meetingreminder_time_hour") ? (Integer)object.get("company_meetingreminder_time_hour") : -1;
        int n6 = object.containsKey("company_meetingreminder_time_minute") ? (Integer)object.get("company_meetingreminder_time_minute") : -1;
        int n7 = object.containsKey("company_meetingreminder_minutes_early") ? (Integer)object.get("company_meetingreminder_minutes_early") : 120;
        object = new Intent();
        object.setType("vnd.android.cursor.item/event");
        object.setAction("android.intent.action.EDIT");
        object.putExtra("title", string3);
        object.putExtra("eventLocation", string4);
        object.putExtra("description", string2);
        if (n2 != -1 && n3 != -1 && n4 != -1 && n5 != -1 && n6 != -1 && n7 != -1) {
            string2 = Calendar.getInstance();
            string2.set(n2, n3 - 1, n4, n5, n6);
            string2.add(12, - n7);
            object.putExtra("beginTime", string2.getTimeInMillis());
            string2.set(n2, n3 - 1, n4, n5, n6);
            string2.add(12, -10);
            object.putExtra("endTime", string2.getTimeInMillis());
        }
        object.putExtra("accessLevel", 0);
        object.putExtra("availability", 2);
        context.startActivity((Intent)object);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void downLoadApp(Context context, String string2, String string3, Map<String, String> map) {
        Object var7_9;
        boolean bl2;
        boolean bl3 = bl2 = false;
        try {
            if (!StringUtils.isNull(string2)) {
                if (this.checkHasAppName(context, string2)) {
                    this.openAppByAppName(context, string2);
                    return;
                }
                string2 = new Intent("android.intent.action.VIEW", Uri.parse((String)("market://details?id=" + string2)));
                string2.addFlags(268435456);
                context.startActivity((Intent)string2);
                return;
            }
        }
        catch (Throwable var2_3) {
            bl3 = bl2;
        }
        if (bl3) return;
        string2 = var7_9 = null;
        if (map != null) {
            string2 = var7_9;
            if (map.containsKey("menuName")) {
                string2 = new JSONObject();
                try {
                    string2.put("menuName", (Object)map.get("menuName"));
                }
                catch (JSONException var4_6) {
                    var4_6.printStackTrace();
                }
            }
        }
        this.openUrl(context, string3, (JSONObject)string2);
    }

    public void downLoadUrl(Context context, String string2) {
        AbsSdkDoAction.a(context, string2);
    }

    public void finishActivity(Context context) {
        this.finishActivity(context, null);
    }

    public void finishActivity(Context context, Map<String, String> map) {
        if (context != null && context instanceof Activity) {
            ((Activity)context).finish();
        }
    }

    public abstract String getContactName(Context var1, String var2);

    public JSONObject getContactObj(Context context, String string2) {
        return null;
    }

    public Drawable getDrawableByNumber(Context context, String string2, Map<String, Object> map) {
        return null;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public JSONObject getExtendValue(int var1_1, JSONObject var2_2) {
        switch (var1_1) {
            default: {
                return null;
            }
            case 1: 
        }
        var1_1 = -1;
        if (var2_2 != null) {
            var1_1 = AbsSdkDoAction.a((String)JsonUtil.getValFromJsonObject(var2_2, "simIndex"));
        }
        if ((var2_2 = this.getProviceAndSP(var1_1)) != null) ** GOTO lbl22
        var3_3 = IccidInfoManager.queryDeftIccidInfo(Constant.getContext());
        if (var3_3 == null) return var2_2;
        var4_8 = new JSONObject();
        {
            catch (Throwable var3_7) {}
        }
        try {
            var4_8.put("provice", (Object)var3_3.areaCode);
            var4_8.put("sp", (Object)var3_3.operator);
            return var4_8;
        }
        catch (Throwable var3_4) {
            var2_2 = var4_8;
            ** GOTO lbl26
lbl22: // 2 sources:
            var3_6 = IccidInfoManager.getProviceCode((String)JsonUtil.getValueFromJsonObject(var2_2, "provice"));
            if (var3_6 == null) return var2_2;
            var2_2.put("provice", (Object)var3_6);
            return var2_2;
lbl26: // 2 sources:
            var3_5.printStackTrace();
            return var2_2;
        }
    }

    public Drawable getHeadDrawableByNumber(Context context, String string2, Map<String, Object> map) {
        return null;
    }

    public int[] getHeadDrawableColorByNumber(Context context, String string2, Map<String, Object> map) {
        return null;
    }

    public String getIccidBySimIndex(int n2) {
        return null;
    }

    public void getLocation(Context context, Handler handler) {
        LocationManager locationManager = (LocationManager)context.getSystemService("location");
        if (!locationManager.isProviderEnabled("gps") && !locationManager.isProviderEnabled("network")) {
            handler.obtainMessage(4100).sendToTarget();
            return;
        }
        context = new Criteria();
        context.setAccuracy(2);
        context.setAltitudeRequired(false);
        context.setBearingRequired(false);
        context.setCostAllowed(true);
        context.setPowerRequirement(3);
        context.setSpeedRequired(false);
        context = locationManager.getLastKnownLocation(locationManager.getBestProvider((Criteria)context, false));
        if (context == null) {
            locationManager = locationManager.getLastKnownLocation("network");
            new StringBuilder().append((Object)locationManager);
            context = locationManager;
            if (locationManager == null) {
                handler.obtainMessage(4100).sendToTarget();
                return;
            }
        }
        handler = handler.obtainMessage(4102);
        locationManager = new Bundle();
        locationManager.putDouble("latitude", context.getLatitude());
        locationManager.putDouble("longitude", context.getLongitude());
        handler.setData((Bundle)locationManager);
        handler.sendToTarget();
    }

    public String getPhoneNumberBySimIndex(int n2) {
        return null;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public JSONObject getProviceAndSP(int n2) {
        String string2 = this.getIccidBySimIndex(n2);
        if (string2 == null) {
            return null;
        }
        try {
            IccidInfo iccidInfo = IccidInfoManager.queryIccidInfo(string2, Constant.getContext());
            if (iccidInfo == null) return null;
            string2 = new JSONObject();
        }
        catch (Throwable var3_4) {
            void var3_5;
            string2 = null;
            var3_5.printStackTrace();
            return string2;
        }
        string2.put("provice", (Object)iccidInfo.areaCode);
        string2.put("sp", (Object)iccidInfo.operator);
        return string2;
        {
            catch (Throwable throwable) {}
        }
    }

    public List<JSONObject> getReceiveMsgByReceiveTime(String string2, long l2, long l3, int n2) {
        return null;
    }

    public View getThirdPopupView(Context context, String string2, Map<String, Object> map, SdkCallBack sdkCallBack) {
        return null;
    }

    public int getWifiType(Context context) {
        return 0;
    }

    public boolean isContact(Context context, String string2) {
        return false;
    }

    public boolean isDoubleSimPhone() {
        return true;
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Set<String> loadPublicNumbers(Context var1_1) {
        var6_5 = new HashSet();
        var4_6 = var1_1.getContentResolver().query(Uri.parse((String)"content://mms-sms/canonical-addresses"), new String[]{"address"}, null, null, null);
        if (var4_6 == null) ** GOTO lbl14
        var1_1 = var4_6;
        try {
            var2_10 = var4_6.getColumnIndex("address");
        }
        catch (Throwable var5_13) lbl-1000: // 2 sources:
        {
            var1_1 = var4_6;
            var5_14.printStackTrace();
            try {
                var4_6.close();
                return var6_5;
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
                return var6_5;
            }
        }
        do {
            block24 : {
                var1_1 = var4_6;
                var3_11 = var4_6.moveToNext();
                if (var3_11) break block24;
lbl14: // 3 sources:
                var4_6.close();
                return var6_5;
            }
            var1_1 = var4_6;
            var5_12 = var4_6.getString(var2_10);
            var1_1 = var4_6;
            if (StringUtils.isNull(var5_12)) continue;
            var1_1 = var4_6;
            var5_12 = StringUtils.getPhoneNumberNo86(StringUtils.replaceBlank(var5_12));
            var1_1 = var4_6;
            if (!StringUtils.isNumber(var5_12)) continue;
            var1_1 = var4_6;
            if (StringUtils.isPhoneNumber(var5_12)) continue;
            var1_1 = var4_6;
            var6_5.add((Object)var5_12);
            continue;
            break;
        } while (true);
        catch (Throwable var4_7) {
            var1_1 = null;
lbl48: // 2 sources:
            do {
                try {
                    var1_1.close();
                }
                catch (Throwable var1_3) {
                    var1_3.printStackTrace();
                    throw var4_8;
                }
                do {
                    throw var4_8;
                    break;
                } while (true);
                break;
            } while (true);
        }
        catch (Throwable var1_4) {
            var1_4.printStackTrace();
            return var6_5;
        }
        {
            catch (Throwable var4_9) {
                ** continue;
            }
        }
        catch (Throwable var5_15) {
            var4_6 = null;
            ** GOTO lbl-1000
        }
    }

    public void logError(String string2, String string3, Throwable throwable) {
    }

    public abstract void markAsReadForDatabase(Context var1, String var2);

    public void nearList(Context context, String string2) {
        AbsSdkDoAction.a(context, string2);
    }

    public void nearSite(Context context, String string2, String string3, String string4) {
        this.nearSite(context, string2, string3, string4, null);
    }

    public void nearSite(Context context, String string2, String string3, String string4, Map<String, String> map) {
        Intent intent = new Intent();
        intent.setClassName(context, "cn.com.xy.sms.sdk.ui.popu.web.NearbyPointList");
        intent.putExtra("address", string2);
        intent.putExtra("locationLatitude", string3);
        intent.putExtra("locationLongitude", string4);
        if (map != null && map.containsKey("menuName")) {
            intent.putExtra("menuName", map.get("menuName"));
        }
        context.startActivity(intent);
    }

    public void onEventCallback(int n2, Map<String, Object> map) {
    }

    public void openAppByAppName(Context context, String string2) {
        PackageManager packageManager = context.getPackageManager();
        new Intent();
        context.startActivity(packageManager.getLaunchIntentForPackage(string2));
    }

    public void openAppByAppName(Context context, String string2, String string3) {
        if (this.checkHasAppName(context, string2)) {
            string3 = context.getPackageManager();
            new Intent();
            context.startActivity(string3.getLaunchIntentForPackage(string2));
            return;
        }
        if (!StringUtils.isNull(string3)) {
            this.downLoadUrl(context, string3);
            return;
        }
        Toast.makeText((Context)context, (CharSequence)("please check is installed  " + string2), (int)1).show();
    }

    public void openAppByUrl(Context context, String string2) {
    }

    public void openAppByUrl(Context context, String string2, String string3) {
    }

    public void openMap(Context context, String string2) {
        this.openMap(context, null, string2, 0.0, 0.0);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void openMap(Context context, String string2, String string3, double d2, double d3) {
        new StringBuilder("lon=").append(string3).append(",lat=").append(d3);
        String string4 = string3;
        try {
            string4 = string3 = string3.replace((CharSequence)" ", (CharSequence)",").replace((CharSequence)"\uff08", (CharSequence)",").replace((CharSequence)"(", (CharSequence)",").replace((CharSequence)"\uff0c", (CharSequence)",").replace((CharSequence)"\uff09", (CharSequence)"").replace((CharSequence)")", (CharSequence)"").replace((CharSequence)"?", (CharSequence)"").replace((CharSequence)"&", (CharSequence)"").replace((CharSequence)"#", (CharSequence)"").trim();
            if (StringUtils.isNull(string3)) return;
            string4 = string3;
            Intent intent = new Intent("android.intent.action.VIEW");
            string4 = string3;
            intent.setData(Uri.parse((String)("geo:" + d3 + "," + d2 + "?q=" + string3)));
            string4 = string3;
            intent.addFlags(268435456);
            string4 = string3;
            context.startActivity(intent);
            return;
        }
        catch (Throwable var3_5) {
            var3_5.printStackTrace();
            try {
                if (!StringUtils.isNull(string4)) {
                    if (d2 != 0.0 && d3 != 0.0) {
                        string2 = "http://api.map.baidu.com/marker?location=" + d3 + "," + d2 + "&title=" + string2 + "&content=" + string4;
                    } else {
                        string2 = "http://api.map.baidu.com/geocoder?address=" + string4;
                    }
                    string2 = new Intent("android.intent.action.VIEW", Uri.parse((String)(String.valueOf((Object)string2) + "&output=html&src=xiaoyuan|\u591a\u8da3")));
                    string2.addFlags(268435456);
                    context.startActivity((Intent)string2);
                    return;
                }
                if (d2 == 0.0 || d3 == 0.0) return;
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
                return;
            }
            string2 = new Intent("android.intent.action.VIEW", Uri.parse((String)("http://api.map.baidu.com/geocoder?location=" + d3 + "," + d2 + "&coord_type=gcj02&output=html&src=xiaoyuan|\u591a\u8da3")));
            string2.addFlags(268435456);
            context.startActivity((Intent)string2);
            return;
        }
    }

    public void openMapOnBrowser(Context context, String string2) {
        this.openMapOnBrowser(context, null, string2, 0.0, 0.0);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void openMapOnBrowser(Context context, String string2, String string3, double d2, double d3) {
        try {
            if (!StringUtils.isNull(string3)) {
                string2 = d2 != 0.0 && d3 != 0.0 ? "http://api.map.baidu.com/marker?location=" + d3 + "," + d2 + "&title=" + string2 + "&content=" + string3 : "http://api.map.baidu.com/geocoder?address=" + string3;
                string2 = new Intent("android.intent.action.VIEW", Uri.parse((String)(String.valueOf((Object)string2) + "&output=html&src=xiaoyuan|\u591a\u8da3")));
                string2.addFlags(268435456);
                context.startActivity((Intent)string2);
                return;
            }
            if (d2 == 0.0 || d3 == 0.0) return;
            {
                string2 = new Intent("android.intent.action.VIEW", Uri.parse((String)("http://api.map.baidu.com/geocoder?location=" + d3 + "," + d2 + "&coord_type=gcj02&output=html&src=xiaoyuan|\u591a\u8da3")));
                string2.addFlags(268435456);
                context.startActivity((Intent)string2);
                return;
            }
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
    }

    public abstract void openSms(Context var1, String var2, Map<String, String> var3);

    public void openSmsDetail(Context context, String string2, Map map) {
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void openUrl(Context var1_1, String var2_2, JSONObject var3_4) {
        var6_5 = var3_4;
        if (var3_4 != null) ** GOTO lbl6
        var3_4 = var2_2;
        try {
            var6_5 = new JSONObject();
lbl6: // 2 sources:
            var5_6 = var2_2;
            var3_4 = var2_2;
            if (!StringUtils.isNull(var2_2)) {
                var3_4 = var2_2;
                var3_4 = var2_2 = var2_2.replaceAll("&amp;", "&");
                var5_6 = var2_2 = var2_2.trim();
                var3_4 = var2_2;
                if (!var2_2.startsWith("http")) {
                    var3_4 = var2_2;
                    var5_6 = "http://" + var2_2;
                }
            }
            var3_4 = var5_6;
            var6_5.put("url", (Object)var5_6);
            var3_4 = var5_6;
            var4_7 = PopupUtil.startWebActivity(var1_1, (JSONObject)var6_5, "WEB_URL", "");
            var3_4 = var5_6;
        }
        catch (JSONException var2_3) {
            var2_3.printStackTrace();
            var4_7 = false;
        }
        if (var4_7 != false) return;
        AbsSdkDoAction.a(var1_1, (String)var3_4);
    }

    public void openWeiBoUrl(Context context, String string2) {
        this.openUrl(context, string2, null);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int orderTraiffc(Context context, JSONObject jSONObject, Map<String, String> map) {
        try {
            AbsSdkDoAction.a(context, jSONObject, "WEB_TRAFFIC_CHOOSE", map);
            do {
                return 0;
                break;
            } while (true);
        }
        catch (JSONException var1_2) {
            var1_2.printStackTrace();
            return 0;
        }
    }

    public int otherOrderTraffic(Context context, JSONObject jSONObject, Map<String, String> map) {
        return 0;
    }

    public int otherRecharge(Context context, JSONObject jSONObject, Map<String, String> map) {
        return 0;
    }

    public int otherRepayment(Context context, JSONObject jSONObject, Map<String, String> map) {
        return 0;
    }

    public int otherService(Context context, JSONObject jSONObject, Map<String, String> map) {
        return 0;
    }

    public void parseMsgCallBack(int n2, Map<String, Object> map) {
    }

    public void parseVersionChange(int n2) {
    }

    public int payWaterGas(Context context, JSONObject jSONObject, Map<String, String> map) {
        return 0;
    }

    public int recharge(Context context, JSONObject jSONObject, Map<String, String> map) {
        this.zfbRecharge(context, jSONObject, map);
        return 0;
    }

    /*
     * Exception decompiling
     */
    public void rechargeService(Context var1_1, JSONObject var2_3, Map<String, String> var3_4) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl41 : TryStatement: try { 1[TRYBLOCK]

        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:44)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:22)
        // org.benf.cfr.reader.util.graph.GraphVisitorDFS.process(GraphVisitorDFS.java:68)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner.removeUnreachableCode(Cleaner.java:54)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.RemoveDeterministicJumps.apply(RemoveDeterministicJumps.java:35)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:507)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    public void repayment(Context context, JSONObject jSONObject) {
        this.zfbRepayment(context, jSONObject);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void repaymentService(Context context, JSONObject jSONObject, Map<String, String> object) {
        if (jSONObject.has("type")) {
            try {
                if ("ZFB_REPAYMENT".equalsIgnoreCase(jSONObject.getString("type"))) {
                    this.zfbRepayment(context, jSONObject);
                    return;
                }
                if ("ALIPAY_REPAYMENT".equalsIgnoreCase(jSONObject.getString("type"))) {
                    if (this.checkHasAppName(context, jSONObject.getString("appName"))) {
                        object = (String)JsonUtil.getValueFromJsonObject(jSONObject, "repayAmount");
                        String string2 = (String)JsonUtil.getValueFromJsonObject(jSONObject, "bankCode");
                        y.a(context, (String)JsonUtil.getValueFromJsonObject(jSONObject, "cardNumber"), (String)object, (String)JsonUtil.getValueFromJsonObject(jSONObject, "holderName"), string2, false);
                        return;
                    }
                    jSONObject.put("type", (Object)"WEB_URL");
                    PopupUtil.startWebActivity(context, jSONObject, "WEB_URL", null);
                    return;
                }
            }
            catch (JSONException var1_2) {
                var1_2.printStackTrace();
                return;
            }
            if ("WEXIN_REPAYMENT".equalsIgnoreCase(jSONObject.getString("type"))) {
                object = jSONObject.getString("appName");
                if (this.checkHasAppName(context, (String)object)) {
                    a.a(context, (String)object);
                    return;
                }
                jSONObject.put("type", (Object)"WEB_URL");
                PopupUtil.startWebActivity(context, jSONObject, "WEB_URL", null);
                return;
            }
            if ("TENPAY_REPAYMENT".equalsIgnoreCase(jSONObject.getString("type"))) {
                object = jSONObject.getString("appName");
                if (this.checkHasAppName(context, (String)object)) {
                    a.a(context, (String)object);
                    return;
                }
                jSONObject.put("type", (Object)"WEB_URL");
                PopupUtil.startWebActivity(context, jSONObject, "WEB_URL", null);
                return;
            }
            if ("BAIDU_REPAYMENT".equalsIgnoreCase(jSONObject.getString("type"))) {
                String string3;
                object = string3 = jSONObject.optString("url");
                if (StringUtils.isNull(string3)) {
                    object = jSONObject.optString("appName");
                }
                this.openUrl(context, (String)object, jSONObject);
                return;
            }
            this.otherRepayment(context, jSONObject, (Map<String, String>)object);
        }
    }

    public void replySms(Context context, String string2, String string3, String string4, Map<String, String> map) {
    }

    public abstract void sendSms(Context var1, String var2, String var3, int var4, Map<String, String> var5);

    public void simChange() {
        IccidLocationUtil.changeIccidAreaCode(true);
    }

    public void statisticAction(String string2, int n2, Map<String, Object> map) {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void toService(Context context, String string2, JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        try {
            String string3;
            if (jSONObject.has("extend")) {
                JsonUtil.putJsonToMap(new JSONObject(jSONObject.getString("extend")), hashMap);
            }
            if (!StringUtils.isNull(string3 = jSONObject.optString("type")) && string3.toUpperCase().startsWith("WEB_")) {
                String string4;
                String string5 = string4 = "";
                if (!hashMap.isEmpty()) {
                    if (StringUtils.isNull("")) {
                        string4 = (String)hashMap.get("phone");
                    }
                    string5 = string4;
                    if (StringUtils.isNull(string4)) {
                        string5 = (String)hashMap.get("phoneNum");
                    }
                }
                if ((string4 = (String)hashMap.get("simIndex")) != null) {
                    jSONObject.put("simIndex", (Object)string4);
                }
                PopupUtil.startWebActivity(context, jSONObject, string3, string5);
                return;
            }
        }
        catch (JSONException var4_8) {
            var4_8.printStackTrace();
        }
        if ("repaymentService".equalsIgnoreCase(string2)) {
            this.repaymentService(context, jSONObject, (Map<String, String>)hashMap);
            return;
        }
        if ("rechargeService".equalsIgnoreCase(string2)) {
            this.rechargeService(context, jSONObject, (Map<String, String>)hashMap);
            return;
        }
        if ("trafficService".equalsIgnoreCase(string2)) {
            this.trafficService(context, jSONObject, (Map<String, String>)hashMap);
            return;
        }
        this.otherService(context, jSONObject, (Map<String, String>)hashMap);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void trafficService(Context context, JSONObject jSONObject, Map<String, String> object) {
        if (!jSONObject.has("type")) {
            return;
        }
        try {
            if ("WEB_TRAFFIC_ORDER".equalsIgnoreCase(jSONObject.getString("type"))) {
                String string2;
                String string3 = string2 = "";
                if (object != null) {
                    string3 = string2;
                    if (!object.isEmpty()) {
                        if (StringUtils.isNull("")) {
                            string2 = (String)object.get("phone");
                        }
                        string3 = string2;
                        if (StringUtils.isNull(string2)) {
                            string3 = (String)object.get("phoneNum");
                        }
                    }
                }
                if (object != null && (object = object.get("simIndex")) != null) {
                    jSONObject.put("simIndex", object);
                }
                PopupUtil.startWebActivity(context, jSONObject, "WEB_TRAFFIC_ORDER", string3);
                return;
            }
            if ("TAOBAO_TRAFFIC".equalsIgnoreCase(jSONObject.getString("type"))) {
                object = jSONObject.getString("appName");
                if (this.checkHasAppName(context, (String)object)) {
                    a.a(context, (String)object);
                    return;
                }
                jSONObject.put("type", (Object)"WEB_URL");
                PopupUtil.startWebActivity(context, jSONObject, "WEB_URL", null);
                return;
            }
        }
        catch (JSONException var1_2) {
            var1_2.printStackTrace();
            return;
        }
        if ("WEIXIN_TRAFFIC".equalsIgnoreCase(jSONObject.getString("type"))) {
            object = jSONObject.getString("appName");
            if (this.checkHasAppName(context, (String)object)) {
                a.a(context, (String)object);
                return;
            }
            jSONObject.put("type", (Object)"WEB_URL");
            PopupUtil.startWebActivity(context, jSONObject, "WEB_URL", null);
            return;
        }
        if (!"TENPAY_TRAFFIC".equalsIgnoreCase(jSONObject.getString("type"))) {
            this.otherOrderTraffic(context, jSONObject, (Map<String, String>)object);
            return;
        }
        object = jSONObject.getString("appName");
        if (this.checkHasAppName(context, (String)object)) {
            a.a(context, (String)object);
            return;
        }
        jSONObject.put("type", (Object)"WEB_URL");
        PopupUtil.startWebActivity(context, jSONObject, "WEB_URL", null);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void zfbRecharge(Context context, JSONObject jSONObject, Map<String, String> object) {
        String string2;
        String string3 = null;
        try {
            String string4 = JsonUtil.getValFromJsonObject(jSONObject, "appName").toString();
            if (object != null) {
                string3 = (String)object.get("exphone");
            }
            string2 = StringUtils.isNull(string3) ? XyUtil.getPhoneNumber(context) : string3;
            if (!StringUtils.isNull(string4) && this.checkHasAppName(context, string4)) {
                y.a(context, string2);
                return;
            }
            string3 = "-1";
            if (object != null) {
                string3 = (String)object.get("simIndex");
            }
        }
        catch (Throwable var1_2) {
            return;
        }
        object = string3;
        if (string3 == null) {
            object = "-1";
        }
        jSONObject.put("simIndex", object);
        if (string2 != null) {
            jSONObject.put("chang_phone", (Object)string2);
        }
        jSONObject.put("type", (Object)"WEB_CHONG_ZHI");
        PopupUtil.startWebActivity(context, jSONObject, "WEB_CHONG_ZHI", null);
    }

    public void zfbRepayment(Context context, JSONObject jSONObject) {
        try {
            if (this.checkHasAppName(context, jSONObject.getString("appName"))) {
                String string2 = (String)JsonUtil.getValueFromJsonObject(jSONObject, "repayAmount");
                String string3 = (String)JsonUtil.getValueFromJsonObject(jSONObject, "bankCode");
                y.a(context, (String)JsonUtil.getValueFromJsonObject(jSONObject, "cardNumber"), string2, (String)JsonUtil.getValueFromJsonObject(jSONObject, "holderName"), string3, false);
                return;
            }
            jSONObject.put("type", (Object)"WEB_REPAYMENT");
            PopupUtil.startWebActivity(context, jSONObject, "WEB_REPAYMENT", null);
            return;
        }
        catch (Throwable var1_2) {
            return;
        }
    }
}


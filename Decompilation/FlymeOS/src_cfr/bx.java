/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
import android.content.ContentValues;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.ParseItemManager;
import cn.com.xy.sms.sdk.db.c;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.a.e;
import cn.com.xy.sms.sdk.db.entity.f;
import cn.com.xy.sms.sdk.db.entity.g;
import cn.com.xy.sms.sdk.db.entity.r;
import cn.com.xy.sms.sdk.db.entity.s;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.util.SdkCallBack;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.json.JSONArray;
import org.json.JSONObject;

public final class bx {
    public static final Object a = new Object();
    static boolean b = false;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String a(String string2, int n2, String string3, Map<String, String> object, XyCallBack xyCallBack) {
        String string4;
        block8 : {
            String string5;
            block9 : {
                if (StringUtils.isNull(string2)) {
                    return "";
                }
                if (!ParseItemManager.isInitData()) {
                    if (xyCallBack == null) return "-1";
                    xyCallBack.execute(null, "-1");
                    return "-1";
                }
                try {
                    string5 = StringUtils.getValueByKey(object, "cnum");
                    String string6 = StringUtils.getValueByKey(object, "code");
                    int n3 = e.c(StringUtils.getValueByKey(object, "simIndex"));
                    string4 = string6;
                    if (StringUtils.isNull(string6)) {
                        string4 = IccidLocationUtil.getAreaCodeByCnumOrIccid(string5, n3, string3, string2, true, false);
                    }
                    if ((string6 = e.a(string2, string4, n2)) != null) {
                        string2 = JsonUtil.PubInfoToJson((JSONObject)string6);
                        break block8;
                    }
                    if (object == null || !object.containsKey("SUPPORT_NET_QUERY")) break block9;
                    bx.a(string2, string5, n2, string4, string3, xyCallBack, true);
                    string2 = "";
                    break block8;
                }
                catch (Throwable var0_1) {
                    LogManager.e("PubInfoService", "queryPublicInfo error: " + var0_1.getMessage(), var0_1);
                    if (xyCallBack == null) return "";
                    xyCallBack.execute("");
                    return "";
                }
            }
            bx.a(string2, string5, n2, string4, string3, xyCallBack, false);
            string2 = "";
        }
        if (xyCallBack != null) {
            xyCallBack.execute(string2);
        }
        object = string2;
        if (SysParamEntityManager.getIntParam(Constant.getContext(), "QUERY_ONLINE") == 0) return object;
        IccidLocationUtil.iccidPool.execute((Runnable)new cc(string4, string3));
        return string2;
    }

    public static String a(String string2, int n2, String string3, Map<String, String> jSONArray, SdkCallBack sdkCallBack) {
        if (StringUtils.isPhoneNumber(string2)) {
            if (sdkCallBack != null) {
                sdkCallBack.execute(new Object[]{""});
            }
            return "";
        }
        String string4 = StringUtils.getValueByKey(jSONArray, "cnum");
        String string5 = StringUtils.getValueByKey(jSONArray, "code");
        int n3 = e.c(StringUtils.getValueByKey(jSONArray, "simIndex"));
        new StringBuilder("prev cnum:").append(string4).append(" simIccid: ").append(string3).append(" areaCode=").append(string5).append(" simIndex=").append(n3);
        String string6 = string5;
        if (StringUtils.isNull(string5)) {
            string6 = IccidLocationUtil.getAreaCodeByCnumOrIccid(string4, n3, string3, string2, true, false);
        }
        new StringBuilder("next cnum:").append(string4).append(" simIccid: ").append(string3).append(" areaCode: ").append(string6).append(" numType=").append(n2).append(" phoneNum=").append(string2).append(" simIndex=").append(n3);
        boolean bl2 = false;
        if (jSONArray != null) {
            bl2 = jSONArray.containsKey("EXCLUDE_CN");
        }
        if ((jSONArray = e.a(string2, string6, n2, bl2, string3, string4, n3, sdkCallBack)) != null && jSONArray.length() > 0) {
            string2 = "";
            if (jSONArray.length() >= 3) {
                string2 = jSONArray.toString();
            }
            if (sdkCallBack != null) {
                sdkCallBack.execute(new Object[]{string2});
            }
            NetUtil.executeRunnable(new cb(string6, string3));
            return string2;
        }
        return "";
    }

    public static String a(Map<String, Object> object) {
        if (object != null && !StringUtils.isNull((String)(object = (String)object.get("title_num")))) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("scenetype", object);
                object = jSONObject.toString();
                return object;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
            }
        }
        return "";
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static void a() {
        if (!NetUtil.checkAccessNetWork(2)) {
            return;
        }
        f f2 = c.c();
        new StringBuilder(" menuInfo=").append(f2);
        if (f2 == null) {
            c.c("menuMain");
        }
        if ((f2 = c.c()) == null) return;
        if (System.currentTimeMillis() > f2.e + DexUtil.getUpdateCycleByType(5, 172800000)) {
            c.a(f2, null, true, null);
            return;
        }
        if (SysParamEntityManager.getIntParam(Constant.getContext(), "AUTO_UPDATE_DATA") != 0) return;
        if (!NetUtil.checkAccessNetWork(1)) return;
        c.b(f2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(String string2, String string3) {
        try {
            long l2;
            long l3 = l2 = SysParamEntityManager.getLongParam("LastPublicUpdate", -1, Constant.getContext());
            if (l2 == -1) {
                SysParamEntityManager.setParam("LastPublicUpdate", String.valueOf((long)System.currentTimeMillis()));
                l3 = System.currentTimeMillis();
            }
            l2 = DexUtil.getUpdateCycleByType(1, 2592000000L);
            if (System.currentTimeMillis() > l3 + l2) {
                e.e();
                bx.b(string2, string3);
            }
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            LogManager.e("PubInfoService", "checkPubInfoUpdate " + var0_1.getMessage(), var0_1);
            return;
        }
    }

    private static void a(String string2, String string3, int n2, String string4, String string5, XyCallBack xyCallBack, boolean bl2) {
        if (SysParamEntityManager.getIntParam(Constant.getContext(), "QUERY_ONLINE") == 0) {
            return;
        }
        IccidLocationUtil.pubInfoPool.execute((Runnable)new cd(bl2, string2, string3, string4, string5, n2, xyCallBack));
    }

    static /* synthetic */ void a(String object, String string2, boolean bl2) {
        ArrayList<String> arrayList = e.d();
        if (arrayList.size() == 0) {
            SysParamEntityManager.setParam("LastPublicUpdate", String.valueOf((long)System.currentTimeMillis()));
            object = a;
            synchronized (object) {
                b = false;
                return;
            }
        }
        bz bz2 = new bz(arrayList, (String)object, string2, bl2);
        new StringBuilder("\u6279\u91cf\u66f4\u65b0\u5f00\u59cb\u6267\u884c needUpdateList\uff1a ").append(arrayList.size());
        bs.a(arrayList, (String)object, string2, "1", bz2, bl2);
    }

    public static void a(Map<String, String> map, XyCallBack xyCallBack) {
        f f2 = c.c();
        new StringBuilder(" menuInfo=").append(f2);
        if (f2 == null) {
            c.c("menuMain");
        }
        c.a(map, xyCallBack);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(String[] object) {
        int n2;
        if (object == null || (n2 = object.length) == 0) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int n3 = 0;
        int n4 = 0;
        do {
            if (n3 >= n2) {
                if (n4 > 0) {
                    object = new r();
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                    object.a = stringBuffer.toString();
                    object.c = "1";
                    s.a((r)object);
                }
                s.b("1");
                return;
            }
            stringBuffer.append((String)object[n3]);
            if (++n4 == 10) {
                r r2 = new r();
                r2.a = stringBuffer.toString();
                r2.c = "1";
                s.a(r2);
                stringBuffer.setLength(0);
                n4 = 0;
            } else {
                stringBuffer.append(",");
            }
            ++n3;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String b(String string2, int n2, String string3, Map<String, String> object, XyCallBack xyCallBack) {
        if (StringUtils.isNull(string2)) {
            return "";
        }
        String string4 = StringUtils.getValueByKey(object, "cnum");
        String string5 = StringUtils.getValueByKey(object, "code");
        int n3 = e.c(StringUtils.getValueByKey(object, "simIndex"));
        String string6 = StringUtils.getValueByKey(object, "id");
        object = string5;
        if (StringUtils.isNull(string5)) {
            object = IccidLocationUtil.getAreaCodeByCnumOrIccid(string4, n3, string3, string2, true, false);
        }
        n3 = SysParamEntityManager.getIntParam(Constant.getContext(), "QUERY_ONLINE");
        string5 = e.a(string2, (String)object, n2);
        new StringBuilder("pubInfo=").append((Object)string5);
        if (string5 == null) {
            if (n3 == 1) {
                if (NetUtil.isEnhance()) {
                    bs.a(string2, string4, (String)object, string3, String.valueOf((int)n2), xyCallBack, 0, string6);
                }
                bx.a();
                string2 = "";
            } else {
                bx.a();
                string2 = "";
            }
        } else {
            string2 = JsonUtil.PubInfoToJson((JSONObject)string5);
            XyUtil.doXycallBackResult(xyCallBack, 0, string2, string6);
            bx.a();
        }
        IccidLocationUtil.iccidPool.execute((Runnable)new by((String)object, string3));
        return string2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void b(String string2, String string3) {
        void var1_1;
        Object object = a;
        synchronized (object) {
            if (b) {
                return;
            }
            b = true;
        }
        new ca("updatePubInfo-Thread", string2, (String)var1_1).start();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static /* synthetic */ void b(String string2, String string3, boolean bl2) {
        long l2 = System.currentTimeMillis() - DexUtil.getUpdateCycleByType(1, 2592000000L);
        List<String> list = g.a(l2);
        if (list != null && !list.isEmpty()) {
            int n2;
            ContentValues contentValues;
            try {
                contentValues = new ContentValues();
                contentValues.put("request_time", Long.valueOf((long)System.currentTimeMillis()));
                DBManager.update("tb_netquery_time", contentValues, "request_time < ? and request_time > 0 ", new String[]{String.valueOf((long)l2)});
            }
            catch (Throwable var8_6) {
                var8_6.printStackTrace();
                LogManager.e("NetQueryTimeManager", "clearUpdateUnKnowNumTime error: " + var8_6.getMessage(), var8_6);
            }
            contentValues = new ArrayList();
            while ((n2 = list.size()) > 0) {
                int n3 = n2;
                if (n2 > 10) {
                    n3 = 10;
                }
                contentValues.addAll(list.subList(0, n3));
                bs.a(contentValues, string2, string3, "1", null, false);
                list.removeAll(contentValues);
                contentValues.clear();
            }
        }
    }
}


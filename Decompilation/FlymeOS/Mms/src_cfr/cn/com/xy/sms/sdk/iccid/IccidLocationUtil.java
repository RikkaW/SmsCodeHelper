/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.telephony.TelephonyManager
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.HashMap
 *  java.util.Map$Entry
 *  java.util.concurrent.Executors
 */
package cn.com.xy.sms.sdk.iccid;

import android.content.Context;
import android.telephony.TelephonyManager;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.iccid.a;
import cn.com.xy.sms.sdk.iccid.b;
import cn.com.xy.sms.sdk.iccid.c;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.i;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IccidLocationUtil {
    private static final String a = "iccid";
    private static long b;
    private static HashMap<String, String[]> c;
    private static boolean d;
    public static final ExecutorService iccidPool;
    public static final ExecutorService pubInfoPool;

    static {
        iccidPool = Executors.newFixedThreadPool((int)1);
        pubInfoPool = Executors.newFixedThreadPool((int)2);
        c = new HashMap();
        d = false;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static IccidInfo a(IccidInfo var0, boolean var1_1, boolean var2_2) {
        if (!var1_1) {
            return var0;
        }
        var4_3 = "";
        if (var0 == null) ** GOTO lbl17
        var3_4 = var4_3;
        if (StringUtils.isNull(var0.cnum)) ** GOTO lbl11
        if ("10000".equals((Object)var0.num.trim()) || "10010".equals((Object)var0.num.trim())) ** GOTO lbl-1000
        var3_4 = var4_3;
        if ("10086".equals((Object)var0.num.trim())) lbl-1000: // 2 sources:
        {
            var3_4 = var0.cnum;
        }
lbl11: // 4 sources:
        var4_3 = var0.iccid;
        var5_5 = var0.num;
        var0 = var4_3;
        var4_3 = var3_4;
        var3_4 = var5_5;
        ** GOTO lbl20
lbl17: // 1 sources:
        var0 = null;
        var4_3 = "";
        var3_4 = null;
lbl20: // 2 sources:
        IccidLocationUtil.queryIccid(var4_3, (String)var0, var3_4, var2_2, true);
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String a(String object, String object2, boolean bl2, boolean bl3) {
        IccidLocationUtil.changeIccidAreaCode(false);
        String[] arrstring = (String[])c.get(object);
        if (arrstring != null) {
            if (StringUtils.isNull(arrstring[0]) && (bl3 || System.currentTimeMillis() - Long.valueOf((String)arrstring[1]) > DexUtil.getUpdateCycleByType(4, 30000))) {
                arrstring[1] = String.valueOf((long)System.currentTimeMillis());
                c.put(object, (Object)arrstring);
                IccidLocationUtil.queryIccid(null, (String)object, null, bl2, false);
                object2 = object = ((String[])c.get(object))[0];
                if (!StringUtils.isNull((String)object)) return object2;
                return "CN";
            }
            if (!StringUtils.isNull(arrstring[0])) {
                return arrstring[0];
            }
        }
        object = c.entrySet();
        int n2 = IccidLocationUtil.getOperatorByNum((String)object2);
        object2 = object.iterator();
        boolean bl4 = false;
        object = null;
        do {
            if (!object2.hasNext()) {
                object2 = object;
                if (!StringUtils.isNull((String)object)) return object2;
                return "CN";
            }
            Object object3 = (Map.Entry)object2.next();
            arrstring = (String[])object3.getValue();
            if (StringUtils.isNull(arrstring[0]) && (bl3 || System.currentTimeMillis() - Long.valueOf((String)arrstring[1]) > DexUtil.getUpdateCycleByType(4, 30000))) {
                object3 = (String)object3.getKey();
                arrstring[1] = String.valueOf((long)System.currentTimeMillis());
                c.put(object3, (Object)arrstring);
                IccidLocationUtil.queryIccid(null, (String)object3, null, bl2, false);
                arrstring = (String[])c.get(object3);
            }
            if (object != null) {
                if (object.equals((Object)arrstring[0])) {
                    object2 = object;
                    if (bl4) return object2;
                }
                n2 = Integer.valueOf((String)arrstring[2]) == n2 ? 1 : 0;
                if (bl4 && n2 != 0) {
                    return "CN";
                }
                object2 = object;
                if (bl4) return object2;
                if (n2 == 0) return "CN";
                return arrstring[0];
            }
            object = arrstring[0];
            if (Integer.valueOf((String)arrstring[2]) != n2) continue;
            bl4 = true;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    static void a(Context var0, IccidInfo var1_1, boolean var2_2) {
        var6_3 = null;
        if (var1_1 == null) return;
        if (StringUtils.isNull(var1_1.iccid) != false) return;
        var4_4 = DexUtil.getUpdateCycleByType(16, 4838400000L);
        var3_5 = var1_1 != null && StringUtils.isNull(var1_1.provinces) == false && var1_1.provinces.equals((Object)"\u672a\u77e5") != false && var1_1.updateTime + var4_4 < System.currentTimeMillis() ? true : StringUtils.isNull(var1_1.provinces) != false && var1_1.updateTime == 0 || StringUtils.isNull(var1_1.provinces) == false && var1_1.updateTime < System.currentTimeMillis() - var4_4;
        new StringBuilder("loadIccidLocate-------: ").append(var1_1.areaCode).append(" ").append(var1_1.provinces).append(" ").append(var1_1.operator).append(" ").append(var1_1.city).append(" locateEnable: ").append(var3_5);
        new StringBuilder("info.updateTime < System.currentTimeMillis()-weekTime: ").append(System.currentTimeMillis() - var4_4 - var1_1.updateTime);
        if (!var3_5) {
            return;
        }
        var7_6 = "";
        if (var1_1 == null) ** GOTO lbl24
        var0 = var7_6;
        if (StringUtils.isNull(var1_1.cnum)) ** GOTO lbl18
        if ("10000".equals((Object)var1_1.num.trim()) || "10010".equals((Object)var1_1.num.trim())) ** GOTO lbl-1000
        var0 = var7_6;
        if ("10086".equals((Object)var1_1.num.trim())) lbl-1000: // 2 sources:
        {
            var0 = var1_1.cnum;
        }
lbl18: // 4 sources:
        var6_3 = var1_1.iccid;
        var7_6 = var1_1.num;
        var1_1 = var6_3;
        var6_3 = var0;
        var0 = var7_6;
        ** GOTO lbl28
lbl24: // 1 sources:
        var7_6 = "";
        var0 = null;
        var1_1 = var6_3;
        var6_3 = var7_6;
lbl28: // 2 sources:
        IccidLocationUtil.queryIccid((String)var6_3, (String)var1_1, (String)var0, var2_2, true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static void a(String string2) {
        IccidInfo iccidInfo = IccidInfoManager.queryIccidInfo(string2, Constant.getContext());
        if (!StringUtils.isNull(string2)) {
            if (iccidInfo == null) {
                IccidLocationUtil.queryIccid(null, string2, null, true, true);
            } else {
                iccidPool.execute((Runnable)new a(iccidInfo));
            }
        }
        if (iccidInfo != null) {
            IccidLocationUtil.putIccidAreaCodeToCache(string2, iccidInfo.areaCode, iccidInfo.operator, iccidInfo.userAreacode, iccidInfo.userOperator);
            return;
        }
        IccidLocationUtil.putIccidAreaCodeToCache(string2, null, null, null, null);
    }

    private static void a(String string2, IccidInfo iccidInfo) {
        if (StringUtils.isNull(string2)) {
            return;
        }
        if (iccidInfo == null) {
            IccidLocationUtil.queryIccid(null, string2, null, true, true);
            return;
        }
        iccidPool.execute((Runnable)new a(iccidInfo));
    }

    static /* synthetic */ void a(String string2, String string3, String string4, boolean bl2) {
        IccidLocationUtil.b(string2, string3, string4, false);
    }

    private static void b(String string2, String string3, String string4, boolean bl2) {
        try {
            NetUtil.requestTokenIfNeed(string3);
            b b2 = new b(string3, string2);
            NetUtil.executeAllNetHttpRequest(i.a(string2, string3, string4), "990005", b2, bl2, false, "location", false);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void changeIccidAreaCode(boolean bl2) {
        if (bl2 || !d) {
            d = true;
            c.clear();
            String string2 = DuoquUtils.getSdkDoAction().getIccidBySimIndex(0);
            String string3 = DuoquUtils.getSdkDoAction().getIccidBySimIndex(1);
            if (!StringUtils.isNull(string2)) {
                IccidLocationUtil.a(string2);
            }
            if (!StringUtils.isNull(string3)) {
                IccidLocationUtil.a(string3);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getAreaCodeByCnumOrIccid(String object, int n2, String string2, String string3, boolean bl2, boolean bl3) {
        try {
            String string4 = IccidLocationUtil.getUserAreaCode(string2, n2);
            if (!StringUtils.isNull(string4)) {
                return string4;
            }
            string4 = null;
            String string5 = StringUtils.getPhoneNumberNo86((String)object);
            object = string4;
            if (!StringUtils.isNull(string5)) {
                object = cn.com.xy.sms.sdk.db.entity.a.a.a(string5);
            }
            if (object != null && !StringUtils.isNull(object.c)) {
                return object.c;
            }
            if (StringUtils.isNull(string5)) return IccidLocationUtil.a(string2, string3, bl2, bl3);
            if (object != null && object.g != 0) {
                if (System.currentTimeMillis() - DexUtil.getUpdateCycleByType(0, 7776000000L) <= object.g) return IccidLocationUtil.a(string2, string3, bl2, bl3);
            }
            IccidLocationUtil.queryIccid(string5, string2, string3, bl2, true);
            return IccidLocationUtil.a(string2, string3, bl2, bl3);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return "CN";
        }
    }

    public static String getICCID(Context context) {
        IccidInfo iccidInfo = IccidInfoManager.queryDeftIccidInfo(context);
        if (iccidInfo != null && !StringUtils.isNull(iccidInfo.iccid)) {
            return iccidInfo.iccid;
        }
        if (!StringUtils.isNull((context = (TelephonyManager)context.getSystemService("phone")).getSimSerialNumber())) {
            return context.getSimSerialNumber();
        }
        return "";
    }

    public static HashMap<String, String[]> getIccidAreaCodeMap() {
        return c;
    }

    public static String getIccidAreaCodeMapValueByIndex(String arrstring, int n2) {
        if (StringUtils.isNull((String)arrstring) || c == null || c.isEmpty()) {
            return null;
        }
        if ((arrstring = (String[])c.get((Object)arrstring)) != null && arrstring.length > n2) {
            return arrstring[n2];
        }
        return null;
    }

    public static String[] getIccidInfoArr(String string2) {
        return (String[])c.get((Object)string2);
    }

    public static int getOperatorByICCID(String string2) {
        if (string2 != null && string2.length() > 6) {
            if ((string2 = string2.substring(4, 6)).equals((Object)"00") || string2.equals((Object)"02") || string2.equals((Object)"05") || string2.equals((Object)"08")) {
                return 1;
            }
            if (string2.equals((Object)"01")) {
                return 2;
            }
            if (string2.equals((Object)"03")) {
                return 3;
            }
        }
        return -2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int getOperatorByNum(String string2) {
        int n2 = -1;
        if ("10086,1008611,1008601".indexOf(string2) != -1) {
            return 1;
        }
        if ("10010,10011".indexOf(string2) != -1) {
            return 2;
        }
        if ("10000,10001".indexOf(string2) == -1) return n2;
        return 3;
    }

    public static String getOperatorNum(String string2, String string3) {
        if (StringUtils.isNull(string3)) {
            return String.valueOf((int)IccidLocationUtil.getOperatorByICCID(string2));
        }
        if ("\u79fb\u52a8".equals((Object)string3)) {
            return "1";
        }
        if ("\u8054\u901a".equals((Object)string3)) {
            return "2";
        }
        if ("\u7535\u4fe1".equals((Object)string3)) {
            return "3";
        }
        return "-2";
    }

    public static String getProvince() {
        IccidInfo iccidInfo = IccidInfoManager.queryDeftIccidInfo(Constant.getContext());
        if (iccidInfo != null) {
            return iccidInfo.provinces;
        }
        return "";
    }

    public static String getUserAreaCode(String object, int n2) {
        if (!StringUtils.isNull((String)(object = IccidLocationUtil.getIccidAreaCodeMapValueByIndex((String)object, 3)))) {
            return object;
        }
        if (n2 >= 0 && (object = IccidInfoManager.queryIccidInfo(null, n2)) != null && !StringUtils.isNull(object.userAreacode)) {
            new StringBuilder("\u901a\u8fc7\u5361\u4f4d\u83b7\u53d6\u7528\u6237\u8bbe\u7f6e\u7684\u533a\u57df\u7f16\u7801 areaCode=").append(object.userAreacode);
            return object.userAreacode;
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int getUserOperatorNum(String[] arrstring) {
        int n2;
        int n3 = n2 = -2;
        if (arrstring.length <= 4) return n3;
        n3 = n2;
        if (StringUtils.isNull(arrstring[4])) return n3;
        try {
            return Integer.parseInt((String)arrstring[4]);
        }
        catch (Throwable throwable) {
            return -2;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void putIccidAreaCodeToCache(String string2, String arrstring, String string3, String string4, String string5) {
        if (string2 == null) {
            return;
        }
        String[] arrstring2 = (String[])c.get((Object)string2);
        if (arrstring2 == null) {
            arrstring2 = new String[]{arrstring, String.valueOf((long)System.currentTimeMillis()), IccidLocationUtil.getOperatorNum(string2, string3), string4, IccidLocationUtil.getOperatorNum(null, string5)};
            arrstring = arrstring2;
        } else {
            arrstring2[0] = arrstring;
            arrstring2[1] = String.valueOf((long)System.currentTimeMillis());
            arrstring2[2] = IccidLocationUtil.getOperatorNum(string2, string3);
            arrstring2[3] = string4;
            arrstring2[4] = IccidLocationUtil.getOperatorNum(null, string5);
            arrstring = arrstring2;
        }
        c.put((Object)string2, (Object)arrstring);
    }

    public static void queryIccid(String string2, String string3, String string4, boolean bl2, boolean bl3) {
        if (!NetUtil.isEnhance()) {
            return;
        }
        if (!bl2) {
            IccidLocationUtil.b(string2, string3, string4, false);
            return;
        }
        iccidPool.execute((Runnable)new c(string2, string3, string4));
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void startQueryIccidLocation(HashMap<String, String> object, boolean bl2) {
        if (object != null) {
            String string2 = (String)object.get((Object)"simIccid");
            String string3 = (String)object.get((Object)"receiveNum");
            String string4 = (String)object.get((Object)"centerNum");
            object.get((Object)"sceneId");
            object.get((Object)"sms");
            object.get((Object)"smsLocate");
            object = string2 == null ? IccidInfoManager.queryDeftIccidInfo(Constant.getContext()) : IccidInfoManager.queryIccidInfo(string2, Constant.getContext());
            if (object == null) {
                object = new IccidInfo();
                object.cnum = string4;
                object.iccid = string2;
                object.num = string3;
            } else {
                if (string4 != null && string4.length() > 0 && object.cnum == null) {
                    object.cnum = string4;
                }
                if (string3 != null && string3.length() > 0 && object.num == null) {
                    object.num = string3;
                }
            }
            IccidInfoManager.updateIccidCnum(object.iccid, string4, string3, Constant.getContext());
            string3 = Constant.getContext();
            SysParamEntityManager.getBooleanParam(Constant.getContext(), "SMSLOCATEENABLE");
            IccidLocationUtil.a((Context)string3, (IccidInfo)object, bl2);
        }
    }
}


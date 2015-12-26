/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.concurrent.Executors
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.entity.a.e;
import cn.com.xy.sms.sdk.db.entity.g;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.i;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONObject;

public final class bs {
    private static final ExecutorService a = Executors.newFixedThreadPool((int)1);
    private static boolean b = false;
    private static long c = 0;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected static /* varargs */ Object a(boolean bl2, String string2, XyCallBack object, int n2, Object ... jSONObject) {
        Object var6_6 = null;
        JSONArray jSONArray = null;
        if (jSONObject == null) {
            if (object == null) return jSONArray;
            object.execute(-1);
            return null;
        }
        Object object2 = jSONArray;
        if (!jSONObject[0].toString().equals((Object)"0")) return object2;
        object2 = jSONArray;
        if (jSONObject.length != 2) return object2;
        Map<String, JSONObject> map = i.b(jSONObject[1].toString());
        if (map == null || map.size() == 0) {
            object2 = jSONArray;
            if (object == null) return object2;
            object.execute(new Object[0]);
            return null;
        }
        jSONObject = map.get(map.keySet().iterator().next());
        String string3 = jSONObject.optString("id");
        if ("0".equals((Object)string3)) {
            string2 = var6_6;
            if (object != null) {
                switch (n2) {
                    default: {
                        string2 = var6_6;
                        break;
                    }
                    case 0: {
                        string2 = jSONObject;
                        break;
                    }
                    case 1: {
                        try {
                            object2 = new String[2];
                            jSONArray = jSONObject.optJSONArray("pubMenuInfolist");
                            string2 = var6_6;
                            if (jSONArray == null) break;
                            string2 = var6_6;
                            if (jSONArray.length() <= 0) break;
                            if (bl2) {
                                object2[0] = jSONObject.optString("pubName");
                                object2[1] = jSONArray.toString();
                                XyUtil.doXycallBackResult((XyCallBack)object, object2[0], object2[1]);
                                string2 = var6_6;
                            }
                            XyUtil.doXycallBackResult((XyCallBack)object, jSONArray.toString(), jSONObject.optString("pubId"));
                            string2 = var6_6;
                            break;
                        }
                        catch (Throwable var1_2) {
                            var1_2.printStackTrace();
                            string2 = var6_6;
                            if (object == null) break;
                            object.execute("-1");
                            string2 = var6_6;
                        }
                        break;
                    }
                    case 2: {
                        object.execute(map);
                        string2 = var6_6;
                    }
                }
            }
            object = map.keySet().iterator();
            do {
                object2 = string2;
                if (!object.hasNext()) return object2;
                e.a(map.get((String)object.next()));
            } while (true);
        }
        object2 = jSONArray;
        if (!"1".equals((Object)string3)) return object2;
        NetUtil.QueryTokenRequest(string2);
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a() {
        Object object = bx.a;
        synchronized (object) {
            if (b) {
                return;
            }
            b = true;
        }
        a.execute((Runnable)new bw());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(String string2, String string3, String string4, String string5, String string6, XyCallBack xyCallBack, int n2, String string7) {
        try {
            if (!g.a(string2, string4)) {
                XyUtil.doXycallBackResult(xyCallBack, -1, null, string7);
                return;
            }
            NetUtil.requestTokenIfNeed(string5);
            bv bv2 = new bv(xyCallBack, 0, string7, string5);
            string2 = i.a(string2, string3, string4, string5, string6);
            if (NetUtil.isEnhance()) {
                NetUtil.executePubNumServiceHttpRequest(string2, "990005", bv2, string3, true, false, "pubinfo", true);
                return;
            }
            XyUtil.doXycallBackResult(xyCallBack, -1, null, string7);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void a(List<String> object, String string2, String string3, String string4, XyCallBack xyCallBack, boolean bl2) {
        try {
            xyCallBack = new bu(string2, string3, xyCallBack);
            NetUtil.requestTokenIfNeed(string3);
            object = i.a(object, string2, string3, string4);
            LogManager.e("queryPubInfo", "queryPubInfoByList dataString=" + (String)object);
            if (StringUtils.isNull((String)object)) {
                return;
            }
            if (NetUtil.checkAccessNetWork(1)) {
                NetUtil.executePubNumServiceHttpRequest((String)object, "990005", xyCallBack, null, bl2, false, "pubinfo", true);
                return;
            }
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
    }

    static /* synthetic */ void a(boolean bl2) {
        b = false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(boolean bl2, String string2, String string3, String string4, String string5, String string6, XyCallBack xyCallBack, int n2, boolean bl3, boolean bl4) {
        try {
            if (!g.a(string2, string4)) {
                LogManager.e("pubInfo", "!!!!!!!!!! queryPubInfoRequest num: " + string2 + " areaCode: " + string4);
                XyUtil.doXycallBack(xyCallBack, "");
                return;
            }
            LogManager.e("pubInfo", "########## queryPubInfoRequest num: " + string2 + " areaCode: " + string4);
            NetUtil.requestTokenIfNeed(string5);
            bt bt2 = new bt(false, string2, string3, string4, string5, xyCallBack, n2, string6);
            string2 = i.a(string2, string3, string4, string5, string6);
            if (NetUtil.isEnhance()) {
                NetUtil.executePubNumServiceHttpRequest(string2, "990005", bt2, string3, false, false, "pubinfo", true);
                return;
            }
            XyUtil.doXycallBack(xyCallBack, "");
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }
}


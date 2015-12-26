/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.concurrent.Executors
 */
package cn.com.xy.sms.sdk.net;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.a;
import cn.com.xy.sms.sdk.net.c;
import cn.com.xy.sms.sdk.net.f;
import cn.com.xy.sms.sdk.net.h;
import cn.com.xy.sms.sdk.net.util.i;
import cn.com.xy.sms.sdk.util.KeyManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetUtil {
    public static String APPVERSION;
    public static final String BIZPORT_DOWN_URL = "http://down2.bizport.cn/publicnum/upload/";
    public static final String CheckResourseRequest = "checkResourseRequest";
    public static final int HTTP_ACCESS_FALIE = -8;
    public static final int HTTP_CONN_OUTTIME = -6;
    public static final int HTTP_PACKAGE_TO_BIG = -9;
    public static final int HTTP_THROWS_EXCEPTION = -7;
    public static String POPUP_SERVER_URL_HTTPS;
    public static String PUBINFO_SERVER_URL_HTTPS;
    public static final String QuerySceneRequest = "queryscene";
    public static final String REQ_QUERY_CHECI = "checi";
    public static final String REQ_QUERY_LOCATION = "location";
    public static final String REQ_QUERY_MENUINFO = "menuinfo";
    public static final String REQ_QUERY_PUBINFO = "pubinfo";
    public static final String REQ_QUERY_TOEKN = "token";
    public static String STATSERVICE_URL;
    public static final String UpdatePublicInfoRequest = "updatepublic";
    public static final String UpdateRecognitionJarRequest = "updatejar";
    private static String a;
    private static int b;
    public static ExecutorService pool;
    public static String prex;
    public static String serverUrl;
    public static String serverUrl2;

    static {
        STATSERVICE_URL = "http://scene" + (int)(Math.random() * 10.0 + 1.0) + ".bizport.cn:8981/statservice/stat/";
        serverUrl2 = "http://pubserver" + (int)(Math.random() * 10.0 + 1.0) + ".bizport.cn:9998/pubNumService/";
        serverUrl = "http://smssdk" + (int)(Math.random() * 10.0 + 1.0) + ".bizport.cn/popupservice/api/";
        prex = "http://down1.bizport.cn";
        PUBINFO_SERVER_URL_HTTPS = "https://pubapi" + (int)(Math.random() * 10.0 + 1.0) + ".bizport.cn:9443/pubNumService/";
        POPUP_SERVER_URL_HTTPS = "https://sdkapi" + (int)(Math.random() * 10.0 + 1.0) + ".bizport.cn:8943/popupservice/";
        APPVERSION = "201512021632";
        pool = Executors.newFixedThreadPool((int)1);
        b = 0;
    }

    public static void QueryTokenRequest(String string2) {
        try {
            f f2 = new f();
            NetUtil.executeAllNetHttpRequest(i.a(string2), "990005", f2, false, true, "token", false);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static boolean checkAccessNetWork() {
        return NetUtil.checkAccessNetWork(2);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static boolean checkAccessNetWork(int n2) {
        int n3 = SysParamEntityManager.getIntParam(Constant.getContext(), "SUPPORT_NETWORK_TYPE");
        if (n3 == 0) {
            return false;
        }
        n2 = n2 == 2 ? (n3 == 2 ? XyUtil.checkNetWork(Constant.getContext(), 2) : XyUtil.checkNetWork(Constant.getContext(), 1)) : XyUtil.checkNetWork(Constant.getContext(), 1);
        if (n2 != 0) return false;
        return true;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static boolean checkAccessNetWork(Map<String, String> object) {
        int n2 = XyUtil.checkNetWork(Constant.getContext());
        if (n2 == -1) {
            return false;
        }
        new StringBuilder("extend=").append(object);
        if (object == null) return NetUtil.checkAccessNetWork(2);
        boolean bl2 = object.isEmpty();
        if (bl2) return NetUtil.checkAccessNetWork(2);
        object = (String)object.get("SUPPORT_NETWORK_TYPE");
        int n3 = !StringUtils.isNull((String)object) ? Integer.valueOf((String)object) : 1;
        {
            catch (Throwable throwable) {
                return NetUtil.checkAccessNetWork(2);
            }
        }
        if (n3 != 0) {
            if (n2 != 1) return true;
            if (n3 != 1) return true;
        }
        try {
            LogManager.e("HTTP", "\u4e0d\u652f\u6301\u7f51\u7edc\u8fde,\u6216\u53ea\u652f\u6301wifi: type: " + n3 + " netType=" + n2, null);
            return false;
        }
        catch (Throwable var0_2) {
            // empty catch block
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void executeAllNetHttpRequest(String object, String string2, XyCallBack xyCallBack, boolean bl2, boolean bl3, String string3, boolean bl4) {
        if (!NetUtil.isEnhance()) {
            return;
        }
        String string4 = SysParamEntityManager.getStringParam(Constant.getContext(), "CUSTOM_PUBLIC_SERVER_URL");
        String string5 = string3;
        if (string3 == null) {
            string5 = "";
        }
        string3 = StringUtils.isNull(string4) ? String.valueOf((Object)NetUtil.getPubNumServiceUrl()) + string5 + "/" : String.valueOf((Object)string4) + string5 + "/";
        object = new cn.com.xy.sms.sdk.net.i(string3, (String)object, "", string2, bl3, xyCallBack, bl4);
        if (bl2) {
            NetUtil.executeRunnable((Runnable)object);
            return;
        }
        object.run();
    }

    public static void executeHttpPublicRequest(String string2, String string3, XyCallBack xyCallBack, String string4, Map<String, String> map) {
        NetUtil.executeRunnable(new cn.com.xy.sms.sdk.net.i(string4, string3, null, "990005", false, xyCallBack, true));
    }

    public static void executeHttpRequest(int n2, int n3, String object, XyCallBack xyCallBack, String string2, boolean bl2) {
        object = new h(n3, string2, (String)object, xyCallBack, true);
        if (bl2) {
            NetUtil.executeRunnable((Runnable)object);
            return;
        }
        object.run();
    }

    public static void executeHttpRequest(int n2, String object, XyCallBack xyCallBack, String string2, Map<String, String> map, boolean bl2) {
        object = new h(-1, string2, (String)object, xyCallBack, true);
        if (bl2) {
            NetUtil.executeRunnable((Runnable)object);
            return;
        }
        object.run();
    }

    public static void executeLoginBeforeHttpRequest(String string2, String string3, XyCallBack xyCallBack, String string4, boolean bl2) {
        NetUtil.executeRunnable(new a(string4, null, string2, false, string3, xyCallBack, bl2));
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void executePubNumServiceHttpRequest(String object, String string2, XyCallBack xyCallBack, String string3, boolean bl2, boolean bl3, String string4, boolean bl4) {
        String string5 = SysParamEntityManager.getStringParam(Constant.getContext(), "CUSTOM_PUBLIC_SERVER_URL");
        string3 = string4;
        if (string4 == null) {
            string3 = "";
        }
        string3 = StringUtils.isNull(string5) ? String.valueOf((Object)NetUtil.getPubNumServiceUrl()) + string3 + "/" : String.valueOf((Object)string5) + string3 + "/";
        object = new cn.com.xy.sms.sdk.net.i(string3, (String)object, "", string2, bl3, xyCallBack, bl4);
        if (bl2) {
            NetUtil.executeRunnable((Runnable)object);
            return;
        }
        object.run();
    }

    public static void executeRunnable(Runnable runnable) {
        pool.execute(runnable);
    }

    public static String getPopupServiceUrl() {
        if (NetUtil.isUseHttps()) {
            return POPUP_SERVER_URL_HTTPS;
        }
        return serverUrl;
    }

    public static String getPubNumServiceUrl() {
        if (NetUtil.isUseHttps()) {
            return PUBINFO_SERVER_URL_HTTPS;
        }
        return serverUrl2;
    }

    public static String getUrlWithPara(String string2) {
        return string2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isEnhance() {
        boolean bl2 = SysParamEntityManager.getBooleanParam(Constant.getContext(), "smartsms_enhance", true);
        if ("VMhlWdEwVNEW_LENOVO".equals((Object)KeyManager.channel) && bl2) {
            return NetUtil.checkAccessNetWork(2);
        }
        boolean bl3 = bl2;
        if (!"1w36SBLwVNEW_ZTE".equals((Object)KeyManager.channel)) return bl3;
        bl3 = bl2;
        if (!bl2) return bl3;
        return NetUtil.checkAccessNetWork(2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean isUseHttps() {
        if (b != 0) {
            if (b == 1) {
                return true;
            }
            return false;
        }
        KeyManager.initAppKey();
        for (int i2 = 0; i2 < 7; ++i2) {
            try {
                if (!new String[]{"3GdfMSKwHUAWEI", "5Mj22a4wHUAWEICARD", "J8KeTyOROASamsungReminder", "SAMBANKVwIDAQAB", "SAMCLASSFIYVwIDAQAB", "5xKI47wSAMALL", "XYTEST"}[i2].equals((Object)KeyManager.channel)) continue;
                b = 1;
                return true;
            }
            catch (Throwable var1_1) {
                var1_1.printStackTrace();
                break;
            }
        }
        b = 2;
        return false;
    }

    public static void requestTokenIfNeed(String string2) {
        if (StringUtils.isNull(SysParamEntityManager.getStringParam(Constant.getContext(), "HTTPTOKEN"))) {
            String string3 = string2;
            if (StringUtils.isNull(string2)) {
                IccidInfo iccidInfo = IccidInfoManager.queryDeftIccidInfo(Constant.getContext());
                string3 = string2;
                if (iccidInfo != null) {
                    string3 = iccidInfo.iccid;
                }
            }
            NetUtil.QueryTokenRequest(string3);
        }
    }
}


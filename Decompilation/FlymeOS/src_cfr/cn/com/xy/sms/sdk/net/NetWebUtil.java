/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.OutputStream
 *  java.io.PrintWriter
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.URL
 *  java.net.URLConnection
 *  java.util.concurrent.Executors
 */
package cn.com.xy.sms.sdk.net;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.g;
import cn.com.xy.sms.sdk.net.i;
import cn.com.xy.sms.sdk.util.KeyManager;
import cn.com.xy.sms.sdk.util.d;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetWebUtil {
    public static String WEBACTIVITY_URL;
    public static String WEB_SERVER_URL;
    public static String WEB_SERVER_URL2;
    public static String WEB_SERVER_URL_FLIGHT;
    private static ExecutorService a;

    static {
        WEB_SERVER_URL2 = "http://android" + (int)(Math.random() * 2.0 + 1.0) + ".bizport.cn:9998/AndroidWeb/kbAction";
        WEB_SERVER_URL = "http://android" + (int)(Math.random() * 2.0 + 1.0) + ".bizport.cn:9998/AndroidWeb/";
        WEB_SERVER_URL_FLIGHT = "http://android.bizport.cn:9998/AndroidWeb/flightAction";
        WEBACTIVITY_URL = "http://x.bizport.cn/get_url_red";
        a = Executors.newFixedThreadPool((int)1);
    }

    static /* synthetic */ String a(String string2, String string3, XyCallBack xyCallBack) {
        return NetWebUtil.b(string2, string3, xyCallBack);
    }

    private static void a(Runnable runnable) {
        a.execute(runnable);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private static String b(String var0, String var1_4, XyCallBack var2_8) {
        block20 : {
            var5_9 = null;
            var6_10 = null;
            var8_15 = null;
            var3_16 = null;
            KeyManager.initAppKey();
            var9_17 = new URL((String)var0).openConnection();
            var9_17.addRequestProperty("sdkversion", NetUtil.APPVERSION);
            var9_17.setRequestProperty("accept", "*/*");
            var9_17.setRequestProperty("connection", "Keep-Alive");
            var9_17.setRequestProperty("app_key", i.e);
            var9_17.setDoOutput(true);
            var9_17.setDoInput(true);
            var7_18 = new PrintWriter(var9_17.getOutputStream());
            var0 = var6_10;
            var4_19 = var7_18;
            var3_16 = var8_15;
            var7_18.print(var1_4);
            var0 = var6_10;
            var4_19 = var7_18;
            var3_16 = var8_15;
            var7_18.flush();
            var0 = var6_10;
            var4_19 = var7_18;
            var3_16 = var8_15;
            var0 = var5_9 = var9_17.getInputStream();
            var4_19 = var7_18;
            var3_16 = var5_9;
            var1_4 = new String(d.b((InputStream)var5_9), "UTF-8");
            var0 = var5_9;
            var4_19 = var7_18;
            d.a((Closeable)var5_9);
            var0 = var5_9;
            var4_19 = var7_18;
            var2_8.execute(new String[]{"0", var1_4});
            try {
                var7_18.close();
                var0 = var1_4;
                if (var5_9 == null) break block20;
                var5_9.close();
                var0 = var1_4;
            }
            catch (Throwable var0_3) {
                var0_3.printStackTrace();
                return var1_4;
            }
        }
        do {
            return var0;
            break;
        } while (true);
        catch (Throwable var6_11) {
            var5_9 = null;
            var1_4 = "";
lbl51: // 3 sources:
            var0 = var3_16;
            var4_19 = var5_9;
            var6_12.printStackTrace();
            var0 = var3_16;
            var4_19 = var5_9;
            var2_8.execute(new String[]{"-1", "error: " + var6_12.getMessage()});
            if (var5_9 == null) ** GOTO lbl61
            try {
                var5_9.close();
lbl61: // 2 sources:
                var0 = var1_4;
                if (var3_16 == null) ** continue;
                var3_16.close();
                return var1_4;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return var1_4;
            }
        }
        catch (Throwable var1_5) {
            var4_19 = null;
            var0 = var5_9;
lbl71: // 2 sources:
            do {
                block21 : {
                    if (var4_19 == null) ** GOTO lbl75
                    try {
                        var4_19.close();
lbl75: // 2 sources:
                        if (var0 == null) break block21;
                        var0.close();
                    }
                    catch (Throwable var0_2) {
                        var0_2.printStackTrace();
                        ** continue;
                    }
                }
lbl81: // 2 sources:
                do {
                    throw var1_6;
                    break;
                } while (true);
                break;
            } while (true);
        }
        {
            catch (Throwable var1_7) {
                ** continue;
            }
        }
        {
            catch (Throwable var6_13) {
                var1_4 = "";
                var5_9 = var7_18;
                ** GOTO lbl51
            }
            catch (Throwable var6_14) {
                var3_16 = var5_9;
                var5_9 = var7_18;
                ** GOTO lbl51
            }
        }
    }

    public static void sendPostRequest(String string2, String string3, XyCallBack xyCallBack) {
        a.execute((Runnable)new g(string2, string3, xyCallBack));
    }
}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.telephony.TelephonyManager
 *  java.io.OutputStream
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.HttpURLConnection
 *  java.net.URL
 *  java.net.URLConnection
 *  javax.net.ssl.HttpsURLConnection
 */
package cn.com.xy.sms.sdk.net;

import android.telephony.TelephonyManager;
import cn.com.xy.sms.sdk.Iservice.OnlineParseInterface;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.b;
import cn.com.xy.sms.sdk.net.c;
import cn.com.xy.sms.sdk.net.util.f;
import cn.com.xy.sms.sdk.net.util.i;
import cn.com.xy.sms.sdk.net.util.k;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.KeyManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.d;
import cn.com.xy.sms.util.ParseManager;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

public class a
implements Runnable {
    private static int h = 40000;
    private static String i = null;
    public String a;
    public String b;
    public boolean c = false;
    private c d;
    private XyCallBack e;
    private boolean f = false;
    private String g;

    private a(String string2, c c2, String string3, boolean bl2, XyCallBack xyCallBack) {
        this.a(string2, c2, string3, bl2, null, xyCallBack, false);
    }

    private a(String string2, c c2, String string3, boolean bl2, String string4, XyCallBack xyCallBack) {
        this.a(string2, c2, string3, bl2, string4, xyCallBack, false);
    }

    public a(String string2, c c2, String string3, boolean bl2, String string4, XyCallBack xyCallBack, boolean bl3) {
        this.a(string2, null, string3, bl2, string4, xyCallBack, bl3);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(boolean bl2) {
        try {
            boolean bl3;
            if (i == null) {
                i = ((TelephonyManager)Constant.getContext().getSystemService("phone")).getDeviceId();
            }
            if (bl3 = StringUtils.isNull(i)) {
                return "";
            }
            if (!bl2) return i;
        }
        catch (Throwable var2_3) {
            var2_3.printStackTrace();
            return "";
        }
        return k.a(i);
    }

    public static void a(String string2, int n2) {
        try {
            if (DuoquUtils.getLogSdkDoAction() != null) {
                new StringBuilder("length=").append(n2).append(" req=").append(string2);
            }
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    private void a(String string2, c c2, String string3, boolean bl2, String string4, XyCallBack xyCallBack, boolean bl3) {
        this.d = c2;
        this.a = string2;
        this.b = string3;
        this.e = xyCallBack;
        this.f = bl2;
        this.g = string4;
        this.c = bl3;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean b() {
        if (StringUtils.isNull(cn.com.xy.sms.sdk.net.i.e)) {
            return false;
        }
        OnlineParseInterface onlineParseInterface = DexUtil.getOnlineParseImpl(false);
        if (onlineParseInterface == null) return true;
        try {
            return onlineParseInterface.isAppChannel(cn.com.xy.sms.sdk.net.i.e);
        }
        catch (Throwable var1_1) {
            LogManager.e("BaseHttpRunnable isAppChannel", "\u83b7\u53d6\u7b97\u6cd5\u5305\u5185\u7684\u662f\u5426\u4e3a\u5e94\u7528\u6e20\u9053\u51fa\u73b0\u5f02\u5e38");
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final HttpURLConnection a() {
        try {
            KeyManager.initAppKey();
            HttpsURLConnection httpsURLConnection = this.a.startsWith("https") || this.a.startsWith("HTTPS") ? b.b(this.a) : (HttpURLConnection)new URL(this.a).openConnection();
            httpsURLConnection.setConnectTimeout(h);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setUseCaches(false);
            httpsURLConnection.setInstanceFollowRedirects(true);
            c c2 = this.d;
            this.a(this.f, this.g, (HttpURLConnection)httpsURLConnection);
            if (this.c) {
                httpsURLConnection.addRequestProperty("nz", "1");
            }
            c2 = httpsURLConnection;
            if (!ParseManager.ismUseNewDes()) return c2;
            httpsURLConnection.addRequestProperty("encrypt", "1");
            return httpsURLConnection;
        }
        catch (Throwable var1_2) {
            LogManager.e("HTTP", "BaseHttpRunnable getHttpURLConnection error: " + var1_2.getMessage(), var1_2);
            return null;
        }
    }

    public final void a(int n2, String string2) {
        new StringBuilder("STATUS: ").append(n2).append(" responseStr: ").append(string2);
        if (this.e != null) {
            this.e.execute(n2, string2);
        }
    }

    protected final void a(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.addRequestProperty("x", i.a());
            if (a.b()) {
                if (this.a.endsWith("token/")) {
                    httpURLConnection.addRequestProperty("s", a.a(false));
                    return;
                }
                httpURLConnection.addRequestProperty("p", a.a(true));
                return;
            }
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl2, String string2, HttpURLConnection httpURLConnection) {
        if (httpURLConnection == null) {
            return;
        }
        httpURLConnection.addRequestProperty("Content-Type", "text/xml;UTF-8");
        String string3 = k.a(cn.com.xy.sms.sdk.net.i.d, cn.com.xy.sms.sdk.net.i.e);
        httpURLConnection.addRequestProperty("app-key", cn.com.xy.sms.sdk.net.i.e);
        httpURLConnection.addRequestProperty("app-key-sign", string3);
        httpURLConnection.addRequestProperty("compress", "1");
        httpURLConnection.addRequestProperty("loginid", "");
        httpURLConnection.addRequestProperty("sdkversion", NetUtil.APPVERSION);
        if (bl2) {
            httpURLConnection.addRequestProperty("h-token", k.a("", cn.com.xy.sms.sdk.net.i.e));
            httpURLConnection.addRequestProperty("command", "0");
        } else {
            httpURLConnection.addRequestProperty("command", "1");
        }
        if (!StringUtils.isNull(string2)) {
            httpURLConnection.addRequestProperty("cmd", string2);
        }
        this.a(httpURLConnection);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    @Override
    public void run() {
        block28 : {
            block30 : {
                block29 : {
                    var3_1 = null;
                    var4_7 = null;
                    var4_7 = var5_9 = this.a();
                    var3_1 = var5_9;
                    var5_9.connect();
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    var9_11 = var5_9.getOutputStream();
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    var8_12 = XyUtil.getXyValue(2);
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    new StringBuilder("url=").append(this.a);
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    new StringBuilder("content=").append(this.b);
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    LogManager.e("xyvalue", "keyStr: " + var8_12);
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    var6_14 = var7_13 = f.a(this.b, var8_12);
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    if (this.c) {
                        var4_7 = var5_9;
                        var3_1 = var5_9;
                        var6_14 = StringUtils.compressGZip(var7_13);
                    }
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    var9_11.write(var6_14);
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    var9_11.flush();
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    d.a((Closeable)var9_11);
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    var1_15 = var5_9.getResponseCode();
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    new StringBuilder("URL:").append(this.a).append("  code=").append(var1_15);
                    if (var1_15 != 200) break block28;
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    var9_11 = var5_9.getInputStream();
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    var7_13 = d.b((InputStream)var9_11);
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    var1_15 = var7_13.length;
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    a.a(this.b, var1_15);
                    if ((long)var1_15 <= 26214400) break block29;
                    var4_7 = var5_9;
                    var3_1 = var5_9;
                    this.a(-9, "");
                    if (var5_9 == null) ** GOTO lbl67
                    try {
                        var5_9.disconnect();
lbl67: // 4 sources:
                        do {
                            return;
                            break;
                        } while (true);
                    }
                    catch (Throwable var3_2) {
                        var3_2.printStackTrace();
                        return;
                    }
                }
                var4_7 = var5_9;
                var3_1 = var5_9;
                var2_16 = this.c;
                var6_14 = var7_13;
                if (!var2_16) break block30;
                var3_1 = var5_9;
                var6_14 = StringUtils.uncompressGZip(var7_13);
            }
lbl84: // 2 sources:
            var4_7 = var5_9;
            var3_1 = var5_9;
            this.a(0, new String(f.a(var6_14, var8_12.getBytes()), "UTF-8"));
            var4_7 = var5_9;
            var3_1 = var5_9;
            d.a((Closeable)var9_11);
lbl91: // 2 sources:
            do {
                if (var5_9 == null) ** GOTO lbl67
                try {
                    var5_9.disconnect();
                    return;
                }
                catch (Throwable var3_3) {
                    var3_3.printStackTrace();
                    return;
                }
                break;
            } while (true);
        }
        var4_7 = var5_9;
        var3_1 = var5_9;
        this.a(-8, "");
        ** continue;
        {
            catch (Throwable var5_10) {
                var3_1 = var4_7;
                LogManager.e("HTTP", "2 http error: " + var5_10.getMessage(), var5_10);
                var3_1 = var4_7;
                if (var5_10.getClass() == SocketTimeoutException.class) {
                    var3_1 = var4_7;
                    this.a(-6, "");
lbl113: // 2 sources:
                    do {
                        if (var4_7 == null) ** continue;
                        try {
                            var4_7.disconnect();
                            return;
                        }
                        catch (Throwable var3_4) {
                            var3_4.printStackTrace();
                            return;
                        }
                        break;
                    } while (true);
                }
                var3_1 = var4_7;
                try {
                    this.a(-7, "");
                    ** continue;
                }
                catch (Throwable var4_8) {
                    if (var3_1 != null) {
                        var3_1.disconnect();
                    }
lbl129: // 4 sources:
                    do {
                        throw var4_8;
                        break;
                    } while (true);
                    catch (Throwable var3_5) {
                        var3_5.printStackTrace();
                    }
                }
            }
        }
        ** while (true)
        {
            catch (Throwable var3_6) {
                var6_14 = var7_13;
                ** GOTO lbl84
            }
        }
    }
}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.OutputStream
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.HttpURLConnection
 */
package cn.com.xy.sms.sdk.net;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.a;
import cn.com.xy.sms.sdk.net.c;
import cn.com.xy.sms.sdk.net.i;
import cn.com.xy.sms.sdk.net.util.f;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.d;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;

public final class h
extends a {
    private int d = -1;

    public h(int n2, String string2, String string3, XyCallBack xyCallBack, boolean bl2) {
        super(string2, null, string3, false, null, xyCallBack, true);
        this.d = n2;
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public final void run() {
        block56 : {
            block55 : {
                block54 : {
                    var2_1 = null;
                    var3_6 = null;
                    var3_6 = var4_8 = this.a();
                    var2_1 = var4_8;
                    var4_8.addRequestProperty("sceneType", String.valueOf((int)this.d));
                    var3_6 = var4_8;
                    var2_1 = var4_8;
                    var4_8.addRequestProperty("reqVersion", "5.1.2");
                    var3_6 = var4_8;
                    var2_1 = var4_8;
                    var4_8.addRequestProperty("clientKey", i.e);
                    var3_6 = var4_8;
                    var2_1 = var4_8;
                    var4_8.addRequestProperty("client_key", "123456");
                    var3_6 = var4_8;
                    var2_1 = var4_8;
                    var4_8.connect();
                    var3_6 = var4_8;
                    var2_1 = var4_8;
                    var7_10 = var4_8.getOutputStream();
                    var3_6 = var4_8;
                    var2_1 = var4_8;
                    var5_11 = XyUtil.getXyValue(1);
                    var3_6 = var4_8;
                    var2_1 = var4_8;
                    LogManager.e("xyvalue", "tempKey: " + var5_11, null);
                    var3_6 = var4_8;
                    var2_1 = var4_8;
                    var5_12 = var6_21 = f.a(this.b, var5_11);
                    var3_6 = var4_8;
                    var2_1 = var4_8;
                    if (!this.c) break block54;
                    var3_6 = var4_8;
                    var2_1 = var4_8;
                    var5_13 = StringUtils.compressGZip(var6_21);
                }
                var3_6 = var4_8;
                var2_1 = var4_8;
                var7_10.write((byte[])var5_14);
                var3_6 = var4_8;
                var2_1 = var4_8;
                var7_10.flush();
                var3_6 = var4_8;
                var2_1 = var4_8;
                d.a((Closeable)var7_10);
                var3_6 = var4_8;
                var2_1 = var4_8;
                var1_22 = var4_8.getResponseCode();
                var3_6 = var4_8;
                var2_1 = var4_8;
                new StringBuilder("########sceneType=").append(this.d);
                var3_6 = var4_8;
                var2_1 = var4_8;
                new StringBuilder("url=").append(this.a);
                var3_6 = var4_8;
                var2_1 = var4_8;
                new StringBuilder("content=").append(this.b);
                var3_6 = var4_8;
                var2_1 = var4_8;
                new StringBuilder(" responseCode: ").append(var4_8.getResponseCode());
                if (var1_22 != 200) break block55;
                var3_6 = var4_8;
                var2_1 = var4_8;
                var7_10 = var4_8.getInputStream();
                var3_6 = var4_8;
                var2_1 = var4_8;
                var6_21 = d.b((InputStream)var7_10);
                var3_6 = var4_8;
                var2_1 = var4_8;
                var1_22 = var6_21.length;
                var3_6 = var4_8;
                var2_1 = var4_8;
                h.a(this.b, var1_22);
                if ((long)var1_22 <= 26214400) ** GOTO lbl111
                var3_6 = var4_8;
                var2_1 = var4_8;
                this.a(-9, "");
                if (var4_8 == null) ** GOTO lbl106
                try {
                    var4_8.disconnect();
lbl106: // 4 sources:
                    do {
                        return;
                        break;
                    } while (true);
                }
                catch (Throwable var2_2) {
                    var2_2.printStackTrace();
                    return;
                }
lbl111: // 1 sources:
                var2_1 = var4_8;
                if (!this.c) break block56;
                var2_1 = var4_8;
                var5_15 = StringUtils.uncompressGZip(var6_21);
                if (var5_15 == null) break block56;
lbl118: // 2 sources:
                var3_6 = var4_8;
                var2_1 = var4_8;
                var5_17 = new String((byte[])var5_16, "UTF-8");
                var3_6 = var4_8;
                var2_1 = var4_8;
                this.a(0, var5_17);
                var3_6 = var4_8;
                var2_1 = var4_8;
                new StringBuilder("response=").append(var5_17).append(" len : ").append(var1_22);
                var3_6 = var4_8;
                var2_1 = var4_8;
                d.a((Closeable)var7_10);
lbl134: // 2 sources:
                do {
                    if (var4_8 == null) ** GOTO lbl106
                    try {
                        var4_8.disconnect();
                        return;
                    }
                    catch (Throwable var2_3) {
                        var2_3.printStackTrace();
                        return;
                    }
                    break;
                } while (true);
                catch (Throwable var5_18) {
                    var3_6 = var4_8;
                    var2_1 = var4_8;
                    var5_18.printStackTrace();
                }
            }
            var3_6 = var4_8;
            var2_1 = var4_8;
            this.a(-8, null);
            ** continue;
            {
                catch (Throwable var4_9) {
                    block57 : {
                        var2_1 = var3_6;
                        LogManager.e("HTTP", "http error: " + var4_9.getMessage(), var4_9);
                        var2_1 = var3_6;
                        if (var4_9.getClass() != SocketTimeoutException.class) break block57;
                        var2_1 = var3_6;
                        this.a(-6, null);
lbl164: // 2 sources:
                        do {
                            if (var3_6 == null) ** continue;
                            try {
                                var3_6.disconnect();
                                return;
                            }
                            catch (Throwable var2_4) {
                                var2_4.printStackTrace();
                                return;
                            }
                            break;
                        } while (true);
                    }
                    var2_1 = var3_6;
                    try {
                        this.a(-7, var4_9.getMessage());
                        ** continue;
                    }
                    catch (Throwable var3_7) {
                        if (var2_1 != null) {
                            var2_1.disconnect();
                        }
                        do {
                            throw var3_7;
                            break;
                        } while (true);
                        catch (Throwable var2_5) {
                            var2_5.printStackTrace();
                            throw var3_7;
                        }
                    }
                }
            }
        }
        var5_20 = var6_21;
        ** GOTO lbl118
    }
}


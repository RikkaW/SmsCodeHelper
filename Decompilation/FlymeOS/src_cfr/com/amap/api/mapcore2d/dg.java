/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.database.Cursor
 *  android.net.Proxy
 *  android.net.Uri
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.InetSocketAddress
 *  java.net.Proxy
 *  java.net.ProxySelector
 *  java.net.URI
 *  java.net.URISyntaxException
 *  java.util.Locale
 *  org.apache.http.HttpHost
 */
package com.amap.api.mapcore2d;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.amap.api.mapcore2d.dd;
import com.amap.api.mapcore2d.ed;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;
import org.apache.http.HttpHost;

public class dg {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String a() {
        try {
            String string2 = android.net.Proxy.getDefaultHost();
            String string3 = string2;
            if (string2 != null) return string3;
            return "null";
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            ed.a(var0_1, "ProxyUtil", "getDefHost");
            return "null";
        }
    }

    public static HttpHost a(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                return dg.a(context, new URI("http://restapi.amap.com"));
            }
            context = dg.b(context);
            return context;
        }
        catch (URISyntaxException var0_1) {
            ed.a((Throwable)var0_1, "ProxyUtil", "getProxy");
            var0_1.printStackTrace();
            return null;
        }
        catch (Throwable var0_2) {
            ed.a(var0_2, "ProxyUtil", "getProxy");
            var0_2.printStackTrace();
            return null;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static HttpHost a(Context var0, URI var1_2) {
        block6 : {
            block5 : {
                block4 : {
                    if (dd.g((Context)var0) != 0) return null;
                    try {
                        var0 = ProxySelector.getDefault();
                        var2_3 = -1;
                        var0 = var0.select(var1_2);
                        if (var0 == null || var0.isEmpty()) break block4;
                        var1_2 = (Proxy)var0.get(0);
                        if (var1_2 == null) break block5;
                        var0 = var1_2;
                        if (var1_2.type() != Proxy.Type.DIRECT) break block6;
                        break block5;
                    }
                    catch (Exception var0_1) {
                        ed.a(var0_1, "ProxyUtil", "getProxySelectorCfg");
                        var0_1.printStackTrace();
                    }
                    return null;
                }
                var0 = null;
                ** GOTO lbl21
            }
            var0 = null;
        }
        if (var0 != null && (var1_2 = (InetSocketAddress)var0.address()) != null) {
            var0 = var1_2.getHostName();
            var2_3 = var1_2.getPort();
        } else {
            var0 = null;
        }
        if (dg.a((String)var0, var2_3) == false) return null;
        return new HttpHost((String)var0, var2_3, "http");
    }

    private static boolean a(String string2, int n2) {
        if (string2 != null && string2.length() > 0 && n2 != -1) {
            return true;
        }
        return false;
    }

    private static int b() {
        try {
            int n2 = android.net.Proxy.getDefaultPort();
            return n2;
        }
        catch (Throwable var1_1) {
            ed.a(var1_1, "ProxyUtil", "getDefPort");
            var1_1.printStackTrace();
            return -1;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static HttpHost b(Context var0) {
        block70 : {
            block67 : {
                block71 : {
                    block72 : {
                        block75 : {
                            block76 : {
                                block69 : {
                                    block68 : {
                                        var3_4 = 0;
                                        if (dd.g((Context)var0) != 0) return null;
                                        var5_5 = Uri.parse((String)"content://telephony/carriers/preferapn");
                                        var6_11 = var0.getContentResolver();
                                        var5_5 = var6_11.query((Uri)var5_5, null, null, null, null);
                                        if (var5_5 == null) break block67;
                                        var7_19 = var5_5;
                                        if (!var5_5.moveToFirst()) break block67;
                                        var7_19 = var5_5;
                                        var6_11 = var8_20 = var5_5.getString(var5_5.getColumnIndex("apn"));
                                        if (var8_20 == null) break block68;
                                        var7_19 = var5_5;
                                        var6_11 = var8_20.toLowerCase(Locale.US);
                                    }
                                    if (var6_11 == null) ** GOTO lbl56
                                    var7_19 = var5_5;
                                    if (!var6_11.contains((CharSequence)"ctwap")) break block69;
                                    var7_19 = var5_5;
                                    var6_11 = dg.a();
                                    var7_19 = var5_5;
                                    var2_21 = dg.b();
                                    var7_19 = var5_5;
                                    if (TextUtils.isEmpty((CharSequence)var6_11)) break block70;
                                    var7_19 = var5_5;
                                    var4_22 = var6_11.equals((Object)"null");
                                    if (var4_22) break block70;
                                    var1_24 = 1;
                                    var0 = var6_11;
lbl38: // 2 sources:
                                    if (var1_24 == 0) {
                                        var6_11 = "10.0.0.200";
lbl40: // 2 sources:
                                        var1_24 = var2_21;
                                        var0 = var6_11;
                                        if (var2_21 == -1) {
                                            var1_24 = 80;
                                            var0 = var6_11;
                                        }
lbl45: // 6 sources:
                                        var2_21 = var1_24;
                                        var6_11 = var0;
                                        if (var5_5 != null) {
                                            var5_5.close();
                                            var6_11 = var0;
                                            var2_21 = var1_24;
                                        }
lbl52: // 9 sources:
                                        while (dg.a((String)var6_11, var2_21)) {
                                            return new HttpHost((String)var6_11, var2_21, "http");
                                        }
                                        return null;
                                    }
                                    ** GOTO lbl251
                                }
                                if (var6_11 == null) ** GOTO lbl247
                                var7_19 = var5_5;
                                if (!var6_11.contains((CharSequence)"wap")) break block67;
                                var7_19 = var5_5;
                                var6_11 = dg.a();
                                var7_19 = var5_5;
                                var1_24 = dg.b();
                                var7_19 = var5_5;
                                if (TextUtils.isEmpty((CharSequence)var6_11)) break block71;
                                var7_19 = var5_5;
                                try {
                                    var4_23 = var6_11.equals((Object)"null");
                                    if (var4_23) break block71;
                                    var2_21 = 1;
                                    var0 = var6_11;
                                }
                                catch (Exception var6_15) {
                                    var0 = var5_5;
                                    var5_5 = var6_15;
                                    ** GOTO lbl170
                                }
lbl76: // 2 sources:
                                do {
                                    if (var2_21 == 0) {
                                        var0 = "10.0.0.172";
                                    }
                                    if (var1_24 != -1) ** GOTO lbl45
                                    var1_24 = 80;
                                    ** GOTO lbl45
                                    break;
                                } while (true);
                                catch (Throwable var5_6) {
                                    var6_11 = ed.b();
                                    if (var6_11 != null) {
                                        var6_11.b(var5_6, "ProxyUtil", "getHostProxy2");
                                    }
                                    var5_6.printStackTrace();
                                    var2_21 = var1_24;
                                    var6_11 = var0;
                                }
                                ** GOTO lbl52
                                catch (SecurityException var6_12) {
                                    block73 : {
                                        block74 : {
                                            var5_5 = null;
                                            var1_24 = -1;
lbl93: // 4 sources:
                                            var10_25 = null;
                                            var9_26 = null;
                                            var7_19 = var5_5;
                                            ed.a((Throwable)var6_11, "ProxyUtil", "getHostProxy");
                                            var7_19 = var5_5;
                                            var0 = dd.i((Context)var0);
                                            if (var0 == null) break block72;
                                            var7_19 = var5_5;
                                            var0 = var0.toLowerCase(Locale.US);
                                            var7_19 = var5_5;
                                            var8_20 = dg.a();
                                            var7_19 = var5_5;
                                            var2_21 = dg.b();
                                            var7_19 = var5_5;
                                            if (var0.indexOf("ctwap") == -1) break block73;
                                            var0 = var9_26;
                                            var1_24 = var3_4;
                                            var7_19 = var5_5;
                                            if (TextUtils.isEmpty((CharSequence)var8_20)) break block74;
                                            var0 = var9_26;
                                            var1_24 = var3_4;
                                            var7_19 = var5_5;
                                            if (var8_20.equals((Object)"null")) break block74;
                                            var1_24 = 1;
                                            var0 = var8_20;
                                        }
                                        if (var1_24 == 0) {
                                            var0 = "10.0.0.200";
                                        }
                                        var6_11 = var0;
                                        if (var2_21 != -1) break block75;
                                        var1_24 = 80;
lbl132: // 4 sources:
                                        do {
                                            var2_21 = var1_24;
                                            var6_11 = var0;
                                            if (var5_5 == null) ** GOTO lbl52
                                            try {
                                                var5_5.close();
                                                var2_21 = var1_24;
                                                var6_11 = var0;
                                            }
                                            catch (Throwable var5_7) {
                                                var6_11 = ed.b();
                                                if (var6_11 != null) {
                                                    var6_11.b(var5_7, "ProxyUtil", "getHostProxy2");
                                                }
                                                var5_7.printStackTrace();
                                                var2_21 = var1_24;
                                                var6_11 = var0;
                                            }
                                            ** GOTO lbl52
                                            break;
                                        } while (true);
                                    }
                                    var7_19 = var5_5;
                                    var6_11 = var10_25;
                                    if (var0.indexOf("wap") == -1) break block75;
                                    var7_19 = var5_5;
                                    if (TextUtils.isEmpty((CharSequence)var8_20)) break block76;
                                    var7_19 = var5_5;
                                    if (var8_20.equals((Object)"null")) break block76;
                                    var0 = var8_20;
                                    var1_24 = 1;
lbl162: // 2 sources:
                                    do {
                                        if (var1_24 == 0) {
                                            var0 = "10.0.0.200";
                                        }
                                        var1_24 = 80;
                                        ** GOTO lbl132
                                        break;
                                    } while (true);
                                }
                                catch (Exception var5_8) {
                                    var0 = null;
                                    var1_24 = -1;
lbl170: // 4 sources:
                                    var7_19 = null;
                                    ed.a((Throwable)var5_5, "ProxyUtil", "getHostProxy1");
                                    var5_5.printStackTrace();
                                    var2_21 = var1_24;
                                    var6_11 = var7_19;
                                    if (var0 == null) ** GOTO lbl52
                                    try {
                                        var0.close();
                                        var2_21 = var1_24;
                                        var6_11 = var7_19;
                                    }
                                    catch (Throwable var0_1) {
                                        var5_5 = ed.b();
                                        if (var5_5 != null) {
                                            var5_5.b(var0_1, "ProxyUtil", "getHostProxy2");
                                        }
                                        var0_1.printStackTrace();
                                        var2_21 = var1_24;
                                        var6_11 = var7_19;
                                    }
                                    ** GOTO lbl52
                                }
                                catch (Throwable var0_2) {
                                    var7_19 = null;
lbl192: // 3 sources:
                                    do {
                                        if (var7_19 != null) {
                                            var7_19.close();
                                        }
                                        do {
                                            throw var0;
                                            break;
                                        } while (true);
                                        catch (Throwable var5_9) {
                                            var6_11 = ed.b();
                                            if (var6_11 != null) {
                                                var6_11.b(var5_9, "ProxyUtil", "getHostProxy2");
                                            }
                                            var5_9.printStackTrace();
                                            throw var0;
                                        }
                                        break;
                                    } while (true);
                                }
                                {
                                    catch (Throwable var0_3) {
                                        ** GOTO lbl192
                                    }
                                }
                                {
                                    catch (Throwable var5_10) {
                                        var7_19 = var0;
                                        var0 = var5_10;
                                        ** continue;
                                    }
                                }
                                catch (Exception var6_13) {
                                    var0 = var5_5;
                                    var5_5 = var6_13;
                                    var1_24 = -1;
                                    ** GOTO lbl170
                                }
                                catch (Exception var6_14) {
                                    var0 = var5_5;
                                    var5_5 = var6_14;
                                    var1_24 = var2_21;
                                    ** GOTO lbl170
                                }
                                catch (SecurityException var6_16) {
                                    var1_24 = -1;
                                    ** GOTO lbl93
                                }
                                catch (SecurityException var6_17) {
                                    var1_24 = var2_21;
                                    ** GOTO lbl93
                                }
                                catch (SecurityException var6_18) {
                                    ** GOTO lbl93
                                }
                            }
                            var1_24 = 0;
                            var0 = null;
                            ** while (true)
                        }
                        var0 = var6_11;
                        var1_24 = var2_21;
                        ** GOTO lbl132
                    }
                    var0 = null;
                    ** while (true)
                }
                var2_21 = 0;
                var0 = null;
                ** while (true)
            }
            var1_24 = -1;
            var0 = null;
            ** GOTO lbl45
lbl251: // 1 sources:
            var6_11 = var0;
            ** GOTO lbl40
        }
        var1_24 = 0;
        var0 = null;
        ** GOTO lbl38
    }
}


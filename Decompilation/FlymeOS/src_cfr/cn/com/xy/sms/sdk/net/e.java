/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  javax.net.ssl.HttpsURLConnection
 */
package cn.com.xy.sms.sdk.net;

import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.d;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

final class e
implements HostnameVerifier {
    private /* synthetic */ d a;

    e(d d2) {
        this.a = d2;
    }

    /*
     * Enabled aggressive exception aggregation
     */
    @Override
    public final boolean verify(String string2, SSLSession sSLSession) {
        boolean bl2;
        block4 : {
            block3 : {
                bl2 = false;
                try {
                    LogManager.e("Https", "hostName: " + string2, null);
                    if (d.a(this.a) == 0 && string2 != null && (string2.indexOf("duoqu.in") != -1 || string2.indexOf("bizport.cn") != -1)) break block3;
                    HostnameVerifier hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
                    if (hostnameVerifier != null) {
                        bl2 = hostnameVerifier.verify(string2, sSLSession);
                        return bl2;
                    }
                    break block4;
                }
                catch (Throwable var1_2) {
                    var1_2.printStackTrace();
                    return false;
                }
            }
            bl2 = true;
        }
        return bl2;
    }
}


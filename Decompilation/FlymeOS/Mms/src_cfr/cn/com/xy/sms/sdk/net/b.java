/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.HttpURLConnection
 *  java.net.URL
 *  java.net.URLConnection
 *  javax.net.ssl.HttpsURLConnection
 *  javax.net.ssl.SSLSocketFactory
 */
package cn.com.xy.sms.sdk.net;

import cn.com.xy.sms.sdk.net.d;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public final class b {
    private SSLSocketFactory a = null;

    public static HttpsURLConnection a(String string2) {
        return b.a(string2, 1);
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static HttpsURLConnection a(String string2, int n2) {
        b b2;
        try {
            b2 = new b();
            string2 = (HttpsURLConnection)new URL(string2).openConnection();
        }
        catch (Throwable var2_3) {
            void var2_4;
            string2 = null;
            var2_4.printStackTrace();
            return string2;
        }
        b2.a((HttpsURLConnection)string2, n2);
        string2.connect();
        return string2;
        {
            catch (Throwable throwable) {}
        }
    }

    private void a(URL uRL) {
        if ((uRL = (HttpURLConnection)uRL.openConnection()) instanceof HttpsURLConnection) {
            this.a((HttpsURLConnection)uRL, 1);
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void a(HttpsURLConnection httpsURLConnection, int n2) {
        if (this.a == null) {
            this.a = d.a(n2).a();
        }
        if (this.a != null) {
            httpsURLConnection.setSSLSocketFactory(this.a);
        }
        try {
            httpsURLConnection.setHostnameVerifier(d.a(n2).b());
            return;
        }
        catch (Throwable var1_3) {
            throw new IOException(var1_3);
        }
        catch (Throwable throwable) {
            if (!(throwable instanceof IOException)) throw new IOException(throwable);
            throw (IOException)throwable;
        }
    }

    public static HttpsURLConnection b(String string2) {
        b b2;
        try {
            b2 = new b();
            string2 = (HttpsURLConnection)new URL(string2).openConnection();
        }
        catch (Throwable var0_1) {
            return null;
        }
        try {
            b2.a((HttpsURLConnection)string2, 1);
        }
        finally {
            return string2;
        }
    }
}


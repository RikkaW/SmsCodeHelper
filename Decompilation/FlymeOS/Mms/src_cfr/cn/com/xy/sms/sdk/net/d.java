/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.security.KeyStore
 *  java.security.SecureRandom
 *  java.security.cert.Certificate
 *  java.security.cert.CertificateFactory
 *  java.security.cert.X509Certificate
 *  javax.net.ssl.SSLContext
 *  javax.net.ssl.SSLSocketFactory
 *  javax.net.ssl.TrustManagerFactory
 */
package cn.com.xy.sms.sdk.net;

import cn.com.xy.sms.sdk.net.e;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class d {
    private static d d = null;
    private static d e = null;
    private SSLContext a;
    private SSLSocketFactory b;
    private HostnameVerifier c;
    private int f = 1;

    private d(int n2) {
        this.f = n2;
        this.c = new e(this);
    }

    static /* synthetic */ int a(d d2) {
        return d2.f;
    }

    public static d a(int n2) {
        synchronized (d.class) {
            if (n2 == 0) {
                if (e == null) {
                    e = new d(n2);
                }
                d d2 = e;
                return d2;
            }
            if (d == null) {
                d = new d(n2);
            }
            d d3 = d;
            return d3;
        }
    }

    private static Certificate a(String string2, String string3) {
        return CertificateFactory.getInstance((String)string3).generateCertificate((InputStream)new ByteArrayInputStream(string2.getBytes()));
    }

    private static X509Certificate a(String string2) {
        return (X509Certificate)CertificateFactory.getInstance((String)"X.509").generateCertificate((InputStream)new ByteArrayInputStream(string2.getBytes()));
    }

    private void a(HostnameVerifier hostnameVerifier) {
        this.c = hostnameVerifier;
    }

    private SSLContext c() {
        synchronized (this) {
            TrustManagerFactory trustManagerFactory;
            if (this.a == null) {
                trustManagerFactory = TrustManagerFactory.getInstance((String)TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(null);
                SSLContext sSLContext = SSLContext.getInstance((String)"TLS");
                sSLContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
                this.a = sSLContext;
            }
            trustManagerFactory = this.a;
            return trustManagerFactory;
        }
    }

    public final SSLSocketFactory a() {
        synchronized (this) {
            if (this.b == null) {
                this.b = this.c().getSocketFactory();
            }
            SSLSocketFactory sSLSocketFactory = this.b;
            return sSLSocketFactory;
        }
    }

    public final HostnameVerifier b() {
        return this.c;
    }
}


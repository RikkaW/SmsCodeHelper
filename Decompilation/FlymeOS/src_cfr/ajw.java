/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.security.cert.X509Certificate
 */
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

class ajw
implements X509TrustManager {
    final /* synthetic */ ajv.a a;

    ajw(ajv.a a2) {
        this.a = a2;
    }

    @Override
    public void checkClientTrusted(X509Certificate[] arrx509Certificate, String string2) {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] arrx509Certificate, String string2) {
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}


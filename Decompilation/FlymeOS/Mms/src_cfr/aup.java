/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.Socket
 *  java.security.KeyStore
 *  java.security.SecureRandom
 *  javax.net.ssl.SSLContext
 *  javax.net.ssl.SSLSocketFactory
 *  org.apache.http.conn.ssl.SSLSocketFactory
 *  org.apache.http.conn.ssl.X509HostnameVerifier
 */
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.X509HostnameVerifier;

public class aup
extends org.apache.http.conn.ssl.SSLSocketFactory {
    SSLContext a = SSLContext.getInstance((String)"TLS");

    public aup(KeyStore object) {
        super((KeyStore)object);
        object = new auq(this);
        this.a.init(null, new TrustManager[]{object}, null);
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static KeyStore a() {
        KeyStore keyStore;
        try {
            keyStore = KeyStore.getInstance((String)KeyStore.getDefaultType());
        }
        catch (Throwable var1_1) {
            void var1_2;
            keyStore = null;
            var1_2.printStackTrace();
            return keyStore;
        }
        keyStore.load(null, null);
        return keyStore;
        {
            catch (Throwable throwable) {}
        }
    }

    public static org.apache.http.conn.ssl.SSLSocketFactory b() {
        try {
            aup aup2 = new aup(aup.a());
            aup2.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            return aup2;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory();
        }
    }

    public Socket createSocket() {
        return this.a.getSocketFactory().createSocket();
    }

    public Socket createSocket(Socket socket, String string2, int n2, boolean bl2) {
        return this.a.getSocketFactory().createSocket(socket, string2, n2, bl2);
    }
}


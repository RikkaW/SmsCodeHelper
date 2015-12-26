/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.net.Socket
 *  java.security.KeyStore
 *  java.security.SecureRandom
 *  javax.net.ssl.SSLContext
 *  javax.net.ssl.SSLSocketFactory
 *  org.apache.http.conn.ssl.SSLSocketFactory
 */
import java.net.Socket;
import java.security.KeyStore;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class amc
extends SSLSocketFactory {
    private SSLContext a = SSLContext.getInstance((String)"TLS");

    public amc(KeyStore object) {
        super((KeyStore)object);
        object = new amd(this);
        this.a.init(null, new TrustManager[]{object}, null);
    }

    public Socket createSocket() {
        return this.a.getSocketFactory().createSocket();
    }

    public Socket createSocket(Socket socket, String string2, int n2, boolean bl2) {
        return this.a.getSocketFactory().createSocket(socket, string2, n2, bl2);
    }
}


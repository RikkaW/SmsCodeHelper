/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.net.Socket
 *  java.net.URI
 *  java.security.KeyStore
 *  java.security.SecureRandom
 *  javax.net.ssl.SSLContext
 *  javax.net.ssl.SSLSocketFactory
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpVersion
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.StatusLine
 *  org.apache.http.client.ClientProtocolException
 *  org.apache.http.client.methods.HttpPost
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.scheme.Scheme
 *  org.apache.http.conn.scheme.SchemeRegistry
 *  org.apache.http.conn.scheme.SocketFactory
 *  org.apache.http.conn.ssl.SSLSocketFactory
 *  org.apache.http.conn.ssl.X509HostnameVerifier
 *  org.apache.http.impl.client.DefaultHttpClient
 *  org.apache.http.params.BasicHttpParams
 *  org.apache.http.params.HttpParams
 *  org.apache.http.params.HttpProtocolParams
 *  org.apache.http.util.EntityUtils
 */
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class ajv {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(String string2, Map<String, String> defaultHttpClient, Scheme scheme, HttpParams httpParams, HttpEntity httpEntity) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return null;
        }
        HttpPost httpPost = new HttpPost();
        httpPost.addHeader("Accept-Charset", "UTF-8");
        if (defaultHttpClient != null) {
            for (String string3 : defaultHttpClient.keySet()) {
                httpPost.addHeader(string3, (String)defaultHttpClient.get(string3));
            }
        }
        if (httpParams != null) {
            httpPost.setParams(httpParams);
        }
        httpPost.setURI(URI.create((String)string2));
        defaultHttpClient = new DefaultHttpClient();
        defaultHttpClient.getConnectionManager().getSchemeRegistry().register(scheme);
        httpPost.setEntity(httpEntity);
        if ((defaultHttpClient = defaultHttpClient.execute((HttpUriRequest)httpPost)) == null) return null;
        try {
            if (200 == defaultHttpClient.getStatusLine().getStatusCode()) {
                return EntityUtils.toString((HttpEntity)defaultHttpClient.getEntity(), (String)"UTF-8");
            }
            Log.e((String)"HttpHelper", (String)("executeHttpPost, rescode=" + defaultHttpClient.getStatusLine().getStatusCode() + ", url=" + string2));
            return null;
        }
        catch (ClientProtocolException var0_1) {
            var0_1.printStackTrace();
            return null;
        }
        catch (IOException var0_2) {
            var0_2.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String a(String string2, HttpEntity httpEntity) {
        Object object;
        try {
            object = KeyStore.getInstance((String)KeyStore.getDefaultType());
            object.load(null, null);
            object = new a((KeyStore)object);
        }
        catch (KeyStoreException var2_3) {
            var2_3.printStackTrace();
            object = null;
        }
        catch (NoSuchAlgorithmException var2_4) {
            var2_4.printStackTrace();
            object = null;
        }
        catch (CertificateException var2_5) {
            var2_5.printStackTrace();
            object = null;
        }
        catch (IOException var2_6) {
            var2_6.printStackTrace();
            object = null;
        }
        catch (KeyManagementException var2_7) {
            var2_7.printStackTrace();
            object = null;
        }
        catch (UnrecoverableKeyException var2_8) {
            var2_8.printStackTrace();
            object = null;
        }
        object.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        object = new Scheme("https", (SocketFactory)object, 443);
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion((HttpParams)basicHttpParams, (ProtocolVersion)HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset((HttpParams)basicHttpParams, (String)"UTF-8");
        return ajv.a(string2, null, (Scheme)object, (HttpParams)basicHttpParams, httpEntity);
    }

    static class a
    extends org.apache.http.conn.ssl.SSLSocketFactory {
        SSLContext a = SSLContext.getInstance((String)"TLS");

        public a(KeyStore object) {
            super((KeyStore)object);
            object = new ajw(this);
            this.a.init(null, new TrustManager[]{object}, null);
        }

        public Socket createSocket() {
            return this.a.getSocketFactory().createSocket();
        }

        public Socket createSocket(Socket socket, String string2, int n2, boolean bl2) {
            return this.a.getSocketFactory().createSocket(socket, string2, n2, bl2);
        }
    }

}


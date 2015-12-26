/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.SSLCertificateSocketFactory
 *  android.net.SSLSessionCache
 *  android.util.Log
 *  java.io.ByteArrayOutputStream
 *  java.io.OutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.URI
 *  java.security.KeyStore
 *  org.apache.http.Header
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpHost
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpRequestInterceptor
 *  org.apache.http.HttpResponse
 *  org.apache.http.client.HttpClient
 *  org.apache.http.client.ResponseHandler
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.client.params.HttpClientParams
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.scheme.PlainSocketFactory
 *  org.apache.http.conn.scheme.Scheme
 *  org.apache.http.conn.scheme.SchemeRegistry
 *  org.apache.http.conn.scheme.SocketFactory
 *  org.apache.http.conn.ssl.SSLSocketFactory
 *  org.apache.http.conn.ssl.X509HostnameVerifier
 *  org.apache.http.impl.client.RequestWrapper
 *  org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager
 *  org.apache.http.params.BasicHttpParams
 *  org.apache.http.params.HttpConnectionParams
 *  org.apache.http.params.HttpParams
 *  org.apache.http.params.HttpProtocolParams
 *  org.apache.http.protocol.HttpContext
 */
import android.content.Context;
import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.security.KeyStore;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

public final class alz
implements HttpClient {
    public static long a = 256;
    private static final HttpRequestInterceptor b = new ama();
    private final HttpClient c;
    private RuntimeException d = new IllegalStateException("MAndroidHttpClient created and never closed");
    private volatile b e;

    private alz(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.c = new amb(this, clientConnectionManager, httpParams);
    }

    public static alz a(String string2, int n2) {
        return alz.a(string2, null, n2, false, false);
    }

    public static alz a(String string2, int n2, boolean bl2) {
        return alz.a(string2, null, n2, bl2, true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static alz a(String object, Context context, int n2, boolean bl2, boolean bl3) {
        BasicHttpParams basicHttpParams;
        SchemeRegistry schemeRegistry;
        Object var5_6;
        block4 : {
            var5_6 = null;
            basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setStaleCheckingEnabled((HttpParams)basicHttpParams, (boolean)false);
            HttpConnectionParams.setConnectionTimeout((HttpParams)basicHttpParams, (int)n2);
            HttpConnectionParams.setSoTimeout((HttpParams)basicHttpParams, (int)n2);
            HttpConnectionParams.setSocketBufferSize((HttpParams)basicHttpParams, (int)8192);
            HttpClientParams.setRedirecting((HttpParams)basicHttpParams, (boolean)bl3);
            HttpProtocolParams.setUserAgent((HttpParams)basicHttpParams, (String)object);
            schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", (SocketFactory)PlainSocketFactory.getSocketFactory(), 80));
            if (bl2) {
                try {
                    object = KeyStore.getInstance((String)KeyStore.getDefaultType());
                    object.load(null, null);
                    object = new amc((KeyStore)object);
                    object.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                    break block4;
                }
                catch (Exception var0_1) {
                    var0_1.printStackTrace();
                }
            }
            object = null;
        }
        if (object == null) {
            object = context == null ? var5_6 : new SSLSessionCache(context);
            object = SSLCertificateSocketFactory.getHttpSocketFactory((int)30000, (SSLSessionCache)object);
        }
        schemeRegistry.register(new Scheme("https", (SocketFactory)object, 443));
        return new alz((ClientConnectionManager)new ThreadSafeClientConnManager((HttpParams)basicHttpParams, schemeRegistry), (HttpParams)basicHttpParams);
    }

    public static alz b(String string2, int n2) {
        return alz.a(string2, null, n2, false, true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String b(HttpUriRequest object, boolean bl2) {
        Header header2;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("curl ");
        for (Header header2 : object.getAllHeaders()) {
            if (!bl2 && (header2.getName().equals((Object)"Authorization") || header2.getName().equals((Object)"Cookie"))) continue;
            stringBuilder.append("--header \"");
            stringBuilder.append(header2.toString().trim());
            stringBuilder.append("\" ");
        }
        URI uRI = object.getURI();
        if (object instanceof RequestWrapper && (header2 = ((RequestWrapper)object).getOriginal()) instanceof HttpUriRequest) {
            uRI = ((HttpUriRequest)header2).getURI();
        }
        stringBuilder.append("\"");
        stringBuilder.append((Object)uRI);
        stringBuilder.append("\"");
        if (!(object instanceof HttpEntityEnclosingRequest)) return stringBuilder.toString();
        if ((object = ((HttpEntityEnclosingRequest)object).getEntity()) == null) return stringBuilder.toString();
        if (!object.isRepeatable()) return stringBuilder.toString();
        if (object.getContentLength() < 1024) {
            uRI = new ByteArrayOutputStream();
            object.writeTo((OutputStream)uRI);
            object = uRI.toString();
            stringBuilder.append(" --data-ascii \"").append((String)object).append("\"");
            return stringBuilder.toString();
        }
        stringBuilder.append(" [TOO MUCH DATA TO INCLUDE]");
        return stringBuilder.toString();
    }

    static /* synthetic */ HttpRequestInterceptor b() {
        return b;
    }

    public void a() {
        if (this.d != null) {
            this.getConnectionManager().shutdown();
            this.d = null;
        }
    }

    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) {
        return (T)this.c.execute(httpHost, httpRequest, responseHandler);
    }

    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        return (T)this.c.execute(httpHost, httpRequest, responseHandler, httpContext);
    }

    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) {
        return (T)this.c.execute(httpUriRequest, responseHandler);
    }

    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        return (T)this.c.execute(httpUriRequest, responseHandler, httpContext);
    }

    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) {
        return this.c.execute(httpHost, httpRequest);
    }

    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        return this.c.execute(httpHost, httpRequest, httpContext);
    }

    public HttpResponse execute(HttpUriRequest httpUriRequest) {
        return this.c.execute(httpUriRequest);
    }

    public HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) {
        return this.c.execute(httpUriRequest, httpContext);
    }

    protected void finalize() {
        super.finalize();
        if (this.d != null) {
            Log.e((String)"MAndroidHttpClient", (String)"Leak found", (Throwable)this.d);
            this.d = null;
        }
    }

    public ClientConnectionManager getConnectionManager() {
        return this.c.getConnectionManager();
    }

    public HttpParams getParams() {
        return this.c.getParams();
    }

    class a
    implements HttpRequestInterceptor {
        private a() {
        }

        /* synthetic */ a(ama ama2) {
            this();
        }

        public void process(HttpRequest httpRequest, HttpContext object) {
            object = alz.this.e;
            if (object != null && ((b)object).a() && httpRequest instanceof HttpUriRequest) {
                ((b)object).a(alz.b((HttpUriRequest)httpRequest, false));
            }
        }
    }

    static class b {
        private final String a;
        private final int b;

        private void a(String string2) {
            Log.println((int)this.b, (String)this.a, (String)string2);
        }

        private boolean a() {
            return Log.isLoggable((String)this.a, (int)this.b);
        }
    }

}


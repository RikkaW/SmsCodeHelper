/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Log
 *  java.io.PushbackInputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Field
 *  java.net.Socket
 *  java.net.URI
 *  java.security.KeyStore
 *  java.security.SecureRandom
 *  java.util.Collections
 *  java.util.HashMap
 *  java.util.LinkedList
 *  java.util.WeakHashMap
 *  java.util.concurrent.Executors
 *  java.util.zip.GZIPInputStream
 *  javax.net.ssl.SSLContext
 *  javax.net.ssl.SSLSocketFactory
 *  org.apache.http.Header
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpRequestInterceptor
 *  org.apache.http.HttpResponseInterceptor
 *  org.apache.http.HttpVersion
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.client.HttpRequestRetryHandler
 *  org.apache.http.client.methods.HttpEntityEnclosingRequestBase
 *  org.apache.http.client.methods.HttpPost
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.params.ConnManagerParams
 *  org.apache.http.conn.params.ConnPerRoute
 *  org.apache.http.conn.params.ConnPerRouteBean
 *  org.apache.http.conn.scheme.PlainSocketFactory
 *  org.apache.http.conn.scheme.Scheme
 *  org.apache.http.conn.scheme.SchemeRegistry
 *  org.apache.http.conn.scheme.SocketFactory
 *  org.apache.http.conn.ssl.SSLSocketFactory
 *  org.apache.http.conn.ssl.X509HostnameVerifier
 *  org.apache.http.entity.HttpEntityWrapper
 *  org.apache.http.impl.client.AbstractHttpClient
 *  org.apache.http.impl.client.DefaultHttpClient
 *  org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager
 *  org.apache.http.params.BasicHttpParams
 *  org.apache.http.params.HttpConnectionParams
 *  org.apache.http.params.HttpParams
 *  org.apache.http.params.HttpProtocolParams
 *  org.apache.http.protocol.BasicHttpContext
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.protocol.SyncBasicHttpContext
 */
import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.net.Socket;
import java.net.URI;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

public class ab {
    private int a = 10;
    private int b = 10000;
    private int c = 10000;
    private final DefaultHttpClient d;
    private final HttpContext e;
    private ExecutorService f;
    private final Map<Context, List<aus>> g;
    private final Map<String, String> h;
    private boolean i = true;

    public ab() {
        this(false, 80, 443);
    }

    public ab(SchemeRegistry schemeRegistry) {
        this.a(schemeRegistry);
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout((HttpParams)basicHttpParams, (long)this.b);
        ConnManagerParams.setMaxConnectionsPerRoute((HttpParams)basicHttpParams, (ConnPerRoute)new ConnPerRouteBean(this.a));
        ConnManagerParams.setMaxTotalConnections((HttpParams)basicHttpParams, (int)10);
        HttpConnectionParams.setSoTimeout((HttpParams)basicHttpParams, (int)this.c);
        HttpConnectionParams.setConnectionTimeout((HttpParams)basicHttpParams, (int)this.b);
        HttpConnectionParams.setTcpNoDelay((HttpParams)basicHttpParams, (boolean)true);
        HttpConnectionParams.setSocketBufferSize((HttpParams)basicHttpParams, (int)8192);
        HttpProtocolParams.setVersion((HttpParams)basicHttpParams, (ProtocolVersion)HttpVersion.HTTP_1_1);
        schemeRegistry = new ThreadSafeClientConnManager((HttpParams)basicHttpParams, schemeRegistry);
        this.f = this.a();
        this.g = Collections.synchronizedMap((Map)new WeakHashMap());
        this.h = new HashMap();
        this.e = new SyncBasicHttpContext((HttpContext)new BasicHttpContext());
        this.d = new DefaultHttpClient((ClientConnectionManager)schemeRegistry, (HttpParams)basicHttpParams);
        this.d.addRequestInterceptor((HttpRequestInterceptor)new bc(this));
        this.d.addResponseInterceptor((HttpResponseInterceptor)new aob(this));
        this.d.addRequestInterceptor((HttpRequestInterceptor)new apc(this), 0);
        this.d.setHttpRequestRetryHandler((HttpRequestRetryHandler)new auu(5, 1500));
    }

    public ab(boolean bl2, int n2, int n3) {
        this(ab.a(bl2, n2, n3));
    }

    static /* synthetic */ Map a(ab ab2) {
        return ab2.h;
    }

    private HttpEntityEnclosingRequestBase a(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, HttpEntity httpEntity) {
        if (httpEntity != null) {
            httpEntityEnclosingRequestBase.setEntity(httpEntity);
        }
        return httpEntityEnclosingRequestBase;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static SchemeRegistry a(boolean bl2, int n2, int n3) {
        if (bl2) {
            Log.d((String)"AsyncHttpClient", (String)"Beware! Using the fix is insecure, as it doesn't verify SSL certificates.");
        }
        int n4 = n2;
        if (n2 < 1) {
            n4 = 80;
            Log.d((String)"AsyncHttpClient", (String)"Invalid HTTP port number specified, defaulting to 80");
        }
        n2 = n3;
        if (n3 < 1) {
            n2 = 443;
            Log.d((String)"AsyncHttpClient", (String)"Invalid HTTPS port number specified, defaulting to 443");
        }
        SSLSocketFactory sSLSocketFactory = bl2 ? aup.b() : SSLSocketFactory.getSocketFactory();
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", (SocketFactory)PlainSocketFactory.getSocketFactory(), n4));
        schemeRegistry.register(new Scheme("https", (SocketFactory)sSLSocketFactory, n2));
        return schemeRegistry;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void a(InputStream inputStream) {
        if (inputStream == null) return;
        try {
            inputStream.close();
            return;
        }
        catch (IOException var0_1) {
            Log.w((String)"AsyncHttpClient", (String)"Cannot close input stream", (Throwable)var0_1);
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static void a(HttpEntity var0) {
        block6 : {
            if (!(var0 instanceof HttpEntityWrapper)) ** GOTO lbl27
            var5_2 = HttpEntityWrapper.class.getDeclaredFields();
            var2_3 = var5_2.length;
            var1_4 = 0;
            ** GOTO lbl24
lbl7: // 2 sources:
            while (var4_5 != null) {
                var4_5.setAccessible(true);
                var0 = (HttpEntity)var4_5.get((Object)var0);
                if (var0 != null) {
                    var0.consumeContent();
                    return;
                }
                break block6;
            }
            break block6;
lbl-1000: // 1 sources:
            {
                var4_5 = var5_2[var1_4];
                try {
                    var3_6 = var4_5.getName().equals((Object)"wrappedEntity");
                    if (var3_6) ** GOTO lbl7
                    ++var1_4;
                    continue;
                }
                catch (Throwable var0_1) {
                    Log.e((String)"AsyncHttpClient", (String)"wrappedEntity consume", (Throwable)var0_1);
                    return;
                }
lbl24: // 2 sources:
                ** while (var1_4 < var2_3)
            }
lbl25: // 1 sources:
            var4_5 = null;
            ** GOTO lbl7
        }
    }

    private void a(SchemeRegistry schemeRegistry) {
        try {
            Object object = KeyStore.getInstance((String)KeyStore.getDefaultType());
            object.load(null, null);
            object = new b((KeyStore)object);
            object.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            schemeRegistry.register(new Scheme("https", (SocketFactory)object, 443));
            return;
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static boolean a(PushbackInputStream pushbackInputStream) {
        if (pushbackInputStream == null) {
            return false;
        }
        byte[] arrby = new byte[2];
        int n2 = pushbackInputStream.read(arrby);
        pushbackInputStream.unread(arrby);
        byte by2 = arrby[0];
        byte by3 = arrby[1];
        if (n2 != 2) return false;
        if (35615 != (by3 << 8 & 65280 | by2 & 255)) return false;
        return true;
    }

    protected asi a(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String string2, aut aut2, Context context) {
        return new asi((AbstractHttpClient)defaultHttpClient, httpContext, httpUriRequest, aut2);
    }

    public aus a(Context context, String string2, HttpEntity httpEntity, String string3, aut aut2) {
        return this.b(this.d, this.e, (HttpUriRequest)this.a((HttpEntityEnclosingRequestBase)new HttpPost(URI.create((String)string2).normalize()), httpEntity), string3, aut2, context);
    }

    protected ExecutorService a() {
        return Executors.newCachedThreadPool();
    }

    public void a(String string2, String string3) {
        this.h.put(string2, string3);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected aus b(DefaultHttpClient object, HttpContext list, HttpUriRequest object2, String map, aut aut2, Context context) {
        if (object2 == null) {
            throw new IllegalArgumentException("HttpUriRequest must not be null");
        }
        if (aut2 == null) {
            throw new IllegalArgumentException("ResponseHandler must not be null");
        }
        if (aut2.d()) {
            throw new IllegalArgumentException("Synchronous ResponseHandler used in AsyncHttpClient. You should create your response handler in a looper thread or use SyncHttpClient instead.");
        }
        if (map != null) {
            if (object2 instanceof HttpEntityEnclosingRequestBase && ((HttpEntityEnclosingRequestBase)object2).getEntity() != null) {
                Log.w((String)"AsyncHttpClient", (String)"Passed contentType will be ignored because HttpEntity sets content type");
            } else {
                object2.setHeader("Content-Type", (String)((Object)map));
            }
        }
        aut2.a(object2.getAllHeaders());
        aut2.a(object2.getURI());
        object = this.a((DefaultHttpClient)object, (HttpContext)list, (HttpUriRequest)object2, (String)((Object)map), aut2, context);
        this.f.submit((Runnable)object);
        object2 = new aus((asi)object);
        if (context != null) {
            list = this.g.get((Object)context);
            map = this.g;
            synchronized (map) {
                object = list;
                if (list == null) {
                    object = Collections.synchronizedList((List)new LinkedList());
                    this.g.put(context, (List)object);
                }
            }
            object.add(object2);
            object = object.iterator();
            while (object.hasNext()) {
                if (!((aus)object.next()).c()) continue;
                object.remove();
            }
        }
        return object2;
    }

    static class a
    extends HttpEntityWrapper {
        InputStream a;
        PushbackInputStream b;
        GZIPInputStream c;

        public a(HttpEntity httpEntity) {
            super(httpEntity);
        }

        public void consumeContent() {
            ab.a(this.a);
            ab.a((InputStream)this.b);
            ab.a((InputStream)this.c);
            super.consumeContent();
        }

        public InputStream getContent() {
            this.a = this.wrappedEntity.getContent();
            this.b = new PushbackInputStream(this.a, 2);
            if (ab.a(this.b)) {
                this.c = new GZIPInputStream((InputStream)this.b);
                return this.c;
            }
            return this.b;
        }

        public long getContentLength() {
            if (this.wrappedEntity == null) {
                return 0;
            }
            return this.wrappedEntity.getContentLength();
        }
    }

    static class b
    extends SSLSocketFactory {
        SSLContext a = SSLContext.getInstance((String)"TLS");

        public b(KeyStore object) {
            super((KeyStore)object);
            object = new aqd(this);
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


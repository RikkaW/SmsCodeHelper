import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.net.Socket;
import java.net.URI;
import java.security.KeyStore;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

public class ab
{
  private int a = 10;
  private int b = 10000;
  private int c = 10000;
  private final DefaultHttpClient d;
  private final HttpContext e;
  private ExecutorService f;
  private final Map<Context, List<aus>> g;
  private final Map<String, String> h;
  private boolean i = true;
  
  public ab()
  {
    this(false, 80, 443);
  }
  
  public ab(SchemeRegistry paramSchemeRegistry)
  {
    a(paramSchemeRegistry);
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    ConnManagerParams.setTimeout(localBasicHttpParams, b);
    ConnManagerParams.setMaxConnectionsPerRoute(localBasicHttpParams, new ConnPerRouteBean(a));
    ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, 10);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, c);
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, b);
    HttpConnectionParams.setTcpNoDelay(localBasicHttpParams, true);
    HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 8192);
    HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
    paramSchemeRegistry = new ThreadSafeClientConnManager(localBasicHttpParams, paramSchemeRegistry);
    f = a();
    g = Collections.synchronizedMap(new WeakHashMap());
    h = new HashMap();
    e = new SyncBasicHttpContext(new BasicHttpContext());
    d = new DefaultHttpClient(paramSchemeRegistry, localBasicHttpParams);
    d.addRequestInterceptor(new bc(this));
    d.addResponseInterceptor(new aob(this));
    d.addRequestInterceptor(new apc(this), 0);
    d.setHttpRequestRetryHandler(new auu(5, 1500));
  }
  
  public ab(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    this(a(paramBoolean, paramInt1, paramInt2));
  }
  
  private HttpEntityEnclosingRequestBase a(HttpEntityEnclosingRequestBase paramHttpEntityEnclosingRequestBase, HttpEntity paramHttpEntity)
  {
    if (paramHttpEntity != null) {
      paramHttpEntityEnclosingRequestBase.setEntity(paramHttpEntity);
    }
    return paramHttpEntityEnclosingRequestBase;
  }
  
  private static SchemeRegistry a(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    if (paramBoolean) {
      Log.d("AsyncHttpClient", "Beware! Using the fix is insecure, as it doesn't verify SSL certificates.");
    }
    int j = paramInt1;
    if (paramInt1 < 1)
    {
      j = 80;
      Log.d("AsyncHttpClient", "Invalid HTTP port number specified, defaulting to 80");
    }
    paramInt1 = paramInt2;
    if (paramInt2 < 1)
    {
      paramInt1 = 443;
      Log.d("AsyncHttpClient", "Invalid HTTPS port number specified, defaulting to 443");
    }
    if (paramBoolean) {}
    for (org.apache.http.conn.ssl.SSLSocketFactory localSSLSocketFactory = aup.b();; localSSLSocketFactory = org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory())
    {
      SchemeRegistry localSchemeRegistry = new SchemeRegistry();
      localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), j));
      localSchemeRegistry.register(new Scheme("https", localSSLSocketFactory, paramInt1));
      return localSchemeRegistry;
    }
  }
  
  public static void a(InputStream paramInputStream)
  {
    if (paramInputStream != null) {}
    try
    {
      paramInputStream.close();
      return;
    }
    catch (IOException paramInputStream)
    {
      Log.w("AsyncHttpClient", "Cannot close input stream", paramInputStream);
    }
  }
  
  public static void a(HttpEntity paramHttpEntity)
  {
    if ((paramHttpEntity instanceof HttpEntityWrapper)) {
      for (;;)
      {
        int k;
        int j;
        try
        {
          arrayOfField = HttpEntityWrapper.class.getDeclaredFields();
          k = arrayOfField.length;
          j = 0;
        }
        catch (Throwable paramHttpEntity)
        {
          Field[] arrayOfField;
          boolean bool;
          Log.e("AsyncHttpClient", "wrappedEntity consume", paramHttpEntity);
          return;
        }
        if (localField == null) {
          break;
        }
        localField.setAccessible(true);
        paramHttpEntity = (HttpEntity)localField.get(paramHttpEntity);
        if (paramHttpEntity == null) {
          break;
        }
        paramHttpEntity.consumeContent();
        return;
        Field localField = arrayOfField[j];
        bool = localField.getName().equals("wrappedEntity");
        if (!bool)
        {
          j += 1;
          if (j >= k) {
            localField = null;
          }
        }
      }
    }
  }
  
  private void a(SchemeRegistry paramSchemeRegistry)
  {
    try
    {
      Object localObject = KeyStore.getInstance(KeyStore.getDefaultType());
      ((KeyStore)localObject).load(null, null);
      localObject = new ab.b((KeyStore)localObject);
      ((org.apache.http.conn.ssl.SSLSocketFactory)localObject).setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      paramSchemeRegistry.register(new Scheme("https", (SocketFactory)localObject, 443));
      return;
    }
    catch (Exception paramSchemeRegistry)
    {
      paramSchemeRegistry.printStackTrace();
    }
  }
  
  public static boolean a(PushbackInputStream paramPushbackInputStream)
  {
    if (paramPushbackInputStream == null) {}
    int j;
    int k;
    int m;
    do
    {
      return false;
      byte[] arrayOfByte = new byte[2];
      j = paramPushbackInputStream.read(arrayOfByte);
      paramPushbackInputStream.unread(arrayOfByte);
      k = arrayOfByte[0];
      m = arrayOfByte[1];
    } while ((j != 2) || (35615 != (m << 8 & 0xFF00 | k & 0xFF)));
    return true;
  }
  
  protected asi a(DefaultHttpClient paramDefaultHttpClient, HttpContext paramHttpContext, HttpUriRequest paramHttpUriRequest, String paramString, aut paramaut, Context paramContext)
  {
    return new asi(paramDefaultHttpClient, paramHttpContext, paramHttpUriRequest, paramaut);
  }
  
  public aus a(Context paramContext, String paramString1, HttpEntity paramHttpEntity, String paramString2, aut paramaut)
  {
    return b(d, e, a(new HttpPost(URI.create(paramString1).normalize()), paramHttpEntity), paramString2, paramaut, paramContext);
  }
  
  protected ExecutorService a()
  {
    return Executors.newCachedThreadPool();
  }
  
  public void a(String paramString1, String paramString2)
  {
    h.put(paramString1, paramString2);
  }
  
  protected aus b(DefaultHttpClient paramDefaultHttpClient, HttpContext paramHttpContext, HttpUriRequest paramHttpUriRequest, String paramString, aut paramaut, Context paramContext)
  {
    if (paramHttpUriRequest == null) {
      throw new IllegalArgumentException("HttpUriRequest must not be null");
    }
    if (paramaut == null) {
      throw new IllegalArgumentException("ResponseHandler must not be null");
    }
    if (paramaut.d()) {
      throw new IllegalArgumentException("Synchronous ResponseHandler used in AsyncHttpClient. You should create your response handler in a looper thread or use SyncHttpClient instead.");
    }
    if (paramString != null)
    {
      if (((paramHttpUriRequest instanceof HttpEntityEnclosingRequestBase)) && (((HttpEntityEnclosingRequestBase)paramHttpUriRequest).getEntity() != null)) {
        Log.w("AsyncHttpClient", "Passed contentType will be ignored because HttpEntity sets content type");
      }
    }
    else
    {
      paramaut.a(paramHttpUriRequest.getAllHeaders());
      paramaut.a(paramHttpUriRequest.getURI());
      paramDefaultHttpClient = a(paramDefaultHttpClient, paramHttpContext, paramHttpUriRequest, paramString, paramaut, paramContext);
      f.submit(paramDefaultHttpClient);
      paramHttpUriRequest = new aus(paramDefaultHttpClient);
      if (paramContext != null)
      {
        paramHttpContext = (List)g.get(paramContext);
        paramString = g;
        paramDefaultHttpClient = paramHttpContext;
        if (paramHttpContext != null) {}
      }
    }
    for (;;)
    {
      try
      {
        paramDefaultHttpClient = Collections.synchronizedList(new LinkedList());
        g.put(paramContext, paramDefaultHttpClient);
        paramDefaultHttpClient.add(paramHttpUriRequest);
        paramDefaultHttpClient = paramDefaultHttpClient.iterator();
        if (paramDefaultHttpClient.hasNext()) {
          break label251;
        }
        return paramHttpUriRequest;
      }
      finally {}
      paramHttpUriRequest.setHeader("Content-Type", paramString);
      break;
      label251:
      if (((aus)paramDefaultHttpClient.next()).c()) {
        paramDefaultHttpClient.remove();
      }
    }
  }
  
  static class a
    extends HttpEntityWrapper
  {
    InputStream a;
    PushbackInputStream b;
    GZIPInputStream c;
    
    public a(HttpEntity paramHttpEntity)
    {
      super();
    }
    
    public void consumeContent()
    {
      ab.a(a);
      ab.a(b);
      ab.a(c);
      super.consumeContent();
    }
    
    public InputStream getContent()
    {
      a = wrappedEntity.getContent();
      b = new PushbackInputStream(a, 2);
      if (ab.a(b))
      {
        c = new GZIPInputStream(b);
        return c;
      }
      return b;
    }
    
    public long getContentLength()
    {
      if (wrappedEntity == null) {
        return 0L;
      }
      return wrappedEntity.getContentLength();
    }
  }
  
  static class b
    extends org.apache.http.conn.ssl.SSLSocketFactory
  {
    SSLContext a = SSLContext.getInstance("TLS");
    
    public b(KeyStore paramKeyStore)
    {
      super();
      paramKeyStore = new aqd(this);
      a.init(null, new TrustManager[] { paramKeyStore }, null);
    }
    
    public Socket createSocket()
    {
      return a.getSocketFactory().createSocket();
    }
    
    public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    {
      return a.getSocketFactory().createSocket(paramSocket, paramString, paramInt, paramBoolean);
    }
  }
}

/* Location:
 * Qualified Name:     ab
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
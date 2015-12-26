import android.content.Context;
import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
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
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

public final class alz
  implements HttpClient
{
  public static long a = 256L;
  private static final HttpRequestInterceptor b = new ama();
  private final HttpClient c;
  private RuntimeException d = new IllegalStateException("MAndroidHttpClient created and never closed");
  private volatile alz.b e;
  
  private alz(ClientConnectionManager paramClientConnectionManager, HttpParams paramHttpParams)
  {
    c = new amb(this, paramClientConnectionManager, paramHttpParams);
  }
  
  public static alz a(String paramString, int paramInt)
  {
    return a(paramString, null, paramInt, false, false);
  }
  
  public static alz a(String paramString, int paramInt, boolean paramBoolean)
  {
    return a(paramString, null, paramInt, paramBoolean, true);
  }
  
  public static alz a(String paramString, Context paramContext, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject = null;
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setStaleCheckingEnabled(localBasicHttpParams, false);
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, paramInt);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, paramInt);
    HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 8192);
    HttpClientParams.setRedirecting(localBasicHttpParams, paramBoolean2);
    HttpProtocolParams.setUserAgent(localBasicHttpParams, paramString);
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
    if (paramBoolean1) {}
    label182:
    label194:
    for (;;)
    {
      try
      {
        paramString = KeyStore.getInstance(KeyStore.getDefaultType());
        paramString.load(null, null);
        paramString = new amc(paramString);
        paramString.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        if (paramString != null) {
          break label194;
        }
        if (paramContext != null) {
          break label182;
        }
        paramString = (String)localObject;
        paramString = SSLCertificateSocketFactory.getHttpSocketFactory(30000, paramString);
        localSchemeRegistry.register(new Scheme("https", paramString, 443));
        return new alz(new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
      paramString = null;
      continue;
      paramString = new SSLSessionCache(paramContext);
    }
  }
  
  public static alz b(String paramString, int paramInt)
  {
    return a(paramString, null, paramInt, false, true);
  }
  
  private static String b(HttpUriRequest paramHttpUriRequest, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("curl ");
    Object localObject = paramHttpUriRequest.getAllHeaders();
    int j = localObject.length;
    int i = 0;
    HttpRequest localHttpRequest;
    if (i < j)
    {
      localHttpRequest = localObject[i];
      if ((!paramBoolean) && ((localHttpRequest.getName().equals("Authorization")) || (localHttpRequest.getName().equals("Cookie")))) {}
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append("--header \"");
        localStringBuilder.append(localHttpRequest.toString().trim());
        localStringBuilder.append("\" ");
      }
    }
    localObject = paramHttpUriRequest.getURI();
    if ((paramHttpUriRequest instanceof RequestWrapper))
    {
      localHttpRequest = ((RequestWrapper)paramHttpUriRequest).getOriginal();
      if ((localHttpRequest instanceof HttpUriRequest)) {
        localObject = ((HttpUriRequest)localHttpRequest).getURI();
      }
    }
    for (;;)
    {
      localStringBuilder.append("\"");
      localStringBuilder.append(localObject);
      localStringBuilder.append("\"");
      if ((paramHttpUriRequest instanceof HttpEntityEnclosingRequest))
      {
        paramHttpUriRequest = ((HttpEntityEnclosingRequest)paramHttpUriRequest).getEntity();
        if ((paramHttpUriRequest != null) && (paramHttpUriRequest.isRepeatable()))
        {
          if (paramHttpUriRequest.getContentLength() >= 1024L) {
            break label273;
          }
          localObject = new ByteArrayOutputStream();
          paramHttpUriRequest.writeTo((OutputStream)localObject);
          paramHttpUriRequest = ((ByteArrayOutputStream)localObject).toString();
          localStringBuilder.append(" --data-ascii \"").append(paramHttpUriRequest).append("\"");
        }
      }
      for (;;)
      {
        return localStringBuilder.toString();
        label273:
        localStringBuilder.append(" [TOO MUCH DATA TO INCLUDE]");
      }
    }
  }
  
  public void a()
  {
    if (d != null)
    {
      getConnectionManager().shutdown();
      d = null;
    }
  }
  
  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler)
  {
    return (T)c.execute(paramHttpHost, paramHttpRequest, paramResponseHandler);
  }
  
  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
  {
    return (T)c.execute(paramHttpHost, paramHttpRequest, paramResponseHandler, paramHttpContext);
  }
  
  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler)
  {
    return (T)c.execute(paramHttpUriRequest, paramResponseHandler);
  }
  
  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
  {
    return (T)c.execute(paramHttpUriRequest, paramResponseHandler, paramHttpContext);
  }
  
  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest)
  {
    return c.execute(paramHttpHost, paramHttpRequest);
  }
  
  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    return c.execute(paramHttpHost, paramHttpRequest, paramHttpContext);
  }
  
  public HttpResponse execute(HttpUriRequest paramHttpUriRequest)
  {
    return c.execute(paramHttpUriRequest);
  }
  
  public HttpResponse execute(HttpUriRequest paramHttpUriRequest, HttpContext paramHttpContext)
  {
    return c.execute(paramHttpUriRequest, paramHttpContext);
  }
  
  protected void finalize()
  {
    super.finalize();
    if (d != null)
    {
      Log.e("MAndroidHttpClient", "Leak found", d);
      d = null;
    }
  }
  
  public ClientConnectionManager getConnectionManager()
  {
    return c.getConnectionManager();
  }
  
  public HttpParams getParams()
  {
    return c.getParams();
  }
  
  class a
    implements HttpRequestInterceptor
  {
    private a() {}
    
    public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    {
      paramHttpContext = alz.a(alz.this);
      if ((paramHttpContext != null) && (alz.b.a(paramHttpContext)) && ((paramHttpRequest instanceof HttpUriRequest))) {
        alz.b.a(paramHttpContext, alz.a((HttpUriRequest)paramHttpRequest, false));
      }
    }
  }
  
  static class b
  {
    private final String a;
    private final int b;
    
    private void a(String paramString)
    {
      Log.println(b, a, paramString);
    }
    
    private boolean a()
    {
      return Log.isLoggable(a, b);
    }
  }
}

/* Location:
 * Qualified Name:     alz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
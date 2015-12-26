import android.util.Log;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.UnknownHostException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

public class asi
  implements Runnable
{
  private final AbstractHttpClient a;
  private final HttpContext b;
  private final HttpUriRequest c;
  private final aut d;
  private int e;
  private boolean f;
  private boolean g;
  private boolean h;
  private boolean i;
  
  public asi(AbstractHttpClient paramAbstractHttpClient, HttpContext paramHttpContext, HttpUriRequest paramHttpUriRequest, aut paramaut)
  {
    a = paramAbstractHttpClient;
    b = paramHttpContext;
    c = paramHttpUriRequest;
    d = paramaut;
  }
  
  private void c()
  {
    if (a()) {}
    HttpResponse localHttpResponse;
    do
    {
      do
      {
        do
        {
          return;
          if (c.getURI().getScheme() == null) {
            throw new MalformedURLException("No valid URI scheme was provided");
          }
          if ((d instanceof aur)) {
            ((aur)d).a(c);
          }
          localHttpResponse = a.execute(c, b);
        } while ((a()) || (d == null));
        d.a(d, localHttpResponse);
      } while (a());
      d.a(localHttpResponse);
    } while (a());
    d.b(d, localHttpResponse);
  }
  
  private void d()
  {
    Object localObject = null;
    HttpRequestRetryHandler localHttpRequestRetryHandler = a.getHttpRequestRetryHandler();
    int k = 1;
    if (k == 0) {
      label18:
      throw ((Throwable)localObject);
    }
    label292:
    for (;;)
    {
      try
      {
        c();
        return;
      }
      catch (UnknownHostException localUnknownHostException)
      {
        IOException localIOException1 = new IOException("UnknownHostException exception: " + localUnknownHostException.getMessage());
        if (e > 0)
        {
          j = e + 1;
          e = j;
          if (localHttpRequestRetryHandler.retryRequest(localIOException1, j, b))
          {
            bool = true;
            break label292;
            localObject = localIOException1;
            k = bool;
            if (!bool) {
              break;
            }
            localObject = localIOException1;
            k = bool;
            if (d == null) {
              break;
            }
            d.a(e);
            localObject = localIOException1;
            k = bool;
            break;
          }
        }
      }
      catch (Exception localException)
      {
        Log.e("AsyncHttpRequest", "Unhandled exception origin cause", localException);
        localObject = new IOException("Unhandled exception: " + localException.getMessage());
        break label18;
        bool = false;
      }
      catch (NullPointerException localNullPointerException)
      {
        IOException localIOException2 = new IOException("NPE in HttpClient: " + localNullPointerException.getMessage());
        j = e + 1;
        e = j;
        bool = localHttpRequestRetryHandler.retryRequest(localIOException2, j, b);
      }
      catch (IOException localIOException3)
      {
        int j;
        boolean bool;
        if (!a())
        {
          j = e + 1;
          e = j;
          bool = localHttpRequestRetryHandler.retryRequest(localIOException3, j, b);
        }
      }
    }
  }
  
  private void e()
  {
    try
    {
      if ((!h) && (f) && (!g))
      {
        g = true;
        if (d != null) {
          d.c();
        }
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void a(asi paramasi) {}
  
  public boolean a()
  {
    if (f) {
      e();
    }
    return f;
  }
  
  public void b(asi paramasi) {}
  
  public boolean b()
  {
    return (a()) || (h);
  }
  
  public void run()
  {
    if (a()) {}
    for (;;)
    {
      return;
      if (!i)
      {
        i = true;
        a(this);
      }
      if (a()) {
        continue;
      }
      if (d != null) {
        d.a();
      }
      if (a()) {
        continue;
      }
      try
      {
        d();
        if (a()) {
          continue;
        }
        if (d != null) {
          d.b();
        }
        if (a()) {
          continue;
        }
        b(this);
        h = true;
        return;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          if ((!a()) && (d != null)) {
            d.a(0, null, null, localIOException);
          } else {
            Log.e("AsyncHttpRequest", "makeRequestWithRetries returned error, but handler is null", localIOException);
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     asi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
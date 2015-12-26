import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

class alz$a
  implements HttpRequestInterceptor
{
  private alz$a(alz paramalz) {}
  
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    paramHttpContext = alz.a(a);
    if ((paramHttpContext != null) && (alz.b.a(paramHttpContext)) && ((paramHttpRequest instanceof HttpUriRequest))) {
      alz.b.a(paramHttpContext, alz.a((HttpUriRequest)paramHttpRequest, false));
    }
  }
}

/* Location:
 * Qualified Name:     alz.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
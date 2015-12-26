import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

class aob
  implements HttpResponseInterceptor
{
  aob(ab paramab) {}
  
  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    paramHttpContext = paramHttpResponse.getEntity();
    if (paramHttpContext == null) {}
    for (;;)
    {
      return;
      Object localObject = paramHttpContext.getContentEncoding();
      if (localObject != null)
      {
        localObject = ((Header)localObject).getElements();
        int j = localObject.length;
        int i = 0;
        while (i < j)
        {
          if (localObject[i].getName().equalsIgnoreCase("gzip"))
          {
            paramHttpResponse.setEntity(new ab.a(paramHttpContext));
            return;
          }
          i += 1;
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     aob
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
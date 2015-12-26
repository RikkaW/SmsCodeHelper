import android.util.Log;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

class bc
  implements HttpRequestInterceptor
{
  bc(ab paramab) {}
  
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    if (!paramHttpRequest.containsHeader("Accept-Encoding")) {
      paramHttpRequest.addHeader("Accept-Encoding", "gzip");
    }
    paramHttpContext = ab.a(a).keySet().iterator();
    for (;;)
    {
      if (!paramHttpContext.hasNext()) {
        return;
      }
      String str = (String)paramHttpContext.next();
      if (paramHttpRequest.containsHeader(str))
      {
        Header localHeader = paramHttpRequest.getFirstHeader(str);
        Log.d("AsyncHttpClient", String.format("Headers were overwritten! (%s | %s) overwrites (%s | %s)", new Object[] { str, ab.a(a).get(str), localHeader.getName(), localHeader.getValue() }));
        paramHttpRequest.removeHeader(localHeader);
      }
      paramHttpRequest.addHeader(str, (String)ab.a(a).get(str));
    }
  }
}

/* Location:
 * Qualified Name:     bc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
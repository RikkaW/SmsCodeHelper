import android.os.Looper;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

final class ama
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    if ((Looper.myLooper() != null) && (Looper.myLooper() == Looper.getMainLooper())) {
      throw new RuntimeException("This thread forbids HTTP requests");
    }
  }
}

/* Location:
 * Qualified Name:     ama
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
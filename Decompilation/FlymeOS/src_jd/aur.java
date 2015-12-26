import java.io.File;
import org.apache.http.client.methods.HttpUriRequest;

public abstract class aur
  extends auo
{
  private long c;
  private boolean d;
  
  public void a(HttpUriRequest paramHttpUriRequest)
  {
    if ((a.exists()) && (a.canWrite())) {
      c = a.length();
    }
    if (c > 0L)
    {
      d = true;
      paramHttpUriRequest.setHeader("Range", "bytes=" + c + "-");
    }
  }
}

/* Location:
 * Qualified Name:     aur
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
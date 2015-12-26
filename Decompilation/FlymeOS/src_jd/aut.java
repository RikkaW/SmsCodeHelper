import java.net.URI;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

public abstract interface aut
{
  public abstract void a();
  
  public abstract void a(int paramInt);
  
  public abstract void a(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte, Throwable paramThrowable);
  
  public abstract void a(aut paramaut, HttpResponse paramHttpResponse);
  
  public abstract void a(URI paramURI);
  
  public abstract void a(HttpResponse paramHttpResponse);
  
  public abstract void a(Header[] paramArrayOfHeader);
  
  public abstract void b();
  
  public abstract void b(aut paramaut, HttpResponse paramHttpResponse);
  
  public abstract void c();
  
  public abstract boolean d();
}

/* Location:
 * Qualified Name:     aut
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
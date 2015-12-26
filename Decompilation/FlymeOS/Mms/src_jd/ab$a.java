import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

class ab$a
  extends HttpEntityWrapper
{
  InputStream a;
  PushbackInputStream b;
  GZIPInputStream c;
  
  public ab$a(HttpEntity paramHttpEntity)
  {
    super(paramHttpEntity);
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

/* Location:
 * Qualified Name:     ab.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
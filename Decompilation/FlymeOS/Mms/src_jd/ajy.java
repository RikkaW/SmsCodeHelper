import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EncodingUtils;

public class ajy
  extends AbstractHttpEntity
{
  private static byte[] b = EncodingUtils.getAsciiBytes("-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
  protected ajz[] a;
  private byte[] c;
  private HttpParams d;
  private boolean e = false;
  
  public ajy(ajz[] paramArrayOfajz)
  {
    setContentType("multipart/form-data");
    if (paramArrayOfajz == null) {
      throw new IllegalArgumentException("parts cannot be null");
    }
    a = paramArrayOfajz;
    d = null;
  }
  
  private static byte[] b()
  {
    Random localRandom = new Random();
    byte[] arrayOfByte = new byte[localRandom.nextInt(11) + 30];
    int i = 0;
    while (i < arrayOfByte.length)
    {
      arrayOfByte[i] = b[localRandom.nextInt(b.length)];
      i += 1;
    }
    return arrayOfByte;
  }
  
  protected byte[] a()
  {
    String str;
    if (c == null)
    {
      str = null;
      if (d != null) {
        str = (String)d.getParameter("http.method.multipart.boundary");
      }
      if (str == null) {
        break label48;
      }
    }
    label48:
    for (c = EncodingUtils.getAsciiBytes(str);; c = b()) {
      return c;
    }
  }
  
  public InputStream getContent()
  {
    if ((!isRepeatable()) && (e)) {
      throw new IllegalStateException("Content has been consumed");
    }
    e = true;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    ajz.a(localByteArrayOutputStream, a, c);
    return new ByteArrayInputStream(localByteArrayOutputStream.toByteArray());
  }
  
  public long getContentLength()
  {
    try
    {
      long l = ajz.a(a, a());
      return l;
    }
    catch (Exception localException) {}
    return 0L;
  }
  
  public Header getContentType()
  {
    StringBuffer localStringBuffer = new StringBuffer("multipart/form-data");
    localStringBuffer.append("; boundary=");
    localStringBuffer.append(EncodingUtils.getAsciiString(a()));
    return new BasicHeader("Content-Type", localStringBuffer.toString());
  }
  
  public boolean isRepeatable()
  {
    int i = 0;
    while (i < a.length)
    {
      if (!a[i].g()) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public boolean isStreaming()
  {
    return false;
  }
  
  public void writeTo(OutputStream paramOutputStream)
  {
    ajz.a(paramOutputStream, a, a());
  }
}

/* Location:
 * Qualified Name:     ajy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
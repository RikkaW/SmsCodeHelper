import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import org.apache.http.util.EncodingUtils;

public class ajx
  extends aka
{
  private static final byte[] i = EncodingUtils.getAsciiBytes("; filename=");
  private byte[] j;
  private String k;
  
  public ajx(String paramString1, String paramString2, byte[] paramArrayOfByte, String paramString3, String paramString4)
  {
    super(paramString1, str, paramString3, "binary");
    if (paramArrayOfByte == null) {
      throw new IllegalArgumentException("Source may not be null");
    }
    k = paramString2;
    j = paramArrayOfByte;
  }
  
  protected long a()
  {
    return j.length;
  }
  
  protected void a(OutputStream paramOutputStream)
  {
    super.a(paramOutputStream);
    if (k != null)
    {
      paramOutputStream.write(i);
      paramOutputStream.write(c);
      paramOutputStream.write(EncodingUtils.getAsciiBytes(k));
      paramOutputStream.write(c);
    }
  }
  
  protected void b(OutputStream paramOutputStream)
  {
    if (a() == 0L) {
      return;
    }
    byte[] arrayOfByte = new byte['က'];
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(j);
    try
    {
      for (;;)
      {
        int m = localByteArrayInputStream.read(arrayOfByte);
        if (m < 0) {
          break;
        }
        paramOutputStream.write(arrayOfByte, 0, m);
      }
    }
    finally
    {
      localByteArrayInputStream.close();
    }
  }
}

/* Location:
 * Qualified Name:     ajx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
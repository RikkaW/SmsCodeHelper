import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

public class ajg
{
  private InputStream a;
  
  public ajg(InputStream paramInputStream)
  {
    a = paramInputStream;
  }
  
  public int a()
  {
    return a.read();
  }
  
  public byte b()
  {
    int i = a.read();
    if (i == -1) {
      throw new IOException("Unexpected stream EOF met");
    }
    return (byte)i;
  }
  
  public int c()
  {
    int i = 0;
    int k;
    int j;
    do
    {
      k = b();
      j = i << 7 | k & 0x7F;
      i = j;
    } while ((k & 0x80) != 0);
    return j;
  }
  
  public String d()
  {
    Object localObject = ByteBuffer.allocate(1024);
    try
    {
      byte b;
      do
      {
        b = b();
        ((ByteBuffer)localObject).put(b);
      } while (b != 0);
    }
    catch (IOException localIOException)
    {
      StringBuffer localStringBuffer;
      for (;;) {}
    }
    localObject = new InputStreamReader(new ByteArrayInputStream(((ByteBuffer)localObject).array()), "UTF-8");
    localStringBuffer = new StringBuffer();
    for (;;)
    {
      int i = ((InputStreamReader)localObject).read();
      if (i == -1) {
        throw new IOException("Unexpected stream EOF met");
      }
      if (i == 0) {
        return localStringBuffer.toString();
      }
      localStringBuffer.append((char)i);
    }
  }
  
  public String e()
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     ajg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
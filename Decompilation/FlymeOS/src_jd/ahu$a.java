import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class ahu$a
  extends FilterOutputStream
{
  private final ahl.a a;
  private boolean b = false;
  
  private ahu$a(OutputStream paramOutputStream, ahl.a parama)
  {
    super(paramOutputStream);
    a = parama;
  }
  
  public void close()
  {
    Object localObject = null;
    try
    {
      super.close();
      if (b) {
        a.b();
      }
      while (localObject != null)
      {
        throw ((Throwable)localObject);
        a.a();
      }
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  public void flush()
  {
    try
    {
      super.flush();
      return;
    }
    catch (IOException localIOException)
    {
      b = true;
      throw localIOException;
    }
  }
  
  public void write(int paramInt)
  {
    try
    {
      super.write(paramInt);
      return;
    }
    catch (IOException localIOException)
    {
      b = true;
      throw localIOException;
    }
  }
  
  public void write(byte[] paramArrayOfByte)
  {
    try
    {
      super.write(paramArrayOfByte);
      return;
    }
    catch (IOException paramArrayOfByte)
    {
      b = true;
      throw paramArrayOfByte;
    }
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      super.write(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    catch (IOException paramArrayOfByte)
    {
      b = true;
      throw paramArrayOfByte;
    }
  }
}

/* Location:
 * Qualified Name:     ahu.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
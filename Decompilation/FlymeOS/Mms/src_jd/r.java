import java.io.StringReader;
import java.nio.CharBuffer;

public class r
  extends StringReader
{
  private int a = 0;
  private int b = 0;
  private boolean c = false;
  
  public r(String paramString)
  {
    super(paramString);
  }
  
  public int a()
  {
    return a;
  }
  
  public p b()
  {
    for (c = false;; c = true)
    {
      mark(0);
      int i = read();
      if (i == -1) {
        return null;
      }
      if (" \r\n\t".indexOf((char)i) < 0) {
        break;
      }
    }
    reset();
    return v.a(this).a(this);
  }
  
  public void mark(int paramInt)
  {
    super.mark(paramInt);
    b = 0;
  }
  
  public int read()
  {
    int i = super.read();
    if (i != -1)
    {
      a += 1;
      b += 1;
    }
    return i;
  }
  
  public int read(CharBuffer paramCharBuffer)
  {
    int i = super.read(paramCharBuffer);
    if (i > 0)
    {
      a += i;
      b += i;
    }
    return i;
  }
  
  public int read(char[] paramArrayOfChar)
  {
    int i = super.read(paramArrayOfChar);
    if (i > 0)
    {
      a += i;
      b += i;
    }
    return i;
  }
  
  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    paramInt1 = super.read(paramArrayOfChar, paramInt1, paramInt2);
    if (paramInt1 > 0)
    {
      a += paramInt1;
      b += paramInt1;
    }
    return paramInt1;
  }
  
  public void reset()
  {
    super.reset();
    a -= b;
  }
}

/* Location:
 * Qualified Name:     r
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
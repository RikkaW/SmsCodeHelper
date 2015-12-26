import java.io.IOException;
import java.io.InputStream;

public class aiy
{
  public int a = 0;
  private InputStream b;
  
  public aiy(InputStream paramInputStream)
  {
    b = paramInputStream;
  }
  
  public int a()
  {
    int i = b.read();
    a += 1;
    if (i == -1) {
      throw new IOException("Unexpected stream EOF met");
    }
    return i;
  }
  
  public int b()
  {
    int i = 0;
    int k;
    int j;
    do
    {
      k = a();
      j = i << 7 | k & 0x7F;
      i = j;
    } while ((k & 0x80) != 0);
    return j;
  }
}

/* Location:
 * Qualified Name:     aiy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
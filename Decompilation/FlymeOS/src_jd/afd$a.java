import java.io.BufferedReader;
import java.io.Reader;

public final class afd$a
  extends BufferedReader
{
  private long a;
  private boolean b;
  private String c;
  
  public afd$a(Reader paramReader)
  {
    super(paramReader);
  }
  
  public String a()
  {
    if (!b)
    {
      long l = System.currentTimeMillis();
      String str = super.readLine();
      a = (System.currentTimeMillis() - l + a);
      c = str;
      b = true;
    }
    return c;
  }
  
  public String readLine()
  {
    if (b)
    {
      str = c;
      c = null;
      b = false;
      return str;
    }
    long l = System.currentTimeMillis();
    String str = super.readLine();
    a = (System.currentTimeMillis() - l + a);
    return str;
  }
}

/* Location:
 * Qualified Name:     afd.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
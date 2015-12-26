import java.io.File;
import java.io.IOException;
import java.util.Arrays;

final class ahl$b
{
  private final String b;
  private final long[] c;
  private boolean d;
  private ahl.a e;
  private long f;
  
  private ahl$b(ahl paramahl, String paramString)
  {
    b = paramString;
    c = new long[ahl.e(paramahl)];
  }
  
  private void a(String[] paramArrayOfString)
  {
    if (paramArrayOfString.length != ahl.e(a)) {
      throw b(paramArrayOfString);
    }
    int i = 0;
    try
    {
      while (i < paramArrayOfString.length)
      {
        c[i] = Long.parseLong(paramArrayOfString[i]);
        i += 1;
      }
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw b(paramArrayOfString);
    }
  }
  
  private IOException b(String[] paramArrayOfString)
  {
    throw new IOException("unexpected journal line: " + Arrays.toString(paramArrayOfString));
  }
  
  public File a(int paramInt)
  {
    return new File(ahl.f(a), b + "." + paramInt);
  }
  
  public String a()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    long[] arrayOfLong = c;
    int j = arrayOfLong.length;
    int i = 0;
    while (i < j)
    {
      long l = arrayOfLong[i];
      localStringBuilder.append(' ').append(l);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public File b(int paramInt)
  {
    return new File(ahl.f(a), b + "." + paramInt + ".tmp");
  }
}

/* Location:
 * Qualified Name:     ahl.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
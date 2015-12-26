import android.text.TextUtils;
import java.util.Arrays;

public class aey$n
  implements aey.e
{
  private final String a;
  private final boolean b;
  private final byte[] c;
  private Integer d = null;
  
  public aey$n(String paramString, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    a = paramString;
    c = paramArrayOfByte;
    b = paramBoolean;
  }
  
  public final aey.g a()
  {
    return aey.g.g;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof n)) {
        return false;
      }
      paramObject = (n)paramObject;
    } while ((TextUtils.equals(a, a)) && (Arrays.equals(c, c)) && (b == b));
    return false;
  }
  
  public int hashCode()
  {
    int k = 0;
    if (d != null) {
      return d.intValue();
    }
    if (a != null) {}
    int j;
    for (int i = a.hashCode();; i = 0)
    {
      i *= 31;
      j = i;
      if (c == null) {
        break;
      }
      byte[] arrayOfByte = c;
      int m = arrayOfByte.length;
      for (;;)
      {
        j = i;
        if (k >= m) {
          break;
        }
        i += arrayOfByte[k];
        k += 1;
      }
    }
    if (b) {}
    for (i = 1231;; i = 1237)
    {
      i += j * 31;
      d = Integer.valueOf(i);
      return i;
    }
  }
  
  public String toString()
  {
    return String.format("format: %s: size: %d, isPrimary: %s", new Object[] { a, Integer.valueOf(c.length), Boolean.valueOf(b) });
  }
}

/* Location:
 * Qualified Name:     aey.n
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
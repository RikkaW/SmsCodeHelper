import android.text.TextUtils;

public class aey$p
  implements aey.e
{
  private final String a;
  private final int b;
  private final String c;
  private final boolean d;
  
  public aey$p(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
  {
    if (paramString1.startsWith("sip:")) {}
    for (a = paramString1.substring(4);; a = paramString1)
    {
      b = paramInt;
      c = paramString2;
      d = paramBoolean;
      return;
    }
  }
  
  public aey.g a()
  {
    return aey.g.i;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof p)) {
        return false;
      }
      paramObject = (p)paramObject;
    } while ((b == b) && (TextUtils.equals(c, c)) && (TextUtils.equals(a, a)) && (d == d));
    return false;
  }
  
  public int hashCode()
  {
    int j = 0;
    int m = b;
    int i;
    if (c != null)
    {
      i = c.hashCode();
      if (a != null) {
        j = a.hashCode();
      }
      if (!d) {
        break label72;
      }
    }
    label72:
    for (int k = 1231;; k = 1237)
    {
      return k + ((i + m * 31) * 31 + j) * 31;
      i = 0;
      break;
    }
  }
  
  public String toString()
  {
    return "sip: " + a;
  }
}

/* Location:
 * Qualified Name:     aey.p
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
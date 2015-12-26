import android.text.TextUtils;

public class aey$h
  implements aey.e
{
  private final String a;
  private final int b;
  private final String c;
  private final int d;
  private final boolean e;
  
  public aey$h(int paramInt1, String paramString1, String paramString2, int paramInt2, boolean paramBoolean)
  {
    b = paramInt1;
    c = paramString1;
    d = paramInt2;
    a = paramString2;
    e = paramBoolean;
  }
  
  public final aey.g a()
  {
    return aey.g.f;
  }
  
  public String b()
  {
    return a;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof h)) {
        return false;
      }
      paramObject = (h)paramObject;
    } while ((d == d) && (b == b) && (TextUtils.equals(c, c)) && (TextUtils.equals(a, a)) && (e == e));
    return false;
  }
  
  public int hashCode()
  {
    int j = 0;
    int m = d;
    int n = b;
    int i;
    if (c != null)
    {
      i = c.hashCode();
      if (a != null) {
        j = a.hashCode();
      }
      if (!e) {
        break label84;
      }
    }
    label84:
    for (int k = 1231;; k = 1237)
    {
      return k + ((i + (m * 31 + n) * 31) * 31 + j) * 31;
      i = 0;
      break;
    }
  }
  
  public String toString()
  {
    return String.format("type: %d, protocol: %d, custom_protcol: %s, data: %s, isPrimary: %s", new Object[] { Integer.valueOf(d), Integer.valueOf(b), c, a, Boolean.valueOf(e) });
  }
}

/* Location:
 * Qualified Name:     aey.h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
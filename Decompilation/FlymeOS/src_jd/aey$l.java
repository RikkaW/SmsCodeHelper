import android.text.TextUtils;

public class aey$l
  implements aey.e
{
  private String a;
  private String b;
  private String c;
  private final String d;
  private final int e;
  private boolean f;
  
  public aey$l(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, boolean paramBoolean)
  {
    e = paramInt;
    a = paramString1;
    b = paramString2;
    c = paramString3;
    d = paramString4;
    f = paramBoolean;
  }
  
  public final aey.g a()
  {
    return aey.g.e;
  }
  
  public String b()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (!TextUtils.isEmpty(a)) {
      localStringBuilder.append(a);
    }
    if (!TextUtils.isEmpty(b))
    {
      if (localStringBuilder.length() > 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append(b);
    }
    if (!TextUtils.isEmpty(c))
    {
      if (localStringBuilder.length() > 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append(c);
    }
    return localStringBuilder.toString();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof l)) {
        return false;
      }
      paramObject = (l)paramObject;
    } while ((e == e) && (TextUtils.equals(a, a)) && (TextUtils.equals(b, b)) && (TextUtils.equals(c, c)) && (f == f));
    return false;
  }
  
  public int hashCode()
  {
    int k = 0;
    int n = e;
    int i;
    int j;
    if (a != null)
    {
      i = a.hashCode();
      if (b == null) {
        break label94;
      }
      j = b.hashCode();
      label38:
      if (c != null) {
        k = c.hashCode();
      }
      if (!f) {
        break label99;
      }
    }
    label94:
    label99:
    for (int m = 1231;; m = 1237)
    {
      return m + ((j + (i + n * 31) * 31) * 31 + k) * 31;
      i = 0;
      break;
      j = 0;
      break label38;
    }
  }
  
  public String toString()
  {
    return String.format("type: %d, organization: %s, department: %s, title: %s, isPrimary: %s", new Object[] { Integer.valueOf(e), a, b, c, Boolean.valueOf(f) });
  }
}

/* Location:
 * Qualified Name:     aey.l
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
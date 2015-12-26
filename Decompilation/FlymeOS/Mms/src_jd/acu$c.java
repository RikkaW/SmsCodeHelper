import android.text.TextUtils;

public class acu$c
{
  private final String a;
  private final int b;
  private final String c;
  private final CharSequence d;
  private final int e;
  private final boolean f;
  
  public acu$c(int paramInt1, String paramString1, CharSequence paramCharSequence, String paramString2, int paramInt2, boolean paramBoolean)
  {
    b = paramInt1;
    c = paramString1;
    paramString1 = paramCharSequence;
    if (TextUtils.isEmpty(paramCharSequence)) {
      paramString1 = "Im";
    }
    d = paramString1;
    e = paramInt2;
    a = paramString2;
    f = paramBoolean;
  }
  
  public CharSequence a()
  {
    return d;
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
      if (!(paramObject instanceof c)) {
        return false;
      }
      paramObject = (c)paramObject;
    } while ((TextUtils.equals(a(), ((c)paramObject).a())) && (TextUtils.equals(a, a)));
    return false;
  }
  
  public int hashCode()
  {
    int j = 0;
    int k = b;
    if (a != null) {}
    for (int i = a.hashCode();; i = 0)
    {
      if (d != null) {
        j = d.hashCode();
      }
      return (i + k * 31) * 31 + j;
    }
  }
  
  public String toString()
  {
    return String.format("type: %d, protocol: %d, custom_protcol: %s, data: %s, isPrimary: %s", new Object[] { Integer.valueOf(e), Integer.valueOf(b), c, a, Boolean.valueOf(f) });
  }
}

/* Location:
 * Qualified Name:     acu.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
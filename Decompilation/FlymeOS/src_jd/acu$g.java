import android.text.TextUtils;

public class acu$g
{
  private final String a;
  private final int b;
  private final String c;
  private boolean d;
  
  public acu$g(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
  {
    a = paramString1;
    b = paramInt;
    c = paramString2;
    d = paramBoolean;
  }
  
  public String a()
  {
    return a;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof g)) {
      return false;
    }
    paramObject = (g)paramObject;
    return TextUtils.equals(a, a);
  }
  
  public int hashCode()
  {
    if (a != null) {
      return a.hashCode();
    }
    return 0;
  }
  
  public String toString()
  {
    return String.format("type: %d, data: %s, label: %s, isPrimary: %s", new Object[] { Integer.valueOf(b), a, c, Boolean.valueOf(d) });
  }
}

/* Location:
 * Qualified Name:     acu.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
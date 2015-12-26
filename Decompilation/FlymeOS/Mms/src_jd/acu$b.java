import android.text.TextUtils;

public class acu$b
{
  private final String a;
  private final int b;
  private final String c;
  private final boolean d;
  
  public acu$b(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
  {
    b = paramInt;
    a = paramString1;
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
    if (!(paramObject instanceof b)) {
      return false;
    }
    paramObject = (b)paramObject;
    return TextUtils.equals(a, a);
  }
  
  public int hashCode()
  {
    if (a != null) {
      return a.hashCode();
    }
    return 0;
  }
}

/* Location:
 * Qualified Name:     acu.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
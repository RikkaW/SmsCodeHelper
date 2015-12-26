import android.text.TextUtils;

public class acu$f
{
  private final String a;
  
  public acu$f(String paramString)
  {
    a = paramString;
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
    if (!(paramObject instanceof f)) {
      return false;
    }
    paramObject = (f)paramObject;
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
    return "Organization: " + a;
  }
}

/* Location:
 * Qualified Name:     acu.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
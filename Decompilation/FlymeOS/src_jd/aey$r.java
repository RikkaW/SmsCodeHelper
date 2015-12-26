import android.text.TextUtils;

public class aey$r
  implements aey.e
{
  private final String a;
  
  public aey$r(String paramString)
  {
    a = paramString;
  }
  
  public aey.g a()
  {
    return aey.g.h;
  }
  
  public String b()
  {
    return a;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof r)) {
      return false;
    }
    paramObject = (r)paramObject;
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
    return "website: " + a;
  }
}

/* Location:
 * Qualified Name:     aey.r
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
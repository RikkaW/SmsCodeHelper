import android.text.TextUtils;

public class aey$b
  implements aey.e
{
  private final String a;
  
  public aey$b(String paramString)
  {
    a = paramString;
  }
  
  public aey.g a()
  {
    return aey.g.m;
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
  
  public String toString()
  {
    return "anniversary: " + a;
  }
}

/* Location:
 * Qualified Name:     aey.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
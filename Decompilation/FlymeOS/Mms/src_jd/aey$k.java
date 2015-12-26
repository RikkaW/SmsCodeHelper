import android.text.TextUtils;

public class aey$k
  implements aey.e
{
  public final String a;
  
  public aey$k(String paramString)
  {
    a = paramString;
  }
  
  public aey.g a()
  {
    return aey.g.k;
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
    if (!(paramObject instanceof k)) {
      return false;
    }
    paramObject = (k)paramObject;
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
    return "note: " + a;
  }
}

/* Location:
 * Qualified Name:     aey.k
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
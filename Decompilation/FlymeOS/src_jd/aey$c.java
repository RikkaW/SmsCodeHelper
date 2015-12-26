import android.text.TextUtils;

public class aey$c
  implements aey.e
{
  private final String a;
  
  public aey$c(String paramString)
  {
    a = paramString;
  }
  
  public aey.g a()
  {
    return aey.g.l;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof c)) {
      return false;
    }
    paramObject = (c)paramObject;
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
    return "birthday: " + a;
  }
}

/* Location:
 * Qualified Name:     aey.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
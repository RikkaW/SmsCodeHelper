import android.text.TextUtils;

public class aey$j
  implements aey.e
{
  private final String a;
  
  public aey$j(String paramString)
  {
    a = paramString;
  }
  
  public aey.g a()
  {
    return aey.g.j;
  }
  
  public String b()
  {
    return a;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof j)) {
      return false;
    }
    paramObject = (j)paramObject;
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
    return "nickname: " + a;
  }
}

/* Location:
 * Qualified Name:     aey.j
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
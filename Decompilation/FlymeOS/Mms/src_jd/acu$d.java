import android.text.TextUtils;

public class acu$d
{
  private final String a;
  
  public acu$d(String paramString)
  {
    a = paramString;
  }
  
  public String a()
  {
    return a;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof d)) {
      return false;
    }
    paramObject = (d)paramObject;
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
 * Qualified Name:     acu.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import android.text.TextUtils;

public class acu$a
{
  private final String a;
  
  public acu$a(String paramString)
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
    if (!(paramObject instanceof a)) {
      return false;
    }
    paramObject = (a)paramObject;
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
 * Qualified Name:     acu.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
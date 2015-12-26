import android.text.TextUtils;

public class acu$i
{
  private final String a;
  
  public acu$i(String paramString)
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
    if (!(paramObject instanceof i)) {
      return false;
    }
    paramObject = (i)paramObject;
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
 * Qualified Name:     acu.i
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
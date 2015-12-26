import android.text.TextUtils;

public class acu$h
{
  private final String a;
  
  public acu$h(String paramString)
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
    if (!(paramObject instanceof h)) {
      return false;
    }
    paramObject = (h)paramObject;
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
    return "postal: " + a;
  }
}

/* Location:
 * Qualified Name:     acu.h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
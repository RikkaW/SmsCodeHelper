import android.text.TextUtils;

public class acu$e
{
  public final String a;
  
  public acu$e(String paramString)
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
    if (!(paramObject instanceof e)) {
      return false;
    }
    paramObject = (e)paramObject;
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
 * Qualified Name:     acu.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
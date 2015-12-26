package android.support.v4.text;

import android.os.Build.VERSION;

public class ICUCompat
{
  private static final ICUCompatImpl IMPL = new ICUCompatImplBase();
  
  static
  {
    if (Build.VERSION.SDK_INT >= 14)
    {
      IMPL = new ICUCompatImplIcs();
      return;
    }
  }
  
  public static String addLikelySubtags(String paramString)
  {
    return IMPL.addLikelySubtags(paramString);
  }
  
  public static String getScript(String paramString)
  {
    return IMPL.getScript(paramString);
  }
  
  static abstract interface ICUCompatImpl
  {
    public abstract String addLikelySubtags(String paramString);
    
    public abstract String getScript(String paramString);
  }
  
  static class ICUCompatImplBase
    implements ICUCompat.ICUCompatImpl
  {
    public String addLikelySubtags(String paramString)
    {
      return paramString;
    }
    
    public String getScript(String paramString)
    {
      return null;
    }
  }
  
  static class ICUCompatImplIcs
    implements ICUCompat.ICUCompatImpl
  {
    public String addLikelySubtags(String paramString)
    {
      return ICUCompatIcs.addLikelySubtags(paramString);
    }
    
    public String getScript(String paramString)
    {
      return ICUCompatIcs.getScript(paramString);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.text.ICUCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
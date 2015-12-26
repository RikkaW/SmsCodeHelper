package android.support.v4.os;

import android.os.Build.VERSION;

public class TraceCompat
{
  public static void beginSection(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      TraceJellybeanMR2.beginSection(paramString);
    }
  }
  
  public static void endSection()
  {
    if (Build.VERSION.SDK_INT >= 18) {
      TraceJellybeanMR2.endSection();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.os.TraceCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
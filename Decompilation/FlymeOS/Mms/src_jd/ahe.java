import android.content.Context;
import android.util.Log;

public final class ahe
{
  private static String a = "";
  
  protected static void a(String paramString)
  {
    if (paramString.equals("GPS_SATELLITE")) {}
  }
  
  protected static boolean a(Context paramContext)
  {
    if (paramContext != null)
    {
      a = paramContext.getPackageName();
      return true;
    }
    Log.d(a, "Error: No SD Card!");
    return false;
  }
}

/* Location:
 * Qualified Name:     ahe
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
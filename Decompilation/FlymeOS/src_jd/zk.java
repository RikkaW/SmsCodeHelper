import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class zk
{
  private static HashMap<String, zi> a = new HashMap();
  private static boolean b = false;
  
  public static zi a(Context paramContext, String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    synchronized (a)
    {
      if (!b)
      {
        b(paramContext);
        b = true;
      }
      localzi = (zi)a.get(paramString);
      localObject = localzi;
      if (localzi == null)
      {
        paramContext = paramContext.getCacheDir();
        paramContext = paramContext.getAbsolutePath() + "/" + paramString;
        Log.d("CacheManager", "Cache dir: " + paramContext);
      }
      for (;;)
      {
        try
        {
          paramContext = new zi(paramContext, paramInt1, paramInt2, false, paramInt3);
        }
        catch (IOException paramContext)
        {
          localObject = localzi;
          continue;
        }
        try
        {
          a.put(paramString, paramContext);
          localObject = paramContext;
          return (zi)localObject;
        }
        catch (IOException paramString)
        {
          localObject = paramContext;
          paramContext = paramString;
        }
      }
      Log.e("CacheManager", "Cannot instantiate cache!", paramContext);
    }
  }
  
  public static void a(Context paramContext)
  {
    paramContext = paramContext.getCacheDir();
    paramContext = paramContext.getAbsolutePath() + "/";
    zi.a(paramContext + "imgcache");
    a.remove("imgcache");
  }
  
  private static void b(Context paramContext)
  {
    int i = 0;
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    try
    {
      int j = localSharedPreferences.getInt("cache-up-to-date", 0);
      i = j;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    if (i != 0) {
      return;
    }
    localSharedPreferences.edit().putInt("cache-up-to-date", 1).apply();
    a(paramContext);
  }
}

/* Location:
 * Qualified Name:     zk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
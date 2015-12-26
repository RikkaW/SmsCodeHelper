import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;

public class aki
{
  private static final String a(String paramString)
  {
    return "update_cache_" + paramString + ".temp";
  }
  
  public static void a(Context paramContext)
  {
    a(paramContext, null);
  }
  
  public static void a(Context paramContext, String paramString)
  {
    Object localObject = null;
    String str = c(paramContext.getPackageName());
    if (!TextUtils.isEmpty(paramString))
    {
      paramContext = a(paramString);
      localObject = b(paramString);
      paramString = paramContext;
    }
    for (paramContext = (Context)localObject;; paramContext = (Context)localObject)
    {
      localObject = new File(str);
      if (((File)localObject).exists())
      {
        if (((File)localObject).isDirectory()) {
          break label69;
        }
        ((File)localObject).delete();
      }
      for (;;)
      {
        return;
        label69:
        localObject = ((File)localObject).listFiles();
        if ((localObject != null) && (localObject.length > 0))
        {
          int j = localObject.length;
          int i = 0;
          while (i < j)
          {
            str = localObject[i];
            if ((str.isFile()) && ((paramString == null) || (!str.getName().equals(paramString))) && ((paramContext == null) || (!str.getName().equals(paramContext))))
            {
              d("delete cache file : " + str.getName());
              str.delete();
            }
            i += 1;
          }
        }
      }
      paramString = null;
    }
  }
  
  public static final boolean a(String paramString1, String paramString2)
  {
    try
    {
      boolean bool = new File(paramString1).renameTo(new File(paramString2));
      return bool;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return false;
  }
  
  public static final String b(Context paramContext, String paramString)
  {
    paramString = a(paramString);
    paramContext = c(paramContext.getPackageName());
    return paramContext + paramString;
  }
  
  private static final String b(String paramString)
  {
    return "update_cache_" + paramString + ".apk";
  }
  
  public static void b(Context paramContext)
  {
    String str2 = paramContext.getPackageName();
    String str1 = c(str2);
    paramContext = b(anl.b(paramContext, str2));
    paramContext = new File(str1 + paramContext);
    if (paramContext.exists())
    {
      d("delete cur cache : " + paramContext.getName());
      paramContext.delete();
    }
  }
  
  public static final String c(Context paramContext, String paramString)
  {
    paramString = b(paramString);
    paramContext = c(paramContext.getPackageName());
    return paramContext + paramString;
  }
  
  private static final String c(String paramString)
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + paramString + "/InstallCache/";
  }
  
  private static void d(String paramString)
  {
    Log.d("FileCacheManager", paramString);
  }
}

/* Location:
 * Qualified Name:     aki
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
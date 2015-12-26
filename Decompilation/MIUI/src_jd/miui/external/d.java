package miui.external;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.File;

class d
{
  private static PackageInfo a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 128);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  private static String a(String paramString)
  {
    return a(new String[] { "/data/app/" + paramString + "-1.apk", "/data/app/" + paramString + "-2.apk", "/data/app/" + paramString + "-1/base.apk", "/data/app/" + paramString + "-2/base.apk" });
  }
  
  private static String a(String paramString1, String paramString2)
  {
    String str = a(paramString1);
    paramString1 = str;
    if (str == null) {
      paramString1 = b(paramString2);
    }
    return paramString1;
  }
  
  private static String a(String[] paramArrayOfString)
  {
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = paramArrayOfString[i];
      if (new File(str).exists()) {
        return str;
      }
      i += 1;
    }
    return null;
  }
  
  private static String b(String paramString)
  {
    return a(new String[] { "/system/app/" + paramString + ".apk", "/system/priv-app/" + paramString + ".apk", "/system/app/" + paramString + "/" + paramString + ".apk", "/system/priv-app/" + paramString + "/" + paramString + ".apk" });
  }
  
  private static String c(String paramString)
  {
    return "/data/data/" + paramString + "/lib/";
  }
  
  public static String getApkPath(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject = null;
    if (paramContext == null) {
      paramContext = a(paramString1, paramString2);
    }
    do
    {
      return paramContext;
      paramString1 = a(paramContext, paramString1);
      paramContext = (Context)localObject;
    } while (paramString1 == null);
    return applicationInfo.publicSourceDir;
  }
  
  public static String getLibPath(Context paramContext, String paramString)
  {
    Object localObject = null;
    if (paramContext == null) {
      paramContext = c(paramString);
    }
    do
    {
      return paramContext;
      paramString = a(paramContext, paramString);
      paramContext = (Context)localObject;
    } while (paramString == null);
    return applicationInfo.nativeLibraryDir;
  }
  
  public static boolean isMiuiSystem()
  {
    return b("miui") != null;
  }
}

/* Location:
 * Qualified Name:     miui.external.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
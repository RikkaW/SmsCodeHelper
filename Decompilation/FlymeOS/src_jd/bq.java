import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.TextUtils;

public class bq
{
  private static int a = 100;
  
  public static String a(Context paramContext)
  {
    localObject = "default";
    try
    {
      Signature[] arrayOfSignature = getPackageManagergetPackageInfogetPackageName64signatures;
      paramContext = (Context)localObject;
      if (arrayOfSignature != null)
      {
        paramContext = (Context)localObject;
        if (arrayOfSignature.length > 0) {
          paramContext = anv.a(arrayOfSignature[0].toByteArray());
        }
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = (Context)localObject;
      }
    }
    localObject = paramContext;
    if (TextUtils.isEmpty(paramContext)) {
      localObject = "default";
    }
    return (String)localObject;
  }
  
  public static String b(Context paramContext)
  {
    Object localObject2 = paramContext.getPackageName();
    paramContext = paramContext.getPackageManager();
    localObject1 = "";
    try
    {
      localObject2 = paramContext.getApplicationInfo((String)localObject2, 128);
      paramContext = (Context)localObject1;
      if (localObject2 != null)
      {
        paramContext = (Context)localObject1;
        if (metaData != null) {
          paramContext = metaData.getString("com.ted.sdk.appkey");
        }
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = (Context)localObject1;
      }
    }
    localObject1 = paramContext;
    if (TextUtils.isEmpty(paramContext)) {
      localObject1 = "default";
    }
    return (String)localObject1;
  }
}

/* Location:
 * Qualified Name:     bq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
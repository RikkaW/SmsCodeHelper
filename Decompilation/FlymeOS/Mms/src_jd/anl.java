import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;

public class anl
{
  private static String a;
  private static String b;
  private static final char[] c = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  private static Boolean d = null;
  
  public static final long a(String paramString)
  {
    try
    {
      paramString = new File(paramString);
      if ((paramString.exists()) && (paramString.isFile()))
      {
        long l = paramString.length();
        return l;
      }
    }
    catch (Exception paramString) {}
    return 0L;
  }
  
  private static Bitmap a(Drawable paramDrawable)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    if (paramDrawable.getOpacity() != -1) {}
    for (Object localObject = Bitmap.Config.ARGB_8888;; localObject = Bitmap.Config.RGB_565)
    {
      localObject = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject);
      Canvas localCanvas = new Canvas((Bitmap)localObject);
      paramDrawable.setBounds(0, 0, i, j);
      paramDrawable.draw(localCanvas);
      return (Bitmap)localObject;
    }
  }
  
  public static final Bitmap a(String paramString, Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager();
      paramString = getPackageInfo0applicationInfo.loadIcon(paramContext);
      if (paramString != null)
      {
        paramString = a(paramString);
        return paramString;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static String a()
  {
    if (!b()) {}
    for (;;)
    {
      try
      {
        String str1 = (String)aji.a("android.os.BuildExt", "MZ_MODEL");
        String str2 = str1;
        if (TextUtils.isEmpty(str1)) {
          str2 = Build.MODEL;
        }
        return str2;
      }
      catch (Exception localException)
      {
        localObject = null;
        continue;
      }
      Object localObject = null;
    }
  }
  
  public static String a(double paramDouble)
  {
    if (paramDouble < 1024.0D) {
      if (paramDouble <= 0.0D) {
        break label318;
      }
    }
    label318:
    for (int i = (int)paramDouble;; i = 0)
    {
      return String.format("%dB", new Object[] { Integer.valueOf(i) });
      if ((paramDouble >= 1024.0D) && (paramDouble < 10240.0D)) {
        return String.format("%dKB", new Object[] { Integer.valueOf((int)(paramDouble / 1024.0D)) });
      }
      if ((paramDouble >= 10240.0D) && (paramDouble < 102400.0D)) {
        return String.format("%dKB", new Object[] { Integer.valueOf((int)(paramDouble / 1024.0D)) });
      }
      if ((paramDouble >= 102400.0D) && (paramDouble < 1048576.0D)) {
        return String.format("%dKB", new Object[] { Integer.valueOf((int)(paramDouble / 1024.0D)) });
      }
      if ((paramDouble >= 1048576.0D) && (paramDouble < 1.048576E8D)) {
        return String.format("%.2fMB", new Object[] { Double.valueOf(paramDouble / 1048576.0D) });
      }
      if ((paramDouble >= 1.048576E8D) && (paramDouble < 1.073741824E9D)) {
        return String.format("%.1fMB", new Object[] { Double.valueOf(paramDouble / 1048576.0D) });
      }
      if ((paramDouble >= 1.073741824E9D) && (paramDouble < 1.073741824E10D)) {
        return String.format("%.2fGB", new Object[] { Double.valueOf(paramDouble / 1.073741824E9D) });
      }
      if ((paramDouble >= 1.073741824E10D) && (paramDouble < 1.073741824E11D)) {
        return String.format("%.1fGB", new Object[] { Double.valueOf(paramDouble / 1.073741824E9D) });
      }
      return String.format("%dGB", new Object[] { Integer.valueOf((int)(paramDouble / 1.073741824E9D)) });
    }
  }
  
  public static String a(Context paramContext)
  {
    return a(paramContext, paramContext.getPackageName());
  }
  
  public static String a(Context paramContext, String paramString)
  {
    String str = b(paramContext, paramString);
    paramString = str;
    if (!l(paramContext))
    {
      paramString = str;
      if (c()) {
        paramString = str + "_i";
      }
    }
    return paramString;
  }
  
  private static String a(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = (String)aji.a("android.os.SystemProperties", "get", new Object[] { paramString1 });
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return paramString2;
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    int j = 0;
    int k = paramArrayOfByte.length;
    char[] arrayOfChar = new char[k << 1];
    int i = 0;
    while (i < k)
    {
      int m = j + 1;
      arrayOfChar[j] = c[((paramArrayOfByte[i] & 0xF0) >>> 4)];
      j = m + 1;
      arrayOfChar[m] = c[(paramArrayOfByte[i] & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar);
  }
  
  public static final String b(Context paramContext)
  {
    paramContext = null;
    try
    {
      localObject = a("ro.build.mask.id", "");
      paramContext = (Context)localObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject;
        localException.printStackTrace();
      }
    }
    localObject = paramContext;
    if (TextUtils.isEmpty(paramContext)) {
      localObject = Build.DISPLAY;
    }
    return (String)localObject;
  }
  
  public static String b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = getPackageManagergetPackageInfo0versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return "";
  }
  
  public static String b(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes("utf-8"));
      paramString = a(localMessageDigest.digest());
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static boolean b()
  {
    try
    {
      boolean bool = ((Boolean)aji.a("android.os.BuildExt", "isFlymeRom", null)).booleanValue();
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static final String c(Context paramContext)
  {
    return Build.VERSION.RELEASE;
  }
  
  public static boolean c()
  {
    try
    {
      boolean bool = ((Boolean)Class.forName("android.os.BuildExt").getMethod("isProductInternational", new Class[0]).invoke(null, new Object[0])).booleanValue();
      return bool;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
      return false;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        localNoSuchMethodException.printStackTrace();
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        localInvocationTargetException.printStackTrace();
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        localIllegalAccessException.printStackTrace();
      }
    }
  }
  
  public static final boolean c(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    try
    {
      File localFile = new File(paramString);
      boolean bool1 = bool2;
      if (localFile.isFile())
      {
        bool1 = bool2;
        if (localFile.exists())
        {
          paramContext = paramContext.getPackageManager().getPackageArchiveInfo(paramString, 0);
          bool1 = bool2;
          if (paramContext != null) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static final PackageInfo d(Context paramContext, String paramString)
  {
    try
    {
      File localFile = new File(paramString);
      if ((localFile.isFile()) && (localFile.exists()))
      {
        paramContext = paramContext.getPackageManager().getPackageArchiveInfo(paramString, 0);
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static final String d(Context paramContext)
  {
    if (l(paramContext)) {
      return "All";
    }
    return a();
  }
  
  public static boolean d()
  {
    try
    {
      boolean bool = ((Boolean)aji.a("android.os.BuildExt", "IS_SHOPDEMO")).booleanValue();
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static final String e(Context paramContext)
  {
    return g(paramContext);
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getApplicationInfo(paramString, 0);
      boolean bool1 = bool2;
      if (paramContext != null) {
        if ((flags & 0x1) == 0)
        {
          int i = flags;
          bool1 = bool2;
          if ((i & 0x80) == 0) {}
        }
        else
        {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static final String f(Context paramContext)
  {
    if (a == null) {
      a = a("ro.serialno", null);
    }
    return a;
  }
  
  public static String g(Context paramContext)
  {
    if (TextUtils.isEmpty(b)) {}
    try
    {
      b = (String)aji.a("android.telephony.MzTelephonyManager", "getDeviceId", null, null);
      if (!TextUtils.isEmpty(b)) {}
    }
    catch (Exception localException1)
    {
      try
      {
        b = (String)aji.a("com.meizu.telephony.MzTelephonymanager", "getDeviceId", new Class[] { Context.class, Integer.TYPE }, new Object[] { paramContext, Integer.valueOf(0) });
        if (TextUtils.isEmpty(b)) {
          b = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
        }
        return b;
        localException1 = localException1;
        localException1.printStackTrace();
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
    }
  }
  
  public static final String h(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    return paramContext.getApplicationInfo().loadLabel(localPackageManager).toString();
  }
  
  public static boolean i(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static boolean j(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext != null)
    {
      paramContext = paramContext.getActiveNetworkInfo();
      if ((paramContext != null) && (paramContext.isAvailable())) {
        return paramContext.getType() == 1;
      }
    }
    return false;
  }
  
  public static String k(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext.getSimState() == 5) {
      return paramContext.getSimOperator();
    }
    return "";
  }
  
  private static boolean l(Context paramContext)
  {
    if (d == null) {}
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (metaData != null)
      {
        d = Boolean.valueOf(metaData.getBoolean("system_independent", false));
        Log.d("MzUpdateComponent", "sSystemIndependent : " + d);
      }
      if (d == null) {
        d = Boolean.valueOf(false);
      }
      return d.booleanValue();
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
}

/* Location:
 * Qualified Name:     anl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
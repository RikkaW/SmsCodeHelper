package miui.external;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Application
  extends android.app.Application
  implements SdkConstants
{
  private boolean j;
  private boolean k;
  private ApplicationDelegate l;
  
  public Application()
  {
    if (!i()) {}
    while (!j()) {
      return;
    }
    j = true;
  }
  
  private void a(String paramString, int paramInt)
  {
    Log.e("miuisdk", "MIUI SDK encounter errors, please contact miuisdk@xiaomi.com for support. phase: " + paramString + " code: " + paramInt);
    e.a(SdkConstants.SdkError.GENERIC);
  }
  
  private void a(Throwable paramThrowable)
  {
    for (;;)
    {
      if ((paramThrowable == null) || (paramThrowable.getCause() == null)) {}
      do
      {
        Log.e("miuisdk", "MIUI SDK encounter errors, please contact miuisdk@xiaomi.com for support.", paramThrowable);
        e.a(SdkConstants.SdkError.GENERIC);
        return;
        if ((paramThrowable instanceof InvocationTargetException))
        {
          paramThrowable = paramThrowable.getCause();
          break;
        }
      } while (!(paramThrowable instanceof ExceptionInInitializerError));
      paramThrowable = paramThrowable.getCause();
    }
  }
  
  private boolean i()
  {
    try
    {
      if ((!d.isMiuiSystem()) && (!b.load(d.getApkPath(null, "com.miui.core", "miui"), null, d.getLibPath(null, "com.miui.core"), Application.class.getClassLoader())))
      {
        e.a(SdkConstants.SdkError.NO_SDK);
        return false;
      }
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
      return false;
    }
    return true;
  }
  
  private boolean j()
  {
    try
    {
      HashMap localHashMap = new HashMap();
      int i = ((Integer)f.o().getMethod("initialize", new Class[] { android.app.Application.class, Map.class }).invoke(null, new Object[] { this, localHashMap })).intValue();
      if (i != 0)
      {
        a("initialize", i);
        return false;
      }
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
      return false;
    }
    return true;
  }
  
  private boolean k()
  {
    try
    {
      HashMap localHashMap = new HashMap();
      int i = ((Integer)f.o().getMethod("start", new Class[] { Map.class }).invoke(null, new Object[] { localHashMap })).intValue();
      if (i == 1)
      {
        e.a(SdkConstants.SdkError.LOW_SDK_VERSION);
        return false;
      }
      if (i != 0)
      {
        a("start", i);
        return false;
      }
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
      return false;
    }
    return true;
  }
  
  final void a(int paramInt)
  {
    super.onTrimMemory(paramInt);
  }
  
  final void a(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(paramContext);
    if (!j) {}
    while (!k()) {
      return;
    }
    l = onCreateApplicationDelegate();
    if (l != null) {
      l.a(this);
    }
    k = true;
  }
  
  final void l()
  {
    super.onCreate();
  }
  
  final void m()
  {
    super.onTerminate();
  }
  
  final void n()
  {
    super.onLowMemory();
  }
  
  public final void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (l != null)
    {
      l.onConfigurationChanged(paramConfiguration);
      return;
    }
    a(paramConfiguration);
  }
  
  public final void onCreate()
  {
    if (!k) {
      return;
    }
    if (l != null)
    {
      l.onCreate();
      return;
    }
    l();
  }
  
  public ApplicationDelegate onCreateApplicationDelegate()
  {
    return null;
  }
  
  public final void onLowMemory()
  {
    if (l != null)
    {
      l.onLowMemory();
      return;
    }
    n();
  }
  
  public final void onTerminate()
  {
    if (l != null)
    {
      l.onTerminate();
      return;
    }
    m();
  }
  
  public final void onTrimMemory(int paramInt)
  {
    if (l != null)
    {
      l.onTrimMemory(paramInt);
      return;
    }
    a(paramInt);
  }
}

/* Location:
 * Qualified Name:     miui.external.Application
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
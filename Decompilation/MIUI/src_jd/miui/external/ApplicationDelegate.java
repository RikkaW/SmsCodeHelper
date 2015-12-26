package miui.external;

import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.ContextWrapper;
import android.content.res.Configuration;

public abstract class ApplicationDelegate
  extends ContextWrapper
  implements ComponentCallbacks2
{
  private Application o;
  
  public ApplicationDelegate()
  {
    super(null);
  }
  
  void a(Application paramApplication)
  {
    o = paramApplication;
    attachBaseContext(paramApplication);
  }
  
  public Application getApplication()
  {
    return o;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    o.a(paramConfiguration);
  }
  
  public void onCreate()
  {
    o.l();
  }
  
  public void onLowMemory()
  {
    o.n();
  }
  
  public void onTerminate()
  {
    o.m();
  }
  
  public void onTrimMemory(int paramInt)
  {
    o.a(paramInt);
  }
  
  public void registerComponentCallbacks(ComponentCallbacks paramComponentCallbacks)
  {
    o.registerComponentCallbacks(paramComponentCallbacks);
  }
  
  public void unregisterComponentCallbacks(ComponentCallbacks paramComponentCallbacks)
  {
    o.unregisterComponentCallbacks(paramComponentCallbacks);
  }
}

/* Location:
 * Qualified Name:     miui.external.ApplicationDelegate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
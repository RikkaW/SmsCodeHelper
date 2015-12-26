package android.support.v4.media.routing;

import android.media.MediaRouter;
import android.os.Build.VERSION;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class MediaRouterJellybean$GetDefaultRouteWorkaround
{
  private Method mGetSystemAudioRouteMethod;
  
  public MediaRouterJellybean$GetDefaultRouteWorkaround()
  {
    if ((Build.VERSION.SDK_INT < 16) || (Build.VERSION.SDK_INT > 17)) {
      throw new UnsupportedOperationException();
    }
    try
    {
      mGetSystemAudioRouteMethod = MediaRouter.class.getMethod("getSystemAudioRoute", new Class[0]);
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException) {}
  }
  
  public Object getDefaultRoute(Object paramObject)
  {
    paramObject = (MediaRouter)paramObject;
    if (mGetSystemAudioRouteMethod != null) {}
    try
    {
      Object localObject = mGetSystemAudioRouteMethod.invoke(paramObject, new Object[0]);
      return localObject;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      return ((MediaRouter)paramObject).getRouteAt(0);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;) {}
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.routing.MediaRouterJellybean.GetDefaultRouteWorkaround
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
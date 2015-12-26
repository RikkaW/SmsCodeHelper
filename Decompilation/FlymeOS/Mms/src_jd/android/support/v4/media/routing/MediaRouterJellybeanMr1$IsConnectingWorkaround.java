package android.support.v4.media.routing;

import android.media.MediaRouter.RouteInfo;
import android.os.Build.VERSION;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class MediaRouterJellybeanMr1$IsConnectingWorkaround
{
  private Method mGetStatusCodeMethod;
  private int mStatusConnecting;
  
  public MediaRouterJellybeanMr1$IsConnectingWorkaround()
  {
    if (Build.VERSION.SDK_INT != 17) {
      throw new UnsupportedOperationException();
    }
    try
    {
      mStatusConnecting = MediaRouter.RouteInfo.class.getField("STATUS_CONNECTING").getInt(null);
      mGetStatusCodeMethod = MediaRouter.RouteInfo.class.getMethod("getStatusCode", new Class[0]);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException) {}catch (NoSuchMethodException localNoSuchMethodException) {}catch (NoSuchFieldException localNoSuchFieldException) {}
  }
  
  public boolean isConnecting(Object paramObject)
  {
    paramObject = (MediaRouter.RouteInfo)paramObject;
    if (mGetStatusCodeMethod != null) {}
    try
    {
      int i = ((Integer)mGetStatusCodeMethod.invoke(paramObject, new Object[0])).intValue();
      int j = mStatusConnecting;
      return i == j;
    }
    catch (InvocationTargetException paramObject)
    {
      return false;
    }
    catch (IllegalAccessException paramObject)
    {
      for (;;) {}
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.routing.MediaRouterJellybeanMr1.IsConnectingWorkaround
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
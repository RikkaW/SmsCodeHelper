package android.support.v4.media.routing;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaRouter.RouteGroup;
import android.media.MediaRouter.RouteInfo;

public final class MediaRouterJellybean$RouteInfo
{
  public static Object getCategory(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getCategory();
  }
  
  public static Object getGroup(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getGroup();
  }
  
  public static Drawable getIconDrawable(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getIconDrawable();
  }
  
  public static CharSequence getName(Object paramObject, Context paramContext)
  {
    return ((MediaRouter.RouteInfo)paramObject).getName(paramContext);
  }
  
  public static int getPlaybackStream(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getPlaybackStream();
  }
  
  public static int getPlaybackType(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getPlaybackType();
  }
  
  public static CharSequence getStatus(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getStatus();
  }
  
  public static int getSupportedTypes(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getSupportedTypes();
  }
  
  public static Object getTag(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getTag();
  }
  
  public static int getVolume(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getVolume();
  }
  
  public static int getVolumeHandling(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getVolumeHandling();
  }
  
  public static int getVolumeMax(Object paramObject)
  {
    return ((MediaRouter.RouteInfo)paramObject).getVolumeMax();
  }
  
  public static boolean isGroup(Object paramObject)
  {
    return paramObject instanceof MediaRouter.RouteGroup;
  }
  
  public static void requestSetVolume(Object paramObject, int paramInt)
  {
    ((MediaRouter.RouteInfo)paramObject).requestSetVolume(paramInt);
  }
  
  public static void requestUpdateVolume(Object paramObject, int paramInt)
  {
    ((MediaRouter.RouteInfo)paramObject).requestUpdateVolume(paramInt);
  }
  
  public static void setTag(Object paramObject1, Object paramObject2)
  {
    ((MediaRouter.RouteInfo)paramObject1).setTag(paramObject2);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.routing.MediaRouterJellybean.RouteInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
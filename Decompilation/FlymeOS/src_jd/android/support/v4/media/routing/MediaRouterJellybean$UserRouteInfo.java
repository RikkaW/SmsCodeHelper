package android.support.v4.media.routing;

import android.graphics.drawable.Drawable;
import android.media.MediaRouter.UserRouteInfo;
import android.media.MediaRouter.VolumeCallback;
import android.media.RemoteControlClient;

public final class MediaRouterJellybean$UserRouteInfo
{
  public static void setIconDrawable(Object paramObject, Drawable paramDrawable)
  {
    ((MediaRouter.UserRouteInfo)paramObject).setIconDrawable(paramDrawable);
  }
  
  public static void setName(Object paramObject, CharSequence paramCharSequence)
  {
    ((MediaRouter.UserRouteInfo)paramObject).setName(paramCharSequence);
  }
  
  public static void setPlaybackStream(Object paramObject, int paramInt)
  {
    ((MediaRouter.UserRouteInfo)paramObject).setPlaybackStream(paramInt);
  }
  
  public static void setPlaybackType(Object paramObject, int paramInt)
  {
    ((MediaRouter.UserRouteInfo)paramObject).setPlaybackType(paramInt);
  }
  
  public static void setRemoteControlClient(Object paramObject1, Object paramObject2)
  {
    ((MediaRouter.UserRouteInfo)paramObject1).setRemoteControlClient((RemoteControlClient)paramObject2);
  }
  
  public static void setStatus(Object paramObject, CharSequence paramCharSequence)
  {
    ((MediaRouter.UserRouteInfo)paramObject).setStatus(paramCharSequence);
  }
  
  public static void setVolume(Object paramObject, int paramInt)
  {
    ((MediaRouter.UserRouteInfo)paramObject).setVolume(paramInt);
  }
  
  public static void setVolumeCallback(Object paramObject1, Object paramObject2)
  {
    ((MediaRouter.UserRouteInfo)paramObject1).setVolumeCallback((MediaRouter.VolumeCallback)paramObject2);
  }
  
  public static void setVolumeHandling(Object paramObject, int paramInt)
  {
    ((MediaRouter.UserRouteInfo)paramObject).setVolumeHandling(paramInt);
  }
  
  public static void setVolumeMax(Object paramObject, int paramInt)
  {
    ((MediaRouter.UserRouteInfo)paramObject).setVolumeMax(paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.routing.MediaRouterJellybean.UserRouteInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
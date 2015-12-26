package android.support.v4.media.routing;

import android.media.MediaRouter;
import android.media.MediaRouter.Callback;
import android.media.MediaRouter.RouteGroup;
import android.media.MediaRouter.RouteInfo;

class MediaRouterJellybean$CallbackProxy<T extends MediaRouterJellybean.Callback>
  extends MediaRouter.Callback
{
  protected final T mCallback;
  
  public MediaRouterJellybean$CallbackProxy(T paramT)
  {
    mCallback = paramT;
  }
  
  public void onRouteAdded(MediaRouter paramMediaRouter, MediaRouter.RouteInfo paramRouteInfo)
  {
    mCallback.onRouteAdded(paramRouteInfo);
  }
  
  public void onRouteChanged(MediaRouter paramMediaRouter, MediaRouter.RouteInfo paramRouteInfo)
  {
    mCallback.onRouteChanged(paramRouteInfo);
  }
  
  public void onRouteGrouped(MediaRouter paramMediaRouter, MediaRouter.RouteInfo paramRouteInfo, MediaRouter.RouteGroup paramRouteGroup, int paramInt)
  {
    mCallback.onRouteGrouped(paramRouteInfo, paramRouteGroup, paramInt);
  }
  
  public void onRouteRemoved(MediaRouter paramMediaRouter, MediaRouter.RouteInfo paramRouteInfo)
  {
    mCallback.onRouteRemoved(paramRouteInfo);
  }
  
  public void onRouteSelected(MediaRouter paramMediaRouter, int paramInt, MediaRouter.RouteInfo paramRouteInfo)
  {
    mCallback.onRouteSelected(paramInt, paramRouteInfo);
  }
  
  public void onRouteUngrouped(MediaRouter paramMediaRouter, MediaRouter.RouteInfo paramRouteInfo, MediaRouter.RouteGroup paramRouteGroup)
  {
    mCallback.onRouteUngrouped(paramRouteInfo, paramRouteGroup);
  }
  
  public void onRouteUnselected(MediaRouter paramMediaRouter, int paramInt, MediaRouter.RouteInfo paramRouteInfo)
  {
    mCallback.onRouteUnselected(paramInt, paramRouteInfo);
  }
  
  public void onRouteVolumeChanged(MediaRouter paramMediaRouter, MediaRouter.RouteInfo paramRouteInfo)
  {
    mCallback.onRouteVolumeChanged(paramRouteInfo);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.routing.MediaRouterJellybean.CallbackProxy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
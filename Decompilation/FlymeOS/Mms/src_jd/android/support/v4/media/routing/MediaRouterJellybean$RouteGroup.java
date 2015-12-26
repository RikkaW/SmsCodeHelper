package android.support.v4.media.routing;

import android.media.MediaRouter.RouteGroup;
import java.util.ArrayList;
import java.util.List;

public final class MediaRouterJellybean$RouteGroup
{
  public static List getGroupedRoutes(Object paramObject)
  {
    paramObject = (MediaRouter.RouteGroup)paramObject;
    int j = ((MediaRouter.RouteGroup)paramObject).getRouteCount();
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      localArrayList.add(((MediaRouter.RouteGroup)paramObject).getRouteAt(i));
      i += 1;
    }
    return localArrayList;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.routing.MediaRouterJellybean.RouteGroup
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
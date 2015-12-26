package android.support.v4.media.routing;

import android.content.Context;
import android.media.MediaRouter.RouteCategory;
import java.util.ArrayList;
import java.util.List;

public final class MediaRouterJellybean$RouteCategory
{
  public static CharSequence getName(Object paramObject, Context paramContext)
  {
    return ((MediaRouter.RouteCategory)paramObject).getName(paramContext);
  }
  
  public static List getRoutes(Object paramObject)
  {
    ArrayList localArrayList = new ArrayList();
    ((MediaRouter.RouteCategory)paramObject).getRoutes(localArrayList);
    return localArrayList;
  }
  
  public static int getSupportedTypes(Object paramObject)
  {
    return ((MediaRouter.RouteCategory)paramObject).getSupportedTypes();
  }
  
  public static boolean isGroupable(Object paramObject)
  {
    return ((MediaRouter.RouteCategory)paramObject).isGroupable();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.routing.MediaRouterJellybean.RouteCategory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
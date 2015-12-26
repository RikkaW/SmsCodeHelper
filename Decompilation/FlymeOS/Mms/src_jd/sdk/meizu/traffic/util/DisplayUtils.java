package sdk.meizu.traffic.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class DisplayUtils
{
  public static int dipToPixel(Context paramContext, float paramFloat)
  {
    return (int)(getResourcesgetDisplayMetricsdensity * paramFloat + 0.5D);
  }
  
  public static int getContentViewHeight(Context paramContext)
  {
    return getScreenHeight(paramContext);
  }
  
  public static int getScreenHeight(Context paramContext)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    Point localPoint = new Point();
    paramContext.getSize(localPoint);
    return y;
  }
  
  public static int getScreenWidth(Context paramContext)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    Point localPoint = new Point();
    paramContext.getSize(localPoint);
    return x;
  }
  
  public static int pixelToDip(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat / getResourcesgetDisplayMetricsdensity + 0.5F);
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.util.DisplayUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class kc
  implements ke
{
  private static int b;
  private static int c;
  private static int d;
  private static int e;
  private static int f;
  private static int g;
  private int a = -1;
  
  public kc(Context paramContext, int paramInt)
  {
    if ((paramInt != 10) && (paramInt != 11)) {
      throw new IllegalArgumentException("Bad layout type detected: " + paramInt);
    }
    a = paramInt;
    float f1 = getResourcesgetDisplayMetricsdensity;
    g = (int)(getResourcesgetConfigurationscreenWidthDp * f1 + 0.5F);
    f = (int)(f1 * getResourcesgetConfigurationscreenHeightDp + 0.5F);
    b = (int)(f * 0.9F);
    c = (int)(f * 0.1F);
    d = (int)(g * 0.9F);
    e = (int)(g * 0.1F);
  }
  
  public int a()
  {
    if (a == 10) {
      return g;
    }
    return f;
  }
  
  public int b()
  {
    if (a == 10) {
      return f;
    }
    return g;
  }
  
  public int c()
  {
    if (a == 10) {
      return b;
    }
    return d;
  }
  
  public int d()
  {
    if (a == 10) {
      return c;
    }
    return e;
  }
}

/* Location:
 * Qualified Name:     kc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
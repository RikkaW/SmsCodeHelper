package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;

public class ResourcesCompat
{
  public static Drawable getDrawable(Resources paramResources, int paramInt, Resources.Theme paramTheme)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return ResourcesCompatApi21.getDrawable(paramResources, paramInt, paramTheme);
    }
    return paramResources.getDrawable(paramInt);
  }
  
  public static Drawable getDrawableForDensity(Resources paramResources, int paramInt1, int paramInt2, Resources.Theme paramTheme)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 21) {
      return ResourcesCompatApi21.getDrawableForDensity(paramResources, paramInt1, paramInt2, paramTheme);
    }
    if (i >= 15) {
      return ResourcesCompatIcsMr1.getDrawableForDensity(paramResources, paramInt1, paramInt2);
    }
    return paramResources.getDrawable(paramInt1);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.content.res.ResourcesCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
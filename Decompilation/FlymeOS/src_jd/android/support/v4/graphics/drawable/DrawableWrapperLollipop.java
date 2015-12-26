package android.support.v4.graphics.drawable;

import android.content.res.Resources.Theme;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

class DrawableWrapperLollipop
  extends DrawableWrapperKitKat
{
  DrawableWrapperLollipop(Drawable paramDrawable)
  {
    super(paramDrawable);
  }
  
  public void applyTheme(Resources.Theme paramTheme)
  {
    mDrawable.applyTheme(paramTheme);
  }
  
  public boolean canApplyTheme()
  {
    return mDrawable.canApplyTheme();
  }
  
  public Rect getDirtyBounds()
  {
    return mDrawable.getDirtyBounds();
  }
  
  public void getOutline(Outline paramOutline)
  {
    mDrawable.getOutline(paramOutline);
  }
  
  public void setHotspot(float paramFloat1, float paramFloat2)
  {
    mDrawable.setHotspot(paramFloat1, paramFloat2);
  }
  
  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mDrawable.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.graphics.drawable.DrawableWrapperLollipop
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
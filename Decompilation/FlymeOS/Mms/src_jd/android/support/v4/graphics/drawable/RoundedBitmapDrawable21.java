package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.view.Gravity;

class RoundedBitmapDrawable21
  extends RoundedBitmapDrawable
{
  protected RoundedBitmapDrawable21(Resources paramResources, Bitmap paramBitmap)
  {
    super(paramResources, paramBitmap);
  }
  
  public void getOutline(Outline paramOutline)
  {
    updateDstRect();
    paramOutline.setRoundRect(mDstRect, getCornerRadius());
  }
  
  void gravityCompatApply(int paramInt1, int paramInt2, int paramInt3, Rect paramRect1, Rect paramRect2)
  {
    Gravity.apply(paramInt1, paramInt2, paramInt3, paramRect1, paramRect2, 0);
  }
  
  public boolean hasMipMap()
  {
    return (mBitmap != null) && (mBitmap.hasMipMap());
  }
  
  public void setMipMap(boolean paramBoolean)
  {
    if (mBitmap != null)
    {
      mBitmap.setHasMipMap(paramBoolean);
      invalidateSelf();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.graphics.drawable.RoundedBitmapDrawable21
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
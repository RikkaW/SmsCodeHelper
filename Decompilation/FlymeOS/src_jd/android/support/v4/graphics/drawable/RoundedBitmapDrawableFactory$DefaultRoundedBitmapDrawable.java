package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.v4.graphics.BitmapCompat;
import android.support.v4.view.GravityCompat;

class RoundedBitmapDrawableFactory$DefaultRoundedBitmapDrawable
  extends RoundedBitmapDrawable
{
  RoundedBitmapDrawableFactory$DefaultRoundedBitmapDrawable(Resources paramResources, Bitmap paramBitmap)
  {
    super(paramResources, paramBitmap);
  }
  
  void gravityCompatApply(int paramInt1, int paramInt2, int paramInt3, Rect paramRect1, Rect paramRect2)
  {
    GravityCompat.apply(paramInt1, paramInt2, paramInt3, paramRect1, paramRect2, 0);
  }
  
  public boolean hasMipMap()
  {
    return (mBitmap != null) && (BitmapCompat.hasMipMap(mBitmap));
  }
  
  public void setMipMap(boolean paramBoolean)
  {
    if (mBitmap != null)
    {
      BitmapCompat.setHasMipMap(mBitmap, paramBoolean);
      invalidateSelf();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory.DefaultRoundedBitmapDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
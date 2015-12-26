package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.graphics.BitmapCompat;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import java.io.InputStream;

public class RoundedBitmapDrawableFactory
{
  private static final String TAG = "RoundedBitmapDrawableFactory";
  
  public static RoundedBitmapDrawable create(Resources paramResources, Bitmap paramBitmap)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return new RoundedBitmapDrawable21(paramResources, paramBitmap);
    }
    return new DefaultRoundedBitmapDrawable(paramResources, paramBitmap);
  }
  
  public static RoundedBitmapDrawable create(Resources paramResources, InputStream paramInputStream)
  {
    paramResources = create(paramResources, BitmapFactory.decodeStream(paramInputStream));
    if (paramResources.getBitmap() == null) {
      Log.w("RoundedBitmapDrawableFactory", "BitmapDrawable cannot decode " + paramInputStream);
    }
    return paramResources;
  }
  
  public static RoundedBitmapDrawable create(Resources paramResources, String paramString)
  {
    paramResources = create(paramResources, BitmapFactory.decodeFile(paramString));
    if (paramResources.getBitmap() == null) {
      Log.w("RoundedBitmapDrawableFactory", "BitmapDrawable cannot decode " + paramString);
    }
    return paramResources;
  }
  
  static class DefaultRoundedBitmapDrawable
    extends RoundedBitmapDrawable
  {
    DefaultRoundedBitmapDrawable(Resources paramResources, Bitmap paramBitmap)
    {
      super(paramBitmap);
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
}

/* Location:
 * Qualified Name:     android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
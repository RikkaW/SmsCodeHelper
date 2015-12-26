package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.InsetDrawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

@Deprecated
public class ActionBarDrawerToggle
  implements DrawerLayout.DrawerListener
{
  private static final int ID_HOME = 16908332;
  private static final ActionBarDrawerToggleImpl IMPL = new ActionBarDrawerToggleImplBase(null);
  private static final float TOGGLE_DRAWABLE_OFFSET = 0.33333334F;
  private final Activity mActivity;
  private final Delegate mActivityImpl;
  private final int mCloseDrawerContentDescRes;
  private Drawable mDrawerImage;
  private final int mDrawerImageResource;
  private boolean mDrawerIndicatorEnabled = true;
  private final DrawerLayout mDrawerLayout;
  private boolean mHasCustomUpIndicator;
  private Drawable mHomeAsUpIndicator;
  private final int mOpenDrawerContentDescRes;
  private Object mSetIndicatorInfo;
  private SlideDrawable mSlider;
  
  static
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 18)
    {
      IMPL = new ActionBarDrawerToggleImplJellybeanMR2(null);
      return;
    }
    if (i >= 11)
    {
      IMPL = new ActionBarDrawerToggleImplHC(null);
      return;
    }
  }
  
  public ActionBarDrawerToggle(Activity paramActivity, DrawerLayout paramDrawerLayout, int paramInt1, int paramInt2, int paramInt3) {}
  
  public ActionBarDrawerToggle(Activity paramActivity, DrawerLayout paramDrawerLayout, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
  {
    mActivity = paramActivity;
    if ((paramActivity instanceof DelegateProvider))
    {
      mActivityImpl = ((DelegateProvider)paramActivity).getDrawerToggleDelegate();
      mDrawerLayout = paramDrawerLayout;
      mDrawerImageResource = paramInt1;
      mOpenDrawerContentDescRes = paramInt2;
      mCloseDrawerContentDescRes = paramInt3;
      mHomeAsUpIndicator = getThemeUpIndicator();
      mDrawerImage = ContextCompat.getDrawable(paramActivity, paramInt1);
      mSlider = new SlideDrawable(mDrawerImage, null);
      paramActivity = mSlider;
      if (!paramBoolean) {
        break label120;
      }
    }
    label120:
    for (float f = 0.33333334F;; f = 0.0F)
    {
      paramActivity.setOffset(f);
      return;
      mActivityImpl = null;
      break;
    }
  }
  
  private static boolean assumeMaterial(Context paramContext)
  {
    return (getApplicationInfotargetSdkVersion >= 21) && (Build.VERSION.SDK_INT >= 21);
  }
  
  Drawable getThemeUpIndicator()
  {
    if (mActivityImpl != null) {
      return mActivityImpl.getThemeUpIndicator();
    }
    return IMPL.getThemeUpIndicator(mActivity);
  }
  
  public boolean isDrawerIndicatorEnabled()
  {
    return mDrawerIndicatorEnabled;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (!mHasCustomUpIndicator) {
      mHomeAsUpIndicator = getThemeUpIndicator();
    }
    mDrawerImage = ContextCompat.getDrawable(mActivity, mDrawerImageResource);
    syncState();
  }
  
  public void onDrawerClosed(View paramView)
  {
    mSlider.setPosition(0.0F);
    if (mDrawerIndicatorEnabled) {
      setActionBarDescription(mOpenDrawerContentDescRes);
    }
  }
  
  public void onDrawerOpened(View paramView)
  {
    mSlider.setPosition(1.0F);
    if (mDrawerIndicatorEnabled) {
      setActionBarDescription(mCloseDrawerContentDescRes);
    }
  }
  
  public void onDrawerSlide(View paramView, float paramFloat)
  {
    float f = mSlider.getPosition();
    if (paramFloat > 0.5F) {}
    for (paramFloat = Math.max(f, Math.max(0.0F, paramFloat - 0.5F) * 2.0F);; paramFloat = Math.min(f, paramFloat * 2.0F))
    {
      mSlider.setPosition(paramFloat);
      return;
    }
  }
  
  public void onDrawerStateChanged(int paramInt) {}
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if ((paramMenuItem != null) && (paramMenuItem.getItemId() == 16908332) && (mDrawerIndicatorEnabled))
    {
      if (mDrawerLayout.isDrawerVisible(8388611)) {
        mDrawerLayout.closeDrawer(8388611);
      }
      for (;;)
      {
        return true;
        mDrawerLayout.openDrawer(8388611);
      }
    }
    return false;
  }
  
  void setActionBarDescription(int paramInt)
  {
    if (mActivityImpl != null)
    {
      mActivityImpl.setActionBarDescription(paramInt);
      return;
    }
    mSetIndicatorInfo = IMPL.setActionBarDescription(mSetIndicatorInfo, mActivity, paramInt);
  }
  
  void setActionBarUpIndicator(Drawable paramDrawable, int paramInt)
  {
    if (mActivityImpl != null)
    {
      mActivityImpl.setActionBarUpIndicator(paramDrawable, paramInt);
      return;
    }
    mSetIndicatorInfo = IMPL.setActionBarUpIndicator(mSetIndicatorInfo, mActivity, paramDrawable, paramInt);
  }
  
  public void setDrawerIndicatorEnabled(boolean paramBoolean)
  {
    int i;
    if (paramBoolean != mDrawerIndicatorEnabled)
    {
      if (!paramBoolean) {
        break label54;
      }
      SlideDrawable localSlideDrawable = mSlider;
      if (!mDrawerLayout.isDrawerOpen(8388611)) {
        break label46;
      }
      i = mCloseDrawerContentDescRes;
      setActionBarUpIndicator(localSlideDrawable, i);
    }
    for (;;)
    {
      mDrawerIndicatorEnabled = paramBoolean;
      return;
      label46:
      i = mOpenDrawerContentDescRes;
      break;
      label54:
      setActionBarUpIndicator(mHomeAsUpIndicator, 0);
    }
  }
  
  public void setHomeAsUpIndicator(int paramInt)
  {
    Drawable localDrawable = null;
    if (paramInt != 0) {
      localDrawable = ContextCompat.getDrawable(mActivity, paramInt);
    }
    setHomeAsUpIndicator(localDrawable);
  }
  
  public void setHomeAsUpIndicator(Drawable paramDrawable)
  {
    if (paramDrawable == null) {
      mHomeAsUpIndicator = getThemeUpIndicator();
    }
    for (mHasCustomUpIndicator = false;; mHasCustomUpIndicator = true)
    {
      if (!mDrawerIndicatorEnabled) {
        setActionBarUpIndicator(mHomeAsUpIndicator, 0);
      }
      return;
      mHomeAsUpIndicator = paramDrawable;
    }
  }
  
  public void syncState()
  {
    SlideDrawable localSlideDrawable;
    if (mDrawerLayout.isDrawerOpen(8388611))
    {
      mSlider.setPosition(1.0F);
      if (mDrawerIndicatorEnabled)
      {
        localSlideDrawable = mSlider;
        if (!mDrawerLayout.isDrawerOpen(8388611)) {
          break label67;
        }
      }
    }
    label67:
    for (int i = mCloseDrawerContentDescRes;; i = mOpenDrawerContentDescRes)
    {
      setActionBarUpIndicator(localSlideDrawable, i);
      return;
      mSlider.setPosition(0.0F);
      break;
    }
  }
  
  static abstract interface ActionBarDrawerToggleImpl
  {
    public abstract Drawable getThemeUpIndicator(Activity paramActivity);
    
    public abstract Object setActionBarDescription(Object paramObject, Activity paramActivity, int paramInt);
    
    public abstract Object setActionBarUpIndicator(Object paramObject, Activity paramActivity, Drawable paramDrawable, int paramInt);
  }
  
  static class ActionBarDrawerToggleImplBase
    implements ActionBarDrawerToggle.ActionBarDrawerToggleImpl
  {
    public Drawable getThemeUpIndicator(Activity paramActivity)
    {
      return null;
    }
    
    public Object setActionBarDescription(Object paramObject, Activity paramActivity, int paramInt)
    {
      return paramObject;
    }
    
    public Object setActionBarUpIndicator(Object paramObject, Activity paramActivity, Drawable paramDrawable, int paramInt)
    {
      return paramObject;
    }
  }
  
  static class ActionBarDrawerToggleImplHC
    implements ActionBarDrawerToggle.ActionBarDrawerToggleImpl
  {
    public Drawable getThemeUpIndicator(Activity paramActivity)
    {
      return ActionBarDrawerToggleHoneycomb.getThemeUpIndicator(paramActivity);
    }
    
    public Object setActionBarDescription(Object paramObject, Activity paramActivity, int paramInt)
    {
      return ActionBarDrawerToggleHoneycomb.setActionBarDescription(paramObject, paramActivity, paramInt);
    }
    
    public Object setActionBarUpIndicator(Object paramObject, Activity paramActivity, Drawable paramDrawable, int paramInt)
    {
      return ActionBarDrawerToggleHoneycomb.setActionBarUpIndicator(paramObject, paramActivity, paramDrawable, paramInt);
    }
  }
  
  static class ActionBarDrawerToggleImplJellybeanMR2
    implements ActionBarDrawerToggle.ActionBarDrawerToggleImpl
  {
    public Drawable getThemeUpIndicator(Activity paramActivity)
    {
      return ActionBarDrawerToggleJellybeanMR2.getThemeUpIndicator(paramActivity);
    }
    
    public Object setActionBarDescription(Object paramObject, Activity paramActivity, int paramInt)
    {
      return ActionBarDrawerToggleJellybeanMR2.setActionBarDescription(paramObject, paramActivity, paramInt);
    }
    
    public Object setActionBarUpIndicator(Object paramObject, Activity paramActivity, Drawable paramDrawable, int paramInt)
    {
      return ActionBarDrawerToggleJellybeanMR2.setActionBarUpIndicator(paramObject, paramActivity, paramDrawable, paramInt);
    }
  }
  
  public static abstract interface Delegate
  {
    @Nullable
    public abstract Drawable getThemeUpIndicator();
    
    public abstract void setActionBarDescription(int paramInt);
    
    public abstract void setActionBarUpIndicator(Drawable paramDrawable, int paramInt);
  }
  
  public static abstract interface DelegateProvider
  {
    @Nullable
    public abstract ActionBarDrawerToggle.Delegate getDrawerToggleDelegate();
  }
  
  class SlideDrawable
    extends InsetDrawable
    implements Drawable.Callback
  {
    private final boolean mHasMirroring;
    private float mOffset;
    private float mPosition;
    private final Rect mTmpRect;
    
    private SlideDrawable(Drawable paramDrawable)
    {
      super(0);
      if (Build.VERSION.SDK_INT > 18) {
        bool = true;
      }
      mHasMirroring = bool;
      mTmpRect = new Rect();
    }
    
    public void draw(Canvas paramCanvas)
    {
      int j = 1;
      copyBounds(mTmpRect);
      paramCanvas.save();
      if (ViewCompat.getLayoutDirection(mActivity.getWindow().getDecorView()) == 1) {}
      for (int i = 1;; i = 0)
      {
        if (i != 0) {
          j = -1;
        }
        int k = mTmpRect.width();
        float f1 = -mOffset;
        float f2 = k;
        float f3 = mPosition;
        paramCanvas.translate(j * (f1 * f2 * f3), 0.0F);
        if ((i != 0) && (!mHasMirroring))
        {
          paramCanvas.translate(k, 0.0F);
          paramCanvas.scale(-1.0F, 1.0F);
        }
        super.draw(paramCanvas);
        paramCanvas.restore();
        return;
      }
    }
    
    public float getPosition()
    {
      return mPosition;
    }
    
    public void setOffset(float paramFloat)
    {
      mOffset = paramFloat;
      invalidateSelf();
    }
    
    public void setPosition(float paramFloat)
    {
      mPosition = paramFloat;
      invalidateSelf();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.ActionBarDrawerToggle
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
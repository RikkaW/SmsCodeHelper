package android.support.v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class ActionBarDrawerToggle
  implements DrawerLayout.DrawerListener
{
  private final Delegate mActivityImpl;
  private final int mCloseDrawerContentDescRes;
  private boolean mDrawerIndicatorEnabled = true;
  private final DrawerLayout mDrawerLayout;
  private boolean mHasCustomUpIndicator;
  private Drawable mHomeAsUpIndicator;
  private final int mOpenDrawerContentDescRes;
  private DrawerToggle mSlider;
  private View.OnClickListener mToolbarNavigationClickListener;
  private boolean mWarnedForDisplayHomeAsUp = false;
  
  public ActionBarDrawerToggle(Activity paramActivity, DrawerLayout paramDrawerLayout, int paramInt1, int paramInt2)
  {
    this(paramActivity, null, paramDrawerLayout, null, paramInt1, paramInt2);
  }
  
  public ActionBarDrawerToggle(Activity paramActivity, DrawerLayout paramDrawerLayout, Toolbar paramToolbar, int paramInt1, int paramInt2)
  {
    this(paramActivity, paramToolbar, paramDrawerLayout, null, paramInt1, paramInt2);
  }
  
  <T extends Drawable,  extends DrawerToggle> ActionBarDrawerToggle(Activity paramActivity, Toolbar paramToolbar, DrawerLayout paramDrawerLayout, T paramT, int paramInt1, int paramInt2)
  {
    if (paramToolbar != null)
    {
      mActivityImpl = new ToolbarCompatDelegate(paramToolbar);
      paramToolbar.setNavigationOnClickListener(new ActionBarDrawerToggle.1(this));
      mDrawerLayout = paramDrawerLayout;
      mOpenDrawerContentDescRes = paramInt1;
      mCloseDrawerContentDescRes = paramInt2;
      if (paramT != null) {
        break label180;
      }
    }
    label180:
    for (mSlider = new DrawerArrowDrawableToggle(paramActivity, mActivityImpl.getActionBarThemedContext());; mSlider = ((DrawerToggle)paramT))
    {
      mHomeAsUpIndicator = getThemeUpIndicator();
      return;
      if ((paramActivity instanceof DelegateProvider))
      {
        mActivityImpl = ((DelegateProvider)paramActivity).getDrawerToggleDelegate();
        break;
      }
      if (Build.VERSION.SDK_INT >= 18)
      {
        mActivityImpl = new JellybeanMr2Delegate(paramActivity, null);
        break;
      }
      if (Build.VERSION.SDK_INT >= 11)
      {
        mActivityImpl = new HoneycombDelegate(paramActivity, null);
        break;
      }
      mActivityImpl = new DummyDelegate(paramActivity);
      break;
    }
  }
  
  private void toggle()
  {
    if (mDrawerLayout.isDrawerVisible(8388611))
    {
      mDrawerLayout.closeDrawer(8388611);
      return;
    }
    mDrawerLayout.openDrawer(8388611);
  }
  
  Drawable getThemeUpIndicator()
  {
    return mActivityImpl.getThemeUpIndicator();
  }
  
  public View.OnClickListener getToolbarNavigationClickListener()
  {
    return mToolbarNavigationClickListener;
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
    mSlider.setPosition(Math.min(1.0F, Math.max(0.0F, paramFloat)));
  }
  
  public void onDrawerStateChanged(int paramInt) {}
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if ((paramMenuItem != null) && (paramMenuItem.getItemId() == 16908332) && (mDrawerIndicatorEnabled))
    {
      toggle();
      return true;
    }
    return false;
  }
  
  void setActionBarDescription(int paramInt)
  {
    mActivityImpl.setActionBarDescription(paramInt);
  }
  
  void setActionBarUpIndicator(Drawable paramDrawable, int paramInt)
  {
    if ((!mWarnedForDisplayHomeAsUp) && (!mActivityImpl.isNavigationVisible()))
    {
      Log.w("ActionBarDrawerToggle", "DrawerToggle may not show up because NavigationIcon is not visible. You may need to call actionbar.setDisplayHomeAsUpEnabled(true);");
      mWarnedForDisplayHomeAsUp = true;
    }
    mActivityImpl.setActionBarUpIndicator(paramDrawable, paramInt);
  }
  
  public void setDrawerIndicatorEnabled(boolean paramBoolean)
  {
    int i;
    if (paramBoolean != mDrawerIndicatorEnabled)
    {
      if (!paramBoolean) {
        break label57;
      }
      Drawable localDrawable = (Drawable)mSlider;
      if (!mDrawerLayout.isDrawerOpen(8388611)) {
        break label49;
      }
      i = mCloseDrawerContentDescRes;
      setActionBarUpIndicator(localDrawable, i);
    }
    for (;;)
    {
      mDrawerIndicatorEnabled = paramBoolean;
      return;
      label49:
      i = mOpenDrawerContentDescRes;
      break;
      label57:
      setActionBarUpIndicator(mHomeAsUpIndicator, 0);
    }
  }
  
  public void setHomeAsUpIndicator(int paramInt)
  {
    Drawable localDrawable = null;
    if (paramInt != 0) {
      localDrawable = mDrawerLayout.getResources().getDrawable(paramInt);
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
  
  public void setToolbarNavigationClickListener(View.OnClickListener paramOnClickListener)
  {
    mToolbarNavigationClickListener = paramOnClickListener;
  }
  
  public void syncState()
  {
    Drawable localDrawable;
    if (mDrawerLayout.isDrawerOpen(8388611))
    {
      mSlider.setPosition(1.0F);
      if (mDrawerIndicatorEnabled)
      {
        localDrawable = (Drawable)mSlider;
        if (!mDrawerLayout.isDrawerOpen(8388611)) {
          break label74;
        }
      }
    }
    label74:
    for (int i = mCloseDrawerContentDescRes;; i = mOpenDrawerContentDescRes)
    {
      setActionBarUpIndicator(localDrawable, i);
      return;
      mSlider.setPosition(0.0F);
      break;
    }
  }
  
  public static abstract interface Delegate
  {
    public abstract Context getActionBarThemedContext();
    
    public abstract Drawable getThemeUpIndicator();
    
    public abstract boolean isNavigationVisible();
    
    public abstract void setActionBarDescription(int paramInt);
    
    public abstract void setActionBarUpIndicator(Drawable paramDrawable, int paramInt);
  }
  
  public static abstract interface DelegateProvider
  {
    @Nullable
    public abstract ActionBarDrawerToggle.Delegate getDrawerToggleDelegate();
  }
  
  static class DrawerArrowDrawableToggle
    extends DrawerArrowDrawable
    implements ActionBarDrawerToggle.DrawerToggle
  {
    private final Activity mActivity;
    
    public DrawerArrowDrawableToggle(Activity paramActivity, Context paramContext)
    {
      super();
      mActivity = paramActivity;
    }
    
    public float getPosition()
    {
      return super.getProgress();
    }
    
    boolean isLayoutRtl()
    {
      return ViewCompat.getLayoutDirection(mActivity.getWindow().getDecorView()) == 1;
    }
    
    public void setPosition(float paramFloat)
    {
      if (paramFloat == 1.0F) {
        setVerticalMirror(true);
      }
      for (;;)
      {
        super.setProgress(paramFloat);
        return;
        if (paramFloat == 0.0F) {
          setVerticalMirror(false);
        }
      }
    }
  }
  
  static abstract interface DrawerToggle
  {
    public abstract float getPosition();
    
    public abstract void setPosition(float paramFloat);
  }
  
  static class DummyDelegate
    implements ActionBarDrawerToggle.Delegate
  {
    final Activity mActivity;
    
    DummyDelegate(Activity paramActivity)
    {
      mActivity = paramActivity;
    }
    
    public Context getActionBarThemedContext()
    {
      return mActivity;
    }
    
    public Drawable getThemeUpIndicator()
    {
      return null;
    }
    
    public boolean isNavigationVisible()
    {
      return true;
    }
    
    public void setActionBarDescription(int paramInt) {}
    
    public void setActionBarUpIndicator(Drawable paramDrawable, int paramInt) {}
  }
  
  static class HoneycombDelegate
    implements ActionBarDrawerToggle.Delegate
  {
    final Activity mActivity;
    ActionBarDrawerToggleHoneycomb.SetIndicatorInfo mSetIndicatorInfo;
    
    private HoneycombDelegate(Activity paramActivity)
    {
      mActivity = paramActivity;
    }
    
    public Context getActionBarThemedContext()
    {
      ActionBar localActionBar = mActivity.getActionBar();
      if (localActionBar != null) {
        return localActionBar.getThemedContext();
      }
      return mActivity;
    }
    
    public Drawable getThemeUpIndicator()
    {
      return ActionBarDrawerToggleHoneycomb.getThemeUpIndicator(mActivity);
    }
    
    public boolean isNavigationVisible()
    {
      ActionBar localActionBar = mActivity.getActionBar();
      return (localActionBar != null) && ((localActionBar.getDisplayOptions() & 0x4) != 0);
    }
    
    public void setActionBarDescription(int paramInt)
    {
      mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarDescription(mSetIndicatorInfo, mActivity, paramInt);
    }
    
    public void setActionBarUpIndicator(Drawable paramDrawable, int paramInt)
    {
      mActivity.getActionBar().setDisplayShowHomeEnabled(true);
      mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarUpIndicator(mSetIndicatorInfo, mActivity, paramDrawable, paramInt);
      mActivity.getActionBar().setDisplayShowHomeEnabled(false);
    }
  }
  
  static class JellybeanMr2Delegate
    implements ActionBarDrawerToggle.Delegate
  {
    final Activity mActivity;
    
    private JellybeanMr2Delegate(Activity paramActivity)
    {
      mActivity = paramActivity;
    }
    
    public Context getActionBarThemedContext()
    {
      ActionBar localActionBar = mActivity.getActionBar();
      if (localActionBar != null) {
        return localActionBar.getThemedContext();
      }
      return mActivity;
    }
    
    public Drawable getThemeUpIndicator()
    {
      TypedArray localTypedArray = getActionBarThemedContext().obtainStyledAttributes(null, new int[] { 16843531 }, 16843470, 0);
      Drawable localDrawable = localTypedArray.getDrawable(0);
      localTypedArray.recycle();
      return localDrawable;
    }
    
    public boolean isNavigationVisible()
    {
      ActionBar localActionBar = mActivity.getActionBar();
      return (localActionBar != null) && ((localActionBar.getDisplayOptions() & 0x4) != 0);
    }
    
    public void setActionBarDescription(int paramInt)
    {
      ActionBar localActionBar = mActivity.getActionBar();
      if (localActionBar != null) {
        localActionBar.setHomeActionContentDescription(paramInt);
      }
    }
    
    public void setActionBarUpIndicator(Drawable paramDrawable, int paramInt)
    {
      ActionBar localActionBar = mActivity.getActionBar();
      if (localActionBar != null)
      {
        localActionBar.setHomeAsUpIndicator(paramDrawable);
        localActionBar.setHomeActionContentDescription(paramInt);
      }
    }
  }
  
  static class ToolbarCompatDelegate
    implements ActionBarDrawerToggle.Delegate
  {
    final CharSequence mDefaultContentDescription;
    final Drawable mDefaultUpIndicator;
    final Toolbar mToolbar;
    
    ToolbarCompatDelegate(Toolbar paramToolbar)
    {
      mToolbar = paramToolbar;
      mDefaultUpIndicator = paramToolbar.getNavigationIcon();
      mDefaultContentDescription = paramToolbar.getNavigationContentDescription();
    }
    
    public Context getActionBarThemedContext()
    {
      return mToolbar.getContext();
    }
    
    public Drawable getThemeUpIndicator()
    {
      return mDefaultUpIndicator;
    }
    
    public boolean isNavigationVisible()
    {
      return true;
    }
    
    public void setActionBarDescription(int paramInt)
    {
      if (paramInt == 0)
      {
        mToolbar.setNavigationContentDescription(mDefaultContentDescription);
        return;
      }
      mToolbar.setNavigationContentDescription(paramInt);
    }
    
    public void setActionBarUpIndicator(Drawable paramDrawable, int paramInt)
    {
      mToolbar.setNavigationIcon(paramDrawable);
      setActionBarDescription(paramInt);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.ActionBarDrawerToggle
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.app.WindowDecorActionBar;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.view.WindowCallbackWrapper;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.Window.Callback;

abstract class AppCompatDelegateImplBase
  extends AppCompatDelegate
{
  private ActionBar mActionBar;
  final AppCompatCallback mAppCompatCallback;
  final Context mContext;
  boolean mHasActionBar;
  private boolean mIsDestroyed;
  boolean mIsFloating;
  private MenuInflater mMenuInflater;
  int mMzUiOptions;
  final Window.Callback mOriginalWindowCallback;
  boolean mOverlayActionBar;
  boolean mOverlayActionMode;
  private CharSequence mTitle;
  final Window mWindow;
  boolean mWindowNoTitle;
  
  AppCompatDelegateImplBase(Context paramContext, Window paramWindow, AppCompatCallback paramAppCompatCallback)
  {
    mContext = paramContext;
    mWindow = paramWindow;
    mAppCompatCallback = paramAppCompatCallback;
    mOriginalWindowCallback = mWindow.getCallback();
    if ((mOriginalWindowCallback instanceof AppCompatWindowCallbackBase)) {
      throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }
    mWindow.setCallback(wrapWindowCallback(mOriginalWindowCallback));
  }
  
  abstract ActionBar createSupportActionBar();
  
  abstract boolean dispatchKeyEvent(KeyEvent paramKeyEvent);
  
  final Context getActionBarThemedContext()
  {
    Context localContext = null;
    Object localObject = getSupportActionBar();
    if (localObject != null) {
      localContext = ((ActionBar)localObject).getThemedContext();
    }
    localObject = localContext;
    if (localContext == null) {
      localObject = mContext;
    }
    return (Context)localObject;
  }
  
  public final ActionBarDrawerToggle.Delegate getDrawerToggleDelegate()
  {
    return new ActionBarDrawableToggleImpl(null);
  }
  
  public MenuInflater getMenuInflater()
  {
    if (mMenuInflater == null) {
      mMenuInflater = new SupportMenuInflater(getActionBarThemedContext());
    }
    return mMenuInflater;
  }
  
  public ActionBar getSupportActionBar()
  {
    if (mHasActionBar) {
      if (mActionBar == null) {
        mActionBar = createSupportActionBar();
      }
    }
    for (;;)
    {
      return mActionBar;
      if ((mActionBar instanceof WindowDecorActionBar)) {
        mActionBar = null;
      }
    }
  }
  
  final CharSequence getTitle()
  {
    if ((mOriginalWindowCallback instanceof Activity)) {
      return ((Activity)mOriginalWindowCallback).getTitle();
    }
    return mTitle;
  }
  
  final Window.Callback getWindowCallback()
  {
    return mWindow.getCallback();
  }
  
  final boolean isDestroyed()
  {
    return mIsDestroyed;
  }
  
  public boolean isHandleNativeActionModesEnabled()
  {
    return false;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    ViewUtils.init();
    paramBundle = mContext.obtainStyledAttributes(R.styleable.Theme);
    if (!paramBundle.hasValue(R.styleable.Theme_windowActionBar))
    {
      paramBundle.recycle();
      throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }
    if (paramBundle.getBoolean(R.styleable.Theme_windowActionBar, false)) {
      mHasActionBar = true;
    }
    if (paramBundle.getBoolean(R.styleable.Theme_windowActionBarOverlay, false)) {
      mOverlayActionBar = true;
    }
    if (paramBundle.getBoolean(R.styleable.Theme_windowActionModeOverlay, false)) {
      mOverlayActionMode = true;
    }
    mIsFloating = paramBundle.getBoolean(R.styleable.Theme_android_windowIsFloating, false);
    mWindowNoTitle = paramBundle.getBoolean(R.styleable.Theme_windowNoTitle, false);
    paramBundle.recycle();
  }
  
  public final void onDestroy()
  {
    mIsDestroyed = true;
  }
  
  abstract boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent);
  
  abstract boolean onMenuOpened(int paramInt, Menu paramMenu);
  
  abstract boolean onPanelClosed(int paramInt, Menu paramMenu);
  
  abstract void onTitleChanged(CharSequence paramCharSequence);
  
  final ActionBar peekSupportActionBar()
  {
    return mActionBar;
  }
  
  public void setHandleNativeActionModesEnabled(boolean paramBoolean) {}
  
  final void setSupportActionBar(ActionBar paramActionBar)
  {
    mActionBar = paramActionBar;
  }
  
  public final void setTitle(CharSequence paramCharSequence)
  {
    mTitle = paramCharSequence;
    onTitleChanged(paramCharSequence);
  }
  
  public void setUiOptions(int paramInt)
  {
    mMzUiOptions = paramInt;
  }
  
  abstract ActionMode startSupportActionModeFromWindow(ActionMode.Callback paramCallback);
  
  Window.Callback wrapWindowCallback(Window.Callback paramCallback)
  {
    return new AppCompatWindowCallbackBase(paramCallback);
  }
  
  class ActionBarDrawableToggleImpl
    implements ActionBarDrawerToggle.Delegate
  {
    private ActionBarDrawableToggleImpl() {}
    
    public Context getActionBarThemedContext()
    {
      return AppCompatDelegateImplBase.this.getActionBarThemedContext();
    }
    
    public Drawable getThemeUpIndicator()
    {
      TintTypedArray localTintTypedArray = TintTypedArray.obtainStyledAttributes(getActionBarThemedContext(), null, new int[] { R.attr.homeAsUpIndicator });
      Drawable localDrawable = localTintTypedArray.getDrawable(0);
      localTintTypedArray.recycle();
      return localDrawable;
    }
    
    public boolean isNavigationVisible()
    {
      ActionBar localActionBar = getSupportActionBar();
      return (localActionBar != null) && ((localActionBar.getDisplayOptions() & 0x4) != 0);
    }
    
    public void setActionBarDescription(int paramInt)
    {
      ActionBar localActionBar = getSupportActionBar();
      if (localActionBar != null) {
        localActionBar.setHomeActionContentDescription(paramInt);
      }
    }
    
    public void setActionBarUpIndicator(Drawable paramDrawable, int paramInt)
    {
      ActionBar localActionBar = getSupportActionBar();
      if (localActionBar != null)
      {
        localActionBar.setHomeAsUpIndicator(paramDrawable);
        localActionBar.setHomeActionContentDescription(paramInt);
      }
    }
  }
  
  class AppCompatWindowCallbackBase
    extends WindowCallbackWrapper
  {
    AppCompatWindowCallbackBase(Window.Callback paramCallback)
    {
      super();
    }
    
    public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
    {
      if (super.dispatchKeyEvent(paramKeyEvent)) {
        return true;
      }
      return AppCompatDelegateImplBase.this.dispatchKeyEvent(paramKeyEvent);
    }
    
    public boolean dispatchKeyShortcutEvent(KeyEvent paramKeyEvent)
    {
      if (onKeyShortcut(paramKeyEvent.getKeyCode(), paramKeyEvent)) {
        return true;
      }
      return super.dispatchKeyShortcutEvent(paramKeyEvent);
    }
    
    public void onContentChanged() {}
    
    public boolean onCreatePanelMenu(int paramInt, Menu paramMenu)
    {
      if ((paramInt == 0) && (!(paramMenu instanceof MenuBuilder))) {
        return false;
      }
      return super.onCreatePanelMenu(paramInt, paramMenu);
    }
    
    public boolean onMenuOpened(int paramInt, Menu paramMenu)
    {
      if (AppCompatDelegateImplBase.this.onMenuOpened(paramInt, paramMenu)) {
        return true;
      }
      return super.onMenuOpened(paramInt, paramMenu);
    }
    
    public void onPanelClosed(int paramInt, Menu paramMenu)
    {
      if (onPanelClosed(paramInt, paramMenu)) {
        return;
      }
      super.onPanelClosed(paramInt, paramMenu);
    }
    
    public boolean onPreparePanel(int paramInt, View paramView, Menu paramMenu)
    {
      MenuBuilder localMenuBuilder;
      boolean bool1;
      if ((paramMenu instanceof MenuBuilder))
      {
        localMenuBuilder = (MenuBuilder)paramMenu;
        if ((paramInt != 0) || (localMenuBuilder != null)) {
          break label34;
        }
        bool1 = false;
      }
      label34:
      boolean bool2;
      do
      {
        return bool1;
        localMenuBuilder = null;
        break;
        if (localMenuBuilder != null) {
          localMenuBuilder.setOverrideVisibleItems(true);
        }
        bool2 = super.onPreparePanel(paramInt, paramView, paramMenu);
        bool1 = bool2;
      } while (localMenuBuilder == null);
      localMenuBuilder.setOverrideVisibleItems(false);
      return bool2;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatDelegateImplBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
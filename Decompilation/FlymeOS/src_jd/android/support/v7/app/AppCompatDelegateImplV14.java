package android.support.v7.app;

import android.content.Context;
import android.support.v7.internal.view.SupportActionModeWrapper;
import android.support.v7.internal.view.SupportActionModeWrapper.CallbackWrapper;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.Window;
import android.view.Window.Callback;

class AppCompatDelegateImplV14
  extends AppCompatDelegateImplV11
{
  private boolean mHandleNativeActionModes = true;
  
  AppCompatDelegateImplV14(Context paramContext, Window paramWindow, AppCompatCallback paramAppCompatCallback)
  {
    super(paramContext, paramWindow, paramAppCompatCallback);
  }
  
  public boolean isHandleNativeActionModesEnabled()
  {
    return mHandleNativeActionModes;
  }
  
  public void setHandleNativeActionModesEnabled(boolean paramBoolean)
  {
    mHandleNativeActionModes = paramBoolean;
  }
  
  Window.Callback wrapWindowCallback(Window.Callback paramCallback)
  {
    return new AppCompatWindowCallbackV14(paramCallback);
  }
  
  class AppCompatWindowCallbackV14
    extends AppCompatDelegateImplBase.AppCompatWindowCallbackBase
  {
    AppCompatWindowCallbackV14(Window.Callback paramCallback)
    {
      super(paramCallback);
    }
    
    public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
    {
      if (AppCompatDelegateImplV14.this.dispatchKeyEvent(paramKeyEvent)) {
        return true;
      }
      return super.dispatchKeyEvent(paramKeyEvent);
    }
    
    public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback paramCallback)
    {
      if (mHandleNativeActionModes) {
        return startAsSupportActionMode(paramCallback);
      }
      return super.onWindowStartingActionMode(paramCallback);
    }
    
    final android.view.ActionMode startAsSupportActionMode(ActionMode.Callback paramCallback)
    {
      paramCallback = new SupportActionModeWrapper.CallbackWrapper(mContext, paramCallback);
      Object localObject = startSupportActionMode(paramCallback);
      if (localObject != null)
      {
        localObject = new SupportActionModeWrapper(mContext, (android.support.v7.view.ActionMode)localObject);
        paramCallback.addActionModeWrapper((SupportActionModeWrapper)localObject);
        return (android.view.ActionMode)localObject;
      }
      return null;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatDelegateImplV14
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
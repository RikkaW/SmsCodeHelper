package android.support.v7.app;

import android.support.v7.internal.view.SupportActionModeWrapper;
import android.support.v7.internal.view.SupportActionModeWrapper.CallbackWrapper;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.Window.Callback;

class AppCompatDelegateImplV14$AppCompatWindowCallbackV14
  extends AppCompatDelegateImplBase.AppCompatWindowCallbackBase
{
  AppCompatDelegateImplV14$AppCompatWindowCallbackV14(AppCompatDelegateImplV14 paramAppCompatDelegateImplV14, Window.Callback paramCallback)
  {
    super(paramAppCompatDelegateImplV14, paramCallback);
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (this$0.dispatchKeyEvent(paramKeyEvent)) {
      return true;
    }
    return super.dispatchKeyEvent(paramKeyEvent);
  }
  
  public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback paramCallback)
  {
    if (AppCompatDelegateImplV14.access$000(this$0)) {
      return startAsSupportActionMode(paramCallback);
    }
    return super.onWindowStartingActionMode(paramCallback);
  }
  
  final android.view.ActionMode startAsSupportActionMode(ActionMode.Callback paramCallback)
  {
    paramCallback = new SupportActionModeWrapper.CallbackWrapper(this$0.mContext, paramCallback);
    Object localObject = this$0.startSupportActionMode(paramCallback);
    if (localObject != null)
    {
      localObject = new SupportActionModeWrapper(this$0.mContext, (android.support.v7.view.ActionMode)localObject);
      paramCallback.addActionModeWrapper((SupportActionModeWrapper)localObject);
      return (android.view.ActionMode)localObject;
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatDelegateImplV14.AppCompatWindowCallbackV14
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
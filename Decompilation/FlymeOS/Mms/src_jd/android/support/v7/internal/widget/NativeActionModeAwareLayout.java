package android.support.v7.internal.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.View;

@TargetApi(11)
public class NativeActionModeAwareLayout
  extends ContentFrameLayout
{
  private OnActionModeForChildListener mActionModeForChildListener;
  
  public NativeActionModeAwareLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public void makeOptionalFitsSystemWindows() {}
  
  public void setActionModeForChildListener(OnActionModeForChildListener paramOnActionModeForChildListener)
  {
    mActionModeForChildListener = paramOnActionModeForChildListener;
  }
  
  public ActionMode startActionModeForChild(View paramView, ActionMode.Callback paramCallback)
  {
    if (mActionModeForChildListener != null) {
      return mActionModeForChildListener.startActionModeForChild(paramView, paramCallback);
    }
    return super.startActionModeForChild(paramView, paramCallback);
  }
  
  public static abstract interface OnActionModeForChildListener
  {
    public abstract ActionMode startActionModeForChild(View paramView, ActionMode.Callback paramCallback);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.NativeActionModeAwareLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
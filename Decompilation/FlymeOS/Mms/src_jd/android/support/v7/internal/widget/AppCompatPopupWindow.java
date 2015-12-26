package android.support.v7.internal.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.PopupWindow;
import java.lang.reflect.Field;

public class AppCompatPopupWindow
  extends PopupWindow
{
  private static final String TAG = "AppCompatPopupWindow";
  private final boolean mOverlapAnchor;
  
  public AppCompatPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.PopupWindow, paramInt, 0);
    mOverlapAnchor = paramContext.getBoolean(R.styleable.PopupWindow_overlapAnchor, false);
    setBackgroundDrawable(paramContext.getDrawable(R.styleable.PopupWindow_android_popupBackground));
    paramContext.recycle();
    if (Build.VERSION.SDK_INT < 14) {
      wrapOnScrollChangedListener(this);
    }
  }
  
  private static void wrapOnScrollChangedListener(PopupWindow paramPopupWindow)
  {
    try
    {
      Field localField1 = PopupWindow.class.getDeclaredField("mAnchor");
      localField1.setAccessible(true);
      Field localField2 = PopupWindow.class.getDeclaredField("mOnScrollChangedListener");
      localField2.setAccessible(true);
      localField2.set(paramPopupWindow, new AppCompatPopupWindow.1(localField1, paramPopupWindow, (ViewTreeObserver.OnScrollChangedListener)localField2.get(paramPopupWindow)));
      return;
    }
    catch (Exception paramPopupWindow)
    {
      Log.d("AppCompatPopupWindow", "Exception while installing workaround OnScrollChangedListener", paramPopupWindow);
    }
  }
  
  public void showAsDropDown(View paramView, int paramInt1, int paramInt2)
  {
    int i = paramInt2;
    if (Build.VERSION.SDK_INT < 21)
    {
      i = paramInt2;
      if (mOverlapAnchor) {
        i = paramInt2 - paramView.getHeight();
      }
    }
    super.showAsDropDown(paramView, paramInt1, i);
  }
  
  @TargetApi(19)
  public void showAsDropDown(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt2;
    if (Build.VERSION.SDK_INT < 21)
    {
      i = paramInt2;
      if (mOverlapAnchor) {
        i = paramInt2 - paramView.getHeight();
      }
    }
    paramInt2 = paramInt1;
    if (Build.DEVICE.equals("HWGRA"))
    {
      int j = paramView.getWidth();
      paramInt2 = paramInt1;
      if ((Gravity.getAbsoluteGravity(paramInt3, paramView.getLayoutDirection()) & 0x7) == 5) {
        paramInt2 = paramInt1 - (getWidth() - j);
      }
    }
    super.showAsDropDown(paramView, paramInt2, i, paramInt3);
  }
  
  public void update(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((Build.VERSION.SDK_INT < 21) && (mOverlapAnchor)) {
      paramInt2 -= paramView.getHeight();
    }
    for (;;)
    {
      super.update(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.AppCompatPopupWindow
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
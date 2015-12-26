package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.appcompat.R.dimen;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.Toast;

public class ToastUtils
{
  public static Toast showToast(View paramView, CharSequence paramCharSequence)
  {
    return showToast(paramView, paramCharSequence, 0);
  }
  
  public static Toast showToast(View paramView, CharSequence paramCharSequence, int paramInt)
  {
    Context localContext = paramView.getContext();
    Object localObject = localContext.getResources().getDisplayMetrics();
    int i = widthPixels;
    int j = heightPixels;
    int n = j / 2;
    paramView.getWindowVisibleDisplayFrame(new Rect());
    localObject = new int[2];
    paramView.getLocationOnScreen((int[])localObject);
    i = paramView.getWidth();
    int k = paramView.getHeight();
    int i1 = localObject[1];
    paramView = Toast.makeText(localContext, paramCharSequence, paramInt);
    paramCharSequence = paramView.getView();
    paramCharSequence.measure(View.MeasureSpec.makeMeasureSpec(localContext.getResources().getDimensionPixelSize(R.dimen.mz_alert_dialog_with_button_min_width), Integer.MIN_VALUE), 0);
    paramInt = paramCharSequence.getMeasuredWidth();
    int i2 = localContext.getResources().getDimensionPixelSize(R.dimen.mz_action_button_min_height);
    int i3 = localContext.getResources().getDimensionPixelSize(R.dimen.status_bar_height);
    int m = localContext.getResources().getDimensionPixelSize(R.dimen.mz_toast_y_offset);
    if (i1 + k < n)
    {
      j = localObject[1];
      paramView.setGravity(51, localObject[0] + i / 2 - paramInt / 2, m + (j + k - i3));
    }
    for (;;)
    {
      paramView.show();
      return paramView;
      k = localObject[1];
      paramView.setGravity(83, localObject[0] + i / 2 - paramInt / 2, m + (j - k - i2));
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ToastUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
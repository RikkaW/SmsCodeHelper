package android.support.v7.widget;

import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.DisplayMetrics;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.OnLayoutChangeListener;

class Toolbar$3
  implements View.OnLayoutChangeListener
{
  Toolbar$3(Toolbar paramToolbar) {}
  
  public void onLayoutChange(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    Object localObject = (View)paramView.getParent();
    paramInt5 = (int)(this$0.getResources().getDisplayMetrics().density * 56.0F);
    if (paramInt3 - paramInt1 < paramInt5)
    {
      paramInt5 = (paramInt5 - (paramInt3 - paramInt1)) / 2;
      ((View)localObject).setTouchDelegate(new TouchDelegate(new Rect(paramInt1 - paramInt5, paramInt2, paramInt5 + paramInt3, paramInt4), paramView));
    }
    localObject = paramView.getBackground();
    paramInt1 = paramInt3 - paramInt1;
    paramInt4 -= paramInt2;
    paramInt6 = paramView.getPaddingLeft() - paramView.getPaddingRight();
    paramInt5 = paramView.getPaddingTop() - paramView.getPaddingBottom();
    if ((localObject != null) && ((paramInt6 != 0) || (paramInt5 != 0)))
    {
      paramInt2 = paramInt1 / 2;
      paramInt3 = paramInt4 / 2;
      paramInt1 = (paramInt1 + paramInt6) / 2;
      paramInt4 = (paramInt4 + paramInt5) / 2;
      DrawableCompat.setHotspotBounds((Drawable)localObject, paramInt1 - paramInt2, paramInt4 - paramInt3, paramInt1 + paramInt2, paramInt4 + paramInt3);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.Toolbar.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.internal.widget.TintImageView;
import android.util.DisplayMetrics;
import android.view.TouchDelegate;
import android.view.View;

class ActionMenuPresenter$OverflowMenuButton
  extends TintImageView
  implements ActionMenuView.ActionMenuChildView
{
  private final float[] mTempPts;
  
  public ActionMenuPresenter$OverflowMenuButton(ActionMenuPresenter paramActionMenuPresenter, Context paramContext) {}
  
  public boolean needsDividerAfter()
  {
    return false;
  }
  
  public boolean needsDividerBefore()
  {
    return false;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    int i = (int)(getResourcesgetDisplayMetricsdensity * 52.0F);
    if (paramInt3 - paramInt1 < i)
    {
      i = (i - (paramInt3 - paramInt1)) / 2;
      Rect localRect = new Rect(paramInt1 - i, paramInt2, i + paramInt3, paramInt4);
      ((View)getParent()).setTouchDelegate(new TouchDelegate(localRect, this));
    }
  }
  
  public boolean performClick()
  {
    if (super.performClick()) {
      return true;
    }
    playSoundEffect(0);
    this$0.showOverflowMenu();
    return true;
  }
  
  protected boolean setFrame(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool = super.setFrame(paramInt1, paramInt2, paramInt3, paramInt4);
    Drawable localDrawable1 = getDrawable();
    Drawable localDrawable2 = getBackground();
    int i = getPaddingLeft() - getPaddingRight();
    paramInt3 = getPaddingTop() - getPaddingBottom();
    if ((localDrawable1 != null) && (localDrawable2 != null) && ((i != 0) || (paramInt3 != 0)))
    {
      int j = getWidth();
      paramInt4 = getHeight();
      paramInt1 = j / 2;
      paramInt2 = paramInt4 / 2;
      i = (j + i) / 2;
      paramInt3 = (paramInt4 + paramInt3) / 2;
      DrawableCompat.setHotspotBounds(localDrawable2, i - paramInt1, paramInt3 - paramInt2, i + paramInt1, paramInt3 + paramInt2);
    }
    return bool;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ActionMenuPresenter.OverflowMenuButton
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
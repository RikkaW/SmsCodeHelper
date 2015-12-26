package android.support.v7.internal.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

class ActionBarBackgroundDrawable
  extends Drawable
{
  final ActionBarContainer mContainer;
  
  public ActionBarBackgroundDrawable(ActionBarContainer paramActionBarContainer)
  {
    mContainer = paramActionBarContainer;
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (mContainer.mIsSplit) {
      if (mContainer.mSplitBackground != null) {
        mContainer.mSplitBackground.draw(paramCanvas);
      }
    }
    do
    {
      return;
      if (mContainer.mBackground != null) {
        mContainer.mBackground.draw(paramCanvas);
      }
    } while ((mContainer.mStackedBackground == null) || (!mContainer.mIsStacked));
    mContainer.mStackedBackground.draw(paramCanvas);
  }
  
  public int getOpacity()
  {
    return 0;
  }
  
  public void setAlpha(int paramInt) {}
  
  public void setColorFilter(ColorFilter paramColorFilter) {}
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ActionBarBackgroundDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
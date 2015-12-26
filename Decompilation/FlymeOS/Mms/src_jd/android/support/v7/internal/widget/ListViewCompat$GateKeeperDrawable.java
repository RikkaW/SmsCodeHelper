package android.support.v7.internal.widget;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.drawable.DrawableWrapper;

class ListViewCompat$GateKeeperDrawable
  extends DrawableWrapper
{
  private boolean mEnabled = true;
  
  public ListViewCompat$GateKeeperDrawable(Drawable paramDrawable)
  {
    super(paramDrawable);
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (mEnabled) {
      super.draw(paramCanvas);
    }
  }
  
  void setEnabled(boolean paramBoolean)
  {
    mEnabled = paramBoolean;
  }
  
  public void setHotspot(float paramFloat1, float paramFloat2)
  {
    if (mEnabled) {
      super.setHotspot(paramFloat1, paramFloat2);
    }
  }
  
  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (mEnabled) {
      super.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
  
  public boolean setState(int[] paramArrayOfInt)
  {
    if (mEnabled) {
      return super.setState(paramArrayOfInt);
    }
    return false;
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (mEnabled) {
      return super.setVisible(paramBoolean1, paramBoolean2);
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ListViewCompat.GateKeeperDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
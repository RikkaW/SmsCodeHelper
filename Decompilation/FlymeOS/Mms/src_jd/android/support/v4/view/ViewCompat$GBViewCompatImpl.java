package android.support.v4.view;

import android.view.View;

class ViewCompat$GBViewCompatImpl
  extends ViewCompat.EclairMr1ViewCompatImpl
{
  public int getOverScrollMode(View paramView)
  {
    return ViewCompatGingerbread.getOverScrollMode(paramView);
  }
  
  public void setOverScrollMode(View paramView, int paramInt)
  {
    ViewCompatGingerbread.setOverScrollMode(paramView, paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewCompat.GBViewCompatImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
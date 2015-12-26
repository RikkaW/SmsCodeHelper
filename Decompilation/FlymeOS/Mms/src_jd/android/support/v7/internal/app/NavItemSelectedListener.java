package android.support.v7.internal.app;

import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemSelectedListener;
import android.view.View;

class NavItemSelectedListener
  implements AdapterViewCompat.OnItemSelectedListener
{
  private final ActionBar.OnNavigationListener mListener;
  
  public NavItemSelectedListener(ActionBar.OnNavigationListener paramOnNavigationListener)
  {
    mListener = paramOnNavigationListener;
  }
  
  public void onItemSelected(AdapterViewCompat<?> paramAdapterViewCompat, View paramView, int paramInt, long paramLong)
  {
    if (mListener != null) {
      mListener.onNavigationItemSelected(paramInt, paramLong);
    }
  }
  
  public void onNothingSelected(AdapterViewCompat<?> paramAdapterViewCompat) {}
}

/* Location:
 * Qualified Name:     android.support.v7.internal.app.NavItemSelectedListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package android.support.v7.internal.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class AdapterViewCompat$OnItemClickListenerWrapper
  implements AdapterView.OnItemClickListener
{
  private final AdapterViewCompat.OnItemClickListener mWrappedListener;
  
  public AdapterViewCompat$OnItemClickListenerWrapper(AdapterViewCompat paramAdapterViewCompat, AdapterViewCompat.OnItemClickListener paramOnItemClickListener)
  {
    mWrappedListener = paramOnItemClickListener;
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    mWrappedListener.onItemClick(this$0, paramView, paramInt, paramLong);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.AdapterViewCompat.OnItemClickListenerWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
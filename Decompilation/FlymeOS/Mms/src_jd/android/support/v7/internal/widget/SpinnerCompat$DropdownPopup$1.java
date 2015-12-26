package android.support.v7.internal.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;

class SpinnerCompat$DropdownPopup$1
  implements AdapterView.OnItemClickListener
{
  SpinnerCompat$DropdownPopup$1(SpinnerCompat.DropdownPopup paramDropdownPopup, SpinnerCompat paramSpinnerCompat) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this$1.this$0.setSelection(paramInt);
    if (this$1.this$0.mOnItemClickListener != null) {
      this$1.this$0.performItemClick(paramView, paramInt, SpinnerCompat.DropdownPopup.access$300(this$1).getItemId(paramInt));
    }
    this$1.dismiss();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.SpinnerCompat.DropdownPopup.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
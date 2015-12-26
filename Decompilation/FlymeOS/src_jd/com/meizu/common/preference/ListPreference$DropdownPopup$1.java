package com.meizu.common.preference;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class ListPreference$DropdownPopup$1
  implements AdapterView.OnItemClickListener
{
  ListPreference$DropdownPopup$1(ListPreference.DropdownPopup paramDropdownPopup, ListPreference paramListPreference) {}
  
  public void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    ListPreference.access$002(this$1.this$0, paramInt);
    this$1.setSelection(ListPreference.access$000(this$1.this$0));
    this$1.dismiss();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.ListPreference.DropdownPopup.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
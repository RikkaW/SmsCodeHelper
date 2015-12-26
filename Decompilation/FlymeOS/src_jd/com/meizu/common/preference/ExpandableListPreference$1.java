package com.meizu.common.preference;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

class ExpandableListPreference$1
  implements AdapterView.OnItemClickListener
{
  ExpandableListPreference$1(ExpandableListPreference paramExpandableListPreference) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if ((this$0.iSAnimating()) || (ExpandableListPreference.access$000(this$0))) {
      return;
    }
    ExpandableListPreference.access$100(this$0).setSelectedPosition(paramInt);
    ExpandableListPreference.access$100(this$0).notifyDataSetChanged();
    if (ExpandableListPreference.access$200(this$0) != null)
    {
      paramAdapterView = ExpandableListPreference.access$200(this$0)[paramInt].toString();
      ExpandableListPreference.access$400(this$0).setText(ExpandableListPreference.access$300(this$0)[paramInt]);
      ExpandableListPreference.access$500(this$0, ExpandableListPreference.access$300(this$0)[paramInt]);
      if (ExpandableListPreference.access$600(this$0, paramAdapterView)) {
        this$0.setValue(paramAdapterView);
      }
    }
    ExpandableListPreference.access$800(this$0).postDelayed(ExpandableListPreference.access$700(this$0), 200L);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.ExpandableListPreference.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
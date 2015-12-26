package com.android.mms.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.android.mms.util.MiStatSdkHelper;

class SearchFragment$3
  implements AdapterView.OnItemClickListener
{
  SearchFragment$3(SearchFragment paramSearchFragment) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramInt -= SearchFragment.access$100(this$0).getHeaderViewsCount();
    if (paramInt >= 0)
    {
      paramAdapterView = SearchFragment.access$000(this$0).getIntent(paramInt);
      if (paramAdapterView != null)
      {
        MiStatSdkHelper.recordSearch("search_click_item");
        ComposeMessageRouterActivity.route(SearchFragment.access$200(this$0), paramAdapterView);
        SearchFragment.access$302(this$0, true);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SearchFragment.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
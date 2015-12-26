package com.android.mms.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.android.mms.util.EditableListView;

class SimMessagesFragment$1
  implements AdapterView.OnItemClickListener
{
  SimMessagesFragment$1(SimMessagesFragment paramSimMessagesFragment) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramInt -= SimMessagesFragment.access$100(this$0).getFirstVisiblePosition();
    if ((paramInt >= 0) && (paramInt < SimMessagesFragment.access$100(this$0).getChildCount()) && ((SimMessagesFragment.access$100(this$0).getChildAt(paramInt) instanceof MessageListItem))) {
      ((MessageListItem)SimMessagesFragment.access$100(this$0).getChildAt(paramInt)).onMessageListItemClick();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SimMessagesFragment.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
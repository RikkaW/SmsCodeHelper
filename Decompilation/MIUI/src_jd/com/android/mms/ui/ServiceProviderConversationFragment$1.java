package com.android.mms.ui;

import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import com.android.mms.data.Conversation;

class ServiceProviderConversationFragment$1
  implements AdapterView.OnItemClickListener
{
  ServiceProviderConversationFragment$1(ServiceProviderConversationFragment paramServiceProviderConversationFragment) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if ((paramInt == 0) && (ServiceProviderConversationFragment.access$000(this$0))) {
      ServiceProviderConversationFragment.access$100(this$0);
    }
    while (paramInt == this$0.mListView.getAdapter().getCount() - 1) {
      return;
    }
    paramAdapterView = (Cursor)this$0.mListView.getItemAtPosition(paramInt);
    paramLong = Conversation.from(this$0.mActivity, paramAdapterView).getThreadId();
    Log.d("ServiceProviderConversationFragment", "onListItemClick: pos=" + paramInt + ", view=" + paramView + ", tid=" + paramLong);
    paramAdapterView = ComposeMessageRouterActivity.createIntent(this$0.mActivity, paramLong);
    this$0.startActivity(paramAdapterView);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ServiceProviderConversationFragment.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
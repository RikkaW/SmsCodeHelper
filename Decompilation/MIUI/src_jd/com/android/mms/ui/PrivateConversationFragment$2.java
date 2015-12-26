package com.android.mms.ui;

import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import com.android.mms.data.Conversation;

class PrivateConversationFragment$2
  implements AdapterView.OnItemClickListener
{
  PrivateConversationFragment$2(PrivateConversationFragment paramPrivateConversationFragment) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (paramInt == 0) {
      ((PrivateConversationListActivity)this$0.mActivity).startSearchMode(this$0.mSearchView, this$0.mRootView);
    }
    while (paramInt == this$0.mListView.getAdapter().getCount() - 1) {
      return;
    }
    paramAdapterView = (Cursor)this$0.mListView.getItemAtPosition(paramInt);
    paramLong = Conversation.from(this$0.mActivity, paramAdapterView).getThreadId();
    Log.d("ConversationFragment", "onListItemClick: pos=" + paramInt + ", view=" + paramView + ", tid=" + paramLong);
    paramAdapterView = ComposeMessageRouterActivity.createIntent(this$0.mActivity, paramLong);
    this$0.startActivity(paramAdapterView);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PrivateConversationFragment.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
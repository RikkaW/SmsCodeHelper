package com.android.mms.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class MultipleRecipientsConversationActivity$1
  implements AdapterView.OnItemClickListener
{
  MultipleRecipientsConversationActivity$1(MultipleRecipientsConversationActivity paramMultipleRecipientsConversationActivity) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramInt -= this$0.mMsgListView.getFirstVisiblePosition();
    if ((paramInt >= 0) && (paramInt < this$0.mMsgListView.getChildCount()) && ((this$0.mMsgListView.getChildAt(paramInt) instanceof MessageListItem))) {
      ((MessageListItem)this$0.mMsgListView.getChildAt(paramInt)).onMessageListItemClick();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MultipleRecipientsConversationActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.android.mms.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class MultipleRecipientsConversationDetailsActivity$1
  implements AdapterView.OnItemClickListener
{
  MultipleRecipientsConversationDetailsActivity$1(MultipleRecipientsConversationDetailsActivity paramMultipleRecipientsConversationDetailsActivity) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if ((paramView != null) && ((paramView instanceof MessageListItem))) {
      ((MessageListItem)paramView).onMessageListItemClick();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MultipleRecipientsConversationDetailsActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
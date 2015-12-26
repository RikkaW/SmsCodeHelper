package com.android.mms.ui;

import android.view.View;
import android.widget.AbsListView.RecyclerListener;

class MessageListAdapter$1
  implements AbsListView.RecyclerListener
{
  MessageListAdapter$1(MessageListAdapter paramMessageListAdapter) {}
  
  public void onMovedToScrapHeap(View paramView)
  {
    if ((paramView instanceof MessageListItem)) {
      ((MessageListItem)paramView).unbind();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListAdapter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
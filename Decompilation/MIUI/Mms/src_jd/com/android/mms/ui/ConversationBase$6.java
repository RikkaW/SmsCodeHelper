package com.android.mms.ui;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

class ConversationBase$6
  implements AbsListView.OnScrollListener
{
  ConversationBase$6(ConversationBase paramConversationBase) {}
  
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    this$0.onMsgListViewScrollStateChanged(paramAbsListView, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
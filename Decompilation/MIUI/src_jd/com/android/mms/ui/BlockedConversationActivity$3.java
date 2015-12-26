package com.android.mms.ui;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import com.android.mms.util.EditableListView.OnItemDoubleClickListener;

class BlockedConversationActivity$3
  implements EditableListView.OnItemDoubleClickListener
{
  BlockedConversationActivity$3(BlockedConversationActivity paramBlockedConversationActivity) {}
  
  public void onDoubleClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    Log.v("TAG", "onDoubleClick " + paramInt);
    paramInt -= this$0.mMsgListView.getFirstVisiblePosition();
    if ((paramInt >= 0) && (paramInt < this$0.mMsgListView.getChildCount()) && ((this$0.mMsgListView.getChildAt(paramInt) instanceof MessageListItem))) {
      ((MessageListItem)this$0.mMsgListView.getChildAt(paramInt)).onMessageListItemDoubleClick();
    }
  }
  
  public void onSingleClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramInt -= this$0.mMsgListView.getFirstVisiblePosition();
    if ((paramInt >= 0) && (paramInt < this$0.mMsgListView.getChildCount()) && ((this$0.mMsgListView.getChildAt(paramInt) instanceof MessageListItem))) {
      ((MessageListItem)this$0.mMsgListView.getChildAt(paramInt)).onMessageListItemClick();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BlockedConversationActivity.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
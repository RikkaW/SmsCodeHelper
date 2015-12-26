package com.android.mms.ui;

import android.content.res.Resources;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MessageListItem$GroupSendViewStubController
{
  public TextView mGroupSendCountTextView;
  public TextView mGroupSendToTextView;
  public TextView mGroupSendToTitle;
  public LinearLayout mGroupSendViewContainer;
  
  public MessageListItem$GroupSendViewStubController(MessageListItem paramMessageListItem) {}
  
  public void refreshSendView(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      int i = (int)this$0.getResources().getDimension(2131427421);
      mGroupSendViewContainer.setPadding(0, 0, i, 0);
      mGroupSendToTitle.setTextColor(this$0.getResources().getColorStateList(2131296351));
      mGroupSendToTextView.setTextColor(this$0.getResources().getColorStateList(2131296351));
      mGroupSendCountTextView.setTextColor(this$0.getResources().getColorStateList(2131296351));
      return;
    }
    mGroupSendViewContainer.setPadding(0, 0, 0, 0);
    mGroupSendToTitle.setTextColor(this$0.getResources().getColorStateList(2131296360));
    mGroupSendToTextView.setTextColor(this$0.getResources().getColorStateList(2131296359));
    mGroupSendCountTextView.setTextColor(this$0.getResources().getColorStateList(2131296359));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListItem.GroupSendViewStubController
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
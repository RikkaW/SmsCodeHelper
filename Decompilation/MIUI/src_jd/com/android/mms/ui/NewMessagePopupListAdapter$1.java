package com.android.mms.ui;

class NewMessagePopupListAdapter$1
  implements MessageListAdapter.GetMsgItem
{
  NewMessagePopupListAdapter$1(NewMessagePopupListAdapter paramNewMessagePopupListAdapter) {}
  
  public MessageItem getCurrMessageItem(int paramInt)
  {
    return NewMessagePopupListAdapter.access$000(this$0, paramInt);
  }
  
  public MessageItem getNextMessageItem(int paramInt)
  {
    return NewMessagePopupListAdapter.access$000(this$0, paramInt + 1);
  }
  
  public MessageItem getPreMessageItem(int paramInt)
  {
    return NewMessagePopupListAdapter.access$000(this$0, paramInt - 1);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessagePopupListAdapter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
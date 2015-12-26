package com.android.mms.ui;

class MessageEditableActivityBase$25
  implements Runnable
{
  MessageEditableActivityBase$25(MessageEditableActivityBase paramMessageEditableActivityBase, boolean paramBoolean) {}
  
  public void run()
  {
    if (val$show)
    {
      this$0.showSoftKeyboard();
      return;
    }
    this$0.hideSoftKeyboard();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.25
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
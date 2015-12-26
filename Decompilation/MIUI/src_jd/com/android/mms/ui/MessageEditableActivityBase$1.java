package com.android.mms.ui;

import android.view.View;
import miui.app.OnStatusBarChangeListener;

class MessageEditableActivityBase$1
  implements OnStatusBarChangeListener
{
  MessageEditableActivityBase$1(MessageEditableActivityBase paramMessageEditableActivityBase) {}
  
  public void onStatusBarHeightChange(int paramInt)
  {
    if (this$0.mStatusBarHeight == paramInt) {
      return;
    }
    this$0.mContactPanel.setPadding(this$0.mContactPanel.getPaddingLeft(), this$0.mContactPanel.getPaddingTop() - this$0.mStatusBarHeight + paramInt, this$0.mContactPanel.getPaddingRight(), this$0.mContactPanel.getPaddingBottom());
    this$0.mStatusBarHeight = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
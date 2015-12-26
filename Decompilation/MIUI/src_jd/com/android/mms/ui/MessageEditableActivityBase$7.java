package com.android.mms.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

class MessageEditableActivityBase$7
  implements View.OnClickListener
{
  MessageEditableActivityBase$7(MessageEditableActivityBase paramMessageEditableActivityBase) {}
  
  public void onClick(View paramView)
  {
    if (this$0.isVisible(this$0.mTextEditor))
    {
      this$0.mTextEditor.requestFocus();
      this$0.mTextEditor.setSelection(0);
      this$0.showSoftKeyboard();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
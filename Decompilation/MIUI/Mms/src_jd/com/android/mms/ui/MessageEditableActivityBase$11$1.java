package com.android.mms.ui;

import android.widget.EditText;

class MessageEditableActivityBase$11$1
  implements Runnable
{
  MessageEditableActivityBase$11$1(MessageEditableActivityBase.11 param11, String paramString, int paramInt1, int paramInt2) {}
  
  public void run()
  {
    this$1.this$0.mTextEditor.setText(val$oldText);
    this$1.this$0.mTextEditor.setSelection(val$oldStart, val$oldEnd);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.11.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
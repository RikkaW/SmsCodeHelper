package com.android.mms.ui;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import com.android.mms.data.WorkingMessage;

class MessageEditableActivityBase$20
  implements View.OnKeyListener
{
  MessageEditableActivityBase$20(MessageEditableActivityBase paramMessageEditableActivityBase) {}
  
  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() != 0) {}
    while ((paramInt != 67) || (this$0.mSubjectTextEditor.length() != 0)) {
      return false;
    }
    MessageEditableActivityBase.access$1200(this$0, false);
    if (this$0.mTextEditor != null) {
      this$0.mTextEditor.requestFocus();
    }
    this$0.mWorkingMessage.setSubject(null, true);
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.20
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
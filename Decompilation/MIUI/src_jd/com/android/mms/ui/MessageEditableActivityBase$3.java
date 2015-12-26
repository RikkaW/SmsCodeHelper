package com.android.mms.ui;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

class MessageEditableActivityBase$3
  implements TextView.OnEditorActionListener
{
  MessageEditableActivityBase$3(MessageEditableActivityBase paramMessageEditableActivityBase) {}
  
  public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (this$0.isPreparedForSending())
      {
        this$0.sendMessage(this$0.mUseSlotId);
        return true;
      }
      Toast.makeText(this$0, 2131361865, 1).show();
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
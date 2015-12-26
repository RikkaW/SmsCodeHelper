package com.android.mms.ui;

import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

class AttachmentProcessor$5
  implements View.OnClickListener
{
  AttachmentProcessor$5(AttachmentProcessor paramAttachmentProcessor, String paramString) {}
  
  public void onClick(View paramView)
  {
    paramView = AttachmentProcessor.access$100(this$0).getEditMessageFocus();
    int i = paramView.getSelectionStart();
    paramView.getText().insert(i, val$smileyString);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentProcessor.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
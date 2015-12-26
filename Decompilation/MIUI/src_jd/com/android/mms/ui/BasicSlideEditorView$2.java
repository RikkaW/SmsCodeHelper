package com.android.mms.ui;

import android.text.Editable;
import android.text.TextWatcher;

class BasicSlideEditorView$2
  implements TextWatcher
{
  BasicSlideEditorView$2(BasicSlideEditorView paramBasicSlideEditorView) {}
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((BasicSlideEditorView.access$100(this$0)) && (BasicSlideEditorView.access$200(this$0) != null)) {
      BasicSlideEditorView.access$200(this$0).onTextChanged(paramCharSequence.toString());
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BasicSlideEditorView.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
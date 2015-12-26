package com.android.mms.ui;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;
import com.android.mms.data.WorkingMessage;

class MessageEditableActivityBase$21
  implements TextWatcher
{
  private boolean mIsEnglish = true;
  private boolean mNested = false;
  private CharSequence mOldText = null;
  private int mSelectionEnd = 0;
  private Toast mToast = null;
  
  MessageEditableActivityBase$21(MessageEditableActivityBase paramMessageEditableActivityBase) {}
  
  private boolean isTooLong(CharSequence paramCharSequence)
  {
    boolean bool2 = false;
    mIsEnglish = true;
    int i = 0;
    while (i < paramCharSequence.length())
    {
      if (paramCharSequence.charAt(i) > '') {
        mIsEnglish = false;
      }
      i += 1;
    }
    boolean bool1;
    if ((mIsEnglish) || (paramCharSequence.length() <= 13))
    {
      bool1 = bool2;
      if (mIsEnglish)
      {
        bool1 = bool2;
        if (paramCharSequence.length() <= 40) {}
      }
    }
    else
    {
      bool1 = true;
    }
    return bool1;
  }
  
  public void afterTextChanged(Editable paramEditable)
  {
    if (mNested) {
      return;
    }
    int i;
    int j;
    if (isTooLong(paramEditable))
    {
      if (!mIsEnglish) {
        break label235;
      }
      i = 2131362141;
      j = 40;
      label29:
      if (mToast != null) {
        break label244;
      }
      mToast = Toast.makeText(this$0, i, 1);
    }
    for (;;)
    {
      mToast.show();
      mNested = true;
      Object localObject2 = "";
      i = 0;
      Object localObject1 = localObject2;
      if (!TextUtils.isEmpty(mOldText))
      {
        int k = mOldText.length();
        i = k - mSelectionEnd;
        if (i > 0) {
          localObject2 = mOldText.subSequence(mSelectionEnd, k);
        }
        localObject1 = localObject2;
        if (TextUtils.isEmpty((CharSequence)localObject2))
        {
          localObject1 = "";
          i = 0;
        }
      }
      i = j - i;
      paramEditable = paramEditable.subSequence(0, i);
      this$0.mSubjectTextEditor.setText(paramEditable.toString() + localObject1.toString());
      this$0.mSubjectTextEditor.setSelection(i);
      mNested = false;
      if (!this$0.isSubjectEditorVisible()) {
        break;
      }
      this$0.mWorkingMessage.setSubject(this$0.mSubjectTextEditor.getText(), true);
      return;
      label235:
      i = 2131362140;
      j = 13;
      break label29;
      label244:
      mToast.setText(i);
    }
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if (mNested) {
      return;
    }
    mOldText = paramCharSequence.subSequence(0, paramCharSequence.length());
    mSelectionEnd = (paramInt1 + paramInt2);
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.21
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
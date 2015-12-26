package com.meizu.common.preference;

import android.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

class EditPhoneNumberPreference$1
  implements TextWatcher
{
  EditPhoneNumberPreference$1(EditPhoneNumberPreference paramEditPhoneNumberPreference) {}
  
  private void enableButton(boolean paramBoolean)
  {
    AlertDialog localAlertDialog = (AlertDialog)this$0.getDialog();
    if (localAlertDialog != null)
    {
      if (EditPhoneNumberPreference.access$000(this$0) != 1) {
        break label57;
      }
      if (EditPhoneNumberPreference.access$100(this$0)) {
        localAlertDialog.getButton(-1).setEnabled(paramBoolean);
      }
    }
    else
    {
      return;
    }
    localAlertDialog.getButton(-3).setEnabled(paramBoolean);
    return;
    label57:
    localAlertDialog.getButton(-1).setEnabled(paramBoolean);
  }
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramCharSequence.length() == 0) && (!this$0.isEmptyAllow()))
    {
      enableButton(false);
      return;
    }
    enableButton(true);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.EditPhoneNumberPreference.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
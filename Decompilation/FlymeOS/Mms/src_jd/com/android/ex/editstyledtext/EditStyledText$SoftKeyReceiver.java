package com.android.ex.editstyledtext;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.Selection;

class EditStyledText$SoftKeyReceiver
  extends ResultReceiver
{
  int a;
  int b;
  EditStyledText c;
  
  protected void onReceiveResult(int paramInt, Bundle paramBundle)
  {
    if (paramInt != 2) {
      Selection.setSelection(c.getText(), a, b);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.ex.editstyledtext.EditStyledText.SoftKeyReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.android.mms.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import miui.app.ProgressDialog;

public class ProgressDialogFragment
  extends DialogFragment
{
  private String mMessage;
  
  public ProgressDialogFragment(String paramString)
  {
    mMessage = paramString;
  }
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    paramBundle = new ProgressDialog(getActivity());
    paramBundle.setMessage(mMessage);
    return paramBundle;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ProgressDialogFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
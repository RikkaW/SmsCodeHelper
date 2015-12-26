package com.android.mms.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import miui.app.ProgressDialog;

public class AttachmentProcessor$ProgressDialogFragment
  extends DialogFragment
{
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    paramBundle = getActivity();
    ProgressDialog localProgressDialog = new ProgressDialog(paramBundle);
    localProgressDialog.setMessage(paramBundle.getString(2131362161));
    return localProgressDialog;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentProcessor.ProgressDialogFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
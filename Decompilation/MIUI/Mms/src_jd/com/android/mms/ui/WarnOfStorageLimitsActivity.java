package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import com.android.internal.app.AlertController;
import com.android.internal.app.AlertController.AlertParams;
import miui.app.Activity;

public class WarnOfStorageLimitsActivity
  extends Activity
  implements DialogInterface, DialogInterface.OnClickListener
{
  protected AlertController mAlert;
  protected AlertController.AlertParams mAlertParams;
  
  public void cancel()
  {
    finish();
  }
  
  public void dismiss()
  {
    if (!isFinishing()) {
      finish();
    }
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (paramInt == -1) {
      startActivity(new Intent(this, MessagingPreferenceActivity.class));
    }
    paramDialogInterface.dismiss();
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(16973939);
    super.onCreate(paramBundle);
    mAlert = new AlertController(this, this, getWindow());
    mAlertParams = new AlertController.AlertParams(this);
    paramBundle = mAlertParams;
    mMessage = getString(2131362033);
    mPositiveButtonText = getString(2131362034);
    mNegativeButtonText = getString(2131362035);
    mPositiveButtonListener = this;
    setupAlert();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (mAlert.onKeyDown(paramInt, paramKeyEvent)) {
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (mAlert.onKeyUp(paramInt, paramKeyEvent)) {
      return true;
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  protected void setupAlert()
  {
    mAlertParams.apply(mAlert);
    mAlert.installContent();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.WarnOfStorageLimitsActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
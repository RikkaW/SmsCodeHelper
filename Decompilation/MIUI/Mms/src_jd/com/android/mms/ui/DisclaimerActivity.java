package com.android.mms.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.System;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class DisclaimerActivity
  extends Activity
  implements DialogInterface.OnClickListener
{
  public String mStopPackageName;
  
  private void forceStopPackage()
  {
    if (!TextUtils.isEmpty(mStopPackageName)) {
      ((ActivityManager)getSystemService("activity")).forceStopPackage(mStopPackageName);
    }
  }
  
  public void onBackPressed()
  {
    setResult(0);
    forceStopPackage();
    finish();
    super.onBackPressed();
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (paramInt == -1)
    {
      Settings.System.putInt(getContentResolver(), "mobile_policy", 1);
      setResult(1);
    }
    while (paramInt != -2) {
      return;
    }
    forceStopPackage();
    setResult(0);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mStopPackageName = getIntent().getStringExtra("stop_package_name");
    Object localObject = getString(2131362292);
    String str1 = getString(2131362293);
    String str2 = getString(2131362294);
    String str3 = getString(2131362295);
    String str4 = getString(2131362296);
    paramBundle = new SpannableStringBuilder();
    paramBundle.append((String)localObject + str1 + str2 + str3 + str4);
    paramBundle.setSpan(new IntentSpan(2), ((String)localObject).length(), ((String)localObject).length() + str1.length(), 33);
    paramBundle.setSpan(new IntentSpan(1), ((String)localObject).length() + str1.length() + str2.length(), ((String)localObject).length() + str1.length() + str2.length() + str3.length(), 33);
    localObject = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject).setPositiveButton(17039370, this);
    ((AlertDialog.Builder)localObject).setNeutralButton(17039360, this);
    ((AlertDialog.Builder)localObject).setMessage(paramBundle);
    paramBundle = ((AlertDialog.Builder)localObject).show();
    paramBundle.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        finish();
      }
    });
    ((TextView)paramBundle.getWindow().findViewById(16908299)).setMovementMethod(LinkMovementMethod.getInstance());
  }
  
  class IntentSpan
    extends ClickableSpan
  {
    private int mLicenseType;
    
    public IntentSpan(int paramInt)
    {
      mLicenseType = paramInt;
    }
    
    public void onClick(View paramView)
    {
      paramView = new Intent("android.intent.action.VIEW_LICENSE");
      paramView.putExtra("android.intent.extra.LICENSE_TYPE", mLicenseType);
      startActivity(paramView);
    }
    
    public void updateDrawState(TextPaint paramTextPaint)
    {
      paramTextPaint.setUnderlineText(true);
      paramTextPaint.setColor(-16776961);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.DisclaimerActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.android.mms.ui;

import android.content.Intent;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

class DisclaimerActivity$IntentSpan
  extends ClickableSpan
{
  private int mLicenseType;
  
  public DisclaimerActivity$IntentSpan(DisclaimerActivity paramDisclaimerActivity, int paramInt)
  {
    mLicenseType = paramInt;
  }
  
  public void onClick(View paramView)
  {
    paramView = new Intent("android.intent.action.VIEW_LICENSE");
    paramView.putExtra("android.intent.extra.LICENSE_TYPE", mLicenseType);
    this$0.startActivity(paramView);
  }
  
  public void updateDrawState(TextPaint paramTextPaint)
  {
    paramTextPaint.setUnderlineText(true);
    paramTextPaint.setColor(-16776961);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.DisclaimerActivity.IntentSpan
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
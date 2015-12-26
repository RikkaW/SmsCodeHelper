package com.xiaomi.mms.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.xiaomi.mms.utils.logger.MyLog;

class MxActivateSimpleDialog$VoipIntentSpan
  extends ClickableSpan
{
  MxActivateSimpleDialog$VoipIntentSpan(MxActivateSimpleDialog paramMxActivateSimpleDialog) {}
  
  public void onClick(View paramView)
  {
    try
    {
      paramView = new Intent("com.miui.voip.action.VOIP_LICENSE");
      MxActivateSimpleDialog.access$000(this$0).startActivity(paramView);
      return;
    }
    catch (ActivityNotFoundException paramView)
    {
      MyLog.e("MxActivateSimpleDialog", paramView);
    }
  }
  
  public void updateDrawState(TextPaint paramTextPaint)
  {
    paramTextPaint.setUnderlineText(true);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.MxActivateSimpleDialog.VoipIntentSpan
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
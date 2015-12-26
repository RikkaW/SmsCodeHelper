package com.android.mms.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import miui.R.id;
import miui.app.AlertDialog;
import miui.app.AlertDialog.Builder;

public class AlertDialogHelper
{
  private static Intent getPrivacyIntent()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW_LICENSE");
    localIntent.putExtra("android.intent.extra.LICENSE_TYPE", 1);
    return localIntent;
  }
  
  private static Intent getUserAgreementIntent()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW_LICENSE");
    localIntent.putExtra("android.intent.extra.LICENSE_TYPE", 2);
    return localIntent;
  }
  
  @SuppressLint({"InflateParams"})
  public static AlertDialog showUserNoticeDialog(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2)
  {
    if (paramInt2 < 0) {
      return null;
    }
    Object localObject = paramContext.getResources();
    String str1 = ((Resources)localObject).getString(2131362293);
    String str2 = ((Resources)localObject).getString(2131362295);
    localObject = ((Resources)localObject).getString(paramInt2, new Object[] { str1, str2 });
    AlertDialogHelper.UrlSpan.UrlSpanOnClickListener local1 = new AlertDialogHelper.UrlSpan.UrlSpanOnClickListener()
    {
      public void onClick()
      {
        val$context.startActivity(AlertDialogHelper.access$000());
      }
    };
    AlertDialogHelper.UrlSpan.UrlSpanOnClickListener local2 = new AlertDialogHelper.UrlSpan.UrlSpanOnClickListener()
    {
      public void onClick()
      {
        val$context.startActivity(AlertDialogHelper.access$100());
      }
    };
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder((CharSequence)localObject);
    paramInt2 = ((String)localObject).indexOf(str1);
    localSpannableStringBuilder.setSpan(new UrlSpan(local1), paramInt2, str1.length() + paramInt2, 33);
    paramInt2 = ((String)localObject).indexOf(str2);
    localSpannableStringBuilder.setSpan(new UrlSpan(local2), paramInt2, str2.length() + paramInt2, 33);
    paramInt2 = ((String)localObject).indexOf(str2);
    localSpannableStringBuilder.setSpan(new UrlSpan(local2), paramInt2, str2.length() + paramInt2, 33);
    if (paramInt3 == 0)
    {
      paramInt2 = 17039370;
      if (paramInt4 != 0) {
        break label265;
      }
    }
    label265:
    for (paramInt3 = 17039360;; paramInt3 = paramInt4)
    {
      paramContext = new AlertDialog.Builder(paramContext).setMessage(localSpannableStringBuilder).setCancelable(false).setNegativeButton(paramInt3, paramOnClickListener2).setPositiveButton(paramInt2, paramOnClickListener1);
      if (paramInt1 > 0) {
        paramContext.setTitle(paramInt1);
      }
      paramContext = paramContext.show();
      ((TextView)paramContext.getWindow().findViewById(R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
      return paramContext;
      paramInt2 = paramInt3;
      break;
    }
  }
  
  public static class UrlSpan
    extends ClickableSpan
  {
    private UrlSpanOnClickListener mOnClickListener;
    
    public UrlSpan(UrlSpanOnClickListener paramUrlSpanOnClickListener)
    {
      mOnClickListener = paramUrlSpanOnClickListener;
    }
    
    public void onClick(View paramView)
    {
      if (mOnClickListener != null) {
        mOnClickListener.onClick();
      }
    }
    
    public void updateDrawState(TextPaint paramTextPaint)
    {
      paramTextPaint.setUnderlineText(true);
      paramTextPaint.setColor(-16776961);
    }
    
    public static abstract interface UrlSpanOnClickListener
    {
      public abstract void onClick();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AlertDialogHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
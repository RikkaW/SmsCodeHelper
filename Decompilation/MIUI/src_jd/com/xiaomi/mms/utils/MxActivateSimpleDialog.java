package com.xiaomi.mms.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.android.mms.util.MSimUtils;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.utils.logger.MyLog;
import miui.app.AlertDialog;
import miui.app.AlertDialog.Builder;

public class MxActivateSimpleDialog
{
  private ActivationCallBack mActivationCallBack;
  private AlertDialog mAlertDialog;
  private Context mContext;
  private DialogInterface.OnCancelListener mDialogCancelListener = new DialogInterface.OnCancelListener()
  {
    public void onCancel(DialogInterface paramAnonymousDialogInterface)
    {
      if (mActivationCallBack != null) {
        mActivationCallBack.onResult(1);
      }
    }
  };
  private boolean mHasVoip;
  private CharSequence mMessage;
  private DialogInterface.OnClickListener mMxAndVoipEnableOnClickListener = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      MxActivateService.enableAll(mContext, true);
      paramAnonymousDialogInterface = new Intent("com.miui.voip.action.TURN_ON_VOIP");
      paramAnonymousDialogInterface.putExtra("extra_turn_on_voip_from", 2);
      mContext.sendBroadcast(paramAnonymousDialogInterface);
    }
  };
  private DialogInterface.OnClickListener mMxEnableOnClickListener = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      MxActivateService.enableAll(mContext, true);
    }
  };
  private DialogInterface.OnClickListener mNegativeButtonCancelListener = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      if (mActivationCallBack != null) {
        mActivationCallBack.onResult(1);
      }
    }
  };
  private DialogInterface.OnClickListener mPhoneActivateOnClickListener = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      int i = MSimUtils.getMultiSimCount();
      if (mSimIndex != -1)
      {
        i = mSimIndex;
        paramAnonymousInt = i;
        paramAnonymousDialogInterface = ActivateManager.get(mContext);
        label36:
        if (paramAnonymousInt > i) {
          break label88;
        }
        if (!mHasVoip) {
          break label82;
        }
      }
      label82:
      for (int j = 17;; j = 1)
      {
        paramAnonymousDialogInterface.startActivateSim(paramAnonymousInt, 2, null, false, null, j);
        paramAnonymousInt += 1;
        break label36;
        paramAnonymousInt = 0;
        i -= 1;
        break;
      }
      label88:
      if (mActivationCallBack != null) {
        mActivationCallBack.onResult(0);
      }
    }
  };
  private int mSimIndex;
  private String mSource;
  private String mTitle;
  private int mType;
  private DialogInterface.OnClickListener mVoipEnableOnClickListener = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      paramAnonymousDialogInterface = new Intent("com.miui.voip.action.TURN_ON_VOIP");
      paramAnonymousDialogInterface.putExtra("extra_turn_on_voip_from", 2);
      mContext.sendBroadcast(paramAnonymousDialogInterface);
    }
  };
  
  public MxActivateSimpleDialog(Context paramContext, int paramInt1, String paramString, int paramInt2)
  {
    this(paramContext, paramInt1, paramString, paramInt2, false);
  }
  
  public MxActivateSimpleDialog(Context paramContext, int paramInt1, String paramString, int paramInt2, boolean paramBoolean)
  {
    mContext = paramContext;
    mType = paramInt1;
    mSource = paramString;
    mSimIndex = paramInt2;
    mHasVoip = paramBoolean;
    initRes(paramInt1, paramString, paramBoolean);
  }
  
  private void create()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(mContext);
    localBuilder.setTitle(mTitle).setMessage(mMessage).setPositiveButton(2131362206, getClickListener()).setNegativeButton(17039360, mNegativeButtonCancelListener).setOnCancelListener(mDialogCancelListener);
    mAlertDialog = localBuilder.create();
  }
  
  private DialogInterface.OnClickListener getClickListener()
  {
    if (mType == 1) {
      return mMxEnableOnClickListener;
    }
    if (mType == 2) {
      return mVoipEnableOnClickListener;
    }
    if (mType == 3) {
      return mMxAndVoipEnableOnClickListener;
    }
    return mPhoneActivateOnClickListener;
  }
  
  private SpannableStringBuilder getVoipSpannableStringBuilderMessage(String paramString1, String paramString2)
  {
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
    localSpannableStringBuilder.append(paramString1);
    int i = paramString1.indexOf(paramString2);
    localSpannableStringBuilder.setSpan(new VoipIntentSpan(), i, paramString2.length() + i, 17);
    return localSpannableStringBuilder;
  }
  
  private void initRes(int paramInt, String paramString, boolean paramBoolean)
  {
    Resources localResources = mContext.getResources();
    if (paramInt == 1)
    {
      mTitle = localResources.getString(2131362164);
      mMessage = localResources.getString(2131362205);
      return;
    }
    if (paramInt == 3)
    {
      mTitle = localResources.getString(2131362397);
      mMessage = getVoipSpannableStringBuilderMessage(localResources.getString(2131362398), localResources.getString(2131362399));
      return;
    }
    if (paramInt == 2)
    {
      mTitle = localResources.getString(2131362394);
      mMessage = getVoipSpannableStringBuilderMessage(localResources.getString(2131362395), localResources.getString(2131362396));
      return;
    }
    if ((paramBoolean) && ("Mms_Reminder".equals(paramString)))
    {
      mTitle = localResources.getString(2131362347);
      mMessage = getVoipSpannableStringBuilderMessage(localResources.getString(2131362344), localResources.getString(2131362345));
      return;
    }
    mTitle = localResources.getString(2131362346);
    mMessage = localResources.getString(2131362343);
  }
  
  public void setActivationCallBack(ActivationCallBack paramActivationCallBack)
  {
    mActivationCallBack = paramActivationCallBack;
  }
  
  public void show()
  {
    if (mAlertDialog == null) {
      create();
    }
    mAlertDialog.show();
    mAlertDialog.getMessageView().setMovementMethod(LinkMovementMethod.getInstance());
  }
  
  public static abstract interface ActivationCallBack
  {
    public abstract void onResult(int paramInt);
  }
  
  class VoipIntentSpan
    extends ClickableSpan
  {
    VoipIntentSpan() {}
    
    public void onClick(View paramView)
    {
      try
      {
        paramView = new Intent("com.miui.voip.action.VOIP_LICENSE");
        mContext.startActivity(paramView);
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
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.MxActivateSimpleDialog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
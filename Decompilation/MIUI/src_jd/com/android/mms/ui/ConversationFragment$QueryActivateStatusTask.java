package com.android.mms.ui;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.MiuiSettings.MiuiVoip;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.transaction.MxActivateService;

class ConversationFragment$QueryActivateStatusTask
  extends AsyncTask<Void, Void, Integer>
{
  private Activity mActivity;
  private boolean mHasVoip;
  private boolean mVoipHasActivated;
  
  public ConversationFragment$QueryActivateStatusTask(Activity paramActivity)
  {
    mActivity = paramActivity;
    mHasVoip = false;
    mVoipHasActivated = false;
  }
  
  protected Integer doInBackground(Void... paramVarArgs)
  {
    mHasVoip = MessageUtils.hasVoipPackage(mActivity);
    mVoipHasActivated = MiuiSettings.MiuiVoip.isVoipActivated(mActivity);
    return Integer.valueOf(MxActivateService.getSimActivateStatus(mActivity));
  }
  
  protected void onPostExecute(Integer paramInteger)
  {
    if (paramInteger.intValue() == 3)
    {
      if (MessageUtils.isMxDisabledBySlotId(mActivity, 0)) {
        MxActivateService.enableMx(mActivity, 0, true, true);
      }
      if ((MSimUtils.isMSim()) && (MessageUtils.isMxDisabledBySlotId(mActivity, 1))) {
        MxActivateService.enableMx(mActivity, 1, true, true);
      }
      if ((mHasVoip) && (!mVoipHasActivated))
      {
        paramInteger = new Intent("com.miui.voip.action.TURN_ON_VOIP");
        paramInteger.putExtra("extra_turn_on_voip_from", 2);
        mActivity.sendBroadcast(paramInteger);
      }
    }
    while (paramInteger.intValue() != 1) {
      return;
    }
    new AlertDialog.Builder(mActivity).setTitle(2131362401).setCancelable(false).setMessage(2131362404).setPositiveButton(2131362403, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MessageUtils.activatePhone(mActivity, mHasVoip);
      }
    }).setNegativeButton(2131361892, null).show();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.QueryActivateStatusTask
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
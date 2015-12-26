package com.android.mms.transaction;

import android.content.Context;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import com.android.mms.LogTag;
import com.android.mms.util.MSimUtils;
import miui.telephony.TelephonyManager;

class MmsSystemEventReceiver$MSimPhoneStateListener
  extends PhoneStateListener
{
  private int DELAY_TIME = 10000;
  private Context mContext;
  private Runnable mDelaySend = new Runnable()
  {
    public void run()
    {
      LogTag.verbose("send queued message without toast", new Object[0]);
      MSimUtils.sendQueuedMessageNoToast(mContext, mSlotId);
    }
  };
  private Handler mHandler;
  private boolean mRegisterd;
  private int mSlotId;
  
  public MmsSystemEventReceiver$MSimPhoneStateListener(MmsSystemEventReceiver paramMmsSystemEventReceiver, Context paramContext, int paramInt, Handler paramHandler)
  {
    mContext = paramContext;
    mSlotId = paramInt;
    mHandler = paramHandler;
    mRegisterd = false;
  }
  
  private void unRegister()
  {
    if (MSimUtils.isMSimSlotIdValid(mSlotId))
    {
      TelephonyManager.getDefault().listenForSlot(mSlotId, this, 0);
      mHandler.removeCallbacks(mDelaySend);
    }
  }
  
  public void onServiceStateChanged(ServiceState paramServiceState)
  {
    if (paramServiceState.getState() == 0)
    {
      LogTag.verbose("on service state is in service", new Object[0]);
      if (mRegisterd)
      {
        unRegister();
        mHandler.postDelayed(mDelaySend, DELAY_TIME);
      }
      mRegisterd = false;
    }
  }
  
  public void register()
  {
    if ((MSimUtils.isMSimSlotIdValid(mSlotId)) && (!mRegisterd))
    {
      TelephonyManager.getDefault().listenForSlot(mSlotId, this, 1);
      mRegisterd = true;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MmsSystemEventReceiver.MSimPhoneStateListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
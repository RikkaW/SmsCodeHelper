package com.android.mms.ui;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.mms.MmsApp;
import miui.cloud.util.SyncAlertHelper;

class ConversationFragment$4
  extends Handler
{
  ConversationFragment$4(ConversationFragment paramConversationFragment, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    Log.v("ConversationFragment", "handle msg on Worker thread, msg: " + what);
    switch (what)
    {
    }
    do
    {
      return;
      boolean bool = SyncAlertHelper.isNeedAlert(MmsApp.getApp().getApplicationContext(), "sms");
      Log.v("ConversationFragment", "isNeedAlert ? " + bool);
      paramMessage = new Message();
      what = 1002;
      obj = Boolean.valueOf(bool);
      ConversationFragment.access$600(this$0).sendMessage(paramMessage);
      return;
      if ((MessageUtils.isNetworkRecommendDateLong(this$0.mActivity)) && (MessageUtils.isSmartMessageNotReady(this$0.mActivity)))
      {
        paramMessage = new Message();
        what = 1006;
        ConversationFragment.access$600(this$0).sendMessage(paramMessage);
        return;
      }
    } while ((!MessageUtils.isYpRecommendEnabled(this$0.mActivity)) || (!MessageUtils.isSmartMessageNotReady(this$0.mActivity)));
    paramMessage = new Message();
    what = 1007;
    ConversationFragment.access$600(this$0).sendMessage(paramMessage);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
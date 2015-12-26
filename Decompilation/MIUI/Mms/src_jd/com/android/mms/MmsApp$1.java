package com.android.mms;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import com.android.mms.transaction.TimedMessageReceiver;
import com.android.mms.transaction.TransactionService;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.TimedMessageExpiredActivity;
import com.android.mms.understand.UnderstandLoader;
import com.android.mms.update.Push;
import com.android.mms.update.UpdateManager;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.MiStatSdkHelper;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.MxMessageTrackService;

class MmsApp$1
  implements Runnable
{
  MmsApp$1(MmsApp paramMmsApp) {}
  
  public void run()
  {
    Application localApplication = MmsApp.getApp();
    Push.initiate(localApplication);
    UpdateManager.init();
    MiStatSdkHelper.start(localApplication);
    UnderstandLoader.prepare();
    if (MessageUtils.isMessagingTemplateAllowed(localApplication))
    {
      MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_setting", "1");
      UnderstandLoader.init();
    }
    for (;;)
    {
      new Thread()
      {
        public void run()
        {
          Application localApplication = MmsApp.getApp();
          TimedMessageReceiver.scheduleNextTimedMsg(localApplication);
          TimedMessageExpiredActivity.markPrivateExpiredMsgFailed(localApplication);
          TimedMessageExpiredActivity.raiseIfNecessary(localApplication);
        }
      }.start();
      this$0.startService(new Intent(localApplication, TransactionService.class));
      MSimUtils.sendQueuedMessageNoToast(localApplication, MSimUtils.SLOT_ID_ALL);
      MxActivateService.enableAll(localApplication, false);
      MxMessageTrackService.startTrack(localApplication);
      MmsApp.access$100(this$0).postDelayed(MmsApp.access$000(this$0), 800L);
      return;
      if (MessageUtils.isUnderstandSupported()) {
        MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_setting", "0");
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.MmsApp.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
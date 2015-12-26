package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.List;

class SmsReportService$2
  extends BroadcastReceiver
{
  SmsReportService$2(SmsReportService paramSmsReportService) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (("android.intent.action.KEYCODE_MUTE".equals(paramIntent.getAction())) && (SmsReportService.access$000(this$0) != null)) {}
    try
    {
      SmsReportService.access$000(this$0).stop();
      SmsReportService.access$100(this$0).clear();
      return;
    }
    catch (IllegalArgumentException paramContext)
    {
      for (;;)
      {
        MyLog.e("SmsReportService", "Fail to stop tts", paramContext);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SmsReportService.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
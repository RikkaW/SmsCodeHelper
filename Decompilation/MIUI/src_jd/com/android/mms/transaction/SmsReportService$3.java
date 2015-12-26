package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.provider.Settings.System;
import android.speech.tts.TextToSpeech;
import com.xiaomi.mms.utils.logger.MyLog;

class SmsReportService$3
  extends BroadcastReceiver
{
  SmsReportService$3(SmsReportService paramSmsReportService) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((Settings.System.getInt(this$0.getContentResolver(), "voiceassist_report_method", 2) == 0) && (!((AudioManager)paramContext.getSystemService("audio")).isWiredHeadsetOn()) && (SmsReportService.access$000(this$0) != null)) {}
    try
    {
      SmsReportService.access$000(this$0).stop();
      return;
    }
    catch (IllegalArgumentException paramContext)
    {
      MyLog.e("SmsReportService", "Fail to stop tts", paramContext);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SmsReportService.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
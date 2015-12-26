package com.android.mms.transaction;

import android.database.ContentObserver;
import android.os.Handler;
import android.speech.tts.TextToSpeech;

class SmsReportService$4
  extends ContentObserver
{
  SmsReportService$4(SmsReportService paramSmsReportService, Handler paramHandler)
  {
    super(paramHandler);
  }
  
  public void onChange(boolean paramBoolean)
  {
    super.onChange(paramBoolean);
    if (SmsReportService.access$000(this$0) != null)
    {
      SmsReportService.access$000(this$0).shutdown();
      SmsReportService.access$002(this$0, null);
    }
    SmsReportService.access$200(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SmsReportService.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
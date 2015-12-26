package com.ted.android.core;

import android.os.Message;
import bo;
import com.ted.android.data.SmsEntity;

class SmsEntityLoader$d
  implements Runnable
{
  private SmsEntityLoader.SmsInfo b;
  private SmsEntity c;
  
  public SmsEntityLoader$d(SmsEntityLoader paramSmsEntityLoader, SmsEntityLoader.SmsInfo paramSmsInfo)
  {
    b = paramSmsInfo;
  }
  
  private void a()
  {
    Message localMessage = new Message();
    what = 1;
    obj = this;
    SmsEntityLoader.access$4(a).sendMessage(localMessage);
  }
  
  public void run()
  {
    SmsEntityLoader.access$0(a, true);
    if (SmsEntityLoader.access$1(a)) {}
    while (Thread.interrupted()) {
      return;
    }
    c = SmsParserEngine.getInstance(SmsEntityLoader.access$2(a)).parseMessage(b.msgId.longValue(), b.body, b.number, b.date);
    SmsEntityLoader.access$0(a, false);
    if (c != null) {
      SmsEntityLoader.access$3(a).a(c);
    }
    a();
  }
}

/* Location:
 * Qualified Name:     com.ted.android.core.SmsEntityLoader.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
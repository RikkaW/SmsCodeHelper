package com.android.mms.transaction;

import android.telephony.PhoneStateListener;
import android.util.Log;

class MessagePopupService$c
  extends PhoneStateListener
{
  private MessagePopupService b;
  private MessagePopupService.b c;
  
  MessagePopupService$c(MessagePopupService paramMessagePopupService1, MessagePopupService paramMessagePopupService2)
  {
    b = paramMessagePopupService2;
  }
  
  public void a(MessagePopupService.b paramb)
  {
    c = paramb;
  }
  
  public void onCallStateChanged(int paramInt, String paramString)
  {
    if (paramInt == 1)
    {
      Log.d("MessagePopupService", "Ringing");
      MessagePopupService.a(b, c.b, true);
    }
    super.onCallStateChanged(paramInt, paramString);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagePopupService.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
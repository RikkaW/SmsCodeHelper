package com.android.mms.transaction;

import aej;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.android.mms.MmsApp;
import gm;
import gq;
import gr;

final class MessagePopupService$d
  extends Handler
{
  public MessagePopupService$d(MessagePopupService paramMessagePopupService, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      super.handleMessage(paramMessage);
      return;
    }
    if (obj == null)
    {
      MessagePopupService.a(true, 6, "MessagePopupService", "LOAD_CONTACT_TOKEN-->msg.obj is null...");
      return;
    }
    long l = System.currentTimeMillis();
    paramMessage = (MessagePopupService.b)obj;
    Object localObject = gr.a(MmsApp.c(), b, true);
    if ((localObject == null) || (((gr)localObject).h().size() != 1))
    {
      MessagePopupService.a(true, 6, "MessagePopupService", "LOAD_CONTACT_TOKEN)-->" + paramMessage);
      return;
    }
    MessagePopupService.a(false, 2, "MessagePopupService", "LOAD_CONTACT_TOKEN)-->" + paramMessage);
    a = ((gr)localObject);
    localObject = (gm)((gr)localObject).h().get(0);
    if (n)
    {
      MessagePopupService.a(true, 2, "MessagePopupService", "LOAD_CONTACT_TOKEN-->contact in load from cache time out " + ((gm)localObject).d() + ", time = " + (System.currentTimeMillis() - l));
      return;
    }
    if (MessagePopupService.b(a) != null) {
      MessagePopupService.b(a).removeMessages(5, paramMessage);
    }
    if ((MessagePopupService.a(a) != null) && (MessagePopupService.a(a).g()) && (!paramMessage.a()))
    {
      MessagePopupService.b localb = MessagePopupService.a(a).i();
      if ((MessagePopupService.a(a).h()) && ((localb == null) || (b != b)))
      {
        MessagePopupService.a(true, 2, "MessagePopupService", "LOAD_CONTACT_TOKEN)-->reply mode and other thread com in, show message in statuBar, number is " + ((gm)localObject).d());
        MessagingNotification.b(a, b, false, true);
        return;
      }
      j = j;
    }
    if ((((gm)localObject).k()) || (f != null))
    {
      MessagePopupService.a(false, 3, "MessagePopupService", "contact in db, query the message, show message in popup, number is " + ((gm)localObject).d() + ", time = " + (System.currentTimeMillis() - l));
      e = ((gm)localObject).d();
      MessagePopupService.a(a, 1, paramMessage);
      return;
    }
    MessagePopupService.a(false, 3, "MessagePopupService", "contact not in db, show message in statuBar, number is " + ((gm)localObject).d() + ", time = " + (System.currentTimeMillis() - l));
    MessagingNotification.b(a, b, false, true);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagePopupService.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
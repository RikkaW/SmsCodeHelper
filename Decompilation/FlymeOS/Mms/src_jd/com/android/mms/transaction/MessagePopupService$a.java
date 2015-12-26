package com.android.mms.transaction;

import aej;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Message;
import android.telephony.TelephonyManager;
import com.android.mms.MmsApp;
import gr.b;
import vv;

final class MessagePopupService$a
  extends gr.b
{
  public MessagePopupService$a(MessagePopupService paramMessagePopupService, ContentResolver paramContentResolver)
  {
    super(paramContentResolver);
  }
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    case 4: 
    default: 
      super.handleMessage(paramMessage);
    }
    do
    {
      do
      {
        return;
        removeMessages(3);
        if (MmsApp.c().d())
        {
          MessagePopupService.a(true, 4, "MessagePopupService", "SHOW_MSG_TOKEN, Mms is front");
          return;
        }
        paramMessage = (MessagePopupService.b)obj;
        MessagePopupService.a(a).a(paramMessage);
        if (!j) {
          a.b(e);
        }
        MessagePopupService.c(a);
        MessagePopupService.d(a).a(paramMessage);
        MessagePopupService.e(a).listen(MessagePopupService.d(a), 32);
        return;
        MessagePopupService.a(false, 3, "MessagePopupService", "TIMEOUT_SHOW_MSG_TOKEN-->time out, hidden popwindow...");
        return;
        paramMessage = (MessagePopupService.b)obj;
        MessagePopupService.a(false, 3, "MessagePopupService", "LOAD_DRAFT_TOKEN-->" + paramMessage);
      } while ((paramMessage == null) || (b <= 0L));
      MessagePopupService.a(a, b);
      return;
      MessagePopupService.a(true, 3, "MessagePopupService", "LOAD_CONTACT_TIMEOUT_VALUE-->" + obj);
    } while (obj == null);
    paramMessage = (MessagePopupService.b)obj;
    if (MessagePopupService.f(a) != null) {
      MessagePopupService.f(a).removeMessages(4, paramMessage);
    }
    n = true;
    MessagingNotification.a(a, b, false, true);
  }
  
  protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
  {
    switch (paramInt)
    {
    }
    do
    {
      return;
    } while (paramCursor == null);
    paramObject = (MessagePopupService.b)paramObject;
    if (paramObject == null)
    {
      MessagePopupService.a(true, 6, "MessagePopupService", "QUERY_LAST_UNREAD_MSG-->msgBean is null...");
      return;
    }
    long l;
    if (paramCursor.moveToNext())
    {
      if (!c) {
        break label273;
      }
      l = paramCursor.getLong(0);
      if (l != b)
      {
        j = false;
        b = l;
      }
      d = paramCursor.getLong(1);
      e = paramCursor.getString(2);
      g = paramCursor.getLong(4);
      i = paramCursor.getInt(5);
      h = paramCursor.getInt(6);
      if (f == null) {
        f = paramCursor.getString(3);
      }
      if (h == vv.a) {
        f = a.getString(2131493489);
      }
      o = paramCursor.getInt(7);
      p = paramCursor.getString(8);
    }
    for (;;)
    {
      paramCursor.close();
      paramCursor = new Message();
      what = 2;
      obj = paramObject;
      sendMessage(paramCursor);
      MessagePopupService.a(true, 6, "MessagePopupService", "QUERY_LAST_UNREAD_MSG-->" + ((MessagePopupService.b)paramObject).toString());
      return;
      label273:
      l = paramCursor.getLong(0);
      if (l != b)
      {
        j = false;
        b = l;
      }
      d = paramCursor.getLong(1);
      g = (paramCursor.getLong(4) * 1000L);
      i = paramCursor.getInt(5);
      o = paramCursor.getInt(8);
      p = paramCursor.getString(9);
      MessagePopupService.a(a, (MessagePopupService.b)paramObject, paramCursor);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagePopupService.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
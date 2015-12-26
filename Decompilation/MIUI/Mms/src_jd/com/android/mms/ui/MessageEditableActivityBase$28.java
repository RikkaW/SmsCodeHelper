package com.android.mms.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import java.util.Map;

class MessageEditableActivityBase$28
  extends Handler
{
  MessageEditableActivityBase$28(MessageEditableActivityBase paramMessageEditableActivityBase) {}
  
  public void handleMessage(Message paramMessage)
  {
    Log.v("MessageEditableActivityBase", "handle msg on main thread, msg: " + what);
    switch (what)
    {
    case 2: 
    default: 
      super.handleMessage(paramMessage);
    }
    Object localObject;
    do
    {
      do
      {
        return;
        this$0.postSwitchMsgType();
        return;
        localObject = (String)obj;
        l = paramMessage.getData().getLong("capability");
        MessageEditableActivityBase.access$2200(this$0, l, (String)localObject);
        MessageEditableActivityBase.access$2300(this$0).remove(localObject);
      } while (what != 1);
      this$0.postSwitchMsgType();
      return;
      localObject = this$0.getRecipients();
    } while (localObject == null);
    String str = (String)obj;
    if (!((ContactList)localObject).contains(Contact.get(str)))
    {
      Log.w("MessageEditableActivityBase", "recipient is no longer in list");
      return;
    }
    long l = paramMessage.getData().getLong("capability");
    MessageEditableActivityBase.access$2200(this$0, l, str);
    this$0.postSwitchMsgType();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.28
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
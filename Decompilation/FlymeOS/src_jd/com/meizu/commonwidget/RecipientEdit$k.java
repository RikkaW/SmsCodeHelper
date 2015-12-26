package com.meizu.commonwidget;

import aii;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

class RecipientEdit$k
  extends Handler
{
  public RecipientEdit$k(RecipientEdit paramRecipientEdit, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    Object localObject1 = obj).b;
    String str = obj).a;
    if (!TextUtils.isEmpty((CharSequence)localObject1))
    {
      localObject2 = ((String)localObject1).split(";");
      if (localObject2.length > 1) {
        paramMessage = localObject2[0];
      }
    }
    for (Object localObject2 = localObject2[1];; localObject2 = "")
    {
      localObject1 = paramMessage;
      if (TextUtils.isEmpty(paramMessage)) {
        localObject1 = ((aii)RecipientEdit.a(a).getAdapter()).a(str);
      }
      paramMessage = (Message)localObject1;
      if (!TextUtils.isEmpty((CharSequence)localObject2)) {
        paramMessage = (String)localObject1 + ";" + (String)localObject2;
      }
      paramMessage = new RecipientEdit.a(str, paramMessage);
      localObject1 = Message.obtain(a.j, 1);
      obj = paramMessage;
      a.j.sendMessage((Message)localObject1);
      return;
      paramMessage = (Message)localObject1;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.commonwidget.RecipientEdit.k
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
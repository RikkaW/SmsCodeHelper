package com.xiaomi.mms.mx.fw;

import com.xiaomi.mms.mx.fw.misc.SerializedAsyncTaskProcessor.SerializedAsyncTask;
import java.util.Vector;

class XmppMessageHandler$1
  extends SerializedAsyncTaskProcessor.SerializedAsyncTask
{
  XmppMessageHandler$1(XmppMessageHandler paramXmppMessageHandler) {}
  
  public void process()
  {
    synchronized (XmppMessageHandler.access$000(this$0))
    {
      XmppMessageHandler.access$102(this$0, false);
      Vector localVector2 = new Vector(XmppMessageHandler.access$000(this$0));
      XmppMessageHandler.access$000(this$0).clear();
      HmsPersister.getInstance().process(localVector2);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.XmppMessageHandler.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
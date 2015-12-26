package com.xiaomi.mms.mx.fw;

import com.xiaomi.mms.mx.fw.misc.SerializedAsyncTaskProcessor.SerializedAsyncTask;
import com.xiaomi.mms.mx.utils.Log;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.IQ.Type;

class XmppIQHandler$ProcessIQTask
  extends SerializedAsyncTaskProcessor.SerializedAsyncTask
{
  private IQ mIQ;
  
  private XmppIQHandler$ProcessIQTask(XmppIQHandler paramXmppIQHandler, IQ paramIQ)
  {
    mIQ = paramIQ;
  }
  
  public void process()
  {
    if (mIQ.getType() == IQ.Type.SET)
    {
      XmppIQHandler.access$100(this$0, mIQ);
      return;
    }
    if (mIQ.getType() == IQ.Type.ERROR)
    {
      XmppIQHandler.access$200(this$0, mIQ);
      return;
    }
    if (mIQ.getType() == IQ.Type.GET)
    {
      Log.e("XmppIQHanlder", "不应该出现收到Get iq:" + mIQ.toXML());
      return;
    }
    Log.e("XmppIQHanlder", "unkown iq:" + mIQ.toXML());
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.XmppIQHandler.ProcessIQTask
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
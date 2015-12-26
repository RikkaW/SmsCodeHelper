package com.xiaomi.mms.mx.fw;

import com.xiaomi.mms.mx.fw.misc.SerializedAsyncTaskProcessor;
import com.xiaomi.mms.mx.fw.misc.SerializedAsyncTaskProcessor.SerializedAsyncTask;
import com.xiaomi.mms.mx.fw.misc.XmppHandler;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import java.util.Vector;

public class XmppMessageHandler
  extends XmppHandler
{
  private static XmppMessageHandler sUniqueInstance;
  private volatile boolean mDelayed = false;
  private final Vector<Message> mPacketCache = new Vector();
  
  public static XmppMessageHandler getInstance()
  {
    if (sUniqueInstance == null) {}
    try
    {
      if (sUniqueInstance == null) {
        sUniqueInstance = new XmppMessageHandler();
      }
      return sUniqueInstance;
    }
    finally {}
  }
  
  public boolean handle(Packet paramPacket)
  {
    if ((paramPacket instanceof Message))
    {
      paramPacket = (Message)paramPacket;
      mPacketCache.add(paramPacket);
      if (!mDelayed)
      {
        mDelayed = true;
        mTaskProcessor.addNewTaskWithDelayed(new SerializedAsyncTaskProcessor.SerializedAsyncTask()
        {
          public void process()
          {
            synchronized (mPacketCache)
            {
              XmppMessageHandler.access$102(XmppMessageHandler.this, false);
              Vector localVector2 = new Vector(mPacketCache);
              mPacketCache.clear();
              HmsPersister.getInstance().process(localVector2);
              return;
            }
          }
        }, 200L);
      }
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.XmppMessageHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
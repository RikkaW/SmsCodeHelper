package com.xiaomi.mms.mx.fw;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mms.mx.fw.faccount.JIDUtils;
import com.xiaomi.mms.mx.fw.misc.SerializedAsyncTaskProcessor;
import com.xiaomi.mms.mx.fw.misc.SerializedAsyncTaskProcessor.SerializedAsyncTask;
import com.xiaomi.mms.mx.fw.misc.XmppHandler;
import com.xiaomi.mms.mx.utils.Log;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.IQ.Type;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.XMPPError;
import java.util.ArrayList;
import java.util.List;

public class XmppIQHandler
  extends XmppHandler
{
  private static XmppIQHandler sUniqueInstance;
  private Context mContext;
  
  private XmppIQHandler(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public static XmppIQHandler getInstance(Context paramContext)
  {
    if (sUniqueInstance == null) {}
    try
    {
      if (sUniqueInstance == null) {
        sUniqueInstance = new XmppIQHandler(paramContext);
      }
      return sUniqueInstance;
    }
    finally {}
  }
  
  private boolean isConflictIQ(IQ paramIQ)
  {
    return paramIQ.getExtension("conflict", "xm") != null;
  }
  
  private boolean isMisMatchIQ(IQ paramIQ)
  {
    return paramIQ.getExtension("mismatch", "xm") != null;
  }
  
  private void processHmsErrorIQ(IQ paramIQ)
  {
    if (paramIQ != null)
    {
      Object localObject1 = paramIQ.getExtension("vipmsg", "xm:vip");
      if (localObject1 != null)
      {
        localObject1 = ((CommonPacketExtension)localObject1).getChildByName("vip");
        if (localObject1 != null)
        {
          localObject1 = ((CommonPacketExtension)localObject1).getAttributeValue("id");
          if (!TextUtils.isEmpty((CharSequence)localObject1))
          {
            Object localObject2 = paramIQ.getError();
            if (localObject2 != null)
            {
              paramIQ = ((XMPPError)localObject2).getType().toString();
              localObject2 = ((XMPPError)localObject2).getReason();
              if (("cancel".equalsIgnoreCase(paramIQ)) && ("not-acceptable".equalsIgnoreCase((String)localObject2)))
              {
                paramIQ = JIDUtils.getFullSmtpName((String)localObject1);
                new ArrayList().add(paramIQ);
              }
            }
          }
        }
      }
    }
  }
  
  private void processHmsSetIQ(IQ paramIQ)
  {
    boolean bool2;
    if (paramIQ != null)
    {
      Log.d("XmppIQHanlder", "process hms set iq : " + paramIQ.toString());
      boolean bool1 = isMisMatchIQ(paramIQ);
      bool2 = isConflictIQ(paramIQ);
      if (!bool1) {
        break label55;
      }
      Log.d("XmppIQHanlder", "received mis-match message, should not happen, do nothing.");
    }
    label55:
    while (!bool2) {
      return;
    }
    Log.d("XmppIQHanlder", "received conflict message, should not happen, do nothing.");
  }
  
  public boolean handle(Packet paramPacket)
  {
    if ((paramPacket instanceof IQ))
    {
      paramPacket = (IQ)paramPacket;
      if (paramPacket != null) {
        mTaskProcessor.addNewTask(new ProcessIQTask(paramPacket, null));
      }
    }
    return false;
  }
  
  private class ProcessIQTask
    extends SerializedAsyncTaskProcessor.SerializedAsyncTask
  {
    private IQ mIQ;
    
    private ProcessIQTask(IQ paramIQ)
    {
      mIQ = paramIQ;
    }
    
    public void process()
    {
      if (mIQ.getType() == IQ.Type.SET)
      {
        XmppIQHandler.this.processHmsSetIQ(mIQ);
        return;
      }
      if (mIQ.getType() == IQ.Type.ERROR)
      {
        XmppIQHandler.this.processHmsErrorIQ(mIQ);
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
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.XmppIQHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
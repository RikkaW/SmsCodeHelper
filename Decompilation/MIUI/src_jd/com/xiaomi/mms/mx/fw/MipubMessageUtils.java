package com.xiaomi.mms.mx.fw;

import android.os.Bundle;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Message;

public class MipubMessageUtils
{
  public static CommonPacketExtension getMsgExtension(Message paramMessage)
  {
    if (paramMessage == null) {
      return null;
    }
    return paramMessage.getExtension("ext").getChildByName("msg");
  }
  
  public static boolean isGlobal(Message paramMessage)
  {
    if (paramMessage == null) {}
    do
    {
      return false;
      CommonPacketExtension localCommonPacketExtension = paramMessage.getExtension("metadata");
      paramMessage = null;
      if (localCommonPacketExtension != null) {
        paramMessage = localCommonPacketExtension.getChildByName("global");
      }
    } while (paramMessage == null);
    return true;
  }
  
  public static boolean isTemplate(Message paramMessage)
  {
    if (paramMessage == null) {}
    do
    {
      return false;
      CommonPacketExtension localCommonPacketExtension = paramMessage.getExtension("metadata");
      paramMessage = null;
      if (localCommonPacketExtension != null) {
        paramMessage = localCommonPacketExtension.getChildByName("template");
      }
    } while (paramMessage == null);
    return true;
  }
  
  public static boolean isTransient(Message paramMessage)
  {
    if (paramMessage == null) {
      return false;
    }
    return paramMessage.toBundle().getBoolean("ext_msg_trans", false);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.MipubMessageUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
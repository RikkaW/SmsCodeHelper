package com.xiaomi.mms.mx.fw.xmppmsg;

import android.text.TextUtils;
import com.xiaomi.mms.mx.fw.HmsPersister;
import com.xiaomi.mms.mx.utils.Log;
import com.xiaomi.push.service.ServiceClient;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Message;
import java.util.Iterator;
import java.util.List;

public abstract class MessageDecor
  extends Message
{
  private CommonPacketExtension mMetadataExtension = null;
  
  protected void buildMessage(String paramString1, String paramString2, String paramString3)
  {
    buildeMessage(paramString1, paramString2, paramString3, "8", null, false, false, null, null);
  }
  
  protected void buildeMessage(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean1, boolean paramBoolean2, String paramString6, String paramString7)
  {
    setFrom(paramString1);
    setTo(paramString2);
    setPacketID(paramString3);
    setChannelId(paramString4);
    setAppId(paramString5);
    setIsTransient(paramBoolean1);
    setEncrypted(paramBoolean2);
    setThread(paramString6);
    setSubject(paramString7);
    setTypeAttribute();
    setBodyElement();
    paramString1 = createCommonPacketExtensions();
    if (paramString1 != null)
    {
      paramString1 = paramString1.iterator();
      while (paramString1.hasNext()) {
        addExtension((CommonPacketExtension)paramString1.next());
      }
    }
  }
  
  public abstract List<CommonPacketExtension> createCommonPacketExtensions();
  
  public final void sendMessage(boolean paramBoolean)
  {
    List localList = (List)getExtensions();
    if ((TextUtils.isEmpty(getBody())) && ((localList == null) || (localList.isEmpty()))) {
      return;
    }
    ServiceClient.getInstance(HmsPersister.getContext()).sendMessage(this, true);
    if (localList.size() > 0) {
      Log.w("MessageDecor", "sending a Xmpp message to" + getTo() + " id=" + getPacketID() + " ext=" + ((CommonPacketExtension)localList.get(0)).getElementName());
    }
    for (;;)
    {
      Log.d("MessageDecor", toXML());
      return;
      Log.w("MessageDecor", "sending a Xmpp message to " + getTo() + " id=" + getPacketID());
    }
  }
  
  public abstract void setBodyElement();
  
  public abstract void setTypeAttribute();
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.xmppmsg.MessageDecor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
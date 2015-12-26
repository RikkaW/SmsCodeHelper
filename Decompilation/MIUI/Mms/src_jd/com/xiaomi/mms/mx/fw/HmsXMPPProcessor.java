package com.xiaomi.mms.mx.fw;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mms.mx.fw.fdata.MessageData;
import com.xiaomi.mms.mx.fw.futils.ISO8601DateParser;
import com.xiaomi.mms.mx.utils.Log;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Message;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class HmsXMPPProcessor
{
  private static HmsXMPPProcessor sInstance = null;
  private Context mContext;
  
  private HmsXMPPProcessor(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public static HmsXMPPProcessor getInstance(Context paramContext)
  {
    if (sInstance == null) {
      sInstance = new HmsXMPPProcessor(paramContext.getApplicationContext());
    }
    return sInstance;
  }
  
  private MessageData handleHmsMessage(Message paramMessage)
  {
    String str2 = paramMessage.getFrom();
    String str3 = paramMessage.getTo();
    String str4 = paramMessage.getSeq();
    String str5 = paramMessage.getBody();
    String str6 = paramMessage.getPacketID();
    String str1 = "";
    boolean bool1 = MipubMessageUtils.isTransient(paramMessage);
    int i = paramMessage.getFrom().lastIndexOf("/");
    if (i >= 0) {
      str1 = paramMessage.getFrom().substring(i + 1);
    }
    long l2 = getOfflineMessageSentTime(paramMessage);
    long l1 = l2;
    if (l2 < 0L) {
      l1 = System.currentTimeMillis();
    }
    CommonPacketExtension localCommonPacketExtension = MipubMessageUtils.getMsgExtension(paramMessage);
    boolean bool2 = MipubMessageUtils.isGlobal(paramMessage);
    boolean bool3 = MipubMessageUtils.isTemplate(paramMessage);
    return new MessageData(str6, 0, l1, System.currentTimeMillis(), str2, paramMessage.getType(), localCommonPacketExtension.getText(), str4, null, str1, str3, str5, bool1, bool2, bool3);
  }
  
  protected long getOfflineMessageSentTime(Message paramMessage)
  {
    long l2 = -1L;
    paramMessage = paramMessage.getExtensions();
    long l1 = l2;
    Object localObject;
    if (paramMessage != null)
    {
      localObject = paramMessage.iterator();
      do
      {
        l1 = l2;
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        paramMessage = (CommonPacketExtension)((Iterator)localObject).next();
      } while (!paramMessage.getElementName().equalsIgnoreCase("delay"));
      localObject = paramMessage.getAttributeValue("ts");
      if (TextUtils.isEmpty((CharSequence)localObject)) {
        break label117;
      }
    }
    label117:
    do
    {
      try
      {
        l1 = Long.parseLong((String)localObject);
        return l1;
      }
      catch (NumberFormatException paramMessage)
      {
        Log.e("HmsXMPPProcessor", "parse time stmap error : " + paramMessage.toString());
        return -1L;
      }
      paramMessage = paramMessage.getAttributeValue("stamp");
      l1 = l2;
    } while (paramMessage == null);
    try
    {
      l1 = ISO8601DateParser.parse(paramMessage).getTime();
      return l1;
    }
    catch (ParseException localParseException)
    {
      Log.e("HmsXMPPProcessor", String.format("The timestamp (%s)cannot be converted to a propertime", new Object[] { paramMessage }));
    }
    return -1L;
  }
  
  public MessageData processHmsMessage(Message paramMessage)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramMessage != null)
    {
      CommonPacketExtension localCommonPacketExtension = paramMessage.getExtension("ext");
      localObject1 = localObject2;
      if (localCommonPacketExtension != null)
      {
        localObject1 = localObject2;
        if ("subscribe".equals(localCommonPacketExtension.getAttributeValue("type"))) {
          localObject1 = handleHmsMessage(paramMessage);
        }
      }
    }
    return (MessageData)localObject1;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.HmsXMPPProcessor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
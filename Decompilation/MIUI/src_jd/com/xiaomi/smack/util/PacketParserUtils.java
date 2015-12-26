package com.xiaomi.smack.util;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.CommonPacketExtensionProvider;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo;
import com.xiaomi.push.service.RC4Cryption;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.XMBinder.BindResult;
import com.xiaomi.smack.XMBinder.BindResult.Type;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.IQ.Type;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.Presence;
import com.xiaomi.smack.packet.Presence.Mode;
import com.xiaomi.smack.packet.Presence.Type;
import com.xiaomi.smack.packet.StreamError;
import com.xiaomi.smack.packet.XMPPError;
import com.xiaomi.smack.packet.XMPPError.Condition;
import com.xiaomi.smack.provider.ProviderManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class PacketParserUtils
{
  private static XmlPullParser sDecryptedMsgParser = null;
  
  private static String getLanguageAttribute(XmlPullParser paramXmlPullParser)
  {
    int i = 0;
    while (i < paramXmlPullParser.getAttributeCount())
    {
      String str = paramXmlPullParser.getAttributeName(i);
      if (("xml:lang".equals(str)) || (("lang".equals(str)) && ("xml".equals(paramXmlPullParser.getAttributePrefix(i))))) {
        return paramXmlPullParser.getAttributeValue(i);
      }
      i += 1;
    }
    return null;
  }
  
  public static XMBinder.BindResult parseBindResult(XmlPullParser paramXmlPullParser)
    throws Exception
  {
    XMBinder.BindResult localBindResult = new XMBinder.BindResult();
    Object localObject = paramXmlPullParser.getAttributeValue("", "id");
    String str1 = paramXmlPullParser.getAttributeValue("", "to");
    String str2 = paramXmlPullParser.getAttributeValue("", "from");
    String str3 = paramXmlPullParser.getAttributeValue("", "chid");
    XMBinder.BindResult.Type localType = XMBinder.BindResult.Type.fromString(paramXmlPullParser.getAttributeValue("", "type"));
    localBindResult.setPacketID((String)localObject);
    localBindResult.setTo(str1);
    localBindResult.setFrom(str2);
    localBindResult.setChannelId(str3);
    localBindResult.setType(localType);
    int i = 0;
    localObject = null;
    while (i == 0)
    {
      int j = paramXmlPullParser.next();
      if (j == 2)
      {
        if (paramXmlPullParser.getName().equals("error")) {
          localObject = parseError(paramXmlPullParser);
        }
      }
      else if ((j == 3) && (paramXmlPullParser.getName().equals("bind"))) {
        i = 1;
      }
    }
    localBindResult.setError((XMPPError)localObject);
    return localBindResult;
  }
  
  private static String parseContent(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    String str = "";
    int i = paramXmlPullParser.getDepth();
    while ((paramXmlPullParser.next() != 3) || (paramXmlPullParser.getDepth() != i)) {
      str = str + paramXmlPullParser.getText();
    }
    return str;
  }
  
  public static XMPPError parseError(XmlPullParser paramXmlPullParser)
    throws Exception
  {
    String str4 = "-1";
    String str1 = null;
    String str2 = null;
    Object localObject = null;
    String str3 = null;
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramXmlPullParser.getAttributeCount())
    {
      if (paramXmlPullParser.getAttributeName(i).equals("code")) {
        str4 = paramXmlPullParser.getAttributeValue("", "code");
      }
      if (paramXmlPullParser.getAttributeName(i).equals("type")) {
        str1 = paramXmlPullParser.getAttributeValue("", "type");
      }
      if (paramXmlPullParser.getAttributeName(i).equals("reason")) {
        str3 = paramXmlPullParser.getAttributeValue("", "reason");
      }
      i += 1;
    }
    i = 0;
    while (i == 0)
    {
      int j = paramXmlPullParser.next();
      if (j == 2)
      {
        if (paramXmlPullParser.getName().equals("text"))
        {
          str2 = paramXmlPullParser.nextText();
        }
        else
        {
          String str5 = paramXmlPullParser.getName();
          String str6 = paramXmlPullParser.getNamespace();
          if ("urn:ietf:params:xml:ns:xmpp-stanzas".equals(str6)) {
            localObject = str5;
          } else {
            localArrayList.add(parsePacketExtension(str5, str6, paramXmlPullParser));
          }
        }
      }
      else if (j == 3)
      {
        if (paramXmlPullParser.getName().equals("error")) {
          i = 1;
        }
      }
      else if (j == 4) {
        str2 = paramXmlPullParser.getText();
      }
    }
    if (str1 == null) {}
    for (paramXmlPullParser = "cancel";; paramXmlPullParser = str1) {
      return new XMPPError(Integer.parseInt(str4), paramXmlPullParser, str3, (String)localObject, str2, localArrayList);
    }
  }
  
  public static IQ parseIQ(XmlPullParser paramXmlPullParser, Connection paramConnection)
    throws Exception
  {
    IQ localIQ = null;
    String str1 = paramXmlPullParser.getAttributeValue("", "id");
    String str2 = paramXmlPullParser.getAttributeValue("", "to");
    String str3 = paramXmlPullParser.getAttributeValue("", "from");
    String str4 = paramXmlPullParser.getAttributeValue("", "chid");
    IQ.Type localType = IQ.Type.fromString(paramXmlPullParser.getAttributeValue("", "type"));
    HashMap localHashMap = new HashMap();
    int i = 0;
    while (i < paramXmlPullParser.getAttributeCount())
    {
      localObject = paramXmlPullParser.getAttributeName(i);
      localHashMap.put(localObject, paramXmlPullParser.getAttributeValue("", (String)localObject));
      i += 1;
    }
    Object localObject = null;
    i = 0;
    while (i == 0)
    {
      int j = paramXmlPullParser.next();
      if (j == 2)
      {
        String str5 = paramXmlPullParser.getName();
        String str6 = paramXmlPullParser.getNamespace();
        if (str5.equals("error"))
        {
          localObject = parseError(paramXmlPullParser);
        }
        else
        {
          localIQ = new IQ();
          localIQ.addExtension(parsePacketExtension(str5, str6, paramXmlPullParser));
        }
      }
      else if ((j == 3) && (paramXmlPullParser.getName().equals("iq")))
      {
        i = 1;
      }
    }
    paramXmlPullParser = localIQ;
    if (localIQ == null)
    {
      if ((IQ.Type.GET == localType) || (IQ.Type.SET == localType))
      {
        paramXmlPullParser = new IQ()
        {
          public String getChildElementXML()
          {
            return null;
          }
        };
        paramXmlPullParser.setPacketID(str1);
        paramXmlPullParser.setTo(str3);
        paramXmlPullParser.setFrom(str2);
        paramXmlPullParser.setType(IQ.Type.ERROR);
        paramXmlPullParser.setChannelId(str4);
        paramXmlPullParser.setError(new XMPPError(XMPPError.Condition.feature_not_implemented));
        paramConnection.sendPacket(paramXmlPullParser);
        MyLog.e("iq usage error. send packet in packet parser.");
        return null;
      }
      paramXmlPullParser = new IQ()
      {
        public String getChildElementXML()
        {
          return null;
        }
      };
    }
    paramXmlPullParser.setPacketID(str1);
    paramXmlPullParser.setTo(str2);
    paramXmlPullParser.setChannelId(str4);
    paramXmlPullParser.setFrom(str3);
    paramXmlPullParser.setType(localType);
    paramXmlPullParser.setError((XMPPError)localObject);
    paramXmlPullParser.setAttributes(localHashMap);
    return paramXmlPullParser;
  }
  
  public static Packet parseMessage(XmlPullParser paramXmlPullParser)
    throws Exception
  {
    String str1;
    String str2;
    int i;
    int j;
    if ("1".equals(paramXmlPullParser.getAttributeValue("", "s")))
    {
      str1 = paramXmlPullParser.getAttributeValue("", "chid");
      localObject3 = paramXmlPullParser.getAttributeValue("", "id");
      str2 = paramXmlPullParser.getAttributeValue("", "from");
      String str3 = paramXmlPullParser.getAttributeValue("", "to");
      String str4 = paramXmlPullParser.getAttributeValue("", "type");
      localObject2 = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(str1, str3);
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(str1, str2);
      }
      localObject2 = null;
      if (localObject1 == null) {
        throw new XMPPException("the channel id is wrong while receiving a encrypted message");
      }
      i = 0;
      while (i == 0)
      {
        j = paramXmlPullParser.next();
        if (j == 2)
        {
          if (!"s".equals(paramXmlPullParser.getName())) {
            throw new XMPPException("error while receiving a encrypted message with wrong format");
          }
          if (paramXmlPullParser.next() != 4) {
            throw new XMPPException("error while receiving a encrypted message with wrong format");
          }
          localObject2 = paramXmlPullParser.getText();
          if (("5".equals(str1)) || ("6".equals(str1)))
          {
            paramXmlPullParser = new Message();
            paramXmlPullParser.setChannelId(str1);
            paramXmlPullParser.setEncrypted(true);
            paramXmlPullParser.setFrom(str2);
            paramXmlPullParser.setTo(str3);
            paramXmlPullParser.setPacketID((String)localObject3);
            paramXmlPullParser.setType(str4);
            localObject1 = new CommonPacketExtension("s", null, (String[])null, (String[])null);
            ((CommonPacketExtension)localObject1).setText((String)localObject2);
            paramXmlPullParser.addExtension((CommonPacketExtension)localObject1);
            return paramXmlPullParser;
          }
          resetDecryptedMsgParser(RC4Cryption.decrypt(RC4Cryption.generateKeyForRC4(security, (String)localObject3), (String)localObject2));
          sDecryptedMsgParser.next();
          localObject2 = parseMessage(sDecryptedMsgParser);
        }
        else if ((j == 3) && (paramXmlPullParser.getName().equals("message")))
        {
          i = 1;
        }
      }
      if (localObject2 != null) {
        return (Packet)localObject2;
      }
      throw new XMPPException("error while receiving a encrypted message with wrong format");
    }
    Object localObject3 = new Message();
    Object localObject2 = paramXmlPullParser.getAttributeValue("", "id");
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = "ID_NOT_AVAILABLE";
    }
    ((Message)localObject3).setPacketID((String)localObject1);
    ((Message)localObject3).setTo(paramXmlPullParser.getAttributeValue("", "to"));
    ((Message)localObject3).setFrom(paramXmlPullParser.getAttributeValue("", "from"));
    ((Message)localObject3).setChannelId(paramXmlPullParser.getAttributeValue("", "chid"));
    ((Message)localObject3).setAppId(paramXmlPullParser.getAttributeValue("", "appid"));
    localObject1 = null;
    for (;;)
    {
      try
      {
        localObject2 = paramXmlPullParser.getAttributeValue("", "transient");
        localObject1 = localObject2;
      }
      catch (Exception localException5)
      {
        continue;
      }
      try
      {
        localObject2 = paramXmlPullParser.getAttributeValue("", "seq");
        if (!TextUtils.isEmpty((CharSequence)localObject2)) {
          ((Message)localObject3).setSeq((String)localObject2);
        }
        try
        {
          localObject2 = paramXmlPullParser.getAttributeValue("", "mseq");
          if (!TextUtils.isEmpty((CharSequence)localObject2)) {
            ((Message)localObject3).setMSeq((String)localObject2);
          }
          try
          {
            localObject2 = paramXmlPullParser.getAttributeValue("", "fseq");
            if (!TextUtils.isEmpty((CharSequence)localObject2)) {
              ((Message)localObject3).setFSeq((String)localObject2);
            }
            try
            {
              localObject2 = paramXmlPullParser.getAttributeValue("", "status");
              if (!TextUtils.isEmpty((CharSequence)localObject2)) {
                ((Message)localObject3).setStatus((String)localObject2);
              }
              boolean bool;
              if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (((String)localObject1).equalsIgnoreCase("true")))
              {
                bool = true;
                ((Message)localObject3).setIsTransient(bool);
                ((Message)localObject3).setType(paramXmlPullParser.getAttributeValue("", "type"));
                localObject1 = getLanguageAttribute(paramXmlPullParser);
                if ((localObject1 != null) && (!"".equals(((String)localObject1).trim())))
                {
                  ((Message)localObject3).setLanguage((String)localObject1);
                  i = 0;
                  localObject1 = null;
                  if (i != 0) {
                    continue;
                  }
                  j = paramXmlPullParser.next();
                  if (j != 2) {
                    continue;
                  }
                  str2 = paramXmlPullParser.getName();
                  str1 = paramXmlPullParser.getNamespace();
                  localObject2 = str1;
                  if (TextUtils.isEmpty(str1)) {
                    localObject2 = "xm";
                  }
                  if (!str2.equals("subject")) {
                    continue;
                  }
                  if (getLanguageAttribute(paramXmlPullParser) == null) {}
                  ((Message)localObject3).setSubject(parseContent(paramXmlPullParser));
                  continue;
                }
              }
              else
              {
                bool = false;
                continue;
              }
              localObject1 = Packet.getDefaultLanguage();
              continue;
              if (str2.equals("body"))
              {
                localObject2 = paramXmlPullParser.getAttributeValue("", "encode");
                str1 = parseContent(paramXmlPullParser);
                if (!TextUtils.isEmpty((CharSequence)localObject2))
                {
                  ((Message)localObject3).setBody(str1, (String)localObject2);
                  continue;
                }
                ((Message)localObject3).setBody(str1);
                continue;
              }
              if (str2.equals("thread"))
              {
                if (localObject1 != null) {
                  continue;
                }
                localObject1 = paramXmlPullParser.nextText();
                continue;
              }
              if (str2.equals("error"))
              {
                ((Message)localObject3).setError(parseError(paramXmlPullParser));
                continue;
              }
              ((Message)localObject3).addExtension(parsePacketExtension(str2, (String)localObject2, paramXmlPullParser));
              continue;
              if ((j != 3) || (!paramXmlPullParser.getName().equals("message"))) {
                continue;
              }
              i = 1;
              continue;
              ((Message)localObject3).setThread((String)localObject1);
              return (Packet)localObject3;
            }
            catch (Exception localException1) {}
          }
          catch (Exception localException2) {}
        }
        catch (Exception localException3) {}
      }
      catch (Exception localException4) {}
    }
  }
  
  public static CommonPacketExtension parsePacketExtension(String paramString1, String paramString2, XmlPullParser paramXmlPullParser)
    throws Exception
  {
    paramString1 = ProviderManager.getInstance().getExtensionProvider("all", "xm:chat");
    if ((paramString1 != null) && ((paramString1 instanceof CommonPacketExtensionProvider))) {
      return ((CommonPacketExtensionProvider)paramString1).parseExtension(paramXmlPullParser);
    }
    return null;
  }
  
  public static Presence parsePresence(XmlPullParser paramXmlPullParser)
    throws Exception
  {
    Object localObject3 = Presence.Type.available;
    Object localObject4 = paramXmlPullParser.getAttributeValue("", "type");
    Object localObject1 = localObject3;
    if (localObject4 != null)
    {
      localObject1 = localObject3;
      if (((String)localObject4).equals("")) {}
    }
    try
    {
      localObject1 = Presence.Type.valueOf((String)localObject4);
      localObject4 = new Presence((Presence.Type)localObject1);
      ((Presence)localObject4).setTo(paramXmlPullParser.getAttributeValue("", "to"));
      ((Presence)localObject4).setFrom(paramXmlPullParser.getAttributeValue("", "from"));
      ((Presence)localObject4).setChannelId(paramXmlPullParser.getAttributeValue("", "chid"));
      localObject3 = paramXmlPullParser.getAttributeValue("", "id");
      localObject1 = localObject3;
      if (localObject3 == null) {
        localObject1 = "ID_NOT_AVAILABLE";
      }
      ((Presence)localObject4).setPacketID((String)localObject1);
      i = 0;
      for (;;)
      {
        if (i != 0) {
          break label382;
        }
        j = paramXmlPullParser.next();
        if (j != 2) {
          break label357;
        }
        localObject1 = paramXmlPullParser.getName();
        localObject3 = paramXmlPullParser.getNamespace();
        if (!((String)localObject1).equals("status")) {
          break;
        }
        ((Presence)localObject4).setStatus(paramXmlPullParser.nextText());
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException1)
    {
      for (;;)
      {
        int i;
        int j;
        System.err.println("Found invalid presence type " + (String)localObject4);
        Object localObject2 = localObject3;
        continue;
        if (((String)localObject2).equals("priority"))
        {
          try
          {
            ((Presence)localObject4).setPriority(Integer.parseInt(paramXmlPullParser.nextText()));
          }
          catch (NumberFormatException localNumberFormatException) {}catch (IllegalArgumentException localIllegalArgumentException2)
          {
            ((Presence)localObject4).setPriority(0);
          }
        }
        else
        {
          String str;
          if (localIllegalArgumentException2.equals("show"))
          {
            str = paramXmlPullParser.nextText();
            try
            {
              ((Presence)localObject4).setMode(Presence.Mode.valueOf(str));
            }
            catch (IllegalArgumentException localIllegalArgumentException3)
            {
              System.err.println("Found invalid presence mode " + str);
            }
          }
          else if (str.equals("error"))
          {
            ((Presence)localObject4).setError(parseError(paramXmlPullParser));
          }
          else
          {
            ((Presence)localObject4).addExtension(parsePacketExtension(str, localIllegalArgumentException3, paramXmlPullParser));
            continue;
            label357:
            if ((j == 3) && (paramXmlPullParser.getName().equals("presence"))) {
              i = 1;
            }
          }
        }
      }
    }
    label382:
    return (Presence)localObject4;
  }
  
  public static StreamError parseStreamError(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    StreamError localStreamError = null;
    int i = 0;
    while (i == 0)
    {
      int j = paramXmlPullParser.next();
      if (j == 2) {
        localStreamError = new StreamError(paramXmlPullParser.getName());
      } else if ((j == 3) && (paramXmlPullParser.getName().equals("error"))) {
        i = 1;
      }
    }
    return localStreamError;
  }
  
  private static void resetDecryptedMsgParser(byte[] paramArrayOfByte)
    throws XmlPullParserException
  {
    if (sDecryptedMsgParser == null) {}
    try
    {
      sDecryptedMsgParser = XmlPullParserFactory.newInstance().newPullParser();
      sDecryptedMsgParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
      sDecryptedMsgParser.setInput(new InputStreamReader(new ByteArrayInputStream(paramArrayOfByte)));
      return;
    }
    catch (XmlPullParserException localXmlPullParserException)
    {
      for (;;)
      {
        localXmlPullParserException.printStackTrace();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.util.PacketParserUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
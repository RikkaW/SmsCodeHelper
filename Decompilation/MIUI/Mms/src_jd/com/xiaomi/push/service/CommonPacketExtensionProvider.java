package com.xiaomi.push.service;

import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.provider.PacketExtensionProvider;
import com.xiaomi.smack.provider.ProviderManager;
import com.xiaomi.smack.util.StringUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class CommonPacketExtensionProvider
  implements PacketExtensionProvider
{
  public static CommonPacketExtension parseExtensionFromStartTag(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    if (paramXmlPullParser.getEventType() != 2) {
      return null;
    }
    Object localObject1 = null;
    Object localObject2 = null;
    Object localObject5 = null;
    CommonPacketExtension localCommonPacketExtension = null;
    String str1 = paramXmlPullParser.getName();
    String str2 = paramXmlPullParser.getNamespace();
    Object localObject3 = localObject5;
    Object localObject4 = localCommonPacketExtension;
    int i;
    if (paramXmlPullParser.getAttributeCount() > 0)
    {
      String[] arrayOfString1 = new String[paramXmlPullParser.getAttributeCount()];
      String[] arrayOfString2 = new String[paramXmlPullParser.getAttributeCount()];
      i = 0;
      for (;;)
      {
        localObject1 = arrayOfString1;
        localObject2 = arrayOfString2;
        localObject3 = localObject5;
        localObject4 = localCommonPacketExtension;
        if (i >= paramXmlPullParser.getAttributeCount()) {
          break;
        }
        arrayOfString1[i] = paramXmlPullParser.getAttributeName(i);
        arrayOfString2[i] = StringUtils.unescapeFromXML(paramXmlPullParser.getAttributeValue(i));
        i += 1;
      }
    }
    for (;;)
    {
      i = paramXmlPullParser.next();
      if (i == 3) {
        break;
      }
      if (i == 4)
      {
        localObject3 = paramXmlPullParser.getText().trim();
      }
      else if (i == 2)
      {
        localObject5 = localObject4;
        if (localObject4 == null) {
          localObject5 = new ArrayList();
        }
        localCommonPacketExtension = parseExtensionFromStartTag(paramXmlPullParser);
        localObject4 = localObject5;
        if (localCommonPacketExtension != null)
        {
          ((List)localObject5).add(localCommonPacketExtension);
          localObject4 = localObject5;
        }
      }
    }
    return new CommonPacketExtension(str1, str2, (String[])localObject1, (String[])localObject2, (String)localObject3, (List)localObject4);
  }
  
  public CommonPacketExtension parseExtension(XmlPullParser paramXmlPullParser)
    throws Exception
  {
    for (int i = paramXmlPullParser.getEventType(); (i != 1) && (i != 2); i = paramXmlPullParser.next()) {}
    if (i == 2) {
      return parseExtensionFromStartTag(paramXmlPullParser);
    }
    return null;
  }
  
  public void register()
  {
    ProviderManager.getInstance().addExtensionProvider("all", "xm:chat", this);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.CommonPacketExtensionProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
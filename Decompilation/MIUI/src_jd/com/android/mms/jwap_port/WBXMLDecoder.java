package com.android.mms.jwap_port;

import android.content.Context;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class WBXMLDecoder
{
  private byte attributeBitMask = Byte.MIN_VALUE;
  private String encoding;
  Context mContext;
  private byte parentBitMask = 64;
  private String publicId = "UNKNOWN";
  private byte publicIdIndex = -1;
  private String stringTable;
  private TokenRepository tokenRepository;
  private DataInputStream wbxmlStream;
  private Document xmlDocument;
  
  public WBXMLDecoder(Context paramContext)
  {
    mContext = paramContext;
  }
  
  private void decodeBody()
    throws IOException
  {
    writeRootElement();
  }
  
  private byte getTokenValue(byte paramByte)
  {
    return (byte)(paramByte & 0x3F);
  }
  
  private boolean hasAttributes(byte paramByte)
  {
    return (attributeBitMask & paramByte) == attributeBitMask;
  }
  
  private boolean hasContent(byte paramByte)
  {
    return (parentBitMask & paramByte) == parentBitMask;
  }
  
  private boolean isAttrNameToken(byte paramByte)
  {
    return paramByte >= 0;
  }
  
  private boolean isAttrValueToken(byte paramByte)
  {
    return paramByte < 0;
  }
  
  private boolean isEntityToken(byte paramByte)
  {
    return paramByte == 2;
  }
  
  private boolean isInlineStrToken(byte paramByte)
  {
    return paramByte == 3;
  }
  
  private boolean isStringTableReferenceToken(byte paramByte)
  {
    return paramByte == -125;
  }
  
  private void readPublicID()
    throws IOException
  {
    Object localObject = new byte[4];
    int i = wbxmlStream.readByte();
    if (i == 0)
    {
      publicIdIndex = wbxmlStream.readByte();
      return;
    }
    localObject = new StringBuffer();
    int j;
    while ((i & 0x80) == 128)
    {
      str = Integer.toBinaryString(i & 0x7F);
      if (str.length() < 7)
      {
        j = str.length();
        i = 0;
        while (i < 7 - j)
        {
          ((StringBuffer)localObject).append('0');
          i += 1;
        }
      }
      ((StringBuffer)localObject).append(str);
      i = wbxmlStream.readByte();
    }
    String str = Integer.toBinaryString(i & 0x7F);
    if (str.length() < 7)
    {
      j = str.length();
      i = 0;
      while (i < 7 - j)
      {
        ((StringBuffer)localObject).append('0');
        i += 1;
      }
    }
    ((StringBuffer)localObject).append(str);
    i = Integer.valueOf(((StringBuffer)localObject).toString(), 2).intValue();
    publicId = PublicIdentifiers.getInstance().getPublicIdentifier(i);
  }
  
  private void readPublicIDFromStringTable()
  {
    int i = stringTable.toString().indexOf(new String(new char[] { '\000' }), publicIdIndex);
    if (i == -1)
    {
      publicId = stringTable.substring(publicIdIndex);
      return;
    }
    publicId = stringTable.substring(publicIdIndex, i);
  }
  
  private void setAttributes(Element paramElement)
    throws IOException
  {
    byte b = wbxmlStream.readByte();
    Object localObject1 = "";
    if (b != 1)
    {
      Object localObject2;
      if (isInlineStrToken(b))
      {
        writeAttrValueAsInlineStr(paramElement, (String)localObject1);
        localObject2 = localObject1;
      }
      for (;;)
      {
        b = wbxmlStream.readByte();
        localObject1 = localObject2;
        break;
        if (isEntityToken(b))
        {
          writeEntityAsAttribute(paramElement, (String)localObject1);
          localObject2 = localObject1;
        }
        else if (isStringTableReferenceToken(b))
        {
          writeAttributeFromStrTable(paramElement, (String)localObject1, wbxmlStream.readByte());
          localObject2 = localObject1;
        }
        else if (isAttrNameToken(b))
        {
          localObject2 = writeAttribute(paramElement, b);
        }
        else
        {
          localObject2 = localObject1;
          if (isAttrValueToken(b))
          {
            writeAttrValue(paramElement, (String)localObject1, b);
            localObject2 = localObject1;
          }
        }
      }
    }
  }
  
  private void writeAttrValue(Element paramElement, String paramString, byte paramByte)
    throws IOException
  {
    String str = paramElement.getAttribute(paramString);
    paramElement.setAttribute(paramString, str + tokenRepository.getAttributeValue(paramByte));
  }
  
  private void writeAttrValueAsInlineStr(Element paramElement, String paramString)
    throws IOException
  {
    int i = wbxmlStream.readByte();
    byte[] arrayOfByte = new byte['Ѐ'];
    int j = 0;
    while (i != 0)
    {
      arrayOfByte[j] = i;
      i = wbxmlStream.readByte();
      j += 1;
    }
    String str2 = paramElement.getAttribute(paramString);
    String str1 = str2;
    if (str2.equals("null")) {
      str1 = "";
    }
    paramElement.setAttribute(paramString, str1 + new String(arrayOfByte, 0, j, encoding));
  }
  
  private String writeAttribute(Element paramElement, byte paramByte)
  {
    int i = 0;
    Object localObject = tokenRepository.getAttributeNameAndPrefix(paramByte);
    String str1 = "";
    String str2 = localObject[0].toString();
    if (localObject[1] != null) {
      i = 1;
    }
    if (i != 0) {
      str1 = localObject[1].trim();
    }
    localObject = xmlDocument.createAttribute(str2);
    ((Attr)localObject).setValue(str1);
    paramElement.setAttributeNode((Attr)localObject);
    return str2;
  }
  
  private void writeAttributeFromStrTable(Element paramElement, String paramString, byte paramByte)
  {
    String str = paramElement.getAttribute(paramString);
    int i = stringTable.indexOf(new String(new char[] { '\000' }), paramByte);
    paramElement.setAttribute(paramString, str + stringTable.substring(paramByte, i));
  }
  
  private void writeChildElement(Element paramElement)
    throws IOException
  {
    byte b1 = wbxmlStream.readByte();
    while (b1 != 1)
    {
      byte b2 = getTokenValue(b1);
      if (isInlineStrToken(b1)) {
        writeContentAsInlineStr(paramElement);
      }
      label113:
      Object localObject;
      do
      {
        for (;;)
        {
          b1 = wbxmlStream.readByte();
          break;
          if (isEntityToken(b2))
          {
            writeEntityContent(paramElement);
          }
          else if (b1 == -61)
          {
            writeOpaqueContent(paramElement);
          }
          else if (isStringTableReferenceToken(b1))
          {
            writeContentFromStrTable(paramElement, wbxmlStream.readByte());
          }
          else
          {
            if (b2 != 0) {
              break label113;
            }
            TokenRepository.setCurrentCodepage(wbxmlStream.readByte());
          }
        }
        localObject = tokenRepository.getTagName(b2);
        localObject = xmlDocument.createElement((String)localObject);
        paramElement.appendChild((Node)localObject);
        if (hasAttributes(b1)) {
          setAttributes((Element)localObject);
        }
      } while (!hasContent(b1));
      writeChildElement((Element)localObject);
      b1 = wbxmlStream.readByte();
    }
  }
  
  private void writeContentAsInlineStr(Element paramElement)
    throws IOException
  {
    int i = wbxmlStream.readByte();
    Object localObject = new byte['Ѐ'];
    int j = 0;
    while (i != 0)
    {
      localObject[j] = i;
      i = wbxmlStream.readByte();
      j += 1;
    }
    localObject = new String((byte[])localObject, 0, j, encoding);
    paramElement.appendChild(xmlDocument.createTextNode((String)localObject));
  }
  
  private void writeContentFromStrTable(Element paramElement, int paramInt)
  {
    int i = stringTable.indexOf(new String(new char[] { '\000' }), paramInt);
    String str = stringTable.substring(paramInt, i);
    paramElement.appendChild(xmlDocument.createTextNode(str));
  }
  
  private void writeEntityAsAttribute(Element paramElement, String paramString)
    throws IOException
  {
    Object localObject2 = paramElement.getAttribute(paramString);
    Object localObject1 = localObject2;
    if (((String)localObject2).equals("null")) {
      localObject1 = "";
    }
    localObject2 = new StringBuffer((String)localObject1);
    for (int i = wbxmlStream.readByte(); (i & 0xFFFFFF80) == 128; i = wbxmlStream.readByte()) {
      ((StringBuffer)localObject2).append(Integer.toString((byte)(i & 0x7F), 2));
    }
    for (localObject1 = Integer.toString(i, 2); ((String)localObject1).length() < 7; localObject1 = "0" + (String)localObject1) {}
    ((StringBuffer)localObject2).append((String)localObject1);
    i = Integer.parseInt(((StringBuffer)localObject2).toString(), 2);
    paramElement.setAttribute(paramString, "&#" + i + ";");
  }
  
  private void writeEntityContent(Element paramElement)
    throws IOException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = wbxmlStream.readByte(); (i & 0xFFFFFF80) == 128; i = wbxmlStream.readByte()) {
      localStringBuffer.append(Integer.toString((byte)(i & 0x7F), 2));
    }
    for (String str = Integer.toString(i, 2); str.length() < 7; str = "0" + str) {}
    localStringBuffer.append(str);
    i = Integer.parseInt(localStringBuffer.toString(), 2);
    paramElement.appendChild(xmlDocument.createTextNode(Integer.toString(i)));
  }
  
  private void writeOpaqueContent(Element paramElement)
    throws IOException
  {
    int j = wbxmlStream.readByte();
    byte[] arrayOfByte = new byte[j];
    int i = 0;
    while (i < j)
    {
      arrayOfByte[i] = wbxmlStream.readByte();
      i += 1;
    }
    for (i = wbxmlStream.readByte(); i != 1; i = wbxmlStream.readByte()) {}
    new String(arrayOfByte, encoding);
    paramElement.appendChild(xmlDocument.createTextNode(""));
  }
  
  private void writeRootElement()
    throws IOException
  {
    byte b1 = wbxmlStream.readByte();
    byte b2 = getTokenValue(b1);
    String str = tokenRepository.getTagName(b2);
    try
    {
      DocumentBuilder localDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Object localObject = PublicIdentifiers.getInstance().getSystemIdentifier(publicId);
      localObject = localDocumentBuilder.getDOMImplementation().createDocumentType(str, publicId, (String)localObject);
      xmlDocument = localDocumentBuilder.getDOMImplementation().createDocument("", str, (DocumentType)localObject);
      if (hasAttributes(b1)) {
        setAttributes(xmlDocument.getDocumentElement());
      }
      if (hasContent(b1)) {
        writeChildElement(xmlDocument.getDocumentElement());
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public Document decode(InputStream paramInputStream)
  {
    wbxmlStream = new DataInputStream(paramInputStream);
    try
    {
      xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
      if (decodeProlog())
      {
        decodeBody();
        return xmlDocument;
      }
      return null;
    }
    catch (Exception paramInputStream)
    {
      for (;;)
      {
        paramInputStream.printStackTrace();
      }
    }
  }
  
  public boolean decodeProlog()
    throws IOException
  {
    wbxmlStream.readByte();
    readPublicID();
    int i = wbxmlStream.readByte();
    encoding = TransTable.getTable(mContext, "jwap_port/charsets").code2str(i);
    i = wbxmlStream.readByte();
    if (i < 0) {}
    byte[] arrayOfByte;
    do
    {
      return false;
      arrayOfByte = new byte[i];
    } while (wbxmlStream.read(arrayOfByte, 0, i) < i);
    try
    {
      stringTable = new String(arrayOfByte, encoding);
      if (publicIdIndex != -1) {
        readPublicIDFromStringTable();
      }
      tokenRepository = new TokenRepository(PublicIdentifiers.getInstance().getPublicIdentifierValueHex(publicId), mContext);
      return true;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
    return false;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.jwap_port.WBXMLDecoder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
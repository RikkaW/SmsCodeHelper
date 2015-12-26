package com.xiaomi.smack;

import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.PacketExtension;
import com.xiaomi.smack.packet.XMPPError;
import com.xiaomi.smack.util.StringUtils;
import java.util.Collection;
import java.util.Iterator;

public class XMBinder$BindResult
  extends Packet
{
  private Type type;
  
  public Type getType()
  {
    return type;
  }
  
  public void setType(Type paramType)
  {
    if (paramType == null)
    {
      type = Type.RESULT;
      return;
    }
    type = paramType;
  }
  
  public String toXML()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<bind ");
    if (getPacketID() != null) {
      localStringBuilder.append("id=\"" + getPacketID() + "\" ");
    }
    if (getTo() != null) {
      localStringBuilder.append("to=\"").append(StringUtils.escapeForXML(getTo())).append("\" ");
    }
    if (getFrom() != null) {
      localStringBuilder.append("from=\"").append(StringUtils.escapeForXML(getFrom())).append("\" ");
    }
    if (getChannelId() != null) {
      localStringBuilder.append(" chid=\"").append(StringUtils.escapeForXML(getChannelId())).append("\" ");
    }
    if (type == null) {
      localStringBuilder.append("type=\"result\">");
    }
    while (getExtensions() != null)
    {
      localObject = getExtensions().iterator();
      while (((Iterator)localObject).hasNext()) {
        localStringBuilder.append(((CommonPacketExtension)((Iterator)localObject).next()).toXML());
      }
      localStringBuilder.append("type=\"").append(getType()).append("\">");
    }
    Object localObject = getError();
    if (localObject != null) {
      localStringBuilder.append(((XMPPError)localObject).toXML());
    }
    localStringBuilder.append("</bind>");
    return localStringBuilder.toString();
  }
  
  public static class Type
  {
    public static final Type ERROR = new Type("error");
    public static final Type RESULT = new Type("result");
    private String value;
    
    private Type(String paramString)
    {
      value = paramString;
    }
    
    public static Type fromString(String paramString)
    {
      if (paramString == null) {}
      do
      {
        return null;
        paramString = paramString.toLowerCase();
        if (ERROR.toString().equals(paramString)) {
          return ERROR;
        }
      } while (!RESULT.toString().equals(paramString));
      return RESULT;
    }
    
    public String toString()
    {
      return value;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.XMBinder.BindResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
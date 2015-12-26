package com.xiaomi.smack.packet;

import android.os.Bundle;
import com.xiaomi.smack.util.StringUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class IQ
  extends Packet
{
  private final Map<String, String> attributes = new HashMap();
  private Type type = Type.GET;
  
  public IQ() {}
  
  public IQ(Bundle paramBundle)
  {
    super(paramBundle);
    if (paramBundle.containsKey("ext_iq_type")) {
      type = Type.fromString(paramBundle.getString("ext_iq_type"));
    }
  }
  
  public String getAttribute(String paramString)
  {
    try
    {
      paramString = (String)attributes.get(paramString);
      return paramString;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public String getChildElementXML()
  {
    return null;
  }
  
  public Type getType()
  {
    return type;
  }
  
  public void setAttribute(String paramString1, String paramString2)
  {
    try
    {
      attributes.put(paramString1, paramString2);
      return;
    }
    finally
    {
      paramString1 = finally;
      throw paramString1;
    }
  }
  
  public void setAttributes(Map<String, String> paramMap)
  {
    try
    {
      attributes.putAll(paramMap);
      return;
    }
    finally
    {
      paramMap = finally;
      throw paramMap;
    }
  }
  
  public void setType(Type paramType)
  {
    if (paramType == null)
    {
      type = Type.GET;
      return;
    }
    type = paramType;
  }
  
  public Bundle toBundle()
  {
    Bundle localBundle = super.toBundle();
    if (type != null) {
      localBundle.putString("ext_iq_type", type.toString());
    }
    return localBundle;
  }
  
  public String toXML()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<iq ");
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
      localStringBuilder.append("chid=\"").append(StringUtils.escapeForXML(getChannelId())).append("\" ");
    }
    Object localObject = attributes.entrySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      localStringBuilder.append(StringUtils.escapeForXML((String)localEntry.getKey())).append("=\"");
      localStringBuilder.append(StringUtils.escapeForXML((String)localEntry.getValue())).append("\" ");
    }
    if (type == null) {
      localStringBuilder.append("type=\"get\">");
    }
    for (;;)
    {
      localObject = getChildElementXML();
      if (localObject != null) {
        localStringBuilder.append((String)localObject);
      }
      localStringBuilder.append(getExtensionsXML());
      localObject = getError();
      if (localObject != null) {
        localStringBuilder.append(((XMPPError)localObject).toXML());
      }
      localStringBuilder.append("</iq>");
      return localStringBuilder.toString();
      localStringBuilder.append("type=\"").append(getType()).append("\">");
    }
  }
  
  public static class Type
  {
    public static final Type COMMAND = new Type("command");
    public static final Type ERROR;
    public static final Type GET = new Type("get");
    public static final Type RESULT;
    public static final Type SET = new Type("set");
    private String value;
    
    static
    {
      RESULT = new Type("result");
      ERROR = new Type("error");
    }
    
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
        if (GET.toString().equals(paramString)) {
          return GET;
        }
        if (SET.toString().equals(paramString)) {
          return SET;
        }
        if (ERROR.toString().equals(paramString)) {
          return ERROR;
        }
        if (RESULT.toString().equals(paramString)) {
          return RESULT;
        }
      } while (!COMMAND.toString().equals(paramString));
      return COMMAND;
    }
    
    public String toString()
    {
      return value;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.packet.IQ
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
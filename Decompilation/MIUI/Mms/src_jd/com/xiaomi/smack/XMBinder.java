package com.xiaomi.smack;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.CloudCoder;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.PacketExtension;
import com.xiaomi.smack.packet.XMPPError;
import com.xiaomi.smack.util.StringUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class XMBinder
{
  public void doBind(PushClientsManager.ClientLoginInfo paramClientLoginInfo, String paramString, Connection paramConnection)
    throws XMPPException
  {
    paramClientLoginInfo = new Bind(paramClientLoginInfo, paramString, paramConnection);
    paramConnection.sendPacket(paramClientLoginInfo);
    MyLog.warn("SMACK: bind id=" + paramClientLoginInfo.getPacketID());
  }
  
  public class Bind
    extends Packet
  {
    public Bind(PushClientsManager.ClientLoginInfo paramClientLoginInfo, String paramString, Connection paramConnection)
    {
      Object localObject = new HashMap();
      int i = paramConnection.getConnTryTimes();
      ((Map)localObject).put("challenge", paramString);
      ((Map)localObject).put("token", token);
      ((Map)localObject).put("chid", chid);
      ((Map)localObject).put("from", userId);
      ((Map)localObject).put("id", getPacketID());
      ((Map)localObject).put("to", "xiaomi.com");
      if (kick)
      {
        ((Map)localObject).put("kick", "1");
        this$1 = null;
        if (paramConnection.getConnectTime() > 0L)
        {
          this$1 = String.format("conn:%1$d,t:%2$d", new Object[] { Integer.valueOf(i), Long.valueOf(paramConnection.getConnectTime()) });
          ((Map)localObject).put("pf", XMBinder.this);
          paramConnection.resetConnTryTimes();
          paramConnection.resetConnectTime();
        }
        if (TextUtils.isEmpty(clientExtra)) {
          break label591;
        }
        ((Map)localObject).put("client_attrs", clientExtra);
        label209:
        if (TextUtils.isEmpty(cloudExtra)) {
          break label606;
        }
        ((Map)localObject).put("cloud_attrs", cloudExtra);
        label233:
        paramConnection = null;
        if ((!authMethod.equals("XIAOMI-PASS")) && (!authMethod.equals("XMPUSH-PASS"))) {
          break label621;
        }
        paramString = CloudCoder.generateSignature(authMethod, null, (Map)localObject, security);
        label275:
        setChannelId(chid);
        setFrom(userId);
        setTo("xiaomi.com");
        setPackageName(pkgName);
        paramConnection = new CommonPacketExtension("token", null, (String[])null, (String[])null);
        paramConnection.setText(token);
        addExtension(paramConnection);
        localObject = new CommonPacketExtension("kick", null, (String[])null, (String[])null);
        if (!kick) {
          break label642;
        }
        paramConnection = "1";
        label371:
        ((CommonPacketExtension)localObject).setText(paramConnection);
        addExtension((CommonPacketExtension)localObject);
        paramConnection = new CommonPacketExtension("sig", null, (String[])null, (String[])null);
        paramConnection.setText(paramString);
        addExtension(paramConnection);
        paramString = new CommonPacketExtension("method", null, (String[])null, (String[])null);
        if (TextUtils.isEmpty(authMethod)) {
          break label649;
        }
        paramString.setText(authMethod);
        label453:
        addExtension(paramString);
        paramConnection = new CommonPacketExtension("client_attrs", null, (String[])null, (String[])null);
        if (clientExtra != null) {
          break label658;
        }
        paramString = "";
        label488:
        paramConnection.setText(paramString);
        addExtension(paramConnection);
        paramString = new CommonPacketExtension("cloud_attrs", null, (String[])null, (String[])null);
        if (cloudExtra != null) {
          break label669;
        }
      }
      label591:
      label606:
      label621:
      label642:
      label649:
      label658:
      label669:
      for (paramClientLoginInfo = "";; paramClientLoginInfo = StringUtils.escapeForXML(cloudExtra))
      {
        paramString.setText(paramClientLoginInfo);
        addExtension(paramString);
        if (!TextUtils.isEmpty(XMBinder.this))
        {
          paramClientLoginInfo = new CommonPacketExtension("pf", null, (String[])null, (String[])null);
          paramClientLoginInfo.setText(XMBinder.this);
          addExtension(paramClientLoginInfo);
        }
        return;
        ((Map)localObject).put("kick", "0");
        break;
        ((Map)localObject).put("client_attrs", "");
        break label209;
        ((Map)localObject).put("cloud_attrs", "");
        break label233;
        paramString = paramConnection;
        if (!authMethod.equals("XIAOMI-SASL")) {
          break label275;
        }
        paramString = paramConnection;
        break label275;
        paramConnection = "0";
        break label371;
        paramString.setText("XIAOMI-SASL");
        break label453;
        paramString = StringUtils.escapeForXML(clientExtra);
        break label488;
      }
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
        localStringBuilder.append("chid=\"").append(StringUtils.escapeForXML(getChannelId())).append("\">");
      }
      if (getExtensions() != null)
      {
        Iterator localIterator = getExtensions().iterator();
        while (localIterator.hasNext()) {
          localStringBuilder.append(((CommonPacketExtension)localIterator.next()).toXML());
        }
      }
      localStringBuilder.append("</bind>");
      return localStringBuilder.toString();
    }
  }
  
  public static class BindResult
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
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.XMBinder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
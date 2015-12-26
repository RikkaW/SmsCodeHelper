package com.xiaomi.smack.packet;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XMPPError
{
  private List<CommonPacketExtension> applicationExtensions = null;
  private int code;
  private String condition;
  private String message;
  private String reason;
  private String type;
  
  public XMPPError(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, List<CommonPacketExtension> paramList)
  {
    code = paramInt;
    type = paramString1;
    reason = paramString2;
    condition = paramString3;
    message = paramString4;
    applicationExtensions = paramList;
  }
  
  public XMPPError(Bundle paramBundle)
  {
    code = paramBundle.getInt("ext_err_code");
    if (paramBundle.containsKey("ext_err_type")) {
      type = paramBundle.getString("ext_err_type");
    }
    condition = paramBundle.getString("ext_err_cond");
    reason = paramBundle.getString("ext_err_reason");
    message = paramBundle.getString("ext_err_msg");
    paramBundle = paramBundle.getParcelableArray("ext_exts");
    if (paramBundle != null)
    {
      applicationExtensions = new ArrayList(paramBundle.length);
      int j = paramBundle.length;
      int i = 0;
      while (i < j)
      {
        CommonPacketExtension localCommonPacketExtension = CommonPacketExtension.parseFromBundle((Bundle)paramBundle[i]);
        if (localCommonPacketExtension != null) {
          applicationExtensions.add(localCommonPacketExtension);
        }
        i += 1;
      }
    }
  }
  
  public XMPPError(Condition paramCondition)
  {
    init(paramCondition);
    message = null;
  }
  
  private void init(Condition paramCondition)
  {
    condition = value;
  }
  
  /* Error */
  public List<CommonPacketExtension> getExtensions()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 24	com/xiaomi/smack/packet/XMPPError:applicationExtensions	Ljava/util/List;
    //   6: ifnonnull +11 -> 17
    //   9: invokestatic 100	java/util/Collections:emptyList	()Ljava/util/List;
    //   12: astore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_1
    //   16: areturn
    //   17: aload_0
    //   18: getfield 24	com/xiaomi/smack/packet/XMPPError:applicationExtensions	Ljava/util/List;
    //   21: invokestatic 104	java/util/Collections:unmodifiableList	(Ljava/util/List;)Ljava/util/List;
    //   24: astore_1
    //   25: goto -12 -> 13
    //   28: astore_1
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_1
    //   32: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	33	0	this	XMPPError
    //   12	13	1	localList	List
    //   28	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	13	28	finally
    //   17	25	28	finally
  }
  
  public String getReason()
  {
    return reason;
  }
  
  public String getType()
  {
    return type;
  }
  
  public Bundle toBundle()
  {
    Bundle localBundle1 = new Bundle();
    if (type != null) {
      localBundle1.putString("ext_err_type", type);
    }
    localBundle1.putInt("ext_err_code", code);
    if (reason != null) {
      localBundle1.putString("ext_err_reason", reason);
    }
    if (condition != null) {
      localBundle1.putString("ext_err_cond", condition);
    }
    if (message != null) {
      localBundle1.putString("ext_err_msg", message);
    }
    if (applicationExtensions != null)
    {
      Bundle[] arrayOfBundle = new Bundle[applicationExtensions.size()];
      int i = 0;
      Iterator localIterator = applicationExtensions.iterator();
      while (localIterator.hasNext())
      {
        Bundle localBundle2 = ((CommonPacketExtension)localIterator.next()).toBundle();
        if (localBundle2 != null)
        {
          arrayOfBundle[i] = localBundle2;
          i += 1;
        }
      }
      localBundle1.putParcelableArray("ext_exts", arrayOfBundle);
    }
    return localBundle1;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (condition != null) {
      localStringBuilder.append(condition);
    }
    localStringBuilder.append("(").append(code).append(")");
    if (message != null) {
      localStringBuilder.append(" ").append(message);
    }
    return localStringBuilder.toString();
  }
  
  public String toXML()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<error code=\"").append(code).append("\"");
    if (type != null)
    {
      localStringBuilder.append(" type=\"");
      localStringBuilder.append(type);
      localStringBuilder.append("\"");
    }
    if (reason != null)
    {
      localStringBuilder.append(" reason=\"");
      localStringBuilder.append(reason);
      localStringBuilder.append("\"");
    }
    localStringBuilder.append(">");
    if (condition != null)
    {
      localStringBuilder.append("<").append(condition);
      localStringBuilder.append(" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\"/>");
    }
    if (message != null)
    {
      localStringBuilder.append("<text xml:lang=\"en\" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\">");
      localStringBuilder.append(message);
      localStringBuilder.append("</text>");
    }
    Iterator localIterator = getExtensions().iterator();
    while (localIterator.hasNext()) {
      localStringBuilder.append(((CommonPacketExtension)localIterator.next()).toXML());
    }
    localStringBuilder.append("</error>");
    return localStringBuilder.toString();
  }
  
  public static class Condition
  {
    public static final Condition bad_request;
    public static final Condition conflict;
    public static final Condition feature_not_implemented;
    public static final Condition forbidden;
    public static final Condition gone;
    public static final Condition interna_server_error = new Condition("internal-server-error");
    public static final Condition item_not_found;
    public static final Condition jid_malformed;
    public static final Condition no_acceptable;
    public static final Condition not_allowed;
    public static final Condition not_authorized;
    public static final Condition payment_required;
    public static final Condition recipient_unavailable;
    public static final Condition redirect;
    public static final Condition registration_required;
    public static final Condition remote_server_error;
    public static final Condition remote_server_not_found;
    public static final Condition remote_server_timeout;
    public static final Condition request_timeout = new Condition("request-timeout");
    public static final Condition resource_constraint;
    public static final Condition service_unavailable;
    public static final Condition subscription_required;
    public static final Condition undefined_condition;
    public static final Condition unexpected_request;
    private String value;
    
    static
    {
      forbidden = new Condition("forbidden");
      bad_request = new Condition("bad-request");
      conflict = new Condition("conflict");
      feature_not_implemented = new Condition("feature-not-implemented");
      gone = new Condition("gone");
      item_not_found = new Condition("item-not-found");
      jid_malformed = new Condition("jid-malformed");
      no_acceptable = new Condition("not-acceptable");
      not_allowed = new Condition("not-allowed");
      not_authorized = new Condition("not-authorized");
      payment_required = new Condition("payment-required");
      recipient_unavailable = new Condition("recipient-unavailable");
      redirect = new Condition("redirect");
      registration_required = new Condition("registration-required");
      remote_server_error = new Condition("remote-server-error");
      remote_server_not_found = new Condition("remote-server-not-found");
      remote_server_timeout = new Condition("remote-server-timeout");
      resource_constraint = new Condition("resource-constraint");
      service_unavailable = new Condition("service-unavailable");
      subscription_required = new Condition("subscription-required");
      undefined_condition = new Condition("undefined-condition");
      unexpected_request = new Condition("unexpected-request");
    }
    
    public Condition(String paramString)
    {
      value = paramString;
    }
    
    public String toString()
    {
      return value;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.packet.XMPPError
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
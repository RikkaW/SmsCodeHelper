package com.xiaomi.smack.packet;

public class XMPPError$Condition
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
  
  public XMPPError$Condition(String paramString)
  {
    value = paramString;
  }
  
  public String toString()
  {
    return value;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.packet.XMPPError.Condition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
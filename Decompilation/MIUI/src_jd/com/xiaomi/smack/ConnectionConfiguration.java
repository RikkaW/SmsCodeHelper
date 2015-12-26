package com.xiaomi.smack;

import java.util.Map;

public class ConnectionConfiguration
  implements Cloneable
{
  public static String XMPP_SERVER_HOST_T = "wcc-ml-test10.bj";
  public static String xmppHost = null;
  private String connectionPoint;
  private boolean debuggerEnabled = Connection.DEBUG_ENABLED;
  private String host;
  private HttpRequestProxy httpProxy;
  private int port;
  private boolean reconnectionAllowed = true;
  private String serviceName;
  
  public ConnectionConfiguration(Map<String, Integer> paramMap, int paramInt, String paramString, HttpRequestProxy paramHttpRequestProxy)
  {
    init(paramMap, paramInt, paramString, paramHttpRequestProxy);
  }
  
  public static final String getXmppServerHost()
  {
    if (xmppHost != null) {
      return xmppHost;
    }
    return "app.chat.xiaomi.net";
  }
  
  private void init(Map<String, Integer> paramMap, int paramInt, String paramString, HttpRequestProxy paramHttpRequestProxy)
  {
    host = getXmppServerHost();
    port = paramInt;
    serviceName = paramString;
    httpProxy = paramHttpRequestProxy;
  }
  
  public byte[] getConnectionBlob()
  {
    return null;
  }
  
  public String getConnectionPoint()
  {
    return connectionPoint;
  }
  
  public String getHost()
  {
    return host;
  }
  
  public int getPort()
  {
    return port;
  }
  
  public String getServiceName()
  {
    return serviceName;
  }
  
  public boolean isDebuggerEnabled()
  {
    return debuggerEnabled;
  }
  
  public void setConnectionPoint(String paramString)
  {
    connectionPoint = paramString;
  }
  
  public void setDebuggerEnabled(boolean paramBoolean)
  {
    debuggerEnabled = paramBoolean;
  }
  
  public void setHost(String paramString)
  {
    host = paramString;
  }
  
  public void setServiceName(String paramString)
  {
    serviceName = paramString;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.ConnectionConfiguration
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
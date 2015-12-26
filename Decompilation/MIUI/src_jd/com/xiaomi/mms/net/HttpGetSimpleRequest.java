package com.xiaomi.mms.net;

import java.net.HttpURLConnection;
import java.net.ProtocolException;

class HttpGetSimpleRequest
  extends HttpSimpleRequest
{
  protected HttpGetSimpleRequest(String paramString)
  {
    super(paramString);
  }
  
  public String getMethod()
  {
    return "GET";
  }
  
  protected void setupConnection()
    throws ProtocolException
  {
    super.setupConnection();
    getConnection().setDoOutput(false);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.net.HttpGetSimpleRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
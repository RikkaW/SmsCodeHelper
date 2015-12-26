package com.amap.api.services.core;

public class v
  extends Exception
{
  private String a = "未知的错误";
  private int b = -1;
  
  public v(String paramString)
  {
    super(paramString);
    a(paramString);
  }
  
  private void a(String paramString)
  {
    if ("IO 操作异常 - IOException".equals(paramString))
    {
      b = 21;
      return;
    }
    if ("socket 连接异常 - SocketException".equals(paramString))
    {
      b = 22;
      return;
    }
    if ("socket 连接超时 - SocketTimeoutException".equals(paramString))
    {
      b = 23;
      return;
    }
    if ("无效的参数 - IllegalArgumentException".equals(paramString))
    {
      b = 24;
      return;
    }
    if ("空指针异常 - NullPointException".equals(paramString))
    {
      b = 25;
      return;
    }
    if ("url异常 - MalformedURLException".equals(paramString))
    {
      b = 26;
      return;
    }
    if ("未知主机 - UnKnowHostException".equals(paramString))
    {
      b = 27;
      return;
    }
    if ("服务器连接失败 - UnknownServiceException".equals(paramString))
    {
      b = 28;
      return;
    }
    if ("协议解析错误 - ProtocolException".equals(paramString))
    {
      b = 29;
      return;
    }
    if ("http连接失败 - ConnectionException".equals(paramString))
    {
      b = 30;
      return;
    }
    if ("未知的错误".equals(paramString))
    {
      b = 31;
      return;
    }
    if ("key鉴权失败".equals(paramString))
    {
      b = 32;
      return;
    }
    if ("requeust is null".equals(paramString))
    {
      b = 1;
      return;
    }
    if ("request url is empty".equals(paramString))
    {
      b = 2;
      return;
    }
    if ("response is null".equals(paramString))
    {
      b = 3;
      return;
    }
    if ("thread pool has exception".equals(paramString))
    {
      b = 4;
      return;
    }
    if ("sdk name is invalid".equals(paramString))
    {
      b = 5;
      return;
    }
    if ("sdk info is null".equals(paramString))
    {
      b = 6;
      return;
    }
    if ("sdk packages is null".equals(paramString))
    {
      b = 7;
      return;
    }
    if ("线程池为空".equals(paramString))
    {
      b = 8;
      return;
    }
    b = -1;
  }
  
  public String a()
  {
    return a;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.v
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
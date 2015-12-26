package com.xiaomi.network;

import android.content.Context;
import com.xiaomi.channel.commonutils.network.NameValuePair;
import java.io.IOException;
import java.util.List;

public abstract class HttpProcessor
{
  private int httpRequest;
  
  public HttpProcessor(int paramInt)
  {
    httpRequest = paramInt;
  }
  
  public int getRequestType()
  {
    return httpRequest;
  }
  
  public boolean prepare(Context paramContext, String paramString, List<NameValuePair> paramList)
    throws IOException
  {
    return true;
  }
  
  public abstract String visit(Context paramContext, String paramString, List<NameValuePair> paramList)
    throws IOException;
}

/* Location:
 * Qualified Name:     com.xiaomi.network.HttpProcessor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
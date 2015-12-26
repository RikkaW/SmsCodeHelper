package com.xiaomi.network;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.xiaomi.channel.commonutils.network.NameValuePair;
import com.xiaomi.channel.commonutils.network.Network;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class HttpUtils$DefaultHttpGetProcessor
  extends HttpProcessor
{
  public HttpUtils$DefaultHttpGetProcessor()
  {
    super(1);
  }
  
  public String visit(Context paramContext, String paramString, List<NameValuePair> paramList)
    throws IOException
  {
    if (paramList == null) {
      return Network.downloadXml(paramContext, new URL(paramString));
    }
    paramString = Uri.parse(paramString).buildUpon();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      NameValuePair localNameValuePair = (NameValuePair)paramList.next();
      paramString.appendQueryParameter(localNameValuePair.getName(), localNameValuePair.getValue());
    }
    return Network.downloadXml(paramContext, new URL(paramString.toString()));
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.network.HttpUtils.DefaultHttpGetProcessor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
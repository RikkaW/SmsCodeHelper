package com.xiaomi.network;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.BasicNameValuePair;
import com.xiaomi.channel.commonutils.network.NameValuePair;
import com.xiaomi.channel.commonutils.network.Network;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HostManagerV2
  extends HostManager
{
  private final int DEFAULT_PORT = 80;
  private final int RESERVED_PORT = 5222;
  private int port = 80;
  
  protected HostManagerV2(Context paramContext, HostFilter paramHostFilter, HostManager.HttpGet paramHttpGet, String paramString)
  {
    this(paramContext, paramHostFilter, paramHttpGet, paramString, null, null);
  }
  
  protected HostManagerV2(Context paramContext, HostFilter paramHostFilter, HostManager.HttpGet paramHttpGet, String paramString1, String paramString2, String paramString3)
  {
    super(paramContext, paramHostFilter, paramHttpGet, paramString1, paramString2, paramString3);
    addReservedHost("resolver.msg.xiaomi.net", "resolver.msg.xiaomi.net:5222");
  }
  
  protected boolean checkHostMapping()
  {
    synchronized (mHostsMapping)
    {
      if (!hostLoaded)
      {
        hostLoaded = true;
        mHostsMapping.clear();
        try
        {
          String str = loadHosts();
          if (!TextUtils.isEmpty(str))
          {
            fromJSONObject(str);
            MyLog.info("loading the new hosts succeed");
            return true;
          }
        }
        catch (Throwable localThrowable)
        {
          MyLog.warn("load bucket failure: " + localThrowable.getMessage());
          return false;
        }
      }
      return true;
    }
  }
  
  protected void fromJSONObject(String paramString)
    throws JSONException
  {
    synchronized (mHostsMapping)
    {
      mHostsMapping.clear();
      paramString = new JSONObject(paramString);
      if (paramString.optInt("ver") != 2) {
        throw new JSONException("Bad version");
      }
    }
    paramString = paramString.optJSONArray("data");
    int i = 0;
    while (i < paramString.length())
    {
      Fallbacks localFallbacks = new Fallbacks().fromJSON(paramString.getJSONObject(i));
      mHostsMapping.put(localFallbacks.getHost(), localFallbacks);
      i += 1;
    }
  }
  
  protected String getHost()
  {
    return "resolver.msg.xiaomi.net";
  }
  
  protected String getRemoteFallbackJSON(ArrayList<String> paramArrayList, String paramString1, String paramString2)
    throws IOException
  {
    Object localObject1 = new ArrayList();
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new BasicNameValuePair("type", paramString1));
    if (paramString1.equals("wap")) {
      localArrayList.add(new BasicNameValuePair("connpt", Network.getActiveConnPoint(sAppContext)));
    }
    localArrayList.add(new BasicNameValuePair("uuid", paramString2));
    localArrayList.add(new BasicNameValuePair("list", join(paramArrayList, ",")));
    ??? = getLocalFallback("resolver.msg.xiaomi.net");
    Object localObject3 = String.format(Locale.US, "http://%1$s/gslb/?ver=3.0", new Object[] { "resolver.msg.xiaomi.net:" + port });
    Object localObject4;
    if (??? == null)
    {
      ((ArrayList)localObject1).add(localObject3);
      synchronized (mReservedHosts)
      {
        localObject3 = ((ArrayList)mReservedHosts.get("resolver.msg.xiaomi.net")).iterator();
        if (((Iterator)localObject3).hasNext())
        {
          localObject4 = (String)((Iterator)localObject3).next();
          ((ArrayList)localObject1).add(String.format(Locale.US, "http://%1$s/gslb/?ver=3.0", new Object[] { localObject4 }));
        }
      }
      ??? = null;
      localObject3 = ((ArrayList)localObject1).iterator();
      localObject1 = ???;
    }
    for (;;)
    {
      if (!((Iterator)localObject3).hasNext()) {
        break label412;
      }
      localObject1 = Uri.parse((String)((Iterator)localObject3).next()).buildUpon();
      ??? = localArrayList.iterator();
      for (;;)
      {
        if (((Iterator)???).hasNext())
        {
          localObject4 = (NameValuePair)((Iterator)???).next();
          ((Uri.Builder)localObject1).appendQueryParameter(((NameValuePair)localObject4).getName(), ((NameValuePair)localObject4).getValue());
          continue;
          localObject1 = ((Fallback)???).getUrls((String)localObject3);
          break;
        }
      }
      try
      {
        if (sHttpGetter == null) {
          return Network.downloadXml(sAppContext, new URL(((Uri.Builder)localObject1).toString()));
        }
        localObject1 = sHttpGetter.doGet(((Uri.Builder)localObject1).toString());
        return (String)localObject1;
      }
      catch (IOException localIOException) {}
    }
    label412:
    if (localIOException != null) {
      return super.getRemoteFallbackJSON(paramArrayList, paramString1, paramString2);
    }
    return null;
  }
  
  public void persist()
  {
    synchronized (mHostsMapping)
    {
      try
      {
        BufferedWriter localBufferedWriter = new BufferedWriter(new OutputStreamWriter(sAppContext.openFileOutput(getProcessName(), 0)));
        String str = toJSONObject().toString();
        if (!TextUtils.isEmpty(str)) {
          localBufferedWriter.write(str);
        }
        localBufferedWriter.close();
      }
      catch (Exception localException)
      {
        for (;;)
        {
          MyLog.warn("persist bucket failure: " + localException.getMessage());
        }
      }
      return;
    }
  }
  
  public void purge()
  {
    synchronized (mHostsMapping)
    {
      Iterator localIterator1 = mHostsMapping.values().iterator();
      if (localIterator1.hasNext()) {
        ((Fallbacks)localIterator1.next()).purge(true);
      }
    }
    int i = 0;
    while (i == 0)
    {
      int j = 1;
      Iterator localIterator2 = mHostsMapping.keySet().iterator();
      String str;
      do
      {
        i = j;
        if (!localIterator2.hasNext()) {
          break;
        }
        str = (String)localIterator2.next();
      } while (!((Fallbacks)mHostsMapping.get(str)).getFallbacks().isEmpty());
      mHostsMapping.remove(str);
      i = 0;
    }
  }
  
  protected JSONObject toJSONObject()
    throws JSONException
  {
    synchronized (mHostsMapping)
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("ver", 2);
      localJSONObject.put("data", toJSON());
      return localJSONObject;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.network.HostManagerV2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
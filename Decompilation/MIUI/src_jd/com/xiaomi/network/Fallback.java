package com.xiaomi.network;

import android.text.TextUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fallback
{
  public String city;
  public String country;
  private long effectDuration = 86400000L;
  private ArrayList<WeightedHost> fallbackHosts = new ArrayList();
  public String host;
  public String ip;
  public String isp;
  private String mDomain = "s.mi1.cc";
  private String mISP;
  private double mPercent = 0.1D;
  public String networkLabel;
  public String province;
  private long timestamp;
  protected String xforward;
  
  public Fallback(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("the host is empty");
    }
    timestamp = System.currentTimeMillis();
    fallbackHosts.add(new WeightedHost(paramString, -1));
    networkLabel = HostManager.getInstance().getActiveNetworkLabel();
    host = paramString;
  }
  
  private void deleteWeightedHost(String paramString)
  {
    try
    {
      Iterator localIterator = fallbackHosts.iterator();
      while (localIterator.hasNext()) {
        if (TextUtils.equals(nexthost, paramString)) {
          localIterator.remove();
        }
      }
    }
    finally {}
  }
  
  public void accessHost(String paramString, int paramInt, long paramLong1, long paramLong2, Exception paramException)
  {
    accessHost(paramString, new AccessHistory(paramInt, paramLong1, paramLong2, paramException));
  }
  
  public void accessHost(String paramString, AccessHistory paramAccessHistory)
  {
    try
    {
      Iterator localIterator = fallbackHosts.iterator();
      while (localIterator.hasNext())
      {
        WeightedHost localWeightedHost = (WeightedHost)localIterator.next();
        if (TextUtils.equals(paramString, host)) {
          localWeightedHost.addAccessHistory(paramAccessHistory);
        }
      }
      return;
    }
    finally {}
  }
  
  void addHost(WeightedHost paramWeightedHost)
  {
    try
    {
      deleteWeightedHost(host);
      fallbackHosts.add(paramWeightedHost);
      return;
    }
    finally
    {
      paramWeightedHost = finally;
      throw paramWeightedHost;
    }
  }
  
  public void addPreferredHost(String[] paramArrayOfString)
  {
    for (;;)
    {
      int i;
      try
      {
        i = fallbackHosts.size() - 1;
        Object localObject;
        if (i >= 0)
        {
          int k = paramArrayOfString.length;
          j = 0;
          if (j < k)
          {
            localObject = paramArrayOfString[j];
            if (!TextUtils.equals(fallbackHosts.get(i)).host, (CharSequence)localObject)) {
              break label166;
            }
            fallbackHosts.remove(i);
          }
        }
        else
        {
          i = 0;
          localObject = fallbackHosts.iterator();
          if (!((Iterator)localObject).hasNext()) {
            break label173;
          }
          WeightedHost localWeightedHost = (WeightedHost)((Iterator)localObject).next();
          if (weight <= i) {
            continue;
          }
          i = weight;
          continue;
          if (j < paramArrayOfString.length)
          {
            addHost(new WeightedHost(paramArrayOfString[j], paramArrayOfString.length + i - j));
            j += 1;
            continue;
          }
          return;
        }
      }
      finally {}
      i -= 1;
      continue;
      label166:
      j += 1;
      continue;
      label173:
      int j = 0;
    }
  }
  
  public void failedHost(String paramString, long paramLong1, long paramLong2, Exception paramException)
  {
    accessHost(paramString, -1, paramLong1, paramLong2, paramException);
  }
  
  public void failedUrl(String paramString, long paramLong1, long paramLong2, Exception paramException)
  {
    try
    {
      failedHost(new URL(paramString).getHost(), paramLong1, paramLong2, paramException);
      return;
    }
    catch (MalformedURLException paramString) {}
  }
  
  public Fallback fromJSON(JSONObject paramJSONObject)
    throws JSONException
  {
    try
    {
      networkLabel = paramJSONObject.optString("net");
      effectDuration = paramJSONObject.getLong("ttl");
      mPercent = paramJSONObject.getDouble("pct");
      timestamp = paramJSONObject.getLong("ts");
      city = paramJSONObject.optString("city");
      province = paramJSONObject.optString("prv");
      country = paramJSONObject.optString("cty");
      isp = paramJSONObject.optString("isp");
      ip = paramJSONObject.optString("ip");
      host = paramJSONObject.optString("host");
      xforward = paramJSONObject.optString("xf");
      paramJSONObject = paramJSONObject.getJSONArray("fbs");
      int i = 0;
      while (i < paramJSONObject.length())
      {
        addHost(new WeightedHost().fromJSON(paramJSONObject.getJSONObject(i)));
        i += 1;
      }
      return this;
    }
    finally {}
  }
  
  public ArrayList<String> getHosts()
  {
    try
    {
      ArrayList localArrayList = getHosts(false);
      return localArrayList;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public ArrayList<String> getHosts(boolean paramBoolean)
  {
    for (;;)
    {
      ArrayList localArrayList;
      int i;
      WeightedHost localWeightedHost;
      try
      {
        WeightedHost[] arrayOfWeightedHost = new WeightedHost[fallbackHosts.size()];
        fallbackHosts.toArray(arrayOfWeightedHost);
        Arrays.sort(arrayOfWeightedHost);
        localArrayList = new ArrayList();
        int j = arrayOfWeightedHost.length;
        i = 0;
        if (i >= j) {
          break label132;
        }
        localWeightedHost = arrayOfWeightedHost[i];
        if (paramBoolean)
        {
          localArrayList.add(host);
        }
        else
        {
          int k = host.indexOf(":");
          if (k != -1) {
            localArrayList.add(host.substring(0, k));
          }
        }
      }
      finally {}
      localArrayList.add(host);
      break label137;
      label132:
      return localArrayList;
      label137:
      i += 1;
    }
  }
  
  /* Error */
  public String getISP()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 264	com/xiaomi/network/Fallback:mISP	Ljava/lang/String;
    //   6: invokestatic 51	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   9: ifne +12 -> 21
    //   12: aload_0
    //   13: getfield 264	com/xiaomi/network/Fallback:mISP	Ljava/lang/String;
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: areturn
    //   21: aload_0
    //   22: getfield 203	com/xiaomi/network/Fallback:isp	Ljava/lang/String;
    //   25: invokestatic 51	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   28: ifeq +10 -> 38
    //   31: ldc_w 266
    //   34: astore_1
    //   35: goto -18 -> 17
    //   38: aload_0
    //   39: iconst_5
    //   40: anewarray 252	java/lang/String
    //   43: dup
    //   44: iconst_0
    //   45: aload_0
    //   46: getfield 203	com/xiaomi/network/Fallback:isp	Ljava/lang/String;
    //   49: aastore
    //   50: dup
    //   51: iconst_1
    //   52: aload_0
    //   53: getfield 196	com/xiaomi/network/Fallback:province	Ljava/lang/String;
    //   56: aastore
    //   57: dup
    //   58: iconst_2
    //   59: aload_0
    //   60: getfield 192	com/xiaomi/network/Fallback:city	Ljava/lang/String;
    //   63: aastore
    //   64: dup
    //   65: iconst_3
    //   66: aload_0
    //   67: getfield 200	com/xiaomi/network/Fallback:country	Ljava/lang/String;
    //   70: aastore
    //   71: dup
    //   72: iconst_4
    //   73: aload_0
    //   74: getfield 206	com/xiaomi/network/Fallback:ip	Ljava/lang/String;
    //   77: aastore
    //   78: ldc_w 268
    //   81: invokestatic 272	com/xiaomi/network/HostManager:join	([Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   84: putfield 264	com/xiaomi/network/Fallback:mISP	Ljava/lang/String;
    //   87: aload_0
    //   88: getfield 264	com/xiaomi/network/Fallback:mISP	Ljava/lang/String;
    //   91: astore_1
    //   92: goto -75 -> 17
    //   95: astore_1
    //   96: aload_0
    //   97: monitorexit
    //   98: aload_1
    //   99: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	100	0	this	Fallback
    //   16	76	1	str	String
    //   95	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	17	95	finally
    //   21	31	95	finally
    //   38	92	95	finally
  }
  
  public double getPercent()
  {
    if (mPercent < 1.0E-5D) {
      return 0.1D;
    }
    return mPercent;
  }
  
  public ArrayList<String> getUrls(String paramString)
    throws MalformedURLException
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("the url is empty.");
    }
    paramString = new URL(paramString);
    ArrayList localArrayList;
    if (TextUtils.equals(paramString.getHost(), host))
    {
      localArrayList = new ArrayList();
      Iterator localIterator = getHosts(true).iterator();
      while (localIterator.hasNext())
      {
        Host localHost = Host.parse((String)localIterator.next(), paramString.getPort());
        localArrayList.add(new URL(paramString.getProtocol(), localHost.getHost(), localHost.getPort(), paramString.getFile()).toString());
      }
    }
    throw new IllegalArgumentException("the url is not supported by the fallback");
    return localArrayList;
  }
  
  ArrayList<WeightedHost> getWeightedHost()
  {
    return fallbackHosts;
  }
  
  public boolean isEffective()
  {
    return System.currentTimeMillis() - timestamp < effectDuration;
  }
  
  boolean isExpired()
  {
    long l1 = 864000000L;
    if (864000000L < effectDuration) {
      l1 = effectDuration;
    }
    long l2 = System.currentTimeMillis();
    return (l2 - timestamp > l1) || ((l2 - timestamp > effectDuration) && (networkLabel.startsWith("WIFI-")));
  }
  
  public boolean match()
  {
    return TextUtils.equals(networkLabel, HostManager.getInstance().getActiveNetworkLabel());
  }
  
  public boolean match(Fallback paramFallback)
  {
    return TextUtils.equals(networkLabel, networkLabel);
  }
  
  public void setDomainName(String paramString)
  {
    mDomain = paramString;
  }
  
  public void setEffectiveDuration(long paramLong)
  {
    if (paramLong <= 0L) {
      throw new IllegalArgumentException("the duration is invalid " + paramLong);
    }
    effectDuration = paramLong;
  }
  
  public void setPercent(double paramDouble)
  {
    mPercent = paramDouble;
  }
  
  public void succeedHost(String paramString, long paramLong1, long paramLong2)
  {
    accessHost(paramString, 0, paramLong1, paramLong2, null);
  }
  
  public void succeedUrl(String paramString, long paramLong1, long paramLong2)
  {
    try
    {
      succeedHost(new URL(paramString).getHost(), paramLong1, paramLong2);
      return;
    }
    catch (MalformedURLException paramString) {}
  }
  
  public JSONObject toJSON()
    throws JSONException
  {
    try
    {
      JSONObject localJSONObject1 = new JSONObject();
      localJSONObject1.put("net", networkLabel);
      localJSONObject1.put("ttl", effectDuration);
      localJSONObject1.put("pct", mPercent);
      localJSONObject1.put("ts", timestamp);
      localJSONObject1.put("city", city);
      localJSONObject1.put("prv", province);
      localJSONObject1.put("cty", country);
      localJSONObject1.put("isp", isp);
      localJSONObject1.put("ip", ip);
      localJSONObject1.put("host", host);
      localJSONObject1.put("xf", xforward);
      JSONArray localJSONArray = new JSONArray();
      Iterator localIterator = fallbackHosts.iterator();
      while (localIterator.hasNext()) {
        localJSONArray.put(((WeightedHost)localIterator.next()).toJSON());
      }
      localJSONObject2.put("fbs", localJSONArray);
    }
    finally {}
    return localJSONObject2;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(networkLabel);
    localStringBuilder.append("\n");
    localStringBuilder.append(getISP());
    Iterator localIterator = fallbackHosts.iterator();
    while (localIterator.hasNext())
    {
      WeightedHost localWeightedHost = (WeightedHost)localIterator.next();
      localStringBuilder.append("\n");
      localStringBuilder.append(localWeightedHost.toString());
    }
    localStringBuilder.append("\n");
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.network.Fallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
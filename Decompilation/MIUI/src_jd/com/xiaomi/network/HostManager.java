package com.xiaomi.network;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.BasicNameValuePair;
import com.xiaomi.channel.commonutils.network.NameValuePair;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.common.logger.thrift.mfs.HostInfo;
import com.xiaomi.common.logger.thrift.mfs.HttpApi;
import com.xiaomi.common.logger.thrift.mfs.LandNodeInfo;
import com.xiaomi.common.logger.thrift.mfs.Location;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HostManager
{
  private static HostManagerFactory factory;
  protected static boolean hostLoaded = false;
  protected static Map<String, ArrayList<String>> mReservedHosts = new HashMap();
  private static String sAppName;
  private static String sAppVersion;
  private static HostManager sInstance;
  private final long MAX_REQUEST_FAILURE_CNT = 15L;
  private String currentISP = "isp_prov_city_country_ip";
  private UploadHostStatHelper.HttpRecordCallback httpRecordCallback = new UploadHostStatHelper.HttpRecordCallback()
  {
    public List<HttpApi> generateStat()
    {
      try
      {
        ArrayList localArrayList = generateHostStats();
        return localArrayList;
      }
      catch (JSONException localJSONException) {}
      return null;
    }
    
    public double getPercentage()
    {
      Fallback localFallback = getFallbacksByHost("f3.mi-stat.gslb.mi-idc.com");
      double d = 0.1D;
      if (localFallback != null) {
        d = localFallback.getPercent();
      }
      return d;
    }
  };
  private long lastRemoteRequestTimestamp = 0L;
  protected Map<String, Fallbacks> mHostsMapping = new HashMap();
  private long remoteRequestFailureCount = 0L;
  protected Context sAppContext;
  private HostFilter sHostFilter;
  protected HttpGet sHttpGetter;
  private String sUserId = "0";
  
  protected HostManager(Context paramContext, HostFilter paramHostFilter, HttpGet paramHttpGet, String paramString)
  {
    this(paramContext, paramHostFilter, paramHttpGet, paramString, null, null);
  }
  
  protected HostManager(Context paramContext, HostFilter paramHostFilter, HttpGet paramHttpGet, String paramString1, String paramString2, String paramString3)
  {
    sAppContext = paramContext.getApplicationContext();
    if (sAppContext == null) {
      sAppContext = paramContext;
    }
    sHttpGetter = paramHttpGet;
    if (paramHostFilter == null)
    {
      sHostFilter = new HostFilter()
      {
        public boolean accept(String paramAnonymousString)
        {
          return true;
        }
      };
      sUserId = paramString1;
      if (paramString2 == null) {
        break label132;
      }
      label108:
      sAppName = paramString2;
      if (paramString3 == null) {
        break label141;
      }
    }
    for (;;)
    {
      sAppVersion = paramString3;
      return;
      sHostFilter = paramHostFilter;
      break;
      label132:
      paramString2 = paramContext.getPackageName();
      break label108;
      label141:
      paramString3 = getVersionName();
    }
  }
  
  public static void addReservedHost(String paramString1, String paramString2)
  {
    ArrayList localArrayList = (ArrayList)mReservedHosts.get(paramString1);
    Map localMap = mReservedHosts;
    if (localArrayList == null) {}
    for (;;)
    {
      try
      {
        localArrayList = new ArrayList();
      }
      finally
      {
        continue;
      }
      try
      {
        localArrayList.add(paramString2);
        mReservedHosts.put(paramString1, localArrayList);
        return;
      }
      finally {}
      if (!localArrayList.contains(paramString2)) {
        localArrayList.add(paramString2);
      }
    }
    throw paramString1;
  }
  
  public static HostManager getInstance()
  {
    try
    {
      if (sInstance == null) {
        throw new IllegalStateException("the host manager is not initialized yet.");
      }
    }
    finally {}
    HostManager localHostManager = sInstance;
    return localHostManager;
  }
  
  private String getVersionName()
  {
    try
    {
      Object localObject = sAppContext.getPackageManager().getPackageInfo(sAppContext.getPackageName(), 16384);
      if (localObject != null)
      {
        localObject = versionName;
        return (String)localObject;
      }
    }
    catch (Exception localException) {}
    return "0";
  }
  
  /* Error */
  public static void init(Context paramContext, HostFilter paramHostFilter, HttpGet paramHttpGet, String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 134	com/xiaomi/network/HostManager:sInstance	Lcom/xiaomi/network/HostManager;
    //   6: ifnonnull +55 -> 61
    //   9: getstatic 161	com/xiaomi/network/HostManager:factory	Lcom/xiaomi/network/HostManager$HostManagerFactory;
    //   12: ifnonnull +53 -> 65
    //   15: new 2	com/xiaomi/network/HostManager
    //   18: dup
    //   19: aload_0
    //   20: aload_1
    //   21: aload_2
    //   22: aload_3
    //   23: aload 4
    //   25: aload 5
    //   27: invokespecial 60	com/xiaomi/network/HostManager:<init>	(Landroid/content/Context;Lcom/xiaomi/network/HostFilter;Lcom/xiaomi/network/HostManager$HttpGet;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   30: putstatic 134	com/xiaomi/network/HostManager:sInstance	Lcom/xiaomi/network/HostManager;
    //   33: getstatic 134	com/xiaomi/network/HostManager:sInstance	Lcom/xiaomi/network/HostManager;
    //   36: ifnull +25 -> 61
    //   39: invokestatic 166	com/xiaomi/network/UploadHostStatHelper:getInstance	()Lcom/xiaomi/network/UploadHostStatHelper;
    //   42: ifnonnull +7 -> 49
    //   45: aload_0
    //   46: invokestatic 169	com/xiaomi/network/UploadHostStatHelper:init	(Landroid/content/Context;)V
    //   49: invokestatic 166	com/xiaomi/network/UploadHostStatHelper:getInstance	()Lcom/xiaomi/network/UploadHostStatHelper;
    //   52: getstatic 134	com/xiaomi/network/HostManager:sInstance	Lcom/xiaomi/network/HostManager;
    //   55: getfield 84	com/xiaomi/network/HostManager:httpRecordCallback	Lcom/xiaomi/network/UploadHostStatHelper$HttpRecordCallback;
    //   58: invokevirtual 173	com/xiaomi/network/UploadHostStatHelper:addCallBack	(Lcom/xiaomi/network/UploadHostStatHelper$HttpRecordCallback;)V
    //   61: ldc 2
    //   63: monitorexit
    //   64: return
    //   65: getstatic 161	com/xiaomi/network/HostManager:factory	Lcom/xiaomi/network/HostManager$HostManagerFactory;
    //   68: aload_0
    //   69: aload_1
    //   70: aload_2
    //   71: aload_3
    //   72: invokeinterface 177 5 0
    //   77: putstatic 134	com/xiaomi/network/HostManager:sInstance	Lcom/xiaomi/network/HostManager;
    //   80: goto -47 -> 33
    //   83: astore_0
    //   84: ldc 2
    //   86: monitorexit
    //   87: aload_0
    //   88: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	89	0	paramContext	Context
    //   0	89	1	paramHostFilter	HostFilter
    //   0	89	2	paramHttpGet	HttpGet
    //   0	89	3	paramString1	String
    //   0	89	4	paramString2	String
    //   0	89	5	paramString3	String
    // Exception table:
    //   from	to	target	type
    //   3	33	83	finally
    //   33	49	83	finally
    //   49	61	83	finally
    //   65	80	83	finally
  }
  
  public static <T> String join(Collection<T> paramCollection, String paramString)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      localStringBuilder.append(paramCollection.next());
      if (paramCollection.hasNext()) {
        localStringBuilder.append(paramString);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String join(String[] paramArrayOfString, String paramString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramArrayOfString[0]);
    int i = 1;
    while (i < paramArrayOfString.length)
    {
      localStringBuilder.append(paramString);
      localStringBuilder.append(paramArrayOfString[i]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  private String processNetwork(String paramString)
  {
    String str;
    if (TextUtils.isEmpty(paramString)) {
      str = "unknown";
    }
    do
    {
      return str;
      str = paramString;
    } while (!paramString.startsWith("WIFI"));
    return "WIFI";
  }
  
  private ArrayList<Fallback> requestRemoteFallbacks(ArrayList<String> paramArrayList)
  {
    purge();
    Object localObject2;
    synchronized (mHostsMapping)
    {
      checkHostMapping();
      localObject2 = mHostsMapping.keySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (String)((Iterator)localObject2).next();
        if (!paramArrayList.contains(localObject3)) {
          paramArrayList.add(localObject3);
        }
      }
    }
    synchronized (mReservedHosts)
    {
      localObject2 = mReservedHosts.keySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (String)((Iterator)localObject2).next();
        if (!paramArrayList.contains(localObject3)) {
          paramArrayList.add(localObject3);
        }
      }
    }
    if (!paramArrayList.contains(getHost())) {
      paramArrayList.add(getHost());
    }
    Object localObject3 = new ArrayList(paramArrayList.size());
    int i = 0;
    while (i < paramArrayList.size())
    {
      ((ArrayList)localObject3).add(null);
      i += 1;
    }
    for (;;)
    {
      int j;
      try
      {
        if (!Network.isWIFIConnected(sAppContext)) {
          break label855;
        }
        ??? = "wifi";
        localObject2 = getRemoteFallbackJSON(paramArrayList, (String)???, sUserId);
        if (!TextUtils.isEmpty((CharSequence)localObject2))
        {
          localObject2 = new JSONObject((String)localObject2);
          if ("OK".equalsIgnoreCase(((JSONObject)localObject2).getString("S")))
          {
            JSONObject localJSONObject1 = ((JSONObject)localObject2).getJSONObject("R");
            String str2 = localJSONObject1.getString("province");
            String str3 = localJSONObject1.getString("city");
            String str4 = localJSONObject1.getString("isp");
            String str5 = localJSONObject1.getString("ip");
            String str6 = localJSONObject1.getString("country");
            JSONObject localJSONObject2 = localJSONObject1.getJSONObject((String)???);
            localObject2 = ???;
            if (((String)???).equals("wap")) {
              localObject2 = getActiveNetworkLabel();
            }
            MyLog.warn("get bucket: " + str6 + " " + str2 + " " + " isp:" + str4 + " " + (String)localObject2 + " hosts:" + localJSONObject2.toString());
            i = 0;
            if (i < paramArrayList.size())
            {
              localObject2 = (String)paramArrayList.get(i);
              ??? = localJSONObject2.optJSONArray((String)localObject2);
              if (??? == null)
              {
                MyLog.warn("no bucket found for " + (String)localObject2);
              }
              else
              {
                localObject2 = new Fallback((String)localObject2);
                j = 0;
                if (j < ((JSONArray)???).length())
                {
                  String str7 = ((JSONArray)???).getString(j);
                  if (TextUtils.isEmpty(str7)) {
                    break label863;
                  }
                  ((Fallback)localObject2).addHost(new WeightedHost(str7, ((JSONArray)???).length() - j));
                  break label863;
                }
                ((ArrayList)localObject3).set(i, localObject2);
                country = str6;
                province = str2;
                isp = str4;
                ip = str5;
                city = str3;
                if (localJSONObject1.has("stat-percent")) {
                  ((Fallback)localObject2).setPercent(localJSONObject1.getDouble("stat-percent"));
                }
                if (localJSONObject1.has("stat-domain")) {
                  ((Fallback)localObject2).setDomainName(localJSONObject1.getString("stat-domain"));
                }
                if (localJSONObject1.has("ttl")) {
                  ((Fallback)localObject2).setEffectiveDuration(localJSONObject1.getInt("ttl") * 1000L);
                }
                setCurrentISP(((Fallback)localObject2).getISP());
              }
            }
          }
        }
      }
      catch (JSONException localJSONException)
      {
        MyLog.warn("failed to get bucket" + localJSONException.getMessage());
        i = 0;
        if (i < paramArrayList.size())
        {
          Fallback localFallback = (Fallback)((ArrayList)localObject3).get(i);
          if (localFallback != null) {
            updateFallbacks((String)paramArrayList.get(i), localFallback);
          }
          i += 1;
          continue;
        }
      }
      catch (IOException localIOException)
      {
        MyLog.warn("failed to get bucket" + localIOException.getMessage());
        continue;
      }
      catch (Exception localException)
      {
        MyLog.warn("failed to get bucket" + localException.getMessage());
        continue;
        persist();
        return (ArrayList<Fallback>)localObject3;
      }
      i += 1;
      continue;
      label855:
      String str1 = "wap";
      continue;
      label863:
      j += 1;
    }
  }
  
  public static void setHostManagerFactory(HostManagerFactory paramHostManagerFactory)
  {
    try
    {
      factory = paramHostManagerFactory;
      if ((UploadHostStatHelper.getInstance() != null) && (sInstance != null)) {
        UploadHostStatHelper.getInstance().removeCallBack(sInstancehttpRecordCallback);
      }
      sInstance = null;
      return;
    }
    finally {}
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
            fromJSON(str);
            MyLog.warn("loading the new hosts succeed");
            return true;
          }
        }
        catch (Throwable localThrowable)
        {
          MyLog.warn("load host exception " + localThrowable.getMessage());
          return false;
        }
      }
      return true;
    }
  }
  
  public void clear()
  {
    synchronized (mHostsMapping)
    {
      mHostsMapping.clear();
      return;
    }
  }
  
  protected void fromJSON(String paramString)
    throws JSONException
  {
    synchronized (mHostsMapping)
    {
      mHostsMapping.clear();
      paramString = new JSONArray(paramString);
      int i = 0;
      while (i < paramString.length())
      {
        Fallbacks localFallbacks = new Fallbacks().fromJSON(paramString.getJSONObject(i));
        mHostsMapping.put(localFallbacks.getHost(), localFallbacks);
        i += 1;
      }
      return;
    }
  }
  
  public ArrayList<HttpApi> generateHostStats()
    throws JSONException
  {
    for (;;)
    {
      Object localObject4;
      Object localObject3;
      ArrayList localArrayList2;
      int k;
      int m;
      synchronized (mHostsMapping)
      {
        localObject4 = new HashMap();
        Iterator localIterator1 = mHostsMapping.keySet().iterator();
        if (!localIterator1.hasNext()) {
          break label634;
        }
        Object localObject1 = (String)localIterator1.next();
        localObject1 = (Fallbacks)mHostsMapping.get(localObject1);
        if (localObject1 == null) {
          continue;
        }
        Iterator localIterator2 = ((Fallbacks)localObject1).getFallbacks().iterator();
        if (!localIterator2.hasNext()) {
          continue;
        }
        Object localObject5 = (Fallback)localIterator2.next();
        localObject3 = (HttpApi)((Map)localObject4).get(((Fallback)localObject5).getISP());
        localObject1 = localObject3;
        if (localObject3 == null)
        {
          localObject1 = new HttpApi();
          ((HttpApi)localObject1).setCategory("httpapi");
          ((HttpApi)localObject1).setClient_ip(ip);
          ((HttpApi)localObject1).setNetwork(processNetwork(networkLabel));
          ((HttpApi)localObject1).setUuid(sUserId);
          ((HttpApi)localObject1).setVersion(sAppVersion);
          ((HttpApi)localObject1).setVersion_type(sAppName);
          ((HttpApi)localObject1).setApp_name(sAppContext.getPackageName());
          ((HttpApi)localObject1).setApp_version(getVersionName());
          localObject3 = new Location();
          ((Location)localObject3).setCity(city);
          ((Location)localObject3).setContry(country);
          ((Location)localObject3).setProvince(province);
          ((Location)localObject3).setIsp(isp);
          ((HttpApi)localObject1).setLocation((Location)localObject3);
          ((Map)localObject4).put(((Fallback)localObject5).getISP(), localObject1);
        }
        localObject3 = new HostInfo();
        ((HostInfo)localObject3).setHost(host);
        localArrayList2 = new ArrayList();
        localObject5 = ((Fallback)localObject5).getWeightedHost().iterator();
        if (((Iterator)localObject5).hasNext())
        {
          Object localObject6 = (WeightedHost)((Iterator)localObject5).next();
          Object localObject7 = ((WeightedHost)localObject6).getUnTouchedAccessHistory();
          if (((ArrayList)localObject7).isEmpty()) {
            continue;
          }
          LandNodeInfo localLandNodeInfo = new LandNodeInfo();
          localLandNodeInfo.setIp(host);
          int j = 0;
          k = 0;
          long l = 0L;
          int i = 0;
          localObject6 = new HashMap();
          localObject7 = ((ArrayList)localObject7).iterator();
          if (((Iterator)localObject7).hasNext())
          {
            Object localObject8 = (AccessHistory)((Iterator)localObject7).next();
            if (((AccessHistory)localObject8).getWeight() >= 0)
            {
              j += 1;
              l += ((AccessHistory)localObject8).getCost();
              i = (int)(i + ((AccessHistory)localObject8).getSize());
              continue;
            }
            localObject8 = ((AccessHistory)localObject8).getException();
            if (TextUtils.isEmpty((CharSequence)localObject8)) {
              break label704;
            }
            if (!((Map)localObject6).containsKey(localObject8)) {
              break label711;
            }
            m = ((Integer)((Map)localObject6).get(localObject8)).intValue() + 1;
            ((Map)localObject6).put(localObject8, Integer.valueOf(m));
            break label704;
          }
          localLandNodeInfo.setExp_info((Map)localObject6);
          localLandNodeInfo.setSuccess_count(j);
          localLandNodeInfo.setFailed_count(k);
          localLandNodeInfo.setDuration(l);
          localLandNodeInfo.setSize(i);
          localArrayList2.add(localLandNodeInfo);
        }
      }
      if (!localArrayList2.isEmpty())
      {
        ((HostInfo)localObject3).setLand_node_info(localArrayList2);
        ((HttpApi)localObject2).addToHost_info((HostInfo)localObject3);
        continue;
        label634:
        ArrayList localArrayList1 = new ArrayList();
        localObject3 = ((Map)localObject4).values().iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject4 = (HttpApi)((Iterator)localObject3).next();
          if (((HttpApi)localObject4).getHost_infoSize() > 0) {
            localArrayList1.add(localObject4);
          }
        }
        return localArrayList1;
        label704:
        k += 1;
        continue;
        label711:
        m = 1;
      }
    }
  }
  
  public String getActiveNetworkLabel()
  {
    if (sAppContext == null) {
      return "unknown";
    }
    try
    {
      Object localObject = (ConnectivityManager)sAppContext.getSystemService("connectivity");
      if (localObject == null) {
        return "unknown";
      }
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      if (localObject == null) {
        return "unknown";
      }
      if (((NetworkInfo)localObject).getType() == 1)
      {
        localObject = (WifiManager)sAppContext.getSystemService("wifi");
        if ((localObject != null) && (((WifiManager)localObject).getConnectionInfo() != null)) {
          return "WIFI-" + ((WifiManager)localObject).getConnectionInfo().getSSID();
        }
      }
      else
      {
        localObject = ((NetworkInfo)localObject).getTypeName() + "-" + ((NetworkInfo)localObject).getSubtypeName();
        return (String)localObject;
      }
    }
    catch (Exception localException) {}
    return "unknown";
  }
  
  public Fallback getFallbacksByHost(String paramString)
  {
    return getFallbacksByHost(paramString, true);
  }
  
  public Fallback getFallbacksByHost(String paramString, boolean paramBoolean)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("the host is empty");
    }
    Object localObject;
    if (!sHostFilter.accept(paramString)) {
      localObject = null;
    }
    final Fallback localFallback;
    do
    {
      return (Fallback)localObject;
      localFallback = getLocalFallback(paramString);
      if (localFallback == null) {
        break;
      }
      localObject = localFallback;
    } while (localFallback.isEffective());
    if ((paramBoolean) && (Network.hasNetwork(sAppContext)))
    {
      localObject = requestRemoteFallback(paramString);
      if (localObject != null) {
        return (Fallback)localObject;
      }
    }
    new Fallback(paramString)
    {
      Fallback local = localFallback;
      
      public void accessHost(String paramAnonymousString, AccessHistory paramAnonymousAccessHistory)
      {
        try
        {
          if (local != null) {
            local.accessHost(paramAnonymousString, paramAnonymousAccessHistory);
          }
          return;
        }
        finally
        {
          paramAnonymousString = finally;
          throw paramAnonymousString;
        }
      }
      
      /* Error */
      public ArrayList<String> getHosts(boolean paramAnonymousBoolean)
      {
        // Byte code:
        //   0: aload_0
        //   1: monitorenter
        //   2: new 39	java/util/ArrayList
        //   5: dup
        //   6: invokespecial 42	java/util/ArrayList:<init>	()V
        //   9: astore_3
        //   10: aload_0
        //   11: getfield 25	com/xiaomi/network/HostManager$3:local	Lcom/xiaomi/network/Fallback;
        //   14: ifnull +16 -> 30
        //   17: aload_3
        //   18: aload_0
        //   19: getfield 25	com/xiaomi/network/HostManager$3:local	Lcom/xiaomi/network/Fallback;
        //   22: iconst_1
        //   23: invokevirtual 44	com/xiaomi/network/Fallback:getHosts	(Z)Ljava/util/ArrayList;
        //   26: invokevirtual 48	java/util/ArrayList:addAll	(Ljava/util/Collection;)Z
        //   29: pop
        //   30: getstatic 52	com/xiaomi/network/HostManager:mReservedHosts	Ljava/util/Map;
        //   33: astore_2
        //   34: aload_2
        //   35: monitorenter
        //   36: getstatic 52	com/xiaomi/network/HostManager:mReservedHosts	Ljava/util/Map;
        //   39: aload_0
        //   40: getfield 55	com/xiaomi/network/HostManager$3:host	Ljava/lang/String;
        //   43: invokeinterface 61 2 0
        //   48: checkcast 39	java/util/ArrayList
        //   51: astore 4
        //   53: aload 4
        //   55: ifnull +10 -> 65
        //   58: aload_3
        //   59: aload 4
        //   61: invokevirtual 48	java/util/ArrayList:addAll	(Ljava/util/Collection;)Z
        //   64: pop
        //   65: aload_2
        //   66: monitorexit
        //   67: aload_0
        //   68: monitorexit
        //   69: aload_3
        //   70: areturn
        //   71: astore_3
        //   72: aload_2
        //   73: monitorexit
        //   74: aload_3
        //   75: athrow
        //   76: astore_2
        //   77: aload_0
        //   78: monitorexit
        //   79: aload_2
        //   80: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	81	0	this	3
        //   0	81	1	paramAnonymousBoolean	boolean
        //   76	4	2	localObject1	Object
        //   9	61	3	localArrayList1	ArrayList
        //   71	4	3	localObject2	Object
        //   51	9	4	localArrayList2	ArrayList
        // Exception table:
        //   from	to	target	type
        //   36	53	71	finally
        //   58	65	71	finally
        //   65	67	71	finally
        //   72	74	71	finally
        //   2	30	76	finally
        //   30	36	76	finally
        //   74	76	76	finally
      }
      
      public boolean isEffective()
      {
        return false;
      }
    };
  }
  
  public Fallback getFallbacksByURL(String paramString)
    throws MalformedURLException
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("the url is empty");
    }
    return getFallbacksByHost(new URL(paramString).getHost(), true);
  }
  
  protected String getHost()
  {
    return "resolver.gslb.mi-idc.com";
  }
  
  protected Fallback getLocalFallback(String paramString)
  {
    synchronized (mHostsMapping)
    {
      checkHostMapping();
      paramString = (Fallbacks)mHostsMapping.get(paramString);
      if (paramString != null)
      {
        paramString = paramString.getFallback();
        if (paramString != null) {
          return paramString;
        }
      }
    }
    return null;
  }
  
  protected String getProcessName()
  {
    Object localObject = ((ActivityManager)sAppContext.getSystemService("activity")).getRunningAppProcesses();
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
        if (pid == Process.myPid()) {
          return processName;
        }
      }
    }
    return "com.xiaomi";
  }
  
  protected String getRemoteFallbackJSON(ArrayList<String> paramArrayList, String paramString1, String paramString2)
    throws IOException
  {
    Object localObject = new ArrayList();
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new BasicNameValuePair("type", paramString1));
    localArrayList.add(new BasicNameValuePair("uuid", paramString2));
    localArrayList.add(new BasicNameValuePair("list", join(paramArrayList, ",")));
    paramArrayList = getLocalFallback("resolver.gslb.mi-idc.com");
    paramString1 = String.format("http://%1$s/gslb/gslb/getbucket.asp?ver=3.0", new Object[] { "resolver.gslb.mi-idc.com" });
    if (paramArrayList == null)
    {
      ((ArrayList)localObject).add(paramString1);
      paramArrayList = (ArrayList<String>)localObject;
      paramString1 = null;
      paramString2 = paramArrayList.iterator();
      paramArrayList = paramString1;
    }
    for (;;)
    {
      if (!paramString2.hasNext()) {
        break label259;
      }
      paramArrayList = Uri.parse((String)paramString2.next()).buildUpon();
      paramString1 = localArrayList.iterator();
      for (;;)
      {
        if (paramString1.hasNext())
        {
          localObject = (NameValuePair)paramString1.next();
          paramArrayList.appendQueryParameter(((NameValuePair)localObject).getName(), ((NameValuePair)localObject).getValue());
          continue;
          paramArrayList = paramArrayList.getUrls(paramString1);
          break;
        }
      }
      try
      {
        if (sHttpGetter == null) {
          return Network.downloadXml(sAppContext, new URL(paramArrayList.toString()));
        }
        paramArrayList = sHttpGetter.doGet(paramArrayList.toString());
        return paramArrayList;
      }
      catch (IOException paramArrayList) {}
    }
    label259:
    if (paramArrayList != null) {
      throw paramArrayList;
    }
    return null;
  }
  
  /* Error */
  protected String loadHosts()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_3
    //   4: aload_2
    //   5: astore_1
    //   6: new 771	java/io/File
    //   9: dup
    //   10: aload_0
    //   11: getfield 92	com/xiaomi/network/HostManager:sAppContext	Landroid/content/Context;
    //   14: invokevirtual 775	android/content/Context:getFilesDir	()Ljava/io/File;
    //   17: aload_0
    //   18: invokevirtual 777	com/xiaomi/network/HostManager:getProcessName	()Ljava/lang/String;
    //   21: invokespecial 780	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   24: astore 4
    //   26: aload_2
    //   27: astore_1
    //   28: aload 4
    //   30: invokevirtual 783	java/io/File:isFile	()Z
    //   33: ifeq +101 -> 134
    //   36: aload_2
    //   37: astore_1
    //   38: new 785	java/io/BufferedReader
    //   41: dup
    //   42: new 787	java/io/InputStreamReader
    //   45: dup
    //   46: new 789	java/io/FileInputStream
    //   49: dup
    //   50: aload 4
    //   52: invokespecial 792	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   55: invokespecial 795	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   58: invokespecial 798	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   61: astore_2
    //   62: new 189	java/lang/StringBuilder
    //   65: dup
    //   66: invokespecial 190	java/lang/StringBuilder:<init>	()V
    //   69: astore_1
    //   70: aload_2
    //   71: invokevirtual 801	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   74: astore_3
    //   75: aload_3
    //   76: ifnull +47 -> 123
    //   79: aload_1
    //   80: aload_3
    //   81: invokevirtual 210	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: pop
    //   85: goto -15 -> 70
    //   88: astore_3
    //   89: aload_2
    //   90: astore_1
    //   91: new 189	java/lang/StringBuilder
    //   94: dup
    //   95: invokespecial 190	java/lang/StringBuilder:<init>	()V
    //   98: ldc_w 439
    //   101: invokevirtual 210	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: aload_3
    //   105: invokevirtual 440	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   108: invokevirtual 210	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: invokevirtual 213	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   114: invokestatic 325	com/xiaomi/channel/commonutils/logger/MyLog:warn	(Ljava/lang/String;)V
    //   117: aload_2
    //   118: invokestatic 806	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   121: aconst_null
    //   122: areturn
    //   123: aload_1
    //   124: invokevirtual 213	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   127: astore_1
    //   128: aload_2
    //   129: invokestatic 806	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   132: aload_1
    //   133: areturn
    //   134: aconst_null
    //   135: invokestatic 806	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   138: goto -17 -> 121
    //   141: astore_2
    //   142: aload_1
    //   143: invokestatic 806	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   146: aload_2
    //   147: athrow
    //   148: astore_3
    //   149: aload_2
    //   150: astore_1
    //   151: aload_3
    //   152: astore_2
    //   153: goto -11 -> 142
    //   156: astore_1
    //   157: aload_3
    //   158: astore_2
    //   159: aload_1
    //   160: astore_3
    //   161: goto -72 -> 89
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	164	0	this	HostManager
    //   5	146	1	localObject1	Object
    //   156	4	1	localThrowable1	Throwable
    //   1	128	2	localBufferedReader	java.io.BufferedReader
    //   141	9	2	localObject2	Object
    //   152	7	2	localObject3	Object
    //   3	78	3	str	String
    //   88	17	3	localThrowable2	Throwable
    //   148	10	3	localObject4	Object
    //   160	1	3	localObject5	Object
    //   24	27	4	localFile	java.io.File
    // Exception table:
    //   from	to	target	type
    //   62	70	88	java/lang/Throwable
    //   70	75	88	java/lang/Throwable
    //   79	85	88	java/lang/Throwable
    //   123	128	88	java/lang/Throwable
    //   6	26	141	finally
    //   28	36	141	finally
    //   38	62	141	finally
    //   91	117	141	finally
    //   62	70	148	finally
    //   70	75	148	finally
    //   79	85	148	finally
    //   123	128	148	finally
    //   6	26	156	java/lang/Throwable
    //   28	36	156	java/lang/Throwable
    //   38	62	156	java/lang/Throwable
  }
  
  /* Error */
  public void persist()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 242	com/xiaomi/network/HostManager:purge	()V
    //   4: aload_0
    //   5: getfield 63	com/xiaomi/network/HostManager:mHostsMapping	Ljava/util/Map;
    //   8: astore_1
    //   9: aload_1
    //   10: monitorenter
    //   11: new 808	java/io/BufferedWriter
    //   14: dup
    //   15: new 810	java/io/OutputStreamWriter
    //   18: dup
    //   19: aload_0
    //   20: getfield 92	com/xiaomi/network/HostManager:sAppContext	Landroid/content/Context;
    //   23: aload_0
    //   24: invokevirtual 777	com/xiaomi/network/HostManager:getProcessName	()Ljava/lang/String;
    //   27: iconst_0
    //   28: invokevirtual 814	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   31: invokespecial 817	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   34: invokespecial 820	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   37: astore_2
    //   38: aload_0
    //   39: invokevirtual 824	com/xiaomi/network/HostManager:toJSON	()Lorg/json/JSONArray;
    //   42: invokevirtual 825	org/json/JSONArray:toString	()Ljava/lang/String;
    //   45: astore_3
    //   46: aload_3
    //   47: invokestatic 223	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   50: ifne +8 -> 58
    //   53: aload_2
    //   54: aload_3
    //   55: invokevirtual 828	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   58: aload_2
    //   59: invokevirtual 831	java/io/BufferedWriter:close	()V
    //   62: aload_1
    //   63: monitorexit
    //   64: return
    //   65: astore_2
    //   66: aload_2
    //   67: invokevirtual 834	java/io/IOException:printStackTrace	()V
    //   70: goto -8 -> 62
    //   73: astore_2
    //   74: aload_1
    //   75: monitorexit
    //   76: aload_2
    //   77: athrow
    //   78: astore_2
    //   79: aload_2
    //   80: invokevirtual 835	org/json/JSONException:printStackTrace	()V
    //   83: goto -21 -> 62
    //   86: astore_2
    //   87: aload_2
    //   88: invokevirtual 836	java/lang/Exception:printStackTrace	()V
    //   91: goto -29 -> 62
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	94	0	this	HostManager
    //   37	22	2	localBufferedWriter	java.io.BufferedWriter
    //   65	2	2	localIOException	IOException
    //   73	4	2	localObject	Object
    //   78	2	2	localJSONException	JSONException
    //   86	2	2	localException	Exception
    //   45	10	3	str	String
    // Exception table:
    //   from	to	target	type
    //   11	58	65	java/io/IOException
    //   58	62	65	java/io/IOException
    //   11	58	73	finally
    //   58	62	73	finally
    //   62	64	73	finally
    //   66	70	73	finally
    //   74	76	73	finally
    //   79	83	73	finally
    //   87	91	73	finally
    //   11	58	78	org/json/JSONException
    //   58	62	78	org/json/JSONException
    //   11	58	86	java/lang/Exception
    //   58	62	86	java/lang/Exception
  }
  
  public void purge()
  {
    synchronized (mHostsMapping)
    {
      Iterator localIterator1 = mHostsMapping.values().iterator();
      if (localIterator1.hasNext()) {
        ((Fallbacks)localIterator1.next()).purge(false);
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
  
  public void refreshFallbacks()
  {
    ArrayList localArrayList;
    synchronized (mHostsMapping)
    {
      checkHostMapping();
      localArrayList = new ArrayList(mHostsMapping.keySet());
    }
    try
    {
      i = localArrayList.size() - 1;
      if (i >= 0)
      {
        Fallbacks localFallbacks = (Fallbacks)mHostsMapping.get(localArrayList.get(i));
        if ((localFallbacks != null) && (localFallbacks.getFallback() != null)) {
          localArrayList.remove(i);
        }
      }
      else
      {
        ??? = requestRemoteFallbacks(localArrayList);
        i = 0;
        while (i < localArrayList.size())
        {
          if (((ArrayList)???).get(i) != null) {
            updateFallbacks((String)localArrayList.get(i), (Fallback)((ArrayList)???).get(i));
          }
          i += 1;
          continue;
          localObject1 = finally;
          throw ((Throwable)localObject1);
        }
        return;
      }
    }
    finally
    {
      for (;;)
      {
        int i;
        continue;
        i -= 1;
      }
    }
  }
  
  protected Fallback requestRemoteFallback(String paramString)
  {
    if (System.currentTimeMillis() - lastRemoteRequestTimestamp > remoteRequestFailureCount * 60L * 1000L)
    {
      lastRemoteRequestTimestamp = System.currentTimeMillis();
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramString);
      paramString = (Fallback)requestRemoteFallbacks(localArrayList).get(0);
      if (paramString != null)
      {
        remoteRequestFailureCount = 0L;
        return paramString;
      }
      if (remoteRequestFailureCount < 15L) {
        remoteRequestFailureCount += 1L;
      }
    }
    return null;
  }
  
  public void setCurrentISP(String paramString)
  {
    currentISP = paramString;
  }
  
  protected JSONArray toJSON()
    throws JSONException
  {
    synchronized (mHostsMapping)
    {
      JSONArray localJSONArray1 = new JSONArray();
      Iterator localIterator = mHostsMapping.values().iterator();
      if (localIterator.hasNext()) {
        localJSONArray1.put(((Fallbacks)localIterator.next()).toJSON());
      }
    }
    return localJSONArray2;
  }
  
  public void updateFallbacks(String paramString, Fallback paramFallback)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramFallback == null)) {
      throw new IllegalArgumentException("the argument is invalid " + paramString + ", " + paramFallback);
    }
    if (sHostFilter.accept(paramString)) {
      synchronized (mHostsMapping)
      {
        checkHostMapping();
        if (mHostsMapping.containsKey(paramString))
        {
          ((Fallbacks)mHostsMapping.get(paramString)).addFallback(paramFallback);
          return;
        }
        Fallbacks localFallbacks = new Fallbacks(paramString);
        localFallbacks.addFallback(paramFallback);
        mHostsMapping.put(paramString, localFallbacks);
      }
    }
  }
  
  public static abstract interface HostManagerFactory
  {
    public abstract HostManager createHostManager(Context paramContext, HostFilter paramHostFilter, HostManager.HttpGet paramHttpGet, String paramString);
  }
  
  public static abstract interface HttpGet
  {
    public abstract String doGet(String paramString)
      throws IOException;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.network.HostManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
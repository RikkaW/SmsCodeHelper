package cn.com.xy.sms.util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import bx;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.ParseItemManager;
import cn.com.xy.sms.sdk.db.TrainManager;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.NetWebUtil;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import cn.com.xy.sms.sdk.util.DateUtils;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.PopupMsgManager;
import cn.com.xy.sms.sdk.util.PopupUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.d;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseManager
{
  public static final String UPDATE_ICCID_INFO_CACHE_ACTION = "cn.com.xy.sms.iccidinfo.action";
  private static final String a = "yyyyMMdd";
  private static ExecutorService b = Executors.newFixedThreadPool(1);
  private static final long c = 604800000L;
  private static boolean d = false;
  private static BroadcastReceiver e = new f();
  private static long f = 0L;
  private static String g = null;
  private static HashMap<String, Long> h = new HashMap();
  private static ExecutorService i = Executors.newFixedThreadPool(1);
  public static long mins = 1L;
  
  static Map<String, Object> a(Context paramContext, String paramString1, String paramString2, String paramString3, long paramLong, Map<String, String> paramMap)
  {
    if (paramContext == null) {
      try
      {
        throw new Exception(" Context is null.");
      }
      finally {}
    }
    if (paramString1 == null) {
      throw new Exception(" phoneNumber is null.");
    }
    if (paramString3 == null) {
      throw new Exception(" smsContent is null.");
    }
    Object localObject2 = null;
    Object localObject1 = paramMap;
    if (paramMap == null) {
      localObject1 = new HashMap();
    }
    if (!((Map)localObject1).containsKey("ALLOW_VERCODE_MSG")) {
      ((Map)localObject1).put("ALLOW_VERCODE_MSG", "true");
    }
    if ((((Map)localObject1).containsKey("ALLOW_PERSONAL_MSG")) && (!DuoquUtils.getSdkDoAction().isContact(paramContext, paramString1))) {
      ((Map)localObject1).put("ALLOW_PERSONAL_MSG", "false");
    }
    ((Map)localObject1).put("version", DexUtil.getSceneVersion());
    ((Map)localObject1).put("channel", SysParamEntityManager.getStringParam(paramContext, "CHANNEL"));
    ((Map)localObject1).put("smsCenterNum", paramString2);
    if (g == null) {
      g = IccidLocationUtil.getProvince();
    }
    if (StringUtils.isNull(g)) {
      ((Map)localObject1).put("provice", g);
    }
    int j = SysParamEntityManager.getIntParam(Constant.getContext(), "RECOGNIZE_LEVEL");
    if (j != -1) {
      ((Map)localObject1).put("RECOGNIZE_LEVEL", j);
    }
    paramMap = paramString3.trim();
    paramContext = (Context)localObject2;
    if (d.c(Constant.getPARSE_PATH(), "parseUtilMain", "jar"))
    {
      paramContext = (Context)localObject2;
      if (cn.com.xy.sms.sdk.net.util.l.a(Constant.getJarPath()).booleanValue())
      {
        paramString3 = DexUtil.parseMsgToMap(paramString1, paramMap, (Map)localObject1);
        paramContext = paramString3;
        if (paramString3 == null)
        {
          LogManager.e("parseMsg", "result is null phoneNumber:" + paramString1 + " smsContent: " + paramMap + " msgID: " + (String)((Map)localObject1).get("msgId"), null);
          paramContext = paramString3;
        }
      }
    }
    if (!ParseItemManager.isInitData())
    {
      paramContext = new HashMap();
      paramContext.put("parseStatu", Integer.valueOf(-1));
    }
    for (;;)
    {
      return paramContext;
      if (System.currentTimeMillis() >= f + DexUtil.getUpdateCycleByType(7, 600000L))
      {
        cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(2, new String[0]));
        f = System.currentTimeMillis();
      }
      if (paramContext != null)
      {
        paramString3 = (String)((Map)localObject1).get("simIndex");
        if (!StringUtils.isNull(paramString3)) {
          paramContext.put("simIndex", paramString3);
        }
        paramString3 = (String)paramContext.get("title_num");
        if (!StringUtils.isNull(paramString3))
        {
          localObject1 = (String)((Map)localObject1).get("simIccid");
          if (!StringUtils.isNull((String)localObject1)) {
            cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(1, new String[] { "simIccid", localObject1, "receiveNum", paramString1, "sms", paramMap, "centerNum", paramString2, "sceneId", paramString3 }));
          }
          cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(6, new String[] { "titleNo", paramString3 }));
          cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(3, new String[] { "titleNo", paramString3 }));
          cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(8, new String[] { "titleNo", paramString3 }));
          cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(5, new String[] { "titleNo", paramString3, "type", "0" }));
        }
      }
      cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(9, new String[] { "num", paramString1, "msg", paramMap, "cnum", paramString2, "smsTime", String.valueOf(paramLong) }));
    }
  }
  
  private static JSONObject a(String paramString1, String paramString2, String paramString3)
  {
    if (StringUtils.isNull(paramString1)) {
      return null;
    }
    paramString1 = paramString1.split("/");
    if ((paramString1.length == 1) || ((StringUtils.isNull(paramString2)) && (StringUtils.isNull(paramString3)))) {
      return TrainManager.queryTrainInfo(paramString1[0]);
    }
    int k = paramString1.length;
    int j = 0;
    for (;;)
    {
      if (j >= k) {
        return null;
      }
      JSONObject localJSONObject = TrainManager.queryTrainInfo(paramString1[j]);
      String str = (String)JsonUtil.getValueFromJsonObject(localJSONObject, "station_list");
      if ((!StringUtils.isNull(str)) && (checkStationList(str, paramString2, paramString3, false))) {
        return localJSONObject;
      }
      j += 1;
    }
  }
  
  private static void a(Context paramContext)
  {
    try
    {
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("cn.com.xy.sms.iccidinfo.action");
      paramContext.registerReceiver(e, localIntentFilter);
      return;
    }
    catch (Throwable paramContext)
    {
      LogManager.e("initSdk", "registerReceiver error " + paramContext.getMessage(), paramContext);
    }
  }
  
  private static boolean a(long paramLong)
  {
    return System.currentTimeMillis() - paramLong > DexUtil.getUpdateCycleByType(21, 604800000L);
  }
  
  public static String addQueryTrafficAndChargeToMenuData(String paramString, Map<String, String> paramMap)
  {
    return cn.com.xy.sms.sdk.db.entity.a.e.a(paramString, paramMap);
  }
  
  public static void addViewVersion(String paramString1, String paramString2)
  {
    paramString1 = paramString2 + "_" + paramString1;
    SysParamEntityManager.insertOrUpdateKeyValue(Constant.getContext(), "bubbleViewVersion", paramString1, null);
    SysParamEntityManager.cacheMap.put("bubbleViewVersion", paramString1);
  }
  
  private static BitmapDrawable b(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2, SdkCallBack paramSdkCallBack)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    Object localObject1 = localObject3;
    try
    {
      if (!NetUtil.checkAccessNetWork(2))
      {
        paramContext = (Context)localObject2;
        if (paramSdkCallBack != null)
        {
          localObject1 = localObject3;
          paramSdkCallBack.execute(null);
          return null;
        }
      }
      else
      {
        localObject1 = localObject3;
        h.put(paramString4, Long.valueOf(System.currentTimeMillis()));
        localObject1 = localObject3;
        if (d.g(paramString1, paramString2, paramString3) == 0)
        {
          localObject1 = localObject3;
          h.remove(paramString4);
          localObject1 = localObject3;
          paramString1 = ViewUtil.createBitmapByPath2(paramContext, paramString4, paramInt1, paramInt2);
          if (paramSdkCallBack != null)
          {
            localObject1 = paramString1;
            paramSdkCallBack.execute(new Object[] { paramString1 });
          }
          paramContext = paramString1;
          if (paramString1 != null)
          {
            localObject1 = paramString1;
            h.remove(paramString4);
            return paramString1;
          }
        }
        else
        {
          paramContext = (Context)localObject2;
          if (paramSdkCallBack != null)
          {
            localObject1 = localObject3;
            paramSdkCallBack.execute(null);
            paramContext = (Context)localObject2;
          }
        }
      }
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return (BitmapDrawable)localObject1;
  }
  
  private static void b(String paramString1, String paramString2, String paramString3, String paramString4, SdkCallBack paramSdkCallBack)
  {
    if (StringUtils.isNull(paramString2)) {
      XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { paramString1, null, paramString2, paramString3, paramString4, Boolean.valueOf(false) });
    }
    for (;;)
    {
      return;
      JSONObject localJSONObject;
      if (StringUtils.isNull(paramString2)) {
        localJSONObject = null;
      }
      for (;;)
      {
        l2 = 0L;
        try
        {
          localObject = (String)JsonUtil.getValueFromJsonObject(localJSONObject, "data_time");
          l1 = l2;
          if (localObject != null) {
            l1 = Long.parseLong((String)localObject);
          }
        }
        catch (NumberFormatException localNumberFormatException)
        {
          for (;;)
          {
            Object localObject;
            int j;
            int k;
            long l1 = l2;
          }
        }
        if (localJSONObject != null) {
          if (System.currentTimeMillis() - l1 > DexUtil.getUpdateCycleByType(21, 604800000L))
          {
            j = 1;
            if ((j != 0) && (NetUtil.checkAccessNetWork(2))) {}
          }
          else
          {
            try
            {
              localObject = (String)JsonUtil.getValueFromJsonObject(localJSONObject, "station_list");
              if (!StringUtils.isNull((String)localObject)) {
                localJSONObject.put("station_list", new JSONArray((String)localObject));
              }
              XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { paramString1, localJSONObject, paramString2, paramString3, paramString4, Boolean.valueOf(false) });
              return;
              localObject = paramString2.split("/");
              if ((localObject.length == 1) || ((StringUtils.isNull(paramString3)) && (StringUtils.isNull(paramString4))))
              {
                localJSONObject = TrainManager.queryTrainInfo(localObject[0]);
              }
              else
              {
                k = localObject.length;
                j = 0;
                for (;;)
                {
                  if (j >= k)
                  {
                    localJSONObject = null;
                    break;
                  }
                  localJSONObject = TrainManager.queryTrainInfo(localObject[j]);
                  String str = (String)JsonUtil.getValueFromJsonObject(localJSONObject, "station_list");
                  if ((!StringUtils.isNull(str)) && (checkStationList(str, paramString3, paramString4, false))) {
                    break;
                  }
                  j += 1;
                }
                j = 0;
              }
            }
            catch (JSONException localJSONException)
            {
              for (;;)
              {
                localJSONException.printStackTrace();
              }
            }
          }
        }
      }
      if (!NetUtil.checkAccessNetWork(2))
      {
        XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { paramString1, "offNetwork" });
        return;
      }
      paramString2 = paramString2.split("/");
      if ((paramString2.length == 1) || ((StringUtils.isNull(paramString3)) && (StringUtils.isNull(paramString4))))
      {
        TrainManager.checkDataOnline(paramSdkCallBack, paramString1, paramString2[0], paramString3, paramString4);
        return;
      }
      k = paramString2.length;
      j = 0;
      while (j < k)
      {
        TrainManager.checkDataOnline(paramSdkCallBack, paramString1, paramString2[j], paramString3, paramString4);
        j += 1;
      }
    }
  }
  
  public static void checkDataForUpdate(Map<String, String> paramMap, SdkCallBack paramSdkCallBack)
  {
    try
    {
      if (!NetUtil.checkAccessNetWork(paramMap))
      {
        XyUtil.doXycallBack(paramSdkCallBack, "-1");
        return;
      }
      if (!ParseItemManager.isInitData())
      {
        XyUtil.doXycallBack(paramSdkCallBack, "0");
        return;
      }
    }
    catch (Throwable paramMap)
    {
      paramMap.printStackTrace();
      return;
    }
    if (cn.com.xy.sms.sdk.util.i.b())
    {
      XyUtil.doXycallBack(paramSdkCallBack, "1");
      bx.a(paramMap, null);
      return;
    }
    bx.a(paramMap, paramSdkCallBack);
  }
  
  public static boolean checkStationList(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    if (paramString1 == null) {
      return false;
    }
    try
    {
      JSONArray localJSONArray = new JSONArray(paramString1);
      if ((StringUtils.isNull(paramString2)) && (!StringUtils.isNull(paramString3)))
      {
        paramString1 = (JSONObject)localJSONArray.get(0);
        if ((paramString1 != null) && (!paramString1.getString("name").trim().equalsIgnoreCase(paramString3.trim()))) {
          return true;
        }
      }
      else if ((StringUtils.isNull(paramString3)) && (!StringUtils.isNull(paramString2)))
      {
        paramString1 = (JSONObject)localJSONArray.get(localJSONArray.length() - 1);
        if ((paramString1 != null) && (!paramString1.getString("name").trim().equalsIgnoreCase(paramString2.trim()))) {
          return true;
        }
      }
      else if ((!StringUtils.isNull(paramString2)) && (!StringUtils.isNull(paramString3)))
      {
        int j = paramString1.indexOf("\"" + paramString2 + "\"");
        int k = paramString1.indexOf("\"" + paramString3 + "\"");
        if ((j != -1) && (k != -1) && (j < k)) {
          return true;
        }
      }
    }
    catch (JSONException paramString1)
    {
      paramString1.printStackTrace();
    }
    return false;
  }
  
  public static void clearHistorySmsByNum(Context paramContext, String paramString, Map<String, String> paramMap)
  {
    PopupMsgManager.removeBusinessMessageByNum(paramContext, paramString, false, paramMap);
  }
  
  public static void deleteMatchCache(String paramString, long paramLong)
  {
    try
    {
      MatchCacheManager.deleteMatchCache(paramString, paramLong);
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void deleteMatchCache(String paramString1, String paramString2, long paramLong)
  {
    try
    {
      MatchCacheManager.deleteMatchCache(paramString1, paramString2, paramLong);
      return;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static boolean doAction(Activity paramActivity, String paramString, Map<String, String> paramMap)
  {
    return DuoquUtils.doAction(paramActivity, paramString, paramMap);
  }
  
  public static void executeQueryTrainInfoRunnable(Runnable paramRunnable)
  {
    b.execute(paramRunnable);
  }
  
  public static BitmapDrawable findLogoByLogoName(Context paramContext, String paramString, int paramInt1, int paramInt2, int paramInt3, Map<String, String> paramMap, SdkCallBack paramSdkCallBack)
  {
    for (;;)
    {
      try
      {
        str2 = Constant.getPath("duoqu_publiclogo");
        str1 = str2 + paramString;
        localObject = new File(str1);
        if (((File)localObject).exists())
        {
          new StringBuilder("logoPath=").append(str1).append("文件存在，decode");
          paramContext = ViewUtil.createBitmapByPath2(paramContext, (File)localObject, paramInt1, paramInt2);
          if (paramContext == null) {}
        }
      }
      catch (Throwable paramContext)
      {
        String str2;
        String str1;
        Object localObject;
        boolean bool2;
        boolean bool1;
        paramContext = null;
        paramMap = (Long)h.get("runResourseQueue");
        if (paramMap != null)
        {
          paramString = paramContext;
          if (System.currentTimeMillis() <= paramMap.longValue() + DexUtil.getUpdateCycleByType(20, 3600000L)) {
            continue;
          }
        }
        cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(7, new String[0]));
        h.put("runResourseQueue", Long.valueOf(System.currentTimeMillis()));
        continue;
      }
      finally
      {
        paramString = (Long)h.get("runResourseQueue");
        if ((paramString == null) || (System.currentTimeMillis() > paramString.longValue() + DexUtil.getUpdateCycleByType(20, 3600000L)))
        {
          cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(7, new String[0]));
          h.put("runResourseQueue", Long.valueOf(System.currentTimeMillis()));
        }
      }
      try
      {
        XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { paramContext });
      }
      catch (Throwable paramString)
      {
        continue;
      }
      try
      {
        paramString = (Long)h.get("runResourseQueue");
        if ((paramString == null) || (System.currentTimeMillis() > paramString.longValue() + DexUtil.getUpdateCycleByType(20, 3600000L)))
        {
          cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(7, new String[0]));
          h.put("runResourseQueue", Long.valueOf(System.currentTimeMillis()));
        }
        return paramContext;
      }
      finally {}
      h.put(str1, Long.valueOf(System.currentTimeMillis()));
      XyUtil.doXycallBackResult(paramSdkCallBack, null);
      paramContext = (Long)h.get("runResourseQueue");
      if ((paramContext == null) || (System.currentTimeMillis() > paramContext.longValue() + DexUtil.getUpdateCycleByType(20, 3600000L)))
      {
        cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(7, new String[0]));
        h.put("runResourseQueue", Long.valueOf(System.currentTimeMillis()));
      }
      paramContext = null;
      continue;
      localObject = (Long)h.get(str1);
      if ((localObject == null) || (System.currentTimeMillis() >= ((Long)localObject).longValue() + DexUtil.getUpdateCycleByType(19, 60L * mins * 1000L))) {
        continue;
      }
      new StringBuilder("logoPath=").append(str1).append(" 还没到时间");
      XyUtil.doXycallBackResult(paramSdkCallBack, null);
      paramContext = (Long)h.get("runResourseQueue");
      if ((paramContext == null) || (System.currentTimeMillis() > paramContext.longValue() + DexUtil.getUpdateCycleByType(20, 3600000L)))
      {
        cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(7, new String[0]));
        h.put("runResourseQueue", Long.valueOf(System.currentTimeMillis()));
      }
      paramContext = null;
    }
    localObject = "http://down2.bizport.cn/publicnum/upload/" + paramString;
    bool2 = false;
    bool1 = bool2;
    if (paramMap != null)
    {
      bool1 = bool2;
      if (!paramMap.isEmpty()) {
        bool1 = "true".equals(paramMap.get("syn"));
      }
    }
    if (!bool1) {
      i.execute(new i(paramContext, (String)localObject, str2, paramString, str1, paramInt1, paramInt2, paramSdkCallBack));
    }
    for (paramContext = null;; paramContext = b(paramContext, (String)localObject, str2, paramString, str1, paramInt1, paramInt2, paramSdkCallBack))
    {
      paramMap = (Long)h.get("runResourseQueue");
      if (paramMap != null)
      {
        paramString = paramContext;
        if (System.currentTimeMillis() <= paramMap.longValue() + DexUtil.getUpdateCycleByType(20, 3600000L)) {}
      }
      else
      {
        cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(7, new String[0]));
        h.put("runResourseQueue", Long.valueOf(System.currentTimeMillis()));
        paramString = paramContext;
      }
      paramContext = paramString;
      break;
    }
  }
  
  public static String getBubbleViewVersion(Map<String, Object> paramMap)
  {
    paramMap = "20151014";
    if (StringUtils.isNull(Constant.current_bubble_version)) {
      Constant.current_bubble_version = "20151014";
    }
    String str1;
    String str2;
    String str3;
    do
    {
      do
      {
        return paramMap;
        str1 = SysParamEntityManager.getStringParam(Constant.getContext(), "bubbleViewVersion");
      } while (StringUtils.isNull(str1));
      paramMap = str1.split("_");
      str1 = paramMap[0];
      str2 = paramMap[1];
      Constant.current_bubble_version = str1;
      str3 = DateUtils.getCurrentTimeString("yyyyMMdd");
      paramMap = str1;
    } while (!DateUtils.compareDateString(str3, str2, "yyyyMMdd"));
    new j(str3);
    return str1;
  }
  
  public static int getOperatorByNum(String paramString)
  {
    if (StringUtils.isNull(paramString)) {
      return -1;
    }
    return IccidLocationUtil.getOperatorByNum(StringUtils.getPhoneNumberNo86(paramString));
  }
  
  public static int getOperatorNumByPubNum(String paramString)
  {
    return cn.com.xy.sms.sdk.db.entity.a.e.b(paramString);
  }
  
  public static int getParseVersion(Context paramContext, Map paramMap)
  {
    try
    {
      paramContext = SdkParamUtil.getParamValue(paramContext, "PARSE_VERSION");
      if (!StringUtils.isNull(paramContext))
      {
        int j = Integer.parseInt(paramContext);
        return j;
      }
    }
    catch (Throwable paramContext)
    {
      LogManager.e("XIAOYUAN", "getParseVersion" + paramContext.getLocalizedMessage(), paramContext);
    }
    return 0;
  }
  
  public static String getSdkVersion()
  {
    return NetUtil.APPVERSION;
  }
  
  public static void initSdk(Context paramContext, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, Map<String, String> paramMap)
  {
    if (paramContext == null) {
      throw new Exception("context is null,please check.");
    }
    Constant.initContext(paramContext);
    try
    {
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("cn.com.xy.sms.iccidinfo.action");
      paramContext.registerReceiver(e, localIntentFilter);
      paramContext = new g(paramContext, paramString1, paramString2, paramBoolean1, paramBoolean2, paramMap);
      if ((paramMap != null) && (paramMap.containsKey("SYNCHRONIZED")))
      {
        paramContext.run();
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        LogManager.e("initSdk", "registerReceiver error " + localThrowable.getMessage(), localThrowable);
      }
      NetUtil.executeRunnable(paramContext);
    }
  }
  
  public static boolean isEnterpriseSms(Context paramContext, String paramString1, String paramString2, Map<String, String> paramMap)
  {
    if (!cn.com.xy.sms.sdk.net.util.l.a()) {
      return false;
    }
    if ((d.c(Constant.getPARSE_PATH(), "parseUtilMain", "jar")) && (cn.com.xy.sms.sdk.net.util.l.a(Constant.getJarPath()).booleanValue())) {
      return DexUtil.isEnterpriseSms(paramContext, paramString1, paramString2, paramMap);
    }
    return PopupUtil.isEnterpriseSms(paramContext, paramString1, paramString2, paramMap);
  }
  
  public static boolean isInitData()
  {
    return ParseItemManager.isInitData();
  }
  
  public static boolean ismUseNewDes()
  {
    return d;
  }
  
  public static HashMap<String, JSONObject> loadAllPubInfo(Set<String> paramSet)
  {
    return cn.com.xy.sms.sdk.db.entity.a.e.a(paramSet);
  }
  
  public static HashMap<String, String> loadAllPubNum(Set<String> paramSet)
  {
    return cn.com.xy.sms.sdk.db.entity.a.e.b(paramSet);
  }
  
  public static void loadLocation(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
  {
    IccidLocationUtil.getAreaCodeByCnumOrIccid(null, paramInt, paramString1, paramString2, paramBoolean, true);
  }
  
  public static boolean needUpdateJar()
  {
    return cn.com.xy.sms.sdk.db.entity.e.e();
  }
  
  public static void parseMsgCallBack(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
  {
    try
    {
      ParseSmsToBubbleUtil.backGroundHandleMapByType(paramMap, a(paramContext, paramString1, paramString2, paramString3, 0L, paramMap));
      return;
    }
    catch (Throwable paramContext)
    {
      throw new RuntimeException(paramContext);
    }
  }
  
  public static String parseMsgToBubble(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
  {
    if (!cn.com.xy.sms.sdk.net.util.l.a())
    {
      paramContext = null;
      return paramContext;
    }
    if (paramMap == null) {
      paramMap = new HashMap();
    }
    for (;;)
    {
      paramMap.put("popup_type", "2");
      paramContext = a(paramContext, paramString1, paramString2, paramString3, 0L, paramMap);
      if (paramContext != null)
      {
        paramString1 = (String)paramContext.get("ADACTION");
        paramContext = paramString1;
        if (paramString1 != null) {
          break;
        }
      }
      return null;
    }
  }
  
  public static Map<String, Object> parseMsgToBubbleCardResult(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, byte paramByte, Map<String, String> paramMap)
  {
    if (!cn.com.xy.sms.sdk.net.util.l.a())
    {
      new StringBuilder("msgId: ").append(paramString1).append(" parseMsgToBubbleCardResult power not valid");
      paramMap = null;
    }
    do
    {
      Map localMap;
      do
      {
        return paramMap;
        long l1 = System.currentTimeMillis();
        localMap = a(paramContext, paramString2, paramString3, paramString4, paramLong, paramMap);
        long l2 = System.currentTimeMillis();
        new StringBuilder("parseMsg time:").append(l2 - l1);
        paramMap = localMap;
      } while (ParseBubbleManager.getParseStatu(localMap) == -1);
      if (ParseItemManager.execNqSql)
      {
        new StringBuilder("ParseItemManager.execNqSql: ").append(ParseItemManager.execNqSql);
        paramContext = localMap;
        if (localMap == null) {
          paramContext = new HashMap();
        }
        paramContext.clear();
        paramContext.put("parseStatu", "-2");
        return paramContext;
      }
      paramContext = PopupUtil.parseMsgToBubbleCardResult(paramContext, paramString1, paramString2, paramString3, paramString4, paramLong, paramByte, localMap, false);
      paramMap = paramContext;
    } while (paramContext != null);
    new StringBuilder("cardResult: ").append(paramContext);
    return paramContext;
  }
  
  public static Map<String, Object> parseMsgToMap(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
  {
    if (paramMap == null) {
      paramMap = new HashMap();
    }
    for (;;)
    {
      paramMap.put("popup_type", "2");
      new StringBuilder("parseMsgToMap: ").append(paramString1).append(" msg: ").append(paramString3);
      return a(paramContext, paramString1, paramString2, paramString3, 0L, paramMap);
    }
  }
  
  public static Map<String, Object> parseMsgToPopupWindow(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
  {
    return parseMsgToPopupWindow(paramContext, paramString1, paramString2, paramString3, false, paramMap);
  }
  
  public static Map<String, Object> parseMsgToPopupWindow(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, Object> paramMap, boolean paramBoolean, Map<String, String> paramMap1)
  {
    if ((paramMap != null) && (paramMap.size() > 1))
    {
      if ((paramMap1 != null) && (!paramMap1.isEmpty()))
      {
        if (paramBoolean)
        {
          String str1 = (String)paramMap1.get("msgId");
          String str2 = (String)paramMap1.get("msgTime");
          if ((!StringUtils.isNull(str1)) && (!StringUtils.isNull(str2))) {
            ParseSmsToBubbleUtil.backGroundParseSmsBubble(str1, paramString1, paramString3, paramString2, Long.valueOf(str2).longValue(), false, true, paramMap);
          }
        }
        ParseSmsToBubbleUtil.backGroundHandleMapByType(paramMap1, paramMap);
        paramString2 = (String)paramMap1.get("POPUP_SHOW_MASTER");
        if (paramString2 != null)
        {
          if ("0".equals(paramString2.trim())) {
            return PopupUtil.getResultMap(false, true);
          }
          if ("1".equals(paramString2.trim()))
          {
            paramString2 = (String)paramMap.get("title_num");
            if (paramString2.startsWith("01"))
            {
              paramString2 = (String)paramMap1.get("POPUP_SHOW_BANK");
              if ((paramString2 != null) && ("0".equals(paramString2.trim()))) {
                return PopupUtil.getResultMap(false, true);
              }
            }
            else if (paramString2.startsWith("02"))
            {
              paramString2 = (String)paramMap1.get("POPUP_SHOW_SP");
              if ((paramString2 != null) && ("0".equals(paramString2.trim()))) {
                return PopupUtil.getResultMap(false, true);
              }
            }
            else
            {
              paramString2 = (String)paramMap1.get("POPUP_SHOW_LIFE");
              if ((paramString2 != null) && ("0".equals(paramString2.trim()))) {
                return PopupUtil.getResultMap(false, true);
              }
            }
          }
          else if ("2".equals(paramString2.trim()))
          {
            paramString2 = (String)paramMap.get("title_num");
            int j = 0;
            for (;;)
            {
              if (j >= 13) {}
              for (j = 0;; j = 1)
              {
                if (j != 0) {
                  break label469;
                }
                return PopupUtil.getResultMap(false, true);
                if (!paramString2.startsWith(new String[] { "01025", "02044", "03006", "03015", "04010", "05035", "08104", "12003", "13004", "15003", "16002", "17005", "00000" }[j])) {
                  break;
                }
              }
              j += 1;
            }
          }
        }
      }
      label469:
      paramMap = PopupUtil.parseMsgToPopupWindow(paramContext, paramString1, paramString3, paramMap);
      if (ViewUtil.getChannelType() == 3)
      {
        paramString2 = null;
        if (paramMap1 != null)
        {
          paramString2 = new HashMap();
          paramString2.putAll(paramMap1);
        }
        return PopupUtil.getResultMap(paramMap, paramString1, paramString3, paramString2, paramContext);
      }
    }
    else
    {
      if (ViewUtil.getChannelType() == 3)
      {
        if ((paramMap1 != null) && (!paramMap1.isEmpty()))
        {
          paramString2 = (String)paramMap1.get("POPUP_SHOW_MASTER");
          if ((paramString2 != null) && ("0".equals(paramString2.trim()))) {
            return PopupUtil.getResultMap(false, false);
          }
        }
        paramString2 = null;
        if (paramMap1 != null)
        {
          paramString2 = new HashMap();
          paramString2.putAll(paramMap1);
        }
        return PopupUtil.getResultMap(PopupUtil.getResultMap(false, false), paramString1, paramString3, paramString2, paramContext);
      }
      return PopupUtil.getResultMap(false, false);
    }
    return paramMap;
  }
  
  public static Map<String, Object> parseMsgToPopupWindow(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean, Map<String, String> paramMap)
  {
    if (paramContext == null) {
      throw new Exception(" Context is null.");
    }
    if (paramString1 == null) {
      throw new Exception(" phoneNumber is null.");
    }
    if (paramString3 == null) {
      throw new Exception(" smsContent is null.");
    }
    if (!cn.com.xy.sms.sdk.net.util.l.a((byte)3)) {
      PopupUtil.getResultMap(false, false);
    }
    if (paramMap == null) {
      paramMap = new HashMap();
    }
    for (;;)
    {
      paramMap.put("popup_type", "1");
      new StringBuilder("调用parseMsg开始=").append(System.currentTimeMillis());
      Map localMap = a(paramContext, paramString1, paramString2, paramString3, 0L, paramMap);
      new StringBuilder("调用parseMsg结束=").append(System.currentTimeMillis());
      return parseMsgToPopupWindow(paramContext, paramString1, paramString2, paramString3, localMap, paramBoolean, paramMap);
    }
  }
  
  public static Map<String, Object> parseMsgToRichAndSimpleBubble(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, byte paramByte, Map<String, String> paramMap)
  {
    if (!cn.com.xy.sms.sdk.net.util.l.a()) {
      return null;
    }
    paramMap = a(paramContext, paramString2, paramString3, paramString4, paramLong, paramMap);
    if (ParseBubbleManager.getParseStatu(paramMap) == -1) {
      return null;
    }
    paramContext = PopupUtil.parseMsgToBubbleCardResult(paramContext, paramString1, paramString2, paramString3, paramString4, paramLong, paramByte, paramMap, false);
    new StringBuilder("cardResult: ").append(paramContext);
    return paramContext;
  }
  
  public static String parseSmsToClassify(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, Map<String, String> paramMap)
  {
    if (!cn.com.xy.sms.sdk.net.util.l.a()) {
      return null;
    }
    return bx.a(a(paramContext, paramString1, paramString2, paramString3, 0L, paramMap));
  }
  
  public static int parseSmsType(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap, int paramInt)
  {
    if (paramMap == null) {
      paramMap = new HashMap();
    }
    for (;;)
    {
      try
      {
        paramMap.put("popup_type", "2");
        paramContext = a(paramContext, paramString1, paramString3, paramString2, 0L, paramMap);
        if ((paramContext != null) && (ParseBubbleManager.getParseStatu(paramContext) != -1))
        {
          paramInt = DexUtil.getSmsTypeByMap(paramContext, paramInt);
          return paramInt;
        }
      }
      catch (Throwable paramContext)
      {
        paramContext.printStackTrace();
      }
      return -1;
    }
  }
  
  public static void queryAllSimCardTrafficAndChargeActionData(Context paramContext, String paramString, SdkCallBack paramSdkCallBack)
  {
    try
    {
      Object localObject = IccidLocationUtil.getIccidAreaCodeMap();
      if (localObject == null)
      {
        paramSdkCallBack.execute(new Object[] { Integer.valueOf(0), "iccidMap is null" });
        return;
      }
      localObject = ((HashMap)localObject).entrySet().iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str = (String)((Map.Entry)((Iterator)localObject).next()).getKey();
        queryMenuByPhoneNum(paramContext, paramString, 1, str, null, new o(paramSdkCallBack, str));
      }
      return;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
      paramSdkCallBack.execute(new Object[] { Integer.valueOf(0), "error:" + paramContext.getMessage() });
    }
  }
  
  public static String queryDefService(Context paramContext)
  {
    return SysParamEntityManager.queryValueParamKey(paramContext, "defService");
  }
  
  public static void queryFlightData(String paramString1, String paramString2, String paramString3, Map<String, Object> paramMap, SdkCallBack paramSdkCallBack)
  {
    if ((StringUtils.isNull(paramString2)) || (StringUtils.isNull(paramString3)))
    {
      XyUtil.doXycallBackResult(paramSdkCallBack, null);
      return;
    }
    String str1 = (String)JsonUtil.getValueWithMap(paramMap, "flight_form");
    String str2 = (String)JsonUtil.getValueWithMap(paramMap, "flight_to");
    String str3 = (String)JsonUtil.getValueWithMap(paramMap, "flight_from_airport");
    String str4 = (String)JsonUtil.getValueWithMap(paramMap, "flight_to_airport");
    String str5 = (String)JsonUtil.getValueWithMap(paramMap, "phoneNumber");
    String str6 = (String)JsonUtil.getValueWithMap(paramMap, "titleNo");
    String str7 = (String)JsonUtil.getValueWithMap(paramMap, "msgId");
    paramString1 = new k((String)JsonUtil.getValueWithMap(paramMap, "bubbleJsonObj"), str5, str6, str7, (String)JsonUtil.getValueWithMap(paramMap, "messageBody"), paramSdkCallBack, str1, str2, str3, str4, paramString1);
    try
    {
      paramMap = new JSONObject();
      paramMap.put("flight_num", paramString2.replace(" ", ""));
      paramMap.put("flight_date", paramString3);
      NetWebUtil.sendPostRequest(NetWebUtil.WEB_SERVER_URL_FLIGHT, paramMap.toString(), paramString1);
      return;
    }
    catch (JSONException paramString1)
    {
      paramString1.printStackTrace();
      XyUtil.doXycallBackResult(paramSdkCallBack, null);
    }
  }
  
  /* Error */
  public static String queryMenuByPhoneNum(Context paramContext, String paramString1, int paramInt, String paramString2, Map<String, String> paramMap)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: ldc 2
    //   4: monitorenter
    //   5: iconst_4
    //   6: invokestatic 626	cn/com/xy/sms/sdk/net/util/l:a	(B)Z
    //   9: istore 5
    //   11: iload 5
    //   13: ifne +8 -> 21
    //   16: ldc 2
    //   18: monitorexit
    //   19: aload_0
    //   20: areturn
    //   21: aload_1
    //   22: invokestatic 922	cn/com/xy/sms/sdk/util/StringUtils:isPhoneNumber	(Ljava/lang/String;)Z
    //   25: ifne -9 -> 16
    //   28: aload_1
    //   29: iload_2
    //   30: aload_3
    //   31: aload 4
    //   33: aconst_null
    //   34: invokestatic 925	bx:a	(Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;Lcn/com/xy/sms/util/SdkCallBack;)Ljava/lang/String;
    //   37: astore_0
    //   38: goto -22 -> 16
    //   41: astore_0
    //   42: ldc 2
    //   44: monitorexit
    //   45: aload_0
    //   46: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	47	0	paramContext	Context
    //   0	47	1	paramString1	String
    //   0	47	2	paramInt	int
    //   0	47	3	paramString2	String
    //   0	47	4	paramMap	Map<String, String>
    //   9	3	5	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   5	11	41	finally
    //   21	38	41	finally
  }
  
  /* Error */
  public static String queryMenuByPhoneNum(Context paramContext, String paramString1, int paramInt, String paramString2, Map<String, String> paramMap, SdkCallBack paramSdkCallBack)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: ldc 2
    //   4: monitorenter
    //   5: iconst_4
    //   6: invokestatic 626	cn/com/xy/sms/sdk/net/util/l:a	(B)Z
    //   9: istore 6
    //   11: iload 6
    //   13: ifne +8 -> 21
    //   16: ldc 2
    //   18: monitorexit
    //   19: aload_0
    //   20: areturn
    //   21: aload_1
    //   22: iload_2
    //   23: aload_3
    //   24: aload 4
    //   26: aload 5
    //   28: invokestatic 925	bx:a	(Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;Lcn/com/xy/sms/util/SdkCallBack;)Ljava/lang/String;
    //   31: astore_1
    //   32: aload_1
    //   33: astore_0
    //   34: invokestatic 930	cn/com/xy/sms/sdk/queue/b:a	()V
    //   37: invokestatic 933	bs:a	()V
    //   40: goto -24 -> 16
    //   43: astore_1
    //   44: ldc_w 935
    //   47: new 154	java/lang/StringBuilder
    //   50: dup
    //   51: ldc_w 937
    //   54: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   57: aload_1
    //   58: invokevirtual 334	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   61: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   67: aload_1
    //   68: invokestatic 220	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   71: goto -55 -> 16
    //   74: astore_0
    //   75: ldc 2
    //   77: monitorexit
    //   78: aload_0
    //   79: athrow
    //   80: astore_1
    //   81: ldc_w 935
    //   84: new 154	java/lang/StringBuilder
    //   87: dup
    //   88: ldc_w 939
    //   91: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   94: aload_1
    //   95: invokevirtual 334	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   98: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   104: aload_1
    //   105: invokestatic 220	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   108: invokestatic 930	cn/com/xy/sms/sdk/queue/b:a	()V
    //   111: invokestatic 933	bs:a	()V
    //   114: goto -98 -> 16
    //   117: astore_1
    //   118: ldc_w 935
    //   121: new 154	java/lang/StringBuilder
    //   124: dup
    //   125: ldc_w 937
    //   128: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   131: aload_1
    //   132: invokevirtual 334	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   135: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   141: aload_1
    //   142: invokestatic 220	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   145: goto -129 -> 16
    //   148: astore_0
    //   149: invokestatic 930	cn/com/xy/sms/sdk/queue/b:a	()V
    //   152: invokestatic 933	bs:a	()V
    //   155: aload_0
    //   156: athrow
    //   157: astore_1
    //   158: ldc_w 935
    //   161: new 154	java/lang/StringBuilder
    //   164: dup
    //   165: ldc_w 937
    //   168: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   171: aload_1
    //   172: invokevirtual 334	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   175: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   181: aload_1
    //   182: invokestatic 220	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   185: goto -30 -> 155
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	188	0	paramContext	Context
    //   0	188	1	paramString1	String
    //   0	188	2	paramInt	int
    //   0	188	3	paramString2	String
    //   0	188	4	paramMap	Map<String, String>
    //   0	188	5	paramSdkCallBack	SdkCallBack
    //   9	3	6	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   34	40	43	java/lang/Throwable
    //   5	11	74	finally
    //   34	40	74	finally
    //   44	71	74	finally
    //   108	114	74	finally
    //   118	145	74	finally
    //   149	155	74	finally
    //   155	157	74	finally
    //   158	185	74	finally
    //   21	32	80	java/lang/Throwable
    //   108	114	117	java/lang/Throwable
    //   21	32	148	finally
    //   81	108	148	finally
    //   149	155	157	java/lang/Throwable
  }
  
  /* Error */
  public static String queryPublicInfo(Context paramContext, String paramString1, int paramInt, String paramString2, Map<String, String> paramMap)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: ldc 2
    //   4: monitorenter
    //   5: iconst_5
    //   6: invokestatic 626	cn/com/xy/sms/sdk/net/util/l:a	(B)Z
    //   9: istore 5
    //   11: iload 5
    //   13: ifne +8 -> 21
    //   16: ldc 2
    //   18: monitorexit
    //   19: aload_0
    //   20: areturn
    //   21: aload_1
    //   22: invokestatic 922	cn/com/xy/sms/sdk/util/StringUtils:isPhoneNumber	(Ljava/lang/String;)Z
    //   25: ifne -9 -> 16
    //   28: aload_1
    //   29: iload_2
    //   30: aload_3
    //   31: aload 4
    //   33: aconst_null
    //   34: invokestatic 944	bx:a	(Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;Lcn/com/xy/sms/sdk/Iservice/XyCallBack;)Ljava/lang/String;
    //   37: astore_0
    //   38: goto -22 -> 16
    //   41: astore_0
    //   42: ldc 2
    //   44: monitorexit
    //   45: aload_0
    //   46: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	47	0	paramContext	Context
    //   0	47	1	paramString1	String
    //   0	47	2	paramInt	int
    //   0	47	3	paramString2	String
    //   0	47	4	paramMap	Map<String, String>
    //   9	3	5	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   5	11	41	finally
    //   21	38	41	finally
  }
  
  /* Error */
  public static String queryPublicInfo(Context paramContext, String paramString1, int paramInt, String paramString2, Map<String, String> paramMap, SdkCallBack paramSdkCallBack)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: ldc 2
    //   4: monitorenter
    //   5: iconst_5
    //   6: invokestatic 626	cn/com/xy/sms/sdk/net/util/l:a	(B)Z
    //   9: istore 6
    //   11: iload 6
    //   13: ifne +8 -> 21
    //   16: ldc 2
    //   18: monitorexit
    //   19: aload_0
    //   20: areturn
    //   21: aload_1
    //   22: invokestatic 922	cn/com/xy/sms/sdk/util/StringUtils:isPhoneNumber	(Ljava/lang/String;)Z
    //   25: istore 6
    //   27: iload 6
    //   29: ifeq +49 -> 78
    //   32: invokestatic 930	cn/com/xy/sms/sdk/queue/b:a	()V
    //   35: invokestatic 933	bs:a	()V
    //   38: goto -22 -> 16
    //   41: astore_1
    //   42: ldc_w 935
    //   45: new 154	java/lang/StringBuilder
    //   48: dup
    //   49: ldc_w 937
    //   52: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   55: aload_1
    //   56: invokevirtual 334	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   59: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: aload_1
    //   66: invokestatic 220	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   69: goto -53 -> 16
    //   72: astore_0
    //   73: ldc 2
    //   75: monitorexit
    //   76: aload_0
    //   77: athrow
    //   78: aload_1
    //   79: iload_2
    //   80: aload_3
    //   81: aload 4
    //   83: aload 5
    //   85: invokestatic 944	bx:a	(Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;Lcn/com/xy/sms/sdk/Iservice/XyCallBack;)Ljava/lang/String;
    //   88: astore_1
    //   89: aload_1
    //   90: astore_0
    //   91: invokestatic 930	cn/com/xy/sms/sdk/queue/b:a	()V
    //   94: invokestatic 933	bs:a	()V
    //   97: goto -81 -> 16
    //   100: astore_1
    //   101: ldc_w 935
    //   104: new 154	java/lang/StringBuilder
    //   107: dup
    //   108: ldc_w 937
    //   111: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   114: aload_1
    //   115: invokevirtual 334	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   118: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   124: aload_1
    //   125: invokestatic 220	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   128: goto -112 -> 16
    //   131: astore_1
    //   132: ldc_w 935
    //   135: new 154	java/lang/StringBuilder
    //   138: dup
    //   139: ldc_w 937
    //   142: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   145: aload_1
    //   146: invokevirtual 334	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   149: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   155: aload_1
    //   156: invokestatic 220	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   159: invokestatic 930	cn/com/xy/sms/sdk/queue/b:a	()V
    //   162: invokestatic 933	bs:a	()V
    //   165: goto -149 -> 16
    //   168: astore_1
    //   169: ldc_w 935
    //   172: new 154	java/lang/StringBuilder
    //   175: dup
    //   176: ldc_w 937
    //   179: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   182: aload_1
    //   183: invokevirtual 334	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   186: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   192: aload_1
    //   193: invokestatic 220	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   196: goto -180 -> 16
    //   199: astore_0
    //   200: invokestatic 930	cn/com/xy/sms/sdk/queue/b:a	()V
    //   203: invokestatic 933	bs:a	()V
    //   206: aload_0
    //   207: athrow
    //   208: astore_1
    //   209: ldc_w 935
    //   212: new 154	java/lang/StringBuilder
    //   215: dup
    //   216: ldc_w 937
    //   219: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   222: aload_1
    //   223: invokevirtual 334	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   226: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   229: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   232: aload_1
    //   233: invokestatic 220	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   236: goto -30 -> 206
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	239	0	paramContext	Context
    //   0	239	1	paramString1	String
    //   0	239	2	paramInt	int
    //   0	239	3	paramString2	String
    //   0	239	4	paramMap	Map<String, String>
    //   0	239	5	paramSdkCallBack	SdkCallBack
    //   9	19	6	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   32	38	41	java/lang/Throwable
    //   5	11	72	finally
    //   32	38	72	finally
    //   42	69	72	finally
    //   91	97	72	finally
    //   101	128	72	finally
    //   159	165	72	finally
    //   169	196	72	finally
    //   200	206	72	finally
    //   206	208	72	finally
    //   209	236	72	finally
    //   91	97	100	java/lang/Throwable
    //   21	27	131	java/lang/Throwable
    //   78	89	131	java/lang/Throwable
    //   159	165	168	java/lang/Throwable
    //   21	27	199	finally
    //   78	89	199	finally
    //   132	159	199	finally
    //   200	206	208	java/lang/Throwable
  }
  
  /* Error */
  public static String queryPublicInfoWithId(Context paramContext, String paramString1, int paramInt, String paramString2, Map<String, String> paramMap, SdkCallBack paramSdkCallBack)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: ldc 2
    //   5: monitorenter
    //   6: iconst_5
    //   7: invokestatic 626	cn/com/xy/sms/sdk/net/util/l:a	(B)Z
    //   10: istore 6
    //   12: iload 6
    //   14: ifne +11 -> 25
    //   17: aload 7
    //   19: astore_0
    //   20: ldc 2
    //   22: monitorexit
    //   23: aload_0
    //   24: areturn
    //   25: aload_1
    //   26: invokestatic 922	cn/com/xy/sms/sdk/util/StringUtils:isPhoneNumber	(Ljava/lang/String;)Z
    //   29: istore 6
    //   31: aload 7
    //   33: astore_0
    //   34: iload 6
    //   36: ifne -16 -> 20
    //   39: aload_1
    //   40: iload_2
    //   41: aload_3
    //   42: aload 4
    //   44: aload 5
    //   46: invokestatic 947	bx:b	(Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;Lcn/com/xy/sms/sdk/Iservice/XyCallBack;)Ljava/lang/String;
    //   49: astore_0
    //   50: invokestatic 930	cn/com/xy/sms/sdk/queue/b:a	()V
    //   53: invokestatic 933	bs:a	()V
    //   56: goto -36 -> 20
    //   59: astore_1
    //   60: ldc_w 935
    //   63: new 154	java/lang/StringBuilder
    //   66: dup
    //   67: ldc_w 937
    //   70: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   73: aload_1
    //   74: invokevirtual 334	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   77: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   83: aload_1
    //   84: invokestatic 220	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   87: goto -67 -> 20
    //   90: astore_0
    //   91: ldc 2
    //   93: monitorexit
    //   94: aload_0
    //   95: athrow
    //   96: astore_0
    //   97: ldc_w 935
    //   100: new 154	java/lang/StringBuilder
    //   103: dup
    //   104: ldc_w 949
    //   107: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   110: aload_0
    //   111: invokevirtual 334	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   114: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   117: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   120: aload_0
    //   121: invokestatic 220	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   124: invokestatic 930	cn/com/xy/sms/sdk/queue/b:a	()V
    //   127: invokestatic 933	bs:a	()V
    //   130: aload 7
    //   132: astore_0
    //   133: goto -113 -> 20
    //   136: astore_0
    //   137: ldc_w 935
    //   140: new 154	java/lang/StringBuilder
    //   143: dup
    //   144: ldc_w 937
    //   147: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   150: aload_0
    //   151: invokevirtual 334	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   154: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   160: aload_0
    //   161: invokestatic 220	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   164: aload 7
    //   166: astore_0
    //   167: goto -147 -> 20
    //   170: astore_0
    //   171: invokestatic 930	cn/com/xy/sms/sdk/queue/b:a	()V
    //   174: invokestatic 933	bs:a	()V
    //   177: aload_0
    //   178: athrow
    //   179: astore_1
    //   180: ldc_w 935
    //   183: new 154	java/lang/StringBuilder
    //   186: dup
    //   187: ldc_w 937
    //   190: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   193: aload_1
    //   194: invokevirtual 334	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   197: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   203: aload_1
    //   204: invokestatic 220	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   207: goto -30 -> 177
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	210	0	paramContext	Context
    //   0	210	1	paramString1	String
    //   0	210	2	paramInt	int
    //   0	210	3	paramString2	String
    //   0	210	4	paramMap	Map<String, String>
    //   0	210	5	paramSdkCallBack	SdkCallBack
    //   10	25	6	bool	boolean
    //   1	164	7	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   50	56	59	java/lang/Throwable
    //   6	12	90	finally
    //   25	31	90	finally
    //   50	56	90	finally
    //   60	87	90	finally
    //   124	130	90	finally
    //   137	164	90	finally
    //   171	177	90	finally
    //   177	179	90	finally
    //   180	207	90	finally
    //   39	50	96	java/lang/Throwable
    //   124	130	136	java/lang/Throwable
    //   39	50	170	finally
    //   97	124	170	finally
    //   171	177	179	java/lang/Throwable
  }
  
  public static void queryTrainInfo(String paramString1, String paramString2, String paramString3, String paramString4, Map<String, Object> paramMap, SdkCallBack paramSdkCallBack)
  {
    String str1 = (String)JsonUtil.getValueWithMap(paramMap, "phoneNumber");
    String str2 = (String)JsonUtil.getValueWithMap(paramMap, "titleNo");
    String str3 = (String)JsonUtil.getValueWithMap(paramMap, "bubbleJsonObj");
    paramMap = (String)JsonUtil.getValueWithMap(paramMap, "messageBody");
    b.execute(new m(paramString1, paramString2, paramString3, paramString4, str3, str1, str2, paramMap, paramSdkCallBack));
  }
  
  public static long setDefServiceSwitch(Context paramContext, String paramString)
  {
    try
    {
      SysParamEntityManager.insertOrUpdateKeyValue(paramContext, "defService", paramString, null);
      return 0L;
    }
    catch (Throwable paramContext) {}
    return -2L;
  }
  
  public static void setLogSdkDoAction(cn.com.xy.sms.sdk.util.k paramk)
  {
    DuoquUtils.logSdkDoAction = paramk;
  }
  
  public static void setSdkDoAction(AbsSdkDoAction paramAbsSdkDoAction)
  {
    DuoquUtils.sdkAction = paramAbsSdkDoAction;
  }
  
  public static void setSmartEnhance(boolean paramBoolean)
  {
    try
    {
      SysParamEntityManager.insertOrUpdateKeyValue(Constant.getContext(), "smartsms_enhance", String.valueOf(paramBoolean), null);
      SysParamEntityManager.cacheMap.put("smartsms_enhance", Boolean.valueOf(paramBoolean));
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public static void setmUseNewDes(boolean paramBoolean)
  {
    d = paramBoolean;
  }
  
  public static void unRegisterReceiver(Context paramContext)
  {
    try
    {
      if (e != null) {
        paramContext.unregisterReceiver(e);
      }
      return;
    }
    catch (Throwable paramContext)
    {
      LogManager.e("ParseManager.unRegisterReceiver", "unRegisterReceiver error " + paramContext.getMessage(), paramContext);
    }
  }
  
  public static void updateData(Map<String, String> paramMap, SdkCallBack paramSdkCallBack)
  {
    try
    {
      NetUtil.executeRunnable(new h(paramMap, paramSdkCallBack));
      return;
    }
    catch (Throwable paramMap)
    {
      paramMap.printStackTrace();
    }
  }
  
  public static void updateMatchCacheManager(BusinessSmsMessage paramBusinessSmsMessage)
  {
    if (paramBusinessSmsMessage == null) {
      return;
    }
    try
    {
      updateMatchCacheManager((String)paramBusinessSmsMessage.getValue("phoneNum"), paramBusinessSmsMessage.getTitleNo(), String.valueOf(paramBusinessSmsMessage.getSmsId()), bubbleJsonObj, paramBusinessSmsMessage.getMessageBody());
      return;
    }
    catch (JSONException paramBusinessSmsMessage)
    {
      paramBusinessSmsMessage.printStackTrace();
    }
  }
  
  public static void updateMatchCacheManager(String paramString1, String paramString2, String paramString3, JSONObject paramJSONObject, String paramString4)
  {
    NetUtil.executeRunnable(new l(paramString1, paramString4, paramJSONObject, paramString2, paramString3));
  }
  
  public static void updateNow()
  {
    cn.com.xy.sms.sdk.util.i.a(true, false);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.ParseManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
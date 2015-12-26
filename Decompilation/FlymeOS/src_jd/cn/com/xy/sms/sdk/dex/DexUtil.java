package cn.com.xy.sms.sdk.dex;

import android.content.Context;
import android.util.LruCache;
import cn.com.xy.sms.sdk.Iservice.CorpSignInterface;
import cn.com.xy.sms.sdk.Iservice.OnlineParseInterface;
import cn.com.xy.sms.sdk.Iservice.OnlineUpdateCycleConfigInterface;
import cn.com.xy.sms.sdk.Iservice.OnlineViewInterface;
import cn.com.xy.sms.sdk.Iservice.ParseBubbleInterface;
import cn.com.xy.sms.sdk.Iservice.ParseCardInterface;
import cn.com.xy.sms.sdk.Iservice.ParseDateInterface;
import cn.com.xy.sms.sdk.Iservice.ParseNotificationInterface;
import cn.com.xy.sms.sdk.Iservice.ParsePayInterface;
import cn.com.xy.sms.sdk.Iservice.ParseRemindInterface;
import cn.com.xy.sms.sdk.Iservice.ParseVerifyCodeInterface;
import cn.com.xy.sms.sdk.Iservice.ParseWatchInterface;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.e;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.util.PopupUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.b;
import cn.com.xy.sms.sdk.util.d;
import cn.com.xy.sms.sdk.util.i;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class DexUtil
{
  private static ClassLoader a;
  private static OnlineParseInterface b;
  private static OnlineUpdateCycleConfigInterface c;
  private static HashMap<String, ClassLoader> d = new HashMap();
  private static HashMap<String, Class> e = new HashMap();
  
  private static OnlineUpdateCycleConfigInterface a()
  {
    try
    {
      Class localClass = getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.OnlineUpdateCycleConfig");
      if (localClass != null) {
        c = (OnlineUpdateCycleConfigInterface)localClass.newInstance();
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        LogManager.e("xiaoyuan", "DexUtil OnlineUpdateCycleConfigInterface：" + localThrowable.getMessage(), localThrowable);
      }
    }
    return c;
  }
  
  public static int getActionCode(String paramString)
  {
    try
    {
      OnlineParseInterface localOnlineParseInterface = getOnlineParseImpl(false);
      if (localOnlineParseInterface != null) {
        return localOnlineParseInterface.getActionCode(paramString);
      }
      int i = PopupUtil.getActionCode(paramString);
      return i;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return PopupUtil.getActionCode(paramString);
  }
  
  public static String getBubbleViewVersion(Map<String, Object> paramMap)
  {
    try
    {
      Object localObject = getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseUtilBubble");
      if (localObject != null)
      {
        localObject = (ParseBubbleInterface)((Class)localObject).newInstance();
        if (localObject != null)
        {
          paramMap = ((ParseBubbleInterface)localObject).getBubbleViewVersion(paramMap);
          return paramMap;
        }
      }
    }
    catch (Throwable paramMap)
    {
      paramMap.printStackTrace();
    }
    return "";
  }
  
  public static Class getClassBymap(Map<String, String> paramMap, String paramString)
  {
    try
    {
      String str = paramString.substring(paramString.lastIndexOf(".") + 1);
      paramMap = getClassLoaderBymap(paramMap, str);
      if (paramMap != null)
      {
        Class localClass = (Class)e.get(str + "_Class");
        if (localClass != null) {
          return localClass;
        }
        paramMap = paramMap.loadClass(paramString);
        if (paramMap != null)
        {
          e.put(str + "_Class", paramMap);
          return paramMap;
        }
      }
    }
    catch (Throwable paramMap)
    {
      paramMap.printStackTrace();
    }
    return null;
  }
  
  public static ClassLoader getClassLoaderBymap(Map<String, String> paramMap, String paramString)
  {
    long l1 = 0L;
    long l2 = 600000L;
    for (;;)
    {
      Object localObject;
      try
      {
        paramMap = (ClassLoader)d.get(paramString + "_ClassLoader");
        if (paramMap == null) {}
      }
      catch (Throwable paramMap)
      {
        paramMap.printStackTrace();
        if (paramString != null) {}
        try
        {
          if (paramString.length() > 7)
          {
            paramMap = (Long)Constant.checkJarMap.get(paramString);
            if (paramMap != null) {
              l1 = paramMap.longValue();
            }
            if (c != null) {
              l2 = getUpdateCycleByType(6, 600000L);
            }
            if (System.currentTimeMillis() >= l2 + l1)
            {
              e.a(paramString, "-1", 1);
              Constant.checkJarMap.put(paramString, Long.valueOf(System.currentTimeMillis()));
              i.a();
            }
          }
        }
        catch (Throwable paramMap)
        {
          continue;
        }
        return null;
      }
      finally
      {
        if (paramString == null) {}
      }
      try
      {
        if (paramString.length() > 7)
        {
          localObject = (Long)Constant.checkJarMap.get(paramString);
          if (localObject != null) {
            l1 = ((Long)localObject).longValue();
          }
          if (c != null) {
            l2 = getUpdateCycleByType(6, 600000L);
          }
          if (System.currentTimeMillis() >= l2 + l1)
          {
            e.a(paramString, "-1", 1);
            Constant.checkJarMap.put(paramString, Long.valueOf(System.currentTimeMillis()));
            i.a();
          }
        }
        return paramMap;
      }
      catch (Throwable paramString)
      {
        return paramMap;
      }
    }
    paramMap = d.d(Constant.getPARSE_PATH(), paramString + "_", ".jar");
    new StringBuilder("subname=").append(paramString).append("jarPath=").append(paramMap);
    localObject = new File(paramMap);
    if ((((File)localObject).exists()) && (l.a(paramMap).booleanValue()))
    {
      localFile = Constant.getContext().getDir("outdex", 0);
      paramMap = new DexClassLoader(((File)localObject).getCanonicalPath(), localFile.getCanonicalPath(), null, getDexClassLoader());
      XyUtil.chmod("640", localFile.getCanonicalPath() + File.separator + ((File)localObject).getName().substring(0, ((File)localObject).getName().length() - 4) + ".dex");
      d.put(paramString + "_ClassLoader", paramMap);
      if (paramString == null) {}
    }
    try
    {
      if (paramString.length() > 7)
      {
        localObject = (Long)Constant.checkJarMap.get(paramString);
        if (localObject != null) {
          l1 = ((Long)localObject).longValue();
        }
        if (c != null) {
          l2 = getUpdateCycleByType(6, 600000L);
        }
        if (System.currentTimeMillis() >= l2 + l1)
        {
          e.a(paramString, "-1", 1);
          Constant.checkJarMap.put(paramString, Long.valueOf(System.currentTimeMillis()));
          i.a();
        }
      }
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
    return paramMap;
    while (paramString == null)
    {
      try
      {
        File localFile;
        if (paramString.length() > 7)
        {
          localObject = (Long)Constant.checkJarMap.get(paramString);
          if (localObject != null) {
            l1 = ((Long)localObject).longValue();
          }
          if (c != null) {
            l2 = getUpdateCycleByType(6, 600000L);
          }
          if (System.currentTimeMillis() >= l2 + l1)
          {
            e.a(paramString, "-1", 1);
            Constant.checkJarMap.put(paramString, Long.valueOf(System.currentTimeMillis()));
            i.a();
          }
        }
      }
      catch (Throwable paramString)
      {
        for (;;) {}
      }
      throw paramMap;
    }
    for (;;)
    {
      try
      {
        if (paramString.length() <= 7) {
          break;
        }
        paramMap = (Long)Constant.checkJarMap.get(paramString);
        if (paramMap != null) {
          l1 = paramMap.longValue();
        }
        if (c == null) {
          break label700;
        }
        l2 = getUpdateCycleByType(6, 600000L);
        if (System.currentTimeMillis() < l2 + l1) {
          break;
        }
        e.a(paramString, "-1", 1);
        Constant.checkJarMap.put(paramString, Long.valueOf(System.currentTimeMillis()));
        i.a();
      }
      catch (Throwable paramMap) {}
      break;
      label700:
      l2 = 600000L;
    }
  }
  
  public static String getCorp(String paramString)
  {
    try
    {
      Object localObject = getCorpSignImpl(false);
      if (localObject != null) {
        return ((CorpSignInterface)localObject).getCorp(paramString);
      }
      localObject = b.a(paramString);
      return (String)localObject;
    }
    catch (Throwable localThrowable)
    {
      try
      {
        paramString = b.a(paramString);
        return paramString;
      }
      catch (Throwable paramString) {}
    }
    return "";
  }
  
  public static CorpSignInterface getCorpSignImpl(boolean paramBoolean)
  {
    try
    {
      Object localObject = getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.CorpSignImpl");
      if (localObject != null)
      {
        localObject = (CorpSignInterface)((Class)localObject).newInstance();
        return (CorpSignInterface)localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public static ClassLoader getDexClassLoader()
  {
    try
    {
      if (a == null)
      {
        File localFile1 = new File(Constant.getJarPath());
        if ((localFile1.exists()) && (l.a(Constant.getJarPath()).booleanValue()))
        {
          File localFile2 = Constant.getContext().getDir("outdex", 0);
          a = new DexClassLoader(localFile1.getCanonicalPath(), localFile2.getCanonicalPath(), null, Constant.getContext().getClassLoader());
          XyUtil.chmod("640", localFile2.getCanonicalPath() + File.separator + localFile1.getName().substring(0, localFile1.getName().length() - 4) + ".dex");
        }
      }
      return a;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
      }
    }
  }
  
  public static ClassLoader getDexClassLoader(boolean paramBoolean)
  {
    try
    {
      if ((a == null) || (paramBoolean))
      {
        File localFile1 = new File(Constant.getJarPath());
        if ((localFile1.exists()) && (l.a(Constant.getJarPath()).booleanValue()))
        {
          File localFile2 = Constant.getContext().getDir("outdex", 0);
          a = new DexClassLoader(localFile1.getCanonicalPath(), localFile2.getCanonicalPath(), null, Constant.getContext().getClassLoader());
        }
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
      }
    }
    return a;
  }
  
  public static OnlineParseInterface getOnlineParseImpl(boolean paramBoolean)
  {
    try
    {
      if ((b == null) || (paramBoolean))
      {
        Object localObject = getDexClassLoader();
        if (localObject != null)
        {
          localObject = ((ClassLoader)localObject).loadClass("cn.com.xy.sms.sdk.Iservice.OnlineParseImpl");
          if (localObject != null) {
            b = (OnlineParseInterface)((Class)localObject).newInstance();
          }
        }
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
      }
    }
    return b;
  }
  
  public static OnlineViewInterface getOnlineViewImpl(boolean paramBoolean)
  {
    try
    {
      Object localObject = getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.OnlineViewImpl");
      if (localObject != null)
      {
        localObject = (OnlineViewInterface)((Class)localObject).newInstance();
        return (OnlineViewInterface)localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public static ParseCardInterface getParseCardImpl(boolean paramBoolean)
  {
    try
    {
      Object localObject = getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseUtilCard");
      if (localObject != null)
      {
        localObject = (ParseCardInterface)((Class)localObject).newInstance();
        return (ParseCardInterface)localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public static ParseDateInterface getParseDateImpl(boolean paramBoolean)
  {
    try
    {
      Object localObject = getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseDate");
      if (localObject != null)
      {
        localObject = (ParseDateInterface)((Class)localObject).newInstance();
        return (ParseDateInterface)localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public static ParseNotificationInterface getParseNotificationImpl(boolean paramBoolean)
  {
    try
    {
      Object localObject = getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseNotification");
      if (localObject != null)
      {
        localObject = (ParseNotificationInterface)((Class)localObject).newInstance();
        return (ParseNotificationInterface)localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public static ParsePayInterface getParsePayImpl(boolean paramBoolean)
  {
    try
    {
      Object localObject = getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseUtilPay");
      if (localObject != null)
      {
        localObject = (ParsePayInterface)((Class)localObject).newInstance();
        return (ParsePayInterface)localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public static ParseRemindInterface getParseRemindImpl(boolean paramBoolean)
  {
    try
    {
      Object localObject = getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseRemind");
      if (localObject != null)
      {
        localObject = (ParseRemindInterface)((Class)localObject).newInstance();
        return (ParseRemindInterface)localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public static ParseWatchInterface getParseWatchImpl(boolean paramBoolean)
  {
    try
    {
      Object localObject = getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseWatch");
      if (localObject != null)
      {
        localObject = (ParseWatchInterface)((Class)localObject).newInstance();
        return (ParseWatchInterface)localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public static String getSceneVersion()
  {
    Object localObject1 = null;
    try
    {
      String str = SysParamEntityManager.getStringParam(Constant.getContext(), "CHANNEL");
      localObject1 = str;
      Object localObject2 = getOnlineParseImpl(false);
      localObject1 = str;
      if (localObject2 != null)
      {
        localObject1 = str;
        localObject2 = ((OnlineParseInterface)localObject2).getSceneVersion(str);
        localObject1 = str;
        boolean bool = StringUtils.isNull((String)localObject2);
        localObject1 = str;
        if (!bool) {
          return (String)localObject2;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      LogManager.e("DexUtil getSceneVersion", "获取算法包内的资源版本号出现异常");
      if ("VMhlWdEwVNEW_LENOVO".equals(localObject1)) {
        return "20150619";
      }
    }
    return "20140815";
  }
  
  public static int getSmsTypeByMap(Map<String, Object> paramMap, int paramInt)
  {
    int i = -1;
    try
    {
      OnlineParseInterface localOnlineParseInterface = getOnlineParseImpl(false);
      new StringBuilder("lib=").append(localOnlineParseInterface);
      if (localOnlineParseInterface != null) {
        i = localOnlineParseInterface.getSmsTypeByMap(paramMap, paramInt);
      }
      return i;
    }
    catch (Throwable paramMap) {}
    return -1;
  }
  
  public static String getSuanfaVersion()
  {
    Object localObject1 = null;
    try
    {
      String str = SysParamEntityManager.getStringParam(Constant.getContext(), "CHANNEL");
      localObject1 = str;
      Object localObject2 = getOnlineParseImpl(false);
      localObject1 = str;
      if (localObject2 != null)
      {
        localObject1 = str;
        localObject2 = ((OnlineParseInterface)localObject2).getReqVersion(str);
        localObject1 = str;
        boolean bool = StringUtils.isNull((String)localObject2);
        localObject1 = str;
        if (!bool) {
          return (String)localObject2;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      LogManager.e("DexUtil getSuanfaVersion", "获取算法包内的版本号出现异常");
      if ("VMhlWdEwVNEW_LENOVO".equals(localObject1)) {
        return "20150619";
      }
    }
    return "20150202";
  }
  
  public static long getUpdateCycleByType(int paramInt, long paramLong)
  {
    try
    {
      if (c == null) {
        c = a();
      }
      long l = paramLong;
      if (c != null) {
        l = c.getUpdateCycle(paramInt, paramLong);
      }
      return l;
    }
    catch (Throwable localThrowable)
    {
      LogManager.e("xiaoyuan", "DexUtil getUpdateCycleByType：" + localThrowable.getMessage(), localThrowable);
    }
    return paramLong;
  }
  
  public static Map<String, Object> handerBubbleValueMap(Map<String, Object> paramMap)
  {
    try
    {
      Object localObject = getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseUtilBubble");
      if (localObject != null)
      {
        localObject = (ParseBubbleInterface)((Class)localObject).newInstance();
        if (localObject != null)
        {
          paramMap = ((ParseBubbleInterface)localObject).handerValueMap(paramMap);
          return paramMap;
        }
      }
    }
    catch (Throwable paramMap)
    {
      paramMap.printStackTrace();
    }
    return null;
  }
  
  public static Map<String, Object> handerDateValueMap(Map<String, Object> paramMap)
  {
    Map localMap = null;
    try
    {
      ParseDateInterface localParseDateInterface = getParseDateImpl(false);
      if (localParseDateInterface != null) {
        localMap = localParseDateInterface.handerValueMap(paramMap);
      }
      return localMap;
    }
    catch (Throwable paramMap)
    {
      paramMap.printStackTrace();
    }
    return null;
  }
  
  public static Map<String, Object> handerNotificationValueMap(Map<String, Object> paramMap)
  {
    Map localMap = null;
    try
    {
      ParseNotificationInterface localParseNotificationInterface = getParseNotificationImpl(false);
      if (localParseNotificationInterface != null) {
        localMap = localParseNotificationInterface.handerValueMap(paramMap);
      }
      return localMap;
    }
    catch (Throwable paramMap)
    {
      paramMap.printStackTrace();
    }
    return null;
  }
  
  public static Map<String, Object> handerPayValueMap(Map<String, Object> paramMap)
  {
    Map localMap = null;
    try
    {
      ParsePayInterface localParsePayInterface = getParsePayImpl(false);
      if (localParsePayInterface != null) {
        localMap = localParsePayInterface.handerValueMap(paramMap);
      }
      return localMap;
    }
    catch (Throwable paramMap)
    {
      paramMap.printStackTrace();
    }
    return null;
  }
  
  public static Map<String, Object> handerRemindValueMap(Map<String, Object> paramMap)
  {
    Map localMap = null;
    try
    {
      ParseRemindInterface localParseRemindInterface = getParseRemindImpl(false);
      if (localParseRemindInterface != null) {
        localMap = localParseRemindInterface.handerValueMap(paramMap);
      }
      return localMap;
    }
    catch (Throwable paramMap)
    {
      paramMap.printStackTrace();
    }
    return null;
  }
  
  public static Map<String, Object> handerValueMap(Map<String, Object> paramMap)
  {
    Map localMap = null;
    try
    {
      ParseCardInterface localParseCardInterface = getParseCardImpl(false);
      if (localParseCardInterface != null) {
        localMap = localParseCardInterface.handerValueMap(paramMap);
      }
      return localMap;
    }
    catch (Throwable paramMap)
    {
      paramMap.printStackTrace();
    }
    return null;
  }
  
  public static Map<String, Object> handerValueMap(Map<String, Object> paramMap, String paramString)
  {
    Map localMap = null;
    try
    {
      OnlineViewInterface localOnlineViewInterface = getOnlineViewImpl(false);
      if (localOnlineViewInterface != null) {
        localMap = localOnlineViewInterface.handerValueMap(paramMap, paramString);
      }
      return localMap;
    }
    catch (Throwable paramMap)
    {
      paramMap.printStackTrace();
    }
    return null;
  }
  
  public static Map<String, Object> handerValueMapByType(int paramInt, Map<String, Object> paramMap)
  {
    try
    {
      Object localObject = getClassBymap(null, "cn.com.xy.sms.sdk.Iservice.ParseVerifyCode");
      if (localObject != null)
      {
        localObject = (ParseVerifyCodeInterface)((Class)localObject).newInstance();
        if (localObject != null)
        {
          paramMap = ((ParseVerifyCodeInterface)localObject).handerValueMapByType(paramInt, paramMap);
          return paramMap;
        }
      }
    }
    catch (Throwable paramMap)
    {
      paramMap.printStackTrace();
    }
    return null;
  }
  
  public static JSONObject handerWatchValueMap(Map<String, Object> paramMap)
  {
    JSONObject localJSONObject = null;
    try
    {
      ParseWatchInterface localParseWatchInterface = getParseWatchImpl(false);
      if (localParseWatchInterface != null) {
        localJSONObject = localParseWatchInterface.handerValueMap(paramMap);
      }
      return localJSONObject;
    }
    catch (Throwable paramMap)
    {
      paramMap.printStackTrace();
    }
    return null;
  }
  
  public static void init()
  {
    try
    {
      if (new File(Constant.getJarPath()).exists()) {
        if (l.a(Constant.getJarPath()).booleanValue())
        {
          a = getDexClassLoader(true);
          b = getOnlineParseImpl(true);
          if (!LogManager.debug) {}
        }
        else
        {
          boolean bool = LogManager.debug;
          if (bool) {}
        }
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public static void initOnlineUpdateCycleConfig()
  {
    c = a();
  }
  
  public static boolean isEnterpriseSms(Context paramContext, String paramString1, String paramString2, Map<String, String> paramMap)
  {
    try
    {
      OnlineParseInterface localOnlineParseInterface = getOnlineParseImpl(false);
      new StringBuilder("lib=").append(localOnlineParseInterface);
      if (localOnlineParseInterface != null) {
        return localOnlineParseInterface.isEnterpriseSms(paramContext, paramString1, paramString2, paramMap);
      }
      boolean bool = PopupUtil.isEnterpriseSms(paramContext, paramString1, paramString2, paramMap);
      return bool;
    }
    catch (Throwable localThrowable) {}
    return PopupUtil.isEnterpriseSms(paramContext, paramString1, paramString2, paramMap);
  }
  
  public static int isServiceChoose(String paramString1, String paramString2)
  {
    int i = -1;
    try
    {
      OnlineParseInterface localOnlineParseInterface = getOnlineParseImpl(false);
      new StringBuilder("lib=").append(localOnlineParseInterface);
      if (localOnlineParseInterface != null) {
        i = localOnlineParseInterface.isServiceChoose(paramString1, paramString2);
      }
      return i;
    }
    catch (Throwable paramString1)
    {
      new StringBuilder("result=-1");
    }
    return -2;
  }
  
  public static Map<String, Object> parseMsgToMap(String paramString1, String paramString2, Map<String, String> paramMap)
  {
    Object localObject;
    try
    {
      localObject = getOnlineParseImpl(false);
      if (localObject != null)
      {
        localObject = ((OnlineParseInterface)localObject).parseMessage(paramString1, paramString2, paramMap);
        if (localObject != null) {
          return localObject;
        }
      }
    }
    catch (Throwable paramString2)
    {
      paramString1 = null;
      label140:
      paramString2.printStackTrace();
      return paramString1;
    }
    try
    {
      LogManager.e("duoqu_test", "&&&&&result is null phoneNumber:" + paramString1 + " smsContent: " + paramString2 + " msgID: " + (String)paramMap.get("msgId"), null);
      return (Map<String, Object>)localObject;
    }
    catch (Throwable paramString2)
    {
      paramString1 = (String)localObject;
      break label140;
    }
    LogManager.e("duoqu_test", "lib = DexUtil.getOnlineParseImpl is null phoneNumber:" + paramString1 + " smsContent: " + paramString2 + " msgID: " + (String)paramMap.get("msgId"), null);
    return null;
    return (Map<String, Object>)localObject;
  }
  
  public static String[] parseMsgToNewContacts(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString)
  {
    String[] arrayOfString = null;
    try
    {
      OnlineParseInterface localOnlineParseInterface = getOnlineParseImpl(false);
      new StringBuilder("lib=").append(localOnlineParseInterface);
      if (localOnlineParseInterface != null) {
        arrayOfString = localOnlineParseInterface.parseMsgToNewContacts(paramString1, paramString2, paramString3, paramArrayOfString);
      }
      return arrayOfString;
    }
    catch (Throwable paramString1) {}
    return null;
  }
  
  public static void removeClassLoaderBySubname(String paramString)
  {
    try
    {
      d.remove(paramString + "_ClassLoader");
      e.remove(paramString + "_Class");
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.dex.DexUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package cn.com.xy.sms.sdk.ui.popu.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.LruCache;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.queue.BubbleTaskQueue;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.util.ParseBubbleManager;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.ParseNotificationManager;
import cn.com.xy.sms.util.ParseRichBubbleManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class XySdkUtil
{
  public static final int DUOQU_BUBBLE_DATA_CACHE_SIZE = 200;
  private static final int PARSE_MSG_TYPE_SIMPLE_AND_RICH = 3;
  public static final String SMARTSMS_BUBBLE = "smartsms_bubble";
  public static final String SMARTSMS_ENHANCE = "smartsms_enhance";
  public static final String SMARTSMS_HAS_SHOW_FIRST = "smartsms_has_show_first";
  public static final String SMARTSMS_NO_SHOW_AGAIN = "smartsms_no_show_again";
  public static final int SMARTSMS_PARSE_TYPE_NOTIFY = 1;
  public static final int SMARTSMS_PARSE_TYPE_ONLY_BUBBLE = 0;
  public static final int SMARTSMS_PARSE_TYPE_POUPP = 2;
  public static final String SMARTSMS_SWITCH = "smartsms_switch";
  public static final String SMARTSMS_UPDATE_TYPE = "smartsms_update_type";
  public static final String TAG = "XIAOYUAN";
  private static final LruCache<Long, JSONObject> mBubbleDataCache = new LruCache(200);
  private static LruCache<String, Map<String, Object>> notifyDataCacheMap = new LruCache(10);
  
  public static void clearCache(int paramInt, String paramString)
  {
    if (mBubbleDataCache != null) {
      mBubbleDataCache.evictAll();
    }
    ParseBubbleManager.clearAllCache(paramString);
    ParseRichBubbleManager.clearCacheBubbleData(paramString);
  }
  
  public static String formatPhoneNum(String paramString)
  {
    if (paramString == null) {
      paramString = null;
    }
    String str;
    do
    {
      return paramString;
      str = paramString.replaceAll(" ", "").replaceAll("-", "");
      if (str.startsWith("86")) {
        return str.substring(2, str.length());
      }
      if (str.startsWith("+86")) {
        return str.substring(3, str.length());
      }
      paramString = str;
    } while (!str.startsWith("0086"));
    return str.substring(4, str.length());
  }
  
  public static JSONObject getBubbleDataFromCache(Long paramLong)
  {
    if (paramLong == null) {
      return null;
    }
    return (JSONObject)mBubbleDataCache.get(paramLong);
  }
  
  public static HashMap<String, String> getExtendMap(int paramInt, String paramString, long paramLong)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("simIndex", String.valueOf(paramInt));
    localHashMap.put("simName", getSimNameBySimIndex(paramInt));
    localHashMap.put("msgId", paramString);
    localHashMap.put("opensms_enable", "true");
    localHashMap.put("msgTime", String.valueOf(paramLong));
    localHashMap.put("handle_type", "1");
    return localHashMap;
  }
  
  public static String getICCID(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (!StringUtils.isNull(paramContext.getSimSerialNumber()))
      {
        paramContext = paramContext.getSimSerialNumber();
        return paramContext;
      }
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static Map<String, Object> getNotifyDataCacheByMsgId(long paramLong, boolean paramBoolean)
  {
    String str = String.valueOf(paramLong);
    Map localMap = (Map)notifyDataCacheMap.get(str);
    if ((localMap != null) && (paramBoolean)) {
      notifyDataCacheMap.remove(str);
    }
    return localMap;
  }
  
  public static String getSimNameBySimIndex(int paramInt)
  {
    return "";
  }
  
  public static void init(Context paramContext, String paramString1, String paramString2, AbsSdkDoAction paramAbsSdkDoAction, String paramString3, HashMap<String, String> paramHashMap)
  {
    if (paramHashMap == null) {}
    for (;;)
    {
      try
      {
        paramHashMap = new HashMap();
        paramHashMap.put("SECRETKEY", paramString2);
        ParseManager.initSdk(paramContext, paramString1, paramString3, true, true, paramHashMap);
        ParseManager.setSdkDoAction(paramAbsSdkDoAction);
        paramContext = new XySdkUtil.1();
        paramContext.sendMessageDelayed(paramContext.obtainMessage(), 6000L);
        return;
      }
      catch (Exception paramContext)
      {
        return;
      }
    }
  }
  
  public static void init(Context paramContext, String paramString1, String paramString2, AbsSdkDoAction paramAbsSdkDoAction, HashMap<String, String> paramHashMap)
  {
    init(paramContext, paramString1, paramString2, paramAbsSdkDoAction, getICCID(paramContext), paramHashMap);
  }
  
  public static void parseMsg(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, int paramInt1, int paramInt2)
  {
    if (paramInt1 == 0) {}
    try
    {
      BubbleTaskQueue.addDataToQueue(0, paramString1, paramString3, paramString2, paramString4, paramLong, 3, null);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      return;
    }
    if (paramInt1 == 1)
    {
      paramString2 = ParseNotificationManager.parseNotificationMsg(Constant.getContext(), paramString1, paramString3, paramString4, paramString2, paramLong, null);
      if ((paramString2 != null) && (paramString2.size() > 1)) {
        notifyDataCacheMap.put(paramString1, paramString2);
      }
    }
    else if (paramInt1 == 2)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("simIndex", String.valueOf(paramInt2));
      localHashMap.put("simName", getSimNameBySimIndex(paramInt2));
      localHashMap.put("msgId", String.valueOf(paramString1));
      localHashMap.put("opensms_enable", "true");
      ParseManager.parseMsgToPopupWindow(Constant.getContext(), paramString3, paramString4, paramString2, localHashMap);
      BubbleTaskQueue.addDataToQueue(0, paramString1, paramString3, paramString2, paramString4, paramLong, 3, null);
    }
  }
  
  public static void parseMsg(String paramString, SmsMessage[] paramArrayOfSmsMessage, int paramInt1, int paramInt2)
  {
    for (;;)
    {
      Object localObject1;
      Object localObject2;
      String str;
      int i;
      HashMap localHashMap;
      try
      {
        int j = paramArrayOfSmsMessage.length;
        if (j == 1)
        {
          localObject1 = replaceFormFeeds(paramArrayOfSmsMessage[0].getDisplayMessageBody());
          localObject2 = paramArrayOfSmsMessage[0].getOriginatingAddress();
          str = paramArrayOfSmsMessage[0].getServiceCenterAddress();
          if (paramInt1 == 0) {
            BubbleTaskQueue.addDataToQueue(0, paramString, (String)localObject2, (String)localObject1, str, paramArrayOfSmsMessage[0].getTimestampMillis(), 3, null);
          }
        }
        else
        {
          localObject1 = new StringBuilder();
          i = 0;
          if (i < j)
          {
            localObject2 = paramArrayOfSmsMessage[i];
            if (localObject2 == null) {
              break label285;
            }
            ((StringBuilder)localObject1).append(((SmsMessage)localObject2).getDisplayMessageBody());
            break label285;
          }
          localObject1 = replaceFormFeeds(((StringBuilder)localObject1).toString());
          continue;
        }
        if (paramInt1 == 1)
        {
          localHashMap = getExtendMap(paramInt2, paramString, paramArrayOfSmsMessage[0].getTimestampMillis());
          paramArrayOfSmsMessage = ParseNotificationManager.parseNotificationMsg(Constant.getContext(), paramString, (String)localObject2, str, (String)localObject1, paramArrayOfSmsMessage[0].getTimestampMillis(), localHashMap);
          if ((paramArrayOfSmsMessage == null) || (paramArrayOfSmsMessage.size() <= 1)) {
            break label284;
          }
          notifyDataCacheMap.put(paramString, paramArrayOfSmsMessage);
          return;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return;
      }
      if (paramInt1 == 2)
      {
        paramString = getExtendMap(paramInt2, paramString, paramArrayOfSmsMessage[0].getTimestampMillis());
        ParseManager.parseMsgToPopupWindow(Constant.getContext(), (String)localObject2, str, (String)localObject1, true, paramString);
        return;
      }
      if (paramInt1 == 3)
      {
        localHashMap = getExtendMap(paramInt2, paramString, paramArrayOfSmsMessage[0].getTimestampMillis());
        paramArrayOfSmsMessage = ParseNotificationManager.parseNotificationMsgAndPopupWindow(Constant.getContext(), paramString, (String)localObject2, str, (String)localObject1, paramArrayOfSmsMessage[0].getTimestampMillis(), localHashMap);
        if ((paramArrayOfSmsMessage != null) && (paramArrayOfSmsMessage.size() > 1)) {
          notifyDataCacheMap.put(paramString, paramArrayOfSmsMessage);
        }
      }
      label284:
      return;
      label285:
      i += 1;
    }
  }
  
  public static void putBubbleDataToCache(Long paramLong, JSONObject paramJSONObject)
  {
    if ((paramLong == null) || (paramJSONObject == null)) {
      return;
    }
    synchronized (mBubbleDataCache)
    {
      mBubbleDataCache.put(paramLong, paramJSONObject);
      return;
    }
  }
  
  private static String replaceFormFeeds(String paramString)
  {
    String str = "";
    if (paramString != null) {
      str = paramString.replace('\f', '\n');
    }
    return str;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.util.XySdkUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
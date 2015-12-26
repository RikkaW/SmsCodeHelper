package com.meizu.cloud.pushsdk.util;

import android.content.Context;
import android.util.Log;
import com.meizu.statsapp.UsageStatsProxy;
import java.util.HashMap;
import java.util.Map;

public class UxIPUtils
{
  private static final String TAG = "UxIPUtils";
  
  public static void init(Context paramContext)
  {
    UsageStatsProxy.a(paramContext, true);
  }
  
  public static void onClickPushMessageEvent(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Log.i("UxIPUtils", "onClickPushMessageLog packageName " + paramString1 + " deviceId= " + paramString2);
    HashMap localHashMap = new HashMap();
    localHashMap.put("taskId", paramString3);
    localHashMap.put("imei", paramString2);
    localHashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000L));
    localHashMap.put("package_name", paramString1);
    UsageStatsProxy.a(paramContext, true).a("click_push_message", localHashMap);
  }
  
  public static void onReceivePushMessageEvent(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Log.i("UxIPUtils", "onReceivePushMessageLog packageName " + paramString1 + " deviceId= " + paramString2);
    HashMap localHashMap = new HashMap();
    localHashMap.put("taskId", paramString3);
    localHashMap.put("imei", paramString2);
    localHashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000L));
    localHashMap.put("package_name", paramString1);
    UsageStatsProxy.a(paramContext, true).a("receive_push_event", localHashMap);
  }
}

/* Location:
 * Qualified Name:     com.meizu.cloud.pushsdk.util.UxIPUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
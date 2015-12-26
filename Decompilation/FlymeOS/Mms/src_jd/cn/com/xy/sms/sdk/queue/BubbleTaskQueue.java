package cn.com.xy.sms.sdk.queue;

import android.content.Context;
import android.content.Intent;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.util.ParseSmsToBubbleUtil;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.JSONObject;

public class BubbleTaskQueue
{
  private static BlockingQueue<JSONObject> a = new LinkedBlockingQueue();
  private static Thread b = null;
  
  public static void addDataToQueue(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, int paramInt2, JSONObject paramJSONObject)
  {
    addDataToQueue(paramInt1, paramString1, paramString2, paramString3, paramString4, paramLong, paramInt2, paramJSONObject, 0);
  }
  
  public static void addDataToQueue(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, int paramInt2, JSONObject paramJSONObject, int paramInt3)
  {
    JSONObject localJSONObject = paramJSONObject;
    if (paramJSONObject == null) {
      localJSONObject = new JSONObject();
    }
    try
    {
      localJSONObject.put("dataStatu", paramInt1);
      localJSONObject.put("msg_id", paramString1);
      localJSONObject.put("phoneNum", paramString2);
      localJSONObject.put("smsContent", paramString3);
      localJSONObject.put("smsReceiveTime", paramLong);
      if (paramString4 != null) {
        localJSONObject.put("centerNum", paramString4);
      }
      localJSONObject.put("dataType", paramInt2);
      localJSONObject.put("isNeedNotify", paramInt3);
      a.put(localJSONObject);
      startTaskQueue();
      return;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  private static void b(JSONObject paramJSONObject)
  {
    Integer localInteger = (Integer)JsonUtil.getValueFromJsonObject(paramJSONObject, "dataType");
    ParseSmsToBubbleUtil.parseSmsToBubbleResultMap((String)JsonUtil.getValueFromJsonObject(paramJSONObject, "msg_id"), (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "phoneNum"), (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "smsContent"), (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "centerNum"), ((Long)JsonUtil.getValueFromJsonObject(paramJSONObject, "smsReceiveTime")).longValue(), localInteger.intValue(), true, true, null);
    try
    {
      paramJSONObject = (Integer)JsonUtil.getValueFromJsonObject(paramJSONObject, "isNeedNotify");
      if (paramJSONObject != null)
      {
        int i = paramJSONObject.intValue();
        if (1 != i) {}
      }
      try
      {
        paramJSONObject = new Intent("cn.com.xy.douqu.reflashlist");
        Constant.getContext().sendBroadcast(paramJSONObject);
        return;
      }
      catch (Throwable paramJSONObject)
      {
        paramJSONObject.printStackTrace();
        return;
      }
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  public static void startTaskQueue()
  {
    try
    {
      if (b == null)
      {
        a locala = new a();
        b = locala;
        locala.start();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.queue.BubbleTaskQueue
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package cn.com.xy.sms.sdk.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.JSONObject;

public final class e
{
  public static BlockingQueue<JSONObject> a = new LinkedBlockingQueue();
  private static Thread b = null;
  
  private static void a()
  {
    try
    {
      if (b == null)
      {
        f localf = new f();
        b = localf;
        localf.start();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static void a(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt2, long paramLong, JSONObject paramJSONObject)
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
      a.put(localJSONObject);
      a();
      return;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.queue.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
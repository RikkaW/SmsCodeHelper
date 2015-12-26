package cn.com.xy.sms.util;

import android.os.Process;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.v;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONObject;

final class p
  implements Runnable
{
  p(String paramString1, int paramInt, String paramString2, String paramString3, long paramLong, SdkCallBack paramSdkCallBack, String paramString4, HashMap paramHashMap) {}
  
  public final void run()
  {
    for (;;)
    {
      try
      {
        v.i.add(a);
        Process.setThreadPriority(-16);
        if (b != 0) {
          break label299;
        }
        ??? = ParseRichBubbleManager.queryBubbleDataFromDb(a, c, d, e);
        if ((??? == null) || (((JSONObject)???).has("need_parse_bubble"))) {
          break label299;
        }
        localJSONObject1 = (JSONObject)JsonUtil.getValueFromJsonObject((JSONObject)???, "bubble_result");
        if (localJSONObject1 == null) {
          continue;
        }
      }
      catch (Throwable localThrowable)
      {
        JSONObject localJSONObject1;
        XyUtil.doXycallBackResult(f, new Object[] { Integer.valueOf(-3), "error: " + localThrowable.getLocalizedMessage(), a });
        localThrowable.printStackTrace();
        return;
        v.h.add(a);
        v.f.remove(a);
        XyUtil.doXycallBackResult(f, new Object[] { Integer.valueOf(-3), " invalid data ", a });
        continue;
      }
      finally
      {
        v.i.remove(a);
      }
      synchronized (v.g)
      {
        v.g.put(a, localJSONObject1);
        v.f.remove(a);
        v.h.remove(a);
        XyUtil.doXycallBackResult(f, new Object[] { Integer.valueOf(1), localJSONObject1, a });
        v.i.remove(a);
        return;
      }
    }
    label299:
    JSONObject localJSONObject2 = ParseRichBubbleManager.queryBubbleDataFromApi(a, c, d, g, e, h);
    if (localJSONObject2 != null) {}
    for (;;)
    {
      synchronized (v.g)
      {
        v.g.put(a, localJSONObject2);
        v.h.remove(a);
        v.f.remove(a);
        XyUtil.doXycallBackResult(f, new Object[] { Integer.valueOf(1), localJSONObject2, a });
        v.i.remove(a);
        return;
      }
      v.h.add(a);
      v.f.remove(a);
      LogManager.e("duoqu_xiaoyuan", "add  invalidBubbleData msgId:  " + a, null);
      XyUtil.doXycallBackResult(f, new Object[] { Integer.valueOf(-3), " parse failed ", a });
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.p
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
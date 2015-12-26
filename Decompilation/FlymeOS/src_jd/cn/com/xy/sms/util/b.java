package cn.com.xy.sms.util;

import android.os.Process;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.v;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

final class b
  implements Runnable
{
  b(v paramv, String paramString1, String paramString2, String paramString3, long paramLong, SdkCallBack paramSdkCallBack, String paramString4, int paramInt) {}
  
  public final void run()
  {
    try
    {
      Process.setThreadPriority(-16);
      a.e.add(b);
      ??? = ParseRichBubbleManager.queryBubbleDataFromDb(b, c, d, e);
      if ((??? == null) || (((JSONObject)???).has("need_parse_simple"))) {
        break label284;
      }
      JSONArray localJSONArray1 = (JSONArray)JsonUtil.getValueFromJsonObject((JSONObject)???, "session_reuslt");
      if (localJSONArray1 != null) {
        synchronized (a.c)
        {
          a.c.put(b, localJSONArray1);
          a.d.remove(b);
          a.e.remove(b);
          XyUtil.doXycallBackResult(f, new Object[] { Integer.valueOf(1), localJSONArray1, b });
          return;
        }
      }
      a.d.add(b);
    }
    catch (Throwable localThrowable)
    {
      LogManager.e("RichBubbleTaskQueue", localThrowable.getLocalizedMessage(), localThrowable);
      XyUtil.doXycallBackResult(f, new Object[] { Integer.valueOf(-3), "result is null: error: " + localThrowable.getMessage(), b });
      return;
    }
    a.e.remove(b);
    XyUtil.doXycallBackResult(f, new Object[] { Integer.valueOf(-3), " invalid data need_parse_simple", b });
    return;
    label284:
    JSONArray localJSONArray2 = ParseBubbleManager.a(b, c, g, d, e);
    if (localJSONArray2 != null) {
      synchronized (a.c)
      {
        a.c.put(b, localJSONArray2);
        a.e.remove(b);
        XyUtil.doXycallBackResult(f, new Object[] { Integer.valueOf(1), localJSONArray2, b });
        return;
      }
    }
    a.d.add(b);
    a.e.remove(b);
    XyUtil.doXycallBackResult(f, new Object[] { Integer.valueOf(-3), "$$$$$$$$$$ dataType: " + h, b });
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package cn.com.xy.sms.sdk.queue;

import android.os.Process;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.v;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.ParseRichBubbleManager;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import org.json.JSONObject;

final class f
  extends Thread
{
  public final void run()
  {
    setName("xiaoyuan_bubbletaskqueue");
    Process.setThreadPriority(g.b);
    label126:
    label271:
    label333:
    label528:
    label841:
    label846:
    label851:
    label854:
    label856:
    label861:
    label864:
    for (;;)
    {
      Object localObject1;
      Object localObject2;
      long l;
      int i;
      try
      {
        localJSONObject = (JSONObject)e.a.take();
        if (localJSONObject == null) {
          continue;
        }
        localObject1 = (Integer)JsonUtil.getValueFromJsonObject(localJSONObject, "dataStatu");
        if (localObject1 == null) {
          continue;
        }
        localObject2 = (Integer)JsonUtil.getValueFromJsonObject(localJSONObject, "dataType");
        if (localObject2 == null) {
          continue;
        }
        if (((Integer)localObject2).intValue() != 2) {
          break label271;
        }
        if (((Integer)localObject1).intValue() != 2) {
          break label846;
        }
        localObject1 = (String)JsonUtil.getValueFromJsonObject(localJSONObject, "save_time");
        if (StringUtils.isNull((String)localObject1)) {
          break label841;
        }
        l = Long.valueOf((String)localObject1).longValue();
        if (System.currentTimeMillis() - l <= 2592000L) {
          break label841;
        }
      }
      catch (Throwable localThrowable)
      {
        JSONObject localJSONObject;
        LogManager.e("RichBubbleTaskQueue", localThrowable.getLocalizedMessage(), localThrowable);
      }
      if (i != 0)
      {
        localObject1 = ParseManager.parseMsgToBubbleCardResult(Constant.getContext(), (String)JsonUtil.getValueFromJsonObject(localJSONObject, "msg_id"), (String)JsonUtil.getValueFromJsonObject(localJSONObject, "phoneNum"), (String)JsonUtil.getValueFromJsonObject(localJSONObject, "centerNum"), (String)JsonUtil.getValueFromJsonObject(localJSONObject, "smsContent"), ((Long)JsonUtil.getValueFromJsonObject(localJSONObject, "smsReceiveTime")).longValue(), (byte)1, null);
        if (localObject1 != null)
        {
          localObject1 = (Long)((Map)localObject1).get("CACHE_SDK_MSG_ID");
          if (localObject1 != null)
          {
            localObject1 = MatchCacheManager.loadDataByParam("id=?", new String[] { String.valueOf(localObject1) });
            v.b((String)JsonUtil.getValueFromJsonObject(localJSONObject, "phoneNum"), (Map)localObject1);
            continue;
            continue;
            if (((Integer)localObject2).intValue() == 1)
            {
              if (((Integer)localObject1).intValue() != 2) {
                break label851;
              }
              localObject1 = (String)JsonUtil.getValueFromJsonObject(localThrowable, "session_lasttime");
              if (!StringUtils.isNull((String)localObject1))
              {
                l = Long.valueOf((String)localObject1).longValue();
                if (System.currentTimeMillis() - l > 2592000L)
                {
                  break label851;
                  if (i == 0) {
                    break label854;
                  }
                  localObject1 = ParseRichBubbleManager.parseMsgToSimpleBubbleResult(Constant.getContext(), (String)JsonUtil.getValueFromJsonObject(localThrowable, "msg_id"), (String)JsonUtil.getValueFromJsonObject(localThrowable, "phoneNum"), (String)JsonUtil.getValueFromJsonObject(localThrowable, "centerNum"), (String)JsonUtil.getValueFromJsonObject(localThrowable, "smsContent"), (byte)1, null);
                  if (localObject1 == null) {
                    continue;
                  }
                  localObject1 = (Long)((Map)localObject1).get("CACHE_SDK_MSG_ID");
                  if ((localObject1 == null) || (((Long)localObject1).longValue() <= -1L)) {
                    continue;
                  }
                  localObject1 = MatchCacheManager.loadDataByParam("id=?", new String[] { String.valueOf(localObject1) });
                  v.a((String)JsonUtil.getValueFromJsonObject(localThrowable, "phoneNum"), (Map)localObject1);
                }
              }
            }
            else
            {
              if (((Integer)localObject2).intValue() != 3) {
                continue;
              }
              int j = 0;
              if (((Integer)localObject1).intValue() != 2) {
                break label856;
              }
              localObject2 = (String)JsonUtil.getValueFromJsonObject(localThrowable, "session_lasttime");
              i = j;
              if (!StringUtils.isNull((String)localObject2))
              {
                l = Long.valueOf((String)localObject2).longValue();
                i = j;
                if (System.currentTimeMillis() - l > 2592000L) {
                  break label856;
                }
              }
              if (i != 0)
              {
                localObject2 = ParseRichBubbleManager.parseMsgToSimpleBubbleResult(Constant.getContext(), (String)JsonUtil.getValueFromJsonObject(localThrowable, "msg_id"), (String)JsonUtil.getValueFromJsonObject(localThrowable, "phoneNum"), (String)JsonUtil.getValueFromJsonObject(localThrowable, "centerNum"), (String)JsonUtil.getValueFromJsonObject(localThrowable, "smsContent"), (byte)1, null);
                if (localObject2 != null)
                {
                  localObject2 = (Long)((Map)localObject2).get("CACHE_SDK_MSG_ID");
                  if ((localObject2 != null) && (((Long)localObject2).longValue() > -1L))
                  {
                    localObject2 = MatchCacheManager.loadDataByParam("id=?", new String[] { String.valueOf(localObject2) });
                    v.a((String)JsonUtil.getValueFromJsonObject(localThrowable, "phoneNum"), (Map)localObject2);
                  }
                }
              }
              if (((Integer)localObject1).intValue() != 2) {
                break label861;
              }
              localObject1 = (String)JsonUtil.getValueFromJsonObject(localThrowable, "save_time");
              if (!StringUtils.isNull((String)localObject1))
              {
                l = Long.valueOf((String)localObject1).longValue();
                if (System.currentTimeMillis() - l > 2592000L) {
                  break label861;
                }
              }
            }
            for (;;)
            {
              if (i == 0) {
                break label864;
              }
              localObject1 = ParseManager.parseMsgToBubbleCardResult(Constant.getContext(), (String)JsonUtil.getValueFromJsonObject(localThrowable, "msg_id"), (String)JsonUtil.getValueFromJsonObject(localThrowable, "phoneNum"), (String)JsonUtil.getValueFromJsonObject(localThrowable, "centerNum"), (String)JsonUtil.getValueFromJsonObject(localThrowable, "smsContent"), ((Long)JsonUtil.getValueFromJsonObject(localThrowable, "smsReceiveTime")).longValue(), (byte)1, null);
              if (localObject1 == null) {
                break;
              }
              localObject1 = (Long)((Map)localObject1).get("CACHE_SDK_MSG_ID");
              if (localObject1 == null) {
                break;
              }
              localObject1 = MatchCacheManager.loadDataByParam("id=?", new String[] { String.valueOf(localObject1) });
              v.b((String)JsonUtil.getValueFromJsonObject(localThrowable, "phoneNum"), (Map)localObject1);
              break;
              continue;
              i = 0;
              break label333;
              i = 0;
              break label126;
              i = 1;
              break label126;
              i = 1;
              break label333;
              break;
              i = 1;
              break label528;
              i = 1;
            }
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.queue.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
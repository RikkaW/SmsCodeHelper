package cn.com.xy.sms.sdk.queue;

import android.os.Process;
import cn.com.xy.sms.sdk.log.LogManager;
import java.util.concurrent.BlockingQueue;
import org.json.JSONObject;

final class a
  extends Thread
{
  public final void run()
  {
    setName("xiaoyuan_taskbubblequeue");
    Process.setThreadPriority(g.b);
    try
    {
      for (;;)
      {
        JSONObject localJSONObject = (JSONObject)BubbleTaskQueue.a().take();
        if (localJSONObject != null) {
          BubbleTaskQueue.a(localJSONObject);
        }
      }
    }
    catch (Throwable localThrowable)
    {
      LogManager.e("BubbleTaskQueue", localThrowable.getLocalizedMessage(), localThrowable);
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.queue.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
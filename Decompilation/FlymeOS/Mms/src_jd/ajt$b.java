import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.meizu.statsapp.UsageStatusLog;
import com.meizu.statsapp.util.Utils;
import java.util.Map;

class ajt$b
  extends Handler
{
  public ajt$b(ajt paramajt, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    int j = 0;
    int i = 0;
    for (;;)
    {
      try
      {
        switch (what)
        {
        case 3: 
          if (ajt.b(a))
          {
            if (!ajt.c(a))
            {
              ajt.a(a, true);
              a.b();
            }
            UsageStatusLog.d("UsageStatsUploader", "NETWORK_STATE_CHANGED, mNetworkConnected=" + ajt.c(a));
            return;
          }
          break;
        }
      }
      catch (Exception paramMessage)
      {
        paramMessage.printStackTrace();
        return;
      }
      if (ajt.c(a)) {
        ajt.a(a, false);
      }
    }
    UsageStatusLog.d("UsageStatsUploader", "UPLOAD_TIME_ALARM");
    a.b();
    ajt.a(a, System.currentTimeMillis());
    return;
    long l = System.currentTimeMillis();
    if (l < ajt.d(a))
    {
      a.b();
      ajt.a(a, l);
      return;
      a.b();
      return;
      ajt.e(a);
      for (;;)
      {
        try
        {
          ajt.f(a).removeMessages(1);
          if (ajt.a(a).a() > 0)
          {
            ajt.a(a).b();
            if (!Utils.isWiFiWorking(ajt.g(a))) {
              continue;
            }
            UsageStatusLog.d("UsageStatsUploader", "upload by wifi");
            paramMessage = ajt.h(a);
            j = i;
            i = j;
            if (ajt.a(a, paramMessage, true))
            {
              i = j;
              if (j == 0) {
                i = 1;
              }
              if (ajt.a(a).a() >= 1) {
                continue;
              }
            }
            if ((i != 0) && (!ajt.i(a)))
            {
              l = System.currentTimeMillis();
              ajt.b(a, l);
              ajt.a(a, l);
            }
            if ((ajt.i(a)) && (ajt.k(a)))
            {
              if (i == 0)
              {
                ajt.a(a, ajt.l(a) * 2);
                ajt.m(a).edit().putInt("ratio", ajt.l(a)).apply();
              }
              ajt.b(a, 0);
              ajt.c(a, false);
            }
          }
          ajt.n(a).clear();
        }
        catch (Exception paramMessage)
        {
          paramMessage.printStackTrace();
          continue;
        }
        if (!ajt.i(a)) {
          break;
        }
        ajt.f(a).sendEmptyMessageDelayed(1, 1800000L);
        return;
        paramMessage = ajt.h(a);
        j = i;
        continue;
        i = j;
        if (!Utils.isWiFiWorking(ajt.g(a)))
        {
          i = j;
          if (Utils.isNetworkWorking(ajt.g(a)))
          {
            UsageStatusLog.d("UsageStatsUploader", "upload by mobile");
            if (ajt.i(a))
            {
              paramMessage = ajt.h(a);
              i = j;
              if (ajt.a(a, paramMessage, false)) {
                i = 1;
              }
            }
            else
            {
              paramMessage = ajt.j(a);
              i = j;
              if (ajt.a(a, paramMessage, false))
              {
                ajt.b(a, false);
                i = 1;
              }
            }
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     ajt.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
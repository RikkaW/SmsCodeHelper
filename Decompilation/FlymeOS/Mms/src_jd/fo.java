import android.provider.Settings.System;
import cn.com.xy.sms.util.ParseManager;
import com.android.mms.MmsApp;
import com.android.mms.util.TimerMessageHelpler;

public class fo
  implements Runnable
{
  public fo(MmsApp paramMmsApp) {}
  
  public void run()
  {
    if (Settings.System.getInt(a.getContentResolver(), "mz_easy_mode", 0) != 0) {}
    for (boolean bool = true;; bool = false)
    {
      MmsApp.n = bool;
      MmsApp.a(MmsApp.v());
      zv.a(MmsApp.v());
      abe.a(MmsApp.v(), false);
      TimerMessageHelpler.a(MmsApp.v());
      MmsApp.b();
      wd.l(MmsApp.v());
      mb.a();
      ParseManager.setSdkDoAction(new ne());
      abu.a(MmsApp.v());
      aat.a(MmsApp.v());
      return;
    }
  }
}

/* Location:
 * Qualified Name:     fo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
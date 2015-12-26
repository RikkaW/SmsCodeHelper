import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings.System;
import com.meizu.statsapp.UsageStatusLog;

class ajl$b
  extends ContentObserver
{
  public ajl$b(ajl paramajl, Handler paramHandler)
  {
    super(paramHandler);
  }
  
  public void onChange(boolean paramBoolean, Uri paramUri)
  {
    paramBoolean = false;
    if (Settings.System.getInt(ajl.d(a).getContentResolver(), "meizu_data_collection", 0) != 0) {
      paramBoolean = true;
    }
    UsageStatusLog.d("UsageStatsManager", "usage stats switch changed : " + paramBoolean);
    if (ajl.e(a) != paramBoolean) {
      ajl.b(a, paramBoolean);
    }
  }
}

/* Location:
 * Qualified Name:     ajl.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
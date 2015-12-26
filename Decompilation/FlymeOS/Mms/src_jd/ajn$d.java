import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings.System;
import com.meizu.statsapp.UsageStatusLog;

class ajn$d
  extends ContentObserver
{
  public ajn$d(ajn paramajn, Handler paramHandler)
  {
    super(paramHandler);
  }
  
  public void onChange(boolean paramBoolean, Uri paramUri)
  {
    paramBoolean = false;
    if (Settings.System.getInt(ajn.k(a).getContentResolver(), "meizu_data_collection", 0) != 0) {
      paramBoolean = true;
    }
    UsageStatusLog.d("UsageStatsManagerServer", "usage stats switch changed : " + paramBoolean);
    if (ajn.b(a) != paramBoolean)
    {
      ajn.a(a, paramBoolean);
      if (!ajn.b(a)) {
        ajn.h(a).sendEmptyMessage(5);
      }
    }
  }
}

/* Location:
 * Qualified Name:     ajn.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
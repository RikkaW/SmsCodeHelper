import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings.System;
import com.android.mms.MmsApp;

public class fp
  extends ContentObserver
{
  public fp(MmsApp paramMmsApp, Handler paramHandler)
  {
    super(paramHandler);
  }
  
  public void onChange(boolean paramBoolean)
  {
    boolean bool = false;
    super.onChange(paramBoolean);
    paramBoolean = bool;
    if (Settings.System.getInt(a.getContentResolver(), "mz_easy_mode", 0) != 0) {
      paramBoolean = true;
    }
    MmsApp.n = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     fp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

final class abf
  extends Thread
{
  abf(Context paramContext, int paramInt, boolean paramBoolean) {}
  
  public void run()
  {
    int i = 0;
    for (;;)
    {
      try
      {
        Thread.sleep(500L);
        if (5 == aac.c(a, b))
        {
          bool = true;
          Log.e("SmsCenterUtils", "getSmsCenterFromSim slotId = " + b + ", isSimReady = " + bool);
          String str = abe.a(a, b);
          if ((bool) && (TextUtils.isEmpty(str)) && (i < 5))
          {
            Thread.sleep(1000L);
            str = abe.a(a, b);
            i += 1;
            continue;
          }
          if (bool)
          {
            Log.e("SmsCenterUtils", "getSmsCenterFromSim slotId = " + b + ", smscAddr = " + str);
            abe.b(str, b);
            if (!c) {
              break;
            }
            PreferenceManager.getDefaultSharedPreferences(a).edit().putString("pref_key_mms_center_number_slot" + (b + 1), str).commit();
            return;
          }
          Log.e("SmsCenterUtils", "getSmsCenterFromSim slotId = " + b + " is not ready");
          return;
        }
      }
      catch (Exception localException)
      {
        Log.e("SmsCenterUtils", "getSmsCenterFromSim simcard[ " + b + " ] e = " + localException);
        return;
      }
      boolean bool = false;
    }
  }
}

/* Location:
 * Qualified Name:     abf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
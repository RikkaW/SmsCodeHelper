import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

public final class abh$a
{
  public static final String[] a = { "_id", "address", "subject", "uuid" };
  private static a h;
  private final Context b;
  private final Handler c;
  private final SharedPreferences d;
  private boolean e;
  private final SharedPreferences.OnSharedPreferenceChangeListener f = new abj(this);
  private final BroadcastReceiver g = new abk(this);
  
  private abh$a(Context paramContext)
  {
    b = paramContext;
    c = new Handler();
    d = PreferenceManager.getDefaultSharedPreferences(paramContext);
    d.registerOnSharedPreferenceChangeListener(f);
    paramContext.registerReceiver(g, new IntentFilter("android.intent.action.SERVICE_STATE"));
    e = a(d);
  }
  
  public static void a(Context paramContext)
  {
    if (h != null) {
      Log.w("SnsMessageManager.SnsDownloadManager", "Already initialized.");
    }
    h = new a(paramContext);
  }
  
  static boolean a()
  {
    return "true".equals(aau.a("PROPERTY_OPERATOR_ISROAMING", null));
  }
  
  static boolean a(SharedPreferences paramSharedPreferences)
  {
    return a(paramSharedPreferences, a());
  }
  
  static boolean a(SharedPreferences paramSharedPreferences, boolean paramBoolean)
  {
    boolean bool1 = false;
    boolean bool2 = paramSharedPreferences.getBoolean("pref_key_mms_retrieval_during_roaming", false);
    if (paramBoolean)
    {
      paramBoolean = bool1;
      if (!bool2) {}
    }
    else
    {
      paramBoolean = true;
    }
    return paramBoolean;
  }
}

/* Location:
 * Qualified Name:     abh.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
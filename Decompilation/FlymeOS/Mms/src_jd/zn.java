import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.ui.SimSelectionPreferenceActivity;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzNotificationInd;
import com.meizu.android.mms.pdu.MzPduPersister;

public class zn
{
  private static zn h;
  private final Context a;
  private final Handler b;
  private final SharedPreferences c;
  private boolean d;
  private boolean e;
  private final SharedPreferences.OnSharedPreferenceChangeListener f = new zo(this);
  private final BroadcastReceiver g = new zp(this);
  
  private zn(Context paramContext)
  {
    a = paramContext;
    b = new Handler();
    c = PreferenceManager.getDefaultSharedPreferences(paramContext);
    c.registerOnSharedPreferenceChangeListener(f);
    paramContext.registerReceiver(g, new IntentFilter("android.intent.action.SERVICE_STATE"));
    if (zv.a)
    {
      d = a(c, 0);
      e = a(c, 1);
    }
    for (;;)
    {
      Log.v("DownloadManager", "mAutoDownload ------> " + d);
      Log.v("DownloadManager", "mAutoDownload2 ------> " + e + ", multi sim device = " + zv.a);
      return;
      d = a(c);
    }
  }
  
  public static zn a()
  {
    if (h == null) {
      throw new IllegalStateException("Uninitialized.");
    }
    return h;
  }
  
  public static void a(Context paramContext)
  {
    Log.v("DownloadManager", "DownloadManager.init()");
    if (h != null) {
      Log.w("DownloadManager", "Already initialized.");
    }
    h = new zn(paramContext);
  }
  
  static boolean a(SharedPreferences paramSharedPreferences)
  {
    return a(paramSharedPreferences, b());
  }
  
  static boolean a(SharedPreferences paramSharedPreferences, boolean paramBoolean)
  {
    if (MmsApp.a) {}
    for (boolean bool = paramSharedPreferences.getBoolean("pref_key_mms_auto_retrieval", true);; bool = true)
    {
      Log.v("DownloadManager", "auto download without roaming -> " + bool);
      if (bool)
      {
        bool = paramSharedPreferences.getBoolean("pref_key_mms_retrieval_during_roaming", false);
        Log.v("DownloadManager", "auto download during roaming -> " + bool + " roaming -> " + paramBoolean);
        if ((!paramBoolean) || (bool)) {
          return true;
        }
      }
      return false;
    }
  }
  
  static boolean a(SharedPreferences paramSharedPreferences, boolean paramBoolean, int paramInt)
  {
    if ((MmsApp.a) || (MmsApp.b)) {}
    for (boolean bool = SimSelectionPreferenceActivity.c(paramInt);; bool = true)
    {
      Log.v("DownloadManager", "auto download without roaming -> slotId : " + paramInt + " autoDownload : " + bool);
      if (bool)
      {
        bool = SimSelectionPreferenceActivity.b(paramInt);
        Log.v("DownloadManager", "auto download during alwaysAuto -> " + bool + ", roaming -> " + paramBoolean + ", slotId -> " + paramInt);
        if ((!paramBoolean) || (bool)) {
          return true;
        }
      }
      return false;
    }
  }
  
  static boolean b()
  {
    String str = aau.a("PROPERTY_OPERATOR_ISROAMING", null);
    Log.v("DownloadManager", "roaming ------> " + str);
    return "true".equals(str);
  }
  
  private boolean b(long paramLong)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)a.getSystemService("phone");
    if (localTelephonyManager == null) {
      return false;
    }
    if (Build.VERSION.SDK_INT > 21) {
      return ((Boolean)aau.a(TelephonyManager.class, localTelephonyManager, "isNetworkRoaming", Integer.TYPE, Integer.valueOf((int)paramLong))).booleanValue();
    }
    return ((Boolean)aau.a(TelephonyManager.class, localTelephonyManager, "isNetworkRoaming", Long.TYPE, Long.valueOf(paramLong))).booleanValue();
  }
  
  public void a(Uri paramUri, int paramInt)
  {
    Object localObject;
    try
    {
      Log.e("DownloadManager", "markState: uri = " + paramUri + ", state = " + paramInt);
      localObject = MzPduPersister.getPduPersister(a).load(paramUri);
      if (!(localObject instanceof MzNotificationInd))
      {
        Log.e("DownloadManager", "markState: pdu type is not NotificationInd.");
        return;
      }
      if ((((MzNotificationInd)localObject).getExpiry() < System.currentTimeMillis() / 1000L) && ((paramInt == 129) || (paramInt == 136)))
      {
        b.post(new zr(this));
        a.getContentResolver().delete(paramUri, null, null);
        return;
      }
    }
    catch (MmsException paramUri)
    {
      Log.e("DownloadManager", paramUri.getMessage(), paramUri);
      return;
    }
    int i;
    if (paramInt == 135) {
      i = paramInt;
    }
    for (;;)
    {
      localObject = new ContentValues(1);
      ((ContentValues)localObject).put("st", Integer.valueOf(i));
      a.getContentResolver().update(paramUri, (ContentValues)localObject, null, null);
      return;
      i = paramInt;
      if (!d) {
        i = paramInt | 0x4;
      }
    }
  }
  
  public void a(Uri paramUri, int paramInt1, int paramInt2)
  {
    if (paramUri == null) {
      return;
    }
    try
    {
      if ((((MzNotificationInd)MzPduPersister.getPduPersister(a).load(paramUri)).getExpiry() < System.currentTimeMillis() / 1000L) && (paramInt1 == 129))
      {
        b.post(new zq(this));
        a.getContentResolver().delete(paramUri, null, null);
        return;
      }
    }
    catch (MmsException paramUri)
    {
      Log.e("DownloadManager", paramUri.getMessage(), paramUri);
      return;
    }
    int i;
    if (paramInt1 == 135) {
      i = paramInt1;
    }
    for (;;)
    {
      ContentValues localContentValues = new ContentValues(1);
      localContentValues.put("st", Integer.valueOf(i));
      a.getContentResolver().update(paramUri, localContentValues, null, null);
      return;
      i = paramInt1;
      if (!a(paramInt2)) {
        i = paramInt1 | 0x4;
      }
    }
  }
  
  public boolean a(int paramInt)
  {
    boolean bool = true;
    if (zv.a)
    {
      if (paramInt == 0) {
        bool = d;
      }
      while (paramInt != 1) {
        return bool;
      }
      return e;
    }
    return d;
  }
  
  public boolean a(long paramLong)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(a);
    int i = zv.b(paramLong);
    return a(localSharedPreferences, b(paramLong), i);
  }
  
  boolean a(SharedPreferences paramSharedPreferences, int paramInt)
  {
    return a(paramSharedPreferences, b(aac.b(paramInt)), paramInt);
  }
  
  public void b(int paramInt)
  {
    b.post(new zs(this, paramInt));
  }
  
  public void b(Uri paramUri, int paramInt1, int paramInt2)
  {
    if (paramUri == null) {
      return;
    }
    int i;
    if (paramInt1 == 135) {
      i = paramInt1;
    }
    for (;;)
    {
      ContentValues localContentValues = new ContentValues(1);
      localContentValues.put("st", Integer.valueOf(i));
      a.getContentResolver().update(paramUri, localContentValues, null, null);
      return;
      i = paramInt1;
      if (!a(paramInt2)) {
        i = paramInt1 | 0x4;
      }
    }
  }
}

/* Location:
 * Qualified Name:     zn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
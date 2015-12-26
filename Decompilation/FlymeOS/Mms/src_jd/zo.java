import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.util.Log;

class zo
  implements SharedPreferences.OnSharedPreferenceChangeListener
{
  zo(zn paramzn) {}
  
  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String arg2)
  {
    if (("pref_key_mms_auto_retrieval".equals(???)) || ("pref_key_mms_retrieval_during_roaming".equals(???)) || ("pref_key_sim1_auto_download_roaming_switch".equals(???)) || ("pref_key_sim2_auto_download_roaming_switch".equals(???)) || ("pref_key_sim1_auto_download_switch".equals(???)) || ("pref_key_sim2_auto_download_switch".equals(???)))
    {
      Log.v("DownloadManager", "Preferences updated.");
      synchronized (zn.c())
      {
        if (zv.a)
        {
          zn.a(a, a.a(paramSharedPreferences, 0));
          zn.b(a, a.a(paramSharedPreferences, 1));
          Log.v("DownloadManager", "onSharedPreferenceChanged mAutoDownload ------> " + zn.a(a));
          Log.v("DownloadManager", "onSharedPreferenceChanged mAutoDownload2 ------> " + zn.b(a) + ", multi sim device = " + zv.a);
          return;
        }
        zn.a(a, zn.a(paramSharedPreferences));
      }
    }
  }
}

/* Location:
 * Qualified Name:     zo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
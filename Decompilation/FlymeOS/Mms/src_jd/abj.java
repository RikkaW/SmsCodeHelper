import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

class abj
  implements SharedPreferences.OnSharedPreferenceChangeListener
{
  abj(abh.a parama) {}
  
  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String arg2)
  {
    if (("pref_key_mms_auto_retrieval".equals(???)) || ("pref_key_mms_retrieval_during_roaming".equals(???))) {
      synchronized (abh.a.b())
      {
        abh.a.a(a, abh.a.a(paramSharedPreferences));
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     abj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
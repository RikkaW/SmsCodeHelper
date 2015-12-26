/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$OnSharedPreferenceChangeListener
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.SharedPreferences;

class abj
implements SharedPreferences.OnSharedPreferenceChangeListener {
    final /* synthetic */ abh.a a;

    abj(abh.a a2) {
        this.a = a2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String object) {
        if (!"pref_key_mms_auto_retrieval".equals(object) && !"pref_key_mms_retrieval_during_roaming".equals(object)) {
            return;
        }
        object = abh.a.b();
        synchronized (object) {
            abh.a.a(this.a, abh.a.a(sharedPreferences));
            return;
        }
    }
}


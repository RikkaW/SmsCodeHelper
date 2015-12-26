/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.meizu.statsapp.UsageStatusLog;

public class ajs
extends BroadcastReceiver {
    /*
     * Enabled aggressive block sorting
     */
    public void onReceive(Context context, Intent intent) {
        UsageStatusLog.d("SJC", "action=" + intent.getAction());
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals((Object)intent.getAction())) {
            ajt.a(false);
            return;
        } else {
            if (!"com.meizu.usagestats.check_upload".equals((Object)intent.getAction())) return;
            {
                ajt.a(true);
                return;
            }
        }
    }
}


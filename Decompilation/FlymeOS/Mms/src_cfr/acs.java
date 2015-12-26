/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.media.AudioManager
 *  android.media.AudioManager$OnAudioFocusChangeListener
 *  java.lang.Object
 */
import android.media.AudioManager;

class acs
implements AudioManager.OnAudioFocusChangeListener {
    final /* synthetic */ aco a;

    acs(aco aco2) {
        this.a = aco2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onAudioFocusChange(int n2) {
        if (n2 == -2) {
            if (!this.a.b()) return;
            {
                aco.a(this.a, "pause", aco.a(this.a));
                return;
            }
        } else if (n2 == 1) {
            if (aco.a(this.a) == null || this.a.b()) return;
            {
                aco.a(this.a).start();
                return;
            }
        } else {
            if (n2 == -1) {
                if (this.a.b()) {
                    aco.a(this.a).stop();
                }
                this.a.c.abandonAudioFocus(this.a.d);
                return;
            }
            if (n2 == 1) {
                if (!this.a.b()) return;
                {
                    aco.a(this.a).stop();
                    return;
                }
            } else {
                if (n2 != 0 || !this.a.b()) return;
                {
                    aco.a(this.a).stop();
                    return;
                }
            }
        }
    }
}


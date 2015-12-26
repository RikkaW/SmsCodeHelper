/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.media.AudioManager
 *  android.media.AudioManager$OnAudioFocusChangeListener
 *  android.net.Uri
 *  java.lang.Object
 */
import android.media.AudioManager;
import android.net.Uri;

class acl
implements AudioManager.OnAudioFocusChangeListener {
    final /* synthetic */ ach a;

    acl(ach ach2) {
        this.a = ach2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onAudioFocusChange(int n2) {
        if (n2 == -2) {
            if (!ach.e(this.a).isPlaying()) return;
            {
                ach.e(this.a).pause();
                if (!ach.f().containsKey((Object)ach.b.b(ach.f(this.a)))) return;
                {
                    ((ach.a)ach.f().get((Object)ach.b.b(ach.f(this.a)))).b(ach.b.b(ach.f(this.a)));
                    return;
                }
            }
        } else if (n2 == 1) {
            if (ach.e(this.a) == null || ach.e(this.a).isPlaying()) return;
            {
                ach.e(this.a).start();
                return;
            }
        } else {
            if (n2 == -1) {
                if (ach.e(this.a).isPlaying()) {
                    ach.e(this.a).stop();
                }
                this.a.a.abandonAudioFocus(this.a.b);
                return;
            }
            if (n2 == 1) {
                if (!ach.e(this.a).isPlaying()) return;
                {
                    ach.e(this.a).stop();
                    return;
                }
            } else {
                if (n2 != 0 || !ach.e(this.a).isPlaying()) return;
                {
                    ach.e(this.a).stop();
                    return;
                }
            }
        }
    }
}


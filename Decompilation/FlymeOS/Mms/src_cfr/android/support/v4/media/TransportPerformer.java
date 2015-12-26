/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.SystemClock
 *  android.view.KeyEvent
 *  java.lang.Object
 */
package android.support.v4.media;

import android.os.SystemClock;
import android.view.KeyEvent;

public abstract class TransportPerformer {
    static final int AUDIOFOCUS_GAIN = 1;
    static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;
    static final int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;
    static final int AUDIOFOCUS_LOSS = -1;
    static final int AUDIOFOCUS_LOSS_TRANSIENT = -2;
    static final int AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK = -3;

    /*
     * Enabled aggressive block sorting
     */
    public void onAudioFocusChange(int n2) {
        int n3 = 0;
        switch (n2) {
            default: {
                n2 = n3;
                break;
            }
            case -1: {
                n2 = 127;
            }
        }
        if (n2 != 0) {
            long l2 = SystemClock.uptimeMillis();
            this.onMediaButtonDown(n2, new KeyEvent(l2, l2, 0, n2, 0));
            this.onMediaButtonUp(n2, new KeyEvent(l2, l2, 1, n2, 0));
        }
    }

    public int onGetBufferPercentage() {
        return 100;
    }

    public abstract long onGetCurrentPosition();

    public abstract long onGetDuration();

    public int onGetTransportControlFlags() {
        return 60;
    }

    public abstract boolean onIsPlaying();

    public boolean onMediaButtonDown(int n2, KeyEvent keyEvent) {
        switch (n2) {
            default: {
                return true;
            }
            case 126: {
                this.onStart();
                return true;
            }
            case 127: {
                this.onPause();
                return true;
            }
            case 86: {
                this.onStop();
                return true;
            }
            case 79: 
            case 85: 
        }
        if (this.onIsPlaying()) {
            this.onPause();
            return true;
        }
        this.onStart();
        return true;
    }

    public boolean onMediaButtonUp(int n2, KeyEvent keyEvent) {
        return true;
    }

    public abstract void onPause();

    public abstract void onSeekTo(long var1);

    public abstract void onStart();

    public abstract void onStop();
}


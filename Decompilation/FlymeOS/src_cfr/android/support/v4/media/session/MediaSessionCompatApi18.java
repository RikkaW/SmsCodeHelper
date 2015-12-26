/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.media.AudioManager
 *  android.media.RemoteControlClient
 *  android.media.RemoteControlClient$OnPlaybackPositionUpdateListener
 *  android.os.SystemClock
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.media.AudioManager;
import android.media.RemoteControlClient;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompatApi14;

public class MediaSessionCompatApi18 {
    private static final long ACTION_SEEK_TO = 256;

    public static Object createPlaybackPositionUpdateListener(MediaSessionCompatApi14.Callback callback) {
        return new OnPlaybackPositionUpdateListener<MediaSessionCompatApi14.Callback>(callback);
    }

    static int getRccTransportControlFlagsFromActions(long l2) {
        int n2;
        int n3 = n2 = MediaSessionCompatApi14.getRccTransportControlFlagsFromActions(l2);
        if ((256 & l2) != 0) {
            n3 = n2 | 256;
        }
        return n3;
    }

    public static void registerMediaButtonEventReceiver(Context context, PendingIntent pendingIntent) {
        ((AudioManager)context.getSystemService("audio")).registerMediaButtonEventReceiver(pendingIntent);
    }

    public static void setOnPlaybackPositionUpdateListener(Object object, Object object2) {
        ((RemoteControlClient)object).setPlaybackPositionUpdateListener((RemoteControlClient.OnPlaybackPositionUpdateListener)object2);
    }

    public static void setState(Object object, int n2, long l2, float f2, long l3) {
        long l4 = 0;
        long l5 = SystemClock.elapsedRealtime();
        long l6 = l2;
        if (n2 == 3) {
            l6 = l2;
            if (l2 > 0) {
                l6 = l4;
                if (l3 > 0) {
                    l6 = l3 = l5 - l3;
                    if (f2 > 0.0f) {
                        l6 = l3;
                        if (f2 != 1.0f) {
                            l6 = (long)((float)l3 * f2);
                        }
                    }
                }
                l6 = l2 + l6;
            }
        }
        n2 = MediaSessionCompatApi14.getRccStateFromState(n2);
        ((RemoteControlClient)object).setPlaybackState(n2, l6, f2);
    }

    public static void setTransportControlFlags(Object object, long l2) {
        ((RemoteControlClient)object).setTransportControlFlags(MediaSessionCompatApi18.getRccTransportControlFlagsFromActions(l2));
    }

    public static void unregisterMediaButtonEventReceiver(Context context, PendingIntent pendingIntent) {
        ((AudioManager)context.getSystemService("audio")).unregisterMediaButtonEventReceiver(pendingIntent);
    }

    static class OnPlaybackPositionUpdateListener<T extends MediaSessionCompatApi14.Callback>
    implements RemoteControlClient.OnPlaybackPositionUpdateListener {
        protected final T mCallback;

        public OnPlaybackPositionUpdateListener(T t2) {
            this.mCallback = t2;
        }

        public void onPlaybackPositionUpdate(long l2) {
            this.mCallback.onSeekTo(l2);
        }
    }

}


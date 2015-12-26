/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.content.Intent
 *  android.graphics.Bitmap
 *  android.media.AudioManager
 *  android.media.RemoteControlClient
 *  android.media.RemoteControlClient$MetadataEditor
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.os.ResultReceiver
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.RemoteControlClient;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;

public class MediaSessionCompatApi14 {
    private static final long ACTION_FAST_FORWARD = 64;
    private static final long ACTION_PAUSE = 2;
    private static final long ACTION_PLAY = 4;
    private static final long ACTION_PLAY_PAUSE = 512;
    private static final long ACTION_REWIND = 8;
    private static final long ACTION_SKIP_TO_NEXT = 32;
    private static final long ACTION_SKIP_TO_PREVIOUS = 16;
    private static final long ACTION_STOP = 1;
    private static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";
    private static final String METADATA_KEY_ALBUM_ART = "android.media.metadata.ALBUM_ART";
    private static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";
    private static final String METADATA_KEY_ART = "android.media.metadata.ART";
    private static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";
    private static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";
    private static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";
    private static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";
    private static final String METADATA_KEY_DATE = "android.media.metadata.DATE";
    private static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";
    private static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";
    private static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";
    private static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";
    private static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";
    private static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";
    static final int RCC_PLAYSTATE_NONE = 0;
    static final int STATE_BUFFERING = 6;
    static final int STATE_CONNECTING = 8;
    static final int STATE_ERROR = 7;
    static final int STATE_FAST_FORWARDING = 4;
    static final int STATE_NONE = 0;
    static final int STATE_PAUSED = 2;
    static final int STATE_PLAYING = 3;
    static final int STATE_REWINDING = 5;
    static final int STATE_SKIPPING_TO_NEXT = 10;
    static final int STATE_SKIPPING_TO_PREVIOUS = 9;
    static final int STATE_SKIPPING_TO_QUEUE_ITEM = 11;
    static final int STATE_STOPPED = 1;

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    static void buildOldMetadata(Bundle bundle, RemoteControlClient.MetadataEditor metadataEditor) {
        if (bundle == null) {
            return;
        }
        if (bundle.containsKey("android.media.metadata.ART")) {
            metadataEditor.putBitmap(100, (Bitmap)bundle.getParcelable("android.media.metadata.ART"));
        } else if (bundle.containsKey("android.media.metadata.ALBUM_ART")) {
            metadataEditor.putBitmap(100, (Bitmap)bundle.getParcelable("android.media.metadata.ALBUM_ART"));
        }
        if (bundle.containsKey("android.media.metadata.ALBUM")) {
            metadataEditor.putString(1, bundle.getString("android.media.metadata.ALBUM"));
        }
        if (bundle.containsKey("android.media.metadata.ALBUM_ARTIST")) {
            metadataEditor.putString(13, bundle.getString("android.media.metadata.ALBUM_ARTIST"));
        }
        if (bundle.containsKey("android.media.metadata.ARTIST")) {
            metadataEditor.putString(2, bundle.getString("android.media.metadata.ARTIST"));
        }
        if (bundle.containsKey("android.media.metadata.AUTHOR")) {
            metadataEditor.putString(3, bundle.getString("android.media.metadata.AUTHOR"));
        }
        if (bundle.containsKey("android.media.metadata.COMPILATION")) {
            metadataEditor.putString(15, bundle.getString("android.media.metadata.COMPILATION"));
        }
        if (bundle.containsKey("android.media.metadata.COMPOSER")) {
            metadataEditor.putString(4, bundle.getString("android.media.metadata.COMPOSER"));
        }
        if (bundle.containsKey("android.media.metadata.DATE")) {
            metadataEditor.putString(5, bundle.getString("android.media.metadata.DATE"));
        }
        if (bundle.containsKey("android.media.metadata.DISC_NUMBER")) {
            metadataEditor.putLong(14, bundle.getLong("android.media.metadata.DISC_NUMBER"));
        }
        if (bundle.containsKey("android.media.metadata.DURATION")) {
            metadataEditor.putLong(9, bundle.getLong("android.media.metadata.DURATION"));
        }
        if (bundle.containsKey("android.media.metadata.GENRE")) {
            metadataEditor.putString(6, bundle.getString("android.media.metadata.GENRE"));
        }
        if (bundle.containsKey("android.media.metadata.TITLE")) {
            metadataEditor.putString(7, bundle.getString("android.media.metadata.TITLE"));
        }
        if (bundle.containsKey("android.media.metadata.TRACK_NUMBER")) {
            metadataEditor.putLong(0, bundle.getLong("android.media.metadata.TRACK_NUMBER"));
        }
        if (!bundle.containsKey("android.media.metadata.WRITER")) return;
        metadataEditor.putString(11, bundle.getString("android.media.metadata.WRITER"));
    }

    public static Object createRemoteControlClient(PendingIntent pendingIntent) {
        return new RemoteControlClient(pendingIntent);
    }

    static int getRccStateFromState(int n2) {
        switch (n2) {
            default: {
                return -1;
            }
            case 6: 
            case 8: {
                return 8;
            }
            case 7: {
                return 9;
            }
            case 4: {
                return 4;
            }
            case 0: {
                return 0;
            }
            case 2: {
                return 2;
            }
            case 3: {
                return 3;
            }
            case 5: {
                return 5;
            }
            case 9: {
                return 7;
            }
            case 10: 
            case 11: {
                return 6;
            }
            case 1: 
        }
        return 1;
    }

    static int getRccTransportControlFlagsFromActions(long l2) {
        int n2 = 0;
        if ((1 & l2) != 0) {
            n2 = 32;
        }
        int n3 = n2;
        if ((2 & l2) != 0) {
            n3 = n2 | 16;
        }
        n2 = n3;
        if ((4 & l2) != 0) {
            n2 = n3 | 4;
        }
        n3 = n2;
        if ((8 & l2) != 0) {
            n3 = n2 | 2;
        }
        n2 = n3;
        if ((16 & l2) != 0) {
            n2 = n3 | 1;
        }
        n3 = n2;
        if ((32 & l2) != 0) {
            n3 = n2 | 128;
        }
        n2 = n3;
        if ((64 & l2) != 0) {
            n2 = n3 | 64;
        }
        n3 = n2;
        if ((512 & l2) != 0) {
            n3 = n2 | 8;
        }
        return n3;
    }

    public static void registerRemoteControlClient(Context context, Object object) {
        ((AudioManager)context.getSystemService("audio")).registerRemoteControlClient((RemoteControlClient)object);
    }

    public static void setMetadata(Object object, Bundle bundle) {
        object = ((RemoteControlClient)object).editMetadata(true);
        MediaSessionCompatApi14.buildOldMetadata(bundle, (RemoteControlClient.MetadataEditor)object);
        object.apply();
    }

    public static void setState(Object object, int n2) {
        ((RemoteControlClient)object).setPlaybackState(MediaSessionCompatApi14.getRccStateFromState(n2));
    }

    public static void setTransportControlFlags(Object object, long l2) {
        ((RemoteControlClient)object).setTransportControlFlags(MediaSessionCompatApi14.getRccTransportControlFlagsFromActions(l2));
    }

    public static void unregisterRemoteControlClient(Context context, Object object) {
        ((AudioManager)context.getSystemService("audio")).unregisterRemoteControlClient((RemoteControlClient)object);
    }

    public static interface Callback {
        public void onCommand(String var1, Bundle var2, ResultReceiver var3);

        public void onFastForward();

        public boolean onMediaButtonEvent(Intent var1);

        public void onPause();

        public void onPlay();

        public void onRewind();

        public void onSeekTo(long var1);

        public void onSetRating(Object var1);

        public void onSkipToNext();

        public void onSkipToPrevious();

        public void onStop();
    }

}


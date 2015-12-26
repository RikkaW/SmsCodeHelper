/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.media.MediaMetadataEditor
 *  android.media.Rating
 *  android.media.RemoteControlClient
 *  android.media.RemoteControlClient$MetadataEditor
 *  android.media.RemoteControlClient$OnMetadataUpdateListener
 *  android.os.Bundle
 *  android.os.Parcelable
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.media.session;

import android.media.MediaMetadataEditor;
import android.media.Rating;
import android.media.RemoteControlClient;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompatApi14;
import android.support.v4.media.session.MediaSessionCompatApi18;

public class MediaSessionCompatApi19 {
    private static final long ACTION_SET_RATING = 128;
    private static final String METADATA_KEY_RATING = "android.media.metadata.RATING";
    private static final String METADATA_KEY_USER_RATING = "android.media.metadata.USER_RATING";
    private static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    static void addNewMetadata(Bundle bundle, RemoteControlClient.MetadataEditor metadataEditor) {
        if (bundle == null) {
            return;
        }
        if (bundle.containsKey("android.media.metadata.YEAR")) {
            metadataEditor.putLong(8, bundle.getLong("android.media.metadata.YEAR"));
        }
        if (bundle.containsKey("android.media.metadata.RATING")) {
            metadataEditor.putObject(101, (Object)bundle.getParcelable("android.media.metadata.RATING"));
        }
        if (!bundle.containsKey("android.media.metadata.USER_RATING")) return;
        metadataEditor.putObject(268435457, (Object)bundle.getParcelable("android.media.metadata.USER_RATING"));
    }

    public static Object createMetadataUpdateListener(MediaSessionCompatApi14.Callback callback) {
        return new OnMetadataUpdateListener<MediaSessionCompatApi14.Callback>(callback);
    }

    static int getRccTransportControlFlagsFromActions(long l2) {
        int n2;
        int n3 = n2 = MediaSessionCompatApi18.getRccTransportControlFlagsFromActions(l2);
        if ((128 & l2) != 0) {
            n3 = n2 | 512;
        }
        return n3;
    }

    public static void setMetadata(Object object, Bundle bundle, long l2) {
        object = ((RemoteControlClient)object).editMetadata(true);
        MediaSessionCompatApi14.buildOldMetadata(bundle, (RemoteControlClient.MetadataEditor)object);
        MediaSessionCompatApi19.addNewMetadata(bundle, (RemoteControlClient.MetadataEditor)object);
        if ((128 & l2) != 0) {
            object.addEditableKey(268435457);
        }
        object.apply();
    }

    public static void setOnMetadataUpdateListener(Object object, Object object2) {
        ((RemoteControlClient)object).setMetadataUpdateListener((RemoteControlClient.OnMetadataUpdateListener)object2);
    }

    public static void setTransportControlFlags(Object object, long l2) {
        ((RemoteControlClient)object).setTransportControlFlags(MediaSessionCompatApi19.getRccTransportControlFlagsFromActions(l2));
    }

    static class OnMetadataUpdateListener<T extends MediaSessionCompatApi14.Callback>
    implements RemoteControlClient.OnMetadataUpdateListener {
        protected final T mCallback;

        public OnMetadataUpdateListener(T t2) {
            this.mCallback = t2;
        }

        public void onMetadataUpdate(int n2, Object object) {
            if (n2 == 268435457 && object instanceof Rating) {
                this.mCallback.onSetRating(object);
            }
        }
    }

}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.content.Intent
 *  android.media.AudioAttributes
 *  android.media.AudioAttributes$Builder
 *  android.media.MediaDescription
 *  android.media.MediaMetadata
 *  android.media.Rating
 *  android.media.VolumeProvider
 *  android.media.session.MediaSession
 *  android.media.session.MediaSession$Callback
 *  android.media.session.MediaSession$QueueItem
 *  android.media.session.MediaSession$Token
 *  android.media.session.PlaybackState
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Parcelable
 *  android.os.ResultReceiver
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaDescription;
import android.media.MediaMetadata;
import android.media.Rating;
import android.media.VolumeProvider;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.ResultReceiver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MediaSessionCompatApi21 {
    MediaSessionCompatApi21() {
    }

    public static Object createCallback(Callback callback) {
        return new CallbackProxy<Callback>(callback);
    }

    public static Object createSession(Context context, String string2) {
        return new MediaSession(context, string2);
    }

    public static Parcelable getSessionToken(Object object) {
        return ((MediaSession)object).getSessionToken();
    }

    public static boolean isActive(Object object) {
        return ((MediaSession)object).isActive();
    }

    public static void release(Object object) {
        ((MediaSession)object).release();
    }

    public static void sendSessionEvent(Object object, String string2, Bundle bundle) {
        ((MediaSession)object).sendSessionEvent(string2, bundle);
    }

    public static void setActive(Object object, boolean bl2) {
        ((MediaSession)object).setActive(bl2);
    }

    public static void setCallback(Object object, Object object2, Handler handler) {
        ((MediaSession)object).setCallback((MediaSession.Callback)object2, handler);
    }

    public static void setExtras(Object object, Bundle bundle) {
        ((MediaSession)object).setExtras(bundle);
    }

    public static void setFlags(Object object, int n2) {
        ((MediaSession)object).setFlags(n2);
    }

    public static void setMediaButtonReceiver(Object object, PendingIntent pendingIntent) {
        ((MediaSession)object).setMediaButtonReceiver(pendingIntent);
    }

    public static void setMetadata(Object object, Object object2) {
        ((MediaSession)object).setMetadata((MediaMetadata)object2);
    }

    public static void setPlaybackState(Object object, Object object2) {
        ((MediaSession)object).setPlaybackState((PlaybackState)object2);
    }

    public static void setPlaybackToLocal(Object object, int n2) {
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        builder.setLegacyStreamType(n2);
        ((MediaSession)object).setPlaybackToLocal(builder.build());
    }

    public static void setPlaybackToRemote(Object object, Object object2) {
        ((MediaSession)object).setPlaybackToRemote((VolumeProvider)object2);
    }

    public static void setQueue(Object object, List<Object> object2) {
        if (object2 == null) {
            ((MediaSession)object).setQueue(null);
            return;
        }
        ArrayList arrayList = new ArrayList();
        object2 = object2.iterator();
        while (object2.hasNext()) {
            arrayList.add((Object)((MediaSession.QueueItem)object2.next()));
        }
        ((MediaSession)object).setQueue((List)arrayList);
    }

    public static void setQueueTitle(Object object, CharSequence charSequence) {
        ((MediaSession)object).setQueueTitle(charSequence);
    }

    public static void setSessionActivity(Object object, PendingIntent pendingIntent) {
        ((MediaSession)object).setSessionActivity(pendingIntent);
    }

    public static Object verifySession(Object object) {
        if (object instanceof MediaSession) {
            return object;
        }
        throw new IllegalArgumentException("mediaSession is not a valid MediaSession object");
    }

    public static Object verifyToken(Object object) {
        if (object instanceof MediaSession.Token) {
            return object;
        }
        throw new IllegalArgumentException("token is not a valid MediaSession.Token object");
    }

    public static interface Callback {
        public void onCommand(String var1, Bundle var2, ResultReceiver var3);

        public void onCustomAction(String var1, Bundle var2);

        public void onFastForward();

        public boolean onMediaButtonEvent(Intent var1);

        public void onPause();

        public void onPlay();

        public void onPlayFromMediaId(String var1, Bundle var2);

        public void onPlayFromSearch(String var1, Bundle var2);

        public void onRewind();

        public void onSeekTo(long var1);

        public void onSetRating(Object var1);

        public void onSkipToNext();

        public void onSkipToPrevious();

        public void onSkipToQueueItem(long var1);

        public void onStop();
    }

    static class CallbackProxy<T extends Callback>
    extends MediaSession.Callback {
        protected final T mCallback;

        public CallbackProxy(T t2) {
            this.mCallback = t2;
        }

        public void onCommand(String string2, Bundle bundle, ResultReceiver resultReceiver) {
            this.mCallback.onCommand(string2, bundle, resultReceiver);
        }

        public void onCustomAction(String string2, Bundle bundle) {
            this.mCallback.onCustomAction(string2, bundle);
        }

        public void onFastForward() {
            this.mCallback.onFastForward();
        }

        public boolean onMediaButtonEvent(Intent intent) {
            if (this.mCallback.onMediaButtonEvent(intent) || super.onMediaButtonEvent(intent)) {
                return true;
            }
            return false;
        }

        public void onPause() {
            this.mCallback.onPause();
        }

        public void onPlay() {
            this.mCallback.onPlay();
        }

        public void onPlayFromMediaId(String string2, Bundle bundle) {
            this.mCallback.onPlayFromMediaId(string2, bundle);
        }

        public void onPlayFromSearch(String string2, Bundle bundle) {
            this.mCallback.onPlayFromSearch(string2, bundle);
        }

        public void onRewind() {
            this.mCallback.onRewind();
        }

        public void onSeekTo(long l2) {
            this.mCallback.onSeekTo(l2);
        }

        public void onSetRating(Rating rating) {
            this.mCallback.onSetRating((Object)rating);
        }

        public void onSkipToNext() {
            this.mCallback.onSkipToNext();
        }

        public void onSkipToPrevious() {
            this.mCallback.onSkipToPrevious();
        }

        public void onSkipToQueueItem(long l2) {
            this.mCallback.onSkipToQueueItem(l2);
        }

        public void onStop() {
            this.mCallback.onStop();
        }
    }

    static class QueueItem {
        QueueItem() {
        }

        public static Object createItem(Object object, long l2) {
            return new MediaSession.QueueItem((MediaDescription)object, l2);
        }

        public static Object getDescription(Object object) {
            return ((MediaSession.QueueItem)object).getDescription();
        }

        public static long getQueueId(Object object) {
            return ((MediaSession.QueueItem)object).getQueueId();
        }
    }

}


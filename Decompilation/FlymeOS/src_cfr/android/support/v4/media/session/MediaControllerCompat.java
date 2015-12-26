/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.os.IBinder$DeathRecipient
 *  android.os.Looper
 *  android.os.Message
 *  android.os.RemoteException
 *  android.os.ResultReceiver
 *  android.text.TextUtils
 *  android.util.Log
 *  android.view.KeyEvent
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaControllerCompatApi21;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.ParcelableVolumeInfo;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class MediaControllerCompat {
    private static final String TAG = "MediaControllerCompat";
    private final MediaControllerImpl mImpl;
    private final MediaSessionCompat.Token mToken;

    public MediaControllerCompat(Context context, MediaSessionCompat.Token token) {
        if (token == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        this.mToken = token;
        if (Build.VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaControllerImplApi21(context, token);
            return;
        }
        this.mImpl = new MediaControllerImplBase(this.mToken);
    }

    public MediaControllerCompat(Context context, MediaSessionCompat mediaSessionCompat) {
        if (mediaSessionCompat == null) {
            throw new IllegalArgumentException("session must not be null");
        }
        this.mToken = mediaSessionCompat.getSessionToken();
        if (Build.VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaControllerImplApi21(context, mediaSessionCompat);
            return;
        }
        this.mImpl = new MediaControllerImplBase(this.mToken);
    }

    public void adjustVolume(int n2, int n3) {
        this.mImpl.adjustVolume(n2, n3);
    }

    public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
        if (keyEvent == null) {
            throw new IllegalArgumentException("KeyEvent may not be null");
        }
        return this.mImpl.dispatchMediaButtonEvent(keyEvent);
    }

    public Bundle getExtras() {
        return this.mImpl.getExtras();
    }

    public long getFlags() {
        return this.mImpl.getFlags();
    }

    public Object getMediaController() {
        return this.mImpl.getMediaController();
    }

    public MediaMetadataCompat getMetadata() {
        return this.mImpl.getMetadata();
    }

    public String getPackageName() {
        return this.mImpl.getPackageName();
    }

    public PlaybackInfo getPlaybackInfo() {
        return this.mImpl.getPlaybackInfo();
    }

    public PlaybackStateCompat getPlaybackState() {
        return this.mImpl.getPlaybackState();
    }

    public List<MediaSessionCompat.QueueItem> getQueue() {
        return this.mImpl.getQueue();
    }

    public CharSequence getQueueTitle() {
        return this.mImpl.getQueueTitle();
    }

    public int getRatingType() {
        return this.mImpl.getRatingType();
    }

    public PendingIntent getSessionActivity() {
        return this.mImpl.getSessionActivity();
    }

    public MediaSessionCompat.Token getSessionToken() {
        return this.mToken;
    }

    public TransportControls getTransportControls() {
        return this.mImpl.getTransportControls();
    }

    public void registerCallback(Callback callback) {
        this.registerCallback(callback, null);
    }

    public void registerCallback(Callback callback, Handler handler) {
        if (callback == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        Handler handler2 = handler;
        if (handler == null) {
            handler2 = new Handler();
        }
        this.mImpl.registerCallback(callback, handler2);
    }

    public void sendCommand(String string2, Bundle bundle, ResultReceiver resultReceiver) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            throw new IllegalArgumentException("command cannot be null or empty");
        }
        this.mImpl.sendCommand(string2, bundle, resultReceiver);
    }

    public void setVolumeTo(int n2, int n3) {
        this.mImpl.setVolumeTo(n2, n3);
    }

    public void unregisterCallback(Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        this.mImpl.unregisterCallback(callback);
    }

    public static abstract class Callback
    implements IBinder.DeathRecipient {
        private final Object mCallbackObj;
        private MessageHandler mHandler;
        private boolean mRegistered = false;

        public Callback() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.mCallbackObj = MediaControllerCompatApi21.createCallback(new StubApi21());
                return;
            }
            this.mCallbackObj = new StubCompat();
        }

        private void setHandler(Handler handler) {
            this.mHandler = new MessageHandler(handler.getLooper());
        }

        public void binderDied() {
            this.onSessionDestroyed();
        }

        public void onAudioInfoChanged(PlaybackInfo playbackInfo) {
        }

        public void onExtrasChanged(Bundle bundle) {
        }

        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
        }

        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
        }

        public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) {
        }

        public void onQueueTitleChanged(CharSequence charSequence) {
        }

        public void onSessionDestroyed() {
        }

        public void onSessionEvent(String string2, Bundle bundle) {
        }

        class MessageHandler
        extends Handler {
            private static final int MSG_DESTROYED = 8;
            private static final int MSG_EVENT = 1;
            private static final int MSG_UPDATE_EXTRAS = 7;
            private static final int MSG_UPDATE_METADATA = 3;
            private static final int MSG_UPDATE_PLAYBACK_STATE = 2;
            private static final int MSG_UPDATE_QUEUE = 5;
            private static final int MSG_UPDATE_QUEUE_TITLE = 6;
            private static final int MSG_UPDATE_VOLUME = 4;

            public MessageHandler(Looper looper) {
                super(looper);
            }

            public void handleMessage(Message message) {
                if (!Callback.this.mRegistered) {
                    return;
                }
                switch (message.what) {
                    default: {
                        return;
                    }
                    case 1: {
                        Callback.this.onSessionEvent((String)message.obj, message.getData());
                        return;
                    }
                    case 2: {
                        Callback.this.onPlaybackStateChanged((PlaybackStateCompat)message.obj);
                        return;
                    }
                    case 3: {
                        Callback.this.onMetadataChanged((MediaMetadataCompat)message.obj);
                        return;
                    }
                    case 5: {
                        Callback.this.onQueueChanged((List)message.obj);
                        return;
                    }
                    case 6: {
                        Callback.this.onQueueTitleChanged((CharSequence)message.obj);
                        return;
                    }
                    case 7: {
                        Callback.this.onExtrasChanged((Bundle)message.obj);
                        return;
                    }
                    case 4: {
                        Callback.this.onAudioInfoChanged((PlaybackInfo)message.obj);
                        return;
                    }
                    case 8: 
                }
                Callback.this.onSessionDestroyed();
            }

            public void post(int n2, Object object, Bundle bundle) {
                this.obtainMessage(n2, object).sendToTarget();
            }
        }

        class StubApi21
        implements MediaControllerCompatApi21.Callback {
            private StubApi21() {
            }

            @Override
            public void onMetadataChanged(Object object) {
                Callback.this.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(object));
            }

            @Override
            public void onPlaybackStateChanged(Object object) {
                Callback.this.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(object));
            }

            @Override
            public void onSessionDestroyed() {
                Callback.this.onSessionDestroyed();
            }

            @Override
            public void onSessionEvent(String string2, Bundle bundle) {
                Callback.this.onSessionEvent(string2, bundle);
            }
        }

        class StubCompat
        extends IMediaControllerCallback.Stub {
            private StubCompat() {
            }

            @Override
            public void onEvent(String string2, Bundle bundle) {
                Callback.this.mHandler.post(1, string2, bundle);
            }

            @Override
            public void onExtrasChanged(Bundle bundle) {
                Callback.this.mHandler.post(7, (Object)bundle, null);
            }

            @Override
            public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
                Callback.this.mHandler.post(3, mediaMetadataCompat, null);
            }

            @Override
            public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
                Callback.this.mHandler.post(2, playbackStateCompat, null);
            }

            @Override
            public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) {
                Callback.this.mHandler.post(5, list, null);
            }

            @Override
            public void onQueueTitleChanged(CharSequence charSequence) {
                Callback.this.mHandler.post(6, charSequence, null);
            }

            @Override
            public void onSessionDestroyed() {
                Callback.this.mHandler.post(8, null, null);
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void onVolumeInfoChanged(ParcelableVolumeInfo object) {
                object = object != null ? new PlaybackInfo(object.volumeType, object.audioStream, object.controlType, object.maxVolume, object.currentVolume) : null;
                Callback.this.mHandler.post(4, object, null);
            }
        }

    }

    static interface MediaControllerImpl {
        public void adjustVolume(int var1, int var2);

        public boolean dispatchMediaButtonEvent(KeyEvent var1);

        public Bundle getExtras();

        public long getFlags();

        public Object getMediaController();

        public MediaMetadataCompat getMetadata();

        public String getPackageName();

        public PlaybackInfo getPlaybackInfo();

        public PlaybackStateCompat getPlaybackState();

        public List<MediaSessionCompat.QueueItem> getQueue();

        public CharSequence getQueueTitle();

        public int getRatingType();

        public PendingIntent getSessionActivity();

        public TransportControls getTransportControls();

        public void registerCallback(Callback var1, Handler var2);

        public void sendCommand(String var1, Bundle var2, ResultReceiver var3);

        public void setVolumeTo(int var1, int var2);

        public void unregisterCallback(Callback var1);
    }

    static class MediaControllerImplApi21
    implements MediaControllerImpl {
        private final Object mControllerObj;

        public MediaControllerImplApi21(Context context, MediaSessionCompat.Token token) {
            this.mControllerObj = MediaControllerCompatApi21.fromToken(context, token.getToken());
            if (this.mControllerObj == null) {
                throw new RemoteException();
            }
        }

        public MediaControllerImplApi21(Context context, MediaSessionCompat mediaSessionCompat) {
            this.mControllerObj = MediaControllerCompatApi21.fromToken(context, mediaSessionCompat.getSessionToken().getToken());
        }

        @Override
        public void adjustVolume(int n2, int n3) {
            MediaControllerCompatApi21.adjustVolume(this.mControllerObj, n2, n3);
        }

        @Override
        public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
            return MediaControllerCompatApi21.dispatchMediaButtonEvent(this.mControllerObj, keyEvent);
        }

        @Override
        public Bundle getExtras() {
            return MediaControllerCompatApi21.getExtras(this.mControllerObj);
        }

        @Override
        public long getFlags() {
            return MediaControllerCompatApi21.getFlags(this.mControllerObj);
        }

        @Override
        public Object getMediaController() {
            return this.mControllerObj;
        }

        @Override
        public MediaMetadataCompat getMetadata() {
            Object object = MediaControllerCompatApi21.getMetadata(this.mControllerObj);
            if (object != null) {
                return MediaMetadataCompat.fromMediaMetadata(object);
            }
            return null;
        }

        @Override
        public String getPackageName() {
            return MediaControllerCompatApi21.getPackageName(this.mControllerObj);
        }

        @Override
        public PlaybackInfo getPlaybackInfo() {
            Object object = MediaControllerCompatApi21.getPlaybackInfo(this.mControllerObj);
            if (object != null) {
                return new PlaybackInfo(MediaControllerCompatApi21.PlaybackInfo.getPlaybackType(object), MediaControllerCompatApi21.PlaybackInfo.getLegacyAudioStream(object), MediaControllerCompatApi21.PlaybackInfo.getVolumeControl(object), MediaControllerCompatApi21.PlaybackInfo.getMaxVolume(object), MediaControllerCompatApi21.PlaybackInfo.getCurrentVolume(object));
            }
            return null;
        }

        @Override
        public PlaybackStateCompat getPlaybackState() {
            Object object = MediaControllerCompatApi21.getPlaybackState(this.mControllerObj);
            if (object != null) {
                return PlaybackStateCompat.fromPlaybackState(object);
            }
            return null;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public List<MediaSessionCompat.QueueItem> getQueue() {
            ArrayList arrayList = MediaControllerCompatApi21.getQueue(this.mControllerObj);
            if (arrayList == null) {
                return null;
            }
            ArrayList arrayList2 = new ArrayList();
            Iterator<Object> iterator = arrayList.iterator();
            do {
                arrayList = arrayList2;
                if (!iterator.hasNext()) return arrayList;
                arrayList2.add(MediaSessionCompat.QueueItem.obtain(iterator.next()));
            } while (true);
        }

        @Override
        public CharSequence getQueueTitle() {
            return MediaControllerCompatApi21.getQueueTitle(this.mControllerObj);
        }

        @Override
        public int getRatingType() {
            return MediaControllerCompatApi21.getRatingType(this.mControllerObj);
        }

        @Override
        public PendingIntent getSessionActivity() {
            return MediaControllerCompatApi21.getSessionActivity(this.mControllerObj);
        }

        @Override
        public TransportControls getTransportControls() {
            Object object = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
            if (object != null) {
                return new TransportControlsApi21(object);
            }
            return null;
        }

        @Override
        public void registerCallback(Callback callback, Handler handler) {
            MediaControllerCompatApi21.registerCallback(this.mControllerObj, callback.mCallbackObj, handler);
        }

        @Override
        public void sendCommand(String string2, Bundle bundle, ResultReceiver resultReceiver) {
            MediaControllerCompatApi21.sendCommand(this.mControllerObj, string2, bundle, resultReceiver);
        }

        @Override
        public void setVolumeTo(int n2, int n3) {
            MediaControllerCompatApi21.setVolumeTo(this.mControllerObj, n2, n3);
        }

        @Override
        public void unregisterCallback(Callback callback) {
            MediaControllerCompatApi21.unregisterCallback(this.mControllerObj, callback.mCallbackObj);
        }
    }

    static class MediaControllerImplBase
    implements MediaControllerImpl {
        private IMediaSession mBinder;
        private MediaSessionCompat.Token mToken;
        private TransportControls mTransportControls;

        public MediaControllerImplBase(MediaSessionCompat.Token token) {
            this.mToken = token;
            this.mBinder = IMediaSession.Stub.asInterface((IBinder)token.getToken());
        }

        @Override
        public void adjustVolume(int n2, int n3) {
            try {
                this.mBinder.adjustVolume(n2, n3, null);
                return;
            }
            catch (RemoteException var3_3) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in adjustVolume. " + (Object)var3_3));
                return;
            }
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
            if (keyEvent == null) {
                throw new IllegalArgumentException("event may not be null.");
            }
            try {
                this.mBinder.sendMediaButton(keyEvent);
                do {
                    return false;
                    break;
                } while (true);
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in dispatchMediaButtonEvent. " + (Object)var1_2));
                return false;
            }
        }

        @Override
        public Bundle getExtras() {
            try {
                Bundle bundle = this.mBinder.getExtras();
                return bundle;
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in getExtras. " + (Object)var1_2));
                return null;
            }
        }

        @Override
        public long getFlags() {
            try {
                long l2 = this.mBinder.getFlags();
                return l2;
            }
            catch (RemoteException var3_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in getFlags. " + (Object)var3_2));
                return 0;
            }
        }

        @Override
        public Object getMediaController() {
            return null;
        }

        @Override
        public MediaMetadataCompat getMetadata() {
            try {
                MediaMetadataCompat mediaMetadataCompat = this.mBinder.getMetadata();
                return mediaMetadataCompat;
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in getMetadata. " + (Object)var1_2));
                return null;
            }
        }

        @Override
        public String getPackageName() {
            try {
                String string2 = this.mBinder.getPackageName();
                return string2;
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in getPackageName. " + (Object)var1_2));
                return null;
            }
        }

        @Override
        public PlaybackInfo getPlaybackInfo() {
            try {
                Object object = this.mBinder.getVolumeAttributes();
                object = new PlaybackInfo(object.volumeType, object.audioStream, object.controlType, object.maxVolume, object.currentVolume);
                return object;
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in getPlaybackInfo. " + (Object)var1_2));
                return null;
            }
        }

        @Override
        public PlaybackStateCompat getPlaybackState() {
            try {
                PlaybackStateCompat playbackStateCompat = this.mBinder.getPlaybackState();
                return playbackStateCompat;
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in getPlaybackState. " + (Object)var1_2));
                return null;
            }
        }

        @Override
        public List<MediaSessionCompat.QueueItem> getQueue() {
            try {
                List<MediaSessionCompat.QueueItem> list = this.mBinder.getQueue();
                return list;
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in getQueue. " + (Object)var1_2));
                return null;
            }
        }

        @Override
        public CharSequence getQueueTitle() {
            try {
                CharSequence charSequence = this.mBinder.getQueueTitle();
                return charSequence;
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in getQueueTitle. " + (Object)var1_2));
                return null;
            }
        }

        @Override
        public int getRatingType() {
            try {
                int n2 = this.mBinder.getRatingType();
                return n2;
            }
            catch (RemoteException var2_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in getRatingType. " + (Object)var2_2));
                return 0;
            }
        }

        @Override
        public PendingIntent getSessionActivity() {
            try {
                PendingIntent pendingIntent = this.mBinder.getLaunchPendingIntent();
                return pendingIntent;
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in getSessionActivity. " + (Object)var1_2));
                return null;
            }
        }

        @Override
        public TransportControls getTransportControls() {
            if (this.mTransportControls == null) {
                this.mTransportControls = new TransportControlsBase(this.mBinder);
            }
            return this.mTransportControls;
        }

        @Override
        public void registerCallback(Callback callback, Handler handler) {
            if (callback == null) {
                throw new IllegalArgumentException("callback may not be null.");
            }
            try {
                this.mBinder.asBinder().linkToDeath((IBinder.DeathRecipient)callback, 0);
                this.mBinder.registerCallbackListener((IMediaControllerCallback)callback.mCallbackObj);
                callback.setHandler(handler);
                callback.mRegistered = true;
                return;
            }
            catch (RemoteException var2_3) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in registerCallback. " + (Object)var2_3));
                callback.onSessionDestroyed();
                return;
            }
        }

        @Override
        public void sendCommand(String string2, Bundle bundle, ResultReceiver resultReceiver) {
            try {
                this.mBinder.sendCommand(string2, bundle, new MediaSessionCompat.ResultReceiverWrapper(resultReceiver));
                return;
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in sendCommand. " + (Object)var1_2));
                return;
            }
        }

        @Override
        public void setVolumeTo(int n2, int n3) {
            try {
                this.mBinder.setVolumeTo(n2, n3, null);
                return;
            }
            catch (RemoteException var3_3) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in setVolumeTo. " + (Object)var3_3));
                return;
            }
        }

        @Override
        public void unregisterCallback(Callback callback) {
            if (callback == null) {
                throw new IllegalArgumentException("callback may not be null.");
            }
            try {
                this.mBinder.unregisterCallbackListener((IMediaControllerCallback)callback.mCallbackObj);
                this.mBinder.asBinder().unlinkToDeath((IBinder.DeathRecipient)callback, 0);
                callback.mRegistered = false;
                return;
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in unregisterCallback. " + (Object)var1_2));
                return;
            }
        }
    }

    public static final class PlaybackInfo {
        public static final int PLAYBACK_TYPE_LOCAL = 1;
        public static final int PLAYBACK_TYPE_REMOTE = 2;
        private final int mAudioStream;
        private final int mCurrentVolume;
        private final int mMaxVolume;
        private final int mPlaybackType;
        private final int mVolumeControl;

        PlaybackInfo(int n2, int n3, int n4, int n5, int n6) {
            this.mPlaybackType = n2;
            this.mAudioStream = n3;
            this.mVolumeControl = n4;
            this.mMaxVolume = n5;
            this.mCurrentVolume = n6;
        }

        public int getAudioStream() {
            return this.mAudioStream;
        }

        public int getCurrentVolume() {
            return this.mCurrentVolume;
        }

        public int getMaxVolume() {
            return this.mMaxVolume;
        }

        public int getPlaybackType() {
            return this.mPlaybackType;
        }

        public int getVolumeControl() {
            return this.mVolumeControl;
        }
    }

    public static abstract class TransportControls {
        TransportControls() {
        }

        public abstract void fastForward();

        public abstract void pause();

        public abstract void play();

        public abstract void playFromMediaId(String var1, Bundle var2);

        public abstract void playFromSearch(String var1, Bundle var2);

        public abstract void rewind();

        public abstract void seekTo(long var1);

        public abstract void sendCustomAction(PlaybackStateCompat.CustomAction var1, Bundle var2);

        public abstract void sendCustomAction(String var1, Bundle var2);

        public abstract void setRating(RatingCompat var1);

        public abstract void skipToNext();

        public abstract void skipToPrevious();

        public abstract void skipToQueueItem(long var1);

        public abstract void stop();
    }

    static class TransportControlsApi21
    extends TransportControls {
        private final Object mControlsObj;

        public TransportControlsApi21(Object object) {
            this.mControlsObj = object;
        }

        @Override
        public void fastForward() {
            MediaControllerCompatApi21.TransportControls.fastForward(this.mControlsObj);
        }

        @Override
        public void pause() {
            MediaControllerCompatApi21.TransportControls.pause(this.mControlsObj);
        }

        @Override
        public void play() {
            MediaControllerCompatApi21.TransportControls.play(this.mControlsObj);
        }

        @Override
        public void playFromMediaId(String string2, Bundle bundle) {
            MediaControllerCompatApi21.TransportControls.playFromMediaId(this.mControlsObj, string2, bundle);
        }

        @Override
        public void playFromSearch(String string2, Bundle bundle) {
            MediaControllerCompatApi21.TransportControls.playFromSearch(this.mControlsObj, string2, bundle);
        }

        @Override
        public void rewind() {
            MediaControllerCompatApi21.TransportControls.rewind(this.mControlsObj);
        }

        @Override
        public void seekTo(long l2) {
            MediaControllerCompatApi21.TransportControls.seekTo(this.mControlsObj, l2);
        }

        @Override
        public void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle bundle) {
            MediaControllerCompatApi21.TransportControls.sendCustomAction(this.mControlsObj, customAction.getAction(), bundle);
        }

        @Override
        public void sendCustomAction(String string2, Bundle bundle) {
            MediaControllerCompatApi21.TransportControls.sendCustomAction(this.mControlsObj, string2, bundle);
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void setRating(RatingCompat object) {
            Object object2 = this.mControlsObj;
            object = object != null ? object.getRating() : null;
            MediaControllerCompatApi21.TransportControls.setRating(object2, object);
        }

        @Override
        public void skipToNext() {
            MediaControllerCompatApi21.TransportControls.skipToNext(this.mControlsObj);
        }

        @Override
        public void skipToPrevious() {
            MediaControllerCompatApi21.TransportControls.skipToPrevious(this.mControlsObj);
        }

        @Override
        public void skipToQueueItem(long l2) {
            MediaControllerCompatApi21.TransportControls.skipToQueueItem(this.mControlsObj, l2);
        }

        @Override
        public void stop() {
            MediaControllerCompatApi21.TransportControls.stop(this.mControlsObj);
        }
    }

    static class TransportControlsBase
    extends TransportControls {
        private IMediaSession mBinder;

        public TransportControlsBase(IMediaSession iMediaSession) {
            this.mBinder = iMediaSession;
        }

        @Override
        public void fastForward() {
            try {
                this.mBinder.fastForward();
                return;
            }
            catch (RemoteException var1_1) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in fastForward. " + (Object)var1_1));
                return;
            }
        }

        @Override
        public void pause() {
            try {
                this.mBinder.pause();
                return;
            }
            catch (RemoteException var1_1) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in pause. " + (Object)var1_1));
                return;
            }
        }

        @Override
        public void play() {
            try {
                this.mBinder.play();
                return;
            }
            catch (RemoteException var1_1) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in play. " + (Object)var1_1));
                return;
            }
        }

        @Override
        public void playFromMediaId(String string2, Bundle bundle) {
            try {
                this.mBinder.playFromMediaId(string2, bundle);
                return;
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in playFromMediaId. " + (Object)var1_2));
                return;
            }
        }

        @Override
        public void playFromSearch(String string2, Bundle bundle) {
            try {
                this.mBinder.playFromSearch(string2, bundle);
                return;
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in playFromSearch. " + (Object)var1_2));
                return;
            }
        }

        @Override
        public void rewind() {
            try {
                this.mBinder.rewind();
                return;
            }
            catch (RemoteException var1_1) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in rewind. " + (Object)var1_1));
                return;
            }
        }

        @Override
        public void seekTo(long l2) {
            try {
                this.mBinder.seekTo(l2);
                return;
            }
            catch (RemoteException var3_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in seekTo. " + (Object)var3_2));
                return;
            }
        }

        @Override
        public void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle bundle) {
            this.sendCustomAction(customAction.getAction(), bundle);
        }

        @Override
        public void sendCustomAction(String string2, Bundle bundle) {
            try {
                this.mBinder.sendCustomAction(string2, bundle);
                return;
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in sendCustomAction. " + (Object)var1_2));
                return;
            }
        }

        @Override
        public void setRating(RatingCompat ratingCompat) {
            try {
                this.mBinder.rate(ratingCompat);
                return;
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in setRating. " + (Object)var1_2));
                return;
            }
        }

        @Override
        public void skipToNext() {
            try {
                this.mBinder.next();
                return;
            }
            catch (RemoteException var1_1) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in skipToNext. " + (Object)var1_1));
                return;
            }
        }

        @Override
        public void skipToPrevious() {
            try {
                this.mBinder.previous();
                return;
            }
            catch (RemoteException var1_1) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in skipToPrevious. " + (Object)var1_1));
                return;
            }
        }

        @Override
        public void skipToQueueItem(long l2) {
            try {
                this.mBinder.skipToQueueItem(l2);
                return;
            }
            catch (RemoteException var3_2) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in skipToQueueItem. " + (Object)var3_2));
                return;
            }
        }

        @Override
        public void stop() {
            try {
                this.mBinder.stop();
                return;
            }
            catch (RemoteException var1_1) {
                Log.e((String)"MediaControllerCompat", (String)("Dead object in stop. " + (Object)var1_1));
                return;
            }
        }
    }

}


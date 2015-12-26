/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.media.AudioManager
 *  android.media.AudioManager$OnAudioFocusChangeListener
 *  android.media.RemoteControlClient
 *  android.media.RemoteControlClient$OnGetPlaybackPositionListener
 *  android.media.RemoteControlClient$OnPlaybackPositionUpdateListener
 *  android.view.View
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnWindowAttachListener
 *  android.view.ViewTreeObserver$OnWindowFocusChangeListener
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package android.support.v4.media;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.RemoteControlClient;
import android.support.v4.media.TransportMediatorCallback;
import android.support.v4.media.TransportMediatorJellybeanMR2$1;
import android.support.v4.media.TransportMediatorJellybeanMR2$2;
import android.support.v4.media.TransportMediatorJellybeanMR2$3;
import android.support.v4.media.TransportMediatorJellybeanMR2$4;
import android.view.View;
import android.view.ViewTreeObserver;

class TransportMediatorJellybeanMR2
implements RemoteControlClient.OnGetPlaybackPositionListener,
RemoteControlClient.OnPlaybackPositionUpdateListener {
    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener;
    boolean mAudioFocused;
    final AudioManager mAudioManager;
    final Context mContext;
    boolean mFocused;
    final Intent mIntent;
    final BroadcastReceiver mMediaButtonReceiver;
    PendingIntent mPendingIntent;
    int mPlayState;
    final String mReceiverAction;
    final IntentFilter mReceiverFilter;
    RemoteControlClient mRemoteControl;
    final View mTargetView;
    final TransportMediatorCallback mTransportCallback;
    final ViewTreeObserver.OnWindowAttachListener mWindowAttachListener;
    final ViewTreeObserver.OnWindowFocusChangeListener mWindowFocusListener;

    public TransportMediatorJellybeanMR2(Context context, AudioManager audioManager, View view, TransportMediatorCallback transportMediatorCallback) {
        this.mWindowAttachListener = new TransportMediatorJellybeanMR2$1(this);
        this.mWindowFocusListener = new TransportMediatorJellybeanMR2$2(this);
        this.mMediaButtonReceiver = new TransportMediatorJellybeanMR2$3(this);
        this.mAudioFocusChangeListener = new TransportMediatorJellybeanMR2$4(this);
        this.mPlayState = 0;
        this.mContext = context;
        this.mAudioManager = audioManager;
        this.mTargetView = view;
        this.mTransportCallback = transportMediatorCallback;
        this.mReceiverAction = context.getPackageName() + ":transport:" + System.identityHashCode((Object)this);
        this.mIntent = new Intent(this.mReceiverAction);
        this.mIntent.setPackage(context.getPackageName());
        this.mReceiverFilter = new IntentFilter();
        this.mReceiverFilter.addAction(this.mReceiverAction);
        this.mTargetView.getViewTreeObserver().addOnWindowAttachListener(this.mWindowAttachListener);
        this.mTargetView.getViewTreeObserver().addOnWindowFocusChangeListener(this.mWindowFocusListener);
    }

    public void destroy() {
        this.windowDetached();
        this.mTargetView.getViewTreeObserver().removeOnWindowAttachListener(this.mWindowAttachListener);
        this.mTargetView.getViewTreeObserver().removeOnWindowFocusChangeListener(this.mWindowFocusListener);
    }

    void dropAudioFocus() {
        if (this.mAudioFocused) {
            this.mAudioFocused = false;
            this.mAudioManager.abandonAudioFocus(this.mAudioFocusChangeListener);
        }
    }

    void gainFocus() {
        if (!this.mFocused) {
            this.mFocused = true;
            this.mAudioManager.registerMediaButtonEventReceiver(this.mPendingIntent);
            this.mAudioManager.registerRemoteControlClient(this.mRemoteControl);
            if (this.mPlayState == 3) {
                this.takeAudioFocus();
            }
        }
    }

    public Object getRemoteControlClient() {
        return this.mRemoteControl;
    }

    void loseFocus() {
        this.dropAudioFocus();
        if (this.mFocused) {
            this.mFocused = false;
            this.mAudioManager.unregisterRemoteControlClient(this.mRemoteControl);
            this.mAudioManager.unregisterMediaButtonEventReceiver(this.mPendingIntent);
        }
    }

    public long onGetPlaybackPosition() {
        return this.mTransportCallback.getPlaybackPosition();
    }

    public void onPlaybackPositionUpdate(long l2) {
        this.mTransportCallback.playbackPositionUpdate(l2);
    }

    public void pausePlaying() {
        if (this.mPlayState == 3) {
            this.mPlayState = 2;
            this.mRemoteControl.setPlaybackState(2);
        }
        this.dropAudioFocus();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void refreshState(boolean bl2, long l2, int n2) {
        if (this.mRemoteControl != null) {
            RemoteControlClient remoteControlClient = this.mRemoteControl;
            int n3 = bl2 ? 3 : 1;
            float f2 = bl2 ? 1.0f : 0.0f;
            remoteControlClient.setPlaybackState(n3, l2, f2);
            this.mRemoteControl.setTransportControlFlags(n2);
        }
    }

    public void startPlaying() {
        if (this.mPlayState != 3) {
            this.mPlayState = 3;
            this.mRemoteControl.setPlaybackState(3);
        }
        if (this.mFocused) {
            this.takeAudioFocus();
        }
    }

    public void stopPlaying() {
        if (this.mPlayState != 1) {
            this.mPlayState = 1;
            this.mRemoteControl.setPlaybackState(1);
        }
        this.dropAudioFocus();
    }

    void takeAudioFocus() {
        if (!this.mAudioFocused) {
            this.mAudioFocused = true;
            this.mAudioManager.requestAudioFocus(this.mAudioFocusChangeListener, 3, 1);
        }
    }

    void windowAttached() {
        this.mContext.registerReceiver(this.mMediaButtonReceiver, this.mReceiverFilter);
        this.mPendingIntent = PendingIntent.getBroadcast((Context)this.mContext, (int)0, (Intent)this.mIntent, (int)268435456);
        this.mRemoteControl = new RemoteControlClient(this.mPendingIntent);
        this.mRemoteControl.setOnGetPlaybackPositionListener((RemoteControlClient.OnGetPlaybackPositionListener)this);
        this.mRemoteControl.setPlaybackPositionUpdateListener((RemoteControlClient.OnPlaybackPositionUpdateListener)this);
    }

    void windowDetached() {
        this.loseFocus();
        if (this.mPendingIntent != null) {
            this.mContext.unregisterReceiver(this.mMediaButtonReceiver);
            this.mPendingIntent.cancel();
            this.mPendingIntent = null;
            this.mRemoteControl = null;
        }
    }
}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnCompletionListener
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 */
package com.xiaomi.mms.mx.audio.player;

import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.mms.mx.audio.player.MediaPlayerObserver;
import com.xiaomi.mms.mx.audio.player.PlayerObservable;
import com.xiaomi.mms.mx.audio.player.PlayerStatus;
import java.io.IOException;
import java.util.Observer;

public class XMAudioPlayer
extends Thread {
    private final String mAudioPath;
    private MediaPlayer mMediaPlayer;
    private Object mMediaPlayerLock = new Object();
    private PlayerObservable mObservable;
    private boolean mStopPlay = false;

    public XMAudioPlayer(String string2, MediaPlayerObserver mediaPlayerObserver) {
        this.mAudioPath = string2;
        this.mObservable = new PlayerObservable();
        this.mObservable.addObserver(mediaPlayerObserver);
    }

    private void doStop() {
        this.mStopPlay = true;
        this.sendPlayStatusMessage(3);
        this.releaseMediaPlayer();
        this.mObservable.deleteObservers();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void init() {
        Object object = this.mMediaPlayerLock;
        synchronized (object) {
            this.mMediaPlayer = new MediaPlayer();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private void play() {
        this.init();
        this.sendPlayStatusMessage(5);
        try {
            Object object = this.mMediaPlayerLock;
            // MONITORENTER : object
        }
        catch (IOException var1_2) {
            this.doStop();
            Log.e((String)"XMAudioPlayer", (String)var1_2.toString());
            return;
        }
        this.mMediaPlayer.setDataSource(this.mAudioPath);
        this.mMediaPlayer.prepare();
        this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){

            public void onCompletion(MediaPlayer mediaPlayer) {
                XMAudioPlayer.this.doStop();
            }
        });
        // MONITOREXIT : object
        this.sendPlayStatusMessage(2);
        this.startMediaPlayer();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void releaseMediaPlayer() {
        Object object = this.mMediaPlayerLock;
        synchronized (object) {
            if (this.mMediaPlayer != null) {
                this.mMediaPlayer.release();
                this.mMediaPlayer = null;
            }
            return;
        }
    }

    private void sendPlayStatusMessage(int n) {
        this.mObservable.notifyObservers(new PlayerStatus(n));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void startMediaPlayer() {
        Object object = this.mMediaPlayerLock;
        synchronized (object) {
            if (this.mMediaPlayer != null) {
                this.mMediaPlayer.start();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void stopMediaPlayer() {
        Object object = this.mMediaPlayerLock;
        synchronized (object) {
            if (this.mMediaPlayer != null) {
                this.mMediaPlayer.stop();
            }
            return;
        }
    }

    public boolean isPlaying(String string2) {
        if (!this.isStopped() && TextUtils.equals((CharSequence)string2, (CharSequence)this.mAudioPath)) {
            return true;
        }
        return false;
    }

    public boolean isStopped() {
        return this.mStopPlay;
    }

    public void run() {
        this.sendPlayStatusMessage(1);
        this.play();
    }

    public void stopPlay() {
        this.stopMediaPlayer();
        this.doStop();
    }

}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Handler
 *  android.os.Message
 *  android.util.Log
 *  android.widget.Toast
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.audio;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.android.mms.MmsApp;
import com.android.mms.audio.AudioHelper;
import com.android.mms.audio.AudioItemCache;
import com.android.mms.audio.AudioItemController;
import com.android.mms.audio.AudioSensorManager;
import com.android.mms.audio.GlobalAudioPlayController;
import com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer;
import com.xiaomi.mms.mx.audio.player.PlayerStatus;

public class AudioPlayingHandler
extends Handler {
    private AudioItemCache mAudioItemCache;
    private AudioSensorManager mAudioSensorMgr;
    private GlobalAudioPlayController mGlobalAudioPlayController = GlobalAudioPlayController.get();
    private long mMsgId;

    public AudioPlayingHandler(long l, AudioSensorManager audioSensorManager, AudioItemCache audioItemCache) {
        this.mMsgId = l;
        this.mAudioItemCache = audioItemCache;
        this.mAudioSensorMgr = audioSensorManager;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void handleMessage(Message message) {
        if (message.obj instanceof PlayerStatus) {
            switch (((PlayerStatus)message.obj).mType) {
                default: {
                    Log.e((String)"AudioPlayingHandler", (String)"playerStatus type error");
                    break;
                }
                case 0: {
                    if (this.mAudioSensorMgr != null) {
                        this.mAudioSensorMgr.endListeningMode(false);
                    }
                    Toast.makeText((Context)MmsApp.getApp(), (int)2131362357, (int)0).show();
                    break;
                }
                case 2: {
                    AudioTalkMediaPlayer audioTalkMediaPlayer = AudioTalkMediaPlayer.getInstance((Context)MmsApp.getApp());
                    AudioItemController audioItemController = this.mAudioItemCache.getItemController(this.mMsgId);
                    if (!audioTalkMediaPlayer.isPlaying()) break;
                    if (this.mAudioSensorMgr != null) {
                        this.mAudioSensorMgr.startListeningMode();
                    }
                    AudioHelper.gainFocus();
                    if (audioItemController == null) break;
                    audioItemController.setPlayingViews();
                    audioItemController.markAsRead();
                    break;
                }
                case 3: {
                    AudioTalkMediaPlayer audioTalkMediaPlayer = AudioTalkMediaPlayer.getInstance((Context)MmsApp.getApp());
                    AudioItemController audioItemController = this.mAudioItemCache.getItemController(this.mMsgId);
                    if (this.mAudioSensorMgr != null) {
                        this.mAudioSensorMgr.endListeningMode(true);
                    }
                    if (audioTalkMediaPlayer.hasNext()) {
                        audioTalkMediaPlayer.playNext();
                    }
                    AudioHelper.releaseFocus();
                    if (audioItemController != null) {
                        audioItemController.resetPlayingViews();
                    }
                    if (this.mGlobalAudioPlayController == null) break;
                    this.mGlobalAudioPlayController.onStopPlay();
                }
            }
        }
        super.handleMessage(message);
    }
}


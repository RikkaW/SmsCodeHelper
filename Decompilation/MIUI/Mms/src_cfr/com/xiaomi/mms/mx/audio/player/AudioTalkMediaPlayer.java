/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.media.AudioManager
 *  android.os.Handler
 *  java.lang.Object
 *  java.lang.String
 *  java.util.LinkedList
 */
package com.xiaomi.mms.mx.audio.player;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import com.xiaomi.mms.mx.audio.player.MediaPlayerObserver;
import com.xiaomi.mms.mx.audio.player.PlayerStatus;
import com.xiaomi.mms.mx.audio.player.XMAudioPlayer;
import java.util.LinkedList;
import java.util.Observable;

public class AudioTalkMediaPlayer {
    private static AudioTalkMediaPlayer instance;
    private XMAudioPlayer mAudioPlayer;
    private Context mContext;
    private LinkedList<PlayPair> mPlayList = new LinkedList();

    private AudioTalkMediaPlayer(Context context) {
        this.mContext = context;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static AudioTalkMediaPlayer getInstance(Context object) {
        synchronized (AudioTalkMediaPlayer.class) {
            if (instance != null) {
                object = instance;
                do {
                    return object;
                    break;
                } while (true);
            }
            object = AudioTalkMediaPlayer.instance = new AudioTalkMediaPlayer((Context)object);
            return object;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void addToPlayList(long l, long l2, int n, String string2, String string3, MediaPlayerObserver mediaPlayerObserver, boolean bl) {
        synchronized (this) {
            block3 : {
                void var8_6;
                void var7_5;
                void var9_7;
                for (PlayPair playPair : this.mPlayList) {
                    if (playPair.msgId != l) continue;
                    playPair.localPath = string2;
                    break block3;
                }
                this.mPlayList.add((Object)new PlayPair(l, l2, string2, (String)var7_5, (MediaPlayerObserver)var8_6, (boolean)var9_7));
            }
            return;
        }
    }

    public void clearPlayList() {
        synchronized (this) {
            this.mPlayList.clear();
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean hasNext() {
        synchronized (this) {
            boolean bl = this.mPlayList.isEmpty();
            if (bl) return false;
            return true;
        }
    }

    public boolean isPlaying() {
        if (this.mAudioPlayer != null && !this.mAudioPlayer.isStopped()) {
            return true;
        }
        return false;
    }

    public boolean isPlaying(String string2) {
        if (this.mAudioPlayer != null && this.mAudioPlayer.isPlaying(string2)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void playNext() {
        synchronized (this) {
            Object object;
            block6 : {
                object = ((PlayPair)this.mPlayList.peek()).localPath;
                if (object != null) break block6;
                do {
                    return;
                    break;
                } while (true);
            }
            object = (PlayPair)this.mPlayList.poll();
            MediaPlayerObserver mediaPlayerObserver = new MediaPlayerObserver(null, (PlayPair)object){
                final /* synthetic */ PlayPair val$playItem;

                @Override
                public void update(Observable object, Object object2) {
                    if (this.val$playItem.mObserver != null) {
                        this.val$playItem.mObserver.update((Observable)object, object2);
                    }
                    if (object2 instanceof PlayerStatus) {
                        object = (PlayerStatus)object2;
                        if (object.mType == 0 || object.mType == 3) {
                            // empty if block
                        }
                    }
                }
            };
            this.mAudioPlayer = new XMAudioPlayer(object.localPath, mediaPlayerObserver);
            this.mAudioPlayer.start();
            return;
        }
    }

    public void setEarphoneStatus(boolean bl) {
        this.setEarphoneStatus(bl, false);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void setEarphoneStatus(boolean bl, boolean bl2) {
        if ((this.mAudioPlayer == null || this.mAudioPlayer.isStopped()) && !bl2) return;
        AudioManager audioManager = (AudioManager)this.mContext.getSystemService("audio");
        if (bl) {
            audioManager.setMode(2);
            audioManager.setSpeakerphoneOn(false);
            return;
        }
        audioManager.setMode(0);
        audioManager.setSpeakerphoneOn(true);
    }

    public void stop() {
        if (this.mAudioPlayer != null && !this.mAudioPlayer.isStopped()) {
            this.mAudioPlayer.stopPlay();
            this.mAudioPlayer = null;
        }
    }

    public static class PlayPair {
        public long attId;
        public String localPath;
        public boolean mMarkAsHeard;
        public MediaPlayerObserver mObserver;
        public String mTargetAccountName;
        public long msgId;

        public PlayPair(long l, long l2, String string2, String string3, MediaPlayerObserver mediaPlayerObserver, boolean bl) {
            this.msgId = l;
            this.attId = l2;
            this.localPath = string2;
            this.mTargetAccountName = string3;
            this.mObserver = mediaPlayerObserver;
            this.mMarkAsHeard = bl;
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean equals(Object object) {
            if (!(object instanceof PlayPair) || this.msgId != ((PlayPair)object).msgId) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (int)this.msgId;
        }
    }

}


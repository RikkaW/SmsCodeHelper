/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package com.android.mms.audio;

import com.android.mms.audio.AudioItemController;
import java.util.HashMap;

public class AudioItemCache {
    private HashMap<Long, AudioItemController> mItemCache = new HashMap();
    private HashMap<Long, String> mMmsAudioPathCache = new HashMap();

    public void add(long l, AudioItemController audioItemController) {
        this.mItemCache.put((Object)l, (Object)audioItemController);
    }

    public void addMmsAudioPath(long l, String string) {
        this.mMmsAudioPathCache.put((Object)l, (Object)string);
    }

    public AudioItemController getItemController(long l) {
        return (AudioItemController)this.mItemCache.get((Object)l);
    }

    public String getMmsAudioPath(long l) {
        return (String)this.mMmsAudioPathCache.get((Object)l);
    }

    public void remove(long l) {
        this.mItemCache.remove((Object)l);
    }

    public void removeMmsAudioPath(long l) {
        this.mMmsAudioPathCache.remove((Object)l);
    }
}


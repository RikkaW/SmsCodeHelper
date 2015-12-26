/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Message
 *  java.lang.Object
 */
package com.xiaomi.mms.mx.audio.player;

import android.os.Handler;
import android.os.Message;
import java.util.Observable;
import java.util.Observer;

public class MediaPlayerObserver
implements Observer {
    private final Handler mHandler;

    public MediaPlayerObserver(Handler handler) {
        this.mHandler = handler;
    }

    @Override
    public void update(Observable observable, Object object) {
        observable = new Message();
        observable.obj = object;
        this.mHandler.sendMessage((Message)observable);
    }
}


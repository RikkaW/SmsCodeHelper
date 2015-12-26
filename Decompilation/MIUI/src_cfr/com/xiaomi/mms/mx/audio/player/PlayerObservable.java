/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.xiaomi.mms.mx.audio.player;

import java.util.Observable;

public class PlayerObservable
extends Observable {
    @Override
    public void notifyObservers(Object object) {
        super.setChanged();
        super.notifyObservers(object);
    }
}


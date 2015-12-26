/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.ArrayList
 */
package com.android.mms.transaction;

import com.android.mms.transaction.Observer;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Observable {
    private Iterator<Observer> mIterator;
    private final ArrayList<Observer> mObservers = new ArrayList();

    public void attach(Observer observer) {
        this.mObservers.add((Object)observer);
    }

    public void detach(Observer observer) {
        if (this.mIterator != null) {
            this.mIterator.remove();
            return;
        }
        this.mObservers.remove((Object)observer);
    }

    public void notifyObservers() {
        this.mIterator = this.mObservers.iterator();
        try {
            while (this.mIterator.hasNext()) {
                this.mIterator.next().update(this);
            }
        }
        finally {
            this.mIterator = null;
        }
    }
}


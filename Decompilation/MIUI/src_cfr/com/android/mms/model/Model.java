/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.ArrayList
 */
package com.android.mms.model;

import com.android.mms.model.IModelChangedObserver;
import java.util.ArrayList;
import java.util.Iterator;

public class Model {
    protected ArrayList<IModelChangedObserver> mModelChangedObservers = new ArrayList();

    protected void notifyModelChanged(boolean bl) {
        Iterator iterator = this.mModelChangedObservers.iterator();
        while (iterator.hasNext()) {
            ((IModelChangedObserver)iterator.next()).onModelChanged(this, bl);
        }
    }

    public void registerModelChangedObserver(IModelChangedObserver iModelChangedObserver) {
        if (!this.mModelChangedObservers.contains((Object)iModelChangedObserver)) {
            this.mModelChangedObservers.add((Object)iModelChangedObserver);
            this.registerModelChangedObserverInDescendants(iModelChangedObserver);
        }
    }

    protected void registerModelChangedObserverInDescendants(IModelChangedObserver iModelChangedObserver) {
    }

    public void unregisterAllModelChangedObservers() {
        this.unregisterAllModelChangedObserversInDescendants();
        this.mModelChangedObservers.clear();
    }

    protected void unregisterAllModelChangedObserversInDescendants() {
    }

    public void unregisterModelChangedObserver(IModelChangedObserver iModelChangedObserver) {
        this.mModelChangedObservers.remove((Object)iModelChangedObserver);
        this.unregisterModelChangedObserverInDescendants(iModelChangedObserver);
    }

    protected void unregisterModelChangedObserverInDescendants(IModelChangedObserver iModelChangedObserver) {
    }
}


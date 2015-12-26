/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 */
package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.collect.MapMaker;

public abstract class GenericMapMaker<K0, V0> {
    MapMaker.RemovalListener<K0, V0> removalListener;

    GenericMapMaker() {
    }

    <K extends K0, V extends V0> MapMaker.RemovalListener<K, V> getRemovalListener() {
        return Objects.firstNonNull(this.removalListener, NullListener.INSTANCE);
    }

    static enum NullListener implements MapMaker.RemovalListener<Object, Object>
    {
        INSTANCE;
        

        private NullListener() {
        }

        @Override
        public void onRemoval(MapMaker.RemovalNotification<Object, Object> removalNotification) {
        }
    }

}


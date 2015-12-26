/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.LayoutInflater
 *  android.view.LayoutInflater$Factory2
 *  java.lang.Object
 */
package android.support.v4.view;

import android.support.v4.view.LayoutInflaterCompatHC;
import android.support.v4.view.LayoutInflaterFactory;
import android.view.LayoutInflater;

class LayoutInflaterCompatLollipop {
    LayoutInflaterCompatLollipop() {
    }

    /*
     * Enabled aggressive block sorting
     */
    static void setFactory(LayoutInflater layoutInflater, LayoutInflaterFactory object) {
        object = object != null ? new LayoutInflaterCompatHC.FactoryWrapperHC((LayoutInflaterFactory)object) : null;
        layoutInflater.setFactory2((LayoutInflater.Factory2)object);
    }
}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.LayoutInflater
 *  android.view.LayoutInflater$Factory
 *  android.view.View
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.view;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

class LayoutInflaterCompatBase {
    LayoutInflaterCompatBase() {
    }

    /*
     * Enabled aggressive block sorting
     */
    static void setFactory(LayoutInflater layoutInflater, LayoutInflaterFactory object) {
        object = object != null ? new FactoryWrapper((LayoutInflaterFactory)object) : null;
        layoutInflater.setFactory((LayoutInflater.Factory)object);
    }

    static class FactoryWrapper
    implements LayoutInflater.Factory {
        final LayoutInflaterFactory mDelegateFactory;

        FactoryWrapper(LayoutInflaterFactory layoutInflaterFactory) {
            this.mDelegateFactory = layoutInflaterFactory;
        }

        public View onCreateView(String string2, Context context, AttributeSet attributeSet) {
            return this.mDelegateFactory.onCreateView(null, string2, context, attributeSet);
        }

        public String toString() {
            return this.getClass().getName() + "{" + this.mDelegateFactory + "}";
        }
    }

}


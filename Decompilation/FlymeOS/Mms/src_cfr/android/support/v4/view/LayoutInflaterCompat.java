/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.LayoutInflater
 *  java.lang.Object
 */
package android.support.v4.view;

import android.os.Build;
import android.support.v4.view.LayoutInflaterCompatBase;
import android.support.v4.view.LayoutInflaterCompatHC;
import android.support.v4.view.LayoutInflaterCompatLollipop;
import android.support.v4.view.LayoutInflaterFactory;
import android.view.LayoutInflater;

public class LayoutInflaterCompat {
    static final LayoutInflaterCompatImpl IMPL;

    static {
        int n2 = Build.VERSION.SDK_INT;
        IMPL = n2 >= 21 ? new LayoutInflaterCompatImplV21() : (n2 >= 11 ? new LayoutInflaterCompatImplV11() : new LayoutInflaterCompatImplBase());
    }

    private LayoutInflaterCompat() {
    }

    public static void setFactory(LayoutInflater layoutInflater, LayoutInflaterFactory layoutInflaterFactory) {
        IMPL.setFactory(layoutInflater, layoutInflaterFactory);
    }

    static interface LayoutInflaterCompatImpl {
        public void setFactory(LayoutInflater var1, LayoutInflaterFactory var2);
    }

    static class LayoutInflaterCompatImplBase
    implements LayoutInflaterCompatImpl {
        LayoutInflaterCompatImplBase() {
        }

        @Override
        public void setFactory(LayoutInflater layoutInflater, LayoutInflaterFactory layoutInflaterFactory) {
            LayoutInflaterCompatBase.setFactory(layoutInflater, layoutInflaterFactory);
        }
    }

    static class LayoutInflaterCompatImplV11
    extends LayoutInflaterCompatImplBase {
        LayoutInflaterCompatImplV11() {
        }

        @Override
        public void setFactory(LayoutInflater layoutInflater, LayoutInflaterFactory layoutInflaterFactory) {
            LayoutInflaterCompatHC.setFactory(layoutInflater, layoutInflaterFactory);
        }
    }

    static class LayoutInflaterCompatImplV21
    extends LayoutInflaterCompatImplV11 {
        LayoutInflaterCompatImplV21() {
        }

        @Override
        public void setFactory(LayoutInflater layoutInflater, LayoutInflaterFactory layoutInflaterFactory) {
            LayoutInflaterCompatLollipop.setFactory(layoutInflater, layoutInflaterFactory);
        }
    }

}


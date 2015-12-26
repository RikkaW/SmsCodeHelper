/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.KeyEvent
 *  java.lang.Object
 */
package android.support.v4.view;

import android.view.KeyEvent;

class KeyEventCompatHoneycomb {
    KeyEventCompatHoneycomb() {
    }

    public static boolean metaStateHasModifiers(int n2, int n3) {
        return KeyEvent.metaStateHasModifiers((int)n2, (int)n3);
    }

    public static boolean metaStateHasNoModifiers(int n2) {
        return KeyEvent.metaStateHasNoModifiers((int)n2);
    }

    public static int normalizeMetaState(int n2) {
        return KeyEvent.normalizeMetaState((int)n2);
    }
}


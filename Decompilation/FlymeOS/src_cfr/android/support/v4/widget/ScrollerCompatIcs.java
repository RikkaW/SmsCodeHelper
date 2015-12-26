/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.widget.OverScroller
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.widget.OverScroller;

class ScrollerCompatIcs {
    ScrollerCompatIcs() {
    }

    public static float getCurrVelocity(Object object) {
        return ((OverScroller)object).getCurrVelocity();
    }
}


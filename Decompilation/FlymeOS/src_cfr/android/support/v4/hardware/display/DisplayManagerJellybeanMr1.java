/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.hardware.display.DisplayManager
 *  android.view.Display
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;

final class DisplayManagerJellybeanMr1 {
    DisplayManagerJellybeanMr1() {
    }

    public static Display getDisplay(Object object, int n2) {
        return ((DisplayManager)object).getDisplay(n2);
    }

    public static Object getDisplayManager(Context context) {
        return context.getSystemService("display");
    }

    public static Display[] getDisplays(Object object) {
        return ((DisplayManager)object).getDisplays();
    }

    public static Display[] getDisplays(Object object, String string2) {
        return ((DisplayManager)object).getDisplays(string2);
    }
}


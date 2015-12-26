/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.Display
 *  android.view.WindowManager
 *  java.lang.Object
 *  java.lang.String
 *  java.util.WeakHashMap
 */
package android.support.v4.hardware.display;

import android.content.Context;
import android.os.Build;
import android.support.v4.hardware.display.DisplayManagerJellybeanMr1;
import android.view.Display;
import android.view.WindowManager;
import java.util.WeakHashMap;

public abstract class DisplayManagerCompat {
    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
    private static final WeakHashMap<Context, DisplayManagerCompat> sInstances = new WeakHashMap();

    DisplayManagerCompat() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static DisplayManagerCompat getInstance(Context context) {
        WeakHashMap<Context, DisplayManagerCompat> weakHashMap = sInstances;
        synchronized (weakHashMap) {
            DisplayManagerCompat displayManagerCompat;
            DisplayManagerCompat displayManagerCompat2 = displayManagerCompat = (DisplayManagerCompat)sInstances.get((Object)context);
            if (displayManagerCompat == null) {
                displayManagerCompat2 = Build.VERSION.SDK_INT >= 17 ? new JellybeanMr1Impl(context) : new LegacyImpl(context);
                sInstances.put((Object)context, (Object)displayManagerCompat2);
            }
            return displayManagerCompat2;
        }
    }

    public abstract Display getDisplay(int var1);

    public abstract Display[] getDisplays();

    public abstract Display[] getDisplays(String var1);

    static class JellybeanMr1Impl
    extends DisplayManagerCompat {
        private final Object mDisplayManagerObj;

        public JellybeanMr1Impl(Context context) {
            this.mDisplayManagerObj = DisplayManagerJellybeanMr1.getDisplayManager(context);
        }

        @Override
        public Display getDisplay(int n2) {
            return DisplayManagerJellybeanMr1.getDisplay(this.mDisplayManagerObj, n2);
        }

        @Override
        public Display[] getDisplays() {
            return DisplayManagerJellybeanMr1.getDisplays(this.mDisplayManagerObj);
        }

        @Override
        public Display[] getDisplays(String string2) {
            return DisplayManagerJellybeanMr1.getDisplays(this.mDisplayManagerObj, string2);
        }
    }

    static class LegacyImpl
    extends DisplayManagerCompat {
        private final WindowManager mWindowManager;

        public LegacyImpl(Context context) {
            this.mWindowManager = (WindowManager)context.getSystemService("window");
        }

        @Override
        public Display getDisplay(int n2) {
            Display display = this.mWindowManager.getDefaultDisplay();
            if (display.getDisplayId() == n2) {
                return display;
            }
            return null;
        }

        @Override
        public Display[] getDisplays() {
            return new Display[]{this.mWindowManager.getDefaultDisplay()};
        }

        @Override
        public Display[] getDisplays(String string2) {
            if (string2 == null) {
                return this.getDisplays();
            }
            return new Display[0];
        }
    }

}


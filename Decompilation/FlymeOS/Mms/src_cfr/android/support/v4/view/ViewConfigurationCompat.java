/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.ViewConfiguration
 *  java.lang.Object
 */
package android.support.v4.view;

import android.os.Build;
import android.support.v4.view.ViewConfigurationCompatFroyo;
import android.support.v4.view.ViewConfigurationCompatICS;
import android.view.ViewConfiguration;

public class ViewConfigurationCompat {
    static final ViewConfigurationVersionImpl IMPL = Build.VERSION.SDK_INT >= 14 ? new IcsViewConfigurationVersionImpl() : (Build.VERSION.SDK_INT >= 11 ? new HoneycombViewConfigurationVersionImpl() : (Build.VERSION.SDK_INT >= 8 ? new FroyoViewConfigurationVersionImpl() : new BaseViewConfigurationVersionImpl()));

    public static int getScaledPagingTouchSlop(ViewConfiguration viewConfiguration) {
        return IMPL.getScaledPagingTouchSlop(viewConfiguration);
    }

    public static boolean hasPermanentMenuKey(ViewConfiguration viewConfiguration) {
        return IMPL.hasPermanentMenuKey(viewConfiguration);
    }

    static class BaseViewConfigurationVersionImpl
    implements ViewConfigurationVersionImpl {
        BaseViewConfigurationVersionImpl() {
        }

        @Override
        public int getScaledPagingTouchSlop(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledTouchSlop();
        }

        @Override
        public boolean hasPermanentMenuKey(ViewConfiguration viewConfiguration) {
            return true;
        }
    }

    static class FroyoViewConfigurationVersionImpl
    extends BaseViewConfigurationVersionImpl {
        FroyoViewConfigurationVersionImpl() {
        }

        @Override
        public int getScaledPagingTouchSlop(ViewConfiguration viewConfiguration) {
            return ViewConfigurationCompatFroyo.getScaledPagingTouchSlop(viewConfiguration);
        }
    }

    static class HoneycombViewConfigurationVersionImpl
    extends FroyoViewConfigurationVersionImpl {
        HoneycombViewConfigurationVersionImpl() {
        }

        @Override
        public boolean hasPermanentMenuKey(ViewConfiguration viewConfiguration) {
            return false;
        }
    }

    static class IcsViewConfigurationVersionImpl
    extends HoneycombViewConfigurationVersionImpl {
        IcsViewConfigurationVersionImpl() {
        }

        @Override
        public boolean hasPermanentMenuKey(ViewConfiguration viewConfiguration) {
            return ViewConfigurationCompatICS.hasPermanentMenuKey(viewConfiguration);
        }
    }

    static interface ViewConfigurationVersionImpl {
        public int getScaledPagingTouchSlop(ViewConfiguration var1);

        public boolean hasPermanentMenuKey(ViewConfiguration var1);
    }

}


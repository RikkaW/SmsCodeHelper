/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.KeyEvent
 *  android.view.KeyEvent$Callback
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.view;

import android.os.Build;
import android.support.v4.view.KeyEventCompatEclair;
import android.support.v4.view.KeyEventCompatHoneycomb;
import android.view.KeyEvent;
import android.view.View;

public class KeyEventCompat {
    static final KeyEventVersionImpl IMPL = Build.VERSION.SDK_INT >= 11 ? new HoneycombKeyEventVersionImpl() : new BaseKeyEventVersionImpl();

    public static boolean dispatch(KeyEvent keyEvent, KeyEvent.Callback callback, Object object, Object object2) {
        return IMPL.dispatch(keyEvent, callback, object, object2);
    }

    public static Object getKeyDispatcherState(View view) {
        return IMPL.getKeyDispatcherState(view);
    }

    public static boolean hasModifiers(KeyEvent keyEvent, int n2) {
        return IMPL.metaStateHasModifiers(keyEvent.getMetaState(), n2);
    }

    public static boolean hasNoModifiers(KeyEvent keyEvent) {
        return IMPL.metaStateHasNoModifiers(keyEvent.getMetaState());
    }

    public static boolean isTracking(KeyEvent keyEvent) {
        return IMPL.isTracking(keyEvent);
    }

    public static boolean metaStateHasModifiers(int n2, int n3) {
        return IMPL.metaStateHasModifiers(n2, n3);
    }

    public static boolean metaStateHasNoModifiers(int n2) {
        return IMPL.metaStateHasNoModifiers(n2);
    }

    public static int normalizeMetaState(int n2) {
        return IMPL.normalizeMetaState(n2);
    }

    public static void startTracking(KeyEvent keyEvent) {
        IMPL.startTracking(keyEvent);
    }

    static class BaseKeyEventVersionImpl
    implements KeyEventVersionImpl {
        private static final int META_ALL_MASK = 247;
        private static final int META_MODIFIER_MASK = 247;

        BaseKeyEventVersionImpl() {
        }

        /*
         * Enabled aggressive block sorting
         */
        private static int metaStateFilterDirectionalModifiers(int n2, int n3, int n4, int n5, int n6) {
            int n7 = 1;
            boolean bl2 = (n3 & n4) != 0;
            n3 = (n3 & (n5 |= n6)) != 0 ? n7 : 0;
            if (bl2) {
                if (n3 == 0) return n2 & ~ n5;
                throw new IllegalArgumentException("bad arguments");
            }
            n5 = n2;
            if (n3 == 0) return n5;
            return n2 & ~ n4;
        }

        @Override
        public boolean dispatch(KeyEvent keyEvent, KeyEvent.Callback callback, Object object, Object object2) {
            return keyEvent.dispatch(callback);
        }

        @Override
        public Object getKeyDispatcherState(View view) {
            return null;
        }

        @Override
        public boolean isTracking(KeyEvent keyEvent) {
            return false;
        }

        @Override
        public boolean metaStateHasModifiers(int n2, int n3) {
            if (BaseKeyEventVersionImpl.metaStateFilterDirectionalModifiers(BaseKeyEventVersionImpl.metaStateFilterDirectionalModifiers(this.normalizeMetaState(n2) & 247, n3, 1, 64, 128), n3, 2, 16, 32) == n3) {
                return true;
            }
            return false;
        }

        @Override
        public boolean metaStateHasNoModifiers(int n2) {
            if ((this.normalizeMetaState(n2) & 247) == 0) {
                return true;
            }
            return false;
        }

        @Override
        public int normalizeMetaState(int n2) {
            if ((n2 & 192) != 0) {
                n2 |= 1;
            }
            int n3 = n2;
            if ((n2 & 48) != 0) {
                n3 = n2 | 2;
            }
            return n3 & 247;
        }

        @Override
        public void startTracking(KeyEvent keyEvent) {
        }
    }

    static class EclairKeyEventVersionImpl
    extends BaseKeyEventVersionImpl {
        EclairKeyEventVersionImpl() {
        }

        @Override
        public boolean dispatch(KeyEvent keyEvent, KeyEvent.Callback callback, Object object, Object object2) {
            return KeyEventCompatEclair.dispatch(keyEvent, callback, object, object2);
        }

        @Override
        public Object getKeyDispatcherState(View view) {
            return KeyEventCompatEclair.getKeyDispatcherState(view);
        }

        @Override
        public boolean isTracking(KeyEvent keyEvent) {
            return KeyEventCompatEclair.isTracking(keyEvent);
        }

        @Override
        public void startTracking(KeyEvent keyEvent) {
            KeyEventCompatEclair.startTracking(keyEvent);
        }
    }

    static class HoneycombKeyEventVersionImpl
    extends EclairKeyEventVersionImpl {
        HoneycombKeyEventVersionImpl() {
        }

        @Override
        public boolean metaStateHasModifiers(int n2, int n3) {
            return KeyEventCompatHoneycomb.metaStateHasModifiers(n2, n3);
        }

        @Override
        public boolean metaStateHasNoModifiers(int n2) {
            return KeyEventCompatHoneycomb.metaStateHasNoModifiers(n2);
        }

        @Override
        public int normalizeMetaState(int n2) {
            return KeyEventCompatHoneycomb.normalizeMetaState(n2);
        }
    }

    static interface KeyEventVersionImpl {
        public boolean dispatch(KeyEvent var1, KeyEvent.Callback var2, Object var3, Object var4);

        public Object getKeyDispatcherState(View var1);

        public boolean isTracking(KeyEvent var1);

        public boolean metaStateHasModifiers(int var1, int var2);

        public boolean metaStateHasNoModifiers(int var1);

        public int normalizeMetaState(int var1);

        public void startTracking(KeyEvent var1);
    }

}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.View
 *  android.view.View$OnTouchListener
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.os.Build;
import android.support.v4.widget.ListPopupWindowCompatKitKat;
import android.view.View;

public class ListPopupWindowCompat {
    static final ListPopupWindowImpl IMPL = Build.VERSION.SDK_INT >= 19 ? new KitKatListPopupWindowImpl() : new BaseListPopupWindowImpl();

    private ListPopupWindowCompat() {
    }

    public static View.OnTouchListener createDragToOpenListener(Object object, View view) {
        return IMPL.createDragToOpenListener(object, view);
    }

    static class BaseListPopupWindowImpl
    implements ListPopupWindowImpl {
        BaseListPopupWindowImpl() {
        }

        @Override
        public View.OnTouchListener createDragToOpenListener(Object object, View view) {
            return null;
        }
    }

    static class KitKatListPopupWindowImpl
    extends BaseListPopupWindowImpl {
        KitKatListPopupWindowImpl() {
        }

        @Override
        public View.OnTouchListener createDragToOpenListener(Object object, View view) {
            return ListPopupWindowCompatKitKat.createDragToOpenListener(object, view);
        }
    }

    static interface ListPopupWindowImpl {
        public View.OnTouchListener createDragToOpenListener(Object var1, View var2);
    }

}


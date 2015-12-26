/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.View
 *  android.widget.PopupWindow
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.os.Build;
import android.support.v4.widget.PopupWindowCompatKitKat;
import android.view.View;
import android.widget.PopupWindow;

public class PopupWindowCompat {
    static final PopupWindowImpl IMPL = Build.VERSION.SDK_INT >= 19 ? new KitKatPopupWindowImpl() : new BasePopupWindowImpl();

    private PopupWindowCompat() {
    }

    public static void showAsDropDown(PopupWindow popupWindow, View view, int n2, int n3, int n4) {
        IMPL.showAsDropDown(popupWindow, view, n2, n3, n4);
    }

    static class BasePopupWindowImpl
    implements PopupWindowImpl {
        BasePopupWindowImpl() {
        }

        @Override
        public void showAsDropDown(PopupWindow popupWindow, View view, int n2, int n3, int n4) {
            popupWindow.showAsDropDown(view, n2, n3);
        }
    }

    static class KitKatPopupWindowImpl
    extends BasePopupWindowImpl {
        KitKatPopupWindowImpl() {
        }

        @Override
        public void showAsDropDown(PopupWindow popupWindow, View view, int n2, int n3, int n4) {
            PopupWindowCompatKitKat.showAsDropDown(popupWindow, view, n2, n3, n4);
        }
    }

    static interface PopupWindowImpl {
        public void showAsDropDown(PopupWindow var1, View var2, int var3, int var4, int var5);
    }

}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.TargetApi
 *  android.content.Context
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.Gravity
 *  android.view.View
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnScrollChangedListener
 *  android.widget.PopupWindow
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Field
 */
package android.support.v7.internal.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.AppCompatPopupWindow$1;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;
import java.lang.reflect.Field;

public class AppCompatPopupWindow
extends PopupWindow {
    private static final String TAG = "AppCompatPopupWindow";
    private final boolean mOverlapAnchor;

    public AppCompatPopupWindow(Context object, AttributeSet attributeSet, int n2) {
        super((Context)object, attributeSet, n2);
        object = TintTypedArray.obtainStyledAttributes((Context)object, attributeSet, R.styleable.PopupWindow, n2, 0);
        this.mOverlapAnchor = object.getBoolean(R.styleable.PopupWindow_overlapAnchor, false);
        this.setBackgroundDrawable(object.getDrawable(R.styleable.PopupWindow_android_popupBackground));
        object.recycle();
        if (Build.VERSION.SDK_INT < 14) {
            AppCompatPopupWindow.wrapOnScrollChangedListener(this);
        }
    }

    private static void wrapOnScrollChangedListener(PopupWindow popupWindow) {
        try {
            Field field = PopupWindow.class.getDeclaredField("mAnchor");
            field.setAccessible(true);
            Field field2 = PopupWindow.class.getDeclaredField("mOnScrollChangedListener");
            field2.setAccessible(true);
            field2.set((Object)popupWindow, (Object)new AppCompatPopupWindow$1(field, popupWindow, (ViewTreeObserver.OnScrollChangedListener)field2.get((Object)popupWindow)));
            return;
        }
        catch (Exception var0_1) {
            Log.d((String)"AppCompatPopupWindow", (String)"Exception while installing workaround OnScrollChangedListener", (Throwable)var0_1);
            return;
        }
    }

    public void showAsDropDown(View view, int n2, int n3) {
        int n4 = n3;
        if (Build.VERSION.SDK_INT < 21) {
            n4 = n3;
            if (this.mOverlapAnchor) {
                n4 = n3 - view.getHeight();
            }
        }
        super.showAsDropDown(view, n2, n4);
    }

    @TargetApi(value=19)
    public void showAsDropDown(View view, int n2, int n3, int n4) {
        int n5 = n3;
        if (Build.VERSION.SDK_INT < 21) {
            n5 = n3;
            if (this.mOverlapAnchor) {
                n5 = n3 - view.getHeight();
            }
        }
        n3 = n2;
        if (Build.DEVICE.equals((Object)"HWGRA")) {
            int n6 = view.getWidth();
            n3 = n2;
            if ((Gravity.getAbsoluteGravity((int)n4, (int)view.getLayoutDirection()) & 7) == 5) {
                n3 = n2 - (this.getWidth() - n6);
            }
        }
        super.showAsDropDown(view, n3, n5, n4);
    }

    public void update(View view, int n2, int n3, int n4, int n5) {
        if (Build.VERSION.SDK_INT < 21 && this.mOverlapAnchor) {
            n3 -= view.getHeight();
        }
        super.update(view, n2, n3, n4, n5);
    }
}


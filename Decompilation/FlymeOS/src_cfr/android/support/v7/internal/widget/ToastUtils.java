/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Rect
 *  android.util.DisplayMetrics
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.widget.Toast
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.appcompat.R;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

public class ToastUtils {
    public static Toast showToast(View view, CharSequence charSequence) {
        return ToastUtils.showToast(view, charSequence, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Toast showToast(View view, CharSequence charSequence, int n2) {
        Context context = view.getContext();
        Object object = context.getResources().getDisplayMetrics();
        int n3 = object.widthPixels;
        Object object2 = object.heightPixels;
        int n4 = object2 / 2;
        view.getWindowVisibleDisplayFrame(new Rect());
        object = new int[2];
        view.getLocationOnScreen((int[])object);
        n3 = view.getWidth();
        Object object3 = view.getHeight();
        Object object4 = object[1];
        view = Toast.makeText((Context)context, (CharSequence)charSequence, (int)n2);
        charSequence = view.getView();
        charSequence.measure(View.MeasureSpec.makeMeasureSpec((int)context.getResources().getDimensionPixelSize(R.dimen.mz_alert_dialog_with_button_min_width), (int)Integer.MIN_VALUE), 0);
        n2 = charSequence.getMeasuredWidth();
        int n5 = context.getResources().getDimensionPixelSize(R.dimen.mz_action_button_min_height);
        int n6 = context.getResources().getDimensionPixelSize(R.dimen.status_bar_height);
        int n7 = context.getResources().getDimensionPixelSize(R.dimen.mz_toast_y_offset);
        if (object4 + object3 < n4) {
            object2 = object[1];
            view.setGravity(51, (int)(object[0] + n3 / 2 - n2 / 2), n7 + (object2 + object3 - n6));
        } else {
            object3 = object[1];
            view.setGravity(83, (int)(object[0] + n3 / 2 - n2 / 2), n7 + (object2 - object3 - n5));
        }
        view.show();
        return view;
    }
}


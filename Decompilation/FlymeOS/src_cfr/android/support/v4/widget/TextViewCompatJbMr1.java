/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.drawable.Drawable
 *  android.widget.TextView
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

class TextViewCompatJbMr1 {
    TextViewCompatJbMr1() {
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4, @Nullable Drawable drawable5) {
        boolean bl2 = textView.getLayoutDirection() == 1;
        Drawable drawable6 = bl2 ? drawable4 : drawable2;
        if (!bl2) {
            drawable2 = drawable4;
        }
        textView.setCompoundDrawables(drawable6, drawable3, drawable2, drawable5);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, int n2, int n3, int n4, int n5) {
        boolean bl2 = textView.getLayoutDirection() == 1;
        int n6 = bl2 ? n4 : n2;
        if (!bl2) {
            n2 = n4;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(n6, n3, n2, n5);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4, @Nullable Drawable drawable5) {
        boolean bl2 = textView.getLayoutDirection() == 1;
        Drawable drawable6 = bl2 ? drawable4 : drawable2;
        if (!bl2) {
            drawable2 = drawable4;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(drawable6, drawable3, drawable2, drawable5);
    }
}


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

class TextViewCompatJbMr2 {
    TextViewCompatJbMr2() {
    }

    public static void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4, @Nullable Drawable drawable5) {
        textView.setCompoundDrawablesRelative(drawable2, drawable3, drawable4, drawable5);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, int n2, int n3, int n4, int n5) {
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(n2, n3, n4, n5);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4, @Nullable Drawable drawable5) {
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable2, drawable3, drawable4, drawable5);
    }
}


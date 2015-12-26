/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.widget.TextView
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompatJbMr1;
import android.support.v4.widget.TextViewCompatJbMr2;
import android.widget.TextView;

public class TextViewCompat {
    static final TextViewCompatImpl IMPL;

    static {
        int n2 = Build.VERSION.SDK_INT;
        IMPL = n2 >= 18 ? new JbMr2TextViewCompatImpl() : (n2 >= 17 ? new JbMr1TextViewCompatImpl() : new BaseTextViewCompatImpl());
    }

    private TextViewCompat() {
    }

    public static void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4, @Nullable Drawable drawable5) {
        IMPL.setCompoundDrawablesRelative(textView, drawable2, drawable3, drawable4, drawable5);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, int n2, int n3, int n4, int n5) {
        IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, n2, n3, n4, n5);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4, @Nullable Drawable drawable5) {
        IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, drawable2, drawable3, drawable4, drawable5);
    }

    static class BaseTextViewCompatImpl
    implements TextViewCompatImpl {
        BaseTextViewCompatImpl() {
        }

        @Override
        public void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4, @Nullable Drawable drawable5) {
            textView.setCompoundDrawables(drawable2, drawable3, drawable4, drawable5);
        }

        @Override
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, int n2, int n3, int n4, int n5) {
            textView.setCompoundDrawablesWithIntrinsicBounds(n2, n3, n4, n5);
        }

        @Override
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4, @Nullable Drawable drawable5) {
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable2, drawable3, drawable4, drawable5);
        }
    }

    static class JbMr1TextViewCompatImpl
    extends BaseTextViewCompatImpl {
        JbMr1TextViewCompatImpl() {
        }

        @Override
        public void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4, @Nullable Drawable drawable5) {
            TextViewCompatJbMr1.setCompoundDrawablesRelative(textView, drawable2, drawable3, drawable4, drawable5);
        }

        @Override
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, int n2, int n3, int n4, int n5) {
            TextViewCompatJbMr1.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, n2, n3, n4, n5);
        }

        @Override
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4, @Nullable Drawable drawable5) {
            TextViewCompatJbMr1.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, drawable2, drawable3, drawable4, drawable5);
        }
    }

    static class JbMr2TextViewCompatImpl
    extends JbMr1TextViewCompatImpl {
        JbMr2TextViewCompatImpl() {
        }

        @Override
        public void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4, @Nullable Drawable drawable5) {
            TextViewCompatJbMr2.setCompoundDrawablesRelative(textView, drawable2, drawable3, drawable4, drawable5);
        }

        @Override
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, int n2, int n3, int n4, int n5) {
            TextViewCompatJbMr2.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, n2, n3, n4, n5);
        }

        @Override
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4, @Nullable Drawable drawable5) {
            TextViewCompatJbMr2.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, drawable2, drawable3, drawable4, drawable5);
        }
    }

    static interface TextViewCompatImpl {
        public void setCompoundDrawablesRelative(@NonNull TextView var1, @Nullable Drawable var2, @Nullable Drawable var3, @Nullable Drawable var4, @Nullable Drawable var5);

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView var1, int var2, int var3, int var4, int var5);

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView var1, @Nullable Drawable var2, @Nullable Drawable var3, @Nullable Drawable var4, @Nullable Drawable var5);
    }

}


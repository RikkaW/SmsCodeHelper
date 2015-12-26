/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.ColorStateList
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.drawable.Drawable
 *  java.lang.Object
 */
package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

public interface DrawableWrapper {
    public Drawable getWrappedDrawable();

    public void setTint(int var1);

    public void setTintList(ColorStateList var1);

    public void setTintMode(PorterDuff.Mode var1);

    public void setWrappedDrawable(Drawable var1);
}


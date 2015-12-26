/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.ColorStateList
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  java.lang.Object
 */
package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;

public interface TintableBackgroundView {
    @Nullable
    public ColorStateList getSupportBackgroundTintList();

    @Nullable
    public PorterDuff.Mode getSupportBackgroundTintMode();

    public void setSupportBackgroundTintList(@Nullable ColorStateList var1);

    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode var1);
}


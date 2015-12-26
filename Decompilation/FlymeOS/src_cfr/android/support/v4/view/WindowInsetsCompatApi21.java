/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Rect
 *  android.view.WindowInsets
 */
package android.support.v4.view;

import android.graphics.Rect;
import android.support.v4.view.WindowInsetsCompat;
import android.view.WindowInsets;

class WindowInsetsCompatApi21
extends WindowInsetsCompat {
    private final WindowInsets mSource;

    WindowInsetsCompatApi21(WindowInsets windowInsets) {
        this.mSource = windowInsets;
    }

    @Override
    public WindowInsetsCompat consumeStableInsets() {
        return new WindowInsetsCompatApi21(this.mSource.consumeStableInsets());
    }

    @Override
    public WindowInsetsCompat consumeSystemWindowInsets() {
        return new WindowInsetsCompatApi21(this.mSource.consumeSystemWindowInsets());
    }

    @Override
    public int getStableInsetBottom() {
        return this.mSource.getStableInsetBottom();
    }

    @Override
    public int getStableInsetLeft() {
        return this.mSource.getStableInsetLeft();
    }

    @Override
    public int getStableInsetRight() {
        return this.mSource.getStableInsetRight();
    }

    @Override
    public int getStableInsetTop() {
        return this.mSource.getStableInsetTop();
    }

    @Override
    public int getSystemWindowInsetBottom() {
        return this.mSource.getSystemWindowInsetBottom();
    }

    @Override
    public int getSystemWindowInsetLeft() {
        return this.mSource.getSystemWindowInsetLeft();
    }

    @Override
    public int getSystemWindowInsetRight() {
        return this.mSource.getSystemWindowInsetRight();
    }

    @Override
    public int getSystemWindowInsetTop() {
        return this.mSource.getSystemWindowInsetTop();
    }

    @Override
    public boolean hasInsets() {
        return this.mSource.hasInsets();
    }

    @Override
    public boolean hasStableInsets() {
        return this.mSource.hasStableInsets();
    }

    @Override
    public boolean hasSystemWindowInsets() {
        return this.mSource.hasSystemWindowInsets();
    }

    @Override
    public boolean isConsumed() {
        return this.mSource.isConsumed();
    }

    @Override
    public boolean isRound() {
        return this.mSource.isRound();
    }

    @Override
    public WindowInsetsCompat replaceSystemWindowInsets(int n2, int n3, int n4, int n5) {
        return new WindowInsetsCompatApi21(this.mSource.replaceSystemWindowInsets(n2, n3, n4, n5));
    }

    @Override
    public WindowInsetsCompat replaceSystemWindowInsets(Rect rect) {
        return new WindowInsetsCompatApi21(this.mSource.replaceSystemWindowInsets(rect));
    }

    WindowInsets unwrap() {
        return this.mSource;
    }
}


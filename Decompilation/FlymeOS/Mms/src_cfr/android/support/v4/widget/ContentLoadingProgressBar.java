/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.widget.ProgressBar
 *  java.lang.System
 */
package android.support.v4.widget;

import android.content.Context;
import android.support.v4.widget.ContentLoadingProgressBar$1;
import android.support.v4.widget.ContentLoadingProgressBar$2;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class ContentLoadingProgressBar
extends ProgressBar {
    private static final int MIN_DELAY = 500;
    private static final int MIN_SHOW_TIME = 500;
    private final Runnable mDelayedHide;
    private final Runnable mDelayedShow;
    private boolean mDismissed = false;
    private boolean mPostedHide = false;
    private boolean mPostedShow = false;
    private long mStartTime = -1;

    public ContentLoadingProgressBar(Context context) {
        this(context, null);
    }

    public ContentLoadingProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.mDelayedHide = new ContentLoadingProgressBar$1(this);
        this.mDelayedShow = new ContentLoadingProgressBar$2(this);
    }

    static /* synthetic */ boolean access$002(ContentLoadingProgressBar contentLoadingProgressBar, boolean bl2) {
        contentLoadingProgressBar.mPostedHide = bl2;
        return bl2;
    }

    static /* synthetic */ long access$102(ContentLoadingProgressBar contentLoadingProgressBar, long l2) {
        contentLoadingProgressBar.mStartTime = l2;
        return l2;
    }

    static /* synthetic */ boolean access$202(ContentLoadingProgressBar contentLoadingProgressBar, boolean bl2) {
        contentLoadingProgressBar.mPostedShow = bl2;
        return bl2;
    }

    static /* synthetic */ boolean access$300(ContentLoadingProgressBar contentLoadingProgressBar) {
        return contentLoadingProgressBar.mDismissed;
    }

    private void removeCallbacks() {
        this.removeCallbacks(this.mDelayedHide);
        this.removeCallbacks(this.mDelayedShow);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void hide() {
        this.mDismissed = true;
        this.removeCallbacks(this.mDelayedShow);
        long l2 = System.currentTimeMillis() - this.mStartTime;
        if (l2 >= 500 || this.mStartTime == -1) {
            this.setVisibility(8);
            return;
        } else {
            if (this.mPostedHide) return;
            {
                this.postDelayed(this.mDelayedHide, 500 - l2);
                this.mPostedHide = true;
                return;
            }
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.removeCallbacks();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.removeCallbacks();
    }

    public void show() {
        this.mStartTime = -1;
        this.mDismissed = false;
        this.removeCallbacks(this.mDelayedHide);
        if (!this.mPostedShow) {
            this.postDelayed(this.mDelayedShow, 500);
            this.mPostedShow = true;
        }
    }
}


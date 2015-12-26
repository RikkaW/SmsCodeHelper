/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.animation.Interpolator
 *  java.lang.Object
 *  java.util.ArrayList
 */
package android.support.v7.internal.view;

import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.internal.view.ViewPropertyAnimatorCompatSet$1;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

public class ViewPropertyAnimatorCompatSet {
    private final ArrayList<ViewPropertyAnimatorCompat> mAnimators;
    private long mDuration = -1;
    private Interpolator mInterpolator;
    private boolean mIsStarted;
    private ViewPropertyAnimatorListener mListener;
    private final ViewPropertyAnimatorListenerAdapter mProxyListener;

    public ViewPropertyAnimatorCompatSet() {
        this.mProxyListener = new ViewPropertyAnimatorCompatSet$1(this);
        this.mAnimators = new ArrayList();
    }

    static /* synthetic */ ViewPropertyAnimatorListener access$000(ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet) {
        return viewPropertyAnimatorCompatSet.mListener;
    }

    static /* synthetic */ void access$100(ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet) {
        viewPropertyAnimatorCompatSet.onAnimationsEnded();
    }

    static /* synthetic */ ArrayList access$200(ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet) {
        return viewPropertyAnimatorCompatSet.mAnimators;
    }

    private void onAnimationsEnded() {
        this.mIsStarted = false;
    }

    public void cancel() {
        if (!this.mIsStarted) {
            return;
        }
        Iterator iterator = this.mAnimators.iterator();
        while (iterator.hasNext()) {
            ((ViewPropertyAnimatorCompat)iterator.next()).cancel();
        }
        this.mIsStarted = false;
    }

    public ViewPropertyAnimatorCompatSet play(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        if (!this.mIsStarted) {
            this.mAnimators.add((Object)viewPropertyAnimatorCompat);
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet setDuration(long l2) {
        if (!this.mIsStarted) {
            this.mDuration = l2;
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet setInterpolator(Interpolator interpolator2) {
        if (!this.mIsStarted) {
            this.mInterpolator = interpolator2;
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet setListener(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (!this.mIsStarted) {
            this.mListener = viewPropertyAnimatorListener;
        }
        return this;
    }

    public void start() {
        if (this.mIsStarted) {
            return;
        }
        for (ViewPropertyAnimatorCompat viewPropertyAnimatorCompat : this.mAnimators) {
            if (this.mDuration >= 0) {
                viewPropertyAnimatorCompat.setDuration(this.mDuration);
            }
            if (this.mInterpolator != null) {
                viewPropertyAnimatorCompat.setInterpolator(this.mInterpolator);
            }
            if (this.mListener != null) {
                viewPropertyAnimatorCompat.setListener(this.mProxyListener);
            }
            viewPropertyAnimatorCompat.start();
        }
        this.mIsStarted = true;
    }
}


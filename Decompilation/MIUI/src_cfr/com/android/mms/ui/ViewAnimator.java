/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.animation.ObjectAnimator
 *  android.animation.TimeInterpolator
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.view.View
 *  android.view.animation.Interpolator
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.concurrent.atomic.AtomicBoolean
 */
package com.android.mms.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;
import com.android.mms.ui.ConversationListItem;
import java.util.concurrent.atomic.AtomicBoolean;

public class ViewAnimator {
    private Animator.AnimatorListener mAnimatorListener;
    private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener;
    private AtomicBoolean mIsRunning = new AtomicBoolean(false);
    private int mPosition;
    private final View mView;

    public ViewAnimator(View view) {
        this.mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener(){

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ViewAnimator.this.mPosition = Integer.parseInt((String)valueAnimator.getAnimatedValue("EXTRA_POSITION").toString());
                ViewAnimator.this.mView.invalidate();
            }
        };
        this.mAnimatorListener = new Animator.AnimatorListener(){

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                ViewAnimator.this.mPosition = 0;
                ViewAnimator.this.mIsRunning.set(false);
                animator.removeAllListeners();
                ViewAnimator.this.mView.invalidate();
                if (ViewAnimator.this.mView instanceof ConversationListItem) {
                    ((ConversationListItem)ViewAnimator.this.mView).onAnimationDone();
                }
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }
        };
        this.mView = view;
    }

    public /* varargs */ void animate(Interpolator interpolator, long l, int ... object) {
        object = (Object)ObjectAnimator.ofInt((Object)this, (String)"EXTRA_POSITION", (int[])object);
        object.setDuration(l);
        object.setInterpolator((TimeInterpolator)interpolator);
        object.addUpdateListener(this.mAnimatorUpdateListener);
        object.addListener(this.mAnimatorListener);
        object.start();
        this.mIsRunning.set(true);
    }

    public int getPosition() {
        return this.mPosition;
    }

    public boolean isRunning() {
        return this.mIsRunning.get();
    }

}


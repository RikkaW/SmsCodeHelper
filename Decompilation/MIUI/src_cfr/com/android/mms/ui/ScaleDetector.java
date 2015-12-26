/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.util.FloatMath
 *  android.util.Log
 *  android.view.MotionEvent
 *  android.view.Window
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.ui;

import android.app.Activity;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import com.android.mms.ui.MessageUtils;

public class ScaleDetector {
    private static String LOGTAG = "ScaleDetector";
    private int mActiveId0;
    private int mActiveId1;
    private MotionEvent mCurrEvent;
    private float mCurrFingerDiffX;
    private float mCurrFingerDiffY;
    private float mCurrLen;
    private float mCurrPressure;
    private boolean mGestureInProgress;
    private boolean mInvalidGesture;
    private OnScaleListener mListener;
    private MotionEvent mPrevEvent;
    private float mPrevFingerDiffX;
    private float mPrevFingerDiffY;
    private float mPrevLen;
    private float mPrevPressure;
    private float mScaleFactor;

    public ScaleDetector(OnScaleListener onScaleListener) {
        this.mListener = onScaleListener;
        this.reset();
    }

    private void log(String string2) {
        Log.e((String)LOGTAG, (String)string2);
    }

    private void reset() {
        if (this.mPrevEvent != null) {
            this.mPrevEvent.recycle();
            this.mPrevEvent = null;
        }
        if (this.mCurrEvent != null) {
            this.mCurrEvent.recycle();
            this.mCurrEvent = null;
        }
        this.mActiveId0 = -1;
        this.mActiveId1 = -1;
        this.mGestureInProgress = false;
        this.mInvalidGesture = false;
    }

    private void setContext(MotionEvent motionEvent) {
        if (this.mCurrEvent != null) {
            this.mCurrEvent.recycle();
        }
        this.mCurrEvent = MotionEvent.obtain((MotionEvent)motionEvent);
        this.mCurrLen = -1.0f;
        this.mPrevLen = -1.0f;
        this.mScaleFactor = -1.0f;
        MotionEvent motionEvent2 = this.mPrevEvent;
        int n = motionEvent2.findPointerIndex(this.mActiveId0);
        int n2 = motionEvent2.findPointerIndex(this.mActiveId1);
        int n3 = motionEvent.findPointerIndex(this.mActiveId0);
        int n4 = motionEvent.findPointerIndex(this.mActiveId1);
        if (n < 0 || n2 < 0 || n3 < 0 || n4 < 0) {
            this.mInvalidGesture = true;
            if (this.mGestureInProgress) {
                this.mListener.onScaleEnd(this);
            }
            return;
        }
        float f2 = motionEvent2.getX(n);
        float f3 = motionEvent2.getY(n);
        float f4 = motionEvent2.getX(n2);
        float f5 = motionEvent2.getY(n2);
        float f6 = motionEvent.getX(n3);
        float f7 = motionEvent.getY(n3);
        float f8 = motionEvent.getX(n4);
        float f9 = motionEvent.getY(n4);
        this.mPrevFingerDiffX = f4 - f2;
        this.mPrevFingerDiffY = f5 - f3;
        this.mCurrFingerDiffX = f8 - f6;
        this.mCurrFingerDiffY = f9 - f7;
        this.mCurrPressure = motionEvent.getPressure(n3) + motionEvent.getPressure(n4);
        this.mPrevPressure = motionEvent2.getPressure(n) + motionEvent2.getPressure(n2);
    }

    public float getCurrentSpan() {
        if (this.mCurrLen == -1.0f) {
            float f2 = this.mCurrFingerDiffX;
            float f3 = this.mCurrFingerDiffY;
            this.mCurrLen = FloatMath.sqrt((float)(f2 * f2 + f3 * f3));
        }
        return this.mCurrLen;
    }

    public float getPreviousSpan() {
        if (this.mPrevLen == -1.0f) {
            float f2 = this.mPrevFingerDiffX;
            float f3 = this.mPrevFingerDiffY;
            this.mPrevLen = FloatMath.sqrt((float)(f2 * f2 + f3 * f3));
        }
        return this.mPrevLen;
    }

    public float getScaleFactor() {
        if (this.mScaleFactor == -1.0f) {
            this.mScaleFactor = this.getCurrentSpan() / this.getPreviousSpan();
        }
        return this.mScaleFactor;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean onTouchEvent(Activity var1_1, MotionEvent var2_2) {
        var3_3 = var2_2.getActionMasked();
        if (var3_3 == 0) {
            this.reset();
        }
        switch (var3_3) {
            case 0: {
                this.mActiveId0 = var2_2.getPointerId(0);
                this.log("ACTION_DOWN: count = " + var2_2.getPointerCount());
                ** break;
            }
            case 5: {
                var3_3 = var2_2.getPointerCount();
                var4_4 = var2_2.getPointerId(var2_2.getActionIndex());
                this.log("ACTION_POINTER_DOWN: count = " + var3_3 + ", actionId = " + var4_4);
                if (var3_3 == 2) {
                    this.mActiveId0 = var2_2.getPointerId(0);
                    this.mActiveId1 = var2_2.getPointerId(1);
                    this.mPrevEvent = MotionEvent.obtain((MotionEvent)var2_2);
                    this.setContext(var2_2);
                    if (this.mListener != null) {
                        this.mGestureInProgress = this.mListener.onScaleStart(this);
                        if (this.mGestureInProgress && var1_1 != null) {
                            var5_6 = MotionEvent.obtain((long)0, (long)0, (int)3, (float)0.0f, (float)0.0f, (int)0);
                            var1_1.getWindow().superDispatchTouchEvent(var5_6);
                            var5_6.recycle();
                        }
                    }
                    this.mInvalidGesture = false;
                }
                if (var3_3 > 2 && !this.mInvalidGesture) {
                    this.mInvalidGesture = true;
                    this.setContext(var2_2);
                    if (this.mGestureInProgress && this.mListener != null) {
                        this.mListener.onScaleEnd(this);
                        ** break;
                    }
                }
                ** GOTO lbl54
            }
            case 2: {
                if (this.mGestureInProgress && !this.mInvalidGesture) {
                    this.setContext(var2_2);
                    if (this.mCurrPressure / this.mPrevPressure > 0.67f && this.mListener.onScale(this)) {
                        this.mPrevEvent.recycle();
                        this.mPrevEvent = MotionEvent.obtain((MotionEvent)var2_2);
                        ** break;
                    }
                }
                ** GOTO lbl54
            }
            case 6: {
                var3_3 = var2_2.getPointerCount();
                var4_5 = var2_2.getPointerId(var2_2.getActionIndex());
                this.log("ACTION_POINTER_UP, count = " + var3_3 + ", ActionId = " + var4_5);
                if (this.mGestureInProgress && var3_3 == 2 && !this.mInvalidGesture) {
                    this.setContext(var2_2);
                    if (this.mListener != null) {
                        this.mListener.onScaleEnd(this);
                    }
                    this.mInvalidGesture = true;
                    ** break;
                }
                ** GOTO lbl54
            }
            case 1: {
                this.log("ACTION_UP");
                this.reset();
            }
lbl54: // 9 sources:
            default: {
                ** GOTO lbl59
            }
            case 3: 
        }
        this.log("ACTION_CANCEL");
        this.reset();
lbl59: // 2 sources:
        if (this.mGestureInProgress != false) return this.mGestureInProgress;
        this.log("return value is false, action = " + var2_2.getActionMasked());
        return this.mGestureInProgress;
    }

    public static interface OnScaleListener {
        public boolean onScale(ScaleDetector var1);

        public void onScaleEnd(ScaleDetector var1);

        public boolean onScaleStart(ScaleDetector var1);
    }

    public static abstract class SimpleOnScaleListener
    implements OnScaleListener {
        private float mMaxTextSize;
        private float mMinTextSize;
        private float mTextSize = 0.0f;

        public SimpleOnScaleListener(float f2, float f3) {
            this.mMinTextSize = f2;
            this.mMaxTextSize = f3;
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public boolean onScale(ScaleDetector scaleDetector) {
            float f2;
            float f3 = this.mTextSize * scaleDetector.getScaleFactor();
            if (this.mTextSize > 0.0f && Math.abs((float)(f3 - this.mTextSize)) < 0.2f) {
                return false;
            }
            if (f3 < this.mMinTextSize) {
                f2 = this.mMinTextSize;
            } else {
                f2 = f3;
                if (f3 > this.mMaxTextSize) {
                    f2 = this.mMaxTextSize;
                }
            }
            this.mTextSize = f2;
            this.performChangeText(f2);
            return true;
        }

        @Override
        public void onScaleEnd(ScaleDetector scaleDetector) {
            MessageUtils.setTextSize(this.mTextSize);
        }

        @Override
        public boolean onScaleStart(ScaleDetector scaleDetector) {
            return true;
        }

        protected abstract void performChangeText(float var1);

        public void setTextSize(float f2) {
            this.mTextSize = f2;
        }
    }

}


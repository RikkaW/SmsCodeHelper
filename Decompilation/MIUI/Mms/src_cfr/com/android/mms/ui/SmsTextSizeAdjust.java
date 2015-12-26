/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.ContextWrapper
 *  android.content.res.Configuration
 *  android.content.res.MiuiConfiguration
 *  android.content.res.Resources
 *  android.view.MotionEvent
 *  miui.R
 *  miui.R$dimen
 *  miui.os.Build
 */
package com.android.mms.ui;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.MiuiConfiguration;
import android.content.res.Resources;
import android.view.MotionEvent;
import com.android.mms.MmsApp;
import com.android.mms.ui.ISmsTextSizeAdjustHost;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.ScaleDetector;
import miui.R;
import miui.os.Build;

public class SmsTextSizeAdjust
extends ContextWrapper {
    private static SmsTextSizeAdjust mInstance;
    private Activity mActivity;
    private float mDefaultTextSize;
    private ISmsTextSizeAdjustHost mHost;
    private ScaleDetector.SimpleOnScaleListener mListener;
    private int mPreviousScaleMode;
    private ScaleDetector mScaleDetector;
    private float mTextSize;

    private SmsTextSizeAdjust() {
        super(MmsApp.getApp().getApplicationContext());
        if (Build.IS_CM_CUSTOMIZATION) {
            this.mListener = new SmsOnScaleListener(this.getResources().getDimension(2131427389), this.getResources().getDimension(2131427390));
            this.mScaleDetector = new ScaleDetector(this.mListener);
        }
        this.mPreviousScaleMode = MiuiConfiguration.getScaleMode();
        this.mDefaultTextSize = this.getResources().getDimension(2131427413);
        this.setTextSizeBySysFS();
    }

    public static void clear(ISmsTextSizeAdjustHost iSmsTextSizeAdjustHost) {
        if (mInstance != null && SmsTextSizeAdjust.mInstance.mHost == iSmsTextSizeAdjustHost) {
            mInstance.unInit();
        }
    }

    public static SmsTextSizeAdjust getInstance() {
        if (mInstance == null) {
            mInstance = new SmsTextSizeAdjust();
        }
        return mInstance;
    }

    public static boolean hasInstance() {
        if (mInstance != null) {
            return true;
        }
        return false;
    }

    private void setTextSizeBySysFS() {
        float f2;
        if (MiuiConfiguration.getScaleMode() == 11) {
            MessageUtils.setTextSize(Float.MAX_VALUE);
            return;
        }
        if (MiuiConfiguration.getScaleMode() != 1) {
            MessageUtils.setTextSize(MmsApp.getApp().getResources().getDimension(R.dimen.normal_text_size));
            return;
        }
        float f3 = f2 = this.getResources().getConfiguration().fontScale;
        if (f2 == 0.0f) {
            f3 = 1.0f;
        }
        MessageUtils.setTextSize(this.mDefaultTextSize * f3);
    }

    private void unInit() {
        this.mHost = null;
        this.mActivity = null;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mScaleDetector != null && this.mActivity != null) {
            return this.mScaleDetector.onTouchEvent(this.mActivity, motionEvent);
        }
        return false;
    }

    public float getTextSize() {
        return this.mTextSize;
    }

    public void init(ISmsTextSizeAdjustHost iSmsTextSizeAdjustHost, Activity activity) {
        this.mHost = iSmsTextSizeAdjustHost;
        this.mActivity = activity;
        this.mTextSize = 0.0f;
        if (this.mListener != null) {
            this.mListener.setTextSize(this.mTextSize);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        int n = MiuiConfiguration.getScaleMode();
        if (n == this.mPreviousScaleMode) {
            return;
        }
        this.mPreviousScaleMode = n;
        this.setTextSizeBySysFS();
    }

    public void refresh() {
        float f2 = MessageUtils.getTextSize(this.mDefaultTextSize);
        if (this.mTextSize != f2) {
            this.mTextSize = f2;
            if (this.mListener != null) {
                this.mListener.setTextSize(this.mTextSize);
            }
            if (this.mHost != null) {
                this.mHost.setTextSize(this.mTextSize);
            }
        }
    }

    public class SmsOnScaleListener
    extends ScaleDetector.SimpleOnScaleListener {
        public SmsOnScaleListener(float f2, float f3) {
            super(f2, f3);
        }

        @Override
        protected void performChangeText(float f2) {
            if (SmsTextSizeAdjust.this.mTextSize != f2) {
                SmsTextSizeAdjust.this.mTextSize = f2;
                if (SmsTextSizeAdjust.this.mHost != null) {
                    SmsTextSizeAdjust.this.mHost.setTextSize(f2);
                }
            }
        }
    }

}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  java.lang.Object
 *  java.lang.System
 */
package com.android.mms.ui;

import android.os.Handler;

public class LinearAnimator {
    public static void start(final float f2, final float f3, final long l, final FrameHandler frameHandler) {
        final long l2 = System.currentTimeMillis();
        final Handler handler = new Handler();
        handler.post(new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                long l3 = System.currentTimeMillis();
                float f22 = l == 0 ? f3 : f2 + (f3 - f2) * (float)(l3 - l2) / (float)l;
                if (f2 <= f3 && f22 >= f3 || f2 > f3 && f22 <= f3) {
                    frameHandler.onFrame(f3);
                    frameHandler.onEnd();
                    return;
                }
                frameHandler.onFrame(f22);
                handler.post((Runnable)this);
            }
        });
    }

    public static interface FrameHandler {
        public void onEnd();

        public void onFrame(float var1);
    }

}


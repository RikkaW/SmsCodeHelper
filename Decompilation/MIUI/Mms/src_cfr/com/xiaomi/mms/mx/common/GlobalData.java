/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Matrix
 *  android.util.DisplayMetrics
 *  java.lang.Object
 */
package com.xiaomi.mms.mx.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.util.DisplayMetrics;

public class GlobalData {
    public static int DEFAULT_PIC_THUMB_HEIGHT;
    public static int DEFAULT_PIC_THUMB_WIDTH;
    public static int SCREEN_HEIGHT;
    public static int SCREEN_WIDTH;
    public static Context sAppContext;
    public static float screenDensity;
    public static DisplayMetrics screenMatrix;
    public static float screenRate;
    public static Matrix screenRateMatrix;

    static {
        screenRate = 0.0f;
        screenDensity = 0.0f;
        SCREEN_WIDTH = 0;
        SCREEN_HEIGHT = 0;
        DEFAULT_PIC_THUMB_WIDTH = 0;
        DEFAULT_PIC_THUMB_HEIGHT = 0;
    }

    public static Context app() {
        return sAppContext;
    }

    private static void calculateScreenRate(Context context) {
        screenMatrix = context.getResources().getDisplayMetrics();
        SCREEN_WIDTH = GlobalData.screenMatrix.widthPixels;
        SCREEN_HEIGHT = GlobalData.screenMatrix.heightPixels;
        screenRate = (float)GlobalData.screenMatrix.densityDpi / 240.0f;
        screenDensity = GlobalData.screenMatrix.density;
        screenRateMatrix = new Matrix();
        screenRateMatrix.setScale(screenRate, screenRate);
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int n = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = n;
        }
        context = GlobalData.app().getResources();
        DEFAULT_PIC_THUMB_WIDTH = context.getDimensionPixelSize(2131427446);
        DEFAULT_PIC_THUMB_HEIGHT = context.getDimensionPixelSize(2131427446);
    }

    public static void initialize(Context context) {
        sAppContext = context;
        GlobalData.calculateScreenRate(context);
    }
}


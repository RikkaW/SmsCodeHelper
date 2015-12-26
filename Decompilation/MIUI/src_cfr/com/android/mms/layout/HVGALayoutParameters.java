/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.layout;

import com.android.mms.layout.LayoutParameters;

public class HVGALayoutParameters
implements LayoutParameters {
    private int mType = -1;

    public HVGALayoutParameters(int n) {
        if (n != 10 && n != 11) {
            throw new IllegalArgumentException("Bad layout type detected: " + n);
        }
        this.mType = n;
    }

    @Override
    public int getHeight() {
        if (this.mType == 10) {
            return 320;
        }
        return 480;
    }

    @Override
    public int getImageHeight() {
        if (this.mType == 10) {
            return 240;
        }
        return 320;
    }

    @Override
    public int getTextHeight() {
        if (this.mType == 10) {
            return 80;
        }
        return 160;
    }

    @Override
    public int getWidth() {
        if (this.mType == 10) {
            return 480;
        }
        return 320;
    }
}


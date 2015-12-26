/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 */
package com.android.mms.model;

import com.android.mms.model.Model;

public class RegionModel
extends Model {
    private String mBackgroundColor;
    private String mFit;
    private int mHeight;
    private int mLeft;
    private final String mRegionId;
    private int mTop;
    private int mWidth;

    public RegionModel(String string, int n, int n2, int n3, int n4) {
        this(string, "meet", n, n2, n3, n4);
    }

    public RegionModel(String string, String string2, int n, int n2, int n3, int n4) {
        this(string, string2, n, n2, n3, n4, null);
    }

    public RegionModel(String string, String string2, int n, int n2, int n3, int n4, String string3) {
        this.mRegionId = string;
        this.mFit = string2;
        this.mLeft = n;
        this.mTop = n2;
        this.mWidth = n3;
        this.mHeight = n4;
        this.mBackgroundColor = string3;
    }

    public String getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public String getFit() {
        return this.mFit;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getLeft() {
        return this.mLeft;
    }

    public String getRegionId() {
        return this.mRegionId;
    }

    public int getTop() {
        return this.mTop;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public void setTop(int n) {
        this.mTop = n;
        this.notifyModelChanged(true);
    }
}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  miui.os.Build
 */
package com.android.mms.model;

import android.util.Log;
import com.android.mms.layout.LayoutManager;
import com.android.mms.layout.LayoutParameters;
import com.android.mms.model.IModelChangedObserver;
import com.android.mms.model.Model;
import com.android.mms.model.RegionModel;
import java.util.ArrayList;
import java.util.Iterator;
import miui.os.Build;

public class LayoutModel
extends Model {
    private RegionModel mImageRegion;
    private LayoutParameters mLayoutParams = LayoutManager.getInstance().getLayoutParameters();
    private int mLayoutType = 0;
    private ArrayList<RegionModel> mNonStdRegions;
    private RegionModel mRootLayout;
    private RegionModel mTextRegion;

    public LayoutModel() {
        this.createDefaultRootLayout();
        this.createDefaultImageRegion();
        this.createDefaultTextRegion();
    }

    public LayoutModel(RegionModel object, ArrayList<RegionModel> object2) {
        this.mRootLayout = object;
        this.mNonStdRegions = new ArrayList();
        object = object2.iterator();
        while (object.hasNext()) {
            object2 = (RegionModel)object.next();
            String string = object2.getRegionId();
            if (string.equals((Object)"Image")) {
                this.mImageRegion = object2;
                continue;
            }
            if (string.equals((Object)"Text")) {
                this.mTextRegion = object2;
                continue;
            }
            this.mNonStdRegions.add(object2);
        }
        this.validateLayouts();
    }

    private void createDefaultImageRegion() {
        if (this.mRootLayout == null) {
            throw new IllegalStateException("Root-Layout uninitialized.");
        }
        this.mImageRegion = new RegionModel("Image", 0, 0, this.mRootLayout.getWidth(), this.mLayoutParams.getImageHeight());
    }

    private void createDefaultRootLayout() {
        this.mRootLayout = new RegionModel(null, 0, 0, this.mLayoutParams.getWidth(), this.mLayoutParams.getHeight());
    }

    private void createDefaultTextRegion() {
        if (this.mRootLayout == null) {
            throw new IllegalStateException("Root-Layout uninitialized.");
        }
        this.mTextRegion = new RegionModel("Text", 0, this.mLayoutParams.getImageHeight(), this.mRootLayout.getWidth(), this.mLayoutParams.getTextHeight());
    }

    private void validateLayouts() {
        if (this.mRootLayout == null) {
            this.createDefaultRootLayout();
        }
        if (this.mImageRegion == null) {
            this.createDefaultImageRegion();
        }
        if (this.mTextRegion == null) {
            this.createDefaultTextRegion();
        }
        this.valideteLayoutType();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void valideteLayoutType() {
        if (!Build.IS_CM_CUSTOMIZATION && !Build.IS_CU_CUSTOMIZATION) return;
        if (this.mImageRegion.getTop() > this.mTextRegion.getTop()) {
            this.mLayoutType = 1;
            return;
        }
        this.mLayoutType = 0;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void changeTo(int n) {
        if (this.mRootLayout == null) {
            throw new IllegalStateException("Root-Layout uninitialized.");
        }
        if (this.mLayoutParams == null) {
            this.mLayoutParams = LayoutManager.getInstance().getLayoutParameters();
        }
        if (this.mLayoutType == n) return;
        switch (n) {
            default: {
                Log.w((String)"Mms/slideshow", (String)("Unknown layout type: " + n));
                return;
            }
            case 0: {
                this.mImageRegion.setTop(0);
                this.mTextRegion.setTop(this.mLayoutParams.getImageHeight());
                this.mLayoutType = n;
                this.notifyModelChanged(true);
                return;
            }
            case 1: 
        }
        this.mImageRegion.setTop(this.mLayoutParams.getTextHeight());
        this.mTextRegion.setTop(0);
        this.mLayoutType = n;
        this.notifyModelChanged(true);
    }

    public RegionModel findRegionById(String string) {
        if ("Image".equals((Object)string)) {
            return this.mImageRegion;
        }
        if ("Text".equals((Object)string)) {
            return this.mTextRegion;
        }
        for (RegionModel regionModel : this.mNonStdRegions) {
            if (!regionModel.getRegionId().equals((Object)string)) continue;
            return regionModel;
        }
        return null;
    }

    public String getBackgroundColor() {
        return this.mRootLayout.getBackgroundColor();
    }

    public RegionModel getImageRegion() {
        return this.mImageRegion;
    }

    public int getLayoutHeight() {
        return this.mRootLayout.getHeight();
    }

    public int getLayoutWidth() {
        return this.mRootLayout.getWidth();
    }

    public ArrayList<RegionModel> getRegions() {
        ArrayList arrayList = new ArrayList();
        if (this.mImageRegion != null) {
            arrayList.add((Object)this.mImageRegion);
        }
        if (this.mTextRegion != null) {
            arrayList.add((Object)this.mTextRegion);
        }
        return arrayList;
    }

    public RegionModel getTextRegion() {
        return this.mTextRegion;
    }

    @Override
    protected void registerModelChangedObserverInDescendants(IModelChangedObserver iModelChangedObserver) {
        if (this.mRootLayout != null) {
            this.mRootLayout.registerModelChangedObserver(iModelChangedObserver);
        }
        if (this.mImageRegion != null) {
            this.mImageRegion.registerModelChangedObserver(iModelChangedObserver);
        }
        if (this.mTextRegion != null) {
            this.mTextRegion.registerModelChangedObserver(iModelChangedObserver);
        }
    }

    @Override
    protected void unregisterAllModelChangedObserversInDescendants() {
        if (this.mRootLayout != null) {
            this.mRootLayout.unregisterAllModelChangedObservers();
        }
        if (this.mImageRegion != null) {
            this.mImageRegion.unregisterAllModelChangedObservers();
        }
        if (this.mTextRegion != null) {
            this.mTextRegion.unregisterAllModelChangedObservers();
        }
    }

    @Override
    protected void unregisterModelChangedObserverInDescendants(IModelChangedObserver iModelChangedObserver) {
        if (this.mRootLayout != null) {
            this.mRootLayout.unregisterModelChangedObserver(iModelChangedObserver);
        }
        if (this.mImageRegion != null) {
            this.mImageRegion.unregisterModelChangedObserver(iModelChangedObserver);
        }
        if (this.mTextRegion != null) {
            this.mTextRegion.unregisterModelChangedObserver(iModelChangedObserver);
        }
    }
}


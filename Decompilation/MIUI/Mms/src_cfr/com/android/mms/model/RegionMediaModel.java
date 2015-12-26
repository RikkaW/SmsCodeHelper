/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.Uri
 *  com.google.android.mms.MmsException
 *  java.lang.String
 */
package com.android.mms.model;

import android.content.Context;
import android.net.Uri;
import com.android.mms.model.MediaModel;
import com.android.mms.model.RegionModel;
import com.google.android.mms.MmsException;

public abstract class RegionMediaModel
extends MediaModel {
    protected RegionModel mRegion;
    protected boolean mVisible = true;

    public RegionMediaModel(Context context, String string, Uri uri, RegionModel regionModel) throws MmsException {
        this(context, string, null, null, uri, regionModel);
    }

    public RegionMediaModel(Context context, String string, String string2, String string3, Uri uri, RegionModel regionModel) throws MmsException {
        super(context, string, string2, string3, uri);
        this.mRegion = regionModel;
    }

    public RegionMediaModel(Context context, String string, String string2, String string3, byte[] arrby, RegionModel regionModel) {
        super(context, string, string2, string3, arrby);
        this.mRegion = regionModel;
    }

    public RegionModel getRegion() {
        return this.mRegion;
    }

    public boolean isVisible() {
        return this.mVisible;
    }
}


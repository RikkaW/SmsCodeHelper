/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.android.mms.model;

import com.android.mms.model.CarrierContentRestriction;
import com.android.mms.model.ContentRestriction;

public class ContentRestrictionFactory {
    private static ContentRestriction sContentRestriction;

    private ContentRestrictionFactory() {
    }

    public static ContentRestriction getContentRestriction() {
        if (sContentRestriction == null) {
            sContentRestriction = new CarrierContentRestriction();
        }
        return sContentRestriction;
    }
}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.model;

import android.content.ContentResolver;
import com.android.mms.ContentRestrictionException;

public interface ContentRestriction {
    public void checkAudioContentType(String var1) throws ContentRestrictionException;

    public void checkImageContentType(String var1) throws ContentRestrictionException;

    public void checkMessageSize(int var1, int var2, ContentResolver var3) throws ContentRestrictionException;

    public void checkVideoContentType(String var1) throws ContentRestrictionException;
}


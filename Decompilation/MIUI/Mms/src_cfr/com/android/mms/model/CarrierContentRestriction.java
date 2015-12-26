/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  com.google.android.mms.ContentType
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.android.mms.model;

import android.content.ContentResolver;
import com.android.mms.ContentRestrictionException;
import com.android.mms.ExceedMessageSizeException;
import com.android.mms.MmsConfig;
import com.android.mms.UnsupportContentTypeException;
import com.android.mms.model.ContentRestriction;
import com.google.android.mms.ContentType;
import java.util.ArrayList;

public class CarrierContentRestriction
implements ContentRestriction {
    private static final ArrayList<String> sSupportedAudioTypes;
    private static final ArrayList<String> sSupportedImageTypes;
    private static final ArrayList<String> sSupportedVideoTypes;

    static {
        sSupportedImageTypes = ContentType.getImageTypes();
        sSupportedAudioTypes = ContentType.getAudioTypes();
        sSupportedVideoTypes = ContentType.getVideoTypes();
    }

    @Override
    public void checkAudioContentType(String string) throws ContentRestrictionException {
        if (string == null) {
            throw new ContentRestrictionException("Null content type to be check");
        }
        if (!sSupportedAudioTypes.contains((Object)string)) {
            throw new UnsupportContentTypeException("Unsupported audio content type : " + string);
        }
    }

    @Override
    public void checkImageContentType(String string) throws ContentRestrictionException {
        if (string == null) {
            throw new ContentRestrictionException("Null content type to be check");
        }
        if (!sSupportedImageTypes.contains((Object)string)) {
            throw new UnsupportContentTypeException("Unsupported image content type : " + string);
        }
    }

    @Override
    public void checkMessageSize(int n, int n2, ContentResolver contentResolver) throws ContentRestrictionException {
        if (n < 0 || n2 < 0) {
            throw new ContentRestrictionException("Negative message size or increase size");
        }
        if ((n += n2) < 0 || n > MmsConfig.getMaxMessageSize()) {
            throw new ExceedMessageSizeException("Exceed message size limitation");
        }
    }

    @Override
    public void checkVideoContentType(String string) throws ContentRestrictionException {
        if (string == null) {
            throw new ContentRestrictionException("Null content type to be check");
        }
        if (!sSupportedVideoTypes.contains((Object)string)) {
            throw new UnsupportContentTypeException("Unsupported video content type : " + string);
        }
    }
}


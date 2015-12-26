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
import com.android.mms.model.FileAttachmentModel;
import com.google.android.mms.MmsException;

public class VCardModel
extends FileAttachmentModel {
    public VCardModel(Context context, Uri uri) throws MmsException {
        super(context, uri, "text/x-vCard");
    }

    public VCardModel(Context context, String string, String string2, Uri uri) throws MmsException {
        super(context, string, string2, uri);
    }
}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 */
package com.android.mms;

import com.android.mms.ContentRestrictionException;

public final class ExceedMessageSizeException
extends ContentRestrictionException {
    private static final long serialVersionUID = 6647713416796190850L;

    public ExceedMessageSizeException() {
    }

    public ExceedMessageSizeException(String string) {
        super(string);
    }
}


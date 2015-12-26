/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  com.google.android.mms.MmsException
 *  java.lang.Object
 */
package com.android.mms.transaction;

import com.google.android.mms.MmsException;

public interface MessageSender {
    public boolean sendMessage() throws MmsException;
}


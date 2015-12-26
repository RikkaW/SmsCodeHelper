/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 *  java.lang.Object
 */
package com.xiaomi.mms.transaction;

import android.net.Uri;

public interface MxMmsTransactionHandler {
    public Uri handleReceiveMxMms(Uri var1);

    public Uri handleSendMxMms(Uri var1);

    public void setSendByMxV2(boolean var1);
}


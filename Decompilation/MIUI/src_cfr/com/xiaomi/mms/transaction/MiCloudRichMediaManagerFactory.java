/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  com.xiaomi.accountsdk.account.data.ExtendedAuthToken
 *  com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.transaction;

import android.content.Context;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager;

public interface MiCloudRichMediaManagerFactory {
    public MiCloudRichMediaManager getMiCloudRichMediaManager(Context var1, String var2, ExtendedAuthToken var3, String var4);

    public MiCloudRichMediaManager getOtherIDCMiCloudRichMediaManager(Context var1, String var2, ExtendedAuthToken var3, String var4);
}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  java.lang.Object
 *  java.lang.String
 *  miui.cloud.sync.providers.SmsSyncInfoProvider
 *  miui.widget.MiCloudStateView
 *  miui.widget.MiCloudStateView$ISyncInfoProvider
 */
package com.android.mms.util;

import android.content.Context;
import android.content.res.Resources;
import miui.cloud.sync.providers.SmsSyncInfoProvider;
import miui.widget.MiCloudStateView;

public class SmsSyncInfoProviderWrapper
implements MiCloudStateView.ISyncInfoProvider {
    SmsSyncInfoProvider mSyncInfoProvider = new SmsSyncInfoProvider();

    public String getAuthority() {
        return "sms";
    }

    public int[] getUnsyncedCount(Context context) {
        return new int[]{this.mSyncInfoProvider.getUnsyncedCount(context)};
    }

    public String getUnsyncedCountText(Context context, int[] arrn) {
        return context.getResources().getQuantityString(2131623942, arrn[0], new Object[]{arrn[0]});
    }
}


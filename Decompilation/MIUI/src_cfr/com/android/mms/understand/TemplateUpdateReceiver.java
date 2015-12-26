/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.understand;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.mms.understand.TemplateUpdate;
import com.android.mms.util.MiStatSdkHelper;

public class TemplateUpdateReceiver
extends BroadcastReceiver {
    public void onReceive(Context object, Intent intent) {
        Log.w((String)"TemplateUpdateReceiver", (String)" on receive ");
        object = new TemplateUpdate.UpdatePatch();
        object.mIsIncremental = intent.getBooleanExtra("increment", false);
        object.mVersion = intent.getLongExtra("version", 0);
        object.mMd5 = intent.getStringExtra("md5");
        object.mOldMd5 = intent.getStringExtra("old_md5");
        MiStatSdkHelper.recourdUpdateEvent("yellowpage");
        TemplateUpdate.onTemplateDownloaded((TemplateUpdate.UpdatePatch)object);
    }
}


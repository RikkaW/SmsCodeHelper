/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  java.lang.Object
 *  java.lang.String
 *  miui.app.Activity
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.android.mms.ui.ComposeMessageRouterActivity;
import miui.app.Activity;

public class SearchActivity
extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bundle = this.getIntent();
        if ("android.intent.action.VIEW".equals((Object)bundle.getAction())) {
            ComposeMessageRouterActivity.route((Context)this, (Intent)bundle);
        }
        this.finish();
    }
}


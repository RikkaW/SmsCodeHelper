/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.layout;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import com.android.mms.layout.HVGALayoutParameters;
import com.android.mms.layout.LayoutParameters;

public class LayoutManager {
    private static LayoutManager sInstance;
    private final Context mContext;
    private LayoutParameters mLayoutParams;

    private LayoutManager(Context context) {
        this.mContext = context;
        this.initLayoutParameters(context.getResources().getConfiguration());
    }

    public static LayoutManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("Uninitialized.");
        }
        return sInstance;
    }

    private static LayoutParameters getLayoutParameters(int n) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("Unsupported display type: " + n);
            }
            case 10: {
                return new HVGALayoutParameters(10);
            }
            case 11: 
        }
        return new HVGALayoutParameters(11);
    }

    public static void init(Context context) {
        if (sInstance != null) {
            Log.w((String)"LayoutManager", (String)"Already initialized.");
        }
        sInstance = new LayoutManager(context.getApplicationContext());
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initLayoutParameters(Configuration configuration) {
        int n = configuration.orientation == 1 ? 11 : 10;
        this.mLayoutParams = LayoutManager.getLayoutParameters(n);
    }

    public LayoutParameters getLayoutParameters() {
        return this.mLayoutParams;
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.initLayoutParameters(configuration);
    }
}


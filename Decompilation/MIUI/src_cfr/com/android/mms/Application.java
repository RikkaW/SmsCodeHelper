/*
 * Decompiled with CFR 0_110.
 */
package com.android.mms;

import com.android.mms.MmsApp;
import miui.external.ApplicationDelegate;

public class Application
extends miui.external.Application {
    @Override
    public ApplicationDelegate onCreateApplicationDelegate() {
        return new MmsApp();
    }
}


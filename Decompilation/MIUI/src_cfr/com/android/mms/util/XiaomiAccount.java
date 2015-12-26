/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.accounts.Account
 *  android.content.Context
 *  java.lang.Object
 *  miui.accounts.ExtraAccountManager
 */
package com.android.mms.util;

import android.accounts.Account;
import android.content.Context;
import miui.accounts.ExtraAccountManager;

public class XiaomiAccount {
    public static Account getAccount(Context context) {
        return ExtraAccountManager.getXiaomiAccount((Context)context);
    }
}


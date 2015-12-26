/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Bundle
 *  android.text.TextUtils
 *  com.xiaomi.accountsdk.account.data.ExtendedAuthToken
 *  com.xiaomi.accountsdk.activate.ActivateManager
 *  com.xiaomi.accountsdk.activate.ActivateManager$ActivateManagerFuture
 *  com.xiaomi.micloudsdk.request.DefaultRequestEnv
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.utils;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.micloudsdk.request.DefaultRequestEnv;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.MxConfigCompat;
import com.xiaomi.mms.utils.PrefUtils;
import com.xiaomi.mms.utils.logger.MyLog;

public class MxRequestEnv
extends DefaultRequestEnv {
    private static MxRequestEnv sMxRequestEnv = null;
    private Context mContext;
    private ExtendedAuthToken[] mExtendedAuthToken;
    private volatile boolean mIsMxV2 = false;
    private volatile int mSimIndex = 0;

    private MxRequestEnv(Context context) {
        this.mContext = context;
        this.mExtendedAuthToken = new ExtendedAuthToken[com.android.mms.util.MSimUtils.getMultiSimCount()];
    }

    private String getFileTokenPref() {
        if (this.mIsMxV2) {
            return "pref_mx2_file_token";
        }
        return "pref_file_token";
    }

    public static MxRequestEnv getMxRequestEnv(Context object) {
        synchronized (MxRequestEnv.class) {
            if (sMxRequestEnv == null) {
                sMxRequestEnv = new MxRequestEnv((Context)object);
            }
            object = sMxRequestEnv;
            return object;
        }
    }

    public String getAccountName() {
        return PushSession.getInstance(this.mContext).getMyMid(this.mSimIndex);
    }

    public void invalidateAuthToken() {
        this.mExtendedAuthToken[this.mSimIndex] = null;
        PrefUtils.remove(this.mContext, this.getFileTokenPref() + this.mSimIndex);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ExtendedAuthToken queryAuthToken() {
        try {
            String string2 = this.getFileTokenPref();
            string2 = PrefUtils.getString(this.mContext, string2 + this.mSimIndex);
            this.mExtendedAuthToken[this.mSimIndex] = ExtendedAuthToken.parse((String)string2);
            if (this.mExtendedAuthToken[this.mSimIndex] != null) {
                return this.mExtendedAuthToken[this.mSimIndex];
            }
            string2 = PushSession.getInstance(this.mContext).getMyMid(this.mSimIndex);
            if (TextUtils.isEmpty((CharSequence)string2)) return this.mExtendedAuthToken[this.mSimIndex];
            Object object = ActivateManager.get((Context)this.mContext);
            int n = this.mSimIndex;
            string2 = this.mIsMxV2 ? MxConfigCompat.getMxV2Sid(this.mContext, string2) : MxConfigCompat.getMxV1Sid(this.mContext, string2);
            object = (Bundle)object.getSimAuthToken(n, string2).getResult();
            string2 = object.getString("user_token");
            if (TextUtils.isEmpty((CharSequence)(object = object.getString("user_security")))) return this.mExtendedAuthToken[this.mSimIndex];
            if (TextUtils.isEmpty((CharSequence)string2)) return this.mExtendedAuthToken[this.mSimIndex];
            this.mExtendedAuthToken[this.mSimIndex] = ExtendedAuthToken.build((String)string2, (String)object);
        }
        catch (Exception var2_2) {
            MyLog.e("failed to get file upload token");
            return this.mExtendedAuthToken[this.mSimIndex];
        }
        return this.mExtendedAuthToken[this.mSimIndex];
    }

    public MxRequestEnv setIsMxV2(boolean bl) {
        this.mIsMxV2 = bl;
        return this;
    }

    public MxRequestEnv setSimIndex(int n) {
        this.mSimIndex = n;
        return this;
    }

    public boolean shouldUpdateHost() {
        if (!this.mIsMxV2) {
            return true;
        }
        return false;
    }
}


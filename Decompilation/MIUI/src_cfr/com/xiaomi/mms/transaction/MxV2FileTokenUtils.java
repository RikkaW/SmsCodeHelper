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
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.transaction;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.MxConfigCompat;
import com.xiaomi.mms.utils.PrefUtils;
import com.xiaomi.mms.utils.logger.MyLog;

public class MxV2FileTokenUtils {
    public static ExtendedAuthToken getFileTokenbysimIndex(Context context, int n) {
        Object object = ExtendedAuthToken.parse((String)PrefUtils.getString(context, "pref_mx2_file_token" + n));
        if (object != null) {
            return object;
        }
        object = PushSession.getInstance(context).getMyMid(n);
        object = ActivateManager.get((Context)context).getSimAuthToken(n, MxConfigCompat.getMxV2Sid(context, (String)object));
        try {
            Object object2 = (Bundle)object.getResult();
            object = object2.getString("user_token");
            object2 = object2.getString("user_security");
            if (!TextUtils.isEmpty((CharSequence)object) && !TextUtils.isEmpty((CharSequence)object2)) {
                object = ExtendedAuthToken.build((String)object, (String)object2);
                PrefUtils.saveString(context, "pref_mx2_file_token" + n, object.toPlain());
                return object;
            }
        }
        catch (Exception var0_1) {
            MyLog.e("failed to get file upload token");
        }
        return null;
    }

    public static ExtendedAuthToken getOtherIDCFileTokenbysimIndex(Context context, int n) {
        Object object = ExtendedAuthToken.parse((String)PrefUtils.getString(context, "pref_mx2_file_token" + n + "_idc"));
        if (object != null) {
            return object;
        }
        object = PushSession.getInstance(context).getMyMid(n);
        object = ActivateManager.get((Context)context).getSimAuthToken(n, MxConfigCompat.getOtherIDCMxV2Sid(context, (String)object));
        try {
            Object object2 = (Bundle)object.getResult();
            object = object2.getString("user_token");
            object2 = object2.getString("user_security");
            if (!TextUtils.isEmpty((CharSequence)object) && !TextUtils.isEmpty((CharSequence)object2)) {
                object = ExtendedAuthToken.build((String)object, (String)object2);
                PrefUtils.saveString(context, "pref_mx2_file_token" + n + "_idc", object.toPlain());
                return object;
            }
        }
        catch (Exception var0_1) {
            MyLog.e("failed to get other idc file auth token");
        }
        return null;
    }

    private static void invalidFileToken(Context context, int n) {
        if (n == -1) {
            MyLog.v("failed to get sim index when invalid file token  simIndex == -1");
            return;
        }
        PrefUtils.remove(context, "pref_mx2_file_token" + n);
    }

    public static void invalidFileToken(Context context, String string2) {
        MxV2FileTokenUtils.invalidFileToken(context, PushSession.getInstance(context).getSimIdByMid(string2));
    }

    private static void invalidOtherIDCFileToken(Context context, int n) {
        if (n == -1) {
            MyLog.v("failed to get sim index when invalid file token  simIndex == -1");
            return;
        }
        PrefUtils.remove(context, "pref_mx2_file_token" + n + "_idc");
    }

    public static void invalidOtherIDCFileToken(Context context, String string2) {
        MxV2FileTokenUtils.invalidOtherIDCFileToken(context, PushSession.getInstance(context).getSimIdByMid(string2));
    }
}


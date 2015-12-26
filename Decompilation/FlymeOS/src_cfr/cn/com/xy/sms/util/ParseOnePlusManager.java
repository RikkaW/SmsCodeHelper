/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.String
 */
package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.util.ParseManager;
import java.util.Map;

public class ParseOnePlusManager
extends ParseManager {
    public static boolean isPinSms(Context context, String string2, String string3, String string4, Map<String, String> map) {
        if (ParseOnePlusManager.parseSmsType(context, string2, string3, string4, map, 1) == 1) {
            return true;
        }
        return false;
    }
}


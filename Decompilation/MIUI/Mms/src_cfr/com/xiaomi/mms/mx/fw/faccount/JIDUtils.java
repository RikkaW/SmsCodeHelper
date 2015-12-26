/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package com.xiaomi.mms.mx.fw.faccount;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class JIDUtils {
    private static final Pattern XM_ACCOUNT_PATTERN = Pattern.compile((String)".*@xiaomi.com(/.*)?");

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getFullSmtpName(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return "";
        }
        String string3 = string2;
        if (XM_ACCOUNT_PATTERN.matcher((CharSequence)string2).matches()) return string3;
        return string2 + "@xiaomi.com";
    }
}


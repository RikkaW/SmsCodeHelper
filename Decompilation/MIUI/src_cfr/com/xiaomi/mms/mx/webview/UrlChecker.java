/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.xiaomi.mms.mx.webview;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

public class UrlChecker {
    private List<String> mBlackDomains = new ArrayList();
    private List<String> mWhiteDomains = new ArrayList();

    public UrlChecker() {
        this.mWhiteDomains.add("www.baidu.com");
        this.mWhiteDomains.add("3g.miliao.com");
        this.mBlackDomains.add("www.360.com");
    }

    public int getPermissionOfUrl(String string2) {
        int n;
        int n2 = n = 63;
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            n2 = n;
            if (string2.startsWith("miliao://")) {
                n2 = 63 + 16384;
            }
        }
        return n2;
    }

    public boolean isInWhiteList(String string2) {
        return true;
    }
}


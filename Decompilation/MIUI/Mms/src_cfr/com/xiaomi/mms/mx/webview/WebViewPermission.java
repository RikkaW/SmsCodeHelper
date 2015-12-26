/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.webkit.WebSettings
 *  android.webkit.WebView
 *  java.lang.Object
 */
package com.xiaomi.mms.mx.webview;

import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewPermission {
    public static boolean canUseAlertDialog(int n) {
        if ((n & 4096) == 4096) {
            return true;
        }
        return false;
    }

    public static boolean canUseMiTalk(int n) {
        if ((n & 16384) == 16384) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void settingWebView(WebView webView, int n) {
        boolean bl = true;
        webView = webView.getSettings();
        boolean bl2 = (n & 1) == 1;
        webView.setJavaScriptEnabled(bl2);
        bl2 = (n & 2) == 2;
        webView.setDomStorageEnabled(bl2);
        bl2 = (n & 4) == 4;
        webView.setDatabaseEnabled(bl2);
        bl2 = (n & 8) == 8;
        webView.setAppCacheEnabled(bl2);
        bl2 = (n & 16) == 16;
        webView.setSaveFormData(bl2);
        bl2 = (n & 32) == 32;
        webView.setSupportZoom(bl2);
        bl2 = (n & 64) == 64;
        webView.setAllowContentAccess(bl2);
        bl2 = (n & 128) == 128;
        webView.setAllowFileAccess(bl2);
        bl2 = (n & 256) == 256;
        webView.setJavaScriptCanOpenWindowsAutomatically(bl2);
        bl2 = (n & 512) == 512;
        webView.setSavePassword(bl2);
        bl2 = (n & 2048) == 2048 ? bl : false;
        webView.setGeolocationEnabled(bl2);
    }
}


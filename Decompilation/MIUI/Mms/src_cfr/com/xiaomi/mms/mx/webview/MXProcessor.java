/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.webkit.WebView
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.mx.webview;

import android.content.Context;
import android.webkit.WebView;
import com.xiaomi.mms.mx.webview.IWebViewProcessor;

public class MXProcessor
implements IWebViewProcessor {
    private Context mContext;

    public MXProcessor(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean processUrl(WebView webView, String string2) {
        return false;
    }
}


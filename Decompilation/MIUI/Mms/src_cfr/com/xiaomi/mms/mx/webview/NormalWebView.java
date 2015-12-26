/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.webkit.WebView
 *  java.lang.Object
 */
package com.xiaomi.mms.mx.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class NormalWebView
extends WebView {
    public OnScrollListener mScrollListener;

    public NormalWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onScrollChanged(int n, int n2, int n3, int n4) {
        super.onScrollChanged(n, n2, n3, n4);
        if (n2 < n4 - 20) {
            if (this.mScrollListener == null) return;
            {
                this.mScrollListener.onScrollUp();
                return;
            }
        } else {
            if (n2 - 20 <= n4 || this.mScrollListener == null) return;
            {
                this.mScrollListener.onScrollDown();
                return;
            }
        }
    }

    public static interface OnScrollListener {
        public void onScrollDown();

        public void onScrollUp();
    }

}


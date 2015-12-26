/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.text.TextUtils
 *  android.util.AttributeSet
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.webkit.WebChromeClient
 *  android.webkit.WebSettings
 *  android.webkit.WebSettings$PluginState
 *  android.webkit.WebView
 *  android.webkit.WebViewClient
 *  android.widget.RelativeLayout
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.xiaomi.mms.mx.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.xiaomi.mms.mx.webview.MxConfigableWebViewClient;
import com.xiaomi.mms.mx.webview.NormalWebView;

public class MxWebView
extends RelativeLayout {
    private View mBottomOpBar;
    private View.OnClickListener mClickListener;
    private String mForwardString;
    private boolean mIsAlwaysHideBottomOpBar = false;
    private boolean mIsPageError = false;
    private boolean mIsPlayAnim = false;
    private boolean mIsShowBottomBar = false;
    private OnCustomActionSetListener mOnCustomActionSetListener;
    private String mOwner;
    private String mOwnerName;
    private long mPageStartTime;
    private String mUrl;
    private NormalWebView mWebView;
    private MxConfigableWebViewClient mWebViewClient;

    public MxWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mClickListener = new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View view) {
                if (view.getId() == 2131820815) {
                    MxWebView.this.onClickBack();
                    return;
                } else {
                    if (view.getId() != 2131820816) return;
                    {
                        MxWebView.this.mWebView.reload();
                        return;
                    }
                }
            }
        };
        this.init();
    }

    private void init() {
        MxWebView.inflate((Context)this.getContext(), (int)2130968676, (ViewGroup)this);
        this.mWebView = (NormalWebView)this.findViewById(2131820809);
        this.mBottomOpBar = this.findViewById(2131820808);
        this.mBottomOpBar.findViewById(2131820815).setOnClickListener(this.mClickListener);
        this.mBottomOpBar.findViewById(2131820816).setOnClickListener(this.mClickListener);
        this.mWebView.setScrollBarStyle(0);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setAllowFileAccess(true);
        this.mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        this.mWebView.getSettings().setSupportZoom(true);
        this.mWebView.getSettings().setBuiltInZoomControls(false);
        this.mWebView.setBackgroundColor(0);
        this.mWebViewClient = new MxConfigableWebViewClient(this.getContext()){

            public void onPageFinished(WebView webView, String string2) {
                super.onPageFinished(webView, string2);
                MxWebView.this.mUrl = string2;
                MxWebView.this.mIsPageError = false;
            }

            public void onPageStarted(WebView webView, String string2, Bitmap bitmap) {
                MxWebView.this.mUrl = string2;
                MxWebView.this.mPageStartTime = System.currentTimeMillis();
                super.onPageStarted(webView, string2, bitmap);
            }

            public void onReceivedError(WebView webView, int n, String string2, String string3) {
                super.onReceivedError(webView, n, string2, string3);
                MxWebView.this.mIsPageError = true;
            }
        };
        this.mWebView.setWebViewClient((WebViewClient)this.mWebViewClient);
        this.mWebView.setWebChromeClient(new WebChromeClient(){

            public void onProgressChanged(WebView webView, int n) {
                if (MxWebView.this.mOnCustomActionSetListener != null) {
                    MxWebView.this.mOnCustomActionSetListener.onLoadingProgressChanged(n);
                }
            }

            public void onReceivedTitle(WebView webView, String string2) {
                super.onReceivedTitle(webView, string2);
                if (MxWebView.this.mOnCustomActionSetListener != null) {
                    MxWebView.this.mOnCustomActionSetListener.onWebViewTitleChanged(string2);
                }
            }
        });
        this.mWebView.setOnTouchListener(new View.OnTouchListener(){

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        this.initFontArea();
        this.mBottomOpBar.setVisibility(8);
    }

    private void initFontArea() {
    }

    private void onClickBack() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        }
    }

    public void destroy() {
        this.mWebView.loadData("", "text/html", "utf-8");
        this.mWebView.destroy();
    }

    public boolean goBack() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
            return true;
        }
        return false;
    }

    public void loadUrl(String string2) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            this.mWebViewClient.preProcessBottomBar(this.mWebView, string2);
            this.mWebView.loadUrl(string2);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        switch (n) {
            default: {
                return super.onKeyDown(n, keyEvent);
            }
            case 4: {
                if (!this.mWebView.canGoBack()) return super.onKeyDown(n, keyEvent);
                this.mWebView.goBack();
                return true;
            }
        }
    }

    public void setOnCustomActionSetListener(OnCustomActionSetListener onCustomActionSetListener) {
        this.mOnCustomActionSetListener = onCustomActionSetListener;
    }

    public void setOwner(String string2, String string3, String string4) {
        this.mOwner = string2;
        this.mOwnerName = string3;
        this.mForwardString = string4;
    }

    public static interface OnCustomActionSetListener {
        public void onLoadingProgressChanged(int var1);

        public void onWebViewTitleChanged(String var1);
    }

}


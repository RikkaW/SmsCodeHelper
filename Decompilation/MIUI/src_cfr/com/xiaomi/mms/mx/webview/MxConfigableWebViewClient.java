/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  android.webkit.WebView
 *  android.webkit.WebViewClient
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.xiaomi.mms.mx.webview;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xiaomi.mms.mx.utils.Log;
import com.xiaomi.mms.mx.webview.AlertWindowProcess;
import com.xiaomi.mms.mx.webview.IWebViewProcessor;
import com.xiaomi.mms.mx.webview.MXProcessor;
import com.xiaomi.mms.mx.webview.UrlChecker;
import com.xiaomi.mms.mx.webview.WebViewFragment;
import com.xiaomi.mms.mx.webview.WebViewPermission;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MxConfigableWebViewClient
extends WebViewClient {
    private UrlChecker mChecker = new UrlChecker();
    private Context mContext;
    private String mLoadedUrl = "";
    private OnViewModeChangeListener mOnViewModeChangeListener;
    private List<IWebViewProcessor> mProcessors = new ArrayList();
    private int mViewMode = 100;

    public MxConfigableWebViewClient(Context context) {
        this.mContext = context;
    }

    private void changeViewMode(int n) {
        if (this.mViewMode != n && this.mOnViewModeChangeListener != null && this.mOnViewModeChangeListener.isCanChangeViewMode()) {
            this.mViewMode = n;
            Log.v("MxConfigableWebViewClient", "MLConfigableWebViewClient:changeViewMode:viewMode=" + n);
            this.mOnViewModeChangeListener.onViewModeChanged(n);
        }
    }

    private boolean checkNeedOpenInNewWindow(String string2, boolean bl) {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            bl3 = bl2;
            if (!TextUtils.isEmpty((CharSequence)this.mLoadedUrl)) {
                bl3 = bl2;
                if (!this.mLoadedUrl.equals((Object)string2)) {
                    bl3 = bl2;
                    if (this.mViewMode == 100) {
                        bl3 = bl2;
                        if (!bl) {
                            WebViewFragment.openNewWindowUrl(this.mContext, string2);
                            bl3 = true;
                        }
                    }
                }
            }
        }
        return bl3;
    }

    public void SettingProcessors(int n) {
        this.mProcessors.clear();
        if (WebViewPermission.canUseAlertDialog(n)) {
            this.mProcessors.add(new AlertWindowProcess());
        }
        if (WebViewPermission.canUseMiTalk(n)) {
            this.mProcessors.add(new MXProcessor(this.mContext));
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void preProcessBottomBar(WebView webView, String string2) {
        boolean bl = this.mChecker.isInWhiteList(string2);
        int n = this.mChecker.getPermissionOfUrl(string2);
        WebViewPermission.settingWebView(webView, n);
        this.SettingProcessors(n);
        if (TextUtils.isEmpty((CharSequence)this.mLoadedUrl) && bl) {
            this.changeViewMode(100);
            return;
        } else {
            if (TextUtils.isEmpty((CharSequence)this.mLoadedUrl) && !bl) {
                this.changeViewMode(101);
                return;
            }
            if (TextUtils.isEmpty((CharSequence)this.mLoadedUrl) || this.mViewMode != 100 || !bl) return;
            {
                this.changeViewMode(101);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean preProcessUrl(WebView webView, String string2) {
        if (this.checkNeedOpenInNewWindow(string2, this.mChecker.isInWhiteList(string2))) {
            return true;
        }
        this.preProcessBottomBar(webView, string2);
        this.mLoadedUrl = string2;
        boolean bl = this.mProcessors.size() > 0;
        Iterator<IWebViewProcessor> iterator = this.mProcessors.iterator();
        while (iterator.hasNext()) {
            IWebViewProcessor iWebViewProcessor = iterator.next();
            if (bl && iWebViewProcessor.processUrl(webView, string2)) {
                bl = true;
                continue;
            }
            bl = false;
        }
        return bl;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String string2) {
        Log.v("MxConfigableWebViewClient", "MxConfigableWebViewClient:shouldOverrideUrlLoading:url=" + string2);
        if (!this.preProcessUrl(webView, string2)) {
            return super.shouldOverrideUrlLoading(webView, string2);
        }
        return true;
    }

    public static interface OnViewModeChangeListener {
        public boolean isCanChangeViewMode();

        public void onViewModeChanged(int var1);
    }

}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.text.TextUtils
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  java.lang.Class
 *  java.lang.String
 */
package com.xiaomi.mms.mx.webview;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.mms.mx.webview.MxWebView;
import com.xiaomi.mms.mx.webview.WebViewActivity;

public class WebViewFragment
extends Fragment {
    private String mForwardString;
    private MxWebView.OnCustomActionSetListener mOnCustomActionSetListener;
    private String mOwner;
    private String mOwnerName;
    private String mUrl;
    private MxWebView mWebView;

    public static void openNewWindowUrl(Context context, String string2) {
        Intent intent = new Intent(context, (Class)WebViewActivity.class);
        intent.putExtra("extra_url", string2);
        if (!(context instanceof Activity)) {
            intent.setFlags(268435456);
        }
        context.startActivity(intent);
    }

    public boolean goPrePage() {
        return this.mWebView.goBack();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (!TextUtils.isEmpty((CharSequence)this.mUrl)) {
            this.mWebView.loadUrl(this.mUrl);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(2130968677, null);
        this.mWebView = (MxWebView)layoutInflater.findViewById(2131820817);
        return layoutInflater;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mWebView.destroy();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view = this.getArguments();
        this.mUrl = view.getString("extra_url");
        this.mForwardString = view.getString("extra_forward_string");
        this.mOwner = view.getString("extra_owner");
        this.mOwnerName = view.getString("extra_owner_name");
        this.mWebView.setOwner(this.mOwner, this.mOwnerName, this.mForwardString);
        this.mWebView.setOnCustomActionSetListener(this.mOnCustomActionSetListener);
    }

    public void setOnCustomActionSetListener(MxWebView.OnCustomActionSetListener onCustomActionSetListener) {
        this.mOnCustomActionSetListener = onCustomActionSetListener;
    }
}


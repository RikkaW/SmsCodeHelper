/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentTransaction
 *  android.content.Intent
 *  android.os.Bundle
 *  android.text.TextUtils
 *  android.view.MenuItem
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 *  miui.app.ActionBar
 *  miui.app.Activity
 */
package com.xiaomi.mms.mx.webview;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import com.xiaomi.mms.mx.webview.MxWebView;
import com.xiaomi.mms.mx.webview.WebViewFragment;
import miui.app.ActionBar;
import miui.app.Activity;

public class WebViewActivity
extends Activity {
    private ActionBar mActionBar;
    private MxWebView.OnCustomActionSetListener mOnCustomActionSetListener;
    private View mShuffleView;
    private WebViewFragment mWebWiewFragment;

    public WebViewActivity() {
        this.mOnCustomActionSetListener = new MxWebView.OnCustomActionSetListener(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void onLoadingProgressChanged(int n) {
                n = n > 0 && n < 100 ? 0 : 4;
                WebViewActivity.this.mShuffleView.setVisibility(n);
            }

            @Override
            public void onWebViewTitleChanged(String string2) {
                if (!TextUtils.isEmpty((CharSequence)string2)) {
                    WebViewActivity.this.mActionBar.setTitle((CharSequence)string2);
                }
            }
        };
    }

    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        ((WebViewFragment)fragment).setOnCustomActionSetListener(this.mOnCustomActionSetListener);
    }

    public void onBackPressed() {
        if (!this.mWebWiewFragment.goPrePage()) {
            super.onBackPressed();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968720);
        bundle = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = bundle.beginTransaction();
        this.mWebWiewFragment = (WebViewFragment)bundle.findFragmentByTag("WebViewFragment");
        if (this.mWebWiewFragment == null) {
            this.mWebWiewFragment = new WebViewFragment();
            this.mWebWiewFragment.setArguments(this.getIntent().getExtras());
            fragmentTransaction.add(2131820901, (Fragment)this.mWebWiewFragment, "WebViewFragment");
        }
        this.mActionBar = this.getActionBar();
        this.mActionBar.setTitle((CharSequence)"  ");
        this.mActionBar.setHomeButtonEnabled(true);
        this.mActionBar.setCustomView(2130968667);
        this.mActionBar.setDisplayShowCustomEnabled(true);
        this.mShuffleView = this.mActionBar.getCustomView().findViewById(2131820795);
        fragmentTransaction.commit();
        bundle.executePendingTransactions();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return false;
            }
            case 16908332: 
        }
        this.finish();
        return true;
    }

}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.annotation.TargetApi
 *  android.app.ActionBar
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.webkit.DownloadListener
 *  android.webkit.WebChromeClient
 *  android.webkit.WebSettings
 *  android.webkit.WebSettings$RenderPriority
 *  android.webkit.WebSettings$TextSize
 *  android.webkit.WebView
 *  android.webkit.WebViewClient
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.ui.popu.web;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.com.xy.sms.sdk.net.NetWebUtil;
import cn.com.xy.sms.sdk.ui.popu.web.SdkWebJavaScript;
import cn.com.xy.sms.sdk.util.KeyManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.util.ParseManager;
import java.io.File;
import org.json.JSONObject;

@TargetApi(value=11)
public class SdkWebActivity
extends Activity
implements dm {
    private static int a = 0;
    private WebView b = null;
    private RelativeLayout c = null;
    private TextView d = null;
    private TextView e = null;
    private ImageView f = null;
    private ProgressBar g;
    private JSONObject h = null;
    private Context i = null;
    private RelativeLayout j = null;
    private RelativeLayout k = null;
    private String l = "";
    private String m = "";
    private String n = "";

    public static /* synthetic */ WebView a(SdkWebActivity sdkWebActivity) {
        return sdkWebActivity.b;
    }

    public static /* synthetic */ ProgressBar b(SdkWebActivity sdkWebActivity) {
        return sdkWebActivity.g;
    }

    @Override
    public WebView a() {
        return this.b;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public String a(String string2) {
        String string3;
        String string4 = string3 = null;
        if (string2 != null) {
            try {
                if (this.h == null) {
                    this.h = new JSONObject(this.getIntent().getStringExtra("JSONDATA"));
                }
                string4 = string3;
                if (this.h != null) {
                    string4 = string3;
                    if (this.h.has(string2)) {
                        string4 = this.h.getString(string2);
                    }
                }
            }
            catch (Exception var1_2) {
                var1_2.printStackTrace();
                string4 = string3;
            }
        }
        string2 = string4;
        if (string4 != null) return string2;
        return "";
    }

    public void a(String string2, String string3) {
        if (StringUtils.isNull(string3)) {
            this.setTitle((CharSequence)string2);
            this.d.setText((CharSequence)string2);
            return;
        }
        this.setTitle((CharSequence)string3);
        this.d.setText((CharSequence)string3);
    }

    @Override
    public Activity b() {
        return this;
    }

    @Override
    public int c() {
        return this.i.getResources().getConfiguration().orientation;
    }

    void d() {
        try {
            this.m = KeyManager.getAppKey();
            this.n = ParseManager.getSdkVersion();
            return;
        }
        catch (Exception var1_1) {
            var1_1.printStackTrace();
            return;
        }
    }

    void e() {
        this.f.setOnClickListener((View.OnClickListener)new dz(this));
        this.e.setOnClickListener((View.OnClickListener)new eb(this));
        this.k.setOnClickListener((View.OnClickListener)new ec(this));
    }

    /*
     * Enabled aggressive block sorting
     */
    void f() {
        String string2;
        String string3 = super.getIntent().getStringExtra("actionType");
        if (string3 != null && "WEB_URL".equals((Object)string3)) {
            string2 = this.a("url");
        } else {
            String string4 = string3 = this.a("HOST");
            if (StringUtils.isNull(string3)) {
                string4 = NetWebUtil.WEB_SERVER_URL;
            }
            string3 = string2 = this.a("PAGEVIEW");
            if (StringUtils.isNull(string2)) {
                string2 = this.a("type");
                if ("WEB_MAP_SITE".equals((Object)string2)) {
                    string3 = this.a("address");
                    string2 = "http://api.map.baidu.com/geocoder?address=" + string3 + "&output=html&src=xiaoyuan|" + this.l;
                    if (!StringUtils.isNull(string3)) {
                        this.g.setVisibility(8);
                        this.b.loadUrl(string2);
                        return;
                    }
                    this.g();
                    return;
                }
                string3 = "h5service?action_type=" + string2 + "&xy_channel=" + this.m + "&xy_sdkver=" + this.n;
                if ("WEB_ABOUT".equals((Object)string2)) {
                    this.f.setVisibility(4);
                } else {
                    this.f.setVisibility(0);
                }
            }
            string2 = string3;
            if (!StringUtils.isNull(string3)) {
                string2 = string4 + "/" + string3;
            }
        }
        if (StringUtils.isNull(string2)) {
            this.g();
            return;
        }
        int n2 = XyUtil.checkNetWork(this.i);
        if (n2 != 0 && n2 != 1) {
            this.g();
            return;
        }
        this.b.loadUrl(string2);
    }

    public void g() {
        this.g.setVisibility(8);
        this.b.setVisibility(8);
        this.d.setText(br.f.duoqu_web_not_find_page);
        this.j.setVisibility(0);
    }

    @SuppressLint(value={"SetJavaScriptEnabled"})
    void h() {
        Object object = this.getApplicationContext().getDir("database", 0).getPath();
        this.b.getSettings().setGeolocationDatabasePath((String)object);
        this.b.getSettings().setGeolocationEnabled(true);
        this.b.getSettings().setJavaScriptEnabled(true);
        this.b.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.b.getSettings().setBuiltInZoomControls(true);
        this.b.getSettings().setDomStorageEnabled(true);
        this.b.getSettings().setLoadWithOverviewMode(true);
        this.b.getSettings().setUseWideViewPort(true);
        this.b.getSettings().setDatabaseEnabled(true);
        this.b.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
        this.b.getSettings().setCacheMode(2);
        this.b.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        this.b.getSettings().setBlockNetworkImage(true);
        this.b.setDownloadListener((DownloadListener)new ed(this));
        this.b.setWebViewClient((WebViewClient)new ee(this));
        this.b.setWebChromeClient((WebChromeClient)new ef(this));
        object = new SdkWebJavaScript(this);
        this.b.addJavascriptInterface(object, "injs");
    }

    @SuppressLint(value={"JavascriptInterface"})
    protected void onCreate(Bundle bundle) {
        ++a;
        super.onCreate(bundle);
        this.setContentView(br.e.duoqu_sdk_web_main);
        bundle = this.getActionBar();
        bundle.setDisplayShowCustomEnabled(true);
        bundle.setDisplayShowHomeEnabled(false);
        bundle.setDisplayOptions(16);
        RelativeLayout relativeLayout = (RelativeLayout)this.getLayoutInflater().inflate(br.e.duoqu_web_action_bar, null);
        bundle.setCustomView((View)relativeLayout);
        this.i = this;
        this.c = (RelativeLayout)this.findViewById(br.d.duoqu_webview);
        this.b = new WebView((Context)this);
        this.c.addView((View)this.b);
        this.b.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -1));
        this.d = (TextView)this.findViewById(br.d.duoqu_title_name);
        this.e = (TextView)relativeLayout.findViewById(br.d.duoqu_header_back);
        this.f = (ImageView)relativeLayout.findViewById(br.d.duoqu_header_menu);
        this.j = (RelativeLayout)this.findViewById(br.d.duoqu_error_page);
        this.k = (RelativeLayout)this.findViewById(br.d.duoqu_network_setting);
        this.g = (ProgressBar)this.findViewById(br.d.duoqu_bar);
        this.l = this.getResources().getString(br.f.duoqu_tip_duoqu_name);
        this.d();
        this.h();
        this.f();
        this.e();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onDestroy() {
        try {
            this.c.removeAllViews();
            this.b.setWebViewClient(null);
            this.b.setWebChromeClient(null);
            this.b.destroy();
            this.b = null;
            en.a(this);
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
        }
        super.onDestroy();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onKeyDown(int n2, KeyEvent keyEvent) {
        if (n2 != 4) return super.onKeyDown(n2, keyEvent);
        if (this.b.canGoBack()) {
            this.b.goBack();
            do {
                return true;
                break;
            } while (true);
        }
        this.finish();
        return true;
    }
}


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
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import br.d;
import br.e;
import br.f;
import cn.com.xy.sms.sdk.net.NetWebUtil;
import cn.com.xy.sms.sdk.util.KeyManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.util.ParseManager;
import dm;
import dz;
import eb;
import ec;
import ed;
import ee;
import ef;
import en;
import java.io.File;
import org.json.JSONObject;

@TargetApi(11)
public class SdkWebActivity
  extends Activity
  implements dm
{
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
  
  public WebView a()
  {
    return b;
  }
  
  public String a(String paramString)
  {
    localObject2 = null;
    localObject1 = localObject2;
    if (paramString != null) {}
    try
    {
      if (h == null) {
        h = new JSONObject(getIntent().getStringExtra("JSONDATA"));
      }
      localObject1 = localObject2;
      if (h != null)
      {
        localObject1 = localObject2;
        if (h.has(paramString)) {
          localObject1 = h.getString(paramString);
        }
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        localObject1 = localObject2;
      }
    }
    paramString = (String)localObject1;
    if (localObject1 == null) {
      paramString = "";
    }
    return paramString;
  }
  
  public void a(String paramString1, String paramString2)
  {
    if (StringUtils.isNull(paramString2))
    {
      setTitle(paramString1);
      d.setText(paramString1);
      return;
    }
    setTitle(paramString2);
    d.setText(paramString2);
  }
  
  public Activity b()
  {
    return this;
  }
  
  public int c()
  {
    return i.getResources().getConfiguration().orientation;
  }
  
  void d()
  {
    try
    {
      m = KeyManager.getAppKey();
      n = ParseManager.getSdkVersion();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  void e()
  {
    f.setOnClickListener(new dz(this));
    e.setOnClickListener(new eb(this));
    k.setOnClickListener(new ec(this));
  }
  
  void f()
  {
    Object localObject1 = super.getIntent().getStringExtra("actionType");
    Object localObject2;
    if ((localObject1 != null) && ("WEB_URL".equals(localObject1)))
    {
      localObject2 = a("url");
      if (StringUtils.isNull((String)localObject2)) {
        break label300;
      }
      int i1 = XyUtil.checkNetWork(i);
      if ((i1 == 0) || (i1 == 1)) {
        b.loadUrl((String)localObject2);
      }
    }
    else
    {
      localObject1 = a("HOST");
      Object localObject3 = localObject1;
      if (StringUtils.isNull((String)localObject1)) {
        localObject3 = NetWebUtil.WEB_SERVER_URL;
      }
      localObject2 = a("PAGEVIEW");
      localObject1 = localObject2;
      if (StringUtils.isNull((String)localObject2))
      {
        localObject2 = a("type");
        if ("WEB_MAP_SITE".equals(localObject2))
        {
          localObject1 = a("address");
          localObject2 = "http://api.map.baidu.com/geocoder?address=" + (String)localObject1 + "&output=html&src=xiaoyuan|" + l;
          if (!StringUtils.isNull((String)localObject1))
          {
            g.setVisibility(8);
            b.loadUrl((String)localObject2);
            return;
          }
          g();
          return;
        }
        localObject1 = "h5service?action_type=" + (String)localObject2 + "&xy_channel=" + m + "&xy_sdkver=" + n;
        if (!"WEB_ABOUT".equals(localObject2)) {
          break label284;
        }
        f.setVisibility(4);
      }
      for (;;)
      {
        localObject2 = localObject1;
        if (StringUtils.isNull((String)localObject1)) {
          break;
        }
        localObject2 = (String)localObject3 + "/" + (String)localObject1;
        break;
        label284:
        f.setVisibility(0);
      }
    }
    g();
    return;
    label300:
    g();
  }
  
  public void g()
  {
    g.setVisibility(8);
    b.setVisibility(8);
    d.setText(br.f.duoqu_web_not_find_page);
    j.setVisibility(0);
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  void h()
  {
    Object localObject = getApplicationContext().getDir("database", 0).getPath();
    b.getSettings().setGeolocationDatabasePath((String)localObject);
    b.getSettings().setGeolocationEnabled(true);
    b.getSettings().setJavaScriptEnabled(true);
    b.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    b.getSettings().setBuiltInZoomControls(true);
    b.getSettings().setDomStorageEnabled(true);
    b.getSettings().setLoadWithOverviewMode(true);
    b.getSettings().setUseWideViewPort(true);
    b.getSettings().setDatabaseEnabled(true);
    b.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
    b.getSettings().setCacheMode(2);
    b.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
    b.getSettings().setBlockNetworkImage(true);
    b.setDownloadListener(new ed(this));
    b.setWebViewClient(new ee(this));
    b.setWebChromeClient(new ef(this));
    localObject = new SdkWebJavaScript(this);
    b.addJavascriptInterface(localObject, "injs");
  }
  
  @SuppressLint({"JavascriptInterface"})
  protected void onCreate(Bundle paramBundle)
  {
    a += 1;
    super.onCreate(paramBundle);
    setContentView(br.e.duoqu_sdk_web_main);
    paramBundle = getActionBar();
    paramBundle.setDisplayShowCustomEnabled(true);
    paramBundle.setDisplayShowHomeEnabled(false);
    paramBundle.setDisplayOptions(16);
    RelativeLayout localRelativeLayout = (RelativeLayout)getLayoutInflater().inflate(br.e.duoqu_web_action_bar, null);
    paramBundle.setCustomView(localRelativeLayout);
    i = this;
    c = ((RelativeLayout)findViewById(br.d.duoqu_webview));
    b = new WebView(this);
    c.addView(b);
    b.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    d = ((TextView)findViewById(br.d.duoqu_title_name));
    e = ((TextView)localRelativeLayout.findViewById(br.d.duoqu_header_back));
    f = ((ImageView)localRelativeLayout.findViewById(br.d.duoqu_header_menu));
    j = ((RelativeLayout)findViewById(br.d.duoqu_error_page));
    k = ((RelativeLayout)findViewById(br.d.duoqu_network_setting));
    g = ((ProgressBar)findViewById(br.d.duoqu_bar));
    l = getResources().getString(br.f.duoqu_tip_duoqu_name);
    d();
    h();
    f();
    e();
  }
  
  protected void onDestroy()
  {
    try
    {
      c.removeAllViews();
      b.setWebViewClient(null);
      b.setWebChromeClient(null);
      b.destroy();
      b = null;
      en.a(this);
      super.onDestroy();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
      }
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (b.canGoBack()) {
        b.goBack();
      }
      for (;;)
      {
        return true;
        finish();
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.web.SdkWebActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
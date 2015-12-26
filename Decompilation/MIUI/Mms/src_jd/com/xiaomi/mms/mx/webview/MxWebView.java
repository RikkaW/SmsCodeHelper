package com.xiaomi.mms.mx.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.RelativeLayout;

public class MxWebView
  extends RelativeLayout
{
  private View mBottomOpBar;
  private View.OnClickListener mClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (paramAnonymousView.getId() == 2131820815) {
        MxWebView.this.onClickBack();
      }
      while (paramAnonymousView.getId() != 2131820816) {
        return;
      }
      mWebView.reload();
    }
  };
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
  
  public MxWebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  private void init()
  {
    inflate(getContext(), 2130968676, this);
    mWebView = ((NormalWebView)findViewById(2131820809));
    mBottomOpBar = findViewById(2131820808);
    mBottomOpBar.findViewById(2131820815).setOnClickListener(mClickListener);
    mBottomOpBar.findViewById(2131820816).setOnClickListener(mClickListener);
    mWebView.setScrollBarStyle(0);
    mWebView.getSettings().setJavaScriptEnabled(true);
    mWebView.getSettings().setAllowFileAccess(true);
    mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
    mWebView.getSettings().setSupportZoom(true);
    mWebView.getSettings().setBuiltInZoomControls(false);
    mWebView.setBackgroundColor(0);
    mWebViewClient = new MxConfigableWebViewClient(getContext())
    {
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        super.onPageFinished(paramAnonymousWebView, paramAnonymousString);
        MxWebView.access$202(MxWebView.this, paramAnonymousString);
        MxWebView.access$302(MxWebView.this, false);
      }
      
      public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
      {
        MxWebView.access$202(MxWebView.this, paramAnonymousString);
        MxWebView.access$402(MxWebView.this, System.currentTimeMillis());
        super.onPageStarted(paramAnonymousWebView, paramAnonymousString, paramAnonymousBitmap);
      }
      
      public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
      {
        super.onReceivedError(paramAnonymousWebView, paramAnonymousInt, paramAnonymousString1, paramAnonymousString2);
        MxWebView.access$302(MxWebView.this, true);
      }
    };
    mWebView.setWebViewClient(mWebViewClient);
    mWebView.setWebChromeClient(new WebChromeClient()
    {
      public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt)
      {
        if (mOnCustomActionSetListener != null) {
          mOnCustomActionSetListener.onLoadingProgressChanged(paramAnonymousInt);
        }
      }
      
      public void onReceivedTitle(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        super.onReceivedTitle(paramAnonymousWebView, paramAnonymousString);
        if (mOnCustomActionSetListener != null) {
          mOnCustomActionSetListener.onWebViewTitleChanged(paramAnonymousString);
        }
      }
    });
    mWebView.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        return false;
      }
    });
    initFontArea();
    mBottomOpBar.setVisibility(8);
  }
  
  private void initFontArea() {}
  
  private void onClickBack()
  {
    if (mWebView.canGoBack()) {
      mWebView.goBack();
    }
  }
  
  public void destroy()
  {
    mWebView.loadData("", "text/html", "utf-8");
    mWebView.destroy();
  }
  
  public boolean goBack()
  {
    if (mWebView.canGoBack())
    {
      mWebView.goBack();
      return true;
    }
    return false;
  }
  
  public void loadUrl(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      mWebViewClient.preProcessBottomBar(mWebView, paramString);
      mWebView.loadUrl(paramString);
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    }
    do
    {
      return super.onKeyDown(paramInt, paramKeyEvent);
    } while (!mWebView.canGoBack());
    mWebView.goBack();
    return true;
  }
  
  public void setOnCustomActionSetListener(OnCustomActionSetListener paramOnCustomActionSetListener)
  {
    mOnCustomActionSetListener = paramOnCustomActionSetListener;
  }
  
  public void setOwner(String paramString1, String paramString2, String paramString3)
  {
    mOwner = paramString1;
    mOwnerName = paramString2;
    mForwardString = paramString3;
  }
  
  public static abstract interface OnCustomActionSetListener
  {
    public abstract void onLoadingProgressChanged(int paramInt);
    
    public abstract void onWebViewTitleChanged(String paramString);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.webview.MxWebView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.xiaomi.mms.mx.webview;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

class MxWebView$3
  extends WebChromeClient
{
  MxWebView$3(MxWebView paramMxWebView) {}
  
  public void onProgressChanged(WebView paramWebView, int paramInt)
  {
    if (MxWebView.access$500(this$0) != null) {
      MxWebView.access$500(this$0).onLoadingProgressChanged(paramInt);
    }
  }
  
  public void onReceivedTitle(WebView paramWebView, String paramString)
  {
    super.onReceivedTitle(paramWebView, paramString);
    if (MxWebView.access$500(this$0) != null) {
      MxWebView.access$500(this$0).onWebViewTitleChanged(paramString);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.webview.MxWebView.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
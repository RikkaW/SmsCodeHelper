package com.xiaomi.mms.mx.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebView;

class MxWebView$2
  extends MxConfigableWebViewClient
{
  MxWebView$2(MxWebView paramMxWebView, Context paramContext)
  {
    super(paramContext);
  }
  
  public void onPageFinished(WebView paramWebView, String paramString)
  {
    super.onPageFinished(paramWebView, paramString);
    MxWebView.access$202(this$0, paramString);
    MxWebView.access$302(this$0, false);
  }
  
  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    MxWebView.access$202(this$0, paramString);
    MxWebView.access$402(this$0, System.currentTimeMillis());
    super.onPageStarted(paramWebView, paramString, paramBitmap);
  }
  
  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    MxWebView.access$302(this$0, true);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.webview.MxWebView.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
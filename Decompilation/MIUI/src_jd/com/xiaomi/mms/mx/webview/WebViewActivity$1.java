package com.xiaomi.mms.mx.webview;

import android.text.TextUtils;
import android.view.View;
import miui.app.ActionBar;

class WebViewActivity$1
  implements MxWebView.OnCustomActionSetListener
{
  WebViewActivity$1(WebViewActivity paramWebViewActivity) {}
  
  public void onLoadingProgressChanged(int paramInt)
  {
    if ((paramInt > 0) && (paramInt < 100)) {}
    for (paramInt = 0;; paramInt = 4)
    {
      WebViewActivity.access$000(this$0).setVisibility(paramInt);
      return;
    }
  }
  
  public void onWebViewTitleChanged(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      WebViewActivity.access$100(this$0).setTitle(paramString);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.webview.WebViewActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.xiaomi.mms.mx.webview;

import android.content.Context;
import android.webkit.WebView;

public class MXProcessor
  implements IWebViewProcessor
{
  private Context mContext;
  
  public MXProcessor(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public boolean processUrl(WebView paramWebView, String paramString)
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.webview.MXProcessor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
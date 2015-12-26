package com.xiaomi.mms.mx.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class NormalWebView
  extends WebView
{
  public OnScrollListener mScrollListener;
  
  public NormalWebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt2 < paramInt4 - 20) {
      if (mScrollListener != null) {
        mScrollListener.onScrollUp();
      }
    }
    while ((paramInt2 - 20 <= paramInt4) || (mScrollListener == null)) {
      return;
    }
    mScrollListener.onScrollDown();
  }
  
  public static abstract interface OnScrollListener
  {
    public abstract void onScrollDown();
    
    public abstract void onScrollUp();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.webview.NormalWebView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
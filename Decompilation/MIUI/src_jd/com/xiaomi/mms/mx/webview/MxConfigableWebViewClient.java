package com.xiaomi.mms.mx.webview;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xiaomi.mms.mx.utils.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MxConfigableWebViewClient
  extends WebViewClient
{
  private UrlChecker mChecker = new UrlChecker();
  private Context mContext;
  private String mLoadedUrl = "";
  private OnViewModeChangeListener mOnViewModeChangeListener;
  private List<IWebViewProcessor> mProcessors = new ArrayList();
  private int mViewMode = 100;
  
  public MxConfigableWebViewClient(Context paramContext)
  {
    mContext = paramContext;
  }
  
  private void changeViewMode(int paramInt)
  {
    if ((mViewMode != paramInt) && (mOnViewModeChangeListener != null) && (mOnViewModeChangeListener.isCanChangeViewMode()))
    {
      mViewMode = paramInt;
      Log.v("MxConfigableWebViewClient", "MLConfigableWebViewClient:changeViewMode:viewMode=" + paramInt);
      mOnViewModeChangeListener.onViewModeChanged(paramInt);
    }
  }
  
  private boolean checkNeedOpenInNewWindow(String paramString, boolean paramBoolean)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!TextUtils.isEmpty(paramString))
    {
      bool1 = bool2;
      if (!TextUtils.isEmpty(mLoadedUrl))
      {
        bool1 = bool2;
        if (!mLoadedUrl.equals(paramString))
        {
          bool1 = bool2;
          if (mViewMode == 100)
          {
            bool1 = bool2;
            if (!paramBoolean)
            {
              WebViewFragment.openNewWindowUrl(mContext, paramString);
              bool1 = true;
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public void SettingProcessors(int paramInt)
  {
    mProcessors.clear();
    if (WebViewPermission.canUseAlertDialog(paramInt)) {
      mProcessors.add(new AlertWindowProcess());
    }
    if (WebViewPermission.canUseMiTalk(paramInt)) {
      mProcessors.add(new MXProcessor(mContext));
    }
  }
  
  public void preProcessBottomBar(WebView paramWebView, String paramString)
  {
    boolean bool = mChecker.isInWhiteList(paramString);
    int i = mChecker.getPermissionOfUrl(paramString);
    WebViewPermission.settingWebView(paramWebView, i);
    SettingProcessors(i);
    if ((TextUtils.isEmpty(mLoadedUrl)) && (bool)) {
      changeViewMode(100);
    }
    do
    {
      return;
      if ((TextUtils.isEmpty(mLoadedUrl)) && (!bool))
      {
        changeViewMode(101);
        return;
      }
    } while ((TextUtils.isEmpty(mLoadedUrl)) || (mViewMode != 100) || (!bool));
    changeViewMode(101);
  }
  
  public boolean preProcessUrl(WebView paramWebView, String paramString)
  {
    if (checkNeedOpenInNewWindow(paramString, mChecker.isInWhiteList(paramString))) {
      return true;
    }
    preProcessBottomBar(paramWebView, paramString);
    mLoadedUrl = paramString;
    if (mProcessors.size() > 0)
    {
      bool = true;
      Iterator localIterator = mProcessors.iterator();
      label54:
      if (!localIterator.hasNext()) {
        return bool;
      }
      IWebViewProcessor localIWebViewProcessor = (IWebViewProcessor)localIterator.next();
      if ((!bool) || (!localIWebViewProcessor.processUrl(paramWebView, paramString))) {
        break label102;
      }
    }
    label102:
    for (boolean bool = true;; bool = false)
    {
      break label54;
      bool = false;
      break;
    }
    return bool;
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    Log.v("MxConfigableWebViewClient", "MxConfigableWebViewClient:shouldOverrideUrlLoading:url=" + paramString);
    if (!preProcessUrl(paramWebView, paramString)) {
      return super.shouldOverrideUrlLoading(paramWebView, paramString);
    }
    return true;
  }
  
  public static abstract interface OnViewModeChangeListener
  {
    public abstract boolean isCanChangeViewMode();
    
    public abstract void onViewModeChanged(int paramInt);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.webview.MxConfigableWebViewClient
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
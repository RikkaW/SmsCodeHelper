package com.xiaomi.mms.mx.webview;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WebViewFragment
  extends Fragment
{
  private String mForwardString;
  private MxWebView.OnCustomActionSetListener mOnCustomActionSetListener;
  private String mOwner;
  private String mOwnerName;
  private String mUrl;
  private MxWebView mWebView;
  
  public static void openNewWindowUrl(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramContext, WebViewActivity.class);
    localIntent.putExtra("extra_url", paramString);
    if (!(paramContext instanceof Activity)) {
      localIntent.setFlags(268435456);
    }
    paramContext.startActivity(localIntent);
  }
  
  public boolean goPrePage()
  {
    return mWebView.goBack();
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    if (!TextUtils.isEmpty(mUrl)) {
      mWebView.loadUrl(mUrl);
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130968677, null);
    mWebView = ((MxWebView)paramLayoutInflater.findViewById(2131820817));
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    mWebView.destroy();
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    paramView = getArguments();
    mUrl = paramView.getString("extra_url");
    mForwardString = paramView.getString("extra_forward_string");
    mOwner = paramView.getString("extra_owner");
    mOwnerName = paramView.getString("extra_owner_name");
    mWebView.setOwner(mOwner, mOwnerName, mForwardString);
    mWebView.setOnCustomActionSetListener(mOnCustomActionSetListener);
  }
  
  public void setOnCustomActionSetListener(MxWebView.OnCustomActionSetListener paramOnCustomActionSetListener)
  {
    mOnCustomActionSetListener = paramOnCustomActionSetListener;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.webview.WebViewFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.xiaomi.mms.mx.webview;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import miui.app.ActionBar;
import miui.app.Activity;

public class WebViewActivity
  extends Activity
{
  private ActionBar mActionBar;
  private MxWebView.OnCustomActionSetListener mOnCustomActionSetListener = new MxWebView.OnCustomActionSetListener()
  {
    public void onLoadingProgressChanged(int paramAnonymousInt)
    {
      if ((paramAnonymousInt > 0) && (paramAnonymousInt < 100)) {}
      for (paramAnonymousInt = 0;; paramAnonymousInt = 4)
      {
        mShuffleView.setVisibility(paramAnonymousInt);
        return;
      }
    }
    
    public void onWebViewTitleChanged(String paramAnonymousString)
    {
      if (!TextUtils.isEmpty(paramAnonymousString)) {
        mActionBar.setTitle(paramAnonymousString);
      }
    }
  };
  private View mShuffleView;
  private WebViewFragment mWebWiewFragment;
  
  public void onAttachFragment(Fragment paramFragment)
  {
    super.onAttachFragment(paramFragment);
    ((WebViewFragment)paramFragment).setOnCustomActionSetListener(mOnCustomActionSetListener);
  }
  
  public void onBackPressed()
  {
    if (!mWebWiewFragment.goPrePage()) {
      super.onBackPressed();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968720);
    paramBundle = getFragmentManager();
    FragmentTransaction localFragmentTransaction = paramBundle.beginTransaction();
    mWebWiewFragment = ((WebViewFragment)paramBundle.findFragmentByTag("WebViewFragment"));
    if (mWebWiewFragment == null)
    {
      mWebWiewFragment = new WebViewFragment();
      mWebWiewFragment.setArguments(getIntent().getExtras());
      localFragmentTransaction.add(2131820901, mWebWiewFragment, "WebViewFragment");
    }
    mActionBar = getActionBar();
    mActionBar.setTitle("  ");
    mActionBar.setHomeButtonEnabled(true);
    mActionBar.setCustomView(2130968667);
    mActionBar.setDisplayShowCustomEnabled(true);
    mShuffleView = mActionBar.getCustomView().findViewById(2131820795);
    localFragmentTransaction.commit();
    paramBundle.executePendingTransactions();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return false;
    }
    finish();
    return true;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.webview.WebViewActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
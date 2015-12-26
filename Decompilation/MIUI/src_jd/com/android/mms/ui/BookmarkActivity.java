package com.android.mms.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import miui.app.ActionBar;
import miui.app.Activity;

public class BookmarkActivity
  extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968588);
    paramBundle = getActionBar();
    FragmentManager localFragmentManager = getFragmentManager();
    FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
    if ((BookmarkFragment)localFragmentManager.findFragmentByTag("BookmarkFragment") == null) {
      localFragmentTransaction.add(2131820565, new BookmarkFragment(), "BookmarkFragment");
    }
    localFragmentTransaction.commitAllowingStateLoss();
    localFragmentManager.executePendingTransactions();
    paramBundle.setTitle(2131362091);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default: 
      return super.onKeyDown(paramInt, paramKeyEvent);
    }
    MessageUtils.launchMessagePreference(this);
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BookmarkActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
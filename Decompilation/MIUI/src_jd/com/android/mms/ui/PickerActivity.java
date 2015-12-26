package com.android.mms.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import miui.app.ActionBar;
import miui.app.Activity;

public class PickerActivity
  extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968688);
    paramBundle = getActionBar();
    int i = getIntent().getIntExtra("pick_type", 0);
    switch (i)
    {
    default: 
      throw new IllegalArgumentException("Unknown pick type " + i);
    case 1: 
      localFragmentManager = getFragmentManager();
      localFragmentTransaction = localFragmentManager.beginTransaction();
      if ((FestivalFragment)localFragmentManager.findFragmentByTag("FestivalFragment") == null) {
        localFragmentTransaction.add(2131820843, new FestivalFragment(), "FestivalFragment");
      }
      localFragmentTransaction.commitAllowingStateLoss();
      localFragmentManager.executePendingTransactions();
      paramBundle.setTitle(2131362044);
      return;
    }
    FragmentManager localFragmentManager = getFragmentManager();
    FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
    if ((BookmarkFragment)localFragmentManager.findFragmentByTag("BookmarkFragment") == null) {
      localFragmentTransaction.add(2131820843, new BookmarkFragment(), "BookmarkFragment");
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
  
  void returnPickerResult(Intent paramIntent)
  {
    setResult(-1, paramIntent);
    finish();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PickerActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
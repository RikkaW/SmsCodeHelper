package com.android.mms.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MiuiSettings.System;
import android.provider.Settings.Secure;
import android.security.ChooseLockSettingsHelper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.android.mms.data.Contact;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.MiStatSdkHelper;
import com.xiaomi.mms.mx.fw.HmsChannelService;
import com.xiaomi.mms.mx.fw.HmsPushBroadcastReceiver;
import com.xiaomi.mms.transaction.MxActivateService;
import miui.app.Activity;
import miui.view.SearchActionMode;
import miui.view.SearchActionMode.Callback;

public class MmsTabActivity
  extends Activity
{
  private ChooseLockSettingsHelper mChooseLockSettingsHelper;
  private ConversationFragment mConversationFragment;
  private SearchActionMode mSearchActionMode;
  private SearchActionMode.Callback mSearchActionModeCallback = new SearchActionMode.Callback()
  {
    public boolean onActionItemClicked(ActionMode paramAnonymousActionMode, MenuItem paramAnonymousMenuItem)
    {
      return true;
    }
    
    public boolean onCreateActionMode(ActionMode paramAnonymousActionMode, Menu paramAnonymousMenu)
    {
      MiStatSdkHelper.recordSearch("search_create");
      if (mSearchFragment == null) {
        MmsTabActivity.this.updateSearchFragment(true, false, true);
      }
      mConversationFragment.forceHideHintView();
      paramAnonymousActionMode = (SearchActionMode)paramAnonymousActionMode;
      paramAnonymousActionMode.setAnchorView(mSearchView);
      paramAnonymousActionMode.setAnimateView(mConversationFragment.getView());
      paramAnonymousActionMode.setResultView(mSearchFragment.getView());
      paramAnonymousActionMode.getSearchInput().addTextChangedListener(mSearchTextWatcher);
      MmsTabActivity.this.setSearchMode(true);
      return true;
    }
    
    public void onDestroyActionMode(ActionMode paramAnonymousActionMode)
    {
      ((SearchActionMode)paramAnonymousActionMode).getSearchInput().removeTextChangedListener(mSearchTextWatcher);
      MmsTabActivity.access$602(MmsTabActivity.this, null);
      if (mSearchFragment != null) {
        MmsTabActivity.this.updateSearchFragment(false, true, false);
      }
      new Handler().post(new Runnable()
      {
        public void run()
        {
          MmsTabActivity.access$002(MmsTabActivity.this, null);
        }
      });
      mConversationFragment.restoreHintView();
      onBackPressed();
    }
    
    public boolean onPrepareActionMode(ActionMode paramAnonymousActionMode, Menu paramAnonymousMenu)
    {
      return true;
    }
  };
  private SearchFragment mSearchFragment;
  private boolean mSearchMode = false;
  private TextWatcher mSearchTextWatcher = new TextWatcher()
  {
    public void afterTextChanged(Editable paramAnonymousEditable) {}
    
    public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    
    public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      String str = null;
      if (paramAnonymousCharSequence != null) {
        str = paramAnonymousCharSequence.toString();
      }
      if (mSearchFragment == null) {}
      do
      {
        return;
        if (!TextUtils.isEmpty(str)) {
          break;
        }
      } while (mSearchFragment.isHidden());
      MmsTabActivity.this.updateSearchFragment(false, false, false);
      mSearchFragment.selectToFirst();
      return;
      if (mSearchFragment.isHidden()) {
        MmsTabActivity.this.updateSearchFragment(false, false, true);
      }
      mSearchFragment.query(str);
    }
  };
  private View mSearchView;
  
  private void createConversationFragment()
  {
    FragmentManager localFragmentManager = getFragmentManager();
    FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
    mConversationFragment = ((ConversationFragment)localFragmentManager.findFragmentByTag("ConversationFragment"));
    if (mConversationFragment == null)
    {
      mConversationFragment = new ConversationFragment();
      localFragmentTransaction.add(2131820794, mConversationFragment, "ConversationFragment");
    }
    mConversationFragment.createFirstQuery(this);
    mSearchFragment = ((SearchFragment)localFragmentManager.findFragmentByTag("SearchFragment"));
    if (mSearchFragment != null)
    {
      Log.v("MmsTabActivity", "onCreate remove seach fragment");
      localFragmentTransaction.remove(mSearchFragment);
    }
    localFragmentTransaction.commitAllowingStateLoss();
    localFragmentManager.executePendingTransactions();
    mSearchFragment = null;
  }
  
  private void ensureHmsChannelConnected()
  {
    if ((!HmsPushBroadcastReceiver.isChannelConnected()) && (!HmsChannelService.isDeviceLoginOut(this))) {
      HmsChannelService.tryOpenChannel(this);
    }
  }
  
  private void ensurePushConnection()
  {
    int j = MSimUtils.getMultiSimCount();
    int i = 0;
    while (i < j)
    {
      if ((MxActivateService.isMxEnabled(this, i)) && (!MxActivateService.isBusy(i))) {
        MxActivateService.enableMx(this, i, true, false);
      }
      i += 1;
    }
  }
  
  private void setSearchMode(boolean paramBoolean)
  {
    if (mSearchMode != paramBoolean)
    {
      mSearchMode = paramBoolean;
      if (!mSearchMode) {
        mSearchTextWatcher.onTextChanged(null, 0, 0, 0);
      }
    }
  }
  
  private void updateSearchFragment(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    FragmentManager localFragmentManager = getFragmentManager();
    FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
    if (paramBoolean1)
    {
      mSearchFragment = ((SearchFragment)localFragmentManager.findFragmentByTag("SearchFragment"));
      if (mSearchFragment == null)
      {
        mSearchFragment = new SearchFragment(false);
        localFragmentTransaction.add(16908290, mSearchFragment, "SearchFragment");
      }
      localFragmentTransaction.hide(mSearchFragment);
    }
    for (;;)
    {
      localFragmentTransaction.commitAllowingStateLoss();
      localFragmentManager.executePendingTransactions();
      return;
      if (paramBoolean2) {
        localFragmentTransaction.remove(mSearchFragment);
      } else if (paramBoolean3) {
        localFragmentTransaction.show(mSearchFragment);
      } else {
        localFragmentTransaction.hide(mSearchFragment);
      }
    }
  }
  
  public boolean enterPrivateActivity()
  {
    if (mChooseLockSettingsHelper == null) {
      mChooseLockSettingsHelper = new ChooseLockSettingsHelper(this, 1);
    }
    if (!mChooseLockSettingsHelper.isPrivacyModeEnabled())
    {
      if (mChooseLockSettingsHelper.isPrivateSmsEnabled())
      {
        Intent localIntent = new Intent();
        localIntent.setClassName("com.android.settings", "com.android.settings.ConfirmSmsLockPattern");
        localIntent.setAction("android.app.action.CONFIRM_SMS_PASSWORD");
        startActivityForResult(localIntent, 0);
        return true;
      }
      startActivity(new Intent(this, PrivateConversationListActivity.class));
      return true;
    }
    return false;
  }
  
  public void finish()
  {
    if (isTaskRoot())
    {
      moveTaskToBack(false);
      return;
    }
    super.finish();
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt2 == -1) && (paramInt1 == 0)) {
      startActivity(new Intent(this, PrivateConversationListActivity.class));
    }
  }
  
  public void onBackPressed()
  {
    if (mSearchMode)
    {
      setSearchMode(false);
      return;
    }
    super.onBackPressed();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968666);
    createConversationFragment();
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
  
  protected void onResume()
  {
    super.onResume();
    NewMessagePopupActivity.dismiss(this);
    ensurePushConnection();
    Settings.Secure.putString(getContentResolver(), "sms_default_application", "com.android.mms");
    ensureHmsChannelConnected();
  }
  
  protected void onStart()
  {
    super.onStart();
    if (!MiuiSettings.System.isSimpleMode(this))
    {
      if (!MiuiSettings.System.useWordPhoto(this)) {
        Contact.requestClear(true);
      }
      return;
    }
    Contact.requestClear(false);
  }
  
  public void startSearchMode(View paramView1, View paramView2)
  {
    if (mSearchActionMode == null)
    {
      mSearchView = paramView1;
      mSearchActionMode = ((SearchActionMode)startActionMode(mSearchActionModeCallback));
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MmsTabActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.android.mms.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import miui.R.drawable;
import miui.app.Activity;
import miui.view.SearchActionMode;
import miui.view.SearchActionMode.Callback;

public class PrivateConversationListActivity
  extends Activity
{
  private Activity mActivity;
  private ConversationFragment mConversationFragment;
  private BroadcastReceiver mFinishReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      paramAnonymousContext = paramAnonymousIntent.getAction();
      if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(paramAnonymousContext)) {
        if ("homekey".equals(paramAnonymousIntent.getStringExtra("reason"))) {
          finish();
        }
      }
      while (!"android.intent.action.SCREEN_OFF".equals(paramAnonymousContext)) {
        return;
      }
      finish();
    }
  };
  private SearchActionMode mSearchActionMode;
  private SearchActionMode.Callback mSearchActionModeCallback = new SearchActionMode.Callback()
  {
    public boolean onActionItemClicked(ActionMode paramAnonymousActionMode, MenuItem paramAnonymousMenuItem)
    {
      return true;
    }
    
    public boolean onCreateActionMode(ActionMode paramAnonymousActionMode, Menu paramAnonymousMenu)
    {
      if (mSearchFragment == null) {
        PrivateConversationListActivity.this.updateSearchFragment(true, false, true);
      }
      paramAnonymousActionMode = (SearchActionMode)paramAnonymousActionMode;
      paramAnonymousActionMode.setAnchorView(mSearchView);
      paramAnonymousActionMode.setAnimateView(mConversationFragment.getView());
      paramAnonymousActionMode.setResultView(mSearchFragment.getView());
      paramAnonymousActionMode.getSearchInput().addTextChangedListener(mSearchTextWatcher);
      PrivateConversationListActivity.this.setSearchMode(true);
      return true;
    }
    
    public void onDestroyActionMode(ActionMode paramAnonymousActionMode)
    {
      ((SearchActionMode)paramAnonymousActionMode).getSearchInput().removeTextChangedListener(mSearchTextWatcher);
      PrivateConversationListActivity.access$602(PrivateConversationListActivity.this, null);
      if (mSearchFragment != null) {
        PrivateConversationListActivity.this.updateSearchFragment(false, true, false);
      }
      PrivateConversationListActivity.access$002(PrivateConversationListActivity.this, null);
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
      PrivateConversationListActivity.this.updateSearchFragment(false, false, false);
      return;
      if (mSearchFragment.isHidden()) {
        PrivateConversationListActivity.this.updateSearchFragment(false, false, true);
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
      mConversationFragment = new PrivateConversationFragment();
      localFragmentTransaction.add(2131820849, mConversationFragment, "ConversationFragment");
    }
    mConversationFragment.createFirstQuery(this);
    localFragmentTransaction.commitAllowingStateLoss();
    localFragmentManager.executePendingTransactions();
  }
  
  private void registerFinishReceiver()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
    localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
    registerReceiver(mFinishReceiver, localIntentFilter);
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
  
  private void unRegisterFinishReceiver()
  {
    unregisterReceiver(mFinishReceiver);
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
        mSearchFragment = new SearchFragment(true);
        localFragmentTransaction.add(2131820849, mSearchFragment, "SearchFragment");
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
  
  public void onBackPressed()
  {
    if (mSearchMode)
    {
      setSearchMode(false);
      return;
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968692);
    mActivity = this;
    createConversationFragment();
    registerFinishReceiver();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return true;
  }
  
  protected void onDestroy()
  {
    unRegisterFinishReceiver();
    super.onDestroy();
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
    case 16908332: 
      if (mSearchMode) {
        onBackPressed();
      }
      break;
    }
    for (;;)
    {
      return true;
      finish();
      continue;
      startActivity(new Intent(mActivity, PrivatePreferenceActivity.class));
    }
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    paramMenu.add(0, 0, 0, 2131362218).setIcon(R.drawable.action_button_setting_light).setShowAsAction(1);
    return true;
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
 * Qualified Name:     com.android.mms.ui.PrivateConversationListActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
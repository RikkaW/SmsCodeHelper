/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentTransaction
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.os.Bundle
 *  android.text.Editable
 *  android.text.TextUtils
 *  android.text.TextWatcher
 *  android.view.ActionMode
 *  android.view.ActionMode$Callback
 *  android.view.KeyEvent
 *  android.view.Menu
 *  android.view.MenuItem
 *  android.view.View
 *  android.widget.EditText
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  miui.R
 *  miui.R$drawable
 *  miui.app.Activity
 *  miui.view.SearchActionMode
 *  miui.view.SearchActionMode$Callback
 */
package com.android.mms.ui;

import android.app.Fragment;
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
import com.android.mms.ui.ConversationFragment;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.PrivateConversationFragment;
import com.android.mms.ui.PrivatePreferenceActivity;
import com.android.mms.ui.SearchFragment;
import miui.R;
import miui.app.Activity;
import miui.view.SearchActionMode;

public class PrivateConversationListActivity
extends Activity {
    private Activity mActivity;
    private ConversationFragment mConversationFragment;
    private BroadcastReceiver mFinishReceiver;
    private SearchActionMode mSearchActionMode;
    private SearchActionMode.Callback mSearchActionModeCallback;
    private SearchFragment mSearchFragment;
    private boolean mSearchMode = false;
    private TextWatcher mSearchTextWatcher;
    private View mSearchView;

    public PrivateConversationListActivity() {
        this.mSearchActionModeCallback = new SearchActionMode.Callback(){

            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return true;
            }

            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                if (PrivateConversationListActivity.this.mSearchFragment == null) {
                    PrivateConversationListActivity.this.updateSearchFragment(true, false, true);
                }
                actionMode = (SearchActionMode)actionMode;
                actionMode.setAnchorView(PrivateConversationListActivity.this.mSearchView);
                actionMode.setAnimateView(PrivateConversationListActivity.this.mConversationFragment.getView());
                actionMode.setResultView(PrivateConversationListActivity.this.mSearchFragment.getView());
                actionMode.getSearchInput().addTextChangedListener(PrivateConversationListActivity.this.mSearchTextWatcher);
                PrivateConversationListActivity.this.setSearchMode(true);
                return true;
            }

            public void onDestroyActionMode(ActionMode actionMode) {
                ((SearchActionMode)actionMode).getSearchInput().removeTextChangedListener(PrivateConversationListActivity.this.mSearchTextWatcher);
                PrivateConversationListActivity.this.mSearchActionMode = null;
                if (PrivateConversationListActivity.this.mSearchFragment != null) {
                    PrivateConversationListActivity.this.updateSearchFragment(false, true, false);
                }
                PrivateConversationListActivity.this.mSearchFragment = null;
                PrivateConversationListActivity.this.onBackPressed();
            }

            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return true;
            }
        };
        this.mSearchTextWatcher = new TextWatcher(){

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }

            /*
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
                String string2 = null;
                if (charSequence != null) {
                    string2 = charSequence.toString();
                }
                if (PrivateConversationListActivity.this.mSearchFragment == null) {
                    return;
                }
                if (TextUtils.isEmpty((CharSequence)string2)) {
                    if (PrivateConversationListActivity.this.mSearchFragment.isHidden()) return;
                    PrivateConversationListActivity.this.updateSearchFragment(false, false, false);
                    return;
                }
                if (PrivateConversationListActivity.this.mSearchFragment.isHidden()) {
                    PrivateConversationListActivity.this.updateSearchFragment(false, false, true);
                }
                PrivateConversationListActivity.this.mSearchFragment.query(string2);
            }
        };
        this.mFinishReceiver = new BroadcastReceiver(){

            /*
             * Enabled aggressive block sorting
             */
            public void onReceive(Context object, Intent intent) {
                object = intent.getAction();
                if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(object)) {
                    if (!"homekey".equals((Object)intent.getStringExtra("reason"))) return;
                    {
                        PrivateConversationListActivity.this.finish();
                        return;
                    }
                } else {
                    if (!"android.intent.action.SCREEN_OFF".equals(object)) return;
                    {
                        PrivateConversationListActivity.this.finish();
                        return;
                    }
                }
            }
        };
    }

    private void createConversationFragment() {
        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        this.mConversationFragment = (ConversationFragment)fragmentManager.findFragmentByTag("ConversationFragment");
        if (this.mConversationFragment == null) {
            this.mConversationFragment = new PrivateConversationFragment();
            fragmentTransaction.add(2131820849, (Fragment)this.mConversationFragment, "ConversationFragment");
        }
        this.mConversationFragment.createFirstQuery((Context)this);
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }

    private void registerFinishReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        this.registerReceiver(this.mFinishReceiver, intentFilter);
    }

    private void setSearchMode(boolean bl) {
        if (this.mSearchMode != bl) {
            this.mSearchMode = bl;
            if (!this.mSearchMode) {
                this.mSearchTextWatcher.onTextChanged(null, 0, 0, 0);
            }
        }
    }

    private void unRegisterFinishReceiver() {
        this.unregisterReceiver(this.mFinishReceiver);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateSearchFragment(boolean bl, boolean bl2, boolean bl3) {
        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (bl) {
            this.mSearchFragment = (SearchFragment)fragmentManager.findFragmentByTag("SearchFragment");
            if (this.mSearchFragment == null) {
                this.mSearchFragment = new SearchFragment(true);
                fragmentTransaction.add(2131820849, (Fragment)this.mSearchFragment, "SearchFragment");
            }
            fragmentTransaction.hide((Fragment)this.mSearchFragment);
        } else if (bl2) {
            fragmentTransaction.remove((Fragment)this.mSearchFragment);
        } else if (bl3) {
            fragmentTransaction.show((Fragment)this.mSearchFragment);
        } else {
            fragmentTransaction.hide((Fragment)this.mSearchFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }

    public void onBackPressed() {
        if (this.mSearchMode) {
            this.setSearchMode(false);
            return;
        }
        super.onBackPressed();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968692);
        this.mActivity = this;
        this.createConversationFragment();
        this.registerFinishReceiver();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    protected void onDestroy() {
        this.unRegisterFinishReceiver();
        super.onDestroy();
    }

    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        switch (n) {
            default: {
                return super.onKeyDown(n, keyEvent);
            }
            case 82: 
        }
        MessageUtils.launchMessagePreference((Context)this);
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return false;
            }
            case 16908332: {
                if (this.mSearchMode) {
                    this.onBackPressed();
                    do {
                        return true;
                        break;
                    } while (true);
                }
                this.finish();
                return true;
            }
            case 0: 
        }
        this.startActivity(new Intent((Context)this.mActivity, (Class)PrivatePreferenceActivity.class));
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        menu.add(0, 0, 0, 2131362218).setIcon(R.drawable.action_button_setting_light).setShowAsAction(1);
        return true;
    }

    public void startSearchMode(View view, View view2) {
        if (this.mSearchActionMode == null) {
            this.mSearchView = view;
            this.mSearchActionMode = (SearchActionMode)this.startActionMode((ActionMode.Callback)this.mSearchActionModeCallback);
        }
    }

}


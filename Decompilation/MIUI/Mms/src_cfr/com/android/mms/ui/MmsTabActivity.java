/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentTransaction
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Configuration
 *  android.os.Bundle
 *  android.os.Handler
 *  android.provider.MiuiSettings
 *  android.provider.MiuiSettings$System
 *  android.provider.Settings
 *  android.provider.Settings$Secure
 *  android.security.ChooseLockSettingsHelper
 *  android.text.Editable
 *  android.text.TextUtils
 *  android.text.TextWatcher
 *  android.util.Log
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
 *  miui.app.Activity
 *  miui.view.SearchActionMode
 *  miui.view.SearchActionMode$Callback
 */
package com.android.mms.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MiuiSettings;
import android.provider.Settings;
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
import com.android.mms.ui.ConversationFragment;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.NewMessagePopupActivity;
import com.android.mms.ui.PrivateConversationListActivity;
import com.android.mms.ui.SearchFragment;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.MiStatSdkHelper;
import com.xiaomi.mms.mx.fw.HmsChannelService;
import com.xiaomi.mms.mx.fw.HmsPushBroadcastReceiver;
import com.xiaomi.mms.transaction.MxActivateService;
import miui.app.Activity;
import miui.view.SearchActionMode;

public class MmsTabActivity
extends Activity {
    private ChooseLockSettingsHelper mChooseLockSettingsHelper;
    private ConversationFragment mConversationFragment;
    private SearchActionMode mSearchActionMode;
    private SearchActionMode.Callback mSearchActionModeCallback;
    private SearchFragment mSearchFragment;
    private boolean mSearchMode = false;
    private TextWatcher mSearchTextWatcher;
    private View mSearchView;

    public MmsTabActivity() {
        this.mSearchActionModeCallback = new SearchActionMode.Callback(){

            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return true;
            }

            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MiStatSdkHelper.recordSearch("search_create");
                if (MmsTabActivity.this.mSearchFragment == null) {
                    MmsTabActivity.this.updateSearchFragment(true, false, true);
                }
                MmsTabActivity.this.mConversationFragment.forceHideHintView();
                actionMode = (SearchActionMode)actionMode;
                actionMode.setAnchorView(MmsTabActivity.this.mSearchView);
                actionMode.setAnimateView(MmsTabActivity.this.mConversationFragment.getView());
                actionMode.setResultView(MmsTabActivity.this.mSearchFragment.getView());
                actionMode.getSearchInput().addTextChangedListener(MmsTabActivity.this.mSearchTextWatcher);
                MmsTabActivity.this.setSearchMode(true);
                return true;
            }

            public void onDestroyActionMode(ActionMode actionMode) {
                ((SearchActionMode)actionMode).getSearchInput().removeTextChangedListener(MmsTabActivity.this.mSearchTextWatcher);
                MmsTabActivity.this.mSearchActionMode = null;
                if (MmsTabActivity.this.mSearchFragment != null) {
                    MmsTabActivity.this.updateSearchFragment(false, true, false);
                }
                new Handler().post(new Runnable(){

                    @Override
                    public void run() {
                        MmsTabActivity.this.mSearchFragment = null;
                    }
                });
                MmsTabActivity.this.mConversationFragment.restoreHintView();
                MmsTabActivity.this.onBackPressed();
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
                if (MmsTabActivity.this.mSearchFragment == null) {
                    return;
                }
                if (TextUtils.isEmpty((CharSequence)string2)) {
                    if (MmsTabActivity.this.mSearchFragment.isHidden()) return;
                    MmsTabActivity.this.updateSearchFragment(false, false, false);
                    MmsTabActivity.this.mSearchFragment.selectToFirst();
                    return;
                }
                if (MmsTabActivity.this.mSearchFragment.isHidden()) {
                    MmsTabActivity.this.updateSearchFragment(false, false, true);
                }
                MmsTabActivity.this.mSearchFragment.query(string2);
            }
        };
    }

    private void createConversationFragment() {
        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        this.mConversationFragment = (ConversationFragment)fragmentManager.findFragmentByTag("ConversationFragment");
        if (this.mConversationFragment == null) {
            this.mConversationFragment = new ConversationFragment();
            fragmentTransaction.add(2131820794, (Fragment)this.mConversationFragment, "ConversationFragment");
        }
        this.mConversationFragment.createFirstQuery((Context)this);
        this.mSearchFragment = (SearchFragment)fragmentManager.findFragmentByTag("SearchFragment");
        if (this.mSearchFragment != null) {
            Log.v((String)"MmsTabActivity", (String)"onCreate remove seach fragment");
            fragmentTransaction.remove((Fragment)this.mSearchFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
        this.mSearchFragment = null;
    }

    private void ensureHmsChannelConnected() {
        if (!HmsPushBroadcastReceiver.isChannelConnected() && !HmsChannelService.isDeviceLoginOut((Context)this)) {
            HmsChannelService.tryOpenChannel((Context)this);
        }
    }

    private void ensurePushConnection() {
        int n = MSimUtils.getMultiSimCount();
        for (int i = 0; i < n; ++i) {
            if (!MxActivateService.isMxEnabled((Context)this, i) || MxActivateService.isBusy(i)) continue;
            MxActivateService.enableMx((Context)this, i, true, false);
        }
    }

    private void setSearchMode(boolean bl) {
        if (this.mSearchMode != bl) {
            this.mSearchMode = bl;
            if (!this.mSearchMode) {
                this.mSearchTextWatcher.onTextChanged(null, 0, 0, 0);
            }
        }
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
                this.mSearchFragment = new SearchFragment(false);
                fragmentTransaction.add(16908290, (Fragment)this.mSearchFragment, "SearchFragment");
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

    public boolean enterPrivateActivity() {
        if (this.mChooseLockSettingsHelper == null) {
            this.mChooseLockSettingsHelper = new ChooseLockSettingsHelper((android.app.Activity)this, 1);
        }
        if (!this.mChooseLockSettingsHelper.isPrivacyModeEnabled()) {
            if (this.mChooseLockSettingsHelper.isPrivateSmsEnabled()) {
                Intent intent = new Intent();
                intent.setClassName("com.android.settings", "com.android.settings.ConfirmSmsLockPattern");
                intent.setAction("android.app.action.CONFIRM_SMS_PASSWORD");
                this.startActivityForResult(intent, 0);
                return true;
            }
            this.startActivity(new Intent((Context)this, (Class)PrivateConversationListActivity.class));
            return true;
        }
        return false;
    }

    public void finish() {
        if (this.isTaskRoot()) {
            this.moveTaskToBack(false);
            return;
        }
        super.finish();
    }

    public void onActivityResult(int n, int n2, Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n2 == -1 && n == 0) {
            this.startActivity(new Intent((Context)this, (Class)PrivateConversationListActivity.class));
        }
    }

    public void onBackPressed() {
        if (this.mSearchMode) {
            this.setSearchMode(false);
            return;
        }
        super.onBackPressed();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968666);
        this.createConversationFragment();
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

    protected void onResume() {
        super.onResume();
        NewMessagePopupActivity.dismiss((Context)this);
        this.ensurePushConnection();
        Settings.Secure.putString((ContentResolver)this.getContentResolver(), (String)"sms_default_application", (String)"com.android.mms");
        this.ensureHmsChannelConnected();
    }

    protected void onStart() {
        super.onStart();
        if (!MiuiSettings.System.isSimpleMode((Context)this)) {
            if (!MiuiSettings.System.useWordPhoto((Context)this)) {
                Contact.requestClear(true);
            }
            return;
        }
        Contact.requestClear(false);
    }

    public void startSearchMode(View view, View view2) {
        if (this.mSearchActionMode == null) {
            this.mSearchView = view;
            this.mSearchActionMode = (SearchActionMode)this.startActionMode((ActionMode.Callback)this.mSearchActionModeCallback);
        }
    }

}


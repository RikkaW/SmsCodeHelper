/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentTransaction
 *  android.content.Context
 *  android.os.Bundle
 *  android.view.KeyEvent
 *  java.lang.String
 *  miui.app.ActionBar
 *  miui.app.Activity
 */
package com.android.mms.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import com.android.mms.ui.BookmarkFragment;
import com.android.mms.ui.MessageUtils;
import miui.app.ActionBar;
import miui.app.Activity;

public class BookmarkActivity
extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968588);
        bundle = this.getActionBar();
        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if ((BookmarkFragment)fragmentManager.findFragmentByTag("BookmarkFragment") == null) {
            fragmentTransaction.add(2131820565, (Fragment)new BookmarkFragment(), "BookmarkFragment");
        }
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
        bundle.setTitle(2131362091);
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
}


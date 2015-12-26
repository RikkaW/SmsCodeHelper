/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentTransaction
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.view.KeyEvent
 *  android.view.MenuItem
 *  java.lang.String
 *  miui.app.ActionBar
 *  miui.app.Activity
 */
package com.android.mms.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import com.android.mms.ui.BookmarkFragment;
import com.android.mms.ui.FestivalFragment;
import com.android.mms.ui.MessageUtils;
import miui.app.ActionBar;
import miui.app.Activity;

public class PickerActivity
extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968688);
        bundle = this.getActionBar();
        int n = this.getIntent().getIntExtra("pick_type", 0);
        switch (n) {
            default: {
                throw new IllegalArgumentException("Unknown pick type " + n);
            }
            case 1: {
                FragmentManager fragmentManager = this.getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if ((FestivalFragment)fragmentManager.findFragmentByTag("FestivalFragment") == null) {
                    fragmentTransaction.add(2131820843, (Fragment)new FestivalFragment(), "FestivalFragment");
                }
                fragmentTransaction.commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
                bundle.setTitle(2131362044);
                return;
            }
            case 0: 
        }
        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if ((BookmarkFragment)fragmentManager.findFragmentByTag("BookmarkFragment") == null) {
            fragmentTransaction.add(2131820843, (Fragment)new BookmarkFragment(), "BookmarkFragment");
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

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return false;
            }
            case 16908332: 
        }
        this.finish();
        return true;
    }

    void returnPickerResult(Intent intent) {
        this.setResult(-1, intent);
        this.finish();
    }
}


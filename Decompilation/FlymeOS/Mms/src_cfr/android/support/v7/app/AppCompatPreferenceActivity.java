/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.preference.PreferenceActivity
 *  android.preference.PreferenceScreen
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.ListView
 *  java.lang.Object
 */
package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.appcompat.R;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class AppCompatPreferenceActivity
extends PreferenceActivity
implements TaskStackBuilder.SupportParentable,
ActionBarDrawerToggle.DelegateProvider,
AppCompatCallback {
    private AppCompatDelegate mDelegate;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void mzResetListViewPaddingTop() {
        int n2;
        ListView listView;
        int n3;
        n3 = this.getResources().getDimensionPixelSize(R.dimen.mz_action_bar_default_height);
        n2 = this.getResources().getDimensionPixelSize(R.dimen.status_bar_height);
        this.getPreferenceScreen();
        listView = null;
        try {
            ListView listView2;
            listView = listView2 = this.getListView();
        }
        catch (Exception var5_5) {}
        if (listView != null) {
            int n4 = listView.getPaddingBottom();
            listView.setPadding(listView.getPaddingLeft(), n3 + listView.getPaddingTop() + n2, listView.getPaddingRight(), n4);
            listView.setClipToPadding(false);
            listView.setScrollBarStyle(0);
        }
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.getDelegate().addContentView(view, layoutParams);
    }

    public AppCompatDelegate getDelegate() {
        if (this.mDelegate == null) {
            this.mDelegate = AppCompatDelegate.create((Activity)this, (AppCompatCallback)this);
        }
        return this.mDelegate;
    }

    @Nullable
    @Override
    public ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
        return this.getDelegate().getDrawerToggleDelegate();
    }

    public MenuInflater getMenuInflater() {
        return this.getDelegate().getMenuInflater();
    }

    public ActionBar getSupportActionBar() {
        return this.getDelegate().getSupportActionBar();
    }

    @Override
    public Intent getSupportParentActivityIntent() {
        return NavUtils.getParentActivityIntent((Activity)this);
    }

    public void invalidateOptionsMenu() {
        this.getDelegate().invalidateOptionsMenu();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.getDelegate().onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle) {
        this.getDelegate().installViewFactory();
        this.getDelegate().onCreate(bundle);
        super.onCreate(bundle);
        this.mzResetListViewPaddingTop();
    }

    public void onCreateSupportNavigateUpTaskStack(TaskStackBuilder taskStackBuilder) {
        taskStackBuilder.addParentStack((Activity)this);
    }

    public void onDestroy() {
        super.onDestroy();
        this.getDelegate().onDestroy();
    }

    public final boolean onMenuItemSelected(int n2, MenuItem menuItem) {
        if (super.onMenuItemSelected(n2, menuItem)) {
            return true;
        }
        ActionBar actionBar = this.getSupportActionBar();
        if (menuItem.getItemId() == 16908332 && actionBar != null && (actionBar.getDisplayOptions() & 4) != 0) {
            return this.onSupportNavigateUp();
        }
        return false;
    }

    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.getDelegate().onPostCreate(bundle);
    }

    protected void onPostResume() {
        super.onPostResume();
        this.getDelegate().onPostResume();
    }

    public void onPrepareSupportNavigateUpTaskStack(TaskStackBuilder taskStackBuilder) {
    }

    public void onStop() {
        super.onStop();
        this.getDelegate().onStop();
    }

    @Override
    public void onSupportActionModeFinished(ActionMode actionMode) {
    }

    @Override
    public void onSupportActionModeStarted(ActionMode actionMode) {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onSupportNavigateUp() {
        Object object = this.getSupportParentActivityIntent();
        if (object == null) return false;
        if (this.supportShouldUpRecreateTask((Intent)object)) {
            object = TaskStackBuilder.create((Context)this);
            this.onCreateSupportNavigateUpTaskStack((TaskStackBuilder)object);
            this.onPrepareSupportNavigateUpTaskStack((TaskStackBuilder)object);
            object.startActivities();
            try {
                ActivityCompat.finishAffinity((Activity)this);
            }
            catch (IllegalStateException var1_2) {
                this.finish();
                return true;
            }
            do {
                return true;
                break;
            } while (true);
        }
        this.supportNavigateUpTo((Intent)object);
        return true;
    }

    protected void onTitleChanged(CharSequence charSequence, int n2) {
        super.onTitleChanged(charSequence, n2);
        this.getDelegate().setTitle(charSequence);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }

    public void setContentView(int n2) {
        this.getDelegate().setContentView(n2);
    }

    public void setContentView(View view) {
        this.getDelegate().setContentView(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.getDelegate().setContentView(view, layoutParams);
    }

    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        this.getDelegate().setSupportActionBar(toolbar);
    }

    @Deprecated
    public void setSupportProgress(int n2) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminate(boolean bl2) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminateVisibility(boolean bl2) {
    }

    @Deprecated
    public void setSupportProgressBarVisibility(boolean bl2) {
    }

    public void setUiOptions(int n2) {
        this.getDelegate().setUiOptions(n2);
    }

    public ActionMode startSupportActionMode(ActionMode.Callback callback) {
        return this.getDelegate().startSupportActionMode(callback);
    }

    public void supportNavigateUpTo(Intent intent) {
        NavUtils.navigateUpTo((Activity)this, intent);
    }

    public boolean supportRequestWindowFeature(int n2) {
        return this.getDelegate().requestWindowFeature(n2);
    }

    public boolean supportShouldUpRecreateTask(Intent intent) {
        return NavUtils.shouldUpRecreateTask((Activity)this, intent);
    }
}


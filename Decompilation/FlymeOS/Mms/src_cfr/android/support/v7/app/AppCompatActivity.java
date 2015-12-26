/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.FragmentManager
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Configuration
 *  android.os.Bundle
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  java.lang.Object
 */
package android.support.v7.app;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class AppCompatActivity
extends FragmentActivity
implements TaskStackBuilder.SupportParentable,
ActionBarDrawerToggle.DelegateProvider,
AppCompatCallback {
    private AppCompatDelegate mDelegate;

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.getDelegate().addContentView(view, layoutParams);
    }

    public AppCompatDelegate getDelegate() {
        if (this.mDelegate == null) {
            this.mDelegate = AppCompatDelegate.create(this, (AppCompatCallback)this);
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

    @Nullable
    public ActionBar getSupportActionBar() {
        return this.getDelegate().getSupportActionBar();
    }

    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        return NavUtils.getParentActivityIntent(this);
    }

    public void invalidateOptionsMenu() {
        this.getDelegate().invalidateOptionsMenu();
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.getDelegate().onConfigurationChanged(configuration);
    }

    public void onContentChanged() {
        this.onSupportContentChanged();
    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        this.getDelegate().installViewFactory();
        super.onCreate(bundle);
        this.getDelegate().onCreate(bundle);
    }

    public void onCreateSupportNavigateUpTaskStack(TaskStackBuilder taskStackBuilder) {
        taskStackBuilder.addParentStack(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.getDelegate().onDestroy();
    }

    @Override
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

    protected void onPostCreate(@Nullable Bundle bundle) {
        super.onPostCreate(bundle);
        this.getDelegate().onPostCreate(bundle);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        this.getDelegate().onPostResume();
    }

    public void onPrepareSupportNavigateUpTaskStack(TaskStackBuilder taskStackBuilder) {
    }

    @Override
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

    @Deprecated
    public void onSupportContentChanged() {
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
                ActivityCompat.finishAffinity(this);
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

    public ActionMode startMultiChoiceActionMode(ActionMode.Callback callback) {
        return this.getDelegate().startMultiChoiceActionMode(callback);
    }

    public ActionMode startSupportActionMode(ActionMode.Callback callback) {
        return this.getDelegate().startSupportActionMode(callback);
    }

    @Override
    public void supportFinishAfterTransition() {
        if (!this.getFragmentManager().popBackStackImmediate()) {
            super.supportFinishAfterTransition();
        }
    }

    @Override
    public void supportInvalidateOptionsMenu() {
        this.getDelegate().invalidateOptionsMenu();
    }

    public void supportNavigateUpTo(Intent intent) {
        NavUtils.navigateUpTo(this, intent);
    }

    public boolean supportRequestWindowFeature(int n2) {
        return this.getDelegate().requestWindowFeature(n2);
    }

    public boolean supportShouldUpRecreateTask(Intent intent) {
        return NavUtils.shouldUpRecreateTask(this, intent);
    }
}


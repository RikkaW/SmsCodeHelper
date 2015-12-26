/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v7.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public abstract class ActionMode {
    private boolean mAnimateToShowMenu = true;
    private BackPressedListener mBackListener;
    private Object mTag;
    private boolean mTitleOptionalHint;

    public abstract void finish();

    public BackPressedListener getBackPressListener() {
        return this.mBackListener;
    }

    public abstract View getCustomView();

    public abstract Menu getMenu();

    public abstract MenuInflater getMenuInflater();

    public abstract CharSequence getSubtitle();

    public Object getTag() {
        return this.mTag;
    }

    public abstract CharSequence getTitle();

    public boolean getTitleOptionalHint() {
        return this.mTitleOptionalHint;
    }

    public abstract void invalidate();

    public boolean isAnimateToShowMenu() {
        return this.mAnimateToShowMenu;
    }

    public boolean isShowActionBar() {
        return true;
    }

    public boolean isTitleOptional() {
        return false;
    }

    public boolean isUiFocusable() {
        return true;
    }

    public void setAnimateToShowMenu(boolean bl2) {
        this.mAnimateToShowMenu = bl2;
    }

    public void setBackPressListener(BackPressedListener backPressedListener) {
        this.mBackListener = backPressedListener;
    }

    public abstract void setCustomView(View var1);

    public void setShowActionBar(boolean bl2) {
    }

    public abstract void setSubtitle(int var1);

    public abstract void setSubtitle(CharSequence var1);

    public void setTag(Object object) {
        this.mTag = object;
    }

    public abstract void setTitle(int var1);

    public abstract void setTitle(CharSequence var1);

    public void setTitleOptionalHint(boolean bl2) {
        this.mTitleOptionalHint = bl2;
    }

    public static interface BackPressedListener {
        public boolean onBackPressed();
    }

    public static interface Callback {
        public boolean onActionItemClicked(ActionMode var1, MenuItem var2);

        public boolean onCreateActionMode(ActionMode var1, Menu var2);

        public void onDestroyActionMode(ActionMode var1);

        public boolean onPrepareActionMode(ActionMode var1, Menu var2);
    }

}


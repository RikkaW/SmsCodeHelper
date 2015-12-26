/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnTouchListener
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.content.Context;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPopupHelper;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.widget.PopupMenu$1;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class PopupMenu
implements MenuBuilder.Callback,
MenuPresenter.Callback {
    private View mAnchor;
    private Context mContext;
    private OnDismissListener mDismissListener;
    private View.OnTouchListener mDragListener;
    private MenuBuilder mMenu;
    private OnMenuItemClickListener mMenuItemClickListener;
    private MenuPopupHelper mPopup;

    public PopupMenu(Context context, View view) {
        this(context, view, 0);
    }

    public PopupMenu(Context context, View view, int n2) {
        this(context, view, n2, R.attr.popupMenuStyle, 0);
    }

    public PopupMenu(Context context, View view, int n2, int n3, int n4) {
        this.mContext = context;
        this.mMenu = new MenuBuilder(context);
        this.mMenu.setCallback(this);
        this.mAnchor = view;
        this.mPopup = new MenuPopupHelper(context, this.mMenu, view, false, n3, n4);
        this.mPopup.setGravity(n2);
        this.mPopup.setCallback(this);
    }

    static /* synthetic */ MenuPopupHelper access$000(PopupMenu popupMenu) {
        return popupMenu.mPopup;
    }

    public void dismiss() {
        this.mPopup.dismiss();
    }

    public View.OnTouchListener getDragToOpenListener() {
        if (this.mDragListener == null) {
            this.mDragListener = new PopupMenu$1(this, this.mAnchor);
        }
        return this.mDragListener;
    }

    public Menu getMenu() {
        return this.mMenu;
    }

    public MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.mContext);
    }

    public void inflate(int n2) {
        this.getMenuInflater().inflate(n2, (Menu)this.mMenu);
    }

    @Override
    public void onCloseMenu(MenuBuilder menuBuilder, boolean bl2) {
        if (this.mDismissListener != null) {
            this.mDismissListener.onDismiss(this);
        }
    }

    public void onCloseSubMenu(SubMenuBuilder subMenuBuilder) {
    }

    @Override
    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        if (this.mMenuItemClickListener != null) {
            return this.mMenuItemClickListener.onMenuItemClick(menuItem);
        }
        return false;
    }

    @Override
    public void onMenuModeChange(MenuBuilder menuBuilder) {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
        boolean bl2 = true;
        if (menuBuilder == null) {
            return false;
        }
        if (!menuBuilder.hasVisibleItems()) return bl2;
        new MenuPopupHelper(this.mContext, menuBuilder, this.mAnchor).show();
        return true;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.mDismissListener = onDismissListener;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mMenuItemClickListener = onMenuItemClickListener;
    }

    public void show() {
        this.mPopup.show();
    }

    public static interface OnDismissListener {
        public void onDismiss(PopupMenu var1);
    }

    public static interface OnMenuItemClickListener {
        public boolean onMenuItemClick(MenuItem var1);
    }

}


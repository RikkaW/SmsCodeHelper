/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.view.MenuItem
 *  android.view.View
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  java.lang.Object
 */
package android.support.v7.internal.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public final class ExpandedMenuView
extends ListView
implements MenuBuilder.ItemInvoker,
MenuView,
AdapterView.OnItemClickListener {
    private static final int[] TINT_ATTRS = new int[]{16842964, 16843049};
    private int mAnimations;
    private MenuBuilder mMenu;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    public ExpandedMenuView(Context object, AttributeSet attributeSet, int n2) {
        super((Context)object, attributeSet);
        this.setOnItemClickListener((AdapterView.OnItemClickListener)this);
        object = TintTypedArray.obtainStyledAttributes((Context)object, attributeSet, TINT_ATTRS, n2, 0);
        if (object.hasValue(0)) {
            this.setBackgroundDrawable(object.getDrawable(0));
        }
        if (object.hasValue(1)) {
            this.setDivider(object.getDrawable(1));
        }
        object.recycle();
    }

    @Override
    public int getWindowAnimations() {
        return this.mAnimations;
    }

    @Override
    public void initialize(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    @Override
    public boolean invokeItem(MenuItemImpl menuItemImpl) {
        return this.mMenu.performItemAction(menuItemImpl, 0);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.setChildrenDrawingCacheEnabled(false);
    }

    public void onItemClick(AdapterView adapterView, View view, int n2, long l2) {
        this.invokeItem((MenuItemImpl)this.getAdapter().getItem(n2));
    }
}


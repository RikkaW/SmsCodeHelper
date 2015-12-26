/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.drawable.Drawable
 *  android.os.Parcelable
 *  android.util.SparseArray
 *  android.view.Menu
 *  android.view.Window
 *  android.view.Window$Callback
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.util.SparseArray;
import android.view.Menu;
import android.view.Window;

public interface DecorContentParent {
    public boolean canShowOverflowMenu();

    public void dismissPopups();

    public CharSequence getTitle();

    public boolean hasIcon();

    public boolean hasLogo();

    public boolean hideOverflowMenu();

    public void initFeature(int var1);

    public boolean isOverflowMenuShowPending();

    public boolean isOverflowMenuShowing();

    public void restoreToolbarHierarchyState(SparseArray<Parcelable> var1);

    public void saveToolbarHierarchyState(SparseArray<Parcelable> var1);

    public void setIcon(int var1);

    public void setIcon(Drawable var1);

    public void setLogo(int var1);

    public void setMenu(Menu var1, MenuPresenter.Callback var2);

    public void setMenuPrepared();

    public void setTransStatusBarInFlyme3(boolean var1);

    public void setUiOptions(int var1);

    public void setWindowCallback(Window.Callback var1);

    public void setWindowTitle(CharSequence var1);

    public boolean showOverflowMenu();
}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.pm.ApplicationInfo
 *  android.content.res.Resources
 *  android.content.res.TypedArray
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  java.lang.Math
 *  java.lang.Object
 */
package android.support.v7.internal.view;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

public class ActionBarPolicy {
    private Context mContext;

    private ActionBarPolicy(Context context) {
        this.mContext = context;
    }

    public static ActionBarPolicy get(Context context) {
        return new ActionBarPolicy(context);
    }

    public boolean enableHomeButtonByDefault() {
        if (this.mContext.getApplicationInfo().targetSdkVersion < 14) {
            return true;
        }
        return false;
    }

    public int getActionBarHeight() {
        TypedArray typedArray = this.mContext.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        int n2 = typedArray.getLayoutDimension(R.styleable.ActionBar_height, 0);
        typedArray.recycle();
        return n2;
    }

    public int getEmbeddedMenuWidthLimit() {
        return this.mContext.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public int getMaxActionButtons() {
        return this.mContext.getResources().getInteger(R.integer.abc_max_action_buttons);
    }

    public int getSplitActionBarPadding() {
        return this.mContext.getResources().getDimensionPixelSize(R.dimen.mz_action_mode_split_padding);
    }

    public int getStackedTabMaxWidth() {
        return this.mContext.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_stacked_tab_max_width);
    }

    public int getTabContainerHeight() {
        TypedArray typedArray = this.mContext.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        int n2 = typedArray.getLayoutDimension(R.styleable.ActionBar_height, 0);
        Resources resources = this.mContext.getResources();
        int n3 = n2;
        if (!this.hasEmbeddedTabs()) {
            n3 = Math.min((int)n2, (int)resources.getDimensionPixelSize(R.dimen.mz_action_bar_stacked_max_height));
        }
        typedArray.recycle();
        return n3;
    }

    public boolean hasEmbeddedTabs() {
        if (this.mContext.getApplicationInfo().targetSdkVersion >= 16) {
            return this.mContext.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs);
        }
        return this.mContext.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs_pre_jb);
    }

    public boolean showsOverflowMenuButton() {
        return true;
    }
}


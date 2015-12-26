/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.PackageManager
 *  android.content.pm.ResolveInfo
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.TypedValue
 *  android.view.MenuItem
 *  android.view.MenuItem$OnMenuItemClickListener
 *  android.view.SubMenu
 *  android.view.View
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ActionProvider;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.ActivityChooserModel;
import android.support.v7.internal.widget.ActivityChooserView;
import android.support.v7.internal.widget.TintManager;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class ShareActionProvider
extends ActionProvider {
    private static final int DEFAULT_INITIAL_ACTIVITY_COUNT = 4;
    public static final String DEFAULT_SHARE_HISTORY_FILE_NAME = "share_history.xml";
    private final Context mContext;
    private int mMaxShownActivityCount = 4;
    private ActivityChooserModel.OnChooseActivityListener mOnChooseActivityListener;
    private final ShareMenuItemOnMenuItemClickListener mOnMenuItemClickListener;
    private OnShareTargetSelectedListener mOnShareTargetSelectedListener;
    private String mShareHistoryFileName;

    public ShareActionProvider(Context context) {
        super(context);
        this.mOnMenuItemClickListener = new ShareMenuItemOnMenuItemClickListener();
        this.mShareHistoryFileName = "share_history.xml";
        this.mContext = context;
    }

    private void setActivityChooserPolicyIfNeeded() {
        if (this.mOnShareTargetSelectedListener == null) {
            return;
        }
        if (this.mOnChooseActivityListener == null) {
            this.mOnChooseActivityListener = new ShareActivityChooserModelPolicy();
        }
        ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName).setOnChooseActivityListener(this.mOnChooseActivityListener);
    }

    private void updateIntent(Intent intent) {
        if (Build.VERSION.SDK_INT >= 21) {
            intent.addFlags(134742016);
            return;
        }
        intent.addFlags(524288);
    }

    @Override
    public boolean hasSubMenu() {
        return true;
    }

    @Override
    public View onCreateActionView() {
        ActivityChooserView activityChooserView = new ActivityChooserView(this.mContext);
        if (!activityChooserView.isInEditMode()) {
            activityChooserView.setActivityChooserModel(ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName));
        }
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(R.attr.actionModeShareDrawable, typedValue, true);
        activityChooserView.setExpandActivityOverflowButtonDrawable(TintManager.getDrawable(this.mContext, typedValue.resourceId));
        activityChooserView.setProvider(this);
        activityChooserView.setDefaultActionButtonContentDescription(R.string.abc_shareactionprovider_share_with_application);
        activityChooserView.setExpandActivityOverflowButtonContentDescription(R.string.abc_shareactionprovider_share_with);
        return activityChooserView;
    }

    @Override
    public void onPrepareSubMenu(SubMenu subMenu) {
        ResolveInfo resolveInfo;
        int n2;
        subMenu.clear();
        ActivityChooserModel activityChooserModel = ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName);
        PackageManager packageManager = this.mContext.getPackageManager();
        int n3 = activityChooserModel.getActivityCount();
        int n4 = Math.min((int)n3, (int)this.mMaxShownActivityCount);
        for (n2 = 0; n2 < n4; ++n2) {
            resolveInfo = activityChooserModel.getActivity(n2);
            subMenu.add(0, n2, n2, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setOnMenuItemClickListener((MenuItem.OnMenuItemClickListener)this.mOnMenuItemClickListener);
        }
        if (n4 < n3) {
            subMenu = subMenu.addSubMenu(0, n4, n4, (CharSequence)this.mContext.getString(R.string.abc_activity_chooser_view_see_all));
            for (n2 = 0; n2 < n3; ++n2) {
                resolveInfo = activityChooserModel.getActivity(n2);
                subMenu.add(0, n2, n2, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setOnMenuItemClickListener((MenuItem.OnMenuItemClickListener)this.mOnMenuItemClickListener);
            }
        }
    }

    public void setOnShareTargetSelectedListener(OnShareTargetSelectedListener onShareTargetSelectedListener) {
        this.mOnShareTargetSelectedListener = onShareTargetSelectedListener;
        this.setActivityChooserPolicyIfNeeded();
    }

    public void setShareHistoryFileName(String string2) {
        this.mShareHistoryFileName = string2;
        this.setActivityChooserPolicyIfNeeded();
    }

    public void setShareIntent(Intent intent) {
        String string2;
        if (intent != null && ("android.intent.action.SEND".equals((Object)(string2 = intent.getAction())) || "android.intent.action.SEND_MULTIPLE".equals((Object)string2))) {
            this.updateIntent(intent);
        }
        ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName).setIntent(intent);
    }

    public static interface OnShareTargetSelectedListener {
        public boolean onShareTargetSelected(ShareActionProvider var1, Intent var2);
    }

    class ShareActivityChooserModelPolicy
    implements ActivityChooserModel.OnChooseActivityListener {
        private ShareActivityChooserModelPolicy() {
        }

        @Override
        public boolean onChooseActivity(ActivityChooserModel activityChooserModel, Intent intent) {
            if (ShareActionProvider.this.mOnShareTargetSelectedListener != null) {
                ShareActionProvider.this.mOnShareTargetSelectedListener.onShareTargetSelected(ShareActionProvider.this, intent);
            }
            return false;
        }
    }

    class ShareMenuItemOnMenuItemClickListener
    implements MenuItem.OnMenuItemClickListener {
        private ShareMenuItemOnMenuItemClickListener() {
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            menuItem = ActivityChooserModel.get(ShareActionProvider.this.mContext, ShareActionProvider.this.mShareHistoryFileName).chooseActivity(menuItem.getItemId());
            if (menuItem != null) {
                String string2 = menuItem.getAction();
                if ("android.intent.action.SEND".equals((Object)string2) || "android.intent.action.SEND_MULTIPLE".equals((Object)string2)) {
                    ShareActionProvider.this.updateIntent((Intent)menuItem);
                }
                ShareActionProvider.this.mContext.startActivity((Intent)menuItem);
            }
            return true;
        }
    }

}


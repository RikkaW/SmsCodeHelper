package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import android.support.v4.view.ActionProvider;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.string;
import android.support.v7.internal.widget.ActivityChooserModel;
import android.support.v7.internal.widget.ActivityChooserModel.OnChooseActivityListener;
import android.support.v7.internal.widget.ActivityChooserView;
import android.support.v7.internal.widget.TintManager;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

public class ShareActionProvider
  extends ActionProvider
{
  private static final int DEFAULT_INITIAL_ACTIVITY_COUNT = 4;
  public static final String DEFAULT_SHARE_HISTORY_FILE_NAME = "share_history.xml";
  private final Context mContext;
  private int mMaxShownActivityCount = 4;
  private ActivityChooserModel.OnChooseActivityListener mOnChooseActivityListener;
  private final ShareMenuItemOnMenuItemClickListener mOnMenuItemClickListener = new ShareMenuItemOnMenuItemClickListener(null);
  private OnShareTargetSelectedListener mOnShareTargetSelectedListener;
  private String mShareHistoryFileName = "share_history.xml";
  
  public ShareActionProvider(Context paramContext)
  {
    super(paramContext);
    mContext = paramContext;
  }
  
  private void setActivityChooserPolicyIfNeeded()
  {
    if (mOnShareTargetSelectedListener == null) {
      return;
    }
    if (mOnChooseActivityListener == null) {
      mOnChooseActivityListener = new ShareActivityChooserModelPolicy(null);
    }
    ActivityChooserModel.get(mContext, mShareHistoryFileName).setOnChooseActivityListener(mOnChooseActivityListener);
  }
  
  private void updateIntent(Intent paramIntent)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramIntent.addFlags(134742016);
      return;
    }
    paramIntent.addFlags(524288);
  }
  
  public boolean hasSubMenu()
  {
    return true;
  }
  
  public View onCreateActionView()
  {
    ActivityChooserView localActivityChooserView = new ActivityChooserView(mContext);
    if (!localActivityChooserView.isInEditMode()) {
      localActivityChooserView.setActivityChooserModel(ActivityChooserModel.get(mContext, mShareHistoryFileName));
    }
    TypedValue localTypedValue = new TypedValue();
    mContext.getTheme().resolveAttribute(R.attr.actionModeShareDrawable, localTypedValue, true);
    localActivityChooserView.setExpandActivityOverflowButtonDrawable(TintManager.getDrawable(mContext, resourceId));
    localActivityChooserView.setProvider(this);
    localActivityChooserView.setDefaultActionButtonContentDescription(R.string.abc_shareactionprovider_share_with_application);
    localActivityChooserView.setExpandActivityOverflowButtonContentDescription(R.string.abc_shareactionprovider_share_with);
    return localActivityChooserView;
  }
  
  public void onPrepareSubMenu(SubMenu paramSubMenu)
  {
    paramSubMenu.clear();
    ActivityChooserModel localActivityChooserModel = ActivityChooserModel.get(mContext, mShareHistoryFileName);
    PackageManager localPackageManager = mContext.getPackageManager();
    int j = localActivityChooserModel.getActivityCount();
    int k = Math.min(j, mMaxShownActivityCount);
    int i = 0;
    ResolveInfo localResolveInfo;
    while (i < k)
    {
      localResolveInfo = localActivityChooserModel.getActivity(i);
      paramSubMenu.add(0, i, i, localResolveInfo.loadLabel(localPackageManager)).setIcon(localResolveInfo.loadIcon(localPackageManager)).setOnMenuItemClickListener(mOnMenuItemClickListener);
      i += 1;
    }
    if (k < j)
    {
      paramSubMenu = paramSubMenu.addSubMenu(0, k, k, mContext.getString(R.string.abc_activity_chooser_view_see_all));
      i = 0;
      while (i < j)
      {
        localResolveInfo = localActivityChooserModel.getActivity(i);
        paramSubMenu.add(0, i, i, localResolveInfo.loadLabel(localPackageManager)).setIcon(localResolveInfo.loadIcon(localPackageManager)).setOnMenuItemClickListener(mOnMenuItemClickListener);
        i += 1;
      }
    }
  }
  
  public void setOnShareTargetSelectedListener(OnShareTargetSelectedListener paramOnShareTargetSelectedListener)
  {
    mOnShareTargetSelectedListener = paramOnShareTargetSelectedListener;
    setActivityChooserPolicyIfNeeded();
  }
  
  public void setShareHistoryFileName(String paramString)
  {
    mShareHistoryFileName = paramString;
    setActivityChooserPolicyIfNeeded();
  }
  
  public void setShareIntent(Intent paramIntent)
  {
    if (paramIntent != null)
    {
      String str = paramIntent.getAction();
      if (("android.intent.action.SEND".equals(str)) || ("android.intent.action.SEND_MULTIPLE".equals(str))) {
        updateIntent(paramIntent);
      }
    }
    ActivityChooserModel.get(mContext, mShareHistoryFileName).setIntent(paramIntent);
  }
  
  public static abstract interface OnShareTargetSelectedListener
  {
    public abstract boolean onShareTargetSelected(ShareActionProvider paramShareActionProvider, Intent paramIntent);
  }
  
  class ShareActivityChooserModelPolicy
    implements ActivityChooserModel.OnChooseActivityListener
  {
    private ShareActivityChooserModelPolicy() {}
    
    public boolean onChooseActivity(ActivityChooserModel paramActivityChooserModel, Intent paramIntent)
    {
      if (mOnShareTargetSelectedListener != null) {
        mOnShareTargetSelectedListener.onShareTargetSelected(ShareActionProvider.this, paramIntent);
      }
      return false;
    }
  }
  
  class ShareMenuItemOnMenuItemClickListener
    implements MenuItem.OnMenuItemClickListener
  {
    private ShareMenuItemOnMenuItemClickListener() {}
    
    public boolean onMenuItemClick(MenuItem paramMenuItem)
    {
      paramMenuItem = ActivityChooserModel.get(mContext, mShareHistoryFileName).chooseActivity(paramMenuItem.getItemId());
      if (paramMenuItem != null)
      {
        String str = paramMenuItem.getAction();
        if (("android.intent.action.SEND".equals(str)) || ("android.intent.action.SEND_MULTIPLE".equals(str))) {
          ShareActionProvider.this.updateIntent(paramMenuItem);
        }
        mContext.startActivity(paramMenuItem);
      }
      return true;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ShareActionProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
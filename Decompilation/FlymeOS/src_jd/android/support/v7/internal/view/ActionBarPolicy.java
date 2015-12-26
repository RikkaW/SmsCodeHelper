package android.support.v7.internal.view;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.bool;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.integer;
import android.support.v7.appcompat.R.styleable;
import android.util.DisplayMetrics;

public class ActionBarPolicy
{
  private Context mContext;
  
  private ActionBarPolicy(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public static ActionBarPolicy get(Context paramContext)
  {
    return new ActionBarPolicy(paramContext);
  }
  
  public boolean enableHomeButtonByDefault()
  {
    return mContext.getApplicationInfo().targetSdkVersion < 14;
  }
  
  public int getActionBarHeight()
  {
    TypedArray localTypedArray = mContext.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
    int i = localTypedArray.getLayoutDimension(R.styleable.ActionBar_height, 0);
    localTypedArray.recycle();
    return i;
  }
  
  public int getEmbeddedMenuWidthLimit()
  {
    return mContext.getResources().getDisplayMetrics().widthPixels / 2;
  }
  
  public int getMaxActionButtons()
  {
    return mContext.getResources().getInteger(R.integer.abc_max_action_buttons);
  }
  
  public int getSplitActionBarPadding()
  {
    return mContext.getResources().getDimensionPixelSize(R.dimen.mz_action_mode_split_padding);
  }
  
  public int getStackedTabMaxWidth()
  {
    return mContext.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_stacked_tab_max_width);
  }
  
  public int getTabContainerHeight()
  {
    TypedArray localTypedArray = mContext.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
    int j = localTypedArray.getLayoutDimension(R.styleable.ActionBar_height, 0);
    Resources localResources = mContext.getResources();
    int i = j;
    if (!hasEmbeddedTabs()) {
      i = Math.min(j, localResources.getDimensionPixelSize(R.dimen.mz_action_bar_stacked_max_height));
    }
    localTypedArray.recycle();
    return i;
  }
  
  public boolean hasEmbeddedTabs()
  {
    if (mContext.getApplicationInfo().targetSdkVersion >= 16) {
      return mContext.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs);
    }
    return mContext.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs_pre_jb);
  }
  
  public boolean showsOverflowMenuButton()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.view.ActionBarPolicy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
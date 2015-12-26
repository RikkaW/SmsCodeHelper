package android.support.v7.internal.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.internal.widget.TintManager;
import android.util.Log;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewDebug.CapturedViewProperty;
import android.widget.LinearLayout;

public final class MenuItemImpl
  implements SupportMenuItem
{
  private static final int CHECKABLE = 1;
  private static final int CHECKED = 2;
  private static final int ENABLED = 16;
  private static final int EXCLUSIVE = 4;
  private static final int HIDDEN = 8;
  private static final int IS_ACTION = 32;
  static final int NO_ICON = 0;
  private static final int SHOW_AS_ACTION_MASK = 3;
  private static final String TAG = "MenuItemImpl";
  private static String sDeleteShortcutLabel;
  private static String sEnterShortcutLabel;
  private static String sPrependShortcutLabel;
  private static String sSpaceShortcutLabel;
  private android.support.v4.view.ActionProvider mActionProvider;
  private View mActionView;
  private final int mCategoryOrder;
  private MenuItem.OnMenuItemClickListener mClickListener;
  private int mFlags = 16;
  private final int mGroup;
  private Drawable mIconDrawable;
  private int mIconResId = 0;
  private final int mId;
  private Intent mIntent;
  private boolean mIsActionViewExpanded = false;
  private boolean mIsOverflowActor;
  private Runnable mItemCallback;
  private boolean mLetMenuSlideOut = true;
  private MenuBuilder mMenu;
  private ContextMenu.ContextMenuInfo mMenuInfo;
  private MenuItemCompat.OnActionExpandListener mOnActionExpandListener;
  private final int mOrdering;
  private char mShortcutAlphabeticChar;
  private char mShortcutNumericChar;
  private int mShowAsAction = 0;
  private SubMenuBuilder mSubMenu;
  private CharSequence mTitle;
  private ColorStateList mTitleColor;
  private CharSequence mTitleCondensed;
  
  MenuItemImpl(MenuBuilder paramMenuBuilder, int paramInt1, int paramInt2, int paramInt3, int paramInt4, CharSequence paramCharSequence, int paramInt5)
  {
    mMenu = paramMenuBuilder;
    mId = paramInt2;
    mGroup = paramInt1;
    mCategoryOrder = paramInt3;
    mOrdering = paramInt4;
    mTitle = paramCharSequence;
    mShowAsAction = paramInt5;
  }
  
  public void actionFormatChanged()
  {
    mMenu.onItemActionRequestChanged(this);
  }
  
  public boolean collapseActionView()
  {
    if ((mShowAsAction & 0x8) == 0) {}
    do
    {
      return false;
      if (mActionView == null) {
        return true;
      }
    } while ((mOnActionExpandListener != null) && (!mOnActionExpandListener.onMenuItemActionCollapse(this)));
    return mMenu.collapseItemActionView(this);
  }
  
  public boolean expandActionView()
  {
    if (!hasCollapsibleActionView()) {}
    while ((mOnActionExpandListener != null) && (!mOnActionExpandListener.onMenuItemActionExpand(this))) {
      return false;
    }
    return mMenu.expandItemActionView(this);
  }
  
  public android.view.ActionProvider getActionProvider()
  {
    throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
  }
  
  public View getActionView()
  {
    if (mActionView != null) {
      return mActionView;
    }
    if (mActionProvider != null)
    {
      mActionView = mActionProvider.onCreateActionView(this);
      return mActionView;
    }
    return null;
  }
  
  public char getAlphabeticShortcut()
  {
    return mShortcutAlphabeticChar;
  }
  
  Runnable getCallback()
  {
    return mItemCallback;
  }
  
  public int getGroupId()
  {
    return mGroup;
  }
  
  public Drawable getIcon()
  {
    if (mIconDrawable != null) {
      return mIconDrawable;
    }
    if (mIconResId != 0)
    {
      Drawable localDrawable = TintManager.getDrawable(mMenu.getContext(), mIconResId);
      mIconResId = 0;
      mIconDrawable = localDrawable;
      return localDrawable;
    }
    return null;
  }
  
  public Intent getIntent()
  {
    return mIntent;
  }
  
  @ViewDebug.CapturedViewProperty
  public int getItemId()
  {
    return mId;
  }
  
  public ContextMenu.ContextMenuInfo getMenuInfo()
  {
    return mMenuInfo;
  }
  
  public char getNumericShortcut()
  {
    return mShortcutNumericChar;
  }
  
  public int getOrder()
  {
    return mCategoryOrder;
  }
  
  public int getOrdering()
  {
    return mOrdering;
  }
  
  char getShortcut()
  {
    if (mMenu.isQwertyMode()) {
      return mShortcutAlphabeticChar;
    }
    return mShortcutNumericChar;
  }
  
  String getShortcutLabel()
  {
    char c = getShortcut();
    if (c == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder(sPrependShortcutLabel);
    switch (c)
    {
    default: 
      localStringBuilder.append(c);
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append(sEnterShortcutLabel);
      continue;
      localStringBuilder.append(sDeleteShortcutLabel);
      continue;
      localStringBuilder.append(sSpaceShortcutLabel);
    }
  }
  
  public SubMenu getSubMenu()
  {
    return mSubMenu;
  }
  
  public android.support.v4.view.ActionProvider getSupportActionProvider()
  {
    return mActionProvider;
  }
  
  @ViewDebug.CapturedViewProperty
  public CharSequence getTitle()
  {
    return mTitle;
  }
  
  public ColorStateList getTitleColor()
  {
    return mTitleColor;
  }
  
  public CharSequence getTitleCondensed()
  {
    if (mTitleCondensed != null) {}
    for (CharSequence localCharSequence = mTitleCondensed;; localCharSequence = mTitle)
    {
      Object localObject = localCharSequence;
      if (Build.VERSION.SDK_INT < 18)
      {
        localObject = localCharSequence;
        if (localCharSequence != null)
        {
          localObject = localCharSequence;
          if (!(localCharSequence instanceof String)) {
            localObject = localCharSequence.toString();
          }
        }
      }
      return (CharSequence)localObject;
    }
  }
  
  CharSequence getTitleForItemView(MenuView.ItemView paramItemView)
  {
    if ((paramItemView != null) && (paramItemView.prefersCondensedTitle())) {
      return getTitleCondensed();
    }
    return getTitle();
  }
  
  public boolean hasCollapsibleActionView()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((mShowAsAction & 0x8) != 0)
    {
      if ((mActionView == null) && (mActionProvider != null)) {
        mActionView = mActionProvider.onCreateActionView(this);
      }
      bool1 = bool2;
      if (mActionView != null) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean hasSubMenu()
  {
    return mSubMenu != null;
  }
  
  public boolean invoke()
  {
    if ((mClickListener != null) && (mClickListener.onMenuItemClick(this))) {}
    do
    {
      do
      {
        return true;
      } while (mMenu.dispatchMenuItemSelected(mMenu.getRootMenu(), this));
      if (mItemCallback != null)
      {
        mItemCallback.run();
        return true;
      }
      if (mIntent != null) {
        try
        {
          mMenu.getContext().startActivity(mIntent);
          return true;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", localActivityNotFoundException);
        }
      }
    } while ((mActionProvider != null) && (mActionProvider.onPerformDefaultAction()));
    return false;
  }
  
  public boolean isActionButton()
  {
    return (mFlags & 0x20) == 32;
  }
  
  public boolean isActionViewExpanded()
  {
    return mIsActionViewExpanded;
  }
  
  public boolean isCheckable()
  {
    return (mFlags & 0x1) == 1;
  }
  
  public boolean isChecked()
  {
    return (mFlags & 0x2) == 2;
  }
  
  public boolean isEnabled()
  {
    return (mFlags & 0x10) != 0;
  }
  
  public boolean isExclusiveCheckable()
  {
    return (mFlags & 0x4) != 0;
  }
  
  public boolean isLetMenuSlideOut()
  {
    return mLetMenuSlideOut;
  }
  
  public boolean isOverflowActor()
  {
    return mIsOverflowActor;
  }
  
  public boolean isVisible()
  {
    if ((mActionProvider != null) && (mActionProvider.overridesItemVisibility())) {
      if (((mFlags & 0x8) != 0) || (!mActionProvider.isVisible())) {}
    }
    while ((mFlags & 0x8) == 0)
    {
      return true;
      return false;
    }
    return false;
  }
  
  public boolean requestsActionButton()
  {
    return (mShowAsAction & 0x1) == 1;
  }
  
  public boolean requiresActionButton()
  {
    return (mShowAsAction & 0x2) == 2;
  }
  
  public MenuItem setActionProvider(android.view.ActionProvider paramActionProvider)
  {
    throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
  }
  
  public SupportMenuItem setActionView(int paramInt)
  {
    Context localContext = mMenu.getContext();
    setActionView(LayoutInflater.from(localContext).inflate(paramInt, new LinearLayout(localContext), false));
    return this;
  }
  
  public SupportMenuItem setActionView(View paramView)
  {
    mActionView = paramView;
    mActionProvider = null;
    if ((paramView != null) && (paramView.getId() == -1) && (mId > 0)) {
      paramView.setId(mId);
    }
    mMenu.onItemActionRequestChanged(this);
    return this;
  }
  
  public void setActionViewExpanded(boolean paramBoolean)
  {
    mIsActionViewExpanded = paramBoolean;
    mMenu.onItemsChanged(false);
  }
  
  public MenuItem setAlphabeticShortcut(char paramChar)
  {
    if (mShortcutAlphabeticChar == paramChar) {
      return this;
    }
    mShortcutAlphabeticChar = Character.toLowerCase(paramChar);
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setCallback(Runnable paramRunnable)
  {
    mItemCallback = paramRunnable;
    return this;
  }
  
  public MenuItem setCheckable(boolean paramBoolean)
  {
    int j = mFlags;
    int k = mFlags;
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      mFlags = (i | k & 0xFFFFFFFE);
      if (j != mFlags) {
        mMenu.onItemsChanged(false);
      }
      return this;
    }
  }
  
  public MenuItem setChecked(boolean paramBoolean)
  {
    if ((mFlags & 0x4) != 0)
    {
      mMenu.setExclusiveItemChecked(this);
      return this;
    }
    setCheckedInt(paramBoolean);
    return this;
  }
  
  void setCheckedInt(boolean paramBoolean)
  {
    int j = mFlags;
    int k = mFlags;
    if (paramBoolean) {}
    for (int i = 2;; i = 0)
    {
      mFlags = (i | k & 0xFFFFFFFD);
      if (j != mFlags) {
        mMenu.onItemsChanged(false);
      }
      return;
    }
  }
  
  public MenuItem setEnabled(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (mFlags |= 0x10;; mFlags &= 0xFFFFFFEF)
    {
      mMenu.onItemsChanged(false);
      return this;
    }
  }
  
  public void setExclusiveCheckable(boolean paramBoolean)
  {
    int j = mFlags;
    if (paramBoolean) {}
    for (int i = 4;; i = 0)
    {
      mFlags = (i | j & 0xFFFFFFFB);
      return;
    }
  }
  
  public MenuItem setIcon(int paramInt)
  {
    mIconDrawable = null;
    mIconResId = paramInt;
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setIcon(Drawable paramDrawable)
  {
    mIconResId = 0;
    mIconDrawable = paramDrawable;
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setIntent(Intent paramIntent)
  {
    mIntent = paramIntent;
    return this;
  }
  
  public void setIsActionButton(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      mFlags |= 0x20;
      return;
    }
    mFlags &= 0xFFFFFFDF;
  }
  
  public MenuItem setIsOverflowActor(boolean paramBoolean)
  {
    if (mIsOverflowActor != paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        mIsOverflowActor = paramBoolean;
        mMenu.onItemsChanged(true);
      }
      return this;
    }
  }
  
  public MenuItem setLetMenuSlideOut(boolean paramBoolean)
  {
    mLetMenuSlideOut = paramBoolean;
    return this;
  }
  
  void setMenuInfo(ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    mMenuInfo = paramContextMenuInfo;
  }
  
  public MenuItem setNumericShortcut(char paramChar)
  {
    if (mShortcutNumericChar == paramChar) {
      return this;
    }
    mShortcutNumericChar = paramChar;
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener paramOnActionExpandListener)
  {
    throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setOnActionExpandListener()");
  }
  
  public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener paramOnMenuItemClickListener)
  {
    mClickListener = paramOnMenuItemClickListener;
    return this;
  }
  
  public MenuItem setShortcut(char paramChar1, char paramChar2)
  {
    mShortcutNumericChar = paramChar1;
    mShortcutAlphabeticChar = Character.toLowerCase(paramChar2);
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public void setShowAsAction(int paramInt)
  {
    switch (paramInt & 0x3)
    {
    default: 
      throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
    }
    mShowAsAction = paramInt;
    mMenu.onItemActionRequestChanged(this);
  }
  
  public SupportMenuItem setShowAsActionFlags(int paramInt)
  {
    setShowAsAction(paramInt);
    return this;
  }
  
  void setSubMenu(SubMenuBuilder paramSubMenuBuilder)
  {
    mSubMenu = paramSubMenuBuilder;
    paramSubMenuBuilder.setHeaderTitle(getTitle());
  }
  
  public SupportMenuItem setSupportActionProvider(android.support.v4.view.ActionProvider paramActionProvider)
  {
    if (mActionProvider != null) {
      mActionProvider.setVisibilityListener(null);
    }
    mActionView = null;
    mActionProvider = paramActionProvider;
    mMenu.onItemsChanged(true);
    if (mActionProvider != null) {
      mActionProvider.setVisibilityListener(new MenuItemImpl.1(this));
    }
    return this;
  }
  
  public SupportMenuItem setSupportOnActionExpandListener(MenuItemCompat.OnActionExpandListener paramOnActionExpandListener)
  {
    mOnActionExpandListener = paramOnActionExpandListener;
    return this;
  }
  
  public MenuItem setTitle(int paramInt)
  {
    return setTitle(mMenu.getContext().getString(paramInt));
  }
  
  public MenuItem setTitle(CharSequence paramCharSequence)
  {
    mTitle = paramCharSequence;
    mMenu.onItemsChanged(false);
    if (mSubMenu != null) {
      mSubMenu.setHeaderTitle(paramCharSequence);
    }
    return this;
  }
  
  public MenuItem setTitleColor(ColorStateList paramColorStateList)
  {
    mTitleColor = paramColorStateList;
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setTitleCondensed(CharSequence paramCharSequence)
  {
    mTitleCondensed = paramCharSequence;
    if (paramCharSequence == null) {
      paramCharSequence = mTitle;
    }
    mMenu.onItemsChanged(false);
    return this;
  }
  
  public MenuItem setVisible(boolean paramBoolean)
  {
    if (setVisibleInt(paramBoolean)) {
      mMenu.onItemVisibleChanged(this);
    }
    return this;
  }
  
  boolean setVisibleInt(boolean paramBoolean)
  {
    boolean bool = false;
    int j = mFlags;
    int k = mFlags;
    if (paramBoolean) {}
    for (int i = 0;; i = 8)
    {
      mFlags = (i | k & 0xFFFFFFF7);
      paramBoolean = bool;
      if (j != mFlags) {
        paramBoolean = true;
      }
      return paramBoolean;
    }
  }
  
  public boolean shouldShowIcon()
  {
    return mMenu.getOptionalIconsVisible();
  }
  
  boolean shouldShowShortcut()
  {
    return (mMenu.isShortcutsVisible()) && (getShortcut() != 0);
  }
  
  public boolean showsTextAsAction()
  {
    return (mShowAsAction & 0x4) == 4;
  }
  
  public String toString()
  {
    return mTitle.toString();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.view.menu.MenuItemImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
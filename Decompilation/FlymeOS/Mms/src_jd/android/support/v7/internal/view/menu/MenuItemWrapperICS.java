package android.support.v7.internal.view.menu;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.util.Log;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

@TargetApi(14)
public class MenuItemWrapperICS
  extends BaseMenuWrapper<SupportMenuItem>
  implements MenuItem
{
  static final String LOG_TAG = "MenuItemWrapper";
  private Method mSetExclusiveCheckableMethod;
  
  MenuItemWrapperICS(Context paramContext, SupportMenuItem paramSupportMenuItem)
  {
    super(paramContext, paramSupportMenuItem);
  }
  
  public boolean collapseActionView()
  {
    return ((SupportMenuItem)mWrappedObject).collapseActionView();
  }
  
  ActionProviderWrapper createActionProviderWrapper(android.view.ActionProvider paramActionProvider)
  {
    return new ActionProviderWrapper(mContext, paramActionProvider);
  }
  
  public boolean expandActionView()
  {
    return ((SupportMenuItem)mWrappedObject).expandActionView();
  }
  
  public android.view.ActionProvider getActionProvider()
  {
    android.support.v4.view.ActionProvider localActionProvider = ((SupportMenuItem)mWrappedObject).getSupportActionProvider();
    if ((localActionProvider instanceof ActionProviderWrapper)) {
      return mInner;
    }
    return null;
  }
  
  public View getActionView()
  {
    View localView2 = ((SupportMenuItem)mWrappedObject).getActionView();
    View localView1 = localView2;
    if ((localView2 instanceof CollapsibleActionViewWrapper)) {
      localView1 = ((CollapsibleActionViewWrapper)localView2).getWrappedView();
    }
    return localView1;
  }
  
  public char getAlphabeticShortcut()
  {
    return ((SupportMenuItem)mWrappedObject).getAlphabeticShortcut();
  }
  
  public int getGroupId()
  {
    return ((SupportMenuItem)mWrappedObject).getGroupId();
  }
  
  public Drawable getIcon()
  {
    return ((SupportMenuItem)mWrappedObject).getIcon();
  }
  
  public Intent getIntent()
  {
    return ((SupportMenuItem)mWrappedObject).getIntent();
  }
  
  public int getItemId()
  {
    return ((SupportMenuItem)mWrappedObject).getItemId();
  }
  
  public ContextMenu.ContextMenuInfo getMenuInfo()
  {
    return ((SupportMenuItem)mWrappedObject).getMenuInfo();
  }
  
  public char getNumericShortcut()
  {
    return ((SupportMenuItem)mWrappedObject).getNumericShortcut();
  }
  
  public int getOrder()
  {
    return ((SupportMenuItem)mWrappedObject).getOrder();
  }
  
  public SubMenu getSubMenu()
  {
    return getSubMenuWrapper(((SupportMenuItem)mWrappedObject).getSubMenu());
  }
  
  public CharSequence getTitle()
  {
    return ((SupportMenuItem)mWrappedObject).getTitle();
  }
  
  public CharSequence getTitleCondensed()
  {
    return ((SupportMenuItem)mWrappedObject).getTitleCondensed();
  }
  
  public boolean hasSubMenu()
  {
    return ((SupportMenuItem)mWrappedObject).hasSubMenu();
  }
  
  public boolean isActionViewExpanded()
  {
    return ((SupportMenuItem)mWrappedObject).isActionViewExpanded();
  }
  
  public boolean isCheckable()
  {
    return ((SupportMenuItem)mWrappedObject).isCheckable();
  }
  
  public boolean isChecked()
  {
    return ((SupportMenuItem)mWrappedObject).isChecked();
  }
  
  public boolean isEnabled()
  {
    return ((SupportMenuItem)mWrappedObject).isEnabled();
  }
  
  public boolean isVisible()
  {
    return ((SupportMenuItem)mWrappedObject).isVisible();
  }
  
  public MenuItem setActionProvider(android.view.ActionProvider paramActionProvider)
  {
    SupportMenuItem localSupportMenuItem = (SupportMenuItem)mWrappedObject;
    if (paramActionProvider != null) {}
    for (paramActionProvider = createActionProviderWrapper(paramActionProvider);; paramActionProvider = null)
    {
      localSupportMenuItem.setSupportActionProvider(paramActionProvider);
      return this;
    }
  }
  
  public MenuItem setActionView(int paramInt)
  {
    ((SupportMenuItem)mWrappedObject).setActionView(paramInt);
    View localView = ((SupportMenuItem)mWrappedObject).getActionView();
    if ((localView instanceof android.view.CollapsibleActionView)) {
      ((SupportMenuItem)mWrappedObject).setActionView(new CollapsibleActionViewWrapper(localView));
    }
    return this;
  }
  
  public MenuItem setActionView(View paramView)
  {
    Object localObject = paramView;
    if ((paramView instanceof android.view.CollapsibleActionView)) {
      localObject = new CollapsibleActionViewWrapper(paramView);
    }
    ((SupportMenuItem)mWrappedObject).setActionView((View)localObject);
    return this;
  }
  
  public MenuItem setAlphabeticShortcut(char paramChar)
  {
    ((SupportMenuItem)mWrappedObject).setAlphabeticShortcut(paramChar);
    return this;
  }
  
  public MenuItem setCheckable(boolean paramBoolean)
  {
    ((SupportMenuItem)mWrappedObject).setCheckable(paramBoolean);
    return this;
  }
  
  public MenuItem setChecked(boolean paramBoolean)
  {
    ((SupportMenuItem)mWrappedObject).setChecked(paramBoolean);
    return this;
  }
  
  public MenuItem setEnabled(boolean paramBoolean)
  {
    ((SupportMenuItem)mWrappedObject).setEnabled(paramBoolean);
    return this;
  }
  
  public void setExclusiveCheckable(boolean paramBoolean)
  {
    try
    {
      if (mSetExclusiveCheckableMethod == null) {
        mSetExclusiveCheckableMethod = ((SupportMenuItem)mWrappedObject).getClass().getDeclaredMethod("setExclusiveCheckable", new Class[] { Boolean.TYPE });
      }
      mSetExclusiveCheckableMethod.invoke(mWrappedObject, new Object[] { Boolean.valueOf(paramBoolean) });
      return;
    }
    catch (Exception localException)
    {
      Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", localException);
    }
  }
  
  public MenuItem setIcon(int paramInt)
  {
    ((SupportMenuItem)mWrappedObject).setIcon(paramInt);
    return this;
  }
  
  public MenuItem setIcon(Drawable paramDrawable)
  {
    ((SupportMenuItem)mWrappedObject).setIcon(paramDrawable);
    return this;
  }
  
  public MenuItem setIntent(Intent paramIntent)
  {
    ((SupportMenuItem)mWrappedObject).setIntent(paramIntent);
    return this;
  }
  
  public MenuItem setNumericShortcut(char paramChar)
  {
    ((SupportMenuItem)mWrappedObject).setNumericShortcut(paramChar);
    return this;
  }
  
  public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener paramOnActionExpandListener)
  {
    SupportMenuItem localSupportMenuItem = (SupportMenuItem)mWrappedObject;
    if (paramOnActionExpandListener != null) {}
    for (paramOnActionExpandListener = new OnActionExpandListenerWrapper(paramOnActionExpandListener);; paramOnActionExpandListener = null)
    {
      localSupportMenuItem.setSupportOnActionExpandListener(paramOnActionExpandListener);
      return this;
    }
  }
  
  public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener paramOnMenuItemClickListener)
  {
    SupportMenuItem localSupportMenuItem = (SupportMenuItem)mWrappedObject;
    if (paramOnMenuItemClickListener != null) {}
    for (paramOnMenuItemClickListener = new OnMenuItemClickListenerWrapper(paramOnMenuItemClickListener);; paramOnMenuItemClickListener = null)
    {
      localSupportMenuItem.setOnMenuItemClickListener(paramOnMenuItemClickListener);
      return this;
    }
  }
  
  public MenuItem setShortcut(char paramChar1, char paramChar2)
  {
    ((SupportMenuItem)mWrappedObject).setShortcut(paramChar1, paramChar2);
    return this;
  }
  
  public void setShowAsAction(int paramInt)
  {
    ((SupportMenuItem)mWrappedObject).setShowAsAction(paramInt);
  }
  
  public MenuItem setShowAsActionFlags(int paramInt)
  {
    ((SupportMenuItem)mWrappedObject).setShowAsActionFlags(paramInt);
    return this;
  }
  
  public MenuItem setTitle(int paramInt)
  {
    ((SupportMenuItem)mWrappedObject).setTitle(paramInt);
    return this;
  }
  
  public MenuItem setTitle(CharSequence paramCharSequence)
  {
    ((SupportMenuItem)mWrappedObject).setTitle(paramCharSequence);
    return this;
  }
  
  public MenuItem setTitleColor(ColorStateList paramColorStateList)
  {
    if ((mWrappedObject instanceof MenuItemImpl)) {
      ((MenuItemImpl)mWrappedObject).setTitleColor(paramColorStateList);
    }
    return this;
  }
  
  public MenuItem setTitleCondensed(CharSequence paramCharSequence)
  {
    ((SupportMenuItem)mWrappedObject).setTitleCondensed(paramCharSequence);
    return this;
  }
  
  public MenuItem setVisible(boolean paramBoolean)
  {
    return ((SupportMenuItem)mWrappedObject).setVisible(paramBoolean);
  }
  
  class ActionProviderWrapper
    extends android.support.v4.view.ActionProvider
  {
    final android.view.ActionProvider mInner;
    
    public ActionProviderWrapper(Context paramContext, android.view.ActionProvider paramActionProvider)
    {
      super();
      mInner = paramActionProvider;
    }
    
    public boolean hasSubMenu()
    {
      return mInner.hasSubMenu();
    }
    
    public View onCreateActionView()
    {
      return mInner.onCreateActionView();
    }
    
    public boolean onPerformDefaultAction()
    {
      return mInner.onPerformDefaultAction();
    }
    
    public void onPrepareSubMenu(SubMenu paramSubMenu)
    {
      mInner.onPrepareSubMenu(getSubMenuWrapper(paramSubMenu));
    }
  }
  
  static class CollapsibleActionViewWrapper
    extends FrameLayout
    implements android.support.v7.view.CollapsibleActionView
  {
    final android.view.CollapsibleActionView mWrappedView;
    
    CollapsibleActionViewWrapper(View paramView)
    {
      super();
      mWrappedView = ((android.view.CollapsibleActionView)paramView);
      addView(paramView);
    }
    
    View getWrappedView()
    {
      return (View)mWrappedView;
    }
    
    public void onActionViewCollapsed()
    {
      mWrappedView.onActionViewCollapsed();
    }
    
    public void onActionViewExpanded()
    {
      mWrappedView.onActionViewExpanded();
    }
  }
  
  class OnActionExpandListenerWrapper
    extends BaseWrapper<MenuItem.OnActionExpandListener>
    implements MenuItemCompat.OnActionExpandListener
  {
    OnActionExpandListenerWrapper(MenuItem.OnActionExpandListener paramOnActionExpandListener)
    {
      super();
    }
    
    public boolean onMenuItemActionCollapse(MenuItem paramMenuItem)
    {
      return ((MenuItem.OnActionExpandListener)mWrappedObject).onMenuItemActionCollapse(getMenuItemWrapper(paramMenuItem));
    }
    
    public boolean onMenuItemActionExpand(MenuItem paramMenuItem)
    {
      return ((MenuItem.OnActionExpandListener)mWrappedObject).onMenuItemActionExpand(getMenuItemWrapper(paramMenuItem));
    }
  }
  
  class OnMenuItemClickListenerWrapper
    extends BaseWrapper<MenuItem.OnMenuItemClickListener>
    implements MenuItem.OnMenuItemClickListener
  {
    OnMenuItemClickListenerWrapper(MenuItem.OnMenuItemClickListener paramOnMenuItemClickListener)
    {
      super();
    }
    
    public boolean onMenuItemClick(MenuItem paramMenuItem)
    {
      return ((MenuItem.OnMenuItemClickListener)mWrappedObject).onMenuItemClick(getMenuItemWrapper(paramMenuItem));
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.view.menu.MenuItemWrapperICS
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
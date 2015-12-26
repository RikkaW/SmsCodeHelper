package android.support.v7.app;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.style;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.support.v7.internal.view.menu.ListMenuPresenter;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.view.menu.MenuView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

final class AppCompatDelegateImplV7$PanelFeatureState
{
  int background;
  View createdPanelView;
  ViewGroup decorView;
  int featureId;
  Bundle frozenActionViewState;
  Bundle frozenMenuState;
  int gravity;
  boolean isHandled;
  boolean isOpen;
  boolean isPrepared;
  ListMenuPresenter listMenuPresenter;
  Context listPresenterContext;
  MenuBuilder menu;
  public boolean qwertyMode;
  boolean refreshDecorView;
  boolean refreshMenuContent;
  View shownPanelView;
  boolean wasLastOpen;
  int windowAnimations;
  int x;
  int y;
  
  AppCompatDelegateImplV7$PanelFeatureState(int paramInt)
  {
    featureId = paramInt;
    refreshDecorView = false;
  }
  
  void applyFrozenState()
  {
    if ((menu != null) && (frozenMenuState != null))
    {
      menu.restorePresenterStates(frozenMenuState);
      frozenMenuState = null;
    }
  }
  
  public void clearMenuPresenters()
  {
    if (menu != null) {
      menu.removeMenuPresenter(listMenuPresenter);
    }
    listMenuPresenter = null;
  }
  
  MenuView getListMenuView(MenuPresenter.Callback paramCallback)
  {
    if (menu == null) {
      return null;
    }
    if (listMenuPresenter == null)
    {
      listMenuPresenter = new ListMenuPresenter(listPresenterContext, R.layout.abc_list_menu_item_layout);
      listMenuPresenter.setCallback(paramCallback);
      menu.addMenuPresenter(listMenuPresenter);
    }
    return listMenuPresenter.getMenuView(decorView);
  }
  
  public boolean hasPanelItems()
  {
    boolean bool2 = true;
    boolean bool1;
    if (shownPanelView == null) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (createdPanelView != null);
      bool1 = bool2;
    } while (listMenuPresenter.getAdapter().getCount() > 0);
    return false;
  }
  
  void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    featureId = featureId;
    wasLastOpen = isOpen;
    frozenMenuState = menuState;
    shownPanelView = null;
    decorView = null;
  }
  
  Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(null);
    featureId = featureId;
    isOpen = isOpen;
    if (menu != null)
    {
      menuState = new Bundle();
      menu.savePresenterStates(menuState);
    }
    return localSavedState;
  }
  
  void setMenu(MenuBuilder paramMenuBuilder)
  {
    if (paramMenuBuilder == menu) {}
    do
    {
      return;
      if (menu != null) {
        menu.removeMenuPresenter(listMenuPresenter);
      }
      menu = paramMenuBuilder;
    } while ((paramMenuBuilder == null) || (listMenuPresenter == null));
    paramMenuBuilder.addMenuPresenter(listMenuPresenter);
  }
  
  void setStyle(Context paramContext)
  {
    TypedValue localTypedValue = new TypedValue();
    Resources.Theme localTheme = paramContext.getResources().newTheme();
    localTheme.setTo(paramContext.getTheme());
    localTheme.resolveAttribute(R.attr.actionBarPopupTheme, localTypedValue, true);
    if (resourceId != 0) {
      localTheme.applyStyle(resourceId, true);
    }
    localTheme.resolveAttribute(R.attr.panelMenuListTheme, localTypedValue, true);
    if (resourceId != 0) {
      localTheme.applyStyle(resourceId, true);
    }
    for (;;)
    {
      paramContext = new ContextThemeWrapper(paramContext, 0);
      paramContext.getTheme().setTo(localTheme);
      listPresenterContext = paramContext;
      paramContext = paramContext.obtainStyledAttributes(R.styleable.Theme);
      background = paramContext.getResourceId(R.styleable.Theme_panelBackground, 0);
      windowAnimations = paramContext.getResourceId(R.styleable.Theme_android_windowAnimationStyle, 0);
      paramContext.recycle();
      return;
      localTheme.applyStyle(R.style.Theme_AppCompat_CompactMenu, true);
    }
  }
  
  static class SavedState
    implements Parcelable
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new AppCompatDelegateImplV7.PanelFeatureState.SavedState.1();
    int featureId;
    boolean isOpen;
    Bundle menuState;
    
    private static SavedState readFromParcel(Parcel paramParcel)
    {
      boolean bool = true;
      SavedState localSavedState = new SavedState();
      featureId = paramParcel.readInt();
      if (paramParcel.readInt() == 1) {}
      for (;;)
      {
        isOpen = bool;
        if (isOpen) {
          menuState = paramParcel.readBundle();
        }
        return localSavedState;
        bool = false;
      }
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(featureId);
      if (isOpen) {}
      for (paramInt = 1;; paramInt = 0)
      {
        paramParcel.writeInt(paramInt);
        if (isOpen) {
          paramParcel.writeBundle(menuState);
        }
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatDelegateImplV7.PanelFeatureState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
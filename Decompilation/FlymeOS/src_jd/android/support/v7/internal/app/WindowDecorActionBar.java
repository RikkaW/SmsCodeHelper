package android.support.v7.internal.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBar.OnMenuVisibilityListener;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBar.TabListenerSDK;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuBuilder.Callback;
import android.support.v7.internal.view.menu.MenuPopupHelper;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.internal.widget.ActionBarContainer;
import android.support.v7.internal.widget.ActionBarContextView;
import android.support.v7.internal.widget.ActionBarOverlayLayout;
import android.support.v7.internal.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.BackPressedListener;
import android.support.v7.view.ActionMode.Callback;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.SpinnerAdapter;
import aqy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class WindowDecorActionBar
  extends ActionBar
  implements ActionBarOverlayLayout.ActionBarVisibilityCallback
{
  private static final boolean ALLOW_SHOW_HIDE_ANIMATIONS;
  private static final int CONTEXT_DISPLAY_NORMAL = 0;
  private static final int CONTEXT_DISPLAY_SPLIT = 1;
  private static final int INVALID_POSITION = -1;
  private static final String TAG = "WindowDecorActionBar";
  ActionModeImpl mActionMode;
  private Activity mActivity;
  private ActionBarContainer mContainerView;
  private boolean mContentAnimations = true;
  private View mContentView;
  private Context mContext;
  private int mContextDisplayMode;
  private ActionBarContextView mContextView;
  private int mCurWindowVisibility = 0;
  private ViewPropertyAnimatorCompatSet mCurrentShowAnim;
  private DecorToolbar mDecorToolbar;
  ActionMode mDeferredDestroyActionMode;
  ActionMode.Callback mDeferredModeDestroyCallback;
  private Dialog mDialog;
  private boolean mDisplayHomeAsUpSet;
  private int mDuration = 250;
  private boolean mHasEmbeddedTabs;
  private boolean mHiddenByApp;
  private boolean mHiddenBySystem;
  final ViewPropertyAnimatorListener mHideListener = new WindowDecorActionBar.1(this);
  boolean mHideOnContentScroll;
  private boolean mLastMenuVisibility;
  private ArrayList<ActionBar.OnMenuVisibilityListener> mMenuVisibilityListeners = new ArrayList();
  private boolean mNowShowing = true;
  private ActionBarOverlayLayout mOverlayLayout;
  private int mSavedTabPosition = -1;
  private TabImpl mSelectedTab;
  private boolean mShowHideAnimationEnabled;
  final ViewPropertyAnimatorListener mShowListener = new WindowDecorActionBar.2(this);
  private boolean mShowingForMode;
  private ActionBarContainer mSplitView;
  private ScrollingTabContainerView mTabScrollView;
  private ArrayList<TabImpl> mTabs = new ArrayList();
  private Context mThemedContext;
  private TintManager mTintManager;
  final ViewPropertyAnimatorUpdateListener mUpdateListener = new WindowDecorActionBar.3(this);
  private boolean mforceShowTab;
  
  static
  {
    boolean bool2 = true;
    if (!WindowDecorActionBar.class.desiredAssertionStatus())
    {
      bool1 = true;
      $assertionsDisabled = bool1;
      if (Build.VERSION.SDK_INT < 14) {
        break label36;
      }
    }
    label36:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      ALLOW_SHOW_HIDE_ANIMATIONS = bool1;
      return;
      bool1 = false;
      break;
    }
  }
  
  public WindowDecorActionBar(Activity paramActivity, boolean paramBoolean)
  {
    mActivity = paramActivity;
    paramActivity = paramActivity.getWindow().getDecorView();
    init(paramActivity);
    if (!paramBoolean) {
      mContentView = paramActivity.findViewById(16908290);
    }
  }
  
  public WindowDecorActionBar(Dialog paramDialog)
  {
    mDialog = paramDialog;
    init(paramDialog.getWindow().getDecorView());
  }
  
  public WindowDecorActionBar(View paramView)
  {
    assert (paramView.isInEditMode());
    init(paramView);
  }
  
  private void animateToMultiChoiceMode(boolean paramBoolean)
  {
    ActionBarContextView localActionBarContextView = mContextView;
    if (paramBoolean) {}
    for (int i = 0;; i = 8)
    {
      localActionBarContextView.multiChoiceMenuViewAnimateToVisibility(i);
      return;
    }
  }
  
  private static boolean checkShowingFlags(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (paramBoolean3) {}
    while ((!paramBoolean1) && (!paramBoolean2)) {
      return true;
    }
    return false;
  }
  
  private void cleanupTabs()
  {
    if (mSelectedTab != null) {
      selectTab(null);
    }
    mTabs.clear();
    if (mTabScrollView != null) {
      mTabScrollView.removeAllTabs();
    }
    mSavedTabPosition = -1;
  }
  
  private void configureTab(ActionBar.Tab paramTab, int paramInt)
  {
    paramTab = (TabImpl)paramTab;
    ActionBar.TabListener localTabListener = paramTab.getCallback();
    ActionBar.TabListenerSDK localTabListenerSDK = paramTab.getCallbackSDK();
    if ((localTabListener == null) && (localTabListenerSDK == null)) {
      throw new IllegalStateException("Action Bar Tab must have a Callback");
    }
    paramTab.setPosition(paramInt);
    mTabs.add(paramInt, paramTab);
    int i = mTabs.size();
    paramInt += 1;
    while (paramInt < i)
    {
      ((TabImpl)mTabs.get(paramInt)).setPosition(paramInt);
      paramInt += 1;
    }
  }
  
  private void ensureTabsExist()
  {
    if (mTabScrollView != null) {
      return;
    }
    ScrollingTabContainerView localScrollingTabContainerView = new ScrollingTabContainerView(mContext);
    localScrollingTabContainerView.setId(R.id.mz_action_bar_tab_scroll_view);
    if (mHasEmbeddedTabs)
    {
      localScrollingTabContainerView.setVisibility(0);
      mDecorToolbar.setEmbeddedTabView(localScrollingTabContainerView);
      localScrollingTabContainerView.showAtToolbar(true);
      mTabScrollView = localScrollingTabContainerView;
      return;
    }
    if (getNavigationMode() == 2)
    {
      localScrollingTabContainerView.setVisibility(0);
      if (mOverlayLayout != null) {
        ViewCompat.requestApplyInsets(mOverlayLayout);
      }
    }
    for (;;)
    {
      mContainerView.setTabContainer(localScrollingTabContainerView);
      localScrollingTabContainerView.showAtToolbar(false);
      break;
      localScrollingTabContainerView.setVisibility(8);
    }
  }
  
  private DecorToolbar getDecorToolbar(View paramView)
  {
    if ((paramView instanceof DecorToolbar)) {
      return (DecorToolbar)paramView;
    }
    if ((paramView instanceof Toolbar)) {
      return ((Toolbar)paramView).getWrapper();
    }
    if ("Can't make a decor toolbar out of " + paramView != null) {}
    for (paramView = paramView.getClass().getSimpleName();; paramView = "null") {
      throw new IllegalStateException(paramView);
    }
  }
  
  private void hideForActionMode()
  {
    if (mShowingForMode)
    {
      mShowingForMode = false;
      if (mOverlayLayout != null) {
        mOverlayLayout.setShowingForActionMode(false);
      }
      updateVisibility(false);
    }
  }
  
  private void init(View paramView)
  {
    mOverlayLayout = ((ActionBarOverlayLayout)paramView.findViewById(R.id.decor_content_parent));
    if (mOverlayLayout != null) {
      mOverlayLayout.setActionBarVisibilityCallback(this);
    }
    mDecorToolbar = getDecorToolbar(paramView.findViewById(R.id.action_bar));
    mContextView = ((ActionBarContextView)paramView.findViewById(R.id.action_context_bar));
    mContainerView = ((ActionBarContainer)paramView.findViewById(R.id.action_bar_container));
    mSplitView = ((ActionBarContainer)paramView.findViewById(R.id.split_action_bar));
    if ((mDecorToolbar == null) || (mContextView == null) || (mContainerView == null)) {
      throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with a compatible window decor layout");
    }
    mContext = mDecorToolbar.getContext();
    int i;
    if (mDecorToolbar.isSplit())
    {
      i = 1;
      mContextDisplayMode = i;
      int j = mDecorToolbar.getDisplayOptions();
      if ((j & 0x4) == 0) {
        break label334;
      }
      i = 1;
      label197:
      if (i != 0) {
        mDisplayHomeAsUpSet = true;
      }
      paramView = ActionBarPolicy.get(mContext);
      if ((!paramView.enableHomeButtonByDefault()) && (i == 0)) {
        break label339;
      }
      bool = true;
      label228:
      setHomeButtonEnabled(bool);
      if ((j & 0x40) == 0) {
        break label345;
      }
      bool = true;
      label244:
      mforceShowTab = bool;
      if ((!paramView.hasEmbeddedTabs()) && (!mforceShowTab)) {
        break label351;
      }
    }
    label334:
    label339:
    label345:
    label351:
    for (boolean bool = true;; bool = false)
    {
      setHasEmbeddedTabs(bool);
      paramView = mContext.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
      if (paramView.getBoolean(R.styleable.ActionBar_hideOnContentScroll, false)) {
        setHideOnContentScrollEnabled(true);
      }
      i = paramView.getDimensionPixelSize(R.styleable.ActionBar_elevation, 0);
      if (i != 0) {
        setElevation(i);
      }
      paramView.recycle();
      return;
      i = 0;
      break;
      i = 0;
      break label197;
      bool = false;
      break label228;
      bool = false;
      break label244;
    }
  }
  
  private void setHasEmbeddedTabs(boolean paramBoolean)
  {
    boolean bool = true;
    mHasEmbeddedTabs = paramBoolean;
    int i;
    label60:
    label93:
    Object localObject;
    if (!mHasEmbeddedTabs)
    {
      mDecorToolbar.setEmbeddedTabView(null);
      mContainerView.setTabContainer(mTabScrollView);
      if (mTabScrollView != null) {
        mTabScrollView.showAtToolbar(false);
      }
      if (getNavigationMode() != 2) {
        break label185;
      }
      i = 1;
      if (mTabScrollView != null)
      {
        if (i == 0) {
          break label190;
        }
        mTabScrollView.setVisibility(0);
        if (mOverlayLayout != null) {
          ViewCompat.requestApplyInsets(mOverlayLayout);
        }
      }
      localObject = mDecorToolbar;
      if ((mHasEmbeddedTabs) || (i == 0)) {
        break label202;
      }
      paramBoolean = true;
      label112:
      ((DecorToolbar)localObject).setCollapsible(paramBoolean);
      localObject = mOverlayLayout;
      if ((mHasEmbeddedTabs) || (i == 0)) {
        break label207;
      }
    }
    label185:
    label190:
    label202:
    label207:
    for (paramBoolean = bool;; paramBoolean = false)
    {
      ((ActionBarOverlayLayout)localObject).setHasNonEmbeddedTabs(paramBoolean);
      return;
      mContainerView.setTabContainer(null);
      mDecorToolbar.setEmbeddedTabView(mTabScrollView);
      if (mTabScrollView == null) {
        break;
      }
      mTabScrollView.showAtToolbar(true);
      break;
      i = 0;
      break label60;
      mTabScrollView.setVisibility(8);
      break label93;
      paramBoolean = false;
      break label112;
    }
  }
  
  private void showForActionMode()
  {
    if (!mShowingForMode)
    {
      mShowingForMode = true;
      if (mOverlayLayout != null) {
        mOverlayLayout.setShowingForActionMode(true);
      }
      updateVisibility(false);
    }
  }
  
  private void updateVisibility(boolean paramBoolean)
  {
    if (checkShowingFlags(mHiddenByApp, mHiddenBySystem, mShowingForMode)) {
      if (!mNowShowing)
      {
        mNowShowing = true;
        doShow(paramBoolean);
      }
    }
    while (!mNowShowing) {
      return;
    }
    mNowShowing = false;
    doHide(paramBoolean);
  }
  
  public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener paramOnMenuVisibilityListener)
  {
    mMenuVisibilityListeners.add(paramOnMenuVisibilityListener);
  }
  
  public void addTab(ActionBar.Tab paramTab)
  {
    addTab(paramTab, mTabs.isEmpty());
  }
  
  public void addTab(ActionBar.Tab paramTab, int paramInt)
  {
    addTab(paramTab, paramInt, mTabs.isEmpty());
  }
  
  public void addTab(ActionBar.Tab paramTab, int paramInt, boolean paramBoolean)
  {
    ensureTabsExist();
    mTabScrollView.addTab(paramTab, paramInt, paramBoolean);
    configureTab(paramTab, paramInt);
    if (paramBoolean) {
      selectTab(paramTab);
    }
  }
  
  public void addTab(ActionBar.Tab paramTab, boolean paramBoolean)
  {
    ensureTabsExist();
    mTabScrollView.addTab(paramTab, paramBoolean);
    configureTab(paramTab, mTabs.size());
    if (paramBoolean) {
      selectTab(paramTab);
    }
  }
  
  public void animateToMode(boolean paramBoolean)
  {
    int j = 0;
    Object localObject;
    if (paramBoolean)
    {
      showForActionMode();
      localObject = mDecorToolbar;
      if (!paramBoolean) {
        break label57;
      }
      i = 8;
      label23:
      ((DecorToolbar)localObject).animateToVisibility(i);
      localObject = mContextView;
      if (!paramBoolean) {
        break label62;
      }
    }
    label57:
    label62:
    for (int i = j;; i = 8)
    {
      ((ActionBarContextView)localObject).animateToVisibility(i);
      return;
      hideForActionMode();
      break;
      i = 0;
      break label23;
    }
  }
  
  public void animateToMode(boolean paramBoolean1, boolean paramBoolean2)
  {
    int j = 0;
    Object localObject;
    if (paramBoolean2)
    {
      showForActionMode();
      localObject = mDecorToolbar;
      if (!paramBoolean1) {
        break label59;
      }
      i = 8;
      label24:
      ((DecorToolbar)localObject).animateToVisibility(i);
      localObject = mContextView;
      if (!paramBoolean1) {
        break label64;
      }
    }
    label59:
    label64:
    for (int i = j;; i = 8)
    {
      ((ActionBarContextView)localObject).animateToVisibility(i);
      return;
      hideForActionMode();
      break;
      i = 0;
      break label24;
    }
  }
  
  public boolean collapseActionView()
  {
    if ((mDecorToolbar != null) && (mDecorToolbar.hasExpandedActionView()))
    {
      mDecorToolbar.collapseActionView();
      return true;
    }
    return false;
  }
  
  void completeDeferredDestroyActionMode()
  {
    if (mDeferredModeDestroyCallback != null)
    {
      mDeferredModeDestroyCallback.onDestroyActionMode(mDeferredDestroyActionMode);
      mDeferredDestroyActionMode = null;
      mDeferredModeDestroyCallback = null;
    }
  }
  
  public void dispatchMenuVisibilityChanged(boolean paramBoolean)
  {
    if (paramBoolean == mLastMenuVisibility) {}
    for (;;)
    {
      return;
      mLastMenuVisibility = paramBoolean;
      int j = mMenuVisibilityListeners.size();
      int i = 0;
      while (i < j)
      {
        ((ActionBar.OnMenuVisibilityListener)mMenuVisibilityListeners.get(i)).onMenuVisibilityChanged(paramBoolean);
        i += 1;
      }
    }
  }
  
  public void doHide(boolean paramBoolean)
  {
    if (mCurrentShowAnim != null) {
      mCurrentShowAnim.cancel();
    }
    if ((mCurWindowVisibility == 0) && (ALLOW_SHOW_HIDE_ANIMATIONS) && ((mShowHideAnimationEnabled) || (paramBoolean)))
    {
      ViewCompat.setAlpha(mContainerView, 1.0F);
      mContainerView.setTransitioning(true);
      ViewPropertyAnimatorCompatSet localViewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
      float f2 = -mContainerView.getHeight();
      float f1 = f2;
      if (paramBoolean)
      {
        localObject = new int[2];
        Object tmp86_84 = localObject;
        tmp86_84[0] = 0;
        Object tmp90_86 = tmp86_84;
        tmp90_86[1] = 0;
        tmp90_86;
        mContainerView.getLocationInWindow((int[])localObject);
        f1 = f2 - localObject[1];
      }
      Object localObject = ViewCompat.animate(mContainerView).translationY(f1);
      ((ViewPropertyAnimatorCompat)localObject).setUpdateListener(mUpdateListener);
      localViewPropertyAnimatorCompatSet.play((ViewPropertyAnimatorCompat)localObject);
      if ((mContentAnimations) && (mContentView != null)) {
        localViewPropertyAnimatorCompatSet.play(ViewCompat.animate(mContentView).translationY(f1));
      }
      if ((mSplitView != null) && (mSplitView.getVisibility() == 0))
      {
        ViewCompat.setAlpha(mSplitView, 1.0F);
        localViewPropertyAnimatorCompatSet.play(ViewCompat.animate(mSplitView).translationY(mSplitView.getHeight()));
      }
      localViewPropertyAnimatorCompatSet.setInterpolator(AnimationUtils.loadInterpolator(mContext, 17432581));
      localViewPropertyAnimatorCompatSet.setDuration(mDuration);
      localViewPropertyAnimatorCompatSet.setListener(mHideListener);
      mCurrentShowAnim = localViewPropertyAnimatorCompatSet;
      localViewPropertyAnimatorCompatSet.start();
      return;
    }
    mHideListener.onAnimationEnd(null);
  }
  
  public void doShow(boolean paramBoolean)
  {
    if (mCurrentShowAnim != null) {
      mCurrentShowAnim.cancel();
    }
    mContainerView.setVisibility(0);
    if ((mCurWindowVisibility == 0) && (ALLOW_SHOW_HIDE_ANIMATIONS) && ((mShowHideAnimationEnabled) || (paramBoolean)))
    {
      ViewCompat.setTranslationY(mContainerView, 0.0F);
      float f2 = -mContainerView.getHeight();
      float f1 = f2;
      if (paramBoolean)
      {
        localObject = new int[2];
        Object tmp77_75 = localObject;
        tmp77_75[0] = 0;
        Object tmp81_77 = tmp77_75;
        tmp81_77[1] = 0;
        tmp81_77;
        mContainerView.getLocationInWindow((int[])localObject);
        f1 = f2 - localObject[1];
      }
      ViewCompat.setTranslationY(mContainerView, f1);
      Object localObject = new ViewPropertyAnimatorCompatSet();
      ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat = ViewCompat.animate(mContainerView).translationY(0.0F);
      localViewPropertyAnimatorCompat.setUpdateListener(mUpdateListener);
      ((ViewPropertyAnimatorCompatSet)localObject).play(localViewPropertyAnimatorCompat);
      if ((mContentAnimations) && (mContentView != null))
      {
        ViewCompat.setTranslationY(mContentView, f1);
        ((ViewPropertyAnimatorCompatSet)localObject).play(ViewCompat.animate(mContentView).translationY(0.0F));
      }
      if (mSplitView != null)
      {
        ViewCompat.setTranslationY(mSplitView, mSplitView.getHeight());
        mSplitView.setVisibility(0);
        ((ViewPropertyAnimatorCompatSet)localObject).play(ViewCompat.animate(mSplitView).translationY(0.0F));
      }
      ((ViewPropertyAnimatorCompatSet)localObject).setInterpolator(AnimationUtils.loadInterpolator(mContext, 17432582));
      ((ViewPropertyAnimatorCompatSet)localObject).setDuration(mDuration);
      ((ViewPropertyAnimatorCompatSet)localObject).setListener(mShowListener);
      mCurrentShowAnim = ((ViewPropertyAnimatorCompatSet)localObject);
      ((ViewPropertyAnimatorCompatSet)localObject).start();
    }
    for (;;)
    {
      if (mOverlayLayout != null) {
        ViewCompat.requestApplyInsets(mOverlayLayout);
      }
      return;
      ViewCompat.setAlpha(mContainerView, 1.0F);
      ViewCompat.setTranslationY(mContainerView, 0.0F);
      if ((mContentAnimations) && (mContentView != null)) {
        ViewCompat.setTranslationY(mContentView, 0.0F);
      }
      if ((mSplitView != null) && (mContextDisplayMode == 1))
      {
        ViewCompat.setAlpha(mSplitView, 1.0F);
        ViewCompat.setTranslationY(mSplitView, 0.0F);
        mSplitView.setVisibility(0);
      }
      mShowListener.onAnimationEnd(null);
    }
  }
  
  public void enableContentAnimations(boolean paramBoolean)
  {
    mContentAnimations = paramBoolean;
  }
  
  public aqy getActionBarTabContainer()
  {
    return (aqy)mContainerView.getTabContainer();
  }
  
  public ArrayList getAllTabs()
  {
    return mTabs;
  }
  
  public View getCustomView()
  {
    return mDecorToolbar.getCustomView();
  }
  
  public int getDisplayOptions()
  {
    return mDecorToolbar.getDisplayOptions();
  }
  
  public float getElevation()
  {
    return ViewCompat.getElevation(mContainerView);
  }
  
  public int getHeight()
  {
    return mContainerView.getHeight();
  }
  
  public int getHideOffset()
  {
    return mOverlayLayout.getActionBarHideOffset();
  }
  
  public int getNavigationItemCount()
  {
    switch (mDecorToolbar.getNavigationMode())
    {
    default: 
      return 0;
    case 2: 
      return mTabs.size();
    }
    return mDecorToolbar.getDropdownItemCount();
  }
  
  public int getNavigationMode()
  {
    return mDecorToolbar.getNavigationMode();
  }
  
  public int getSelectedNavigationIndex()
  {
    switch (mDecorToolbar.getNavigationMode())
    {
    default: 
    case 2: 
      do
      {
        return -1;
      } while (mSelectedTab == null);
      return mSelectedTab.getPosition();
    }
    return mDecorToolbar.getDropdownSelectedPosition();
  }
  
  public ActionBar.Tab getSelectedTab()
  {
    return mSelectedTab;
  }
  
  public CharSequence getSubtitle()
  {
    return mDecorToolbar.getSubtitle();
  }
  
  public ActionBar.Tab getTabAt(int paramInt)
  {
    return (ActionBar.Tab)mTabs.get(paramInt);
  }
  
  public int getTabCount()
  {
    return mTabs.size();
  }
  
  public Context getThemedContext()
  {
    int i;
    if (mThemedContext == null)
    {
      TypedValue localTypedValue = new TypedValue();
      mContext.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, localTypedValue, true);
      i = resourceId;
      if (i == 0) {
        break label61;
      }
    }
    label61:
    for (mThemedContext = new ContextThemeWrapper(mContext, i);; mThemedContext = mContext) {
      return mThemedContext;
    }
  }
  
  TintManager getTintManager()
  {
    if (mTintManager == null) {
      mTintManager = TintManager.get(mContext);
    }
    return mTintManager;
  }
  
  public CharSequence getTitle()
  {
    return mDecorToolbar.getTitle();
  }
  
  public boolean hasIcon()
  {
    return mDecorToolbar.hasIcon();
  }
  
  public boolean hasLogo()
  {
    return mDecorToolbar.hasLogo();
  }
  
  public void hide()
  {
    if (!mHiddenByApp)
    {
      mHiddenByApp = true;
      updateVisibility(false);
    }
  }
  
  public void hide(int paramInt)
  {
    mDuration = paramInt;
    hide();
  }
  
  public void hideForSystem()
  {
    if (!mHiddenBySystem)
    {
      mHiddenBySystem = true;
      updateVisibility(true);
    }
  }
  
  public boolean isHideOnContentScrollEnabled()
  {
    return mOverlayLayout.isHideOnContentScrollEnabled();
  }
  
  public boolean isShowing()
  {
    int i = getHeight();
    return (mNowShowing) && ((i == 0) || (getHideOffset() < i));
  }
  
  public boolean isTitleTruncated()
  {
    return (mDecorToolbar != null) && (mDecorToolbar.isTitleTruncated());
  }
  
  public ActionBar.Tab newTab()
  {
    return new TabImpl();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if ((ActionBarPolicy.get(mContext).hasEmbeddedTabs()) || (mforceShowTab)) {}
    for (boolean bool = true;; bool = false)
    {
      setHasEmbeddedTabs(bool);
      return;
    }
  }
  
  public void onContentScrollStarted()
  {
    if (mCurrentShowAnim != null)
    {
      mCurrentShowAnim.cancel();
      mCurrentShowAnim = null;
    }
  }
  
  public void onContentScrollStopped() {}
  
  public void onWindowVisibilityChanged(int paramInt)
  {
    mCurWindowVisibility = paramInt;
  }
  
  public void removeAllTabs()
  {
    cleanupTabs();
  }
  
  public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener paramOnMenuVisibilityListener)
  {
    mMenuVisibilityListeners.remove(paramOnMenuVisibilityListener);
  }
  
  public void removeTab(ActionBar.Tab paramTab)
  {
    removeTabAt(paramTab.getPosition());
  }
  
  public void removeTabAt(int paramInt)
  {
    if (mTabScrollView == null) {}
    int i;
    do
    {
      return;
      if (mSelectedTab != null) {}
      for (i = mSelectedTab.getPosition();; i = mSavedTabPosition)
      {
        mTabScrollView.removeTabAt(paramInt);
        localTabImpl = (TabImpl)mTabs.remove(paramInt);
        if (localTabImpl != null) {
          localTabImpl.setPosition(-1);
        }
        int k = mTabs.size();
        int j = paramInt;
        while (j < k)
        {
          ((TabImpl)mTabs.get(j)).setPosition(j);
          j += 1;
        }
      }
    } while (i != paramInt);
    if (mTabs.isEmpty()) {}
    for (TabImpl localTabImpl = null;; localTabImpl = (TabImpl)mTabs.get(Math.max(0, paramInt - 1)))
    {
      selectTab(localTabImpl);
      return;
    }
  }
  
  public void selectTab(ActionBar.Tab paramTab)
  {
    android.app.FragmentTransaction localFragmentTransaction1 = null;
    int i = -1;
    if (getNavigationMode() != 2) {
      if (paramTab != null)
      {
        i = paramTab.getPosition();
        mSavedTabPosition = i;
      }
    }
    label90:
    label132:
    label165:
    label183:
    label204:
    label304:
    label325:
    label344:
    for (;;)
    {
      return;
      i = -1;
      break;
      android.support.v4.app.FragmentTransaction localFragmentTransaction;
      if (((mActivity instanceof FragmentActivity)) && (!mDecorToolbar.getViewGroup().isInEditMode()))
      {
        localFragmentTransaction = ((FragmentActivity)mActivity).getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
        if (!mDecorToolbar.getViewGroup().isInEditMode()) {
          break label165;
        }
        if (mSelectedTab != paramTab) {
          break label204;
        }
        if (mSelectedTab != null)
        {
          if (mSelectedTab.getCallback() == null) {
            break label183;
          }
          mSelectedTab.getCallback().onTabReselected(mSelectedTab, localFragmentTransaction);
          mTabScrollView.animateToTab(paramTab.getPosition());
        }
      }
      for (;;)
      {
        if ((localFragmentTransaction == null) || (localFragmentTransaction.isEmpty())) {
          break label344;
        }
        localFragmentTransaction.commit();
        return;
        localFragmentTransaction = null;
        break;
        localFragmentTransaction1 = mActivity.getFragmentManager().beginTransaction().disallowAddToBackStack();
        break label90;
        mSelectedTab.getCallbackSDK().onTabReselected(mSelectedTab, localFragmentTransaction1);
        break label132;
        ScrollingTabContainerView localScrollingTabContainerView = mTabScrollView;
        if (paramTab != null) {
          i = paramTab.getPosition();
        }
        localScrollingTabContainerView.setTabSelected(i);
        if (mSelectedTab != null)
        {
          if (mSelectedTab.getCallback() == null) {
            break label304;
          }
          mSelectedTab.getCallback().onTabUnselected(mSelectedTab, localFragmentTransaction);
        }
        for (;;)
        {
          mSelectedTab = ((TabImpl)paramTab);
          if (mSelectedTab == null) {
            break;
          }
          if (mSelectedTab.getCallback() == null) {
            break label325;
          }
          mSelectedTab.getCallback().onTabSelected(mSelectedTab, localFragmentTransaction);
          break;
          mSelectedTab.getCallbackSDK().onTabUnselected(mSelectedTab, localFragmentTransaction1);
        }
        mSelectedTab.getCallbackSDK().onTabSelected(mSelectedTab, localFragmentTransaction1);
      }
    }
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    mContainerView.setPrimaryBackground(paramDrawable);
  }
  
  public void setCustomView(int paramInt)
  {
    setCustomView(LayoutInflater.from(getThemedContext()).inflate(paramInt, mDecorToolbar.getViewGroup(), false));
  }
  
  public void setCustomView(View paramView)
  {
    mDecorToolbar.setCustomView(paramView);
  }
  
  public void setCustomView(View paramView, ActionBar.LayoutParams paramLayoutParams)
  {
    paramView.setLayoutParams(paramLayoutParams);
    mDecorToolbar.setCustomView(paramView);
  }
  
  public void setDefaultDisplayHomeAsUpEnabled(boolean paramBoolean)
  {
    if (!mDisplayHomeAsUpSet) {
      setDisplayHomeAsUpEnabled(paramBoolean);
    }
  }
  
  public void setDisplayHomeAsUpEnabled(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 4;; i = 0)
    {
      setDisplayOptions(i, 4);
      return;
    }
  }
  
  public void setDisplayOptions(int paramInt)
  {
    if ((paramInt & 0x4) != 0) {
      mDisplayHomeAsUpSet = true;
    }
    mDecorToolbar.setDisplayOptions(paramInt);
  }
  
  public void setDisplayOptions(int paramInt1, int paramInt2)
  {
    int i = mDecorToolbar.getDisplayOptions();
    if ((paramInt2 & 0x4) != 0) {
      mDisplayHomeAsUpSet = true;
    }
    mDecorToolbar.setDisplayOptions(i & (paramInt2 ^ 0xFFFFFFFF) | paramInt1 & paramInt2);
  }
  
  public void setDisplayShowCustomEnabled(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 16;; i = 0)
    {
      setDisplayOptions(i, 16);
      return;
    }
  }
  
  public void setDisplayShowHomeEnabled(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 2;; i = 0)
    {
      setDisplayOptions(i, 2);
      return;
    }
  }
  
  public void setDisplayShowTabEnabled(boolean paramBoolean)
  {
    mforceShowTab = paramBoolean;
    setHasEmbeddedTabs(paramBoolean);
  }
  
  public void setDisplayShowTitleEnabled(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 8;; i = 0)
    {
      setDisplayOptions(i, 8);
      return;
    }
  }
  
  public void setDisplayUseLogoEnabled(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      setDisplayOptions(i, 1);
      return;
    }
  }
  
  public void setElevation(float paramFloat)
  {
    ViewCompat.setElevation(mContainerView, paramFloat);
    if (mSplitView != null) {
      ViewCompat.setElevation(mSplitView, paramFloat);
    }
  }
  
  public void setHideOffset(int paramInt)
  {
    if ((paramInt != 0) && (!mOverlayLayout.isInOverlayMode())) {
      throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to set a non-zero hide offset");
    }
    mOverlayLayout.setActionBarHideOffset(paramInt);
  }
  
  public void setHideOnContentScrollEnabled(boolean paramBoolean)
  {
    if ((paramBoolean) && (!mOverlayLayout.isInOverlayMode())) {
      throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
    }
    mHideOnContentScroll = paramBoolean;
    mOverlayLayout.setHideOnContentScrollEnabled(paramBoolean);
  }
  
  public void setHomeActionContentDescription(int paramInt)
  {
    mDecorToolbar.setNavigationContentDescription(paramInt);
  }
  
  public void setHomeActionContentDescription(CharSequence paramCharSequence)
  {
    mDecorToolbar.setNavigationContentDescription(paramCharSequence);
  }
  
  public void setHomeAsUpIndicator(int paramInt)
  {
    mDecorToolbar.setNavigationIcon(paramInt);
  }
  
  public void setHomeAsUpIndicator(Drawable paramDrawable)
  {
    mDecorToolbar.setNavigationIcon(paramDrawable);
  }
  
  public void setHomeButtonEnabled(boolean paramBoolean)
  {
    mDecorToolbar.setHomeButtonEnabled(paramBoolean);
  }
  
  public void setIcon(int paramInt)
  {
    mDecorToolbar.setIcon(paramInt);
  }
  
  public void setIcon(Drawable paramDrawable)
  {
    mDecorToolbar.setIcon(paramDrawable);
  }
  
  public void setListNavigationCallbacks(SpinnerAdapter paramSpinnerAdapter, ActionBar.OnNavigationListener paramOnNavigationListener)
  {
    mDecorToolbar.setDropdownParams(paramSpinnerAdapter, new NavItemSelectedListener(paramOnNavigationListener));
  }
  
  public void setLogo(int paramInt)
  {
    mDecorToolbar.setLogo(paramInt);
  }
  
  public void setLogo(Drawable paramDrawable)
  {
    mDecorToolbar.setLogo(paramDrawable);
  }
  
  public void setNavigationMode(int paramInt)
  {
    boolean bool2 = true;
    int i = mDecorToolbar.getNavigationMode();
    label88:
    Object localObject;
    switch (i)
    {
    default: 
      if ((i != paramInt) && (!mHasEmbeddedTabs) && (mOverlayLayout != null)) {
        ViewCompat.requestApplyInsets(mOverlayLayout);
      }
      mDecorToolbar.setNavigationMode(paramInt);
      switch (paramInt)
      {
      default: 
        localObject = mDecorToolbar;
        if ((paramInt == 2) && (!mHasEmbeddedTabs))
        {
          bool1 = true;
          label108:
          ((DecorToolbar)localObject).setCollapsible(bool1);
          localObject = mOverlayLayout;
          if ((paramInt != 2) || (mHasEmbeddedTabs)) {
            break label210;
          }
        }
        break;
      }
      break;
    }
    label210:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      ((ActionBarOverlayLayout)localObject).setHasNonEmbeddedTabs(bool1);
      return;
      mSavedTabPosition = getSelectedNavigationIndex();
      selectTab(null);
      mTabScrollView.setVisibility(8);
      break;
      ensureTabsExist();
      mTabScrollView.setVisibility(0);
      if (mSavedTabPosition == -1) {
        break label88;
      }
      setSelectedNavigationItem(mSavedTabPosition);
      mSavedTabPosition = -1;
      break label88;
      bool1 = false;
      break label108;
    }
  }
  
  public void setScrollTabAllowCollapse(boolean paramBoolean)
  {
    aqy localaqy = (aqy)mContainerView.getTabContainer();
    if (localaqy != null) {
      localaqy.setAllowCollapse(paramBoolean);
    }
  }
  
  public void setScrollTabCollapseButtonClickListener(View.OnClickListener paramOnClickListener)
  {
    aqy localaqy = (aqy)mContainerView.getTabContainer();
    if (localaqy != null) {
      localaqy.setCollapseButtonClickListener(paramOnClickListener);
    }
  }
  
  public void setSelectedNavigationItem(int paramInt)
  {
    switch (mDecorToolbar.getNavigationMode())
    {
    default: 
      throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
    case 2: 
      selectTab((ActionBar.Tab)mTabs.get(paramInt));
      return;
    }
    mDecorToolbar.setDropdownSelectedPosition(paramInt);
  }
  
  public void setShowHideAnimationEnabled(boolean paramBoolean)
  {
    mShowHideAnimationEnabled = paramBoolean;
    if ((!paramBoolean) && (mCurrentShowAnim != null)) {
      mCurrentShowAnim.cancel();
    }
  }
  
  public void setSplitBackgroundDrawable(Drawable paramDrawable)
  {
    if (mSplitView != null) {
      mSplitView.setSplitBackground(paramDrawable);
    }
  }
  
  public void setStackedBackgroundDrawable(Drawable paramDrawable)
  {
    mContainerView.setStackedBackground(paramDrawable);
  }
  
  public void setSubtitle(int paramInt)
  {
    setSubtitle(mContext.getString(paramInt));
  }
  
  public void setSubtitle(CharSequence paramCharSequence)
  {
    mDecorToolbar.setSubtitle(paramCharSequence);
  }
  
  public void setTabIndicatorDrawable(Drawable paramDrawable)
  {
    if (mTabScrollView != null) {
      mTabScrollView.setIndicatorDrawable(paramDrawable);
    }
  }
  
  public void setTabScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    if (mTabScrollView != null) {
      mTabScrollView.setScrollPosition(paramInt1, paramFloat, true);
    }
  }
  
  public void setTitle(int paramInt)
  {
    setTitle(mContext.getString(paramInt));
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    mDecorToolbar.setTitle(paramCharSequence);
  }
  
  public void setUiOptions(int paramInt)
  {
    mOverlayLayout.setUiOptions(paramInt);
  }
  
  public void setWindowTitle(CharSequence paramCharSequence)
  {
    mDecorToolbar.setWindowTitle(paramCharSequence);
  }
  
  public void show()
  {
    if (mHiddenByApp)
    {
      mHiddenByApp = false;
      updateVisibility(false);
    }
  }
  
  public void show(int paramInt)
  {
    mDuration = paramInt;
    show();
  }
  
  public void showForSystem()
  {
    if (mHiddenBySystem)
    {
      mHiddenBySystem = false;
      updateVisibility(true);
    }
  }
  
  public ActionMode startActionMode(ActionMode.Callback paramCallback)
  {
    if (mActionMode != null) {
      mActionMode.finish();
    }
    mOverlayLayout.setHideOnContentScrollEnabled(false);
    mContextView.killMode();
    paramCallback = new ActionModeImpl(mContextView.getContext(), paramCallback);
    if (paramCallback.dispatchOnCreate())
    {
      paramCallback.invalidate();
      mContextView.initForMode(paramCallback);
      animateToMode(true);
      if ((mSplitView != null) && (mContextDisplayMode == 1) && (mSplitView.getVisibility() != 0))
      {
        mSplitView.setVisibility(0);
        if (mOverlayLayout != null) {
          ViewCompat.requestApplyInsets(mOverlayLayout);
        }
      }
      mContextView.sendAccessibilityEvent(32);
      mActionMode = paramCallback;
      return paramCallback;
    }
    return null;
  }
  
  public ActionMode startMultiChoiceActionMode(ActionMode.Callback paramCallback)
  {
    if (mActionMode != null) {
      mActionMode.finish();
    }
    mOverlayLayout.setHideOnContentScrollEnabled(false);
    mContextView.killMode();
    paramCallback = new ActionModeImpl(mContextView.getContext(), paramCallback);
    if (paramCallback.dispatchOnCreate())
    {
      paramCallback.invalidate();
      mContextView.setSplitView(mSplitView);
      mContextView.initForMultiChoiceMode(paramCallback);
      animateToMode(true, paramCallback.isShowActionBar());
      if (paramCallback.isAnimateToShowMenu()) {
        animateToMultiChoiceMode(true);
      }
      if ((mSplitView != null) && (mSplitView.getVisibility() != 0))
      {
        mSplitView.setVisibility(0);
        if (mOverlayLayout != null) {
          ViewCompat.requestApplyInsets(mOverlayLayout);
        }
      }
      mContextView.sendAccessibilityEvent(32);
      paramCallback.setIsMultiChoiceMode(true);
      mActionMode = paramCallback;
      return paramCallback;
    }
    return null;
  }
  
  public class ActionModeImpl
    extends ActionMode
    implements MenuBuilder.Callback
  {
    private final Context mActionModeContext;
    private ActionMode.BackPressedListener mBackPressedListener = new WindowDecorActionBar.ActionModeImpl.1(this);
    private ActionMode.Callback mCallback;
    private WeakReference<View> mCustomView;
    private boolean mIsMultiChoiceMode;
    private boolean mIsShowActionBar = true;
    private final MenuBuilder mMenu;
    
    public ActionModeImpl(Context paramContext, ActionMode.Callback paramCallback)
    {
      mActionModeContext = paramContext;
      mCallback = paramCallback;
      mMenu = new MenuBuilder(paramContext).setDefaultShowAsAction(1);
      mMenu.setCallback(this);
      setBackPressListener(mBackPressedListener);
    }
    
    public boolean dispatchOnCreate()
    {
      mMenu.stopDispatchingItemsChanged();
      try
      {
        boolean bool = mCallback.onCreateActionMode(this, mMenu);
        return bool;
      }
      finally
      {
        mMenu.startDispatchingItemsChanged();
      }
    }
    
    public void finish()
    {
      if (mActionMode != this) {
        return;
      }
      if ((!WindowDecorActionBar.checkShowingFlags(mHiddenByApp, mHiddenBySystem, false)) && (isShowActionBar()))
      {
        mDeferredDestroyActionMode = this;
        mDeferredModeDestroyCallback = mCallback;
      }
      for (;;)
      {
        mCallback = null;
        animateToMode(false);
        if ((mIsMultiChoiceMode) && (isAnimateToShowMenu())) {
          WindowDecorActionBar.this.animateToMultiChoiceMode(false);
        }
        mContextView.closeMode();
        mDecorToolbar.getViewGroup().sendAccessibilityEvent(32);
        mOverlayLayout.setHideOnContentScrollEnabled(mHideOnContentScroll);
        mActionMode = null;
        return;
        mCallback.onDestroyActionMode(this);
      }
    }
    
    public View getCustomView()
    {
      if (mCustomView != null) {
        return (View)mCustomView.get();
      }
      return null;
    }
    
    public Menu getMenu()
    {
      return mMenu;
    }
    
    public MenuInflater getMenuInflater()
    {
      return new SupportMenuInflater(mActionModeContext);
    }
    
    public CharSequence getSubtitle()
    {
      return mContextView.getSubtitle();
    }
    
    public CharSequence getTitle()
    {
      return mContextView.getTitle();
    }
    
    public void invalidate()
    {
      if (mActionMode != this) {
        return;
      }
      mMenu.stopDispatchingItemsChanged();
      try
      {
        mCallback.onPrepareActionMode(this, mMenu);
        return;
      }
      finally
      {
        mMenu.startDispatchingItemsChanged();
      }
    }
    
    public boolean isShowActionBar()
    {
      return mIsShowActionBar;
    }
    
    public boolean isTitleOptional()
    {
      return mContextView.isTitleOptional();
    }
    
    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean) {}
    
    public void onCloseSubMenu(SubMenuBuilder paramSubMenuBuilder) {}
    
    public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
    {
      if (mCallback != null) {
        return mCallback.onActionItemClicked(this, paramMenuItem);
      }
      return false;
    }
    
    public void onMenuModeChange(MenuBuilder paramMenuBuilder)
    {
      if (mCallback == null) {
        return;
      }
      invalidate();
      mContextView.showOverflowMenu();
    }
    
    public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder)
    {
      boolean bool = true;
      if (mCallback == null) {
        bool = false;
      }
      while (!paramSubMenuBuilder.hasVisibleItems()) {
        return bool;
      }
      new MenuPopupHelper(getThemedContext(), paramSubMenuBuilder).show();
      return true;
    }
    
    public void setCustomView(View paramView)
    {
      mContextView.setCustomView(paramView);
      mCustomView = new WeakReference(paramView);
    }
    
    public void setIsMultiChoiceMode(boolean paramBoolean)
    {
      mIsMultiChoiceMode = paramBoolean;
    }
    
    public void setShowActionBar(boolean paramBoolean)
    {
      mIsShowActionBar = paramBoolean;
    }
    
    public void setSubtitle(int paramInt)
    {
      setSubtitle(mContext.getResources().getString(paramInt));
    }
    
    public void setSubtitle(CharSequence paramCharSequence)
    {
      mContextView.setSubtitle(paramCharSequence);
    }
    
    public void setTitle(int paramInt)
    {
      setTitle(mContext.getResources().getString(paramInt));
    }
    
    public void setTitle(CharSequence paramCharSequence)
    {
      mContextView.setTitle(paramCharSequence);
    }
    
    public void setTitleOptionalHint(boolean paramBoolean)
    {
      super.setTitleOptionalHint(paramBoolean);
      mContextView.setTitleOptional(paramBoolean);
    }
  }
  
  public class TabImpl
    extends ActionBar.Tab
  {
    private ActionBar.TabListener mCallback;
    private ActionBar.TabListenerSDK mCallbackSDK;
    private CharSequence mContentDesc;
    private View mCustomView;
    private Drawable mIcon;
    private boolean mIsEnabled = true;
    private int mPosition = -1;
    private Object mTag;
    private CharSequence mText;
    
    public TabImpl() {}
    
    public ActionBar.TabListener getCallback()
    {
      return mCallback;
    }
    
    public ActionBar.TabListenerSDK getCallbackSDK()
    {
      return mCallbackSDK;
    }
    
    public CharSequence getContentDescription()
    {
      return mContentDesc;
    }
    
    public View getCustomView()
    {
      return mCustomView;
    }
    
    public Drawable getIcon()
    {
      return mIcon;
    }
    
    public int getPosition()
    {
      return mPosition;
    }
    
    public Object getTag()
    {
      return mTag;
    }
    
    public CharSequence getText()
    {
      return mText;
    }
    
    public boolean isEnabled()
    {
      return mIsEnabled;
    }
    
    public void select()
    {
      selectTab(this);
    }
    
    public ActionBar.Tab setContentDescription(int paramInt)
    {
      return setContentDescription(mContext.getResources().getText(paramInt));
    }
    
    public ActionBar.Tab setContentDescription(CharSequence paramCharSequence)
    {
      mContentDesc = paramCharSequence;
      if (mPosition >= 0) {
        mTabScrollView.updateTab(mPosition);
      }
      return this;
    }
    
    public ActionBar.Tab setCustomView(int paramInt)
    {
      return setCustomView(LayoutInflater.from(getThemedContext()).inflate(paramInt, null));
    }
    
    public ActionBar.Tab setCustomView(View paramView)
    {
      mCustomView = paramView;
      if (mPosition >= 0) {
        mTabScrollView.updateTab(mPosition);
      }
      return this;
    }
    
    public ActionBar.Tab setEnabled(boolean paramBoolean)
    {
      mIsEnabled = paramBoolean;
      if (mPosition >= 0) {
        mTabScrollView.updateTab(mPosition);
      }
      return this;
    }
    
    public ActionBar.Tab setIcon(int paramInt)
    {
      return setIcon(getTintManager().getDrawable(paramInt));
    }
    
    public ActionBar.Tab setIcon(Drawable paramDrawable)
    {
      mIcon = paramDrawable;
      if (mPosition >= 0) {
        mTabScrollView.updateTab(mPosition);
      }
      return this;
    }
    
    public void setPosition(int paramInt)
    {
      mPosition = paramInt;
    }
    
    public ActionBar.Tab setTabListener(ActionBar.TabListener paramTabListener)
    {
      mCallback = paramTabListener;
      return this;
    }
    
    public ActionBar.Tab setTabListenerSDK(ActionBar.TabListenerSDK paramTabListenerSDK)
    {
      mCallbackSDK = paramTabListenerSDK;
      return this;
    }
    
    public ActionBar.Tab setTag(Object paramObject)
    {
      mTag = paramObject;
      return this;
    }
    
    public ActionBar.Tab setText(int paramInt)
    {
      return setText(mContext.getResources().getText(paramInt));
    }
    
    public ActionBar.Tab setText(CharSequence paramCharSequence)
    {
      mText = paramCharSequence;
      if (mPosition >= 0) {
        mTabScrollView.updateTab(mPosition);
      }
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.app.WindowDecorActionBar
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
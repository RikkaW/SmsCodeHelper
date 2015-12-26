package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Fragment
  implements ComponentCallbacks, View.OnCreateContextMenuListener
{
  static final int ACTIVITY_CREATED = 2;
  static final int CREATED = 1;
  static final int INITIALIZING = 0;
  static final int RESUMED = 5;
  static final int STARTED = 4;
  static final int STOPPED = 3;
  static final Object USE_DEFAULT_TRANSITION = new Object();
  private static final SimpleArrayMap<String, Class<?>> sClassMap = new SimpleArrayMap();
  FragmentActivity mActivity;
  boolean mAdded;
  Boolean mAllowEnterTransitionOverlap;
  Boolean mAllowReturnTransitionOverlap;
  View mAnimatingAway;
  Bundle mArguments;
  int mBackStackNesting;
  boolean mCalled;
  boolean mCheckedForLoaderManager;
  FragmentManagerImpl mChildFragmentManager;
  ViewGroup mContainer;
  int mContainerId;
  boolean mDeferStart;
  boolean mDetached;
  Object mEnterTransition = null;
  SharedElementCallback mEnterTransitionCallback = null;
  Object mExitTransition = null;
  SharedElementCallback mExitTransitionCallback = null;
  int mFragmentId;
  FragmentManagerImpl mFragmentManager;
  boolean mFromLayout;
  boolean mHasMenu;
  boolean mHidden;
  boolean mInLayout;
  int mIndex = -1;
  View mInnerView;
  LoaderManagerImpl mLoaderManager;
  boolean mLoadersStarted;
  boolean mMenuVisible = true;
  int mNextAnim;
  Fragment mParentFragment;
  Object mReenterTransition = USE_DEFAULT_TRANSITION;
  boolean mRemoving;
  boolean mRestored;
  boolean mResumed;
  boolean mRetainInstance;
  boolean mRetaining;
  Object mReturnTransition = USE_DEFAULT_TRANSITION;
  Bundle mSavedFragmentState;
  SparseArray<Parcelable> mSavedViewState;
  Object mSharedElementEnterTransition = null;
  Object mSharedElementReturnTransition = USE_DEFAULT_TRANSITION;
  int mState = 0;
  int mStateAfterAnimating;
  String mTag;
  Fragment mTarget;
  int mTargetIndex = -1;
  int mTargetRequestCode;
  boolean mUserVisibleHint = true;
  View mView;
  String mWho;
  
  public static Fragment instantiate(Context paramContext, String paramString)
  {
    return instantiate(paramContext, paramString, null);
  }
  
  public static Fragment instantiate(Context paramContext, String paramString, @Nullable Bundle paramBundle)
  {
    try
    {
      Class localClass2 = (Class)sClassMap.get(paramString);
      Class localClass1 = localClass2;
      if (localClass2 == null)
      {
        localClass1 = paramContext.getClassLoader().loadClass(paramString);
        sClassMap.put(paramString, localClass1);
      }
      paramContext = (Fragment)localClass1.newInstance();
      if (paramBundle != null)
      {
        paramBundle.setClassLoader(paramContext.getClass().getClassLoader());
        mArguments = paramBundle;
      }
      return paramContext;
    }
    catch (ClassNotFoundException paramContext)
    {
      throw new InstantiationException("Unable to instantiate fragment " + paramString + ": make sure class name exists, is public, and has an" + " empty constructor that is public", paramContext);
    }
    catch (InstantiationException paramContext)
    {
      throw new InstantiationException("Unable to instantiate fragment " + paramString + ": make sure class name exists, is public, and has an" + " empty constructor that is public", paramContext);
    }
    catch (IllegalAccessException paramContext)
    {
      throw new InstantiationException("Unable to instantiate fragment " + paramString + ": make sure class name exists, is public, and has an" + " empty constructor that is public", paramContext);
    }
  }
  
  static boolean isSupportFragmentClass(Context paramContext, String paramString)
  {
    try
    {
      Class localClass2 = (Class)sClassMap.get(paramString);
      Class localClass1 = localClass2;
      if (localClass2 == null)
      {
        localClass1 = paramContext.getClassLoader().loadClass(paramString);
        sClassMap.put(paramString, localClass1);
      }
      boolean bool = Fragment.class.isAssignableFrom(localClass1);
      return bool;
    }
    catch (ClassNotFoundException paramContext) {}
    return false;
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mFragmentId=#");
    paramPrintWriter.print(Integer.toHexString(mFragmentId));
    paramPrintWriter.print(" mContainerId=#");
    paramPrintWriter.print(Integer.toHexString(mContainerId));
    paramPrintWriter.print(" mTag=");
    paramPrintWriter.println(mTag);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mState=");
    paramPrintWriter.print(mState);
    paramPrintWriter.print(" mIndex=");
    paramPrintWriter.print(mIndex);
    paramPrintWriter.print(" mWho=");
    paramPrintWriter.print(mWho);
    paramPrintWriter.print(" mBackStackNesting=");
    paramPrintWriter.println(mBackStackNesting);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mAdded=");
    paramPrintWriter.print(mAdded);
    paramPrintWriter.print(" mRemoving=");
    paramPrintWriter.print(mRemoving);
    paramPrintWriter.print(" mResumed=");
    paramPrintWriter.print(mResumed);
    paramPrintWriter.print(" mFromLayout=");
    paramPrintWriter.print(mFromLayout);
    paramPrintWriter.print(" mInLayout=");
    paramPrintWriter.println(mInLayout);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mHidden=");
    paramPrintWriter.print(mHidden);
    paramPrintWriter.print(" mDetached=");
    paramPrintWriter.print(mDetached);
    paramPrintWriter.print(" mMenuVisible=");
    paramPrintWriter.print(mMenuVisible);
    paramPrintWriter.print(" mHasMenu=");
    paramPrintWriter.println(mHasMenu);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mRetainInstance=");
    paramPrintWriter.print(mRetainInstance);
    paramPrintWriter.print(" mRetaining=");
    paramPrintWriter.print(mRetaining);
    paramPrintWriter.print(" mUserVisibleHint=");
    paramPrintWriter.println(mUserVisibleHint);
    if (mFragmentManager != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mFragmentManager=");
      paramPrintWriter.println(mFragmentManager);
    }
    if (mActivity != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mActivity=");
      paramPrintWriter.println(mActivity);
    }
    if (mParentFragment != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mParentFragment=");
      paramPrintWriter.println(mParentFragment);
    }
    if (mArguments != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mArguments=");
      paramPrintWriter.println(mArguments);
    }
    if (mSavedFragmentState != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mSavedFragmentState=");
      paramPrintWriter.println(mSavedFragmentState);
    }
    if (mSavedViewState != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mSavedViewState=");
      paramPrintWriter.println(mSavedViewState);
    }
    if (mTarget != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mTarget=");
      paramPrintWriter.print(mTarget);
      paramPrintWriter.print(" mTargetRequestCode=");
      paramPrintWriter.println(mTargetRequestCode);
    }
    if (mNextAnim != 0)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mNextAnim=");
      paramPrintWriter.println(mNextAnim);
    }
    if (mContainer != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mContainer=");
      paramPrintWriter.println(mContainer);
    }
    if (mView != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mView=");
      paramPrintWriter.println(mView);
    }
    if (mInnerView != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mInnerView=");
      paramPrintWriter.println(mView);
    }
    if (mAnimatingAway != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mAnimatingAway=");
      paramPrintWriter.println(mAnimatingAway);
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mStateAfterAnimating=");
      paramPrintWriter.println(mStateAfterAnimating);
    }
    if (mLoaderManager != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Loader Manager:");
      mLoaderManager.dump(paramString + "  ", paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
    if (mChildFragmentManager != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Child " + mChildFragmentManager + ":");
      mChildFragmentManager.dump(paramString + "  ", paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    return super.equals(paramObject);
  }
  
  Fragment findFragmentByWho(String paramString)
  {
    if (paramString.equals(mWho)) {
      return this;
    }
    if (mChildFragmentManager != null) {
      return mChildFragmentManager.findFragmentByWho(paramString);
    }
    return null;
  }
  
  public final FragmentActivity getActivity()
  {
    return mActivity;
  }
  
  public boolean getAllowEnterTransitionOverlap()
  {
    if (mAllowEnterTransitionOverlap == null) {
      return true;
    }
    return mAllowEnterTransitionOverlap.booleanValue();
  }
  
  public boolean getAllowReturnTransitionOverlap()
  {
    if (mAllowReturnTransitionOverlap == null) {
      return true;
    }
    return mAllowReturnTransitionOverlap.booleanValue();
  }
  
  public final Bundle getArguments()
  {
    return mArguments;
  }
  
  public final FragmentManager getChildFragmentManager()
  {
    if (mChildFragmentManager == null)
    {
      instantiateChildFragmentManager();
      if (mState < 5) {
        break label31;
      }
      mChildFragmentManager.dispatchResume();
    }
    for (;;)
    {
      return mChildFragmentManager;
      label31:
      if (mState >= 4) {
        mChildFragmentManager.dispatchStart();
      } else if (mState >= 2) {
        mChildFragmentManager.dispatchActivityCreated();
      } else if (mState >= 1) {
        mChildFragmentManager.dispatchCreate();
      }
    }
  }
  
  public Object getEnterTransition()
  {
    return mEnterTransition;
  }
  
  public Object getExitTransition()
  {
    return mExitTransition;
  }
  
  public final FragmentManager getFragmentManager()
  {
    return mFragmentManager;
  }
  
  public final int getId()
  {
    return mFragmentId;
  }
  
  public LayoutInflater getLayoutInflater(Bundle paramBundle)
  {
    paramBundle = mActivity.getLayoutInflater().cloneInContext(mActivity);
    getChildFragmentManager();
    LayoutInflaterCompat.setFactory(paramBundle, mChildFragmentManager.getLayoutInflaterFactory());
    return paramBundle;
  }
  
  public LoaderManager getLoaderManager()
  {
    if (mLoaderManager != null) {
      return mLoaderManager;
    }
    if (mActivity == null) {
      throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }
    mCheckedForLoaderManager = true;
    mLoaderManager = mActivity.getLoaderManager(mWho, mLoadersStarted, true);
    return mLoaderManager;
  }
  
  public final Fragment getParentFragment()
  {
    return mParentFragment;
  }
  
  public Object getReenterTransition()
  {
    if (mReenterTransition == USE_DEFAULT_TRANSITION) {
      return getExitTransition();
    }
    return mReenterTransition;
  }
  
  public final Resources getResources()
  {
    if (mActivity == null) {
      throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }
    return mActivity.getResources();
  }
  
  public final boolean getRetainInstance()
  {
    return mRetainInstance;
  }
  
  public Object getReturnTransition()
  {
    if (mReturnTransition == USE_DEFAULT_TRANSITION) {
      return getEnterTransition();
    }
    return mReturnTransition;
  }
  
  public Object getSharedElementEnterTransition()
  {
    return mSharedElementEnterTransition;
  }
  
  public Object getSharedElementReturnTransition()
  {
    if (mSharedElementReturnTransition == USE_DEFAULT_TRANSITION) {
      return getSharedElementEnterTransition();
    }
    return mSharedElementReturnTransition;
  }
  
  public final String getString(int paramInt)
  {
    return getResources().getString(paramInt);
  }
  
  public final String getString(int paramInt, Object... paramVarArgs)
  {
    return getResources().getString(paramInt, paramVarArgs);
  }
  
  public final String getTag()
  {
    return mTag;
  }
  
  public final Fragment getTargetFragment()
  {
    return mTarget;
  }
  
  public final int getTargetRequestCode()
  {
    return mTargetRequestCode;
  }
  
  public final CharSequence getText(int paramInt)
  {
    return getResources().getText(paramInt);
  }
  
  public boolean getUserVisibleHint()
  {
    return mUserVisibleHint;
  }
  
  @Nullable
  public View getView()
  {
    return mView;
  }
  
  public final boolean hasOptionsMenu()
  {
    return mHasMenu;
  }
  
  public final int hashCode()
  {
    return super.hashCode();
  }
  
  void initState()
  {
    mIndex = -1;
    mWho = null;
    mAdded = false;
    mRemoving = false;
    mResumed = false;
    mFromLayout = false;
    mInLayout = false;
    mRestored = false;
    mBackStackNesting = 0;
    mFragmentManager = null;
    mChildFragmentManager = null;
    mActivity = null;
    mFragmentId = 0;
    mContainerId = 0;
    mTag = null;
    mHidden = false;
    mDetached = false;
    mRetaining = false;
    mLoaderManager = null;
    mLoadersStarted = false;
    mCheckedForLoaderManager = false;
  }
  
  void instantiateChildFragmentManager()
  {
    mChildFragmentManager = new FragmentManagerImpl();
    mChildFragmentManager.attachActivity(mActivity, new Fragment.1(this), this);
  }
  
  public final boolean isAdded()
  {
    return (mActivity != null) && (mAdded);
  }
  
  public final boolean isDetached()
  {
    return mDetached;
  }
  
  public final boolean isHidden()
  {
    return mHidden;
  }
  
  final boolean isInBackStack()
  {
    return mBackStackNesting > 0;
  }
  
  public final boolean isInLayout()
  {
    return mInLayout;
  }
  
  public final boolean isMenuVisible()
  {
    return mMenuVisible;
  }
  
  public final boolean isRemoving()
  {
    return mRemoving;
  }
  
  public final boolean isResumed()
  {
    return mResumed;
  }
  
  public final boolean isVisible()
  {
    return (isAdded()) && (!isHidden()) && (mView != null) && (mView.getWindowToken() != null) && (mView.getVisibility() == 0);
  }
  
  public void onActivityCreated(@Nullable Bundle paramBundle)
  {
    mCalled = true;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  public void onAttach(Activity paramActivity)
  {
    mCalled = true;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    mCalled = true;
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    return false;
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    mCalled = true;
  }
  
  public Animation onCreateAnimation(int paramInt1, boolean paramBoolean, int paramInt2)
  {
    return null;
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    getActivity().onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
  }
  
  public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {}
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    return null;
  }
  
  public void onDestroy()
  {
    mCalled = true;
    if (!mCheckedForLoaderManager)
    {
      mCheckedForLoaderManager = true;
      mLoaderManager = mActivity.getLoaderManager(mWho, mLoadersStarted, false);
    }
    if (mLoaderManager != null) {
      mLoaderManager.doDestroy();
    }
  }
  
  public void onDestroyOptionsMenu() {}
  
  public void onDestroyView()
  {
    mCalled = true;
  }
  
  public void onDetach()
  {
    mCalled = true;
  }
  
  public void onHiddenChanged(boolean paramBoolean) {}
  
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle)
  {
    mCalled = true;
  }
  
  public void onLowMemory()
  {
    mCalled = true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return false;
  }
  
  public void onOptionsMenuClosed(Menu paramMenu) {}
  
  public void onPause()
  {
    mCalled = true;
  }
  
  public void onPrepareOptionsMenu(Menu paramMenu) {}
  
  public void onResume()
  {
    mCalled = true;
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {}
  
  public void onStart()
  {
    mCalled = true;
    if (!mLoadersStarted)
    {
      mLoadersStarted = true;
      if (!mCheckedForLoaderManager)
      {
        mCheckedForLoaderManager = true;
        mLoaderManager = mActivity.getLoaderManager(mWho, mLoadersStarted, false);
      }
      if (mLoaderManager != null) {
        mLoaderManager.doStart();
      }
    }
  }
  
  public void onStop()
  {
    mCalled = true;
  }
  
  public void onViewCreated(View paramView, @Nullable Bundle paramBundle) {}
  
  public void onViewStateRestored(@Nullable Bundle paramBundle)
  {
    mCalled = true;
  }
  
  void performActivityCreated(Bundle paramBundle)
  {
    if (mChildFragmentManager != null) {
      mChildFragmentManager.noteStateNotSaved();
    }
    mCalled = false;
    onActivityCreated(paramBundle);
    if (!mCalled) {
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onActivityCreated()");
    }
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchActivityCreated();
    }
  }
  
  void performConfigurationChanged(Configuration paramConfiguration)
  {
    onConfigurationChanged(paramConfiguration);
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchConfigurationChanged(paramConfiguration);
    }
  }
  
  boolean performContextItemSelected(MenuItem paramMenuItem)
  {
    if (!mHidden)
    {
      if (onContextItemSelected(paramMenuItem)) {}
      while ((mChildFragmentManager != null) && (mChildFragmentManager.dispatchContextItemSelected(paramMenuItem))) {
        return true;
      }
    }
    return false;
  }
  
  void performCreate(Bundle paramBundle)
  {
    if (mChildFragmentManager != null) {
      mChildFragmentManager.noteStateNotSaved();
    }
    mCalled = false;
    onCreate(paramBundle);
    if (!mCalled) {
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onCreate()");
    }
    if (paramBundle != null)
    {
      paramBundle = paramBundle.getParcelable("android:support:fragments");
      if (paramBundle != null)
      {
        if (mChildFragmentManager == null) {
          instantiateChildFragmentManager();
        }
        mChildFragmentManager.restoreAllState(paramBundle, null);
        mChildFragmentManager.dispatchCreate();
      }
    }
  }
  
  boolean performCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    boolean bool2 = false;
    boolean bool3 = false;
    if (!mHidden)
    {
      boolean bool1 = bool3;
      if (mHasMenu)
      {
        bool1 = bool3;
        if (mMenuVisible)
        {
          bool1 = true;
          onCreateOptionsMenu(paramMenu, paramMenuInflater);
        }
      }
      bool2 = bool1;
      if (mChildFragmentManager != null) {
        bool2 = bool1 | mChildFragmentManager.dispatchCreateOptionsMenu(paramMenu, paramMenuInflater);
      }
    }
    return bool2;
  }
  
  View performCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (mChildFragmentManager != null) {
      mChildFragmentManager.noteStateNotSaved();
    }
    return onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
  }
  
  void performDestroy()
  {
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchDestroy();
    }
    mCalled = false;
    onDestroy();
    if (!mCalled) {
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroy()");
    }
  }
  
  void performDestroyView()
  {
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchDestroyView();
    }
    mCalled = false;
    onDestroyView();
    if (!mCalled) {
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroyView()");
    }
    if (mLoaderManager != null) {
      mLoaderManager.doReportNextStart();
    }
  }
  
  void performLowMemory()
  {
    onLowMemory();
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchLowMemory();
    }
  }
  
  boolean performOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (!mHidden)
    {
      if ((mHasMenu) && (mMenuVisible) && (onOptionsItemSelected(paramMenuItem))) {}
      while ((mChildFragmentManager != null) && (mChildFragmentManager.dispatchOptionsItemSelected(paramMenuItem))) {
        return true;
      }
    }
    return false;
  }
  
  void performOptionsMenuClosed(Menu paramMenu)
  {
    if (!mHidden)
    {
      if ((mHasMenu) && (mMenuVisible)) {
        onOptionsMenuClosed(paramMenu);
      }
      if (mChildFragmentManager != null) {
        mChildFragmentManager.dispatchOptionsMenuClosed(paramMenu);
      }
    }
  }
  
  void performPause()
  {
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchPause();
    }
    mCalled = false;
    onPause();
    if (!mCalled) {
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onPause()");
    }
  }
  
  boolean performPrepareOptionsMenu(Menu paramMenu)
  {
    boolean bool2 = false;
    boolean bool3 = false;
    if (!mHidden)
    {
      boolean bool1 = bool3;
      if (mHasMenu)
      {
        bool1 = bool3;
        if (mMenuVisible)
        {
          bool1 = true;
          onPrepareOptionsMenu(paramMenu);
        }
      }
      bool2 = bool1;
      if (mChildFragmentManager != null) {
        bool2 = bool1 | mChildFragmentManager.dispatchPrepareOptionsMenu(paramMenu);
      }
    }
    return bool2;
  }
  
  void performReallyStop()
  {
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchReallyStop();
    }
    if (mLoadersStarted)
    {
      mLoadersStarted = false;
      if (!mCheckedForLoaderManager)
      {
        mCheckedForLoaderManager = true;
        mLoaderManager = mActivity.getLoaderManager(mWho, mLoadersStarted, false);
      }
      if (mLoaderManager != null)
      {
        if (mActivity.mRetaining) {
          break label83;
        }
        mLoaderManager.doStop();
      }
    }
    return;
    label83:
    mLoaderManager.doRetain();
  }
  
  void performResume()
  {
    if (mChildFragmentManager != null)
    {
      mChildFragmentManager.noteStateNotSaved();
      mChildFragmentManager.execPendingActions();
    }
    mCalled = false;
    onResume();
    if (!mCalled) {
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onResume()");
    }
    if (mChildFragmentManager != null)
    {
      mChildFragmentManager.dispatchResume();
      mChildFragmentManager.execPendingActions();
    }
  }
  
  void performSaveInstanceState(Bundle paramBundle)
  {
    onSaveInstanceState(paramBundle);
    if (mChildFragmentManager != null)
    {
      Parcelable localParcelable = mChildFragmentManager.saveAllState();
      if (localParcelable != null) {
        paramBundle.putParcelable("android:support:fragments", localParcelable);
      }
    }
  }
  
  void performStart()
  {
    if (mChildFragmentManager != null)
    {
      mChildFragmentManager.noteStateNotSaved();
      mChildFragmentManager.execPendingActions();
    }
    mCalled = false;
    onStart();
    if (!mCalled) {
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStart()");
    }
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchStart();
    }
    if (mLoaderManager != null) {
      mLoaderManager.doReportStart();
    }
  }
  
  void performStop()
  {
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchStop();
    }
    mCalled = false;
    onStop();
    if (!mCalled) {
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStop()");
    }
  }
  
  public void registerForContextMenu(View paramView)
  {
    paramView.setOnCreateContextMenuListener(this);
  }
  
  final void restoreViewState(Bundle paramBundle)
  {
    if (mSavedViewState != null)
    {
      mInnerView.restoreHierarchyState(mSavedViewState);
      mSavedViewState = null;
    }
    mCalled = false;
    onViewStateRestored(paramBundle);
    if (!mCalled) {
      throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onViewStateRestored()");
    }
  }
  
  public void setAllowEnterTransitionOverlap(boolean paramBoolean)
  {
    mAllowEnterTransitionOverlap = Boolean.valueOf(paramBoolean);
  }
  
  public void setAllowReturnTransitionOverlap(boolean paramBoolean)
  {
    mAllowReturnTransitionOverlap = Boolean.valueOf(paramBoolean);
  }
  
  public void setArguments(Bundle paramBundle)
  {
    if (mIndex >= 0) {
      throw new IllegalStateException("Fragment already active");
    }
    mArguments = paramBundle;
  }
  
  public void setEnterSharedElementCallback(SharedElementCallback paramSharedElementCallback)
  {
    mEnterTransitionCallback = paramSharedElementCallback;
  }
  
  public void setEnterTransition(Object paramObject)
  {
    mEnterTransition = paramObject;
  }
  
  public void setExitSharedElementCallback(SharedElementCallback paramSharedElementCallback)
  {
    mExitTransitionCallback = paramSharedElementCallback;
  }
  
  public void setExitTransition(Object paramObject)
  {
    mExitTransition = paramObject;
  }
  
  public void setHasOptionsMenu(boolean paramBoolean)
  {
    if (mHasMenu != paramBoolean)
    {
      mHasMenu = paramBoolean;
      if ((isAdded()) && (!isHidden())) {
        mActivity.supportInvalidateOptionsMenu();
      }
    }
  }
  
  final void setIndex(int paramInt, Fragment paramFragment)
  {
    mIndex = paramInt;
    if (paramFragment != null)
    {
      mWho = (mWho + ":" + mIndex);
      return;
    }
    mWho = ("android:fragment:" + mIndex);
  }
  
  public void setInitialSavedState(SavedState paramSavedState)
  {
    if (mIndex >= 0) {
      throw new IllegalStateException("Fragment already active");
    }
    if ((paramSavedState != null) && (mState != null)) {}
    for (paramSavedState = mState;; paramSavedState = null)
    {
      mSavedFragmentState = paramSavedState;
      return;
    }
  }
  
  public void setMenuVisibility(boolean paramBoolean)
  {
    if (mMenuVisible != paramBoolean)
    {
      mMenuVisible = paramBoolean;
      if ((mHasMenu) && (isAdded()) && (!isHidden())) {
        mActivity.supportInvalidateOptionsMenu();
      }
    }
  }
  
  public void setReenterTransition(Object paramObject)
  {
    mReenterTransition = paramObject;
  }
  
  public void setRetainInstance(boolean paramBoolean)
  {
    if ((paramBoolean) && (mParentFragment != null)) {
      throw new IllegalStateException("Can't retain fragements that are nested in other fragments");
    }
    mRetainInstance = paramBoolean;
  }
  
  public void setReturnTransition(Object paramObject)
  {
    mReturnTransition = paramObject;
  }
  
  public void setSharedElementEnterTransition(Object paramObject)
  {
    mSharedElementEnterTransition = paramObject;
  }
  
  public void setSharedElementReturnTransition(Object paramObject)
  {
    mSharedElementReturnTransition = paramObject;
  }
  
  public void setTargetFragment(Fragment paramFragment, int paramInt)
  {
    mTarget = paramFragment;
    mTargetRequestCode = paramInt;
  }
  
  public void setUserVisibleHint(boolean paramBoolean)
  {
    if ((!mUserVisibleHint) && (paramBoolean) && (mState < 4)) {
      mFragmentManager.performPendingDeferredStart(this);
    }
    mUserVisibleHint = paramBoolean;
    if (!paramBoolean) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      mDeferStart = paramBoolean;
      return;
    }
  }
  
  public void startActivity(Intent paramIntent)
  {
    if (mActivity == null) {
      throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }
    mActivity.startActivityFromFragment(this, paramIntent, -1);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    if (mActivity == null) {
      throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }
    mActivity.startActivityFromFragment(this, paramIntent, paramInt);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    DebugUtils.buildShortClassTag(this, localStringBuilder);
    if (mIndex >= 0)
    {
      localStringBuilder.append(" #");
      localStringBuilder.append(mIndex);
    }
    if (mFragmentId != 0)
    {
      localStringBuilder.append(" id=0x");
      localStringBuilder.append(Integer.toHexString(mFragmentId));
    }
    if (mTag != null)
    {
      localStringBuilder.append(" ");
      localStringBuilder.append(mTag);
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
  
  public void unregisterForContextMenu(View paramView)
  {
    paramView.setOnCreateContextMenuListener(null);
  }
  
  public static class InstantiationException
    extends RuntimeException
  {
    public InstantiationException(String paramString, Exception paramException)
    {
      super(paramException);
    }
  }
  
  public static class SavedState
    implements Parcelable
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Fragment.SavedState.1();
    final Bundle mState;
    
    SavedState(Bundle paramBundle)
    {
      mState = paramBundle;
    }
    
    SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      mState = paramParcel.readBundle();
      if ((paramClassLoader != null) && (mState != null)) {
        mState.setClassLoader(paramClassLoader);
      }
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeBundle(mState);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.Fragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
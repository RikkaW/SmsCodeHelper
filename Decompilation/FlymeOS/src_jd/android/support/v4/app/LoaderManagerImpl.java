package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCompleteListener;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

class LoaderManagerImpl
  extends LoaderManager
{
  static boolean DEBUG = false;
  static final String TAG = "LoaderManager";
  FragmentActivity mActivity;
  boolean mCreatingLoader;
  final SparseArrayCompat<LoaderInfo> mInactiveLoaders = new SparseArrayCompat();
  final SparseArrayCompat<LoaderInfo> mLoaders = new SparseArrayCompat();
  boolean mRetaining;
  boolean mRetainingStarted;
  boolean mStarted;
  final String mWho;
  
  LoaderManagerImpl(String paramString, FragmentActivity paramFragmentActivity, boolean paramBoolean)
  {
    mWho = paramString;
    mActivity = paramFragmentActivity;
    mStarted = paramBoolean;
  }
  
  private LoaderInfo createAndInstallLoader(int paramInt, Bundle paramBundle, LoaderManager.LoaderCallbacks<Object> paramLoaderCallbacks)
  {
    try
    {
      mCreatingLoader = true;
      paramBundle = createLoader(paramInt, paramBundle, paramLoaderCallbacks);
      installLoader(paramBundle);
      return paramBundle;
    }
    finally
    {
      mCreatingLoader = false;
    }
  }
  
  private LoaderInfo createLoader(int paramInt, Bundle paramBundle, LoaderManager.LoaderCallbacks<Object> paramLoaderCallbacks)
  {
    LoaderInfo localLoaderInfo = new LoaderInfo(paramInt, paramBundle, paramLoaderCallbacks);
    mLoader = paramLoaderCallbacks.onCreateLoader(paramInt, paramBundle);
    return localLoaderInfo;
  }
  
  public void destroyLoader(int paramInt)
  {
    if (mCreatingLoader) {
      throw new IllegalStateException("Called while creating a loader");
    }
    if (DEBUG) {
      Log.v("LoaderManager", "destroyLoader in " + this + " of " + paramInt);
    }
    int i = mLoaders.indexOfKey(paramInt);
    LoaderInfo localLoaderInfo;
    if (i >= 0)
    {
      localLoaderInfo = (LoaderInfo)mLoaders.valueAt(i);
      mLoaders.removeAt(i);
      localLoaderInfo.destroy();
    }
    paramInt = mInactiveLoaders.indexOfKey(paramInt);
    if (paramInt >= 0)
    {
      localLoaderInfo = (LoaderInfo)mInactiveLoaders.valueAt(paramInt);
      mInactiveLoaders.removeAt(paramInt);
      localLoaderInfo.destroy();
    }
    if ((mActivity != null) && (!hasRunningLoaders())) {
      mActivity.mFragments.startPendingDeferredFragments();
    }
  }
  
  void doDestroy()
  {
    if (!mRetaining)
    {
      if (DEBUG) {
        Log.v("LoaderManager", "Destroying Active in " + this);
      }
      i = mLoaders.size() - 1;
      while (i >= 0)
      {
        ((LoaderInfo)mLoaders.valueAt(i)).destroy();
        i -= 1;
      }
      mLoaders.clear();
    }
    if (DEBUG) {
      Log.v("LoaderManager", "Destroying Inactive in " + this);
    }
    int i = mInactiveLoaders.size() - 1;
    while (i >= 0)
    {
      ((LoaderInfo)mInactiveLoaders.valueAt(i)).destroy();
      i -= 1;
    }
    mInactiveLoaders.clear();
  }
  
  void doReportNextStart()
  {
    int i = mLoaders.size() - 1;
    while (i >= 0)
    {
      mLoaders.valueAt(i)).mReportNextStart = true;
      i -= 1;
    }
  }
  
  void doReportStart()
  {
    int i = mLoaders.size() - 1;
    while (i >= 0)
    {
      ((LoaderInfo)mLoaders.valueAt(i)).reportStart();
      i -= 1;
    }
  }
  
  void doRetain()
  {
    if (DEBUG) {
      Log.v("LoaderManager", "Retaining in " + this);
    }
    if (!mStarted)
    {
      RuntimeException localRuntimeException = new RuntimeException("here");
      localRuntimeException.fillInStackTrace();
      Log.w("LoaderManager", "Called doRetain when not started: " + this, localRuntimeException);
    }
    for (;;)
    {
      return;
      mRetaining = true;
      mStarted = false;
      int i = mLoaders.size() - 1;
      while (i >= 0)
      {
        ((LoaderInfo)mLoaders.valueAt(i)).retain();
        i -= 1;
      }
    }
  }
  
  void doStart()
  {
    if (DEBUG) {
      Log.v("LoaderManager", "Starting in " + this);
    }
    if (mStarted)
    {
      RuntimeException localRuntimeException = new RuntimeException("here");
      localRuntimeException.fillInStackTrace();
      Log.w("LoaderManager", "Called doStart when already started: " + this, localRuntimeException);
    }
    for (;;)
    {
      return;
      mStarted = true;
      int i = mLoaders.size() - 1;
      while (i >= 0)
      {
        ((LoaderInfo)mLoaders.valueAt(i)).start();
        i -= 1;
      }
    }
  }
  
  void doStop()
  {
    if (DEBUG) {
      Log.v("LoaderManager", "Stopping in " + this);
    }
    if (!mStarted)
    {
      RuntimeException localRuntimeException = new RuntimeException("here");
      localRuntimeException.fillInStackTrace();
      Log.w("LoaderManager", "Called doStop when not started: " + this, localRuntimeException);
      return;
    }
    int i = mLoaders.size() - 1;
    while (i >= 0)
    {
      ((LoaderInfo)mLoaders.valueAt(i)).stop();
      i -= 1;
    }
    mStarted = false;
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    int j = 0;
    String str;
    int i;
    LoaderInfo localLoaderInfo;
    if (mLoaders.size() > 0)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Active Loaders:");
      str = paramString + "    ";
      i = 0;
      while (i < mLoaders.size())
      {
        localLoaderInfo = (LoaderInfo)mLoaders.valueAt(i);
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  #");
        paramPrintWriter.print(mLoaders.keyAt(i));
        paramPrintWriter.print(": ");
        paramPrintWriter.println(localLoaderInfo.toString());
        localLoaderInfo.dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
        i += 1;
      }
    }
    if (mInactiveLoaders.size() > 0)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Inactive Loaders:");
      str = paramString + "    ";
      i = j;
      while (i < mInactiveLoaders.size())
      {
        localLoaderInfo = (LoaderInfo)mInactiveLoaders.valueAt(i);
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  #");
        paramPrintWriter.print(mInactiveLoaders.keyAt(i));
        paramPrintWriter.print(": ");
        paramPrintWriter.println(localLoaderInfo.toString());
        localLoaderInfo.dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
        i += 1;
      }
    }
  }
  
  void finishRetain()
  {
    if (mRetaining)
    {
      if (DEBUG) {
        Log.v("LoaderManager", "Finished Retaining in " + this);
      }
      mRetaining = false;
      int i = mLoaders.size() - 1;
      while (i >= 0)
      {
        ((LoaderInfo)mLoaders.valueAt(i)).finishRetain();
        i -= 1;
      }
    }
  }
  
  public <D> Loader<D> getLoader(int paramInt)
  {
    if (mCreatingLoader) {
      throw new IllegalStateException("Called while creating a loader");
    }
    LoaderInfo localLoaderInfo = (LoaderInfo)mLoaders.get(paramInt);
    if (localLoaderInfo != null)
    {
      if (mPendingLoader != null) {
        return mPendingLoader.mLoader;
      }
      return mLoader;
    }
    return null;
  }
  
  public boolean hasRunningLoaders()
  {
    int j = mLoaders.size();
    int i = 0;
    boolean bool2 = false;
    if (i < j)
    {
      LoaderInfo localLoaderInfo = (LoaderInfo)mLoaders.valueAt(i);
      if ((mStarted) && (!mDeliveredData)) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        bool2 |= bool1;
        i += 1;
        break;
      }
    }
    return bool2;
  }
  
  public <D> Loader<D> initLoader(int paramInt, Bundle paramBundle, LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks)
  {
    if (mCreatingLoader) {
      throw new IllegalStateException("Called while creating a loader");
    }
    LoaderInfo localLoaderInfo = (LoaderInfo)mLoaders.get(paramInt);
    if (DEBUG) {
      Log.v("LoaderManager", "initLoader in " + this + ": args=" + paramBundle);
    }
    if (localLoaderInfo == null)
    {
      paramLoaderCallbacks = createAndInstallLoader(paramInt, paramBundle, paramLoaderCallbacks);
      paramBundle = paramLoaderCallbacks;
      if (DEBUG) {
        Log.v("LoaderManager", "  Created new loader " + paramLoaderCallbacks);
      }
    }
    for (paramBundle = paramLoaderCallbacks;; paramBundle = localLoaderInfo)
    {
      if ((mHaveData) && (mStarted)) {
        paramBundle.callOnLoadFinished(mLoader, mData);
      }
      return mLoader;
      if (DEBUG) {
        Log.v("LoaderManager", "  Re-using existing loader " + localLoaderInfo);
      }
      mCallbacks = paramLoaderCallbacks;
    }
  }
  
  void installLoader(LoaderInfo paramLoaderInfo)
  {
    mLoaders.put(mId, paramLoaderInfo);
    if (mStarted) {
      paramLoaderInfo.start();
    }
  }
  
  public <D> Loader<D> restartLoader(int paramInt, Bundle paramBundle, LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks)
  {
    if (mCreatingLoader) {
      throw new IllegalStateException("Called while creating a loader");
    }
    LoaderInfo localLoaderInfo1 = (LoaderInfo)mLoaders.get(paramInt);
    if (DEBUG) {
      Log.v("LoaderManager", "restartLoader in " + this + ": args=" + paramBundle);
    }
    if (localLoaderInfo1 != null)
    {
      LoaderInfo localLoaderInfo2 = (LoaderInfo)mInactiveLoaders.get(paramInt);
      if (localLoaderInfo2 == null) {
        break label309;
      }
      if (!mHaveData) {
        break label175;
      }
      if (DEBUG) {
        Log.v("LoaderManager", "  Removing last inactive loader: " + localLoaderInfo1);
      }
      mDeliveredData = false;
      localLoaderInfo2.destroy();
      mLoader.abandon();
      mInactiveLoaders.put(paramInt, localLoaderInfo1);
    }
    for (;;)
    {
      return createAndInstallLoadermLoader;
      label175:
      if (!mStarted)
      {
        if (DEBUG) {
          Log.v("LoaderManager", "  Current loader is stopped; replacing");
        }
        mLoaders.put(paramInt, null);
        localLoaderInfo1.destroy();
      }
      else
      {
        if (mPendingLoader != null)
        {
          if (DEBUG) {
            Log.v("LoaderManager", "  Removing pending loader: " + mPendingLoader);
          }
          mPendingLoader.destroy();
          mPendingLoader = null;
        }
        if (DEBUG) {
          Log.v("LoaderManager", "  Enqueuing as new pending loader");
        }
        mPendingLoader = createLoader(paramInt, paramBundle, paramLoaderCallbacks);
        return mPendingLoader.mLoader;
        label309:
        if (DEBUG) {
          Log.v("LoaderManager", "  Making last loader inactive: " + localLoaderInfo1);
        }
        mLoader.abandon();
        mInactiveLoaders.put(paramInt, localLoaderInfo1);
      }
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    localStringBuilder.append("LoaderManager{");
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    localStringBuilder.append(" in ");
    DebugUtils.buildShortClassTag(mActivity, localStringBuilder);
    localStringBuilder.append("}}");
    return localStringBuilder.toString();
  }
  
  void updateActivity(FragmentActivity paramFragmentActivity)
  {
    mActivity = paramFragmentActivity;
  }
  
  final class LoaderInfo
    implements Loader.OnLoadCompleteListener<Object>
  {
    final Bundle mArgs;
    LoaderManager.LoaderCallbacks<Object> mCallbacks;
    Object mData;
    boolean mDeliveredData;
    boolean mDestroyed;
    boolean mHaveData;
    final int mId;
    boolean mListenerRegistered;
    Loader<Object> mLoader;
    LoaderInfo mPendingLoader;
    boolean mReportNextStart;
    boolean mRetaining;
    boolean mRetainingStarted;
    boolean mStarted;
    
    public LoaderInfo(Bundle paramBundle, LoaderManager.LoaderCallbacks<Object> paramLoaderCallbacks)
    {
      mId = paramBundle;
      mArgs = paramLoaderCallbacks;
      LoaderManager.LoaderCallbacks localLoaderCallbacks;
      mCallbacks = localLoaderCallbacks;
    }
    
    void callOnLoadFinished(Loader<Object> paramLoader, Object paramObject)
    {
      String str;
      if (mCallbacks != null)
      {
        if (mActivity == null) {
          break label158;
        }
        str = mActivity.mFragments.mNoTransactionsBecause;
        mActivity.mFragments.mNoTransactionsBecause = "onLoadFinished";
      }
      for (;;)
      {
        try
        {
          if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "  onLoadFinished in " + paramLoader + ": " + paramLoader.dataToString(paramObject));
          }
          mCallbacks.onLoadFinished(paramLoader, paramObject);
          if (mActivity != null) {
            mActivity.mFragments.mNoTransactionsBecause = str;
          }
          mDeliveredData = true;
          return;
        }
        finally
        {
          if (mActivity != null) {
            mActivity.mFragments.mNoTransactionsBecause = str;
          }
        }
        label158:
        str = null;
      }
    }
    
    void destroy()
    {
      if (LoaderManagerImpl.DEBUG) {
        Log.v("LoaderManager", "  Destroying: " + this);
      }
      mDestroyed = true;
      boolean bool = mDeliveredData;
      mDeliveredData = false;
      String str;
      if ((mCallbacks != null) && (mLoader != null) && (mHaveData) && (bool))
      {
        if (LoaderManagerImpl.DEBUG) {
          Log.v("LoaderManager", "  Reseting: " + this);
        }
        if (mActivity == null) {
          break label269;
        }
        str = mActivity.mFragments.mNoTransactionsBecause;
        mActivity.mFragments.mNoTransactionsBecause = "onLoaderReset";
      }
      for (;;)
      {
        try
        {
          mCallbacks.onLoaderReset(mLoader);
          if (mActivity != null) {
            mActivity.mFragments.mNoTransactionsBecause = str;
          }
          mCallbacks = null;
          mData = null;
          mHaveData = false;
          if (mLoader != null)
          {
            if (mListenerRegistered)
            {
              mListenerRegistered = false;
              mLoader.unregisterListener(this);
            }
            mLoader.reset();
          }
          if (mPendingLoader != null) {
            mPendingLoader.destroy();
          }
          return;
        }
        finally
        {
          if (mActivity != null) {
            mActivity.mFragments.mNoTransactionsBecause = str;
          }
        }
        label269:
        str = null;
      }
    }
    
    public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mId=");
      paramPrintWriter.print(mId);
      paramPrintWriter.print(" mArgs=");
      paramPrintWriter.println(mArgs);
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mCallbacks=");
      paramPrintWriter.println(mCallbacks);
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mLoader=");
      paramPrintWriter.println(mLoader);
      if (mLoader != null) {
        mLoader.dump(paramString + "  ", paramFileDescriptor, paramPrintWriter, paramArrayOfString);
      }
      if ((mHaveData) || (mDeliveredData))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mHaveData=");
        paramPrintWriter.print(mHaveData);
        paramPrintWriter.print("  mDeliveredData=");
        paramPrintWriter.println(mDeliveredData);
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mData=");
        paramPrintWriter.println(mData);
      }
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mStarted=");
      paramPrintWriter.print(mStarted);
      paramPrintWriter.print(" mReportNextStart=");
      paramPrintWriter.print(mReportNextStart);
      paramPrintWriter.print(" mDestroyed=");
      paramPrintWriter.println(mDestroyed);
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mRetaining=");
      paramPrintWriter.print(mRetaining);
      paramPrintWriter.print(" mRetainingStarted=");
      paramPrintWriter.print(mRetainingStarted);
      paramPrintWriter.print(" mListenerRegistered=");
      paramPrintWriter.println(mListenerRegistered);
      if (mPendingLoader != null)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Pending Loader ");
        paramPrintWriter.print(mPendingLoader);
        paramPrintWriter.println(":");
        mPendingLoader.dump(paramString + "  ", paramFileDescriptor, paramPrintWriter, paramArrayOfString);
      }
    }
    
    void finishRetain()
    {
      if (mRetaining)
      {
        if (LoaderManagerImpl.DEBUG) {
          Log.v("LoaderManager", "  Finished Retaining: " + this);
        }
        mRetaining = false;
        if ((mStarted != mRetainingStarted) && (!mStarted)) {
          stop();
        }
      }
      if ((mStarted) && (mHaveData) && (!mReportNextStart)) {
        callOnLoadFinished(mLoader, mData);
      }
    }
    
    public void onLoadComplete(Loader<Object> paramLoader, Object paramObject)
    {
      if (LoaderManagerImpl.DEBUG) {
        Log.v("LoaderManager", "onLoadComplete: " + this);
      }
      if (mDestroyed) {
        if (LoaderManagerImpl.DEBUG) {
          Log.v("LoaderManager", "  Ignoring load complete -- destroyed");
        }
      }
      do
      {
        do
        {
          return;
          if (mLoaders.get(mId) == this) {
            break;
          }
        } while (!LoaderManagerImpl.DEBUG);
        Log.v("LoaderManager", "  Ignoring load complete -- not active");
        return;
        LoaderInfo localLoaderInfo = mPendingLoader;
        if (localLoaderInfo != null)
        {
          if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "  Switching to pending loader: " + localLoaderInfo);
          }
          mPendingLoader = null;
          mLoaders.put(mId, null);
          destroy();
          installLoader(localLoaderInfo);
          return;
        }
        if ((mData != paramObject) || (!mHaveData))
        {
          mData = paramObject;
          mHaveData = true;
          if (mStarted) {
            callOnLoadFinished(paramLoader, paramObject);
          }
        }
        paramLoader = (LoaderInfo)mInactiveLoaders.get(mId);
        if ((paramLoader != null) && (paramLoader != this))
        {
          mDeliveredData = false;
          paramLoader.destroy();
          mInactiveLoaders.remove(mId);
        }
      } while ((mActivity == null) || (hasRunningLoaders()));
      mActivity.mFragments.startPendingDeferredFragments();
    }
    
    void reportStart()
    {
      if ((mStarted) && (mReportNextStart))
      {
        mReportNextStart = false;
        if (mHaveData) {
          callOnLoadFinished(mLoader, mData);
        }
      }
    }
    
    void retain()
    {
      if (LoaderManagerImpl.DEBUG) {
        Log.v("LoaderManager", "  Retaining: " + this);
      }
      mRetaining = true;
      mRetainingStarted = mStarted;
      mStarted = false;
      mCallbacks = null;
    }
    
    void start()
    {
      if ((mRetaining) && (mRetainingStarted)) {
        mStarted = true;
      }
      do
      {
        do
        {
          return;
        } while (mStarted);
        mStarted = true;
        if (LoaderManagerImpl.DEBUG) {
          Log.v("LoaderManager", "  Starting: " + this);
        }
        if ((mLoader == null) && (mCallbacks != null)) {
          mLoader = mCallbacks.onCreateLoader(mId, mArgs);
        }
      } while (mLoader == null);
      if ((mLoader.getClass().isMemberClass()) && (!Modifier.isStatic(mLoader.getClass().getModifiers()))) {
        throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + mLoader);
      }
      if (!mListenerRegistered)
      {
        mLoader.registerListener(mId, this);
        mListenerRegistered = true;
      }
      mLoader.startLoading();
    }
    
    void stop()
    {
      if (LoaderManagerImpl.DEBUG) {
        Log.v("LoaderManager", "  Stopping: " + this);
      }
      mStarted = false;
      if ((!mRetaining) && (mLoader != null) && (mListenerRegistered))
      {
        mListenerRegistered = false;
        mLoader.unregisterListener(this);
        mLoader.stopLoading();
      }
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(64);
      localStringBuilder.append("LoaderInfo{");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      localStringBuilder.append(" #");
      localStringBuilder.append(mId);
      localStringBuilder.append(" : ");
      DebugUtils.buildShortClassTag(mLoader, localStringBuilder);
      localStringBuilder.append("}}");
      return localStringBuilder.toString();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.LoaderManagerImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
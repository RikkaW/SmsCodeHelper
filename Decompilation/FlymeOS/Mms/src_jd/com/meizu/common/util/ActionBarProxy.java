package com.meizu.common.util;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import com.meizu.common.R.drawable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class ActionBarProxy
  implements InvocationHandler
{
  private static final String TAG = ActionBarProxy.class.getName();
  private boolean mListenerHadAdd = false;
  private OnBackButtonEnableChangeListener mOnBackButtonEnableChangeListener;
  private Class mOnBackButtonEnableChangeListenerClazz;
  private ArrayList<OnBackButtonEnableChangeListener> mOnBackButtonEnableChangeListeners;
  private OnTopBackButtonEnableChangeListener mOnTopBackButtonEnableChangeListener;
  private ActionBar mProxidedActionBar;
  private OnBackButtonEnableChangeListener mTopBackBtnListener;
  private int mTopBackButtonColor;
  private boolean mTopBackButtonEnabled;
  
  public ActionBarProxy(ActionBar paramActionBar)
  {
    mProxidedActionBar = paramActionBar;
    if (paramActionBar == null) {
      throw new IllegalArgumentException("the ActionBar proxied can not be null");
    }
    try
    {
      mOnBackButtonEnableChangeListenerClazz = Class.forName("android.app.ActionBar$OnBackButtonEnableChangeListener");
      return;
    }
    catch (ClassNotFoundException paramActionBar)
    {
      Log.e(TAG, "init ActionBarProxy error:" + paramActionBar.getMessage());
    }
  }
  
  @SuppressLint({"NewApi"})
  private void setHomeAsUpIndicator(int paramInt)
  {
    if (mTopBackButtonColor == 0)
    {
      mProxidedActionBar.setHomeAsUpIndicator(paramInt);
      return;
    }
    Drawable localDrawable = mProxidedActionBar.getThemedContext().getResources().getDrawable(paramInt);
    localDrawable.setColorFilter(new LightingColorFilter(0, mTopBackButtonColor));
    mProxidedActionBar.setHomeAsUpIndicator(localDrawable);
  }
  
  public boolean addOnBackButtonEnableChangeListener(OnBackButtonEnableChangeListener paramOnBackButtonEnableChangeListener)
  {
    if ((mProxidedActionBar == null) || (paramOnBackButtonEnableChangeListener == null)) {}
    do
    {
      return false;
      if (mOnBackButtonEnableChangeListener != null) {
        break;
      }
    } while (!setOnBackButtonEnableChangeListener(paramOnBackButtonEnableChangeListener));
    return true;
    if (mOnBackButtonEnableChangeListeners == null) {
      mOnBackButtonEnableChangeListeners = new ArrayList();
    }
    if (!mOnBackButtonEnableChangeListeners.contains(paramOnBackButtonEnableChangeListener)) {
      mOnBackButtonEnableChangeListeners.add(paramOnBackButtonEnableChangeListener);
    }
    return true;
  }
  
  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
  {
    paramObject = paramMethod.getName();
    try
    {
      if ((((String)paramObject).equals("onBackButtonEnableChange")) && (mOnBackButtonEnableChangeListeners != null) && (mOnBackButtonEnableChangeListeners.size() > 0))
      {
        boolean bool = ((Boolean)paramArrayOfObject[0]).booleanValue();
        paramObject = (ArrayList)mOnBackButtonEnableChangeListeners.clone();
        int j = ((ArrayList)paramObject).size();
        int i = 0;
        while (i < j)
        {
          ((OnBackButtonEnableChangeListener)((ArrayList)paramObject).get(i)).onBackButtonEnableChange(bool);
          i += 1;
        }
      }
      return null;
    }
    catch (Exception paramObject)
    {
      Log.e(TAG, "ActionBarProxy invoke error:" + ((Exception)paramObject).getMessage());
    }
  }
  
  public boolean isBackButtonEnabled()
  {
    if (mProxidedActionBar == null) {
      return true;
    }
    try
    {
      Method localMethod = mProxidedActionBar.getClass().getDeclaredMethod("isBackButtonEnabled", new Class[0]);
      localMethod.setAccessible(true);
      boolean bool = ((Boolean)localMethod.invoke(mProxidedActionBar, new Object[0])).booleanValue();
      return bool;
    }
    catch (Exception localException) {}
    return true;
  }
  
  public void removeOnBackButtonEnableChangeListener(OnBackButtonEnableChangeListener paramOnBackButtonEnableChangeListener)
  {
    if (mOnBackButtonEnableChangeListeners == null) {
      return;
    }
    mOnBackButtonEnableChangeListeners.remove(paramOnBackButtonEnableChangeListener);
  }
  
  public boolean setActionBarViewCollapsable(Activity paramActivity, boolean paramBoolean)
  {
    int i = 0;
    if (paramActivity != null)
    {
      paramActivity = paramActivity.findViewById(paramActivity.getResources().getIdentifier("action_bar", "id", "android"));
      if (paramActivity != null)
      {
        if (paramBoolean) {
          i = 8;
        }
        paramActivity.setVisibility(i);
        return true;
      }
      Log.e(TAG, "setActionBarViewCollapsable fail to be invoked. Caused by actionbarView not found");
      return false;
    }
    Log.e(TAG, "setActionBarViewCollapsable fail to be invoked. Caused by activity which is null");
    return false;
  }
  
  public boolean setBackButtonDrawable(Drawable paramDrawable)
  {
    if ((mProxidedActionBar != null) && (CommonUtils.isFlymeOS())) {
      try
      {
        Method localMethod = mProxidedActionBar.getClass().getDeclaredMethod("setBackButtonDrawable", new Class[] { Drawable.class });
        localMethod.setAccessible(true);
        localMethod.invoke(mProxidedActionBar, new Object[] { paramDrawable });
        return true;
      }
      catch (Exception paramDrawable)
      {
        Log.e(TAG, "setBackButtonDrawable fail to be invoked. Caused by :" + paramDrawable.getMessage());
      }
    }
    return false;
  }
  
  public boolean setEnabledBackWhenOverlay(boolean paramBoolean)
  {
    if ((mProxidedActionBar != null) && (CommonUtils.isFlymeOS())) {
      try
      {
        Method localMethod = mProxidedActionBar.getClass().getDeclaredMethod("setEnabledBackWhenOverlay", new Class[] { Boolean.TYPE });
        localMethod.setAccessible(true);
        localMethod.invoke(mProxidedActionBar, new Object[] { Boolean.valueOf(paramBoolean) });
        return true;
      }
      catch (Exception localException)
      {
        Log.e(TAG, "setEnabledBackWhenOverlay fail to be invoked. Caused by :" + localException.getMessage());
      }
    }
    return false;
  }
  
  public boolean setOnBackButtonEnableChangeListener(OnBackButtonEnableChangeListener paramOnBackButtonEnableChangeListener)
  {
    if (mProxidedActionBar == null) {
      return false;
    }
    if ((mOnBackButtonEnableChangeListener != null) && (mOnBackButtonEnableChangeListeners != null)) {
      mOnBackButtonEnableChangeListeners.remove(mOnBackButtonEnableChangeListener);
    }
    mOnBackButtonEnableChangeListener = paramOnBackButtonEnableChangeListener;
    if (paramOnBackButtonEnableChangeListener != null)
    {
      if (mOnBackButtonEnableChangeListeners == null) {
        mOnBackButtonEnableChangeListeners = new ArrayList();
      }
      if (mOnBackButtonEnableChangeListeners.contains(mOnBackButtonEnableChangeListener)) {
        break label201;
      }
      mOnBackButtonEnableChangeListeners.add(mOnBackButtonEnableChangeListener);
    }
    label201:
    for (int i = 0;; i = 0) {
      try
      {
        Method localMethod = mProxidedActionBar.getClass().getDeclaredMethod("setOnBackButtonEnableChangeListener", new Class[] { mOnBackButtonEnableChangeListenerClazz });
        localMethod.setAccessible(true);
        if (i != 0) {
          paramOnBackButtonEnableChangeListener = null;
        }
        Object localObject;
        do
        {
          localMethod.invoke(mProxidedActionBar, new Object[] { paramOnBackButtonEnableChangeListener });
          return true;
          if (mOnBackButtonEnableChangeListeners.size() != 0) {
            break label201;
          }
          i = 1;
          break;
          localObject = Proxy.newProxyInstance(mOnBackButtonEnableChangeListenerClazz.getClassLoader(), new Class[] { mOnBackButtonEnableChangeListenerClazz }, this);
          paramOnBackButtonEnableChangeListener = (OnBackButtonEnableChangeListener)localObject;
        } while (localObject != null);
        return false;
      }
      catch (Exception paramOnBackButtonEnableChangeListener)
      {
        paramOnBackButtonEnableChangeListener.printStackTrace();
        return false;
      }
    }
  }
  
  public void setOnTopBackButtonEnableChangeListener(OnTopBackButtonEnableChangeListener paramOnTopBackButtonEnableChangeListener)
  {
    mOnTopBackButtonEnableChangeListener = paramOnTopBackButtonEnableChangeListener;
  }
  
  public void setTopBackButtonColor(int paramInt)
  {
    mTopBackButtonColor = paramInt;
    if (mTopBackButtonEnabled) {
      setHomeAsUpIndicator(R.drawable.mz_ic_ab_back_top);
    }
  }
  
  public void setTopBackButtonColorRes(int paramInt)
  {
    if (mProxidedActionBar == null) {
      return;
    }
    setTopBackButtonColor(mProxidedActionBar.getThemedContext().getResources().getColor(paramInt));
  }
  
  @SuppressLint({"NewApi"})
  public void setTopBackButtonEnabled(boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool = isBackButtonEnabled();
    Configuration localConfiguration = mProxidedActionBar.getThemedContext().getResources().getConfiguration();
    if ((paramBoolean2) || (orientation == 2))
    {
      bool = paramBoolean1;
      mProxidedActionBar.setDisplayHomeAsUpEnabled(bool);
      mProxidedActionBar.setHomeButtonEnabled(bool);
      mTopBackButtonEnabled = bool;
      if (!bool) {
        break label215;
      }
      if (Build.VERSION.SDK_INT >= 18) {
        setHomeAsUpIndicator(R.drawable.mz_ic_ab_back_top);
      }
      label75:
      if (mOnTopBackButtonEnableChangeListener != null) {
        mOnTopBackButtonEnableChangeListener.onTopBackButtonEnableChange(bool);
      }
      if (mTopBackBtnListener == null) {
        mTopBackBtnListener = new ActionBarProxy.1(this);
      }
      if (!mListenerHadAdd)
      {
        if (addOnBackButtonEnableChangeListener(mTopBackBtnListener)) {
          break label236;
        }
        mProxidedActionBar.setDisplayHomeAsUpEnabled(false);
        mProxidedActionBar.setHomeButtonEnabled(false);
        if (Build.VERSION.SDK_INT >= 18) {
          mProxidedActionBar.setHomeAsUpIndicator(R.drawable.mz_ic_ab_back_transparent);
        }
      }
    }
    for (;;)
    {
      if ((!paramBoolean1) && (paramBoolean2) && (mListenerHadAdd))
      {
        removeOnBackButtonEnableChangeListener(mTopBackBtnListener);
        mListenerHadAdd = false;
      }
      return;
      if ((bool) && (orientation == 1))
      {
        bool = false;
        break;
      }
      bool = true;
      break;
      label215:
      if (Build.VERSION.SDK_INT < 18) {
        break label75;
      }
      mProxidedActionBar.setHomeAsUpIndicator(R.drawable.mz_ic_ab_back_transparent);
      break label75;
      label236:
      mListenerHadAdd = true;
    }
  }
  
  public static abstract interface OnBackButtonEnableChangeListener
  {
    public abstract void onBackButtonEnableChange(boolean paramBoolean);
  }
  
  public static abstract interface OnTopBackButtonEnableChangeListener
  {
    public abstract void onTopBackButtonEnableChange(boolean paramBoolean);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.ActionBarProxy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
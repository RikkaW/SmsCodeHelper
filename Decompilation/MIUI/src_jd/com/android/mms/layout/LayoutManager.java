package com.android.mms.layout;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;

public class LayoutManager
{
  private static LayoutManager sInstance;
  private final Context mContext;
  private LayoutParameters mLayoutParams;
  
  private LayoutManager(Context paramContext)
  {
    mContext = paramContext;
    initLayoutParameters(paramContext.getResources().getConfiguration());
  }
  
  public static LayoutManager getInstance()
  {
    if (sInstance == null) {
      throw new IllegalStateException("Uninitialized.");
    }
    return sInstance;
  }
  
  private static LayoutParameters getLayoutParameters(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("Unsupported display type: " + paramInt);
    case 10: 
      return new HVGALayoutParameters(10);
    }
    return new HVGALayoutParameters(11);
  }
  
  public static void init(Context paramContext)
  {
    if (sInstance != null) {
      Log.w("LayoutManager", "Already initialized.");
    }
    sInstance = new LayoutManager(paramContext.getApplicationContext());
  }
  
  private void initLayoutParameters(Configuration paramConfiguration)
  {
    if (orientation == 1) {}
    for (int i = 11;; i = 10)
    {
      mLayoutParams = getLayoutParameters(i);
      return;
    }
  }
  
  public LayoutParameters getLayoutParameters()
  {
    return mLayoutParams;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    initLayoutParameters(paramConfiguration);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.layout.LayoutManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
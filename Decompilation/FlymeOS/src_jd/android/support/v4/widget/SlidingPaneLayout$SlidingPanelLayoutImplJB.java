package android.support.v4.widget;

import android.util.Log;
import android.view.View;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class SlidingPaneLayout$SlidingPanelLayoutImplJB
  extends SlidingPaneLayout.SlidingPanelLayoutImplBase
{
  private Method mGetDisplayList;
  private Field mRecreateDisplayList;
  
  SlidingPaneLayout$SlidingPanelLayoutImplJB()
  {
    try
    {
      mGetDisplayList = View.class.getDeclaredMethod("getDisplayList", (Class[])null);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        try
        {
          mRecreateDisplayList = View.class.getDeclaredField("mRecreateDisplayList");
          mRecreateDisplayList.setAccessible(true);
          return;
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          Log.e("SlidingPaneLayout", "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", localNoSuchFieldException);
        }
        localNoSuchMethodException = localNoSuchMethodException;
        Log.e("SlidingPaneLayout", "Couldn't fetch getDisplayList method; dimming won't work right.", localNoSuchMethodException);
      }
    }
  }
  
  public void invalidateChildRegion(SlidingPaneLayout paramSlidingPaneLayout, View paramView)
  {
    if ((mGetDisplayList != null) && (mRecreateDisplayList != null)) {
      try
      {
        mRecreateDisplayList.setBoolean(paramView, true);
        mGetDisplayList.invoke(paramView, (Object[])null);
        super.invalidateChildRegion(paramSlidingPaneLayout, paramView);
        return;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Log.e("SlidingPaneLayout", "Error refreshing display list state", localException);
        }
      }
    }
    paramView.invalidate();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.SlidingPaneLayout.SlidingPanelLayoutImplJB
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
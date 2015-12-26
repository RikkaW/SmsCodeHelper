package com.meizu.common.util;

import android.util.Log;
import android.widget.ListPopupWindow;
import java.lang.reflect.Method;

public class PopupWindowUtils
{
  private static final String TAG = "PopupWindowUtils";
  
  public static boolean adjustWindowPositionForMz(Object paramObject, boolean paramBoolean)
  {
    if (CommonUtils.isFlymeOS()) {
      try
      {
        Method localMethod = paramObject.getClass().getDeclaredMethod("adjustWindowPositionForMz", new Class[] { Boolean.TYPE });
        localMethod.setAccessible(true);
        localMethod.invoke(paramObject, new Object[] { Boolean.valueOf(paramBoolean) });
        return true;
      }
      catch (Exception localException)
      {
        Log.e("PopupWindowUtils", paramObject.getClass().getName() + "#adjustWindowPositionForMz fail to be invoked.Caused by:" + localException.getMessage());
      }
    }
    return false;
  }
  
  public static boolean setContentHeight(ListPopupWindow paramListPopupWindow, int paramInt)
  {
    if (CommonUtils.isFlymeOS()) {
      try
      {
        Method localMethod = ListPopupWindow.class.getDeclaredMethod("setContentHeight", new Class[] { Integer.TYPE });
        localMethod.setAccessible(true);
        localMethod.invoke(paramListPopupWindow, new Object[] { Integer.valueOf(paramInt) });
        return true;
      }
      catch (Exception paramListPopupWindow)
      {
        Log.e("PopupWindowUtils", "setContentHeight fail to be invoked.Caused by:" + paramListPopupWindow.getMessage());
        return false;
      }
    }
    paramListPopupWindow.setHeight(paramInt);
    return true;
  }
  
  public static boolean setLayoutMode(Object paramObject, int paramInt)
  {
    if (CommonUtils.isFlymeOS()) {
      try
      {
        Method localMethod = paramObject.getClass().getDeclaredMethod("setLayoutMode", new Class[] { Integer.TYPE });
        localMethod.setAccessible(true);
        localMethod.invoke(paramObject, new Object[] { Integer.valueOf(paramInt) });
        return true;
      }
      catch (Exception localException)
      {
        Log.e("PopupWindowUtils", paramObject.getClass().getName() + "#setLayoutMode fail to be invoked.Caused by:" + localException.getMessage());
      }
    }
    return false;
  }
  
  public static boolean setPopupLayoutMode(Object paramObject, int paramInt)
  {
    if (CommonUtils.isFlymeOS()) {
      try
      {
        Method localMethod = paramObject.getClass().getDeclaredMethod("setPopupLayoutMode", new Class[] { Integer.TYPE });
        localMethod.setAccessible(true);
        localMethod.invoke(paramObject, new Object[] { Integer.valueOf(paramInt) });
        return true;
      }
      catch (Exception paramObject)
      {
        Log.e("PopupWindowUtils", "popupObject#setPopupLayoutMode fail to be invoked.Caused by:" + ((Exception)paramObject).getMessage());
      }
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.PopupWindowUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
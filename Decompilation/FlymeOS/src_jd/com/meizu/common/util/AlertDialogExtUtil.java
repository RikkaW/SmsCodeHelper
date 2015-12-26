package com.meizu.common.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

public class AlertDialogExtUtil
{
  private static final String TAG = "AlertDialogExtUtil";
  public static final int TITLE_CENTER_HIGHLIGHT = 1;
  private static Class alertDialogClazz = null;
  private static Method setMaxHeightMethod = null;
  private static Method setWidthMethod = null;
  
  public static boolean setAutoShowSoftInput(AlertDialog paramAlertDialog, boolean paramBoolean)
  {
    if (CommonUtils.isFlymeOS()) {
      try
      {
        Method localMethod = alertDialogClazz.getDeclaredMethod("setAutoShowSoftInput", new Class[] { Boolean.TYPE });
        localMethod.setAccessible(true);
        localMethod.invoke(paramAlertDialog, new Object[] { Boolean.valueOf(paramBoolean) });
        return true;
      }
      catch (Exception paramAlertDialog)
      {
        Log.e("AlertDialogExtUtil", "setAutoShowSoftInput fail to be invoked.Caused by:" + paramAlertDialog.getMessage());
      }
    }
    return false;
  }
  
  public static boolean setButtonTextColor(AlertDialog paramAlertDialog, int paramInt1, int paramInt2)
  {
    if (paramAlertDialog == null) {
      return false;
    }
    Button localButton = paramAlertDialog.getButton(paramInt1);
    if ((localButton != null) && (localButton.getVisibility() == 0)) {
      localButton.setTextColor(paramAlertDialog.getContext().getResources().getColorStateList(paramInt2));
    }
    return true;
  }
  
  public static void setButtonUnDismissOnClick(AlertDialog paramAlertDialog, int paramInt, DialogInterface.OnClickListener paramOnClickListener)
  {
    if (paramAlertDialog == null) {}
    Button localButton;
    do
    {
      return;
      localButton = paramAlertDialog.getButton(paramInt);
    } while ((localButton == null) || (localButton.getVisibility() != 0));
    localButton.setOnClickListener(new AlertDialogExtUtil.1(paramAlertDialog, paramInt, paramOnClickListener));
  }
  
  public static boolean setCustEditViewHasMargin(AlertDialog paramAlertDialog, boolean paramBoolean)
  {
    if (CommonUtils.isFlymeOS()) {
      try
      {
        Method localMethod = alertDialogClazz.getDeclaredMethod("setCustEditViewHasMargin", new Class[] { Boolean.TYPE });
        localMethod.setAccessible(true);
        localMethod.invoke(paramAlertDialog, new Object[] { Boolean.valueOf(paramBoolean) });
        return true;
      }
      catch (Exception paramAlertDialog)
      {
        Log.e("AlertDialogExtUtil", "setCustEditViewHasMargin fail to be invoked.Caused by:" + paramAlertDialog.getMessage());
      }
    }
    return false;
  }
  
  public static boolean setListViewLayoutWidth(AlertDialog paramAlertDialog, int paramInt)
  {
    if (CommonUtils.isFlymeOS()) {
      try
      {
        Method localMethod = alertDialogClazz.getDeclaredMethod("setListViewLayoutWidth", new Class[] { Integer.TYPE });
        localMethod.setAccessible(true);
        localMethod.invoke(paramAlertDialog, new Object[] { Integer.valueOf(paramInt) });
        return true;
      }
      catch (Exception paramAlertDialog)
      {
        Log.e("AlertDialogExtUtil", "setListViewLayoutWidth fail to be invoked.Caused by:" + paramAlertDialog.getMessage());
      }
    }
    return false;
  }
  
  public static boolean setMaxHeight(AlertDialog paramAlertDialog, int paramInt)
  {
    if (CommonUtils.isFlymeOS()) {}
    try
    {
      if (alertDialogClazz == null)
      {
        alertDialogClazz = AlertDialog.class;
        setMaxHeightMethod = alertDialogClazz.getDeclaredMethod("setMaxHeight", new Class[] { Integer.TYPE });
        setMaxHeightMethod.setAccessible(true);
        setMaxHeightMethod.invoke(paramAlertDialog, new Object[] { Integer.valueOf(paramInt) });
        return true;
      }
      if (setMaxHeightMethod != null) {
        setMaxHeightMethod.invoke(paramAlertDialog, new Object[] { Integer.valueOf(paramInt) });
      }
    }
    catch (Exception paramAlertDialog)
    {
      for (;;)
      {
        Log.e("AlertDialogExtUtil", "setMaxHeight fail to be invoked.Caused by:" + paramAlertDialog.getMessage());
      }
    }
    return false;
  }
  
  public static boolean setMessage(AlertDialog paramAlertDialog, CharSequence paramCharSequence, int paramInt)
  {
    if (CommonUtils.isFlymeOS()) {
      try
      {
        Method localMethod = alertDialogClazz.getDeclaredMethod("setMessage", new Class[] { CharSequence.class, Integer.TYPE });
        localMethod.setAccessible(true);
        localMethod.invoke(paramAlertDialog, new Object[] { paramCharSequence, Integer.valueOf(paramInt) });
        return true;
      }
      catch (Exception paramAlertDialog)
      {
        Log.e("AlertDialogExtUtil", "setMessage fail to be invoked.Caused by:" + paramAlertDialog.getMessage());
      }
    }
    return false;
  }
  
  public static boolean setTitleStyle(AlertDialog paramAlertDialog, int paramInt)
  {
    if (CommonUtils.isFlymeOS()) {
      try
      {
        Method localMethod = alertDialogClazz.getDeclaredMethod("setTitleStyle", new Class[] { Integer.TYPE });
        localMethod.setAccessible(true);
        localMethod.invoke(paramAlertDialog, new Object[] { Integer.valueOf(paramInt) });
        return true;
      }
      catch (Exception paramAlertDialog)
      {
        Log.e("AlertDialogExtUtil", "setTitleStyle fail to be invoked.Caused by:" + paramAlertDialog.getMessage());
      }
    }
    return false;
  }
  
  public static boolean setWidth(AlertDialog paramAlertDialog, int paramInt)
  {
    if (CommonUtils.isFlymeOS()) {}
    try
    {
      if (alertDialogClazz == null)
      {
        alertDialogClazz = AlertDialog.class;
        setWidthMethod = alertDialogClazz.getDeclaredMethod("setWidth", new Class[] { Integer.TYPE });
        setWidthMethod.setAccessible(true);
        setWidthMethod.invoke(paramAlertDialog, new Object[] { Integer.valueOf(paramInt) });
        return true;
      }
      if (setWidthMethod != null) {
        setWidthMethod.invoke(paramAlertDialog, new Object[] { Integer.valueOf(paramInt) });
      }
    }
    catch (Exception paramAlertDialog)
    {
      for (;;)
      {
        Log.e("AlertDialogExtUtil", "setWidth fail to be invoked.Caused by:" + paramAlertDialog.getMessage());
      }
    }
    return false;
  }
  
  static final class ButtonHandler
    extends Handler
  {
    private WeakReference<DialogInterface> mDialog;
    
    public ButtonHandler(DialogInterface paramDialogInterface)
    {
      mDialog = new WeakReference(paramDialogInterface);
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (what)
      {
      default: 
        return;
      }
      ((DialogInterface.OnClickListener)obj).onClick((DialogInterface)mDialog.get(), what);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.AlertDialogExtUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
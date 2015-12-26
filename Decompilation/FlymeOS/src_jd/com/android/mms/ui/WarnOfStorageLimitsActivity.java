package com.android.mms.ui;

import aau;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import fy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WarnOfStorageLimitsActivity
  extends fy
  implements DialogInterface, DialogInterface.OnClickListener
{
  protected Object a;
  protected Object b;
  
  private boolean a(int paramInt, KeyEvent paramKeyEvent)
  {
    try
    {
      boolean bool = ((Boolean)a.getClass().getDeclaredMethod("onKeyDown", new Class[] { Integer.TYPE, KeyEvent.class }).invoke(a, new Object[] { Integer.valueOf(paramInt), paramKeyEvent })).booleanValue();
      return bool;
    }
    catch (NoSuchMethodException paramKeyEvent)
    {
      paramKeyEvent.printStackTrace();
      return false;
    }
    catch (InvocationTargetException paramKeyEvent)
    {
      for (;;)
      {
        paramKeyEvent.printStackTrace();
      }
    }
    catch (IllegalAccessException paramKeyEvent)
    {
      for (;;)
      {
        paramKeyEvent.printStackTrace();
      }
    }
  }
  
  private boolean b(int paramInt, KeyEvent paramKeyEvent)
  {
    try
    {
      boolean bool = ((Boolean)a.getClass().getDeclaredMethod("onKeyUp", new Class[] { Integer.TYPE, KeyEvent.class }).invoke(a, new Object[] { Integer.valueOf(paramInt), paramKeyEvent })).booleanValue();
      return bool;
    }
    catch (NoSuchMethodException paramKeyEvent)
    {
      paramKeyEvent.printStackTrace();
      return false;
    }
    catch (InvocationTargetException paramKeyEvent)
    {
      for (;;)
      {
        paramKeyEvent.printStackTrace();
      }
    }
    catch (IllegalAccessException paramKeyEvent)
    {
      for (;;)
      {
        paramKeyEvent.printStackTrace();
      }
    }
  }
  
  private void c()
  {
    Class localClass1 = aau.c("com.android.internal.app.AlertController");
    Class localClass2 = aau.c("com.android.internal.app.AlertController$AlertParams");
    try
    {
      a = localClass1.getConstructor(new Class[] { Context.class, DialogInterface.class, Window.class }).newInstance(new Object[] { this, this, getWindow() });
      b = localClass2.getConstructor(new Class[] { Context.class }).newInstance(new Object[] { this });
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
      return;
    }
    catch (InstantiationException localInstantiationException)
    {
      localInstantiationException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
    }
  }
  
  private void d()
  {
    try
    {
      a.getClass().getDeclaredMethod("setMessage", new Class[] { CharSequence.class }).invoke(a, new Object[] { getString(2131493138) });
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
    }
  }
  
  private void e()
  {
    try
    {
      Field localField = b.getClass().getDeclaredField("mButtonPositiveText");
      localField.setAccessible(true);
      localField.set(localField.get(b), getString(2131493139));
      return;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      localNoSuchFieldException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
    }
  }
  
  private void f()
  {
    try
    {
      Field localField = b.getClass().getDeclaredField("mButtonNegativeText");
      localField.setAccessible(true);
      localField.set(localField.get(b), getString(2131493140));
      return;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      localNoSuchFieldException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
    }
  }
  
  private void g()
  {
    try
    {
      Field localField = b.getClass().getDeclaredField("mPositiveButtonListener");
      localField.setAccessible(true);
      localField.set(localField.get(b), this);
      return;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      localNoSuchFieldException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
    }
  }
  
  private void h()
  {
    try
    {
      b.getClass().getDeclaredMethod("apply", new Class[] { a.getClass() }).invoke(b, new Object[] { a });
      a.getClass().getDeclaredMethod("installContent", new Class[0]).invoke(a, new Object[0]);
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
    }
  }
  
  protected void b()
  {
    h();
  }
  
  public void cancel()
  {
    finish();
  }
  
  public void dismiss()
  {
    if (!isFinishing()) {
      finish();
    }
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (paramInt == -1)
    {
      startActivity(new Intent(this, MessagingPreferenceActivity.class));
      overridePendingTransition(aau.f("mz_app_to_next_open_enter"), aau.f("mz_app_to_next_open_exit"));
    }
    paramDialogInterface.dismiss();
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(aau.e("Theme_Dialog_Alert"));
    super.onCreate(paramBundle);
    c();
    paramBundle = b;
    d();
    e();
    f();
    g();
    b();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (a(paramInt, paramKeyEvent)) {
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (b(paramInt, paramKeyEvent)) {
      return true;
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.WarnOfStorageLimitsActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package me.gitai.library.utils;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;


public class ToastUtil
{
  private static Application sContext;

  public static void initialize(Application context) {
    sContext = context;
  }

  public static Application getContext() {
    if(sContext == null) {
      throw new Error("Application context is null.");
    } else {
      return sContext;
    }
  }

  public static void show(Context paramContext, int paramInt)
  {
    synchronized (ToastUtil.class)
    {
      Toast.makeText(paramContext, paramInt, Toast.LENGTH_SHORT).show();
      return;
    }
  }

  public static void show(Context paramContext, String paramString)
  {
    synchronized (ToastUtil.class)
    {
      Toast.makeText(paramContext, paramString, Toast.LENGTH_SHORT).show();
      return;
    }
  }

  public static void show(int paramInt)
  {
    synchronized (ToastUtil.class)
    {
        try{
            Toast.makeText(getContext(), paramInt, Toast.LENGTH_SHORT).show();
        }catch (Exception localException){
            localException.printStackTrace();
        }
      return;
    }
  }

  public static void show(int paramInt,int LENGTH)
  {
    synchronized (ToastUtil.class)
    {
      try{
        Toast.makeText(getContext(), paramInt, LENGTH).show();
      }catch (Exception localException){
        localException.printStackTrace();
      }
      return;
    }
  }

  public static void show(String tag,String paramString){
    show(tag + paramString);
  }

  public static void showId(int id,Object... strs)
  {
    show(String.format(getContext().getString(id),strs));
  }

  public static void show(Exception ex){
    show(ex.getLocalizedMessage());
  }


  public static void show(String paramString)
  {
    synchronized (ToastUtil.class)
    {
        try{
            Toast.makeText(getContext(), paramString, Toast.LENGTH_SHORT).show();
        }catch (Exception localException){
            localException.printStackTrace();
        }
      return;
    }
  }

  public static void show(String paramString,int LENGTH)
  {
    synchronized (ToastUtil.class)
    {
      try{
        Toast.makeText(getContext(), paramString, LENGTH).show();
      }catch (Exception localException){
        localException.printStackTrace();
      }
      return;
    }
  }

}
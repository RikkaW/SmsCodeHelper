package com.meizu.common.util;

import android.os.Build;
import android.preference.PreferenceActivity;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CommonUtils
{
  private static final String TAG = "CommonUtils";
  private static Boolean isFlymeOS = null;
  private static Boolean isFlymeOS4 = null;
  
  public static boolean PreferenceActivity_setActionBarToTop(PreferenceActivity paramPreferenceActivity, boolean paramBoolean)
  {
    if (isFlymeOS()) {
      try
      {
        Method localMethod = PreferenceActivity.class.getDeclaredMethod("setActionBarToTop", new Class[] { Boolean.TYPE });
        localMethod.setAccessible(true);
        localMethod.invoke(paramPreferenceActivity, new Object[] { Boolean.valueOf(paramBoolean) });
        return true;
      }
      catch (Exception paramPreferenceActivity)
      {
        Log.e("CommonUtils", "PreferenceActivity_setActionBarToTop fail to be invoked.Caused by:" + paramPreferenceActivity.getMessage());
      }
    }
    return false;
  }
  
  public static boolean TextView_setEmojiAlphaEnabled(TextView paramTextView, boolean paramBoolean)
  {
    if (isFlymeOS()) {
      try
      {
        Class.forName("android.widget.TextView").getMethod("setEmojiAlphaEnabled", new Class[] { Boolean.TYPE }).invoke(paramTextView, new Object[] { Boolean.valueOf(paramBoolean) });
        return true;
      }
      catch (Exception paramTextView)
      {
        Log.e("CommonUtils", "TextView.setEmojiAlphaEnabled fail to be invoked!");
      }
    }
    return false;
  }
  
  public static boolean TextView_startSelectionActionMode(TextView paramTextView)
  {
    if (isFlymeOS()) {
      try
      {
        boolean bool = ((Boolean)Class.forName("android.widget.TextView").getMethod("startSelectionActionMode", new Class[0]).invoke(paramTextView, new Object[0])).booleanValue();
        return bool;
      }
      catch (Exception paramTextView)
      {
        Log.e("CommonUtils", "TextView.startSelectionActionMode fail to be invoked!");
      }
    }
    return false;
  }
  
  public static String getSystemProperties(String paramString)
  {
    try
    {
      Method localMethod = Build.class.getDeclaredMethod("getString", new Class[] { String.class });
      localMethod.setAccessible(true);
      paramString = (String)localMethod.invoke(null, new Object[] { paramString });
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static boolean isFlymeOS()
  {
    try
    {
      if (isFlymeOS != null) {
        return isFlymeOS.booleanValue();
      }
      if (isFlymeOS4()) {}
      for (isFlymeOS = Boolean.TRUE;; isFlymeOS = Boolean.FALSE) {
        return isFlymeOS.booleanValue();
      }
      return false;
    }
    catch (Exception localException) {}
  }
  
  public static boolean isFlymeOS4()
  {
    try
    {
      if (isFlymeOS4 != null) {
        return isFlymeOS4.booleanValue();
      }
      String str1 = Build.DISPLAY;
      String str2 = getSystemProperties("ro.build.description");
      if ((str1.startsWith("Flyme OS 4")) || ((str2 != null) && (str2.matches(".*")))) {}
      for (isFlymeOS4 = Boolean.TRUE;; isFlymeOS4 = Boolean.FALSE) {
        return isFlymeOS4.booleanValue();
      }
      return false;
    }
    catch (Exception localException) {}
  }
  
  public static boolean setDarkStatusBarIcon(Window paramWindow, boolean paramBoolean)
  {
    if (isFlymeOS())
    {
      paramWindow = paramWindow.getAttributes();
      try
      {
        Field localField1 = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
        Integer localInteger = (Integer)localField1.get(WindowManager.LayoutParams.class);
        Field localField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
        localField1.setAccessible(true);
        int i = Integer.valueOf(localField1.getInt(paramWindow)).intValue();
        if (paramBoolean) {
          localField2.set(paramWindow, Integer.valueOf(localInteger.intValue() | i));
        } else {
          localField2.set(paramWindow, Integer.valueOf((localInteger.intValue() ^ 0xFFFFFFFF) & i));
        }
      }
      catch (Exception paramWindow)
      {
        Log.e("CommonUtils", "setDarkStatusBarIcon fail to be invoked.Caused by:" + paramWindow.getMessage());
      }
    }
    return false;
    return true;
  }
  
  public SpannableStringBuilder createSpannableStringBuilder(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramCharSequence);
    if (paramCharSequence != null)
    {
      localSpannableStringBuilder.setSpan(new AbsoluteSizeSpan(paramInt1, true), 0, paramCharSequence.length(), 34);
      localSpannableStringBuilder.setSpan(new ForegroundColorSpan(paramInt2), 0, paramCharSequence.length(), 34);
    }
    return localSpannableStringBuilder;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.CommonUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
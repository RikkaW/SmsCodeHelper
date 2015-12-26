package com.meizu.common.util;

import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.LightingColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import com.meizu.common.R.attr;
import com.meizu.common.R.color;
import com.meizu.common.R.dimen;
import com.meizu.common.R.styleable;
import com.meizu.common.drawble.BlurDrawable;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ResourceUtils
{
  @SuppressLint({"NewApi"})
  public static void actionBarHomeAsUpOnScrolling(Activity paramActivity, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4, int paramInt5)
  {
    if ((paramActivity == null) || (Build.VERSION.SDK_INT < 18)) {}
    Drawable localDrawable2;
    Drawable localDrawable3;
    do
    {
      return;
      localDrawable2 = paramActivity.getResources().getDrawable(paramInt1);
      localDrawable3 = paramActivity.getResources().getDrawable(paramInt2);
    } while ((localDrawable2 == null) || (localDrawable3 == null));
    if (paramInt3 == paramInt4)
    {
      paramActivity.getActionBar().setHomeAsUpIndicator(paramInt1);
      return;
    }
    if (paramBoolean) {}
    for (Drawable localDrawable1 = localDrawable3;; localDrawable1 = localDrawable2)
    {
      paramActivity.getActionBar().setHomeAsUpIndicator(localDrawable1);
      Rect localRect = new Rect();
      localDrawable3.getPadding(localRect);
      paramInt1 = localDrawable3.getIntrinsicWidth();
      paramInt2 = right;
      int i = localDrawable2.getIntrinsicWidth();
      float f = paramInt3 / (paramInt5 - paramInt4);
      paramInt2 = (int)((paramInt1 - paramInt2 - i) * f + i);
      paramInt1 = paramInt2;
      if (paramBoolean) {
        paramInt1 = paramInt2 + right;
      }
      paramActivity = paramActivity.obtainStyledAttributes(R.styleable.MZTheme);
      paramInt2 = paramActivity.getInt(R.styleable.MZTheme_mzThemeColor, 3319271);
      paramActivity.recycle();
      paramInt2 = getGradualColor(11053224, paramInt2, f, 1);
      localDrawable1.setBounds(0, 0, paramInt1, localDrawable1.getIntrinsicHeight());
      localDrawable1.setColorFilter(new LightingColorFilter(0, paramInt2));
      return;
    }
  }
  
  public static Drawable createBlurDrawable(Drawable paramDrawable, float paramFloat, int paramInt)
  {
    BlurDrawable localBlurDrawable = new BlurDrawable();
    if ((paramFloat >= 0.0F) && (paramFloat <= 1.0F)) {
      localBlurDrawable.setBlurLevel(paramFloat);
    }
    return new LayerDrawable(new Drawable[] { localBlurDrawable, paramDrawable });
  }
  
  private static Drawable createDrawable(int paramInt1, int paramInt2, Rect paramRect)
  {
    Object localObject = new ColorDrawable(paramInt2);
    BlurDrawable localBlurDrawable = new BlurDrawable();
    localBlurDrawable.setColorFilter(paramInt1, BlurDrawable.DEFAULT_BLUR_COLOR_MODE);
    localObject = new LayerDrawable(new Drawable[] { localBlurDrawable, localObject });
    ((LayerDrawable)localObject).setLayerInset(1, left, top, right, bottom);
    return (Drawable)localObject;
  }
  
  public static int getActionBarHeight(Context paramContext)
  {
    TypedValue localTypedValue = new TypedValue();
    if (paramContext.getTheme().resolveAttribute(R.attr.actionBarSize, localTypedValue, true)) {
      return TypedValue.complexToDimensionPixelSize(data, paramContext.getResources().getDisplayMetrics());
    }
    return paramContext.getResources().getDimensionPixelSize(R.dimen.mz_action_bar_default_height_appcompat);
  }
  
  public static ValueAnimator getBackgroundAnimation(View paramView, int paramInt1, int paramInt2)
  {
    int j = Color.alpha(paramInt1);
    int i;
    if (j != 255)
    {
      i = paramInt1;
      if (j != 0) {}
    }
    else
    {
      i = Color.argb(20, Color.red(paramInt1), Color.green(paramInt1), Color.blue(paramInt1));
    }
    paramView.setHasTransientState(true);
    paramView.setBackgroundColor(i);
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofInt(paramView, "backgroundColor", new int[] { i, paramInt2 });
    localObjectAnimator.setEvaluator(new ArgbEvaluator());
    localObjectAnimator.setDuration(1000L);
    localObjectAnimator.setStartDelay(700L);
    localObjectAnimator.addListener(new ResourceUtils.1(paramView));
    return localObjectAnimator;
  }
  
  public static int getGradualColor(int paramInt1, int paramInt2, float paramFloat, int paramInt3)
  {
    int i = Color.red(paramInt1);
    int n = Color.green(paramInt1);
    int j = Color.blue(paramInt1);
    paramInt1 = Color.alpha(paramInt1);
    int i2 = Color.red(paramInt2);
    int i1 = Color.green(paramInt2);
    int m = Color.blue(paramInt2);
    int k = Color.alpha(paramInt2);
    if (paramInt3 < 0)
    {
      i = Math.round(i2 - (i2 - i) * paramFloat);
      paramInt3 = Math.round(i1 - (i1 - n) * paramFloat);
      paramInt2 = Math.round(m - (m - j) * paramFloat);
    }
    for (paramInt1 = Math.round(k - (k - paramInt1) * paramFloat);; paramInt1 = Math.round(paramInt1 + (k - paramInt1) * paramFloat))
    {
      return Color.argb(paramInt1, i, paramInt3, paramInt2);
      float f = i;
      i = Math.round((i2 - i) * paramFloat + f);
      paramInt3 = Math.round(n + (i1 - n) * paramFloat);
      paramInt2 = Math.round(j + (m - j) * paramFloat);
    }
  }
  
  public static Integer getMzThemeColor(Context paramContext)
  {
    int i = paramContext.getResources().getIdentifier("mzThemeColor", "attr", paramContext.getPackageName());
    if (i > 0)
    {
      paramContext = paramContext.getTheme().obtainStyledAttributes(new int[] { i });
      i = paramContext.getColor(0, -1);
      paramContext.recycle();
      if (i != -1) {}
    }
    else
    {
      return null;
    }
    return Integer.valueOf(i);
  }
  
  public static Drawable getSmartBarBackground(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    int i = paramContext.getResources().getColor(R.color.mc_smartbar_divider);
    int j = paramContext.getResources().getColor(R.color.mc_smartbar_background);
    int k = paramContext.getResources().getDimensionPixelSize(R.dimen.mc_smartbarbar_divider_height);
    int m = paramContext.getResources().getDimensionPixelSize(R.dimen.mz_action_bar_default_height_appcompat_split);
    paramContext = new Rect();
    paramContext.set(0, 0, 0, m - k);
    return createDrawable(j, i, paramContext);
  }
  
  public static int getStatusBarHeight(Context paramContext)
  {
    try
    {
      Class localClass = Class.forName("com.android.internal.R$dimen");
      Object localObject = localClass.newInstance();
      int i = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
      i = paramContext.getResources().getDimensionPixelSize(i);
      return i;
    }
    catch (Exception paramContext)
    {
      Log.e("ResurceUtils", "get status bar height fail", paramContext);
    }
    return 75;
  }
  
  public static ArrayList<String> getStringArray(Context paramContext, TypedArray paramTypedArray, int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    if (paramTypedArray.getValue(paramInt, localTypedValue))
    {
      paramInt = resourceId;
      if (paramInt == 0) {
        throw new IllegalStateException("can't find the resourceId");
      }
      paramContext = paramContext.getResources().obtainTypedArray(paramInt);
      int i = paramContext.length();
      paramTypedArray = new ArrayList(i);
      paramInt = 0;
      while (paramInt < i)
      {
        paramTypedArray.add(paramContext.getString(paramInt));
        paramInt += 1;
      }
      paramContext.recycle();
      return paramTypedArray;
    }
    return null;
  }
  
  public static Drawable getTitleBarBackground(Context paramContext, int paramInt)
  {
    return getTitleBarBackground(paramContext, paramInt, true);
  }
  
  public static Drawable getTitleBarBackground(Context paramContext, int paramInt, boolean paramBoolean)
  {
    if (paramContext == null) {
      return null;
    }
    int j = paramContext.getResources().getColor(R.color.mc_titlebar_background);
    Rect localRect = new Rect();
    int k = paramContext.getResources().getDimensionPixelSize(R.dimen.mc_titlebar_divider_height);
    int m = getActionBarHeight(paramContext);
    if (paramBoolean) {}
    for (int i = getStatusBarHeight(paramContext);; i = 0)
    {
      localRect.set(0, i + m - k, 0, 0);
      return createDrawable(j, paramInt, localRect);
    }
  }
  
  public static void startBrightnessAnim(Drawable paramDrawable)
  {
    if (paramDrawable != null)
    {
      if ((paramDrawable instanceof ColorDrawable)) {
        Log.i("error", "fade animation will not be useful to ColorDrawable because ColorDrawable has not implement method setColorFilter");
      }
    }
    else {
      return;
    }
    Object localObject = paramDrawable.getBounds();
    if ((localObject == null) || ((localObject != null) && (((Rect)localObject).isEmpty())))
    {
      Log.i("error", "you should setBounds for drawable before start brightness anim");
      return;
    }
    localObject = new ColorMatrix();
    ValueAnimator localValueAnimator = ValueAnimator.ofPropertyValuesHolder(new PropertyValuesHolder[] { PropertyValuesHolder.ofKeyframe("", new Keyframe[] { Keyframe.ofFloat(0.0F, 0.0F), Keyframe.ofFloat(0.15F, 35.0F), Keyframe.ofFloat(1.0F, 0.0F) }) });
    localValueAnimator.addUpdateListener(new ResourceUtils.2((ColorMatrix)localObject, paramDrawable));
    localValueAnimator.setDuration(550L);
    localValueAnimator.start();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.ResourceUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
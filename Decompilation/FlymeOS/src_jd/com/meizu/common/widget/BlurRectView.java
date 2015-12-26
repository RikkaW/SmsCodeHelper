package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnDrawListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BlurRectView
  extends FrameLayout
  implements ViewTreeObserver.OnDrawListener, ViewTreeObserver.OnGlobalLayoutListener
{
  private static final String TAG = "BlurRectView";
  private boolean mBlurAsBackground = false;
  private Method mBlurMethod;
  private View mBlurView;
  private Field mDirtyField;
  private int mDownscale = 6;
  private boolean mFindBlurRect = false;
  private Object mMzImageProcessing;
  private int mRadius = 7;
  private Bitmap mRawBitmap;
  private Object mViewRootImpl;
  private ViewTreeObserver mViewTreeObserver;
  private Rect mVisibleRect = new Rect();
  private Drawable mWindowBackground;
  
  public BlurRectView(Context paramContext)
  {
    super(paramContext);
  }
  
  public BlurRectView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public BlurRectView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private void applyBlur()
  {
    int i = mDownscale;
    int j = getMeasuredWidth();
    int k = getMeasuredHeight();
    float f = 1.0F / i;
    if (mRawBitmap == null)
    {
      mRawBitmap = Bitmap.createBitmap(j / i, k / i, Bitmap.Config.ARGB_8888);
      i = 1;
    }
    for (;;)
    {
      Canvas localCanvas = new Canvas(mRawBitmap);
      mWindowBackground.setBounds(new Rect(0, 0, j, k));
      mWindowBackground.draw(localCanvas);
      localCanvas.scale(f, f);
      drawBlurView(localCanvas);
      try
      {
        mBlurMethod.invoke(mMzImageProcessing, new Object[] { mRawBitmap, Integer.valueOf(mRadius) });
        if ((i != 0) && (getBackground() == null))
        {
          mBlurAsBackground = true;
          setBackground(new BitmapDrawable(getContext().getResources(), mRawBitmap));
        }
        return;
        mRawBitmap.eraseColor(0);
        i = 0;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        for (;;)
        {
          localIllegalAccessException.printStackTrace();
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;)
        {
          localIllegalArgumentException.printStackTrace();
        }
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        for (;;)
        {
          localInvocationTargetException.printStackTrace();
        }
      }
    }
  }
  
  private void drawBlurView(Canvas paramCanvas)
  {
    int i = mBlurView.getLeft() - getLeft() - mBlurView.getScrollX();
    int j = mBlurView.getTop() - getTop() - mBlurView.getScrollY();
    paramCanvas.translate(i, j);
    mBlurView.draw(paramCanvas);
    paramCanvas.translate(-i, -j);
  }
  
  private boolean findNextView()
  {
    Object localObject = getParent();
    if ((localObject instanceof FrameLayout))
    {
      localObject = (FrameLayout)localObject;
      int i = ((FrameLayout)localObject).getChildCount() - 1;
      int j = 0;
      while (i >= 0)
      {
        View localView = ((FrameLayout)localObject).getChildAt(i);
        if ((j != 0) && (localView.getVisibility() == 0))
        {
          if (new Rect(localView.getLeft(), localView.getTop(), localView.getRight(), localView.getBottom()).intersect(getLeft(), getTop(), getRight(), getBottom()))
          {
            mBlurView = localView;
            return true;
          }
        }
        else if (localView == this) {
          j = 1;
        }
        i -= 1;
      }
    }
    return false;
  }
  
  private void getWindowBackgroundDrawable()
  {
    Object localObject = new TypedValue();
    getContext().getTheme().resolveAttribute(16842836, (TypedValue)localObject, true);
    localObject = getContext().getTheme().obtainStyledAttributes(resourceId, new int[] { 16842836 });
    mWindowBackground = ((TypedArray)localObject).getDrawable(0);
    ((TypedArray)localObject).recycle();
  }
  
  private Bitmap softwareBlur(Bitmap paramBitmap)
  {
    int i6 = mRadius;
    if (i6 < 1) {
      return null;
    }
    int i13 = paramBitmap.getWidth();
    int i14 = paramBitmap.getHeight();
    int[] arrayOfInt1 = new int[i13 * i14];
    paramBitmap.getPixels(arrayOfInt1, 0, i13, 0, 0, i13, i14);
    int i12 = i13 - 1;
    int i15 = i14 - 1;
    int i = i13 * i14;
    int i16 = i6 + i6 + 1;
    int[] arrayOfInt2 = new int[i];
    int[] arrayOfInt3 = new int[i];
    int[] arrayOfInt4 = new int[i];
    int[] arrayOfInt5 = new int[Math.max(i13, i14)];
    i = i16 + 1 >> 1;
    int j = i * i;
    int[] arrayOfInt6 = new int[j * 256];
    i = 0;
    while (i < j * 256)
    {
      arrayOfInt6[i] = (i / j);
      i += 1;
    }
    int[][] arrayOfInt = (int[][])Array.newInstance(Integer.TYPE, new int[] { i16, 3 });
    int i17 = i6 + 1;
    int i8 = 0;
    i = 0;
    int i7 = 0;
    int i9;
    int m;
    int n;
    int i1;
    int i2;
    int i3;
    int i4;
    int i5;
    int k;
    int i10;
    int[] arrayOfInt7;
    int i11;
    int i18;
    while (i7 < i14)
    {
      j = 0;
      i9 = -i6;
      m = 0;
      n = 0;
      i1 = 0;
      i2 = 0;
      i3 = 0;
      i4 = 0;
      i5 = 0;
      k = 0;
      if (i9 <= i6)
      {
        i10 = arrayOfInt1[(Math.min(i12, Math.max(i9, 0)) + i)];
        arrayOfInt7 = arrayOfInt[(i9 + i6)];
        arrayOfInt7[0] = ((0xFF0000 & i10) >> 16);
        arrayOfInt7[1] = ((0xFF00 & i10) >> 8);
        arrayOfInt7[2] = (i10 & 0xFF);
        i10 = i17 - Math.abs(i9);
        i5 += arrayOfInt7[0] * i10;
        i4 += arrayOfInt7[1] * i10;
        i3 += i10 * arrayOfInt7[2];
        if (i9 > 0)
        {
          m += arrayOfInt7[0];
          k += arrayOfInt7[1];
          j += arrayOfInt7[2];
        }
        for (;;)
        {
          i9 += 1;
          break;
          i2 += arrayOfInt7[0];
          i1 += arrayOfInt7[1];
          n += arrayOfInt7[2];
        }
      }
      i9 = i4;
      i11 = 0;
      i4 = i6;
      i10 = i5;
      i5 = i3;
      i3 = i11;
      while (i3 < i13)
      {
        arrayOfInt2[i] = arrayOfInt6[i10];
        arrayOfInt3[i] = arrayOfInt6[i9];
        arrayOfInt4[i] = arrayOfInt6[i5];
        arrayOfInt7 = arrayOfInt[((i4 - i6 + i16) % i16)];
        int i19 = arrayOfInt7[0];
        i18 = arrayOfInt7[1];
        i11 = arrayOfInt7[2];
        if (i7 == 0) {
          arrayOfInt5[i3] = Math.min(i3 + i6 + 1, i12);
        }
        int i20 = arrayOfInt1[(arrayOfInt5[i3] + i8)];
        arrayOfInt7[0] = ((0xFF0000 & i20) >> 16);
        arrayOfInt7[1] = ((0xFF00 & i20) >> 8);
        arrayOfInt7[2] = (i20 & 0xFF);
        m += arrayOfInt7[0];
        k += arrayOfInt7[1];
        j += arrayOfInt7[2];
        i10 = i10 - i2 + m;
        i9 = i9 - i1 + k;
        i5 = i5 - n + j;
        i4 = (i4 + 1) % i16;
        arrayOfInt7 = arrayOfInt[(i4 % i16)];
        i2 = i2 - i19 + arrayOfInt7[0];
        i1 = i1 - i18 + arrayOfInt7[1];
        n = n - i11 + arrayOfInt7[2];
        m -= arrayOfInt7[0];
        k -= arrayOfInt7[1];
        j -= arrayOfInt7[2];
        i += 1;
        i3 += 1;
      }
      i8 += i13;
      i7 += 1;
    }
    i = 0;
    while (i < i13)
    {
      i5 = 0;
      i8 = -i6 * i13;
      i7 = -i6;
      i3 = 0;
      n = 0;
      i1 = 0;
      i2 = 0;
      j = 0;
      m = 0;
      k = 0;
      i4 = 0;
      if (i7 <= i6)
      {
        i9 = Math.max(0, i8) + i;
        arrayOfInt7 = arrayOfInt[(i7 + i6)];
        arrayOfInt7[0] = arrayOfInt2[i9];
        arrayOfInt7[1] = arrayOfInt3[i9];
        arrayOfInt7[2] = arrayOfInt4[i9];
        i10 = i17 - Math.abs(i7);
        i11 = arrayOfInt2[i9];
        i12 = arrayOfInt3[i9];
        i18 = arrayOfInt4[i9];
        if (i7 > 0)
        {
          i3 += arrayOfInt7[0];
          i4 += arrayOfInt7[1];
          i5 += arrayOfInt7[2];
        }
        for (;;)
        {
          i9 = i8;
          if (i7 < i15) {
            i9 = i8 + i13;
          }
          i7 += 1;
          j = i18 * i10 + j;
          m = i12 * i10 + m;
          k = i11 * i10 + k;
          i8 = i9;
          break;
          i2 += arrayOfInt7[0];
          i1 += arrayOfInt7[1];
          n += arrayOfInt7[2];
        }
      }
      i10 = m;
      i11 = k;
      i12 = 0;
      k = i;
      i7 = i5;
      i8 = i4;
      i9 = i3;
      m = n;
      n = i1;
      i1 = i2;
      i2 = i6;
      i5 = i11;
      i4 = i10;
      i3 = j;
      j = i12;
      while (j < i14)
      {
        arrayOfInt1[k] = (0xFF000000 & arrayOfInt1[k] | arrayOfInt6[i5] << 16 | arrayOfInt6[i4] << 8 | arrayOfInt6[i3]);
        arrayOfInt7 = arrayOfInt[((i2 - i6 + i16) % i16)];
        i12 = arrayOfInt7[0];
        i11 = arrayOfInt7[1];
        i10 = arrayOfInt7[2];
        if (i == 0) {
          arrayOfInt5[j] = (Math.min(j + i17, i15) * i13);
        }
        i18 = arrayOfInt5[j] + i;
        arrayOfInt7[0] = arrayOfInt2[i18];
        arrayOfInt7[1] = arrayOfInt3[i18];
        arrayOfInt7[2] = arrayOfInt4[i18];
        i9 += arrayOfInt7[0];
        i8 += arrayOfInt7[1];
        i7 += arrayOfInt7[2];
        i5 = i5 - i1 + i9;
        i4 = i4 - n + i8;
        i3 = i3 - m + i7;
        i2 = (i2 + 1) % i16;
        arrayOfInt7 = arrayOfInt[i2];
        i1 = i1 - i12 + arrayOfInt7[0];
        n = n - i11 + arrayOfInt7[1];
        m = m - i10 + arrayOfInt7[2];
        i9 -= arrayOfInt7[0];
        i8 -= arrayOfInt7[1];
        i7 -= arrayOfInt7[2];
        k += i13;
        j += 1;
      }
      i += 1;
    }
    paramBitmap.setPixels(arrayOfInt1, 0, i13, 0, 0, i13, i14);
    return paramBitmap;
  }
  
  public void draw(Canvas paramCanvas)
  {
    if ((!mBlurAsBackground) && (mRawBitmap != null) && (!mRawBitmap.isRecycled()))
    {
      paramCanvas.drawBitmap(mRawBitmap, 0.0F, 0.0F, null);
      Rect localRect1 = new Rect(0, 0, mRawBitmap.getWidth(), mRawBitmap.getHeight());
      Rect localRect2 = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
      paramCanvas.drawBitmap(mRawBitmap, localRect1, localRect2, null);
    }
    super.draw(paramCanvas);
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (mViewTreeObserver == null) {
      mViewTreeObserver = getViewTreeObserver();
    }
    mViewTreeObserver.addOnGlobalLayoutListener(this);
    mViewTreeObserver.addOnDrawListener(this);
    getWindowBackgroundDrawable();
    try
    {
      mViewRootImpl = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]).invoke(this, new Object[0]);
      mDirtyField = mViewRootImpl.getClass().getDeclaredField("mDirty");
      mDirtyField.setAccessible(true);
    }
    catch (NoSuchMethodException localNoSuchMethodException1)
    {
      for (;;)
      {
        try
        {
          Class localClass = Class.forName("android.graphics.MZImageProcessing");
          mBlurMethod = localClass.getDeclaredMethod("stackBlurMultiThreadProcessedByNative", new Class[] { Bitmap.class, Integer.TYPE });
          mMzImageProcessing = localClass.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
          return;
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
          localClassNotFoundException.printStackTrace();
          return;
        }
        catch (NoSuchMethodException localNoSuchMethodException2)
        {
          localNoSuchMethodException2.printStackTrace();
          return;
        }
        catch (IllegalAccessException localIllegalAccessException2)
        {
          localIllegalAccessException2.printStackTrace();
          return;
        }
        catch (IllegalArgumentException localIllegalArgumentException2)
        {
          localIllegalArgumentException2.printStackTrace();
          return;
        }
        catch (InvocationTargetException localInvocationTargetException2)
        {
          localInvocationTargetException2.printStackTrace();
        }
        localNoSuchMethodException1 = localNoSuchMethodException1;
        localNoSuchMethodException1.printStackTrace();
      }
    }
    catch (IllegalAccessException localIllegalAccessException1)
    {
      for (;;)
      {
        localIllegalAccessException1.printStackTrace();
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException1)
    {
      for (;;)
      {
        localIllegalArgumentException1.printStackTrace();
      }
    }
    catch (InvocationTargetException localInvocationTargetException1)
    {
      for (;;)
      {
        localInvocationTargetException1.printStackTrace();
      }
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;)
      {
        localNoSuchFieldException.printStackTrace();
      }
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (mViewTreeObserver != null)
    {
      mViewTreeObserver.removeOnGlobalLayoutListener(this);
      mViewTreeObserver.removeOnDrawListener(this);
    }
  }
  
  public void onDraw()
  {
    if (mDirtyField != null) {}
    for (;;)
    {
      try
      {
        Rect localRect1 = (Rect)mDirtyField.get(mViewRootImpl);
        if (localRect1 != null) {
          break label64;
        }
        bool = false;
        if ((mFindBlurRect) && (bool)) {
          applyBlur();
        }
        return;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        localIllegalAccessException.printStackTrace();
        Object localObject = null;
        continue;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        localIllegalArgumentException.printStackTrace();
      }
      Rect localRect2 = null;
      continue;
      label64:
      boolean bool = Rect.intersects(mVisibleRect, localRect2);
    }
  }
  
  public void onGlobalLayout()
  {
    if (!mFindBlurRect) {
      mFindBlurRect = findNextView();
    }
    getGlobalVisibleRect(mVisibleRect);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if ((mRawBitmap != null) && (!mRawBitmap.isRecycled()))
    {
      mRawBitmap.recycle();
      mRawBitmap = null;
      if (mBlurAsBackground) {
        setBackground(null);
      }
    }
  }
  
  public void saveToSdCard(Bitmap paramBitmap, String paramString)
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 40, localByteArrayOutputStream);
      paramBitmap = new File(Environment.getExternalStorageDirectory() + File.separator + "DCIM" + File.separator + paramString);
      paramBitmap.createNewFile();
      paramBitmap = new FileOutputStream(paramBitmap);
      paramBitmap.write(localByteArrayOutputStream.toByteArray());
      paramBitmap.close();
      return;
    }
    catch (IOException paramBitmap)
    {
      paramBitmap.printStackTrace();
    }
  }
  
  public void setBlurDownScale(int paramInt)
  {
    mDownscale = Math.abs(paramInt);
    mDownscale = Math.max(1, mDownscale);
  }
  
  public void setBlurRadius(int paramInt)
  {
    mRadius = Math.abs(paramInt);
  }
  
  public void setBlurRaidusAndDownScale(int paramInt1, int paramInt2)
  {
    setBlurRadius(paramInt1);
    setBlurDownScale(paramInt2);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.BlurRectView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
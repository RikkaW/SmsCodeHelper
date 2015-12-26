package com.meizu.common.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.meizu.common.R.drawable;

public class ContactHeaderUtils
{
  private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
  private static final int COLORDRAWABLE_DIMENSION = 2;
  private static final int DEFAULT_BG_COLOR = -11227562;
  private static final int DEFAULT_BORDER_COLOR = -1;
  private static final int[] colorArray = { -1937599, -2271161, -9540664, -11437103, -14107502, -11227562, -1464773 };
  
  private static String checkText(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      paramString = "";
    }
    String str;
    int i;
    do
    {
      do
      {
        return paramString;
        paramString = paramString.trim();
        if (TextUtils.isEmpty(paramString)) {
          return "";
        }
        str = paramString.substring(0, 1);
        i = str.charAt(0);
        paramString = str;
      } while (97 > i);
      paramString = str;
    } while (i > 122);
    return str.toUpperCase();
  }
  
  public static Bitmap createContactHeaderDrawable(Resources paramResources, int paramInt1, int paramInt2, Object paramObject1, Object paramObject2, int paramInt3)
  {
    return createContactHeaderDrawable(paramResources, paramInt1, paramInt2, new Object[] { paramObject1 }, new Object[] { paramObject2 }, paramInt3);
  }
  
  public static Bitmap createContactHeaderDrawable(Resources paramResources, int paramInt1, int paramInt2, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt3)
  {
    int i = 1;
    if (paramArrayOfObject1 != null) {
      i = paramArrayOfObject1.length;
    }
    if (i > 3) {
      i = 3;
    }
    for (;;)
    {
      float f1 = getDisplayMetricsdensity;
      int k = (int)(paramInt1 * f1);
      int m = (int)(paramInt2 * f1);
      paramInt1 = (int)f1;
      if (i == 1) {}
      for (paramInt2 = 0;; paramInt2 = paramInt1 + 1)
      {
        paramInt1 = -11227562;
        RectF localRectF = new RectF();
        Object localObject = new RectF();
        Paint localPaint1 = new Paint();
        Paint localPaint2 = new Paint();
        Paint localPaint3 = new Paint();
        Paint localPaint4 = new Paint();
        Paint localPaint5 = new Paint();
        if (paramInt3 != -1) {}
        for (;;)
        {
          f1 = 0.0F;
          String str = "";
          Bitmap localBitmap = Bitmap.createBitmap(k, m, Bitmap.Config.ARGB_8888);
          Canvas localCanvas = new Canvas(localBitmap);
          localPaint4.setStyle(Paint.Style.FILL_AND_STROKE);
          localPaint4.setAntiAlias(true);
          localPaint4.setColor(-11227562);
          localPaint5.setAntiAlias(true);
          localPaint5.setTextAlign(Paint.Align.CENTER);
          localPaint5.setColor(-1);
          localPaint2.setStyle(Paint.Style.STROKE);
          localPaint2.setAntiAlias(true);
          localPaint2.setColor(paramInt3);
          localPaint2.setStrokeWidth(paramInt2);
          localPaint3.setStyle(Paint.Style.STROKE);
          localPaint3.setAntiAlias(true);
          localPaint3.setColor(419430400);
          localPaint3.setStrokeWidth(1.0F);
          localPaint1.setAntiAlias(true);
          localPaint1.setFilterBitmap(true);
          if (i == 3) {
            ((RectF)localObject).set(0.0F, 0.0F, k * 33 / 46, m * 33 / 46);
          }
          float f3;
          float f4;
          if (i == 2)
          {
            ((RectF)localObject).set(0.0F, 0.0F, k * 38 / 46, m * 38 / 46);
            f3 = Math.min((((RectF)localObject).height() - paramInt2 - 1.0F) / 2.0F, (((RectF)localObject).width() - paramInt2 - 1.0F) / 2.0F);
            localRectF.set((RectF)localObject);
            localRectF.inset(paramInt2, paramInt2);
            f4 = Math.min(localRectF.height() / 2.0F, localRectF.width() / 2.0F);
            if (i == 3) {
              f1 = k * 13 / 92 + paramInt2;
            }
            if (i == 2) {
              f1 = k * 8 / 92 + paramInt2 / 2;
            }
            if (i != 1) {
              break label1433;
            }
            f1 = 0.0F;
          }
          label457:
          label554:
          label643:
          label942:
          label1045:
          label1424:
          label1430:
          label1433:
          for (;;)
          {
            Matrix localMatrix = null;
            BitmapShader localBitmapShader = null;
            localObject = null;
            paramInt3 = i - 1;
            int j;
            if (paramInt3 >= 0)
            {
              j = 0;
              if ((paramArrayOfObject1 == null) || (paramArrayOfObject1[paramInt3] == null) || ((paramArrayOfObject1[paramInt3] instanceof String)))
              {
                if ((paramArrayOfObject1 == null) || (paramArrayOfObject1[paramInt3] == null)) {
                  break label1430;
                }
                str = checkText((String)paramArrayOfObject1[paramInt3]);
                paramInt1 = getColorByText((String)paramArrayOfObject1[paramInt3]);
              }
            }
            for (;;)
            {
              if ((paramArrayOfObject1 == null) || (paramArrayOfObject1[paramInt3] == null) || (TextUtils.isEmpty(str)))
              {
                localObject = getBitmapFromDrawable(paramResources.getDrawable(R.drawable.mc_contact_list_picture));
                j = 1;
              }
              for (;;)
              {
                if ((paramArrayOfObject2 != null) && (paramArrayOfObject2[paramInt3] != null)) {
                  paramInt1 = getColorByText((String)paramArrayOfObject2[paramInt3]);
                }
                for (;;)
                {
                  float f2;
                  if (localObject != null)
                  {
                    localBitmapShader = new BitmapShader((Bitmap)localObject, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                    if (((Bitmap)localObject).getWidth() * localRectF.height() > localRectF.width() * ((Bitmap)localObject).getHeight())
                    {
                      f2 = localRectF.height() / ((Bitmap)localObject).getHeight();
                      localMatrix = new Matrix();
                      localMatrix.setScale(f2, f2);
                      localBitmapShader.setLocalMatrix(localMatrix);
                    }
                  }
                  else
                  {
                    if (paramInt3 != 0) {
                      break label1045;
                    }
                    localPaint4.setColor(paramInt1);
                    if (TextUtils.isEmpty(str)) {
                      break label942;
                    }
                    localCanvas.drawCircle(k / 2 - f1, m / 2, f4, localPaint4);
                    localPaint5.setTextSize((float)(f4 * 0.9D));
                    Paint.FontMetrics localFontMetrics = localPaint5.getFontMetrics();
                    f2 = (bottom - top) / 2.0F;
                    float f5 = bottom;
                    float f6 = m / 2;
                    localCanvas.drawText(str, k / 2 - f1, f2 - f5 + f6 - 2.0F, localPaint5);
                  }
                  for (;;)
                  {
                    if (paramInt2 != 0) {
                      localCanvas.drawCircle(k / 2 - f1, m / 2, f3, localPaint2);
                    }
                    localCanvas.drawCircle(k / 2 - f1, m / 2, f4, localPaint3);
                    paramInt3 -= 1;
                    break label457;
                    ((RectF)localObject).set(0.0F, 0.0F, (i + 1) * k / (i * 2), (i + 1) * m / (i * 2));
                    break;
                    if ((paramArrayOfObject1[paramInt3] instanceof Bitmap))
                    {
                      localObject = (Bitmap)paramArrayOfObject1[paramInt3];
                      break label554;
                    }
                    if (!(paramArrayOfObject1[paramInt3] instanceof Drawable)) {
                      break label1424;
                    }
                    localObject = getBitmapFromDrawable((Drawable)paramArrayOfObject1[paramInt3]);
                    break label554;
                    f2 = localRectF.width() / ((Bitmap)localObject).getWidth();
                    break label643;
                    if (localObject != null)
                    {
                      localMatrix.postTranslate((k - localRectF.width()) / 2.0F - f1, (m - localRectF.height()) / 2.0F);
                      localBitmapShader.setLocalMatrix(localMatrix);
                      localPaint1.setShader(localBitmapShader);
                      if (j != 0) {
                        localCanvas.drawCircle(k / 2 - f1, m / 2, f4, localPaint4);
                      }
                      localCanvas.drawCircle(k / 2 - f1, m / 2, f4, localPaint1);
                    }
                  }
                  if (paramInt3 == i - 1)
                  {
                    if (localObject != null) {
                      if (j != 0)
                      {
                        localPaint4.setColor(paramInt1);
                        localCanvas.drawCircle(k / 2 + f1, m / 2, f4, localPaint4);
                      }
                    }
                    for (;;)
                    {
                      if (paramInt2 != 0) {
                        localCanvas.drawCircle(k / 2 + f1, m / 2, f3, localPaint2);
                      }
                      localCanvas.drawCircle(k / 2 + f1, m / 2, f4, localPaint3);
                      break;
                      localMatrix.postTranslate((k - localRectF.width()) / 2.0F + f1, (m - localRectF.height()) / 2.0F);
                      localBitmapShader.setLocalMatrix(localMatrix);
                      localPaint1.setShader(localBitmapShader);
                      localCanvas.drawCircle(k / 2 + f1, m / 2, f4, localPaint1);
                      continue;
                      localPaint4.setColor(paramInt1);
                      localCanvas.drawCircle(k / 2 + f1, m / 2, f4, localPaint4);
                    }
                  }
                  if (localObject != null) {
                    if (j != 0)
                    {
                      localPaint4.setColor(paramInt1);
                      localCanvas.drawCircle(k / 2, m / 2, f4, localPaint4);
                    }
                  }
                  for (;;)
                  {
                    if (paramInt2 != 0) {
                      localCanvas.drawCircle(k / 2, m / 2, f3, localPaint2);
                    }
                    localCanvas.drawCircle(k / 2, m / 2, f4, localPaint3);
                    break;
                    localMatrix.postTranslate((k - localRectF.width()) / 2.0F, (m - localRectF.height()) / 2.0F);
                    localBitmapShader.setLocalMatrix(localMatrix);
                    localPaint1.setShader(localBitmapShader);
                    localCanvas.drawCircle(k / 2, m / 2, f4, localPaint1);
                    continue;
                    localPaint4.setColor(paramInt1);
                    localCanvas.drawCircle(k / 2, m / 2, f4, localPaint4);
                  }
                  return localBitmap;
                }
              }
            }
          }
          paramInt3 = -1;
        }
      }
    }
  }
  
  private static Bitmap getBitmapFromDrawable(Drawable paramDrawable)
  {
    if (paramDrawable == null) {
      return null;
    }
    if ((paramDrawable instanceof BitmapDrawable)) {
      return ((BitmapDrawable)paramDrawable).getBitmap();
    }
    try
    {
      if ((paramDrawable instanceof ColorDrawable)) {
        return Bitmap.createBitmap(2, 2, BITMAP_CONFIG);
      }
      paramDrawable = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), BITMAP_CONFIG);
      return paramDrawable;
    }
    catch (OutOfMemoryError paramDrawable)
    {
      Log.e("ContactHeaderUtils ", "getBitmapFromDrawable  OutOfMemoryError !");
    }
    return null;
  }
  
  public static int getColorByText(String paramString)
  {
    int i = 5;
    if (!TextUtils.isEmpty(paramString)) {
      i = Math.abs(paramString.hashCode()) % colorArray.length;
    }
    if (i < colorArray.length) {
      return colorArray[i];
    }
    return -11227562;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.ContactHeaderUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
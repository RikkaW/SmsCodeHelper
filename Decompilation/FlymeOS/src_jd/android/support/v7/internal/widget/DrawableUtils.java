package android.support.v7.internal.widget;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DrawableUtils
{
  public static final Rect INSETS_NONE = new Rect();
  private static final String TAG = "DrawableUtils";
  private static Class<?> sInsetsClazz;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 18) {}
    try
    {
      sInsetsClazz = Class.forName("android.graphics.Insets");
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException) {}
  }
  
  public static Rect getOpticalBounds(Drawable paramDrawable)
  {
    if (sInsetsClazz != null) {}
    for (;;)
    {
      Object localObject;
      Rect localRect;
      int j;
      int i;
      try
      {
        paramDrawable = DrawableCompat.unwrap(paramDrawable);
        localObject = paramDrawable.getClass().getMethod("getOpticalInsets", new Class[0]).invoke(paramDrawable, new Object[0]);
        if (localObject == null) {
          break label215;
        }
        localRect = new Rect();
        Field[] arrayOfField = sInsetsClazz.getFields();
        int k = arrayOfField.length;
        j = 0;
        paramDrawable = localRect;
        if (j >= k) {
          break label219;
        }
        paramDrawable = arrayOfField[j];
        str = paramDrawable.getName();
        i = -1;
        switch (str.hashCode())
        {
        case 3317767: 
          if (!str.equals("left")) {
            break label263;
          }
          i = 0;
        }
      }
      catch (Exception paramDrawable)
      {
        String str;
        Log.e("DrawableUtils", "Couldn't obtain the optical insets. Ignoring.");
      }
      if (str.equals("top"))
      {
        i = 1;
        break label263;
        if (str.equals("right"))
        {
          i = 2;
          break label263;
          if (str.equals("bottom"))
          {
            i = 3;
            break label263;
            left = paramDrawable.getInt(localObject);
            break label296;
            label215:
            paramDrawable = INSETS_NONE;
            label219:
            return paramDrawable;
            top = paramDrawable.getInt(localObject);
            break label296;
            right = paramDrawable.getInt(localObject);
            break label296;
            bottom = paramDrawable.getInt(localObject);
            break label296;
          }
        }
      }
      label263:
      switch (i)
      {
      }
      label296:
      j += 1;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.DrawableUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
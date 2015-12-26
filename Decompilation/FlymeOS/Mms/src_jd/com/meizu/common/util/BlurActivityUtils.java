package com.meizu.common.util;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.meizu.common.drawble.BlurDrawable;

public class BlurActivityUtils
{
  public static void enableBlurActivity(Activity paramActivity)
  {
    new BlurDrawable();
    Object localObject = paramActivity.getActionBar();
    ((ActionBar)localObject).setDisplayOptions(0);
    setActionBarViewCollapsable((ActionBar)localObject, true);
    ColorDrawable localColorDrawable = new ColorDrawable(-695533);
    ((ActionBar)localObject).setBackgroundDrawable(new LayerDrawable(new Drawable[] { new BlurDrawable(), localColorDrawable }));
    ((ActionBar)localObject).setSplitBackgroundDrawable(new BlurDrawable());
    paramActivity = paramActivity.getWindow();
    localObject = paramActivity.getAttributes();
    flags |= 0x4000000;
    paramActivity.setAttributes((WindowManager.LayoutParams)localObject);
  }
  
  /* Error */
  private static void setActionBarViewCollapsable(ActionBar paramActionBar, boolean paramBoolean)
  {
    // Byte code:
    //   0: ldc 84
    //   2: invokestatic 90	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   5: ldc 91
    //   7: iconst_1
    //   8: anewarray 86	java/lang/Class
    //   11: dup
    //   12: iconst_0
    //   13: getstatic 97	java/lang/Boolean:TYPE	Ljava/lang/Class;
    //   16: aastore
    //   17: invokevirtual 101	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   20: astore_2
    //   21: aload_2
    //   22: aload_0
    //   23: iconst_1
    //   24: anewarray 4	java/lang/Object
    //   27: dup
    //   28: iconst_0
    //   29: iload_1
    //   30: invokestatic 105	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   33: aastore
    //   34: invokevirtual 111	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   37: pop
    //   38: return
    //   39: astore_0
    //   40: aload_0
    //   41: invokevirtual 114	java/lang/IllegalArgumentException:printStackTrace	()V
    //   44: return
    //   45: astore_0
    //   46: aload_0
    //   47: invokevirtual 115	java/lang/SecurityException:printStackTrace	()V
    //   50: return
    //   51: astore_0
    //   52: aload_0
    //   53: invokevirtual 116	java/lang/IllegalAccessException:printStackTrace	()V
    //   56: return
    //   57: astore_0
    //   58: aload_0
    //   59: invokevirtual 117	java/lang/NoSuchMethodException:printStackTrace	()V
    //   62: return
    //   63: astore_0
    //   64: aload_0
    //   65: invokevirtual 118	java/lang/reflect/InvocationTargetException:printStackTrace	()V
    //   68: return
    //   69: astore_0
    //   70: aload_0
    //   71: invokevirtual 119	java/lang/ClassNotFoundException:printStackTrace	()V
    //   74: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	75	0	paramActionBar	ActionBar
    //   0	75	1	paramBoolean	boolean
    //   20	2	2	localMethod	java.lang.reflect.Method
    // Exception table:
    //   from	to	target	type
    //   21	38	39	java/lang/IllegalArgumentException
    //   0	21	45	java/lang/SecurityException
    //   21	38	45	java/lang/SecurityException
    //   40	44	45	java/lang/SecurityException
    //   52	56	45	java/lang/SecurityException
    //   64	68	45	java/lang/SecurityException
    //   21	38	51	java/lang/IllegalAccessException
    //   0	21	57	java/lang/NoSuchMethodException
    //   21	38	57	java/lang/NoSuchMethodException
    //   40	44	57	java/lang/NoSuchMethodException
    //   52	56	57	java/lang/NoSuchMethodException
    //   64	68	57	java/lang/NoSuchMethodException
    //   21	38	63	java/lang/reflect/InvocationTargetException
    //   0	21	69	java/lang/ClassNotFoundException
    //   21	38	69	java/lang/ClassNotFoundException
    //   40	44	69	java/lang/ClassNotFoundException
    //   52	56	69	java/lang/ClassNotFoundException
    //   64	68	69	java/lang/ClassNotFoundException
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.BlurActivityUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
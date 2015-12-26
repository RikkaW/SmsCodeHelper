package android.support.v7.internal.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Constructor;
import java.util.Map;

public class AppCompatViewInflater
{
  private static final String LOG_TAG = "AppCompatViewInflater";
  private static final Map<String, Constructor<? extends View>> sConstructorMap = new ArrayMap();
  static final Class<?>[] sConstructorSignature = { Context.class, AttributeSet.class };
  private final Object[] mConstructorArgs = new Object[2];
  
  private View createView(Context paramContext, String paramString1, String paramString2)
  {
    Constructor localConstructor = (Constructor)sConstructorMap.get(paramString1);
    Object localObject = localConstructor;
    if (localConstructor == null) {}
    try
    {
      localObject = paramContext.getClassLoader();
      if (paramString2 != null) {}
      for (paramContext = paramString2 + paramString1;; paramContext = paramString1)
      {
        localObject = ((ClassLoader)localObject).loadClass(paramContext).asSubclass(View.class).getConstructor(sConstructorSignature);
        sConstructorMap.put(paramString1, localObject);
        ((Constructor)localObject).setAccessible(true);
        paramContext = (View)((Constructor)localObject).newInstance(mConstructorArgs);
        return paramContext;
      }
      return null;
    }
    catch (Exception paramContext) {}
  }
  
  /* Error */
  private View createViewFromTag(Context paramContext, String paramString, AttributeSet paramAttributeSet)
  {
    // Byte code:
    //   0: aload_2
    //   1: astore 4
    //   3: aload_2
    //   4: ldc 96
    //   6: invokevirtual 102	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   9: ifeq +14 -> 23
    //   12: aload_3
    //   13: aconst_null
    //   14: ldc 104
    //   16: invokeinterface 108 3 0
    //   21: astore 4
    //   23: aload_0
    //   24: getfield 37	android/support/v7/internal/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   27: iconst_0
    //   28: aload_1
    //   29: aastore
    //   30: aload_0
    //   31: getfield 37	android/support/v7/internal/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   34: iconst_1
    //   35: aload_3
    //   36: aastore
    //   37: iconst_m1
    //   38: aload 4
    //   40: bipush 46
    //   42: invokevirtual 112	java/lang/String:indexOf	(I)I
    //   45: if_icmpne +29 -> 74
    //   48: aload_0
    //   49: aload_1
    //   50: aload 4
    //   52: ldc 114
    //   54: invokespecial 116	android/support/v7/internal/app/AppCompatViewInflater:createView	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/view/View;
    //   57: astore_1
    //   58: aload_0
    //   59: getfield 37	android/support/v7/internal/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   62: iconst_0
    //   63: aconst_null
    //   64: aastore
    //   65: aload_0
    //   66: getfield 37	android/support/v7/internal/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   69: iconst_1
    //   70: aconst_null
    //   71: aastore
    //   72: aload_1
    //   73: areturn
    //   74: aload_0
    //   75: aload_1
    //   76: aload 4
    //   78: aconst_null
    //   79: invokespecial 116	android/support/v7/internal/app/AppCompatViewInflater:createView	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/view/View;
    //   82: astore_1
    //   83: aload_0
    //   84: getfield 37	android/support/v7/internal/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   87: iconst_0
    //   88: aconst_null
    //   89: aastore
    //   90: aload_0
    //   91: getfield 37	android/support/v7/internal/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   94: iconst_1
    //   95: aconst_null
    //   96: aastore
    //   97: aload_1
    //   98: areturn
    //   99: astore_1
    //   100: aload_0
    //   101: getfield 37	android/support/v7/internal/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   104: iconst_0
    //   105: aconst_null
    //   106: aastore
    //   107: aload_0
    //   108: getfield 37	android/support/v7/internal/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   111: iconst_1
    //   112: aconst_null
    //   113: aastore
    //   114: aconst_null
    //   115: areturn
    //   116: astore_1
    //   117: aload_0
    //   118: getfield 37	android/support/v7/internal/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   121: iconst_0
    //   122: aconst_null
    //   123: aastore
    //   124: aload_0
    //   125: getfield 37	android/support/v7/internal/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   128: iconst_1
    //   129: aconst_null
    //   130: aastore
    //   131: aload_1
    //   132: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	133	0	this	AppCompatViewInflater
    //   0	133	1	paramContext	Context
    //   0	133	2	paramString	String
    //   0	133	3	paramAttributeSet	AttributeSet
    //   1	76	4	str	String
    // Exception table:
    //   from	to	target	type
    //   23	58	99	java/lang/Exception
    //   74	83	99	java/lang/Exception
    //   23	58	116	finally
    //   74	83	116	finally
  }
  
  private static Context themifyContext(Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.View, 0, 0);
    if (paramBoolean1) {}
    for (int i = paramAttributeSet.getResourceId(R.styleable.View_android_theme, 0);; i = 0)
    {
      int j = i;
      if (paramBoolean2)
      {
        j = i;
        if (i == 0)
        {
          i = paramAttributeSet.getResourceId(R.styleable.View_theme, 0);
          j = i;
          if (i != 0)
          {
            Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
            j = i;
          }
        }
      }
      paramAttributeSet.recycle();
      paramAttributeSet = paramContext;
      if (j != 0) {
        if ((paramContext instanceof ContextThemeWrapper))
        {
          paramAttributeSet = paramContext;
          if (((ContextThemeWrapper)paramContext).getThemeResId() == j) {}
        }
        else
        {
          paramAttributeSet = new ContextThemeWrapper(paramContext, j);
        }
      }
      return paramAttributeSet;
    }
  }
  
  public final View createView(View paramView, String paramString, @NonNull Context paramContext, @NonNull AttributeSet paramAttributeSet, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if ((paramBoolean1) && (paramView != null)) {}
    for (paramView = paramView.getContext();; paramView = paramContext)
    {
      Object localObject;
      if (!paramBoolean2)
      {
        localObject = paramView;
        if (!paramBoolean3) {}
      }
      else
      {
        localObject = themifyContext(paramView, paramAttributeSet, paramBoolean2, paramBoolean3);
      }
      if (paramContext != localObject) {
        return createViewFromTag((Context)localObject, paramString, paramAttributeSet);
      }
      return null;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.app.AppCompatViewInflater
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
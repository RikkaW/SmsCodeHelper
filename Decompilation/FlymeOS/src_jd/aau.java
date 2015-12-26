import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager.LayoutParams;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class aau
{
  public static Object a(Class<?> paramClass, Object paramObject, String paramString)
  {
    try
    {
      paramClass = paramClass.getDeclaredField(paramString);
      paramClass.setAccessible(true);
      paramClass = paramClass.get(paramObject);
      return paramClass;
    }
    catch (IllegalAccessException paramClass)
    {
      paramClass.printStackTrace();
      return null;
    }
    catch (NoSuchFieldException paramClass)
    {
      for (;;)
      {
        paramClass.printStackTrace();
      }
    }
  }
  
  public static Object a(Class<?> paramClass1, Object paramObject1, String paramString, Class<?> paramClass2, Object paramObject2)
  {
    try
    {
      paramClass1 = paramClass1.getDeclaredMethod(paramString, new Class[] { paramClass2 }).invoke(paramObject1, new Object[] { paramObject2 });
      return paramClass1;
    }
    catch (Exception paramClass1)
    {
      paramClass1.printStackTrace();
    }
    return null;
  }
  
  public static Object a(Class<?> paramClass, Object paramObject1, String paramString, Object paramObject2)
  {
    try
    {
      paramClass = paramClass.getDeclaredField(paramString);
      paramClass.setAccessible(true);
      paramClass.set(paramObject1, paramObject2);
      return null;
    }
    catch (NoSuchFieldException paramClass)
    {
      for (;;)
      {
        paramClass.printStackTrace();
      }
    }
    catch (IllegalAccessException paramClass)
    {
      for (;;)
      {
        paramClass.printStackTrace();
      }
    }
  }
  
  public static Object a(Class paramClass, String paramString)
  {
    try
    {
      paramClass = paramClass.getDeclaredField(paramString).get(paramClass);
      return paramClass;
    }
    catch (NoSuchFieldException paramClass)
    {
      paramClass.printStackTrace();
      return null;
    }
    catch (IllegalAccessException paramClass)
    {
      for (;;)
      {
        paramClass.printStackTrace();
      }
    }
    catch (IllegalArgumentException paramClass)
    {
      for (;;)
      {
        paramClass.printStackTrace();
      }
    }
  }
  
  public static Object a(Class<?> paramClass1, String paramString, Class<?> paramClass2, Object paramObject)
  {
    try
    {
      paramClass1 = paramClass1.getDeclaredMethod(paramString, new Class[] { paramClass2 }).invoke(paramClass1, new Object[] { paramObject });
      return paramClass1;
    }
    catch (Exception paramClass1)
    {
      paramClass1.printStackTrace();
    }
    return null;
  }
  
  public static Object a(Class<?> paramClass, String paramString, Class[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      paramClass = paramClass.getDeclaredMethod(paramString, paramArrayOfClass).invoke(null, paramArrayOfObject);
      return paramClass;
    }
    catch (Exception paramClass)
    {
      paramClass.printStackTrace();
    }
    return null;
  }
  
  public static Object a(Class<?> paramClass, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      paramClass = paramClass.getDeclaredConstructor(paramArrayOfClass).newInstance(paramArrayOfObject);
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      paramClass.printStackTrace();
      return null;
    }
    catch (InvocationTargetException paramClass)
    {
      for (;;)
      {
        paramClass.printStackTrace();
      }
    }
    catch (InstantiationException paramClass)
    {
      for (;;)
      {
        paramClass.printStackTrace();
      }
    }
    catch (IllegalAccessException paramClass)
    {
      for (;;)
      {
        paramClass.printStackTrace();
      }
    }
  }
  
  public static Object a(Object paramObject, String paramString)
  {
    try
    {
      paramString = paramObject.getClass().getDeclaredMethod(paramString, new Class[0]);
      paramString.setAccessible(true);
      paramObject = paramString.invoke(paramObject, new Object[0]);
      return paramObject;
    }
    catch (Exception paramObject)
    {
      ((Exception)paramObject).printStackTrace();
    }
    return null;
  }
  
  public static Object a(Object paramObject1, String paramString, Class<?> paramClass, Object paramObject2)
  {
    try
    {
      paramObject1 = paramObject1.getClass().getMethod(paramString, new Class[] { paramClass }).invoke(paramObject1, new Object[] { paramObject2 });
      return paramObject1;
    }
    catch (NoSuchMethodException paramObject1)
    {
      ((NoSuchMethodException)paramObject1).printStackTrace();
      return null;
    }
    catch (InvocationTargetException paramObject1)
    {
      for (;;)
      {
        ((InvocationTargetException)paramObject1).printStackTrace();
      }
    }
    catch (IllegalAccessException paramObject1)
    {
      for (;;)
      {
        ((IllegalAccessException)paramObject1).printStackTrace();
      }
    }
  }
  
  public static Object a(String paramString, Class<?> paramClass, Object paramObject)
  {
    try
    {
      paramString = Class.forName(paramString).getDeclaredConstructor(new Class[] { paramClass }).newInstance(new Object[] { paramObject });
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    catch (NoSuchMethodException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    catch (InvocationTargetException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    catch (InstantiationException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    catch (IllegalAccessException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public static Object a(String paramString1, Object paramObject, String paramString2)
  {
    try
    {
      paramObject = Class.forName("android.os.ServiceManager").getMethod("getService", new Class[] { String.class }).invoke(paramObject, new Object[] { paramString2 });
      paramString1 = Class.forName(paramString1);
      paramString1 = paramString1.getMethod("asInterface", new Class[] { IBinder.class }).invoke(paramString1, new Object[] { paramObject });
      return paramString1;
    }
    catch (InvocationTargetException paramString1)
    {
      paramString1.printStackTrace();
      return null;
    }
    catch (NoSuchMethodException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
    catch (IllegalAccessException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
    catch (ClassNotFoundException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
  }
  
  public static Object a(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = Class.forName(paramString1);
      paramString1 = paramString1.getDeclaredField(paramString2).get(paramString1);
      return paramString1;
    }
    catch (NoSuchFieldException paramString1)
    {
      paramString1.printStackTrace();
      return null;
    }
    catch (ClassNotFoundException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
    catch (IllegalAccessException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
    catch (IllegalArgumentException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
  }
  
  public static Object a(String paramString1, String paramString2, Class<?> paramClass, Object paramObject)
  {
    try
    {
      paramString1 = Class.forName(paramString1).getDeclaredMethod(paramString2, new Class[] { paramClass }).invoke(null, new Object[] { paramObject });
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public static Object a(String paramString1, String paramString2, Class<?> paramClass1, Object paramObject1, String paramString3, String paramString4, Class<?> paramClass2, String paramString5, Class<?> paramClass3, Object paramObject2)
  {
    Object localObject = null;
    try
    {
      paramString1 = Class.forName(paramString1);
      paramString2 = paramString1.getDeclaredMethod(paramString2, new Class[] { paramClass1 }).invoke(paramString1, new Object[] { paramObject1 });
      paramString1 = (String)localObject;
      if (paramString2 != null)
      {
        paramString1 = Class.forName(paramString3);
        paramString2 = paramString1.getDeclaredMethod(paramString4, new Class[] { paramClass2 }).invoke(paramString1, new Object[] { paramString2 });
        paramString1 = (String)localObject;
        if (paramString2 != null) {
          paramString1 = paramString2.getClass().getDeclaredMethod(paramString5, new Class[] { paramClass3 }).invoke(paramString2, new Object[] { paramObject2 });
        }
      }
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public static Object a(String paramString1, String paramString2, String paramString3, Class<?> paramClass, Object paramObject)
  {
    Object localObject = null;
    try
    {
      Class localClass = Class.forName(paramString1);
      paramString2 = localClass.getDeclaredMethod(paramString2, new Class[0]).invoke(null, new Object[0]);
      paramString1 = (String)localObject;
      if (paramString2 != null) {
        paramString1 = localClass.getDeclaredMethod(paramString3, new Class[] { paramClass }).invoke(paramString2, new Object[] { paramObject });
      }
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public static Object a(String paramString1, String paramString2, String paramString3, Class<?>[] paramArrayOfClass, Context paramContext)
  {
    try
    {
      paramString1 = Class.forName(paramString1);
      paramString3 = paramString1.getDeclaredField(paramString3);
      paramString1 = paramString1.getMethod(paramString2, paramArrayOfClass).invoke(null, new Object[] { paramContext, paramString3.get(paramString1) });
      return paramString1;
    }
    catch (ClassNotFoundException paramString1)
    {
      paramString1.printStackTrace();
      return null;
    }
    catch (InvocationTargetException paramString1)
    {
      paramString1.printStackTrace();
      return null;
    }
    catch (NoSuchMethodException paramString1)
    {
      paramString1.printStackTrace();
      return null;
    }
    catch (IllegalAccessException paramString1)
    {
      paramString1.printStackTrace();
      return null;
    }
    catch (NoSuchFieldException paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public static Object a(String paramString1, String paramString2, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      paramString1 = Class.forName(paramString1).getDeclaredMethod(paramString2, paramArrayOfClass).invoke(null, paramArrayOfObject);
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public static String a(String paramString)
  {
    paramString = a("com.android.internal.telephony.TelephonyProperties", paramString);
    if ((paramString != null) && ((paramString instanceof String)))
    {
      paramString = a("android.os.SystemProperties", "get", String.class, paramString);
      if ((paramString != null) && ((paramString instanceof String))) {
        return (String)paramString;
      }
    }
    return null;
  }
  
  public static String a(String paramString, Object paramObject)
  {
    paramString = a("com.android.internal.telephony.TelephonyProperties", paramString);
    if ((paramString != null) && ((paramString instanceof String)))
    {
      paramString = a("android.os.SystemProperties", "get", new Class[] { String.class, String.class }, new Object[] { paramString, paramObject });
      if ((paramString != null) && ((paramString instanceof String))) {
        return (String)paramString;
      }
    }
    return null;
  }
  
  public static void a()
  {
    label251:
    for (;;)
    {
      try
      {
        Class localClass = Class.forName("android.os.BuildExt");
        Object localObject = localClass.newInstance();
        Method localMethod = localClass.getDeclaredMethod("isChinaMobile", new Class[0]);
        localMethod.setAccessible(true);
        com.android.mms.MmsApp.a = ((Boolean)localMethod.invoke(localObject, new Object[0])).booleanValue();
        localMethod = localClass.getDeclaredMethod("isChinaUnicom", new Class[0]);
        localMethod.setAccessible(true);
        com.android.mms.MmsApp.c = ((Boolean)localMethod.invoke(localObject, new Object[0])).booleanValue();
        com.android.mms.MmsApp.d = ((Boolean)localClass.getMethod("isProductInternational", new Class[0]).invoke(null, new Object[0])).booleanValue();
        com.android.mms.MmsApp.b = ((Boolean)a(localClass, "CUSTOMIZE_CHINATELECOM")).booleanValue();
        com.android.mms.MmsApp.e = ((Boolean)localClass.getMethod("isFlymeRom", new Class[0]).invoke(null, new Object[0])).booleanValue();
        if ((!((Boolean)a(localClass, "IS_MX2")).booleanValue()) && (!((Boolean)a(localClass, "IS_MX3")).booleanValue())) {
          if (((Boolean)a(localClass, "IS_MX4_Pro")).booleanValue())
          {
            break label251;
            com.android.mms.MmsApp.g = bool;
            com.android.mms.MmsApp.f = ((Boolean)a(localClass, "IS_CTA")).booleanValue();
          }
          else
          {
            bool = false;
            continue;
          }
        }
        boolean bool = true;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        localClassNotFoundException.printStackTrace();
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
    }
  }
  
  public static void a(Context paramContext, SQLiteException paramSQLiteException)
  {
    a("android.database.sqlite.SqliteWrapper", "checkSQLiteException", new Class[] { Context.class, SQLiteException.class }, null, new Object[] { paramContext, paramSQLiteException });
  }
  
  public static void a(WindowManager.LayoutParams paramLayoutParams, int paramInt)
  {
    try
    {
      Field localField = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
      localField.setAccessible(true);
      localField.set(paramLayoutParams, Integer.valueOf(((Integer)localField.get(paramLayoutParams)).intValue() | paramInt));
      return;
    }
    catch (IllegalAccessException paramLayoutParams)
    {
      paramLayoutParams.printStackTrace();
      return;
    }
    catch (NoSuchFieldException paramLayoutParams)
    {
      paramLayoutParams.printStackTrace();
    }
  }
  
  public static void a(Class<?> paramClass1, Object paramObject1, String paramString1, String paramString2, Class<?> paramClass2, Object paramObject2)
  {
    try
    {
      paramClass1 = paramClass1.getDeclaredField(paramString1);
      paramClass1.setAccessible(true);
      paramClass1 = paramClass1.get(paramObject1);
      paramClass1.getClass().getDeclaredMethod(paramString2, new Class[] { paramClass2 }).invoke(paramClass1, new Object[] { paramObject2 });
      return;
    }
    catch (NoSuchFieldException paramClass1)
    {
      paramClass1.printStackTrace();
      return;
    }
    catch (InvocationTargetException paramClass1)
    {
      paramClass1.printStackTrace();
      return;
    }
    catch (NoSuchMethodException paramClass1)
    {
      paramClass1.printStackTrace();
      return;
    }
    catch (IllegalAccessException paramClass1)
    {
      paramClass1.printStackTrace();
    }
  }
  
  public static void a(Class<?> paramClass, Object paramObject, String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2)
  {
    try
    {
      paramClass = paramClass.getDeclaredField(paramString1).get(paramObject);
      paramObject = paramClass.getClass().getDeclaredField(paramString2);
      ((Field)paramObject).setAccessible(true);
      ((Field)paramObject).set(paramClass, Integer.valueOf(paramInt1));
      paramObject = paramClass.getClass().getDeclaredField(paramString3);
      ((Field)paramObject).setAccessible(true);
      ((Field)paramObject).set(paramClass, Integer.valueOf(paramInt2));
      return;
    }
    catch (NoSuchFieldException paramClass)
    {
      paramClass.printStackTrace();
      return;
    }
    catch (IllegalAccessException paramClass)
    {
      paramClass.printStackTrace();
    }
  }
  
  public static void a(Class<?> paramClass, Object paramObject, String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      paramClass = paramClass.getDeclaredMethod(paramString, paramArrayOfClass);
      paramClass.setAccessible(true);
      paramClass.invoke(paramObject, paramArrayOfObject);
      return;
    }
    catch (InvocationTargetException paramClass)
    {
      paramClass.printStackTrace();
      return;
    }
    catch (NoSuchMethodException paramClass)
    {
      paramClass.printStackTrace();
      return;
    }
    catch (IllegalAccessException paramClass)
    {
      paramClass.printStackTrace();
    }
  }
  
  public static void a(Object paramObject, String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      paramString = paramObject.getClass().getDeclaredMethod(paramString, paramArrayOfClass);
      paramString.setAccessible(true);
      paramString.invoke(paramObject, paramArrayOfObject);
      return;
    }
    catch (Exception paramObject)
    {
      ((Exception)paramObject).printStackTrace();
    }
  }
  
  public static void a(String paramString1, Object paramObject, String paramString2, Class<?>[] paramArrayOfClass1, Object[] paramArrayOfObject1, String paramString3, Class<?>[] paramArrayOfClass2, Object[] paramArrayOfObject2)
  {
    try
    {
      paramString1 = Class.forName(paramString1);
      paramObject = paramString1.getDeclaredMethod(paramString2, paramArrayOfClass1).invoke(paramObject, paramArrayOfObject1);
      paramString1.getDeclaredMethod(paramString3, paramArrayOfClass2).invoke(paramObject, paramArrayOfObject2);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static void a(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      Class localClass = Class.forName("android.os.ServiceManager");
      paramString2 = localClass.getMethod("getService", new Class[] { String.class }).invoke(localClass, new Object[] { paramString2 });
      paramString1 = Class.forName(paramString1);
      paramString2 = paramString1.getMethod("asInterface", new Class[] { IBinder.class }).invoke(paramString1, new Object[] { paramString2 });
      if (paramString2 != null) {
        paramString1.getMethod(paramString3, new Class[0]).invoke(paramString2, new Object[0]);
      }
      return;
    }
    catch (ClassNotFoundException paramString1)
    {
      paramString1.printStackTrace();
      return;
    }
    catch (NoSuchMethodException paramString1)
    {
      paramString1.printStackTrace();
      return;
    }
    catch (InvocationTargetException paramString1)
    {
      paramString1.printStackTrace();
      return;
    }
    catch (IllegalAccessException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static void a(String paramString1, String paramString2, Class<?>[] paramArrayOfClass, Object paramObject, Object[] paramArrayOfObject)
  {
    try
    {
      Class.forName(paramString1).getDeclaredMethod(paramString2, paramArrayOfClass).invoke(paramObject, paramArrayOfObject);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static Object b(Class<?> paramClass, Object paramObject, String paramString)
  {
    try
    {
      paramClass = paramClass.getDeclaredMethod(paramString, new Class[0]).invoke(paramObject, new Object[0]);
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      paramClass.printStackTrace();
      return null;
    }
    catch (InvocationTargetException paramClass)
    {
      for (;;)
      {
        paramClass.printStackTrace();
      }
    }
    catch (IllegalAccessException paramClass)
    {
      for (;;)
      {
        paramClass.printStackTrace();
      }
    }
  }
  
  public static Object b(Class<?> paramClass, Object paramObject, String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      paramClass = paramClass.getDeclaredMethod(paramString, paramArrayOfClass);
      paramClass.setAccessible(true);
      paramClass = paramClass.invoke(paramObject, paramArrayOfObject);
      return paramClass;
    }
    catch (Exception paramClass)
    {
      paramClass.printStackTrace();
    }
    return null;
  }
  
  public static Object b(Object paramObject, String paramString)
  {
    try
    {
      paramString = paramObject.getClass().getDeclaredField(paramString);
      paramString.setAccessible(true);
      paramObject = paramString.get(paramObject);
      return paramObject;
    }
    catch (IllegalAccessException paramObject)
    {
      ((IllegalAccessException)paramObject).printStackTrace();
      return null;
    }
    catch (NoSuchFieldException paramObject)
    {
      for (;;)
      {
        ((NoSuchFieldException)paramObject).printStackTrace();
      }
    }
  }
  
  public static Object b(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = Class.forName(paramString1).getDeclaredMethod(paramString2, new Class[0]).invoke(null, new Object[0]);
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public static Object b(String paramString1, String paramString2, Class<?> paramClass, Object paramObject)
  {
    try
    {
      paramString1 = Class.forName(paramString1).getDeclaredMethod(paramString2, new Class[] { paramClass }).invoke(null, new Object[] { paramObject });
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public static String b(String paramString)
  {
    paramString = a("android.os.SystemProperties", "get", String.class, paramString);
    if ((paramString != null) && ((paramString instanceof String))) {
      return (String)paramString;
    }
    return null;
  }
  
  public static void b(Class<?> paramClass1, Object paramObject1, String paramString, Class<?> paramClass2, Object paramObject2)
  {
    try
    {
      paramClass1 = paramClass1.getDeclaredMethod(paramString, new Class[] { paramClass2 });
      paramClass1.setAccessible(true);
      paramClass1.invoke(paramObject1, new Object[] { paramObject2 });
      return;
    }
    catch (Exception paramClass1)
    {
      paramClass1.printStackTrace();
    }
  }
  
  public static void b(Object paramObject1, String paramString, Class<?> paramClass, Object paramObject2)
  {
    try
    {
      paramString = paramObject1.getClass().getDeclaredMethod(paramString, new Class[] { paramClass });
      paramString.setAccessible(true);
      paramString.invoke(paramObject1, new Object[] { paramObject2 });
      return;
    }
    catch (Exception paramObject1)
    {
      ((Exception)paramObject1).printStackTrace();
    }
  }
  
  public static void b(String paramString1, String paramString2, Class[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      Class.forName(paramString1).getDeclaredMethod(paramString2, paramArrayOfClass).invoke(null, paramArrayOfObject);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static Class<?> c(String paramString)
  {
    try
    {
      paramString = Class.forName(paramString);
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return Object.class;
  }
  
  public static Object c(Object paramObject1, String paramString, Class<?> paramClass, Object paramObject2)
  {
    try
    {
      paramString = paramObject1.getClass().getDeclaredMethod(paramString, new Class[] { paramClass });
      paramString.setAccessible(true);
      paramObject1 = paramString.invoke(paramObject1, new Object[] { paramObject2 });
      return paramObject1;
    }
    catch (Exception paramObject1)
    {
      ((Exception)paramObject1).printStackTrace();
    }
    return null;
  }
  
  public static boolean d(String paramString)
  {
    return nc.d(paramString);
  }
  
  public static int e(String paramString)
  {
    try
    {
      Class localClass = Class.forName("com.android.internal.R$style");
      Object localObject = localClass.newInstance();
      int i = Integer.parseInt(localClass.getField(paramString).get(localObject).toString());
      return i;
    }
    catch (Exception paramString)
    {
      Log.e("ReflectHelper", "get getStyleId ", paramString);
    }
    return -1;
  }
  
  public static int f(String paramString)
  {
    try
    {
      Class localClass = Class.forName("com.flyme.internal.R$anim");
      Object localObject = localClass.newInstance();
      int i = Integer.parseInt(localClass.getField(paramString).get(localObject).toString());
      return i;
    }
    catch (Exception paramString)
    {
      Log.e("ReflectHelper", "get getAnimId ", paramString);
    }
    return -1;
  }
  
  public static int g(String paramString)
  {
    try
    {
      Class localClass = Class.forName("com.android.internal.R$id");
      Object localObject = localClass.newInstance();
      int i = Integer.parseInt(localClass.getField(paramString).get(localObject).toString());
      return i;
    }
    catch (Exception paramString)
    {
      Log.e("ReflectHelper", "get getInternalId ", paramString);
    }
    return -1;
  }
}

/* Location:
 * Qualified Name:     aau
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
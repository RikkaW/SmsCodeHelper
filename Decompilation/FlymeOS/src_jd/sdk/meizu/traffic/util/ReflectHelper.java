package sdk.meizu.traffic.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectHelper
{
  public static Object getField(Object paramObject, Class<?> paramClass, String paramString)
  {
    if ((paramObject == null) || (paramClass == null) || (paramString == null)) {
      throw new IllegalArgumentException("parameter can not be null!");
    }
    try
    {
      paramClass = paramClass.getDeclaredField(paramString);
      paramClass.setAccessible(true);
      paramObject = paramClass.get(paramObject);
      return paramObject;
    }
    catch (Exception paramObject)
    {
      throw new NoSuchFieldException(paramString);
    }
  }
  
  public static Object getField(Object paramObject, String paramString)
  {
    if ((paramObject == null) || (paramString == null)) {
      throw new IllegalArgumentException("parameter can not be null!");
    }
    return getFieldStepwise(paramObject, paramObject.getClass(), paramString);
  }
  
  private static Object getFieldStepwise(Object paramObject, Class<?> paramClass, String paramString)
  {
    while (paramClass != null) {
      try
      {
        Object localObject = getField(paramObject, paramClass, paramString);
        return localObject;
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        try
        {
          paramClass = paramClass.getSuperclass();
        }
        catch (Exception paramClass)
        {
          paramClass = null;
        }
      }
    }
    throw new NoSuchFieldException(paramString);
  }
  
  private static Class<?>[] getParamsTypes(Object[] paramArrayOfObject)
  {
    Class[] arrayOfClass = new Class[paramArrayOfObject.length];
    int i = 0;
    while (i < arrayOfClass.length)
    {
      arrayOfClass[i] = paramArrayOfObject[i].getClass();
      i += 1;
    }
    return arrayOfClass;
  }
  
  public static Object getStaticField(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new IllegalArgumentException("parameter can not be null!");
    }
    try
    {
      paramString1 = Class.forName(paramString1);
      return getFieldStepwise(paramString1, paramString1, paramString2);
    }
    catch (ClassNotFoundException paramString1)
    {
      throw new IllegalArgumentException("className not found");
    }
  }
  
  private static Object invoke(Class<?> paramClass, Object paramObject, String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject == null) || (paramArrayOfObject.length == 0))
    {
      paramClass = paramClass.getMethod(paramString, new Class[0]);
      paramClass.setAccessible(true);
      return paramClass.invoke(paramObject, new Object[0]);
    }
    paramClass = paramClass.getMethod(paramString, paramArrayOfClass);
    paramClass.setAccessible(true);
    return paramClass.invoke(paramObject, paramArrayOfObject);
  }
  
  private static Object invoke(Class<?> paramClass, Object paramObject, String paramString, Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject == null) || (paramArrayOfObject.length == 0))
    {
      paramClass = paramClass.getMethod(paramString, new Class[0]);
      paramClass.setAccessible(true);
      return paramClass.invoke(paramObject, new Object[0]);
    }
    paramClass = paramClass.getMethod(paramString, getParamsTypes(paramArrayOfObject));
    paramClass.setAccessible(true);
    return paramClass.invoke(paramObject, paramArrayOfObject);
  }
  
  public static Object invoke(Object paramObject, String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    return invoke(paramObject.getClass(), paramObject, paramString, paramArrayOfClass, paramArrayOfObject);
  }
  
  public static Object invoke(Object paramObject, String paramString, Object[] paramArrayOfObject)
  {
    return invoke(paramObject.getClass(), paramObject, paramString, paramArrayOfObject);
  }
  
  public static Object invokeStatic(String paramString1, String paramString2, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    paramString1 = Class.forName(paramString1);
    return invoke(paramString1, paramString1, paramString2, paramArrayOfClass, paramArrayOfObject);
  }
  
  public static Object invokeStatic(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    paramString1 = Class.forName(paramString1);
    return invoke(paramString1, paramString1, paramString2, paramArrayOfObject);
  }
  
  public static Object reflectConstructor(Class<?> paramClass, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0)) {
      return paramClass.getConstructor(paramArrayOfClass).newInstance(paramArrayOfObject);
    }
    return paramClass.getConstructor(new Class[0]).newInstance(new Object[0]);
  }
  
  public static Object reflectConstructor(Class<?> paramClass, Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0)) {
      return paramClass.getConstructor(getParamsTypes(paramArrayOfObject)).newInstance(paramArrayOfObject);
    }
    return paramClass.getConstructor(new Class[0]).newInstance(new Object[0]);
  }
  
  public static boolean setField(Object paramObject1, Class<?> paramClass, String paramString, Object paramObject2)
  {
    if ((paramObject1 == null) || (paramClass == null) || (paramString == null)) {
      throw new IllegalArgumentException("parameter can not be null!");
    }
    try
    {
      paramClass = paramClass.getDeclaredField(paramString);
      paramClass.setAccessible(true);
      paramClass.set(paramObject1, paramObject2);
      return true;
    }
    catch (Exception paramObject1) {}
    return false;
  }
  
  public static boolean setField(Object paramObject1, String paramString, Object paramObject2)
  {
    if ((paramObject1 == null) || (paramString == null)) {
      throw new IllegalArgumentException("parameter can not be null!");
    }
    return setFieldStepwise(paramObject1, paramObject1.getClass(), paramString, paramObject2);
  }
  
  private static boolean setFieldStepwise(Object paramObject1, Class<?> paramClass, String paramString, Object paramObject2)
  {
    while (paramClass != null)
    {
      if (setField(paramObject1, paramClass, paramString, paramObject2)) {
        return true;
      }
      try
      {
        paramClass = paramClass.getSuperclass();
      }
      catch (Exception paramClass)
      {
        paramClass = null;
      }
    }
    return false;
  }
  
  public static boolean setStaticField(String paramString1, String paramString2, Object paramObject)
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new IllegalArgumentException("parameter can not be null!");
    }
    try
    {
      paramString1 = Class.forName(paramString1);
      return setFieldStepwise(paramString1, paramString1, paramString2, paramObject);
    }
    catch (ClassNotFoundException paramString1)
    {
      throw new IllegalArgumentException("className not found");
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.util.ReflectHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
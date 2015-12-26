import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class akc
{
  private static Object a(Class<?> paramClass, Object paramObject, String paramString, Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject == null) || (paramArrayOfObject.length == 0))
    {
      paramClass = paramClass.getMethod(paramString, new Class[0]);
      paramClass.setAccessible(true);
      return paramClass.invoke(paramObject, new Object[0]);
    }
    paramClass = paramClass.getMethod(paramString, a(paramArrayOfObject));
    paramClass.setAccessible(true);
    return paramClass.invoke(paramObject, paramArrayOfObject);
  }
  
  public static Object a(Object paramObject, Class<?> paramClass, String paramString)
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
  
  public static Object a(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new IllegalArgumentException("parameter can not be null!");
    }
    try
    {
      paramString1 = Class.forName(paramString1);
      return b(paramString1, paramString1, paramString2);
    }
    catch (ClassNotFoundException paramString1)
    {
      throw new IllegalArgumentException("className not found");
    }
  }
  
  public static Object a(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    paramString1 = Class.forName(paramString1);
    return a(paramString1, paramString1, paramString2, paramArrayOfObject);
  }
  
  private static Class<?>[] a(Object[] paramArrayOfObject)
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
  
  private static Object b(Object paramObject, Class<?> paramClass, String paramString)
  {
    while (paramClass != null) {
      try
      {
        Object localObject = a(paramObject, paramClass, paramString);
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
}

/* Location:
 * Qualified Name:     akc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
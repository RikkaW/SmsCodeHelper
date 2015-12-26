package miui.external;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

class b
{
  private static Object a(ClassLoader paramClassLoader)
    throws NoSuchFieldException
  {
    Field[] arrayOfField;
    int j;
    int i;
    if ((paramClassLoader instanceof BaseDexClassLoader))
    {
      arrayOfField = BaseDexClassLoader.class.getDeclaredFields();
      j = arrayOfField.length;
      i = 0;
    }
    for (;;)
    {
      Object localObject;
      if (i < j)
      {
        localObject = arrayOfField[i];
        if ("dalvik.system.DexPathList".equals(((Field)localObject).getType().getName())) {
          ((Field)localObject).setAccessible(true);
        }
      }
      try
      {
        localObject = ((Field)localObject).get(paramClassLoader);
        return localObject;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        i += 1;
        continue;
        throw new NoSuchFieldException("dexPathList field not found.");
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        for (;;) {}
      }
    }
  }
  
  private static Field a(Object paramObject)
    throws NoSuchFieldException
  {
    paramObject = paramObject.getClass().getDeclaredFields();
    int j = paramObject.length;
    int i = 0;
    while (i < j)
    {
      Field localField = paramObject[i];
      Class localClass = localField.getType();
      if ((localClass.isArray()) && ("dalvik.system.DexPathList$Element".equals(localClass.getComponentType().getName())))
      {
        localField.setAccessible(true);
        return localField;
      }
      i += 1;
    }
    throw new NoSuchFieldException("dexElements field not found.");
  }
  
  private static void a(Object paramObject, File paramFile)
    throws NoSuchFieldException, IllegalAccessException
  {
    Field localField = b(paramObject);
    File[] arrayOfFile1 = (File[])localField.get(paramObject);
    File[] arrayOfFile2 = new File[arrayOfFile1.length + 1];
    arrayOfFile2[0] = paramFile;
    System.arraycopy(arrayOfFile1, 0, arrayOfFile2, 1, arrayOfFile1.length);
    localField.set(paramObject, arrayOfFile2);
  }
  
  private static void a(Object paramObject1, Object paramObject2)
    throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException
  {
    Field localField = a(paramObject1);
    Object[] arrayOfObject1 = (Object[])localField.get(paramObject1);
    Object[] arrayOfObject2 = (Object[])Array.newInstance(Class.forName("dalvik.system.DexPathList$Element"), arrayOfObject1.length + 1);
    arrayOfObject2[0] = paramObject2;
    System.arraycopy(arrayOfObject1, 0, arrayOfObject2, 1, arrayOfObject1.length);
    localField.set(paramObject1, arrayOfObject2);
  }
  
  private static Field b(Object paramObject)
    throws NoSuchFieldException
  {
    paramObject = paramObject.getClass().getDeclaredFields();
    int j = paramObject.length;
    int i = 0;
    while (i < j)
    {
      Field localField = paramObject[i];
      Class localClass = localField.getType();
      if ((localClass.isArray()) && (localClass.getComponentType() == File.class))
      {
        localField.setAccessible(true);
        return localField;
      }
      i += 1;
    }
    throw new NoSuchFieldException("nativeLibraryDirectories field not found.");
  }
  
  public static boolean load(String paramString1, String paramString2, String paramString3, ClassLoader paramClassLoader)
  {
    try
    {
      Object localObject = a(paramClassLoader);
      if (paramString2 == null) {}
      for (paramString1 = new PathClassLoader(paramString1, paramString3, paramClassLoader.getParent());; paramString1 = new DexClassLoader(paramString1, paramString2, paramString3, paramClassLoader.getParent()))
      {
        paramString1 = a(paramString1);
        a(localObject, ((Object[])(Object[])a(paramString1).get(paramString1))[0]);
        if (paramString3 == null) {
          break;
        }
        a(localObject, new File(paramString3));
        break;
      }
      return true;
    }
    catch (IllegalArgumentException paramString1) {}catch (NoSuchFieldException paramString1) {}catch (ClassNotFoundException paramString1) {}catch (IllegalAccessException paramString1) {}
  }
}

/* Location:
 * Qualified Name:     miui.external.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
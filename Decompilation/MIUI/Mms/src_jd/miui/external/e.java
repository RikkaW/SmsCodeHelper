package miui.external;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.IBinder;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

final class e
  extends Instrumentation
  implements SdkConstants
{
  private SdkConstants.SdkError m;
  
  private e(SdkConstants.SdkError paramSdkError)
  {
    m = paramSdkError;
  }
  
  private static Field a(Class<?> paramClass1, Object paramObject1, Object paramObject2, String paramString, Class<?> paramClass2)
    throws NoSuchFieldException
  {
    Field[] arrayOfField = paramClass1.getDeclaredFields();
    if ((paramObject1 != null) && (paramObject2 != null))
    {
      j = arrayOfField.length;
      i = 0;
      if (i < j)
      {
        paramClass1 = arrayOfField[i];
        paramClass1.setAccessible(true);
      }
    }
    do
    {
      try
      {
        Object localObject = paramClass1.get(paramObject1);
        if (localObject == paramObject2) {
          return paramClass1;
        }
      }
      catch (IllegalArgumentException paramClass1)
      {
        paramClass1.printStackTrace();
        i += 1;
      }
      catch (IllegalAccessException paramClass1)
      {
        for (;;)
        {
          paramClass1.printStackTrace();
        }
      }
      if (paramString != null)
      {
        j = arrayOfField.length;
        i = 0;
        while (i < j)
        {
          paramClass1 = arrayOfField[i];
          if (paramClass1.getName().equals(paramString))
          {
            paramClass1.setAccessible(true);
            return paramClass1;
          }
          i += 1;
        }
      }
      paramObject1 = null;
      paramClass1 = null;
    } while (paramClass2 != null);
    int j = arrayOfField.length;
    int i = 0;
    paramClass1 = (Class<?>)paramObject1;
    while (i < j)
    {
      paramString = arrayOfField[i];
      if (paramString.getType() != paramClass2)
      {
        paramObject1 = paramClass1;
        if (!paramString.getType().isInstance(paramClass2)) {}
      }
      else
      {
        if (paramClass1 != null) {
          break label201;
        }
        paramObject1 = paramString;
      }
      i += 1;
      paramClass1 = (Class<?>)paramObject1;
      continue;
      label201:
      throw new NoSuchFieldException("More than one matched field found: " + paramClass1.getName() + " and " + paramString.getName());
    }
    if (paramClass1 == null) {
      throw new NoSuchFieldException("No such field found of value " + paramObject2);
    }
    paramClass1.setAccessible(true);
    return paramClass1;
  }
  
  static void a(SdkConstants.SdkError paramSdkError)
  {
    try
    {
      Object localObject2 = Class.forName("android.app.ActivityThread");
      Object localObject1 = ((Class)localObject2).getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
      localObject2 = a((Class)localObject2, localObject1, (Instrumentation)((Class)localObject2).getMethod("getInstrumentation", new Class[0]).invoke(localObject1, new Object[0]), null, null);
      Instrumentation localInstrumentation = (Instrumentation)((Field)localObject2).get(localObject1);
      e locale = new e(paramSdkError);
      for (paramSdkError = Instrumentation.class; paramSdkError != null; paramSdkError = paramSdkError.getSuperclass())
      {
        Field[] arrayOfField = paramSdkError.getDeclaredFields();
        int j = arrayOfField.length;
        int i = 0;
        while (i < j)
        {
          Field localField = arrayOfField[i];
          localField.setAccessible(true);
          localField.set(locale, localField.get(localInstrumentation));
          i += 1;
        }
      }
      ((Field)localObject2).set(localObject1, locale);
      return;
    }
    catch (Exception paramSdkError)
    {
      paramSdkError.printStackTrace();
    }
  }
  
  public Activity newActivity(Class<?> paramClass, Context paramContext, IBinder paramIBinder, Application paramApplication, Intent paramIntent, ActivityInfo paramActivityInfo, CharSequence paramCharSequence, Activity paramActivity, String paramString, Object paramObject)
    throws InstantiationException, IllegalAccessException
  {
    if (!paramClass.getSimpleName().startsWith("SdkError"))
    {
      Class localClass = SdkErrorActivity.class;
      paramClass = paramIntent;
      if (paramIntent == null) {
        paramClass = new Intent();
      }
      paramClass.putExtra("com.miui.sdk.error", m);
      paramIntent = paramClass;
      paramClass = localClass;
    }
    for (;;)
    {
      return super.newActivity(paramClass, paramContext, paramIBinder, paramApplication, paramIntent, paramActivityInfo, paramCharSequence, paramActivity, paramString, paramObject);
    }
  }
  
  public Activity newActivity(ClassLoader paramClassLoader, String paramString, Intent paramIntent)
    throws InstantiationException, IllegalAccessException, ClassNotFoundException
  {
    String str = paramString;
    Object localObject = paramIntent;
    if (!paramString.startsWith("SdkError"))
    {
      str = SdkErrorActivity.class.getName();
      paramString = paramIntent;
      if (paramIntent == null) {
        paramString = new Intent();
      }
      paramString.putExtra("com.miui.sdk.error", m);
      localObject = paramString;
    }
    return super.newActivity(paramClassLoader, str, (Intent)localObject);
  }
}

/* Location:
 * Qualified Name:     miui.external.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
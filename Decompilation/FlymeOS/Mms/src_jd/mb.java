import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class mb
  implements InvocationHandler
{
  private static Class<?> a;
  private static Method b;
  private static Method c;
  private InputMethodManager d;
  private Object e;
  
  public mb() {}
  
  public mb(InputMethodManager paramInputMethodManager)
  {
    d = paramInputMethodManager;
  }
  
  public static void a()
  {
    if (a == null) {}
    try
    {
      a = Class.forName("android.view.inputmethod.InputMethodManager$InputShownChangeListener");
      b = InputMethodManager.class.getMethod("addInputShownChangeListener", new Class[] { a });
      c = InputMethodManager.class.getMethod("removeInputShownChangeListener", new Class[] { a });
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      a = null;
      b = null;
      c = null;
    }
  }
  
  private Object e()
  {
    a();
    return Proxy.newProxyInstance(a.getClassLoader(), new Class[] { a }, this);
  }
  
  public void a(InputMethodManager paramInputMethodManager)
  {
    d = paramInputMethodManager;
  }
  
  protected void a(boolean paramBoolean) {}
  
  public void b()
  {
    if (d == null) {
      return;
    }
    if (e == null) {
      e = e();
    }
    try
    {
      b.invoke(d, new Object[] { e });
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public boolean c()
  {
    try
    {
      boolean bool = ((Boolean)InputMethodManager.class.getMethod("isSoftInputShown", new Class[0]).invoke(d, new Object[0])).booleanValue();
      return bool;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return false;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        localInvocationTargetException.printStackTrace();
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        localNoSuchMethodException.printStackTrace();
      }
    }
  }
  
  public void d()
  {
    if (d == null) {
      return;
    }
    if (e == null) {
      e = e();
    }
    try
    {
      c.invoke(d, new Object[] { e });
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
  }
  
  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
  {
    boolean bool = false;
    paramMethod = paramMethod.getName();
    if ("onInputShownChanged".equals(paramMethod)) {
      a(((Boolean)paramArrayOfObject[0]).booleanValue());
    }
    do
    {
      return null;
      if ("equals".equals(paramMethod))
      {
        if (paramObject == paramArrayOfObject[0]) {
          bool = true;
        }
        return Boolean.valueOf(bool);
      }
    } while (!"toString".equals(paramMethod));
    return paramObject;
  }
}

/* Location:
 * Qualified Name:     mb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
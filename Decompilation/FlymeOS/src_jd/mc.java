import android.view.MenuItem;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class mc
  implements InvocationHandler
{
  private Object a;
  
  public mc(Object paramObject)
  {
    a = paramObject;
  }
  
  private Object a(Class<?> paramClass)
  {
    return Proxy.newProxyInstance(paramClass.getClassLoader(), new Class[] { paramClass }, this);
  }
  
  public void a()
  {
    Class localClass = aau.c("com.android.internal.view.menu.MenuBuilder$Callback");
    try
    {
      a.getClass().getDeclaredMethod("setCallback", new Class[] { localClass }).invoke(a, new Object[] { a(localClass) });
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
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
    }
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, CharSequence paramCharSequence)
  {
    try
    {
      a.getClass().getDeclaredMethod("add", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, CharSequence.class }).invoke(a, new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), paramCharSequence });
      return;
    }
    catch (NoSuchMethodException paramCharSequence)
    {
      paramCharSequence.printStackTrace();
      return;
    }
    catch (InvocationTargetException paramCharSequence)
    {
      paramCharSequence.printStackTrace();
      return;
    }
    catch (IllegalAccessException paramCharSequence)
    {
      paramCharSequence.printStackTrace();
    }
  }
  
  public void a(Object paramObject) {}
  
  public boolean a(Object paramObject, MenuItem paramMenuItem)
  {
    return true;
  }
  
  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
  {
    paramObject = null;
    paramMethod = paramMethod.getName();
    if ("onMenuItemSelected".equals(paramMethod)) {
      paramObject = Boolean.valueOf(a(paramArrayOfObject[0], (MenuItem)paramArrayOfObject[1]));
    }
    while (!"onMenuModeChange".equals(paramMethod)) {
      return paramObject;
    }
    a(paramArrayOfObject[0]);
    return null;
  }
}

/* Location:
 * Qualified Name:     mc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
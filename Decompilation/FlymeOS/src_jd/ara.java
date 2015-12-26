import android.widget.AbsListView;
import android.widget.ListView;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class ara
  implements InvocationHandler
{
  protected AbsListView a;
  private boolean b;
  
  public ara(AbsListView paramAbsListView)
  {
    a = paramAbsListView;
    b = true;
  }
  
  private Object a(Class<?> paramClass)
  {
    return Proxy.newProxyInstance(paramClass.getClassLoader(), new Class[] { paramClass }, this);
  }
  
  public boolean a()
  {
    boolean bool = true;
    if ((!b) || (!(a instanceof ListView))) {
      return false;
    }
    label100:
    for (;;)
    {
      try
      {
        Object localObject = Class.forName("android.widget.ListView$DividerPadding");
        Method localMethod = ListView.class.getMethod("setDividerPadding", new Class[] { localObject });
        bool = false;
      }
      catch (Exception localException)
      {
        try
        {
          localObject = a((Class)localObject);
          if (localObject == null) {
            break label100;
          }
          localMethod.invoke(a, new Object[] { localObject });
          return bool;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          localIllegalArgumentException.printStackTrace();
          return false;
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          localIllegalAccessException.printStackTrace();
          return false;
        }
        catch (InvocationTargetException localInvocationTargetException)
        {
          localInvocationTargetException.printStackTrace();
          return false;
        }
        localException = localException;
        localException.printStackTrace();
        return false;
      }
    }
  }
  
  public abstract int[] a(int paramInt);
  
  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
  {
    try
    {
      if ("getDividerPadding".equals(paramMethod.getName()))
      {
        paramObject = a(((Integer)paramArrayOfObject[0]).intValue());
        return paramObject;
      }
    }
    catch (Exception paramObject)
    {
      ((Exception)paramObject).printStackTrace();
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     ara
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ma
  implements InvocationHandler
{
  private EditText a;
  
  public ma() {}
  
  public ma(EditText paramEditText)
  {
    a = paramEditText;
  }
  
  private Object a(Class<?> paramClass)
  {
    return Proxy.newProxyInstance(paramClass.getClassLoader(), new Class[] { paramClass }, this);
  }
  
  public void a(EditText paramEditText)
  {
    a = paramEditText;
  }
  
  public void a(boolean paramBoolean)
  {
    try
    {
      Object localObject = Class.forName("android.widget.EditText$OnKeyPreImeListener");
      Method localMethod = EditText.class.getDeclaredMethod("setOnKeyPreImeListener", new Class[] { localObject });
      if (paramBoolean)
      {
        localMethod.invoke(a, new Object[] { null });
        return;
      }
      localObject = a((Class)localObject);
      if (localObject != null)
      {
        localMethod.invoke(a, new Object[] { localObject });
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  protected boolean a(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }
  
  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
  {
    paramObject = null;
    if ("onKeyPreIme".equals(paramMethod.getName())) {
      paramObject = Boolean.valueOf(a((View)paramArrayOfObject[0], ((Integer)paramArrayOfObject[1]).intValue(), (KeyEvent)paramArrayOfObject[2]));
    }
    return paramObject;
  }
}

/* Location:
 * Qualified Name:     ma
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import android.widget.TextView;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class me
  implements InvocationHandler
{
  private TextView a;
  
  public me(TextView paramTextView)
  {
    a = paramTextView;
  }
  
  public boolean a()
  {
    try
    {
      boolean bool = ((Boolean)TextView.class.getDeclaredMethod("startSelectionActionMode", new Class[0]).invoke(a, new Object[0])).booleanValue();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     me
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
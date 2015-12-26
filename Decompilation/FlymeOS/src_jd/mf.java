import android.content.Context;
import android.os.UserHandle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class mf
{
  private Object a = aau.a(aau.b("android.app.ActivityManagerNative", "getDefault"), "getCurrentUser");
  
  public mf(Context paramContext) {}
  
  public boolean a()
  {
    try
    {
      boolean bool = ((Boolean)Class.forName("android.content.pm.UserInfo").getDeclaredMethod("isGuest", new Class[0]).invoke(a, new Object[0])).booleanValue();
      return bool;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
      return false;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        localNoSuchMethodException.printStackTrace();
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        localInvocationTargetException.printStackTrace();
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        localIllegalAccessException.printStackTrace();
      }
    }
    catch (NullPointerException localNullPointerException)
    {
      for (;;)
      {
        localNullPointerException.printStackTrace();
      }
    }
  }
  
  public UserHandle b()
  {
    try
    {
      UserHandle localUserHandle = (UserHandle)Class.forName("android.content.pm.UserInfo").getDeclaredMethod("getUserHandle", new Class[0]).invoke(a, new Object[0]);
      return localUserHandle;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return null;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        localInvocationTargetException.printStackTrace();
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        localClassNotFoundException.printStackTrace();
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        localNoSuchMethodException.printStackTrace();
      }
    }
    catch (NullPointerException localNullPointerException)
    {
      for (;;)
      {
        localNullPointerException.printStackTrace();
      }
    }
  }
}

/* Location:
 * Qualified Name:     mf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
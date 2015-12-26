import android.content.Context;
import android.util.Log;
import com.android.mms.model.Model;
import com.android.mms.ui.ViewInterface;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class xg
{
  public static xf a(String paramString, Context paramContext, ViewInterface paramViewInterface, Model paramModel)
  {
    String str1 = paramString;
    String str2 = paramString;
    try
    {
      if (paramString.indexOf(".") == -1)
      {
        str2 = paramString;
        str1 = "com.android.mms.ui." + paramString;
      }
      str2 = str1;
      paramString = (xf)Class.forName(str1).getConstructor(new Class[] { Context.class, ViewInterface.class, Model.class }).newInstance(new Object[] { paramContext, paramViewInterface, paramModel });
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
      Log.e("PresenterFactory", "Type not found: " + str2, paramString);
      return null;
    }
    catch (NoSuchMethodException paramString)
    {
      for (;;)
      {
        Log.e("PresenterFactory", "No such constructor.", paramString);
      }
    }
    catch (InvocationTargetException paramString)
    {
      for (;;)
      {
        Log.e("PresenterFactory", "Unexpected InvocationTargetException", paramString);
      }
    }
    catch (IllegalAccessException paramString)
    {
      for (;;)
      {
        Log.e("PresenterFactory", "Unexpected IllegalAccessException", paramString);
      }
    }
    catch (InstantiationException paramString)
    {
      for (;;)
      {
        Log.e("PresenterFactory", "Unexpected InstantiationException", paramString);
      }
    }
  }
}

/* Location:
 * Qualified Name:     xg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
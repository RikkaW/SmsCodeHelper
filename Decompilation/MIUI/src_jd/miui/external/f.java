package miui.external;

import android.util.Log;

class f
  implements SdkConstants
{
  public static Class<?> o()
    throws ClassNotFoundException
  {
    try
    {
      Class localClass1 = Class.forName("miui.core.SdkManager");
      return localClass1;
    }
    catch (ClassNotFoundException localClassNotFoundException1)
    {
      try
      {
        Class localClass2 = Class.forName("com.miui.internal.core.SdkManager");
        Log.w("miuisdk", "using legacy sdk");
        return localClass2;
      }
      catch (ClassNotFoundException localClassNotFoundException2)
      {
        Log.e("miuisdk", "no sdk found");
        throw localClassNotFoundException2;
      }
    }
  }
}

/* Location:
 * Qualified Name:     miui.external.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
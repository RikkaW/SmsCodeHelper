import android.content.Context;
import android.telephony.MzTelephonyManager;
import android.text.TextUtils;
import android.util.Log;

public class abe
{
  public static String a;
  public static String b;
  
  public static String a(int paramInt)
  {
    if (paramInt == 0)
    {
      if (a != null) {
        return a;
      }
      return "";
    }
    if (paramInt == 1)
    {
      if (b != null) {
        return b;
      }
      return "";
    }
    return "";
  }
  
  public static String a(Context paramContext, int paramInt)
  {
    return MzTelephonyManager.getScAddress(paramContext, paramInt);
  }
  
  private static void a(Context paramContext, int paramInt, boolean paramBoolean)
  {
    Log.d("SmsCenterUtils", "getSmsCenterFromSim slotId= " + paramInt);
    new abf(paramContext, paramInt, paramBoolean).start();
  }
  
  public static void a(Context paramContext, String paramString, int paramInt)
  {
    MzTelephonyManager.setScAddress(paramContext, paramString, paramInt);
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    if (!zv.a)
    {
      a(paramContext, 0, paramBoolean);
      return;
    }
    a(paramContext, 0, paramBoolean);
    a(paramContext, 1, paramBoolean);
  }
  
  public static void a(String paramString, int paramInt)
  {
    new abg(paramInt, paramString).start();
  }
  
  private static void c(String paramString, int paramInt)
  {
    if (paramInt == 0) {
      if (!TextUtils.isEmpty(paramString))
      {
        a = paramString;
        Log.e("SmsCenterUtils", "setSmsCenterNumber mSmsCenterNumber1 = " + paramString);
      }
    }
    while (paramInt != 1)
    {
      return;
      Log.e("SmsCenterUtils", "setSmsCenterNumber mSmsCenterNumber1 is null");
      return;
    }
    if (!TextUtils.isEmpty(paramString))
    {
      b = paramString;
      Log.e("SmsCenterUtils", "setSmsCenterNumber mSmsCenterNumber2 = " + paramString);
      return;
    }
    Log.e("SmsCenterUtils", "setSmsCenterNumber mSmsCenterNumber2 is null");
  }
}

/* Location:
 * Qualified Name:     abe
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
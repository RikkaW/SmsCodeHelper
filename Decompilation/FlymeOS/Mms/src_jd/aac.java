import android.content.Context;
import android.os.Build.VERSION;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.mms.MmsApp;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class aac
{
  private static Integer a = null;
  
  public static int a(int paramInt)
  {
    if (zv.a) {
      return ((Integer)aau.a("android.telephony.TelephonyManager", "getDefault", "getSimState", Integer.TYPE, Integer.valueOf(paramInt))).intValue();
    }
    return ((TelephonyManager)aau.b("android.telephony.TelephonyManager", "getDefault")).getSimState();
  }
  
  public static int a(long paramLong)
  {
    if (zv.a)
    {
      if (Build.VERSION.SDK_INT > 21) {
        return ((Integer)aau.b("android.telephony.SubscriptionManager", "getSlotId", Integer.TYPE, Integer.valueOf((int)paramLong))).intValue();
      }
      return ((Integer)aau.b("android.telephony.SubscriptionManager", "getSlotId", Long.TYPE, Long.valueOf(paramLong))).intValue();
    }
    return 0;
  }
  
  public static aac.a a(Context paramContext, int paramInt)
  {
    paramContext = aac.a.a;
    if (((Boolean)aau.a("android.telephony.TelephonyManager", "getDefault", "hasIccCard", Integer.TYPE, Integer.valueOf(paramInt))).booleanValue()) {
      if (((Integer)aau.a("android.telephony.TelephonyManager", "getDefault", "getSimState", Integer.TYPE, Integer.valueOf(paramInt))).intValue() == 5) {
        paramContext = aac.a.d;
      }
    }
    for (;;)
    {
      a("getCardState(" + paramInt + "):" + paramContext.name());
      return paramContext;
      paramContext = aac.a.c;
      continue;
      paramContext = aac.a.b;
    }
  }
  
  private static TelephonyManager a()
  {
    return (TelephonyManager)aau.b("android.telephony.TelephonyManager", "getDefault");
  }
  
  private static void a(String paramString)
  {
    Log.d("MmsTelephonyManager", paramString);
  }
  
  public static long b(int paramInt)
  {
    try
    {
      Object localObject = Class.forName("android.telephony.SubscriptionManager");
      if (Build.VERSION.SDK_INT > 21)
      {
        localObject = (int[])((Class)localObject).getDeclaredMethod("getSubId", new Class[] { Integer.TYPE }).invoke(null, new Object[] { Integer.valueOf(paramInt) });
        if ((localObject != null) && (localObject.length < 0)) {
          return Integer.valueOf(localObject[0]).longValue();
        }
      }
      else
      {
        localObject = (long[])((Class)localObject).getDeclaredMethod("getSubId", new Class[] { Integer.TYPE }).invoke(null, new Object[] { Integer.valueOf(paramInt) });
        if ((localObject != null) && (localObject.length < 0))
        {
          long l = Long.valueOf(localObject[0]).longValue();
          return l;
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return -1L;
  }
  
  public static String b(Context paramContext, int paramInt)
  {
    localObject1 = null;
    for (;;)
    {
      try
      {
        paramContext = Class.forName("android.telephony.SubscriptionManager");
        if (Build.VERSION.SDK_INT <= 21) {
          continue;
        }
        localObject2 = paramContext.getDeclaredMethod("from", new Class[] { Context.class }).invoke(null, new Object[] { MmsApp.c() });
        paramContext = paramContext.getDeclaredMethod("getActiveSubscriptionInfoForSimSlotIndex", new Class[] { Integer.TYPE }).invoke(localObject2, new Object[] { Integer.valueOf(paramInt) });
        if (paramContext == null) {
          continue;
        }
        paramContext = paramContext.getClass().getDeclaredMethod("getDisplayName", new Class[0]).invoke(paramContext, new Object[0]).toString();
      }
      catch (Exception paramContext)
      {
        Object localObject2;
        paramContext.printStackTrace();
        paramContext = (Context)localObject1;
        continue;
        paramContext = null;
        continue;
      }
      a("getCardName(" + paramInt + "):" + paramContext);
      return paramContext;
      localObject2 = (List)paramContext.getDeclaredMethod("getSubInfoUsingSlotId", new Class[] { Integer.TYPE }).invoke(null, new Object[] { Integer.valueOf(paramInt) });
      paramContext = (Context)localObject1;
      if (localObject2 != null)
      {
        paramContext = (Context)localObject1;
        if (!((List)localObject2).isEmpty()) {
          paramContext = Class.forName("android.telephony.SubInfoRecord").getDeclaredField("displayName").get(((List)localObject2).get(0)).toString();
        }
      }
    }
  }
  
  public static boolean b(long paramLong)
  {
    return paramLong > -1L;
  }
  
  public static int c(Context paramContext, int paramInt)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (zv.a) {
      return ((Integer)aau.a(TelephonyManager.class, paramContext, "getSimState", Integer.TYPE, Integer.valueOf(paramInt))).intValue();
    }
    return paramContext.getSimState();
  }
  
  public static SmsManager c(long paramLong)
  {
    try
    {
      Object localObject = Class.forName("android.telephony.SmsManager");
      if (Build.VERSION.SDK_INT > 21) {
        return (SmsManager)((Class)localObject).getDeclaredMethod("getSmsManagerForSubscriptionId", new Class[] { Integer.TYPE }).invoke(localObject, new Object[] { Integer.valueOf((int)paramLong) });
      }
      localObject = (SmsManager)((Class)localObject).getDeclaredMethod("getSmsManagerForSubscriber", new Class[] { Long.TYPE }).invoke(localObject, new Object[] { Long.valueOf(paramLong) });
      return (SmsManager)localObject;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
      return null;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return null;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
      return null;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
    }
    return null;
  }
  
  public static String d(Context paramContext, int paramInt)
  {
    if (paramInt == -10) {
      return "-10";
    }
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (zv.a)
    {
      if (Build.VERSION.SDK_INT > 21) {
        return (String)aau.a(TelephonyManager.class, paramContext, "getSubscriberId", Integer.TYPE, Integer.valueOf((int)b(paramInt)));
      }
      return (String)aau.a(TelephonyManager.class, paramContext, "getSubscriberId", Long.TYPE, Long.valueOf(b(paramInt)));
    }
    return paramContext.getSubscriberId();
  }
  
  public static boolean e(Context paramContext, int paramInt)
  {
    if (zv.a) {
      return ((Boolean)aau.a("android.telephony.TelephonyManager", "getDefault", "hasIccCard", Integer.TYPE, Integer.valueOf(paramInt))).booleanValue();
    }
    return a().hasIccCard();
  }
  
  public static enum a
  {
    private a() {}
  }
}

/* Location:
 * Qualified Name:     aac
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
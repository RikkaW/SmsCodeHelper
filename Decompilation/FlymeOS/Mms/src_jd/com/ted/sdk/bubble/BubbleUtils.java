package com.ted.sdk.bubble;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import ant;
import asd;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.message.TextMessageParser.BusinessCardItem;
import com.ted.sdk.yellow.util.LocationInfo;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class BubbleUtils
{
  private static final int[] CATEGORY_MONEY_RELATED = { 1000, 1001, 1002, 1017, 1018, 1019, 1021 };
  private static final Pattern PATTERN_BUSINESS_CARD = Pattern.compile("([.*\\n]?\\[姓名\\](.*)\n\\[(电话|号码|手机)\\](.*)$)|(姓名\\:(.*)\n(电话|号码|手机)\\:(.*)$)");
  private static final String TAG = BubbleUtils.class.getSimpleName();
  
  public static ArrayList<String> MatchingDigital(String paramString)
  {
    return (ArrayList)deeperCall(new Object[] { paramString });
  }
  
  public static void call(Context paramContext, String paramString)
  {
    deeperCall(new Object[] { paramContext, paramString });
  }
  
  private static Class<?> change2Base(Class<?> paramClass)
  {
    Object localObject;
    if (Byte.class.equals(paramClass)) {
      localObject = Byte.TYPE;
    }
    do
    {
      return (Class<?>)localObject;
      if (Short.class.equals(paramClass)) {
        return Short.TYPE;
      }
      if (Integer.class.equals(paramClass)) {
        return Integer.TYPE;
      }
      if (Long.class.equals(paramClass)) {
        return Long.TYPE;
      }
      if (Float.class.equals(paramClass)) {
        return Float.TYPE;
      }
      if (Double.class.equals(paramClass)) {
        return Double.TYPE;
      }
      if (Character.class.equals(paramClass)) {
        return Character.TYPE;
      }
      if (Boolean.class.equals(paramClass)) {
        return Boolean.TYPE;
      }
      localObject = paramClass;
    } while (!isContextClass(paramClass));
    return Context.class;
  }
  
  public static void copyVerificationCode(Context paramContext, String paramString, String[] paramArrayOfString)
  {
    deeperCall(new Object[] { paramContext, paramString, paramArrayOfString });
  }
  
  public static void courierSearch(Context paramContext, BubbleEntity paramBubbleEntity, String paramString)
  {
    deeperCall(new Object[] { paramContext, paramBubbleEntity, paramString });
  }
  
  private static Object deeperCall(Object... paramVarArgs)
  {
    int j = 0;
    String str = getMethodName();
    Class[] arrayOfClass;
    int i;
    if (paramVarArgs != null)
    {
      arrayOfClass = new Class[paramVarArgs.length];
      int k = paramVarArgs.length;
      i = 0;
      if (j < k) {}
    }
    for (;;)
    {
      try
      {
        paramVarArgs = com.ted.android.message.BubbleUtils.class.getMethod(str, arrayOfClass).invoke(null, paramVarArgs);
        return paramVarArgs;
      }
      catch (NoSuchMethodException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
        return null;
      }
      catch (IllegalArgumentException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
        return null;
      }
      catch (IllegalAccessException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
        return null;
      }
      catch (InvocationTargetException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
        return null;
      }
      arrayOfClass[i] = change2Base(paramVarArgs[j].getClass());
      j += 1;
      i += 1;
      break;
      arrayOfClass = null;
    }
  }
  
  public static String getAddressFromSmsBody(Context paramContext, String paramString)
  {
    return (String)deeperCall(new Object[] { paramContext, paramString });
  }
  
  public static String getCalanderEventUrl()
  {
    return (String)deeperCall(new Object[0]);
  }
  
  public static String getCourierNumber(String paramString)
  {
    return (String)deeperCall(new Object[] { paramString });
  }
  
  public static String getCourierNumber(String paramString, BubbleEntity paramBubbleEntity)
  {
    return (String)deeperCall(new Object[] { paramString, paramBubbleEntity });
  }
  
  private static String getMethodName()
  {
    StackTraceElement[] arrayOfStackTraceElement = new Exception().getStackTrace();
    String str2 = "";
    String str1 = str2;
    if (arrayOfStackTraceElement != null)
    {
      str1 = str2;
      if (arrayOfStackTraceElement.length > 2) {
        str1 = arrayOfStackTraceElement[2].getMethodName();
      }
    }
    return str1;
  }
  
  public static long getTimeLongFromBody(String paramString, long paramLong)
  {
    return ((Long)deeperCall(new Object[] { paramString, Long.valueOf(paramLong) })).longValue();
  }
  
  public static boolean hasUrlInText(String paramString)
  {
    return asd.a.matcher(paramString).find();
  }
  
  private static boolean isContextClass(Class<?> paramClass)
  {
    if (Context.class.equals(paramClass)) {
      return true;
    }
    if (Object.class.equals(paramClass)) {
      return false;
    }
    return isContextClass(paramClass.getSuperclass());
  }
  
  public static boolean isInWaringWhiteList(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (!TextUtils.isDigitsOnly(paramString))) {}
    while (!Pattern.compile("(^(100|101)\\d{0,5}$)|(^(11|12|95|96)\\d{1,6}$)").matcher(paramString).find()) {
      return false;
    }
    return true;
  }
  
  public static boolean isMoneyRelated(ActionBase paramActionBase)
  {
    return (buttonText.contains("立即充值")) || (buttonText.contains("在线充值")) || (buttonText.contains("缴电费")) || (buttonText.contains("缴水费")) || (buttonText.contains("缴燃气费")) || (buttonText.contains("支付宝还款"));
  }
  
  public static boolean isReminderExpired(long paramLong1, long paramLong2)
  {
    boolean bool = true;
    long l = System.currentTimeMillis();
    if ((paramLong2 < paramLong1) || (paramLong2 < l + 3600L)) {
      bool = false;
    }
    return bool;
  }
  
  public static List<String> matcherEmail(String paramString)
  {
    return (List)deeperCall(new Object[] { paramString });
  }
  
  public static boolean needWarning(int paramInt, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    while (((paramString.contains("密码")) || (paramString.contains("账号"))) && ((paramString.length() <= 20) && (!BankNumberUtils.containsBankAccount(paramString)))) {
      return false;
    }
    Log.d(TAG, "Hit needWarning logic!");
    return true;
  }
  
  public static void openApp(Context paramContext, JSONObject paramJSONObject)
  {
    deeperCall(new Object[] { paramContext, paramJSONObject });
  }
  
  public static void openDownlaodDialog(Context paramContext, String paramString1, String paramString2)
  {
    deeperCall(new Object[] { paramContext, paramString1, paramString2 });
  }
  
  public static void openMapApp(Context paramContext, String paramString)
  {
    deeperCall(new Object[] { paramContext, paramString });
  }
  
  public static void openUrl(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return;
    }
    deeperCall(new Object[] { paramContext, ant.a(paramString, LocationInfo.getInstance().getLatitude(), LocationInfo.getInstance().getLongitude()) });
  }
  
  public static TextMessageParser.BusinessCardItem parseBusinessCard(String paramString)
  {
    return (TextMessageParser.BusinessCardItem)deeperCall(new Object[] { paramString, PATTERN_BUSINESS_CARD });
  }
  
  public static void remindEvent(Context paramContext, String paramString1, String paramString2)
  {
    deeperCall(new Object[] { paramContext, paramString1, paramString2 });
  }
  
  public static void sendMail(Context paramContext, String paramString)
  {
    deeperCall(new Object[] { paramContext, paramString });
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.bubble.BubbleUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
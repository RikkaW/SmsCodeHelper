import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.meizu.statsapp.UsageStatsProxy;
import java.util.HashMap;
import java.util.Map;

public class aab
{
  public static boolean a = false;
  public static boolean b = false;
  
  public static void a(Activity paramActivity)
  {
    a(paramActivity, c(paramActivity));
  }
  
  public static void a(Context paramContext)
  {
    Log.i("MessageUsageStatsUtils", "monitorSendMessageSituation OPEN_FROM_NEW_MESSAGE = " + a + ", OPEN_FROM_LIST_ITEM_CLICK = " + b);
    if (a)
    {
      a(paramContext, "send_message_under_new_conversation", "ComposeMessageActivity");
      a = false;
    }
    while (!b) {
      return;
    }
    a(paramContext, "send_message_under_conversation", "ComposeMessageActivity");
    b = false;
  }
  
  public static void a(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (!TextUtils.isEmpty(paramString)))
    {
      a("onPageStart pageName = " + paramString);
      b(paramContext).a(paramString);
      return;
    }
    Log.e("MessageUsageStatsUtils", "onPageStart context = " + paramContext + ", pageName = " + paramString);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    a(paramContext, paramString1, paramString2, "");
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if ((paramContext != null) && (!TextUtils.isEmpty(paramString1)))
    {
      a("onEvent eventName = " + paramString1 + ", pageName = " + paramString2 + ", property = " + paramString3);
      b(paramContext).a(paramString1, paramString2, paramString3);
      return;
    }
    Log.e("MessageUsageStatsUtils", "onEvent context = " + paramContext + ", eventName = " + paramString1);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if ((paramContext != null) && (!TextUtils.isEmpty(paramString1)))
    {
      a("onEvent eventName = " + paramString1 + ", pageName = " + paramString2 + ", key = " + paramString3 + ", property = " + paramString4);
      HashMap localHashMap = new HashMap();
      localHashMap.put(paramString3, paramString4);
      b(paramContext).a(paramString1, paramString2, localHashMap);
      return;
    }
    Log.e("MessageUsageStatsUtils", "onEvent context = " + paramContext + ", eventName = " + paramString1);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (String str = "1";; str = "0")
    {
      a(paramContext, paramString1, paramString2, str);
      return;
    }
  }
  
  private static void a(String paramString)
  {
    Log.v("MessageUsageStatsUtils", "logV:" + paramString);
  }
  
  private static UsageStatsProxy b(Context paramContext)
  {
    return UsageStatsProxy.a(paramContext);
  }
  
  public static void b(Activity paramActivity)
  {
    b(paramActivity, c(paramActivity));
  }
  
  public static void b(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (!TextUtils.isEmpty(paramString)))
    {
      a("onPageStop pageName = " + paramString);
      b(paramContext).b(paramString);
      return;
    }
    Log.e("MessageUsageStatsUtils", "onPageStop context = " + paramContext + ", pageName = " + paramString);
  }
  
  public static String c(Activity paramActivity)
  {
    if (paramActivity != null) {
      return paramActivity.getClass().getSimpleName();
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     aab
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.ui.notification.MessageItem;
import cn.com.xy.sms.sdk.ui.popu.util.ContentUtil;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class cj
{
  private static NotificationManager a = null;
  
  public static int a(Long paramLong)
  {
    if (paramLong == null) {
      return 0;
    }
    return paramLong.intValue();
  }
  
  private static NotificationManager a(Context paramContext)
  {
    if (a == null) {
      a = (NotificationManager)paramContext.getSystemService("notification");
    }
    return a;
  }
  
  public static Drawable a(Context paramContext, String paramString)
  {
    if (!StringUtils.isNull(paramString))
    {
      paramString = DuoquUtils.getSdkDoAction().getDrawableByNumber(paramContext, paramString, null);
      if (paramString != null) {
        return paramString;
      }
    }
    return paramContext.getResources().getDrawable(br.c.duoqu_default_logo);
  }
  
  public static RemoteViews a(Context paramContext, long paramLong1, String paramString1, String paramString2, long paramLong2, Map<String, Object> paramMap, Map<String, String> paramMap1)
  {
    return a(paramContext, paramLong1, paramString1, paramString2, paramLong2, paramMap, paramMap1, true);
  }
  
  private static RemoteViews a(Context paramContext, long paramLong1, String paramString1, String paramString2, long paramLong2, Map<String, Object> paramMap, Map<String, String> paramMap1, boolean paramBoolean)
  {
    if ((paramBoolean) && (!d(paramContext, paramLong1, paramString1, paramString2, paramLong2, paramMap, paramMap1))) {
      return null;
    }
    String str2 = (String)paramMap.get("view_content_title");
    String str1 = (String)paramMap.get("view_content_text");
    if (StringUtils.isNull(str2)) {
      return null;
    }
    if (StringUtils.isNull(str1)) {
      str1 = paramString2.trim();
    }
    for (;;)
    {
      LogManager.i("getPopupNotificationView", "mTitle---" + str2 + ", mText---" + str1);
      paramString2 = a(a(Long.valueOf(paramLong1)), paramString1, paramString2, paramLong2, paramMap, paramMap1);
      paramMap1 = new cl();
      RemoteViews localRemoteViews = paramMap1.a(paramContext);
      paramMap1.a(paramContext, ((BitmapDrawable)a(paramContext, paramString1)).getBitmap(), str2, str1, a(paramMap), paramString2);
      return localRemoteViews;
    }
  }
  
  private static MessageItem a(int paramInt, String paramString1, String paramString2, long paramLong, Map<String, Object> paramMap, Map<String, String> paramMap1)
  {
    MessageItem localMessageItem = new MessageItem();
    a = paramInt;
    b = paramString1;
    c = paramString2;
    d = paramMap1;
    e = paramMap;
    return localMessageItem;
  }
  
  private static JSONObject a(Map<String, Object> paramMap)
  {
    paramMap = (String)paramMap.get("ADACTION");
    JSONObject localJSONObject;
    for (;;)
    {
      String str;
      try
      {
        localJSONObject = new JSONObject();
        if ("zh-cn".equalsIgnoreCase(ContentUtil.getLanguage()))
        {
          localJSONObject.put("readBtn", ContentUtil.DUOQU_BUTTON_NAME_HAS_READ_ZH);
          localJSONObject.put("deleteBtn", ContentUtil.DUOQU_BUTTON_NAME_DELETE_ZH);
          localJSONObject.put("replyBtn", ContentUtil.DUOQU_BUTTON_NAME_REPLY_ZH);
          if (StringUtils.isNull(paramMap)) {
            break;
          }
          paramMap = new JSONArray(paramMap);
          if (paramMap.length() != 1) {
            break label236;
          }
          str = ContentUtil.getBtnName(paramMap.getJSONObject(0));
          paramMap = null;
          if (!StringUtils.isNull(str)) {
            localJSONObject.put("btn1", str);
          }
          if (StringUtils.isNull(paramMap)) {
            break;
          }
          localJSONObject.put("btn2", paramMap);
          break;
        }
        if ("zh-tw".equalsIgnoreCase(ContentUtil.getLanguage()))
        {
          localJSONObject.put("readBtn", ContentUtil.DUOQU_BUTTON_NAME_HAS_READ_TW);
          localJSONObject.put("deleteBtn", ContentUtil.DUOQU_BUTTON_NAME_DELETE_TW);
          localJSONObject.put("replyBtn", ContentUtil.DUOQU_BUTTON_NAME_REPLY_TW);
          continue;
        }
        localJSONObject.put("readBtn", ContentUtil.DUOQU_BUTTON_NAME_HAS_READ_EN);
      }
      catch (Exception paramMap)
      {
        LogManager.i("getButtonName", "error---" + paramMap.getMessage());
        return null;
      }
      localJSONObject.put("deleteBtn", ContentUtil.DUOQU_BUTTON_NAME_DELETE_EN);
      localJSONObject.put("replyBtn", ContentUtil.DUOQU_BUTTON_NAME_REPLY_EN);
      continue;
      label236:
      if (paramMap.length() > 1)
      {
        str = ContentUtil.getBtnName(paramMap.getJSONObject(0));
        paramMap = ContentUtil.getBtnName(paramMap.getJSONObject(1));
      }
      else
      {
        paramMap = null;
        str = null;
      }
    }
    return localJSONObject;
  }
  
  public static void a()
  {
    a = null;
  }
  
  public static void a(Context paramContext, int paramInt)
  {
    if (paramInt != 0)
    {
      LogManager.i("cancelId", "cancel_id---" + paramInt);
      a(paramContext).cancel(paramInt);
    }
  }
  
  public static void a(Context paramContext, MessageItem paramMessageItem)
  {
    DuoquUtils.getSdkDoAction().openSms(paramContext, b, d);
  }
  
  public static void a(Context paramContext, MessageItem paramMessageItem, int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("phoneNum", b);
    localHashMap.put("content", c);
    localHashMap.put("msgId", String.valueOf(a));
    LogManager.i("MyNotificationManager", "valueMap---" + localHashMap.toString());
    for (;;)
    {
      try
      {
        paramMessageItem = (String)e.get("ADACTION");
        if (!StringUtils.isNull(paramMessageItem))
        {
          paramMessageItem = new JSONArray(paramMessageItem).getJSONObject(paramInt);
          DuoquUtils.doActionContext(paramContext, (String)JsonUtil.getValueFromJsonObject(paramMessageItem, "action_data"), localHashMap);
          return;
        }
      }
      catch (Exception paramContext)
      {
        LogManager.e("MyNotificationManager", "error---" + paramContext.getMessage());
        return;
      }
      paramMessageItem = null;
    }
  }
  
  public static RemoteViews b(Context paramContext, long paramLong1, String paramString1, String paramString2, long paramLong2, Map<String, Object> paramMap, Map<String, String> paramMap1)
  {
    return b(paramContext, paramLong1, paramString1, paramString2, paramLong2, paramMap, paramMap1, true);
  }
  
  private static RemoteViews b(Context paramContext, long paramLong1, String paramString1, String paramString2, long paramLong2, Map<String, Object> paramMap, Map<String, String> paramMap1, boolean paramBoolean)
  {
    if ((paramBoolean) && (!d(paramContext, paramLong1, paramString1, paramString2, paramLong2, paramMap, paramMap1))) {
      return null;
    }
    String str2 = (String)paramMap.get("view_content_title");
    String str1 = (String)paramMap.get("view_content_text");
    if (StringUtils.isNull(str2)) {
      return null;
    }
    if (StringUtils.isNull(str1)) {
      str1 = paramString2.trim();
    }
    for (;;)
    {
      LogManager.i("getDropNotificationView", "mTitle---" + str2 + ", mText---" + str1);
      paramString2 = a(a(Long.valueOf(paramLong1)), paramString1, paramString2, paramLong2, paramMap, paramMap1);
      paramMap = new ci();
      paramMap1 = paramMap.a(paramContext);
      paramMap.a(paramContext, ((BitmapDrawable)a(paramContext, paramString1)).getBitmap(), str2, str1, null, paramString2);
      return paramMap1;
    }
  }
  
  public static RemoteViews c(Context paramContext, long paramLong1, String paramString1, String paramString2, long paramLong2, Map<String, Object> paramMap, Map<String, String> paramMap1)
  {
    return c(paramContext, paramLong1, paramString1, paramString2, paramLong2, paramMap, paramMap1, true);
  }
  
  private static RemoteViews c(Context paramContext, long paramLong1, String paramString1, String paramString2, long paramLong2, Map<String, Object> paramMap, Map<String, String> paramMap1, boolean paramBoolean)
  {
    if ((paramBoolean) && (!d(paramContext, paramLong1, paramString1, paramString2, paramLong2, paramMap, paramMap1))) {
      return null;
    }
    return e(paramContext, paramLong1, paramString1, paramString2, paramLong2, paramMap, paramMap1);
  }
  
  private static boolean d(Context paramContext, long paramLong1, String paramString1, String paramString2, long paramLong2, Map<String, Object> paramMap, Map<String, String> paramMap1)
  {
    if (paramContext == null) {}
    while ((paramLong1 == 0L) || (StringUtils.isNull(paramString1)) || (StringUtils.isNull(paramString2)) || (paramMap == null) || (paramMap.size() == 0)) {
      return false;
    }
    return true;
  }
  
  private static RemoteViews e(Context paramContext, long paramLong1, String paramString1, String paramString2, long paramLong2, Map<String, Object> paramMap, Map<String, String> paramMap1)
  {
    String str2 = (String)paramMap.get("view_content_title");
    String str1 = (String)paramMap.get("view_content_text");
    if (StringUtils.isNull(str2)) {
      return null;
    }
    if (StringUtils.isNull(str1)) {
      str1 = paramString2.trim();
    }
    for (;;)
    {
      LogManager.i("getDropNotificationView", "mTitle---" + str2 + ", mText---" + str1);
      paramString2 = a(a(Long.valueOf(paramLong1)), paramString1, paramString2, paramLong2, paramMap, paramMap1);
      paramMap1 = new ci();
      RemoteViews localRemoteViews = paramMap1.a(paramContext);
      paramMap1.a(paramContext, ((BitmapDrawable)a(paramContext, paramString1)).getBitmap(), str2, str1, a(paramMap), paramString2);
      return localRemoteViews;
    }
  }
}

/* Location:
 * Qualified Name:     cj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import cn.com.xy.sms.sdk.ui.notification.MessageItem;
import org.json.JSONObject;

public abstract class cg
{
  static int c = 100000;
  static int d = 200000;
  static int e = 300000;
  static int f = 400000;
  protected RemoteViews a = null;
  MessageItem b = null;
  private final int g = 199999;
  private final int h = 299999;
  private final int i = 399999;
  private final int j = 499999;
  
  protected int a()
  {
    return br.e.duoqu_drop_notification;
  }
  
  protected PendingIntent a(Context paramContext, int paramInt, MessageItem paramMessageItem, String paramString)
  {
    Intent localIntent = new Intent(paramString);
    localIntent.setClassName(paramContext, "cn.com.xy.sms.sdk.ui.notification.DoActionActivity");
    localIntent.putExtra("action", paramString);
    localIntent.putExtra("message", paramMessageItem);
    localIntent.addFlags(268468224);
    return PendingIntent.getActivity(paramContext, paramInt, localIntent, 134217728);
  }
  
  public RemoteViews a(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    int k = a();
    if (k != 0) {
      a = new RemoteViews(paramContext.getPackageName(), k);
    }
    return a;
  }
  
  public void a(Context paramContext, Bitmap paramBitmap, String paramString1, String paramString2, JSONObject paramJSONObject, MessageItem paramMessageItem)
  {
    if ((a == null) || (paramMessageItem == null) || (paramContext == null)) {
      return;
    }
    b = paramMessageItem;
    a(paramBitmap);
    a(paramString1);
    b(paramString2);
    a(paramContext, paramMessageItem, paramJSONObject);
  }
  
  protected void a(Context paramContext, MessageItem paramMessageItem, JSONObject paramJSONObject)
  {
    if ((paramJSONObject == null) || (paramJSONObject.length() < 1)) {}
    do
    {
      return;
      if (e == 399999) {
        e = 300000;
      }
      if (f == 499999) {
        f = 400000;
      }
      if (c == 199999) {
        c = 100000;
      }
      if (d == 299999) {
        d = 200000;
      }
      RemoteViews localRemoteViews = a;
      k = br.d.duoqu_drop_notify_layout;
      m = e;
      e = m + 1;
      localRemoteViews.setOnClickPendingIntent(k, a(paramContext, m, paramMessageItem, "com.xy.sms.ui.notification.layoutClickAction"));
      localRemoteViews = a;
      k = br.d.duoqu_drop_btn_one;
      m = f;
      f = m + 1;
      localRemoteViews.setOnClickPendingIntent(k, b(paramContext, m, paramMessageItem, "com.xy.sms.ui.notification.hasReadButtonClickAction"));
      if (paramJSONObject.length() == 3)
      {
        paramJSONObject = a;
        k = br.d.duoqu_drop_btn_two;
        m = c;
        c = m + 1;
        paramJSONObject.setOnClickPendingIntent(k, b(paramContext, m, paramMessageItem, "com.xy.sms.ui.notification.deleteButtonClickAction"));
        paramJSONObject = a;
        k = br.d.duoqu_drop_btn_three;
        m = d;
        d = m + 1;
        paramJSONObject.setOnClickPendingIntent(k, a(paramContext, m, paramMessageItem, "com.xy.sms.ui.notification.replyButtonClickAction"));
        return;
      }
      if (paramJSONObject.length() == 4)
      {
        paramJSONObject = a;
        k = br.d.duoqu_drop_btn_two;
        m = c;
        c = m + 1;
        paramJSONObject.setOnClickPendingIntent(k, b(paramContext, m, paramMessageItem, "com.xy.sms.ui.notification.deleteButtonClickAction"));
        paramJSONObject = a;
        k = br.d.duoqu_drop_btn_three;
        m = d;
        d = m + 1;
        paramJSONObject.setOnClickPendingIntent(k, a(paramContext, m, paramMessageItem, "com.xy.sms.ui.notification.fristServiceButtonClickAction"));
        return;
      }
    } while (paramJSONObject.length() != 5);
    paramJSONObject = a;
    int k = br.d.duoqu_drop_btn_two;
    int m = c;
    c = m + 1;
    paramJSONObject.setOnClickPendingIntent(k, a(paramContext, m, paramMessageItem, "com.xy.sms.ui.notification.secondServiceButtonClickAction"));
    paramJSONObject = a;
    k = br.d.duoqu_drop_btn_three;
    m = d;
    d = m + 1;
    paramJSONObject.setOnClickPendingIntent(k, a(paramContext, m, paramMessageItem, "com.xy.sms.ui.notification.fristServiceButtonClickAction"));
  }
  
  protected void a(Bitmap paramBitmap)
  {
    a.setImageViewBitmap(br.d.duoqu_drop_logo_img, paramBitmap);
  }
  
  protected void a(String paramString)
  {
    a.setTextViewText(br.d.duoqu_drop_content_title, paramString);
  }
  
  protected PendingIntent b(Context paramContext, int paramInt, MessageItem paramMessageItem, String paramString)
  {
    paramString = new Intent(paramString);
    paramString.putExtra("message", paramMessageItem);
    paramString.addFlags(268468224);
    return PendingIntent.getBroadcast(paramContext, paramInt, paramString, 134217728);
  }
  
  protected void b(String paramString)
  {
    a.setTextViewText(br.d.duoqu_drop_content_text, paramString);
  }
}

/* Location:
 * Qualified Name:     cg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
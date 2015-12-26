import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import cn.com.xy.sms.sdk.ui.notification.MessageItem;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ci
  extends cg
{
  public void a(Context paramContext, Bitmap paramBitmap, String paramString1, String paramString2, JSONObject paramJSONObject, MessageItem paramMessageItem)
  {
    super.a(paramContext, paramBitmap, paramString1, paramString2, paramJSONObject, paramMessageItem);
    if ((paramJSONObject != null) && (paramJSONObject.length() > 0)) {}
    try
    {
      a.setViewVisibility(br.d.duoqu_drop_btn_layout, 0);
      a.setTextViewText(br.d.duoqu_drop_btn_one, (String)paramJSONObject.get("readBtn"));
      if (paramJSONObject.length() == 3)
      {
        a.setViewVisibility(br.d.duoqu_drop_split_line1, 0);
        a.setViewVisibility(br.d.duoqu_drop_btn_two, 0);
        a.setTextViewText(br.d.duoqu_drop_btn_two, (String)paramJSONObject.get("deleteBtn"));
        a.setViewVisibility(br.d.duoqu_drop_split_line2, 0);
        a.setViewVisibility(br.d.duoqu_drop_btn_three, 0);
        a.setTextViewText(br.d.duoqu_drop_btn_three, (String)paramJSONObject.get("replyBtn"));
      }
      for (;;)
      {
        if ((!d.isEmpty()) && (d.containsKey("simIndex")))
        {
          paramContext = (String)d.get("simIndex");
          if (!"0".equals(paramContext)) {
            break label428;
          }
          a.setViewVisibility(br.d.duoqu_drop_sim_status, 0);
          a.setImageViewResource(br.d.duoqu_drop_sim_status, br.c.duoqu_notice_sim1_list);
        }
        return;
        if (paramJSONObject.length() != 4) {
          break;
        }
        a.setViewVisibility(br.d.duoqu_drop_split_line1, 0);
        a.setViewVisibility(br.d.duoqu_drop_btn_two, 0);
        a.setTextViewText(br.d.duoqu_drop_btn_two, (String)paramJSONObject.get("deleteBtn"));
        a.setViewVisibility(br.d.duoqu_drop_split_line2, 0);
        a.setViewVisibility(br.d.duoqu_drop_btn_three, 0);
        a.setTextViewText(br.d.duoqu_drop_btn_three, (String)paramJSONObject.get("btn1"));
      }
    }
    catch (JSONException paramContext)
    {
      label428:
      do
      {
        for (;;)
        {
          paramContext.printStackTrace();
          continue;
          if (paramJSONObject.length() == 5)
          {
            a.setViewVisibility(br.d.duoqu_drop_split_line1, 0);
            a.setViewVisibility(br.d.duoqu_drop_btn_two, 0);
            a.setTextViewText(br.d.duoqu_drop_btn_two, (String)paramJSONObject.get("btn2"));
            a.setViewVisibility(br.d.duoqu_drop_split_line2, 0);
            a.setViewVisibility(br.d.duoqu_drop_btn_three, 0);
            a.setTextViewText(br.d.duoqu_drop_btn_three, (String)paramJSONObject.get("btn1"));
          }
        }
      } while (!"1".equals(paramContext));
      a.setViewVisibility(br.d.duoqu_drop_sim_status, 0);
      a.setImageViewResource(br.d.duoqu_drop_sim_status, br.c.duoqu_notice_sim2_list);
    }
  }
}

/* Location:
 * Qualified Name:     ci
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
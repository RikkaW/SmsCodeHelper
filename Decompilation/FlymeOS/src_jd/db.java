import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ContentUtil;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.util.ParseManager;
import org.json.JSONException;
import org.json.JSONObject;

class db
  implements fd
{
  db(cw paramcw) {}
  
  public void a(JSONObject paramJSONObject)
  {
    cw.a(false);
    if (paramJSONObject == null) {}
    String str;
    do
    {
      return;
      str = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "name");
      paramJSONObject = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "spt");
      if (!StringUtils.isNull(str)) {
        ContentUtil.setText(cw.c(a), str, ContentUtil.NO_DATA);
      }
      if (!StringUtils.isNull(paramJSONObject))
      {
        ContentUtil.setText(cw.d(a), paramJSONObject + "", ContentUtil.NO_DATA);
        cw.a(a, true);
      }
    } while ((a.g == null) || (a.g.bubbleJsonObj == null));
    try
    {
      a.g.bubbleJsonObj.put("db_train_arrive_city_" + cw.a(a, a.g), str);
      a.g.bubbleJsonObj.put("db_train_arrive_time_" + cw.a(a, a.g), paramJSONObject);
      ParseManager.updateMatchCacheManager(a.g);
      return;
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     db
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
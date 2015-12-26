import android.os.Handler;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.util.SdkCallBack;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class cz
  implements SdkCallBack
{
  cz(cw paramcw, BusinessSmsMessage paramBusinessSmsMessage, int paramInt) {}
  
  public void execute(Object... paramVarArgs)
  {
    if ((paramVarArgs == null) || (paramVarArgs.length != 6) || (paramVarArgs[0] == null) || (paramVarArgs[1] == null)) {
      if (paramVarArgs == null) {}
    }
    for (;;)
    {
      try
      {
        if ((paramVarArgs.length > 0) && (paramVarArgs[0] != null) && ("offNetwork".equalsIgnoreCase(paramVarArgs[0].toString()))) {
          a.bubbleJsonObj.put("networkState", "offNetwork");
        }
        return;
      }
      catch (JSONException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
        return;
      }
      try
      {
        Object localObject = JsonUtil.getValueFromJsonObject((JSONObject)paramVarArgs[1], "station_list").toString();
        a.bubbleJsonObj.put("station_list_" + b, localObject);
        localObject = new JSONArray((String)localObject);
        a.bubbleJsonObj.put("station_list_obj_" + b, localObject);
        paramVarArgs = paramVarArgs[0].toString();
        String str = String.valueOf(c.g.getSmsId());
        if ((!StringUtils.isNull(str)) && (paramVarArgs.equals(str)))
        {
          cw.b(c).post(new da(this, (JSONArray)localObject));
          return;
        }
      }
      catch (JSONException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
      }
    }
  }
}

/* Location:
 * Qualified Name:     cz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
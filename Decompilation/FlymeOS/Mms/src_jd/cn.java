import android.os.AsyncTask;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import org.json.JSONObject;

class cn
  extends AsyncTask
{
  cn(cm paramcm, BusinessSmsMessage paramBusinessSmsMessage) {}
  
  protected BusinessSmsMessage a(Object... paramVarArgs)
  {
    BusinessSmsMessage localBusinessSmsMessage = (BusinessSmsMessage)paramVarArgs[0];
    paramVarArgs = (String)localBusinessSmsMessage.getValue("view_side_phone_num");
    Object localObject = DuoquUtils.getSdkDoAction().getContactObj(b.f, paramVarArgs);
    if (localObject == null)
    {
      localBusinessSmsMessage.putValue("smart_call_data_null", "true");
      localObject = a.getImgNameByKey("v_hd_bg");
      String str = a.getImgNameByKey("v_hd_bg_fir");
      if (!StringUtils.isNull((String)localObject)) {
        break label164;
      }
      localObject = str;
    }
    label164:
    for (;;)
    {
      localBusinessSmsMessage.putValue("smart_call_number_text_color", localObject);
      localBusinessSmsMessage.putValue("smart_call_number_text", paramVarArgs);
      localBusinessSmsMessage.putValue("smart_call_data_null", "false");
      return localBusinessSmsMessage;
      paramVarArgs = JsonUtil.getValFromJsonObject((JSONObject)localObject, "contact_type");
      if (paramVarArgs != null) {}
      for (;;)
      {
        try
        {
          paramVarArgs = (Integer)paramVarArgs;
          if ((paramVarArgs != null) && (paramVarArgs.intValue() != 0) && (1 != paramVarArgs.intValue())) {}
          paramVarArgs = (String)JsonUtil.getValFromJsonObject((JSONObject)localObject, "contact_name");
        }
        catch (Exception paramVarArgs)
        {
          paramVarArgs = Integer.valueOf(1);
        }
      }
    }
  }
  
  protected void onPostExecute(Object paramObject)
  {
    if (b.g == paramObject) {
      cm.a(b, (String)b.g.getValue("smart_call_number_text"), (String)b.g.getValue("smart_call_number_text_color"), (String)b.g.getValue("view_side_phone_num"));
    }
  }
}

/* Location:
 * Qualified Name:     cn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
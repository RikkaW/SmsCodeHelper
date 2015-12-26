package sdk.meizu.traffic;

import com.meizu.account.pay.OutTradeOrderInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class TrafficOrder
  extends OutTradeOrderInfo
{
  public TrafficOrder(JSONObject paramJSONObject)
  {
    try
    {
      if (paramJSONObject.has("p")) {
        setPartner(paramJSONObject.getString("p"));
      }
      if (paramJSONObject.has("i")) {
        setOutTrade(paramJSONObject.getString("i"));
      }
      if (paramJSONObject.has("u")) {
        setNotifyUrl(paramJSONObject.getString("u"));
      }
      if (paramJSONObject.has("s")) {
        setSign(paramJSONObject.getString("s"));
      }
      if (paramJSONObject.has("st")) {
        setSignType(paramJSONObject.getString("st"));
      }
      if (paramJSONObject.has("a")) {
        setPayAccounts(paramJSONObject.getString("a"));
      }
      if (paramJSONObject.has("sj")) {
        setSubject(paramJSONObject.getString("sj"));
      }
      if (paramJSONObject.has("ec")) {
        setExtContent(paramJSONObject.getString("ec"));
      }
      if (paramJSONObject.has("bd")) {
        setBody(paramJSONObject.getString("bd"));
      }
      if (paramJSONObject.has("tp")) {
        setTotalFee(paramJSONObject.getString("tp"));
      }
      return;
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
  }
  
  public String toString()
  {
    return "Partner:" + getPartner() + " | NotifyUrl:" + getNotifyUrl() + " | Sign:" + getSign() + " | signType:" + getSignType() + " | account:" + getPayAccounts();
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficOrder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
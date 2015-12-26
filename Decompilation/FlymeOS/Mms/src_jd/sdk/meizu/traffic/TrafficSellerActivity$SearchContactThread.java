package sdk.meizu.traffic;

import android.os.Handler;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sdk.meizu.traffic.hybird.JsCmd;
import sdk.meizu.traffic.util.ContactUtil;

public class TrafficSellerActivity$SearchContactThread
  extends Thread
{
  private String mQueryPhone = "";
  private JsCmd mSearchCallback;
  
  public TrafficSellerActivity$SearchContactThread(TrafficSellerActivity paramTrafficSellerActivity, String paramString, JsCmd paramJsCmd)
  {
    mQueryPhone = paramString;
    mSearchCallback = paramJsCmd;
  }
  
  public void run()
  {
    Object localObject = ContactUtil.searchContactsByNum(this$0, mQueryPhone);
    JSONArray localJSONArray = new JSONArray();
    try
    {
      localObject = ((ArrayList)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        Pair localPair = (Pair)((Iterator)localObject).next();
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("pName", first);
        localJSONObject.put("pNums", second);
        localJSONArray.put(localJSONObject);
      }
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      this$0.mUiHandler.post(new TrafficSellerActivity.SearchContactThread.1(this, localJSONArray));
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity.SearchContactThread
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
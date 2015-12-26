import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import cn.com.xy.sms.sdk.util.PopupUtil;
import org.json.JSONException;
import org.json.JSONObject;

class dr
  implements View.OnClickListener
{
  dr(dn paramdn, Activity paramActivity) {}
  
  public void onClick(View paramView)
  {
    try
    {
      dn.c(b).setVisibility(0);
      paramView = new JSONObject();
      paramView.put("type", "WEB_ABOUT");
      PopupUtil.startWebActivity(a, paramView, "WEB_ABOUT", "");
      b.dismiss();
      return;
    }
    catch (JSONException paramView)
    {
      paramView.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     dr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
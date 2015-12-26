import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import org.json.JSONArray;

public class df
  extends dg
{
  private int a = 0;
  
  public df(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, XyCallBack paramXyCallBack, int paramInt1, ViewGroup paramViewGroup, int paramInt2)
  {
    super(paramActivity, paramBusinessSmsMessage, paramXyCallBack, paramInt1, paramViewGroup, paramInt2);
  }
  
  public void a()
  {
    try
    {
      super.a();
      a = ViewManger.getIntDimen(Constant.getContext(), br.b.duoqu_type_split_lr_height_112);
      a("H", Integer.valueOf(a));
      a("MTPO", Integer.valueOf(-a));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void a(BusinessSmsMessage paramBusinessSmsMessage, boolean paramBoolean)
  {
    g = paramBusinessSmsMessage;
    paramBusinessSmsMessage = paramBusinessSmsMessage.getActionJsonArray();
    if ((paramBusinessSmsMessage == null) || (paramBusinessSmsMessage.length() == 0))
    {
      e.setVisibility(8);
      return;
    }
    e.setVisibility(0);
  }
}

/* Location:
 * Qualified Name:     df
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
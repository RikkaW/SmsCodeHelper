import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import java.util.HashMap;

public abstract class dg
{
  public View e;
  public Activity f;
  public BusinessSmsMessage g;
  public XyCallBack h;
  public HashMap<String, Object> i = null;
  
  public dg(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, XyCallBack paramXyCallBack, int paramInt1, ViewGroup paramViewGroup, int paramInt2)
  {
    a(paramActivity, paramBusinessSmsMessage, paramXyCallBack, paramInt1, paramViewGroup);
  }
  
  public Object a(String paramString)
  {
    if (i != null) {
      return i.get(paramString);
    }
    return null;
  }
  
  public void a() {}
  
  void a(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, XyCallBack paramXyCallBack, int paramInt, ViewGroup paramViewGroup)
  {
    f = paramActivity;
    g = paramBusinessSmsMessage;
    h = paramXyCallBack;
    e = ViewManger.createContextByLayoutId(f, paramInt, null);
  }
  
  public void a(BusinessSmsMessage paramBusinessSmsMessage, boolean paramBoolean) {}
  
  public void a(String paramString, Object paramObject)
  {
    if ((paramString != null) && (paramObject != null))
    {
      if (i == null) {
        i = new HashMap();
      }
      i.put(paramString, paramObject);
    }
  }
  
  public void d()
  {
    a();
    e();
    if (g.messageBody != null) {
      a(g, false);
    }
  }
  
  public void e() {}
}

/* Location:
 * Qualified Name:     dg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
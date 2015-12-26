import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ContentUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import java.util.Iterator;
import java.util.List;

public class di
  extends RelativeLayout
{
  public BusinessSmsMessage a;
  public ViewGroup b = null;
  private dh c = null;
  
  public di(Context paramContext)
  {
    super(paramContext);
  }
  
  public void a() {}
  
  public void a(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage)
  {
    b = this;
    b.setPadding(b.getPaddingLeft(), ViewManger.getIntDimen(paramActivity, br.b.base_margin_top), b.getPaddingRight(), b.getPaddingBottom());
  }
  
  public void a(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, XyCallBack paramXyCallBack)
  {
    a = paramBusinessSmsMessage;
    a(paramActivity, paramBusinessSmsMessage);
    c = new dh(paramActivity, paramXyCallBack, paramBusinessSmsMessage, b);
    b();
    a(paramBusinessSmsMessage);
  }
  
  public void a(Activity paramActivity, boolean paramBoolean)
  {
    c.a(paramActivity, a, paramBoolean);
  }
  
  public void a(BusinessSmsMessage paramBusinessSmsMessage)
  {
    a = paramBusinessSmsMessage;
    a();
    setBackground(a);
  }
  
  public void b()
  {
    c.a(b, this);
  }
  
  public void b(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage)
  {
    a = paramBusinessSmsMessage;
    a(paramActivity, true);
  }
  
  public void setBackground(BusinessSmsMessage paramBusinessSmsMessage)
  {
    setBackgroundResource(ContentUtil.getBackgroundResId(null));
  }
  
  public void setButtonClickAble(boolean paramBoolean)
  {
    if (c.c != null)
    {
      Iterator localIterator = c.c.iterator();
      while (localIterator.hasNext())
      {
        dg localdg = (dg)localIterator.next();
        if ((localdg instanceof co)) {
          ((co)localdg).a(paramBoolean);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     di
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
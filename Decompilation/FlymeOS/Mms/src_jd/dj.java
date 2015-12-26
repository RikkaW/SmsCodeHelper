import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout.LayoutParams;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;

public class dj
  extends di
{
  private Integer c = null;
  
  public dj(Context paramContext)
  {
    super(paramContext);
  }
  
  public void a()
  {
    a(false);
    Integer localInteger1 = (Integer)a.getExtendParamValue("duoqu_leftPadding");
    Integer localInteger2 = (Integer)a.getExtendParamValue("duoqu_topPadding");
    int i;
    if (localInteger1 == null)
    {
      i = getPaddingLeft();
      if (localInteger2 != null) {
        break label78;
      }
    }
    label78:
    for (int j = getPaddingTop();; j = localInteger2.intValue())
    {
      setPadding(i, j, getPaddingTop(), getPaddingBottom());
      c();
      return;
      i = localInteger1.intValue();
      break;
    }
  }
  
  public void a(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage)
  {
    b = this;
  }
  
  public void a(boolean paramBoolean)
  {
    c = ((Integer)a.getExtendParamValue("duoqu_bubble_view_width"));
    Object localObject;
    if (!paramBoolean)
    {
      localObject = (Integer)a.getExtendParamValue("duoqu_bg_resid");
      if (localObject == null) {
        break label49;
      }
      b.setBackgroundResource(((Integer)localObject).intValue());
    }
    label49:
    do
    {
      return;
      localObject = (Drawable)a.getExtendParamValue("duoqu_bg_drawable");
    } while (localObject == null);
    ViewUtil.setBackground(b, (Drawable)localObject);
  }
  
  public void b(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage)
  {
    if (a.messageBody == null)
    {
      Log.w("duoqu_xiaoyuan", "mBusinessSmsMessage.messageBody is null reBindData false.");
      a(paramBusinessSmsMessage);
      a(paramActivity, false);
    }
    do
    {
      return;
      super.b(paramActivity, paramBusinessSmsMessage);
      a(true);
    } while ((c == null) || (b.getLayoutParams().width == c.intValue()));
    paramActivity = b.getLayoutParams();
    width = c.intValue();
    b.setLayoutParams(paramActivity);
  }
  
  void c()
  {
    if (c != null) {}
    for (int i = c.intValue();; i = -2)
    {
      Object localObject = b.getLayoutParams();
      if (localObject == null) {
        localObject = new RelativeLayout.LayoutParams(i, -2);
      }
      for (;;)
      {
        b.setId(999999999);
        b.setLayoutParams((ViewGroup.LayoutParams)localObject);
        return;
        width = i;
      }
    }
  }
}

/* Location:
 * Qualified Name:     dj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
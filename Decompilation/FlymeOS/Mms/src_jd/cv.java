import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ContentUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ResourceCacheUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import cn.com.xy.sms.sdk.util.StringUtils;
import org.json.JSONArray;

public class cv
  extends dg
{
  private RelativeLayout a = null;
  private TextView b = null;
  
  public cv(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, XyCallBack paramXyCallBack, int paramInt1, ViewGroup paramViewGroup, int paramInt2)
  {
    super(paramActivity, paramBusinessSmsMessage, paramXyCallBack, paramInt1, paramViewGroup, paramInt2);
  }
  
  public void a()
  {
    a = ((RelativeLayout)e.findViewById(br.d.duoqu_horiz_budy_title));
    b = ((TextView)e.findViewById(br.d.duoqu_horiz_budy_titletext));
  }
  
  public void a(BusinessSmsMessage paramBusinessSmsMessage, boolean paramBoolean)
  {
    g = paramBusinessSmsMessage;
    if (paramBusinessSmsMessage == null) {
      return;
    }
    paramBusinessSmsMessage.getTableDataSize("duoqu_table_data_horiz");
    Object localObject = g.getActionJsonArray();
    if (localObject != null) {
      ((JSONArray)localObject).length();
    }
    ViewManger.setViewBg(f, a, paramBusinessSmsMessage.getImgNameByKey("v_by_bg_fir"), br.c.duoqu_budy_title_rectangle, 0);
    localObject = (String)paramBusinessSmsMessage.getValue("v_by_text_fir_l_color");
    if (!StringUtils.isNull((String)localObject)) {
      ContentUtil.textSetColor(b, (String)localObject);
    }
    for (;;)
    {
      ContentUtil.setText(b, (String)paramBusinessSmsMessage.getValue("budy_view_title_text"), ContentUtil.NO_DATA);
      return;
      b.setTextColor(ResourceCacheUtil.parseColor("#ffffff"));
    }
  }
}

/* Location:
 * Qualified Name:     cv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
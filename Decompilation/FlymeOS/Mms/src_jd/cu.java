import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import cn.com.xy.sms.sdk.ui.popu.widget.DuoquHorizLFItemTable;
import cn.com.xy.sms.sdk.util.StringUtils;
import org.json.JSONArray;

public class cu
  extends dg
{
  private DuoquHorizLFItemTable a = null;
  
  public cu(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, XyCallBack paramXyCallBack, int paramInt1, ViewGroup paramViewGroup, int paramInt2)
  {
    super(paramActivity, paramBusinessSmsMessage, paramXyCallBack, paramInt1, paramViewGroup, paramInt2);
  }
  
  private void a(String paramString, int paramInt)
  {
    String str = paramString;
    if (StringUtils.isNull(paramString)) {
      str = "#E6E6E6";
    }
    try
    {
      ViewManger.setViewBg(f, e, str, paramInt, 0);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void a()
  {
    a = ((DuoquHorizLFItemTable)e.findViewById(br.d.duoqu_horiz_tilte_list_lf));
  }
  
  public void a(int paramInt)
  {
    if (!StringUtils.isNull((String)g.getValue("v_by_bg")))
    {
      a((String)g.getValue("v_by_bg"), paramInt);
      return;
    }
    if (!StringUtils.isNull((String)g.getValue("v_by_bg_sec")))
    {
      a((String)g.getValue("v_by_bg_sec"), paramInt);
      return;
    }
    a("", paramInt);
  }
  
  public void a(BusinessSmsMessage paramBusinessSmsMessage, boolean paramBoolean)
  {
    g = paramBusinessSmsMessage;
    if ((paramBusinessSmsMessage == null) || (a == null)) {
      return;
    }
    int i = paramBusinessSmsMessage.getTableDataSize("duoqu_table_data_horiz");
    a.a(paramBusinessSmsMessage, i, "duoqu_table_data_horiz", paramBoolean);
    paramBusinessSmsMessage = g.getActionJsonArray();
    i = 0;
    if (paramBusinessSmsMessage != null) {
      i = paramBusinessSmsMessage.length();
    }
    if (i == 0)
    {
      a(br.c.duoqu_bottom_rectangle);
      return;
    }
    a(br.c.duoqu_bottom_rectangle_0);
  }
}

/* Location:
 * Qualified Name:     cu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
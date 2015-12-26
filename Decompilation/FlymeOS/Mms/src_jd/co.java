import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ContentUtil;
import cn.com.xy.sms.sdk.ui.popu.util.SimpleButtonUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class co
  extends dg
  implements View.OnClickListener
{
  HashMap<String, String> a = null;
  private boolean b = true;
  private View c;
  private View d;
  private View j = null;
  private View k = null;
  private TextView l = null;
  private TextView m = null;
  private int[] n = new int[2];
  
  public co(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, XyCallBack paramXyCallBack, int paramInt1, ViewGroup paramViewGroup, int paramInt2)
  {
    super(paramActivity, paramBusinessSmsMessage, paramXyCallBack, paramInt1, paramViewGroup, paramInt2);
  }
  
  private void a(int paramInt1, int paramInt2, int paramInt3)
  {
    ContentUtil.setViewVisibility(d, paramInt3);
    ContentUtil.setViewVisibility(j, paramInt1);
    ContentUtil.setViewVisibility(k, paramInt2);
    ContentUtil.setViewVisibility(l, paramInt1);
    ContentUtil.setViewVisibility(m, paramInt2);
    b();
    b((String)g.getValue("v_bt_bg"));
  }
  
  private void a(JSONObject paramJSONObject)
  {
    if (a == null) {
      a = new HashMap();
    }
    for (;;)
    {
      a.put("simIndex", String.valueOf(g.simIndex));
      a.put("phoneNum", g.originatingAddress);
      a.put("content", g.getMessageBody());
      a.put("viewType", String.valueOf(g.viewType));
      JsonUtil.putJsonToMap(paramJSONObject, a);
      paramJSONObject = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "action_data");
      DuoquUtils.doAction(f, paramJSONObject, a);
      return;
      a.clear();
    }
  }
  
  private void b(String paramString)
  {
    String str = paramString;
    if (StringUtils.isNull(paramString)) {
      str = "#E6E6E6";
    }
    try
    {
      ViewManger.setViewBg(f, e, str, br.c.duoqu_bottom_rectangle, 0);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void a()
  {
    n[0] = Math.round(f.getResources().getDimension(br.b.bubble_bottom_two_height));
    n[1] = Math.round(f.getResources().getDimension(br.b.bubble_bottom_two_minheight));
    c = e.findViewById(br.d.duoqu_bottom_split_line);
    d = e.findViewById(br.d.duoqu_btn_split_line);
    j = e.findViewById(br.d.duoqu_btn_1);
    k = e.findViewById(br.d.duoqu_btn_2);
    l = ((TextView)e.findViewById(br.d.duoqu_btn_text_1));
    m = ((TextView)e.findViewById(br.d.duoqu_btn_text_2));
    j.setOnClickListener(this);
    k.setOnClickListener(this);
  }
  
  public void a(BusinessSmsMessage paramBusinessSmsMessage, boolean paramBoolean)
  {
    g = paramBusinessSmsMessage;
    Object localObject = paramBusinessSmsMessage.getExtendParamValue("isClickAble");
    String str;
    if ((localObject == null) || (Boolean.valueOf(localObject.toString()).booleanValue()))
    {
      a(true);
      localObject = (String)paramBusinessSmsMessage.getValue("v_bt_text_color");
      str = paramBusinessSmsMessage.getImgNameByKey("v_hd_bg");
      paramBusinessSmsMessage = paramBusinessSmsMessage.getImgNameByKey("v_hd_bg_fir");
      if (StringUtils.isNull((String)localObject)) {
        break label100;
      }
      paramBusinessSmsMessage = (BusinessSmsMessage)localObject;
    }
    for (;;)
    {
      if (!StringUtils.isNull(paramBusinessSmsMessage))
      {
        ContentUtil.textSetColor(l, paramBusinessSmsMessage);
        ContentUtil.textSetColor(m, paramBusinessSmsMessage);
      }
      return;
      a(false);
      break;
      label100:
      if (!StringUtils.isNull(str)) {
        paramBusinessSmsMessage = str;
      }
    }
  }
  
  public void a(boolean paramBoolean)
  {
    int i = 0;
    try
    {
      JSONArray localJSONArray = g.getActionJsonArray();
      if (localJSONArray == null) {
        break label177;
      }
      i = localJSONArray.length();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    a(8, 8, 8);
    e.setVisibility(8);
    return;
    j.setClickable(paramBoolean);
    a(0, 8, 8);
    JSONObject localJSONObject1 = localException.getJSONObject(0);
    SimpleButtonUtil.setBottonValue(f, l, localJSONObject1, b, paramBoolean);
    e.setVisibility(0);
    return;
    j.setClickable(paramBoolean);
    k.setClickable(paramBoolean);
    a(0, 0, 0);
    JSONObject localJSONObject2 = localJSONObject1.getJSONObject(0);
    SimpleButtonUtil.setBottonValue(f, l, localJSONObject2, b, paramBoolean);
    localJSONObject1 = localJSONObject1.getJSONObject(1);
    SimpleButtonUtil.setBottonValue(f, m, localJSONObject1, b, paramBoolean);
    e.setVisibility(0);
    return;
    label177:
    switch (i)
    {
    }
  }
  
  public void b()
  {
    if ((e == null) || (j == null)) {}
    ViewGroup.LayoutParams localLayoutParams;
    do
    {
      do
      {
        return;
        localLayoutParams = e.getLayoutParams();
        if (j.getVisibility() != 0) {
          break;
        }
      } while (localLayoutParams == null);
      height = n[0];
      e.setLayoutParams(localLayoutParams);
      return;
    } while (localLayoutParams == null);
    height = n[1];
    e.setLayoutParams(localLayoutParams);
  }
  
  public void onClick(View paramView)
  {
    try
    {
      JSONArray localJSONArray = g.getActionJsonArray();
      if ((paramView == j) && (localJSONArray != null) && (localJSONArray.length() > 0))
      {
        a(localJSONArray.getJSONObject(0));
        return;
      }
      if ((paramView == k) && (localJSONArray != null) && (localJSONArray.length() > 1))
      {
        a(localJSONArray.getJSONObject(1));
        return;
      }
    }
    catch (Exception paramView)
    {
      paramView.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     co
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ContentUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ResourceCacheUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class cp
  extends dg
{
  private TextView A = null;
  private ImageView B = null;
  private ImageView C = null;
  private ImageView D = null;
  private List<String> E = new ArrayList();
  private JSONArray F = null;
  public View.OnClickListener a = new cq(this);
  public View.OnClickListener b = new cr(this);
  public View.OnTouchListener c = new cs(this);
  fc d = new ct(this);
  private RelativeLayout j = null;
  private TextView k = null;
  private TextView l = null;
  private TextView m = null;
  private TextView n = null;
  private ImageView o = null;
  private int p = 0;
  private int q = 0;
  private int r = 0;
  private TextView s = null;
  private TextView t = null;
  private ImageView u = null;
  private TextView v = null;
  private TextView w = null;
  private TextView x = null;
  private TextView y = null;
  private TextView z = null;
  
  public cp(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, XyCallBack paramXyCallBack, int paramInt1, ViewGroup paramViewGroup, int paramInt2)
  {
    super(paramActivity, paramBusinessSmsMessage, paramXyCallBack, paramInt1, paramViewGroup, paramInt2);
  }
  
  private void a(BusinessSmsMessage paramBusinessSmsMessage)
  {
    Object localObject = (String)paramBusinessSmsMessage.getValue("view_fight_number_list");
    if (StringUtils.isNull((String)localObject)) {
      return;
    }
    localObject = ((String)localObject).split(";");
    E = Arrays.asList((Object[])localObject);
    F = new JSONArray();
    int i = 0;
    for (;;)
    {
      if (i < localObject.length)
      {
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("num", E.get(i));
          F.put(localJSONObject);
          i += 1;
        }
        catch (JSONException localJSONException)
        {
          for (;;)
          {
            localJSONException.printStackTrace();
          }
        }
      }
    }
    if ((E != null) && (!E.isEmpty())) {
      r = E.size();
    }
    if ((E != null) && (r >= 2) && (messageBody.indexOf("单程") == -1))
    {
      u.setVisibility(0);
      u.setOnClickListener(a);
      t.setOnClickListener(a);
      return;
    }
    u.setVisibility(8);
    u.setOnClickListener(null);
    t.setOnClickListener(null);
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
  
  private void a(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
    {
      b();
      return;
    }
    ContentUtil.setText(t, (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_fight_number"), ContentUtil.NO_DATA);
    String str1 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_depart_time");
    String str2 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_depart_date");
    Object localObject1 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_transfer_place");
    Object localObject2;
    if (!StringUtils.isNull((String)localObject1))
    {
      ContentUtil.setText(A, (String)localObject1, ContentUtil.NO_DATA);
      z.setVisibility(0);
      A.setVisibility(0);
      C.setVisibility(0);
      D = C;
      b();
      localObject2 = "";
      if (!StringUtils.isNull(str2)) {
        localObject2 = "" + str2;
      }
      localObject1 = localObject2;
      if (!StringUtils.isNull(str2))
      {
        localObject1 = localObject2;
        if (!StringUtils.isNull(str1)) {
          localObject1 = (String)localObject2 + " ";
        }
      }
      localObject2 = localObject1;
      if (!StringUtils.isNull(str1)) {
        localObject2 = (String)localObject1 + str1;
      }
      ContentUtil.setText(w, (String)localObject2, ContentUtil.NO_DATA);
      str1 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_arrive_time");
      str2 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_arrive_date");
      localObject2 = "";
      if (!StringUtils.isNull(str2)) {
        localObject2 = "" + str2;
      }
      localObject1 = localObject2;
      if (!StringUtils.isNull(str2))
      {
        localObject1 = localObject2;
        if (!StringUtils.isNull(str1)) {
          localObject1 = (String)localObject2 + " ";
        }
      }
      localObject2 = localObject1;
      if (!StringUtils.isNull(str1)) {
        localObject2 = (String)localObject1 + str1;
      }
      ContentUtil.setText(y, (String)localObject2, ContentUtil.NO_DATA);
      localObject1 = j.getLayoutParams();
      height = q;
      localObject2 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_depart_city");
      str1 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_arrive_city");
      if ((!StringUtils.isNull((String)localObject2)) || (!StringUtils.isNull(str1))) {
        break label653;
      }
      k.setVisibility(8);
      l.setVisibility(8);
      height = p;
      label450:
      ContentUtil.setText(k, (String)localObject2, ContentUtil.NO_DATA);
      ContentUtil.setText(l, str1, ContentUtil.NO_DATA);
      str2 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_from_place_arr_complex");
      paramJSONObject = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_to_place_arr_complex");
      if ((!StringUtils.isNull(str2)) || (!StringUtils.isNull(paramJSONObject))) {
        break label672;
      }
      m.setVisibility(8);
      n.setVisibility(8);
      height = p;
    }
    for (;;)
    {
      ContentUtil.setText(m, str2, ContentUtil.NO_DATA);
      ContentUtil.setText(n, paramJSONObject, ContentUtil.NO_DATA);
      if ((StringUtils.isNull((String)localObject2)) && (StringUtils.isNull(str1)) && (StringUtils.isNull(str2)) && (StringUtils.isNull(paramJSONObject)))
      {
        k.setVisibility(0);
        l.setVisibility(0);
      }
      j.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      return;
      z.setVisibility(8);
      A.setVisibility(8);
      C.setVisibility(8);
      D = B;
      break;
      label653:
      k.setVisibility(0);
      l.setVisibility(0);
      break label450;
      label672:
      m.setVisibility(0);
      n.setVisibility(0);
    }
  }
  
  private void b()
  {
    int i1 = g.getTableDataSize("duoqu_table_data_horiz");
    JSONArray localJSONArray = g.getActionJsonArray();
    if (localJSONArray != null) {}
    for (int i = localJSONArray.length();; i = 0)
    {
      if ((i == 0) && (i1 == 0))
      {
        D.setVisibility(8);
        a(br.c.duoqu_bottom_rectangle);
        return;
      }
      D.setVisibility(0);
      a(br.c.duoqu_bottom_rectangle_0);
      return;
    }
  }
  
  private void b(BusinessSmsMessage paramBusinessSmsMessage)
  {
    if (StringUtils.isNull((String)paramBusinessSmsMessage.getValue("view_fight_number_list")))
    {
      b();
      return;
    }
    Object localObject = (String)paramBusinessSmsMessage.getValue("db_air_data_index");
    if (!StringUtils.isNull((String)localObject)) {}
    for (localObject = "View_air_content_" + (String)localObject;; localObject = "View_air_content_0")
    {
      localObject = (JSONObject)paramBusinessSmsMessage.getValue((String)localObject);
      if (localObject == null) {
        break;
      }
      a((JSONObject)localObject);
      paramBusinessSmsMessage = paramBusinessSmsMessage.getImgNameByKey("v_by_text_thr");
      if (StringUtils.isNull(paramBusinessSmsMessage)) {
        break;
      }
      try
      {
        ViewManger.setViewBg(Constant.getContext(), o, paramBusinessSmsMessage, 0, -1, true);
        return;
      }
      catch (Throwable paramBusinessSmsMessage)
      {
        paramBusinessSmsMessage.printStackTrace();
        return;
      }
    }
  }
  
  public void a()
  {
    p = ((int)f.getResources().getDimension(br.b.duoqu_budy_title_height));
    q = ((int)f.getResources().getDimension(br.b.duoqu_budy_title_double_height));
    j = ((RelativeLayout)e.findViewById(br.d.duoqu_horiz_air_title));
    k = ((TextView)e.findViewById(br.d.duoqu_air_depart_city));
    l = ((TextView)e.findViewById(br.d.duoqu_air_arrive_city));
    m = ((TextView)e.findViewById(br.d.duoqu_air_depart_airport));
    n = ((TextView)e.findViewById(br.d.duoqu_air_arrive_airport));
    o = ((ImageView)e.findViewById(br.d.duoqu_iv_direction));
    s = ((TextView)e.findViewById(br.d.duoqu_air_num_title));
    t = ((TextView)e.findViewById(br.d.duoqu_air_num));
    u = ((ImageView)e.findViewById(br.d.duoqu_base_select_air_place));
    v = ((TextView)e.findViewById(br.d.duoqu_departure_time_title));
    w = ((TextView)e.findViewById(br.d.duoqu_departure_time));
    x = ((TextView)e.findViewById(br.d.duoqu_arrival_time_title));
    y = ((TextView)e.findViewById(br.d.duoqu_arrival_time));
    z = ((TextView)e.findViewById(br.d.duoqu_transfer_place_title));
    A = ((TextView)e.findViewById(br.d.duoqu_transfer_place));
    B = ((ImageView)e.findViewById(br.d.duoqu_air_dotted_split_4));
    C = ((ImageView)e.findViewById(br.d.duoqu_air_dotted_split_5));
    D = B;
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
    if (paramBusinessSmsMessage == null) {
      return;
    }
    Object localObject = paramBusinessSmsMessage.getImgNameByKey("v_by_l_color");
    String str = paramBusinessSmsMessage.getImgNameByKey("v_by_r_color");
    ContentUtil.isTextSetColor(s, (String)localObject, br.a.duoqu_calls_l);
    ContentUtil.isTextSetColor(v, (String)localObject, br.a.duoqu_calls_l);
    ContentUtil.isTextSetColor(x, (String)localObject, br.a.duoqu_calls_l);
    ContentUtil.isTextSetColor(z, (String)localObject, br.a.duoqu_calls_l);
    ContentUtil.isTextSetColor(t, str, br.a.duoqu_calls_r);
    ContentUtil.isTextSetColor(w, str, br.a.duoqu_calls_r);
    ContentUtil.isTextSetColor(y, str, br.a.duoqu_calls_r);
    ContentUtil.isTextSetColor(A, str, br.a.duoqu_calls_r);
    localObject = paramBusinessSmsMessage.getImgNameByKey("v_hd_bg");
    str = paramBusinessSmsMessage.getImgNameByKey("v_hd_bg_fir");
    TextView localTextView = t;
    if (StringUtils.isNull((String)localObject)) {
      localObject = str;
    }
    for (;;)
    {
      ContentUtil.setTextColor(localTextView, (String)localObject);
      localObject = (String)paramBusinessSmsMessage.getValue("v_by_text_fir_l_color");
      if (!StringUtils.isNull((String)localObject))
      {
        ContentUtil.textSetColor(k, (String)localObject);
        label184:
        localObject = (String)paramBusinessSmsMessage.getValue("v_by_text_fir_r_color");
        if (StringUtils.isNull((String)localObject)) {
          break label343;
        }
        ContentUtil.textSetColor(l, (String)localObject);
        label210:
        localObject = (String)paramBusinessSmsMessage.getValue("v_by_text_sec_l_color");
        if (StringUtils.isNull((String)localObject)) {
          break label359;
        }
        ContentUtil.textSetColor(m, (String)localObject);
        label236:
        localObject = (String)paramBusinessSmsMessage.getValue("v_by_text_sec_r_color");
        if (StringUtils.isNull((String)localObject)) {
          break label375;
        }
        ContentUtil.textSetColor(n, (String)localObject);
        ViewManger.setViewBg(f, j, paramBusinessSmsMessage.getImgNameByKey("v_by_bg_fir"), br.c.duoqu_budy_title_rectangle, 0);
        localObject = paramBusinessSmsMessage.getImgNameByKey("v_by_iconname2");
        if (StringUtils.isNull((String)localObject)) {}
      }
      try
      {
        ViewManger.setViewBg(Constant.getContext(), u, (String)localObject, 0, -1, true);
        a(paramBusinessSmsMessage);
        b(paramBusinessSmsMessage);
        return;
        continue;
        k.setTextColor(ResourceCacheUtil.parseColor("#ffffff"));
        break label184;
        label343:
        l.setTextColor(ResourceCacheUtil.parseColor("#ffffff"));
        break label210;
        label359:
        m.setTextColor(ResourceCacheUtil.parseColor("#ffffff"));
        break label236;
        label375:
        n.setTextColor(ResourceCacheUtil.parseColor("#ffffff"));
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     cp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
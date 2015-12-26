import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ContentUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import cn.com.xy.sms.sdk.util.StringUtils;
import org.json.JSONArray;

public class cm
  extends dg
{
  private ImageView A;
  private ImageView B;
  private ImageView C;
  private ImageView D;
  private ImageView E;
  private int F = 0;
  private TextView a;
  private TextView b;
  private TextView c;
  private TextView d;
  private TextView j;
  private TextView k;
  private TextView l;
  private TextView m;
  private TextView n;
  private TextView o;
  private TextView p;
  private TextView q;
  private TextView r;
  private TextView s;
  private TextView t;
  private TextView u;
  private TextView v;
  private TextView w;
  private ImageView x;
  private ImageView y;
  private ImageView z;
  
  public cm(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, XyCallBack paramXyCallBack, int paramInt1, ViewGroup paramViewGroup, int paramInt2)
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
  
  private void a(String paramString1, String paramString2, String paramString3)
  {
    if ((StringUtils.isNull(paramString1)) || (StringUtils.isNull(paramString3)) || (paramString3.equals(paramString1)))
    {
      a.setVisibility(8);
      b.setVisibility(8);
      x.setVisibility(8);
      j.setTextSize(20.0F);
      paramString1 = (String)g.getValue("v_hd_bg");
      paramString2 = (String)g.getValue("v_hd_bg_fir");
      paramString3 = j;
      if (StringUtils.isNull(paramString1)) {
        paramString1 = paramString2;
      }
      for (;;)
      {
        ContentUtil.setTextColor(paramString3, paramString1);
        return;
      }
    }
    a(a, b, null, paramString1, paramString2, true);
  }
  
  public void a()
  {
    a = ((TextView)e.findViewById(br.d.duoqu_call_username));
    b = ((TextView)e.findViewById(br.d.duoqu_call_username_title));
    c = ((TextView)e.findViewById(br.d.duoqu_number_frequency));
    d = ((TextView)e.findViewById(br.d.duoqu_comingcall_number_title));
    l = ((TextView)e.findViewById(br.d.duoqu_call_time));
    m = ((TextView)e.findViewById(br.d.duoqu_call_time_title));
    n = ((TextView)e.findViewById(br.d.duoqu_lastcall_time));
    o = ((TextView)e.findViewById(br.d.duoqu_lastcall_time_title));
    p = ((TextView)e.findViewById(br.d.duoqu_call_from));
    q = ((TextView)e.findViewById(br.d.duoqu_callfrom_time_title));
    r = ((TextView)e.findViewById(br.d.duoqu_call_state));
    s = ((TextView)e.findViewById(br.d.duoqu_call_state_title));
    t = ((TextView)e.findViewById(br.d.duoqu_calling_state));
    u = ((TextView)e.findViewById(br.d.duoqu_calling_state_title));
    v = ((TextView)e.findViewById(br.d.duoqu_callingfrom_time));
    w = ((TextView)e.findViewById(br.d.duoqu_callingfrom_time_title));
    j = ((TextView)e.findViewById(br.d.duoqu_call_number));
    k = ((TextView)e.findViewById(br.d.duoqu_call_number_title));
    x = ((ImageView)e.findViewById(br.d.duoqu_dotted_split_1));
    y = ((ImageView)e.findViewById(br.d.duoqu_dotted_split_2));
    z = ((ImageView)e.findViewById(br.d.duoqu_dotted_split_3));
    A = ((ImageView)e.findViewById(br.d.duoqu_dotted_split_4));
    B = ((ImageView)e.findViewById(br.d.duoqu_dotted_split_5));
    C = ((ImageView)e.findViewById(br.d.duoqu_dotted_split_6));
    D = ((ImageView)e.findViewById(br.d.duoqu_dotted_split_7));
    E = ((ImageView)e.findViewById(br.d.duoqu_dotted_split_8));
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
  
  public void a(TextView paramTextView1, TextView paramTextView2, ImageView paramImageView, String paramString1, String paramString2, boolean paramBoolean)
  {
    if ((paramBoolean) || ((!StringUtils.isNull(paramString1)) && (F < 4)))
    {
      F += 1;
      paramTextView1.setVisibility(0);
      paramTextView2.setVisibility(0);
      if (paramImageView != null) {
        paramImageView.setVisibility(0);
      }
      ContentUtil.setText(paramTextView1, paramString1, null);
      ContentUtil.setTextColor(paramTextView1, paramString2);
    }
    do
    {
      return;
      paramTextView1.setVisibility(8);
      paramTextView2.setVisibility(8);
    } while (paramImageView == null);
    paramImageView.setVisibility(8);
  }
  
  public void a(BusinessSmsMessage paramBusinessSmsMessage, boolean paramBoolean)
  {
    g = paramBusinessSmsMessage;
    F = 0;
    ContentUtil.setViewVisibility(e, 0);
    Object localObject = (String)paramBusinessSmsMessage.getValue("view_side_phone_num");
    String str1 = (String)paramBusinessSmsMessage.getValue("view_frequency");
    String str2 = (String)paramBusinessSmsMessage.getValue("view_call_time");
    String str3 = (String)paramBusinessSmsMessage.getValue("view_lastcall_time");
    String str4 = (String)paramBusinessSmsMessage.getValue("view_sideattribution");
    String str5 = (String)paramBusinessSmsMessage.getValue("view_ownstate");
    String str6 = (String)paramBusinessSmsMessage.getValue("view_sidestate");
    String str7 = (String)paramBusinessSmsMessage.getValue("view_calling_time");
    a(j, k, x, (String)localObject, null, false);
    a(c, d, y, str1, null, false);
    a(l, m, z, str2, null, false);
    a(n, o, A, str3, null, false);
    a(p, q, B, str4, null, false);
    a(r, s, C, str5, null, false);
    a(t, u, D, str6, null, false);
    a(v, w, E, str7, null, false);
    localObject = g.getActionJsonArray();
    int i = 0;
    if (localObject != null) {
      i = ((JSONArray)localObject).length();
    }
    if (i == 0) {
      a(br.c.duoqu_bottom_rectangle);
    }
    for (;;)
    {
      localObject = paramBusinessSmsMessage.getImgNameByKey("v_by_l_color");
      str1 = paramBusinessSmsMessage.getImgNameByKey("v_by_r_color");
      ContentUtil.isTextSetColor(b, (String)localObject, br.a.duoqu_calls_l);
      ContentUtil.isTextSetColor(d, (String)localObject, br.a.duoqu_calls_l);
      ContentUtil.isTextSetColor(m, (String)localObject, br.a.duoqu_calls_l);
      ContentUtil.isTextSetColor(o, (String)localObject, br.a.duoqu_calls_l);
      ContentUtil.isTextSetColor(q, (String)localObject, br.a.duoqu_calls_l);
      ContentUtil.isTextSetColor(s, (String)localObject, br.a.duoqu_calls_l);
      ContentUtil.isTextSetColor(u, (String)localObject, br.a.duoqu_calls_l);
      ContentUtil.isTextSetColor(w, (String)localObject, br.a.duoqu_calls_l);
      ContentUtil.isTextSetColor(k, (String)localObject, br.a.duoqu_calls_l);
      ContentUtil.isTextSetColor(a, str1, br.a.duoqu_calls_r);
      ContentUtil.isTextSetColor(c, str1, br.a.duoqu_calls_r);
      ContentUtil.isTextSetColor(j, str1, br.a.duoqu_calls_r);
      ContentUtil.isTextSetColor(l, str1, br.a.duoqu_calls_r);
      ContentUtil.isTextSetColor(n, str1, br.a.duoqu_calls_r);
      ContentUtil.isTextSetColor(p, str1, br.a.duoqu_calls_r);
      ContentUtil.isTextSetColor(r, str1, br.a.duoqu_calls_r);
      ContentUtil.isTextSetColor(t, str1, br.a.duoqu_calls_r);
      ContentUtil.isTextSetColor(v, str1, br.a.duoqu_calls_r);
      localObject = (String)g.getValue("smart_call_number_text_color");
      if (localObject == null) {
        break;
      }
      a((String)g.getValue("smart_call_number_text"), (String)localObject, (String)g.getValue("view_side_phone_num"));
      return;
      a(br.c.duoqu_bottom_rectangle_0);
    }
    new cn(this, paramBusinessSmsMessage).execute(new Object[] { g });
  }
}

/* Location:
 * Qualified Name:     cm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
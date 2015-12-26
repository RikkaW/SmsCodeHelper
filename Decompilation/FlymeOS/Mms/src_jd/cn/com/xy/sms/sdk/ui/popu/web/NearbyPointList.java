package cn.com.xy.sms.sdk.ui.popu.web;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.d;
import br.e;
import br.f;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.action.NearbyPoint;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.StringUtils;
import dt;
import du;
import dw;
import dx;
import dy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NearbyPointList
  extends Activity
{
  private static final String a = NearbyPointList.class.getSimpleName();
  private ListView b;
  private LinearLayout c;
  private ImageView d;
  private TextView e;
  private TextView f = null;
  private TextView g = null;
  private ImageView h = null;
  private ArrayList<HashMap<String, Object>> i = new ArrayList();
  private dy j;
  private LinearLayout k;
  private LinearLayout l;
  private LinearLayout m;
  private LinearLayout n;
  private NearbyPoint o = null;
  private int p = 0;
  private int q = 0;
  private a r = null;
  private String s = null;
  private double t = -1.0D;
  private double u = -1.0D;
  private Handler v = new dx(this);
  
  private ArrayList<HashMap<String, Object>> a(String paramString)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      paramString = new JSONObject(paramString);
      p = paramString.getInt("total");
      JSONArray localJSONArray = paramString.getJSONArray("results");
      int i1 = 0;
      paramString = localArrayList;
      if (i1 < localJSONArray.length())
      {
        JSONObject localJSONObject = localJSONArray.getJSONObject(i1);
        paramString = new HashMap();
        paramString.put("name", localJSONObject.getString("name"));
        paramString.put("address", localJSONObject.getString("address"));
        if (localJSONObject.has("telephone")) {
          paramString.put("phone", localJSONObject.getString("telephone"));
        }
        for (;;)
        {
          paramString.put("distance", Integer.valueOf(localJSONObject.getJSONObject("detail_info").getInt("distance")));
          localJSONObject = localJSONObject.getJSONObject("location");
          paramString.put("longitude", Double.valueOf(localJSONObject.getDouble("lng")));
          paramString.put("latitude", Double.valueOf(localJSONObject.getDouble("lat")));
          localArrayList.add(paramString);
          i1 += 1;
          break;
          paramString.put("phone", "");
        }
      }
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString = new ArrayList();
    }
  }
  
  private void a(double paramDouble1, double paramDouble2)
  {
    if ((paramDouble1 <= 0.0D) || (paramDouble2 <= 0.0D))
    {
      a(8, 0, 8, 8);
      return;
    }
    o.sendMapQueryUrl(s, paramDouble2, paramDouble1, 0);
  }
  
  private void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    k.setVisibility(paramInt1);
    l.setVisibility(paramInt2);
    m.setVisibility(paramInt3);
    n.setVisibility(paramInt4);
    if ((paramInt2 == 0) || (paramInt3 == 0)) {
      q = 0;
    }
  }
  
  private void b(double paramDouble1, double paramDouble2)
  {
    if ((paramDouble1 > 0.0D) && (paramDouble2 > 0.0D)) {
      try
      {
        a(paramDouble1, paramDouble2);
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        LogManager.i(a, "获取经纬度失败");
        a(8, 0, 8, 8);
        return;
      }
    }
    try
    {
      DuoquUtils.getSdkDoAction().getLocation(getApplicationContext(), v);
      return;
    }
    catch (Exception localException)
    {
      LogManager.i(a, "获取经纬度失败");
      a(8, 0, 8, 8);
    }
  }
  
  private void b(String paramString)
  {
    if (StringUtils.isNull(paramString))
    {
      a(8, 0, 8, 8);
      return;
    }
    Object localObject;
    try
    {
      localObject = new JSONObject(paramString);
      if (((JSONObject)localObject).getInt("status") != 0) {
        throw new JSONException("返回结果异常");
      }
    }
    catch (Exception paramString)
    {
      a(8, 0, 8, 8);
      return;
    }
    if (((JSONObject)localObject).getInt("total") == 0) {
      throw new JSONException("检索结果数据数量为0");
    }
    paramString = a(paramString).iterator();
    while (paramString.hasNext())
    {
      localObject = (HashMap)paramString.next();
      i.add(localObject);
    }
    if (p > 10) {
      c.setVisibility(0);
    }
    j.notifyDataSetChanged();
    a(8, 8, 8, 0);
  }
  
  private void d()
  {
    a(0, 8, 8, 8);
    if (r != null)
    {
      r.isInterrupted();
      r = null;
    }
    r = new a(null);
    r.start();
  }
  
  public void a()
  {
    Window localWindow = getWindow();
    localWindow.setFlags(67108864, 67108864);
    localWindow.setFlags(134217728, 134217728);
  }
  
  void b()
  {
    h.setOnClickListener(new du(this));
    f.setOnClickListener(new dw(this));
  }
  
  @SuppressLint({"NewApi"})
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(br.e.duoqu_nearby_point_list);
    paramBundle = getActionBar();
    paramBundle.setDisplayShowCustomEnabled(true);
    paramBundle.setDisplayShowHomeEnabled(false);
    paramBundle.setDisplayOptions(16);
    RelativeLayout localRelativeLayout = (RelativeLayout)getLayoutInflater().inflate(br.e.duoqu_web_action_bar, null);
    paramBundle.setCustomView(localRelativeLayout);
    s = getIntent().getStringExtra("address");
    o = new NearbyPoint(this, v);
    k = ((LinearLayout)findViewById(br.d.duoqu_ll_nearby_point_loading));
    l = ((LinearLayout)findViewById(br.d.duoqu_ll_nearby_point_not_find));
    l.setOnClickListener(new c(null));
    m = ((LinearLayout)findViewById(br.d.duoqu_ll_nearby_point_network_lose));
    m.setOnClickListener(new c(null));
    n = ((LinearLayout)findViewById(br.d.duoqu_ll_nearby_point_list));
    b = ((ListView)findViewById(br.d.duoqu_lv_nearby_point));
    j = new dy(this, i);
    f = ((TextView)localRelativeLayout.findViewById(br.d.duoqu_header_back));
    g = ((TextView)localRelativeLayout.findViewById(br.d.duoqu_title_name));
    g.setText(s);
    h = ((ImageView)localRelativeLayout.findViewById(br.d.duoqu_header_menu));
    paramBundle = getLayoutInflater().inflate(br.e.duoqu_nearby_point_list_bottom, null);
    d = ((ImageView)paramBundle.findViewById(br.d.duoqu_iv_load_more));
    e = ((TextView)paramBundle.findViewById(br.d.duoqu_tv_load_more));
    c = ((LinearLayout)paramBundle.findViewById(br.d.duoqu_ll_load_more));
    c.setOnClickListener(new b(null));
    b.addFooterView(paramBundle);
    b.setDivider(null);
    b.setAdapter(j);
    b.setOnItemClickListener(new dt(this));
    if (Build.VERSION.SDK_INT >= 19) {
      a();
    }
    b();
    d();
  }
  
  protected void onDestroy()
  {
    v.removeCallbacksAndMessages(null);
    super.onDestroy();
  }
  
  class a
    extends Thread
  {
    private a() {}
    
    public void run()
    {
      try
      {
        Thread.sleep(200L);
        NearbyPointList.l(NearbyPointList.this).obtainMessage(4101).sendToTarget();
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException.printStackTrace();
      }
    }
  }
  
  class b
    implements View.OnClickListener
  {
    private b() {}
    
    public void onClick(View paramView)
    {
      NearbyPointList.c(NearbyPointList.this).setVisibility(8);
      NearbyPointList.d(NearbyPointList.this).setVisibility(0);
      NearbyPointList.d(NearbyPointList.this).setText(getApplication().getString(br.f.duoqu_tip_loading));
      NearbyPointList.e(NearbyPointList.this).setEnabled(false);
      NearbyPointList.f(NearbyPointList.this);
      NearbyPointList.k(NearbyPointList.this).sendMapQueryUrl(NearbyPointList.g(NearbyPointList.this), NearbyPointList.h(NearbyPointList.this), NearbyPointList.i(NearbyPointList.this), NearbyPointList.j(NearbyPointList.this));
    }
  }
  
  class c
    implements View.OnClickListener
  {
    private c() {}
    
    public void onClick(View paramView)
    {
      NearbyPointList.b(NearbyPointList.this);
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.web.NearbyPointList
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
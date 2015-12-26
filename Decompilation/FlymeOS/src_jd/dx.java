import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.ui.popu.web.NearbyPointList;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;

public class dx
  extends Handler
{
  public dx(NearbyPointList paramNearbyPointList) {}
  
  public void handleMessage(Message paramMessage)
  {
    if (a.isFinishing()) {
      return;
    }
    switch (what)
    {
    default: 
      return;
    case 4097: 
      NearbyPointList.a(a, paramMessage.getData().getString("queryResult"));
      if (NearbyPointList.m(a) > (NearbyPointList.j(a) + 1) * 10)
      {
        NearbyPointList.c(a).setVisibility(0);
        NearbyPointList.d(a).setVisibility(8);
        NearbyPointList.e(a).setEnabled(true);
        return;
      }
      NearbyPointList.e(a).setVisibility(8);
      return;
    case 4098: 
      LogManager.i(NearbyPointList.c(), "地图检索参数错误");
      NearbyPointList.a(a, 8, 0, 8, 8);
      return;
    case 4099: 
      LogManager.i(NearbyPointList.c(), "参数错误无法生成百度地图检索Url");
      NearbyPointList.a(a, 8, 0, 8, 8);
      return;
    case 4100: 
      LogManager.i(NearbyPointList.c(), "百度地图检索url请求失败");
      NearbyPointList.a(a, 8, 0, 8, 8);
      return;
    case 4102: 
      NearbyPointList.a(a, paramMessage.getData().getDouble("longitude"));
      NearbyPointList.b(a, paramMessage.getData().getDouble("latitude"));
      NearbyPointList.a(a, NearbyPointList.i(a), NearbyPointList.h(a));
      return;
    }
    if (XyUtil.checkNetWork(a.getApplicationContext(), 2) != 0)
    {
      NearbyPointList.a(a, 8, 8, 0, 8);
      return;
    }
    paramMessage = a.getIntent().getStringExtra("locationLongitude");
    String str = a.getIntent().getStringExtra("locationLatitude");
    if ((!StringUtils.isNull(paramMessage)) && (StringUtils.isNull(str)))
    {
      NearbyPointList.a(a, Double.parseDouble(paramMessage));
      NearbyPointList.b(a, Double.parseDouble(str));
    }
    for (;;)
    {
      NearbyPointList.b(a, NearbyPointList.i(a), NearbyPointList.h(a));
      return;
      NearbyPointList.a(a, -1.0D);
      NearbyPointList.b(a, -1.0D);
    }
  }
}

/* Location:
 * Qualified Name:     dx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
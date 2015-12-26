package cn.com.xy.sms.sdk.ui.popu.web;

import android.app.Application;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.f;
import cn.com.xy.sms.sdk.action.NearbyPoint;

class NearbyPointList$b
  implements View.OnClickListener
{
  private NearbyPointList$b(NearbyPointList paramNearbyPointList) {}
  
  public void onClick(View paramView)
  {
    NearbyPointList.c(a).setVisibility(8);
    NearbyPointList.d(a).setVisibility(0);
    NearbyPointList.d(a).setText(a.getApplication().getString(br.f.duoqu_tip_loading));
    NearbyPointList.e(a).setEnabled(false);
    NearbyPointList.f(a);
    NearbyPointList.k(a).sendMapQueryUrl(NearbyPointList.g(a), NearbyPointList.h(a), NearbyPointList.i(a), NearbyPointList.j(a));
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.web.NearbyPointList.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
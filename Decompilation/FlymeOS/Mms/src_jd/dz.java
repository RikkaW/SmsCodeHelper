import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import cn.com.xy.sms.sdk.ui.popu.web.SdkWebActivity;
import cn.com.xy.sms.util.ParseManager;

public class dz
  implements View.OnClickListener
{
  public dz(SdkWebActivity paramSdkWebActivity) {}
  
  public void onClick(View paramView)
  {
    paramView = new dn(a, new ea(this));
    Rect localRect = new Rect();
    a.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
    int i = top;
    int j = a.findViewById(br.d.duoqu_header).getHeight();
    int k = ViewUtil.dp2px(a, 5);
    paramView.setBackgroundDrawable(a.getResources().getDrawable(br.c.duoqu_popupwindow_menu));
    if ("1".equals(ParseManager.queryDefService(a)))
    {
      paramView.b().setVisibility(0);
      paramView.a().setVisibility(0);
    }
    for (;;)
    {
      paramView.showAtLocation(a.findViewById(br.d.duoqu_webview), 53, k, i + j);
      return;
      paramView.b().setVisibility(8);
      paramView.a().setVisibility(8);
    }
  }
}

/* Location:
 * Qualified Name:     dz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;

class cx
  implements View.OnClickListener
{
  cx(cw paramcw) {}
  
  public void onClick(View paramView)
  {
    paramView = new ex(a.b, a.g, cw.a(a), a.f, br.g.ShareDialog, "请选择车次");
    Window localWindow = paramView.getWindow();
    int i = ViewUtil.dp2px(a.f, 18);
    localWindow.getDecorView().setPadding(i, 0, i, 0);
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    width = -1;
    localWindow.setAttributes(localLayoutParams);
    paramView.show();
  }
}

/* Location:
 * Qualified Name:     cx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
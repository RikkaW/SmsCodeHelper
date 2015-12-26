import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import cn.com.xy.sms.sdk.ui.popu.util.ResourceCacheUtil;

class cs
  implements View.OnTouchListener
{
  cs(cp paramcp) {}
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0) {
      paramView.setBackgroundColor(ResourceCacheUtil.parseColor("#264a51"));
    }
    if (paramMotionEvent.getAction() == 1) {
      paramView.setBackgroundColor(ResourceCacheUtil.parseColor("#15175d6c"));
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     cs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
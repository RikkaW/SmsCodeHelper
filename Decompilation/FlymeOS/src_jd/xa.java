import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import com.android.mms.ui.NotificationReply;

public class xa
  implements View.OnAttachStateChangeListener
{
  public xa(NotificationReply paramNotificationReply) {}
  
  public void onViewAttachedToWindow(View paramView)
  {
    aau.b(View.class, NotificationReply.c(a), "blurUnderMeRect", Boolean.TYPE, Boolean.valueOf(true));
    aau.b(View.class, NotificationReply.c(a), "setBlurIntensity", Float.TYPE, Float.valueOf(0.6F));
  }
  
  public void onViewDetachedFromWindow(View paramView) {}
}

/* Location:
 * Qualified Name:     xa
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
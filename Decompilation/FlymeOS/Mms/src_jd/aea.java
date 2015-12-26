import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.widget.ProgressBar;
import com.android.mms.view.MessageListItemTalk;

public class aea
  implements ValueAnimator.AnimatorUpdateListener
{
  public aea(MessageListItemTalk paramMessageListItemTalk, int paramInt) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    if (b.J != null)
    {
      b.J.setMax(a);
      int i = Integer.parseInt(paramValueAnimator.getAnimatedValue().toString());
      b.J.setProgress(i);
    }
  }
}

/* Location:
 * Qualified Name:     aea
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
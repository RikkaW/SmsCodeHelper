import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;
import com.android.mms.view.MessageListItemBase;

public class adn
  extends AnimatorListenerAdapter
{
  public adn(MessageListItemBase paramMessageListItemBase) {}
  
  public void onAnimationCancel(Animator paramAnimator)
  {
    a.w.setHasTransientState(false);
    a.ah = false;
  }
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    a.w.setHasTransientState(false);
    a.ah = false;
  }
  
  public void onAnimationStart(Animator paramAnimator)
  {
    a.w.setHasTransientState(true);
    a.ah = true;
  }
}

/* Location:
 * Qualified Name:     adn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.widget.ListView;
import com.android.mms.fragmentstyle.ConversationListFragment;

public class jk
  implements Animator.AnimatorListener
{
  public jk(ConversationListFragment paramConversationListFragment) {}
  
  public void onAnimationCancel(Animator paramAnimator) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    if (ConversationListFragment.b(a) != null)
    {
      ConversationListFragment.b(a).setTranslationY(0.0F);
      ConversationListFragment.b(a).removeHeaderView(ConversationListFragment.x(a));
      ConversationListFragment.b(a).requestLayout();
    }
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator) {}
}

/* Location:
 * Qualified Name:     jk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
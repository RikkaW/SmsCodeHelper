import android.os.Handler;
import com.android.mms.MmsApp.g;
import com.android.mms.fragmentstyle.ConversationListFragment;

public class jf
  implements MmsApp.g
{
  public jf(ConversationListFragment paramConversationListFragment) {}
  
  public void a(boolean paramBoolean)
  {
    ConversationListFragment.e(a).removeCallbacks(ConversationListFragment.d(a));
    long l = System.currentTimeMillis();
    if (l - ConversationListFragment.e() > 1000L)
    {
      ConversationListFragment.e(a).post(ConversationListFragment.d(a));
      return;
    }
    ConversationListFragment.e(a).postDelayed(ConversationListFragment.d(a), ConversationListFragment.e() + 1000L - l);
  }
}

/* Location:
 * Qualified Name:     jf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
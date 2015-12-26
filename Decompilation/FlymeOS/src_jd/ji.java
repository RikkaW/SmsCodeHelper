import android.os.Handler;
import android.util.Log;
import com.android.mms.fragmentstyle.ConversationListFragment;

public class ji
  implements Runnable
{
  public ji(ConversationListFragment paramConversationListFragment) {}
  
  public void run()
  {
    if (Log.isLoggable("Mms:app", 2)) {
      fl.a("mDeleteObsoleteThreadsRunnable getSavingDraft(): " + zt.c().b(), new Object[0]);
    }
    if (zt.c().b())
    {
      fl.a("mDeleteObsoleteThreadsRunnable saving draft, trying again", new Object[0]);
      ConversationListFragment.e(a).postDelayed(ConversationListFragment.f(a), 1000L);
      return;
    }
    fl.a("mDeleteObsoleteThreadsRunnable calling asyncDeleteObsoleteThreads", new Object[0]);
    gr.a(a.a, 1803);
  }
}

/* Location:
 * Qualified Name:     ji
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
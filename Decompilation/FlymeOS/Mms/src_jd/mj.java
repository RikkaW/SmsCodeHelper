import com.android.mms.quickreply.CustomeScrollView;
import com.android.mms.quickreply.EditQuickReplyActivity;

public class mj
  extends mb
{
  public mj(EditQuickReplyActivity paramEditQuickReplyActivity) {}
  
  public void a(boolean paramBoolean)
  {
    if (EditQuickReplyActivity.e(a) == null) {
      return;
    }
    CustomeScrollView localCustomeScrollView = EditQuickReplyActivity.f(a);
    mk localmk = new mk(this, paramBoolean);
    if (paramBoolean) {}
    for (long l = 0L;; l = 200L)
    {
      localCustomeScrollView.postDelayed(localmk, l);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     mj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
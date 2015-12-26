import com.android.mms.ui.ComposeMessageActivity;

public class sd
  implements Runnable
{
  public sd(ComposeMessageActivity paramComposeMessageActivity, gm paramgm) {}
  
  public void run()
  {
    gq localgq = ComposeMessageActivity.Z(b);
    if (localgq.contains(a))
    {
      ComposeMessageActivity.a(b, localgq, false);
      if (b.b != null) {
        b.b.b();
      }
    }
  }
}

/* Location:
 * Qualified Name:     sd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.recipient.MessageRecipient;
import com.android.mms.ui.ComposeMessageActivity;
import com.meizu.commonwidget.RecipientEdit.e;
import java.util.List;

public class qi
  implements RecipientEdit.e
{
  public qi(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void a()
  {
    boolean bool2 = true;
    if (!ComposeMessageActivity.i(a))
    {
      new IllegalStateException("afterTextChanged called with invisible mRecipientsEditor");
      Log.w("Mms/compose", "RecipientsWatcher: afterTextChanged called with invisible mRecipientsEditor");
      return;
    }
    long l = System.currentTimeMillis();
    Object localObject = ComposeMessageActivity.j(a).getValidNumbers();
    if ((MmsApp.e) && (localObject != null) && (((List)localObject).size() == 1) && (((String)((List)localObject).get(0)).startsWith("@")))
    {
      wd.a(2131493694, a, 1, 1, true, 0, ComposeMessageActivity.n(a));
      ComposeMessageActivity.j(a).d();
    }
    a.c.a(ComposeMessageActivity.j(a).getValidNumbers());
    vx localvx = a.b;
    if ((localObject != null) && (((List)localObject).size() > 1))
    {
      bool1 = true;
      localvx.a(bool1);
      if (ga.a()) {
        a.s();
      }
      a.i();
      wd.a(null, "setWorkingRecipients", l);
      ComposeMessageActivity.o(a);
      ComposeMessageActivity.p(a);
      bool1 = ComposeMessageActivity.q(a);
      localObject = a;
      if (bool1) {
        break label244;
      }
    }
    label244:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      ComposeMessageActivity.d((ComposeMessageActivity)localObject, bool1);
      return;
      bool1 = false;
      break;
    }
  }
}

/* Location:
 * Qualified Name:     qi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
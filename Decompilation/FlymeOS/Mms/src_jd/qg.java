import android.os.Handler;
import com.android.mms.MmsApp;
import com.android.mms.recipient.MessageRecipient;
import com.android.mms.ui.ComposeMessageActivity;
import com.meizu.commonwidget.RecipientEdit.i;

public class qg
  implements RecipientEdit.i
{
  public qg(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void a(int paramInt)
  {
    if ((MmsApp.a) && (ComposeMessageActivity.j(a).b())) {
      return;
    }
    new Handler().postDelayed(new qh(this, paramInt), 100L);
  }
}

/* Location:
 * Qualified Name:     qg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
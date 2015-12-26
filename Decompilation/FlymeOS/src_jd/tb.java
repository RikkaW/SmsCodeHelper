import android.net.Uri;
import android.util.Log;
import com.android.mms.ui.ComposeMessageActivity;

public class tb
  implements xv.b
{
  public tb(ComposeMessageActivity paramComposeMessageActivity, int paramInt, Uri paramUri) {}
  
  public void a(int paramInt1, String paramString, int paramInt2)
  {
    Log.i("Mms/compose", "showSendTypeChooseDialog mSelectedSlotId = " + ComposeMessageActivity.t(c) + ", selectedSlotId = " + paramInt1);
    ComposeMessageActivity.i(c, paramInt1);
    ComposeMessageActivity.c(c, paramString);
    ComposeMessageActivity.j(c, paramInt2);
    ComposeMessageActivity.b(c, a, b);
    ComposeMessageActivity.ag(c);
  }
}

/* Location:
 * Qualified Name:     tb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
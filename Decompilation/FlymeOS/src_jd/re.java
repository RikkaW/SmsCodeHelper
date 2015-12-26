import android.content.ContentUris;
import android.net.Uri;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.ui.ComposeMessageActivity;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzPduPart;
import com.meizu.android.mms.pdu.MzPduPersister;

public class re
  implements wd.d
{
  public re(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void a(MzPduPart paramMzPduPart, boolean paramBoolean, int paramInt)
  {
    if (paramMzPduPart == null)
    {
      ComposeMessageActivity.a(a, -1, 2131493152);
      return;
    }
    MzPduPersister localMzPduPersister = MzPduPersister.getPduPersister(a);
    Uri localUri = a.c.b(true);
    if (localUri == null) {
      paramInt = -1;
    }
    for (;;)
    {
      ComposeMessageActivity.a(a, paramInt, 2131493152);
      return;
      try
      {
        paramMzPduPart = localMzPduPersister.persistPart(paramMzPduPart, ContentUris.parseId(localUri), null, paramInt);
        int i = a.c.a(1, paramMzPduPart, paramBoolean);
        if (a.p()) {
          MmsApp.c().a(true);
        }
        paramInt = i;
        if (Log.isLoggable("Mms:app", 2))
        {
          ComposeMessageActivity.b("ResizeImageResultCallback: dataUri=" + paramMzPduPart);
          paramInt = i;
        }
      }
      catch (MmsException paramMzPduPart)
      {
        paramInt = -1;
      }
    }
  }
}

/* Location:
 * Qualified Name:     re
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
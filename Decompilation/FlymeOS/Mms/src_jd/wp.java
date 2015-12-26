import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzPduPersister;

final class wp
  implements Runnable
{
  wp(lr paramlr, Activity paramActivity, Uri paramUri) {}
  
  public void run()
  {
    if (a == null) {}
    try
    {
      MzPduPersister.getPduPersister(b).load(c, true);
      return;
    }
    catch (MmsException localMmsException)
    {
      Log.e("Mms", "Unable to save message for preview");
    }
  }
}

/* Location:
 * Qualified Name:     wp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
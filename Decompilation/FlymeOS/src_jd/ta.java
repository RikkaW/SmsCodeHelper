import android.net.Uri;
import android.util.Log;
import com.android.mms.ui.ComposeMessageActivity;

public class ta
  implements Runnable
{
  public ta(ComposeMessageActivity paramComposeMessageActivity, String paramString) {}
  
  public void run()
  {
    try
    {
      Uri localUri = ComposeMessageActivity.au(b).b(a);
      if (localUri != null)
      {
        ComposeMessageActivity.au(b).a(localUri);
        b.d();
        Log.d("Mms/compose", "doInBackground --> insertedUri = " + localUri + ", usrMsg = " + a);
        ComposeMessageActivity.au(b).a(a);
        return;
      }
      Log.d("Mms/compose", "doInBackground --> insertedUri = null");
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     ta
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
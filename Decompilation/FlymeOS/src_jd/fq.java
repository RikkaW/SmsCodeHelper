import android.content.ContentResolver;
import android.net.Uri;
import android.provider.Telephony.Mms;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.TempFileProvider;

public class fq
  implements Runnable
{
  public fq(MmsApp paramMmsApp) {}
  
  public void run()
  {
    Log.v("Mms", "flush temp file when MmsApp start...");
    TempFileProvider.a(a);
    Log.v("Mms", "delete garbageParts when MmsApp start...");
    a.getContentResolver().delete(Uri.withAppendedPath(Telephony.Mms.CONTENT_URI, "part"), " mid not in (select _id from pdu) ", null);
  }
}

/* Location:
 * Qualified Name:     fq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
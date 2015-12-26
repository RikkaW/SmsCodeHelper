import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.provider.Telephony.Sms;

class ng
  implements Runnable
{
  ng(nf paramnf) {}
  
  public void run()
  {
    Uri localUri = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, Long.parseLong(a.a));
    a.b.getContentResolver().delete(localUri, null, null);
    gr.a(a.b, false);
  }
}

/* Location:
 * Qualified Name:     ng
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
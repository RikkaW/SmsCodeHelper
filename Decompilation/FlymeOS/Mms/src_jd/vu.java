import android.content.ContentUris;
import android.net.Uri;
import android.provider.Telephony.Mms;
import android.util.Log;

class vu
  implements Runnable
{
  vu(vt paramvt) {}
  
  public void run()
  {
    Uri localUri = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, vt.g(a));
    fl.a("resendMmsWorker: resend MMS message slotId : " + vt.h(a) + "  " + localUri, new Object[0]);
    if (localUri == null) {
      Log.e("MessageDeliveyInfoListView", "Failed to resend message: mmsUri == null");
    }
    oh localoh = new oh(vt.c(a), localUri, vt.i(a), vt.j(a), true);
    localoh.a(vt.h(a));
    try
    {
      localoh.a(vt.k(a));
      return;
    }
    catch (Exception localException)
    {
      Log.e("MessageDeliveyInfoListView", "Failed to send message: " + localUri + ", mThreadId=" + vt.k(a), localException);
    }
  }
}

/* Location:
 * Qualified Name:     vu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
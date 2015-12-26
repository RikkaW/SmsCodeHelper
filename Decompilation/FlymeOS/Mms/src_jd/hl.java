import android.net.Uri;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.pdu.MzSendReq;

class hl
  implements Runnable
{
  hl(hb paramhb, gr paramgr, Uri paramUri, MzPduPersister paramMzPduPersister, int paramInt1, String[] paramArrayOfString, int paramInt2) {}
  
  public void run()
  {
    MzSendReq localMzSendReq = hb.a(a, null);
    hb.a(g, a, Uri.parse("file://" + b.toString()), c, localMzSendReq, d, e, false, f);
    hb.b(g, a);
  }
}

/* Location:
 * Qualified Name:     hl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
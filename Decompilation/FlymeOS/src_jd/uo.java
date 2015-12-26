import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony.Mms;
import android.provider.Telephony.MmsSms;
import android.provider.Telephony.Sms;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;

public class uo
{
  private AlertDialog a;
  private vt b;
  private Context c;
  private boolean d = true;
  private final ContentObserver e = new uq(this, new Handler());
  
  private void b()
  {
    c.getContentResolver().registerContentObserver(Telephony.Sms.CONTENT_URI, true, e);
    c.getContentResolver().registerContentObserver(Telephony.Mms.CONTENT_URI, true, e);
    c.getContentResolver().registerContentObserver(Telephony.MmsSms.CONTENT_URI, true, e);
  }
  
  private void b(Context paramContext, Bundle paramBundle)
  {
    b = new vt(paramContext);
    b.a(paramBundle);
  }
  
  private void c()
  {
    c.getContentResolver().unregisterContentObserver(e);
  }
  
  public void a()
  {
    if (!d) {
      b.a();
    }
    b();
    a.show();
  }
  
  public void a(int paramInt)
  {
    b.setSlotId(paramInt);
  }
  
  public void a(Context paramContext, Bundle paramBundle)
  {
    if (a == null)
    {
      c = paramContext;
      b(paramContext, paramBundle);
      a = new AlertDialog.Builder(paramContext, 2131624313).setTitle(2131493334).setOnDismissListener(new up(this)).setView(b).create();
      d = false;
    }
  }
}

/* Location:
 * Qualified Name:     uo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
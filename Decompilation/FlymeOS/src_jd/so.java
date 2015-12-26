import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.android.mms.ui.ComposeMessageActivity;

public class so
  implements Runnable
{
  public so(ComposeMessageActivity paramComposeMessageActivity, int paramInt, Uri paramUri) {}
  
  public void run()
  {
    ContentValues localContentValues = new ContentValues(2);
    localContentValues.put("sim_id", Integer.valueOf(a));
    localContentValues.put("sub_id", Long.valueOf(aac.b(a)));
    localContentValues.put("imsi", aac.d(c, a));
    c.getContentResolver().update(b, localContentValues, null, null);
  }
}

/* Location:
 * Qualified Name:     so
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
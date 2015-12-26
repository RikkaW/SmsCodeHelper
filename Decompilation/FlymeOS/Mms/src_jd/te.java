import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.android.mms.ui.ComposeMessageActivity;

public class te
  implements Runnable
{
  public te(ComposeMessageActivity paramComposeMessageActivity, String paramString, Uri paramUri) {}
  
  public void run()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("file_link", a);
    localContentValues.put("file_link_old", ComposeMessageActivity.e(c));
    if (ComposeMessageActivity.f(c) != null) {
      localContentValues.put("pdu_media_partid", ComposeMessageActivity.f(c).getLastPathSegment());
    }
    Uri localUri = Uri.parse("content://mms/update/path/" + b.getLastPathSegment());
    ComposeMessageActivity.g(c).update(localUri, localContentValues, null, null);
  }
}

/* Location:
 * Qualified Name:     te
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
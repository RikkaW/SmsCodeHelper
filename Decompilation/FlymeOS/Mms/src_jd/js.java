import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.android.mms.fragmentstyle.FavoriteActivity;

public class js
  implements Runnable
{
  public js(FavoriteActivity paramFavoriteActivity, String paramString, Uri paramUri) {}
  
  public void run()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("file_link", a);
    localContentValues.put("file_link_old", FavoriteActivity.i(c));
    if (FavoriteActivity.j(c) != null) {
      localContentValues.put("pdu_media_partid", FavoriteActivity.j(c).getLastPathSegment());
    }
    Uri localUri = Uri.parse("content://mms/update/path/" + b.getLastPathSegment());
    c.getContentResolver().update(localUri, localContentValues, null, null);
  }
}

/* Location:
 * Qualified Name:     js
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
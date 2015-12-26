import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;
import com.android.mms.ui.MeizuSlideshowActivity;

class vq
  implements Runnable
{
  vq(vp paramvp, String paramString1, String paramString2, Uri paramUri) {}
  
  public void run()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("file_link", a);
    localContentValues.put("file_link_old", b);
    if (vp.a(d) != null) {
      localContentValues.put("pdu_media_partid", vp.a(d).getLastPathSegment());
    }
    Log.v("MeizuSlideviewDoc", "MeizuSlideviewDoc save flyme mms newPath is " + vp.b(d));
    Uri localUri = Uri.parse("content://mms/update/path/" + c.getLastPathSegment());
    vp.c(d).getContentResolver().update(localUri, localContentValues, null, null);
  }
}

/* Location:
 * Qualified Name:     vq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
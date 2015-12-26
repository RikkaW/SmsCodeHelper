import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.Telephony.Mms;
import android.text.TextUtils;
import com.meizu.android.mms.pdu.MzEncodedStringValue;
import com.meizu.android.mms.pdu.MzPduPersister;

public class zg
{
  public static String a(Context paramContext, Uri paramUri)
  {
    paramUri = paramUri.getLastPathSegment();
    Object localObject = Telephony.Mms.CONTENT_URI.buildUpon();
    ((Uri.Builder)localObject).appendPath(paramUri).appendPath("addr");
    paramUri = paramContext.getContentResolver().query(((Uri.Builder)localObject).build(), new String[] { "address", "charset" }, "type=137", null, null);
    if (paramUri != null) {}
    try
    {
      if (paramUri.moveToFirst())
      {
        localObject = paramUri.getString(0);
        if (!TextUtils.isEmpty((CharSequence)localObject))
        {
          paramContext = MzPduPersister.getBytes((String)localObject);
          paramContext = new MzEncodedStringValue(paramUri.getInt(1), paramContext).getString();
          return paramContext;
        }
      }
      return paramContext.getString(2131492969);
    }
    finally
    {
      paramUri.close();
    }
  }
}

/* Location:
 * Qualified Name:     zg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
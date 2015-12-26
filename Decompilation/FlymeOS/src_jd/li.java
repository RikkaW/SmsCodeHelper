import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.util.MzSqliteWrapper;
import java.util.HashMap;
import java.util.Map;

public class li
  extends lm
{
  private final HashMap<String, String> o = new HashMap();
  
  public li(Context paramContext, Uri paramUri)
  {
    this(paramContext, null, null, paramUri);
    a(paramContext, paramUri);
  }
  
  public li(Context paramContext, String paramString1, String paramString2, Uri paramUri)
  {
    super(paramContext, "file", paramString1, paramString2, paramUri);
  }
  
  private void b(Context paramContext, Uri paramUri)
  {
    e = paramUri.getPath();
    MimeTypeMap localMimeTypeMap = MimeTypeMap.getSingleton();
    paramUri = MimeTypeMap.getFileExtensionFromUrl(e);
    paramContext = paramUri;
    if (TextUtils.isEmpty(paramUri))
    {
      int i = e.lastIndexOf('.');
      paramContext = paramUri;
      if (i >= 0) {
        paramContext = e.substring(i + 1);
      }
    }
    g = localMimeTypeMap.getMimeTypeFromExtension(paramContext);
    f = e.substring(e.lastIndexOf('/') + 1);
    f = f.replace(' ', '_');
  }
  
  private void c(Uri paramUri)
  {
    ContentResolver localContentResolver = a.getContentResolver();
    Cursor localCursor = MzSqliteWrapper.query(a, localContentResolver, paramUri, null, null, null, null);
    if (localCursor != null)
    {
      for (;;)
      {
        try
        {
          if (!localCursor.moveToFirst()) {
            break label275;
          }
          if (b(paramUri))
          {
            e = localCursor.getString(localCursor.getColumnIndexOrThrow("_data"));
            g = localCursor.getString(localCursor.getColumnIndexOrThrow("ct"));
            f = localCursor.getString(localCursor.getColumnIndexOrThrow("fn"));
            if (!TextUtils.isEmpty(g)) {
              break label302;
            }
            throw new MmsException("Type of media is unknown.");
          }
        }
        finally
        {
          localCursor.close();
        }
        e = localCursor.getString(localCursor.getColumnIndexOrThrow("_data"));
        try
        {
          g = localCursor.getString(localCursor.getColumnIndexOrThrow("mime_type"));
          f = e.substring(e.lastIndexOf('/') + 1);
          f = f.replace(' ', '_');
        }
        catch (IllegalArgumentException localIllegalArgumentException1)
        {
          for (;;)
          {
            try
            {
              g = localCursor.getString(localCursor.getColumnIndexOrThrow("mimetype"));
            }
            catch (IllegalArgumentException localIllegalArgumentException2)
            {
              g = localContentResolver.getType(paramUri);
              Log.v("Mms/media", "initFromContentUri: " + paramUri + ", getType => " + g);
            }
          }
        }
      }
      label275:
      throw new MmsException("Nothing found: " + paramUri);
      label302:
      localCursor.close();
      return;
    }
    throw new MmsException("Bad URI: " + paramUri);
  }
  
  public Map<String, ?> a()
  {
    return o;
  }
  
  public void a(Context paramContext, Uri paramUri)
  {
    if (paramUri.getScheme().equals("content")) {
      c(paramUri);
    }
    for (;;)
    {
      f = c(f);
      return;
      if (paramUri.getScheme().equals("file")) {
        b(paramContext, paramUri);
      }
    }
  }
  
  public void a(att paramatt)
  {
    a(false);
  }
}

/* Location:
 * Qualified Name:     li
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
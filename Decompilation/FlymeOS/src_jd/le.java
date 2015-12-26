import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.google.android.mms.MmsException;
import java.util.HashMap;
import java.util.Map;

public class le
  extends lm
{
  private final HashMap<String, String> o = new HashMap();
  
  public le(Context paramContext, Uri paramUri)
  {
    this(paramContext, null, null, paramUri);
    c(paramUri);
    b();
  }
  
  public le(Context paramContext, Uri paramUri, boolean paramBoolean)
  {
    this(paramContext, null, null, paramUri);
    c(paramUri);
    if (paramBoolean) {
      b();
    }
  }
  
  public le(Context paramContext, String paramString1, String paramString2, Uri paramUri)
  {
    super(paramContext, "audio", paramString1, paramString2, paramUri);
  }
  
  private void c(Uri paramUri)
  {
    if (paramUri.getScheme().equals("content")) {
      d(paramUri);
    }
    for (;;)
    {
      f = c(f);
      x();
      return;
      if (paramUri.getScheme().equals("file")) {
        e(paramUri);
      }
    }
  }
  
  private void d(Uri paramUri)
  {
    Cursor localCursor = a.getContentResolver().query(paramUri, null, null, null, null);
    if (localCursor != null)
    {
      for (;;)
      {
        try
        {
          if (!localCursor.moveToFirst()) {
            break;
          }
          if (b(paramUri))
          {
            e = localCursor.getString(localCursor.getColumnIndexOrThrow("_data"));
            g = localCursor.getString(localCursor.getColumnIndexOrThrow("ct"));
            f = localCursor.getString(localCursor.getColumnIndexOrThrow("fn"));
            if (!TextUtils.isEmpty(g)) {
              break label288;
            }
            throw new MmsException("Type of media is unknown.");
          }
        }
        finally
        {
          localCursor.close();
        }
        e = localCursor.getString(localCursor.getColumnIndexOrThrow("_data"));
        g = localCursor.getString(localCursor.getColumnIndexOrThrow("mime_type"));
        paramUri = localCursor.getString(localCursor.getColumnIndexOrThrow("album"));
        if (!TextUtils.isEmpty(paramUri)) {
          o.put("album", paramUri);
        }
        paramUri = localCursor.getString(localCursor.getColumnIndexOrThrow("artist"));
        if (!TextUtils.isEmpty(paramUri)) {
          o.put("artist", paramUri);
        }
        f = e.substring(e.lastIndexOf('/') + 1);
        f = f.replace(' ', '_');
      }
      throw new MmsException("Nothing found: " + paramUri);
      label288:
      localCursor.close();
      return;
    }
    throw new MmsException("Bad URI: " + paramUri);
  }
  
  private void e(Uri paramUri)
  {
    paramUri.getPath();
    String str2 = paramUri.getPath();
    MimeTypeMap localMimeTypeMap = MimeTypeMap.getSingleton();
    String str1 = MimeTypeMap.getFileExtensionFromUrl(str2);
    paramUri = str1;
    if (TextUtils.isEmpty(str1))
    {
      int i = str2.lastIndexOf('.');
      paramUri = str1;
      if (i >= 0) {
        paramUri = str2.substring(i + 1);
      }
    }
    g = localMimeTypeMap.getMimeTypeFromExtension(paramUri);
    f = str2.substring(str2.lastIndexOf('/') + 1);
    f = f.replace(' ', '_');
  }
  
  public Map<String, ?> a()
  {
    return o;
  }
  
  public void a(att paramatt)
  {
    String str = paramatt.b();
    lm.a locala = lm.a.a;
    if (str.equals("SmilMediaStart"))
    {
      locala = lm.a.b;
      A();
    }
    for (;;)
    {
      a(locala);
      a(false);
      return;
      if (str.equals("SmilMediaEnd"))
      {
        locala = lm.a.c;
      }
      else if (str.equals("SmilMediaPause"))
      {
        locala = lm.a.d;
      }
      else if (str.equals("SmilMediaSeek"))
      {
        locala = lm.a.e;
        j = ((hs)paramatt).f();
      }
    }
  }
  
  protected void b()
  {
    lh.a().b(g);
  }
  
  protected boolean c()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     le
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
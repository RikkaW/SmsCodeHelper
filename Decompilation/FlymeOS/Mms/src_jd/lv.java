import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.util.MzSqliteWrapper;
import java.util.HashMap;
import java.util.Map;

public class lv
  extends lm
{
  private final HashMap<String, String> o = new HashMap();
  private String p;
  
  public lv(Context paramContext, Uri paramUri)
  {
    this(paramContext, null, null, paramUri);
    if (paramUri.getScheme().equals("content")) {
      c(paramUri);
    }
    for (;;)
    {
      f = c(f);
      return;
      if (paramUri.getScheme().equals("file")) {
        a(paramContext, paramUri);
      }
    }
  }
  
  public lv(Context paramContext, String paramString1, String paramString2, Uri paramUri)
  {
    super(paramContext, "ref", paramString1, paramString2, paramUri);
  }
  
  private void a(Context paramContext, Uri paramUri)
  {
    p = paramUri.getPath();
    p = paramUri.getPath();
    MimeTypeMap localMimeTypeMap = MimeTypeMap.getSingleton();
    paramUri = MimeTypeMap.getFileExtensionFromUrl(p);
    paramContext = paramUri;
    if (TextUtils.isEmpty(paramUri))
    {
      int i = p.lastIndexOf('.');
      paramContext = paramUri;
      if (i >= 0) {
        paramContext = p.substring(i + 1);
      }
    }
    g = localMimeTypeMap.getMimeTypeFromExtension(paramContext);
    f = p.substring(p.lastIndexOf('/') + 1);
    f = f.replace(' ', '_');
  }
  
  private void c(Uri paramUri)
  {
    Object localObject = a.getContentResolver();
    localObject = MzSqliteWrapper.query(a, (ContentResolver)localObject, paramUri, null, null, null, null);
    if (localObject != null)
    {
      for (;;)
      {
        try
        {
          if (!((Cursor)localObject).moveToFirst()) {
            break;
          }
          if (b(paramUri))
          {
            p = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndexOrThrow("_data"));
            g = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndexOrThrow("ct"));
            f = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndexOrThrow("fn"));
            if (!TextUtils.isEmpty(g)) {
              break label228;
            }
            throw new MmsException("Type of media is unknown.");
          }
        }
        finally
        {
          ((Cursor)localObject).close();
        }
        p = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndexOrThrow("_data"));
        g = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndexOrThrow("mime_type"));
        f = p.substring(p.lastIndexOf('/') + 1);
        f = f.replace(' ', '_');
      }
      throw new MmsException("Nothing found: " + paramUri);
      label228:
      ((Cursor)localObject).close();
      return;
    }
    throw new MmsException("Bad URI: " + paramUri);
  }
  
  public Map<String, ?> a()
  {
    return o;
  }
  
  public void a(att paramatt)
  {
    a(false);
  }
}

/* Location:
 * Qualified Name:     lv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
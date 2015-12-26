import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.util.MzSqliteWrapper;
import java.util.HashMap;

public class lt
  extends lm
{
  private final HashMap<String, String> o = new HashMap();
  private String p;
  
  public lt(Context paramContext, Uri paramUri)
  {
    this(paramContext, null, null, paramUri);
    c(paramUri);
  }
  
  public lt(Context paramContext, String paramString1, String paramString2, Uri paramUri)
  {
    super(paramContext, "talk", paramString1, paramString2, paramUri);
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
              break label216;
            }
            throw new MmsException("Type of media is unknown.");
          }
        }
        finally
        {
          ((Cursor)localObject).close();
        }
        p = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndexOrThrow("_data"));
        g = "audio/amr-meizu";
        f = p.substring(p.lastIndexOf('/') + 1);
        f = f.replace(' ', '_');
      }
      throw new MmsException("Nothing found: " + paramUri);
      label216:
      ((Cursor)localObject).close();
      return;
    }
    throw new MmsException("Bad URI: " + paramUri);
  }
  
  private void e(Uri paramUri)
  {
    p = paramUri.getPath();
    p = paramUri.getPath();
    if (TextUtils.isEmpty(MimeTypeMap.getFileExtensionFromUrl(p)))
    {
      int i = p.lastIndexOf('.');
      if (i >= 0) {
        p.substring(i + 1);
      }
    }
    g = "audio/amr-meizu";
    f = p.substring(p.lastIndexOf('/') + 1);
    f = f.replace(' ', '_');
  }
  
  public void a(att paramatt)
  {
    a(false);
  }
  
  public int i()
  {
    return Math.max(1000, super.i());
  }
}

/* Location:
 * Qualified Name:     lt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
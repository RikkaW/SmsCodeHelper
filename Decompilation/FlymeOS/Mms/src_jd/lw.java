import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.android.mms.MmsApp;
import com.google.android.mms.MmsException;

public class lw
  extends lo
{
  private zz q;
  
  public lw(Context paramContext, Uri paramUri, lp paramlp)
  {
    this(paramContext, null, null, paramUri, paramlp);
    c(paramUri);
    a();
  }
  
  public lw(Context paramContext, String paramString1, String paramString2, Uri paramUri, lp paramlp)
  {
    super(paramContext, "video", paramString1, paramString2, paramUri, paramlp);
  }
  
  private void c(Uri paramUri)
  {
    if (paramUri.getScheme().equals("content")) {
      e(paramUri);
    }
    for (;;)
    {
      f = c(f);
      x();
      return;
      if (paramUri.getScheme().equals("file")) {
        d(paramUri);
      }
    }
  }
  
  private void d(Uri paramUri)
  {
    Object localObject = paramUri.getPath();
    f = ((String)localObject).substring(((String)localObject).lastIndexOf('/') + 1);
    e = paramUri.getPath();
    MimeTypeMap localMimeTypeMap = MimeTypeMap.getSingleton();
    String str = MimeTypeMap.getFileExtensionFromUrl(f);
    localObject = str;
    if (TextUtils.isEmpty(str))
    {
      int i = f.lastIndexOf('.');
      localObject = str;
      if (i >= 0) {
        localObject = f.substring(i + 1);
      }
    }
    g = localMimeTypeMap.getMimeTypeFromExtension((String)localObject);
    if (Log.isLoggable("Mms:app", 2)) {
      Log.v("Mms/media", "New VideoModel initFromFile created: mSrc=" + f + " mContentType=" + g + " mUri=" + paramUri);
    }
    f = f.replace(' ', '_');
  }
  
  private void e(Uri paramUri)
  {
    Cursor localCursor = a.getContentResolver().query(paramUri, null, null, null, null);
    int i;
    if (localCursor != null)
    {
      for (;;)
      {
        try
        {
          if (!localCursor.moveToFirst()) {
            break label353;
          }
          if (b(paramUri))
          {
            e = localCursor.getString(localCursor.getColumnIndexOrThrow("_data"));
            g = localCursor.getString(localCursor.getColumnIndexOrThrow("ct"));
            f = localCursor.getString(localCursor.getColumnIndexOrThrow("fn"));
            if (!TextUtils.isEmpty(g)) {
              break;
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
        f = e.substring(e.lastIndexOf('/') + 1);
        f = f.replace(' ', '_');
      }
      if ((g.equals("video/mp4")) && (!TextUtils.isEmpty(f)))
      {
        i = f.lastIndexOf(".");
        if (i == -1) {}
      }
    }
    try
    {
      String str = f.substring(i + 1);
      if ((!TextUtils.isEmpty(str)) && ((str.equalsIgnoreCase("3gp")) || (str.equalsIgnoreCase("3gpp")) || (str.equalsIgnoreCase("3g2")))) {
        g = "video/3gpp";
      }
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      for (;;) {}
    }
    if (Log.isLoggable("Mms:app", 2)) {
      Log.v("Mms/media", "New VideoModel initFromContentUri created: mSrc=" + f + " mContentType=" + g + " mUri=" + paramUri);
    }
    localCursor.close();
    return;
    label353:
    throw new MmsException("Nothing found: " + paramUri);
    throw new MmsException("Bad URI: " + paramUri);
  }
  
  public zz a(zy paramzy)
  {
    q = MmsApp.c().f().b(k(), paramzy);
    return q;
  }
  
  protected void a()
  {
    lh.a().c(g);
  }
  
  public void a(att paramatt)
  {
    String str = paramatt.b();
    if (Log.isLoggable("Mms:app", 2)) {
      Log.v("Mms/media", "[VideoModel] handleEvent " + paramatt.b() + " on " + this);
    }
    Object localObject = lm.a.a;
    if (str.equals("SmilMediaStart"))
    {
      localObject = lm.a.b;
      A();
      p = true;
    }
    for (;;)
    {
      a((lm.a)localObject);
      a(false);
      return;
      if (str.equals("SmilMediaEnd"))
      {
        paramatt = lm.a.c;
        localObject = paramatt;
        if (h != 1)
        {
          p = false;
          localObject = paramatt;
        }
      }
      else if (str.equals("SmilMediaPause"))
      {
        localObject = lm.a.d;
        p = true;
      }
      else if (str.equals("SmilMediaSeek"))
      {
        localObject = lm.a.e;
        j = ((hs)paramatt).f();
        p = true;
      }
    }
  }
  
  public void b()
  {
    if ((q != null) && (!q.a()))
    {
      if (Log.isLoggable("Mms:app", 3)) {
        Log.v("Mms/media", "cancelThumbnailLoading for: " + this);
      }
      q.a(k());
      q = null;
    }
  }
  
  protected boolean c()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     lw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.model.Model;
import com.google.android.mms.MmsException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public abstract class lm
  extends Model
  implements atv
{
  protected Context a;
  protected int b;
  protected int c;
  protected String d;
  protected String e;
  protected String f;
  protected String g;
  protected short h;
  protected long i;
  protected int j;
  protected boolean k;
  protected String l;
  protected long m;
  private Uri o;
  private byte[] p;
  private final ArrayList<lm.a> q;
  
  public lm(Context paramContext, String paramString1, String paramString2, String paramString3, Uri paramUri)
  {
    a = paramContext;
    d = paramString1;
    g = paramString2;
    f = paramString3;
    o = paramUri;
    a();
    q = new ArrayList();
  }
  
  public lm(Context paramContext, String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      throw new IllegalArgumentException("data may not be null.");
    }
    a = paramContext;
    d = paramString1;
    g = paramString2;
    f = paramString3;
    p = paramArrayOfByte;
    i = paramArrayOfByte.length;
    if (MmsApp.a)
    {
      if (true != q()) {
        break label111;
      }
      if (i != 0L) {
        break label98;
      }
    }
    label98:
    label111:
    for (m = l1;; m = (i + 102L))
    {
      q = new ArrayList();
      return;
      l1 = i + 172L;
      break;
    }
  }
  
  private void a()
  {
    long l1 = 0L;
    Object localObject6 = a.getContentResolver();
    localObject5 = null;
    Object localObject1 = null;
    try
    {
      localObject6 = ((ContentResolver)localObject6).openInputStream(o);
      if (localObject6 == null)
      {
        localObject1 = localObject6;
        localObject5 = localObject6;
        Log.w("Mms/media", "initMediaSize: input is null,the mUri = " + o);
        if (localObject6 == null) {}
      }
      for (;;)
      {
        try
        {
          ((InputStream)localObject6).close();
          return;
        }
        catch (IOException localIOException1)
        {
          Log.e("Mms/media", "IOException caught while closing stream", localIOException1);
          return;
        }
        Object localObject2 = localObject6;
        localObject5 = localObject6;
        label170:
        label283:
        Object localObject3;
        if ((localObject6 instanceof FileInputStream))
        {
          localObject2 = localObject6;
          localObject5 = localObject6;
          i = ((FileInputStream)localObject6).getChannel().size();
          localObject2 = localObject6;
          localObject5 = localObject6;
          if (MmsApp.a)
          {
            localObject2 = localObject6;
            localObject5 = localObject6;
            if (true != q()) {
              break label302;
            }
            localObject2 = localObject6;
            localObject5 = localObject6;
            if (i != 0L) {
              break label283;
            }
            localObject2 = localObject6;
            localObject5 = localObject6;
          }
          for (m = l1;; m = (i + 102L))
          {
            localObject2 = localObject6;
            localObject5 = localObject6;
            if (s())
            {
              localObject2 = localObject6;
              localObject5 = localObject6;
              if (i > ga.f())
              {
                localObject2 = localObject6;
                localObject5 = localObject6;
                Log.w("Mms/media", "initMediaSize: Video size: f.getChannel().size(): " + i + " larger than max message size: " + ga.f());
              }
            }
            if (localObject6 == null) {
              break;
            }
            try
            {
              ((InputStream)localObject6).close();
              return;
            }
            catch (IOException localIOException2)
            {
              Log.e("Mms/media", "IOException caught while closing stream", localIOException2);
              return;
            }
            localObject3 = localObject6;
            localObject5 = localObject6;
            l1 = i + 172L;
            break label170;
            label302:
            localObject3 = localObject6;
            localObject5 = localObject6;
          }
        }
        try
        {
          ((InputStream)localObject5).close();
          throw ((Throwable)localObject4);
          IOException localIOException3;
          for (;;)
          {
            localIOException3 = localIOException6;
            localObject5 = localIOException6;
            if (-1 == localIOException6.read()) {
              break;
            }
            localIOException3 = localIOException6;
            localObject5 = localIOException6;
            i += 1L;
            localIOException3 = localIOException6;
            localObject5 = localIOException6;
            if (MmsApp.a)
            {
              localIOException3 = localIOException6;
              localObject5 = localIOException6;
              m += 1L;
            }
          }
          if (localIOException3 == null) {
            continue;
          }
          try
          {
            localIOException3.close();
            return;
          }
          catch (IOException localIOException4)
          {
            Log.e("Mms/media", "IOException caught while closing stream", localIOException4);
            return;
          }
        }
        catch (IOException localIOException5)
        {
          for (;;)
          {
            Log.e("Mms/media", "IOException caught while closing stream", localIOException5);
          }
        }
      }
    }
    catch (IOException localIOException6)
    {
      localObject5 = localObject3;
      Log.e("Mms/media", "IOException caught while opening or reading stream", localIOException6);
      localObject5 = localObject3;
      if ((localIOException6 instanceof FileNotFoundException))
      {
        localObject5 = localObject3;
        throw new MmsException(localIOException6.getMessage());
      }
    }
    finally
    {
      if (localObject5 == null) {}
    }
  }
  
  public static boolean b(Uri paramUri)
  {
    return (paramUri.getAuthority().startsWith("mms")) || (paramUri.getAuthority().startsWith("temp_mms_file"));
  }
  
  private boolean d(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    int n;
    if (paramString.length() >= 11) {
      n = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (n < paramString.length())
      {
        Character localCharacter = Character.valueOf(paramString.charAt(n));
        if ((localCharacter.charValue() < 0) || (localCharacter.charValue() > '')) {
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      n += 1;
    }
  }
  
  public static int f()
  {
    return 172;
  }
  
  protected void A()
  {
    if (Log.isLoggable("Mms:app", 2)) {
      Log.d("Mms/media", "pauseMusicPlayer");
    }
    Intent localIntent = new Intent("com.android.music.musicservicecommand");
    localIntent.putExtra("command", "pause");
    a.sendBroadcast(localIntent);
  }
  
  public void a(int paramInt)
  {
    b = paramInt;
    a(true);
  }
  
  protected void a(long paramLong1, long paramLong2) {}
  
  public void a(Uri paramUri)
  {
    o = paramUri;
  }
  
  public void a(String paramString)
  {
    m = (paramString.getBytes().length + 172);
  }
  
  public void a(lm.a parama)
  {
    q.add(parama);
  }
  
  public void a(short paramShort)
  {
    h = paramShort;
    a(true);
  }
  
  public void b(int paramInt)
  {
    if ((c()) && (paramInt < 0)) {}
    for (;;)
    {
      try
      {
        x();
        a(true);
        return;
      }
      catch (MmsException localMmsException)
      {
        Log.e("Mms/media", localMmsException.getMessage(), localMmsException);
        return;
      }
      c = paramInt;
    }
  }
  
  public void b(String paramString)
  {
    l = paramString;
  }
  
  protected String c(String paramString)
  {
    String str1;
    if (TextUtils.isEmpty(paramString)) {
      str1 = paramString;
    }
    String str2;
    do
    {
      return str1;
      int n = paramString.lastIndexOf(".");
      str2 = "";
      str1 = paramString;
      if (n >= 0)
      {
        str2 = paramString.substring(n);
        str1 = paramString.substring(0, n);
      }
      paramString = str1;
      if (d(str1)) {
        paramString = str1.substring(0, 11);
      }
      str1 = paramString;
    } while (str2.equals(""));
    return paramString + str2;
  }
  
  protected boolean c()
  {
    return false;
  }
  
  public boolean e()
  {
    return k;
  }
  
  public int g()
  {
    return b;
  }
  
  public int i()
  {
    return c;
  }
  
  public String j()
  {
    return g;
  }
  
  public Uri k()
  {
    return o;
  }
  
  public String l()
  {
    return e;
  }
  
  public byte[] m()
  {
    if (p != null)
    {
      byte[] arrayOfByte = new byte[p.length];
      System.arraycopy(p, 0, arrayOfByte, 0, p.length);
      return arrayOfByte;
    }
    return null;
  }
  
  public String n()
  {
    return f;
  }
  
  public String o()
  {
    return l;
  }
  
  public long p()
  {
    if (MmsApp.a) {
      return m;
    }
    return i;
  }
  
  public boolean q()
  {
    return d.equals("text");
  }
  
  public boolean r()
  {
    return d.equals("img");
  }
  
  public boolean s()
  {
    return d.equals("video");
  }
  
  public boolean t()
  {
    return d.equals("audio");
  }
  
  public boolean u()
  {
    return d.equals("ref");
  }
  
  public boolean v()
  {
    return d.equals("talk");
  }
  
  public boolean w()
  {
    return d.equals("file");
  }
  
  protected void x()
  {
    if (o == null) {
      throw new IllegalArgumentException("Uri may not be null.");
    }
    MediaMetadataRetriever localMediaMetadataRetriever = new MediaMetadataRetriever();
    int n = 0;
    try
    {
      localMediaMetadataRetriever.setDataSource(a, o);
      String str = localMediaMetadataRetriever.extractMetadata(9);
      if (str != null) {
        n = Integer.parseInt(str);
      }
      c = n;
      return;
    }
    catch (Exception localException)
    {
      Log.e("Mms/media", "MediaMetadataRetriever failed to get duration for " + o.getPath(), localException);
      throw new MmsException(localException);
    }
    finally
    {
      localMediaMetadataRetriever.release();
    }
  }
  
  public int y()
  {
    return j;
  }
  
  public lm.a z()
  {
    if (q.size() == 0) {
      return lm.a.a;
    }
    return (lm.a)q.remove(0);
  }
  
  public static enum a
  {
    private a() {}
  }
}

/* Location:
 * Qualified Name:     lm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
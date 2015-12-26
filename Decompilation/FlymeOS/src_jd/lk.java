import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzPduPart;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.pdu.smil.SmilSimpleParse;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class lk
  extends lo
{
  private static final Set<String> q = new HashSet(Arrays.asList(new String[] { "image/jpeg" }));
  private int r;
  private int s;
  private SoftReference<Bitmap> t = new SoftReference(null);
  private zz u;
  
  public lk(Context paramContext, Uri paramUri, lp paramlp)
  {
    super(paramContext, "img", paramUri, paramlp);
    c(paramUri);
    b();
  }
  
  public lk(Context paramContext, String paramString1, String paramString2, Uri paramUri, lp paramlp)
  {
    super(paramContext, "img", paramString1, paramString2, paramUri, paramlp);
    d(paramUri);
    l = "";
  }
  
  private Bitmap a(int paramInt, Uri paramUri)
  {
    paramUri = zf.a(r, s, paramInt, paramInt, 102400L, paramUri, a);
    if (paramUri == null) {
      return null;
    }
    return BitmapFactory.decodeByteArray(paramUri, 0, paramUri.length);
  }
  
  private void c(Uri paramUri)
  {
    paramUri = new zf(a, paramUri);
    e = paramUri.c();
    g = paramUri.a();
    if (TextUtils.isEmpty(g)) {
      throw new MmsException("Type of media is unknown.");
    }
    f = c(paramUri.b());
    r = paramUri.d();
    s = paramUri.e();
  }
  
  private void d(Uri paramUri)
  {
    paramUri = new zf(a, paramUri);
    r = paramUri.d();
    s = paramUri.e();
  }
  
  public int a()
  {
    return r;
  }
  
  public Bitmap a(int paramInt1, int paramInt2)
  {
    Bitmap localBitmap1 = (Bitmap)t.get();
    Bitmap localBitmap2 = localBitmap1;
    if (localBitmap1 == null) {
      localBitmap2 = localBitmap1;
    }
    try
    {
      localBitmap1 = a(Math.max(paramInt1, paramInt2), k());
      localBitmap2 = localBitmap1;
      if (localBitmap1 != null)
      {
        localBitmap2 = localBitmap1;
        t = new SoftReference(localBitmap1);
        localBitmap2 = localBitmap1;
      }
      return localBitmap2;
    }
    catch (OutOfMemoryError localOutOfMemoryError) {}
    return localBitmap2;
  }
  
  public zz a(zy paramzy)
  {
    u = MmsApp.c().f().a(k(), paramzy, SmilSimpleParse.isLocationUri(o()));
    return u;
  }
  
  protected void a(long paramLong1, long paramLong2)
  {
    Object localObject = new zf(a, k());
    boolean bool = wd.a(a);
    int i;
    int j;
    label46:
    label60:
    long l;
    int k;
    if (bool)
    {
      i = ga.n();
      if (!bool) {
        break label268;
      }
      j = ga.m();
      if (!bool) {
        break label276;
      }
      paramLong1 = Math.min(ga.g(), paramLong1);
      l = p();
      if (((zf)localObject).e() <= ((zf)localObject).d()) {
        break label480;
      }
      k = j;
      j = i;
    }
    for (;;)
    {
      if (Log.isLoggable("Mms:app", 2)) {
        Log.v("Mms/image", "is2Gmode: " + bool + ", resizeMedia size: " + l + " image.getWidth(): " + ((zf)localObject).d() + " widthLimit: " + k + " image.getHeight(): " + ((zf)localObject).e() + " heightLimit: " + j + " image.getContentType(): " + ((zf)localObject).a());
      }
      if ((l != 0L) && (l <= paramLong1) && (((zf)localObject).d() <= k) && (((zf)localObject).e() <= j) && (q.contains(((zf)localObject).a())))
      {
        if (Log.isLoggable("Mms:app", 2)) {
          Log.v("Mms/image", "resizeMedia - already sized");
        }
        return;
        i = ga.l();
        break;
        label268:
        j = ga.k();
        break label46;
        label276:
        paramLong1 = Math.min(ga.f(), paramLong1);
        break label60;
      }
      MzPduPart localMzPduPart = ((zf)localObject).a(k, j, paramLong1);
      if (localMzPduPart == null) {
        throw new fk("Not enough memory to turn image into part: " + k());
      }
      g = new String(localMzPduPart.getContentType());
      String str = n();
      localObject = str.getBytes();
      localMzPduPart.setContentLocation((byte[])localObject);
      i = str.lastIndexOf(".");
      if (i != -1) {
        localObject = str.substring(0, i).getBytes();
      }
      localMzPduPart.setContentId((byte[])localObject);
      localObject = MzPduPersister.getPduPersister(a);
      this.i = localMzPduPart.getData().length;
      if (Log.isLoggable("Mms:app", 2)) {
        Log.v("Mms/image", "resizeMedia mSize: " + this.i);
      }
      a(((MzPduPersister)localObject).persistPart(localMzPduPart, paramLong2, null));
      return;
      label480:
      k = i;
    }
  }
  
  public void a(att paramatt)
  {
    if (paramatt.b().equals("SmilMediaStart")) {
      p = true;
    }
    for (;;)
    {
      a(false);
      return;
      if (h != 1) {
        p = false;
      }
    }
  }
  
  protected void b()
  {
    lh.a().a(g);
  }
  
  public void d()
  {
    if ((u != null) && (!u.a()))
    {
      if (Log.isLoggable("Mms:app", 3)) {
        Log.v("Mms/image", "cancelThumbnailLoading for: " + this);
      }
      u.a(k());
      u = null;
    }
  }
  
  public boolean e()
  {
    if (((zf.b(g)) && (i > 5242880L)) || (zf.a(g))) {}
    while ((a() == -1) && (p() >= 0L)) {
      return false;
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     lk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import android.graphics.Bitmap;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class abo
  implements Runnable
{
  abo(abm.b paramb, Bitmap paramBitmap) {}
  
  public void run()
  {
    Object localObject = (Set)b.a.b.get(abm.b.a(b));
    if (localObject != null)
    {
      abm.a(b.a);
      Bitmap localBitmap;
      if (a == null) {
        if (abm.b.b(b)) {
          localBitmap = abm.e();
        }
      }
      for (;;)
      {
        localObject = zh.a((Set)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          zy localzy = (zy)((Iterator)localObject).next();
          if (Log.isLoggable("Mms:thumbnailcache", 3)) {
            Log.d("ThumbnailManager", "Invoking item loaded callback " + localzy);
          }
          localzy.a(new abm.a(localBitmap, abm.b.b(b)), null);
        }
        if (abm.b.c(b))
        {
          localBitmap = abm.f();
        }
        else
        {
          localBitmap = abm.g();
          continue;
          localBitmap = a;
        }
      }
    }
    if (Log.isLoggable("ThumbnailManager", 3)) {
      Log.d("ThumbnailManager", "No image callback!");
    }
    if (a != null)
    {
      abm.b(b.a).a(abm.b.a(b), a);
      if (Log.isLoggable("Mms:thumbnailcache", 3)) {
        Log.v("ThumbnailManager", "in callback runnable: bitmap uri: " + abm.b.a(b) + " width: " + a.getWidth() + " height: " + a.getHeight() + " size: " + a.getByteCount());
      }
    }
    b.a.b.remove(abm.b.a(b));
    b.a.a.remove(abm.b.a(b));
    if (Log.isLoggable("Mms:thumbnailcache", 3)) {
      Log.d("ThumbnailManager", "Image task for " + abm.b.a(b) + "exiting " + b.a.a.size() + " remain");
    }
  }
}

/* Location:
 * Qualified Name:     abo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
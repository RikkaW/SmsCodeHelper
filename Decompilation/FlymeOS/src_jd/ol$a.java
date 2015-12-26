import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import java.util.LinkedList;

final class ol$a
  extends Thread
{
  ol$a(ol paramol)
  {
    super("NotificationPlayer-" + ol.b(paramol));
  }
  
  public void run()
  {
    for (;;)
    {
      synchronized (ol.c(a))
      {
        Log.d(ol.b(a), "RemoveFirst");
        ol.b localb1 = (ol.b)ol.c(a).removeFirst();
        switch (a)
        {
        }
      }
      synchronized (ol.c(a))
      {
        while (ol.c(a).size() == 0)
        {
          ol.a(a, null);
          ol.f(a);
          return;
          localb2 = finally;
          throw localb2;
          Log.d(ol.b(a), "PLAY");
          ol.a(a, localb2);
          continue;
          Log.d(ol.b(a), "STOP");
          if (ol.a(a) != null)
          {
            long l = SystemClock.uptimeMillis() - g;
            if (l > 1000L) {
              Log.w(ol.b(a), "Notification stop delayed by " + l + "msecs");
            }
            ol.a(a).stop();
            ol.a(a).release();
            ol.a(a, null);
            ol.d(a).abandonAudioFocus(null);
            ol.a(a, null);
            if ((ol.e(a) != null) && (ol.e(a).getThread().getState() != Thread.State.TERMINATED)) {
              ol.e(a).quit();
            }
          }
          else
          {
            Log.w(ol.b(a), "STOP command without a player");
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     ol.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
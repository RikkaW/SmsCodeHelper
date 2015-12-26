import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Looper;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.util.Log;
import java.util.LinkedList;

public class ol
  implements MediaPlayer.OnCompletionListener
{
  private LinkedList<ol.b> a = new LinkedList();
  private Looper b;
  private String c;
  private ol.a d;
  private ol.c e;
  private final Object f = new Object();
  private MediaPlayer g;
  private PowerManager.WakeLock h;
  private AudioManager i;
  private int j = 2;
  
  public ol(String paramString)
  {
    if (paramString != null)
    {
      c = paramString;
      return;
    }
    c = "NotificationPlayer";
  }
  
  private void a(ol.b paramb)
  {
    try
    {
      Log.d(c, "Starting playback");
      synchronized (f)
      {
        if ((b != null) && (b.getThread().getState() != Thread.State.TERMINATED)) {
          b.quit();
        }
        e = new ol.c(paramb);
        synchronized (e)
        {
          e.start();
          e.wait();
          long l = SystemClock.uptimeMillis() - g;
          if (l > 1000L) {
            Log.w(c, "Notification sound delayed by " + l + "msecs");
          }
          return;
        }
      }
      return;
    }
    catch (Exception localException)
    {
      Log.w(c, "error loading sound for " + c, localException);
    }
  }
  
  private void b()
  {
    if (h != null) {
      h.acquire();
    }
  }
  
  private void b(ol.b paramb)
  {
    a.add(paramb);
    if (d == null)
    {
      b();
      d = new ol.a();
      d.start();
    }
  }
  
  private void c()
  {
    if (h != null) {
      h.release();
    }
  }
  
  public void a()
  {
    synchronized (a)
    {
      if (j != 2)
      {
        ol.b localb = new ol.b(null);
        g = SystemClock.uptimeMillis();
        a = 2;
        b(localb);
        j = 2;
      }
      return;
    }
  }
  
  public void a(Context arg1, Uri paramUri, boolean paramBoolean, int paramInt, float paramFloat)
  {
    ol.b localb = new ol.b(null);
    g = SystemClock.uptimeMillis();
    a = 1;
    b = ???;
    c = paramUri;
    d = paramBoolean;
    e = paramInt;
    f = paramFloat;
    synchronized (a)
    {
      b(localb);
      j = 1;
      return;
    }
  }
  
  public void onCompletion(MediaPlayer arg1)
  {
    if (i != null) {
      i.abandonAudioFocus(null);
    }
    synchronized (a)
    {
      if (a.size() == 0) {}
      synchronized (f)
      {
        if (b != null) {
          b.quit();
        }
        e = null;
        return;
      }
    }
  }
  
  final class a
    extends Thread
  {
    a()
    {
      super();
    }
    
    public void run()
    {
      for (;;)
      {
        synchronized (ol.c(ol.this))
        {
          Log.d(ol.b(ol.this), "RemoveFirst");
          ol.b localb1 = (ol.b)ol.c(ol.this).removeFirst();
          switch (a)
          {
          }
        }
        synchronized (ol.c(ol.this))
        {
          while (ol.c(ol.this).size() == 0)
          {
            ol.a(ol.this, null);
            ol.f(ol.this);
            return;
            localb2 = finally;
            throw localb2;
            Log.d(ol.b(ol.this), "PLAY");
            ol.a(ol.this, localb2);
            continue;
            Log.d(ol.b(ol.this), "STOP");
            if (ol.a(ol.this) != null)
            {
              long l = SystemClock.uptimeMillis() - g;
              if (l > 1000L) {
                Log.w(ol.b(ol.this), "Notification stop delayed by " + l + "msecs");
              }
              ol.a(ol.this).stop();
              ol.a(ol.this).release();
              ol.a(ol.this, null);
              ol.d(ol.this).abandonAudioFocus(null);
              ol.a(ol.this, null);
              if ((ol.e(ol.this) != null) && (ol.e(ol.this).getThread().getState() != Thread.State.TERMINATED)) {
                ol.e(ol.this).quit();
              }
            }
            else
            {
              Log.w(ol.b(ol.this), "STOP command without a player");
            }
          }
        }
      }
    }
  }
  
  static final class b
  {
    int a;
    Context b;
    Uri c;
    boolean d;
    int e;
    float f;
    long g;
    
    public String toString()
    {
      return "{ code=" + a + " looping=" + d + " stream=" + e + " uri=" + c + " }";
    }
  }
  
  final class c
    extends Thread
  {
    public ol.b a;
    
    public c(ol.b paramb)
    {
      a = paramb;
    }
    
    public void run()
    {
      Looper.prepare();
      ol.a(ol.this, Looper.myLooper());
      for (;;)
      {
        AudioManager localAudioManager;
        try
        {
          localAudioManager = (AudioManager)a.b.getSystemService("audio");
          try
          {
            MediaPlayer localMediaPlayer = new MediaPlayer();
            localMediaPlayer.setAudioStreamType(a.e);
            localMediaPlayer.setDataSource(a.b, a.c);
            localMediaPlayer.setLooping(a.d);
            localMediaPlayer.setVolume(a.f, a.f);
            localMediaPlayer.prepare();
            if ((a.c != null) && (a.c.getEncodedPath() != null) && (a.c.getEncodedPath().length() > 0))
            {
              if (!a.d) {
                continue;
              }
              localAudioManager.requestAudioFocus(null, a.e, 1);
            }
            localMediaPlayer.setOnCompletionListener(ol.this);
            localMediaPlayer.start();
            if (ol.a(ol.this) != null) {
              ol.a(ol.this).release();
            }
            ol.a(ol.this, localMediaPlayer);
          }
          catch (Exception localException)
          {
            Log.w(ol.b(ol.this), "error loading sound for " + a.c, localException);
            continue;
          }
          ol.a(ol.this, localAudioManager);
          notify();
          Looper.loop();
          return;
        }
        finally {}
        localAudioManager.requestAudioFocus(null, a.e, 3);
      }
    }
  }
}

/* Location:
 * Qualified Name:     ol
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
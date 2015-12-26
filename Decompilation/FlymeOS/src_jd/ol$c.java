import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;

final class ol$c
  extends Thread
{
  public ol.b a;
  
  public ol$c(ol paramol, ol.b paramb)
  {
    a = paramb;
  }
  
  public void run()
  {
    Looper.prepare();
    ol.a(b, Looper.myLooper());
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
          localMediaPlayer.setOnCompletionListener(b);
          localMediaPlayer.start();
          if (ol.a(b) != null) {
            ol.a(b).release();
          }
          ol.a(b, localMediaPlayer);
        }
        catch (Exception localException)
        {
          Log.w(ol.b(b), "error loading sound for " + a.c, localException);
          continue;
        }
        ol.a(b, localAudioManager);
        notify();
        Looper.loop();
        return;
      }
      finally {}
      localAudioManager.requestAudioFocus(null, a.e, 3);
    }
  }
}

/* Location:
 * Qualified Name:     ol.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
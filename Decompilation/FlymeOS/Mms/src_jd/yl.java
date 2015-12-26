import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import com.android.mms.ui.SlideView;

public class yl
  implements MediaPlayer.OnPreparedListener
{
  public yl(SlideView paramSlideView) {}
  
  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    SlideView.a(a, true);
    if (SlideView.a(a) > 0)
    {
      SlideView.b(a).seekTo(SlideView.a(a));
      SlideView.a(a, 0);
    }
    if (SlideView.c(a))
    {
      SlideView.b(a).start();
      SlideView.b(a, false);
      SlideView.d(a);
    }
    if (SlideView.e(a))
    {
      SlideView.b(a).stop();
      SlideView.b(a).release();
      SlideView.a(a, null);
      SlideView.c(a, false);
      SlideView.f(a);
    }
  }
}

/* Location:
 * Qualified Name:     yl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
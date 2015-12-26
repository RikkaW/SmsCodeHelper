import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import java.util.HashMap;

class acl
  implements AudioManager.OnAudioFocusChangeListener
{
  acl(ach paramach) {}
  
  public void onAudioFocusChange(int paramInt)
  {
    if (paramInt == -2) {
      if (ach.e(a).isPlaying())
      {
        ach.e(a).pause();
        if (ach.f().containsKey(ach.b.b(ach.f(a)))) {
          ((ach.a)ach.f().get(ach.b.b(ach.f(a)))).b(ach.b.b(ach.f(a)));
        }
      }
    }
    do
    {
      do
      {
        do
        {
          return;
          if (paramInt != 1) {
            break;
          }
        } while ((ach.e(a) == null) || (ach.e(a).isPlaying()));
        ach.e(a).start();
        return;
        if (paramInt == -1)
        {
          if (ach.e(a).isPlaying()) {
            ach.e(a).stop();
          }
          a.a.abandonAudioFocus(a.b);
          return;
        }
        if (paramInt != 1) {
          break;
        }
      } while (!ach.e(a).isPlaying());
      ach.e(a).stop();
      return;
    } while ((paramInt != 0) || (!ach.e(a).isPlaying()));
    ach.e(a).stop();
  }
}

/* Location:
 * Qualified Name:     acl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaRecorder;

class acs
  implements AudioManager.OnAudioFocusChangeListener
{
  acs(aco paramaco) {}
  
  public void onAudioFocusChange(int paramInt)
  {
    if (paramInt == -2) {
      if (a.b()) {
        aco.a(a, "pause", aco.a(a));
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
        } while ((aco.a(a) == null) || (a.b()));
        aco.a(a).start();
        return;
        if (paramInt == -1)
        {
          if (a.b()) {
            aco.a(a).stop();
          }
          a.c.abandonAudioFocus(a.d);
          return;
        }
        if (paramInt != 1) {
          break;
        }
      } while (!a.b());
      aco.a(a).stop();
      return;
    } while ((paramInt != 0) || (!a.b()));
    aco.a(a).stop();
  }
}

/* Location:
 * Qualified Name:     acs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
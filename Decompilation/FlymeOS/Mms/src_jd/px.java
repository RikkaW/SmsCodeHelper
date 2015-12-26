import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import com.android.mms.ui.AudioAttachmentView;

public class px
  implements MediaPlayer.OnErrorListener
{
  public px(AudioAttachmentView paramAudioAttachmentView) {}
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    AudioAttachmentView.a(a);
    return true;
  }
}

/* Location:
 * Qualified Name:     px
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
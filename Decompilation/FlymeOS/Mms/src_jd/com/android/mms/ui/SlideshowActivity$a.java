package com.android.mms.ui;

import android.widget.MediaController.MediaPlayerControl;
import ig;

class SlideshowActivity$a
  implements MediaController.MediaPlayerControl
{
  private final ig b;
  private boolean c = true;
  
  public SlideshowActivity$a(SlideshowActivity paramSlideshowActivity, ig paramig)
  {
    b = paramig;
  }
  
  public boolean canPause()
  {
    return true;
  }
  
  public boolean canSeekBackward()
  {
    return true;
  }
  
  public boolean canSeekForward()
  {
    return true;
  }
  
  public int getAudioSessionId()
  {
    return 0;
  }
  
  public int getBufferPercentage()
  {
    return 100;
  }
  
  public int getCurrentPosition()
  {
    if (b != null) {
      return b.o();
    }
    return 0;
  }
  
  public int getDuration()
  {
    return b.n();
  }
  
  public boolean isPlaying()
  {
    return c;
  }
  
  public void pause()
  {
    b.g();
    c = false;
  }
  
  public void seekTo(int paramInt) {}
  
  public void start()
  {
    b.h();
    c = true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowActivity.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
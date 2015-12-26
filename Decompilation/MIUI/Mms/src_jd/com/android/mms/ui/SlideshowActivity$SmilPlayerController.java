package com.android.mms.ui;

import android.widget.MediaController.MediaPlayerControl;
import com.android.mms.dom.smil.SmilPlayer;

class SlideshowActivity$SmilPlayerController
  implements MediaController.MediaPlayerControl
{
  private final SmilPlayer mPlayer;
  
  public SlideshowActivity$SmilPlayerController(SlideshowActivity paramSlideshowActivity, SmilPlayer paramSmilPlayer)
  {
    mPlayer = paramSmilPlayer;
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
    return mPlayer.getCurrentPosition();
  }
  
  public int getDuration()
  {
    return mPlayer.getDuration();
  }
  
  public boolean isPlaying()
  {
    if (mPlayer != null) {
      return mPlayer.isPlayingState();
    }
    return false;
  }
  
  public void pause()
  {
    if (mPlayer != null) {
      mPlayer.pause();
    }
  }
  
  public void seekTo(int paramInt) {}
  
  public void start()
  {
    if (mPlayer != null) {
      mPlayer.start();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowActivity.SmilPlayerController
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
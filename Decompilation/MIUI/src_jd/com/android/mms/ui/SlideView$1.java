package com.android.mms.ui;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;

class SlideView$1
  implements MediaPlayer.OnPreparedListener
{
  SlideView$1(SlideView paramSlideView) {}
  
  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    SlideView.access$002(this$0, true);
    if (SlideView.access$100(this$0) > 0)
    {
      SlideView.access$200(this$0).seekTo(SlideView.access$100(this$0));
      SlideView.access$102(this$0, 0);
    }
    if (SlideView.access$300(this$0))
    {
      SlideView.access$200(this$0).start();
      SlideView.access$302(this$0, false);
      SlideView.access$400(this$0);
    }
    if (SlideView.access$500(this$0))
    {
      SlideView.access$200(this$0).stop();
      SlideView.access$200(this$0).release();
      SlideView.access$202(this$0, null);
      SlideView.access$502(this$0, false);
      SlideView.access$600(this$0);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideView.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
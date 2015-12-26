package com.android.mms.ui;

import android.os.Handler;

final class LinearAnimator$1
  implements Runnable
{
  LinearAnimator$1(long paramLong1, float paramFloat1, float paramFloat2, long paramLong2, LinearAnimator.FrameHandler paramFrameHandler, Handler paramHandler) {}
  
  public void run()
  {
    long l = System.currentTimeMillis();
    if (val$duration == 0L) {}
    for (float f = val$endValue; ((val$startValue <= val$endValue) && (f >= val$endValue)) || ((val$startValue > val$endValue) && (f <= val$endValue)); f = val$startValue + (val$endValue - val$startValue) * (float)(l - val$startTime) / (float)val$duration)
    {
      val$frameHandler.onFrame(val$endValue);
      val$frameHandler.onEnd();
      return;
    }
    val$frameHandler.onFrame(f);
    val$handler.post(this);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.LinearAnimator.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
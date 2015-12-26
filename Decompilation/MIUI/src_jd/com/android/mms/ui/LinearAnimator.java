package com.android.mms.ui;

import android.os.Handler;

public class LinearAnimator
{
  public static void start(final float paramFloat1, float paramFloat2, long paramLong, final FrameHandler paramFrameHandler)
  {
    final long l = System.currentTimeMillis();
    Handler localHandler = new Handler();
    localHandler.post(new Runnable()
    {
      public void run()
      {
        long l = System.currentTimeMillis();
        if (val$duration == 0L) {}
        for (float f = paramFloat1; ((l <= paramFloat1) && (f >= paramFloat1)) || ((l > paramFloat1) && (f <= paramFloat1)); f = l + (paramFloat1 - l) * (float)(l - paramFrameHandler) / (float)val$duration)
        {
          val$frameHandler.onFrame(paramFloat1);
          val$frameHandler.onEnd();
          return;
        }
        val$frameHandler.onFrame(f);
        val$handler.post(this);
      }
    });
  }
  
  public static abstract interface FrameHandler
  {
    public abstract void onEnd();
    
    public abstract void onFrame(float paramFloat);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.LinearAnimator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
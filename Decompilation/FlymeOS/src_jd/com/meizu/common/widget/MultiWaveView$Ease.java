package com.meizu.common.widget;

import android.animation.TimeInterpolator;

class MultiWaveView$Ease
{
  private static final float DOMAIN = 1.0F;
  private static final float DURATION = 1.0F;
  private static final float START = 0.0F;
  
  static class Cubic
  {
    public static final TimeInterpolator easeIn = new MultiWaveView.Ease.Cubic.1();
    public static final TimeInterpolator easeInOut = new MultiWaveView.Ease.Cubic.3();
    public static final TimeInterpolator easeOut = new MultiWaveView.Ease.Cubic.2();
  }
  
  static class Linear
  {
    public static final TimeInterpolator easeNone = new MultiWaveView.Ease.Linear.1();
  }
  
  static class Quad
  {
    public static final TimeInterpolator easeIn = new MultiWaveView.Ease.Quad.1();
    public static final TimeInterpolator easeInOut = new MultiWaveView.Ease.Quad.3();
    public static final TimeInterpolator easeOut = new MultiWaveView.Ease.Quad.2();
  }
  
  static class Quart
  {
    public static final TimeInterpolator easeIn = new MultiWaveView.Ease.Quart.1();
    public static final TimeInterpolator easeInOut = new MultiWaveView.Ease.Quart.3();
    public static final TimeInterpolator easeOut = new MultiWaveView.Ease.Quart.2();
  }
  
  static class Quint
  {
    public static final TimeInterpolator easeIn = new MultiWaveView.Ease.Quint.1();
    public static final TimeInterpolator easeInOut = new MultiWaveView.Ease.Quint.3();
    public static final TimeInterpolator easeOut = new MultiWaveView.Ease.Quint.2();
  }
  
  static class Sine
  {
    public static final TimeInterpolator easeIn = new MultiWaveView.Ease.Sine.1();
    public static final TimeInterpolator easeInOut = new MultiWaveView.Ease.Sine.3();
    public static final TimeInterpolator easeOut = new MultiWaveView.Ease.Sine.2();
  }
  
  static class customTrack
  {
    public static final TimeInterpolator easeLadderShape = new MultiWaveView.Ease.customTrack.1();
    public static final TimeInterpolator easeSinShape = new MultiWaveView.Ease.customTrack.2();
    static float mOutput = 1.0F;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.Ease
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
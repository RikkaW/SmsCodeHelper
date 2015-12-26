package android.support.v7.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class SwitchCompat$1
  extends Animation
{
  SwitchCompat$1(SwitchCompat paramSwitchCompat, float paramFloat1, float paramFloat2) {}
  
  protected void applyTransformation(float paramFloat, Transformation paramTransformation)
  {
    SwitchCompat.access$000(this$0, val$startPosition + val$diff * paramFloat);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.SwitchCompat.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
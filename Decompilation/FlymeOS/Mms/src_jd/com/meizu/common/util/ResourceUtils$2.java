package com.meizu.common.util;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;

final class ResourceUtils$2
  implements ValueAnimator.AnimatorUpdateListener
{
  ResourceUtils$2(ColorMatrix paramColorMatrix, Drawable paramDrawable) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    float f = ((Float)paramValueAnimator.getAnimatedValue()).floatValue();
    val$colorMatrix.set(new float[] { 1.0F, 0.0F, 0.0F, 0.0F, f, 0.0F, 1.0F, 0.0F, 0.0F, f, 0.0F, 0.0F, 1.0F, 0.0F, f, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F });
    val$drawable.setColorFilter(new ColorMatrixColorFilter(val$colorMatrix));
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.ResourceUtils.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
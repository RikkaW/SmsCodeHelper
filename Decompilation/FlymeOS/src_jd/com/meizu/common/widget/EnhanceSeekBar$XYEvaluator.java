package com.meizu.common.widget;

import android.animation.TypeEvaluator;

class EnhanceSeekBar$XYEvaluator
  implements TypeEvaluator
{
  private EnhanceSeekBar$XYEvaluator(EnhanceSeekBar paramEnhanceSeekBar) {}
  
  public Object evaluate(float paramFloat, Object paramObject1, Object paramObject2)
  {
    paramObject1 = (EnhanceSeekBar.XYHolder)paramObject1;
    paramObject2 = (EnhanceSeekBar.XYHolder)paramObject2;
    return new EnhanceSeekBar.XYHolder(this$0, ((EnhanceSeekBar.XYHolder)paramObject1).getX() + (((EnhanceSeekBar.XYHolder)paramObject2).getX() - ((EnhanceSeekBar.XYHolder)paramObject1).getX()) * paramFloat, ((EnhanceSeekBar.XYHolder)paramObject1).getY() + (((EnhanceSeekBar.XYHolder)paramObject2).getY() - ((EnhanceSeekBar.XYHolder)paramObject1).getY()) * paramFloat);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.EnhanceSeekBar.XYEvaluator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
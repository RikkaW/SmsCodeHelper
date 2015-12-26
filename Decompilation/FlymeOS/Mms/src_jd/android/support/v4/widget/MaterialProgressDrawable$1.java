package android.support.v4.widget;

import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;

class MaterialProgressDrawable$1
  extends Animation
{
  MaterialProgressDrawable$1(MaterialProgressDrawable paramMaterialProgressDrawable, MaterialProgressDrawable.Ring paramRing) {}
  
  public void applyTransformation(float paramFloat, Transformation paramTransformation)
  {
    if (this$0.mFinishing)
    {
      MaterialProgressDrawable.access$000(this$0, paramFloat, val$ring);
      return;
    }
    float f1 = MaterialProgressDrawable.access$100(this$0, val$ring);
    float f2 = val$ring.getStartingEndTrim();
    float f4 = val$ring.getStartingStartTrim();
    float f3 = val$ring.getStartingRotation();
    MaterialProgressDrawable.access$200(this$0, paramFloat, val$ring);
    if (paramFloat <= 0.5F)
    {
      float f5 = paramFloat / 0.5F;
      f5 = MaterialProgressDrawable.access$300().getInterpolation(f5);
      val$ring.setStartTrim(f4 + f5 * (0.8F - f1));
    }
    if (paramFloat > 0.5F)
    {
      f4 = (paramFloat - 0.5F) / 0.5F;
      f4 = MaterialProgressDrawable.access$300().getInterpolation(f4);
      val$ring.setEndTrim((0.8F - f1) * f4 + f2);
    }
    val$ring.setRotation(0.25F * paramFloat + f3);
    f1 = MaterialProgressDrawable.access$400(this$0) / 5.0F;
    this$0.setRotation(216.0F * paramFloat + 1080.0F * f1);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.MaterialProgressDrawable.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
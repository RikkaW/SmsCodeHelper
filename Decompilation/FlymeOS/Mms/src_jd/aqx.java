import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Paint;

class aqx
  implements ValueAnimator.AnimatorUpdateListener
{
  aqx(aqt paramaqt, float paramFloat) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    int i = ((Integer)paramValueAnimator.getAnimatedValue()).intValue();
    if (aqt.access$600(b)) {
      aqt.access$102(b, i);
    }
    if (aqt.access$700(b) >= i) {
      aqt.access$302(b, false);
    }
    if (aqt.access$800(b))
    {
      i = (int)((i / aqt.access$200(b) - a) / (1.0F - a) * aqt.access$900(b));
      aqt.access$1000(b).setAlpha(i);
      aqt.access$1100(b).setAlpha(i / 2);
    }
    b.invalidateSelf();
  }
}

/* Location:
 * Qualified Name:     aqx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
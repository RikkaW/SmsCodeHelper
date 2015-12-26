import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class aqw
  implements ValueAnimator.AnimatorUpdateListener
{
  aqw(aqt paramaqt) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    int i = ((Integer)paramValueAnimator.getAnimatedValue()).intValue();
    aqt.access$102(a, i);
    if (aqt.access$200(a) <= i)
    {
      aqt.access$302(a, false);
      if ((!aqt.access$400(a)) && (aqt.access$500(a))) {
        a.rippleFade();
      }
    }
    a.invalidateSelf();
  }
}

/* Location:
 * Qualified Name:     aqw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
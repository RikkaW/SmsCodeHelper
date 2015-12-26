import android.animation.Animator.AnimatorListener;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

public class gj
{
  public static final Interpolator a = new PathInterpolator(0.0F, 0.0F, 0.2F, 1.0F);
  public static final Interpolator b = new PathInterpolator(0.4F, 0.0F, 1.0F, 1.0F);
  public static final Interpolator c = new PathInterpolator(0.4F, 0.0F, 0.2F, 1.0F);
  public static final Interpolator d = new PathInterpolator(0.0F, 0.3F, 0.1F, 1.0F);
  public static final Interpolator e = new PathInterpolator(0.0F, 0.66F, 0.33F, 1.0F);
  public static final Interpolator f = new PathInterpolator(0.0F, 0.66F, 0.34F, 1.0F);
  public static final Interpolator g = new PathInterpolator(0.0F, 0.33F, 0.67F, 1.0F);
  public static final Interpolator h = new PathInterpolator(0.33F, 0.0F, 0.1F, 1.0F);
  public static final Interpolator i = new PathInterpolator(0.33F, 0.0F, 0.1F, 1.0F);
  
  public static void a(View paramView, int paramInt, float paramFloat)
  {
    paramView.setTranslationY(paramFloat);
    paramView.animate().translationY(0.0F).setDuration(paramInt).setInterpolator(g);
  }
  
  public static void a(View paramView, int paramInt, float paramFloat, Animator.AnimatorListener paramAnimatorListener)
  {
    paramView.setTranslationY(0.0F);
    paramView.animate().translationY(paramFloat).setDuration(paramInt).setInterpolator(h).setListener(paramAnimatorListener);
  }
  
  public static void b(View paramView, int paramInt, float paramFloat)
  {
    paramView.setTranslationY(0.0F);
    paramView.animate().translationY(paramFloat).setDuration(paramInt).setInterpolator(e);
  }
  
  public static void b(View paramView, int paramInt, float paramFloat, Animator.AnimatorListener paramAnimatorListener)
  {
    paramView.setTranslationY(paramFloat);
    paramView.animate().translationY(0.0F).setDuration(paramInt).setInterpolator(i).setListener(paramAnimatorListener);
  }
}

/* Location:
 * Qualified Name:     gj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
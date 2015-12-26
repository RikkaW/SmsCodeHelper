import com.android.mms.ui.SlideshowPresenter;

public class yx
  implements ps.a
{
  public yx(SlideshowPresenter paramSlideshowPresenter) {}
  
  public void a(int paramInt1, int paramInt2)
  {
    ll localll = ((lr)a.mModel).f();
    a.mWidthTransformRatio = SlideshowPresenter.access$000(a, paramInt1, localll.d());
    a.mHeightTransformRatio = SlideshowPresenter.access$100(a, paramInt2, localll.e());
    if (a.mWidthTransformRatio > a.mHeightTransformRatio) {}
    for (float f = a.mWidthTransformRatio;; f = a.mHeightTransformRatio)
    {
      a.mWidthTransformRatio = f;
      a.mHeightTransformRatio = f;
      return;
    }
  }
}

/* Location:
 * Qualified Name:     yx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
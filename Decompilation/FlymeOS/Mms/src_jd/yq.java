import com.android.mms.ui.SlideView;
import com.android.mms.ui.SlideshowActivity;

public class yq
  implements Runnable
{
  public yq(SlideshowActivity paramSlideshowActivity, lr paramlr) {}
  
  private boolean a()
  {
    return (SlideshowActivity.a(b).d()) || (SlideshowActivity.a(b).b()) || (SlideshowActivity.a(b).c());
  }
  
  public void run()
  {
    SlideshowActivity.a(b, ig.a());
    if (SlideshowActivity.b(b) > 1)
    {
      SlideshowActivity.c(b);
      SlideshowActivity.e(b).setMediaController(SlideshowActivity.d(b));
    }
    SlideshowActivity.a(b, ls.a(a));
    int i;
    int j;
    int k;
    int m;
    if (SlideshowActivity.a(SlideshowActivity.f(b)))
    {
      Object localObject = a.f();
      if (localObject == null) {
        break label256;
      }
      lp locallp = ((ll)localObject).a();
      if (locallp == null) {
        break label249;
      }
      i = locallp.c();
      j = locallp.d();
      localObject = ((ll)localObject).b();
      if (localObject == null) {
        break label237;
      }
      k = ((lp)localObject).c();
      int n = ((lp)localObject).d();
      m = i;
      i = k;
      k = j;
      j = i;
      i = n;
    }
    for (;;)
    {
      SlideshowActivity.e(b).d(j, i, m, k);
      ((atw)SlideshowActivity.f(b)).a("SimlDocumentEnd", b, false);
      SlideshowActivity.a(b).a(SlideshowActivity.f(b));
      if (a())
      {
        SlideshowActivity.a(b).k();
        return;
      }
      SlideshowActivity.a(b).f();
      SlideshowActivity.a(true);
      return;
      label237:
      k = j;
      m = i;
      i = 0;
      j = 0;
      continue;
      label249:
      j = 0;
      i = 0;
      break;
      label256:
      i = 0;
      j = 0;
      k = 0;
      m = 0;
    }
  }
}

/* Location:
 * Qualified Name:     yq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
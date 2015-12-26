import com.android.mms.ui.MmsThumbnailPresenter;
import com.android.mms.ui.SlideViewInterface;

public class wx
  implements zy<abm.a>
{
  public wx(MmsThumbnailPresenter paramMmsThumbnailPresenter) {}
  
  public void a(abm.a parama, Throwable paramThrowable)
  {
    if ((paramThrowable != null) || (MmsThumbnailPresenter.access$000(a) != null)) {}
    do
    {
      do
      {
        synchronized (MmsThumbnailPresenter.access$000(a))
        {
          MmsThumbnailPresenter.access$000(a).a(true);
          if (MmsThumbnailPresenter.access$100(a) != null)
          {
            MmsThumbnailPresenter.access$100(a).a(parama, paramThrowable);
            return;
          }
        }
        paramThrowable = ((lr)a.mModel).a(0);
      } while (paramThrowable == null);
      if ((paramThrowable.g()) && (b))
      {
        paramThrowable = paramThrowable.s();
        ((SlideViewInterface)a.mView).b(paramThrowable.n() + "/" + paramThrowable.p(), a);
        return;
      }
    } while ((!paramThrowable.e()) || (b));
    paramThrowable = paramThrowable.q();
    ((SlideViewInterface)a.mView).a(paramThrowable.n() + "/" + paramThrowable.p(), a);
  }
}

/* Location:
 * Qualified Name:     wx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
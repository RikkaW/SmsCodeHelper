import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.android.mms.ui.MeizuSearchActivity;
import com.android.mms.ui.MeizuSearchActivity.a;

public class vm
  extends ContentObserver
{
  public vm(MeizuSearchActivity.a parama, Handler paramHandler)
  {
    super(paramHandler);
  }
  
  public void onChange(boolean paramBoolean)
  {
    if ((MeizuSearchActivity.o(a.a)) && (!a.a.isFinishing())) {
      MeizuSearchActivity.f(a.a);
    }
    super.onChange(paramBoolean);
  }
  
  public void onChange(boolean paramBoolean, Uri paramUri)
  {
    super.onChange(paramBoolean, paramUri);
  }
}

/* Location:
 * Qualified Name:     vm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
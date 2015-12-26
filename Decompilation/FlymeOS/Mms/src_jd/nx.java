import android.database.ContentObserver;
import android.os.Handler;
import com.android.mms.transaction.MessagePopupService;

public class nx
  extends ContentObserver
{
  public nx(MessagePopupService paramMessagePopupService, Handler paramHandler)
  {
    super(paramHandler);
  }
  
  public void onChange(boolean paramBoolean)
  {
    super.onChange(paramBoolean);
    if (MessagePopupService.a(a) != null) {
      MessagePopupService.a(a).l();
    }
  }
}

/* Location:
 * Qualified Name:     nx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
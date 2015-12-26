import android.database.Cursor;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.android.mms.recipient.MessageRecipient;
import com.meizu.commonwidget.RecipientEdit.f;
import java.util.HashMap;

public class mz
  implements AdapterView.OnItemClickListener
{
  public mz(MessageRecipient paramMessageRecipient) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    aig localaig = new aig();
    na localna = MessageRecipient.b(a);
    int i = localna.e(paramInt);
    Cursor localCursor = localna.d(i);
    int j = localna.f(paramInt);
    paramAdapterView = localna.c(i);
    i = j;
    if (paramAdapterView.a())
    {
      i = j;
      if ((paramAdapterView instanceof aii.b)) {
        i = j - 1;
      }
    }
    if ((localCursor.moveToPosition(i)) && (localCursor.getColumnCount() >= 4))
    {
      paramView = localCursor.getString(0);
      paramAdapterView = localCursor.getString(1);
      localaig.a(paramView, null, Long.valueOf(localCursor.getLong(3)));
    }
    for (;;)
    {
      if (localna.g(paramInt)) {
        MessageRecipient.a(a, paramLong);
      }
      for (;;)
      {
        if ((a.c != null) && (a.getRecipientCount() == 1)) {
          a.c.a();
        }
        return;
        if (TextUtils.isEmpty(paramAdapterView))
        {
          a.a(paramView);
        }
        else
        {
          a.a(paramAdapterView, paramView);
          a.getRecipientsInfo().put(paramAdapterView, localaig);
        }
      }
      paramAdapterView = "";
      paramView = "";
    }
  }
}

/* Location:
 * Qualified Name:     mz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
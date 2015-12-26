import android.database.DataSetObserver;
import android.widget.ListView;
import com.android.mms.ui.SelectConversationList;
import com.android.mms.ui.SelectConversationList.b;

public class xt
  extends DataSetObserver
{
  public xt(SelectConversationList.b paramb) {}
  
  public void onChanged()
  {
    SelectConversationList.a(a.e).post(new xu(this));
  }
}

/* Location:
 * Qualified Name:     xt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
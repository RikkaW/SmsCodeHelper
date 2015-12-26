import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.database.Cursor;
import android.widget.ListView;
import com.android.mms.ui.SearchActivity;

public class xl
  extends AsyncQueryHandler
{
  public xl(SearchActivity paramSearchActivity, ContentResolver paramContentResolver, String paramString, ListView paramListView)
  {
    super(paramContentResolver);
  }
  
  protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
  {
    if (paramCursor == null)
    {
      c.setTitle(c.getResources().getQuantityString(2131427331, 0, new Object[] { Integer.valueOf(0), a }));
      return;
    }
    paramInt = paramCursor.getColumnIndex("thread_id");
    int i = paramCursor.getColumnIndex("address");
    int j = paramCursor.getColumnIndex("body");
    int k = paramCursor.getColumnIndex("_id");
    int m = paramCursor.getCount();
    c.setTitle(c.getResources().getQuantityString(2131427331, m, new Object[] { Integer.valueOf(m), a }));
    c.setListAdapter(new xm(this, c, paramCursor, false, i, j, paramInt, k));
    b.setFocusable(true);
    b.setFocusableInTouchMode(true);
    b.requestFocus();
  }
}

/* Location:
 * Qualified Name:     xl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
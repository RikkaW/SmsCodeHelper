import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.RecyclerListener;
import android.widget.CursorAdapter;
import com.android.mms.ui.MeizuSearchActivity;
import com.android.mms.view.MeizuSearchListItem;

public class gf
  extends CursorAdapter
  implements AbsListView.RecyclerListener
{
  protected String a;
  
  public gf(Context paramContext, String paramString)
  {
    super(paramContext, null);
    a = paramString;
  }
  
  private boolean a()
  {
    return ((Boolean)aau.a(CursorAdapter.class, this, "mDataValid")).booleanValue();
  }
  
  public void a(Cursor paramCursor, String paramString)
  {
    if (paramCursor == getCursor()) {
      return;
    }
    a = paramString;
    super.changeCursor(paramCursor);
  }
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    paramView = paramView.findViewById(2131886592);
    if (!(paramView instanceof MeizuSearchListItem)) {
      return;
    }
    ((MeizuSearchListItem)paramView).a(paramCursor, a);
  }
  
  public long getItemId(int paramInt)
  {
    long l2 = 0L;
    long l1 = l2;
    if (a())
    {
      l1 = l2;
      if (getCursor() != null)
      {
        l1 = l2;
        if (getCursor().moveToPosition(paramInt)) {
          l1 = MeizuSearchActivity.b(getCursor()).hashCode();
        }
      }
    }
    return l1;
  }
  
  public boolean hasStableIds()
  {
    return true;
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    return LayoutInflater.from(paramContext).inflate(2130968729, paramViewGroup, false);
  }
  
  protected void onContentChanged() {}
  
  public void onMovedToScrapHeap(View paramView)
  {
    ((MeizuSearchListItem)paramView.findViewById(2131886592)).c();
  }
}

/* Location:
 * Qualified Name:     gf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
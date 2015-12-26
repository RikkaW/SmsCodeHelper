import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.RecyclerListener;
import android.widget.CursorAdapter;
import com.android.mms.MmsApp;
import com.android.mms.view.ConversationListItem;

public class ge
  extends CursorAdapter
  implements AbsListView.RecyclerListener
{
  private final LayoutInflater a;
  private ge.a b;
  private boolean c = true;
  
  public ge(Context paramContext, Cursor paramCursor)
  {
    super(paramContext, paramCursor, false);
    a = LayoutInflater.from(paramContext);
  }
  
  public void a(ge.a parama)
  {
    b = parama;
  }
  
  public void a(boolean paramBoolean)
  {
    c = paramBoolean;
  }
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    if (!(paramView instanceof ConversationListItem))
    {
      Log.e("ConversationListAdapter", "Unexpected bound view: " + paramView);
      return;
    }
    paramView = (ConversationListItem)paramView;
    paramView.setAvatarClickable(c);
    paramView.a(paramContext, gr.a(MmsApp.c(), paramCursor));
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    return a.inflate(2130968617, paramViewGroup, false);
  }
  
  protected void onContentChanged()
  {
    if ((getCursor() != null) && (!getCursor().isClosed()) && (b != null)) {
      b.a(this);
    }
  }
  
  public void onMovedToScrapHeap(View paramView)
  {
    ((ConversationListItem)paramView).a();
  }
  
  public static abstract interface a
  {
    public abstract void a(ge paramge);
  }
}

/* Location:
 * Qualified Name:     ge
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
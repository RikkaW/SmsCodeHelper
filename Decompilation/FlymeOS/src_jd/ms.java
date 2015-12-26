import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ms
  extends CursorAdapter
{
  private LayoutInflater a;
  
  public ms(Context paramContext, Cursor paramCursor, int paramInt)
  {
    super(paramContext, paramCursor, paramInt);
    a = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    ((TextView)paramView.findViewById(2131886718)).setText(paramCursor.getString(1));
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    return a.inflate(2130968819, paramViewGroup, false);
  }
}

/* Location:
 * Qualified Name:     ms
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.android.mms.ui.SearchActivity.TextViewSnippet;

class xm
  extends CursorAdapter
{
  xm(xl paramxl, Context paramContext, Cursor paramCursor, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super(paramContext, paramCursor, paramBoolean);
  }
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    TextView localTextView = (TextView)paramView.findViewById(2131886173);
    SearchActivity.TextViewSnippet localTextViewSnippet = (SearchActivity.TextViewSnippet)paramView.findViewById(2131886726);
    paramContext = paramCursor.getString(a);
    if (paramContext != null)
    {
      paramContext = gm.a(paramContext, false);
      if (paramContext == null) {
        break label124;
      }
    }
    label124:
    for (paramContext = paramContext.i();; paramContext = "")
    {
      localTextView.setText(paramContext);
      localTextViewSnippet.a(paramCursor.getString(b), e.a);
      paramView.setOnClickListener(new xn(this, paramCursor.getLong(c), paramCursor.getLong(d)));
      return;
      paramContext = null;
      break;
    }
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    return LayoutInflater.from(paramContext).inflate(2130968828, paramViewGroup, false);
  }
}

/* Location:
 * Qualified Name:     xm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
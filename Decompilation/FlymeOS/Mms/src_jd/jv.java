import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.mms.ui.MessageListView;
import java.util.regex.Pattern;

public class jv
  extends vx
{
  public jv(Context paramContext, Cursor paramCursor, MessageListView paramMessageListView, boolean paramBoolean1, Pattern paramPattern, boolean paramBoolean2)
  {
    super(paramContext, paramCursor, paramMessageListView, paramBoolean1, paramPattern, paramBoolean2);
  }
  
  private int b(Cursor paramCursor)
  {
    if ("sms".equals(paramCursor.getString(c.a))) {
      return 0;
    }
    int i = paramCursor.getInt(c.o);
    paramCursor = paramCursor.getString(c.D);
    if ((!TextUtils.isEmpty(paramCursor)) && (paramCursor.contains("talk#audio/amr")) && (i != 130)) {
      return 2;
    }
    return 1;
  }
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    super.bindView(paramView, paramContext, paramCursor);
    paramView.setPadding(0, 0, 0, 0);
  }
  
  public int getItemViewType(int paramInt)
  {
    return b((Cursor)getItem(paramInt));
  }
  
  public int getViewTypeCount()
  {
    return 3;
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    switch (b(paramCursor))
    {
    default: 
      return null;
    case 0: 
      return b.inflate(2130968663, paramViewGroup, false);
    case 1: 
      return b.inflate(2130968662, paramViewGroup, false);
    }
    return b.inflate(2130968664, paramViewGroup, false);
  }
}

/* Location:
 * Qualified Name:     jv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
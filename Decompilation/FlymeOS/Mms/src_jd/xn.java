import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.SearchActivity;

class xn
  implements View.OnClickListener
{
  xn(xm paramxm, long paramLong1, long paramLong2) {}
  
  public void onClick(View paramView)
  {
    paramView = new Intent(c.e.c, ComposeMessageActivity.class);
    paramView.putExtra("thread_id", a);
    paramView.putExtra("highlight", c.e.a);
    paramView.putExtra("select_id", b);
    c.e.c.startActivity(paramView);
  }
}

/* Location:
 * Qualified Name:     xn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import android.content.Intent;
import android.net.Uri;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.SearchActivity;

public class xk
  implements Runnable
{
  public xk(SearchActivity paramSearchActivity, Uri paramUri, String paramString) {}
  
  public void run()
  {
    try
    {
      long l1 = Long.parseLong(a.getQueryParameter("source_id"));
      long l2 = Long.parseLong(a.getQueryParameter("which_table"));
      l2 = SearchActivity.a(c, l1, l2);
      Intent localIntent = new Intent(c, ComposeMessageActivity.class);
      localIntent.putExtra("highlight", b);
      localIntent.putExtra("select_id", l1);
      localIntent.putExtra("thread_id", l2);
      c.startActivity(localIntent);
      c.finish();
      return;
    }
    catch (NumberFormatException localNumberFormatException) {}
  }
}

/* Location:
 * Qualified Name:     xk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
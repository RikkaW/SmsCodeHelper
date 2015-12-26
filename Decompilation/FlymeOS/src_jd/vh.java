import android.content.Intent;
import android.net.Uri;
import com.android.mms.MmsApp;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.MeizuSearchActivity;

public class vh
  implements Runnable
{
  public vh(MeizuSearchActivity paramMeizuSearchActivity, Uri paramUri) {}
  
  public void run()
  {
    int i = 0;
    for (;;)
    {
      try
      {
        long l2 = Long.parseLong(a.getQueryParameter("source_id"));
        Intent localIntent = new Intent(b, ComposeMessageActivity.class);
        if (a.getQueryParameter("which_table") != null)
        {
          l1 = Long.parseLong(a.getQueryParameter("which_table"));
          l1 = MeizuSearchActivity.a(b, l2, l1, false);
          localIntent.putExtra("highlight", MeizuSearchActivity.j(b));
          localIntent.putExtra("select_id", l2);
          localIntent.putExtra("thread_id", l1);
          if (l1 > 0L)
          {
            b.startActivity(localIntent);
            MeizuSearchActivity.k(b);
          }
        }
        else
        {
          if (a.getQueryParameter("thread_id") == null) {
            break label277;
          }
          String str1 = a.getQueryParameter("msg_type");
          String str2 = a.getQueryParameter("group_msg_id");
          if ("mms".equals(str1)) {}
          l1 = Long.parseLong(a.getQueryParameter("thread_id"));
          gr localgr = gr.a(MmsApp.c(), l1, false);
          if (localgr != null) {
            l1 = localgr.e();
          }
          localIntent.putExtra("highlight", MeizuSearchActivity.j(b));
          localIntent.putExtra("select_id", l2);
          localIntent.putExtra("thread_id", l1);
          localIntent.putExtra("msg_type", str1);
          localIntent.putExtra("group_msg_id", str2);
          i = 1;
          continue;
        }
        if (i == 0) {
          break;
        }
        MeizuSearchActivity.l(b);
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        return;
      }
      label277:
      long l1 = -1L;
    }
  }
}

/* Location:
 * Qualified Name:     vh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
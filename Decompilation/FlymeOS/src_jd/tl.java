import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import com.android.mms.MmsApp;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.ComposeMessageActivity.a;
import com.android.mms.ui.ComposeMessageActivity.e;

public class tl
  extends AsyncTask<Void, Void, Void>
{
  public tl(ComposeMessageActivity.e parame) {}
  
  protected Void a(Void... paramVarArgs)
  {
    boolean bool = true;
    label79:
    label111:
    ComposeMessageActivity.a locala;
    Uri localUri;
    if (ComposeMessageActivity.e.a(a).j())
    {
      hb.a(ComposeMessageActivity.e.a(a).Q());
      MmsApp.c().e().a(aa).t);
      paramVarArgs = Boolean.valueOf(false);
      if (a.a.b == null) {
        break label199;
      }
      localObject = a.a.b.getCursor();
      if (localObject != null)
      {
        ((Cursor)localObject).moveToLast();
        if (((Cursor)localObject).getLong(1) != aa).e) {
          break label204;
        }
        paramVarArgs = Boolean.valueOf(bool);
      }
      locala = ComposeMessageActivity.h(a.a);
      localUri = aa).t;
      if (!aa).j) {
        break label209;
      }
    }
    label199:
    label204:
    label209:
    for (Object localObject = null;; localObject = "locked=0")
    {
      locala.startDelete(9700, paramVarArgs, localUri, (String)localObject, null);
      return null;
      if (!ComposeMessageActivity.e.a(a).E()) {
        break;
      }
      hb.a(ComposeMessageActivity.e.a(a).K());
      break;
      localObject = null;
      break label79;
      bool = false;
      break label111;
    }
  }
}

/* Location:
 * Qualified Name:     tl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
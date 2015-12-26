import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Telephony.MmsSms;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.ComposeMessageActivity.a;
import com.android.mms.ui.ComposeMessageActivity.e;
import java.util.ArrayList;
import java.util.List;

public class tm
  extends AsyncTask<Void, Void, Void>
{
  public tm(ComposeMessageActivity.e parame, boolean paramBoolean) {}
  
  protected Void a(Void... paramVarArgs)
  {
    Uri localUri = Uri.withAppendedPath(Telephony.MmsSms.CONTENT_URI, "messages_group_delete");
    paramVarArgs = new StringBuilder();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < ComposeMessageActivity.e.b(b).length)
    {
      if ((ComposeMessageActivity.e.b(b)[i].j()) || (ComposeMessageActivity.e.b(b)[i].E())) {
        localArrayList.add(ComposeMessageActivity.e.b(b)[i]);
      }
      paramVarArgs.append(bb)[i].t).append(";");
      i += 1;
    }
    String str = paramVarArgs.substring(0, paramVarArgs.length() - 1);
    if (a) {}
    for (paramVarArgs = null;; paramVarArgs = "locked=0")
    {
      ComposeMessageActivity.h(b.a).startDelete(9701, localArrayList, localUri, paramVarArgs, new String[] { str });
      return null;
    }
  }
}

/* Location:
 * Qualified Name:     tm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
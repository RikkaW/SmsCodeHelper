import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Telephony.MmsSms;
import com.android.mms.fragmentstyle.FavoriteActivity;
import com.android.mms.fragmentstyle.FavoriteActivity.a;
import java.util.ArrayList;
import java.util.List;

public class jp
  extends AsyncTask<Void, Void, Void>
{
  public jp(FavoriteActivity paramFavoriteActivity, vv[] paramArrayOfvv, boolean paramBoolean) {}
  
  protected Void a(Void... paramVarArgs)
  {
    Uri localUri = Uri.withAppendedPath(Telephony.MmsSms.CONTENT_URI, "group_delete_favorite");
    paramVarArgs = new StringBuilder();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < a.length)
    {
      if ((a[i].j()) || (a[i].E())) {
        localArrayList.add(a[i]);
      }
      paramVarArgs.append(a[i].t).append(";");
      i += 1;
    }
    String str = paramVarArgs.substring(0, paramVarArgs.length() - 1);
    if (b) {}
    for (paramVarArgs = null;; paramVarArgs = "locked=0")
    {
      FavoriteActivity.g(c).startDelete(1811, localArrayList, localUri, paramVarArgs, new String[] { str });
      return null;
    }
  }
}

/* Location:
 * Qualified Name:     jp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
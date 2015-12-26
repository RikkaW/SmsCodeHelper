import android.os.AsyncTask;
import android.telephony.MzTelephony.MmsSmsExt;
import android.text.TextUtils;
import com.android.mms.MmsApp;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.ui.MeizuSearchActivity;
import com.android.mms.ui.MeizuSearchActivity.a;
import com.android.mms.view.MeizuSearchListItem;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class vb
  extends AsyncTask<Void, Void, Void>
{
  public vb(MeizuSearchActivity paramMeizuSearchActivity, HashMap paramHashMap) {}
  
  protected Void a(Void... paramVarArgs)
  {
    Object localObject2 = new HashSet();
    Object localObject1 = new HashSet();
    paramVarArgs = new HashSet();
    Iterator localIterator = a.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject3 = (Map.Entry)localIterator.next();
      long[] arrayOfLong = MeizuSearchActivity.a((String)((Map.Entry)localObject3).getKey());
      localObject3 = MeizuSearchActivity.b((String)((Map.Entry)localObject3).getValue());
      if ((arrayOfLong != null) && (localObject3 != null) && (localObject3[0] != 1L)) {
        if (MeizuSearchListItem.a(arrayOfLong[2]))
        {
          ((Collection)localObject2).add(Long.valueOf(arrayOfLong[1]));
          gr.a(MmsApp.c(), arrayOfLong[1], true).c(false);
        }
        else if (arrayOfLong[2] == 2L)
        {
          ((Collection)localObject1).add(Long.valueOf(arrayOfLong[0]));
        }
        else
        {
          paramVarArgs.add(Long.valueOf(arrayOfLong[0]));
        }
      }
    }
    localObject2 = TextUtils.join(";", (Iterable)localObject2);
    localObject1 = TextUtils.join(";", (Iterable)localObject1);
    paramVarArgs = TextUtils.join(";", paramVarArgs);
    gr.b();
    MeizuSearchActivity.d(b).startUpdate(2, null, MzTelephony.MmsSmsExt.CONTENT_MSG_AND_THREAD_MIX_OPERATE_URI, gr.e, "(read=0 OR seen=0)", new String[] { localObject2, localObject1, paramVarArgs });
    MessagingNotification.a(b, -2L);
    if (a != null) {
      a.clear();
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     vb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
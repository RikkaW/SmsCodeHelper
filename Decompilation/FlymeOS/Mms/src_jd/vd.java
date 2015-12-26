import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.LongSparseArray;
import android.widget.ListView;
import com.android.mms.MmsApp;
import com.android.mms.fragmentstyle.ConversationListFragment;
import com.android.mms.ui.MeizuSearchActivity;
import com.android.mms.ui.MeizuSearchActivity.a;
import com.android.mms.view.MeizuSearchListItem;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class vd
  extends AsyncTask<Void, Void, Void>
{
  public vd(MeizuSearchActivity paramMeizuSearchActivity, HashMap paramHashMap, boolean paramBoolean) {}
  
  protected Void a(Void... paramVarArgs)
  {
    Object localObject2 = a.keySet().iterator();
    paramVarArgs = new HashSet();
    HashSet localHashSet1 = new HashSet();
    HashSet localHashSet2 = new HashSet();
    Object localObject1 = new LongSparseArray();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = MeizuSearchActivity.a((String)((Iterator)localObject2).next());
      if (localObject3 != null) {
        if ((b) || (MeizuSearchListItem.a(localObject3[2])))
        {
          paramVarArgs.add(Long.valueOf(localObject3[1]));
        }
        else if (localObject3[2] == 2L)
        {
          localHashSet1.add(Long.valueOf(localObject3[0]));
          if (localObject3[3] != 3L) {
            MeizuSearchActivity.a(c, (LongSparseArray)localObject1, (long[])localObject3);
          }
        }
        else
        {
          localHashSet2.add(Long.valueOf(localObject3[0]));
          if (localObject3[3] != 3L) {
            MeizuSearchActivity.a(c, (LongSparseArray)localObject1, (long[])localObject3);
          }
        }
      }
    }
    Log.d("MeizuSearchActivity", "deleteCheckedMessages(), threadIds is " + paramVarArgs.size());
    int i = 0;
    while (i < ((LongSparseArray)localObject1).size())
    {
      long l = ((LongSparseArray)localObject1).keyAt(i);
      localObject2 = gr.a(MmsApp.c(), l, true);
      if ((localObject2 != null) && (((gr)localObject2).k() == ((Long)((LongSparseArray)localObject1).get(l)).longValue())) {
        paramVarArgs.add(Long.valueOf(l));
      }
      i += 1;
    }
    ((LongSparseArray)localObject1).clear();
    Log.d("MeizuSearchActivity", "deleteCheckedMessages(), after threadIds is " + paramVarArgs.size());
    localObject1 = TextUtils.join(";", paramVarArgs);
    localObject2 = TextUtils.join(";", localHashSet1);
    Object localObject3 = TextUtils.join(";", localHashSet2);
    MeizuSearchActivity.a(c, MeizuSearchActivity.c(c).getFirstVisiblePosition());
    MeizuSearchActivity.e(c, true);
    MeizuSearchActivity.b(c, true);
    MeizuSearchActivity.a locala = MeizuSearchActivity.d(c);
    if (paramVarArgs.size() > 0) {}
    for (boolean bool = true;; bool = false)
    {
      gr.a(locala, 1, false, bool, new String[] { localObject1, localObject2, localObject3 }, new Collection[] { paramVarArgs, localHashSet1, localHashSet2 });
      if (paramVarArgs.size() > 0) {
        ConversationListFragment.a(paramVarArgs, false);
      }
      if (a != null) {
        a.clear();
      }
      return null;
    }
  }
}

/* Location:
 * Qualified Name:     vd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
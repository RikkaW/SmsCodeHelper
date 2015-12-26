package com.android.mms.ui;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.widget.ListView;
import com.android.mms.MmsApp;
import com.android.mms.fragmentstyle.ConversationListFragment;
import gm;
import gq;
import gr;
import java.util.ArrayList;

class SelectConversationList$a
  extends AsyncTask<Parcelable[], Integer, ArrayList<Integer>>
{
  Cursor a;
  
  public SelectConversationList$a(SelectConversationList paramSelectConversationList, Cursor paramCursor)
  {
    a = paramCursor;
  }
  
  protected ArrayList<Integer> a(Parcelable[]... paramVarArgs)
  {
    Object localObject = paramVarArgs[0];
    b.a = new ArrayList();
    paramVarArgs = new ArrayList();
    int i = 0;
    while (i < localObject.length)
    {
      String str = ((ContentValues)localObject[i]).getAsString("data1");
      b.a.add(str);
      i += 1;
    }
    i = 0;
    for (;;)
    {
      try
      {
        if (i >= SelectConversationList.a(b).getCount()) {
          continue;
        }
        a.moveToPosition(i);
        localObject = gr.a(MmsApp.c(), a).h();
        if (((gq)localObject).size() != 1) {
          break label236;
        }
        localObject = (gm)((gq)localObject).get(0);
        j = 0;
      }
      catch (Exception localException)
      {
        int j;
        localException.printStackTrace();
        return paramVarArgs;
        j += 1;
        continue;
        return paramVarArgs;
      }
      finally
      {
        a.close();
      }
      if (j < b.a.size()) {
        if (SelectConversationList.a(((gm)localObject).d()).equals(b.a.get(j))) {
          paramVarArgs.add(Integer.valueOf(i));
        }
      }
      label236:
      i += 1;
    }
  }
  
  protected void a(ArrayList<Integer> paramArrayList)
  {
    super.onPostExecute(paramArrayList);
    SelectConversationList.c(b).a();
    if (paramArrayList == null) {
      return;
    }
    SelectConversationList.a(b, false);
    int i = 0;
    while (i < paramArrayList.size())
    {
      SelectConversationList.a(b).setItemChecked(((Integer)paramArrayList.get(i)).intValue(), true);
      i += 1;
    }
    SelectConversationList.a(b, true);
    ((SelectConversationList.b)b.c()).c();
    SelectConversationList.c(b).a(null);
  }
  
  protected void onPreExecute()
  {
    SelectConversationList.c(b).a(2131493788);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SelectConversationList.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.android.mms.fragmentstyle;

import android.os.AsyncTask;
import com.android.mms.MmsApp;
import gr;
import java.util.HashSet;

class ConversationListFragment$d
  extends AsyncTask<Void, Void, Void>
{
  private HashSet<Long> b;
  
  public ConversationListFragment$d(HashSet<Long> paramHashSet)
  {
    HashSet localHashSet;
    b = localHashSet;
  }
  
  protected Void a(Void... paramVarArgs)
  {
    gr.a(MmsApp.c(), b);
    return null;
  }
  
  protected void a(Void paramVoid)
  {
    if (b.size() > 20) {
      a.a();
    }
  }
  
  protected void onPreExecute()
  {
    if (b.size() > 20) {
      a.a(2131493852);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.ConversationListFragment.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
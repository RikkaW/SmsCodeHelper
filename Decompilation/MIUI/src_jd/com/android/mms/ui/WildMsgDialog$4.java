package com.android.mms.ui;

import android.os.AsyncTask;

class WildMsgDialog$4
  extends AsyncTask<Void, Void, Void>
{
  WildMsgDialog$4(WildMsgDialog paramWildMsgDialog) {}
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    WildMsgDialog.access$700(this$0);
    return null;
  }
  
  protected void onCancelled()
  {
    WildMsgDialog.access$802(this$0, null);
    WildMsgDialog.access$600(this$0);
  }
  
  protected void onPostExecute(Void paramVoid)
  {
    WildMsgDialog.access$802(this$0, null);
    if (WildMsgDialog.access$900(this$0)) {
      WildMsgDialog.access$1000(this$0);
    }
    while (!WildMsgDialog.access$300(this$0)) {
      return;
    }
    WildMsgDialog.access$1100(this$0, WildMsgDialog.access$100(this$0));
  }
  
  protected void onPreExecute()
  {
    WildMsgDialog.access$600(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.WildMsgDialog.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.android.mms.ui;

import android.os.AsyncTask;
import miui.yellowpage.MiPubUtils;

class SingleRecipientConversationActivity$9
  extends AsyncTask<Void, Void, Void>
{
  SingleRecipientConversationActivity$9(SingleRecipientConversationActivity paramSingleRecipientConversationActivity) {}
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    MiPubUtils.getYellowPageMenu(this$0, this$0.mYellowPageId.longValue(), true, 4);
    return null;
  }
  
  protected void onPostExecute(Void paramVoid)
  {
    SingleRecipientConversationActivity.access$702(this$0, null);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SingleRecipientConversationActivity.9
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
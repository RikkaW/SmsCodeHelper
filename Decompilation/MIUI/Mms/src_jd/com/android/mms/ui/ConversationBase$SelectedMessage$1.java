package com.android.mms.ui;

import android.os.AsyncTask;
import android.widget.Toast;
import com.android.mms.util.SimCardManager;

class ConversationBase$SelectedMessage$1
  extends AsyncTask<Void, Void, Integer>
{
  ConversationBase$SelectedMessage$1(ConversationBase.SelectedMessage paramSelectedMessage, int paramInt) {}
  
  protected Integer doInBackground(Void... paramVarArgs)
  {
    if (ConversationBase.SelectedMessage.access$3100(this$1).isOutMessage()) {}
    for (int i = 5;; i = 1) {
      return Integer.valueOf(SimCardManager.getInstance(ConversationBase.SelectedMessage.access$3200(this$1)).saveMessageToIcc(ConversationBase.SelectedMessage.access$3100(this$1).getAddress(), ConversationBase.SelectedMessage.access$3100(this$1).getBody(), ConversationBase.SelectedMessage.access$3100(this$1).getDate(), i, val$slotId));
    }
  }
  
  protected void onPostExecute(Integer paramInteger)
  {
    if (paramInteger.intValue() == 1000)
    {
      Toast.makeText(ConversationBase.SelectedMessage.access$3200(this$1), 2131362241, 0).show();
      return;
    }
    if (paramInteger.intValue() == 1002)
    {
      Toast.makeText(ConversationBase.SelectedMessage.access$3200(this$1), 2131361800, 0).show();
      return;
    }
    Toast.makeText(ConversationBase.SelectedMessage.access$3200(this$1), 2131362242, 0).show();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.SelectedMessage.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
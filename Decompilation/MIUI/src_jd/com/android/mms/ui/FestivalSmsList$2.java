package com.android.mms.ui;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;
import com.android.mms.data.FestivalDatabase;

class FestivalSmsList$2
  extends AsyncTask<Void, Void, Boolean>
{
  FestivalSmsList$2(FestivalSmsList paramFestivalSmsList) {}
  
  protected Boolean doInBackground(Void... paramVarArgs)
  {
    return Boolean.valueOf(FestivalDatabase.getInstance().getMoreMessages(FestivalSmsList.access$100(this$0)));
  }
  
  protected void onPostExecute(Boolean paramBoolean)
  {
    FestivalSmsList.access$202(this$0, null);
    if (!paramBoolean.booleanValue()) {
      Toast.makeText(this$0, 2131362060, 0).show();
    }
    for (;;)
    {
      FestivalSmsList.access$302(this$0, false);
      FestivalSmsList.access$400(this$0).setVisibility(8);
      return;
      FestivalSmsList.access$000(this$0).requery();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.FestivalSmsList.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
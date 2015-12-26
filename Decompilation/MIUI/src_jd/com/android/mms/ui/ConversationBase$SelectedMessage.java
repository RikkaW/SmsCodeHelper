package com.android.mms.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.SimCardManager;

class ConversationBase$SelectedMessage
{
  private Context mContext;
  private MessageItem mSelectedItem;
  
  public ConversationBase$SelectedMessage(ConversationBase paramConversationBase, Context paramContext, MessageItem paramMessageItem)
  {
    mContext = paramContext;
    mSelectedItem = paramMessageItem;
  }
  
  private void createSimPickerDialog()
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    LinearLayout localLinearLayout = (LinearLayout)View.inflate(mContext, 2130968704, null);
    localLinearLayout.addView(createSimPickerItem(0), localLayoutParams);
    localLinearLayout.addView(createSimPickerItem(1), localLayoutParams);
    ConversationBase.access$3302(this$0, new AlertDialog.Builder(mContext).setTitle(2131362032).setView(localLinearLayout).show());
  }
  
  private View createSimPickerItem(final int paramInt)
  {
    LinearLayout localLinearLayout = (LinearLayout)View.inflate(mContext, 2130968705, null);
    ((ImageView)localLinearLayout.findViewById(2131820664)).setImageResource(MSimUtils.getSimIconResId(paramInt));
    Object localObject = null;
    switch (paramInt)
    {
    }
    for (;;)
    {
      ((TextView)localLinearLayout.findViewById(2131820628)).setText((CharSequence)localObject);
      localLinearLayout.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ConversationBase.SelectedMessage.this.saveToIcc(paramInt);
          if (ConversationBase.access$3300(this$0) != null)
          {
            ConversationBase.access$3300(this$0).dismiss();
            ConversationBase.access$3302(this$0, null);
          }
        }
      });
      return localLinearLayout;
      if (!MSimUtils.isSimInserted(0))
      {
        localObject = mContext.getString(2131362235);
      }
      else
      {
        localObject = MSimUtils.getSimDisplayName(mContext, 0);
        continue;
        if (!MSimUtils.isSimInserted(1)) {
          localObject = mContext.getString(2131362236);
        } else {
          localObject = MSimUtils.getSimDisplayName(mContext, 1);
        }
      }
    }
  }
  
  private void saveToIcc(final int paramInt)
  {
    if (mSelectedItem.isSms()) {
      new AsyncTask()
      {
        protected Integer doInBackground(Void... paramAnonymousVarArgs)
        {
          if (mSelectedItem.isOutMessage()) {}
          for (int i = 5;; i = 1) {
            return Integer.valueOf(SimCardManager.getInstance(mContext).saveMessageToIcc(mSelectedItem.getAddress(), mSelectedItem.getBody(), mSelectedItem.getDate(), i, paramInt));
          }
        }
        
        protected void onPostExecute(Integer paramAnonymousInteger)
        {
          if (paramAnonymousInteger.intValue() == 1000)
          {
            Toast.makeText(mContext, 2131362241, 0).show();
            return;
          }
          if (paramAnonymousInteger.intValue() == 1002)
          {
            Toast.makeText(mContext, 2131361800, 0).show();
            return;
          }
          Toast.makeText(mContext, 2131362242, 0).show();
        }
      }.execute(new Void[0]);
    }
  }
  
  public void saveMessageToSim()
  {
    if (MSimUtils.isMSimInserted()) {
      createSimPickerDialog();
    }
    int i;
    do
    {
      return;
      i = MSimUtils.getInsertedSlotId();
    } while (i == -1);
    saveToIcc(i);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.SelectedMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
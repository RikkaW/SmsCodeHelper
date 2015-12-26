package com.android.mms.ui;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import com.android.mms.MmsApp;
import com.android.mms.data.Conversation;

public class ServiceProviderConversationListActivity$ConfirmMarkAsReadDialogFragment
  extends DialogFragment
{
  private int mSelectedCategory = 1;
  
  public ServiceProviderConversationListActivity$ConfirmMarkAsReadDialogFragment() {}
  
  public ServiceProviderConversationListActivity$ConfirmMarkAsReadDialogFragment(int paramInt)
  {
    mSelectedCategory = paramInt;
  }
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    paramBundle = new AlertDialog.Builder(getActivity());
    paramBundle.setTitle(2131362276).setIconAttribute(16843605).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        ServiceProviderConversationListActivity.access$002(new AsyncTask()
        {
          protected Void doInBackground(Void... paramAnonymous2VarArgs)
          {
            Conversation.markSPAsReadSync(MmsApp.getApp(), mSelectedCategory);
            return null;
          }
          
          protected void onPostExecute(Void paramAnonymous2Void)
          {
            ServiceProviderConversationListActivity.access$300();
            ServiceProviderConversationListActivity.access$002(null);
          }
          
          protected void onPreExecute()
          {
            ServiceProviderConversationListActivity.access$200(getActivity(), getFragmentManager());
          }
        });
        ServiceProviderConversationListActivity.access$000().execute(new Void[] { (Void)null });
      }
    }).setNegativeButton(17039360, null);
    return paramBundle.create();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ServiceProviderConversationListActivity.ConfirmMarkAsReadDialogFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.android.mms.ui;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.MmsApp;
import com.android.mms.data.Conversation;
import miui.app.ActionBar;
import miui.app.Activity;

public class ServiceProviderConversationListActivity
  extends Activity
{
  private static DialogFragment sMarkAsReadDialog;
  private static AsyncTask<Void, Void, Void> sMarkAsReadTask;
  private ServiceProviderConversationFragment mFragment;
  
  private static void dismissMarkAsReadProgress()
  {
    if (sMarkAsReadDialog != null) {
      sMarkAsReadDialog.dismissAllowingStateLoss();
    }
    sMarkAsReadDialog = null;
  }
  
  private static void showMarkAsReadProgress(Context paramContext, FragmentManager paramFragmentManager)
  {
    if (sMarkAsReadDialog == null)
    {
      sMarkAsReadDialog = (DialogFragment)paramFragmentManager.findFragmentByTag("markAsReadProgress");
      if (sMarkAsReadDialog == null) {
        sMarkAsReadDialog = new ProgressDialogFragment(paramContext.getString(2131362278));
      }
    }
    sMarkAsReadDialog.setCancelable(false);
    sMarkAsReadDialog.show(paramFragmentManager, "markAsReadProgress");
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968692);
    paramBundle = getFragmentManager();
    FragmentTransaction localFragmentTransaction = paramBundle.beginTransaction();
    mFragment = ((ServiceProviderConversationFragment)paramBundle.findFragmentByTag("ServiceProviderConversationFragment"));
    if (mFragment == null)
    {
      mFragment = new ServiceProviderConversationFragment();
      localFragmentTransaction.add(2131820849, mFragment, "ServiceProviderConversationFragment");
    }
    ActionBar localActionBar = getActionBar();
    localActionBar.setTitle(2131362267);
    localActionBar.setHomeButtonEnabled(true);
    localActionBar.setCustomView(2130968630);
    localActionBar.setDisplayShowCustomEnabled(true);
    localActionBar.getCustomView().findViewById(2131820672).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if ((ServiceProviderConversationListActivity.sMarkAsReadTask == null) && (mFragment != null)) {
          new ServiceProviderConversationListActivity.ConfirmMarkAsReadDialogFragment(mFragment.mSelectedCategory).show(getFragmentManager(), "markAsReadDialog");
        }
      }
    });
    localFragmentTransaction.commitAllowingStateLoss();
    paramBundle.executePendingTransactions();
    if (sMarkAsReadTask != null) {
      showMarkAsReadProgress(this, paramBundle);
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default: 
      return super.onKeyDown(paramInt, paramKeyEvent);
    }
    MessageUtils.launchMessagePreference(this);
    return true;
  }
  
  public static class ConfirmMarkAsReadDialogFragment
    extends DialogFragment
  {
    private int mSelectedCategory = 1;
    
    public ConfirmMarkAsReadDialogFragment() {}
    
    public ConfirmMarkAsReadDialogFragment(int paramInt)
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
              ServiceProviderConversationListActivity.showMarkAsReadProgress(getActivity(), getFragmentManager());
            }
          });
          ServiceProviderConversationListActivity.sMarkAsReadTask.execute(new Void[] { (Void)null });
        }
      }).setNegativeButton(17039360, null);
      return paramBundle.create();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ServiceProviderConversationListActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
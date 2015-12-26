package com.android.mms.ui;

import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import com.android.mms.MmsApp;
import java.util.ArrayList;
import miui.app.AlertDialog;
import miui.app.AlertDialog.Builder;
import miui.provider.ExtraTelephony.SimCards;

public class WildMsgDialog
{
  private boolean[] mCheckedItems;
  private Context mContext;
  private AlertDialog mDialog;
  private boolean mFromSettings;
  private String[] mItems;
  private DialogInterface.OnMultiChoiceClickListener mOnMultiChoiceClickListener = new DialogInterface.OnMultiChoiceClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, boolean paramAnonymousBoolean)
    {
      if ((mCheckedItems != null) && (mCheckedItems.length > paramAnonymousInt)) {
        mCheckedItems[paramAnonymousInt] = paramAnonymousBoolean;
      }
    }
  };
  private DialogInterface.OnClickListener mOnNegativeClickListener = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      paramAnonymousInt = Settings.System.getInt(mContext.getContentResolver(), "mms_sync_wild_msg_state", 0);
      if ((paramAnonymousInt != 4) && (paramAnonymousInt != 5)) {
        Settings.System.putInt(mContext.getContentResolver(), "mms_sync_wild_msg_state", 2);
      }
      if (!mFromSettings) {
        WildMsgDialog.this.showWildGuideSettingsDialog(mContext);
      }
      WildMsgDialog.this.dismissDialog();
    }
  };
  private DialogInterface.OnClickListener mOnPositiveClickListener = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      paramAnonymousInt = Settings.System.getInt(mContext.getContentResolver(), "mms_sync_wild_msg_state", 0);
      if ((paramAnonymousInt != 4) && (paramAnonymousInt != 5)) {
        Settings.System.putInt(mContext.getContentResolver(), "mms_sync_wild_msg_state", 2);
      }
      int i = 0;
      paramAnonymousInt = 0;
      paramAnonymousDialogInterface = new ArrayList();
      int j = i;
      if (mItems != null)
      {
        j = i;
        if (mCheckedItems != null)
        {
          i = 0;
          j = paramAnonymousInt;
          if (i < mCheckedItems.length)
          {
            if (mCheckedItems[i] != 0) {
              paramAnonymousDialogInterface.add(mItems[i]);
            }
            for (;;)
            {
              i += 1;
              break;
              paramAnonymousInt = 1;
            }
          }
        }
      }
      Log.v("WildMsgDialog", "update list.size is " + paramAnonymousDialogInterface.size());
      if (paramAnonymousDialogInterface.size() > 0) {
        new Thread(new Runnable()
        {
          public void run()
          {
            Application localApplication = MmsApp.getApp();
            ContentValues localContentValues = new ContentValues(1);
            localContentValues.put("download_status", Integer.valueOf(1));
            int i = localApplication.getContentResolver().update(ExtraTelephony.SimCards.CONTENT_URI, localContentValues, val$selection, null);
            Log.v("WildMsgDialog", "update number to sim cards is " + i);
            if (i > 0)
            {
              Log.v("WildMsgDialog", "force sync");
              MessageUtils.forceSync(localApplication);
            }
          }
        }, "update_sim_cards_thread").start();
      }
      if ((j != 0) && (!mFromSettings)) {
        WildMsgDialog.this.showWildGuideSettingsDialog(mContext);
      }
      WildMsgDialog.this.dismissDialog();
    }
  };
  private AsyncTask<Void, Void, Void> mTask;
  
  public WildMsgDialog(Context paramContext, boolean paramBoolean)
  {
    mContext = paramContext;
    mFromSettings = paramBoolean;
  }
  
  private void clear()
  {
    dismissDialog();
    mDialog = null;
    mItems = null;
    mCheckedItems = null;
  }
  
  private void dismissDialog()
  {
    if ((mDialog != null) && (mDialog.isShowing()))
    {
      Log.v("WildMsgDialog", "dismiss wild message dialog");
      mDialog.dismiss();
    }
    mDialog = null;
  }
  
  private void load()
  {
    mItems = null;
    mCheckedItems = null;
    Object localObject1;
    if (mFromSettings) {
      localObject1 = null;
    }
    for (;;)
    {
      localObject1 = mContext.getContentResolver().query(ExtraTelephony.SimCards.CONTENT_URI, new String[] { "number" }, (String)localObject1, null, null);
      int i;
      if (localObject1 != null) {
        i = 0;
      }
      try
      {
        int j = ((Cursor)localObject1).getCount();
        Log.v("WildMsgDialog", "loadNumberFromSimCards count is " + j);
        if (j > 0)
        {
          mItems = new String[j];
          ((Cursor)localObject1).moveToPosition(-1);
          for (;;)
          {
            if ((((Cursor)localObject1).moveToNext()) && (i < j))
            {
              mItems[i] = ((Cursor)localObject1).getString(0);
              i += 1;
              continue;
              localObject1 = "download_status=0";
              break;
            }
          }
        }
        ((Cursor)localObject1).close();
        if (mItems != null)
        {
          mCheckedItems = new boolean[mItems.length];
          i = 0;
          while (i < mCheckedItems.length)
          {
            mCheckedItems[i] = true;
            i += 1;
          }
        }
        return;
      }
      finally
      {
        ((Cursor)localObject1).close();
      }
    }
  }
  
  private boolean shouldShowDialog()
  {
    if ((mItems == null) || (mItems.length <= 0))
    {
      Log.v("WildMsgDialog", "shouldShowDialog mItems is empty");
      return false;
    }
    if ((mCheckedItems == null) || (mCheckedItems.length <= 0))
    {
      Log.v("WildMsgDialog", "shouldShowDialog mCheckedItems is empty");
      return false;
    }
    Log.v("WildMsgDialog", "shouldShowDialog is true");
    return true;
  }
  
  private void showDialog()
  {
    dismissDialog();
    Object localObject = new AlertDialog.Builder(mContext);
    ((AlertDialog.Builder)localObject).setTitle(2131362196);
    ((AlertDialog.Builder)localObject).setMultiChoiceItems(mItems, mCheckedItems, mOnMultiChoiceClickListener);
    ((AlertDialog.Builder)localObject).setCancelable(mFromSettings);
    ((AlertDialog.Builder)localObject).setPositiveButton(2131361891, mOnPositiveClickListener);
    ((AlertDialog.Builder)localObject).setNegativeButton(2131361892, mOnNegativeClickListener);
    mDialog = ((AlertDialog.Builder)localObject).show();
    localObject = mDialog.getListView();
    int i = 0;
    while (i < mCheckedItems.length)
    {
      ((ListView)localObject).setItemChecked(i, mCheckedItems[i]);
      i += 1;
    }
    Log.v("WildMsgDialog", "show wild message dialog");
  }
  
  private void showGuideCloudDialog(Context paramContext)
  {
    paramContext = new AlertDialog.Builder(paramContext);
    paramContext.setMessage(2131362198);
    paramContext.setCancelable(true);
    paramContext.setPositiveButton(2131361891, null);
    paramContext.show();
  }
  
  private void showWildGuideSettingsDialog(Context paramContext)
  {
    paramContext = new AlertDialog.Builder(paramContext);
    paramContext.setMessage(2131362197);
    paramContext.setCancelable(true);
    paramContext.setPositiveButton(2131361891, null);
    paramContext.show();
  }
  
  public void cancel()
  {
    if (mTask != null) {
      mTask.cancel(true);
    }
    mTask = null;
    clear();
  }
  
  public void show()
  {
    cancel();
    mTask = new AsyncTask()
    {
      protected Void doInBackground(Void... paramAnonymousVarArgs)
      {
        WildMsgDialog.this.load();
        return null;
      }
      
      protected void onCancelled()
      {
        WildMsgDialog.access$802(WildMsgDialog.this, null);
        WildMsgDialog.this.clear();
      }
      
      protected void onPostExecute(Void paramAnonymousVoid)
      {
        WildMsgDialog.access$802(WildMsgDialog.this, null);
        if (WildMsgDialog.this.shouldShowDialog()) {
          WildMsgDialog.this.showDialog();
        }
        while (!mFromSettings) {
          return;
        }
        WildMsgDialog.this.showGuideCloudDialog(mContext);
      }
      
      protected void onPreExecute()
      {
        WildMsgDialog.this.clear();
      }
    };
    mTask.execute(new Void[0]);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.WildMsgDialog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
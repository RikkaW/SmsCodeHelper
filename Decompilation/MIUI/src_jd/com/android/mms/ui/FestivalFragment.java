package com.android.mms.ui;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.app.Dialog;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.LogTag;
import com.android.mms.MmsApp;
import com.android.mms.data.FestivalDatabase;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;

public class FestivalFragment
  extends Fragment
{
  private static UpdateTask sUpdateTask = null;
  private Activity mActivity;
  private View mDownloadingView;
  private boolean mIsForeground = false;
  private LinearLayout mList;
  private AsyncTask<Void, Void, ArrayList<LinearLayout>> mLoadTask;
  private boolean mNeedReload = true;
  private boolean mPickerMode;
  private SharedPreferences mPref;
  private View mRootView;
  private Dialog mShowNetworkingDlg;
  private BroadcastReceiver mUpdateCompleteReceiver;
  
  private void checkAndShowNetworkingDialog()
  {
    View localView = View.inflate(mActivity, 2130968609, null);
    final CheckBox localCheckBox = (CheckBox)localView.findViewById(2131820639);
    localCheckBox.setChecked(true);
    mShowNetworkingDlg = new AlertDialog.Builder(mActivity).setTitle(2131362335).setPositiveButton(getString(2131362337), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (localCheckBox.isChecked()) {
          FestivalFragment.setAllowNetworkingDialog(mActivity, false);
        }
        FestivalFragment.setAllowNetworking(mActivity, true);
        checkForUpdate(false);
      }
    }).setNegativeButton(17039360, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (localCheckBox.isChecked()) {
          FestivalFragment.setAllowNetworkingDialog(mActivity, false);
        }
        FestivalFragment.setAllowNetworking(mActivity, false);
      }
    }).setView(localView).setCancelable(false).show();
  }
  
  public static boolean isAllowNetworking(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_festival_allow_networking", false);
  }
  
  public static boolean isAllowNetworkingDialog(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_festival_networking", true);
  }
  
  private boolean loadCategories()
  {
    if (!mNeedReload) {
      return false;
    }
    if (mLoadTask != null)
    {
      LogTag.verbose("Previous load is running. Stop it.", new Object[0]);
      mLoadTask.cancel(true);
      mLoadTask = null;
    }
    if (mShowNetworkingDlg != null)
    {
      LogTag.verbose("Previous dialog is show. Dismiss it", new Object[0]);
      mShowNetworkingDlg.dismiss();
      mShowNetworkingDlg = null;
    }
    mLoadTask = new AsyncTask()
    {
      protected ArrayList<LinearLayout> doInBackground(Void... paramAnonymousVarArgs)
      {
        LogTag.verbose("Loading categories.", new Object[0]);
        LayoutInflater localLayoutInflater = mActivity.getLayoutInflater();
        int m = getResources().getDimensionPixelSize(2131427373);
        int n = getResources().getDimensionPixelSize(2131427374);
        Cursor localCursor = FestivalDatabase.getInstance().query("categories LEFT JOIN data ON categories.image_id=data._id", new String[] { "category_id", "row", "title", "data", "sms_count" }, null, null, null, null, null);
        if (localCursor == null)
        {
          LogTag.error("Null cursor while loading categories", new Object[0]);
          return null;
        }
        ArrayList localArrayList = Lists.newArrayList();
        for (;;)
        {
          String str;
          Object localObject2;
          Object localObject1;
          try
          {
            localCursor.moveToPosition(-1);
            LogTag.verbose("Got %d results.", new Object[] { Integer.valueOf(localCursor.getCount()) });
            int i = -1;
            paramAnonymousVarArgs = null;
            if (!localCursor.moveToNext()) {
              break;
            }
            if (Thread.currentThread().isInterrupted())
            {
              LogTag.warn("Load process interrupted.", new Object[0]);
              return null;
            }
            final long l = localCursor.getLong(0);
            str = localCursor.getString(2);
            localObject2 = localCursor.getBlob(3);
            localObject1 = null;
            if (localObject2 != null)
            {
              localObject1 = FestivalFragment.makeRoundImage(BitmapFactory.decodeByteArray((byte[])localObject2, 0, localObject2.length), 10, 10);
              localObject1 = new BitmapDrawable(getResources(), (Bitmap)localObject1);
            }
            localObject2 = localCursor.getString(4);
            int k = localCursor.getInt(1);
            int j = i;
            if (k != i)
            {
              j = k;
              paramAnonymousVarArgs = (LinearLayout)localLayoutInflater.inflate(2130968613, null);
              localArrayList.add(paramAnonymousVarArgs);
            }
            if (localObject1 != null)
            {
              localObject2 = localLayoutInflater.inflate(2130968611, null);
              ((View)localObject2).setBackground((Drawable)localObject1);
              localObject1 = new LinearLayout.LayoutParams(-2, -2, 1.0F);
              ((LinearLayout.LayoutParams)localObject1).setMargins(m, m, m, m);
              ((View)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject1);
              ((View)localObject2).setContentDescription(str);
              paramAnonymousVarArgs.addView((View)localObject2);
              localObject1 = localObject2;
              if (paramAnonymousVarArgs.getPaddingLeft() != n)
              {
                paramAnonymousVarArgs.setPadding(n, 0, n, 0);
                localObject1 = localObject2;
              }
              ((View)localObject1).setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymous2View)
                {
                  paramAnonymous2View = new Intent(mActivity, FestivalSmsList.class);
                  paramAnonymous2View.putExtra("category_id", l);
                  Log.v("FestivalFragment", "Clicked category " + l);
                  if (mPickerMode)
                  {
                    paramAnonymous2View.setAction("android.intent.action.PICK");
                    startActivityForResult(paramAnonymous2View, 1000);
                    return;
                  }
                  paramAnonymous2View.setAction("android.intent.action.VIEW");
                  startActivity(paramAnonymous2View);
                }
              });
              i = j;
              continue;
            }
            localObject1 = (LinearLayout)localLayoutInflater.inflate(2130968612, null);
          }
          finally
          {
            localCursor.close();
          }
          TextView localTextView1 = (TextView)((LinearLayout)localObject1).findViewById(2131820640);
          TextView localTextView2 = (TextView)((LinearLayout)localObject1).findViewById(2131820641);
          localTextView1.setText(str);
          localTextView2.setText((CharSequence)localObject2);
          ((View)localObject1).setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
          paramAnonymousVarArgs.addView((View)localObject1);
        }
        localCursor.close();
        return localArrayList;
      }
      
      protected void onPostExecute(ArrayList<LinearLayout> paramAnonymousArrayList)
      {
        if (paramAnonymousArrayList != null)
        {
          FestivalFragment.access$102(FestivalFragment.this, false);
          mList.removeAllViews();
          paramAnonymousArrayList = paramAnonymousArrayList.iterator();
          while (paramAnonymousArrayList.hasNext())
          {
            LinearLayout localLinearLayout = (LinearLayout)paramAnonymousArrayList.next();
            mList.addView(localLinearLayout);
          }
        }
        FestivalFragment.access$902(FestivalFragment.this, null);
        if (FestivalFragment.isAllowNetworkingDialog(mActivity)) {
          FestivalFragment.this.checkAndShowNetworkingDialog();
        }
        while (!FestivalFragment.isAllowNetworking(mActivity)) {
          return;
        }
        checkForUpdate(false);
      }
    }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    return true;
  }
  
  public static Bitmap makeRoundImage(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    if (paramBitmap == null) {
      return null;
    }
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint(1);
    int k = Math.min(i, j) / 3;
    paramInt1 = Math.min(paramInt1, k);
    paramInt2 = Math.min(paramInt2, k);
    localCanvas.drawARGB(0, 0, 0, 0);
    localPaint.setColor(-12434878);
    localCanvas.drawRoundRect(new RectF(0.0F, 0.0F, i, j), paramInt1, paramInt2, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
    return localBitmap;
  }
  
  private void onInvisible()
  {
    if (mLoadTask != null)
    {
      mLoadTask.cancel(true);
      mLoadTask = null;
    }
    if (mShowNetworkingDlg != null)
    {
      mShowNetworkingDlg.dismiss();
      mShowNetworkingDlg = null;
    }
  }
  
  private void onVisible()
  {
    View localView = mDownloadingView;
    if (sUpdateTask == null) {}
    for (int i = 8;; i = 0)
    {
      localView.setVisibility(i);
      if ((!loadCategories()) && (isAllowNetworkingDialog(mActivity))) {
        checkAndShowNetworkingDialog();
      }
      return;
    }
  }
  
  public static void setAllowNetworking(Context paramContext, boolean paramBoolean)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean("pref_key_festival_allow_networking", paramBoolean);
    paramContext.commit();
  }
  
  public static void setAllowNetworkingDialog(Context paramContext, boolean paramBoolean)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean("pref_key_festival_networking", paramBoolean);
    paramContext.commit();
  }
  
  public void checkForUpdate(boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      long l = mPref.getLong("update_timestamp", 0L);
      if (System.currentTimeMillis() - l < 86400000L) {
        return;
      }
    }
    if (sUpdateTask != null)
    {
      Log.v("FestivalFragment", "Updating is under way, do not initiate another one.");
      return;
    }
    NetworkInfo localNetworkInfo = ((ConnectivityManager)mActivity.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected()))
    {
      if (paramBoolean) {
        Toast.makeText(mActivity, 2131362060, 0).show();
      }
      Log.w("FestivalFragment", "No network. Don't check for update");
      return;
    }
    sUpdateTask = new UpdateTask(null);
    sUpdateTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    mDownloadingView.setVisibility(0);
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default: 
      throw new IllegalArgumentException("Invalid requestCode " + paramInt1);
    }
    if (((paramInt2 == -1) || (paramIntent != null)) && (mPickerMode)) {
      ((PickerActivity)mActivity).returnPickerResult(paramIntent);
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    mRootView = paramLayoutInflater.inflate(2130968610, null);
    mActivity = getActivity();
    if ((mActivity instanceof PickerActivity)) {
      mPickerMode = true;
    }
    mList = ((LinearLayout)mRootView.findViewById(16908298));
    mDownloadingView = mRootView.findViewById(2131820642);
    mPref = PreferenceManager.getDefaultSharedPreferences(mActivity);
    mUpdateCompleteReceiver = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        boolean bool = paramAnonymousIntent.getBooleanExtra("success", false);
        LogTag.verbose("Received broadcast of finishing update. Success=%s", new Object[] { Boolean.valueOf(bool) });
        FestivalFragment.access$102(FestivalFragment.this, true);
        if ((getUserVisibleHint()) && (mIsForeground))
        {
          LogTag.verbose("Activity is visible. Presenting result.", new Object[0]);
          if (bool) {
            break label95;
          }
          Toast.makeText(mActivity, 2131362060, 0).show();
        }
        for (;;)
        {
          mDownloadingView.setVisibility(8);
          return;
          label95:
          FestivalFragment.this.loadCategories();
        }
      }
    };
    paramLayoutInflater = new IntentFilter("com.android.mms.UPDATE_COMPLETE");
    mActivity.registerReceiver(mUpdateCompleteReceiver, paramLayoutInflater);
    setHasOptionsMenu(true);
    return mRootView;
  }
  
  public void onDestroyView()
  {
    if (mUpdateCompleteReceiver != null)
    {
      mActivity.unregisterReceiver(mUpdateCompleteReceiver);
      mUpdateCompleteReceiver = null;
    }
    super.onDestroyView();
  }
  
  public void onPause()
  {
    mIsForeground = false;
    if (getUserVisibleHint()) {
      onInvisible();
    }
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    mIsForeground = true;
    if (getUserVisibleHint()) {
      onVisible();
    }
  }
  
  public void setUserVisibleHint(boolean paramBoolean)
  {
    if (paramBoolean) {
      if (mIsForeground) {
        onVisible();
      }
    }
    for (;;)
    {
      super.setUserVisibleHint(paramBoolean);
      return;
      if (mIsForeground) {
        onInvisible();
      }
    }
  }
  
  private static final class UpdateTask
    extends AsyncTask<Void, Void, Boolean>
  {
    protected Boolean doInBackground(Void... paramVarArgs)
    {
      LogTag.verbose("Start updating messages", new Object[0]);
      return Boolean.valueOf(FestivalDatabase.getInstance().updateMessages());
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      LogTag.verbose("Done updating messages", new Object[0]);
      Application localApplication = MmsApp.getApp();
      if (paramBoolean.booleanValue())
      {
        LogTag.verbose("Succeeded updating messages", new Object[0]);
        localObject = PreferenceManager.getDefaultSharedPreferences(localApplication).edit();
        ((SharedPreferences.Editor)localObject).putLong("update_timestamp", System.currentTimeMillis());
        ((SharedPreferences.Editor)localObject).commit();
      }
      FestivalFragment.access$002(null);
      Object localObject = new Intent("com.android.mms.UPDATE_COMPLETE");
      ((Intent)localObject).putExtra("success", paramBoolean);
      localApplication.sendBroadcast((Intent)localObject);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.FestivalFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.android.mms.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.transaction.SendMessageService;
import java.util.ArrayList;

public class TimedMessageExpiredActivity
  extends Activity
{
  private ExpiredTimedMessageListAdapter mAdapter;
  private final View.OnClickListener mCloseOnClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      TimedMessageExpiredActivity.this.markExpiredMsgAsFailed(false);
      finish();
    }
  };
  private BroadcastReceiver mHomeKeyExpiredMsgReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if ("homekey".equals(paramAnonymousIntent.getStringExtra("reason")))
      {
        TimedMessageExpiredActivity.this.markExpiredMsgAsFailed(false);
        finish();
      }
    }
  };
  private MessageListView mListView;
  private final AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener()
  {
    public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
    {
      if ((mAdapter.getCachedMessageItem(paramAnonymousInt) == null) || (paramAnonymousView == null)) {
        return;
      }
      paramAnonymousAdapterView = (CheckBox)paramAnonymousView.findViewById(2131820631);
      if (mListView.isItemChecked(paramAnonymousInt)) {
        paramAnonymousAdapterView.setChecked(true);
      }
      for (;;)
      {
        mAdapter.setListViewCheckStates(mListView.getCheckedItemPositions());
        paramAnonymousInt = mListView.getCheckedItemCount();
        paramAnonymousAdapterView = getResources().getString(2131362071);
        if (paramAnonymousInt != 0) {
          break;
        }
        mReSend.setText(paramAnonymousAdapterView);
        mReSend.setEnabled(false);
        return;
        paramAnonymousAdapterView.setChecked(false);
      }
      mReSend.setText(paramAnonymousAdapterView + "(" + paramAnonymousInt + ")");
      mReSend.setEnabled(true);
    }
  };
  private Button mReSend;
  private final View.OnClickListener mResendOnClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      TimedMessageExpiredActivity.this.resendExpiredMsg();
      TimedMessageExpiredActivity.this.markExpiredMsgAsFailed(true);
      finish();
    }
  };
  private TextView mTitleView;
  
  private void markExpiredMsgAsFailed(boolean paramBoolean)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    int i = 0;
    if (i < mAdapter.getCount())
    {
      MessageItem localMessageItem = mAdapter.getCachedMessageItem(i);
      if (localMessageItem == null) {}
      for (;;)
      {
        i += 1;
        break;
        if ((!paramBoolean) || (!mListView.isItemChecked(i))) {
          if (localMessageItem.isMms()) {
            localArrayList2.add(String.valueOf(localMessageItem.getMsgId()));
          } else {
            localArrayList1.add(String.valueOf(localMessageItem.getMsgId()));
          }
        }
      }
    }
    if ((localArrayList2.size() > 0) || (localArrayList1.size() > 0)) {
      SendMessageService.startMarkFail(this, localArrayList2, localArrayList1);
    }
  }
  
  public static void markPrivateExpiredMsgFailed(Context paramContext)
  {
    Object localObject = Uri.withAppendedPath(ExpiredTimedMessageListAdapter.PRIVATE_EXPIRED_URI, Long.toString(MmsApp.getStartupTime()));
    localObject = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), (Uri)localObject, MessageListAdapter.PROJECTION, null, null, null);
    if (localObject == null) {
      return;
    }
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    for (;;)
    {
      try
      {
        localArrayList1 = new ArrayList();
        localArrayList2 = new ArrayList();
        if (!((Cursor)localObject).moveToNext()) {
          break;
        }
        String str = ((Cursor)localObject).getString(0);
        long l = ((Cursor)localObject).getLong(1);
        if ("mms".equals(str)) {
          localArrayList2.add(String.valueOf(l));
        } else {
          localArrayList1.add(String.valueOf(l));
        }
      }
      finally
      {
        ((Cursor)localObject).close();
      }
    }
    if ((localArrayList2.size() > 0) || (localArrayList1.size() > 0)) {
      SendMessageService.startMarkFail(paramContext, localArrayList2, localArrayList1);
    }
    ((Cursor)localObject).close();
  }
  
  /* Error */
  public static void raiseIfNecessary(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 187	com/android/mms/ui/ExpiredTimedMessageListAdapter:NO_PRIVATE_EXPIRED_URI	Landroid/net/Uri;
    //   3: invokestatic 133	com/android/mms/MmsApp:getStartupTime	()J
    //   6: invokestatic 138	java/lang/Long:toString	(J)Ljava/lang/String;
    //   9: invokestatic 144	android/net/Uri:withAppendedPath	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   12: astore_2
    //   13: aload_0
    //   14: aload_0
    //   15: invokevirtual 150	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   18: aload_2
    //   19: getstatic 156	com/android/mms/ui/MessageListAdapter:PROJECTION	[Ljava/lang/String;
    //   22: aconst_null
    //   23: aconst_null
    //   24: aconst_null
    //   25: invokestatic 162	android/database/sqlite/SqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   28: astore_2
    //   29: aload_2
    //   30: ifnonnull +4 -> 34
    //   33: return
    //   34: aload_2
    //   35: invokeinterface 188 1 0
    //   40: istore_1
    //   41: iload_1
    //   42: ifle +39 -> 81
    //   45: iconst_1
    //   46: istore_1
    //   47: aload_2
    //   48: invokeinterface 183 1 0
    //   53: iload_1
    //   54: ifeq -21 -> 33
    //   57: new 190	android/content/Intent
    //   60: dup
    //   61: aload_0
    //   62: ldc 2
    //   64: invokespecial 193	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   67: astore_2
    //   68: aload_2
    //   69: ldc -62
    //   71: invokevirtual 198	android/content/Intent:setFlags	(I)Landroid/content/Intent;
    //   74: pop
    //   75: aload_0
    //   76: aload_2
    //   77: invokevirtual 202	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   80: return
    //   81: iconst_0
    //   82: istore_1
    //   83: goto -36 -> 47
    //   86: astore_0
    //   87: aload_2
    //   88: invokeinterface 183 1 0
    //   93: aload_0
    //   94: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	paramContext	Context
    //   40	43	1	i	int
    //   12	76	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   34	41	86	finally
  }
  
  private void resendExpiredMsg()
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    int i = 0;
    if (i < mAdapter.getCount())
    {
      if (!mListView.isItemChecked(i)) {}
      for (;;)
      {
        i += 1;
        break;
        MessageItem localMessageItem = mAdapter.getCachedMessageItem(i);
        if (localMessageItem != null) {
          if (localMessageItem.isMms())
          {
            localArrayList1.add(String.valueOf(localMessageItem.getMsgId()));
          }
          else
          {
            localArrayList2.add(String.valueOf(localMessageItem.getMsgId()));
            localArrayList3.add(Long.valueOf(localMessageItem.getSimId()));
          }
        }
      }
    }
    if (localArrayList2.size() > 0) {
      SendMessageService.startReSendSms(this, localArrayList2, MessageUtils.getSlotId(localArrayList3));
    }
    if (localArrayList1.size() > 0) {
      SendMessageService.startReSendMms(this, localArrayList1);
    }
  }
  
  public void onBackPressed() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968715);
    MessageUtils.setWindowLayoutParams(this);
    setFinishOnTouchOutside(false);
    mAdapter = new ExpiredTimedMessageListAdapter(this);
    mTitleView = ((TextView)findViewById(2131820892));
    mListView = ((MessageListView)findViewById(16908298));
    mListView.setChoiceMode(2);
    mListView.setAdapter(mAdapter);
    mListView.setOnItemClickListener(mOnItemClickListener);
    mAdapter.load();
    mAdapter.registerDataSetObserver(new DataSetObserver()
    {
      public void onChanged()
      {
        if (mAdapter.getCount() == 0)
        {
          finish();
          return;
        }
        mTitleView.setText(getString(2131362157, new Object[] { Integer.valueOf(mAdapter.getCount()) }));
      }
    });
    findViewById(2131820894).setOnClickListener(mCloseOnClickListener);
    mReSend = ((Button)findViewById(2131820893));
    mReSend.setEnabled(false);
    mReSend.setOnClickListener(mResendOnClickListener);
    paramBundle = new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS");
    registerReceiver(mHomeKeyExpiredMsgReceiver, paramBundle);
  }
  
  protected void onDestroy()
  {
    mAdapter.stop();
    mAdapter.changeCursor(null);
    unregisterReceiver(mHomeKeyExpiredMsgReceiver);
    super.onDestroy();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.TimedMessageExpiredActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
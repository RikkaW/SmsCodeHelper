package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Telephony.Sms;
import android.provider.Telephony.Sms.Inbox;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.util.MSimUtils;
import java.util.ArrayList;
import miui.app.Activity;
import miui.app.AlertDialog;
import miui.app.AlertDialog.Builder;

public class ClassZeroActivity
  extends Activity
{
  private static final int BUFFER_OFFSET = "         ".length() * 2;
  private static final String[] REPLACE_PROJECTION = { "_id", "address", "protocol", "sim_id" };
  private final DialogInterface.OnClickListener mCancelListener = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      paramAnonymousDialogInterface.dismiss();
      ClassZeroActivity.this.processNextMessage();
    }
  };
  private AlertDialog mDialog = null;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (what == 1)
      {
        ClassZeroActivity.access$002(ClassZeroActivity.this, false);
        mDialog.dismiss();
        ClassZeroActivity.this.saveMessage();
        ClassZeroActivity.this.processNextMessage();
      }
    }
  };
  private MessageItem mMessageItem;
  private ArrayList<MessageItem> mMessageQueue = null;
  private boolean mRead = false;
  private final DialogInterface.OnClickListener mSaveListener = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      ClassZeroActivity.access$002(ClassZeroActivity.this, true);
      ClassZeroActivity.this.saveMessage();
      paramAnonymousDialogInterface.dismiss();
      ClassZeroActivity.this.processNextMessage();
    }
  };
  private long mTimerSet = 0L;
  
  private void displayZeroMessage(MessageItem paramMessageItem)
  {
    mMessageItem = paramMessageItem;
    paramMessageItem = mMessage.getMessageBody();
    mDialog = new AlertDialog.Builder(this).setMessage(paramMessageItem).setTitle(2131362026).setPositiveButton(2131362032, mSaveListener).setNegativeButton(17039360, mCancelListener).show();
    mDialog.setCancelable(false);
    mDialog.setCanceledOnTouchOutside(false);
    mTimerSet = (300000L + SystemClock.uptimeMillis());
  }
  
  private ContentValues extractContentValues(SmsMessage paramSmsMessage)
  {
    int j = 1;
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("address", paramSmsMessage.getDisplayOriginatingAddress());
    localContentValues.put("date", new Long(System.currentTimeMillis()));
    localContentValues.put("protocol", Integer.valueOf(paramSmsMessage.getProtocolIdentifier()));
    if (mRead)
    {
      i = 1;
      localContentValues.put("read", Integer.valueOf(i));
      if (!mRead) {
        break label153;
      }
      i = 1;
      label82:
      localContentValues.put("seen", Integer.valueOf(i));
      if (paramSmsMessage.getPseudoSubject().length() > 0) {
        localContentValues.put("subject", paramSmsMessage.getPseudoSubject());
      }
      if (!paramSmsMessage.isReplyPathPresent()) {
        break label158;
      }
    }
    label153:
    label158:
    for (int i = j;; i = 0)
    {
      localContentValues.put("reply_path_present", Integer.valueOf(i));
      localContentValues.put("service_center", paramSmsMessage.getServiceCenterAddress());
      return localContentValues;
      i = 0;
      break;
      i = 0;
      break label82;
    }
  }
  
  private void processNextMessage()
  {
    if (mMessageQueue.isEmpty())
    {
      finish();
      return;
    }
    mMessageQueue.remove(0);
    if (mMessageQueue.isEmpty())
    {
      finish();
      return;
    }
    displayZeroMessage((MessageItem)mMessageQueue.get(0));
  }
  
  private boolean queueMsgFromIntent(Intent paramIntent)
  {
    byte[] arrayOfByte = paramIntent.getByteArrayExtra("pdu");
    String str = paramIntent.getStringExtra("format");
    long l = MSimUtils.getSimIdFromIntent(paramIntent);
    paramIntent = SmsMessage.createFromPdu(arrayOfByte, str);
    if ((TextUtils.isEmpty(paramIntent.getMessageBody())) || (l < 0L))
    {
      if (mMessageQueue.size() == 0) {
        finish();
      }
      return false;
    }
    mMessageQueue.add(new MessageItem(paramIntent, l));
    return true;
  }
  
  private Uri replaceMessage(MessageItem paramMessageItem)
  {
    Object localObject2 = mMessage;
    ContentValues localContentValues = extractContentValues((SmsMessage)localObject2);
    localContentValues.put("body", ((SmsMessage)localObject2).getMessageBody());
    ContentResolver localContentResolver = getContentResolver();
    Object localObject1 = ((SmsMessage)localObject2).getOriginatingAddress();
    localObject2 = Integer.toString(((SmsMessage)localObject2).getProtocolIdentifier());
    String str = Long.toString(mSimId);
    localObject1 = SqliteWrapper.query(this, localContentResolver, Telephony.Sms.Inbox.CONTENT_URI, REPLACE_PROJECTION, "address = ? AND protocol = ? AND sim_id = ?", new String[] { localObject1, localObject2, str }, null);
    try
    {
      if (((Cursor)localObject1).moveToFirst())
      {
        long l = ((Cursor)localObject1).getLong(0);
        paramMessageItem = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, l);
        SqliteWrapper.update(this, localContentResolver, paramMessageItem, localContentValues, null, null);
        return paramMessageItem;
      }
      return storeMessage(paramMessageItem);
    }
    finally
    {
      ((Cursor)localObject1).close();
    }
  }
  
  private void saveMessage()
  {
    if (mMessageItem.mMessage.isReplace()) {}
    for (Uri localUri = replaceMessage(mMessageItem);; localUri = storeMessage(mMessageItem))
    {
      if ((!mRead) && (localUri != null)) {
        MessagingNotification.nonBlockingUpdateNewMessageIndicator(this, true, false);
      }
      return;
    }
  }
  
  private Uri storeMessage(MessageItem paramMessageItem)
  {
    SmsMessage localSmsMessage = mMessage;
    ContentValues localContentValues = extractContentValues(localSmsMessage);
    localContentValues.put("body", localSmsMessage.getDisplayMessageBody());
    localContentValues.put("sim_id", Long.valueOf(mSimId));
    return SqliteWrapper.insert(this, getContentResolver(), Telephony.Sms.Inbox.CONTENT_URI, localContentValues);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (mMessageQueue == null) {
      mMessageQueue = new ArrayList();
    }
    if (!queueMsgFromIntent(getIntent())) {}
    do
    {
      return;
      if (mMessageQueue.size() == 1) {
        displayZeroMessage((MessageItem)mMessageQueue.get(0));
      }
    } while (paramBundle == null);
    mTimerSet = paramBundle.getLong("timer_fire", mTimerSet);
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    queueMsgFromIntent(paramIntent);
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putLong("timer_fire", mTimerSet);
  }
  
  protected void onStart()
  {
    super.onStart();
    long l = SystemClock.uptimeMillis();
    if (mTimerSet <= l)
    {
      mHandler.sendEmptyMessage(1);
      return;
    }
    mHandler.sendEmptyMessageAtTime(1, mTimerSet);
  }
  
  protected void onStop()
  {
    super.onStop();
    mHandler.removeMessages(1);
  }
  
  private static class MessageItem
  {
    public SmsMessage mMessage;
    public long mSimId;
    
    public MessageItem(SmsMessage paramSmsMessage, long paramLong)
    {
      mMessage = paramSmsMessage;
      mSimId = paramLong;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ClassZeroActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
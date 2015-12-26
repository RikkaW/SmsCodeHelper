package com.xiaomi.mms.transaction;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.logger.MyLog;

public class MxResendService
  extends IntentService
{
  private static final Uri SMS_URI = Uri.parse("content://sms");
  
  public MxResendService()
  {
    super("MxResendService");
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    if (paramIntent == null) {}
    int i;
    do
    {
      return;
      i = MSimUtils.getSlotIdFromIntent(paramIntent);
      paramIntent = new ContentValues();
      paramIntent.put("mx_status", Integer.valueOf(1));
      paramIntent.put("type", Integer.valueOf(6));
      int j = SqliteWrapper.update(this, getContentResolver(), SMS_URI, paramIntent, "type=4 and mx_status=1", null);
      MyLog.i("MxResendService", "dropped msg resent, count:" + j);
      if (j > 0) {
        MSimUtils.sendQueuedMessage(this, i);
      }
      i = MxMessagePduHelper.moveUnsentMessageToPending(this);
      MyLog.i("MxResendService", "resend dropped mms, count: " + i);
    } while (i <= 0);
    startService(new Intent(this, MxMmsTransactionService.class));
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MxResendService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
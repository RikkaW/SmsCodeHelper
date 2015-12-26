package com.android.mms.transaction;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.android.mms.jwap_port.WBXMLDecoder;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.ByteArrayInputStream;
import miui.provider.ExtraTelephony;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

class PushReceiver$SiSlPushTask
  extends AsyncTask<Intent, Void, Void>
{
  private String mAddress;
  private StringBuilder mBody;
  private Context mContext;
  
  public PushReceiver$SiSlPushTask(PushReceiver paramPushReceiver, Context paramContext)
  {
    mContext = paramContext;
  }
  
  private void appendNewLine(String paramString)
  {
    if (mBody.length() > 0) {
      mBody.append('\n');
    }
    for (;;)
    {
      mBody.append(paramString);
      return;
      mBody.append(mContext.getResources().getString(2131362146));
    }
  }
  
  private void storeWapPushMessage(int paramInt, long paramLong)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("address", mAddress);
    localContentValues.put("protocol", Integer.valueOf(0));
    localContentValues.put("read", Integer.valueOf(0));
    localContentValues.put("status", Integer.valueOf(-1));
    localContentValues.put("body", mBody.toString());
    localContentValues.put("block_type", Integer.valueOf(paramInt));
    localContentValues.put("sim_id", Long.valueOf(paramLong));
    mContext.getContentResolver().insert(Uri.parse("content://sms/inbox"), localContentValues);
  }
  
  private void traverse(Node paramNode)
  {
    for (Node localNode = paramNode.getFirstChild(); localNode != null; localNode = localNode.getNextSibling()) {
      traverse(localNode);
    }
    if (paramNode.getNodeType() == 3) {
      appendNewLine(paramNode.getNodeValue());
    }
    paramNode = paramNode.getAttributes();
    if (paramNode != null)
    {
      paramNode = paramNode.getNamedItem("href");
      if (paramNode != null)
      {
        paramNode = paramNode.getNodeValue();
        if ((paramNode != null) && (paramNode.length() > 0)) {
          appendNewLine(paramNode);
        }
      }
    }
  }
  
  protected Void doInBackground(Intent... paramVarArgs)
  {
    MyLog.d("PushReceiver", "ReceiveWapPushTask doInBackground");
    paramVarArgs = paramVarArgs[0];
    byte[] arrayOfByte = paramVarArgs.getByteArrayExtra("data");
    mAddress = paramVarArgs.getStringExtra("address");
    int i = MSimUtils.getSlotIdFromIntent(paramVarArgs);
    long l = MSimUtils.getSimIdBySlotId(i);
    if (l < 0L) {
      MyLog.e("PushReceiver", "Cannot get sim id for slot " + i);
    }
    do
    {
      do
      {
        do
        {
          return null;
          if (TextUtils.isEmpty(mAddress)) {
            mAddress = mContext.getResources().getString(2131362146);
          }
          paramVarArgs = new WBXMLDecoder(mContext).decode(new ByteArrayInputStream(arrayOfByte));
        } while (paramVarArgs == null);
        mBody = new StringBuilder();
        traverse(paramVarArgs);
      } while (mBody.length() <= 0);
      paramVarArgs = mBody.toString();
      i = ExtraTelephony.getSmsBlockType(mContext, mAddress, paramVarArgs, i);
      storeWapPushMessage(i, l);
    } while (i > 1);
    MessagingNotification.blockingUpdateNewMessageIndicator(mContext, true, false);
    return null;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.PushReceiver.SiSlPushTask
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
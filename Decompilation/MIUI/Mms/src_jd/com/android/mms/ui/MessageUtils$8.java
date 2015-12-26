package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import miui.app.ProgressDialog;

final class MessageUtils$8
  extends AsyncTask<Void, Void, Void>
{
  MessageUtils$8(Context paramContext, MessageItem paramMessageItem, Intent paramIntent, ProgressDialog paramProgressDialog) {}
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    String str = val$context.getString(2131361856);
    paramVarArgs = str;
    if (val$msgItem.getSubject() != null) {
      paramVarArgs = str + val$msgItem.getSubject();
    }
    val$intent.putExtra("msg_uri", val$msgItem.getMessageUri());
    val$intent.putExtra("subject", paramVarArgs);
    val$intent.putExtra("mx2type", val$msgItem.getMx2Type());
    val$context.startActivity(val$intent);
    return null;
  }
  
  protected void onPostExecute(Void paramVoid)
  {
    val$dialog.dismiss();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.8
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
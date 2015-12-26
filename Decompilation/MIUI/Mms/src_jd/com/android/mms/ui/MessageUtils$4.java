package com.android.mms.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.transaction.MmsMessageSender;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class MessageUtils$4
  implements DialogInterface.OnClickListener
{
  MessageUtils$4(Map paramMap, Context paramContext, int paramInt, Runnable paramRunnable) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    Iterator localIterator = val$map.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      MmsMessageSender.sendReadRec(val$context, (String)localEntry.getValue(), (String)localEntry.getKey(), val$status);
    }
    if (val$callback != null) {
      val$callback.run();
    }
    paramDialogInterface.dismiss();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
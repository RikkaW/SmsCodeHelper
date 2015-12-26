package com.meizu.common.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

final class AlertDialogExtUtil$ButtonHandler
  extends Handler
{
  private WeakReference<DialogInterface> mDialog;
  
  public AlertDialogExtUtil$ButtonHandler(DialogInterface paramDialogInterface)
  {
    mDialog = new WeakReference(paramDialogInterface);
  }
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      return;
    }
    ((DialogInterface.OnClickListener)obj).onClick((DialogInterface)mDialog.get(), what);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.AlertDialogExtUtil.ButtonHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
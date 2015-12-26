package android.support.v7.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

final class AlertController$ButtonHandler
  extends Handler
{
  private static final int MSG_DISMISS_DIALOG = 1;
  private WeakReference<DialogInterface> mDialog;
  
  public AlertController$ButtonHandler(DialogInterface paramDialogInterface)
  {
    mDialog = new WeakReference(paramDialogInterface);
  }
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    case 0: 
    default: 
      return;
    case -3: 
    case -2: 
    case -1: 
      ((DialogInterface.OnClickListener)obj).onClick((DialogInterface)mDialog.get(), what);
      return;
    }
    ((DialogInterface)obj).dismiss();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AlertController.ButtonHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
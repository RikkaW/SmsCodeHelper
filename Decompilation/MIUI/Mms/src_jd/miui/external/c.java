package miui.external;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;

class c
  implements DialogInterface.OnClickListener
{
  c(SdkErrorActivity paramSdkErrorActivity) {}
  
  public void onClick(final DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.dismiss();
    paramDialogInterface = SdkErrorActivity.a(a);
    new SdkErrorActivity.a(a, paramDialogInterface).show(a.getFragmentManager(), "SdkUpdatePromptDialog");
    new AsyncTask()
    {
      protected Boolean a(Void... paramAnonymousVarArgs)
      {
        try
        {
          Thread.sleep(5000L);
          return Boolean.valueOf(SdkErrorActivity.b(a));
        }
        catch (InterruptedException paramAnonymousVarArgs)
        {
          for (;;)
          {
            paramAnonymousVarArgs.printStackTrace();
          }
        }
      }
      
      protected void a(Boolean paramAnonymousBoolean)
      {
        paramDialogInterface.dismiss();
        if (paramAnonymousBoolean.booleanValue()) {}
        for (paramAnonymousBoolean = SdkErrorActivity.c(a);; paramAnonymousBoolean = SdkErrorActivity.d(a))
        {
          new SdkErrorActivity.a(a, paramAnonymousBoolean).show(a.getFragmentManager(), "SdkUpdateFinishDialog");
          return;
        }
      }
    }.execute(new Void[0]);
  }
}

/* Location:
 * Qualified Name:     miui.external.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
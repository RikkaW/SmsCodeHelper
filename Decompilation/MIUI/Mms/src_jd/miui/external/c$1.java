package miui.external;

import android.app.Dialog;
import android.os.AsyncTask;

class c$1
  extends AsyncTask<Void, Void, Boolean>
{
  c$1(c paramc, Dialog paramDialog) {}
  
  protected Boolean a(Void... paramVarArgs)
  {
    try
    {
      Thread.sleep(5000L);
      return Boolean.valueOf(SdkErrorActivity.b(e.a));
    }
    catch (InterruptedException paramVarArgs)
    {
      for (;;)
      {
        paramVarArgs.printStackTrace();
      }
    }
  }
  
  protected void a(Boolean paramBoolean)
  {
    d.dismiss();
    if (paramBoolean.booleanValue()) {}
    for (paramBoolean = SdkErrorActivity.c(e.a);; paramBoolean = SdkErrorActivity.d(e.a))
    {
      new SdkErrorActivity.a(e.a, paramBoolean).show(e.a.getFragmentManager(), "SdkUpdateFinishDialog");
      return;
    }
  }
}

/* Location:
 * Qualified Name:     miui.external.c.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
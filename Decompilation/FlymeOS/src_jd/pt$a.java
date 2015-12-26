import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;

class pt$a
  extends AsyncTask<Runnable, Void, Void>
{
  final Runnable a;
  
  public pt$a(pt parampt, int paramInt, Runnable paramRunnable)
  {
    a = paramRunnable;
    if (pt.a(parampt) == null) {
      pt.a(parampt, a());
    }
    pt.a(parampt).setMessage(pt.b(parampt).getText(paramInt));
  }
  
  private ProgressDialog a()
  {
    ProgressDialog localProgressDialog = new ProgressDialog(pt.b(b));
    localProgressDialog.setIndeterminate(true);
    localProgressDialog.setProgressStyle(0);
    localProgressDialog.setCanceledOnTouchOutside(false);
    localProgressDialog.setCancelable(false);
    return localProgressDialog;
  }
  
  protected Void a(Runnable... paramVarArgs)
  {
    int i;
    if (paramVarArgs != null) {
      i = 0;
    }
    try
    {
      while (i < paramVarArgs.length)
      {
        paramVarArgs[i].run();
        i += 1;
      }
      return null;
    }
    finally
    {
      pt.d(b).removeCallbacks(pt.c(b));
    }
  }
  
  protected void a(Void paramVoid)
  {
    if (pt.b(b).isFinishing()) {}
    do
    {
      return;
      if ((pt.a(b) != null) && (pt.a(b).isShowing())) {
        pt.a(b).dismiss();
      }
      pt.a(b, null);
    } while (a == null);
    a.run();
  }
  
  protected void onPreExecute()
  {
    pt.d(b).postDelayed(pt.c(b), 500L);
  }
}

/* Location:
 * Qualified Name:     pt.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
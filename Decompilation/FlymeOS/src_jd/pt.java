import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;

public class pt
{
  private ProgressDialog a;
  private final Activity b;
  private final Handler c;
  private Runnable d = new pu(this);
  
  public pt(Activity paramActivity)
  {
    b = paramActivity;
    c = new Handler();
  }
  
  public void a()
  {
    c.removeCallbacks(d);
    if ((a != null) && (a.isShowing())) {
      a.dismiss();
    }
  }
  
  public void a(Runnable paramRunnable1, Runnable paramRunnable2, int paramInt)
  {
    new pt.a(paramInt, paramRunnable2).execute(new Runnable[] { paramRunnable1 });
  }
  
  public void b()
  {
    if ((a != null) && (!a.isShowing())) {
      a.show();
    }
  }
  
  class a
    extends AsyncTask<Runnable, Void, Void>
  {
    final Runnable a;
    
    public a(int paramInt, Runnable paramRunnable)
    {
      a = paramRunnable;
      if (pt.a(pt.this) == null) {
        pt.a(pt.this, a());
      }
      pt.a(pt.this).setMessage(pt.b(pt.this).getText(paramInt));
    }
    
    private ProgressDialog a()
    {
      ProgressDialog localProgressDialog = new ProgressDialog(pt.b(pt.this));
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
        pt.d(pt.this).removeCallbacks(pt.c(pt.this));
      }
    }
    
    protected void a(Void paramVoid)
    {
      if (pt.b(pt.this).isFinishing()) {}
      do
      {
        return;
        if ((pt.a(pt.this) != null) && (pt.a(pt.this).isShowing())) {
          pt.a(pt.this).dismiss();
        }
        pt.a(pt.this, null);
      } while (a == null);
      a.run();
    }
    
    protected void onPreExecute()
    {
      pt.d(pt.this).postDelayed(pt.c(pt.this), 500L);
    }
  }
}

/* Location:
 * Qualified Name:     pt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
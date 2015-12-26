import android.support.v7.app.ActionBarActivity;
import com.android.mms.MmsApp;

public class fx
  extends ActionBarActivity
{
  protected long a()
  {
    return 0L;
  }
  
  public void onStart()
  {
    super.onStart();
    MmsApp.c().b(a());
    aab.a(this);
    ako.a(this);
  }
  
  public void onStop()
  {
    super.onStop();
    aab.b(this);
    ako.b(this);
  }
}

/* Location:
 * Qualified Name:     fx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
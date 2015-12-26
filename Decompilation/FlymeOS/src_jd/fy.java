import android.app.Activity;
import com.android.mms.MmsApp;

public class fy
  extends Activity
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
  }
  
  public void onStop()
  {
    super.onStop();
    aab.b(this);
  }
}

/* Location:
 * Qualified Name:     fy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
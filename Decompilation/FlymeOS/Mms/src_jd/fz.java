import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatPreferenceActivity;
import com.android.mms.MmsApp;

public class fz
  extends AppCompatPreferenceActivity
{
  protected long a()
  {
    return 0L;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getSupportActionBar();
    paramBundle.setDisplayShowTabEnabled(false);
    paramBundle.setDisplayShowTitleEnabled(true);
    paramBundle.setDisplayOptions(12);
    aaa.a(paramBundle, this);
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
 * Qualified Name:     fz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
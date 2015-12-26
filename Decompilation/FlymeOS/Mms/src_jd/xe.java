import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageStats;
import android.content.res.Resources;
import android.preference.Preference;
import android.util.Log;
import com.android.mms.ui.OtherPreferenceActivity;
import java.math.BigDecimal;

public class xe
  extends IPackageStatsObserver.Stub
{
  public xe(OtherPreferenceActivity paramOtherPreferenceActivity) {}
  
  public void onGetStatsCompleted(PackageStats paramPackageStats, boolean paramBoolean)
  {
    OtherPreferenceActivity.a(String.valueOf(new BigDecimal((float)(dataSize / 1024L) / 1024.0F).setScale(2, 4).floatValue()));
    OtherPreferenceActivity.a(a.getResources().getText(2131493572).toString());
    OtherPreferenceActivity.a(a).setSummary(OtherPreferenceActivity.b());
    Log.v("OtherPreferenceActivity", "mMessageCapacity:" + OtherPreferenceActivity.b());
  }
}

/* Location:
 * Qualified Name:     xe
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
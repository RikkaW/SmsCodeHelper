package sdk.meizu.traffic.hybird;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class HyBirdPreferenceHelper
{
  private static final String CONFIG_FILE_MODIFY = "Last-Modified";
  private static final String H5_RESOURCE_MODIFY = "h5_resource_modify";
  private static final String HYBIRD_PREFERENCE = "hybird.prefs";
  private static final String NATIVE_SUPPORT_PREFERENCE_kEY = "mzaccount.SUPPORT_NATIVE_DEBUG";
  
  public static final String readConfigFileModify(Context paramContext)
  {
    return paramContext.getSharedPreferences("hybird.prefs", 0).getString("Last-Modified", null);
  }
  
  public static final long readH5ResourceModify(Context paramContext)
  {
    return paramContext.getSharedPreferences("hybird.prefs", 0).getLong("h5_resource_modify", 0L);
  }
  
  public static final boolean readNativeSupportPreferenceKey(Context paramContext)
  {
    return paramContext.getSharedPreferences("hybird.prefs", 0).getBoolean("mzaccount.SUPPORT_NATIVE_DEBUG", false);
  }
  
  public static final void writeConfigFileModify(Context paramContext, String paramString)
  {
    paramContext.getSharedPreferences("hybird.prefs", 0).edit().putString("Last-Modified", paramString).apply();
  }
  
  public static final void writeH5ResourceModify(Context paramContext, long paramLong)
  {
    paramContext.getSharedPreferences("hybird.prefs", 0).edit().putLong("h5_resource_modify", paramLong).apply();
  }
  
  public static final void writeNativeSupportPreferenceKey(Context paramContext, boolean paramBoolean)
  {
    paramContext.getSharedPreferences("hybird.prefs", 0).edit().putBoolean("mzaccount.SUPPORT_NATIVE_DEBUG", paramBoolean).apply();
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.hybird.HyBirdPreferenceHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
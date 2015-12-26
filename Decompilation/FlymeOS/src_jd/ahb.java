public final class ahb
{
  protected static boolean a = false;
  protected static final String[] b = { "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE" };
  
  protected static boolean a(String[] paramArrayOfString, String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    int i;
    if (paramArrayOfString != null)
    {
      bool1 = bool2;
      if (paramString != null) {
        i = 0;
      }
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < paramArrayOfString.length)
      {
        if (paramArrayOfString[i].equals(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     ahb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
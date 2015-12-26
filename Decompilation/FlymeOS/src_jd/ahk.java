import android.util.SparseArray;

public class ahk
{
  static String a = null;
  static String b = null;
  static String c = null;
  static String d = "";
  static String e = "";
  static String f = "";
  static boolean g = false;
  static boolean h = true;
  static long i = 10000L;
  static long j = 30000L;
  static boolean k = true;
  static final SparseArray<String> l = new SparseArray();
  static final String[] m = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.INTERNET", "android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE" };
  
  static
  {
    l.append(0, "UNKNOWN");
    l.append(1, "GPRS");
    l.append(2, "EDGE");
    l.append(3, "UMTS");
    l.append(4, "CDMA");
    l.append(5, "EVDO_0");
    l.append(6, "EVDO_A");
    l.append(7, "1xRTT");
    l.append(8, "HSDPA");
    l.append(9, "HSUPA");
    l.append(10, "HSPA");
    l.append(11, "IDEN");
    l.append(12, "EVDO_B");
    l.append(13, "LTE");
    l.append(14, "EHRPD");
    l.append(15, "HSPAP");
  }
  
  static void a(String paramString)
  {
    d = paramString;
  }
  
  static void a(boolean paramBoolean)
  {
    g = paramBoolean;
  }
  
  static void b(String paramString)
  {
    e = paramString;
  }
  
  static void c(String paramString)
  {
    f = paramString;
  }
}

/* Location:
 * Qualified Name:     ahk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
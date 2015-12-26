import android.os.Bundle;
import com.android.internal.telephony.uicc.IccUtils;

public class ox
{
  private final Bundle a;
  
  private ox(int paramInt)
  {
    a = new Bundle();
    a.putInt("type", paramInt);
  }
  
  public ox(int paramInt, String paramString)
  {
    this(paramInt);
    a.putString("bundle_uri", paramString);
  }
  
  public ox(Bundle paramBundle)
  {
    a = paramBundle;
  }
  
  public int a()
  {
    return a.getInt("type");
  }
  
  public String b()
  {
    return a.getString("bundle_uri");
  }
  
  public byte[] c()
  {
    return a.getByteArray("mms-push-data");
  }
  
  public String d()
  {
    return a.getString("mmsc-url");
  }
  
  public String e()
  {
    return a.getString("proxy-address");
  }
  
  public int f()
  {
    return a.getInt("proxy-port");
  }
  
  public String toString()
  {
    return "transactionType: " + a() + " uri: " + b() + " pushData: " + IccUtils.bytesToHexString(c()) + " mmscUrl: " + d() + " proxyAddress: " + e() + " proxyPort: " + f();
  }
}

/* Location:
 * Qualified Name:     ox
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import org.apache.http.conn.util.InetAddressUtils;

public class amq
{
  public static String a()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        localObject = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration;
        while (!localEnumeration.hasMoreElements())
        {
          if (!((Enumeration)localObject).hasMoreElements()) {
            break;
          }
          localEnumeration = ((NetworkInterface)((Enumeration)localObject).nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration.nextElement();
      } while ((localInetAddress.isLoopbackAddress()) || (!InetAddressUtils.isIPv4Address(localInetAddress.getHostAddress())));
      Object localObject = localInetAddress.getHostAddress();
      return (String)localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public static String a(String paramString)
  {
    str = null;
    for (;;)
    {
      try
      {
        boolean bool = InetAddressUtils.isIPv4Address(paramString);
        if (!bool) {
          continue;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        paramString = str;
        continue;
        paramString = null;
        continue;
      }
      str = paramString;
      if (paramString == null) {
        str = "";
      }
      return str;
      paramString = InetAddress.getByName(paramString);
      if (paramString == null) {
        continue;
      }
      paramString = paramString.getHostAddress();
    }
  }
}

/* Location:
 * Qualified Name:     amq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
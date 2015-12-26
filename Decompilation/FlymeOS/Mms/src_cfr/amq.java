/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.net.InetAddress
 *  java.net.NetworkInterface
 *  org.apache.http.conn.util.InetAddressUtils
 */
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import org.apache.http.conn.util.InetAddressUtils;

public class amq {
    public static String a() {
        try {
            Object object = NetworkInterface.getNetworkInterfaces();
            while (object.hasMoreElements()) {
                Enumeration enumeration = ((NetworkInterface)object.nextElement()).getInetAddresses();
                while (enumeration.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress)enumeration.nextElement();
                    if (inetAddress.isLoopbackAddress() || !InetAddressUtils.isIPv4Address((String)inetAddress.getHostAddress())) continue;
                    object = inetAddress.getHostAddress();
                    return object;
                }
            }
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
        }
        return "";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String a(String string2) {
        String string3 = null;
        try {
            boolean bl2 = InetAddressUtils.isIPv4Address((String)string2);
            if (!bl2) {
                string2 = (string2 = InetAddress.getByName((String)string2)) != null ? string2.getHostAddress() : null;
            }
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            string2 = string3;
        }
        string3 = string2;
        if (string2 != null) return string3;
        return "";
    }
}


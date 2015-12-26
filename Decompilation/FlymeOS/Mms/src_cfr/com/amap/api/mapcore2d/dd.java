/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.net.wifi.ScanResult
 *  android.net.wifi.WifiInfo
 *  android.net.wifi.WifiManager
 *  android.os.Environment
 *  android.provider.Settings
 *  android.provider.Settings$System
 *  android.telephony.CellLocation
 *  android.telephony.TelephonyManager
 *  android.telephony.cdma.CdmaCellLocation
 *  android.telephony.gsm.GsmCellLocation
 *  android.util.DisplayMetrics
 *  android.view.Display
 *  android.view.WindowManager
 *  java.io.File
 *  java.io.FileNotFoundException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  javax.xml.parsers.SAXParser
 *  javax.xml.parsers.SAXParserFactory
 *  org.xml.sax.SAXException
 */
package com.amap.api.mapcore2d;

import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.amap.api.mapcore2d.ed;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class dd {
    private static String a = null;
    private static boolean b = false;
    private static String c = null;
    private static String d = null;
    private static String e = null;
    private static String f = null;

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(Context object) {
        try {
            if (a != null && !"".equals((Object)a)) {
                return a;
            }
            if (object.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0) {
                a = Settings.System.getString((ContentResolver)object.getContentResolver(), (String)"mqBRboGZkQPcAkyk");
            }
            if (a != null && !"".equals((Object)a)) {
                return a;
            }
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        try {
            if (!"mounted".equals((Object)Environment.getExternalStorageState())) return a;
            object = Environment.getExternalStorageDirectory().getAbsolutePath();
            if (!(object = new File((String)object + "/.UTSystemConfig/Global/Alvin2.xml")).exists()) return a;
            SAXParserFactory.newInstance().newSAXParser().parse((File)object, (DefaultHandler)new a());
            do {
                return a;
                break;
            } while (true);
        }
        catch (FileNotFoundException var0_2) {
            var0_2.printStackTrace();
            return a;
        }
        catch (ParserConfigurationException var0_3) {
            var0_3.printStackTrace();
            return a;
        }
        catch (SAXException var0_4) {
            var0_4.printStackTrace();
            return a;
        }
        catch (IOException var0_5) {
            var0_5.printStackTrace();
            return a;
        }
        catch (Throwable var0_6) {
            var0_6.printStackTrace();
            return a;
        }
    }

    private static List<ScanResult> a(List<ScanResult> list) {
        int n2 = list.size();
        for (int i2 = 0; i2 < n2 - 1; ++i2) {
            for (int i3 = 1; i3 < n2 - i2; ++i3) {
                if (list.get((int)(i3 - 1)).level <= list.get((int)i3).level) continue;
                ScanResult scanResult = list.get(i3 - 1);
                list.set(i3 - 1, list.get(i3));
                list.set(i3, scanResult);
            }
        }
        return list;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static String b(Context list) {
        StringBuilder stringBuilder = new StringBuilder();
        if (list == null) return stringBuilder.toString();
        try {
            if (list.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") != 0) {
                return stringBuilder.toString();
            }
            if (!(list = (WifiManager)list.getSystemService("wifi")).isWifiEnabled()) return stringBuilder.toString();
            if ((list = list.getScanResults()) == null || list.size() == 0) {
                return stringBuilder.toString();
            }
            list = dd.a(list);
            boolean bl2 = true;
            int n2 = 0;
            while (n2 < list.size()) {
                if (n2 >= 10) return stringBuilder.toString();
                {
                    ScanResult scanResult = list.get(n2);
                    if (bl2) {
                        bl2 = false;
                    } else {
                        stringBuilder.append("||");
                    }
                    stringBuilder.append(scanResult.BSSID);
                    ++n2;
                    continue;
                }
            }
            return stringBuilder.toString();
        }
        catch (Throwable var0_1) {
            ed.a(var0_1, "DeviceInfo", "getWifiMacs");
            var0_1.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static String c(Context context) {
        try {
            if (c != null && !"".equals((Object)c)) {
                return c;
            }
            if (context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") != 0) {
                return c;
            }
            c = ((WifiManager)context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            do {
                return c;
                break;
            } while (true);
        }
        catch (Throwable var0_1) {
            ed.a(var0_1, "DeviceInfo", "getDeviceMac");
            var0_1.printStackTrace();
            return c;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static String d(Context context) {
        StringBuilder stringBuilder;
        block5 : {
            stringBuilder = new StringBuilder();
            try {
                if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
                    return stringBuilder.toString();
                }
                if (!((context = ((TelephonyManager)context.getSystemService("phone")).getCellLocation()) instanceof GsmCellLocation)) break block5;
                context = (GsmCellLocation)context;
                int n2 = context.getCid();
                stringBuilder.append(context.getLac()).append("||").append(n2).append("&bt=gsm");
            }
            catch (Throwable var0_1) {
                ed.a(var0_1, "DeviceInfo", "cellInfo");
                var0_1.printStackTrace();
                return stringBuilder.toString();
            }
            return stringBuilder.toString();
        }
        if (!(context instanceof CdmaCellLocation)) return stringBuilder.toString();
        context = (CdmaCellLocation)context;
        int n3 = context.getSystemId();
        int n4 = context.getNetworkId();
        int n5 = context.getBaseStationId();
        if (n3 < 0 || n4 < 0 || n5 < 0) {
            // empty if block
        }
        stringBuilder.append(n3).append("||").append(n4).append("||").append(n5).append("&bt=cdma");
        return stringBuilder.toString();
    }

    static String e(Context object) {
        try {
            object = dd.v((Context)object);
            return object;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return "";
        }
    }

    static int f(Context context) {
        try {
            int n2 = dd.w(context);
            return n2;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return -1;
        }
    }

    public static int g(Context context) {
        try {
            int n2 = dd.u(context);
            return n2;
        }
        catch (Throwable var0_1) {
            ed.a(var0_1, "DeviceInfo", "getActiveNetWorkType");
            var0_1.printStackTrace();
            return -1;
        }
    }

    static int h(Context context) {
        try {
            int n2 = dd.u(context);
            return n2;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return -1;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static String i(Context object) {
        block5 : {
            try {
                if (object.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0) break block5;
                return null;
            }
            catch (Throwable var0_1) {
                ed.a(var0_1, "DeviceInfo", "getNetworkExtraInfo");
                var0_1.printStackTrace();
                return null;
            }
        }
        object = (ConnectivityManager)object.getSystemService("connectivity");
        if (object == null) return null;
        object = object.getActiveNetworkInfo();
        if (object == null) return null;
        return object.getExtraInfo();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static String j(Context object) {
        try {
            if (d != null && !"".equals((Object)d)) {
                return d;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager)object.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int n2 = displayMetrics.widthPixels;
            int n3 = displayMetrics.heightPixels;
            object = n3 > n2 ? "" + n2 + "*" + n3 : "" + n3 + "*" + n2;
            d = object;
            return d;
        }
        catch (Throwable var0_1) {
            ed.a(var0_1, "DeviceInfo", "getReslution");
            var0_1.printStackTrace();
            return d;
        }
    }

    static String k(Context object) {
        try {
            object = dd.t((Context)object);
            return object;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    static String l(Context object) {
        try {
            object = dd.t((Context)object);
            return object;
        }
        catch (Throwable var0_1) {
            ed.a(var0_1, "DeviceInfo", "getActiveNetworkTypeName");
            var0_1.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static String m(Context context) {
        try {
            if (e != null && !"".equals((Object)e)) {
                return e;
            }
            if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
                return e;
            }
            e = ((TelephonyManager)context.getSystemService("phone")).getDeviceId();
            do {
                return e;
                break;
            } while (true);
        }
        catch (Throwable var0_1) {
            ed.a(var0_1, "DeviceInfo", "getDeviceID");
            var0_1.printStackTrace();
            return e;
        }
    }

    static String n(Context object) {
        try {
            object = dd.r((Context)object);
            return object;
        }
        catch (Throwable var0_1) {
            ed.a(var0_1, "DeviceInfo", "getSubscriberId");
            var0_1.printStackTrace();
            return null;
        }
    }

    static String o(Context object) {
        try {
            object = dd.s((Context)object);
            return object;
        }
        catch (Throwable var0_1) {
            ed.a(var0_1, "DeviceInfo", "getNetworkOperatorName");
            var0_1.printStackTrace();
            return null;
        }
    }

    static String p(Context object) {
        try {
            object = dd.s((Context)object);
            return object;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    static String q(Context object) {
        try {
            object = dd.r((Context)object);
            return object;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    private static String r(Context context) {
        if (f != null && !"".equals((Object)f)) {
            return f;
        }
        if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return f;
        }
        f = ((TelephonyManager)context.getSystemService("phone")).getSubscriberId();
        return f;
    }

    private static String s(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return null;
        }
        return ((TelephonyManager)context.getSystemService("phone")).getNetworkOperatorName();
    }

    private static String t(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return "";
        }
        if ((context = (ConnectivityManager)context.getSystemService("connectivity")) == null) {
            return "";
        }
        if ((context = context.getActiveNetworkInfo()) == null) {
            return "";
        }
        return context.getTypeName();
    }

    private static int u(Context context) {
        if (context == null || context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return -1;
        }
        if ((context = (ConnectivityManager)context.getSystemService("connectivity")) == null) {
            return -1;
        }
        if ((context = context.getActiveNetworkInfo()) == null) {
            return -1;
        }
        return context.getType();
    }

    private static String v(Context object) {
        if ((object = dd.n((Context)object)) == null || object.length() < 5) {
            return "";
        }
        return object.substring(3, 5);
    }

    private static int w(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return -1;
        }
        return ((TelephonyManager)context.getSystemService("phone")).getNetworkType();
    }

    static class a
    extends DefaultHandler {
        a() {
        }

        @Override
        public void characters(char[] arrc, int n2, int n3) {
            if (b) {
                a = new String(arrc, n2, n3);
            }
        }

        @Override
        public void endElement(String string2, String string3, String string4) {
            b = false;
        }

        @Override
        public void startElement(String string2, String string3, String string4, Attributes attributes) {
            if (string3.equals((Object)"string") && "UTDID".equals((Object)attributes.getValue("name"))) {
                b = true;
            }
        }
    }

}


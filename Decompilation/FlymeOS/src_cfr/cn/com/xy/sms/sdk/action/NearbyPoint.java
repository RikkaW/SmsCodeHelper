/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.os.Handler
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.net.URLEncoder
 */
package cn.com.xy.sms.sdk.action;

import android.app.Activity;
import android.os.Handler;
import cn.com.xy.sms.sdk.action.c;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class NearbyPoint {
    public static final int DO_GET_LOCATION = 4101;
    public static final int GET_QUERY_URL_FAILURE = 4099;
    public static final int QUERY_PARAM_ERROR = 4098;
    public static final int QUERY_REQUEST_ERROR = 4100;
    public static final String QUERY_RESULT = "queryResult";
    public static final int QUERY_RESULT_RECEIVE = 4097;
    private Activity a;
    private c b = null;
    private Handler c;
    private double d;
    private double e;
    private String f;
    private int g = 0;

    public NearbyPoint(Activity activity, Handler handler) {
        this.c = handler;
    }

    static /* synthetic */ double a(NearbyPoint nearbyPoint) {
        return nearbyPoint.d;
    }

    static /* synthetic */ String a(NearbyPoint nearbyPoint, String string2, double d2, double d3, int n2, int n3) {
        return NearbyPoint.a("6a0ddfcfdf1a1e7a1f38501fc5d218bf", string2, d2, d3, 20000, "json", 2, n3);
    }

    private String a(String string2, double d2, double d3, int n2, int n3) {
        return NearbyPoint.a("6a0ddfcfdf1a1e7a1f38501fc5d218bf", string2, d2, d3, n2, "json", 2, n3);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static String a(String string2, String string3, double d2, double d3, int n2, String string4, int n3, int n4) {
        if (string2.length() != 32) return null;
        if (string3 == null) return null;
        if (d2 < 0.0) return null;
        if (d3 < 0.0) return null;
        if (n2 <= 0) return null;
        if (!string4.equalsIgnoreCase("json")) {
            if (!string4.equalsIgnoreCase("xml")) return null;
        }
        string2 = new StringBuffer();
        string2.append("http://android.bizport.cn:9998/AndroidWeb/getPlaceListAPI?");
        string2.append("query=");
        try {
            string2.append(URLEncoder.encode((String)string3, (String)"UTF-8"));
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
        }
        string2.append("&lat=");
        string2.append(d2);
        string2.append("&lng=");
        string2.append(d3);
        string2.append("&radius=");
        string2.append(n2);
        string2.append("&scope=");
        string2.append(2);
        string2.append("&page_num=");
        string2.append(n4);
        string2.append("&output=");
        string2.append(string4);
        return string2.toString();
    }

    static /* synthetic */ double b(NearbyPoint nearbyPoint) {
        return nearbyPoint.e;
    }

    static /* synthetic */ String c(NearbyPoint nearbyPoint) {
        return nearbyPoint.f;
    }

    static /* synthetic */ Handler d(NearbyPoint nearbyPoint) {
        return nearbyPoint.c;
    }

    static /* synthetic */ int e(NearbyPoint nearbyPoint) {
        return nearbyPoint.g;
    }

    public double getLocationLatitude() {
        return this.d;
    }

    public double getLocationLongitude() {
        return this.e;
    }

    public void sendMapQueryUrl(String string2, double d2, double d3, int n2) {
        if (this.b != null) {
            this.b.isInterrupted();
            this.b = null;
        }
        this.f = string2;
        this.d = d2;
        this.e = d3;
        this.g = n2;
        this.b = new c(this, 0);
        this.b.start();
    }
}


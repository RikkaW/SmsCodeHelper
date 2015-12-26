/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.PendingIntent
 *  android.app.PendingIntent$CanceledException
 *  android.content.Context
 *  android.content.Intent
 *  android.location.Criteria
 *  android.location.GpsStatus
 *  android.location.GpsStatus$Listener
 *  android.location.Location
 *  android.location.LocationListener
 *  android.location.LocationManager
 *  android.os.Bundle
 *  android.os.Looper
 *  android.os.Parcelable
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.Hashtable
 *  java.util.Vector
 */
package com.amap.api.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import com.amap.api.location.AMapLocalWeatherListener;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.location.core.d;
import com.amap.api.location.h;
import com.amap.api.location.i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class LocationManagerProxy {
    public static final String GPS_PROVIDER = "gps";
    public static final String KEY_LOCATION_CHANGED = "location";
    public static final String KEY_PROVIDER_ENABLED = "providerEnabled";
    public static final String KEY_PROXIMITY_ENTERING = "entering";
    public static final String KEY_STATUS_CHANGED = "status";
    public static final String NETWORK_PROVIDER = "network";
    public static final int WEATHER_TYPE_FORECAST = 2;
    public static final int WEATHER_TYPE_LIVE = 1;
    static Object a;
    private static LocationManagerProxy c;
    private LocationManager b = null;
    private com.amap.api.location.a d = null;
    private Context e;
    private h f;
    private b g;
    private ArrayList<PendingIntent> h = new ArrayList();
    private Hashtable<String, LocationProviderProxy> i = new Hashtable();
    private Vector<i> j = new Vector();
    private Vector<i> k = new Vector();
    private a l;

    static {
        c = null;
        a = new Object();
    }

    private LocationManagerProxy(Activity activity) {
        this.l = new a();
        this.a(activity.getApplicationContext());
    }

    private LocationManagerProxy(Context context) {
        this.l = new a();
        this.a(context);
    }

    private static void a() {
        c = null;
    }

    private void a(Context context) {
        try {
            this.e = context;
            this.b = (LocationManager)context.getSystemService("location");
            this.d = com.amap.api.location.a.a(context.getApplicationContext(), this.b);
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(String string2, long l2, float f2, AMapLocationListener object, boolean bl2) {
        synchronized (this) {
            try {
                if (this.d == null) {
                    this.d = com.amap.api.location.a.a(this.e.getApplicationContext(), this.b);
                }
                if (string2 == null) {
                    string2 = "lbs";
                }
                if ("lbs".equals((Object)string2)) {
                    if (this.d != null) {
                        this.d.a(l2, f2, (AMapLocationListener)object, "lbs", bl2);
                    }
                } else if ("gps".equals((Object)string2)) {
                    if (this.d != null) {
                        this.d.a(l2, f2, (AMapLocationListener)object, "gps", bl2);
                    }
                } else {
                    Looper looper = this.e.getMainLooper();
                    if (Looper.myLooper() == null) {
                        Looper.prepare();
                    }
                    object = new i(l2, f2, (AMapLocationListener)object, string2, false);
                    this.j.add(object);
                    this.b.requestLocationUpdates(string2, l2, f2, (LocationListener)this.l, looper);
                }
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
            }
            return;
        }
    }

    static /* synthetic */ Vector c(LocationManagerProxy locationManagerProxy) {
        return locationManagerProxy.j;
    }

    static /* synthetic */ Vector d(LocationManagerProxy locationManagerProxy) {
        return locationManagerProxy.k;
    }

    static /* synthetic */ LocationManager e(LocationManagerProxy locationManagerProxy) {
        return locationManagerProxy.b;
    }

    static /* synthetic */ a f(LocationManagerProxy locationManagerProxy) {
        return locationManagerProxy.l;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static LocationManagerProxy getInstance(Activity object) {
        block6 : {
            try {
                Object object2 = a;
                synchronized (object2) {
                    if (c != null) break block6;
                }
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return null;
            }
            {
                c = new LocationManagerProxy((Activity)object);
            }
        }
        return c;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static LocationManagerProxy getInstance(Context object) {
        synchronized (LocationManagerProxy.class) {
            try {
                if (c != null) return c;
                c = new LocationManagerProxy((Context)object);
                return c;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return null;
            }
        }
    }

    public static String getVersion() {
        return "V1.3.3";
    }

    public void addGeoFenceAlert(double d2, double d3, float f2, long l2, PendingIntent pendingIntent) {
        try {
            if (this.d != null) {
                this.d.b(d2, d3, f2, l2, pendingIntent);
            }
            return;
        }
        catch (Throwable var8_6) {
            var8_6.printStackTrace();
            return;
        }
    }

    public boolean addGpsStatusListener(GpsStatus.Listener listener) {
        try {
            if (this.b != null) {
                boolean bl2 = this.b.addGpsStatusListener(listener);
                return bl2;
            }
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        return false;
    }

    public void addProximityAlert(double d2, double d3, float f2, long l2, PendingIntent pendingIntent) {
        try {
            if (this.d.g) {
                this.b.addProximityAlert(d2, d3, f2, l2, pendingIntent);
            }
            this.d.a(d2, d3, f2, l2, pendingIntent);
            return;
        }
        catch (Throwable var8_6) {
            var8_6.printStackTrace();
            return;
        }
    }

    public void addTestProvider(String string2, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6, boolean bl7, boolean bl8, int n2, int n3) {
        try {
            if (this.b != null) {
                this.b.addTestProvider(string2, bl2, bl3, bl4, bl5, bl6, bl7, bl8, n2, n3);
            }
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public void clearTestProviderEnabled(String string2) {
        try {
            if (this.b != null) {
                this.b.clearTestProviderEnabled(string2);
            }
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public void clearTestProviderLocation(String string2) {
        try {
            if (this.b != null) {
                this.b.clearTestProviderLocation(string2);
            }
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public void clearTestProviderStatus(String string2) {
        try {
            if (this.b != null) {
                this.b.clearTestProviderStatus(string2);
            }
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    @Deprecated
    public void destory() {
        try {
            this.destroy();
            return;
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public void destroy() {
        try {
            var2_1 = LocationManagerProxy.a;
            // MONITORENTER : var2_1
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
            return;
        }
        com.amap.api.location.a.c();
        if (this.i != null) {
            this.i.clear();
        }
        if (this.j != null) {
            this.j.clear();
        }
        if (this.b == null) ** GOTO lbl22
        if (this.l != null) {
            this.b.removeUpdates((LocationListener)this.l);
        }
        if (this.h == null) ** GOTO lbl22
        for (var1_3 = 0; var1_3 < this.h.size(); ++var1_3) {
            var3_4 = (PendingIntent)this.h.get(var1_3);
            if (var3_4 == null) continue;
            this.b.removeUpdates(var3_4);
        }
lbl22: // 3 sources:
        if (this.h != null) {
            this.h.clear();
        }
        this.d = null;
        LocationManagerProxy.a();
        this.l = null;
        // MONITOREXIT : var2_1
    }

    public List<String> getAllProviders() {
        List list;
        block5 : {
            List list2 = this.b.getAllProviders();
            if (list2 == null) break block5;
            list = list2;
            if (!list2.contains("lbs")) {
                list2.add("lbs");
                return list2;
            }
        }
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add("lbs");
            arrayList.addAll(this.b.getAllProviders());
            return arrayList;
        }
        catch (Throwable var1_4) {
            var1_4.printStackTrace();
            list = null;
        }
        return list;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String getBestProvider(Criteria object, boolean bl2) {
        String string2;
        String string3 = "lbs";
        if (object == null) {
            return "lbs";
        }
        try {
            if (!this.getProvider("lbs").meetsCriteria((Criteria)object)) {
                string3 = this.b.getBestProvider((Criteria)object, bl2);
            }
            string2 = string3;
            if (!bl2) return string2;
            string2 = string3;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return "gps";
        }
        if (d.a(this.e)) return string2;
        return this.b.getBestProvider((Criteria)object, bl2);
    }

    public GpsStatus getGpsStatus(GpsStatus gpsStatus) {
        GpsStatus gpsStatus2 = null;
        try {
            if (this.b != null) {
                gpsStatus2 = this.b.getGpsStatus(gpsStatus);
            }
            return gpsStatus2;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public AMapLocation getLastKnownLocation(String object) {
        block5 : {
            if (this.d != null) break block5;
            return null;
        }
        if ("lbs".equals(object)) {
            return this.d.a();
        }
        if (this.b == null) return null;
        if ((object = this.b.getLastKnownLocation((String)object)) == null) return null;
        try {
            return new AMapLocation((Location)object);
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        return null;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public LocationProviderProxy getProvider(String var1_1) {
        if (var1_1 != null) ** GOTO lbl4
        try {
            throw new IllegalArgumentException("name\u4e0d\u80fd\u4e3a\u7a7a\uff01");
lbl4: // 1 sources:
            if (this.i.containsKey((Object)var1_1)) {
                return (LocationProviderProxy)this.i.get((Object)var1_1);
            }
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return null;
        }
        var2_3 = LocationProviderProxy.a(this.b, var1_1);
        this.i.put((Object)var1_1, (Object)var2_3);
        return var2_3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public List<String> getProviders(Criteria criteria, boolean bl2) {
        try {
            List list;
            block5 : {
                List list2 = this.b.getProviders(criteria, bl2);
                if (list2 != null) {
                    list = list2;
                    if (list2.size() != 0) break block5;
                }
                list = new ArrayList();
            }
            if ("lbs".equals((Object)this.getBestProvider(criteria, bl2))) {
                list.add("lbs");
            }
            return list;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public List<String> getProviders(boolean bl2) {
        try {
            List list;
            List list2 = list = this.b.getProviders(bl2);
            if (this.isProviderEnabled("lbs")) {
                block5 : {
                    if (list != null) {
                        list2 = list;
                        if (list.size() != 0) break block5;
                    }
                    list2 = new ArrayList();
                }
                list2.add("lbs");
            }
            return list2;
        }
        catch (Throwable var2_4) {
            var2_4.printStackTrace();
            return null;
        }
    }

    public boolean isProviderEnabled(String string2) {
        try {
            if ("lbs".equals((Object)string2)) {
                return d.a(this.e);
            }
            boolean bl2 = this.b.isProviderEnabled(string2);
            return bl2;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return false;
        }
    }

    public void removeGeoFenceAlert(PendingIntent pendingIntent) {
        try {
            if (this.d != null) {
                this.d.b(pendingIntent);
            }
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public void removeGpsStatusListener(GpsStatus.Listener listener) {
        try {
            if (this.b != null) {
                this.b.removeGpsStatusListener(listener);
            }
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public void removeProximityAlert(PendingIntent pendingIntent) {
        try {
            if (this.d != null && this.d.g && this.b != null) {
                this.b.removeProximityAlert(pendingIntent);
            }
            if (this.d != null) {
                this.d.a(pendingIntent);
            }
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public void removeUpdates(PendingIntent pendingIntent) {
        try {
            if (this.f != null) {
                this.h.remove((Object)pendingIntent);
                if (this.h.size() == 0) {
                    this.f.a();
                }
            }
            this.f = null;
            this.b.removeUpdates(pendingIntent);
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public void removeUpdates(AMapLocationListener var1_1) {
        block11 : {
            // MONITORENTER : this
            if (var1_1 == null) ** GOTO lbl7
            try {
                if (this.d != null) {
                    this.d.a(var1_1);
                }
                this.b.removeUpdates((LocationListener)var1_1);
lbl7: // 2 sources:
                if (this.j != null && this.j.size() > 0) {
                    var2_3 = this.j.size();
                    var3_4 = 0;
lbl10: // 2 sources:
                    if (var3_4 < var2_3) {
                        var4_5 = (i)this.j.get(var3_4);
                        if (var1_1.equals((Object)var4_5.b)) {
                            this.j.remove((Object)var4_5);
                            --var3_4;
                            --var2_3;
                        }
                        break block11;
                    }
                    if (this.j.size() == 0 && this.l != null) {
                        this.b.removeUpdates((LocationListener)this.l);
                    }
                }
                // MONITOREXIT : this
                return;
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
                return;
            }
        }
        ++var3_4;
        ** GOTO lbl10
    }

    public void requestLocationData(String string2, long l2, float f2, AMapLocationListener aMapLocationListener) {
        synchronized (this) {
            this.a(string2, l2, f2, aMapLocationListener, true);
            return;
        }
    }

    public void requestLocationUpdates(String string2, long l2, float f2, PendingIntent pendingIntent) {
        try {
            if ("lbs".equals((Object)string2)) {
                if (this.f == null) {
                    this.f = new h(this);
                }
                if (this.g == null) {
                    this.g = new b();
                }
                this.f.a(this.g, l2, f2, string2);
                this.h.add((Object)pendingIntent);
                return;
            }
            this.h.add((Object)pendingIntent);
            this.b.requestLocationUpdates(string2, l2, f2, pendingIntent);
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    @Deprecated
    public void requestLocationUpdates(String string2, long l2, float f2, AMapLocationListener aMapLocationListener) {
        synchronized (this) {
            this.a(string2, l2, f2, aMapLocationListener, false);
            return;
        }
    }

    public void requestWeatherUpdates(int n2, AMapLocalWeatherListener aMapLocalWeatherListener) {
        try {
            this.d.a(n2, aMapLocalWeatherListener);
            return;
        }
        catch (Throwable var2_3) {
            var2_3.printStackTrace();
            return;
        }
    }

    public void setGpsEnable(boolean bl2) {
        try {
            if (this.d != null) {
                this.d.a(bl2);
            }
            return;
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
            return;
        }
    }

    public void setTestProviderEnabled(String string2, boolean bl2) {
        try {
            if (this.b != null) {
                this.b.setTestProviderEnabled(string2, bl2);
            }
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public void setTestProviderLocation(String string2, Location location) {
        try {
            if (this.b != null) {
                this.b.setTestProviderLocation(string2, location);
            }
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public void setTestProviderStatus(String string2, int n2, Bundle bundle, long l2) {
        try {
            if (this.b != null) {
                this.b.setTestProviderStatus(string2, n2, bundle, l2);
            }
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    class a
    implements AMapLocationListener {
        a() {
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive exception aggregation
         */
        public void onLocationChanged(Location var1_1) {
            block20 : {
                block19 : {
                    block18 : {
                        block17 : {
                            var3_3 = 0;
                            if (var1_1 == null) ** GOTO lbl53
                            var1_1 = new AMapLocation((Location)var1_1);
                            var2_4 = 0;
lbl6: // 2 sources:
                            if (LocationManagerProxy.c(LocationManagerProxy.this) == null || var2_4 >= LocationManagerProxy.c(LocationManagerProxy.this).size()) ** GOTO lbl-1000
                            var4_6 = (i)LocationManagerProxy.c(LocationManagerProxy.this).get(var2_4);
                            if (var4_6 == null) break block17;
                            if (var4_6.b == null) ** GOTO lbl12
                            var4_6.b.onLocationChanged((AMapLocation)var1_1);
                        }
lbl13: // 2 sources:
                        if (var4_6 != null) {
                            if (var4_6.a != -1 || LocationManagerProxy.d(LocationManagerProxy.this) == null) break block18;
                            LocationManagerProxy.d(LocationManagerProxy.this).add((Object)var4_6);
                            break block18;
                        }
                        ** GOTO lbl48
lbl-1000: // 1 sources:
                        {
                            if (LocationManagerProxy.d(LocationManagerProxy.this) == null || LocationManagerProxy.d(LocationManagerProxy.this).size() <= 0 || LocationManagerProxy.c(LocationManagerProxy.this) == null) break block19;
                            for (var2_4 = var3_3; var2_4 < LocationManagerProxy.d(LocationManagerProxy.this).size(); ++var2_4) {
                                LocationManagerProxy.c(LocationManagerProxy.this).remove(LocationManagerProxy.d(LocationManagerProxy.this).get(var2_4));
                            }
                        }
                        LocationManagerProxy.d(LocationManagerProxy.this).clear();
                        if (LocationManagerProxy.c(LocationManagerProxy.this).size() != 0 || LocationManagerProxy.e(LocationManagerProxy.this) == null || LocationManagerProxy.f(LocationManagerProxy.this) == null) break block19;
                        LocationManagerProxy.e(LocationManagerProxy.this).removeUpdates((LocationListener)LocationManagerProxy.f(LocationManagerProxy.this));
                        return;
lbl28: // 2 sources:
                        if (LocationManagerProxy.c(LocationManagerProxy.this) != null && var2_5 < LocationManagerProxy.c(LocationManagerProxy.this).size()) {
                            var1_1 = (i)LocationManagerProxy.c(LocationManagerProxy.this).get(var2_5);
                            if (var1_1 != null && var1_1.a == -1 && LocationManagerProxy.d(LocationManagerProxy.this) != null) {
                                LocationManagerProxy.d(LocationManagerProxy.this).add(var1_1);
                            }
                            break block20;
                        }
                        if (LocationManagerProxy.d(LocationManagerProxy.this) == null || LocationManagerProxy.d(LocationManagerProxy.this).size() <= 0 || LocationManagerProxy.c(LocationManagerProxy.this) == null) break block19;
                        for (var2_5 = 0; var2_5 < LocationManagerProxy.d(LocationManagerProxy.this).size(); ++var2_5) {
                            LocationManagerProxy.c(LocationManagerProxy.this).remove(LocationManagerProxy.d(LocationManagerProxy.this).get(var2_5));
                        }
                        try {
                            LocationManagerProxy.d(LocationManagerProxy.this).clear();
                            if (LocationManagerProxy.c(LocationManagerProxy.this).size() == 0 && LocationManagerProxy.e(LocationManagerProxy.this) != null && LocationManagerProxy.f(LocationManagerProxy.this) != null) {
                                LocationManagerProxy.e(LocationManagerProxy.this).removeUpdates((LocationListener)LocationManagerProxy.f(LocationManagerProxy.this));
                                return;
                            }
                            break block19;
                        }
                        catch (Throwable var1_2) {
                            var1_2.printStackTrace();
                            return;
                        }
                        catch (Throwable var5_7) {}
                        ** GOTO lbl13
                    }
                    ++var2_4;
                    ** GOTO lbl6
                }
                return;
lbl53: // 1 sources:
                var2_5 = 0;
                ** GOTO lbl28
            }
            ++var2_5;
            ** GOTO lbl28
        }

        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
        }

        public void onProviderDisabled(String string2) {
        }

        public void onProviderEnabled(String string2) {
        }

        public void onStatusChanged(String string2, int n2, Bundle bundle) {
        }
    }

    class b
    implements AMapLocationListener {
        b() {
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public void onLocationChanged(Location location) {
            if (LocationManagerProxy.this.h == null || LocationManagerProxy.this.h.size() <= 0) return;
            for (PendingIntent pendingIntent : LocationManagerProxy.this.h) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable("location", (Parcelable)location);
                intent.putExtras(bundle);
                try {
                    pendingIntent.send(LocationManagerProxy.this.e, 0, intent);
                    continue;
                }
                catch (PendingIntent.CanceledException var3_5) {
                    try {
                        var3_5.printStackTrace();
                        continue;
                    }
                    catch (Throwable var1_2) {
                        var1_2.printStackTrace();
                        return;
                    }
                }
            }
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (LocationManagerProxy.this.h == null || LocationManagerProxy.this.h.size() <= 0) return;
            for (PendingIntent pendingIntent : LocationManagerProxy.this.h) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable("location", (Parcelable)aMapLocation);
                intent.putExtras(bundle);
                try {
                    pendingIntent.send(LocationManagerProxy.this.e, 0, intent);
                    continue;
                }
                catch (PendingIntent.CanceledException var3_5) {
                    try {
                        var3_5.printStackTrace();
                        continue;
                    }
                    catch (Throwable var1_2) {
                        var1_2.printStackTrace();
                        return;
                    }
                }
            }
        }

        public void onProviderDisabled(String string2) {
        }

        public void onProviderEnabled(String string2) {
        }

        public void onStatusChanged(String string2, int n2, Bundle bundle) {
        }
    }

}


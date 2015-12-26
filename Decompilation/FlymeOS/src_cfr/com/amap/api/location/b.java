/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.location.Location
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Message
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package com.amap.api.location;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.location.AMapLocalDayWeatherForecast;
import com.amap.api.location.AMapLocalWeatherForecast;
import com.amap.api.location.AMapLocalWeatherListener;
import com.amap.api.location.AMapLocalWeatherLive;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.core.AMapLocException;
import com.amap.api.location.core.c;
import com.amap.api.location.core.d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class b
implements AMapLocationListener {
    a a = null;
    AMapLocalWeatherListener b;
    com.amap.api.location.a c;
    List<Integer> d = new ArrayList();
    List<AMapLocalWeatherListener> e = new ArrayList();
    private Context f;
    private int g;
    private AMapLocalWeatherListener h;
    private boolean i = false;

    public b(com.amap.api.location.a a2, Context context) {
        this.f = context;
        this.c = a2;
        this.a = new a(this, context.getMainLooper());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private AMapLocalWeatherLive a(String string2, AMapLocation aMapLocation) {
        AMapLocalWeatherLive aMapLocalWeatherLive = new AMapLocalWeatherLive();
        try {
            d.a(string2);
        }
        catch (AMapLocException var4_5) {
            aMapLocalWeatherLive.a(var4_5);
        }
        try {
            string2 = new JSONObject(string2).getJSONArray("lives");
            if (string2 != null && string2.length() > 0) {
                Object object = (JSONObject)string2.get(0);
                string2 = this.a((JSONObject)object, "weather");
                String string3 = this.a((JSONObject)object, "temperature");
                String string4 = this.a((JSONObject)object, "winddirection");
                String string5 = this.a((JSONObject)object, "windpower");
                String string6 = this.a((JSONObject)object, "humidity");
                object = this.a((JSONObject)object, "reporttime");
                aMapLocalWeatherLive.a(string2);
                aMapLocalWeatherLive.f((String)object);
                aMapLocalWeatherLive.e(string6);
                aMapLocalWeatherLive.b(string3);
                aMapLocalWeatherLive.c(string4);
                aMapLocalWeatherLive.d(string5);
                aMapLocalWeatherLive.setCity(aMapLocation.getCity());
                aMapLocalWeatherLive.setCityCode(aMapLocation.getCityCode());
                aMapLocalWeatherLive.setProvince(aMapLocation.getProvince());
            }
            return aMapLocalWeatherLive;
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return aMapLocalWeatherLive;
        }
    }

    private String a() {
        return "http://restapi.amap.com/v3/weather/weatherInfo?";
    }

    private byte[] a(AMapLocation aMapLocation, String string2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("output=json&ec=1").append("&extensions=" + string2).append("&city=").append(aMapLocation.getAdCode());
        stringBuffer.append("&key=" + c.a());
        return com.amap.api.location.core.a.b(com.amap.api.location.core.a.a(stringBuffer.toString())).getBytes("utf-8");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private AMapLocalWeatherForecast b(String string2, AMapLocation aMapLocation) {
        AMapLocalWeatherForecast aMapLocalWeatherForecast = new AMapLocalWeatherForecast();
        try {
            d.a(string2);
        }
        catch (AMapLocException var5_4) {
            aMapLocalWeatherForecast.a(var5_4);
            var5_4.printStackTrace();
        }
        if ((string2 = new JSONObject(string2).getJSONArray("forecasts")) != null && string2.length() > 0) {
            string2 = (JSONObject)string2.get(0);
            aMapLocalWeatherForecast.a(this.a((JSONObject)string2, "reporttime"));
            string2 = string2.getJSONArray("casts");
            if (string2 != null && string2.length() > 0) {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < string2.length(); ++i2) {
                    AMapLocalDayWeatherForecast aMapLocalDayWeatherForecast = new AMapLocalDayWeatherForecast();
                    Object object = (JSONObject)string2.get(i2);
                    String string3 = this.a((JSONObject)object, "date");
                    String string4 = this.a((JSONObject)object, "week");
                    String string5 = this.a((JSONObject)object, "dayweather");
                    String string6 = this.a((JSONObject)object, "nightweather");
                    String string7 = this.a((JSONObject)object, "daytemp");
                    String string8 = this.a((JSONObject)object, "nighttemp");
                    String string9 = this.a((JSONObject)object, "daywind");
                    String string10 = this.a((JSONObject)object, "nightwind");
                    String string11 = this.a((JSONObject)object, "daypower");
                    object = this.a((JSONObject)object, "nightpower");
                    aMapLocalDayWeatherForecast.a(string3);
                    aMapLocalDayWeatherForecast.b(string4);
                    aMapLocalDayWeatherForecast.c(string5);
                    aMapLocalDayWeatherForecast.d(string6);
                    aMapLocalDayWeatherForecast.e(string7);
                    aMapLocalDayWeatherForecast.f(string8);
                    aMapLocalDayWeatherForecast.g(string9);
                    aMapLocalDayWeatherForecast.h(string10);
                    aMapLocalDayWeatherForecast.i(string11);
                    aMapLocalDayWeatherForecast.j((String)object);
                    aMapLocalDayWeatherForecast.setCity(aMapLocation.getCity());
                    aMapLocalDayWeatherForecast.setCityCode(aMapLocation.getCityCode());
                    aMapLocalDayWeatherForecast.setProvince(aMapLocation.getProvince());
                    arrayList.add(aMapLocalDayWeatherForecast);
                }
                aMapLocalWeatherForecast.a((List<AMapLocalDayWeatherForecast>)arrayList);
            }
        }
        return aMapLocalWeatherForecast;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected String a(JSONObject jSONObject, String string2) {
        if (jSONObject == null || !jSONObject.has(string2) || jSONObject.getString(string2).equals((Object)"[]")) {
            return "";
        }
        return jSONObject.optString(string2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void a(int n2, AMapLocalWeatherListener aMapLocalWeatherListener, AMapLocation aMapLocation) {
        try {
            this.g = n2;
            this.h = aMapLocalWeatherListener;
            if (aMapLocation == null) {
                if (this.d != null) {
                    this.d.add(this.g);
                }
                if (this.e != null) {
                    this.e.add(this.h);
                }
                if (this.i) {
                    return;
                }
                this.i = true;
                this.c.a(-1, 10.0f, this, "lbs", true);
                return;
            }
            if (n2 == 1) {
                this.a(aMapLocation, "base", aMapLocalWeatherListener);
            }
        }
        catch (Throwable var2_3) {
            var2_3.printStackTrace();
            return;
        }
        if (n2 == 2) {
            this.a(aMapLocation, "all", aMapLocalWeatherListener);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    void a(AMapLocation object, String string2, AMapLocalWeatherListener object2) {
        String string3;
        Object object3;
        Object object4;
        this.b = object2;
        if (object == null) {
            return;
        }
        object3 = this.a((AMapLocation)((Object)object), string2);
        string3 = this.a();
        object2 = new AMapLocException();
        object4 = null;
        try {
            object4 = object3 = (Object)ahq.a().a(this.f, string3, (byte[])object3, "sea");
        }
        catch (AMapLocException var3_4) {}
        if ("base".equals((Object)string2)) {
            if (object4 != null) {
                object3 = this.a((String)object4, (AMapLocation)((Object)object));
            } else {
                object3 = new AMapLocalWeatherLive();
                object2 = new AMapLocException("http\u8fde\u63a5\u5931\u8d25 - ConnectionException");
            }
            object3.a((AMapLocException)((Object)object2));
            object3.setCity(object.getCity());
            object3.setCityCode(object.getCityCode());
            object3.setProvince(object.getProvince());
            string3 = Message.obtain();
            string3.what = 1;
            string3.obj = object3;
            this.a.sendMessage((Message)string3);
        }
        if (!"all".equals((Object)string2)) return;
        if (object4 != null) {
            object = this.b((String)object4, (AMapLocation)((Object)object));
        } else {
            object = new AMapLocalWeatherForecast();
            object2 = new AMapLocException("http\u8fde\u63a5\u5931\u8d25 - ConnectionException");
        }
        object.a((AMapLocException)((Object)object2));
        string2 = Message.obtain();
        string2.what = 2;
        string2.obj = object;
        this.a.sendMessage((Message)string2);
    }

    public void onLocationChanged(Location location) {
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public void onLocationChanged(AMapLocation var1_1) {
        if (var1_1 == null) ** GOTO lbl-1000
        try {
            if (var1_1.getAMapException() != null && var1_1.getAMapException().getErrorCode() == 0 && var1_1.getAdCode() != null && var1_1.getAdCode().length() > 0) {
                this.c.a(this);
                var2_3 = Message.obtain();
                var2_3.what = 3;
                var2_3.obj = var1_1;
                this.a.sendMessage(var2_3);
            } else lbl-1000: // 2 sources:
            {
                this.c.a(this);
                var1_1 = Message.obtain();
                var2_4 = new AMapLocException("\u5b9a\u4f4d\u5931\u8d25\u65e0\u6cd5\u83b7\u53d6\u57ce\u5e02\u4fe1\u606f");
                var1_1.what = 4;
                var1_1.obj = var2_4;
                this.a.sendMessage((Message)var1_1);
            }
            this.i = false;
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public void onProviderDisabled(String string2) {
    }

    public void onProviderEnabled(String string2) {
    }

    public void onStatusChanged(String string2, int n2, Bundle bundle) {
    }

    static class a
    extends Handler {
        private WeakReference<b> a;

        a(b b2, Looper looper) {
            super(looper);
            try {
                this.a = new WeakReference<b>(b2);
                return;
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
                return;
            }
        }

        /*
         * Exception decompiling
         */
        public void handleMessage(Message var1_1) {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.ConfusedCFRException: Started 3 blocks at once
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:371)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:449)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
            // org.benf.cfr.reader.Main.doJar(Main.java:128)
            // org.benf.cfr.reader.Main.main(Main.java:178)
            throw new IllegalStateException("Decompilation failed");
        }
    }

}


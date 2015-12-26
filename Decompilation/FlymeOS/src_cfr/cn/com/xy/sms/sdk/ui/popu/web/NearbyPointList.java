/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.ActionBar
 *  android.app.Activity
 *  android.app.Application
 *  android.content.Context
 *  android.content.Intent
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Message
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.Window
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.ArrayList
 *  java.util.HashMap
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.ui.popu.web;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.com.xy.sms.sdk.action.NearbyPoint;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NearbyPointList
extends Activity {
    private static final String a = NearbyPointList.class.getSimpleName();
    private ListView b;
    private LinearLayout c;
    private ImageView d;
    private TextView e;
    private TextView f = null;
    private TextView g = null;
    private ImageView h = null;
    private ArrayList<HashMap<String, Object>> i = new ArrayList();
    private dy j;
    private LinearLayout k;
    private LinearLayout l;
    private LinearLayout m;
    private LinearLayout n;
    private NearbyPoint o = null;
    private int p = 0;
    private int q = 0;
    private a r = null;
    private String s = null;
    private double t = -1.0;
    private double u = -1.0;
    private Handler v;

    public NearbyPointList() {
        this.v = new dx(this);
    }

    public static /* synthetic */ double a(NearbyPointList nearbyPointList, double d2) {
        nearbyPointList.t = d2;
        return d2;
    }

    public static /* synthetic */ ArrayList a(NearbyPointList nearbyPointList) {
        return nearbyPointList.i;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private ArrayList<HashMap<String, Object>> a(String string2) {
        try {
            ArrayList arrayList = new ArrayList();
            string2 = new JSONObject(string2);
            this.p = string2.getInt("total");
            JSONArray jSONArray = string2.getJSONArray("results");
            int n2 = 0;
            do {
                string2 = arrayList;
                if (n2 >= jSONArray.length()) return string2;
                JSONObject jSONObject = jSONArray.getJSONObject(n2);
                string2 = new HashMap();
                string2.put((Object)"name", (Object)jSONObject.getString("name"));
                string2.put((Object)"address", (Object)jSONObject.getString("address"));
                if (jSONObject.has("telephone")) {
                    string2.put((Object)"phone", (Object)jSONObject.getString("telephone"));
                } else {
                    string2.put((Object)"phone", (Object)"");
                }
                string2.put((Object)"distance", (Object)jSONObject.getJSONObject("detail_info").getInt("distance"));
                jSONObject = jSONObject.getJSONObject("location");
                string2.put((Object)"longitude", (Object)jSONObject.getDouble("lng"));
                string2.put((Object)"latitude", (Object)jSONObject.getDouble("lat"));
                arrayList.add((Object)string2);
                ++n2;
            } while (true);
        }
        catch (Exception var1_2) {
            return new ArrayList();
        }
    }

    private void a(double d2, double d3) {
        if (d2 <= 0.0 || d3 <= 0.0) {
            this.a(8, 0, 8, 8);
            return;
        }
        this.o.sendMapQueryUrl(this.s, d3, d2, 0);
    }

    private void a(int n2, int n3, int n4, int n5) {
        this.k.setVisibility(n2);
        this.l.setVisibility(n3);
        this.m.setVisibility(n4);
        this.n.setVisibility(n5);
        if (n3 == 0 || n4 == 0) {
            this.q = 0;
        }
    }

    public static /* synthetic */ void a(NearbyPointList nearbyPointList, double d2, double d3) {
        nearbyPointList.a(d2, d3);
    }

    public static /* synthetic */ void a(NearbyPointList nearbyPointList, int n2, int n3, int n4, int n5) {
        nearbyPointList.a(n2, n3, n4, n5);
    }

    public static /* synthetic */ void a(NearbyPointList nearbyPointList, String string2) {
        nearbyPointList.b(string2);
    }

    public static /* synthetic */ double b(NearbyPointList nearbyPointList, double d2) {
        nearbyPointList.u = d2;
        return d2;
    }

    private void b(double d2, double d3) {
        if (d2 > 0.0 && d3 > 0.0) {
            try {
                this.a(d2, d3);
                return;
            }
            catch (NumberFormatException var5_3) {
                LogManager.i(a, "\u83b7\u53d6\u7ecf\u7eac\u5ea6\u5931\u8d25");
                this.a(8, 0, 8, 8);
                return;
            }
        }
        try {
            DuoquUtils.getSdkDoAction().getLocation(this.getApplicationContext(), this.v);
            return;
        }
        catch (Exception var5_4) {
            LogManager.i(a, "\u83b7\u53d6\u7ecf\u7eac\u5ea6\u5931\u8d25");
            this.a(8, 0, 8, 8);
            return;
        }
    }

    public static /* synthetic */ void b(NearbyPointList nearbyPointList, double d2, double d3) {
        nearbyPointList.b(d2, d3);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void b(String object) {
        if (StringUtils.isNull((String)object)) {
            this.a(8, 0, 8, 8);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject((String)object);
            if (jSONObject.getInt("status") != 0) {
                throw new JSONException("\u8fd4\u56de\u7ed3\u679c\u5f02\u5e38");
            }
            if (jSONObject.getInt("total") == 0) {
                throw new JSONException("\u68c0\u7d22\u7ed3\u679c\u6570\u636e\u6570\u91cf\u4e3a0");
            }
        }
        catch (Exception var1_2) {
            this.a(8, 0, 8, 8);
            return;
        }
        for (JSONObject jSONObject : this.a((String)object)) {
            this.i.add((Object)jSONObject);
        }
        if (this.p > 10) {
            this.c.setVisibility(0);
        }
        this.j.notifyDataSetChanged();
        this.a(8, 8, 8, 0);
    }

    public static /* synthetic */ String c() {
        return a;
    }

    private void d() {
        this.a(0, 8, 8, 8);
        if (this.r != null) {
            this.r.isInterrupted();
            this.r = null;
        }
        this.r = new a(null);
        this.r.start();
    }

    static /* synthetic */ int f(NearbyPointList nearbyPointList) {
        int n2 = nearbyPointList.q;
        nearbyPointList.q = n2 + 1;
        return n2;
    }

    public static /* synthetic */ int m(NearbyPointList nearbyPointList) {
        return nearbyPointList.p;
    }

    public void a() {
        Window window = this.getWindow();
        window.setFlags(67108864, 67108864);
        window.setFlags(134217728, 134217728);
    }

    void b() {
        this.h.setOnClickListener((View.OnClickListener)new du(this));
        this.f.setOnClickListener((View.OnClickListener)new dw(this));
    }

    @SuppressLint(value={"NewApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(br.e.duoqu_nearby_point_list);
        bundle = this.getActionBar();
        bundle.setDisplayShowCustomEnabled(true);
        bundle.setDisplayShowHomeEnabled(false);
        bundle.setDisplayOptions(16);
        RelativeLayout relativeLayout = (RelativeLayout)this.getLayoutInflater().inflate(br.e.duoqu_web_action_bar, null);
        bundle.setCustomView((View)relativeLayout);
        this.s = this.getIntent().getStringExtra("address");
        this.o = new NearbyPoint(this, this.v);
        this.k = (LinearLayout)this.findViewById(br.d.duoqu_ll_nearby_point_loading);
        this.l = (LinearLayout)this.findViewById(br.d.duoqu_ll_nearby_point_not_find);
        this.l.setOnClickListener((View.OnClickListener)new c(null));
        this.m = (LinearLayout)this.findViewById(br.d.duoqu_ll_nearby_point_network_lose);
        this.m.setOnClickListener((View.OnClickListener)new c(null));
        this.n = (LinearLayout)this.findViewById(br.d.duoqu_ll_nearby_point_list);
        this.b = (ListView)this.findViewById(br.d.duoqu_lv_nearby_point);
        this.j = new dy((Context)this, this.i);
        this.f = (TextView)relativeLayout.findViewById(br.d.duoqu_header_back);
        this.g = (TextView)relativeLayout.findViewById(br.d.duoqu_title_name);
        this.g.setText((CharSequence)this.s);
        this.h = (ImageView)relativeLayout.findViewById(br.d.duoqu_header_menu);
        bundle = this.getLayoutInflater().inflate(br.e.duoqu_nearby_point_list_bottom, null);
        this.d = (ImageView)bundle.findViewById(br.d.duoqu_iv_load_more);
        this.e = (TextView)bundle.findViewById(br.d.duoqu_tv_load_more);
        this.c = (LinearLayout)bundle.findViewById(br.d.duoqu_ll_load_more);
        this.c.setOnClickListener((View.OnClickListener)new b(null));
        this.b.addFooterView((View)bundle);
        this.b.setDivider(null);
        this.b.setAdapter((ListAdapter)this.j);
        this.b.setOnItemClickListener((AdapterView.OnItemClickListener)new dt(this));
        if (Build.VERSION.SDK_INT >= 19) {
            this.a();
        }
        this.b();
        this.d();
    }

    protected void onDestroy() {
        this.v.removeCallbacksAndMessages((Object)null);
        super.onDestroy();
    }

    class a
    extends Thread {
        private a() {
        }

        /* synthetic */ a(dt dt2) {
            this();
        }

        public void run() {
            try {
                Thread.sleep((long)200);
                NearbyPointList.this.v.obtainMessage(4101).sendToTarget();
                return;
            }
            catch (InterruptedException var1_1) {
                var1_1.printStackTrace();
                return;
            }
        }
    }

    class b
    implements View.OnClickListener {
        private b() {
        }

        /* synthetic */ b(dt dt2) {
            this();
        }

        public void onClick(View view) {
            NearbyPointList.this.d.setVisibility(8);
            NearbyPointList.this.e.setVisibility(0);
            NearbyPointList.this.e.setText((CharSequence)NearbyPointList.this.getApplication().getString(br.f.duoqu_tip_loading));
            NearbyPointList.this.c.setEnabled(false);
            NearbyPointList.f(NearbyPointList.this);
            NearbyPointList.this.o.sendMapQueryUrl(NearbyPointList.this.s, NearbyPointList.this.u, NearbyPointList.this.t, NearbyPointList.this.q);
        }
    }

    class c
    implements View.OnClickListener {
        private c() {
        }

        /* synthetic */ c(dt dt2) {
            this();
        }

        public void onClick(View view) {
            NearbyPointList.this.d();
        }
    }

}


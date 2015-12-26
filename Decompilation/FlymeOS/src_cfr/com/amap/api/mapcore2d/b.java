/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.TypedArray
 *  android.graphics.Bitmap
 *  android.graphics.Canvas
 *  android.graphics.Color
 *  android.graphics.Matrix
 *  android.graphics.Point
 *  android.graphics.PointF
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.Drawable$Callback
 *  android.location.Location
 *  android.os.Handler
 *  android.os.Message
 *  android.os.Parcelable
 *  android.os.RemoteException
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.view.GestureDetector
 *  android.view.GestureDetector$OnDoubleTapListener
 *  android.view.GestureDetector$OnGestureListener
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.LinearLayout
 *  android.widget.Scroller
 *  android.widget.TextView
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.lang.reflect.Field
 *  java.lang.reflect.Method
 *  java.util.ArrayList
 *  java.util.Timer
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.amap.api.mapcore2d.aa;
import com.amap.api.mapcore2d.ab;
import com.amap.api.mapcore2d.ac;
import com.amap.api.mapcore2d.ae;
import com.amap.api.mapcore2d.af;
import com.amap.api.mapcore2d.ag;
import com.amap.api.mapcore2d.ah;
import com.amap.api.mapcore2d.ai;
import com.amap.api.mapcore2d.ak;
import com.amap.api.mapcore2d.am;
import com.amap.api.mapcore2d.an;
import com.amap.api.mapcore2d.ao;
import com.amap.api.mapcore2d.ap;
import com.amap.api.mapcore2d.aq;
import com.amap.api.mapcore2d.as;
import com.amap.api.mapcore2d.at;
import com.amap.api.mapcore2d.au;
import com.amap.api.mapcore2d.aw;
import com.amap.api.mapcore2d.ay;
import com.amap.api.mapcore2d.b$1;
import com.amap.api.mapcore2d.b$2;
import com.amap.api.mapcore2d.bb;
import com.amap.api.mapcore2d.bd;
import com.amap.api.mapcore2d.be;
import com.amap.api.mapcore2d.bg;
import com.amap.api.mapcore2d.bh;
import com.amap.api.mapcore2d.bk;
import com.amap.api.mapcore2d.bl;
import com.amap.api.mapcore2d.bm;
import com.amap.api.mapcore2d.bn;
import com.amap.api.mapcore2d.bo;
import com.amap.api.mapcore2d.bp;
import com.amap.api.mapcore2d.bq;
import com.amap.api.mapcore2d.br;
import com.amap.api.mapcore2d.bs;
import com.amap.api.mapcore2d.bu;
import com.amap.api.mapcore2d.bv;
import com.amap.api.mapcore2d.by;
import com.amap.api.mapcore2d.c;
import com.amap.api.mapcore2d.cb;
import com.amap.api.mapcore2d.cc;
import com.amap.api.mapcore2d.ci;
import com.amap.api.mapcore2d.ck;
import com.amap.api.mapcore2d.cm;
import com.amap.api.mapcore2d.cn;
import com.amap.api.mapcore2d.cp;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.d;
import com.amap.api.mapcore2d.e;
import com.amap.api.mapcore2d.ed;
import com.amap.api.mapcore2d.f;
import com.amap.api.mapcore2d.g;
import com.amap.api.mapcore2d.m;
import com.amap.api.mapcore2d.s;
import com.amap.api.mapcore2d.t;
import com.amap.api.mapcore2d.u;
import com.amap.api.mapcore2d.v;
import com.amap.api.mapcore2d.w;
import com.amap.api.mapcore2d.x;
import com.amap.api.mapcore2d.y;
import com.amap.api.mapcore2d.z;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.GroundOverlayOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.PolygonOptions;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.maps2d.model.Text;
import com.amap.api.maps2d.model.TextOptions;
import com.amap.api.maps2d.model.TileOverlay;
import com.amap.api.maps2d.model.TileOverlayOptions;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class b
extends View
implements GestureDetector.OnDoubleTapListener,
GestureDetector.OnGestureListener,
ag,
bm.b,
bv.a,
t.a,
v.a {
    private cm A;
    private bu B;
    private LocationSource C;
    private x D;
    private com.amap.api.mapcore2d.a E;
    private boolean F;
    private boolean G;
    private AMap.OnCameraChangeListener H;
    private s I;
    private AMap.CancelableCallback J;
    private bh K;
    private boolean L;
    private boolean M;
    private View N;
    private AMap.OnInfoWindowClickListener O;
    private AMap.InfoWindowAdapter P;
    private bk Q;
    private AMap.OnMarkerClickListener R;
    private Drawable S;
    private aq T;
    private boolean U;
    private boolean V;
    private boolean W;
    private AMap.OnMarkerDragListener Z;
    public bb a;
    private int aA;
    private int aB;
    private boolean aC;
    private a aD;
    private Thread aE;
    private AMap.OnMapTouchListener aa;
    private AMap.OnMapLongClickListener ab;
    private AMap.OnMapLoadedListener ac;
    private AMap.OnMapClickListener ad;
    private boolean ae;
    private AMap.OnMapScreenShotListener af;
    private Timer ag;
    private TimerTask ah;
    private Handler ai;
    private Point aj;
    private GestureDetector ak;
    private bm.a al;
    private ArrayList<GestureDetector.OnGestureListener> am;
    private ArrayList<bm.b> an;
    private Scroller ao;
    private int ap;
    private int aq;
    private Matrix ar;
    private float as;
    private boolean at;
    private float au;
    private float av;
    private int aw;
    private int ax;
    private long ay;
    private int az;
    int[] b = new int[2];
    boolean c = false;
    bd d;
    cn e;
    public bg f;
    protected au g;
    public cc h;
    public be i;
    final Handler j;
    private Context k;
    private bl l;
    private boolean m = false;
    private boolean n = true;
    private Marker o;
    private ak p;
    private final int[] q = new int[]{10000000, 5000000, 2000000, 1000000, 500000, 200000, 100000, 50000, 30000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 25, 10, 5};
    private boolean r = true;
    private int s = 1;
    private int t = 0;
    private ay u;
    private Location v;
    private g w;
    private AMap.OnMyLocationChangeListener x;
    private boolean y;
    private bn z;

    public b(Context context) {
        super(context);
        this.d = new bd(this);
        this.y = true;
        this.E = null;
        this.F = false;
        this.G = false;
        this.J = null;
        this.L = false;
        this.M = false;
        this.S = null;
        this.U = false;
        this.V = false;
        this.W = false;
        this.ae = false;
        this.af = null;
        this.ag = null;
        this.ah = new c(this);
        this.ai = new d(this);
        this.j = new e(this);
        this.am = new ArrayList();
        this.an = new ArrayList();
        this.ap = 0;
        this.aq = 0;
        this.ar = new Matrix();
        this.as = 1.0f;
        this.at = false;
        this.ay = 0;
        this.az = 0;
        this.aA = 0;
        this.aB = 0;
        this.aC = false;
        this.aD = null;
        this.aE = new f(this);
        this.R();
        this.setClickable(true);
        this.a(context, null);
    }

    public b(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.a(context, attributeSet);
    }

    private b(Context context, AttributeSet attributeSet, int n2) {
        super(context, attributeSet, n2);
        this.d = new bd(this);
        this.y = true;
        this.E = null;
        this.F = false;
        this.G = false;
        this.J = null;
        this.L = false;
        this.M = false;
        this.S = null;
        this.U = false;
        this.V = false;
        this.W = false;
        this.ae = false;
        this.af = null;
        this.ag = null;
        this.ah = new c(this);
        this.ai = new d(this);
        this.j = new e(this);
        this.am = new ArrayList();
        this.an = new ArrayList();
        this.ap = 0;
        this.aq = 0;
        this.ar = new Matrix();
        this.as = 1.0f;
        this.at = false;
        this.ay = 0;
        this.az = 0;
        this.aA = 0;
        this.aB = 0;
        this.aC = false;
        this.aD = null;
        this.aE = new f(this);
        this.R();
        this.k = context;
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, new int[]{16843281});
        if (false) {
            throw new NullPointerException();
        }
        typedArray.getString(0);
        this.a(context, attributeSet);
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void R() {
        Method[] arrmethod = View.class.getMethods();
        int n2 = arrmethod.length;
        int n3 = 0;
        while (n3 < n2) {
            Method method = arrmethod[n3];
            if (method.getName().equals((Object)"setLayerType")) {
                if (method == null) return;
                method.invoke((Object)this, new Object[]{View.class.getField("LAYER_TYPE_SOFTWARE").getInt((Object)null), null});
                return;
            }
            ++n3;
        }
        return;
        catch (Exception exception) {
            cy.a(exception, "AMapDelegateImpGLSurfaceView", "setLayerType");
            return;
        }
    }

    private void S() {
        this.a(this.k);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        this.f.addView((View)this, 0, layoutParams);
    }

    private void T() {
        this.l.a();
        if (this.a != null) {
            this.a.a(true);
            this.a.e();
        }
        this.a = null;
        this.l = null;
    }

    private void U() {
        u u2;
        if (this.L) {
            this.L = false;
        }
        if (this.W) {
            this.W = false;
            u2 = u.a();
            u2.i = true;
            this.d.a(u2);
        }
        if (this.M) {
            this.M = false;
            u2 = u.a();
            u2.i = true;
            this.d.a(u2);
        }
        this.V = false;
        if (this.Z != null && this.o != null) {
            this.Z.onMarkerDragEnd(this.o);
            this.o = null;
            this.p = null;
        }
    }

    private void V() {
        if (this.aj == null) {
            return;
        }
        int n2 = this.aj.x;
        int n3 = this.az;
        int n4 = this.aj.y;
        int n5 = this.aA;
        this.aj.x = this.az;
        this.aj.y = this.aA;
        this.a.c(n2 - n3, n4 - n5);
    }

    private CameraPosition W() {
        ae ae2 = this.C();
        if (ae2 == null) {
            return null;
        }
        return CameraPosition.fromLatLngZoom(new LatLng((double)ae2.b() / 1000000.0, (double)ae2.a() / 1000000.0), this.f());
    }

    private void X() {
        int n2;
        if (this.B == null) {
            return;
        }
        int n3 = this.getWidth();
        Object object = new aa();
        aa aa2 = new aa();
        this.a(0, 0, (aa)object);
        this.a(n3, 0, aa2);
        int n4 = (int)this.f();
        n3 = n2 = this.q[n4];
        if (y.i == 512) {
            n3 = n2 * 256 / 512;
        }
        n2 = (int)(this.K.h[n4] * (double)y.k / 0.0254);
        object = cy.b(n3);
        this.B.a(n2);
        this.B.a((String)object);
        this.B.invalidate();
    }

    private LatLng Y() {
        ae ae2 = this.C();
        if (ae2 == null) {
            return null;
        }
        return new LatLng(z.a(ae2.b()), z.a(ae2.a()));
    }

    private an Z() {
        ae ae2 = this.C();
        an an2 = new an();
        an2.a = (int)ae2.e();
        an2.b = (int)ae2.f();
        return an2;
    }

    static /* synthetic */ AMap.CancelableCallback a(b b2, AMap.CancelableCallback cancelableCallback) {
        b2.J = cancelableCallback;
        return cancelableCallback;
    }

    static /* synthetic */ AMap.OnMapScreenShotListener a(b b2, AMap.OnMapScreenShotListener onMapScreenShotListener) {
        b2.af = onMapScreenShotListener;
        return onMapScreenShotListener;
    }

    static /* synthetic */ AMap.OnMapTouchListener a(b b2) {
        return b2.aa;
    }

    private LatLng a(LatLng object) {
        an an2 = new an();
        this.b(object.latitude, object.longitude, an2);
        an2.b -= 60;
        object = new aa();
        this.a(an2.a, an2.b, (aa)object);
        return new LatLng(object.b, object.a);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void a(float f2, PointF object, float f3, float f4) {
        boolean bl2;
        int n2;
        try {
            boolean bl3 = this.g.f();
            if (!bl3) {
                return;
            }
        }
        catch (RemoteException var2_3) {
            cy.a((Throwable)var2_3, "AMapDelegateImpGLSurfaceView", "doScale");
        }
        this.aB = 2;
        int n3 = this.l.b.c() / 2;
        int n4 = this.l.b.d() / 2;
        if (f2 > 0.0f) {
            this.e.a((float)(this.l.b.e() + 1));
            this.L();
            n2 = (int)Math.floor((double)f2);
            bl2 = true;
        } else {
            if (f2 >= 0.0f) return;
            this.e.a((float)(this.l.b.e() - 1));
            this.L();
            n2 = (int)Math.floor((double)Math.abs((float)f2));
            bl2 = false;
        }
        n2 = bl2 ? (n2 += this.l.b.e()) : this.l.b.e() - n2;
        if ((n2 = this.a(n2)) == this.l.b.e()) return;
        this.b[0] = this.b[1];
        this.b[1] = n2;
        if (this.b[0] == this.b[1]) return;
        object = this.l.a.a(n3, n4);
        this.l.b.a(n2);
        this.l.b.a((ae)object);
    }

    private void a(int n2, int n3) {
        if (this.aj == null) {
            return;
        }
        this.az = n2;
        this.aA = n3;
        this.V();
    }

    private void a(int n2, int n3, an an2) {
        int n4 = (int)this.f();
        Object object = new PointF((float)n2, (float)n3);
        object = this.K.a((PointF)object, this.K.i, this.K.k, this.K.h[n4], this.K.l);
        if (an2 != null) {
            an2.a = (int)object.e();
            an2.b = (int)object.f();
        }
    }

    private void a(Context context) {
        this.aj = null;
        this.ak = new GestureDetector((GestureDetector.OnGestureListener)this);
        this.al = bm.a(context, this);
        this.ao = new Scroller(context);
        new DisplayMetrics();
        context = context.getApplicationContext().getResources().getDisplayMetrics();
        this.aw = context.widthPixels;
        this.ax = context.heightPixels;
        this.ap = context.widthPixels / 2;
        this.aq = context.heightPixels / 2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(Context object, AttributeSet attributeSet) {
        this.k = object;
        this.T = new bs(this);
        this.setBackgroundColor(Color.rgb((int)222, (int)215, (int)214));
        v.a().a(this);
        bv.a().a(this);
        t.a().a(this);
        this.E = new com.amap.api.mapcore2d.a(this);
        this.w = new g(this);
        this.I = new s((Context)object);
        this.h = new cc(this.k, this);
        this.l = new bl(this.k, this);
        this.h.a(true);
        this.K = this.l.h;
        this.a = new bb(this.l);
        this.g = new ci(this);
        this.e = new cn(this.k, this.a, this);
        this.f = new bg(this.k, this);
        this.u = new ay(this.k, this.d, this);
        this.A = new cm(this.k, this);
        this.B = new bu(this.k, this);
        this.D = new x(this.k, this.d, this);
        this.i = new be(this.k, attributeSet, this);
        object = new ViewGroup.LayoutParams(-1, -1);
        this.S();
        this.f.addView((View)this.h, (ViewGroup.LayoutParams)object);
        this.f.addView((View)this.A, (ViewGroup.LayoutParams)object);
        this.f.addView((View)this.B, (ViewGroup.LayoutParams)object);
        object = new bg.a((ViewGroup.LayoutParams)object);
        this.f.addView((View)this.i, (ViewGroup.LayoutParams)object);
        object = new bg.a(-2, -2, new LatLng(0.0, 0.0), 0, 0, 83);
        this.f.addView((View)this.e, (ViewGroup.LayoutParams)object);
        object = new bg.a(-2, -2, new LatLng(0.0, 0.0), 0, 0, 83);
        this.f.addView((View)this.u, (ViewGroup.LayoutParams)object);
        try {
            if (!this.q().d()) {
                this.u.setVisibility(8);
            }
        }
        catch (RemoteException var1_2) {
            cy.a((Throwable)var1_2, "AMapDelegateImpGLSurfaceView", "initEnviornment");
        }
        this.D.setVisibility(8);
        object = new bg.a(-2, -2, new LatLng(0.0, 0.0), 0, 0, 51);
        this.f.addView((View)this.D, (ViewGroup.LayoutParams)object);
        this.z = new bn(this);
        this.e.setId(m.a);
        try {
            this.aE.setName("AuthThread");
            this.aE.start();
            if (this.ag == null) {
                this.ag = new Timer();
                this.ag.schedule(this.ah, 10000, 1000);
            }
            return;
        }
        catch (Exception var1_3) {
            cy.a(var1_3, "AMapDelegateImpGLSurfaceView", "initEnviornment");
            return;
        }
    }

    private void a(MotionEvent object) {
        if (this.V && this.p != null && this.o != null) {
            int n2 = (int)object.getX();
            int n3 = (int)(object.getY() - 60.0f);
            object = new aa();
            this.a(n2, n3, (aa)object);
            object = new LatLng(object.b, object.a);
            if (this.p != null && this.p.h()) {
                this.p.a((LatLng)object);
                if (this.Z != null) {
                    this.Z.onMarkerDrag(this.o);
                }
            }
        }
    }

    static /* synthetic */ void a(b b2, boolean bl2, CameraPosition cameraPosition) {
        b2.a(bl2, cameraPosition);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(boolean bl2, CameraPosition cameraPosition) {
        if (this.H == null || !this.I.a() || !this.isEnabled()) {
            return;
        }
        CameraPosition cameraPosition2 = cameraPosition;
        if (cameraPosition == null) {
            try {
                cameraPosition2 = this.g();
            }
            catch (RemoteException var3_5) {
                cy.a((Throwable)var3_5, "AMapDelegateImpGLSurfaceView", "cameraChangeFinish");
                cameraPosition2 = cameraPosition;
            }
        }
        try {
            this.H.onCameraChangeFinish(cameraPosition2);
            return;
        }
        catch (Throwable var2_3) {
            var2_3.printStackTrace();
            return;
        }
    }

    static /* synthetic */ boolean a(b b2, boolean bl2) {
        b2.F = bl2;
        return bl2;
    }

    static /* synthetic */ bl b(b b2) {
        return b2.l;
    }

    private boolean b(MotionEvent motionEvent) {
        boolean bl2;
        boolean bl3 = bl2 = this.al.a(motionEvent, this.getWidth(), this.getHeight());
        if (!bl2) {
            bl3 = this.ak.onTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 1 && this.W) {
            t.a().b();
        }
        if (motionEvent.getAction() == 2) {
            this.a(motionEvent);
        }
        if (motionEvent.getAction() == 1) {
            this.U();
        }
        return bl3;
    }

    static /* synthetic */ boolean b(b b2, boolean bl2) {
        b2.G = bl2;
        return bl2;
    }

    static /* synthetic */ void c(b b2) {
        b2.X();
    }

    static /* synthetic */ AMap.OnMapLoadedListener d(b b2) {
        return b2.ac;
    }

    static /* synthetic */ Context e(b b2) {
        return b2.k;
    }

    static /* synthetic */ cm f(b b2) {
        return b2.A;
    }

    static /* synthetic */ View g(b b2) {
        return b2.N;
    }

    static /* synthetic */ bk h(b b2) {
        return b2.Q;
    }

    static /* synthetic */ AMap.OnMapScreenShotListener i(b b2) {
        return b2.af;
    }

    static /* synthetic */ AMap.OnCameraChangeListener j(b b2) {
        return b2.H;
    }

    static /* synthetic */ CameraPosition k(b b2) {
        return b2.W();
    }

    static /* synthetic */ AMap.CancelableCallback l(b b2) {
        return b2.J;
    }

    static /* synthetic */ boolean m(b b2) {
        return b2.G;
    }

    static /* synthetic */ LatLng n(b b2) {
        return b2.Y();
    }

    static /* synthetic */ s o(b b2) {
        return b2.I;
    }

    Point A() {
        return this.A.c();
    }

    public boolean B() {
        return this.n;
    }

    public ae C() {
        if (this.l == null || this.l.b == null) {
            return null;
        }
        return this.l.b.f();
    }

    public bb D() {
        return this.a;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean E() {
        aw aw2;
        if (this.l == null || this.l.d == null || (aw2 = this.a().d.b(this.a().d.f)) == null) {
            return false;
        }
        return aw2.f;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean F() {
        boolean bl2 = this.E();
        if (this.a() == null) {
            return false;
        }
        if (bl2) return false;
        Object object = this.a().d.g;
        if ((object = this.a().d.b((String)object)) == null) return false;
        return object.f;
    }

    public bm G() {
        return this.al;
    }

    public float H() {
        return this.as;
    }

    public void I() {
        this.au = 0.0f;
        this.av = 0.0f;
    }

    public int J() {
        return 0;
    }

    protected void K() {
        this.j.sendEmptyMessage(10);
    }

    void L() {
        this.j.sendEmptyMessage(15);
    }

    @Override
    public void M() {
        if (this.J != null) {
            this.J.onCancel();
        }
    }

    @Override
    public void N() {
        if (this.J != null) {
            this.J.onFinish();
        }
    }

    @Override
    public void O() {
        this.j.sendEmptyMessage(17);
    }

    @Override
    public void P() {
        this.postInvalidate();
        this.f.postInvalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public List<Marker> Q() {
        boolean bl2 = this.getWidth() > 0 && this.getHeight() > 0;
        cp.a(bl2, (Object)"\u5730\u56fe\u672a\u521d\u59cb\u5316\u5b8c\u6210\uff01");
        return this.i.g();
    }

    public int a(int n2) {
        int n3 = n2;
        if (n2 < this.l.b.b()) {
            n3 = this.l.b.b();
        }
        n2 = n3;
        if (n3 > this.l.b.a()) {
            n2 = this.l.b.a();
        }
        return n2;
    }

    @Override
    public ah a(CircleOptions circleOptions) {
        w w2 = new w(this);
        w2.b(circleOptions.getFillColor());
        w2.a(circleOptions.getCenter());
        w2.a(circleOptions.isVisible());
        w2.b(circleOptions.getStrokeWidth());
        w2.a(circleOptions.getZIndex());
        w2.a(circleOptions.getStrokeColor());
        w2.a(circleOptions.getRadius());
        if (this.l == null) {
            return null;
        }
        this.l.f.a(w2);
        this.invalidate();
        return w2;
    }

    @Override
    public ai a(GroundOverlayOptions groundOverlayOptions) {
        af af2 = new af(this);
        af2.b(groundOverlayOptions.getAnchorU(), groundOverlayOptions.getAnchorV());
        af2.c(groundOverlayOptions.getBearing());
        af2.a(groundOverlayOptions.getWidth(), groundOverlayOptions.getHeight());
        af2.a(groundOverlayOptions.getImage());
        af2.a(groundOverlayOptions.getLocation());
        af2.a(groundOverlayOptions.getBounds());
        af2.d(groundOverlayOptions.getTransparency());
        af2.a(groundOverlayOptions.isVisible());
        af2.a(groundOverlayOptions.getZIndex());
        if (this.l == null) {
            return null;
        }
        this.l.f.a(af2);
        this.invalidate();
        return af2;
    }

    @Override
    public ao a(PolygonOptions polygonOptions) {
        bp bp2 = new bp(this);
        bp2.a(polygonOptions.getFillColor());
        bp2.a(polygonOptions.getPoints());
        bp2.a(polygonOptions.isVisible());
        bp2.b(polygonOptions.getStrokeWidth());
        bp2.a(polygonOptions.getZIndex());
        bp2.b(polygonOptions.getStrokeColor());
        if (this.l == null) {
            return null;
        }
        this.l.f.a(bp2);
        this.invalidate();
        return bp2;
    }

    @Override
    public ap a(PolylineOptions polylineOptions) {
        bq bq2 = new bq(this);
        bq2.a(polylineOptions.getColor());
        bq2.b(polylineOptions.isDottedLine());
        bq2.c(polylineOptions.isGeodesic());
        bq2.a(polylineOptions.getPoints());
        bq2.a(polylineOptions.isVisible());
        bq2.b(polylineOptions.getWidth());
        bq2.a(polylineOptions.getZIndex());
        if (this.l == null) {
            return null;
        }
        this.a().f.a(bq2);
        this.invalidate();
        return bq2;
    }

    @Override
    public bl a() {
        return this.l;
    }

    @Override
    public Marker a(MarkerOptions object) {
        object = new bk((MarkerOptions)object, this.i);
        this.i.a((ak)object);
        this.invalidate();
        return new Marker((ak)object);
    }

    @Override
    public Text a(TextOptions object) {
        object = new by(this, (TextOptions)object, this.i);
        this.i.a((as)object);
        this.invalidate();
        return new Text((as)object);
    }

    @Override
    public TileOverlay a(TileOverlayOptions object) {
        object = new cb((TileOverlayOptions)object, this.h);
        this.h.a((at)object);
        this.invalidate();
        return new TileOverlay((at)object);
    }

    @Override
    public void a(double d2, double d3, ab ab2) {
        int n2 = (int)this.f();
        ae ae2 = new ae((int)z.a(d2), (int)z.a(d3));
        ae2 = this.K.b(ae2, this.K.i, this.K.k, this.K.h[n2]);
        if (ab2 != null) {
            ab2.a = ae2.x;
            ab2.b = ae2.y;
        }
    }

    public void a(double d2, double d3, an an2) {
        ae ae2 = this.K.b(new ae((int)(d2 * 1000000.0), (int)(1000000.0 * d3)));
        an2.a = ae2.a();
        an2.b = ae2.b();
    }

    @Override
    public void a(float f2) {
        if (this.z != null) {
            this.z.a(f2);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void a(float f2, Point object, boolean bl2) {
        if (this.a == null) {
            return;
        }
        float f3 = this.f();
        if (cy.b(f3 + f2) - f3 == 0.0f) return;
        new an();
        an an2 = this.Z();
        if (object == null) return;
        an an3 = new an();
        this.a(object.x, object.y, an3);
        int n2 = an2.a - an3.a;
        int n3 = an2.b - an3.b;
        n2 = (int)((double)n2 / Math.pow((double)2.0, (double)f2) - (double)n2);
        n3 = (int)((double)n3 / Math.pow((double)2.0, (double)f2) - (double)n3);
        an2.a = n2 + an3.a;
        an2.b = an3.b + n3;
        object = new ae(an2.b, an2.a, false);
        object = this.l.h.b((ae)object);
        if (bl2) {
            this.a.b((ae)object, 1000);
            return;
        }
        this.a.a((ae)object);
        t.a().b();
    }

    @Override
    public void a(int n2, int n3, aa aa2) {
        int n4 = (int)this.f();
        Object object = new PointF((float)n2, (float)n3);
        object = this.K.a((PointF)object, this.K.i, this.K.k, this.K.h[n4], this.K.l);
        if (aa2 != null) {
            double d2 = z.a(object.b());
            double d3 = z.a(object.a());
            aa2.b = d2;
            aa2.a = d3;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void a(Location location) {
        if (location == null) {
            return;
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        try {
            if (!this.n() || this.C == null) {
                this.z.a();
                this.z = null;
                return;
            }
            if (this.z == null || this.v == null) {
                if (this.z == null) {
                    this.z = new bn(this);
                }
                if (latLng != null) {
                    this.a(u.a(latLng, (float)this.l.b.e()));
                }
            }
        }
        catch (RemoteException var3_3) {
            cy.a((Throwable)var3_3, "AMapDelegateImpGLSurfaceView", "showMyLocationOverlay");
        }
        this.z.a(latLng, location.getAccuracy());
        if (this.x != null && (this.v == null || this.v.getBearing() != location.getBearing() || this.v.getAccuracy() != location.getAccuracy() || this.v.getLatitude() != location.getLatitude() || this.v.getLongitude() != location.getLongitude())) {
            this.x.onMyLocationChange(location);
        }
        this.v = new Location(location);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(ak ak2) {
        int n2;
        Object object;
        int n3 = -2;
        if (ak2 == null || ak2.f() == null && ak2.g() == null) {
            return;
        }
        this.t();
        Object object2 = new Marker(ak2);
        if (this.P != null) {
            this.N = this.P.getInfoWindow((Marker)object2);
        }
        try {
            if (this.S == null) {
                this.S = bo.a(this.k, "infowindow_bg2d.9.png");
            }
        }
        catch (Exception var5_4) {
            cy.a(var5_4, "AMapDelegateImpGLSurfaceView", "showInfoWindow");
        }
        if (this.N == null && this.P != null) {
            this.N = this.P.getInfoContents((Marker)object2);
        }
        if (this.N != null) {
            if (this.N.getBackground() == null) {
                this.N.setBackgroundDrawable(this.S);
            }
        } else {
            object2 = new LinearLayout(this.k);
            object2.setBackgroundDrawable(this.S);
            object = new TextView(this.k);
            object.setText((CharSequence)ak2.f());
            object.setTextColor(-16777216);
            TextView textView = new TextView(this.k);
            textView.setTextColor(-16777216);
            textView.setText((CharSequence)ak2.g());
            object2.setOrientation(1);
            object2.addView((View)object);
            object2.addView((View)textView);
            this.N = object2;
        }
        object2 = this.N.getLayoutParams();
        this.N.setDrawingCacheEnabled(true);
        this.N.setDrawingCacheQuality(0);
        object = ak2.e();
        if (object2 != null) {
            n2 = object2.width;
            n3 = object2.height;
        } else {
            n2 = -2;
        }
        object2 = new bg.a(n2, n3, ak2.c(), - (int)object.a + ak2.n() / 2, - (int)object.b + 2, 81);
        this.Q = (bk)ak2;
        this.f.addView(this.N, (ViewGroup.LayoutParams)object2);
    }

    @Override
    public void a(u u2) {
        this.E.a(u2);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void a(u object, long l2, AMap.CancelableCallback cancelableCallback) {
        if (object.a == u.a.j) {
            boolean bl2 = this.getWidth() > 0 && this.getHeight() > 0;
            cp.a(bl2, (Object)"the map must have a size");
        }
        if (this.a == null) {
            return;
        }
        if (!this.I.a()) {
            this.I.a(true);
            if (this.J != null) {
                this.J.onCancel();
            }
        }
        this.J = cancelableCallback;
        if (this.F) {
            this.G = true;
        }
        if (object.a == u.a.h) {
            this.K();
            if (this.l == null) return;
            if (!this.m) return;
            this.a.c((int)object.b, (int)object.c);
            this.postInvalidate();
            return;
        }
        if (object.a == u.a.b) {
            this.a.c();
            return;
        }
        if (object.a == u.a.e) {
            this.a.d();
            return;
        }
        if (object.a == u.a.f) {
            int n2 = (int)object.d;
            this.a.c(n2);
            return;
        }
        if (object.a == u.a.g) {
            this.a(object.e, object.h, true);
            return;
        }
        if (object.a == u.a.i) {
            object = object.f;
            this.a.c((int)object.zoom);
            int n3 = (int)(object.target.latitude * 1000000.0);
            int n4 = (int)(object.target.longitude * 1000000.0);
            this.a.b(new ae(n3, n4), (int)l2);
            return;
        }
        if (object.a == u.a.c) {
            object = object.f;
            int n5 = (int)(object.target.latitude * 1000000.0);
            int n6 = (int)(object.target.longitude * 1000000.0);
            this.a.b(new ae(n5, n6), (int)l2);
            return;
        }
        if (object.a != u.a.j && object.a != u.a.k) {
            object.i = true;
            this.d.a((u)object);
            return;
        }
        this.K();
        this.a((u)object, true, l2);
    }

    @Override
    public void a(u u2, AMap.CancelableCallback cancelableCallback) {
        this.a(u2, 250, cancelableCallback);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void a(u object, boolean bl2, long l2) {
        if (this.a == null) {
            return;
        }
        try {
            object = object.g;
            int n2 = (int)(object.northeast.latitude * 1000000.0 - object.southwest.latitude * 1000000.0);
            int n3 = (int)(object.northeast.longitude * 1000000.0 - object.southwest.longitude * 1000000.0);
            object = new ae((int)((object.northeast.latitude * 1000000.0 + object.southwest.latitude * 1000000.0) / 2.0), (int)((object.northeast.longitude * 1000000.0 + object.southwest.longitude * 1000000.0) / 2.0));
            if (bl2) {
                this.a.b((ae)object, (int)l2);
            } else {
                this.a.a((ae)object);
            }
            this.a.a(n2, n3);
            t.a().b();
            return;
        }
        catch (Exception var1_2) {
            cy.a(var1_2, "AMapDelegateImpGLSurfaceView", "newLatLngBoundsWithSize");
            return;
        }
    }

    @Override
    public void a(AMap.InfoWindowAdapter infoWindowAdapter) {
        this.P = infoWindowAdapter;
    }

    @Override
    public void a(AMap.OnCameraChangeListener onCameraChangeListener) {
        this.H = onCameraChangeListener;
    }

    @Override
    public void a(AMap.OnInfoWindowClickListener onInfoWindowClickListener) {
        this.O = onInfoWindowClickListener;
    }

    @Override
    public void a(AMap.OnMapClickListener onMapClickListener) {
        this.ad = onMapClickListener;
    }

    @Override
    public void a(AMap.OnMapLoadedListener onMapLoadedListener) {
        this.ac = onMapLoadedListener;
    }

    @Override
    public void a(AMap.OnMapLongClickListener onMapLongClickListener) {
        this.ab = onMapLongClickListener;
    }

    @Override
    public void a(AMap.OnMapScreenShotListener onMapScreenShotListener) {
        this.af = onMapScreenShotListener;
        this.U = true;
    }

    @Override
    public void a(AMap.OnMapTouchListener onMapTouchListener) {
        this.aa = onMapTouchListener;
    }

    @Override
    public void a(AMap.OnMarkerClickListener onMarkerClickListener) {
        this.R = onMarkerClickListener;
    }

    @Override
    public void a(AMap.OnMarkerDragListener onMarkerDragListener) {
        this.Z = onMarkerDragListener;
    }

    @Override
    public void a(AMap.OnMyLocationChangeListener onMyLocationChangeListener) {
        this.x = onMyLocationChangeListener;
    }

    @Override
    public void a(LocationSource locationSource) {
        this.C = locationSource;
        if (locationSource != null) {
            this.u.a(true);
            return;
        }
        this.u.a(false);
    }

    @Override
    public void a(MyLocationStyle myLocationStyle) {
        if (this.o() != null) {
            this.o().a(myLocationStyle);
        }
    }

    @Override
    public void a(boolean bl2) {
    }

    @Override
    public boolean a(float f2, float f3) {
        this.a.a(true);
        if (this.at) {
            this.au += f2;
            this.av += f3;
        }
        this.invalidate();
        return this.at;
    }

    @Override
    public boolean a(float f2, PointF pointF) {
        this.l.d.c = false;
        this.K();
        this.a(f2, pointF, this.au, this.av);
        this.at = false;
        this.postInvalidateDelayed(8);
        this.l.a(true);
        t.a().b();
        return true;
    }

    @Override
    public boolean a(Matrix matrix) {
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public boolean a(PointF pointF) {
        boolean bl2;
        try {
            bl2 = this.g.f();
            if (!bl2) {
                return false;
            }
        }
        catch (RemoteException var1_2) {
            cy.a((Throwable)var1_2, "AMapDelegateImpGLSurfaceView", "startScale");
        }
        try {
            bl2 = this.q().f();
            if (!bl2) return false;
        }
        catch (RemoteException var1_3) {
            cy.a((Throwable)var1_3, "AMapDelegateImpGLSurfaceView", "startScale");
        }
        this.l.a(this.n);
        this.l.d.a(true);
        this.l.d.c = true;
        this.at = true;
        return true;
    }

    @Override
    public boolean a(String string2) {
        if (this.l == null) {
            return false;
        }
        return this.l.f.b(string2);
    }

    protected PointF b(PointF pointF) {
        PointF pointF2 = new PointF();
        int n2 = this.getWidth();
        int n3 = this.getHeight();
        float f2 = pointF.x - (float)(n2 >> 1);
        float f3 = pointF.y - (float)(n3 >> 1);
        double d2 = Math.atan2((double)f3, (double)f2);
        double d3 = Math.pow((double)f2, (double)2.0);
        d3 = Math.sqrt((double)(Math.pow((double)f3, (double)2.0) + d3));
        pointF2.x = (float)(Math.cos((double)(d2 -= (double)this.J() * 3.141592653589793 / 180.0)) * d3 + (double)(n2 >> 1));
        d2 = Math.sin((double)d2);
        pointF2.y = (float)((double)(n3 >> 1) + d3 * d2);
        return pointF2;
    }

    @Override
    public bh b() {
        return this.K;
    }

    @Override
    public bk b(MarkerOptions object) {
        object = new bk((MarkerOptions)object, this.i);
        this.i.a((ak)object);
        this.invalidate();
        return object;
    }

    @Override
    public void b(double d2, double d3, an an2) {
        int n2 = (int)this.f();
        ae ae2 = new ae((int)z.a(d2), (int)z.a(d3));
        ae2 = this.K.b(ae2, this.K.i, this.K.k, this.K.h[n2]);
        if (an2 != null) {
            an2.a = (int)ae2.x;
            an2.b = (int)ae2.y;
        }
    }

    public void b(float f2) {
        this.as = f2;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void b(int n2) {
        if (n2 == 2) {
            this.s = 2;
            this.h(true);
            this.A.a(true);
        } else {
            this.s = 1;
            this.h(false);
            this.A.a(false);
        }
        this.postInvalidate();
    }

    public void b(int n2, int n3, aa aa2) {
        if (aa2 != null) {
            aa2.a = z.a(n2);
            aa2.b = z.a(n3);
        }
    }

    @Override
    public void b(u u2) {
        this.a(u2, null);
    }

    @Override
    public void b(boolean bl2) {
        this.i(bl2);
        this.postInvalidate();
    }

    @Override
    public boolean b(Matrix matrix) {
        try {
            boolean bl2 = this.g.f();
            if (!bl2) {
                return false;
            }
        }
        catch (RemoteException var3_3) {
            cy.a((Throwable)var3_3, "AMapDelegateImpGLSurfaceView", "onScale");
        }
        this.ar.set(matrix);
        this.postInvalidate();
        return true;
    }

    public boolean b(ak ak2) {
        if (this.Q != null && this.N != null) {
            return this.Q.d().equals((Object)ak2.d());
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean b(String object) {
        try {
            object = this.i.a((String)object);
            if (object == null) return false;
        }
        catch (RemoteException var1_2) {
            cy.a((Throwable)var1_2, "AMapDelegateImpGLSurfaceView", "removeMarker");
            return false;
        }
        return this.i.b((ak)object);
    }

    @Override
    public int c() {
        if (this.l == null || this.l.b == null) {
            return 0;
        }
        return this.l.b.c();
    }

    protected PointF c(PointF pointF) {
        PointF pointF2 = new PointF();
        int n2 = this.getWidth();
        int n3 = this.getHeight();
        float f2 = pointF.x - (float)(n2 >> 1);
        float f3 = pointF.y - (float)(n3 >> 1);
        double d2 = Math.atan2((double)f3, (double)f2);
        double d3 = Math.pow((double)f2, (double)2.0);
        d3 = Math.sqrt((double)(Math.pow((double)f3, (double)2.0) + d3));
        pointF2.x = (float)(Math.cos((double)(d2 += (double)this.J() * 3.141592653589793 / 180.0)) * d3 + (double)(n2 >> 1));
        d2 = Math.sin((double)d2);
        pointF2.y = (float)((double)(n3 >> 1) + d3 * d2);
        return pointF2;
    }

    @Override
    public void c(int n2) {
        if (this.A != null) {
            this.A.a(n2);
            this.A.invalidate();
            if (this.B.getVisibility() == 0) {
                this.B.invalidate();
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void c(String string2) {
        if (this.l == null || this.l.d == null || this.E()) {
            return;
        }
        this.l.d.a(string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void c(boolean bl2) {
        if (this.C != null) {
            if (bl2) {
                this.C.activate(this.w);
                this.u.a(true);
                if (this.z == null) {
                    this.z = new bn(this);
                }
            } else {
                if (this.z != null) {
                    this.z.a();
                    this.z = null;
                }
                this.v = null;
                this.C.deactivate();
                this.u.a(false);
            }
        } else {
            this.u.a(false);
        }
        if (bl2) {
            this.u.setVisibility(0);
        } else {
            this.u.setVisibility(8);
        }
        this.y = bl2;
    }

    @Override
    public boolean c(float f2) {
        try {
            boolean bl2 = this.g.f();
            if (!bl2) {
                return false;
            }
        }
        catch (RemoteException var3_3) {
            cy.a((Throwable)var3_3, "AMapDelegateImpGLSurfaceView", "onScale");
        }
        this.b(f2);
        return false;
    }

    public void computeScroll() {
        if (this.ao.computeScrollOffset()) {
            int n2 = this.ao.getCurrX();
            int n3 = this.ap;
            int n4 = this.ao.getCurrY();
            int n5 = this.aq;
            this.ap = this.ao.getCurrX();
            this.aq = this.ao.getCurrY();
            ae ae2 = this.l.a.a(n2 - n3 + this.l.h.k.x, n4 - n5 + this.l.h.k.y);
            if (this.ao.isFinished()) {
                t.a().b();
                if (this.H != null) {
                    this.a(true, this.W());
                }
                this.l.b.a(false, false);
                return;
            }
            this.l.b.b(ae2);
            return;
        }
        super.computeScroll();
    }

    @Override
    public int d() {
        if (this.l == null || this.l.b == null) {
            return 0;
        }
        return this.l.b.d();
    }

    @Override
    public void d(int n2) {
        if (this.e != null) {
            this.e.a(n2);
            this.e.invalidate();
        }
    }

    @Override
    public void d(boolean bl2) {
        if (bl2) {
            this.e.setVisibility(0);
            return;
        }
        this.e.setVisibility(8);
    }

    @Override
    public View e() {
        return this.f;
    }

    void e(int n2) {
        this.t = n2;
    }

    @Override
    public void e(boolean bl2) {
        if (bl2) {
            this.u.setVisibility(0);
            return;
        }
        this.u.setVisibility(8);
    }

    @Override
    public float f() {
        int n2;
        if (this.l == null || this.l.b == null) {
            return 0.0f;
        }
        try {
            n2 = this.l.b.e();
        }
        catch (Exception var2_2) {
            cy.a(var2_2, "AMapDelegateImpGLSurfaceView", "getZoomLevel");
            return 0.0f;
        }
        return n2;
    }

    @Override
    public void f(boolean bl2) {
        if (bl2) {
            this.D.setVisibility(0);
            return;
        }
        this.D.setVisibility(8);
    }

    @Override
    public CameraPosition g() {
        LatLng latLng = this.Y();
        if (latLng == null) {
            return null;
        }
        float f2 = this.f();
        return CameraPosition.builder().target(latLng).zoom(f2).build();
    }

    @Override
    public void g(boolean bl2) {
        if (bl2) {
            this.B.setVisibility(0);
            this.L();
            return;
        }
        this.B.a("");
        this.B.a(0);
        this.B.setVisibility(8);
    }

    @Override
    public float h() {
        if (this.l == null || this.l.b == null) {
            return y.c;
        }
        return this.l.b.a();
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void h(boolean bl2) {
        if (this.E() == bl2) {
            return;
        }
        boolean bl3 = this.F();
        this.i(false);
        if (!bl2) {
            this.a().d.a(this.a().d.f, false);
            this.a().d.a(this.a().d.e, true);
            if (bl3) {
                this.i(true);
            }
            this.a().b.a(false, false);
            return;
        }
        if (this.l == null) return;
        if (this.a().d.b(this.a().d.f) != null) {
            this.a().d.a(this.a().d.f, true);
            if (bl3) {
                this.i(true);
            }
            this.a().b.a(false, false);
            return;
        }
        aw aw2 = new aw();
        aw2.j = new b$1(this);
        aw2.a = this.a().d.f;
        aw2.e = true;
        aw2.d = true;
        aw2.f = true;
        aw2.g = true;
        aw2.b = y.c;
        aw2.c = y.d;
        this.a().d.a(aw2, this.getContext());
        this.a().d.a(this.a().d.f, true);
        if (bl3) {
            this.i(true);
        }
        this.a().b.a(false, false);
    }

    @Override
    public float i() {
        if (this.l == null || this.l.b == null) {
            return y.d;
        }
        return this.l.b.b();
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void i(boolean bl2) {
        if (bl2 == this.F()) {
            return;
        }
        boolean bl3 = this.E();
        if (this.l == null) return;
        if (bl3) return;
        String string2 = this.a().d.g;
        if (!bl2) {
            this.a().d.a(string2, false);
            this.a().b.a(false, false);
            return;
        }
        if (this.a().d.b(string2) != null) {
            this.a().d.a(string2, true);
            this.a().b.a(false, false);
            return;
        }
        if (bl3) {
            aw aw2 = new aw();
            aw2.h = true;
            aw2.i = 120000;
            aw2.a = string2;
            aw2.e = false;
            aw2.d = true;
            aw2.f = true;
            aw2.g = false;
            aw2.b = 18;
            aw2.c = 9;
            this.a().d.a(aw2, this.getContext());
        } else {
            aw aw3 = new aw();
            aw3.h = true;
            aw3.i = 120000;
            aw3.j = new b$2(this);
            aw3.a = string2;
            aw3.e = false;
            aw3.d = true;
            aw3.f = true;
            aw3.g = false;
            aw3.b = 18;
            aw3.c = 9;
            this.a().d.a(aw3, this.getContext());
        }
        this.a().d.a(string2, true);
        this.a().b.a(false, false);
    }

    @Override
    public void j() {
        if (this.a == null) {
            return;
        }
        if (!this.I.a()) {
            this.I.a(true);
            t.a().b();
            if (this.J != null) {
                this.J.onCancel();
            }
            this.J = null;
        }
        this.a.a(true);
    }

    @Override
    public void k() {
        try {
            this.t();
            if (this.l == null) {
                return;
            }
            this.l.f.a();
            this.i.c();
            if (this.z != null) {
                this.z.a();
            }
            this.invalidate();
            return;
        }
        catch (Exception var1_1) {
            cy.a(var1_1, "AMapDelegateImpGLSurfaceView", "clear");
            Log.d((String)"amapApi", (String)("AMapDelegateImpGLSurfaceView clear erro" + var1_1.getMessage()));
            return;
        }
        catch (Throwable var1_2) {
            cy.a(var1_2, "AMapDelegateImpGLSurfaceView", "clear");
            return;
        }
    }

    @Override
    public int l() {
        return this.s;
    }

    @Override
    public boolean m() {
        return this.F();
    }

    @Override
    public boolean n() {
        return this.y;
    }

    public bn o() {
        return this.z;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean onDoubleTap(MotionEvent motionEvent) {
        block4 : {
            try {
                boolean bl2 = this.g.f();
                if (bl2) break block4;
                return true;
            }
            catch (RemoteException var3_3) {
                cy.a((Throwable)var3_3, "AMapDelegateImpGLSurfaceView", "onDoubleTap");
            }
        }
        if (this.r) {
            this.a.b((int)motionEvent.getX(), (int)motionEvent.getY());
        }
        if (this.aB > 1) {
            return true;
        }
        this.aC = true;
        this.e.a((float)(this.l.b.e() + 1));
        return true;
    }

    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.W = false;
        if (!this.aC && !this.I.a()) {
            this.I.a(true);
            if (this.J != null) {
                this.J.onCancel();
            }
            this.J = null;
        }
        this.aC = false;
        this.aB = 0;
        if (this.aj == null) {
            this.aj = new Point((int)motionEvent.getX(), (int)motionEvent.getY());
            return true;
        }
        this.aj.set((int)motionEvent.getX(), (int)motionEvent.getY());
        return true;
    }

    protected void onDraw(Canvas canvas) {
        if (this.U) {
            this.setDrawingCacheEnabled(true);
            this.buildDrawingCache();
            Bitmap bitmap = this.getDrawingCache();
            Message message = new Message();
            message.what = 16;
            message.obj = bitmap;
            this.j.sendMessage(message);
            this.U = false;
        }
        this.l.b.a(this.getWidth(), this.getHeight());
        this.l.d.a(canvas, this.ar, this.au, this.av);
        if (!this.I.a()) {
            this.j.sendEmptyMessage(13);
        }
        if (!this.ae) {
            this.j.sendEmptyMessage(11);
            this.ae = true;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        if (this.al.k) return true;
        if (motionEvent.getEventTime() - this.al.o < 30) {
            return true;
        }
        this.invalidate();
        this.W = false;
        try {
            boolean bl2 = this.g.e();
            if (!bl2) return true;
        }
        catch (RemoteException var1_2) {
            cy.a((Throwable)var1_2, "AMapDelegateImpGLSurfaceView", "onFling");
        }
        this.J = null;
        this.ao.fling(this.ap, this.aq, (int)(- f2) * 3 / 5, (int)(- f3) * 3 / 5, - this.aw, this.aw, - this.ax, this.ax);
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onKeyDown(int n2, KeyEvent keyEvent) {
        boolean bl2 = false;
        if (this.l == null) {
            return true;
        }
        boolean bl3 = bl2;
        if (!this.m) return bl3;
        if (this.l.d.a(n2, keyEvent)) return true;
        bl3 = bl2;
        if (!this.a.onKey(this, n2, keyEvent)) return bl3;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onKeyUp(int n2, KeyEvent keyEvent) {
        boolean bl2 = false;
        if (this.l == null) {
            return true;
        }
        boolean bl3 = bl2;
        if (!this.m) return bl3;
        if (this.l.d.b(n2, keyEvent)) return true;
        bl3 = bl2;
        if (!this.a.onKey(this, n2, keyEvent)) return bl3;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void onLongPress(MotionEvent object) {
        this.W = false;
        if (this.ab != null) {
            aa aa2 = new aa();
            this.a((int)object.getX(), (int)object.getY(), aa2);
            this.ab.onMapLongClick(new LatLng(aa2.b, aa2.a));
            this.L = true;
        }
        this.p = this.i.a((MotionEvent)object);
        if (this.p == null) {
            return;
        }
        this.o = new Marker(this.p);
        if (this.Z == null) return;
        if (this.p == null) return;
        if (!this.p.h()) return;
        object = this.a(this.p.c());
        this.p.a((LatLng)object);
        this.i.c(this.p);
        this.Z.onMarkerDragStart(this.o);
        this.V = true;
    }

    protected final void onMeasure(int n2, int n3) {
        super.onMeasure(n2, n3);
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
    }

    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        if (this.al.k || motionEvent2.getEventTime() - this.al.o < 30) {
            return true;
        }
        try {
            if (!this.g.e()) {
                this.W = false;
                return true;
            }
        }
        catch (RemoteException var1_2) {
            cy.a((Throwable)var1_2, "AMapDelegateImpGLSurfaceView", "onScroll");
        }
        if (this.aB > 1) {
            this.W = false;
            return true;
        }
        this.W = true;
        this.a((int)motionEvent2.getX(), (int)motionEvent2.getY());
        this.postInvalidate();
        this.K();
        return true;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean onSingleTapUp(MotionEvent object) {
        if (this.a == null) {
            return false;
        }
        this.l.d.b((MotionEvent)object);
        Object object2 = this.am.iterator();
        while (object2.hasNext()) {
            ((GestureDetector.OnGestureListener)object2.next()).onSingleTapUp((MotionEvent)object);
        }
        this.W = false;
        if (this.L) {
            this.L = false;
            return true;
        }
        try {
            if (this.N != null && this.i.a((Rect)(object2 = new Rect(this.N.getLeft(), this.N.getTop(), this.N.getRight(), this.N.getBottom())), (int)object.getX(), (int)object.getY()) && this.O != null) {
                object = this.i.e();
                if (!object.s()) {
                    return true;
                }
                object = new Marker((ak)object);
                this.O.onInfoWindowClick((Marker)object);
                return true;
            }
            if (this.i.b((MotionEvent)object)) {
                object = this.i.e();
                if (object == null) return true;
                if (!object.s()) {
                    return true;
                }
                object2 = new Marker((ak)object);
                if (this.R != null) {
                    if (this.R.onMarkerClick((Marker)object2) || this.i.b() <= 0) {
                        this.i.c((ak)object);
                        return true;
                    }
                    try {
                        if (this.i.e() != null && !object.q() && (object2 = object.c()) != null) {
                            this.a.a(cy.a((LatLng)object2));
                            t.a().b();
                        }
                    }
                    catch (RemoteException var2_4) {
                        cy.a((Throwable)var2_4, "AMapDelegateImpGLSurfaceView", "onSingleTapUp");
                    }
                }
                this.a((ak)object);
                this.i.c((ak)object);
                return true;
            }
            if (this.ad == null) return true;
            object2 = new aa();
            this.a((int)object.getX(), (int)object.getY(), (aa)object2);
            this.ad.onMapClick(new LatLng(object2.b, object2.a));
            return true;
        }
        catch (Exception var1_2) {
            cy.a(var1_2, "AMapDelegateImpGLSurfaceView", "onSingleTapUp");
            return true;
        }
    }

    protected void onSizeChanged(int n2, int n3, int n4, int n5) {
        super.onSizeChanged(n2, n3, n4, n5);
        Point point = new Point(n2 / 2, n3 / 2);
        this.l.h.a(point);
        this.l.b.a(n2, n3);
        if (this.a.a() != 0 && this.a.b() != 0) {
            this.a.a(this.a.a(), this.a.b());
            this.a.a(0);
            this.a.b(0);
        }
        this.u();
        if (this.aD != null) {
            this.aD.a(n2, n3, n4, n5);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!y.o) {
            return true;
        }
        if (this.l == null) return true;
        if (!this.m) {
            return false;
        }
        if (this.aa != null) {
            this.ai.removeMessages(1);
            Message message = this.ai.obtainMessage();
            message.what = 1;
            message.obj = MotionEvent.obtain((MotionEvent)motionEvent);
            message.sendToTarget();
        }
        if (this.l.d.a(motionEvent)) return true;
        this.b(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    public void onWindowFocusChanged(boolean bl2) {
        super.onWindowFocusChanged(bl2);
    }

    @Override
    public Location p() {
        if (this.C != null) {
            return this.w.a;
        }
        return null;
    }

    @Override
    public au q() {
        return this.g;
    }

    @Override
    public aq r() {
        return this.T;
    }

    @Override
    public br s() {
        return this.l.a;
    }

    public void setClickable(boolean bl2) {
        this.m = bl2;
        super.setClickable(bl2);
    }

    public void t() {
        if (this.N != null) {
            this.N.clearFocus();
            this.N.destroyDrawingCache();
            this.f.removeView(this.N);
            Drawable drawable2 = this.N.getBackground();
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            this.N = null;
        }
        this.Q = null;
    }

    public void u() {
        if (this.N != null && this.Q != null) {
            bg.a a2 = (bg.a)this.N.getLayoutParams();
            if (a2 != null) {
                a2.b = this.Q.c();
            }
            this.f.a();
        }
    }

    @Override
    public void v() {
        try {
            if (this.ag != null) {
                this.ag.cancel();
                this.ag = null;
            }
            if (this.ah != null) {
                this.ah.cancel();
                this.ah = null;
            }
            if (this.ai != null) {
                this.ai.removeCallbacksAndMessages((Object)null);
            }
            if (this.j != null) {
                this.j.removeCallbacksAndMessages((Object)null);
            }
            v.a().b(this);
            bv.a().b(this);
            t.a().b(this);
            this.e.a();
            this.B.a();
            this.A.a();
            this.u.a();
            this.D.a();
            this.l.f.b();
            this.i.f();
            if (this.S != null) {
                this.S.setCallback(null);
            }
            this.f.removeAllViews();
            this.t();
            if (this.l != null) {
                this.l.c.b();
                this.T();
            }
            y.h = null;
            y.g = null;
            ed.c();
            System.gc();
            return;
        }
        catch (Exception var1_1) {
            cy.a(var1_1, "AMapDelegateImpGLSurfaceView", "destroy");
            return;
        }
    }

    @Override
    public float w() {
        int n2 = this.getWidth();
        aa aa2 = new aa();
        aa aa3 = new aa();
        this.a(0, 0, aa2);
        this.a(n2, 0, aa3);
        return (float)(cy.a(new LatLng(aa2.b, aa2.a), new LatLng(aa3.b, aa3.a)) / (double)n2);
    }

    public LatLngBounds x() {
        return null;
    }

    @Override
    public void y() {
        if (this.l != null) {
            this.l.c.c();
        }
    }

    @Override
    public void z() {
        if (this.l != null) {
            this.l.c.d();
        }
    }

    static abstract class a {
        private a() {
        }

        public abstract void a(int var1, int var2, int var3, int var4);
    }

}


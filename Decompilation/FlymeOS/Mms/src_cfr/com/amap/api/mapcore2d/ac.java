/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Canvas
 *  android.os.Handler
 *  android.os.RemoteException
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.Comparator
 *  java.util.concurrent.CopyOnWriteArrayList
 */
package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.mapcore2d.ad;
import com.amap.api.mapcore2d.am;
import com.amap.api.mapcore2d.cy;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

class ac {
    private static int a = 0;
    private CopyOnWriteArrayList<am> b = new CopyOnWriteArrayList();
    private a c;
    private Handler d;
    private Runnable e;

    ac() {
        this.c = new a(null);
        this.d = new Handler();
        this.e = new ad(this);
    }

    static String a(String string2) {
        return string2 + ++a;
    }

    static /* synthetic */ CopyOnWriteArrayList a(ac ac2) {
        return ac2.b;
    }

    static /* synthetic */ a b(ac ac2) {
        return ac2.c;
    }

    private am c(String string2) {
        for (am am2 : this.b) {
            if (am2 == null || !am2.c().equals((Object)string2)) continue;
            return am2;
        }
        return null;
    }

    private void c() {
        this.d.removeCallbacks(this.e);
        this.d.postDelayed(this.e, 10);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a() {
        Iterator iterator = this.b.iterator();
        while (iterator.hasNext()) {
            ((am)iterator.next()).l();
        }
        try {
            iterator = this.b.iterator();
            while (iterator.hasNext()) {
                ((am)iterator.next()).l();
            }
            this.b.clear();
            return;
        }
        catch (Exception var1_2) {
            cy.a(var1_2, "GLOverlayLayer", "clear");
            Log.d((String)"amapApi", (String)("GLOverlayLayer clear erro" + var1_2.getMessage()));
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(Canvas canvas) {
        this.c();
        int n2 = this.b.size();
        Iterator iterator = this.b.iterator();
        while (iterator.hasNext()) {
            am am2 = (am)iterator.next();
            try {
                if (!am2.e()) continue;
                if (n2 > 20) {
                    if (!am2.a()) continue;
                    am2.a(canvas);
                }
                am2.a(canvas);
                continue;
            }
            catch (RemoteException var4_5) {
                cy.a((Throwable)var4_5, "GLOverlayLayer", "draw");
                continue;
            }
            break;
        }
        return;
    }

    public void a(am am2) {
        this.b(am2.c());
        this.b.add((Object)am2);
        this.c();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void b() {
        try {
            Iterator iterator = this.b.iterator();
            while (iterator.hasNext()) {
                ((am)iterator.next()).l();
            }
            this.a();
            return;
        }
        catch (Exception var1_2) {
            cy.a(var1_2, "GLOverlayLayer", "destory");
            Log.d((String)"amapApi", (String)("GLOverlayLayer destory erro" + var1_2.getMessage()));
            return;
        }
    }

    public boolean b(String object) {
        if ((object = this.c((String)object)) != null) {
            return this.b.remove(object);
        }
        return false;
    }

    class a
    implements Comparator<Object> {
        private a() {
        }

        /* synthetic */ a(ad ad2) {
            this();
        }

        public int compare(Object object, Object object2) {
            object = (am)object;
            object2 = (am)object2;
            if (object != null && object2 != null) {
                block5 : {
                    if (object.d() <= object2.d()) break block5;
                    return 1;
                }
                try {
                    float f2 = object.d();
                    float f3 = object2.d();
                    if (f2 < f3) {
                        return -1;
                    }
                }
                catch (Exception var1_2) {
                    cy.a(var1_2, "GLOverlayLayer", "compare");
                }
            }
            return 0;
        }
    }

}


/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import com.amap.api.mapcore2d.cb;
import com.amap.api.mapcore2d.cq;
import com.amap.api.mapcore2d.cs;
import com.amap.api.mapcore2d.cw;
import com.amap.api.mapcore2d.cy;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

public abstract class cv {
    protected Resources a;
    private cs b;
    private cs.a c;
    private boolean d = false;
    private boolean e = false;
    private final Object f = new Object();

    protected cv(Context context) {
        this.a = context.getResources();
    }

    public static void a(cb.a a2) {
        a a3 = cv.c(a2);
        if (a3 != null) {
            a3.a(true);
            cw.a("ImageWorker", "cancelWork - cancelled work for " + a2, 111);
        }
    }

    private static a c(cb.a a2) {
        if (a2 != null) {
            return a2.d;
        }
        return null;
    }

    protected abstract Bitmap a(Object var1);

    protected cs a() {
        return this.b;
    }

    public void a(cs.a a2) {
        this.c = a2;
        this.b = cs.a(this.c);
        new b().c((Params[])new Object[]{1});
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl2, cb.a a2) {
        Object object;
        if (a2 == null) {
            return;
        }
        if (this.b != null) {
            object = new StringBuilder();
            object.append(a2.a);
            object.append("-");
            object.append(a2.b);
            object.append("-");
            object.append(a2.c);
            object = this.b.a(object.toString());
        } else {
            object = null;
        }
        if (object != null) {
            a2.a((Bitmap)object);
            return;
        }
        a2.d = object = new a(a2);
        object.a(cq.b, (Params[])new Boolean[]{bl2});
    }

    protected void b() {
        if (this.b != null) {
            this.b.a();
        }
    }

    protected void c() {
        if (this.b != null) {
            this.b.b();
        }
    }

    protected void d() {
        if (this.b != null) {
            this.b.c();
        }
    }

    protected void e() {
        if (this.b != null) {
            this.b.d();
            this.b = null;
        }
    }

    public void f() {
        new b().c((Params[])new Object[]{0});
    }

    public void g() {
        new b().c((Params[])new Object[]{3});
    }

    public class a
    extends cq<Boolean, Void, Bitmap> {
        private final WeakReference<cb.a> c;

        private a(cb.a a2) {
            this.c = new WeakReference<cb.a>(a2);
        }

        private cb.a e() {
            cb.a a2 = this.c.get();
            if (this == cv.c(a2)) {
                return a2;
            }
            return null;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        protected /* varargs */ Bitmap a(Boolean ... object) {
            Object var4_2 = null;
            cw.a("ImageWorker", "doInBackground - starting work", 111);
            boolean bl2 = object[0];
            cb.a a2 = this.c.get();
            if (a2 == null) {
                return null;
            }
            object = new StringBuilder();
            object.append(a2.a);
            object.append("-");
            object.append(a2.b);
            object.append("-");
            object.append(a2.c);
            String string2 = object.toString();
            object = cv.this.f;
            synchronized (object) {
                boolean bl3;
                while (cv.this.e && !(bl3 = this.c())) {
                    try {
                        cv.this.f.wait();
                    }
                    catch (InterruptedException var7_7) {
                        cy.a(var7_7, "ImageWorker", "doInBackground");
                    }
                }
            }
            object = var4_2;
            if (cv.this.b != null) {
                object = var4_2;
                if (!this.c()) {
                    object = var4_2;
                    if (this.e() != null) {
                        object = var4_2;
                        if (!cv.this.d) {
                            object = cv.this.b.b(string2);
                        }
                    }
                }
            }
            if (bl2 && object == null && !this.c() && this.e() != null && !cv.this.d) {
                object = cv.this.a(a2);
            }
            if (object != null && cv.this.b != null) {
                cv.this.b.a(string2, (Bitmap)object);
            }
            cw.a("ImageWorker", "doInBackground - finished work", 111);
            return object;
        }

        @Override
        protected void a(Bitmap bitmap) {
            if (this.c() || cv.this.d) {
                bitmap = null;
            }
            cb.a a2 = this.e();
            if (bitmap != null && !bitmap.isRecycled() && a2 != null) {
                cw.a("ImageWorker", "onPostExecute - setting bitmap: " + a2.toString(), 111);
                a2.a(bitmap);
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        protected void b(Bitmap object) {
            super.b(object);
            object = cv.this.f;
            synchronized (object) {
                cv.this.f.notifyAll();
                return;
            }
        }
    }

    class b
    extends cq<Object, Void, Void> {
        private b() {
        }

        @Override
        protected /* synthetic */ Object a(Object[] arrobject) {
            return this.d(arrobject);
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        protected /* varargs */ Void d(Object ... var1_1) {
            switch ((Integer)var1_1[0]) {
                case 0: {
                    cv.this.c();
                    ** break;
                }
                case 1: {
                    cv.this.b();
                    ** break;
                }
                case 2: {
                    cv.this.d();
                }
lbl10: // 4 sources:
                default: {
                    return null;
                }
                case 3: 
            }
            cv.this.e();
            return null;
        }
    }

}


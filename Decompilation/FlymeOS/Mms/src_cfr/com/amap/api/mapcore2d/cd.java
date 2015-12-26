/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.PointF
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.PointF;
import com.amap.api.mapcore2d.ae;
import com.amap.api.mapcore2d.av;
import com.amap.api.mapcore2d.aw;
import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.bh;
import com.amap.api.mapcore2d.bl;
import com.amap.api.mapcore2d.bw;
import com.amap.api.mapcore2d.bx;
import com.amap.api.mapcore2d.ca;
import com.amap.api.mapcore2d.ce;
import com.amap.api.mapcore2d.ck;
import com.amap.api.mapcore2d.cl;
import com.amap.api.mapcore2d.j;
import com.amap.api.mapcore2d.n;
import com.amap.api.mapcore2d.q;
import com.amap.api.mapcore2d.r;
import java.util.ArrayList;
import java.util.List;

class cd
extends j<n.a, n.a>
implements cl {
    private Context d;
    private av e = new av();

    public cd(bl bl2, Context context) {
        super(bl2, context);
        this.d = context;
        this.a = new ca();
        bl2.b.a(this);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private ArrayList<n.a> a(ArrayList<n.a> arrayList, aw aw2, int n2, boolean bl2) {
        if (arrayList == null) return null;
        if (aw2 == null) {
            return null;
        }
        if (!aw2.f) return null;
        if (aw2.o == null) return null;
        aw2.o.clear();
        if (n2 > aw2.b) return null;
        if (n2 < aw2.c) return null;
        int n3 = arrayList.size();
        if (n3 <= 0) return null;
        ArrayList arrayList2 = new ArrayList();
        n2 = 0;
        while (n2 < n3) {
            n.a a2 = (n.a)arrayList.get(n2);
            if (a2 != null) {
                Object object = new StringBuilder();
                object.append(a2.b);
                object.append("-");
                object.append(a2.c);
                object.append("-");
                object.append(a2.d);
                int n4 = aw2.m.a(object.toString());
                object = new n.a(a2.b, a2.c, a2.d, aw2.k);
                object.g = n4;
                object.f = a2.f;
                aw2.o.add((n.a)object);
                if (this.a((n.a)object) && !bl2 && !this.e.contains(object)) {
                    if (!aw2.g) {
                        object.a = -1;
                    }
                    arrayList2.add(object);
                }
            }
            ++n2;
        }
        return arrayList2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(ArrayList<n.a> arrayList, boolean bl2) {
        if (this.a == null || arrayList == null || arrayList.size() == 0) {
            return;
        }
        this.a.a(arrayList, bl2);
    }

    private boolean a(n.a a2) {
        if (a2 == null || a2.g < 0) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void c(ArrayList<n.a> arrayList) {
        int n2;
        if (arrayList == null || this.e == null || (n2 = arrayList.size()) == 0) {
            return;
        }
        int n3 = 0;
        while (n3 < n2) {
            this.e.a((n.a)arrayList.get(n3));
            ++n3;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean i() {
        if (this.b == null || this.b.d == null) {
            return false;
        }
        if (this.b.d.a == null) {
            return false;
        }
        int n2 = this.b.d.a.size();
        if (n2 <= 0) {
            return false;
        }
        int n3 = 0;
        while (n3 < n2) {
            aw aw2 = this.b.d.a.get(n3);
            if (aw2 != null && aw2.f) {
                return true;
            }
            ++n3;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    protected ArrayList<n.a> a(ArrayList<n.a> arrayList) {
        ArrayList arrayList2;
        if (arrayList == null) return null;
        if (arrayList.size() == 0) {
            return null;
        }
        if (this.b == null) return null;
        if (this.b.d == null) return null;
        if (this.b.d.a == null) return null;
        int n2 = this.b.d.a.size();
        if (((n.a)arrayList.get((int)0)).e >= n2) return null;
        this.a((List<n.a>)arrayList);
        if (arrayList.size() == 0) return null;
        if (this.b.d.a.size() == 0) return null;
        n2 = ((n.a)arrayList.get((int)0)).e;
        if (this.b.d.a.get((int)n2).j != null) {
            ce ce2 = new ce(this.d, arrayList);
            ce2.a(this.b.d.a.get(((n.a)arrayList.get((int)0)).e));
            arrayList2 = (ArrayList)ce2.a();
            ce2.a((aw)null);
        } else {
            arrayList2 = null;
        }
        this.c(arrayList);
        if (this.b == null) return arrayList2;
        if (this.b.d == null) {
            return arrayList2;
        }
        this.b.d.b();
        return arrayList2;
    }

    @Override
    public void a() {
        super.a();
        this.e.clear();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(List<n.a> list) {
        int n2;
        if (list == null || (n2 = list.size()) == 0) {
            return;
        }
        int n3 = 0;
        while (n3 < n2) {
            if (!this.e.b(list.get(n3))) {
                list.remove(n3);
                --n3;
                --n2;
            }
            ++n3;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(boolean bl2, boolean bl3) {
        ArrayList<n.a> arrayList;
        if (!this.i() || (arrayList = this.b.h.a(this.b.h.i, this.b.h.g, this.b.b.c(), this.b.b.d())) == null || arrayList.size() <= 0) {
            return;
        }
        int n2 = this.b.d.a.size();
        int n3 = 0;
        bl2 = true;
        do {
            if (n3 >= n2) {
                arrayList.clear();
                this.b.b.g().invalidate();
                return;
            }
            ArrayList<n.a> arrayList2 = this.a(arrayList, this.b.d.a.get(n3), this.b.b.e(), bl3);
            if (arrayList2 != null) {
                this.a(arrayList2, bl2);
                if (bl2) {
                    bl2 = false;
                }
                arrayList2.clear();
            }
            ++n3;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    protected ArrayList<n.a> b(ArrayList<n.a> var1_1) {
        if (var1_1 == null) {
            return null;
        }
        var2_3 = var1_1.size();
        if (var2_3 == 0) {
            return null;
        }
        var3_4 = 0;
        var7_5 = null;
        block0 : do {
            var8_2 = var7_5;
            if (var3_4 >= var2_3) return var8_2;
            var8_2 = (n.a)var1_1.get(var3_4);
            if (var8_2 != null) {
                if (this.b == null) return null;
                if (this.b.d == null) return null;
                if (this.b.d.a == null) {
                    return null;
                }
                var4_6 = this.b.d.a.size();
                if (var8_2.e < var4_6 && this.b.d.a.get((int)var8_2.e).g) {
                    var5_7 = this.b.d.a.get((int)var8_2.e).n.a(var8_2);
                    if (var5_7 >= 0) {
                        var1_1.remove(var3_4);
                        --var2_3;
                        --var3_4;
                        var9_9 = this.b.d.a.get((int)var8_2.e).o;
                        if (var9_9 != null) {
                            break;
                        }
                    } else {
                        if (var7_5 == null) {
                            var7_5 = new ArrayList();
                        }
                        var8_2 = new n.a(var8_2);
                        var8_2.a = -1;
                        var7_5.add((Object)var8_2);
                    }
                }
            }
lbl33: // 8 sources:
            do {
                ++var3_4;
                continue block0;
                break;
            } while (true);
            break;
        } while (true);
        var6_8 = var9_9.size();
        var4_6 = 0;
        do {
            if (var4_6 >= var6_8) ** GOTO lbl33
            var10_10 = var9_9.get(var4_6);
            if (var10_10 != null && var10_10.equals(var8_2)) {
                var10_10.g = var5_7;
                this.b.d.b();
                ** continue;
            }
            ++var4_6;
        } while (true);
    }

    @Override
    protected int e() {
        return 4;
    }

    @Override
    protected int f() {
        return 1;
    }

    @Override
    public void g() {
    }

    @Override
    public void h() {
        this.a(false, false);
    }
}


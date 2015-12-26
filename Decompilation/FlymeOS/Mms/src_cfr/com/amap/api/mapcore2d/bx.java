/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.LinkedList
 *  java.util.concurrent.Semaphore
 */
package com.amap.api.mapcore2d;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

class bx<T> {
    protected LinkedList<T> a = new LinkedList();
    protected final Semaphore b = new Semaphore(0, false);
    protected boolean c = true;

    bx() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ArrayList<T> a(int n2, boolean bl2) {
        if (this.a == null) return null;
        try {
            this.b.acquire();
        }
        catch (InterruptedException var3_3) {}
        try {
            if (this.c) return this.b(n2, bl2);
            return null;
        }
        catch (Exception var3_5) {
            return null;
        }
    }

    public void a() {
        this.c = false;
        this.b.release(100);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(List<T> list, boolean bl2) {
        synchronized (this) {
            LinkedList<T> linkedList = this.a;
            if (linkedList != null) {
                void var2_2;
                if (var2_2 == true) {
                    this.a.clear();
                }
                if (list != null) {
                    this.a.addAll(list);
                }
                this.b();
            }
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected ArrayList<T> b(int n2, boolean bl2) {
        int n3 = 0;
        synchronized (this) {
            int n4;
            ArrayList arrayList;
            block11 : {
                block10 : {
                    arrayList = this.a;
                    if (arrayList != null) break block10;
                    return null;
                }
                int n5 = this.a.size();
                n4 = n2;
                if (n2 <= n5) break block11;
                n4 = n5;
            }
            arrayList = new ArrayList(n4);
            for (n2 = n3; n2 < n4; ++n2) {
                arrayList.add(this.a.get(0));
                this.a.removeFirst();
                continue;
            }
            try {
                this.b();
                return arrayList;
            }
            catch (Throwable var6_5) {
                throw var6_5;
            }
            finally {
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void b() {
        if (this.a == null || !this.c || this.a.size() == 0) {
            return;
        }
        this.b.release();
    }

    public void c() {
        if (this.a == null) {
            return;
        }
        this.a.clear();
    }
}


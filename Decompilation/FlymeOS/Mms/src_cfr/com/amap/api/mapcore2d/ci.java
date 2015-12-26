/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Message
 *  java.lang.Object
 */
package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Message;
import com.amap.api.mapcore2d.ag;
import com.amap.api.mapcore2d.au;
import com.amap.api.mapcore2d.cj;

class ci
implements au {
    private ag a;
    private boolean b = true;
    private boolean c = false;
    private boolean d = true;
    private boolean e = true;
    private boolean f = true;
    private boolean g = false;
    private int h = 0;
    private int i = 0;
    private final Handler j;

    ci(ag ag2) {
        this.j = new cj(this);
        this.a = ag2;
    }

    static /* synthetic */ ag a(ci ci2) {
        return ci2.a;
    }

    static /* synthetic */ boolean b(ci ci2) {
        return ci2.e;
    }

    static /* synthetic */ boolean c(ci ci2) {
        return ci2.g;
    }

    static /* synthetic */ boolean d(ci ci2) {
        return ci2.f;
    }

    static /* synthetic */ boolean e(ci ci2) {
        return ci2.c;
    }

    @Override
    public void a(int n2) {
        this.h = n2;
        this.a.c(n2);
    }

    @Override
    public void a(boolean bl2) {
        this.g = bl2;
        this.j.obtainMessage(1).sendToTarget();
    }

    @Override
    public boolean a() {
        return this.g;
    }

    @Override
    public void b(int n2) {
        this.i = n2;
        this.a.d(n2);
    }

    @Override
    public void b(boolean bl2) {
        this.e = bl2;
        this.j.obtainMessage(0).sendToTarget();
    }

    @Override
    public boolean b() {
        return this.e;
    }

    @Override
    public void c(boolean bl2) {
        this.f = bl2;
        this.j.obtainMessage(2).sendToTarget();
    }

    @Override
    public boolean c() {
        return this.f;
    }

    @Override
    public void d(boolean bl2) {
        this.c = bl2;
        this.j.obtainMessage(3).sendToTarget();
    }

    @Override
    public boolean d() {
        return this.c;
    }

    @Override
    public void e(boolean bl2) {
        this.b = bl2;
    }

    @Override
    public boolean e() {
        return this.b;
    }

    @Override
    public void f(boolean bl2) {
        this.d = bl2;
    }

    @Override
    public boolean f() {
        return this.d;
    }

    @Override
    public int g() {
        return this.h;
    }

    @Override
    public void g(boolean bl2) {
        this.f(bl2);
        this.e(bl2);
    }

    @Override
    public int h() {
        return this.i;
    }
}


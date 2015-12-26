/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 */
import android.view.View;
import android.view.ViewGroup;
import flyme.support.v7.widget.RecyclerView;
import java.util.List;

public class ard {
    final b a;
    final a b;
    final List<View> c;

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int e(int n2) {
        if (n2 < 0) {
            return -1;
        }
        int n3 = this.a.a();
        int n4 = n2;
        while (n4 < n3) {
            int n5 = n2 - (n4 - this.b.e(n4));
            if (n5 == 0) {
                do {
                    n2 = n4;
                    if (!this.b.c(n4)) return n2;
                    ++n4;
                } while (true);
            }
            n4 += n5;
        }
        return -1;
    }

    public View a(int n2, int n3) {
        int n4 = this.c.size();
        for (int i2 = 0; i2 < n4; ++i2) {
            View view = this.c.get(i2);
            RecyclerView.t t2 = this.a.b(view);
            if (t2.d() != n2 || t2.l() || n3 != -1 && t2.f() != n3) continue;
            return view;
        }
        return null;
    }

    public void a() {
        this.b.a();
        this.c.clear();
        this.a.b();
    }

    public void a(int n2) {
        View view = this.a.b(n2 = this.e(n2));
        if (view == null) {
            return;
        }
        if (this.b.d(n2)) {
            this.c.remove((Object)view);
        }
        this.a.a(n2);
    }

    public void a(View view) {
        int n2 = this.a.a(view);
        if (n2 < 0) {
            return;
        }
        if (this.b.d(n2)) {
            this.c.remove((Object)view);
        }
        this.a.a(n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(View view, int n2, ViewGroup.LayoutParams layoutParams, boolean bl2) {
        n2 = n2 < 0 ? this.a.a() : this.e(n2);
        this.b.a(n2, bl2);
        if (bl2) {
            this.c.add(view);
        }
        this.a.a(view, n2, layoutParams);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(View view, int n2, boolean bl2) {
        n2 = n2 < 0 ? this.a.a() : this.e(n2);
        this.b.a(n2, bl2);
        if (bl2) {
            this.c.add(view);
        }
        this.a.a(view, n2);
    }

    public void a(View view, boolean bl2) {
        this.a(view, -1, bl2);
    }

    public int b() {
        return this.a.a() - this.c.size();
    }

    /*
     * Enabled aggressive block sorting
     */
    public int b(View view) {
        int n2 = this.a.a(view);
        if (n2 == -1 || this.b.c(n2)) {
            return -1;
        }
        return n2 - this.b.e(n2);
    }

    public View b(int n2) {
        n2 = this.e(n2);
        return this.a.b(n2);
    }

    public int c() {
        return this.a.a();
    }

    public View c(int n2) {
        return this.a.b(n2);
    }

    public boolean c(View view) {
        return this.c.contains((Object)view);
    }

    public void d(int n2) {
        n2 = this.e(n2);
        this.b.d(n2);
        this.a.c(n2);
    }

    public void d(View view) {
        int n2 = this.a.a(view);
        if (n2 < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + (Object)view);
        }
        this.b.a(n2);
        this.c.add(view);
    }

    public String toString() {
        return this.b.toString() + ", hidden list:" + this.c.size();
    }

    static class a {
        long a = 0;
        a b;

        a() {
        }

        private void b() {
            if (this.b == null) {
                this.b = new a();
            }
        }

        void a() {
            this.a = 0;
            if (this.b != null) {
                this.b.a();
            }
        }

        void a(int n2) {
            if (n2 >= 64) {
                this.b();
                this.b.a(n2 - 64);
                return;
            }
            this.a |= 1 << n2;
        }

        /*
         * Enabled aggressive block sorting
         */
        void a(int n2, boolean bl2) {
            if (n2 >= 64) {
                this.b();
                this.b.a(n2 - 64, bl2);
                return;
            } else {
                boolean bl3 = (this.a & Long.MIN_VALUE) != 0;
                long l2 = (1 << n2) - 1;
                long l3 = this.a;
                this.a = ((l2 ^ -1) & this.a) << 1 | l3 & l2;
                if (bl2) {
                    this.a(n2);
                } else {
                    this.b(n2);
                }
                if (!bl3 && this.b == null) return;
                {
                    this.b();
                    this.b.a(0, bl3);
                    return;
                }
            }
        }

        void b(int n2) {
            if (n2 >= 64) {
                if (this.b != null) {
                    this.b.b(n2 - 64);
                }
                return;
            }
            this.a &= 1 << n2 ^ -1;
        }

        boolean c(int n2) {
            if (n2 >= 64) {
                this.b();
                return this.b.c(n2 - 64);
            }
            if ((this.a & 1 << n2) != 0) {
                return true;
            }
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        boolean d(int n2) {
            if (n2 >= 64) {
                this.b();
                return this.b.d(n2 - 64);
            }
            long l2 = 1 << n2;
            boolean bl2 = (this.a & l2) != 0;
            this.a &= l2 ^ -1;
            long l3 = this.a;
            this.a = Long.rotateRight((long)((l2 ^ -1) & this.a), (int)1) | l3 & --l2;
            boolean bl3 = bl2;
            if (this.b == null) return bl3;
            if (this.b.c(0)) {
                this.a(63);
            }
            this.b.d(0);
            return bl2;
        }

        int e(int n2) {
            if (this.b == null) {
                if (n2 >= 64) {
                    return Long.bitCount((long)this.a);
                }
                return Long.bitCount((long)(this.a & (1 << n2) - 1));
            }
            if (n2 < 64) {
                return Long.bitCount((long)(this.a & (1 << n2) - 1));
            }
            return this.b.e(n2 - 64) + Long.bitCount((long)this.a);
        }

        public String toString() {
            if (this.b == null) {
                return Long.toBinaryString((long)this.a);
            }
            return this.b.toString() + "xx" + Long.toBinaryString((long)this.a);
        }
    }

    public static interface b {
        public int a();

        public int a(View var1);

        public void a(int var1);

        public void a(View var1, int var2);

        public void a(View var1, int var2, ViewGroup.LayoutParams var3);

        public View b(int var1);

        public RecyclerView.t b(View var1);

        public void b();

        public void c(int var1);
    }

}


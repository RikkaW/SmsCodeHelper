/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
import android.support.v4.util.Pools;
import flyme.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class arc
implements ari.a {
    final ArrayList<b> a;
    final ArrayList<b> b;
    final a c;
    Runnable d;
    final boolean e;
    final ari f;
    private Pools.Pool<b> g;

    /*
     * Enabled aggressive block sorting
     */
    private int b(int n2, int n3) {
        b b2;
        for (int i2 = this.b.size() - 1; i2 >= 0; --i2) {
            int n4;
            b2 = (b)this.b.get(i2);
            if (b2.a == 3) {
                int n5;
                if (b2.b < b2.c) {
                    n5 = b2.b;
                    n4 = b2.c;
                } else {
                    n5 = b2.c;
                    n4 = b2.b;
                }
                if (n2 >= n5 && n2 <= n4) {
                    if (n5 == b2.b) {
                        if (n3 == 0) {
                            ++b2.c;
                        } else if (n3 == 1) {
                            --b2.c;
                        }
                        ++n2;
                    } else {
                        if (n3 == 0) {
                            ++b2.b;
                        } else if (n3 == 1) {
                            --b2.b;
                        }
                        --n2;
                    }
                } else if (n2 < b2.b) {
                    if (n3 == 0) {
                        ++b2.b;
                        ++b2.c;
                    } else if (n3 == 1) {
                        --b2.b;
                        --b2.c;
                    }
                }
                n4 = n2;
            } else if (b2.b <= n2) {
                if (b2.a == 0) {
                    n4 = n2 - b2.c;
                } else {
                    n4 = n2;
                    if (b2.a == 1) {
                        n4 = n2 + b2.c;
                    }
                }
            } else if (n3 == 0) {
                ++b2.b;
                n4 = n2;
            } else {
                n4 = n2;
                if (n3 == 1) {
                    --b2.b;
                    n4 = n2;
                }
            }
            n2 = n4;
        }
        n3 = this.b.size() - 1;
        while (n3 >= 0) {
            b2 = (b)this.b.get(n3);
            if (b2.a == 3) {
                if (b2.c == b2.b || b2.c < 0) {
                    this.b.remove(n3);
                    this.a(b2);
                }
            } else if (b2.c <= 0) {
                this.b.remove(n3);
                this.a(b2);
            }
            --n3;
        }
        return n2;
    }

    private void b(b b2) {
        this.g(b2);
    }

    private boolean b(int n2) {
        int n3 = this.b.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            b b2 = (b)this.b.get(i2);
            if (b2.a == 3) {
                if (this.a(b2.c, i2 + 1) != n2) continue;
                return true;
            }
            if (b2.a != 0) continue;
            int n4 = b2.b;
            int n5 = b2.c;
            for (int i3 = b2.b; i3 < n4 + n5; ++i3) {
                if (this.a(i3, i2 + 1) != n2) continue;
                return true;
            }
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void c(b b2) {
        int n2 = b2.b;
        int n3 = b2.b + b2.c;
        int n4 = -1;
        int n5 = b2.b;
        int n6 = 0;
        while (n5 < n3) {
            int n7;
            if (this.c.a(n5) != null || this.b(n5)) {
                if (n4 == 0) {
                    this.e(this.a(1, n2, n6));
                    n7 = 1;
                } else {
                    n7 = 0;
                }
                n4 = 1;
            } else {
                if (n4 == 1) {
                    this.g(this.a(1, n2, n6));
                    n4 = 1;
                } else {
                    n4 = 0;
                }
                int n8 = 0;
                n7 = n4;
                n4 = n8;
            }
            if (n7 != 0) {
                n7 = n5 - n6;
                n5 = n3 - n6;
                n3 = 1;
            } else {
                n7 = n5;
                n5 = n3;
                n3 = ++n6;
            }
            n6 = n3;
            n3 = n5;
            n5 = n7 + 1;
        }
        b b3 = b2;
        if (n6 != b2.c) {
            this.a(b2);
            b3 = this.a(1, n2, n6);
        }
        if (n4 == 0) {
            this.e(b3);
            return;
        }
        this.g(b3);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void d(b b2) {
        int n2 = b2.b;
        int n3 = b2.b;
        int n4 = b2.c;
        int n5 = -1;
        int n6 = 0;
        for (int i2 = b2.b; i2 < n3 + n4; ++i2) {
            int n7;
            int n8;
            if (this.c.a(i2) != null || this.b(i2)) {
                n8 = n6;
                n7 = n2;
                if (n5 == 0) {
                    this.e(this.a(2, n2, n6));
                    n8 = 0;
                    n7 = i2;
                }
                n2 = n7;
                n6 = 1;
            } else {
                n8 = n6;
                n7 = n2;
                if (n5 == 1) {
                    this.g(this.a(2, n2, n6));
                    n8 = 0;
                    n7 = i2;
                }
                n2 = n7;
                n6 = 0;
            }
            n5 = n6;
            n6 = ++n8;
        }
        b b3 = b2;
        if (n6 != b2.c) {
            this.a(b2);
            b3 = this.a(2, n2, n6);
        }
        if (n5 == 0) {
            this.e(b3);
            return;
        }
        this.g(b3);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void e(b b2) {
        int n2;
        if (b2.a == 0 || b2.a == 3) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int n3 = this.b(b2.b, b2.a);
        int n4 = b2.b;
        switch (b2.a) {
            default: {
                throw new IllegalArgumentException("op should be remove or update." + b2);
            }
            case 2: {
                n2 = 1;
                break;
            }
            case 1: {
                n2 = 0;
                break;
            }
        }
        int n5 = 1;
        for (int i2 = 1; i2 < b2.c; ++i2) {
            int n6;
            int n7 = this.b(b2.b + n2 * i2, b2.a);
            switch (b2.a) {
                default: {
                    n6 = 0;
                    break;
                }
                case 2: {
                    if (n7 == n3 + 1) {
                        n6 = 1;
                        break;
                    }
                    n6 = 0;
                    break;
                }
                case 1: {
                    n6 = n7 == n3 ? 1 : 0;
                }
            }
            if (n6 != 0) {
                n6 = n5 + 1;
            } else {
                b b3 = this.a(b2.a, n3, n5);
                this.a(b3, n4);
                this.a(b3);
                n6 = n4;
                if (b2.a == 2) {
                    n6 = n4 + n5;
                }
                n5 = 1;
                n3 = n7;
                n4 = n6;
                n6 = n5;
            }
            n5 = n6;
        }
        this.a(b2);
        if (n5 > 0) {
            b2 = this.a(b2.a, n3, n5);
            this.a(b2, n4);
            this.a(b2);
        }
    }

    private void f(b b2) {
        this.g(b2);
    }

    private void g(b b2) {
        this.b.add((Object)b2);
        switch (b2.a) {
            default: {
                throw new IllegalArgumentException("Unknown update op type for " + b2);
            }
            case 0: {
                this.c.d(b2.b, b2.c);
                return;
            }
            case 3: {
                this.c.e(b2.b, b2.c);
                return;
            }
            case 1: {
                this.c.b(b2.b, b2.c);
                return;
            }
            case 2: 
        }
        this.c.c(b2.b, b2.c);
    }

    public int a(int n2) {
        return this.a(n2, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    int a(int n2, int n3) {
        int n4 = this.b.size();
        int n5 = n3;
        n3 = n2;
        do {
            n2 = n3;
            if (n5 >= n4) return n2;
            b b2 = (b)this.b.get(n5);
            if (b2.a == 3) {
                if (b2.b == n3) {
                    n2 = b2.c;
                } else {
                    int n6 = n3;
                    if (b2.b < n3) {
                        n6 = n3 - 1;
                    }
                    n2 = n6;
                    if (b2.c <= n6) {
                        n2 = n6 + 1;
                    }
                }
            } else {
                n2 = n3;
                if (b2.b <= n3) {
                    if (b2.a == 1) {
                        if (n3 < b2.b + b2.c) {
                            return -1;
                        }
                        n2 = n3 - b2.c;
                    } else {
                        n2 = n3;
                        if (b2.a == 0) {
                            n2 = n3 + b2.c;
                        }
                    }
                }
            }
            ++n5;
            n3 = n2;
        } while (true);
    }

    @Override
    public b a(int n2, int n3, int n4) {
        b b2 = this.g.acquire();
        if (b2 == null) {
            return new b(n2, n3, n4);
        }
        b2.a = n2;
        b2.b = n3;
        b2.c = n4;
        return b2;
    }

    public void a() {
        this.a((List<b>)this.a);
        this.a((List<b>)this.b);
    }

    @Override
    public void a(b b2) {
        if (!this.e) {
            this.g.release(b2);
        }
    }

    void a(b b2, int n2) {
        this.c.a(b2);
        switch (b2.a) {
            default: {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            case 1: {
                this.c.a(n2, b2.c);
                return;
            }
            case 2: 
        }
        this.c.c(n2, b2.c);
    }

    void a(List<b> list) {
        int n2 = list.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            this.a(list.get(i2));
        }
        list.clear();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void b() {
        this.f.a((List<b>)this.a);
        var2_1 = this.a.size();
        var1_2 = 0;
        do {
            if (var1_2 >= var2_1) {
                this.a.clear();
                return;
            }
            var3_3 = (b)this.a.get(var1_2);
            switch (var3_3.a) {
                case 0: {
                    this.f(var3_3);
                    ** break;
                }
                case 1: {
                    this.c(var3_3);
                    ** break;
                }
                case 2: {
                    this.d(var3_3);
                }
lbl18: // 4 sources:
                default: {
                    ** GOTO lbl22
                }
                case 3: 
            }
            this.b(var3_3);
lbl22: // 2 sources:
            if (this.d != null) {
                this.d.run();
            }
            ++var1_2;
        } while (true);
    }

    public void c() {
        int n2 = this.b.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            this.c.b((b)this.b.get(i2));
        }
        this.a((List<b>)this.b);
    }

    public boolean d() {
        if (this.a.size() > 0) {
            return true;
        }
        return false;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void e() {
        this.c();
        var2_1 = this.a.size();
        var1_2 = 0;
        do {
            if (var1_2 >= var2_1) {
                this.a((List<b>)this.a);
                return;
            }
            var3_3 = (b)this.a.get(var1_2);
            switch (var3_3.a) {
                case 0: {
                    this.c.b(var3_3);
                    this.c.d(var3_3.b, var3_3.c);
                    ** break;
                }
                case 1: {
                    this.c.b(var3_3);
                    this.c.a(var3_3.b, var3_3.c);
                    ** break;
                }
                case 2: {
                    this.c.b(var3_3);
                    this.c.c(var3_3.b, var3_3.c);
                }
lbl21: // 4 sources:
                default: {
                    ** GOTO lbl26
                }
                case 3: 
            }
            this.c.b(var3_3);
            this.c.e(var3_3.b, var3_3.c);
lbl26: // 2 sources:
            if (this.d != null) {
                this.d.run();
            }
            ++var1_2;
        } while (true);
    }

    public static interface a {
        public RecyclerView.t a(int var1);

        public void a(int var1, int var2);

        public void a(b var1);

        public void b(int var1, int var2);

        public void b(b var1);

        public void c(int var1, int var2);

        public void d(int var1, int var2);

        public void e(int var1, int var2);
    }

    static class b {
        int a;
        int b;
        int c;

        b(int n2, int n3, int n4) {
            this.a = n2;
            this.b = n3;
            this.c = n4;
        }

        String a() {
            switch (this.a) {
                default: {
                    return "??";
                }
                case 0: {
                    return "add";
                }
                case 1: {
                    return "rm";
                }
                case 2: {
                    return "up";
                }
                case 3: 
            }
            return "mv";
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null) return false;
            if (this.getClass() != object.getClass()) {
                return false;
            }
            object = (b)object;
            if (this.a != object.a) {
                return false;
            }
            if (this.a == 3 && Math.abs((int)(this.c - this.b)) == 1 && this.c == object.b) {
                if (this.b == object.c) return true;
            }
            if (this.c != object.c) {
                return false;
            }
            if (this.b == object.b) return true;
            return false;
        }

        public int hashCode() {
            return (this.a * 31 + this.b) * 31 + this.c;
        }

        public String toString() {
            return "[" + this.a() + ",s:" + this.b + "c:" + this.c + "]";
        }
    }

}


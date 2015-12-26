/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.Cursor
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class aif
extends BaseAdapter {
    private final Context a;
    private a[] b;
    private int c = 0;
    private int d = 0;
    private boolean e = true;
    private boolean f = true;
    private boolean g;

    public aif(Context context, int n2) {
        this.a = context;
        this.b = new a[2];
    }

    protected int a(int n2, int n3) {
        return 1;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected View a(int n2, Cursor cursor, int n3, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.a(this.a, n2, cursor, n3, viewGroup);
        }
        this.a(view, n2, cursor, n3);
        return view;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected View a(int n2, Cursor cursor, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.a(this.a, n2, cursor, viewGroup);
        }
        this.a(view, n2, cursor);
        return view;
    }

    protected abstract View a(Context var1, int var2, Cursor var3, int var4, ViewGroup var5);

    protected View a(Context context, int n2, Cursor cursor, ViewGroup viewGroup) {
        return null;
    }

    public void a(int n2, Cursor cursor) {
        Cursor cursor2 = this.b[n2].c;
        if (cursor2 != cursor) {
            if (cursor2 != null && !cursor2.isClosed()) {
                cursor2.close();
            }
            this.b[n2].c = cursor;
            if (cursor != null) {
                this.b[n2].d = cursor.getColumnIndex("_id");
            }
            this.e();
            this.notifyDataSetChanged();
        }
    }

    public void a(int n2, boolean bl2) {
        this.b[n2].b = bl2;
        this.e();
    }

    public void a(a a2) {
        a[] arra;
        if (this.c >= this.b.length) {
            arra = new a[this.c + 2];
            System.arraycopy((Object)this.b, (int)0, (Object)arra, (int)0, (int)this.c);
            this.b = arra;
        }
        arra = this.b;
        int n2 = this.c;
        this.c = n2 + 1;
        arra[n2] = a2;
        this.e();
        this.notifyDataSetChanged();
    }

    protected void a(View view, int n2, Cursor cursor) {
    }

    protected abstract void a(View var1, int var2, Cursor var3, int var4);

    public void a(boolean bl2) {
        this.f = bl2;
        if (bl2 && this.g) {
            this.notifyDataSetChanged();
        }
    }

    public void a(boolean bl2, boolean bl3) {
        this.a(new a(bl2, bl3));
    }

    public boolean areAllItemsEnabled() {
        for (int i2 = 0; i2 < this.c; ++i2) {
            if (!this.b[i2].b) continue;
            return false;
        }
        return true;
    }

    public void b(int n2) {
        Cursor cursor = this.b[n2].c;
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        System.arraycopy((Object)this.b, (int)(n2 + 1), (Object)this.b, (int)n2, (int)(this.c - n2 - 1));
        --this.c;
        this.e();
        this.notifyDataSetChanged();
    }

    protected boolean b(int n2, int n3) {
        return true;
    }

    public a c(int n2) {
        if (n2 >= this.c) {
            throw new ArrayIndexOutOfBoundsException(n2);
        }
        return this.b[n2];
    }

    public Context c() {
        return this.a;
    }

    public Cursor d(int n2) {
        return this.b[n2].c;
    }

    public void d() {
        for (int i2 = 0; i2 < this.c; ++i2) {
            Cursor cursor = this.b[i2].c;
            if (cursor == null || cursor.isClosed()) continue;
            cursor.close();
            this.b[i2].c = null;
        }
        this.c = 0;
        this.e();
        this.notifyDataSetChanged();
    }

    public int e(int n2) {
        this.g();
        int n3 = 0;
        for (int i2 = 0; i2 < this.c; ++i2) {
            int n4 = this.b[i2].e + n3;
            if (n2 >= n3 && n2 < n4) {
                return i2;
            }
            n3 = n4;
        }
        return -1;
    }

    protected void e() {
        this.e = false;
    }

    public int f() {
        return this.c;
    }

    public int f(int n2) {
        int n3 = 0;
        for (int i2 = 0; i2 < this.c; ++i2) {
            int n4 = this.b[i2].e + n3;
            if (n2 >= n3 && n2 < n4) {
                return n2 - n3;
            }
            n3 = n4;
        }
        return -1;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void g() {
        if (this.e) {
            return;
        }
        this.d = 0;
        var2_1 = 0;
        do {
            if (var2_1 >= this.c) {
                this.e = true;
                return;
            }
            var4_4 = this.b[var2_1].c;
            var1_2 = var4_4 != null ? var4_4.getCount() : 0;
            var3_3 = var1_2;
            if (!this.b[var2_1].b) ** GOTO lbl17
            if (var1_2 != 0) ** GOTO lbl-1000
            var3_3 = var1_2;
            if (this.b[var2_1].a) lbl-1000: // 2 sources:
            {
                var3_3 = var1_2 + 1;
            }
lbl17: // 4 sources:
            this.b[var2_1].e = var3_3;
            this.d = var3_3 + this.d;
            ++var2_1;
        } while (true);
    }

    public int getCount() {
        this.g();
        return this.d;
    }

    public Object getItem(int n2) {
        this.g();
        int n3 = 0;
        for (int i2 = 0; i2 < this.c; ++i2) {
            int n4 = this.b[i2].e + n3;
            if (n2 >= n3 && n2 < n4) {
                n2 = n3 = n2 - n3;
                if (this.b[i2].b) {
                    n2 = n3 - 1;
                }
                if (n2 == -1) {
                    return null;
                }
                Cursor cursor = this.b[i2].c;
                cursor.moveToPosition(n2);
                return cursor;
            }
            n3 = n4;
        }
        return null;
    }

    public long getItemId(int n2) {
        this.g();
        int n3 = 0;
        for (int i2 = 0; i2 < this.c; ++i2) {
            int n4 = this.b[i2].e + n3;
            if (n2 >= n3 && n2 < n4) {
                n2 = n3 = n2 - n3;
                if (this.b[i2].b) {
                    n2 = n3 - 1;
                }
                if (n2 == -1) {
                    return 0;
                }
                if (this.b[i2].d == -1) {
                    return 0;
                }
                Cursor cursor = this.b[i2].c;
                if (cursor == null || cursor.isClosed() || !cursor.moveToPosition(n2)) {
                    return 0;
                }
                return cursor.getLong(this.b[i2].d);
            }
            n3 = n4;
        }
        return 0;
    }

    public int getItemViewType(int n2) {
        this.g();
        int n3 = 0;
        for (int i2 = 0; i2 < this.c; ++i2) {
            int n4 = this.b[i2].e + n3;
            if (n2 >= n3 && n2 < n4) {
                if (this.b[i2].b && n2 - n3 == 0) {
                    return -1;
                }
                return this.a(i2, n2);
            }
            n3 = n4;
        }
        throw new ArrayIndexOutOfBoundsException(n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public View getView(int n2, View view, ViewGroup viewGroup) {
        int n3 = 0;
        this.g();
        int n4 = 0;
        do {
            if (n3 >= this.c) {
                throw new ArrayIndexOutOfBoundsException(n2);
            }
            int n5 = this.b[n3].e + n4;
            if (n2 >= n4 && n2 < n5) {
                n2 = n4 = n2 - n4;
                if (this.b[n3].b) {
                    n2 = n4 - 1;
                }
                if (n2 == -1) {
                    view = this.a(n3, this.b[n3].c, view, viewGroup);
                } else {
                    if (!this.b[n3].c.moveToPosition(n2)) {
                        throw new IllegalStateException("Couldn't move cursor to position " + n2);
                    }
                    view = this.a(n3, this.b[n3].c, n2, view, viewGroup);
                }
                if (view != null) break;
                throw new NullPointerException("View should not be null, partition: " + n3 + " position: " + n2);
            }
            ++n3;
            n4 = n5;
        } while (true);
        return view;
    }

    public int getViewTypeCount() {
        return this.h() + 1;
    }

    public int h() {
        return 1;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isEnabled(int n2) {
        this.g();
        int n3 = 0;
        int n4 = 0;
        while (n3 < this.c) {
            int n5 = this.b[n3].e + n4;
            if (n2 >= n4 && n2 < n5) {
                if (!this.b[n3].b || (n2 -= n4) != 0) return this.b(n3, n2);
                return false;
            }
            ++n3;
            n4 = n5;
        }
        return false;
    }

    public void notifyDataSetChanged() {
        if (this.f) {
            this.g = false;
            super.notifyDataSetChanged();
            return;
        }
        this.g = true;
    }

    public static class a {
        boolean a;
        boolean b;
        Cursor c;
        int d;
        int e;

        public a(boolean bl2, boolean bl3) {
            this.a = bl2;
            this.b = bl3;
        }

        public boolean a() {
            return this.b;
        }
    }

}


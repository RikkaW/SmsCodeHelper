/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.graphics.Canvas
 *  android.graphics.ColorFilter
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.Drawable$Callback
 *  android.graphics.drawable.Drawable$ConstantState
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class aek
extends Drawable
implements Drawable.Callback {
    b a;
    private int b = 0;
    private int[] c;
    private int[] d;
    private int[] e;
    private int[] f;
    private final Rect g = new Rect();
    private boolean h;

    aek() {
        this((b)null, null);
    }

    aek(b b2, Resources resources) {
        this.a = b2 = this.a(b2, resources);
        if (b2.a > 0) {
            this.a();
        }
    }

    public aek(Drawable[] arrdrawable) {
        this(arrdrawable, null);
    }

    aek(Drawable[] arrdrawable, b arra) {
        this((b)arra, null);
        int n2 = arrdrawable.length;
        arra = new a[n2];
        for (int i2 = 0; i2 < n2; ++i2) {
            arra[i2] = new a();
            arra[i2].a = arrdrawable[i2];
            arrdrawable[i2].setCallback((Drawable.Callback)this);
            b b2 = this.a;
            b2.d |= arrdrawable[i2].getChangingConfigurations();
        }
        this.a.a = n2;
        this.a.b = arra;
        this.a();
    }

    private void a() {
        int n2 = this.a.a;
        if (this.c != null && this.c.length >= n2) {
            return;
        }
        this.c = new int[n2];
        this.d = new int[n2];
        this.e = new int[n2];
        this.f = new int[n2];
    }

    private boolean a(int n2, a a2) {
        Rect rect = this.g;
        a2.a.getPadding(rect);
        if (rect.left != this.c[n2] || rect.top != this.d[n2] || rect.right != this.e[n2] || rect.bottom != this.f[n2]) {
            this.c[n2] = rect.left;
            this.d[n2] = rect.top;
            this.e[n2] = rect.right;
            this.f[n2] = rect.bottom;
            return true;
        }
        return false;
    }

    b a(b b2, Resources resources) {
        return new b(b2, this, resources);
    }

    public void draw(Canvas canvas) {
        a[] arra = this.a.b;
        int n2 = this.a.a;
        for (int i2 = 0; i2 < n2; ++i2) {
            arra[i2].a.draw(canvas);
        }
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.a.c | this.a.d;
    }

    public Drawable.ConstantState getConstantState() {
        if (this.a.c()) {
            this.a.c = this.getChangingConfigurations();
            return this.a;
        }
        return null;
    }

    public int getIntrinsicHeight() {
        return this.a.b[0].a.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return this.a.b[0].a.getIntrinsicWidth();
    }

    public int getOpacity() {
        if (this.b != 0) {
            return this.b;
        }
        return this.a.a();
    }

    public boolean getPadding(Rect rect) {
        rect.left = 0;
        rect.top = 0;
        rect.right = 0;
        rect.bottom = 0;
        a[] arra = this.a.b;
        int n2 = this.a.a;
        for (int i2 = 0; i2 < n2; ++i2) {
            this.a(i2, arra[i2]);
        }
        rect.left = this.c[0];
        rect.top = this.d[0];
        rect.right = this.e[0];
        rect.bottom = this.f[0];
        return true;
    }

    public void invalidateDrawable(Drawable drawable2) {
        drawable2 = this.getCallback();
        if (drawable2 != null) {
            drawable2.invalidateDrawable((Drawable)this);
        }
    }

    public boolean isStateful() {
        return this.a.b();
    }

    public Drawable mutate() {
        if (!this.h && super.mutate() == this) {
            if (!this.a.c()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.a = new b(this.a, this, null);
            a[] arra = this.a.b;
            int n2 = this.a.a;
            for (int i2 = 0; i2 < n2; ++i2) {
                arra[i2].a.mutate();
            }
            this.h = true;
        }
        return this;
    }

    protected void onBoundsChange(Rect rect) {
        a[] arra = this.a.b;
        int n2 = this.a.a;
        for (int i2 = 0; i2 < n2; ++i2) {
            a a2 = arra[i2];
            a2.a.setBounds(rect.left + a2.b + 0, rect.top + a2.c + 0, rect.right - a2.d - 0, rect.bottom - a2.e - 0);
        }
    }

    protected boolean onLevelChange(int n2) {
        boolean bl2 = false;
        a[] arra = this.a.b;
        int n3 = this.a.a;
        boolean bl3 = false;
        for (int i2 = 0; i2 < n3; ++i2) {
            a a2 = arra[i2];
            if (a2.a.setLevel(n2)) {
                bl2 = true;
            }
            if (!this.a(i2, a2)) continue;
            bl3 = true;
        }
        if (bl3) {
            this.onBoundsChange(this.getBounds());
        }
        return bl2;
    }

    protected boolean onStateChange(int[] arrn) {
        boolean bl2 = false;
        a[] arra = this.a.b;
        int n2 = this.a.a;
        boolean bl3 = false;
        for (int i2 = 0; i2 < n2; ++i2) {
            a a2 = arra[i2];
            if (a2.a.setState(arrn)) {
                bl2 = true;
            }
            if (!this.a(i2, a2)) continue;
            bl3 = true;
        }
        if (bl3) {
            this.onBoundsChange(this.getBounds());
        }
        return bl2;
    }

    public void scheduleDrawable(Drawable drawable2, Runnable runnable, long l2) {
        drawable2 = this.getCallback();
        if (drawable2 != null) {
            drawable2.scheduleDrawable((Drawable)this, runnable, l2);
        }
    }

    public void setAlpha(int n2) {
        a[] arra = this.a.b;
        int n3 = this.a.a;
        arra[0].a.setAlpha(n2);
        arra[1].a.setAlpha(255 - n2);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        a[] arra = this.a.b;
        int n2 = this.a.a;
        for (int i2 = 0; i2 < n2; ++i2) {
            arra[i2].a.setColorFilter(colorFilter);
        }
    }

    public void setDither(boolean bl2) {
        a[] arra = this.a.b;
        int n2 = this.a.a;
        for (int i2 = 0; i2 < n2; ++i2) {
            arra[i2].a.setDither(bl2);
        }
    }

    public boolean setVisible(boolean bl2, boolean bl3) {
        boolean bl4 = super.setVisible(bl2, bl3);
        a[] arra = this.a.b;
        int n2 = this.a.a;
        for (int i2 = 0; i2 < n2; ++i2) {
            arra[i2].a.setVisible(bl2, bl3);
        }
        return bl4;
    }

    public void unscheduleDrawable(Drawable drawable2, Runnable runnable) {
        drawable2 = this.getCallback();
        if (drawable2 != null) {
            drawable2.unscheduleDrawable((Drawable)this, runnable);
        }
    }

    static class a {
        public Drawable a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;

        a() {
        }
    }

    static class b
    extends Drawable.ConstantState {
        int a;
        a[] b;
        int c;
        int d;
        private boolean e;
        private int f;
        private boolean g;
        private boolean h;
        private boolean i;
        private boolean j;

        /*
         * Enabled aggressive block sorting
         */
        b(b b2, aek aek2, Resources resources) {
            int n2;
            int n3 = 0;
            this.e = false;
            this.g = false;
            if (b2 == null) {
                this.a = 0;
                this.b = null;
                return;
            }
            a[] arra = b2.b;
            this.a = n2 = b2.a;
            this.b = new a[n2];
            this.c = b2.c;
            this.d = b2.d;
            do {
                a a2;
                if (n3 >= n2) {
                    this.e = b2.e;
                    this.f = b2.f;
                    this.g = b2.g;
                    this.h = b2.h;
                    this.j = true;
                    this.i = true;
                    return;
                }
                Object object = this.b;
                object[n3] = a2 = new a();
                object = arra[n3];
                a2.a = resources != null ? object.a.getConstantState().newDrawable(resources) : object.a.getConstantState().newDrawable();
                a2.a.setCallback((Drawable.Callback)aek2);
                a2.b = object.b;
                a2.c = object.c;
                a2.d = object.d;
                a2.e = object.e;
                a2.f = object.f;
                ++n3;
            } while (true);
        }

        /*
         * Enabled aggressive block sorting
         */
        public final int a() {
            if (this.e) {
                return this.f;
            }
            int n2 = this.a;
            int n3 = n2 > 0 ? this.b[0].a.getOpacity() : -2;
            int n4 = 1;
            do {
                if (n4 >= n2) {
                    this.f = n3;
                    this.e = true;
                    return n3;
                }
                n3 = Drawable.resolveOpacity((int)n3, (int)this.b[n4].a.getOpacity());
                ++n4;
            } while (true);
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public final boolean b() {
            var4_1 = false;
            if (this.g) {
                return this.h;
            }
            var2_2 = this.a;
            var1_3 = 0;
            do {
                var3_4 = var4_1;
                if (var1_3 >= var2_2) ** GOTO lbl11
                if (this.b[var1_3].a.isStateful()) {
                    var3_4 = true;
lbl11: // 2 sources:
                    this.h = var3_4;
                    this.g = true;
                    return var3_4;
                }
                ++var1_3;
            } while (true);
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public boolean c() {
            if (this.i != false) return this.j;
            if (this.b == null) return this.j;
            this.j = true;
            var2_1 = this.a;
            var1_2 = 0;
            do {
                if (var1_2 >= var2_1) ** GOTO lbl10
                if (this.b[var1_2].a.getConstantState() == null) {
                    this.j = false;
lbl10: // 2 sources:
                    this.i = true;
                    return this.j;
                }
                ++var1_2;
            } while (true);
        }

        public int getChangingConfigurations() {
            return this.c;
        }

        public Drawable newDrawable() {
            return new aek(this, null);
        }

        public Drawable newDrawable(Resources resources) {
            return new aek(this, resources);
        }
    }

}


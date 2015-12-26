/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Paint$FontMetricsInt
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.text.TextPaint
 *  android.text.TextUtils
 *  android.util.DisplayMetrics
 *  android.view.ActionMode
 *  android.view.ActionMode$Callback
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.ViewConfiguration
 *  android.widget.PopupWindow
 *  android.widget.PopupWindow$OnDismissListener
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Method
 *  java.util.ArrayList
 *  java.util.Arrays
 */
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.PopupWindow;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class arj
extends PopupWindow
implements PopupWindow.OnDismissListener {
    static Object a;
    static Class b;
    private static final int[][] c;
    private static Method o;
    private static Method p;
    private static Bitmap[] q;
    private a d;
    private int e = 0;
    private int[] f = new int[2];
    private Rect g;
    private b h;
    private Context i;
    private View j;
    private RectF k;
    private boolean l;
    private ArrayList<ArrayList<c>> m = new ArrayList();
    private int n = 0;

    static {
        c = new int[2][];
        arj.c[0] = new int[]{16842921};
        arj.c[1] = new int[]{16842919};
        a = null;
        o = null;
        b = null;
        p = null;
    }

    public arj(Context context) {
        super(context);
        this.i = context;
        this.setTouchable(true);
        this.setOutsideTouchable(true);
        this.setClippingEnabled(false);
        this.setWindowLayoutMode(-2, -2);
        this.setInputMethodMode(2);
        this.a(1002);
        this.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.d = new a(this.i);
        this.setContentView((View)this.d);
        this.setOnDismissListener((PopupWindow.OnDismissListener)this);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static Bitmap[] b(int var0, int var1_1, int var2_2) {
        if (arj.q == null) {
            arj.q = new Bitmap[var0];
        } else if (arj.q.length < var0) {
            arj.q = (Bitmap[])Arrays.copyOf((Object[])arj.q, (int)var0);
        }
        var3_3 = 0;
        while (var3_3 < var0) {
            var5_5 = arj.q[var3_3];
            if (var5_5 == null || var5_5.getWidth() < var1_1) ** GOTO lbl-1000
            var4_4 = var5_5;
            if (var5_5.getHeight() < var2_2) lbl-1000: // 2 sources:
            {
                if (var5_5 != null) {
                    var5_5.recycle();
                }
                var4_4 = Bitmap.createBitmap((int)var1_1, (int)var2_2, (Bitmap.Config)Bitmap.Config.ARGB_8888);
            }
            arj.q[var3_3] = var4_4;
            ++var3_3;
        }
        return arj.q;
    }

    private boolean c() {
        try {
            if (a == null) {
                Class class_ = Class.forName((String)"meizu.splitmode.FlymeSplitModeManager");
                Method method = class_.getDeclaredMethod("getInstance", new Class[]{Context.class});
                method.setAccessible(true);
                a = method.invoke((Object)this.i, new Object[0]);
                o = class_.getDeclaredMethod("isSplitMode", new Class[0]);
                o.setAccessible(true);
                return (Boolean)o.invoke(a, new Object[0]);
            }
            if (o != null) {
                boolean bl2 = (Boolean)o.invoke(a, new Object[0]);
                return bl2;
            }
        }
        catch (Exception var2_2) {
            var2_2.printStackTrace();
        }
        return false;
    }

    static /* synthetic */ int d(arj arj2) {
        int n2 = arj2.n;
        arj2.n = n2 + 1;
        return n2;
    }

    static /* synthetic */ int e(arj arj2) {
        int n2 = arj2.n;
        arj2.n = n2 - 1;
        return n2;
    }

    public ActionMode a(View object, ActionMode.Callback callback) {
        this.j = object;
        if (this.h != null) {
            this.h.finish();
        }
        if ((object = new b(callback)).a()) {
            object.invalidate();
            this.h = object;
            this.m.clear();
            this.n = 0;
            return object;
        }
        return null;
    }

    public void a() {
        if (this.j == null || this.k == null) {
            return;
        }
        this.a(this.j, this.k);
    }

    public void a(int n2) {
        try {
            if (b == null) {
                b = PopupWindow.class;
                p = b.getDeclaredMethod("setWindowLayoutType", new Class[]{Integer.TYPE});
                p.setAccessible(true);
                p.invoke((Object)this, new Object[]{n2});
                return;
            }
            if (o != null) {
                p.invoke((Object)this, new Object[]{n2});
                return;
            }
        }
        catch (Exception var2_2) {
            // empty catch block
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean a(View var1_1, RectF var2_2) {
        if (var1_1 != this.j) return false;
        if (this.h == null) {
            return false;
        }
        if (this.g == null) {
            this.g = new Rect();
        }
        var1_1 = this.g;
        this.j.getWindowVisibleDisplayFrame((Rect)var1_1);
        var5_3 = this.i.getResources().getDisplayMetrics().heightPixels;
        if (var1_1.top < 0) {
            var1_1.top = 0;
        }
        if (var1_1.bottom > var5_3) {
            var1_1.bottom = var5_3;
        }
        this.j.getLocationInWindow(this.f);
        this.k = new RectF(var2_2);
        var2_2.offset((float)this.f[0], (float)this.f[1]);
        if (!this.c() && var2_2.top < (float)var1_1.top) {
            var2_2.top = var1_1.top;
        }
        if (var2_2.bottom > (float)var1_1.bottom) {
            var2_2.bottom = var1_1.bottom;
        }
        this.d.onMeasure(0, 0);
        var7_4 = this.d.a();
        var8_5 = this.d.getMeasuredHeight();
        var3_6 = Float.MAX_VALUE;
        if ((float)(var1_1.top + var8_5) <= var2_2.top) {
            var3_6 = Math.abs((float)((float)var1_1.centerY() - (var2_2.top - (float)(var8_5 >> 1))));
            var5_3 = 48;
        } else {
            var5_3 = 0;
        }
        var6_7 = this.l == false && (float)(var1_1.bottom - var8_5) > var2_2.bottom && Math.abs((float)((float)var1_1.centerY() - (var2_2.bottom + (float)(var8_5 >> 1)))) < var3_6 ? 80 : var5_3;
        var5_3 = var6_7;
        if (var6_7 == 0) {
            if (var2_2.top - (float)var1_1.top > (float)var1_1.bottom - var2_2.bottom) {
                var5_3 = 48;
            } else {
                var5_3 = var6_7;
                if (!this.l) {
                    var5_3 = 80;
                }
            }
        }
        if (this.e == 0) ** GOTO lbl-1000
        var5_3 = this.e;
        if (var5_3 == 48) {
            this.d.a(false);
            var3_6 = var2_2.top - (float)var8_5;
        } else if (var5_3 == 80) lbl-1000: // 2 sources:
        {
            this.d.a(true);
            var3_6 = var2_2.bottom;
        } else {
            this.d.a(false);
            var3_6 = var2_2.top - (float)var8_5;
        }
        var4_8 = var3_6;
        if (!this.c()) {
            var4_8 = var3_6;
            if (var3_6 < (float)var1_1.top) {
                var4_8 = var1_1.top;
            }
        }
        var5_3 = (var6_7 = (int)var2_2.centerX()) <= var1_1.width() / 2 ? (var6_7 < var7_4 / 2 ? var6_7 : var7_4 / 2) : (var1_1.width() - var6_7 < var7_4 / 2 ? var7_4 / 2 + var7_4 / 2 - (var1_1.width() - var6_7) : var7_4 / 2);
        var5_3 = var6_7 = var6_7 - this.d.a(var5_3, var7_4) + 0;
        if (var6_7 < 0) {
            var5_3 = 0;
        }
        var6_7 = var5_3;
        if (var5_3 > var1_1.width() - var7_4) {
            var6_7 = var1_1.width() - var7_4;
        }
        if (this.isShowing()) {
            this.setWindowLayoutMode(0, 0);
            this.update(var6_7, (int)var4_8, var7_4, var8_5);
            return true;
        }
        this.setWindowLayoutMode(-2, -2);
        this.showAtLocation(this.j, 0, var6_7, (int)var4_8);
        return true;
    }

    public void onDismiss() {
        this.n = 0;
    }

    class a
    extends View {
        private int A;
        private int B;
        private int C;
        private final int D;
        private final int E;
        private float F;
        private final Runnable G;
        boolean a;
        int b;
        int c;
        private final int e;
        private final int f;
        private final int g;
        private final int h;
        private Drawable i;
        private Drawable j;
        private Drawable k;
        private Drawable l;
        private Drawable m;
        private Drawable n;
        private int o;
        private int p;
        private int q;
        private boolean r;
        private final int s;
        private TextPaint t;
        private Paint.FontMetricsInt u;
        private Paint v;
        private final int w;
        private final int x;
        private Rect y;
        private int[] z;

        public a(Context context) {
            super(context);
            this.p = -1;
            this.q = -1;
            this.r = false;
            this.y = new Rect();
            this.a = true;
            this.b = 0;
            this.c = 0;
            this.C = 0;
            this.G = new ark(this);
            arj.this = context.getResources();
            this.e = arj.this.getDimensionPixelSize(R.dimen.option_popup_text_size);
            this.h = arj.this.getDimensionPixelSize(R.dimen.option_popup_item_padding);
            this.f = arj.this.getDimensionPixelSize(R.dimen.option_popup_item_width_min);
            this.g = arj.this.getDimensionPixelSize(R.dimen.option_popup_item_width_max);
            this.D = arj.this.getDimensionPixelSize(R.dimen.option_popup_navigation_next_offset);
            this.E = arj.this.getDimensionPixelSize(R.dimen.option_popup_navigation_prev_offset);
            this.x = arj.this.getDimensionPixelSize(R.dimen.option_popup_height);
            this.i = arj.this.getDrawable(R.drawable.mz_btn_copy_left);
            this.k = arj.this.getDrawable(R.drawable.mz_btn_copy_middle);
            this.j = arj.this.getDrawable(R.drawable.mz_btn_copy_right);
            this.l = arj.this.getDrawable(R.drawable.mz_btn_copy_divider);
            this.m = arj.this.getDrawable(R.drawable.mz_btn_copy_prev_page);
            this.n = arj.this.getDrawable(R.drawable.mz_btn_copy_next_page);
            this.C = arj.this.getDimensionPixelSize(R.dimen.option_popup_navigation_menu_width);
            this.s = ViewConfiguration.get((Context)context).getScaledTouchSlop();
            arj.this = new Rect();
            this.i.getPadding((Rect)arj.this);
            this.y.left = Math.max((int)arj.this.left, (int)this.y.left);
            this.y.top = Math.max((int)arj.this.top, (int)this.y.top);
            this.y.bottom = Math.max((int)arj.this.bottom, (int)this.y.bottom);
            this.k.getPadding((Rect)arj.this);
            this.y.top = Math.max((int)arj.this.top, (int)this.y.top);
            this.y.bottom = Math.max((int)arj.this.bottom, (int)this.y.bottom);
            this.j.getPadding((Rect)arj.this);
            this.y.right = Math.max((int)arj.this.right, (int)this.y.right);
            this.y.top = Math.max((int)arj.this.top, (int)this.y.top);
            this.y.bottom = Math.max((int)arj.this.bottom, (int)this.y.bottom);
            this.w = this.i.getIntrinsicWidth() + this.k.getIntrinsicWidth() + this.j.getIntrinsicWidth();
            this.t = new TextPaint();
            this.t.setAntiAlias(true);
            this.t.setTextSize((float)this.e);
            this.t.setColor(-16777216);
            this.F = this.t.measureText("\u2025");
            this.u = this.t.getFontMetricsInt();
            this.v = new Paint();
            this.v.setAntiAlias(true);
            this.v.setColor(-3355444);
        }

        /*
         * Enabled aggressive block sorting
         */
        private int a(float f2, float f3) {
            Rect rect;
            int n2 = this.p;
            if (arj.this.n > arj.this.m.size() - 1) {
                return -1;
            }
            ArrayList arrayList = (ArrayList)arj.this.m.get(arj.this.n);
            int n3 = arrayList.size();
            if (n2 >= 0 && n2 < n3) {
                rect = ((c)arrayList.get((int)n2)).a;
                if (f2 >= (float)(rect.left - this.s) && f2 < (float)(rect.right + this.s) && f3 >= (float)(rect.top - this.s + this.y.top) && f3 < (float)(rect.bottom + this.s - this.y.bottom)) {
                    return n2;
                }
            }
            n2 = 0;
            while (n2 < n3) {
                rect = ((c)arrayList.get((int)n2)).a;
                int n4 = n2 == 0 ? rect.left + this.y.left : rect.left;
                int n5 = n3 - 1 == n2 ? rect.right - this.y.right : rect.right;
                if (f2 >= (float)n4 && f2 < (float)n5 && f3 >= (float)(rect.top + this.y.top) && f3 < (float)(rect.bottom - this.y.bottom)) {
                    return n2;
                }
                ++n2;
            }
            return -1;
        }

        private int a(MenuItem object) {
            int n2 = 0;
            if (TextUtils.isEmpty((CharSequence)object.getTitle())) {
                if ((object = object.getIcon()) != null) {
                    n2 = object.getIntrinsicWidth();
                }
                return n2;
            }
            object = object.getTitle();
            return (int)this.t.measureText((CharSequence)object, 0, object.length());
        }

        static /* synthetic */ int a(a a2) {
            return a2.q;
        }

        static /* synthetic */ int a(a a2, int n2) {
            a2.q = n2;
            return n2;
        }

        private String a(String string2, float f2) {
            int n2;
            int n3 = n2 = string2.length();
            if (n2 <= 1) {
                return string2;
            }
            while (this.t.measureText(string2, 0, n2 = n3 - 1) + this.F > f2) {
                n3 = n2;
                if (1 < n2) continue;
            }
            return string2.substring(0, n2) + "\u2025";
        }

        /*
         * Enabled aggressive block sorting
         */
        private void a(Canvas canvas, c object, int n2, int n3, int n4, int n5) {
            float f2;
            int n6 = object.e || object.d ? 1 : 0;
            if (n6 != 0) {
                Drawable drawable2 = object.e ? this.n : this.m;
                n4 = drawable2.getIntrinsicWidth();
                n6 = drawable2.getIntrinsicHeight();
                n2 = object.g + n2;
                n3 = (n3 + n5 - n6) / 2;
                drawable2.setBounds(n2, n3, n4 + n2, n6 + n3);
                drawable2.draw(canvas);
                return;
            }
            object = object.b;
            CharSequence charSequence = object.getTitle();
            if (TextUtils.isEmpty((CharSequence)charSequence)) {
                if ((object = object.getIcon()) == null) return;
                {
                    n6 = object.getIntrinsicWidth();
                    int n7 = object.getIntrinsicHeight();
                    n2 = (n2 + n4 - n6) / 2;
                    n3 = (n3 + n5 - n7) / 2;
                    object.setBounds(n2, n3, n6 + n2, n7 + n3);
                    object.draw(canvas);
                    return;
                }
            }
            charSequence = charSequence.toString();
            float f3 = n4 - n2 - this.h * 2;
            float f4 = f2 = this.t.measureText((String)charSequence);
            object = charSequence;
            if (f2 > f3) {
                object = this.a((String)charSequence, f3);
                f4 = this.t.measureText((String)object);
            }
            n6 = this.u.bottom;
            int n8 = this.u.top;
            canvas.drawText((String)object, ((float)(n2 + n4) - f4) / 2.0f, (float)(n5 + n3 - (n6 - n8)) / 2.0f - (float)this.u.top, (Paint)this.t);
        }

        /*
         * Enabled aggressive block sorting
         */
        private void a(ArrayList<c> arrayList) {
            c c2;
            int n2;
            int n3;
            if (arrayList == null) {
                return;
            }
            int n4 = this.b();
            int n5 = this.C;
            int n6 = this.y.left;
            int n7 = this.y.right;
            ArrayList arrayList2 = new ArrayList();
            n6 = n5 + n6 + n7;
            n7 = 0;
            int n8 = 0;
            n5 = 0;
            while (n5 < arrayList.size()) {
                c2 = (c)arrayList.get(n5);
                n3 = n6;
                if (n8 != 0) {
                    n3 = n6 + this.C;
                }
                if (c2.c + n3 > n4 && (n3 - this.C + c2.c >= n4 || n5 != arrayList.size() - 1)) {
                    n8 = this.C;
                    n3 = this.y.left;
                    n2 = this.y.right;
                    arrayList2.add((Object)new d(n7));
                    n6 = 0;
                    n7 = n2 + (n8 + n3);
                    n8 = n5 - 1;
                    n5 = 1;
                } else {
                    n8 = c2.c;
                    n6 = n7 + 1;
                    n7 = n3 + n8;
                    n3 = 0;
                    n8 = n5;
                    n5 = n3;
                }
                n3 = n7;
                n7 = n5;
                n5 = n8 + 1;
                n8 = n7;
                n7 = n6;
                n6 = n3;
            }
            arrayList2.add((Object)new d(n7));
            n2 = ((d)arrayList2.get((int)0)).a;
            n6 = this.y.left;
            c2 = new ArrayList();
            n3 = 0;
            n5 = 0;
            n4 = 0;
            do {
                if (n4 >= arrayList.size()) {
                    arj.this.m.add((Object)c2);
                    return;
                }
                c c3 = (c)arrayList.get(n4);
                if (n3 != 0) {
                    Object object;
                    n7 = n6;
                    if (n5 == 0) {
                        object = new Rect(n6, 0, this.C + n6, this.x);
                        object = new c((Rect)object, null, this.C);
                        object.d = true;
                        object.g = this.E;
                        c2.add(object);
                        n7 = n6 + this.C;
                    }
                    object = c3.a;
                    object.left = n7;
                    n6 = object.right = n7 + c3.c;
                    c2.add((Object)c3);
                    n7 = n5 + 1;
                } else {
                    c2.add((Object)c3);
                    n6 += c3.c;
                    n7 = n5 + 1;
                }
                c3 = c2;
                n8 = n6;
                n5 = n7;
                int n9 = n2;
                int n10 = n3;
                if (n7 == n2) {
                    c3 = c2;
                    n8 = n6;
                    n5 = n7;
                    n9 = n2;
                    n10 = n3;
                    if (arrayList2.size() > 1) {
                        c3 = c2;
                        n8 = n6;
                        n5 = n7;
                        n9 = n2;
                        n10 = n3;
                        if (n3 + 1 < arrayList2.size()) {
                            c3 = new Rect(n6, 0, this.C + n6, this.x);
                            c3 = new c((Rect)c3, null, this.C);
                            c3.e = true;
                            c3.g = this.D;
                            c2.add((Object)c3);
                            arj.this.m.add((Object)c2);
                            c3 = new ArrayList();
                            n8 = this.y.left;
                            n5 = 0;
                            n10 = n3 + 1;
                            n9 = ((d)arrayList2.get((int)n10)).a;
                        }
                    }
                }
                ++n4;
                c2 = c3;
                n6 = n8;
                n2 = n9;
                n3 = n10;
            } while (true);
        }

        private boolean a(ArrayList<c> object, int n2) {
            if (n2 > 0 && n2 < object.size()) {
                c c2 = (c)object.get(n2 - 1);
                object = (c)object.get(n2);
                if (c2.d || object.e) {
                    return true;
                }
                c2 = c2.b;
                object = object.b;
                if (c2.getGroupId() != object.getGroupId()) {
                    return true;
                }
                return false;
            }
            return false;
        }

        private int b() {
            Resources resources = this.getResources();
            if (resources == null) {
                return 0;
            }
            return resources.getDisplayMetrics().widthPixels;
        }

        private Bitmap[] c() {
            Canvas canvas = new Canvas();
            int n2 = this.getMeasuredHeight();
            int n3 = this.o;
            int n4 = this.o + this.k.getIntrinsicWidth();
            Bitmap[] arrbitmap = arj.b(2, this.A, this.x);
            for (int i2 = 0; i2 < 2; ++i2) {
                int[] arrn = c[i2];
                Bitmap bitmap = arrbitmap[i2];
                bitmap.eraseColor(0);
                canvas.setBitmap(bitmap);
                this.i.setState(arrn);
                this.i.setBounds(0, 0, n3, n2);
                this.i.draw(canvas);
                this.k.setState(arrn);
                this.k.setBounds(n3, 0, n4, n2);
                this.k.draw(canvas);
                this.j.setState(arrn);
                this.j.setBounds(n4, 0, this.A, n2);
                this.j.draw(canvas);
                if (!this.r) continue;
                if (this.z == null || this.z.length < this.A * 2) {
                    this.z = new int[this.A * 2];
                }
                for (int i3 = 0; i3 < n2 >> 1; ++i3) {
                    int n5 = n2 - i3 - 1;
                    bitmap.getPixels(this.z, 0, this.A, 0, i3, this.A, 1);
                    bitmap.getPixels(this.z, this.A, this.A, 0, n5, this.A, 1);
                    bitmap.setPixels(this.z, this.A, this.A, 0, i3, this.A, 1);
                    bitmap.setPixels(this.z, 0, this.A, 0, n5, this.A, 1);
                }
            }
            return arrbitmap;
        }

        public int a() {
            return this.A;
        }

        /*
         * Enabled aggressive block sorting
         */
        public int a(int n2, int n3) {
            int n4;
            ArrayList arrayList;
            int n5 = 0;
            int n6 = this.k.getIntrinsicWidth() / 2;
            if (arj.this.n < arj.this.m.size() && (arrayList = (ArrayList)arj.this.m.get(arj.this.n)).size() > 0) {
                n5 = ((c)arrayList.get((int)0)).c;
                n4 = ((c)arrayList.get((int)(arrayList.size() - 1))).c;
            } else {
                n4 = 0;
            }
            int n7 = n2;
            if (n2 < n5 / 2 + this.y.left) {
                n7 = n5 / 2 + this.y.left;
            }
            n2 = n7;
            if (n7 > n3 - n4 / 2 - this.y.right) {
                n2 = n3 - n4 / 2 - this.y.right;
            }
            this.o = n2 - n6;
            return n2;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public void a(boolean bl2) {
            if (this.r == bl2) return;
            boolean bl3 = true;
            if (!bl3) return;
            this.r = bl2;
            if (!arj.this.isShowing()) return;
            this.postInvalidate();
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public void onDraw(Canvas canvas) {
            if (arj.this.h == null) {
                return;
            }
            arj.this.h.getMenu();
            Bitmap[] arrbitmap = this.c();
            if (arj.this.n >= arj.this.m.size()) return;
            ArrayList arrayList = (ArrayList)arj.this.m.get(arj.this.n);
            int n2 = arrayList.size();
            new Rect();
            if (this.B != 0) {
                canvas.translate((float)this.B, 0.0f);
            }
            int n3 = 0;
            do {
                int n4;
                int n5;
                int n6;
                int n7;
                if (n3 >= n2) {
                    if (this.B == 0) return;
                    canvas.translate((float)(- this.B), 0.0f);
                    return;
                }
                c c2 = (c)arrayList.get(n3);
                Rect rect = c2.a;
                if (n3 == 0) {
                    rect.left = 0;
                }
                if (n3 == n2 - 1) {
                    rect.right = this.A;
                }
                int n8 = this.p == n3 && this.q == n3 ? 1 : 0;
                n8 = n8 != 0 ? 1 : 0;
                canvas.drawBitmap(arrbitmap[n8], rect, rect, this.v);
                if (this.a(arrayList, n3)) {
                    n6 = this.l.getIntrinsicWidth();
                    n7 = this.l.getIntrinsicHeight();
                    n4 = rect.left - n6 / 2;
                    n5 = (rect.height() - this.y.top - this.y.bottom - n7) / 2;
                    n8 = this.r ? this.y.bottom : this.y.top;
                    this.l.setBounds(n4, n8, n6 + n4, n7 + (n8 += n5));
                    this.l.draw(canvas);
                }
                n8 = this.r ? this.y.bottom : this.y.top;
                n5 = this.getHeight();
                n6 = this.r ? this.y.top : this.y.bottom;
                if (n3 == 0) {
                    n7 = rect.left;
                    n7 = this.y.left + n7;
                } else {
                    n7 = rect.left;
                }
                n4 = n3 == n2 - 1 ? rect.right - this.y.right : rect.right;
                this.a(canvas, c2, n7, n8, n4, n5 - n6);
                ++n3;
            } while (true);
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public void onMeasure(int var1_1, int var2_2) {
            if (arj.a(arj.this) == null) {
                this.setMeasuredDimension(0, 0);
                return;
            }
            var4_3 = this.y.left;
            var1_1 = this.y.left;
            var3_4 = this.y.right + var1_1;
            var8_5 = arj.a(arj.this).getMenu();
            var7_6 = var8_5.size();
            if (arj.c(arj.this).size() != 0) ** GOTO lbl21
            var9_7 = new ArrayList();
            var5_8 = 0;
            do {
                if (var5_8 >= var7_6) ** GOTO lbl19
                var6_9 = this.a(var8_5.getItem(var5_8)) + this.h * 2;
                var1_1 = this.b != 0 ? this.b : this.g;
                var2_2 = this.c != 0 ? this.c : this.f;
                if (var6_9 >= var2_2) ** GOTO lbl33
                ** GOTO lbl34
lbl19: // 1 sources:
                if (var9_7.size() > 0) {
                    this.a(var9_7);
                }
lbl21: // 4 sources:
                var1_1 = var3_4;
                if (arj.c(arj.this).size() > 0) {
                    var1_1 = var3_4;
                    if (arj.b(arj.this) < arj.c(arj.this).size()) {
                        var8_5 = ((ArrayList)arj.c(arj.this).get(arj.b(arj.this))).iterator();
                        var1_1 = var3_4;
                        while (var8_5.hasNext()) {
                            var1_1 = ((c)var8_5.next()).c + var1_1;
                        }
                    }
                }
                this.A = Math.max((int)var1_1, (int)this.w);
                this.setMeasuredDimension(this.A, this.x);
                return;
lbl33: // 1 sources:
                var2_2 = var6_9;
lbl34: // 2 sources:
                if (var2_2 <= var1_1) {
                    var1_1 = var2_2;
                }
                var9_7.add((Object)new c(new Rect(var4_3, 0, var4_3 + var1_1, this.x), var8_5.getItem(var5_8), var1_1));
                ++var5_8;
                var4_3 += var1_1;
            } while (true);
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (!this.a) return true;
            switch (motionEvent.getActionMasked()) {
                default: {
                    return true;
                }
                case 0: {
                    this.q = this.p = this.a(motionEvent.getX(), motionEvent.getY());
                    if (this.p < 0) return true;
                    {
                        this.invalidate();
                        return true;
                    }
                }
                case 2: {
                    int n2 = this.a(motionEvent.getX(), motionEvent.getY());
                    if (this.p == n2) {
                        return true;
                    }
                    if (this.p >= 0 || n2 >= 0) {
                        this.invalidate();
                    }
                    this.q = this.p = n2;
                    return true;
                }
                case 1: {
                    if (this.p >= 0) {
                        this.post(this.G);
                        this.invalidate();
                    }
                    this.p = -1;
                    return true;
                }
                case 3: 
            }
            this.p = -1;
            return true;
        }
    }

    class b
    extends ActionMode
    implements MenuBuilder.Callback {
        private ActionMode.Callback b;
        private MenuBuilder c;

        public b(ActionMode.Callback callback) {
            this.c = new MenuBuilder(arj.this.i);
            this.c.setCallback(this);
            this.b = callback;
        }

        static /* synthetic */ MenuBuilder a(b b2) {
            return b2.c;
        }

        public boolean a() {
            this.c.stopDispatchingItemsChanged();
            try {
                boolean bl2 = this.b.onCreateActionMode((ActionMode)this, (Menu)this.c);
                return bl2;
            }
            finally {
                this.c.startDispatchingItemsChanged();
            }
        }

        public void finish() {
            if (arj.this.h != this) {
                return;
            }
            arj.this.dismiss();
            this.b.onDestroyActionMode((ActionMode)this);
            this.b = null;
            arj.this.h = null;
        }

        public View getCustomView() {
            return null;
        }

        public Menu getMenu() {
            return this.c;
        }

        public MenuInflater getMenuInflater() {
            return new MenuInflater(arj.this.i);
        }

        public CharSequence getSubtitle() {
            return null;
        }

        public CharSequence getTitle() {
            return null;
        }

        public void invalidate() {
            this.c.stopDispatchingItemsChanged();
            try {
                this.b.onPrepareActionMode((ActionMode)this, (Menu)this.c);
                return;
            }
            finally {
                this.c.startDispatchingItemsChanged();
            }
        }

        @Override
        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            if (this.b != null) {
                return this.b.onActionItemClicked((ActionMode)this, menuItem);
            }
            return false;
        }

        @Override
        public void onMenuModeChange(MenuBuilder menuBuilder) {
        }

        public void setCustomView(View view) {
        }

        public void setSubtitle(int n2) {
        }

        public void setSubtitle(CharSequence charSequence) {
        }

        public void setTitle(int n2) {
        }

        public void setTitle(CharSequence charSequence) {
        }
    }

    class c {
        public Rect a;
        public MenuItem b;
        public int c;
        public boolean d;
        public boolean e;
        public int f;
        public int g;

        public c(Rect rect, MenuItem menuItem, int n2) {
            this.d = false;
            this.e = false;
            this.f = 0;
            this.a = rect;
            this.b = menuItem;
            this.c = n2;
        }
    }

    class d {
        int a;

        d(int n2) {
            this.a = n2;
        }
    }

}


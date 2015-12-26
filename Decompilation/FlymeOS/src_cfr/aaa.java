/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.LayerDrawable
 *  android.text.TextPaint
 *  android.text.TextUtils
 *  android.util.DisplayMetrics
 *  android.view.LayoutInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ListView
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 */
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.MultiChoiceView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.meizu.common.util.ContactHeaderUtils;
import com.meizu.common.util.ResourceUtils;
import flyme.support.v7.widget.TwoStateTextView;

public class aaa {
    public static int a;
    public static int b;
    public static int c;
    public static int d;
    public static int e;
    private static Drawable f;
    private static Drawable g;
    private static Drawable h;
    private static Drawable i;
    private static Drawable j;
    private static Drawable k;
    private static Drawable l;
    private static int m;

    static {
        c = 0;
        d = 0;
        e = 0;
        m = 96;
    }

    public static int a() {
        return c;
    }

    public static final int a(int n2) {
        switch (n2) {
            default: {
                return 2130771969;
            }
            case 1: {
                return 2130771970;
            }
            case 2: 
        }
        return 2130771971;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Bitmap a(Context context, gq gq2, int n2, int n3) {
        int n4 = gq2.size();
        float f2 = context.getResources().getDisplayMetrics().density;
        int n5 = (int)((float)n2 / f2);
        n3 = (int)((float)n3 / f2);
        if (n4 == 0) {
            return ContactHeaderUtils.createContactHeaderDrawable(context.getResources(), n5, n3, new Object[]{""}, new Object[]{""}, -1);
        }
        Object[] arrobject = new Object[n4];
        Object[] arrobject2 = new Object[n4];
        n2 = 0;
        while (n2 < n4) {
            gm gm2 = (gm)gq2.get(n2);
            Drawable drawable2 = gm2.a(context, null);
            if (drawable2 != null) {
                arrobject[n2] = drawable2;
                arrobject2[n2] = null;
            } else if (gm2.k() && !TextUtils.isEmpty((CharSequence)gm2.g())) {
                arrobject[n2] = gm2.g();
                arrobject2[n2] = gm2.g();
            } else {
                arrobject[n2] = "";
                arrobject2[n2] = gm2.d();
            }
            ++n2;
        }
        return ContactHeaderUtils.createContactHeaderDrawable(context.getResources(), n5, n3, arrobject, arrobject2, -1);
    }

    public static View a(Context context, String string2) {
        if (context == null || TextUtils.isEmpty((CharSequence)string2)) {
            return null;
        }
        context = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968625, null, false);
        ((TextView)context.findViewById(2131886173)).setText((CharSequence)string2);
        return context;
    }

    public static String a(Context context, int n2) {
        if (n2 > 0) {
            return context.getResources().getString(2131493761, new Object[]{n2});
        }
        return context.getResources().getString(2131493743);
    }

    public static void a(Activity activity) {
    }

    public static void a(Context context) {
        context = context.getResources();
        c = context.getDimensionPixelSize(2131559871);
        d = c + context.getDimensionPixelSize(2131559058);
        e = context.getDimensionPixelSize(2131558825) + context.getDimensionPixelSize(2131559870);
        m = context.getDimensionPixelSize(2131559051);
        a = context.getDimensionPixelSize(2131559329);
        b = context.getDimensionPixelSize(2131559326);
    }

    public static void a(Context object, MultiChoiceView multiChoiceView, int n2, int n3) {
        multiChoiceView.setTitle(aaa.a((Context)object, n2));
        object = (TwoStateTextView)multiChoiceView.getSelectAllView();
        if (n3 > 0) {
            object.setEnabled(true);
            object.setTotalCount(n3);
            object.setSelectedCount(n2);
            return;
        }
        object.setEnabled(false);
    }

    public static void a(Context context, View view, boolean bl2) {
        if (bl2) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), context.getResources().getDimensionPixelOffset(2131558606));
            return;
        }
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), 0);
    }

    public static void a(Context context, ListView listView) {
        aaa.a(context, listView, true);
    }

    public static void a(Context context, ListView listView, boolean bl2) {
        int n2 = context.getResources().getDimensionPixelSize(2131558863);
        listView.setPadding(listView.getPaddingLeft(), listView.getPaddingTop(), listView.getPaddingRight(), n2);
        context = listView.getEmptyView();
        if (context != null && bl2) {
            context.setPadding(context.getPaddingLeft(), context.getPaddingTop(), context.getPaddingRight(), n2);
        }
    }

    public static void a(ActionBar actionBar, Context context) {
        actionBar.setBackgroundDrawable(aaa.b(context));
        actionBar.setSplitBackgroundDrawable(aaa.d(context));
    }

    public static void a(MenuItem menuItem, boolean bl2) {
        menuItem.setEnabled(bl2);
    }

    public static void a(ListView listView, boolean bl2) {
        if (bl2) {
            int n2 = aaa.a();
            listView.setPadding(listView.getPaddingLeft(), n2, listView.getPaddingRight(), listView.getPaddingBottom());
        }
        listView.measure(listView.getMeasuredWidthAndState(), listView.getMeasuredHeightAndState());
    }

    public static void a(TextView textView) {
        textView.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        textView.getPaint().setStrokeWidth(0.6f);
    }

    public static int b() {
        return e;
    }

    public static Drawable b(Context context) {
        if (i == null) {
            i = aaa.c(context);
        }
        return i;
    }

    public static void b(Activity activity) {
    }

    public static void b(ActionBar actionBar, Context context) {
        actionBar.setBackgroundDrawable(aaa.f(context));
        actionBar.setSplitBackgroundDrawable(aaa.g(context));
    }

    public static Drawable c(Context context) {
        return new LayerDrawable(new Drawable[]{ResourceUtils.createBlurDrawable(context.getResources().getDrawable(2130838604), 0.5f, 0), aaa.i(context)});
    }

    public static void c() {
        f = null;
        g = null;
        h = null;
        l = null;
        k = null;
    }

    public static void c(Activity activity) {
        activity.overridePendingTransition(aau.f("mz_new_app_to_next_open_enter"), aau.f("mz_new_app_to_next_open_exit"));
    }

    public static void c(ActionBar actionBar, Context context) {
        actionBar.setBackgroundDrawable(aaa.c(context));
        actionBar.setSplitBackgroundDrawable(aaa.e(context));
    }

    public static Drawable d(Context context) {
        if (j == null) {
            j = aaa.e(context);
        }
        return j;
    }

    public static void d(Activity activity) {
        activity.overridePendingTransition(aau.f("mz_edit_new_open_enter"), aau.f("mz_edit_new_open_exit"));
    }

    public static Drawable e(Context context) {
        return new LayerDrawable(new Drawable[]{ResourceUtils.createBlurDrawable(context.getResources().getDrawable(2130838467), 0.5f, 0), aaa.j(context)});
    }

    public static void e(Activity activity) {
        activity.overridePendingTransition(aau.f("mz_edit_new_close_enter"), aau.f("mz_edit_new_close_exit"));
    }

    private static Drawable f(Context context) {
        if (f == null) {
            f = context.getResources().getDrawable(2130838604);
            f = ResourceUtils.createBlurDrawable(f, 0.5f, 0);
            f = new LayerDrawable(new Drawable[]{f, aaa.h(context)});
        }
        return f;
    }

    public static void f(Activity activity) {
        activity.overridePendingTransition(2131034145, 2131034146);
    }

    private static Drawable g(Context context) {
        if (h == null) {
            h = context.getResources().getDrawable(2130838467);
            h = ResourceUtils.createBlurDrawable(h, 0.5f, 0);
            h = new LayerDrawable(new Drawable[]{h, aaa.j(context)});
        }
        return h;
    }

    public static void g(Activity activity) {
        activity.overridePendingTransition(2131034143, 2131034144);
    }

    private static Drawable h(Context context) {
        if (l == null) {
            l = context.getResources().getDrawable(2130838686);
        }
        return l;
    }

    private static Drawable i(Context context) {
        if (k == null) {
            k = context.getResources().getDrawable(2130838689);
        }
        return k;
    }

    private static Drawable j(Context context) {
        return context.getResources().getDrawable(2130838677);
    }
}


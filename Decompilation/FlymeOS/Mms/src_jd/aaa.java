import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint.Style;
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
import android.widget.ListView;
import android.widget.TextView;
import com.meizu.common.util.ContactHeaderUtils;
import com.meizu.common.util.ResourceUtils;
import flyme.support.v7.widget.TwoStateTextView;

public class aaa
{
  public static int a;
  public static int b;
  public static int c = 0;
  public static int d = 0;
  public static int e = 0;
  private static Drawable f;
  private static Drawable g;
  private static Drawable h;
  private static Drawable i;
  private static Drawable j;
  private static Drawable k;
  private static Drawable l;
  private static int m = 96;
  
  public static int a()
  {
    return c;
  }
  
  public static final int a(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
    default: 
      return 2130771969;
    case 1: 
      return 2130771970;
    }
    return 2130771971;
  }
  
  public static Bitmap a(Context paramContext, gq paramgq, int paramInt1, int paramInt2)
  {
    int n = paramgq.size();
    float f1 = getResourcesgetDisplayMetricsdensity;
    int i1 = (int)(paramInt1 / f1);
    paramInt2 = (int)(paramInt2 / f1);
    if (n == 0) {
      return ContactHeaderUtils.createContactHeaderDrawable(paramContext.getResources(), i1, paramInt2, new Object[] { "" }, new Object[] { "" }, -1);
    }
    Object[] arrayOfObject1 = new Object[n];
    Object[] arrayOfObject2 = new Object[n];
    paramInt1 = 0;
    if (paramInt1 < n)
    {
      gm localgm = (gm)paramgq.get(paramInt1);
      Drawable localDrawable = localgm.a(paramContext, null);
      if (localDrawable != null)
      {
        arrayOfObject1[paramInt1] = localDrawable;
        arrayOfObject2[paramInt1] = null;
      }
      for (;;)
      {
        paramInt1 += 1;
        break;
        if ((localgm.k()) && (!TextUtils.isEmpty(localgm.g())))
        {
          arrayOfObject1[paramInt1] = localgm.g();
          arrayOfObject2[paramInt1] = localgm.g();
        }
        else
        {
          arrayOfObject1[paramInt1] = "";
          arrayOfObject2[paramInt1] = localgm.d();
        }
      }
    }
    return ContactHeaderUtils.createContactHeaderDrawable(paramContext.getResources(), i1, paramInt2, arrayOfObject1, arrayOfObject2, -1);
  }
  
  public static View a(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      return null;
    }
    paramContext = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2130968625, null, false);
    ((TextView)paramContext.findViewById(2131886173)).setText(paramString);
    return paramContext;
  }
  
  public static String a(Context paramContext, int paramInt)
  {
    if (paramInt > 0) {
      return paramContext.getResources().getString(2131493761, new Object[] { Integer.valueOf(paramInt) });
    }
    return paramContext.getResources().getString(2131493743);
  }
  
  public static void a(Activity paramActivity) {}
  
  public static void a(Context paramContext)
  {
    paramContext = paramContext.getResources();
    c = paramContext.getDimensionPixelSize(2131559871);
    d = c + paramContext.getDimensionPixelSize(2131559058);
    e = paramContext.getDimensionPixelSize(2131558825) + paramContext.getDimensionPixelSize(2131559870);
    m = paramContext.getDimensionPixelSize(2131559051);
    a = paramContext.getDimensionPixelSize(2131559329);
    b = paramContext.getDimensionPixelSize(2131559326);
  }
  
  public static void a(Context paramContext, MultiChoiceView paramMultiChoiceView, int paramInt1, int paramInt2)
  {
    paramMultiChoiceView.setTitle(a(paramContext, paramInt1));
    paramContext = (TwoStateTextView)paramMultiChoiceView.getSelectAllView();
    if (paramInt2 > 0)
    {
      paramContext.setEnabled(true);
      paramContext.setTotalCount(paramInt2);
      paramContext.setSelectedCount(paramInt1);
      return;
    }
    paramContext.setEnabled(false);
  }
  
  public static void a(Context paramContext, View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramView.setPadding(paramView.getPaddingLeft(), paramView.getPaddingTop(), paramView.getPaddingRight(), paramContext.getResources().getDimensionPixelOffset(2131558606));
      return;
    }
    paramView.setPadding(paramView.getPaddingLeft(), paramView.getPaddingTop(), paramView.getPaddingRight(), 0);
  }
  
  public static void a(Context paramContext, ListView paramListView)
  {
    a(paramContext, paramListView, true);
  }
  
  public static void a(Context paramContext, ListView paramListView, boolean paramBoolean)
  {
    int n = paramContext.getResources().getDimensionPixelSize(2131558863);
    paramListView.setPadding(paramListView.getPaddingLeft(), paramListView.getPaddingTop(), paramListView.getPaddingRight(), n);
    paramContext = paramListView.getEmptyView();
    if ((paramContext != null) && (paramBoolean)) {
      paramContext.setPadding(paramContext.getPaddingLeft(), paramContext.getPaddingTop(), paramContext.getPaddingRight(), n);
    }
  }
  
  public static void a(ActionBar paramActionBar, Context paramContext)
  {
    paramActionBar.setBackgroundDrawable(b(paramContext));
    paramActionBar.setSplitBackgroundDrawable(d(paramContext));
  }
  
  public static void a(MenuItem paramMenuItem, boolean paramBoolean)
  {
    paramMenuItem.setEnabled(paramBoolean);
  }
  
  public static void a(ListView paramListView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      int n = a();
      paramListView.setPadding(paramListView.getPaddingLeft(), n, paramListView.getPaddingRight(), paramListView.getPaddingBottom());
    }
    paramListView.measure(paramListView.getMeasuredWidthAndState(), paramListView.getMeasuredHeightAndState());
  }
  
  public static void a(TextView paramTextView)
  {
    paramTextView.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
    paramTextView.getPaint().setStrokeWidth(0.6F);
  }
  
  public static int b()
  {
    return e;
  }
  
  public static Drawable b(Context paramContext)
  {
    if (i == null) {
      i = c(paramContext);
    }
    return i;
  }
  
  public static void b(Activity paramActivity) {}
  
  public static void b(ActionBar paramActionBar, Context paramContext)
  {
    paramActionBar.setBackgroundDrawable(f(paramContext));
    paramActionBar.setSplitBackgroundDrawable(g(paramContext));
  }
  
  public static Drawable c(Context paramContext)
  {
    return new LayerDrawable(new Drawable[] { ResourceUtils.createBlurDrawable(paramContext.getResources().getDrawable(2130838604), 0.5F, 0), i(paramContext) });
  }
  
  public static void c()
  {
    f = null;
    g = null;
    h = null;
    l = null;
    k = null;
  }
  
  public static void c(Activity paramActivity)
  {
    paramActivity.overridePendingTransition(aau.f("mz_new_app_to_next_open_enter"), aau.f("mz_new_app_to_next_open_exit"));
  }
  
  public static void c(ActionBar paramActionBar, Context paramContext)
  {
    paramActionBar.setBackgroundDrawable(c(paramContext));
    paramActionBar.setSplitBackgroundDrawable(e(paramContext));
  }
  
  public static Drawable d(Context paramContext)
  {
    if (j == null) {
      j = e(paramContext);
    }
    return j;
  }
  
  public static void d(Activity paramActivity)
  {
    paramActivity.overridePendingTransition(aau.f("mz_edit_new_open_enter"), aau.f("mz_edit_new_open_exit"));
  }
  
  public static Drawable e(Context paramContext)
  {
    return new LayerDrawable(new Drawable[] { ResourceUtils.createBlurDrawable(paramContext.getResources().getDrawable(2130838467), 0.5F, 0), j(paramContext) });
  }
  
  public static void e(Activity paramActivity)
  {
    paramActivity.overridePendingTransition(aau.f("mz_edit_new_close_enter"), aau.f("mz_edit_new_close_exit"));
  }
  
  private static Drawable f(Context paramContext)
  {
    if (f == null)
    {
      f = paramContext.getResources().getDrawable(2130838604);
      f = ResourceUtils.createBlurDrawable(f, 0.5F, 0);
      f = new LayerDrawable(new Drawable[] { f, h(paramContext) });
    }
    return f;
  }
  
  public static void f(Activity paramActivity)
  {
    paramActivity.overridePendingTransition(2131034145, 2131034146);
  }
  
  private static Drawable g(Context paramContext)
  {
    if (h == null)
    {
      h = paramContext.getResources().getDrawable(2130838467);
      h = ResourceUtils.createBlurDrawable(h, 0.5F, 0);
      h = new LayerDrawable(new Drawable[] { h, j(paramContext) });
    }
    return h;
  }
  
  public static void g(Activity paramActivity)
  {
    paramActivity.overridePendingTransition(2131034143, 2131034144);
  }
  
  private static Drawable h(Context paramContext)
  {
    if (l == null) {
      l = paramContext.getResources().getDrawable(2130838686);
    }
    return l;
  }
  
  private static Drawable i(Context paramContext)
  {
    if (k == null) {
      k = paramContext.getResources().getDrawable(2130838689);
    }
    return k;
  }
  
  private static Drawable j(Context paramContext)
  {
    return paramContext.getResources().getDrawable(2130838677);
  }
}

/* Location:
 * Qualified Name:     aaa
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
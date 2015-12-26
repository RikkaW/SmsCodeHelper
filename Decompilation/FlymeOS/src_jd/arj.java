import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.drawable;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuBuilder.Callback;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class arj
  extends PopupWindow
  implements PopupWindow.OnDismissListener
{
  static Object a;
  static Class b = null;
  private static final int[][] c = new int[2][];
  private static Method o;
  private static Method p = null;
  private static Bitmap[] q;
  private arj.a d;
  private int e = 0;
  private int[] f = new int[2];
  private Rect g;
  private arj.b h;
  private Context i;
  private View j;
  private RectF k;
  private boolean l;
  private ArrayList<ArrayList<arj.c>> m = new ArrayList();
  private int n = 0;
  
  static
  {
    c[0] = { 16842921 };
    c[1] = { 16842919 };
    a = null;
    o = null;
  }
  
  public arj(Context paramContext)
  {
    super(paramContext);
    i = paramContext;
    setTouchable(true);
    setOutsideTouchable(true);
    setClippingEnabled(false);
    setWindowLayoutMode(-2, -2);
    setInputMethodMode(2);
    a(1002);
    setBackgroundDrawable(new ColorDrawable(0));
    d = new arj.a(i);
    setContentView(d);
    setOnDismissListener(this);
  }
  
  private static Bitmap[] b(int paramInt1, int paramInt2, int paramInt3)
  {
    if (q == null) {
      q = new Bitmap[paramInt1];
    }
    for (;;)
    {
      int i1 = 0;
      while (i1 < paramInt1)
      {
        Bitmap localBitmap2 = q[i1];
        Bitmap localBitmap1;
        if ((localBitmap2 != null) && (localBitmap2.getWidth() >= paramInt2))
        {
          localBitmap1 = localBitmap2;
          if (localBitmap2.getHeight() >= paramInt3) {}
        }
        else
        {
          if (localBitmap2 != null) {
            localBitmap2.recycle();
          }
          localBitmap1 = Bitmap.createBitmap(paramInt2, paramInt3, Bitmap.Config.ARGB_8888);
        }
        q[i1] = localBitmap1;
        i1 += 1;
      }
      if (q.length < paramInt1) {
        q = (Bitmap[])Arrays.copyOf(q, paramInt1);
      }
    }
    return q;
  }
  
  private boolean c()
  {
    try
    {
      if (a == null)
      {
        Class localClass = Class.forName("meizu.splitmode.FlymeSplitModeManager");
        Method localMethod = localClass.getDeclaredMethod("getInstance", new Class[] { Context.class });
        localMethod.setAccessible(true);
        a = localMethod.invoke(i, new Object[0]);
        o = localClass.getDeclaredMethod("isSplitMode", new Class[0]);
        o.setAccessible(true);
        return ((Boolean)o.invoke(a, new Object[0])).booleanValue();
      }
      if (o != null)
      {
        boolean bool = ((Boolean)o.invoke(a, new Object[0])).booleanValue();
        return bool;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public ActionMode a(View paramView, ActionMode.Callback paramCallback)
  {
    j = paramView;
    if (h != null) {
      h.finish();
    }
    paramView = new arj.b(paramCallback);
    if (paramView.a())
    {
      paramView.invalidate();
      h = paramView;
      m.clear();
      n = 0;
      return paramView;
    }
    return null;
  }
  
  public void a()
  {
    if ((j == null) || (k == null)) {
      return;
    }
    a(j, k);
  }
  
  public void a(int paramInt)
  {
    try
    {
      if (b == null)
      {
        b = PopupWindow.class;
        p = b.getDeclaredMethod("setWindowLayoutType", new Class[] { Integer.TYPE });
        p.setAccessible(true);
        p.invoke(this, new Object[] { Integer.valueOf(paramInt) });
        return;
      }
      if (o != null)
      {
        p.invoke(this, new Object[] { Integer.valueOf(paramInt) });
        return;
      }
    }
    catch (Exception localException) {}
  }
  
  public boolean a(View paramView, RectF paramRectF)
  {
    if ((paramView != j) || (h == null)) {
      return false;
    }
    if (g == null) {
      g = new Rect();
    }
    paramView = g;
    j.getWindowVisibleDisplayFrame(paramView);
    int i1 = i.getResources().getDisplayMetrics().heightPixels;
    if (top < 0) {
      top = 0;
    }
    if (bottom > i1) {
      bottom = i1;
    }
    j.getLocationInWindow(f);
    k = new RectF(paramRectF);
    paramRectF.offset(f[0], f[1]);
    if ((!c()) && (top < top)) {
      top = top;
    }
    if (bottom > bottom) {
      bottom = bottom;
    }
    d.onMeasure(0, 0);
    int i3 = d.a();
    int i4 = d.getMeasuredHeight();
    float f1 = Float.MAX_VALUE;
    if (top + i4 <= top) {
      f1 = Math.abs(paramView.centerY() - (top - (i4 >> 1)));
    }
    for (i1 = 48;; i1 = 0)
    {
      if ((!l) && (bottom - i4 > bottom) && (Math.abs(paramView.centerY() - (bottom + (i4 >> 1))) < f1)) {}
      for (int i2 = 80;; i2 = i1)
      {
        i1 = i2;
        label378:
        float f2;
        if (i2 == 0)
        {
          if (top - top > bottom - bottom) {
            i1 = 48;
          }
        }
        else
        {
          if (e != 0) {
            i1 = e;
          }
          if (i1 != 48) {
            break label540;
          }
          d.a(false);
          f1 = top - i4;
          f2 = f1;
          if (!c())
          {
            f2 = f1;
            if (f1 < top) {
              f2 = top;
            }
          }
          i2 = (int)paramRectF.centerX();
          if (i2 > paramView.width() / 2) {
            break label592;
          }
          if (i2 >= i3 / 2) {
            break label583;
          }
          i1 = i2;
          label439:
          i2 = i2 - d.a(i1, i3) + 0;
          i1 = i2;
          if (i2 < 0) {
            i1 = 0;
          }
          i2 = i1;
          if (i1 > paramView.width() - i3) {
            i2 = paramView.width() - i3;
          }
          if (!isShowing()) {
            break label637;
          }
          setWindowLayoutMode(0, 0);
          update(i2, (int)f2, i3, i4);
        }
        for (;;)
        {
          return true;
          i1 = i2;
          if (l) {
            break;
          }
          i1 = 80;
          break;
          label540:
          if (i1 == 80)
          {
            d.a(true);
            f1 = bottom;
            break label378;
          }
          d.a(false);
          f1 = top - i4;
          break label378;
          label583:
          i1 = i3 / 2;
          break label439;
          label592:
          if (paramView.width() - i2 < i3 / 2)
          {
            i1 = i3 / 2 + i3 / 2 - (paramView.width() - i2);
            break label439;
          }
          i1 = i3 / 2;
          break label439;
          label637:
          setWindowLayoutMode(-2, -2);
          showAtLocation(j, 0, i2, (int)f2);
        }
      }
    }
  }
  
  public void onDismiss()
  {
    n = 0;
  }
  
  class a
    extends View
  {
    private int A;
    private int B;
    private int C = 0;
    private final int D;
    private final int E;
    private float F;
    private final Runnable G = new ark(this);
    boolean a = true;
    int b = 0;
    int c = 0;
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
    private int p = -1;
    private int q = -1;
    private boolean r = false;
    private final int s;
    private TextPaint t;
    private Paint.FontMetricsInt u;
    private Paint v;
    private final int w;
    private final int x;
    private Rect y = new Rect();
    private int[] z;
    
    public a(Context paramContext)
    {
      super();
      this$1 = paramContext.getResources();
      e = getDimensionPixelSize(R.dimen.option_popup_text_size);
      h = getDimensionPixelSize(R.dimen.option_popup_item_padding);
      f = getDimensionPixelSize(R.dimen.option_popup_item_width_min);
      g = getDimensionPixelSize(R.dimen.option_popup_item_width_max);
      D = getDimensionPixelSize(R.dimen.option_popup_navigation_next_offset);
      E = getDimensionPixelSize(R.dimen.option_popup_navigation_prev_offset);
      x = getDimensionPixelSize(R.dimen.option_popup_height);
      i = getDrawable(R.drawable.mz_btn_copy_left);
      k = getDrawable(R.drawable.mz_btn_copy_middle);
      j = getDrawable(R.drawable.mz_btn_copy_right);
      l = getDrawable(R.drawable.mz_btn_copy_divider);
      m = getDrawable(R.drawable.mz_btn_copy_prev_page);
      n = getDrawable(R.drawable.mz_btn_copy_next_page);
      C = getDimensionPixelSize(R.dimen.option_popup_navigation_menu_width);
      s = ViewConfiguration.get(paramContext).getScaledTouchSlop();
      this$1 = new Rect();
      i.getPadding(arj.this);
      y.left = Math.max(left, y.left);
      y.top = Math.max(top, y.top);
      y.bottom = Math.max(bottom, y.bottom);
      k.getPadding(arj.this);
      y.top = Math.max(top, y.top);
      y.bottom = Math.max(bottom, y.bottom);
      j.getPadding(arj.this);
      y.right = Math.max(right, y.right);
      y.top = Math.max(top, y.top);
      y.bottom = Math.max(bottom, y.bottom);
      w = (i.getIntrinsicWidth() + k.getIntrinsicWidth() + j.getIntrinsicWidth());
      t = new TextPaint();
      t.setAntiAlias(true);
      t.setTextSize(e);
      t.setColor(-16777216);
      F = t.measureText("‥");
      u = t.getFontMetricsInt();
      v = new Paint();
      v.setAntiAlias(true);
      v.setColor(-3355444);
    }
    
    private int a(float paramFloat1, float paramFloat2)
    {
      int i1 = p;
      if (arj.b(arj.this) > arj.c(arj.this).size() - 1) {
        return -1;
      }
      ArrayList localArrayList = (ArrayList)arj.c(arj.this).get(arj.b(arj.this));
      int i4 = localArrayList.size();
      Rect localRect;
      if ((i1 >= 0) && (i1 < i4))
      {
        localRect = geta;
        if ((paramFloat1 >= left - s) && (paramFloat1 < right + s) && (paramFloat2 >= top - s + y.top) && (paramFloat2 < bottom + s - y.bottom)) {
          return i1;
        }
      }
      i1 = 0;
      while (i1 < i4)
      {
        localRect = geta;
        int i2;
        if (i1 == 0)
        {
          i2 = left + y.left;
          if (i4 - 1 != i1) {
            break label294;
          }
        }
        label294:
        for (int i3 = right - y.right;; i3 = right)
        {
          if ((paramFloat1 < i2) || (paramFloat1 >= i3) || (paramFloat2 < top + y.top) || (paramFloat2 >= bottom - y.bottom)) {
            break label304;
          }
          return i1;
          i2 = left;
          break;
        }
        label304:
        i1 += 1;
      }
      return -1;
    }
    
    private int a(MenuItem paramMenuItem)
    {
      int i1 = 0;
      if (TextUtils.isEmpty(paramMenuItem.getTitle()))
      {
        paramMenuItem = paramMenuItem.getIcon();
        if (paramMenuItem != null) {
          i1 = paramMenuItem.getIntrinsicWidth();
        }
        return i1;
      }
      paramMenuItem = paramMenuItem.getTitle();
      return (int)t.measureText(paramMenuItem, 0, paramMenuItem.length());
    }
    
    private String a(String paramString, float paramFloat)
    {
      int i2 = paramString.length();
      int i1 = i2;
      if (i2 <= 1) {
        return paramString;
      }
      do
      {
        i2 = i1 - 1;
        if (t.measureText(paramString, 0, i2) + F <= paramFloat) {
          break;
        }
        i1 = i2;
      } while (1 < i2);
      return paramString.substring(0, i2) + "‥";
    }
    
    private void a(Canvas paramCanvas, arj.c paramc, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      if ((e) || (d))
      {
        i1 = 1;
        if (i1 == 0) {
          break label107;
        }
        if (!e) {
          break label98;
        }
        localObject = n;
        label35:
        paramInt3 = ((Drawable)localObject).getIntrinsicWidth();
        i1 = ((Drawable)localObject).getIntrinsicHeight();
        paramInt1 = g + paramInt1;
        paramInt2 = (paramInt2 + paramInt4 - i1) / 2;
        ((Drawable)localObject).setBounds(paramInt1, paramInt2, paramInt3 + paramInt1, i1 + paramInt2);
        ((Drawable)localObject).draw(paramCanvas);
      }
      label98:
      label107:
      do
      {
        return;
        i1 = 0;
        break;
        localObject = m;
        break label35;
        paramc = b;
        localObject = paramc.getTitle();
        if (!TextUtils.isEmpty((CharSequence)localObject)) {
          break label195;
        }
        paramc = paramc.getIcon();
      } while (paramc == null);
      int i1 = paramc.getIntrinsicWidth();
      int i2 = paramc.getIntrinsicHeight();
      paramInt1 = (paramInt1 + paramInt3 - i1) / 2;
      paramInt2 = (paramInt2 + paramInt4 - i2) / 2;
      paramc.setBounds(paramInt1, paramInt2, i1 + paramInt1, i2 + paramInt2);
      paramc.draw(paramCanvas);
      return;
      label195:
      Object localObject = ((CharSequence)localObject).toString();
      float f3 = paramInt3 - paramInt1 - h * 2;
      float f2 = t.measureText((String)localObject);
      float f1 = f2;
      paramc = (arj.c)localObject;
      if (f2 > f3)
      {
        paramc = a((String)localObject, f3);
        f1 = t.measureText(paramc);
      }
      i1 = u.bottom;
      i2 = u.top;
      paramCanvas.drawText(paramc, (paramInt1 + paramInt3 - f1) / 2.0F, (paramInt4 + paramInt2 - (i1 - i2)) / 2.0F - u.top, t);
    }
    
    private void a(ArrayList<arj.c> paramArrayList)
    {
      if (paramArrayList == null) {
        return;
      }
      int i6 = b();
      int i1 = C;
      int i2 = y.left;
      int i3 = y.right;
      ArrayList localArrayList = new ArrayList();
      i2 = i1 + i2 + i3;
      i3 = 0;
      int i4 = 0;
      i1 = 0;
      if (i1 < paramArrayList.size())
      {
        localObject1 = (arj.c)paramArrayList.get(i1);
        i5 = i2;
        if (i4 != 0) {
          i5 = i2 + C;
        }
        if ((c + i5 > i6) && ((i5 - C + c >= i6) || (i1 != paramArrayList.size() - 1)))
        {
          i4 = C;
          i5 = y.left;
          i7 = y.right;
          localArrayList.add(new arj.d(arj.this, i3));
          i2 = 0;
          i3 = i7 + (i4 + i5);
          i4 = i1 - 1;
        }
        for (i1 = 1;; i1 = i5)
        {
          i5 = i3;
          i3 = i1;
          i1 = i4 + 1;
          i4 = i3;
          i3 = i2;
          i2 = i5;
          break;
          i4 = c;
          i2 = i3 + 1;
          i3 = i5 + i4;
          i5 = 0;
          i4 = i1;
        }
      }
      localArrayList.add(new arj.d(arj.this, i3));
      int i7 = get0a;
      i2 = y.left;
      Object localObject1 = new ArrayList();
      int i5 = 0;
      i1 = 0;
      i6 = 0;
      if (i6 < paramArrayList.size())
      {
        Object localObject2 = (arj.c)paramArrayList.get(i6);
        if (i5 != 0)
        {
          i3 = i2;
          if (i1 == 0)
          {
            localObject3 = new Rect(i2, 0, C + i2, x);
            localObject3 = new arj.c(arj.this, (Rect)localObject3, null, C);
            d = true;
            g = E;
            ((ArrayList)localObject1).add(localObject3);
            i3 = i2 + C;
          }
          Object localObject3 = a;
          left = i3;
          right = (i3 + c);
          i2 = right;
          ((ArrayList)localObject1).add(localObject2);
        }
        for (i3 = i1 + 1;; i3 = i1 + 1)
        {
          localObject2 = localObject1;
          i4 = i2;
          i1 = i3;
          int i9 = i7;
          int i8 = i5;
          if (i3 == i7)
          {
            localObject2 = localObject1;
            i4 = i2;
            i1 = i3;
            i9 = i7;
            i8 = i5;
            if (localArrayList.size() > 1)
            {
              localObject2 = localObject1;
              i4 = i2;
              i1 = i3;
              i9 = i7;
              i8 = i5;
              if (i5 + 1 < localArrayList.size())
              {
                localObject2 = new Rect(i2, 0, C + i2, x);
                localObject2 = new arj.c(arj.this, (Rect)localObject2, null, C);
                e = true;
                g = D;
                ((ArrayList)localObject1).add(localObject2);
                arj.c(arj.this).add(localObject1);
                localObject2 = new ArrayList();
                i4 = y.left;
                i1 = 0;
                i8 = i5 + 1;
                i9 = geta;
              }
            }
          }
          i6 += 1;
          localObject1 = localObject2;
          i2 = i4;
          i7 = i9;
          i5 = i8;
          break;
          ((ArrayList)localObject1).add(localObject2);
          i2 += c;
        }
      }
      arj.c(arj.this).add(localObject1);
    }
    
    private boolean a(ArrayList<arj.c> paramArrayList, int paramInt)
    {
      if ((paramInt > 0) && (paramInt < paramArrayList.size()))
      {
        Object localObject = (arj.c)paramArrayList.get(paramInt - 1);
        paramArrayList = (arj.c)paramArrayList.get(paramInt);
        if ((d) || (e)) {
          return true;
        }
        localObject = b;
        paramArrayList = b;
        return ((MenuItem)localObject).getGroupId() != paramArrayList.getGroupId();
      }
      return false;
    }
    
    private int b()
    {
      Resources localResources = getResources();
      if (localResources == null) {
        return 0;
      }
      return getDisplayMetricswidthPixels;
    }
    
    private Bitmap[] c()
    {
      Canvas localCanvas = new Canvas();
      int i3 = getMeasuredHeight();
      int i4 = o;
      int i5 = o + k.getIntrinsicWidth();
      Bitmap[] arrayOfBitmap = arj.a(2, A, x);
      int i1 = 0;
      while (i1 < 2)
      {
        int[] arrayOfInt = arj.b()[i1];
        Bitmap localBitmap = arrayOfBitmap[i1];
        localBitmap.eraseColor(0);
        localCanvas.setBitmap(localBitmap);
        i.setState(arrayOfInt);
        i.setBounds(0, 0, i4, i3);
        i.draw(localCanvas);
        k.setState(arrayOfInt);
        k.setBounds(i4, 0, i5, i3);
        k.draw(localCanvas);
        j.setState(arrayOfInt);
        j.setBounds(i5, 0, A, i3);
        j.draw(localCanvas);
        if (r)
        {
          if ((z == null) || (z.length < A * 2)) {
            z = new int[A * 2];
          }
          int i2 = 0;
          while (i2 < i3 >> 1)
          {
            int i6 = i3 - i2 - 1;
            localBitmap.getPixels(z, 0, A, 0, i2, A, 1);
            localBitmap.getPixels(z, A, A, 0, i6, A, 1);
            localBitmap.setPixels(z, A, A, 0, i2, A, 1);
            localBitmap.setPixels(z, 0, A, 0, i6, A, 1);
            i2 += 1;
          }
        }
        i1 += 1;
      }
      return arrayOfBitmap;
    }
    
    public int a()
    {
      return A;
    }
    
    public int a(int paramInt1, int paramInt2)
    {
      int i3 = 0;
      int i4 = k.getIntrinsicWidth() / 2;
      ArrayList localArrayList;
      if (arj.b(arj.this) < arj.c(arj.this).size())
      {
        localArrayList = (ArrayList)arj.c(arj.this).get(arj.b(arj.this));
        if (localArrayList.size() > 0) {
          i3 = get0c;
        }
      }
      for (int i2 = getsize1c;; i2 = 0)
      {
        int i1 = paramInt1;
        if (paramInt1 < i3 / 2 + y.left) {
          i1 = i3 / 2 + y.left;
        }
        paramInt1 = i1;
        if (i1 > paramInt2 - i2 / 2 - y.right) {
          paramInt1 = paramInt2 - i2 / 2 - y.right;
        }
        o = (paramInt1 - i4);
        return paramInt1;
      }
    }
    
    public void a(boolean paramBoolean)
    {
      if (r != paramBoolean) {}
      for (int i1 = 1;; i1 = 0)
      {
        if (i1 != 0)
        {
          r = paramBoolean;
          if (isShowing()) {
            postInvalidate();
          }
        }
        return;
      }
    }
    
    public void onDraw(Canvas paramCanvas)
    {
      if (arj.a(arj.this) == null) {}
      label173:
      label179:
      label278:
      label325:
      label347:
      label370:
      label422:
      label427:
      label438:
      label449:
      label461:
      label471:
      do
      {
        Bitmap[] arrayOfBitmap;
        do
        {
          return;
          arj.a(arj.this).getMenu();
          arrayOfBitmap = c();
        } while (arj.b(arj.this) >= arj.c(arj.this).size());
        ArrayList localArrayList = (ArrayList)arj.c(arj.this).get(arj.b(arj.this));
        int i6 = localArrayList.size();
        new Rect();
        if (B != 0) {
          paramCanvas.translate(B, 0.0F);
        }
        int i1 = 0;
        if (i1 < i6)
        {
          arj.c localc = (arj.c)localArrayList.get(i1);
          Rect localRect = a;
          if (i1 == 0) {
            left = 0;
          }
          if (i1 == i6 - 1) {
            right = A;
          }
          int i2;
          int i3;
          int i4;
          int i7;
          if ((p == i1) && (q == i1))
          {
            i2 = 1;
            if (i2 == 0) {
              break label422;
            }
            i2 = 1;
            paramCanvas.drawBitmap(arrayOfBitmap[i2], localRect, localRect, v);
            if (a(localArrayList, i1))
            {
              i3 = l.getIntrinsicWidth();
              i4 = l.getIntrinsicHeight();
              i5 = left - i3 / 2;
              i7 = (localRect.height() - y.top - y.bottom - i4) / 2;
              if (!r) {
                break label427;
              }
              i2 = y.bottom;
              i2 += i7;
              l.setBounds(i5, i2, i3 + i5, i4 + i2);
              l.draw(paramCanvas);
            }
            if (!r) {
              break label438;
            }
            i2 = y.bottom;
            i7 = getHeight();
            if (!r) {
              break label449;
            }
            i3 = y.top;
            if (i1 != 0) {
              break label461;
            }
            i4 = left;
            i4 = y.left + i4;
            if (i1 != i6 - 1) {
              break label471;
            }
          }
          for (int i5 = right - y.right;; i5 = right)
          {
            a(paramCanvas, localc, i4, i2, i5, i7 - i3);
            i1 += 1;
            break;
            i2 = 0;
            break label173;
            i2 = 0;
            break label179;
            i2 = y.top;
            break label278;
            i2 = y.top;
            break label325;
            i3 = y.bottom;
            break label347;
            i4 = left;
            break label370;
          }
        }
      } while (B == 0);
      paramCanvas.translate(-B, 0.0F);
    }
    
    public void onMeasure(int paramInt1, int paramInt2)
    {
      if (arj.a(arj.this) == null)
      {
        setMeasuredDimension(0, 0);
        return;
      }
      int i2 = y.left;
      paramInt1 = y.left;
      int i1 = y.right + paramInt1;
      Object localObject = arj.a(arj.this).getMenu();
      int i5 = ((Menu)localObject).size();
      ArrayList localArrayList;
      int i3;
      int i4;
      if (arj.c(arj.this).size() == 0)
      {
        localArrayList = new ArrayList();
        i3 = 0;
        if (i3 < i5)
        {
          i4 = a(((Menu)localObject).getItem(i3)) + h * 2;
          if (b != 0)
          {
            paramInt1 = b;
            label131:
            if (c == 0) {
              break label222;
            }
            paramInt2 = c;
            label143:
            if (i4 >= paramInt2) {
              break label367;
            }
          }
        }
      }
      for (;;)
      {
        if (paramInt2 > paramInt1) {}
        for (;;)
        {
          localArrayList.add(new arj.c(arj.this, new Rect(i2, 0, i2 + paramInt1, x), ((Menu)localObject).getItem(i3), paramInt1));
          i3 += 1;
          i2 += paramInt1;
          break;
          paramInt1 = g;
          break label131;
          label222:
          paramInt2 = f;
          break label143;
          if (localArrayList.size() > 0) {
            a(localArrayList);
          }
          paramInt1 = i1;
          if (arj.c(arj.this).size() > 0)
          {
            paramInt1 = i1;
            if (arj.b(arj.this) < arj.c(arj.this).size())
            {
              localObject = ((ArrayList)arj.c(arj.this).get(arj.b(arj.this))).iterator();
              for (paramInt1 = i1; ((Iterator)localObject).hasNext(); paramInt1 = nextc + paramInt1) {}
            }
          }
          A = Math.max(paramInt1, w);
          setMeasuredDimension(A, x);
          return;
          paramInt1 = paramInt2;
        }
        label367:
        paramInt2 = i4;
      }
    }
    
    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      if (!a) {}
      int i1;
      do
      {
        do
        {
          return true;
          switch (paramMotionEvent.getActionMasked())
          {
          default: 
            return true;
          case 0: 
            p = a(paramMotionEvent.getX(), paramMotionEvent.getY());
            q = p;
          }
        } while (p < 0);
        invalidate();
        return true;
        i1 = a(paramMotionEvent.getX(), paramMotionEvent.getY());
      } while (p == i1);
      if ((p >= 0) || (i1 >= 0)) {
        invalidate();
      }
      p = i1;
      q = p;
      return true;
      if (p >= 0)
      {
        post(G);
        invalidate();
      }
      p = -1;
      return true;
      p = -1;
      return true;
    }
  }
  
  class b
    extends ActionMode
    implements MenuBuilder.Callback
  {
    private ActionMode.Callback b;
    private MenuBuilder c = new MenuBuilder(arj.f(arj.this));
    
    public b(ActionMode.Callback paramCallback)
    {
      c.setCallback(this);
      b = paramCallback;
    }
    
    public boolean a()
    {
      c.stopDispatchingItemsChanged();
      try
      {
        boolean bool = b.onCreateActionMode(this, c);
        return bool;
      }
      finally
      {
        c.startDispatchingItemsChanged();
      }
    }
    
    public void finish()
    {
      if (arj.a(arj.this) != this) {
        return;
      }
      dismiss();
      b.onDestroyActionMode(this);
      b = null;
      arj.a(arj.this, null);
    }
    
    public View getCustomView()
    {
      return null;
    }
    
    public Menu getMenu()
    {
      return c;
    }
    
    public MenuInflater getMenuInflater()
    {
      return new MenuInflater(arj.f(arj.this));
    }
    
    public CharSequence getSubtitle()
    {
      return null;
    }
    
    public CharSequence getTitle()
    {
      return null;
    }
    
    public void invalidate()
    {
      c.stopDispatchingItemsChanged();
      try
      {
        b.onPrepareActionMode(this, c);
        return;
      }
      finally
      {
        c.startDispatchingItemsChanged();
      }
    }
    
    public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
    {
      if (b != null) {
        return b.onActionItemClicked(this, paramMenuItem);
      }
      return false;
    }
    
    public void onMenuModeChange(MenuBuilder paramMenuBuilder) {}
    
    public void setCustomView(View paramView) {}
    
    public void setSubtitle(int paramInt) {}
    
    public void setSubtitle(CharSequence paramCharSequence) {}
    
    public void setTitle(int paramInt) {}
    
    public void setTitle(CharSequence paramCharSequence) {}
  }
  
  class c
  {
    public Rect a;
    public MenuItem b;
    public int c;
    public boolean d = false;
    public boolean e = false;
    public int f = 0;
    public int g;
    
    public c(Rect paramRect, MenuItem paramMenuItem, int paramInt)
    {
      a = paramRect;
      b = paramMenuItem;
      c = paramInt;
    }
  }
  
  class d
  {
    int a;
    
    d(int paramInt)
    {
      a = paramInt;
    }
  }
}

/* Location:
 * Qualified Name:     arj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
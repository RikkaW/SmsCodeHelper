package com.android.mms.view;

import aaw;
import abe;
import abu;
import adp;
import adq;
import adr;
import adt;
import adu;
import ael;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.com.xy.sms.util.SdkCallBack;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.FullScreenBrowsingActivity;
import com.android.mms.ui.MessageListView;
import fe;
import ga;
import nk;
import nl;
import nm;
import org.json.JSONArray;
import vv;

public class MessageListItemSms
  extends MessageListItemBase
  implements nl
{
  public static int a = 0;
  public static int b = 0;
  public static int c = 0;
  private SdkCallBack ak = null;
  private nk al = null;
  private nm am = null;
  protected TextView d;
  protected View e;
  protected TextView f;
  public View g;
  public boolean h = false;
  public ActionMode.Callback i = new adp(this);
  abu j = null;
  private ViewGroup k;
  private a l;
  private int m = -1;
  private int n = -1;
  private long o = 0L;
  private boolean p = false;
  
  public MessageListItemSms(Context paramContext)
  {
    super(paramContext);
  }
  
  public MessageListItemSms(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private void L()
  {
    if (g == null)
    {
      View localView = LayoutInflater.from(U).inflate(2130968844, w, false);
      w.addView(localView);
      g = findViewById(2131886753);
      d = ((TextView)findViewById(2131886754));
      e = findViewById(2131886755);
      f = ((TextView)findViewById(2131886756));
    }
  }
  
  private void M()
  {
    if (M.ak()) {
      return;
    }
    j = new abu(U, M, z, n);
    j.a(true);
    j.a(new adt(this));
  }
  
  private boolean N()
  {
    return (s != null) && (s.m()) && (M.ak());
  }
  
  private void O()
  {
    if (U == null) {
      return;
    }
    ((Activity)U).runOnUiThread(new adu(this));
  }
  
  private void a(int paramInt, boolean paramBoolean)
  {
    if (am == null) {
      am = new nm(getXySmartSmsHolder(), this);
    }
    am.a(M, paramInt, paramBoolean);
  }
  
  private void a(vv paramvv, byte paramByte)
  {
    if (z != null) {
      z.setTag(e + "");
    }
    if (ak == null) {
      ak = new adr(this, paramvv);
    }
    try
    {
      String str = abe.a(1);
      fe.a(e + "", m, str, o, ab, paramByte, paramvv.ai(), ak, false);
      return;
    }
    catch (Exception paramvv)
    {
      O();
      Log.e("duoqu_xiaoyuan", "com.android.mms.ui.SmartSmsBubbleManager.getSimpleBubbleData error", paramvv);
    }
  }
  
  public void K()
  {
    super.K();
    long l1 = System.currentTimeMillis();
    long l2 = l1 - o;
    if ((l2 < ga.b) && (l2 > 0L)) {
      p = true;
    }
    o = l1;
    if (p)
    {
      p = false;
      ai.removeCallbacks(aj);
      if (T != null) {
        T.b();
      }
      if (M != null) {}
    }
    else
    {
      return;
    }
    Intent localIntent = new Intent(U, FullScreenBrowsingActivity.class);
    localIntent.putExtra("fullScreenBrowsingText", M.o);
    U.startActivity(localIntent);
    ((ComposeMessageActivity)U).overridePendingTransition(2131034126, 2131034128);
  }
  
  public void a(View paramView)
  {
    paramView.setOnClickListener(new adq(this));
  }
  
  public void a(a parama)
  {
    if (M == null) {
      return;
    }
    String str = M.o;
    Object localObject = str;
    if (str == null)
    {
      localObject = a(M.o, M.q, M.p, false);
      M.a((CharSequence)localObject);
    }
    L();
    g.setVisibility(0);
    d.setVisibility(0);
    if (g == 1)
    {
      d.setText(a);
      f.setVisibility(8);
      setOnClickListener(M);
      z.a((CharSequence)localObject, false);
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        break label222;
      }
      z.setVisibility(8);
    }
    for (;;)
    {
      M();
      return;
      if (g == 2)
      {
        e.setVisibility(0);
        d.setText(a);
        f.setVisibility(0);
        f.setText(d);
        break;
      }
      d.setVisibility(8);
      f.setVisibility(8);
      e.setVisibility(8);
      break;
      label222:
      z.setVisibility(0);
    }
  }
  
  public void a(vv paramvv, boolean paramBoolean1, int paramInt, MessageListView paramMessageListView, boolean paramBoolean2)
  {
    super.a(paramvv, paramBoolean1, paramInt, paramMessageListView, paramBoolean2);
    if (n == abu.a) {
      z.setAutoLinkMask(15);
    }
    if ((m == 1) && (!paramvv.B())) {
      a(2, N());
    }
    for (;;)
    {
      a();
      a(M);
      requestLayout();
      c();
      z.setCustomSelectionActionModeCallback(i);
      x();
      b();
      d();
      return;
      z();
    }
  }
  
  public void e()
  {
    if (w.hasTransientState()) {}
    do
    {
      return;
      super.e();
      M = null;
      if (z != null)
      {
        z.setMovementMethod(null);
        z.a();
        z.setCustomSelectionActionModeCallback(null);
        if (z.hasTransientState()) {
          z.setHasTransientState(false);
        }
      }
      a(K, 1);
      a(g, 0);
      if (g != null) {
        g.setVisibility(8);
      }
      if (h) {
        o();
      }
      q = 0;
      r = 0;
    } while (j == null);
    j.a(false);
    j = null;
  }
  
  public TextView getContentTextView()
  {
    return z;
  }
  
  public void getHitRect(Rect paramRect)
  {
    super.a(paramRect);
    if ((s != null) && (s.m())) {
      return;
    }
    Rect localRect1 = new Rect();
    w.getHitRect(localRect1);
    Rect localRect2 = new Rect();
    x.getHitRect(localRect2);
    int i1;
    int i2;
    int i3;
    if ((k != null) && (k.getVisibility() == 0))
    {
      Rect localRect3 = new Rect();
      k.getHitRect(localRect3);
      i1 = left;
      i2 = left;
      i3 = left;
      int i4 = top;
      localRect1.offset(i1 + i2 + i3, top + i4 + top);
      if ((M == null) || (!M.B())) {
        break label318;
      }
      q = (left - Math.max(left, left - 20));
      r = (top - Math.max(top, top - 10));
      localRect1.set(Math.max(left, left - 20), Math.max(top, top - 10), Math.min(right, right + 10), Math.min(bottom, bottom + 10));
    }
    for (;;)
    {
      paramRect.set(localRect1);
      return;
      i1 = left;
      i2 = left;
      i3 = top;
      localRect1.offset(i1 + i2, top + i3);
      break;
      label318:
      q = (left - Math.max(left, left - 10));
      r = (top - Math.max(top, top - 10));
      localRect1.set(Math.max(left, left - 10), Math.max(top, top - 10), Math.min(right, right + 20), Math.min(bottom, bottom + 10));
    }
  }
  
  public View getListItemView()
  {
    return this;
  }
  
  public nk getXySmartSmsHolder()
  {
    if ((al == null) || ((U != null) && ((U instanceof nk)))) {
      al = ((nk)U);
    }
    return al;
  }
  
  public void k_()
  {
    if ((w == null) || (z == null)) {}
    Drawable localDrawable;
    do
    {
      return;
      localDrawable = w.getBackground();
    } while (localDrawable == null);
    Rect localRect = new Rect();
    localDrawable.getPadding(localRect);
    w.setPadding(left - a, top - b, right - a, bottom - b - c);
    z.setPadding(a * 2, b * 2, a * 2, z.getPaddingBottom());
    h = true;
  }
  
  public void l_()
  {
    a(M, (byte)1);
  }
  
  protected void o()
  {
    if (z == null) {
      return;
    }
    z.setPadding(0, 0, 0, z.getPaddingBottom());
    h = false;
  }
  
  public void onClick(View paramView)
  {
    if ((paramView == null) || (M == null)) {}
    do
    {
      do
      {
        return;
        if ((paramView != d) && (paramView != f)) {
          break;
        }
      } while (l == null);
      if (paramView == d)
      {
        paramView = l.c;
        aaw.a((Activity)U, paramView, M.m);
        return;
      }
    } while (paramView != f);
    paramView = l.f;
    aaw.a((Activity)U, paramView, M.m);
    return;
    super.onClick(paramView);
  }
  
  public void onFinishInflate()
  {
    super.onFinishInflate();
    z = ((MmsFoldableTextView)findViewById(2131886461));
    k = ((ViewGroup)findViewById(2131886611));
    if (a == 0)
    {
      a = getResources().getDimensionPixelSize(2131558799);
      b = getResources().getDimensionPixelSize(2131558800);
      c = getResources().getDimensionPixelSize(2131558801);
    }
    a(z);
  }
  
  public void setNumberCheckState(int paramInt)
  {
    m = paramInt;
  }
  
  protected void setOnClickListener(vv paramvv)
  {
    if ((g == null) || (d == null) || (f == null)) {
      return;
    }
    d.setOnClickListener(this);
    d.setOnLongClickListener(this);
    f.setOnClickListener(this);
    f.setOnLongClickListener(this);
  }
  
  public void setUnderlineFuncState(int paramInt)
  {
    n = paramInt;
  }
  
  public void z()
  {
    if (M == null) {
      return;
    }
    CharSequence localCharSequence2 = M.I();
    CharSequence localCharSequence1 = localCharSequence2;
    if (localCharSequence2 == null)
    {
      localCharSequence1 = a(M.o, M.q, M.p, false);
      M.a(localCharSequence1);
    }
    z.a(localCharSequence1, false);
    if (TextUtils.isEmpty(localCharSequence1)) {
      z.setVisibility(8);
    }
    for (;;)
    {
      M();
      return;
      z.setVisibility(0);
    }
  }
  
  public class a
  {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public int g;
    private Long i;
    
    public a(Long paramLong, JSONArray paramJSONArray)
    {
      aaw.a(this, paramJSONArray);
      i = paramLong;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MessageListItemSms
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
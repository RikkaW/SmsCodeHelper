package com.android.mms.view;

import aaa;
import ach;
import adv;
import adw;
import adx;
import ady;
import adz;
import aea;
import aeb;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.mms.ui.MessageListView;
import java.io.File;
import java.util.Map;
import vv;
import wd;
import xf;

public class MessageListItemTalk
  extends MessageListItemBase
{
  private static int d = 0;
  private static int e = 0;
  private static int f = 0;
  private static int g = 0;
  private static int h = 0;
  private static int i = 0;
  private static boolean j = false;
  ValueAnimator a = new ValueAnimator();
  public Handler b = new Handler();
  public Runnable c = new aeb(this);
  private Handler k = new adz(this);
  private int l = 1;
  
  public MessageListItemTalk(Context paramContext)
  {
    super(paramContext);
  }
  
  public MessageListItemTalk(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private void L()
  {
    l = (ach.a().d() / 1000 + 1);
    H.setText(g(l));
    l += 1;
    if (b == null) {
      b = new Handler();
    }
    for (;;)
    {
      b.postDelayed(c, 1000L);
      return;
      b.removeCallbacks(c);
    }
  }
  
  private String g(int paramInt)
  {
    if (paramInt < 10) {
      return "00:0" + paramInt;
    }
    return "00:" + paramInt;
  }
  
  private void o()
  {
    if (M.B == null) {
      M.a(new ady(this));
    }
  }
  
  private void setTalkBackgroundInUI(boolean paramBoolean)
  {
    Handler localHandler = k;
    if (paramBoolean) {}
    for (int m = 0;; m = 1)
    {
      localHandler.sendEmptyMessage(m);
      return;
    }
  }
  
  private void setTalkPlayingLayout(int paramInt)
  {
    if (J == null) {
      return;
    }
    w.setPadding(0, 0, 0, 0);
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)w.getLayoutParams();
    width = paramInt;
    w.setLayoutParams(localMarginLayoutParams);
    localMarginLayoutParams = (ViewGroup.MarginLayoutParams)J.getLayoutParams();
    width = paramInt;
    height = w.getHeight();
    J.setLayoutParams(localMarginLayoutParams);
    localMarginLayoutParams = (ViewGroup.MarginLayoutParams)I.getLayoutParams();
    leftMargin = i;
    I.setLayoutParams(localMarginLayoutParams);
    localMarginLayoutParams = (ViewGroup.MarginLayoutParams)H.getLayoutParams();
    rightMargin = i;
    H.setLayoutParams(localMarginLayoutParams);
  }
  
  protected void G()
  {
    if (J == null) {
      return;
    }
    L();
    int m = ach.a().e();
    int n = w.getWidth();
    w.setBackground(null);
    J.setVisibility(0);
    setTalkPlayingLayout(n);
    a.addUpdateListener(new aea(this, n));
    int i1 = (int)(ach.a().d() / ach.a().e() * n);
    a.setDuration(m);
    a.setIntValues(new int[] { i1, n });
    a.start();
  }
  
  protected void H()
  {
    f(M.aa());
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)I.getLayoutParams();
    leftMargin = 0;
    I.setLayoutParams(localMarginLayoutParams);
    localMarginLayoutParams = (ViewGroup.MarginLayoutParams)H.getLayoutParams();
    rightMargin = 0;
    H.setLayoutParams(localMarginLayoutParams);
    c();
    if (J != null) {
      J.setVisibility(8);
    }
  }
  
  public void a(Uri paramUri, String paramString, Map<String, ?> paramMap)
  {
    if ((B == null) || (M == null) || (!M.j())) {
      return;
    }
    if ((!TextUtils.isEmpty(paramString)) && (E != null)) {
      E.setText(paramString);
    }
    B.setImageResource(2130838193);
    B.setVisibility(0);
  }
  
  public void a(vv paramvv, boolean paramBoolean1, int paramInt, MessageListView paramMessageListView, boolean paramBoolean2)
  {
    super.a(paramvv, paramBoolean1, paramInt, paramMessageListView, paramBoolean2);
    if (M.o()) {
      f();
    }
    c();
    b();
  }
  
  public void b(boolean paramBoolean)
  {
    if (TextUtils.isEmpty(M.Z))
    {
      wd.a(2131493296, U, 0, 1, true, 0, aaa.b());
      return;
    }
    File localFile;
    if (M.Z.startsWith("file://"))
    {
      localFile = new File(M.Z.substring("file://".length()));
      if ((!localFile.isFile()) || (!localFile.exists())) {
        wd.a(2131493296, U, 0, 1, true, 0, aaa.b());
      }
    }
    else
    {
      localFile = new File(M.Z);
      if ((!localFile.isFile()) || (!localFile.exists()))
      {
        wd.a(2131493296, U, 0, 1, true, 0, aaa.b());
        return;
      }
    }
    ach.a().a(M.t, new adx(this));
    ach.a().a(U, M.Z, M.t);
  }
  
  public void e()
  {
    if (w.hasTransientState()) {
      return;
    }
    super.e();
    M = null;
    a(K, 1);
    if (N != null) {
      N.cancelBackgroundLoading();
    }
    q = 0;
    r = 0;
    if ((a != null) && (a.isRunning())) {
      a.cancel();
    }
    J = null;
  }
  
  public void f()
  {
    if (J == null) {
      J = ((ProgressBar)findViewById(2131886455));
    }
    if (j)
    {
      G();
      a();
      if (!ach.a().a(M.Z, M.t)) {
        break label141;
      }
      setTalkBackgroundInUI(true);
      ach.a().a(M.t, new adw(this));
    }
    for (;;)
    {
      M.a(null);
      o();
      a(M);
      return;
      H();
      H.setText(g(M.aa()));
      f(M.aa());
      break;
      label141:
      setTalkBackgroundInUI(false);
    }
  }
  
  protected void f(int paramInt)
  {
    ViewGroup.LayoutParams localLayoutParams = w.getLayoutParams();
    if (paramInt <= 2) {
      width = d;
    }
    for (;;)
    {
      w.setLayoutParams(localLayoutParams);
      return;
      if (paramInt <= 10) {
        width = (d + (paramInt - 2) * (f / 8));
      } else if (paramInt <= 20) {
        width = (d + f + (paramInt - 10) * (g / 10));
      } else if (paramInt <= 60) {
        width = (d + f + g + (paramInt - 20) * (h / 40));
      } else {
        width = e;
      }
    }
  }
  
  public void onClick(View paramView)
  {
    if ((paramView == null) || (M == null)) {
      return;
    }
    vv localvv = M;
    if ((localvv.m()) && (!s.i()))
    {
      if (localvv.n())
      {
        wd.a(2131493563, U, 0, 1, true, 0, aaa.b());
        return;
      }
      wd.a(2131493296, U, 0, 1, true, 0, aaa.b());
      return;
    }
    super.onClick(paramView);
  }
  
  public void onFinishInflate()
  {
    super.onFinishInflate();
    if (d == 0)
    {
      d = getResources().getDimensionPixelSize(2131558789);
      f = getResources().getDimensionPixelSize(2131558796);
      g = getResources().getDimensionPixelSize(2131558797);
      h = getResources().getDimensionPixelSize(2131558798);
      e = d + f + g + h;
      i = getResources().getDimensionPixelSize(2131559872);
    }
    H = ((TextView)findViewById(2131886458));
    Typeface localTypeface = Typeface.createFromFile("/system/fonts/Roboto-Medium.ttf");
    H.setTypeface(localTypeface);
    I = ((ImageView)findViewById(2131886456));
    J = ((ProgressBar)findViewById(2131886455));
  }
  
  protected void y()
  {
    H.setText(g(M.aa()));
    f(M.aa());
    a();
    if (ach.a().a(M.Z, M.t))
    {
      setTalkBackgroundInUI(true);
      ach.a().a(M.t, new adv(this));
    }
    for (;;)
    {
      M.a(null);
      o();
      a(M);
      return;
      setTalkBackgroundInUI(false);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MessageListItemTalk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
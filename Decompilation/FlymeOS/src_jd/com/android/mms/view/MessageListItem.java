package com.android.mms.view;

import aaa;
import adb;
import adc;
import add;
import ade;
import adf;
import adg;
import adh;
import adi;
import adj;
import aek;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.AnimatorSet.Builder;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode.Callback;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.MmsApp.c;
import com.android.mms.transaction.flyme.FlymeTransactionService;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.MessageListView;
import java.util.HashMap;
import java.util.Map;
import vr;
import vv;
import wd;
import xf;
import xg;

public class MessageListItem
  extends MessageListItemBase
{
  public static int a = 0;
  protected static int b = 0;
  protected static int c = 0;
  protected static int d = 0;
  protected static int e = 0;
  protected static int f = 0;
  protected static int g = 0;
  protected static int h = 0;
  protected static int i = 0;
  protected static int j = 0;
  protected static int k = 0;
  public View l;
  public boolean m = true;
  public boolean n = false;
  protected MmsFoldableTextView.a o = new adb(this);
  public ActionMode.Callback p = new adc(this);
  
  public MessageListItem(Context paramContext)
  {
    super(paramContext);
  }
  
  public MessageListItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private void L()
  {
    if (A == null)
    {
      A = findViewById(2131886637);
      if (A == null)
      {
        l = findViewById(2131886462);
        l.setVisibility(0);
        A = findViewById(2131886637);
      }
    }
    if (A == null) {}
    do
    {
      return;
      if (B == null) {
        B = ((MessageRoundCornerImageView)findViewById(2131886638));
      }
      if (C == null) {
        C = ((ImageView)findViewById(2131886639));
      }
      if (D == null) {
        D = findViewById(2131886641);
      }
      if (E == null) {
        E = ((TextView)findViewById(2131886217));
      }
      if (F == null) {
        F = ((TextView)findViewById(2131886218));
      }
      if (G == null) {
        G = ((ProgressBar)findViewById(2131886643));
      }
      if (V == null) {
        V = findViewById(2131886640);
      }
    } while (W != null);
    W = findViewById(2131886642);
  }
  
  private void M()
  {
    Log.i("MessageListItem", "startReDownload");
    ac = M.a();
    M.g();
    M.D = 0L;
    N();
    D();
  }
  
  private void N()
  {
    M.a(new adj(this));
  }
  
  private void a(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool = true;
    switch (paramInt)
    {
    default: 
      if (!M.C()) {
        break label351;
      }
      e(false);
      if (paramBoolean2)
      {
        D.setVisibility(8);
        return;
      }
      break;
    case 2: 
      if (M.C())
      {
        if (!paramBoolean2) {}
        for (paramBoolean1 = true;; paramBoolean1 = false)
        {
          e(paramBoolean1);
          if (!paramBoolean2) {
            break;
          }
          D.setVisibility(8);
          return;
        }
        D.setVisibility(0);
        F.setText(d(true));
        G.setProgress((int)(M.D * 100L / M.C));
        G.setVisibility(0);
        return;
      }
      if (M.y())
      {
        if (!paramBoolean2)
        {
          paramBoolean1 = bool;
          e(paramBoolean1);
          o();
          if (!paramBoolean2) {
            break label198;
          }
          D.setVisibility(8);
        }
        for (;;)
        {
          N();
          return;
          paramBoolean1 = false;
          break;
          label198:
          D.setVisibility(0);
        }
      }
      if (M.B())
      {
        e(false);
        if (paramBoolean2)
        {
          D.setVisibility(8);
          return;
        }
        D.setVisibility(0);
        F.setText(d(M.z()));
        return;
      }
      e(false);
      if (paramBoolean1)
      {
        D.setVisibility(0);
        F.setText(d(false));
        return;
      }
      if (paramBoolean2)
      {
        D.setVisibility(8);
        return;
      }
      D.setVisibility(0);
      F.setText(d(false));
      return;
    }
    D.setVisibility(0);
    F.setText(d(false));
    return;
    label351:
    if (M.y())
    {
      e(false);
      if (paramBoolean2)
      {
        D.setVisibility(8);
        return;
      }
      D.setVisibility(0);
      F.setText(d(false));
      return;
    }
    if (M.B())
    {
      e(false);
      if (paramBoolean2)
      {
        D.setVisibility(8);
        return;
      }
      D.setVisibility(0);
      F.setText(d(M.z()));
      return;
    }
    e(false);
    if (paramBoolean1)
    {
      D.setVisibility(0);
      F.setText(d(false));
      return;
    }
    if (paramBoolean2)
    {
      D.setVisibility(8);
      return;
    }
    D.setVisibility(0);
    F.setText(d(false));
  }
  
  private void a(long paramLong1, long paramLong2)
  {
    G.setProgress((int)(100L * paramLong2 / paramLong1));
    post(new add(this, paramLong1, paramLong2));
  }
  
  private void a(long paramLong1, long paramLong2, boolean paramBoolean)
  {
    long l1 = paramLong1;
    if (M != null) {
      l1 = Math.max(paramLong1, M.C);
    }
    if (paramBoolean) {
      G.setProgress((int)(100L * paramLong2 / l1));
    }
    if (l1 <= 1048576L)
    {
      F.setText(paramLong2 / 1024L + "KB/" + (1023L + l1) / 1024L + "KB");
      return;
    }
    F.setText(wd.a(paramLong2, 1048576L, "MB", null) + "/" + wd.a(1023L + l1, 1048576L, "MB", null));
  }
  
  private void a(View paramView, int paramInt1, int paramInt2)
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)paramView.getLayoutParams();
    topMargin = paramInt1;
    bottomMargin = paramInt2;
    paramView.setLayoutParams(localLayoutParams);
  }
  
  private void a(vv paramvv, boolean paramBoolean, View paramView)
  {
    if ((M.v != 4) || (M.D()))
    {
      a(M, 2, paramBoolean, 0);
      return;
    }
    if (S == null) {
      S = new vr(U, paramView);
    }
    S.a(null);
    S.a(new adi(this, paramvv, paramBoolean));
    if ((U instanceof ComposeMessageActivity)) {
      ((ComposeMessageActivity)U).d(false);
    }
    S.a();
  }
  
  private void c(View paramView)
  {
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)paramView.getLayoutParams();
    topMargin = 0;
    bottomMargin = 0;
    leftMargin = 0;
    rightMargin = 0;
    paramView.setLayoutParams(localLayoutParams);
  }
  
  private void c(boolean paramBoolean)
  {
    F.setText(d(paramBoolean));
    ProgressBar localProgressBar;
    if (M.C <= 0L)
    {
      if (paramBoolean) {
        G.setProgress(100);
      }
      localProgressBar = G;
      if (!paramBoolean) {
        break label88;
      }
    }
    label88:
    for (int i1 = 0;; i1 = 4)
    {
      localProgressBar.setVisibility(i1);
      return;
      if (!paramBoolean) {
        break;
      }
      G.setProgress((int)(M.D * 100L / M.C));
      break;
    }
  }
  
  private String d(boolean paramBoolean)
  {
    String str = wd.c(U, M.C);
    if (paramBoolean)
    {
      if (M.C <= 1048576L) {
        return M.D / 1024L + " KB/" + str;
      }
      return wd.a(M.D, 1048576L, " MB", null) + "/" + str;
    }
    return str;
  }
  
  private void e(boolean paramBoolean)
  {
    if (G == null) {
      return;
    }
    if (paramBoolean)
    {
      G.setVisibility(0);
      return;
    }
    G.setVisibility(4);
  }
  
  private void f(int paramInt)
  {
    boolean bool2 = true;
    if ((B.getTag() != null) && (M.M() == ((Long)B.getTag()).longValue())) {}
    for (int i1 = 0;; i1 = 1)
    {
      B.setTag(Long.valueOf(M.M()));
      switch (M.v)
      {
      case 6: 
      default: 
        f(false);
        return;
      case 1: 
      case 2: 
      case 8: 
        f(true);
        boolean bool1 = bool2;
        if (paramInt != 1)
        {
          bool1 = bool2;
          if (!B()) {
            if (!M.E()) {
              break label190;
            }
          }
        }
        for (bool1 = bool2;; bool1 = false)
        {
          setImageMargin(bool1);
          if (!bool1) {
            break;
          }
          if (i1 != 0) {
            B.setImageResource(2130837691);
          }
          D.setVisibility(8);
          a(paramInt, false, bool1);
          return;
        }
        if (i1 != 0) {
          wd.a(M.v, B, null, null, m);
        }
        if ("file_name_error" != M.x) {
          E.setText(M.x);
        }
        for (;;)
        {
          E.setVisibility(0);
          D.setVisibility(0);
          break;
          E.setText(U.getString(2131492892));
        }
      case 4: 
        if (M.h())
        {
          f(true);
          setImageMargin(true);
          if (i1 != 0) {
            switch (M.w)
            {
            default: 
              wd.a(M.w, B, M.x, null, m);
            }
          }
        }
        for (;;)
        {
          D.setVisibility(8);
          return;
          B.setImageResource(2130837691);
          continue;
          f(false);
        }
      case 3: 
        f(true);
        setImageMargin(false);
        wd.a(3, B, null, null, m);
        if ("file_name_error" != M.x) {
          E.setText(M.x);
        }
        for (;;)
        {
          a(paramInt, false, false);
          return;
          E.setText(U.getString(2131492892));
        }
      case 5: 
        label190:
        f(true);
        setImageMargin(false);
        wd.a(5, B, null, null, m);
        if ("file_name_error" != M.x) {
          E.setText(M.x);
        }
        for (;;)
        {
          a(paramInt, true, false);
          return;
          E.setText(U.getString(2131492892));
        }
      }
      f(true);
      setImageMargin(false);
      wd.a(M.x, B, m);
      if ("file_name_error" != M.x) {
        E.setText(M.x);
      }
      for (;;)
      {
        a(paramInt, false, false);
        return;
        E.setText(U.getString(2131492892));
      }
    }
  }
  
  private void f(boolean paramBoolean)
  {
    if (A == null) {}
    do
    {
      return;
      if ((paramBoolean) && (A.getVisibility() != 0))
      {
        A.setVisibility(0);
        return;
      }
    } while ((paramBoolean) || (A.getVisibility() == 8));
    A.setVisibility(8);
  }
  
  private void g(boolean paramBoolean)
  {
    if ((C == null) || (M == null)) {
      return;
    }
    if (paramBoolean)
    {
      ViewGroup.LayoutParams localLayoutParams = C.getLayoutParams();
      if ((A()) && (!M.E())) {
        width = f;
      }
      for (height = g;; height = -2)
      {
        C.setLayoutParams(localLayoutParams);
        C.setVisibility(0);
        return;
        width = -2;
      }
    }
    C.setVisibility(8);
  }
  
  private int getFoldableTextViewType()
  {
    if ((M == null) || (M.q())) {
      return 0;
    }
    switch (M.v)
    {
    default: 
      return 3;
    }
    return 4;
  }
  
  private void setMessageBackgroundInternal(View paramView)
  {
    if (M == null) {
      return;
    }
    if (M.B())
    {
      paramView.setBackgroundResource(2130837580);
      return;
    }
    paramView.setBackgroundResource(2130837576);
  }
  
  public void a(Uri paramUri, String paramString, Map<String, ?> paramMap)
  {
    if ((B == null) || (M == null) || (!M.j())) {
      return;
    }
    if ((!TextUtils.isEmpty(paramString)) && (E != null)) {
      E.setText(paramString);
    }
    wd.a(3, B, null, null, m);
    B.setVisibility(0);
  }
  
  public void a(Uri paramUri, String paramString1, Map<String, ?> paramMap, String paramString2)
  {
    if ((B == null) || (M == null) || (!M.j())) {
      return;
    }
    if ((!TextUtils.isEmpty(paramString1)) && (E != null)) {
      E.setText(paramString1);
    }
    wd.a(paramString1, paramString2, B, m);
    B.setVisibility(0);
  }
  
  public void a(View paramView)
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)paramView.getLayoutParams();
    topMargin = 0;
    bottomMargin = 0;
    leftMargin = 0;
    rightMargin = 0;
    paramView.setLayoutParams(localLayoutParams);
  }
  
  public void a(String paramString, Bitmap paramBitmap)
  {
    if ((B == null) || (M == null) || ((!M.j()) && (!M.E()))) {
      return;
    }
    if (paramBitmap == null)
    {
      for (;;)
      {
        try
        {
          if (M != null) {
            break;
          }
          if (A())
          {
            wd.a(1, B, null, null, m);
            B.setVisibility(0);
            return;
          }
        }
        catch (OutOfMemoryError paramString)
        {
          Log.e("MessageListItem", "setImage: out of memory: ", paramString);
          return;
        }
        B.setImageResource(2130837691);
      }
      M.B();
      switch (M.v)
      {
      }
    }
    for (;;)
    {
      if (A())
      {
        wd.a(1, B, null, null, m);
        break;
        wd.a(M.v, B, null, null, m);
        break;
        if (A())
        {
          wd.a(8, B, null, null, m);
          break;
        }
        B.setImageResource(2130837691);
        break;
      }
      B.setImageResource(2130837691);
      break;
      B.setImageBitmap(paramBitmap);
      break;
    }
  }
  
  public void a(vv paramvv, boolean paramBoolean1, int paramInt, MessageListView paramMessageListView, boolean paramBoolean2)
  {
    super.a(paramvv, paramBoolean1, paramInt, paramMessageListView, paramBoolean2);
    paramInt = 1;
    if (M.H()) {
      q();
    }
    for (;;)
    {
      if (paramInt != 0) {
        c();
      }
      z.setCustomSelectionActionModeCallback(p);
      x();
      b();
      d();
      return;
      if (M.F())
      {
        y();
      }
      else if (M.G())
      {
        u();
      }
      else if (M.E())
      {
        t();
      }
      else if (M.q())
      {
        z();
      }
      else if (M.p())
      {
        r();
      }
      else if (M.n())
      {
        g();
      }
      else if (M.l())
      {
        p();
      }
      else if (M.o())
      {
        f();
      }
      else if (M.m())
      {
        v();
      }
      else
      {
        paramInt = 0;
        s();
      }
    }
  }
  
  public void a(boolean paramBoolean)
  {
    if ((!ae) && (B != null) && (B.getVisibility() == 0)) {
      B.setSelected(paramBoolean);
    }
  }
  
  public void b(Uri paramUri, String paramString, Map<String, ?> paramMap)
  {
    if ((B == null) || (M == null) || (!M.j())) {
      return;
    }
    if ((!TextUtils.isEmpty(paramString)) && (E != null)) {
      E.setText(paramString);
    }
    wd.a(5, B, null, null, m);
    B.setVisibility(0);
  }
  
  public void b(String paramString, Bitmap paramBitmap)
  {
    if ((B == null) || (M == null) || ((!M.j()) && (!M.E()))) {
      return;
    }
    if (paramBitmap == null) {}
    for (;;)
    {
      try
      {
        if (A())
        {
          wd.a(2, B, null, null, m);
          g(false);
          B.setVisibility(0);
          return;
        }
      }
      catch (OutOfMemoryError paramString)
      {
        Log.e("MessageListItem", "setVideo: out of memory: ", paramString);
        return;
      }
      B.setImageResource(2130837691);
      continue;
      B.setImageBitmap(paramBitmap);
      g(true);
    }
  }
  
  public void c()
  {
    if (M != null)
    {
      if (M.af()) {
        setMessageBackgroundInternal(y);
      }
      if (M.ae()) {
        setMessageBackgroundInternal(z);
      }
      if (((B.getVisibility() != 0) && (V.getVisibility() != 0)) || (!ae)) {
        break label89;
      }
      A.setBackgroundResource(2130837581);
    }
    for (;;)
    {
      w.setBackground(null);
      return;
      label89:
      A.setBackground(null);
    }
  }
  
  public void d()
  {
    if ((w == null) || (M == null)) {}
    int i1;
    int i2;
    label53:
    int i3;
    label156:
    label161:
    label166:
    do
    {
      return;
      if ((y != null) && (y.getVisibility() == 0))
      {
        i1 = 1;
        if ((A == null) || (A.getVisibility() != 0)) {
          break label156;
        }
        i2 = 1;
        if ((z == null) || (z.getVisibility() != 0)) {
          break label161;
        }
      }
      for (i3 = 1;; i3 = 0)
      {
        if ((B.getVisibility() == 0) && (ae))
        {
          FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)B.getLayoutParams();
          topMargin = j;
          bottomMargin = k;
          B.setLayoutParams(localLayoutParams);
        }
        if ((i1 == 0) || (i2 == 0) || (i3 != 0)) {
          break label166;
        }
        a(A, a, 0);
        return;
        i1 = 0;
        break;
        i2 = 0;
        break label53;
      }
      if ((i1 != 0) && (i2 != 0) && (i3 != 0))
      {
        a(A, a, a);
        a(z, 0, 0);
        return;
      }
      if ((i1 != 0) && (i2 == 0) && (i3 != 0))
      {
        a(y, 0, 0);
        a(z, a, 0);
        return;
      }
    } while ((i1 != 0) || (i2 == 0) || (i3 == 0));
    a(A, 0, a);
    a(z, 0, 0);
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
      a(B, 0);
      a(K, 1);
      if (N != null) {
        N.cancelBackgroundLoading();
      }
      if ((A != null) && (A.getVisibility() == 0)) {
        A.setVisibility(8);
      }
      if (C != null) {
        C.setVisibility(8);
      }
      if ((G != null) && (G.getVisibility() == 0)) {
        G.setVisibility(4);
      }
      if (V != null)
      {
        if (V.getVisibility() == 0) {
          V.setVisibility(8);
        }
        a(V, 1);
      }
      if ((W != null) && (W.getVisibility() == 0)) {
        W.setVisibility(8);
      }
      if ((F != null) && (F.getVisibility() == 8)) {
        F.setVisibility(0);
      }
      q = 0;
      r = 0;
      if (A != null) {
        a(A);
      }
      if (y != null) {
        a(y);
      }
      if (z != null) {
        a(z);
      }
    } while (B == null);
    c(B);
  }
  
  protected void g()
  {
    w.setVisibility(0);
    y.setVisibility(8);
    z.setVisibility(8);
    a();
    L();
    A.setVisibility(0);
    setImageMargin(false);
    D.setVisibility(0);
    E.setText(M.x);
    if (M != null) {
      M.B();
    }
    wd.a(M.v, B, M.x, E, true);
    switch (M.O())
    {
    default: 
      e(false);
      F.setText(d(true));
      V.setVisibility(8);
      W.setVisibility(8);
    }
    for (;;)
    {
      if ((!s.i()) && (B != null)) {
        B.setImageResource(2130838209);
      }
      a(M);
      requestLayout();
      return;
      e(true);
      o();
      B.setVisibility(0);
      V.setVisibility(8);
      N();
      W.setVisibility(8);
      continue;
      e(false);
      if (M.G != 132)
      {
        B.setVisibility(8);
        V.setVisibility(0);
        V.setOnClickListener(this);
        F.setText(d(false));
        W.setVisibility(8);
      }
      else
      {
        B.setVisibility(0);
        V.setVisibility(8);
        e(false);
        F.setVisibility(8);
        W.setVisibility(0);
      }
    }
  }
  
  public TextView getContentTextView()
  {
    return z;
  }
  
  public void i() {}
  
  public void l() {}
  
  protected void o()
  {
    MmsApp.c localc = (MmsApp.c)MmsApp.p.get(M.K());
    if (localc == null)
    {
      c(true);
      return;
    }
    a(b, a, true);
    G.setVisibility(0);
  }
  
  public void onClick(View paramView)
  {
    if ((paramView == null) || (M == null)) {}
    do
    {
      return;
      if (wd.c(U.getContentResolver()))
      {
        wd.m(U);
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
      if (paramView != B) {
        break label366;
      }
      if ((y) && ((!localvv.s()) || (localvv.B()) || (wd.c(U, Uri.parse("file://" + Z))))) {
        break;
      }
      if ((!localvv.s()) || (localvv.B()))
      {
        wd.a(2131493296, U, 0, 1, true, 0, aaa.b());
        return;
      }
      if (ac == -1L)
      {
        if (ad == M.a())
        {
          M();
          return;
        }
        new AlertDialog.Builder(U).setMessage(2131493716).setPositiveButton(2131493717, new adh(this)).setNegativeButton(2131493022, null).show();
        return;
      }
      if (ac != M.a())
      {
        wd.a(2131493721, U, 0, 1, true, 0, aaa.b());
        return;
      }
      paramView = new Intent(U, FlymeTransactionService.class);
      paramView.setAction("android.intent.action.CANCEL_FLYME_MSG");
      paramView.setData(t);
    } while (U.startService(paramView) != null);
    Log.e("MessageListItem", "can't cancel service transactionservice");
    return;
    a(M, false, B);
    return;
    label366:
    if (paramView == V)
    {
      super.E();
      return;
    }
    super.onClick(paramView);
  }
  
  public void onFinishInflate()
  {
    super.onFinishInflate();
    if (a == 0) {
      a = getResources().getDimensionPixelSize(2131558785);
    }
    if ((f == 0) || (g == 0))
    {
      f = getResources().getDimensionPixelSize(2131559267);
      g = getResources().getDimensionPixelSize(2131559266);
    }
    if ((b == 0) || (c == 0) || (d == 0) || (e == 0))
    {
      b = getResources().getDimensionPixelSize(2131559303);
      c = getResources().getDimensionPixelSize(2131559302);
      d = getResources().getDimensionPixelSize(2131559301);
      e = getResources().getDimensionPixelSize(2131559300);
    }
    if (h == 0)
    {
      h = getResources().getDimensionPixelSize(2131559317);
      i = getResources().getDimensionPixelSize(2131559318);
      j = getResources().getDimensionPixelSize(2131559858);
      k = getResources().getDimensionPixelSize(2131559857);
    }
    y = ((TextView)findViewById(2131886460));
    z = ((MmsFoldableTextView)findViewById(2131886461));
    l = findViewById(2131886462);
  }
  
  protected void p()
  {
    w.setVisibility(0);
    L();
    y.setVisibility(8);
    A.setVisibility(0);
    setImageMargin(false);
    B.setImageResource(2130838181);
    D.setVisibility(0);
    String str = wd.c(U, M.C);
    if (M.aj())
    {
      E.setText(U.getString(2131493739));
      F.setText(str);
      e(false);
      z.setVisibility(8);
      a();
      switch (M.O())
      {
      default: 
        t.setText(M.k);
        V.setVisibility(8);
        B.setVisibility(0);
        W.setVisibility(8);
      }
    }
    for (;;)
    {
      a(M);
      requestLayout();
      return;
      E.setText(U.getString(2131493020));
      break;
      t.setText(2131493478);
      V.setVisibility(8);
      B.setVisibility(0);
      W.setVisibility(8);
      continue;
      t.setText(M.k);
      V.setVisibility(0);
      B.setVisibility(8);
      W.setVisibility(8);
    }
  }
  
  protected void q()
  {
    w.setVisibility(0);
    L();
    f(false);
    y.setVisibility(8);
    String str = U.getString(2131493553) + M.aa() + " \"";
    z.a(a(str + "\n" + M.k, M.q, M.p, false), false);
    a();
    a(M);
  }
  
  protected void r()
  {
    w.setVisibility(0);
    L();
    f(false);
    y.setVisibility(8);
    String str = U.getString(2131493553) + M.aa() + " \"";
    z.a(a(str + "\n" + M.k, M.q, M.p, false), false);
    a();
    a(M);
  }
  
  public void s()
  {
    w.setVisibility(0);
    Object localObject;
    if (TextUtils.isEmpty(M.A))
    {
      y.setVisibility(8);
      z.a(getFoldableTextViewType(), o);
      CharSequence localCharSequence = M.J();
      localObject = localCharSequence;
      if (localCharSequence == null)
      {
        localCharSequence = M.I();
        if ((M.v == 4) && (TextUtils.isEmpty(M.o))) {
          M.o = " ";
        }
        localObject = localCharSequence;
        if (localCharSequence == null)
        {
          localObject = a(M.o, M.q, M.p, true);
          M.a((CharSequence)localObject);
        }
        localObject = new SpannableStringBuilder((CharSequence)localObject);
        localObject = z.a((SpannableStringBuilder)localObject, M.u());
        M.b((CharSequence)localObject);
      }
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        break label289;
      }
      z.setVisibility(8);
      label191:
      a();
      L();
      if (M.v == 0) {
        break label309;
      }
      f(1);
      setOnClickListener(M);
    }
    for (;;)
    {
      if (M.B != null) {
        break label317;
      }
      M.a(new ade(this));
      a(M);
      c();
      requestLayout();
      return;
      y.setVisibility(0);
      y.setText(M.A);
      break;
      label289:
      z.a((CharSequence)localObject, true);
      z.setVisibility(0);
      break label191;
      label309:
      f(false);
    }
    label317:
    if (N == null)
    {
      N = xg.a("MmsThumbnailPresenter", U, this, M.B);
      label346:
      if (P != null) {
        break label404;
      }
      P = new MessageListItemBase.a(this);
    }
    for (;;)
    {
      N.present(P);
      break;
      N.setModel(M.B);
      N.setView(this);
      break label346;
      label404:
      P.a(this);
    }
  }
  
  public void setActivated(boolean paramBoolean)
  {
    super.setActivated(paramBoolean);
    a(paramBoolean);
  }
  
  public void setImageMargin(boolean paramBoolean)
  {
    boolean bool = true;
    Object localObject;
    if (paramBoolean)
    {
      localObject = A.getLayoutParams();
      height = e;
      A.setLayoutParams((ViewGroup.LayoutParams)localObject);
      localObject = B.getLayoutParams();
      width = d;
      height = e;
      B.setLayoutParams((ViewGroup.LayoutParams)localObject);
      if (paramBoolean) {
        break label153;
      }
      paramBoolean = true;
      label65:
      ae = paramBoolean;
      localObject = B;
      if (ae) {
        break label158;
      }
    }
    label153:
    label158:
    for (paramBoolean = bool;; paramBoolean = false)
    {
      ((MessageRoundCornerImageView)localObject).setIsNeedRedraw(paramBoolean);
      return;
      localObject = A.getLayoutParams();
      height = h;
      width = i;
      A.setLayoutParams((ViewGroup.LayoutParams)localObject);
      localObject = B.getLayoutParams();
      width = b;
      height = c;
      B.setLayoutParams((ViewGroup.LayoutParams)localObject);
      break;
      paramBoolean = false;
      break label65;
    }
  }
  
  protected void setOnClickListener(vv paramvv)
  {
    if ((B == null) || (paramvv == null)) {
      return;
    }
    switch (v)
    {
    case 6: 
    default: 
      a(B, 0);
      return;
    }
    B.setOnClickListener(this);
    B.setOnLongClickListener(this);
  }
  
  public void t()
  {
    w.setVisibility(0);
    y.setVisibility(8);
    z.a(getFoldableTextViewType(), o);
    CharSequence localCharSequence = M.J();
    Object localObject = localCharSequence;
    if (localCharSequence == null)
    {
      localCharSequence = M.I();
      localObject = localCharSequence;
      if (localCharSequence == null)
      {
        localObject = a(M.o, M.q, M.p, true);
        M.a((CharSequence)localObject);
      }
      localObject = new SpannableStringBuilder((CharSequence)localObject);
      localObject = z.a((SpannableStringBuilder)localObject, M.u());
      M.b((CharSequence)localObject);
    }
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      z.setVisibility(8);
      a();
      L();
      if (M.v == 0) {
        break label276;
      }
      f(3);
      setOnClickListener(M);
    }
    for (;;)
    {
      if (M.B != null) {
        break label284;
      }
      M.a(new adf(this));
      if (!s.i())
      {
        if (B != null) {
          B.setImageResource(2130838209);
        }
        if (C != null) {
          C.setVisibility(8);
        }
      }
      a(M);
      requestLayout();
      return;
      z.a((CharSequence)localObject, true);
      z.setVisibility(0);
      break;
      label276:
      f(false);
    }
    label284:
    if (N == null)
    {
      N = xg.a("MmsThumbnailPresenter", U, this, M.B);
      label313:
      if (P != null) {
        break label371;
      }
      P = new MessageListItemBase.a(this);
    }
    for (;;)
    {
      N.present(P);
      break;
      N.setModel(M.B);
      N.setView(this);
      break label313;
      label371:
      P.a(this);
    }
  }
  
  protected void u()
  {
    w.setVisibility(0);
    y.setVisibility(8);
    z.setVisibility(8);
    a();
    L();
    A.setVisibility(0);
    setImageMargin(false);
    D.setVisibility(0);
    E.setText(M.x);
    if (M != null) {
      M.B();
    }
    wd.a(M.v, B, M.x, E, m);
    switch (M.O())
    {
    default: 
      e(false);
      F.setText(d(false));
    }
    for (;;)
    {
      if ((!s.i()) && (B != null)) {
        B.setImageResource(2130838209);
      }
      a(M);
      requestLayout();
      return;
      e(false);
      continue;
      e(false);
      F.setText(d(false));
    }
  }
  
  public void v()
  {
    w.setVisibility(0);
    Object localObject;
    if (TextUtils.isEmpty(M.A))
    {
      y.setVisibility(8);
      z.a(getFoldableTextViewType(), o);
      CharSequence localCharSequence = M.J();
      localObject = localCharSequence;
      if (localCharSequence == null)
      {
        localCharSequence = M.I();
        localObject = localCharSequence;
        if (localCharSequence == null)
        {
          localObject = a(M.o, M.q, M.p, true);
          M.a((CharSequence)localObject);
        }
        localObject = new SpannableStringBuilder((CharSequence)localObject);
        localObject = z.a((SpannableStringBuilder)localObject, M.u());
        M.b((CharSequence)localObject);
      }
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        break label301;
      }
      z.setVisibility(8);
      label157:
      if (!B()) {
        break label321;
      }
      a();
      label168:
      L();
      if (M.v == 0) {
        break label346;
      }
      f(2);
      setOnClickListener(M);
    }
    for (;;)
    {
      if (M.B != null) {
        break label354;
      }
      M.a(new adg(this));
      if (!s.i())
      {
        if (B != null) {
          B.setImageResource(2130838209);
        }
        if (C != null) {
          C.setVisibility(8);
        }
      }
      a(M);
      requestLayout();
      return;
      y.setVisibility(0);
      y.setText(M.A);
      break;
      label301:
      z.a((CharSequence)localObject, true);
      z.setVisibility(0);
      break label157;
      label321:
      t.setVisibility(0);
      t.setText(M.k);
      break label168;
      label346:
      f(false);
    }
    label354:
    if (N == null)
    {
      N = xg.a("MmsThumbnailPresenter", U, this, M.B);
      label383:
      if (P != null) {
        break label441;
      }
      P = new MessageListItemBase.a(this);
    }
    for (;;)
    {
      N.present(P);
      break;
      N.setModel(M.B);
      N.setView(this);
      break label383;
      label441:
      P.a(this);
    }
  }
  
  protected void w()
  {
    Object localObject3 = null;
    if (M == null) {
      return;
    }
    if (M.af())
    {
      localObject1 = new aek(new Drawable[] { y.getBackground(), getSelectedBackgroundResource() });
      y.setBackground((Drawable)localObject1);
    }
    for (Object localObject1 = a(localObject1);; localObject1 = null)
    {
      if ((B.getVisibility() == 0) && (ae))
      {
        localObject2 = new aek(new Drawable[] { A.getBackground(), getSelectedBackgroundResource() });
        A.setBackground((Drawable)localObject2);
      }
      for (Object localObject2 = a(localObject2);; localObject2 = null)
      {
        if (M.ae())
        {
          localObject3 = new aek(new Drawable[] { z.getBackground(), getSelectedBackgroundResource() });
          z.setBackground((Drawable)localObject3);
          localObject3 = a(localObject3);
        }
        if ((localObject1 != null) && (localObject2 != null) && (localObject3 != null))
        {
          ag.play((Animator)localObject1).with((Animator)localObject2).with((Animator)localObject3);
          return;
        }
        if ((localObject1 != null) && (localObject2 != null))
        {
          ag.play((Animator)localObject1).with((Animator)localObject2);
          return;
        }
        if ((localObject2 != null) && (localObject3 != null))
        {
          ag.play((Animator)localObject2).with((Animator)localObject3);
          return;
        }
        if ((localObject1 != null) && (localObject3 != null))
        {
          ag.play((Animator)localObject1).with((Animator)localObject3);
          return;
        }
        AnimatorSet localAnimatorSet = ag;
        if (localObject1 != null) {}
        for (;;)
        {
          localAnimatorSet.play((Animator)localObject1);
          return;
          if (localObject2 != null) {
            localObject1 = localObject2;
          } else {
            localObject1 = localObject3;
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MessageListItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
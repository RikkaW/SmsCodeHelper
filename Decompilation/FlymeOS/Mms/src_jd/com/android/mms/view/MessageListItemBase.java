package com.android.mms.view;

import aaa;
import aac;
import aau;
import aaz;
import abm.a;
import acu;
import adk;
import adl;
import adm;
import adn;
import ado;
import aek;
import ael;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewStub;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.transaction.TransactionService;
import com.android.mms.transaction.flyme.FlymeTransactionService;
import com.android.mms.transaction.sns.SnsTransactionService;
import com.android.mms.ui.AttachMentViewBase;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.FullScreenBrowsingActivity;
import com.android.mms.ui.MessageListView;
import com.meizu.android.mms.MzContentType;
import com.meizu.android.mms.pdu.MzMultimediaMessagePdu;
import com.meizu.android.mms.pdu.MzPduBody;
import com.meizu.android.mms.pdu.MzPduPart;
import com.meizu.android.mms.util.MzPduCacheEntry;
import com.meizu.widget.ListDragShadowItem;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;
import lq;
import lr;
import uo;
import vr;
import vv;
import vv.a;
import vx;
import wd;
import xf;
import zl;
import zv;
import zy;

public class MessageListItemBase
  extends AttachMentViewBase
  implements View.OnClickListener, View.OnLongClickListener, ListDragShadowItem
{
  public static boolean ab = false;
  public static long ac = -1L;
  public static long ad = -1L;
  private static int c = 0;
  private static int d = 0;
  private static int g = 0;
  public View A;
  public MessageRoundCornerImageView B;
  protected ImageView C;
  public View D;
  public TextView E;
  public TextView F;
  public ProgressBar G;
  public TextView H;
  public ImageView I;
  public ProgressBar J;
  protected View K;
  protected Handler L;
  public vv M;
  protected xf N;
  protected int O;
  protected a P;
  protected boolean Q;
  protected MmsFoldableTextView.a R = null;
  protected vr S = null;
  protected ael T;
  public Context U;
  public View V;
  protected View W;
  private ImageView a;
  protected uo aa;
  public boolean ae = false;
  protected ImageView af;
  protected AnimatorSet ag;
  public boolean ah = false;
  protected Handler ai = new Handler();
  protected Runnable aj = new ado(this);
  private View b;
  private CheckBox e;
  private LinearLayout f;
  protected int q = 0;
  protected int r = 0;
  public MessageListView s;
  protected TextView t;
  protected TextView u;
  protected TextView v;
  public ViewGroup w;
  protected ViewGroup x;
  public TextView y;
  public MmsFoldableTextView z;
  
  public MessageListItemBase(Context paramContext)
  {
    super(paramContext);
    U = paramContext;
  }
  
  public MessageListItemBase(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    U = paramContext;
  }
  
  public static final void a(Context paramContext, vv paramvv)
  {
    Intent localIntent = new Intent(paramContext, FlymeTransactionService.class);
    localIntent.setAction("android.intent.action.CANCEL_FLYME_MSG");
    localIntent.setData(t);
    if (paramContext.startService(localIntent) != null)
    {
      Log.e("MessageListItem", "cancelTransaction msgItem.mMessageUri = " + t);
      return;
    }
    Log.e("MessageListItem", "can't cancel service transactionservice");
  }
  
  private void e(vv paramvv)
  {
    boolean bool2 = false;
    if (a == null) {
      return;
    }
    int i;
    if ((paramvv.E()) || (paramvv.D()))
    {
      i = 1;
      if (m == null) {
        break label83;
      }
    }
    label83:
    for (boolean bool1 = m.equals("4007883333");; bool1 = false)
    {
      ImageView localImageView = a;
      int j = paramvv.ag();
      if ((i != 0) || (bool1)) {
        bool2 = true;
      }
      zv.a(localImageView, 3, j, bool2);
      return;
      i = 0;
      break;
    }
  }
  
  private void f(int paramInt)
  {
    if ((M != null) && (f != null) && (!M.w()))
    {
      LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)f.getLayoutParams();
      rightMargin = paramInt;
      f.setLayoutParams(localLayoutParams);
    }
  }
  
  private View g(int paramInt)
  {
    return ((ViewStub)findViewById(paramInt)).inflate();
  }
  
  private void o()
  {
    if (!MmsApp.d)
    {
      if ((!M.ad()) || (!(U instanceof ComposeMessageActivity))) {
        break label56;
      }
      if (v == null) {
        v = ((TextView)g(2131886603));
      }
      v.setVisibility(0);
    }
    label56:
    while (v == null) {
      return;
    }
    v.setVisibility(8);
  }
  
  protected boolean A()
  {
    boolean bool = true;
    if ((M == null) || (!M.j()) || ((M.v != 1) && (M.v != 2) && (M.v != 8))) {
      bool = false;
    }
    while (M.C >= 5242880L) {
      return bool;
    }
    return false;
  }
  
  protected boolean B()
  {
    boolean bool = true;
    if ((M == null) || (!M.j()) || ((M.v != 1) && (M.v != 2) && (M.v != 8))) {
      bool = false;
    }
    while (M.C < 5242880L) {
      return bool;
    }
    return false;
  }
  
  protected boolean C()
  {
    boolean bool;
    for (;;)
    {
      int i;
      try
      {
        vv localvv = M;
        Object localObject1 = lr.a(U, localvv.K());
        if (localObject1 != null)
        {
          if (!(((MzPduCacheEntry)localObject1).getPdu() instanceof MzMultimediaMessagePdu)) {
            return false;
          }
          MzPduBody localMzPduBody = ((MzMultimediaMessagePdu)((MzPduCacheEntry)localObject1).getPdu()).getBody();
          if (localMzPduBody != null)
          {
            if ((localvv.m()) || (localvv.E()))
            {
              int j = localMzPduBody.getPartsNum();
              Object localObject2 = null;
              i = 0;
              localObject1 = localObject2;
              if (i < j)
              {
                localObject1 = localMzPduBody.getPart(i);
                String str = new String(((MzPduPart)localObject1).getContentType());
                if (("text/plain".equals(str)) || ("application/smil".equals(str)) || ("text/html".equals(str))) {
                  break label196;
                }
                localObject1 = ((MzPduPart)localObject1).getDataUri();
              }
              localvv.a((Uri)localObject1);
              a(localvv, 4, false);
              bool = true;
              break;
            }
            bool = a(localMzPduBody);
          }
        }
      }
      catch (Exception localException)
      {
        bool = false;
      }
      return false;
      label196:
      i += 1;
    }
    return bool;
  }
  
  public void D()
  {
    if (M == null) {}
    vv localvv;
    boolean bool;
    do
    {
      return;
      localvv = M;
      if ((wd.b(U)) || (wd.c(U))) {}
      for (bool = true;; bool = false)
      {
        Log.i("MessageListItem", "downloadFlymeMms haveNetwork = " + bool);
        long l = wd.h();
        if (l >= C) {
          break;
        }
        ((ComposeMessageActivity)U).a(this, wd.c(U, l));
        return;
      }
    } while (!bool);
    Intent localIntent = new Intent(U, FlymeTransactionService.class);
    localIntent.putExtra("bundle_uri", t.toString());
    localIntent.putExtra("type", 1);
    if (U.startService(localIntent) != null)
    {
      new Thread(new adk(this, localvv)).start();
      return;
    }
    Log.e("MessageListItem", "can't start service transactionservice");
  }
  
  public boolean E()
  {
    int i = 0;
    boolean bool1 = true;
    if (M == null) {
      bool1 = false;
    }
    vv localvv;
    label255:
    do
    {
      return bool1;
      localvv = M;
      if ((d(1)) || (c(1)))
      {
        if (5 == aac.a(T)) {}
        for (bool1 = true;; bool1 = false)
        {
          l = aac.b(T);
          boolean bool2 = aac.b(l);
          Log.d("MessageListItem", "isSIMReady = " + bool1 + ", isSubIdValid = " + bool2 + ", subId = " + l + ", msgItem.mSlotId = " + T);
          if ((!bool1) || (!bool2) || (!wd.c(U, T))) {
            break label255;
          }
          localIntent = new Intent(U, TransactionService.class);
          localIntent.putExtra("bundle_uri", t.toString());
          localIntent.putExtra("type", 1);
          localIntent.putExtra("subscription", l);
          if (U.startService(localIntent) == null) {
            break;
          }
          new Thread(new adl(this, localvv)).start();
          return true;
        }
        Log.e("MessageListItem", "can't start service transactionservice");
        return true;
        wd.a(2131492945, U, 0, 1, true, 0, aaa.b());
        return true;
      }
      if ((!d(3)) && (!c(3))) {
        break;
      }
      if ((wd.b(U)) || (wd.c(U))) {
        i = 1;
      }
      long l = wd.h();
      if (l < C)
      {
        ((ComposeMessageActivity)U).a(this, wd.c(U, l));
        return true;
      }
    } while (i == 0);
    Intent localIntent = new Intent(U, SnsTransactionService.class);
    localIntent.putExtra("bundle_uri", t.toString());
    localIntent.putExtra("type", 1);
    if (U.startService(localIntent) != null)
    {
      new Thread(new adm(this, localvv)).start();
      return true;
    }
    Log.e("MessageListItem", "can't start service transactionservice");
    return true;
    if ((d(2)) || (c(2)))
    {
      D();
      return true;
    }
    if ((localvv.o()) || (localvv.F())) {
      b(true);
    }
    do
    {
      return false;
      if (localvv.U())
      {
        F();
        return true;
      }
      if ((e(2)) || (ab))
      {
        a(U, localvv);
        return true;
      }
      if ((localvv.s()) && ((localvv.y()) || (localvv.C())))
      {
        a(U, localvv);
        return true;
      }
    } while ((localvv.l()) || (localvv.n()) || (localvv.B()) || (v == 5) || (!ae));
    if (localvv.k())
    {
      C();
      return true;
    }
    wd.e(U, Z);
    return true;
  }
  
  protected void F()
  {
    if (U == null) {}
    Uri localUri;
    do
    {
      return;
      localUri = acu.a(U, M.V());
    } while (localUri == null);
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addFlags(1);
    localIntent.addFlags(524288);
    localIntent.setDataAndType(localUri, "text/x-vcard");
    try
    {
      U.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      wd.a(2131493620, U, 0, 1, true, 0);
    }
  }
  
  protected void G() {}
  
  protected void H() {}
  
  protected void I()
  {
    if ((w == null) || (M == null)) {
      return;
    }
    ag = new AnimatorSet();
    if (w.getBackground() != null)
    {
      aek localaek = new aek(new Drawable[] { w.getBackground(), getSelectedBackgroundResource() });
      w.setBackground(localaek);
      ag.play(a(localaek));
    }
    for (;;)
    {
      ag.addListener(new adn(this));
      return;
      w();
    }
  }
  
  protected void J()
  {
    if ((M == null) || (w == null)) {}
    do
    {
      return;
      if (ag == null)
      {
        I();
        ag.start();
        return;
      }
    } while (ag.isStarted());
    ag.start();
  }
  
  protected void K()
  {
    T = ((ael)ael.a());
  }
  
  protected ObjectAnimator a(Object paramObject)
  {
    paramObject = ObjectAnimator.ofInt(paramObject, "alpha", new int[] { 0, 0, 0, 255 });
    ((ObjectAnimator)paramObject).setInterpolator(new AccelerateDecelerateInterpolator());
    ((ObjectAnimator)paramObject).setDuration(1800L);
    return (ObjectAnimator)paramObject;
  }
  
  protected CharSequence a(String paramString1, Pattern paramPattern, String paramString2, boolean paramBoolean)
  {
    paramPattern = new SpannableStringBuilder();
    if (!TextUtils.isEmpty(paramString1))
    {
      if ((paramString2 == null) || (!"text/html".equals(paramString2))) {
        break label40;
      }
      paramPattern.append(Html.fromHtml(paramString1));
    }
    label40:
    do
    {
      return paramPattern;
      paramPattern.append(paramString1);
    } while ((!M.D()) && (!M.E()));
    Linkify.addLinks(paramPattern, 15);
    aaz.a(paramPattern);
    return paramPattern;
  }
  
  public void a()
  {
    Object localObject = U.getResources();
    if ((M.C()) || (M.y())) {
      M.k = ((Resources)localObject).getString(2131493108);
    }
    while (!M.ah())
    {
      t.setText(M.k);
      return;
    }
    Date localDate = new Date(M.V);
    if (DateFormat.is24HourFormat(U)) {}
    for (int i = 2131493808;; i = 2131493809)
    {
      localObject = ((Resources)localObject).getString(2131493810, new Object[] { new SimpleDateFormat(((Resources)localObject).getString(i)).format(localDate) });
      M.k = ((String)localObject);
      break;
    }
  }
  
  public void a(int paramInt) {}
  
  public void a(Rect paramRect)
  {
    super.getHitRect(paramRect);
  }
  
  public void a(Uri paramUri, String paramString, Map<String, ?> paramMap) {}
  
  protected void a(View paramView, int paramInt)
  {
    if ((paramView == null) || (!paramView.isShown())) {}
    do
    {
      do
      {
        do
        {
          return;
          switch (paramInt)
          {
          default: 
            return;
          case 0: 
            if (paramView.isClickable())
            {
              paramView.setOnClickListener(null);
              paramView.setClickable(false);
            }
            break;
          }
        } while (!paramView.isLongClickable());
        paramView.setOnLongClickListener(null);
        paramView.setLongClickable(false);
        return;
      } while (!paramView.isClickable());
      paramView.setOnClickListener(null);
      paramView.setClickable(false);
      return;
    } while (!paramView.isLongClickable());
    paramView.setOnLongClickListener(null);
    paramView.setLongClickable(false);
  }
  
  public void a(String paramString, long paramLong) {}
  
  public void a(String paramString, Bitmap paramBitmap) {}
  
  public void a(String paramString, Uri paramUri) {}
  
  public void a(String paramString1, String paramString2) {}
  
  public void a(vv paramvv)
  {
    u.setVisibility(8);
    switch (paramvv.r())
    {
    default: 
      if (paramvv.C()) {
        u.setVisibility(8);
      }
      break;
    }
    for (;;)
    {
      b(paramvv);
      c(paramvv);
      d(paramvv);
      return;
      if (paramvv.A())
      {
        u.setText(2131493331);
        u.setVisibility(0);
      }
      else if (paramvv.y())
      {
        u.setVisibility(8);
      }
      else if (paramvv.C())
      {
        u.setVisibility(8);
      }
      else if (paramvv.z())
      {
        u.setText(2131493328);
        u.setVisibility(0);
        u.setTextColor(c);
      }
      else if (paramvv.B())
      {
        u.setVisibility(8);
      }
      else if (paramvv.p())
      {
        if (e(2))
        {
          u.setVisibility(8);
        }
        else
        {
          u.setVisibility(8);
          continue;
          if (paramvv.C())
          {
            u.setVisibility(8);
          }
          else if (((paramvv.x()) && (paramvv.z())) || (h == vv.a.c))
          {
            u.setText(2131493328);
            u.setTextColor(c);
            u.setVisibility(0);
          }
          else if ((paramvv.A()) && (paramvv.B()))
          {
            u.setText(2131493331);
            u.setVisibility(0);
          }
          else if (paramvv.y())
          {
            u.setVisibility(8);
          }
          else if (e(3))
          {
            u.setVisibility(8);
          }
          else if (d(3))
          {
            u.setText(2131493328);
            u.setVisibility(0);
            u.setTextColor(c);
            continue;
            if (((paramvv.x()) && (paramvv.z())) || (h == vv.a.c))
            {
              u.setText(2131493328);
              u.setVisibility(0);
              u.setTextColor(c);
            }
            else if ((paramvv.A()) && (paramvv.B()))
            {
              u.setText(2131493331);
              u.setVisibility(0);
            }
            else if (paramvv.y())
            {
              u.setVisibility(8);
            }
            else if (e(1))
            {
              u.setVisibility(8);
            }
            else if (d(1))
            {
              u.setText(2131493769);
              u.setVisibility(0);
              u.setTextColor(c);
            }
          }
        }
      }
    }
  }
  
  protected void a(vv paramvv, int paramInt, boolean paramBoolean)
  {
    Message localMessage;
    if (L != null)
    {
      localMessage = Message.obtain(L, paramInt);
      if (!paramBoolean) {
        break label41;
      }
    }
    label41:
    for (paramInt = 1;; paramInt = 0)
    {
      arg1 = paramInt;
      obj = paramvv;
      localMessage.sendToTarget();
      return;
    }
  }
  
  public void a(vv paramvv, int paramInt1, boolean paramBoolean, int paramInt2)
  {
    Message localMessage;
    if (L != null)
    {
      localMessage = Message.obtain(L, paramInt1);
      if (!paramBoolean) {
        break label48;
      }
    }
    label48:
    for (paramInt1 = 1;; paramInt1 = 0)
    {
      arg1 = paramInt1;
      arg2 = paramInt2;
      obj = paramvv;
      localMessage.sendToTarget();
      return;
    }
  }
  
  public void a(vv paramvv, boolean paramBoolean1, int paramInt, MessageListView paramMessageListView, boolean paramBoolean2)
  {
    M = paramvv;
    s = paramMessageListView;
    O = paramInt;
    Q = paramBoolean1;
    o();
    e(paramvv);
  }
  
  protected boolean a(Uri paramUri)
  {
    String str = wd.e();
    Intent localIntent = new Intent();
    localIntent.addFlags(524288);
    localIntent.setAction("com.meizu.action.SAVE_FILE");
    localIntent.putExtra("init_directory", str);
    localIntent.putExtra("SAVEATTACHMENT", true);
    localIntent.putExtra("android.intent.extra.TITLE", M.x);
    localIntent.putExtra("android.intent.extra.STREAM", paramUri.toString());
    localIntent.putExtra("title", U.getString(2131493088));
    ((Activity)U).startActivityForResult(localIntent, 110);
    return true;
  }
  
  protected boolean a(MzPduBody paramMzPduBody)
  {
    int j = paramMzPduBody.getPartsNum();
    int i = 0;
    boolean bool = true;
    String str2;
    String str1;
    lq locallq;
    if (i < j)
    {
      MzPduPart localMzPduPart = paramMzPduBody.getPart(i);
      str2 = new String(localMzPduPart.getContentType());
      str1 = str2;
      if (str2.equals("application/oct-stream"))
      {
        str1 = str2;
        if (M.B.size() == 1)
        {
          locallq = M.B.a(0);
          str1 = str2;
          if (locallq != null)
          {
            if (!locallq.e()) {
              break label157;
            }
            str1 = "image/*";
          }
        }
      }
      label104:
      if ((!MzContentType.isImageType(str1)) && (!MzContentType.isVideoType(str1)) && (!MzContentType.isAudioType(str1)) && (!MzContentType.isVcardType(str1))) {
        break label212;
      }
      bool = a(localMzPduPart.getDataUri()) & bool;
    }
    label157:
    label212:
    for (;;)
    {
      i += 1;
      break;
      if (locallq.g())
      {
        str1 = "video/*";
        break label104;
      }
      if (locallq.f())
      {
        str1 = "audio/*";
        break label104;
      }
      str1 = str2;
      if (!locallq.i()) {
        break label104;
      }
      str1 = "text/x-vCard";
      break label104;
      return bool;
    }
  }
  
  public void b()
  {
    boolean bool2 = M.j();
    boolean bool1;
    if (U).f < 0L)
    {
      bool1 = true;
      if (bool2 == bool1) {
        break label36;
      }
    }
    label36:
    do
    {
      return;
      bool1 = false;
      break;
      if ((M.j()) && (M.M() == -U).f))
      {
        J();
        U).f = 0L;
        U).g = null;
        return;
      }
    } while ((!M.q()) || (TextUtils.isEmpty(M.N())) || (!M.N().equals(U).g)));
    J();
    U).f = 0L;
    U).g = null;
  }
  
  public void b(int paramInt) {}
  
  public void b(Uri paramUri, String paramString, Map<String, ?> paramMap) {}
  
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
        B.setImageResource(2130838190);
        B.setVisibility(0);
        return;
      }
      catch (OutOfMemoryError paramString)
      {
        Log.e("MessageListItem", "setVideo: out of memory: ", paramString);
        return;
      }
      B.setImageBitmap(paramBitmap);
    }
  }
  
  protected void b(vv paramvv)
  {
    if (f == null) {}
    do
    {
      return;
      if ((paramvv.u()) && (paramvv.B()))
      {
        if (K == null)
        {
          K = LayoutInflater.from(U).inflate(2130968671, f, false);
          f.addView(K);
        }
        K.setVisibility(0);
        K.setOnClickListener(this);
        wd.a(K, (View)K.getParent(), 90, 8, 54, 10);
        return;
      }
    } while (K == null);
    K.setVisibility(8);
    a(K, 1);
  }
  
  protected void b(boolean paramBoolean) {}
  
  public boolean b(View paramView)
  {
    return paramView.getVisibility() == 0;
  }
  
  public void c()
  {
    if (M == null) {
      return;
    }
    if (M.B()) {
      w.setBackgroundResource(2130837580);
    }
    for (;;)
    {
      w.getBackground().jumpToCurrentState();
      return;
      w.setBackgroundResource(2130837576);
    }
  }
  
  public void c(Uri paramUri, String paramString, Map<String, ?> paramMap) {}
  
  protected void c(vv paramvv)
  {
    if ((paramvv.z()) && (!s.m()))
    {
      if (b == null) {
        b = g(2131886610);
      }
      b.setVisibility(0);
      b.setOnClickListener(this);
    }
    while (b == null) {
      return;
    }
    b.setVisibility(8);
    a(b, 1);
  }
  
  protected boolean c(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      if ((M == null) || (!M.l()) || ((M.O() != 0) && (M.O() != 128))) {
        break;
      }
    case 2: 
    case 3: 
      do
      {
        do
        {
          return true;
        } while ((M != null) && (M.n()) && ((M.O() == 0) || (M.O() == 128) || (M.O() == 132)));
        return false;
      } while ((M != null) && (M.G()) && ((M.O() == 0) || (M.O() == 128)));
      return false;
    }
    return false;
  }
  
  public void d() {}
  
  protected void d(vv paramvv)
  {
    if ((f == null) || (U == null) || (s == null)) {}
    do
    {
      return;
      if (paramvv.ak())
      {
        if (af == null) {
          af = ((ImageView)findViewById(2131886606));
        }
        paramvv = (ViewGroup.MarginLayoutParams)af.getLayoutParams();
        if (s.m()) {}
        for (rightMargin = U.getResources().getDimensionPixelSize(2131558755);; rightMargin = U.getResources().getDimensionPixelSize(2131558754))
        {
          af.setLayoutParams(paramvv);
          af.setVisibility(0);
          af.setOnClickListener(this);
          return;
        }
      }
    } while (af == null);
    af.setVisibility(8);
    a(af, 1);
  }
  
  protected boolean d(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      if ((M == null) || (!M.l()) || ((M.O() != 130) && (M.O() != 135))) {
        break;
      }
    case 2: 
    case 3: 
      do
      {
        do
        {
          return true;
        } while ((M != null) && (M.n()) && ((M.O() == 130) || (M.O() == 135)));
        return false;
      } while ((M != null) && (M.G()) && ((M.O() == 130) || (M.O() == 130)));
      return false;
    }
    return false;
  }
  
  protected void dispatchDraw(Canvas paramCanvas)
  {
    if (MmsApp.a) {
      zl.a(this);
    }
    super.dispatchDraw(paramCanvas);
  }
  
  public void e()
  {
    if (u != null) {
      u.setTextColor(d);
    }
  }
  
  protected boolean e(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      if ((M == null) || (!M.l()) || ((M.O() != 131) && (M.O() != 129))) {
        break;
      }
    case 2: 
    case 3: 
      do
      {
        do
        {
          return true;
        } while ((M != null) && (M.n()) && ((M.O() == 131) || (M.O() == 129)));
        return false;
      } while ((M != null) && (M.G()) && ((M.O() == 131) || (M.O() == 129)));
      return false;
    }
    return false;
  }
  
  public void f() {}
  
  protected void g() {}
  
  public TextView getContentTextView()
  {
    return z;
  }
  
  public View getDragView()
  {
    s.setDragView(this);
    return w;
  }
  
  public void getHitRect(Rect paramRect)
  {
    super.getHitRect(paramRect);
    if ((s != null) && (s.m())) {
      return;
    }
    Rect localRect1 = new Rect();
    w.getHitRect(localRect1);
    Rect localRect2 = new Rect();
    x.getHitRect(localRect2);
    int i = left;
    int j = left;
    int k = top;
    localRect1.offset(i + j, top + k);
    if ((M != null) && (M.B()))
    {
      q = (left - Math.max(left, left - 20));
      r = (top - Math.max(top, top - 10));
      localRect1.set(Math.max(left, left - 20), Math.max(top, top - 10), Math.min(right, right + 10), Math.min(bottom, bottom + 10));
    }
    for (;;)
    {
      paramRect.set(localRect1);
      return;
      q = (left - Math.max(left, left - 10));
      r = (top - Math.max(top, top - 10));
      localRect1.set(Math.max(left, left - 10), Math.max(top, top - 10), Math.min(right, right + 20), Math.min(bottom, bottom + 10));
    }
  }
  
  public View getMessageBody()
  {
    return w;
  }
  
  public vv getMessageItem()
  {
    return M;
  }
  
  protected Drawable getSelectedBackgroundResource()
  {
    if (M.B()) {
      return getResources().getDrawable(2130837578);
    }
    return getResources().getDrawable(2130837574);
  }
  
  public void h() {}
  
  public void i() {}
  
  public void j() {}
  
  public void k() {}
  
  public void l() {}
  
  public void m() {}
  
  public void n() {}
  
  public boolean needBackground()
  {
    return false;
  }
  
  public void onClick(View paramView)
  {
    vv localvv = M;
    switch (paramView.getId())
    {
    default: 
    case 2131886469: 
    case 2131886475: 
      do
      {
        do
        {
          return;
        } while (!localvv.u());
        if (aa == null)
        {
          aa = new uo();
          paramView = new Bundle();
          paramView.putLong("message_id", e);
          paramView.putString("message_type", d);
          paramView.putInt("message_protocol", L);
          paramView.putBoolean("message_is_sms", localvv.q());
          paramView.putLong("mms_size", C);
          paramView.putLong("message_thread_id", f);
          paramView.putString("message_group_id", localvv.N());
          paramView.putInt("message_sim_id", ((ComposeMessageActivity)U).c(U));
          aa.a(U, paramView);
        }
        for (;;)
        {
          aa.a();
          return;
          aa.a(((ComposeMessageActivity)U).c(U));
        }
      } while (!localvv.z());
      ((ComposeMessageActivity)U).a(this);
      return;
    }
    paramView = new Intent(U, FullScreenBrowsingActivity.class);
    paramView.putExtra("fullScreenBrowsingText", M.o);
    U.startActivity(paramView);
    ((ComposeMessageActivity)U).overridePendingTransition(aau.f("mz_edit_new_open_enter"), aau.f("mz_edit_new_open_exit"));
  }
  
  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if ((S != null) && (S.b())) {
      S.a();
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    e();
    if (R != null) {
      R = null;
    }
    if (N != null) {
      N = null;
    }
    if (P != null) {
      P = null;
    }
    if (S != null) {
      S = null;
    }
  }
  
  public void onFinishInflate()
  {
    super.onFinishInflate();
    t = ((TextView)findViewById(2131886602));
    u = ((TextView)findViewById(2131886605));
    w = ((ViewGroup)findViewById(2131886454));
    x = ((ViewGroup)findViewById(2131886600));
    a = ((ImageView)findViewById(2131886604));
    if (c == 0)
    {
      c = U.getResources().getColor(2131820650);
      d = U.getResources().getColor(2131820744);
      g = U.getResources().getDimensionPixelSize(2131558692);
    }
    f = ((LinearLayout)findViewById(2131886601));
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (s.g())) {
      return true;
    }
    return super.onInterceptTouchEvent(paramMotionEvent);
  }
  
  public boolean onLongClick(View paramView)
  {
    if (!s.g())
    {
      s.a(this, vx.a(M.j(), M.e));
      return true;
    }
    return false;
  }
  
  protected void p() {}
  
  protected void q() {}
  
  protected void r() {}
  
  protected void s() {}
  
  public void setEnableCheckBox(boolean paramBoolean)
  {
    int j = 0;
    if (e != null)
    {
      localCheckBox = e;
      if (paramBoolean)
      {
        i = 0;
        localCheckBox.setVisibility(i);
        i = j;
        if (paramBoolean) {
          i = g;
        }
        f(i);
      }
    }
    while (!paramBoolean) {
      for (;;)
      {
        CheckBox localCheckBox;
        return;
        int i = 8;
      }
    }
    if (M.w()) {}
    for (e = ((CheckBox)LayoutInflater.from(U).inflate(2130968611, x, false));; e = ((CheckBox)LayoutInflater.from(U).inflate(2130968612, x, false)))
    {
      x.addView(e);
      f(g);
      return;
    }
  }
  
  public void setImageRegionFit(String paramString) {}
  
  public void setImageVisibility(boolean paramBoolean) {}
  
  public void setMsgListItemHandler(Handler paramHandler)
  {
    L = paramHandler;
  }
  
  public void setNumberCheckState(int paramInt) {}
  
  public void setOnClickListener(vv paramvv) {}
  
  public void setSelected(boolean paramBoolean)
  {
    super.setSelected(paramBoolean);
    c();
  }
  
  public void setTalkBackground(boolean paramBoolean)
  {
    if ((I == null) || (M == null) || (J == null) || (H == null)) {
      return;
    }
    if (paramBoolean)
    {
      localImageView = I;
      if (M.B()) {}
      for (i = 2130837679;; i = 2130837678)
      {
        localImageView.setImageResource(i);
        G();
        return;
      }
    }
    H();
    ImageView localImageView = I;
    if (M.B()) {}
    for (int i = 2130837681;; i = 2130837680)
    {
      localImageView.setImageResource(i);
      return;
    }
  }
  
  public void setTextVisibility(boolean paramBoolean) {}
  
  public void setUnderlineFuncState(int paramInt) {}
  
  public void setVideoVisibility(boolean paramBoolean) {}
  
  public void setVisibility(boolean paramBoolean) {}
  
  protected void t() {}
  
  protected void u() {}
  
  protected void v() {}
  
  protected void w() {}
  
  public void x()
  {
    if ((s.g()) || (M == null) || (M.l()) || (M.n()) || (M.o()) || (M.U()) || (TextUtils.isEmpty(M.o)))
    {
      z.setMovementMethod(null);
      z.setLinksClickable(false);
    }
    for (;;)
    {
      if (z.isFocusable()) {
        z.setFocusable(false);
      }
      if (z.isFocusableInTouchMode()) {
        z.setFocusableInTouchMode(false);
      }
      return;
      z.setLinksClickable(true);
      z.setMovementMethod(ael.a());
    }
  }
  
  protected void y() {}
  
  protected void z() {}
  
  public static class a
    implements zy<abm.a>
  {
    protected long a;
    protected final MessageListItemBase b;
    
    public a(MessageListItemBase paramMessageListItemBase)
    {
      b = paramMessageListItemBase;
      a = paramMessageListItemBase.getMessageItem().M();
    }
    
    public void a(abm.a parama, Throwable paramThrowable)
    {
      paramThrowable = b.M;
      if ((paramThrowable != null) && (paramThrowable.M() == a))
      {
        if (b) {
          b.b(null, a);
        }
      }
      else {
        return;
      }
      b.a(null, a);
    }
    
    public void a(MessageListItemBase paramMessageListItemBase)
    {
      a = paramMessageListItemBase.getMessageItem().M();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MessageListItemBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
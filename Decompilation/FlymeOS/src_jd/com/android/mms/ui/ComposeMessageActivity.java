package com.android.mms.ui;

import aaa;
import aad;
import aan;
import aau;
import aaw;
import aba;
import abh;
import abh.b;
import abm;
import abu;
import aco;
import aco.a;
import acu;
import ael;
import aey;
import afa;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video.Media;
import android.provider.Settings.System;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Draft;
import android.provider.Telephony.Sms;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.MultiChoiceView;
import android.support.v7.widget.Toolbar;
import android.telephony.MzPhoneNumberUtils;
import android.telephony.MzTelephony.SmsExt;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.Time;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import cn.com.xy.sms.sdk.ui.popu.util.XySdkUtil;
import com.android.mms.MmsApp;
import com.android.mms.MmsApp.d;
import com.android.mms.MmsApp.g;
import com.android.mms.TempFileProvider;
import com.android.mms.fragmentstyle.ConversationListFragment.b;
import com.android.mms.recipient.MessageRecipient;
import com.android.mms.smartmessage.SmartMessageViewContainer;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.SmsReceiver;
import com.android.mms.util.TimerMessageHelpler;
import com.android.mms.view.AttachmentGroupView;
import com.android.mms.view.CmaContentFrm;
import com.android.mms.view.EditTextEx;
import com.android.mms.view.MessageListItemBase;
import com.android.mms.view.MmsEllipTextView;
import com.android.mms.view.MmsRichContainer;
import com.android.mms.view.MzContactHeaderWidget;
import com.android.mms.view.NewConsationTitleFrm;
import com.android.mms.view.SwitchImageButton.a;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.MzContentType;
import com.meizu.android.mms.pdu.MzEncodedStringValue;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.pdu.MzSendReq;
import com.meizu.commonwidget.RecipientEdit.RecipientAutoCompleteTextView.a;
import fk;
import fl;
import fm;
import ga;
import gi;
import gk;
import gk.c;
import gm;
import gm.b;
import gq;
import gr;
import gr.b;
import hb;
import hb.a;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import kn;
import lq;
import lr;
import lx;
import mb;
import mq;
import mq.b;
import nd.b;
import nd.c;
import ne;
import nk;
import pj;
import pt;
import qb;
import qc;
import qd;
import qe;
import qf;
import qg;
import qi;
import qj;
import qk;
import ql;
import qm;
import qn;
import qo;
import qp;
import qq;
import qr;
import qs;
import qt;
import qu;
import qv;
import qw;
import qx;
import qy;
import qz;
import ra;
import rb;
import rc;
import rd;
import re;
import rf;
import rg;
import rh;
import ri;
import rj;
import rk;
import rl;
import rm;
import rn;
import ro;
import rp;
import rq;
import rr;
import rs;
import rt;
import ru;
import rv;
import rx;
import ry;
import rz;
import sa;
import sb;
import sc;
import sd;
import se;
import sf;
import sg;
import sh;
import si;
import sj;
import sm;
import sn;
import so;
import sp;
import sr;
import ss;
import st;
import su;
import sv;
import sw;
import sx;
import sy;
import sz;
import ta;
import tb;
import tc;
import te;
import tf;
import tg;
import th;
import ti;
import tj;
import tk;
import tl;
import tm;
import tn;
import to;
import tp;
import tq;
import tr;
import ts;
import tt;
import tu;
import vr;
import vv;
import vv.a;
import vx;
import vx.c;
import wd;
import wd.d;
import xj;
import xv;
import zd;
import zt;
import zv;
import zv.a;

public class ComposeMessageActivity
  extends fm
  implements View.OnClickListener, MmsApp.d, gm.b, hb.a, mq.b, nd.b, nk
{
  private static gq aT;
  private static final String aZ = MediaStore.Video.Media.getContentUri("external").toString();
  private static final String ba = MediaStore.Images.Media.getContentUri("external").toString();
  private static final String[] bi = { "association_id" };
  private static int bm = 1;
  private EditText A;
  private MmsRichContainer B;
  private MessageListView C;
  private MessageRecipient D;
  private boolean E;
  private boolean F;
  private boolean G;
  private boolean H;
  private ProgressDialog I;
  private boolean J;
  private int K;
  private boolean L;
  private Uri M;
  private pt N;
  private String O;
  private int P;
  private boolean Q;
  private boolean R;
  private ProgressDialog S;
  private boolean T;
  private AlertDialog U;
  private AlertDialog V;
  private aco W;
  private View X;
  private NewConsationTitleFrm Y;
  private long Z = 0L;
  public gr a;
  private ImageButton aA;
  private ImageButton aB;
  private View aC;
  private aad aD;
  private Toolbar aE;
  private int aF = -1;
  private boolean aG = false;
  private boolean aH = false;
  private MenuItem aI;
  private MenuItem aJ;
  private int aK = -1;
  private boolean aL = true;
  private final BroadcastReceiver aM = new qb(this);
  private final Handler aN = new ri(this);
  private final Handler aO = new ss(this);
  private Uri aP = null;
  private Uri aQ = null;
  private String aR = "";
  private vv aS;
  private RecipientEdit.RecipientAutoCompleteTextView.a aU = new qj(this);
  private boolean aV = false;
  private int aW = 0;
  private boolean aX = false;
  private final wd.d aY = new re(this);
  private boolean aa = false;
  private View ab;
  private String ac;
  private String ad;
  private AttachmentGroupView ae;
  private mq af;
  private String ag = null;
  private h ah = null;
  private lx ai;
  private View aj;
  private String ak;
  private String al;
  private int am = -1;
  private c an;
  private ImageButton ao;
  private gk ap;
  private String aq;
  private int ar = -1;
  private int as = 2;
  private boolean at = false;
  private boolean au = false;
  private d av;
  private d aw;
  private boolean ax = false;
  private vr ay;
  private xj az;
  public vx b;
  private final TextWatcher bb = new rp(this);
  private final TextWatcher bc = new rq(this);
  private View.OnFocusChangeListener bd = new ru(this);
  private mb be = new rv(this);
  private Time bf;
  private final vx.c bg = new sb(this);
  private final BroadcastReceiver bh = new sf(this);
  private ViewGroup bj;
  private MessageListView.d bk = new sj(this);
  private boolean bl = false;
  private String bn;
  private boolean bo = false;
  private l bp = new l(null);
  private Handler bq = new su(this);
  private SwitchImageButton.a br = new sx(this);
  private SwitchImageButton.a bs = new sy(this);
  private View.OnClickListener bt = new sz(this);
  private xv bu;
  private boolean bv = false;
  private aco.a bw;
  private Runnable bx = new th(this);
  private boolean by = false;
  public hb c;
  public SmartMessageViewContainer d;
  boolean e = false;
  public long f = 0L;
  public String g;
  public boolean h = false;
  protected boolean i = true;
  public MzContactHeaderWidget j;
  public boolean k = false;
  int l = -1;
  Runnable m = new qu(this);
  Runnable n = new qv(this);
  MmsApp.g o = new sc(this);
  public boolean p = true;
  public Handler q = new sp(this);
  g r;
  private ContentResolver s;
  private a t;
  private boolean u;
  private boolean v;
  private boolean w;
  private boolean x = false;
  private EditTextEx y;
  private TextView z;
  
  private void D()
  {
    t().a(new qm(this), new qx(this), 2131492901);
  }
  
  private void E()
  {
    if ((y != null) && (!TextUtils.isEmpty(y.getText())))
    {
      a(y.getText(), 0, 0, 0);
      return;
    }
    z.setText("");
    z.setVisibility(8);
  }
  
  private void F()
  {
    a(null, false, false, false);
  }
  
  private void G()
  {
    int i2 = ga.o();
    int i3;
    if (i2 != Integer.MAX_VALUE)
    {
      i3 = aj();
      if (i3 <= i2) {
        break label78;
      }
    }
    label78:
    for (int i1 = 1;; i1 = 0)
    {
      if (i3 != K)
      {
        K = i3;
        if (i1 != 0) {
          wd.a(getString(2131493148, new Object[] { Integer.valueOf(i3), Integer.valueOf(i2) }), this, 1, 1, true, 0, aN());
        }
      }
      return;
    }
  }
  
  private final void H()
  {
    if (S == null) {
      return;
    }
    if (S.isShowing()) {
      S.dismiss();
    }
    S = null;
  }
  
  private gq I()
  {
    if (X())
    {
      if (aT == null) {
        aT = new gq();
      }
      return aT;
    }
    return a.h();
  }
  
  private boolean J()
  {
    return I().size() > 1;
  }
  
  private void K()
  {
    if (X()) {
      return;
    }
    if (D == null)
    {
      D = ((MessageRecipient)findViewById(2131886229));
      D.setImeOptions(33554432);
    }
    D.setVisibility(0);
    C.setVisibility(8);
    D.setMaxHeight(getResources().getDimensionPixelSize(2131559835));
    D.setOnKeyPreImeListener(aU);
    if (aba.a().b()) {}
    for (int i1 = 3;; i1 = 1)
    {
      D.setInputType(i1);
      D.setButtonClickListener(new qc(this));
      D.setOnFocusChangeListener(bd);
      D.setOnRecipientFirstAddListener(new qd(this));
      D.setOnSipStateChangedListener(new qe(this));
      D.setOnTextChangedListener(new qf(this));
      D.setOnSingleRecipientAddWhenGroupListener(new qg(this));
      D.setOnRecipientChangedListener(new qi(this));
      if ((aba.a().b()) && (!r())) {
        D.setSipDestChecker(this);
      }
      L();
      j(true);
      return;
    }
  }
  
  private void L()
  {
    Object localObject = a.h();
    if (localObject != null)
    {
      ArrayList localArrayList = new ArrayList();
      localObject = ((gq)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        localArrayList.add(((gm)((Iterator)localObject).next()).d());
      }
      if (localArrayList.size() > 0)
      {
        b("addRecipient. myList:" + localArrayList.size());
        D.a(localArrayList);
      }
    }
  }
  
  private void M()
  {
    j = ((MzContactHeaderWidget)findViewById(2131886225));
    j.setOnSipStateChangedListener(new qk(this));
  }
  
  private void N()
  {
    ActionBar localActionBar = getSupportActionBar();
    ab = LayoutInflater.from(this).inflate(2130968610, null);
    localActionBar.setDisplayOptions(20);
    localActionBar.setCustomView(ab, new ActionBar.LayoutParams(-1, -1, 19));
    aE = ((Toolbar)findViewById(2131886192));
  }
  
  private void O()
  {
    if (ab == null) {
      N();
    }
  }
  
  private void P()
  {
    C.a();
    if (j != null) {
      j.j();
    }
    a(false, -1);
    H();
    if ((U != null) && (U.isShowing())) {
      U.dismiss();
    }
    U = null;
    if ((I != null) && (I.isShowing())) {
      I.dismiss();
    }
    I = null;
    B.b(false);
  }
  
  private void Q()
  {
    if (c.t() != a) {
      fl.a("ComposeMessageActivity: mWorkingMessage.mConversation=" + c.t() + ", mConversation=" + a + ", MISMATCH!", this);
    }
  }
  
  private void R()
  {
    if (!MmsApp.c().l()) {
      a.c();
    }
    S();
    ad();
  }
  
  private void S()
  {
    long l1 = a.e();
    if (l1 <= 0L) {
      return;
    }
    new Thread(new qo(this, l1), "ComposeMessageActivity.updateSendFailedNotification").start();
  }
  
  private void T()
  {
    if (Y.getVisibility() == 0)
    {
      Y.setVisibility(8);
      aaa.b(getSupportActionBar(), this);
    }
  }
  
  private void U()
  {
    int i1 = 2131493613;
    y.setEnabled(w);
    if (!w)
    {
      if (D != null) {
        D.setFocusableInTouchMode(false);
      }
      if (A != null) {
        A.setFocusableInTouchMode(false);
      }
      y.setFocusableInTouchMode(false);
      y.setHint(2131493571);
      return;
    }
    if (E)
    {
      if (D != null) {
        D.setFocusableInTouchMode(true);
      }
      if (A != null) {
        A.setFocusableInTouchMode(true);
      }
      y.setFocusableInTouchMode(true);
      if (h)
      {
        y.setHint(2131493613);
        return;
      }
      EditTextEx localEditTextEx = y;
      if (MmsApp.n) {}
      for (;;)
      {
        localEditTextEx.setHint(i1);
        return;
        i1 = 2131493153;
      }
    }
    if (D != null) {
      D.setFocusable(false);
    }
    if (A != null) {
      A.setFocusable(false);
    }
    y.setFocusable(false);
    y.setHint(2131493031);
  }
  
  private void V()
  {
    super.finish();
    startActivity(new Intent(this, ConversationList.class));
  }
  
  private void W()
  {
    if (D != null)
    {
      D.setVisibility(8);
      D.d();
      D.setSipDestChecker(null);
      D.setOnSipStateChangedListener(null);
    }
    C.setVisibility(0);
  }
  
  private boolean X()
  {
    return (D != null) && (D.getVisibility() == 0);
  }
  
  private boolean Y()
  {
    return (A != null) && (A.getVisibility() == 0);
  }
  
  private final boolean Z()
  {
    if (c == null) {}
    lr locallr;
    do
    {
      return false;
      locallr = c.i();
    } while ((locallr == null) || (locallr.size() <= 0));
    return locallr.a(0).j();
  }
  
  public static long a(lr paramlr, long paramLong)
  {
    long l2 = ga.f() - 1024;
    long l1 = l2;
    if (paramlr != null) {
      l1 = l2 - paramlr.d() + paramLong;
    }
    return l1;
  }
  
  public static Intent a(Context paramContext, long paramLong)
  {
    paramContext = new Intent(paramContext, ComposeMessageActivity.class);
    if (paramLong > 0L) {
      paramContext.setData(gr.a(paramLong));
    }
    return paramContext;
  }
  
  public static Intent a(Context paramContext, long paramLong, String paramString)
  {
    paramContext = new Intent(paramContext, ComposeMessageActivity.class);
    if (paramLong > 0L) {
      paramContext.setData(gr.a(paramLong));
    }
    if (!TextUtils.isEmpty(paramString)) {
      paramContext.putExtra("conversation_last_imsi", paramString);
    }
    return paramContext;
  }
  
  private Uri a(Intent paramIntent)
  {
    Object localObject1 = paramIntent.getStringExtra("subject");
    int i1 = paramIntent.getIntExtra("mmsprotocl", 1);
    try
    {
      MzSendReq localMzSendReq = new MzSendReq();
      localMzSendReq.setSubject(new MzEncodedStringValue((String)localObject1));
      long l1 = System.currentTimeMillis();
      localMzSendReq.setBody(c.i().b());
      wd.a("forwardmsg", "makeCopy", Long.valueOf(l1).longValue());
      l1 = System.currentTimeMillis();
      MzPduPersister localMzPduPersister = MzPduPersister.getPduPersister(this);
      Object localObject2 = c.i();
      if (i1 == 1)
      {
        paramIntent = new ContentValues(3);
        paramIntent.put("protocol", Integer.valueOf(i1));
        paramIntent.put("slideshow_description", ((lr)localObject2).l());
        paramIntent.put("file_link", "");
      }
      for (;;)
      {
        paramIntent = localMzPduPersister.persist(localMzSendReq, Telephony.Mms.Draft.CONTENT_URI, false, MessagingPreferenceActivity.b(this), null, i1, paramIntent);
        wd.a("forwardmsg", "persist", Long.valueOf(l1).longValue());
        Z = ContentUris.parseId(paramIntent);
        MmsApp.c().a(true);
        return paramIntent;
        localObject1 = new ContentValues(4);
        long l2 = ((lr)localObject2).p();
        if (l2 > 0L) {
          ((ContentValues)localObject1).put("m_size", Long.valueOf(l2));
        }
        ((ContentValues)localObject1).put("protocol", Integer.valueOf(i1));
        ((ContentValues)localObject1).put("slideshow_description", ((lr)localObject2).l());
        localObject2 = ((lr)localObject2).k();
        paramIntent = (Intent)localObject1;
        if (localObject2 != null)
        {
          ((ContentValues)localObject1).put("file_link", Uri.decode(((Uri)localObject2).toString()));
          paramIntent = (Intent)localObject1;
        }
      }
      return null;
    }
    catch (MmsException paramIntent)
    {
      wd.a(2131492907, this, 0, 1, true, 0, aN());
    }
  }
  
  private void a(int paramInt, long paramLong)
  {
    lr locallr = c.i();
    if (locallr == null) {
      throw new IllegalStateException("mWorkingMessage.getSlideshow() == null");
    }
    if (locallr.g())
    {
      wd.a(this, locallr, paramLong);
      return;
    }
    t().a(new rt(this), new se(this, paramInt, paramLong), 2131492901);
  }
  
  private void a(int paramInt, Uri paramUri)
  {
    new Thread(new so(this, paramInt, paramUri)).start();
  }
  
  private void a(int paramInt1, vv paramvv, int paramInt2)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("is_favorite", Integer.valueOf(paramInt1));
    if (paramvv.j())
    {
      t.startUpdate(paramInt2, null, ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, paramvv.a()), localContentValues, null, null);
      return;
    }
    if (Q)
    {
      t.startUpdate(paramInt2, null, Telephony.Sms.CONTENT_URI, localContentValues, "group_msg_id = ?", new String[] { O });
      return;
    }
    t.startUpdate(paramInt2, null, ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, paramvv.a()), localContentValues, null, null);
  }
  
  private void a(long paramLong)
  {
    int i1 = 1;
    boolean bool2 = false;
    aW();
    boolean bool1;
    Object localObject;
    if (u)
    {
      boolean bool3 = getIntent().getBooleanExtra("forwarded_message", false);
      bool1 = bool2;
      if (bool3)
      {
        localObject = new Intent();
        ((Intent)localObject).putExtra("thread_id", paramLong);
        ((Intent)localObject).putExtra("sending_msg_id", Z);
        if (zv.a) {
          ((Intent)localObject).putExtra("conversation_last_imsi", zv.c(am));
        }
        setResult(-1, (Intent)localObject);
        bool1 = bool2;
        if (p())
        {
          a.a(r(), aS());
          T();
          W();
          a(getSupportActionBar());
          a(a.h(), true);
          y.setFilters(new InputFilter[] { new InputFilter.LengthFilter(ga.a(bi())) });
          wd.d();
          a(a.h());
          if (gr.a(a))
          {
            a.a(a.k() + 1);
            x = true;
          }
          bool1 = d(false);
          aG();
        }
      }
      if ((bool1) && (y != null)) {
        y.postDelayed(new sa(this, bool3), 200L);
      }
      for (;;)
      {
        if (aS()) {
          k(c.a().toString());
        }
        return;
        finish();
        if (bool3) {
          overridePendingTransition(2131034154, 2131034154);
        }
      }
    }
    if (p())
    {
      bool1 = r();
      a.a(bool1, aS());
      T();
      W();
      a(getSupportActionBar());
      a(a.h(), true);
      y.setFilters(new InputFilter[] { new InputFilter.LengthFilter(ga.a(bi())) });
      wd.d();
      a.a(a.k() + 1);
      gr.a(a);
      getWindow().setSoftInputMode(18);
      z();
      A();
      localObject = b;
      if (a.h().size() <= 1) {
        break label564;
      }
      bool1 = true;
      label465:
      ((vx)localObject).b(bool1);
      localObject = b;
      if (!bh()) {
        break label570;
      }
      label485:
      ((vx)localObject).a(i1);
      localObject = b;
      if (!abu.a()) {
        break label575;
      }
    }
    label564:
    label570:
    label575:
    for (i1 = abu.b;; i1 = abu.a)
    {
      ((vx)localObject).b(i1);
      aV();
      aG();
      a(a.h());
      B.b(false);
      if (!aS()) {
        break;
      }
      k(c.a().toString());
      return;
      bool1 = false;
      break label465;
      i1 = 0;
      break label485;
    }
  }
  
  private void a(Context paramContext)
  {
    Intent localIntent = new Intent("com.mediatek.intent.action.SWITCH_SVLTE_RAT_MODE");
    Log.d("Mms/compose", "ShowLTEOnlyDialog.");
    paramContext.sendBroadcast(localIntent);
  }
  
  private void a(DialogInterface.OnClickListener paramOnClickListener, boolean paramBoolean)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setCancelable(true);
    if (paramBoolean) {}
    for (int i1 = 2131492920;; i1 = 2131492921)
    {
      localBuilder.setMessage(i1);
      localBuilder.setPositiveButton(2131492930, paramOnClickListener);
      localBuilder.setNegativeButton(2131493022, null);
      localBuilder.show();
      return;
    }
  }
  
  private void a(Uri paramUri)
  {
    c(c.a(3, paramUri, false), 2131493151);
  }
  
  private void a(Uri paramUri, String paramString)
  {
    if ((paramUri == null) || (!paramUri.getAuthority().startsWith("mms"))) {}
    while ((aR.equals(paramString)) || (TextUtils.isEmpty(paramString))) {
      return;
    }
    aS.a(paramString);
    MmsApp.c().e().a(paramUri);
    new Thread(new te(this, paramString, paramUri), "ComposeMessageActivity.updateFlymeMmsAttachPath").start();
  }
  
  private void a(Uri paramUri, boolean paramBoolean)
  {
    t().a(new rg(this, paramUri, paramBoolean), null, 2131493822);
  }
  
  private void a(Uri paramUri, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramUri != null) {
      c(c.a(7, paramUri, paramBoolean1), 2131493610);
    }
  }
  
  private void a(Uri paramUri, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    boolean bool = aba.a().b();
    int i1;
    if ((bool) && (k()))
    {
      i1 = 1;
      if ((!MmsApp.b) || (am != 0) || (!zv.f(this))) {
        break label55;
      }
      a(this);
    }
    label55:
    label289:
    label392:
    label410:
    label923:
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return;
                i1 = 0;
                break;
                if (X()) {
                  break label410;
                }
                if ((i1 == 0) && (j.c()) && (!MmsApp.a))
                {
                  if (!bool) {}
                  for (paramUri = getString(2131493242);; paramUri = getString(2131493243))
                  {
                    U = new AlertDialog.Builder(this).setTitle(paramUri).setPositiveButton(2131493166, new b(null)).show();
                    return;
                  }
                }
                if ((j.d()) && (!pj.a()))
                {
                  U = new AlertDialog.Builder(this).setTitle(getString(2131493249)).setPositiveButton(2131493166, new b(null)).show();
                  return;
                }
                if ((i1 == 0) || (paramBoolean1)) {
                  break label392;
                }
                if (c.b() <= 500) {
                  break label289;
                }
              } while ((V != null) && (V.isShowing()));
              paramUri = getString(2131493307);
              U = new AlertDialog.Builder(this).setTitle(paramUri).setPositiveButton(2131493166, new b(null)).show();
              V = U;
              return;
              localObject = c.e();
              if ((localObject == null) || (localObject.getLastPathSegment().split("\\.")[0].length() <= 100)) {
                break label392;
              }
            } while ((V != null) && (V.isShowing()));
            paramUri = getString(2131493305);
            U = new AlertDialog.Builder(this).setTitle(paramUri).setPositiveButton(2131493166, new b(null)).show();
            V = U;
            return;
          } while (!aX());
          a(true, paramUri, paramBoolean1, paramBoolean2, paramBoolean3);
          return;
          if (D.g())
          {
            paramUri = new AlertDialog.Builder(this);
            paramUri.setTitle(2131492968);
            localObject = (MmsEllipTextView)LayoutInflater.from(this).inflate(2130968766, null);
            ((MmsEllipTextView)localObject).a(D.a(), String.valueOf(D.getInvalidNumbers().size()));
            paramUri.setView((View)localObject);
            paramUri.setNegativeButton(2131493022, new b(null));
            paramUri.setPositiveButton(2131493166, new i(null));
            U = paramUri.show();
            return;
          }
          if ((aS()) && (c.h()))
          {
            wd.a(2131493690, this, 0, 1, true, 0, aN());
            return;
          }
          if ((!MmsApp.a) && (D.b()) && (D.getRecipientCount() > 1))
          {
            f(2);
            return;
          }
          if ((!MmsApp.a) && (D.e()) && (D.getRecipientCount() > 1))
          {
            f(8);
            return;
          }
          if ((!MmsApp.a) && (i1 == 0) && (D.b()))
          {
            if (!bool) {}
            for (paramUri = getString(2131493242);; paramUri = getString(2131493243))
            {
              U = new AlertDialog.Builder(this).setTitle(paramUri).setPositiveButton(2131493166, new b(null)).show();
              return;
            }
          }
          if ((i1 != 0) && (c.j()))
          {
            U = new AlertDialog.Builder(this).setTitle(2131493248).setPositiveButton(2131493166, new b(null)).show();
            return;
          }
          if ((pj.a()) && (D.c()) && (D.getRecipientCount() > 1))
          {
            U = new AlertDialog.Builder(this).setTitle(2131493246).setPositiveButton(2131493166, new b(null)).show();
            return;
          }
        } while (!aX());
        if ((i1 == 0) || (paramBoolean1)) {
          break label1026;
        }
        if (c.b() <= 500) {
          break label923;
        }
      } while ((V != null) && (V.isShowing()));
      paramUri = getString(2131493307);
      U = new AlertDialog.Builder(this).setTitle(paramUri).setPositiveButton(2131493166, new b(null)).show();
      V = U;
      return;
      Object localObject = c.e();
      if ((localObject == null) || (localObject.getLastPathSegment().split("\\.")[0].length() <= 100)) {
        break label1026;
      }
    } while ((V != null) && (V.isShowing()));
    paramUri = getString(2131493305);
    U = new AlertDialog.Builder(this).setTitle(paramUri).setPositiveButton(2131493166, new b(null)).show();
    V = U;
    return;
    label1026:
    a(true, paramUri, paramBoolean1, paramBoolean2, paramBoolean3);
  }
  
  private void a(Bundle paramBundle)
  {
    Object localObject = getIntent();
    if (paramBundle != null)
    {
      setIntent(getIntent().setAction("android.intent.action.VIEW"));
      localObject = paramBundle.getString("recipients");
      if (paramBundle.getBoolean("is_new_conversation", false))
      {
        localObject = gr.a(MmsApp.c(), (String)localObject, false);
        a = ((gr)localObject);
        u = paramBundle.getBoolean("exit_on_sent", false);
        v = paramBundle.getBoolean("forwarded_message", false);
        if (u) {
          C.setVisibility(4);
        }
        c.b(paramBundle);
        if (zv.a)
        {
          i(paramBundle.getString("selected_imsi"));
          be();
        }
      }
    }
    long l1;
    do
    {
      return;
      localObject = gr.a(MmsApp.c(), gq.a((String)localObject, false, true), false);
      break;
      l1 = ((Intent)localObject).getLongExtra("thread_id", 0L);
      if (l1 <= 0L) {
        break label332;
      }
      a = gr.a(MmsApp.c(), l1, false);
      if (((Intent)localObject).getBooleanExtra("init_imsi_from_converation", false)) {
        al = a.x();
      }
      y.setFilters(new InputFilter[] { new InputFilter.LengthFilter(ga.a(bi())) });
      as();
      u = ((Intent)localObject).getBooleanExtra("exit_on_sent", false);
      v = ((Intent)localObject).getBooleanExtra("forwarded_message", false);
      if (u) {
        C.setVisibility(4);
      }
      if (((Intent)localObject).hasExtra("sms_body")) {
        c.a(((Intent)localObject).getStringExtra("sms_body"));
      }
      c.a(((Intent)localObject).getStringExtra("subject"), false);
    } while (!zv.a);
    b((Intent)localObject);
    be();
    return;
    label332:
    Uri localUri = ((Intent)localObject).getData();
    if (localUri != null)
    {
      paramBundle = ((Intent)localObject).getExtras();
      if (paramBundle == null) {
        break label530;
      }
    }
    label530:
    for (paramBundle = paramBundle.getStringArrayList("contacts_selected");; paramBundle = null)
    {
      if ((localUri.getScheme().equals("smsto")) && (paramBundle != null))
      {
        l1 = System.currentTimeMillis();
        a = gr.a(MmsApp.c());
        wd.a(null, "Conversation.createNew(this)", Long.valueOf(l1).longValue());
        e = true;
        break;
      }
      a = gr.a(MmsApp.c(), localUri, false, false);
      c.a(b(localUri));
      if (!localUri.getScheme().equals("smsto")) {
        break;
      }
      e = true;
      al = a.x();
      break;
      paramBundle = ((Intent)localObject).getStringExtra("address");
      if (!TextUtils.isEmpty(paramBundle)) {}
      for (a = gr.a(MmsApp.c(), gq.a(paramBundle, false, true), false);; a = gr.a(MmsApp.c()))
      {
        e = true;
        break;
      }
    }
  }
  
  private void a(ActionBar paramActionBar)
  {
    paramActionBar = aE.getLayoutParams();
    if (p()) {}
    for (height = getResources().getDimensionPixelSize(2131559402);; height = getResources().getDimensionPixelSize(2131558825))
    {
      aE.setLayoutParams(paramActionBar);
      return;
    }
  }
  
  private void a(f paramf, int paramInt)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    int i1;
    if (paramf == f.c)
    {
      i1 = 2;
      y.requestFocus();
      k(i1);
      localInputMethodManager.showSoftInput(y, 0, null);
      paramf = y.getText();
      i1 = y.getSelectionStart();
      paramInt = i1;
      if (i1 < 0) {
        paramInt = paramf.length();
      }
      Selection.setSelection(paramf, paramInt);
    }
    do
    {
      return;
      if (paramf == f.d)
      {
        i1 = 4;
        break;
      }
      if (paramf == f.b)
      {
        i1 = 1;
        break;
      }
      if (paramf == f.a)
      {
        if ((paramInt == 2) && (aW == 2))
        {
          i1 = 1;
          break;
        }
        if (paramInt == 2)
        {
          i1 = 2;
          break;
        }
        if ((paramInt == 4) && (aW == 4))
        {
          i1 = 1;
          break;
        }
        i1 = paramInt;
        if (paramInt != 4) {
          break;
        }
        i1 = 4;
        break;
      }
      i1 = paramInt;
      if (paramf != f.e) {
        break;
      }
      be.a(localInputMethodManager);
    } while ((!be.c()) && (!aX));
    aX = false;
    k(0);
    localInputMethodManager.hideSoftInputFromWindow(y.getWindowToken(), 0);
  }
  
  private void a(gq paramgq)
  {
    if (paramgq == null) {}
    for (O = "";; O = paramgq.a())
    {
      O();
      return;
    }
  }
  
  private void a(gq paramgq, boolean paramBoolean)
  {
    int i2 = paramgq.size();
    int i1;
    if (i2 > 1)
    {
      i1 = 1;
      aK = i1;
      switch (i2)
      {
      default: 
        j.a(paramgq);
      }
    }
    for (;;)
    {
      if (paramBoolean) {
        h(true);
      }
      return;
      i1 = 0;
      break;
      j.setDisplayName("");
      continue;
      j.a((gm)paramgq.get(0));
    }
  }
  
  private void a(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((!MmsApp.a) && (z.getVisibility() == 8) && (TextUtils.isEmpty(paramCharSequence))) {}
    while (r()) {
      return;
    }
    hb localhb = c;
    if (localhb.u())
    {
      if (MmsApp.a)
      {
        if (paramInt2 > paramInt3) {}
        for (paramInt1 = 1; paramInt1 == 0; paramInt1 = 0)
        {
          z.setText(getString(2131493019));
          z.setVisibility(0);
          return;
        }
      }
      z.setVisibility(8);
      return;
    }
    if (j())
    {
      z.setVisibility(8);
      return;
    }
    paramCharSequence = SmsMessage.calculateLength(paramCharSequence, false);
    paramInt2 = paramCharSequence[0];
    paramInt3 = paramCharSequence[2];
    boolean bool;
    if (MmsApp.a)
    {
      if (ga.t()) {
        break label247;
      }
      paramCharSequence = c;
      if (paramInt2 > 1)
      {
        bool = true;
        paramCharSequence.a(bool, true);
      }
    }
    else
    {
      if ((localhb.u()) || ((paramInt2 <= 1) && (paramInt3 > 10))) {
        break label350;
      }
    }
    label247:
    label350:
    for (paramInt1 = 1;; paramInt1 = 0)
    {
      if (paramInt1 != 0)
      {
        if (paramInt2 > 1) {}
        for (paramCharSequence = paramInt3 + "/" + paramInt2;; paramCharSequence = String.valueOf(paramInt3))
        {
          z.setText(paramCharSequence);
          z.setVisibility(0);
          return;
          bool = false;
          break;
          paramInt1 = ga.B();
          paramCharSequence = c;
          if ((paramInt1 > 0) && (paramInt2 > paramInt1)) {}
          for (bool = true;; bool = false)
          {
            paramCharSequence.a(bool, true);
            break;
          }
        }
      }
      if ((MmsApp.a) && (localhb.u()))
      {
        z.setText(getString(2131493019));
        z.setVisibility(0);
        return;
      }
      z.setText("");
      z.setVisibility(8);
      return;
    }
  }
  
  private void a(Runnable paramRunnable)
  {
    if (MmsApp.a) {
      bf();
    }
    if (!c.f())
    {
      paramRunnable.run();
      return;
    }
    if (MmsApp.a) {
      G = true;
    }
    for (;;)
    {
      paramRunnable.run();
      return;
      if ((X()) && (c.u()))
      {
        if (!v) {
          c.p();
        }
        for (;;)
        {
          MmsApp.c().a(true);
          if (c.b() <= 0) {
            break;
          }
          G = true;
          break;
          c.b(ContentUris.withAppendedId(Uri.parse("content://mms/forward-drafts"), Z));
        }
      }
      G = true;
    }
  }
  
  private void a(String paramString, Uri paramUri, boolean paramBoolean)
  {
    boolean bool;
    if (paramUri != null)
    {
      bool = "*/*".equals(paramString);
      if ((paramString.startsWith("image/")) || ((bool) && (paramUri.toString().startsWith(ba)))) {
        b(paramUri, paramBoolean);
      }
    }
    else
    {
      return;
    }
    if ((paramString.startsWith("video/")) || ((bool) && (paramUri.toString().startsWith(aZ))))
    {
      d(paramUri, paramBoolean);
      return;
    }
    if (paramString.startsWith("audio/"))
    {
      a(paramUri);
      return;
    }
    if (paramString.equals("text/x-vcard"))
    {
      if (bo)
      {
        new acu(this).a(paramUri, bp);
        return;
      }
      paramString = paramUri;
      if (paramUri.getScheme().equals("content")) {
        paramString = acu.a(this, paramUri, l());
      }
      f(paramString, false);
      return;
    }
    if (!TextUtils.isEmpty(paramString))
    {
      Log.v("Mms/compose", "Flyme MMS type is: " + paramString);
      a(paramUri, false, true);
      return;
    }
    runOnUiThread(new rm(this));
  }
  
  private void a(HashMap<Long, j> paramHashMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramHashMap.size() == 0) {}
    label88:
    do
    {
      return;
      paramHashMap = c(paramHashMap);
      int i1 = 0;
      for (;;)
      {
        if (i1 >= paramHashMap.size()) {
          break label88;
        }
        vv localvv = getc;
        if (localvv == null) {
          break;
        }
        if (!TextUtils.isEmpty(o)) {
          localStringBuilder.append(o).append("\n");
        }
        i1 += 1;
      }
    } while (localStringBuilder.length() == 0);
    g(localStringBuilder.substring(0, localStringBuilder.length() - 1));
  }
  
  private void a(HashMap<Long, j> paramHashMap, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i2 = paramHashMap.size();
    if (i2 == 0) {}
    vv[] arrayOfvv;
    int i1;
    vv localvv;
    do
    {
      return;
      arrayOfvv = new vv[i2];
      List localList = c(paramHashMap);
      i1 = 0;
      if (i1 >= i2) {
        break;
      }
      localvv = getc;
    } while (localvv == null);
    arrayOfvv[i1] = localvv;
    if ((MmsApp.a) && (i2 > 1))
    {
      if (!localvv.v()) {
        localStringBuilder.append(m).append("\n");
      }
      Date localDate = new Date(N);
      localStringBuilder.append(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(localDate)).append("\n");
    }
    if (i1 == i2 - 1) {
      localStringBuilder.append(o);
    }
    for (;;)
    {
      i1 += 1;
      break;
      localStringBuilder.append(o).append("\n");
    }
    if (paramBoolean)
    {
      paramHashMap.clear();
      C.a();
    }
    if ((i2 == 1) && (0d.equals("mms")))
    {
      c(arrayOfvv[0]);
      return;
    }
    e(localStringBuilder.toString());
  }
  
  private void a(boolean paramBoolean, int paramInt)
  {
    if ((k == paramBoolean) && (l == paramInt)) {
      return;
    }
    l = paramInt;
    k = paramBoolean;
    l = paramInt;
    aU();
    runOnUiThread(new ql(this, paramBoolean));
    Log.d("Mms/compose", "setFlyMeStatus(), mFlyMeOpen : " + k);
  }
  
  private void a(boolean paramBoolean1, Uri paramUri, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    if (!c.v())
    {
      if (wd.g())
      {
        h(-6);
        return;
      }
      h(-5);
      return;
    }
    if ((paramBoolean1) && (Boolean.parseBoolean(aau.a("PROPERTY_INECM_MODE")))) {
      try
      {
        startActivityForResult(new Intent((String)aau.a("com.android.internal.telephony.TelephonyIntents", "ACTION_SHOW_NOTICE_ECM_BLOCK_OTHERS"), null), 107);
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        Log.e("Mms/compose", "Cannot find EmergencyCallbackModeExitDialog", localActivityNotFoundException);
      }
    }
    long l1 = 0L;
    Object localObject;
    String str;
    boolean bool2;
    label217:
    label232:
    boolean bool3;
    int i1;
    if (!L)
    {
      localObject = a.h().a();
      if (!((String)localObject).equals(O))
      {
        str = c.n();
        if (!O.equals(str)) {
          fl.a("ComposeMessageActivity.sendMessage recipients in window: \"" + O + "\" differ from recipients from conv: \"" + (String)localObject + "\" and working recipients: " + str, this);
        }
      }
      Q();
      bool2 = aba.a().b();
      boolean bool1;
      label314:
      long l2;
      if ((k()) && (bool2))
      {
        paramBoolean1 = true;
        if ((!l()) || (!bool2)) {
          break label403;
        }
        bool1 = true;
        Log.d("Mms/compose", "send Message -> sipModeSms : " + paramBoolean1 + ", sipModeMms : " + bool1 + ", isSipMessageOpen : " + bool2);
        bool3 = r();
        localObject = c.i();
        if ((localObject == null) || (!c.u())) {
          break label409;
        }
        i1 = 1;
        l2 = 0L;
        l1 = l2;
        if (paramBoolean2) {
          break label459;
        }
        l1 = l2;
        if (i1 == 0) {
          break label459;
        }
        if (!bool3) {
          break label415;
        }
        i1 = 2;
      }
      for (;;)
      {
        ((lr)localObject).d(i1);
        l2 = ((lr)localObject).p();
        if (bool3) {}
        try
        {
          if (l2 > ga.d()) {
            break;
          }
          i1 = ((lr)localObject).n();
          if (i1 == 0) {
            break label432;
          }
          b(paramBoolean1, i1);
          return;
        }
        catch (fk paramUri)
        {
          b(paramBoolean1, 2);
          return;
        }
        paramBoolean1 = false;
        break label217;
        label403:
        bool1 = false;
        break label232;
        label409:
        i1 = 0;
        break label314;
        label415:
        if (bool1) {
          i1 = 1;
        } else {
          i1 = 0;
        }
      }
      label432:
      l1 = l2;
      if (!bool3)
      {
        l1 = l2;
        if (ak())
        {
          b(paramBoolean1, 3);
          return;
        }
      }
      label459:
      a(bool1, paramBoolean1);
      if (!paramBoolean2) {
        break label609;
      }
      if ((!bool1) && (!bool3))
      {
        W.a(paramUri);
        b(paramBoolean1, 3);
        return;
      }
      if (bool3)
      {
        i1 = abh.a(this, true);
        if (i1 != 0)
        {
          W.a(paramUri);
          f(abh.b.a(this, i1, -1));
          return;
        }
      }
      if ((bool2) && (!bool3)) {
        aV = true;
      }
      localObject = c;
      str = O;
      if (!bool3) {
        break label603;
      }
      i1 = 2;
      label567:
      l1 = ((hb)localObject).a(str, paramUri, i1, -10);
    }
    for (;;)
    {
      H = true;
      L = true;
      Q = true;
      a(l1);
      return;
      label603:
      i1 = 1;
      break label567;
      label609:
      if ((paramBoolean1) && (l1 > 10485760L) && (wd.e(this)))
      {
        U = new AlertDialog.Builder(this).setTitle(2131493603).setPositiveButton(2131493326, new rz(this, bool2, bool3)).setNegativeButton(2131493022, new b(null)).show();
        return;
      }
      if (paramBoolean3)
      {
        if ((TextUtils.isEmpty(ac)) || (TextUtils.isEmpty(ad))) {
          break;
        }
        if ((!az()) || (paramUri == null))
        {
          l1 = c.a(ac + "\n" + ad, O, am);
          continue;
        }
        localObject = ad;
        str = ac;
        hb localhb = c;
        if (r()) {}
        for (i1 = 2;; i1 = 1)
        {
          l1 = localhb.a(this, paramUri, new String[] { localObject, str }, i1, -10);
          break;
        }
      }
      if (paramBoolean4)
      {
        l1 = c.a(ag, O, am);
      }
      else
      {
        if ((bool2) && (!bool3)) {
          aV = true;
        }
        c.d(B.f());
        l1 = c.a(O, am);
      }
    }
  }
  
  private void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    int i2 = 0;
    int i1 = 0;
    if (aF == 0)
    {
      c.a(0);
      return;
    }
    if (aF == 1)
    {
      localhb = c;
      if ((paramBoolean2) || (paramBoolean1)) {
        i1 = 1;
      }
      localhb.a(i1);
      return;
    }
    hb localhb = c;
    if (!paramBoolean2)
    {
      i1 = i2;
      if (!paramBoolean1) {}
    }
    else
    {
      i1 = 1;
    }
    localhb.a(i1);
  }
  
  private boolean a(int paramInt1, int paramInt2)
  {
    return (paramInt1 & paramInt2) > 0;
  }
  
  public static boolean a(Intent paramIntent, Context paramContext)
  {
    if (MessagingNotification.a(paramIntent))
    {
      MessagingNotification.a(paramContext, 789);
      return true;
    }
    return false;
  }
  
  private boolean a(Configuration paramConfiguration)
  {
    boolean bool = true;
    if (keyboardHidden == 1) {}
    for (;;)
    {
      E = bool;
      return false;
      bool = false;
    }
  }
  
  private boolean a(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = true;
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 1))
    {
      if ((!p()) || (D == null) || (D.getAllNumbers() != null) || (c.d()) || (c.l()) || (c.h()) || (c.j())) {
        break label90;
      }
      finish();
    }
    label90:
    do
    {
      bool = false;
      do
      {
        return bool;
        if (paramView != y) {
          break;
        }
      } while (C.n());
      aX = wd.g(this);
    } while (!i(aW));
    a(f.b, 0);
    return true;
  }
  
  private boolean a(String paramString, nd.c paramc, int paramInt)
  {
    if (!TextUtils.isEmpty(paramString)) {
      if (p())
      {
        if (D != null)
        {
          D.a(paramString, paramc, paramInt);
          return true;
        }
      }
      else if (j.getVisibility() == 0)
      {
        j.a(paramString, paramc, paramInt);
        return true;
      }
    }
    return false;
  }
  
  private boolean a(vv paramvv)
  {
    aS = paramvv;
    aP = t;
    aQ = paramvv.ac();
    Object localObject2 = paramvv.Z();
    aR = ((String)localObject2);
    if ((TextUtils.isEmpty((CharSequence)localObject2)) || (aP == null)) {
      return false;
    }
    Object localObject1 = new File((String)localObject2);
    if ((!((File)localObject1).exists()) || (!((File)localObject1).isFile()))
    {
      Log.i("Mms/compose", "saveFlymeMmsAttachment file_no_exsit");
      return false;
    }
    localObject1 = ((File)localObject1).getParent();
    if (((String)localObject2).startsWith("/sdcard/Download/FMessage/")) {
      localObject1 = wd.e();
    }
    localObject2 = lr.a(aR);
    Intent localIntent = new Intent();
    localIntent.addFlags(524288);
    localIntent.setAction("com.meizu.action.SAVE_FILE");
    localIntent.putExtra("init_directory", (String)localObject1);
    localIntent.putExtra("SAVEATTACHMENT", true);
    localIntent.putExtra("android.intent.extra.TITLE", x);
    localIntent.putExtra("android.intent.extra.STREAM", ((Uri)localObject2).toString());
    localIntent.putExtra("title", getString(2131493088));
    startActivityForResult(localIntent, 114);
    return true;
  }
  
  private boolean aA()
  {
    int i1 = aj();
    return (i1 > 0) && (i1 <= ga.o());
  }
  
  private void aB()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(2131492968);
    MmsEllipTextView localMmsEllipTextView = (MmsEllipTextView)LayoutInflater.from(this).inflate(2130968766, null);
    localMmsEllipTextView.a(D.a(), String.valueOf(D.getInvalidNumbers().size()));
    localBuilder.setView(localMmsEllipTextView);
    localBuilder.setNegativeButton(2131493022, new b(null));
    localBuilder.setPositiveButton(2131493166, new i(null));
    U = localBuilder.show();
  }
  
  private void aC()
  {
    if (af == null) {
      af = new mq(this, this);
    }
    for (;;)
    {
      af.b();
      return;
      af.c();
    }
  }
  
  private AdapterView.OnItemClickListener aD()
  {
    return new sm(this);
  }
  
  private final h aE()
  {
    if (ah == null) {
      ah = new h(null);
    }
    return ah;
  }
  
  private final lx aF()
  {
    if (ai == null) {
      ai = new lx(C);
    }
    return ai;
  }
  
  private void aG()
  {
    if (ap != null) {
      ap.a(am);
    }
  }
  
  private void aH()
  {
    am = zv.c();
    Log.i("Mms/compose", "initSelectedSlotIdForDraft mSelectedSlotId = " + am);
  }
  
  private c aI()
  {
    if (an == null) {
      an = new c(null);
    }
    return an;
  }
  
  private void aJ()
  {
    s();
    a(y.getText(), 0, 0, 0);
  }
  
  private final boolean aK()
  {
    Intent localIntent = getIntent();
    if ((localIntent == null) || (!localIntent.hasExtra("shareByFlymeMsg"))) {
      return false;
    }
    return localIntent.getBooleanExtra("shareByFlymeMsg", false);
  }
  
  private d aL()
  {
    if (av == null) {
      av = new d(null);
    }
    return av;
  }
  
  private d aM()
  {
    if (aw == null) {
      aw = new d(null);
    }
    return aw;
  }
  
  private int aN()
  {
    if (X()) {
      return aaa.a();
    }
    return aaa.b();
  }
  
  private void aO()
  {
    if (W != null) {
      W.c();
    }
  }
  
  private aco.a aP()
  {
    if (bw == null) {
      bw = new sw(this);
    }
    return bw;
  }
  
  private void aQ()
  {
    p(false);
    aA.setImageResource(2130837781);
    ae.setSuperVisibility(8);
  }
  
  private void aR()
  {
    if ((l()) && (w))
    {
      if ((c.d()) || (c.h()) || (c.l())) {
        az.a(0);
      }
    }
    else {
      return;
    }
    az.a(1);
  }
  
  private boolean aS()
  {
    boolean bool = true;
    if ((a == null) || (MmsApp.a)) {
      bool = false;
    }
    while (a.z()) {
      return bool;
    }
    if ((X()) && (D.getRecipientCount() == 1))
    {
      List localList = D.getValidNumbers();
      if ((localList != null) && (localList.size() > 0)) {
        return ((String)localList.get(0)).equals("4007883333");
      }
      return false;
    }
    return false;
  }
  
  private boolean aT()
  {
    return aF == 1;
  }
  
  private void aU()
  {
    int i1;
    if (k)
    {
      int i2 = 0;
      if (zv.j())
      {
        i1 = i2;
        if (ak != null)
        {
          i1 = i2;
          if (ak.equals("-10")) {
            i1 = 1;
          }
        }
        if (i1 != 0)
        {
          aF = 1;
          ak = "-10";
        }
      }
    }
    for (am = -10;; am = -1)
    {
      do
      {
        Log.i("Mms/compose", "updateSendType  mSelectedImsi = " + ak + ", mSelectedSlotId = " + am + ", mSendType = " + aF + ", isFlyMeOpen = " + k);
        return;
        if (zv.m())
        {
          i1 = 1;
          break;
        }
        i1 = 1;
        break;
      } while ((ak == null) || (!ak.equals("-10")));
      aF = -1;
    }
  }
  
  private void aV()
  {
    if ((zv.k()) && (k))
    {
      aF = 1;
      am = -10;
      ak = "-10";
    }
  }
  
  private void aW()
  {
    Log.i("Mms/compose", "updateImsiForConversation mSelectedSlotId = " + am);
    a.a(zv.c(am));
  }
  
  private boolean aX()
  {
    if (aS())
    {
      if (c.b() > 420)
      {
        wd.a(this, 2131493691, 0, aN());
        return false;
      }
      if ((wd.d(this)) || (wd.e(this))) {}
      for (int i1 = 1; i1 == 0; i1 = 0)
      {
        wd.o(this);
        return false;
      }
    }
    return true;
  }
  
  private void aY()
  {
    aA.setEnabled(w);
  }
  
  private boolean aZ()
  {
    return (X()) && (D.h());
  }
  
  private final boolean aa()
  {
    Intent localIntent = getIntent();
    if ((localIntent == null) || (!localIntent.hasExtra("shareByFlymeMsg"))) {}
    while (!localIntent.getBooleanExtra("shareByFlymeMsg", false)) {
      return false;
    }
    localIntent.putExtra("shareByFlymeMsg", false);
    return true;
  }
  
  private final void ab()
  {
    Intent localIntent = getIntent();
    if ((localIntent == null) || (!localIntent.hasExtra("shareByFlymeMsg"))) {
      return;
    }
    localIntent.putExtra("shareByFlymeMsg", false);
  }
  
  private boolean ac()
  {
    int i1 = 0;
    Object localObject2 = getIntent();
    Object localObject1 = ((Intent)localObject2).getExtras();
    bo = ((Intent)localObject2).getBooleanExtra("com.android.contacts.extra.SHARED_BY_CONTACT", false);
    if (localObject1 == null) {}
    String str;
    do
    {
      return false;
      str = ((Intent)localObject2).getType();
      localObject2 = ((Intent)localObject2).getAction();
      if ("android.intent.action.SEND".equals(localObject2))
      {
        if (((Bundle)localObject1).containsKey("android.intent.extra.STREAM"))
        {
          localObject2 = (Uri)((Bundle)localObject1).getParcelable("android.intent.extra.STREAM");
          wd.d();
          t().a(new rk(this, str, (Uri)localObject2), null, 2131493822);
        }
        if (((Bundle)localObject1).containsKey("android.intent.extra.TEXT")) {
          c.a(((Bundle)localObject1).getString("android.intent.extra.TEXT"));
        }
        return true;
      }
    } while ((!"android.intent.action.SEND_MULTIPLE".equals(localObject2)) || (!((Bundle)localObject1).containsKey("android.intent.extra.STREAM")));
    wd.d();
    localObject2 = c.i();
    localObject1 = ((Bundle)localObject1).getParcelableArrayList("android.intent.extra.STREAM");
    if (localObject2 != null) {
      i1 = ((lr)localObject2).size();
    }
    int i2 = ((ArrayList)localObject1).size();
    if (i2 + i1 > 20) {}
    for (i1 = Math.min(20 - i1, i2);; i1 = i2)
    {
      t().a(new rl(this, i1, (ArrayList)localObject1, str), null, 2131493822);
      return true;
    }
  }
  
  private void ad()
  {
    E();
    if (c.j()) {
      B.b();
    }
    CharSequence localCharSequence;
    do
    {
      return;
      if (!C.g()) {
        B.c();
      }
      localCharSequence = c.a();
      if (!TextUtils.equals(localCharSequence, y.getText()))
      {
        if ((localCharSequence != null) && (w))
        {
          y.setTextKeepState(localCharSequence);
          y.setSelection(y.length());
          return;
        }
        y.setText("");
        return;
      }
    } while (!TextUtils.isEmpty(localCharSequence));
    y.setText("");
  }
  
  private void ae()
  {
    aw();
    Y = ((NewConsationTitleFrm)findViewById(2131886226));
    C = ((MessageListView)findViewById(2131886231));
    C.setCacheColorHint(0);
    C.setDivider(null);
    C.setDividerHeight(m(2131558741));
    C.setHeaderDividersEnabled(false);
    C.setFooterDividersEnabled(false);
    ah = new h(null);
    C.setMultiChoiceModeListener(aE());
    C.setChoiceMode(1);
    C.setPopupActionModeCallback(bk);
    ai = aF();
    C.setListViewProxy(ai);
    C.setClipToPadding(false);
    o(C.getPaddingBottom());
    M();
    B = ((MmsRichContainer)findViewById(2131886235));
    B.setHandler(aN);
    B.setAfteronKeyPreImeListener(new rr(this));
    B.setSubjectVisibilityListener(new rs(this));
    y = B.getBodyContent();
    y.setOnClickListener(this);
    y.setOnFocusChangeListener(bd);
    y.addTextChangedListener(bb);
    X = findViewById(2131886233);
    o(true);
    aj = findViewById(2131886238);
    p(false);
    aA = ((ImageButton)findViewById(2131886234));
    if (!MmsApp.n)
    {
      aY();
      aA.setOnClickListener(bt);
      aA.setVisibility(0);
    }
    az = new xj(this);
    az.a(new SwitchImageButton.a[] { br, bs });
    z = az.a();
    ao = ((ImageButton)findViewById(2131886236));
    wd.a(ao, (View)ao.getParent(), 0, 0, 30, 0);
    r(false);
    bk();
  }
  
  private void af()
  {
    long l1 = 300L;
    if (u) {
      return;
    }
    t.removeMessages(9702);
    Time localTime = new Time();
    localTime.setToNow();
    if (localTime.toMillis(true) - bf.toMillis(true) < 300L) {}
    for (;;)
    {
      bf = localTime;
      t.sendEmptyMessageDelayed(9702, l1);
      return;
      l1 = 0L;
    }
  }
  
  private void ag()
  {
    if (b != null) {
      return;
    }
    Object localObject = getIntent().getStringExtra("highlight");
    g = getIntent().getStringExtra("group_msg_id");
    if (getIntent().hasExtra("msg_type"))
    {
      f = vx.a(getIntent().getStringExtra("msg_type"), getIntent().getLongExtra("select_id", 0L));
      if (localObject != null) {
        break label179;
      }
      localObject = null;
      label80:
      b = new vx(this, null, C, true, (Pattern)localObject, J());
      b.a(bg);
      b.a(aO);
      C.setAdapter(b);
      localObject = C;
      if (u) {
        break label191;
      }
    }
    label179:
    label191:
    for (int i1 = 4;; i1 = 0)
    {
      ((MessageListView)localObject).setVisibility(i1);
      C.setOnListviewMoveCallback(new rx(this));
      return;
      f = 0L;
      break;
      localObject = Pattern.compile(Pattern.quote((String)localObject), 2);
      break label80;
    }
  }
  
  private boolean ah()
  {
    if (c.f())
    {
      Log.w("Mms/compose", "CMA.loadDraft: called with non-empty working message, bail");
      return false;
    }
    if (Log.isLoggable("Mms:app", 2)) {
      b("CMA.loadDraft");
    }
    aL = false;
    c = hb.a(this, a, new ry(this), Z);
    Z = 0L;
    if (MmsApp.a) {
      c.a(a);
    }
    return true;
  }
  
  private boolean ai()
  {
    int i1 = aj();
    int i2;
    label30:
    int i3;
    if ((i1 > 0) && (i1 <= ga.o()))
    {
      i1 = 1;
      if (c.c()) {
        break label98;
      }
      i2 = 1;
      if ((!c.h()) && (!c.d()) && (!c.l())) {
        break label103;
      }
      i3 = 1;
      label62:
      if (!a.z()) {
        break label110;
      }
      if ((i1 == 0) || (i2 == 0) || (i3 == 0) || (!w)) {
        break label108;
      }
    }
    label98:
    label103:
    label108:
    label110:
    label138:
    do
    {
      do
      {
        return true;
        i1 = 0;
        break;
        i2 = 0;
        break label30;
        i3 = 0;
        break label62;
        return false;
        if (!k) {
          break label138;
        }
      } while ((i1 != 0) && (i2 != 0) && (i3 != 0) && (w));
      return false;
    } while ((i1 != 0) && (i2 != 0) && (i3 != 0) && (B()));
    return false;
  }
  
  private int aj()
  {
    if (X()) {
      return D.getRecipientCount();
    }
    return I().size();
  }
  
  private boolean ak()
  {
    boolean bool = j();
    if ((!X()) && (j != null)) {
      if ((!j.c()) || (!bool)) {}
    }
    do
    {
      return true;
      return false;
      if (D == null) {
        break;
      }
    } while ((D.b()) && (bool));
    return false;
    return false;
  }
  
  private void al()
  {
    if (Log.isLoggable("Mms:app", 2)) {
      b("resetMessage");
    }
    B.d();
    i(false);
    y.requestFocus();
    y.removeTextChangedListener(bb);
    TextKeyListener.clear(y.getText());
    c.a(a, false);
    c = hb.a(this);
    c.a(a);
    T();
    W();
    ad();
    an();
    y.addTextChangedListener(bb);
    K = 0;
    L = false;
    aJ();
  }
  
  private void am()
  {
    if (Log.isLoggable("Mms:app", 2)) {
      b("resetMessageAfterRecord");
    }
    c.a(a);
    c.q();
    T();
    W();
    ad();
    an();
    K = 0;
    L = false;
  }
  
  private void an()
  {
    if (az != null) {
      az.a(ai(), 0);
    }
  }
  
  private void ao()
  {
    if (!E) {}
    List localList;
    do
    {
      do
      {
        return;
      } while ((!X()) || (y.isFocused()) || ((A != null) && (A.isFocused())));
      localList = D.getAllNumbers();
    } while ((localList != null) && (localList.size() > 0));
    D.requestFocus();
  }
  
  private void ap()
  {
    if ((F) && (hasWindowFocus()))
    {
      a.c();
      F = false;
    }
  }
  
  private void aq()
  {
    gm.a(this);
  }
  
  private void ar()
  {
    gm.b(this);
  }
  
  private void as()
  {
    if ((R) && (a != null)) {
      MessagingNotification.a(a.e());
    }
  }
  
  private void at()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.android.mms.sip.dest_check_popup");
    localIntentFilter.addDataScheme("content");
    registerReceiver(bh, localIntentFilter);
  }
  
  private void au()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
    localIntentFilter.addAction("android.intent.action.MEDIA_REMOVED");
    localIntentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
    localIntentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
    localIntentFilter.addDataScheme("file");
    registerReceiver(bh, localIntentFilter);
    if (C != null)
    {
      boolean bool = wd.f();
      C.a(bool);
      u(bool);
    }
  }
  
  private boolean av()
  {
    Intent localIntent = getIntent();
    if (!v) {
      return false;
    }
    if (localIntent.hasExtra("copy_pdu_uri"))
    {
      c = hb.a(this, (Uri)localIntent.getParcelableExtra("copy_pdu_uri"), c, true);
      int i1 = localIntent.getIntExtra("mmsprotocl", 1);
      c.i().d(hb.b(i1));
      c.a(localIntent.getStringExtra("subject"), false);
      new Thread(new k(localIntent)).start();
    }
    for (;;)
    {
      b.changeCursor(null);
      return true;
      if (localIntent.hasExtra("msg_uri"))
      {
        c = hb.a(this, (Uri)localIntent.getParcelableExtra("msg_uri"));
        c.a(localIntent.getStringExtra("subject"), false);
      }
      else
      {
        c.a(localIntent.getStringExtra("sms_body"));
      }
    }
  }
  
  private void aw()
  {
    bj = ((ViewGroup)findViewById(2131886228));
    ax();
  }
  
  private void ax()
  {
    if (bj == null) {}
    Drawable localDrawable;
    do
    {
      return;
      localDrawable = getResources().getDrawable(2130837588);
    } while (localDrawable == null);
    if ((localDrawable instanceof BitmapDrawable)) {
      ((BitmapDrawable)localDrawable).setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    }
    bj.setBackground(localDrawable);
  }
  
  private void ay()
  {
    if (B == null) {
      return;
    }
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)B.getLayoutParams();
    if (((h) || (aS())) && (!MmsApp.a) && (!MmsApp.n)) {}
    for (leftMargin = m(2131558703);; leftMargin = 0)
    {
      B.setLayoutParams(localMarginLayoutParams);
      return;
    }
  }
  
  private boolean az()
  {
    Log.i("Mms/compose", "canSendLocationAsMms mSelectedSlotId = " + am);
    if (am == -1) {
      return m();
    }
    return l();
  }
  
  private int b(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    Log.i("Mms/compose", "getResendSlotId DualSimMessageUtils.CURRENT_NOT_RADIO_OFF_SIM_COUNT = " + zv.i + ", defaultSlotId = " + paramInt1 + ", mSelectedSlotId = " + am);
    if (paramInt2 > -1)
    {
      am = paramInt2;
      return paramInt2;
    }
    if (paramBoolean)
    {
      am = -10;
      return -10;
    }
    if (zv.i == 0) {
      return paramInt1;
    }
    q(true);
    Log.i("Mms/compose", "getResendSlotId mSelectedSlotId = " + am);
    return am;
  }
  
  private String b(Uri paramUri)
  {
    if (paramUri == null) {}
    for (;;)
    {
      return null;
      paramUri = paramUri.getSchemeSpecificPart();
      if (paramUri.contains("?"))
      {
        paramUri = paramUri.substring(paramUri.indexOf('?') + 1).split("&");
        int i2 = paramUri.length;
        int i1 = 0;
        while (i1 < i2)
        {
          String str = paramUri[i1];
          if (str.startsWith("body=")) {
            try
            {
              str = URLDecoder.decode(str.substring(5), "UTF-8");
              return str;
            }
            catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
          }
          i1 += 1;
        }
      }
    }
  }
  
  private void b(int paramInt, Uri paramUri)
  {
    Log.i("Mms/compose", "showSendTypeChooseDialog uri = " + paramUri + ", flag = " + paramInt);
    if (bu == null)
    {
      bu = new xv();
      bu.a(new tb(this, paramInt, paramUri));
    }
    bu.a(this, k);
  }
  
  private void b(int paramInt, boolean paramBoolean)
  {
    if (j()) {
      b(true, 4);
    }
    long l2 = 0L;
    Object localObject = c.i();
    long l1 = l2;
    if (paramBoolean)
    {
      l1 = l2;
      if (localObject != null)
      {
        hb.a((lr)localObject);
        l1 = ((lr)localObject).a(0).b();
      }
    }
    int i1 = paramInt;
    if (MmsApp.a)
    {
      i1 = paramInt;
      if (c.h())
      {
        i1 = paramInt;
        if (paramInt != 7) {
          i1 = 6;
        }
      }
    }
    switch (i1)
    {
    case 9: 
    default: 
    case 0: 
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
      do
      {
        return;
        wd.b(this, 100);
        aaa.a(this);
        return;
        localObject = new Intent("android.media.action.MEIZU_CAMERA_APP_MMS_EX");
        if (r())
        {
          ((Intent)localObject).putExtra("meizu_video_record_max_size", ga.d());
          ((Intent)localObject).putExtra("isFlymeMms", 3);
        }
        for (;;)
        {
          ((Intent)localObject).addFlags(524288);
          startActivityForResult((Intent)localObject, 101);
          return;
          if (l())
          {
            ((Intent)localObject).putExtra("meizu_video_record_max_size", ga.e());
            ((Intent)localObject).putExtra("isFlymeMms", 2);
          }
          else
          {
            ((Intent)localObject).putExtra("output", TempFileProvider.b);
            ((Intent)localObject).putExtra("output_video", TempFileProvider.f);
            ((Intent)localObject).putExtra("meizu_video_record_max_size", 276480L);
            ((Intent)localObject).putExtra("isFlymeMms", 1);
          }
        }
        wd.a(this, 102);
        return;
        l1 = a((lr)localObject, l1);
        if (l1 > 0L)
        {
          wd.b(this, 103, l1);
          return;
        }
        wd.a(getString(2131493013), this, 0, 1, true, 0, aN());
        return;
        wd.a(this, 104);
        return;
        if (MmsApp.a)
        {
          wd.a(this, 105, wd.k());
          return;
        }
        a(f.a, 4);
      } while ((B == null) || (!B.e()));
      B.a(false, 0);
      return;
    case 6: 
      D();
      return;
    case 7: 
      localObject = new Intent("android.intent.action.PICK");
      ((Intent)localObject).setType("vnd.android.cursor.dir/contact");
      ((Intent)localObject).putExtra("com.android.contacts.extra.TITLE_EXTRA", getString(2131493213));
      ((Intent)localObject).putExtra("com.android.contacts.extra.REQUESTED_INFO_TYPE", ga.b(this));
      ((Intent)localObject).putExtra("com.android.contacts.extra.SHOULD_INCLUDE_PROFILE", true);
      ((Intent)localObject).putExtra("com.android.contacts.extra.REQUESTED_ORIENTATION", getRequestedOrientation());
      ((Intent)localObject).addFlags(524288);
      startActivityForResult((Intent)localObject, 111);
      return;
    case 8: 
      if (r()) {
        wd.a(this, 112, true);
      }
      for (;;)
      {
        aaa.a(this);
        return;
        if ((p()) && (D != null) && (D.getRecipientCount() == 0))
        {
          if (aba.a().b()) {
            wd.a(this, 112, true);
          } else if (pj.a()) {
            wd.a(this, 112, true);
          } else {
            wd.a(this, 112, false);
          }
        }
        else {
          wd.a(this, 112, l());
        }
      }
    }
    f(false);
  }
  
  private void b(long paramLong)
  {
    boolean bool = c.u();
    if ((bool) && (paramLong == 0L))
    {
      y.requestFocus();
      c.a(null, true);
      an();
      c.a(true);
    }
    while ((bool) || (paramLong != 1L)) {
      return;
    }
    c.a("", true);
    an();
    y.requestFocus();
    d(true);
  }
  
  private void b(Intent paramIntent)
  {
    if (paramIntent.getIntExtra("isFlymeMms", -1) == 2)
    {
      aF = 1;
      ak = "-10";
    }
    for (am = -10;; am = -10)
    {
      r(true);
      Log.i("Mms/compose", "initSelectedSlotId  mSelectedImsi = " + ak + ", mSelectedSlotId = " + am + ", mSendType = " + aF + ", isFlyMeOpen = " + k + ", mOriginalImsi = " + aq + ", mOriginalSlotId = " + ar);
      return;
      if ((au) || (!aK()) || (!wd.j(this))) {
        break;
      }
      au = true;
      aF = 1;
      ak = "-10";
    }
    ak = paramIntent.getStringExtra("conversation_last_imsi");
    if ((ak == null) && (!TextUtils.isEmpty(al)))
    {
      ak = al;
      al = null;
      aq = ak;
    }
    aF = 0;
    if ((!TextUtils.isEmpty(ak)) && (ak.equals("-10"))) {}
    for (am = -1;; am = zv.a(ak))
    {
      ar = am;
      if (!k) {
        break;
      }
      aU();
      break;
    }
  }
  
  private void b(Uri paramUri, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    if (aD == null) {
      aD = new aad();
    }
    new Thread(new tg(this, paramUri, paramString), "handleMZAssistantResendMsg").start();
  }
  
  private void b(Uri paramUri, boolean paramBoolean)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      b("addImage: append=" + paramBoolean + ", uri=" + paramUri);
    }
    Log.d("Mms/compose", "uri : " + paramUri.toString());
    paramUri = wd.d(this, paramUri);
    Log.d("Mms/compose", "newUri : " + paramUri);
    paramUri = Uri.parse("file://" + paramUri);
    int i1 = c.a(1, paramUri, paramBoolean);
    if ((MmsApp.a) && (!l()) && ((i1 == -4) || (i1 == -2)))
    {
      if (Log.isLoggable("Mms:app", 2)) {
        b("resize image " + paramUri);
      }
      wd.a(this, paramUri, aN, aY, paramBoolean, 1);
      return;
    }
    c(i1, 2131493152);
  }
  
  public static void b(String paramString)
  {
    Object localObject = Thread.currentThread();
    long l1 = ((Thread)localObject).getId();
    localObject = localObject.getStackTrace()[3].getMethodName();
    Log.d("Mms/compose", "[" + l1 + "] [" + (String)localObject + "] " + paramString);
  }
  
  private void b(ArrayList<vv> paramArrayList, boolean paramBoolean, int paramInt)
  {
    Log.e("Mms/compose", "reSendSmsMessageItem usesipway is " + paramBoolean);
    int i1 = paramArrayList.size();
    if (i1 < 1) {
      return;
    }
    if (aS())
    {
      b(((vv)paramArrayList.get(0)).K(), get0o);
      return;
    }
    int i2 = b(((vv)paramArrayList.get(0)).ag(), paramInt, paramBoolean);
    Log.e("Mms/compose", "reSendSmsMessageItem selectedSlotId = " + i2);
    int i3 = ((vv)paramArrayList.get(0)).ag();
    paramInt = 0;
    Object localObject;
    if (paramInt < i1)
    {
      localObject = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, gete);
      ContentValues localContentValues;
      if ((((Boolean)aau.a(Telephony.Sms.class, "moveMessageToFolder", new Class[] { Context.class, Uri.class, Integer.TYPE, Integer.TYPE }, new Object[] { this, localObject, Integer.valueOf(6), Integer.valueOf(0) })).booleanValue()) && (get0h == vv.a.c))
      {
        localContentValues = new ContentValues(1);
        localContentValues.put("status", Integer.valueOf(32));
        getContentResolver().update((Uri)localObject, localContentValues, null, null);
        label288:
        if (!((vv)paramArrayList.get(paramInt)).D()) {
          break label346;
        }
      }
      label346:
      label478:
      for (;;)
      {
        paramInt += 1;
        break;
        Log.e("Mms/compose", "reSendSmsMessageItem: failed to move message " + localObject + " to sent folder");
        break label288;
        if ((!paramBoolean) && (((vv)paramArrayList.get(paramInt)).t()))
        {
          Log.e("Mms/compose", "reSendSmsMessageItem:  move message to normal");
          MzTelephony.SmsExt.changeMessageProtocol(this, (Uri)localObject, 0, 0);
        }
        for (;;)
        {
          if (i2 == i3) {
            break label478;
          }
          a(i2, (Uri)localObject);
          break;
          if ((paramBoolean) && (!((vv)paramArrayList.get(paramInt)).t()))
          {
            Log.e("Mms/compose", "reSendSmsMessageItem:  move message to sip ");
            MzTelephony.SmsExt.changeMessageProtocol(this, (Uri)localObject, 256, 0);
            localContentValues = new ContentValues(2);
            localContentValues.put("status", Integer.valueOf(32));
            getContentResolver().update((Uri)localObject, localContentValues, null, null);
          }
        }
      }
    }
    if (r()) {
      localObject = "com.android.mms.transaction.SEND_SNS_MESSAGE";
    }
    for (;;)
    {
      localObject = new Intent((String)localObject, null, this, SmsReceiver.class);
      Log.d("Mms/compose", "reSendSmsMessageItem -> slotId : " + ((vv)paramArrayList.get(0)).ag());
      ((Intent)localObject).putExtra("sim_id", i2);
      sendBroadcast((Intent)localObject);
      return;
      if (paramBoolean) {
        localObject = "com.android.mms.transaction.SEND_SIP_MESSAGE";
      } else {
        localObject = "com.android.mms.transaction.SEND_MESSAGE";
      }
    }
  }
  
  private void b(HashMap<Long, j> paramHashMap)
  {
    int i1 = paramHashMap.size();
    if (i1 == 0) {
      return;
    }
    if (i1 == b.getCount())
    {
      paramHashMap = new ArrayList();
      paramHashMap.add(Long.valueOf(a.e()));
      new ConversationListFragment.b(paramHashMap, t, this).a();
      return;
    }
    vv[] arrayOfvv = new vv[i1];
    Iterator localIterator = paramHashMap.values().iterator();
    i1 = 0;
    boolean bool = false;
    if (localIterator.hasNext())
    {
      j localj = (j)localIterator.next();
      arrayOfvv[i1] = c;
      if (a > 0L) {}
      for (;;)
      {
        i1 += 1;
        break;
        bool = true;
      }
    }
    a(paramHashMap.size(), 1, bool);
    new e(arrayOfvv).a(false);
  }
  
  private void b(boolean paramBoolean, int paramInt)
  {
    if ((paramBoolean) || (paramInt == 3))
    {
      wd.a(2131493315, this, 0, 1, true, 0, aN());
      return;
    }
    switch (paramInt)
    {
    default: 
      return;
    case 1: 
      wd.a(2131493620, this, 0, 1, true, 0, aN());
      return;
    case 2: 
      wd.a(wd.l(), this, 0, 1, true, 0, aN());
      return;
    }
    wd.a(2131493314, this, 0, 1, true, 0, aN());
  }
  
  private boolean b(int paramInt1, int paramInt2)
  {
    return (paramInt1 & paramInt2) == paramInt2;
  }
  
  public static boolean b(Intent paramIntent, Context paramContext)
  {
    if (MessagingNotification.b(paramIntent))
    {
      MessagingNotification.a(paramContext, 531);
      return true;
    }
    return false;
  }
  
  private boolean b(vv paramvv)
  {
    if (paramvv == null) {}
    Cursor localCursor;
    do
    {
      return false;
      localCursor = b.a(paramvv);
    } while (localCursor == null);
    ListView localListView = new ListView(this);
    localListView.setAdapter(new gi(zd.a(this, localCursor, C), this));
    new AlertDialog.Builder(this).setTitle(2131493003).setView(localListView).setCancelable(true).show();
    return true;
  }
  
  private boolean b(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (aF == 1) {
      if (paramBoolean1) {
        if (((!c.h()) && ((!c.l()) || (!c.d())) && (!paramBoolean2)) || (!bj())) {
          paramBoolean1 = true;
        }
      }
    }
    for (;;)
    {
      if (!paramBoolean1) {
        wd.a(2131493834, this, 0, 0, true, 0);
      }
      return paramBoolean1;
      paramBoolean1 = false;
      continue;
      if (!bj()) {}
      for (paramBoolean1 = true;; paramBoolean1 = false) {
        break;
      }
      paramBoolean1 = true;
    }
  }
  
  private void ba()
  {
    String str = getResources().getString(2131493710);
    new AlertDialog.Builder(this).setTitle(str).setPositiveButton(2131493166, new tc(this)).setNegativeButton(2131493022, null).show();
  }
  
  private void bb()
  {
    if (aC == null)
    {
      ((ViewGroup)findViewById(2131886232)).addView(LayoutInflater.from(this).inflate(2130968841, null));
      aC = findViewById(2131886747);
      d = ((SmartMessageViewContainer)findViewById(2131886749));
      aB = ((ImageButton)findViewById(2131886748));
      aB.setOnClickListener(this);
    }
  }
  
  private void bc()
  {
    if (ae == null)
    {
      ViewGroup localViewGroup = (ViewGroup)findViewById(2131886230);
      ae = ((AttachmentGroupView)LayoutInflater.from(this).inflate(2130968605, null));
      ae.setOnItemClickListener(aD());
      localViewGroup.addView(ae);
      ((CmaContentFrm)bj).setAttachmentGroupView(ae);
      ae.setIsKeybordShowing(wd.g(this));
      ae.setAttachmentGroupViewVisibilityListener(new tf(this));
    }
  }
  
  private void bd()
  {
    if (W == null)
    {
      W = aco.a();
      W.a(MmsApp.c(), y, aP());
      W.a(bv);
    }
  }
  
  private void be()
  {
    if (aB == null) {
      return;
    }
    if ((zv.i == 2) && (zv.e == 2) && (!aS()))
    {
      Log.d("Mms/compose", "updateSmartMsgImeBtnIcon() --> mSelectedSlotId = " + am);
      switch (am)
      {
      default: 
        aB.setImageDrawable(getResources().getDrawable(2130837712));
        return;
      case 0: 
        aB.setImageDrawable(getResources().getDrawable(2130837710));
        return;
      }
      aB.setImageDrawable(getResources().getDrawable(2130837711));
      return;
    }
    aB.setImageDrawable(getResources().getDrawable(2130837793));
  }
  
  private void bf()
  {
    if ((p()) && (!aZ()) && (M != null)) {
      c.b(M);
    }
  }
  
  private void bg()
  {
    if ((c.h()) && (!c.j()))
    {
      if (c.x())
      {
        c.e(false);
        aN.removeCallbacks(bx);
        aN.postDelayed(bx, 200L);
      }
      B.a();
    }
  }
  
  private boolean bh()
  {
    if (a == null) {}
    while (a.h().size() != 1) {
      return false;
    }
    boolean bool = MzPhoneNumberUtils.isNotificationNumber(this, ((gm)a.h().get(0)).d());
    Log.d("Mms/compose", "isNotificationNumber = " + bool);
    return bool;
  }
  
  private int bi()
  {
    if (aS()) {
      return 1;
    }
    if (k()) {
      return 0;
    }
    return -1;
  }
  
  private boolean bj()
  {
    if (X()) {
      if (D.getRecipientCount() <= 1) {}
    }
    do
    {
      do
      {
        return true;
        return false;
        if (aK == -1) {
          break;
        }
      } while (aK == 1);
      return false;
    } while (a.h().size() > 1);
    return false;
  }
  
  private void bk()
  {
    C.setOnScrollListener(new ti(this));
  }
  
  private List<j> c(HashMap<Long, j> paramHashMap)
  {
    paramHashMap = paramHashMap.values().iterator();
    ArrayList localArrayList = new ArrayList();
    while (paramHashMap.hasNext()) {
      localArrayList.add(paramHashMap.next());
    }
    Collections.sort(localArrayList);
    return localArrayList;
  }
  
  private void c(int paramInt1, int paramInt2)
  {
    if (paramInt1 == 0) {
      return;
    }
    Log.d("Mms/compose", "handleAddAttachmentError(), error is " + paramInt1);
    ab();
    runOnUiThread(new rf(this, paramInt2, paramInt1));
  }
  
  private void c(int paramInt, Uri paramUri)
  {
    Log.i("Mms/compose", "chooseSendTypeAndSend flag = " + paramInt + ", mSelectedSlotId = " + am + ", isFlyMeOpen = " + k + ", CURRENT_USEABLE_SIM_COUNT = " + zv.e + ", CURRENT_NOT_RADIO_OFF_SIM_COUNT = " + zv.i);
    if (aS())
    {
      am = -1;
      aF = 0;
      d(paramInt, paramUri);
    }
    for (;;)
    {
      Log.i("Mms/compose", "chooseSendTypeAndSend flag = " + paramInt + ", mSelectedSlotId = " + am + ", mSendType = " + aF);
      return;
      if (((am >= 0) && (zv.j())) || ((k) && (am == -10)))
      {
        d(paramInt, paramUri);
      }
      else if (zv.j())
      {
        b(paramInt, paramUri);
      }
      else
      {
        if (zv.m())
        {
          if (k) {
            if ((am >= 0) && (am == zv.h())) {
              aF = 0;
            }
          }
          for (;;)
          {
            d(paramInt, paramUri);
            break;
            am = -10;
            aF = 1;
            continue;
            q(true);
          }
        }
        if (k)
        {
          am = -10;
          aF = 1;
          d(paramInt, paramUri);
        }
      }
    }
  }
  
  private void c(Uri paramUri, boolean paramBoolean)
  {
    t().a(new rh(this, paramUri, paramBoolean), null, 2131493822);
  }
  
  private void c(vv paramvv)
  {
    long l1 = System.currentTimeMillis();
    Intent localIntent = a(this, 0L);
    localIntent.putExtra("exit_on_sent", true);
    localIntent.putExtra("forwarded_message", true);
    if (d.equals("sms")) {
      localIntent.putExtra("sms_body", o);
    }
    for (;;)
    {
      localIntent.putExtra("extra_from_create_new_btn", true);
      localIntent.setClassName(this, "com.android.mms.ui.ForwardMessageActivity");
      startActivityForResult(localIntent, 113);
      wd.a("forwardmsg", "forwardMessage all", l1);
      aaa.d(this);
      return;
      if ((!y) || ((paramvv.m()) && (!paramvv.B()) && (!wd.c(this, Uri.parse("file://" + Z)))))
      {
        Log.i("Mms/compose", "forwardMms the forward menu should be disable");
        return;
      }
      if (B == null)
      {
        Log.i("Mms/compose", "forwardMms msgItem.mSlideshow is null");
        return;
      }
      String str2 = getString(2131492965);
      String str1 = str2;
      if (A != null) {
        str1 = str2 + A;
      }
      localIntent.putExtra("copy_pdu_uri", paramvv.K());
      localIntent.putExtra("mmsprotocl", paramvv.r());
      localIntent.putExtra("subject", str1);
    }
  }
  
  private void c(boolean paramBoolean, int paramInt)
  {
    int i5 = 0;
    int i6 = C.getLastVisiblePosition();
    int i7 = C.getCount() - 1;
    if ((i6 < 0) || (i7 < 0))
    {
      if (Log.isLoggable("Mms:app", 2)) {
        Log.v("Mms/compose", "smoothScrollToEnd: lastItemVisible=" + i6 + ", lastItemInList=" + i7 + ", mMsgListView not ready");
      }
      ai.c();
      return;
    }
    View localView = C.getChildAt(i6 - C.getFirstVisiblePosition());
    int i2;
    int i1;
    if (localView != null)
    {
      i2 = localView.getBottom();
      i1 = localView.getHeight();
    }
    for (;;)
    {
      if (Log.isLoggable("Mms:app", 2)) {
        Log.v("Mms/compose", "smoothScrollToEnd newPosition: " + i7 + " mLastSmoothScrollPosition: " + P + " first: " + C.getFirstVisiblePosition() + " lastItemVisible: " + i6 + " lastVisibleItemBottom: " + i2 + " lastVisibleItemBottom + listSizeChange: " + (i2 + paramInt) + " mMsgListView.getHeight() - mMsgListView.getPaddingBottom(): " + (C.getHeight() - C.getPaddingBottom()) + " listSizeChange: " + paramInt);
      }
      int i8 = C.getHeight();
      if (i1 > i8) {}
      for (int i3 = 1;; i3 = 0)
      {
        int i4;
        if (!paramBoolean)
        {
          if (paramInt == 0)
          {
            i4 = i5;
            if (i7 == P) {}
          }
          else
          {
            i4 = i5;
            if (i2 + paramInt > i8 - C.getPaddingBottom()) {}
          }
        }
        else {
          i4 = 1;
        }
        if ((i4 == 0) && ((i3 == 0) || (i7 != i6))) {
          break;
        }
        if (Math.abs(paramInt) <= 600) {
          break label432;
        }
        if (Log.isLoggable("Mms:app", 2)) {
          Log.v("Mms/compose", "keyboard state changed. setSelection=" + i7);
        }
        if (i3 == 0) {
          break label422;
        }
        C.setSelectionFromTop(i7, i8 - i1);
        return;
      }
      label422:
      C.setSelection(i7);
      return;
      label432:
      if (i7 - i6 > 8)
      {
        if (Log.isLoggable("Mms:app", 2)) {
          Log.v("Mms/compose", "too many to scroll, setSelection=" + i7);
        }
        C.setSelection(i7);
        return;
      }
      if (Log.isLoggable("Mms:app", 2)) {
        Log.v("Mms/compose", "smooth scroll to " + i7);
      }
      if (i3 != 0) {
        C.setSelectionFromTop(i7, i8 - i1);
      }
      for (;;)
      {
        P = i7;
        return;
        C.smoothScrollToPosition(i7);
      }
      i1 = 0;
      i2 = 0;
    }
  }
  
  public static final boolean c(int paramInt)
  {
    return (paramInt & 0x1) > 0;
  }
  
  private void d(int paramInt, Uri paramUri)
  {
    if (paramUri != null) {}
    for (boolean bool = true; !b(true, bool); bool = false)
    {
      aG();
      return;
    }
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      w();
      return;
    case 1: 
      a(null, false, false, true);
      ag = null;
      return;
    }
    a(paramUri, false, true, false);
  }
  
  private void d(Uri paramUri, boolean paramBoolean)
  {
    if (paramUri != null) {
      c(c.a(2, paramUri, paramBoolean), 2131493155);
    }
  }
  
  private void d(vv paramvv)
  {
    if ((paramvv == null) || (TextUtils.isEmpty(o))) {
      return;
    }
    g(o);
  }
  
  public static final boolean d(int paramInt)
  {
    return (paramInt & 0x2) > 0;
  }
  
  private boolean d(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 2) && (paramInt2 != 2)) {}
    while ((paramInt1 != 2) && (paramInt2 == 2)) {
      return true;
    }
    return false;
  }
  
  public static final int e(int paramInt)
  {
    if ((c(paramInt)) && (d(paramInt))) {
      return 1;
    }
    if (c(paramInt)) {
      return 0;
    }
    return -1;
  }
  
  private void e(Uri paramUri, boolean paramBoolean)
  {
    t().a(new rj(this, paramUri, paramBoolean), null, 2131493822);
  }
  
  private void e(String paramString)
  {
    Intent localIntent = a(this, 0L);
    localIntent.putExtra("exit_on_sent", true);
    localIntent.putExtra("forwarded_message", true);
    localIntent.putExtra("sms_body", paramString);
    localIntent.putExtra("extra_from_create_new_btn", true);
    localIntent.setClassName(this, "com.android.mms.ui.ForwardMessageActivity");
    startActivityForResult(localIntent, 113);
    aaa.d(this);
  }
  
  private final void f(int paramInt)
  {
    int i1 = 2131493244;
    if ((a(paramInt, 2)) && (a(paramInt, 4))) {
      i1 = 2131493245;
    }
    for (;;)
    {
      wd.a(i1, this, 0, 1, true, 0, aN());
      if (X())
      {
        D.a(paramInt);
        D.requestFocus();
      }
      return;
      if (a(paramInt, 4)) {
        i1 = 2131493246;
      } else if (a(paramInt, 8)) {
        i1 = 2131493693;
      }
    }
  }
  
  private void f(Uri paramUri, boolean paramBoolean)
  {
    if (paramUri != null) {
      c(c.a(5, paramUri, paramBoolean), 2131493615);
    }
  }
  
  private void f(String paramString)
  {
    if (paramString == null) {
      return;
    }
    wd.a(paramString, this, 0, 1, true, 0, aN());
  }
  
  private void g(int paramInt)
  {
    if (S == null)
    {
      S = new ProgressDialog(this);
      S.setMessage(getString(paramInt));
      S.setCancelable(true);
      S.setCanceledOnTouchOutside(false);
      S.setIndeterminate(true);
      S.setOnCancelListener(new tj(this));
    }
    S.show();
  }
  
  private void g(Uri paramUri, boolean paramBoolean)
  {
    if (paramUri != null) {
      c(c.a(7, paramUri, paramBoolean), 2131493610);
    }
  }
  
  private void g(String paramString)
  {
    ((ClipboardManager)getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, paramString));
  }
  
  private void g(boolean paramBoolean) {}
  
  private void h(int paramInt)
  {
    switch (paramInt)
    {
    case 1001: 
    case 1002: 
    default: 
      return;
    case -2: 
      if (l()) {}
      for (paramInt = 2131493715;; paramInt = wd.l())
      {
        wd.a(paramInt, this, 0, 1, true, 0, aN());
        return;
      }
    case -5: 
      a(2131493830, 2131493567, 2131493166);
      return;
    }
    a(2131493297, 2131493297, 2131492930);
  }
  
  private void h(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      j.setVisibility(0);
      if ((!j.e()) && (aba.a().b()) && (!r()))
      {
        j.setSipDestChecker(this);
        aV = false;
        j.f();
      }
    }
    for (;;)
    {
      if (aI != null) {
        j.a(aI);
      }
      if (aJ != null) {
        j.b(aJ);
      }
      return;
      j.setVisibility(8);
      j.setSipDestChecker(null);
    }
  }
  
  private boolean h(String paramString)
  {
    boolean bool = false;
    int i1 = y.getSelectionStart();
    Editable localEditable = y.getEditableText();
    if ((i1 < 0) || (i1 >= localEditable.length())) {
      y.append(paramString);
    }
    for (;;)
    {
      if (D != null)
      {
        D.requestFocus();
        bool = true;
      }
      return bool;
      localEditable.insert(i1, paramString);
    }
  }
  
  private void i(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      ak = paramString;
      am = zv.a(ak);
    }
    Log.i("Mms/compose", "initSelectedSlotId selectedImsi = " + paramString + ", mSelectedImsi = " + ak + ", mSelectedSlotId = " + am);
    r(true);
  }
  
  private void i(boolean paramBoolean)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      b("" + paramBoolean);
    }
    if (A == null) {
      if (paramBoolean) {}
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return;
            A = B.getSubjectText();
            if ((p()) || ((!r()) && (!h))) {
              break;
            }
          } while (A.getVisibility() != 0);
          A.removeTextChangedListener(bc);
          B.setSubjectVisibility(false);
          A.setOnFocusChangeListener(null);
          return;
          if ((!paramBoolean) || (A.getVisibility() == 0)) {
            break;
          }
        } while ((!MessagingPreferenceActivity.c(this)) && (!c.l()));
        A.addTextChangedListener(bc);
        A.setText(c.k());
        B.setSubjectVisibility(true);
        A.setOnFocusChangeListener(bd);
        return;
        if ((paramBoolean) && (A.getVisibility() == 0) && (c.l()) && (TextUtils.isEmpty(A.getText())))
        {
          A.setText(c.k());
          return;
        }
      } while ((paramBoolean) || (A.getVisibility() != 0));
      if ((!MmsApp.a) || (!MessagingPreferenceActivity.c(this)))
      {
        A.removeTextChangedListener(bc);
        A.setText(c.k());
        B.setSubjectVisibility(false);
        A.setOnFocusChangeListener(null);
        return;
      }
    } while ((!MmsApp.a) || (!MessagingPreferenceActivity.c(this)));
    A.removeTextChangedListener(bc);
    A.setText("");
    A.addTextChangedListener(bc);
  }
  
  private final boolean i(int paramInt)
  {
    return (j(paramInt)) && (aX);
  }
  
  private void j(String paramString)
  {
    int i4 = 1;
    int i2 = 1;
    int i1 = 1;
    int i3 = 0;
    int i5 = y.getSelectionStart();
    Editable localEditable = y.getText();
    Object localObject;
    if ((localEditable == null) || (localEditable.length() == 0))
    {
      i1 = 0;
      i2 = i3;
      StringBuilder localStringBuilder = new StringBuilder();
      if (i2 == 0) {
        break label245;
      }
      localObject = "\n";
      label66:
      localObject = localStringBuilder.append((String)localObject).append(paramString);
      if (i1 == 0) {
        break label253;
      }
    }
    label209:
    label240:
    label245:
    label253:
    for (paramString = "\n";; paramString = "")
    {
      localEditable.insert(i5, paramString);
      return;
      if (i5 == 0)
      {
        i2 = i3;
        if (localEditable.toString().charAt(i5) != '\n') {
          break;
        }
        i1 = 0;
        i2 = i3;
        break;
      }
      if (i5 == localEditable.length())
      {
        if (localEditable.toString().charAt(Math.max(i5 - 1, 0)) != '\n') {}
        for (i1 = i4;; i1 = 0)
        {
          i2 = i1;
          i1 = 0;
          break;
        }
      }
      if (localEditable.toString().charAt(Math.max(i5 - 1, 0)) != '\n')
      {
        i1 = 1;
        if (localEditable.toString().charAt(i5) == '\n') {
          break label240;
        }
      }
      for (;;)
      {
        i3 = i1;
        i1 = i2;
        i2 = i3;
        break;
        i1 = 0;
        break label209;
        i2 = 0;
      }
      localObject = "";
      break label66;
    }
  }
  
  private void j(boolean paramBoolean)
  {
    if ((Y == null) || (a == null)) {
      return;
    }
    if (!p())
    {
      Y.setVisibility(8);
      aaa.b(getSupportActionBar(), this);
      return;
    }
    Y.setVisibility(0);
    aaa.b(getSupportActionBar(), this);
  }
  
  private final boolean j(int paramInt)
  {
    return (b(paramInt, 2)) || (b(paramInt, 4));
  }
  
  private void k(int paramInt)
  {
    Object localObject;
    int i1;
    label81:
    boolean bool;
    if (y != null)
    {
      if ((az != null) && ((l()) || (r())))
      {
        localObject = az;
        if (!b(paramInt, 4)) {
          break label115;
        }
        i1 = 2130837794;
        ((xj)localObject).a(1, i1);
      }
      if (!b(paramInt, 2)) {
        break label130;
      }
      EditTextEx localEditTextEx = y;
      if (!r()) {
        break label122;
      }
      localObject = "weibo";
      localEditTextEx.a("com.meizu.input.cover.SMILE", (String)localObject);
      bool = false;
    }
    for (;;)
    {
      aW = paramInt;
      if (B != null) {
        B.a(bool);
      }
      return;
      label115:
      i1 = 2130837821;
      break;
      label122:
      localObject = "mms";
      break label81;
      label130:
      if (b(paramInt, 4))
      {
        y.a("com.meizu.input.cover.VOICE", null);
        bool = false;
      }
      else
      {
        y.a(null, null);
        bool = true;
      }
    }
  }
  
  private void k(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    if (aD == null) {
      aD = new aad();
    }
    new Thread(new ta(this, paramString), "handleMZAssistantSendMsg").start();
  }
  
  private void k(boolean paramBoolean)
  {
    boolean bool = false;
    if ((!R) || (J)) {}
    do
    {
      return;
      if ((aX != paramBoolean) && (B != null)) {
        B.a(false, 0);
      }
      aX = paramBoolean;
      e(false);
    } while (B == null);
    MmsRichContainer localMmsRichContainer = B;
    paramBoolean = bool;
    if (!j(aW)) {
      paramBoolean = true;
    }
    localMmsRichContainer.a(paramBoolean);
  }
  
  private void l(int paramInt)
  {
    Uri localUri = a.d();
    if (localUri == null)
    {
      b("##### startMsgListQuery: conversationUri is null, bail!");
      return;
    }
    long l1 = a.e();
    if (Log.isLoggable("Mms:app", 2)) {
      b("startMsgListQuery for " + localUri + ", threadId=" + l1 + " token: " + paramInt + " mConversation: " + a);
    }
    t.cancelOperation(paramInt);
    try
    {
      t.startQuery(paramInt, Long.valueOf(l1), localUri.buildUpon().appendQueryParameter("addPartText", "true").build(), vx.a, "is_favorite != 2", new String[] { "GROUP BY group_msg_id" }, "association_id");
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      aau.a(this, localSQLiteException);
    }
  }
  
  private void l(boolean paramBoolean)
  {
    B.a(c);
    if ((paramBoolean) || (c.h()) || (c.l())) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      i(paramBoolean);
      return;
    }
  }
  
  private int m(int paramInt)
  {
    return getResources().getDimensionPixelSize(paramInt);
  }
  
  private void m(boolean paramBoolean)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      fl.a("saveDraft", new Object[0]);
    }
    if (c.r()) {}
    do
    {
      do
      {
        return;
        if (!MmsApp.a) {
          break;
        }
      } while (((p()) && (!aZ())) || (x));
      for (;;)
      {
        if ((!J) && (!c.f()) && ((!X()) || (aj() == 0)))
        {
          if (!aL) {
            break;
          }
          if (Log.isLoggable("Mms:app", 2)) {
            b("not worth saving, discard WorkingMessage and bail");
          }
          c.p();
          return;
          if (p()) {
            break;
          }
          if (x) {
            return;
          }
        }
      }
    } while (J);
    zv.b(am, ak);
    c.c(paramBoolean);
  }
  
  private void n(int paramInt)
  {
    if (B != null) {
      B.b(paramInt);
    }
  }
  
  private void n(boolean paramBoolean)
  {
    a(paramBoolean, null, false, false, false);
  }
  
  private void o(int paramInt)
  {
    C.setPadding(C.getPaddingLeft(), aaa.b(), C.getPaddingRight(), paramInt);
  }
  
  private void o(boolean paramBoolean)
  {
    if ((bj == null) || (!ga.a())) {
      return;
    }
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)bj.getLayoutParams();
    if (paramBoolean)
    {
      bottomMargin = 0;
      if ((a != null) && (p())) {
        topMargin = getResources().getDimensionPixelSize(2131559402);
      }
    }
    for (;;)
    {
      bj.setLayoutParams(localLayoutParams);
      return;
      topMargin = getResources().getDimensionPixelSize(2131558824);
      continue;
      bottomMargin = getResources().getDimensionPixelSize(2131559414);
      topMargin = getResources().getDimensionPixelSize(2131558824);
    }
  }
  
  private void p(int paramInt)
  {
    if (paramInt != as) {
      switch (paramInt)
      {
      }
    }
    for (;;)
    {
      as = paramInt;
      return;
      if (aC != null) {
        aC.setVisibility(0);
      }
      X.setVisibility(8);
      continue;
      if (aC != null)
      {
        aC.setVisibility(8);
        aB.setVisibility(8);
        if (!MmsApp.n) {
          aA.setVisibility(0);
        }
        ay();
      }
      X.setVisibility(0);
      if ((y != null) && (!y.hasFocus()))
      {
        y.requestFocus();
        continue;
        X.setVisibility(0);
        if ((y != null) && (!y.hasFocus())) {
          y.requestFocus();
        }
        if (aC != null) {
          aC.setVisibility(8);
        }
        if ((MmsApp.a) && (!MmsApp.n))
        {
          aA.setVisibility(0);
          continue;
          X.setVisibility(8);
          if ((h) && (aC != null)) {
            aC.setVisibility(8);
          }
        }
      }
    }
  }
  
  private void p(boolean paramBoolean)
  {
    View localView = aj;
    if (paramBoolean) {}
    for (int i1 = 0;; i1 = 4)
    {
      localView.setVisibility(i1);
      return;
    }
  }
  
  private int q(boolean paramBoolean)
  {
    int i1 = -1;
    Log.i("Mms/compose", "selectSlotId CURRENT_NOT_RADIO_OFF_SIM_COUNT = " + zv.i + ",CURRENT_USEABLE_SIM_COUNT = " + zv.e);
    if (zv.l()) {}
    for (;;)
    {
      Log.i("Mms/compose", "selectSlotId slotId = " + i1);
      if (paramBoolean) {
        am = i1;
      }
      return i1;
      if (zv.j())
      {
        if (am <= -1) {
          i1 = 0;
        } else {
          i1 = am;
        }
      }
      else {
        i1 = zv.h();
      }
    }
  }
  
  private void r(boolean paramBoolean)
  {
    if (ap == null)
    {
      ap = new gk();
      ap.a(new sn(this));
      ap.a(ao, this, false);
      ap.a(aI());
    }
    if (paramBoolean) {
      ap.a(am);
    }
  }
  
  private void s(boolean paramBoolean)
  {
    if (ap != null) {}
  }
  
  private void t(boolean paramBoolean)
  {
    if (bl) {
      bl = false;
    }
    while (!h) {
      return;
    }
    if (!paramBoolean)
    {
      if ((MmsApp.a) && (aH))
      {
        aH = false;
        return;
      }
      if (aC != null) {
        aC.setVisibility(0);
      }
      if (y()) {
        aQ();
      }
      X.post(new st(this));
      return;
    }
    p(3);
  }
  
  private void u(boolean paramBoolean)
  {
    bv = paramBoolean;
    if (W != null) {
      W.a(paramBoolean);
    }
  }
  
  private void v(boolean paramBoolean)
  {
    int i1 = 0;
    Object localObject;
    boolean bool;
    if (ap != null)
    {
      localObject = ap;
      if (!paramBoolean)
      {
        bool = true;
        ((gk)localObject).a(bool);
        ap.a(ao, this, false);
      }
    }
    else if ((aA != null) && (!MmsApp.n))
    {
      localObject = aA;
      if (!paramBoolean) {
        break label79;
      }
    }
    for (;;)
    {
      ((ImageButton)localObject).setVisibility(i1);
      ay();
      return;
      bool = false;
      break;
      label79:
      i1 = 8;
    }
  }
  
  private void w(boolean paramBoolean)
  {
    if (j()) {
      b(true, 4);
    }
    if (kn.a()) {}
    for (Intent localIntent = new Intent(kn.c);; localIntent = new Intent(kn.b))
    {
      localIntent.putExtra("send_as_mms", az());
      localIntent.putExtra("send_out_directly", paramBoolean);
      startActivityForResult(localIntent, 115);
      return;
    }
  }
  
  public void A()
  {
    int i1 = 4;
    if (h)
    {
      if (!aX)
      {
        if (ax) {}
        for (;;)
        {
          p(i1);
          d.setEnabled(B());
          return;
          i1 = 1;
        }
      }
      if (ax) {}
      for (;;)
      {
        p(i1);
        return;
        i1 = 3;
      }
    }
    if (ax) {}
    for (;;)
    {
      p(i1);
      return;
      i1 = 2;
    }
  }
  
  public boolean B()
  {
    return (!i) || ((w) && (zv.i > 0) && (zv.e > 0));
  }
  
  protected long a()
  {
    if (a == null) {
      return super.a();
    }
    return a.e();
  }
  
  public void a(int paramInt)
  {
    if (p()) {
      b("onSipStatusChanged: statu=" + paramInt);
    }
    while ((j == null) || (!j.e()) || (r())) {
      return;
    }
    aV = false;
    j.g();
    b("onSipStatusChanged: statu=" + paramInt + ", re-checked conversation sip statu...");
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3)
  {
    if (isFinishing()) {
      return;
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(paramInt1);
    localBuilder.setMessage(paramInt2);
    localBuilder.setPositiveButton(paramInt3, new ra(this));
    localBuilder.setNegativeButton(17039360, null);
    localBuilder.show();
  }
  
  public void a(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    switch (paramInt2)
    {
    default: 
      paramInt1 = 0;
    }
    for (;;)
    {
      if (paramInt1 != 0)
      {
        if (S == null)
        {
          S = new ProgressDialog(this);
          S.setMessage(getString(2131493280));
          S.setCancelable(false);
          S.setIndeterminate(true);
        }
        S.show();
      }
      return;
      if ((a.k() < 20) && (!a.q())) {
        break;
      }
      paramInt1 = 1;
      continue;
      if ((paramInt1 < 20) && (!paramBoolean)) {
        break;
      }
      paramInt1 = 1;
    }
  }
  
  public void a(int paramInt, boolean paramBoolean)
  {
    runOnUiThread(new qz(this, paramInt, paramBoolean));
  }
  
  public void a(Bundle paramBundle, long paramLong)
  {
    boolean bool = false;
    Intent localIntent = getIntent();
    c = hb.a(this);
    a(paramBundle);
    new Thread(new qn(this, localIntent)).start();
    ag();
    int i1;
    Uri localUri;
    if ((paramBundle != null) || ((!ac()) && (!av())))
    {
      i1 = 1;
      if (i1 != 0)
      {
        Z = localIntent.getLongExtra("sending_msg_id", 0L);
        ah();
      }
      c.a(a);
      if (a.e() > 0L) {
        break label457;
      }
      W();
      K();
      localUri = localIntent.getData();
      if ((localUri != null) && (localUri.getScheme().equals("smsto")))
      {
        paramBundle = localIntent.getExtras();
        if (paramBundle == null) {
          break label543;
        }
      }
    }
    label457:
    label498:
    label535:
    label543:
    for (paramBundle = paramBundle.getStringArrayList("contacts_selected");; paramBundle = null)
    {
      if (paramBundle != null)
      {
        paramLong = System.currentTimeMillis();
        if ((paramBundle != null) && (paramBundle.size() > 0)) {
          D.a(paramBundle);
        }
        wd.a(null, "mRecipientsEditor addRecipient", paramLong);
      }
      for (;;)
      {
        an();
        aY();
        l(localIntent.hasExtra("subject"));
        if (i1 == 0) {
          ad();
        }
        U();
        if (Log.isLoggable("Mms:app", 2)) {
          b("update title, mConversation=" + a.toString());
        }
        a(a.h());
        aW = 0;
        T = false;
        paramBundle = b;
        if (a.h().size() > 1) {
          bool = true;
        }
        paramBundle.b(bool);
        i();
        a(getSupportActionBar());
        z();
        if ((MmsApp.a) && (MessagingPreferenceActivity.c(this))) {
          i(true);
        }
        aG = localIntent.hasExtra("extra_from_create_new_btn");
        return;
        i1 = 0;
        break;
        paramBundle = new ArrayList(Arrays.asList(gq.a(localUri.getSchemeSpecificPart(), false, true).c()));
        if ((paramBundle != null) && (paramBundle.size() > 0))
        {
          D.a(paramBundle);
          if ((y != null) && (!y.isFocused()) && (!c.j())) {
            y.requestFocus();
          }
        }
      }
      W();
      if (aba.a().b()) {
        aV = true;
      }
      aaa.b(getSupportActionBar(), this);
      paramBundle = b;
      if (bh())
      {
        i2 = 1;
        paramBundle.a(i2);
        paramBundle = b;
        if (!abu.a()) {
          break label535;
        }
      }
      for (int i2 = abu.b;; i2 = abu.a)
      {
        paramBundle.b(i2);
        break;
        i2 = 0;
        break label498;
      }
    }
  }
  
  public final void a(MessageListItemBase paramMessageListItemBase)
  {
    if (paramMessageListItemBase == null) {
      return;
    }
    vv localvv = paramMessageListItemBase.getMessageItem();
    if (localvv == null)
    {
      Log.e("Mms/compose", "showAlertDialog():msgItem = null");
      return;
    }
    boolean bool2 = c(localvv.j());
    xv localxv = new xv();
    localxv.a(new si(this));
    if ((j != null) && ((bool2) || (j.c()))) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      localxv.a(paramMessageListItemBase, this, bool1, w, bool2, a.d(), I().size(), localvv);
      return;
    }
  }
  
  public final void a(MessageListItemBase paramMessageListItemBase, String paramString)
  {
    if (paramMessageListItemBase == null) {
      return;
    }
    paramMessageListItemBase = new AlertDialog.Builder(this);
    paramMessageListItemBase.setTitle(2131493310);
    paramMessageListItemBase.setMessage(getString(2131493309, new Object[] { paramString }));
    paramMessageListItemBase.setPositiveButton(2131493308, new sg(this));
    paramMessageListItemBase.setNegativeButton(getResources().getString(2131493239), new sh(this));
    U = paramMessageListItemBase.show();
  }
  
  public void a(gm paramgm)
  {
    if ((TextUtils.isEmpty(paramgm.d())) || (p())) {
      return;
    }
    aO.post(new sd(this, paramgm));
  }
  
  public void a(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!TextUtils.isEmpty(paramString))
    {
      if ((!aA()) || ((!k) && (!zv.m()))) {
        break label62;
      }
      ag = paramString;
      c(1, null);
    }
    label62:
    for (bool1 = bool2;; bool1 = h(paramString))
    {
      af.e();
      if (bool1) {
        d(true);
      }
      return;
    }
  }
  
  public void a(ArrayList<vv> paramArrayList, boolean paramBoolean, int paramInt)
  {
    if (paramArrayList.size() < 1)
    {
      Log.w("Mms/compose", "resend message list size <1");
      return;
    }
    if ((j.d()) && (!pj.a()))
    {
      U = new AlertDialog.Builder(this).setTitle(getString(2131493249)).setPositiveButton(2131493166, new b(null)).show();
      return;
    }
    if ("sms".equals(get0d))
    {
      b(paramArrayList, paramBoolean, paramInt);
      return;
    }
    a((vv)paramArrayList.get(0), paramBoolean, paramInt);
  }
  
  public void a(vv paramvv, boolean paramBoolean, int paramInt)
  {
    if (paramvv.m())
    {
      if (paramBoolean)
      {
        Log.i("Mms/compose", "reSendMmsMessageItem resend flyme mms in flyme service");
        c.a(paramvv.K(), L, false, null, paramvv.Y(), paramvv.L(), a.e(), -10, false);
      }
      return;
    }
    if ((paramBoolean) && (!paramvv.i()))
    {
      String str2 = paramvv.Z();
      String str1 = str2;
      if (TextUtils.isEmpty(str2))
      {
        str1 = str2;
        if (paramvv.X() > 0L) {
          str1 = "content://mms/part/" + paramvv.X();
        }
      }
      Log.i("Mms/compose", "reSendMmsMessageItem resend normal mms in flyme service");
      c.a(paramvv.K(), 2, true, str1, paramvv.Y(), paramvv.L(), a.e(), -10, true);
      return;
    }
    int i1 = paramvv.ag();
    paramInt = b(i1, paramInt, false);
    if (i1 != paramInt) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      Log.i("Mms/compose", "reSendMmsMessageItem resend normal mms in normal selectedSlotId = " + paramInt + ", defaultSlotId = " + i1);
      c.a(paramvv.K(), L, false, null, paramvv.Y(), paramvv.L(), a.e(), paramInt, paramBoolean);
      return;
    }
  }
  
  public void a(boolean paramBoolean)
  {
    runOnUiThread(new qt(this, paramBoolean));
  }
  
  public void a(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length < 1)) {
      Log.e("Mms/compose", "checkSipDestAddrIsAvailable numbers is null");
    }
    do
    {
      for (;;)
      {
        return;
        Log.i("Mms/compose", "checkSipDestAddrIsAvailable numbers length " + paramArrayOfString.length + ", isSipStatuOffline = " + aba.a().c());
        if ((!aba.a().c()) && (!wd.a(paramArrayOfString, this))) {
          break;
        }
        int i2 = paramArrayOfString.length;
        int i1 = 0;
        while ((i1 < i2) && (!a(paramArrayOfString[i1], nd.c.a, 0))) {
          i1 += 1;
        }
      }
    } while (!aba.a().b());
    aba.a().a(this, paramArrayOfString);
  }
  
  public void b()
  {
    runOnUiThread(new qs(this));
  }
  
  public void b(int paramInt)
  {
    runOnUiThread(new qy(this, paramInt));
  }
  
  public void b(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      runOnUiThread(n);
      return;
    }
    runOnUiThread(m);
  }
  
  public int c(String paramString)
  {
    int i2 = zv.a(paramString);
    Log.i("Mms/compose", "getSelectSlotId msgItemImsi = " + paramString + ", slotId = " + i2);
    int i1 = i2;
    if (i2 == -1) {
      i1 = q(false);
    }
    return i1;
  }
  
  public void c()
  {
    if ((a != null) && (p())) {
      a.g();
    }
  }
  
  public boolean c(boolean paramBoolean)
  {
    if (paramBoolean) {
      return l();
    }
    return k();
  }
  
  public void d()
  {
    runOnUiThread(new qw(this));
  }
  
  public final void d(String paramString)
  {
    c.a(paramString);
    c(0, null);
  }
  
  public boolean d(boolean paramBoolean)
  {
    if (getCurrentFocus() == null) {
      return false;
    }
    InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    be.a(localInputMethodManager);
    boolean bool = be.c();
    if ((bool) && (!paramBoolean)) {
      return localInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
    if ((!bool) && (paramBoolean)) {
      return localInputMethodManager.showSoftInput(getCurrentFocus(), 0);
    }
    return false;
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0) {
      onUserInteraction();
    }
    if (x()) {}
    while (getWindow().superDispatchTouchEvent(paramMotionEvent)) {
      return true;
    }
    return onTouchEvent(paramMotionEvent);
  }
  
  public Activity e()
  {
    return this;
  }
  
  public void e(boolean paramBoolean)
  {
    if ((y.getInputType() & 0x2000F) == 131073) {}
    for (int i1 = 1;; i1 = 0)
    {
      if (((i1 != 0) && (paramBoolean)) || ((i1 == 0) && (!paramBoolean))) {
        y.setSingleLine(paramBoolean);
      }
      return;
    }
  }
  
  public void f(boolean paramBoolean)
  {
    long l1 = 0L;
    if (paramBoolean) {
      l1 = c.w();
    }
    TimerMessageHelpler.a(this, l1, new sv(this));
  }
  
  public boolean f()
  {
    return false;
  }
  
  public void finish()
  {
    d(false);
    super.finish();
    if (aG) {
      aaa.e(this);
    }
  }
  
  public ListView g()
  {
    return C;
  }
  
  public boolean h()
  {
    return by;
  }
  
  public void i()
  {
    int i1 = 2131493613;
    int i2 = 2131493153;
    if (y == null) {
      return;
    }
    if (B.f())
    {
      y.setHint(2131493698);
      return;
    }
    if (c.u())
    {
      y.setHint(null);
      return;
    }
    if (r())
    {
      y.setHint(2131493614);
      return;
    }
    if (k())
    {
      y.setHint(2131493612);
      return;
    }
    if ((p()) && (D != null) && (D.b()) && (D.getRecipientCount() == 1))
    {
      if ((MmsApp.a) && (!MmsApp.n))
      {
        y.setHint(2131493153);
        return;
      }
      y.setHint(2131493612);
      return;
    }
    if (I().b())
    {
      if ((MmsApp.a) && (!MmsApp.n))
      {
        y.setHint(2131493153);
        return;
      }
      y.setHint(2131493612);
      return;
    }
    if (h)
    {
      localEditTextEx = y;
      if ((MmsApp.a) && (!MmsApp.n)) {}
      for (i1 = i2;; i1 = 2131493613)
      {
        localEditTextEx.setHint(i1);
        return;
      }
    }
    if (aS())
    {
      y.setHint(2131493613);
      return;
    }
    EditTextEx localEditTextEx = y;
    if (MmsApp.n) {}
    for (;;)
    {
      localEditTextEx.setHint(i1);
      return;
      i1 = 2131493153;
    }
  }
  
  public boolean j()
  {
    return (k) && (l == 0);
  }
  
  public boolean k()
  {
    return (k) && (l > -1) && (aT());
  }
  
  public boolean l()
  {
    return (k) && (l == 1) && (aT());
  }
  
  public boolean m()
  {
    return (k) && (l == 1);
  }
  
  public final int n()
  {
    int i1 = 2;
    if ((p()) && (D != null) && (D.getRecipientCount() == 0)) {
      if (aba.a().b()) {
        i1 = 1;
      }
    }
    while (r())
    {
      do
      {
        return i1;
      } while (pj.a());
      return 0;
    }
    if (l()) {
      return 1;
    }
    return 0;
  }
  
  public void o()
  {
    if (!u)
    {
      a.a(true);
      a.c();
      l(9527);
      S();
    }
    ad();
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    int i1 = 0;
    J = false;
    if (c.g()) {
      c.o();
    }
    if (paramInt2 != -1) {}
    label30:
    label196:
    do
    {
      Object localObject;
      do
      {
        String str1;
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          do
                          {
                            do
                            {
                              do
                              {
                                do
                                {
                                  return;
                                  if (Log.isLoggable("Mms", 2))
                                  {
                                    if (paramIntent == null) {
                                      break label196;
                                    }
                                    b("onActivityResult(" + paramInt1 + ", " + paramInt1 + "), dataUri = " + paramIntent.getData());
                                  }
                                  for (;;)
                                  {
                                    switch (paramInt1)
                                    {
                                    case 108: 
                                    default: 
                                      return;
                                    case 100: 
                                      if (paramIntent == null) {
                                        break label30;
                                      }
                                      aa = true;
                                      a(paramIntent.getData(), false);
                                      return;
                                      b("onActivityResult(" + paramInt1 + ", " + paramInt1 + "), data is null...");
                                    }
                                  }
                                } while (paramIntent == null);
                                paramIntent = hb.a(this, paramIntent.getData());
                              } while (paramIntent == null);
                              c = paramIntent;
                              paramIntent.a(c.k(), false);
                              c.a(a);
                              as();
                              l(false);
                              an();
                              return;
                            } while (paramIntent == null);
                            wd.e(paramIntent.getData().getPath());
                            return;
                          } while (paramIntent == null);
                          localObject = paramIntent.getStringExtra("attach_name");
                          wd.e(paramIntent.getData().getPath());
                          a(aP, (String)localObject);
                          return;
                        } while (paramIntent == null);
                        aa = true;
                        paramInt1 = paramIntent.getIntExtra("Camera_Type", 0);
                        paramInt2 = paramIntent.getIntExtra("isFlymeMms", 1);
                        if (paramInt1 == 0)
                        {
                          paramIntent = paramIntent.getData();
                          if (paramInt2 == 1)
                          {
                            paramIntent = TempFileProvider.b;
                            MmsApp.c().f().a(paramIntent);
                          }
                          a(paramIntent, false);
                          return;
                        }
                        paramIntent = paramIntent.getData();
                        if (paramInt2 == 1)
                        {
                          paramIntent = TempFileProvider.c(this);
                          MmsApp.c().f().a(paramIntent);
                        }
                        c(paramIntent, false);
                        return;
                      } while (paramIntent == null);
                      ac = paramIntent.getStringExtra("location_content");
                      ad = paramIntent.getStringExtra("location_shortaddress");
                      if (paramIntent.getBooleanExtra("send_out_directly", false))
                      {
                        c(2, paramIntent.getData());
                        return;
                      }
                      aa = h(ac + "\n" + ad);
                      return;
                      paramIntent = TempFileProvider.c(this);
                      MmsApp.c().f().a(paramIntent);
                      c(paramIntent, false);
                      return;
                    } while (paramIntent == null);
                    c(paramIntent.getData(), false);
                    return;
                    paramIntent = (Uri)paramIntent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
                  } while (Settings.System.DEFAULT_RINGTONE_URI.equals(paramIntent));
                  a(paramIntent);
                  return;
                  if (paramIntent != null)
                  {
                    if (MmsApp.a)
                    {
                      if (paramIntent.getData() != null)
                      {
                        a(paramIntent.getData());
                        return;
                      }
                      Log.i("Mms/compose", "REQUEST_CODE_RECORD_SOUND data.getData is null");
                      return;
                    }
                    a(paramIntent.getData());
                    F();
                    return;
                  }
                  Log.i("Mms/compose", "REQUEST_CODE_RECORD_SOUND data is null");
                  return;
                } while (!paramIntent.getBooleanExtra("exit_ecm_result", false));
                n(false);
                return;
                paramInt1 = paramIntent.getIntExtra("com.android.contacts.extra.REQUESTED_INFO_TYPE", -1);
              } while ((paramInt1 != 1) && (paramInt1 != 0));
              paramIntent = paramIntent.getData();
            } while (paramIntent == null);
            aa = true;
            if (paramInt1 == 1)
            {
              l1 = Long.parseLong(paramIntent.getLastPathSegment());
              j(new acu(this).a(l1));
            }
            for (;;)
            {
              ga.a(this, paramInt1);
              return;
              if (b(false, false)) {
                e(paramIntent, false);
              }
            }
            paramIntent = paramIntent.getData();
            if (paramIntent == null)
            {
              c(-1, 2131492877);
              return;
            }
            aa = true;
            localObject = paramIntent.getPath();
            if ((!TextUtils.isEmpty((CharSequence)localObject)) && (((String)localObject).lastIndexOf('/') > 0)) {
              wd.f(((String)localObject).substring(0, ((String)localObject).lastIndexOf('/')));
            }
            localObject = wd.j((String)aau.b(Uri.class, paramIntent, "toSafeString"));
            str1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension((String)localObject);
            if (MzContentType.isSupportedImageType(str1))
            {
              a(paramIntent, false);
              return;
            }
            if (MzContentType.isSupportedVideoType(str1))
            {
              c(paramIntent, false);
              return;
            }
            if (MzContentType.isSupportedAudioType(str1))
            {
              a(paramIntent);
              return;
            }
            if (MzContentType.isSupportedVcardType(str1))
            {
              f(paramIntent, false);
              return;
            }
            if ((l()) || (r()) || ((p()) && (D != null) && (D.getRecipientCount() == 0) && ((aba.a().b()) || (pj.a()))))
            {
              g(paramIntent, false);
              return;
            }
            c(-3, 2131492877);
            Log.e("Mms/compose", "add file from doc failed, uri: " + paramIntent + ", extension: " + (String)localObject + ", mimeType: " + str1);
            return;
            paramIntent = paramIntent.getExtras();
          } while ((paramIntent == null) || (!paramIntent.containsKey("com.android.contacts.extra.MULTIPLE_PICK_DATAS")));
          paramIntent = paramIntent.getParcelableArray("com.android.contacts.extra.MULTIPLE_PICK_DATAS");
        } while ((paramIntent == null) || (paramIntent.length <= 0));
        localObject = new ArrayList();
        paramInt2 = paramIntent.length;
        paramInt1 = i1;
        if (paramInt1 < paramInt2)
        {
          String str2 = paramIntent[paramInt1];
          str1 = ((ContentValues)str2).getAsString("display_name");
          str2 = ((ContentValues)str2).getAsString("data1");
          if (TextUtils.isEmpty(str1)) {
            ((ArrayList)localObject).add(str2);
          }
          for (;;)
          {
            paramInt1 += 1;
            break;
            ((ArrayList)localObject).add(str2 + ";" + str1);
          }
        }
        if (((ArrayList)localObject).size() < 150)
        {
          D.a((ArrayList)localObject);
          ((ArrayList)localObject).clear();
          return;
        }
      } while (((ArrayList)localObject).size() < 150);
      g(2131493534);
      aO.postDelayed(new rd(this, (ArrayList)localObject), 200L);
      return;
      if (C != null) {
        C.a();
      }
      onNewIntent(paramIntent);
      return;
    } while (paramIntent == null);
    long l1 = paramIntent.getLongExtra("extra_time", 0L);
    Log.v("Mms/compose", "REQUEST_CODE_SELECT_TIME selectedTime = " + l1);
    Log.v("Mms/compose", "REQUEST_CODE_SELECT_TIME currentTime = " + System.currentTimeMillis());
    if (l1 < System.currentTimeMillis())
    {
      wd.a(getResources().getString(2131493777), this, 0, 1, true, 0, aN());
      return;
    }
    aa = true;
    b(0L);
    B.b(true);
    c.a(l1);
  }
  
  public void onBackPressed()
  {
    if (C.n()) {
      return;
    }
    if (y())
    {
      t(false);
      aQ();
      return;
    }
    if ((MmsApp.a) && (p()) && (!aZ()) && (c.f()))
    {
      ba();
      return;
    }
    a(new qr(this));
  }
  
  public void onClick(View paramView)
  {
    if (paramView == y)
    {
      aX = wd.g(this);
      if (i(aW))
      {
        a(f.b, 0);
        if ((B != null) && (B.e())) {
          B.a(false, 0);
        }
      }
    }
    do
    {
      do
      {
        do
        {
          return;
        } while ((!aX) || (!c.u()) || (B == null) || (!B.e()));
        B.a(false, 0);
        return;
      } while (paramView != aB);
      if (as == 1)
      {
        p(3);
        y.post(new rn(this));
        return;
      }
    } while (as != 3);
    bl = true;
    d(false);
    y.postDelayed(new ro(this), 200L);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    a(paramConfiguration);
    U();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    w = ga.C();
    super.onCreate(paramBundle);
    if (MmsApp.n) {
      setTheme(2131624161);
    }
    for (;;)
    {
      Z = 0L;
      bf = new Time();
      bf.setToNow();
      a(getResources().getConfiguration());
      setContentView(2130968614);
      at();
      N();
      ae();
      s = getContentResolver();
      t = new a(s);
      a(paramBundle, 0L);
      if (a != null) {
        a.e(false);
      }
      au();
      l(9527);
      MmsApp.c().a(o);
      MmsApp.c().a(this);
      zv.a(aL());
      zv.c(aM());
      return;
      setTheme(2131624162);
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131951617, paramMenu);
    aI = paramMenu.findItem(2131886795);
    aJ = paramMenu.findItem(2131886794);
    return true;
  }
  
  protected void onDestroy()
  {
    unregisterReceiver(bh);
    MmsApp.c().b(o);
    MmsApp.c().b(this);
    if (av != null)
    {
      zv.b(av);
      zv.d(aw);
    }
    if (b != null)
    {
      b.changeCursor(null);
      b.a();
    }
    super.onDestroy();
    if (a != null) {
      a.e(false);
    }
    if (af != null) {
      af.d();
    }
    aO();
    aab.b = false;
    aab.a = false;
    ne.a(null);
    ael.c();
    if ((I() != null) && (I().size() == 1))
    {
      String str = ((gm)I().get(0)).d();
      XySdkUtil.clearCache(hashCode(), str);
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    }
    do
    {
      for (;;)
      {
        return super.onKeyDown(paramInt, paramKeyEvent);
        if ((b != null) && (C.isFocused())) {
          try
          {
            Cursor localCursor = (Cursor)C.getSelectedItem();
            if (localCursor != null)
            {
              paramKeyEvent = localCursor.getString(0);
              long l1 = localCursor.getLong(1);
              paramKeyEvent = b.a(paramKeyEvent, l1, localCursor);
              if (paramKeyEvent != null) {
                a(new e(paramKeyEvent), j);
              }
              return true;
            }
          }
          catch (ClassCastException localClassCastException)
          {
            Log.e("Mms/compose", "Unexpected ClassCastException.", localClassCastException);
            return super.onKeyDown(paramInt, paramKeyEvent);
          }
        }
      }
    } while (!ai());
    F();
    return true;
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    setIntent(paramIntent);
    H = false;
    long l1 = a.e();
    long l2 = paramIntent.getLongExtra("thread_id", 0L);
    Z = paramIntent.getLongExtra("sending_msg_id", 0L);
    Uri localUri = paramIntent.getData();
    gr localgr;
    int i1;
    if (l2 > 0L)
    {
      localgr = gr.a(MmsApp.c(), l2, false);
      if (Z > 0L) {
        localgr.e(true);
      }
      if (Log.isLoggable("Mms:app", 2)) {
        b("onNewIntent: data=" + localUri + ", thread_id extra is " + l2 + ", new conversation=" + localgr + ", mConversation=" + a);
      }
      if (((localgr.e() != a.e()) && (a.e() != 0L)) || (!localgr.equals(a))) {
        break label368;
      }
      i1 = 1;
      label195:
      if (i1 == 0) {
        break label390;
      }
      b("onNewIntent: same conversation");
      if (a.e() != 0L) {
        break label373;
      }
      a = localgr;
      c.a(a);
      as();
      z();
      label242:
      if ((paramIntent.getBooleanExtra("init_imsi_from_converation", false)) || ((localUri != null) && (localUri.getScheme().equals("smsto")))) {
        al = a.x();
      }
      if (zv.a) {
        b(paramIntent);
      }
    }
    for (;;)
    {
      o();
      if (!aS()) {
        break label492;
      }
      aD = new aad();
      aD.a();
      v(false);
      return;
      if (a.e() == 0L) {
        c.m();
      }
      localgr = gr.a(MmsApp.c(), localUri, false, false);
      e = true;
      break;
      label368:
      i1 = 0;
      break label195;
      label373:
      if (C == null) {
        break label242;
      }
      C.a();
      break label242;
      label390:
      if (Log.isLoggable("Mms:app", 2)) {
        b("onNewIntent: different conversation");
      }
      m(false);
      P();
      a(null, l1);
      a(f.e, 0);
      if (b != null)
      {
        b.changeCursor(null);
        b.a();
      }
      if ((!p()) && (j != null))
      {
        a(a.h(), true);
        j.setFlymeState(true);
      }
      aG();
    }
    label492:
    w = ga.C();
    aY();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if ((paramMenuItem.getAlphabeticShortcut() == 'c') && (!p)) {
      g(2131493535);
    }
    do
    {
      do
      {
        return true;
        switch (paramMenuItem.getItemId())
        {
        default: 
          return true;
        case 7: 
          c.s();
          gr.u();
          fl.a(this);
          return true;
        case 16908332: 
          a(new rb(this));
          return true;
        }
      } while (j == null);
      j.i();
      return true;
    } while (j == null);
    j.b();
    return true;
  }
  
  protected void onPause()
  {
    super.onPause();
    MessagingNotification.a(-2L);
    if (N != null) {
      N.a();
    }
    if ((!MmsApp.a) && (p()) && (y != null))
    {
      wd.a(((CharSequence)aau.b("android.text.MzTextUtils", "removeComposing", CharSequence.class, y.getText())).toString(), am);
      if (!G) {}
    }
    MmsApp.c().a(a.e());
    a.c();
    R = false;
    InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    be.d();
    if (aL) {
      c.a(this);
    }
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    super.onPrepareOptionsMenu(paramMenu);
    if (j != null)
    {
      j.a(aI);
      j.b(aJ);
    }
    return true;
  }
  
  protected void onRestart()
  {
    super.onRestart();
    if (c.r())
    {
      if (!c.f()) {
        break label79;
      }
      c.q();
      Q();
    }
    for (;;)
    {
      if (MmsApp.a)
      {
        r = new g(new Handler());
        getContentResolver().registerContentObserver(Uri.parse("content://mms-sms/"), true, r);
      }
      return;
      label79:
      if ((X()) && (aj() > 0))
      {
        V();
      }
      else
      {
        ah();
        c.a(a);
        B.a(c);
      }
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    if (N != null) {
      N.b();
    }
    if (!p())
    {
      if (Y.getVisibility() == 0) {
        Y.setVisibility(8);
      }
      a(a.h(), true);
    }
    for (;;)
    {
      Object localObject = (InputMethodManager)getSystemService("input_method");
      be.a((InputMethodManager)localObject);
      be.b();
      if ((aX) || (e) || (aa))
      {
        e = false;
        aa = false;
        aO.postDelayed(new qp(this, (InputMethodManager)localObject), 250L);
      }
      if (Log.isLoggable("Mms:app", 2)) {
        b("update title, mConversation=" + a.toString());
      }
      R = true;
      as();
      a.c();
      return;
      h(false);
      aV = false;
      if ((y != null) && (y.getText().length() == 0) && (!c.h()))
      {
        localObject = wd.c();
        if (!TextUtils.equals((CharSequence)localObject, y.getText())) {
          if (localObject != null)
          {
            y.setTextKeepState((CharSequence)localObject);
            if (zv.a)
            {
              y.setSelection(y.length());
              aH();
            }
          }
          else
          {
            y.setText("");
          }
        }
      }
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    if (X())
    {
      paramBundle.putBoolean("is_new_conversation", true);
      if ((D != null) && (D.getRecipientDataList() != null) && (D.getRecipientDataList().size() > 0))
      {
        ArrayList localArrayList = D.getRecipientDataList();
        paramBundle.putString("recipients", TextUtils.join(";", (String[])localArrayList.toArray(new String[localArrayList.size()])));
      }
    }
    for (;;)
    {
      c.a(paramBundle);
      if (u) {
        paramBundle.putBoolean("exit_on_sent", u);
      }
      if (v) {
        paramBundle.putBoolean("forwarded_message", v);
      }
      if (zv.a) {
        paramBundle.putString("selected_imsi", zv.c(am));
      }
      super.onSaveInstanceState(paramBundle);
      return;
      paramBundle.putBoolean("is_new_conversation", false);
      paramBundle.putString("recipients", I().a());
    }
  }
  
  protected void onStart()
  {
    int i1 = 20;
    super.onStart();
    A();
    ao();
    registerReceiver(aM, new IntentFilter("android.intent.action.ACTION_SHUTDOWN"));
    if (e)
    {
      e = false;
      getWindow().setSoftInputMode(i1);
      R();
      if (T) {
        break label244;
      }
      c.m();
      label72:
      if (Log.isLoggable("Mms:app", 2)) {
        b("update title, mConversation=" + a.toString());
      }
      a(a.h());
      if (y != null) {
        e(false);
      }
      aq();
      MessagingNotification.b(this);
      if (!aS()) {
        break label252;
      }
      aD = new aad();
      aD.a();
      v(false);
    }
    for (;;)
    {
      if ((r == null) && (zt.c().b()))
      {
        r = new g(new Handler());
        getContentResolver().registerContentObserver(Uri.parse("content://mms-sms/"), true, r);
      }
      return;
      if (a.e() <= 0L) {
        break;
      }
      i1 = 18;
      break;
      label244:
      T = false;
      break label72;
      label252:
      w = ga.C();
      aY();
    }
  }
  
  protected void onStop()
  {
    super.onStop();
    t.cancelOperation(9527);
    ar();
    if (j != null) {
      j.k();
    }
    B.a(false, 0);
    a.a(false);
    new Thread(new qq(this)).start();
    if (Log.isLoggable("Mms:app", 2)) {
      b("save draft");
    }
    m(true);
    unregisterReceiver(aM);
    if (aD != null) {
      aD.b();
    }
    if (r != null) {
      getContentResolver().unregisterContentObserver(r);
    }
  }
  
  public void onUserInteraction()
  {
    ap();
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    if ((paramBoolean) && (ga.a()) && (y != null) && (y.hasFocus()) && (j(aW))) {
      y.postDelayed(new rc(this), 80L);
    }
  }
  
  public boolean p()
  {
    return (X()) || (a.e() <= 0L);
  }
  
  public final boolean q()
  {
    return i(aW);
  }
  
  public boolean r()
  {
    boolean bool = true;
    if (!pj.a()) {
      bool = false;
    }
    do
    {
      do
      {
        return bool;
        if (!X()) {
          break;
        }
      } while ((D.c()) && (D.getRecipientCount() == 1));
      return false;
    } while ((a != null) && (a.a()));
    return false;
  }
  
  public void s()
  {
    if (az != null)
    {
      if (((!l()) && (!r())) || (!w) || (c.d()) || (c.h()) || (c.l())) {
        break label71;
      }
      az.a(1);
    }
    for (;;)
    {
      i();
      return;
      label71:
      if (j())
      {
        az.a(0);
      }
      else
      {
        az.a(0);
        if ((b(aW, 4)) && (aX)) {
          a(f.a, 4);
        }
      }
    }
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    if (paramInt >= 0) {
      J = true;
    }
    if ((E) && (!"com.android.contacts.action.MZ_QUICK_CONTACT".equals(paramIntent.getAction())) && (paramInt != 111) && (paramInt != 109)) {
      u();
    }
    super.startActivityForResult(paramIntent, paramInt);
  }
  
  public pt t()
  {
    if (N == null) {
      N = new pt(this);
    }
    return N;
  }
  
  public void u()
  {
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(y.getWindowToken(), 0);
  }
  
  public final void v()
  {
    if ((c == null) || (c.r())) {
      return;
    }
    c.p();
  }
  
  protected void w()
  {
    if (p)
    {
      F();
      return;
    }
    g(2131493535);
  }
  
  boolean x()
  {
    if (W == null) {
      return false;
    }
    return W.b();
  }
  
  public boolean y()
  {
    return (ae != null) && (ae.getVisibility() == 0);
  }
  
  protected void z()
  {
    if (!aaw.a()) {
      return;
    }
    new Thread(new sr(this)).start();
  }
  
  public final class a
    extends gr.b
  {
    public a(ContentResolver paramContentResolver)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (what)
      {
      default: 
        super.handleMessage(paramMessage);
        return;
      }
      ComposeMessageActivity.c(ComposeMessageActivity.this, 9527);
    }
    
    protected void onDeleteComplete(int paramInt1, Object paramObject, int paramInt2)
    {
      super.onDeleteComplete(paramInt1, paramObject, paramInt2);
      switch (paramInt1)
      {
      }
      while (paramInt1 == 1801)
      {
        paramObject = a.h();
        Log.d("Mms/compose", "onDeleteComplete(DELETE_CONVERSATION_TOKEN), mWorkingMessage.isDiscarded() = " + c.r());
        c.q();
        c.p();
        if (paramObject != null)
        {
          paramObject = ((gq)paramObject).iterator();
          for (;;)
          {
            if (((Iterator)paramObject).hasNext())
            {
              ((gm)((Iterator)paramObject).next()).a();
              continue;
              a.a(0);
              MessagingNotification.a(ComposeMessageActivity.this, -2L, false);
              ComposeMessageActivity.Y(ComposeMessageActivity.this);
              break;
              paramObject = (List)paramObject;
              paramInt2 = 0;
              if (paramInt2 < ((List)paramObject).size())
              {
                if (((vv)((List)paramObject).get(paramInt2)).E()) {
                  hb.a(((vv)((List)paramObject).get(paramInt2)).K());
                }
                for (;;)
                {
                  paramInt2 += 1;
                  break;
                  hb.a(((vv)((List)paramObject).get(paramInt2)).Q());
                  MmsApp.c().e().a(gett);
                }
              }
              MessagingNotification.a(ComposeMessageActivity.this, -2L, false);
              ComposeMessageActivity.Y(ComposeMessageActivity.this);
              ComposeMessageActivity.J(ComposeMessageActivity.this);
              break;
            }
          }
        }
        gr.b(MmsApp.c());
        finish();
      }
      while (paramInt1 != 9700) {
        return;
      }
      ComposeMessageActivity.c(ComposeMessageActivity.this, 9528);
    }
    
    protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
    {
      int i = -1;
      if (isFinishing())
      {
        if (paramCursor != null) {
          paramCursor.close();
        }
        return;
      }
      long l;
      int j;
      String str;
      boolean bool;
      switch (paramInt)
      {
      default: 
        return;
      case 9527: 
        a.a(false);
        l = ((Long)paramObject).longValue();
        if (Log.isLoggable("Mms:app", 2)) {
          ComposeMessageActivity.b("##### onQueryComplete: msg history result for threadId " + l);
        }
        if (a.e() == 0L)
        {
          finish();
          return;
        }
        if (l != a.e())
        {
          ComposeMessageActivity.b("onQueryComplete: msg history query result is for threadId " + l + ", but mConversation has threadId " + a.e() + " starting a new query");
          if (paramCursor != null) {
            paramCursor.close();
          }
          ComposeMessageActivity.S(ComposeMessageActivity.this);
          return;
        }
        ComposeMessageActivity.T(ComposeMessageActivity.this);
        j = b.getCount();
        if ((paramCursor != null) && (paramCursor.getCount() == 0) && (j > 0)) {
          finish();
        }
        l = getIntent().getLongExtra("select_id", 0L);
        paramObject = getIntent().getStringExtra("group_msg_id");
        str = getIntent().getStringExtra("msg_type");
        if ((l != 0L) && (paramCursor != null))
        {
          getIntent().putExtra("select_id", 0L);
          paramCursor.moveToPosition(-1);
          if (str == null)
          {
            bool = false;
            for (;;)
            {
              label331:
              if (paramCursor.moveToNext()) {
                if (bool == paramCursor.getString(0).equals("mms")) {
                  if (bool)
                  {
                    if (paramCursor.getLong(1) != l) {
                      continue;
                    }
                    paramInt = paramCursor.getPosition();
                  }
                }
              }
            }
          }
        }
        break;
      }
      for (;;)
      {
        if (isFinishing())
        {
          if (paramCursor == null) {
            break;
          }
          paramCursor.close();
          return;
          bool = str.equals("mms");
          break label331;
          str = paramCursor.getString(37);
          if ((TextUtils.isEmpty(str)) || (!str.equals(paramObject))) {
            break label331;
          }
          paramInt = paramCursor.getPosition();
          continue;
        }
        b.a(ComposeMessageActivity.U(ComposeMessageActivity.this));
        b.changeCursor(paramCursor);
        int k = b.getCount();
        if (paramInt != -1)
        {
          ComposeMessageActivity.R(ComposeMessageActivity.this).setSelection(paramInt);
          label510:
          if (j != k) {
            ComposeMessageActivity.R(ComposeMessageActivity.this).a(paramCursor);
          }
          if ((paramCursor != null) && (aba.a().b()) && (aba.a().d()) && (!r()) && (!p()) && (ComposeMessageActivity.this.j != null) && (paramCursor.moveToLast()))
          {
            paramObject = b;
            l = paramCursor.getLong(30);
            paramObject = b;
            if (!"sms".equals(paramCursor.getString(0))) {
              break label913;
            }
            paramCursor = ComposeMessageActivity.this.j;
            if (l != 256L) {
              break label906;
            }
          }
        }
        label906:
        for (paramObject = nd.c.c;; paramObject = nd.c.a)
        {
          if (l == 256L) {
            i = 0;
          }
          paramCursor.a((nd.c)paramObject, i);
          a.a(b.getCount());
          if (a.k() > 0) {
            a.e(false);
          }
          if (((ComposeMessageActivity.k(ComposeMessageActivity.this) != null) && (ComposeMessageActivity.k(ComposeMessageActivity.this).isFocused()) && (ComposeMessageActivity.k(ComposeMessageActivity.this).isShown())) || (ComposeMessageActivity.l(ComposeMessageActivity.this) == null) || (!ComposeMessageActivity.l(ComposeMessageActivity.this).isShown()) || (ComposeMessageActivity.l(ComposeMessageActivity.this).isFocused())) {
            break;
          }
          ComposeMessageActivity.l(ComposeMessageActivity.this).requestFocus();
          return;
          if (j > k)
          {
            ComposeMessageActivity.q(ComposeMessageActivity.this, false);
            break label510;
          }
          if (ComposeMessageActivity.V(ComposeMessageActivity.this))
          {
            ComposeMessageActivity.b(ComposeMessageActivity.this, ComposeMessageActivity.V(ComposeMessageActivity.this), 0);
            ComposeMessageActivity.q(ComposeMessageActivity.this, false);
            break label510;
          }
          if ((j == k - 1) && (ComposeMessageActivity.v(ComposeMessageActivity.this)))
          {
            ComposeMessageActivity.b(ComposeMessageActivity.this, true, 0);
            break label510;
          }
          if (j == k) {
            break label510;
          }
          ComposeMessageActivity.W(ComposeMessageActivity.this).c();
          break label510;
        }
        label913:
        paramCursor = ComposeMessageActivity.this.j;
        if (l == 2L) {}
        for (paramObject = nd.c.c;; paramObject = nd.c.a)
        {
          if (l == 2L) {
            i = 1;
          }
          paramCursor.a((nd.c)paramObject, i);
          break;
        }
        l = ((Long)paramObject).longValue();
        if (Log.isLoggable("Mms:app", 2)) {
          ComposeMessageActivity.b("##### onQueryComplete (after delete): msg history result for threadId " + l);
        }
        if (paramCursor == null) {
          break;
        }
        if ((l > 0L) && (paramCursor.getCount() == 0))
        {
          ComposeMessageActivity.b("##### MESSAGE_LIST_QUERY_AFTER_DELETE_TOKEN clearing thread id: " + l);
          paramObject = gr.a(MmsApp.c(), l, false);
          if (paramObject != null)
          {
            ((gr)paramObject).g();
            ((gr)paramObject).b(false);
          }
          ComposeMessageActivity.a(ComposeMessageActivity.this, new tk(this));
        }
        paramCursor.close();
        return;
        paramInt = -1;
      }
    }
  }
  
  class b
    implements DialogInterface.OnClickListener
  {
    private b() {}
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      if (ComposeMessageActivity.i(ComposeMessageActivity.this)) {
        ComposeMessageActivity.j(ComposeMessageActivity.this).requestFocus();
      }
      paramDialogInterface.dismiss();
    }
  }
  
  class c
    implements gk.c
  {
    private c() {}
    
    public void a(int paramInt)
    {
      if (ComposeMessageActivity.t(ComposeMessageActivity.this) != paramInt)
      {
        ComposeMessageActivity.i(ComposeMessageActivity.this, paramInt);
        if (ComposeMessageActivity.t(ComposeMessageActivity.this) != -10) {
          break label56;
        }
        ComposeMessageActivity.j(ComposeMessageActivity.this, 1);
      }
      for (;;)
      {
        ComposeMessageActivity.s(ComposeMessageActivity.this);
        ComposeMessageActivity.ag(ComposeMessageActivity.this);
        return;
        label56:
        ComposeMessageActivity.j(ComposeMessageActivity.this, 0);
      }
    }
  }
  
  class d
    implements zv.a
  {
    private d() {}
    
    public void a(int paramInt1, int paramInt2)
    {
      if (ComposeMessageActivity.q(ComposeMessageActivity.this)) {}
      do
      {
        return;
        ComposeMessageActivity.p(ComposeMessageActivity.this);
        if (ComposeMessageActivity.u(ComposeMessageActivity.this) != null)
        {
          ComposeMessageActivity.u(ComposeMessageActivity.this).a();
          ComposeMessageActivity.al(ComposeMessageActivity.this);
          ComposeMessageActivity.u(ComposeMessageActivity.this).b(ComposeMessageActivity.t(ComposeMessageActivity.this));
        }
        invalidateOptionsMenu();
        if (ComposeMessageActivity.am(ComposeMessageActivity.this) != null) {
          ComposeMessageActivity.am(ComposeMessageActivity.this).a();
        }
      } while (!ComposeMessageActivity.b(ComposeMessageActivity.this, paramInt1, paramInt2));
      b.notifyDataSetChanged();
    }
  }
  
  public class e
    implements DialogInterface.OnClickListener
  {
    private final vv b;
    private final vv[] c;
    
    public e(vv paramvv)
    {
      b = paramvv;
      c = null;
    }
    
    public e(vv[] paramArrayOfvv)
    {
      c = paramArrayOfvv;
      if (c.length > 0)
      {
        b = c[0];
        return;
      }
      b = null;
    }
    
    public void a(boolean paramBoolean)
    {
      new tm(this, paramBoolean).execute(new Void[0]);
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      paramDialogInterface.dismiss();
      new tl(this).execute(new Void[0]);
    }
  }
  
  public static enum f
  {
    private f() {}
  }
  
  public class g
    extends ContentObserver
  {
    public g(Handler paramHandler)
    {
      super();
    }
    
    public void onChange(boolean paramBoolean)
    {
      super.onChange(paramBoolean);
      if (a.i())
      {
        ComposeMessageActivity.av(ComposeMessageActivity.this);
        c.a(a);
      }
      while (!zt.c().b()) {
        return;
      }
      new Handler().postDelayed(new tn(this), 200L);
    }
  }
  
  public class h
    implements MessageListView.b
  {
    private MenuItem b;
    private MenuItem c;
    private MenuItem d;
    private HashMap<Long, ComposeMessageActivity.j> e;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private ActionMode k;
    private MultiChoiceView l;
    private int m = 0;
    
    private h() {}
    
    private void a(vv paramvv, long paramLong)
    {
      e.remove(Long.valueOf(paramLong));
      if (u == 130) {
        j -= 1;
      }
      do
      {
        do
        {
          return;
          if (!paramvv.j()) {
            break;
          }
          if (paramvv.o())
          {
            h -= 1;
            return;
          }
          f -= 1;
          if (!paramvv.ae()) {
            g -= 1;
          }
        } while (!y);
        m -= 1;
        return;
      } while (M != vv.a);
      i -= 1;
    }
    
    private void a(boolean paramBoolean)
    {
      f = 0;
      g = 0;
      h = 0;
      i = 0;
      j = 0;
      m = 0;
      if (e != null) {
        e.clear();
      }
      if (paramBoolean) {
        e = null;
      }
    }
    
    private void b()
    {
      l = new MultiChoiceView(ComposeMessageActivity.this);
      l.setOnCloseItemClickListener(new tp(this));
      l.setOnSelectAllItemClickListener(new tq(this));
      k.setCustomView(l);
    }
    
    private void b(vv paramvv, long paramLong)
    {
      if (u == 130) {
        j += 1;
      }
      for (;;)
      {
        e.put(Long.valueOf(paramLong), new ComposeMessageActivity.j(ComposeMessageActivity.this, paramLong, paramvv));
        return;
        if (paramvv.j())
        {
          if (paramvv.o())
          {
            h += 1;
          }
          else
          {
            f += 1;
            if (!paramvv.ae()) {
              g += 1;
            }
            if (y) {
              m += 1;
            }
          }
        }
        else if (M == vv.a) {
          i += 1;
        }
      }
    }
    
    public ActionMode a()
    {
      return k;
    }
    
    public void a(int paramInt)
    {
      if (paramInt == 0)
      {
        aaa.a(b, false);
        aaa.a(c, false);
        aaa.a(d, false);
        if (!ga.C())
        {
          aaa.a(d, false);
          aaa.a(c, false);
        }
        aaa.a(ComposeMessageActivity.this, l, ComposeMessageActivity.R(ComposeMessageActivity.this).getCheckedItemCount(), b.getCount());
        return;
      }
      if ((j > 0) || (i > 0) || (g > 0) || (h > 0))
      {
        aaa.a(b, false);
        label118:
        if ((j <= 0) && (i <= 0) && ((paramInt <= 1) || (f <= 0)) && (h <= 0)) {
          break label181;
        }
        aaa.a(c, false);
      }
      for (;;)
      {
        aaa.a(d, true);
        break;
        aaa.a(b, true);
        break label118;
        label181:
        if ((f == 1) && (paramInt == 1))
        {
          MenuItem localMenuItem = c;
          if ((ga.C()) && (m == 1)) {}
          for (boolean bool = true;; bool = false)
          {
            aaa.a(localMenuItem, bool);
            break;
          }
        }
        aaa.a(c, ga.C());
      }
    }
    
    public void a(Cursor paramCursor)
    {
      if ((e == null) || (e.size() == 0) || (b.getCount() == 0)) {}
      HashMap localHashMap;
      do
      {
        return;
        localHashMap = (HashMap)e.clone();
        paramCursor.moveToFirst();
        do
        {
          localHashMap.remove(Long.valueOf(vx.a(paramCursor.getString(0).equals("mms"), paramCursor.getLong(1))));
        } while ((paramCursor.moveToNext()) && (localHashMap.size() > 0));
      } while (localHashMap.size() == 0);
      paramCursor = localHashMap.values().iterator();
      while (paramCursor.hasNext()) {
        a(nextc, false);
      }
      if (e.size() == 0)
      {
        ComposeMessageActivity.R(ComposeMessageActivity.this).a();
        return;
      }
      a(e.size());
    }
    
    public void a(Menu paramMenu)
    {
      getMenuInflater().inflate(2131951618, paramMenu);
      b = paramMenu.findItem(2131886796);
      c = paramMenu.findItem(2131886797);
      d = paramMenu.findItem(2131886798);
    }
    
    public void a(vv paramvv, boolean paramBoolean)
    {
      long l1 = vx.a(paramvv.j(), e);
      boolean bool = e.containsKey(Long.valueOf(l1));
      if ((bool) && (!paramBoolean)) {
        a(paramvv, l1);
      }
      while ((bool) || (!paramBoolean)) {
        return;
      }
      b(paramvv, l1);
    }
    
    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      switch (paramMenuItem.getItemId())
      {
      default: 
        paramActionMode.finish();
      case 2131886796: 
      case 2131886797: 
        do
        {
          do
          {
            return true;
          } while (e.size() <= 0);
          try
          {
            ComposeMessageActivity.a(ComposeMessageActivity.this, e);
            return true;
          }
          catch (Exception paramMenuItem)
          {
            Log.e("Mms/compose", "exception : ", paramMenuItem);
            return true;
          }
          finally
          {
            paramActionMode.finish();
          }
        } while (e.size() <= 0);
        try
        {
          ComposeMessageActivity.a(ComposeMessageActivity.this, e, true);
          return true;
        }
        catch (Exception paramActionMode)
        {
          Log.e("Mms/compose", "exception : ", paramActionMode);
          return true;
        }
      }
      paramMenuItem = getResources().getQuantityString(2131427333, e.size(), new Object[] { Integer.valueOf(e.size()) });
      String str = getResources().getString(17039360);
      ComposeMessageActivity localComposeMessageActivity = ComposeMessageActivity.this;
      paramActionMode = new to(this, paramActionMode);
      wd.a(localComposeMessageActivity, new CharSequence[] { paramMenuItem, str }, paramActionMode).show();
      return true;
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      k = paramActionMode;
      if (e == null) {
        e = new HashMap();
      }
      b();
      ComposeMessageActivity.R(ComposeMessageActivity.this).c();
      a(paramMenu);
      onPrepareActionMode(paramActionMode, paramMenu);
      b.c(true);
      return true;
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      if (e != null)
      {
        paramActionMode = e.values().iterator();
        n = 0;
        while (paramActionMode.hasNext())
        {
          ComposeMessageActivity.j localj = (ComposeMessageActivity.j)paramActionMode.next();
          if (c != null) {
            c.b(false);
          }
          n += 1;
        }
      }
      ComposeMessageActivity.R(ComposeMessageActivity.this).d();
      if (!ga.a()) {
        ComposeMessageActivity.g(ComposeMessageActivity.this, 0);
      }
      ComposeMessageActivity.s(ComposeMessageActivity.this, true);
      a(true);
      k = null;
      ComposeMessageActivity.R(ComposeMessageActivity.this).setFinisMuiltTransientState(true);
      paramActionMode = ComposeMessageActivity.this;
      if (h) {}
      for (int n = 1;; n = 2)
      {
        ComposeMessageActivity.e(paramActionMode, n);
        ComposeMessageActivity.t(ComposeMessageActivity.this, false);
        b.c(false);
        return;
      }
    }
    
    public void onItemCheckedStateChanged(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
    {
      if (b.getItemId(paramInt) != paramLong) {
        return;
      }
      paramActionMode = (Cursor)b.getItem(paramInt);
      if ((paramActionMode == null) || (paramInt >= b.getCount()))
      {
        localObject = new StringBuilder().append("onItemCheckedStateChanged(1)--> position = ").append(paramInt).append(", cursor.getCount() = ");
        if (paramActionMode != null) {}
        for (paramInt = paramActionMode.getCount();; paramInt = 0)
        {
          Log.e("ComposeModecallback", paramInt);
          return;
        }
      }
      Object localObject = b.a(paramActionMode);
      if (localObject == null)
      {
        Log.e("ComposeModecallback", "onItemCheckedStateChanged(2)--> position = " + paramInt + ", cursor.getCount() = " + paramActionMode.getCount());
        return;
      }
      ((vv)localObject).b(paramBoolean);
      a((vv)localObject, paramBoolean);
      a(e.size());
    }
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      if (!ComposeMessageActivity.aa(ComposeMessageActivity.this))
      {
        c.setEnabled(false);
        d.setEnabled(false);
      }
      ComposeMessageActivity.s(ComposeMessageActivity.this, false);
      if (e != null) {
        a(e.size());
      }
      if (!ga.a()) {
        ComposeMessageActivity.g(ComposeMessageActivity.this, ComposeMessageActivity.ab(ComposeMessageActivity.this).getHeight());
      }
      ComposeMessageActivity.e(ComposeMessageActivity.this, 4);
      ComposeMessageActivity.t(ComposeMessageActivity.this, true);
      ComposeMessageActivity.a(ComposeMessageActivity.this, ComposeMessageActivity.f.e, 0);
      return true;
    }
  }
  
  class i
    implements DialogInterface.OnClickListener
  {
    private i() {}
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      if (ComposeMessageActivity.i(ComposeMessageActivity.this))
      {
        Iterator localIterator = ComposeMessageActivity.j(ComposeMessageActivity.this).getInvalidNumbers().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          ComposeMessageActivity.j(ComposeMessageActivity.this).b(str);
        }
        ComposeMessageActivity.j(ComposeMessageActivity.this).requestFocus();
        i();
      }
      paramDialogInterface.dismiss();
    }
  }
  
  class j
    implements Comparable<j>
  {
    public long a;
    public long b;
    public vv c;
    
    public j(long paramLong, vv paramvv)
    {
      a = paramLong;
      c = paramvv;
      if (c != null)
      {
        b = c.N;
        return;
      }
      b = 0L;
    }
    
    public int a(j paramj)
    {
      if (c == null) {
        return -1;
      }
      if (c == null) {
        return 1;
      }
      return (int)(b - b);
    }
  }
  
  public class k
    implements Runnable
  {
    private final Intent b;
    
    k(Intent paramIntent)
    {
      b = paramIntent;
    }
    
    public void run()
    {
      p = false;
      Uri localUri = ComposeMessageActivity.a(ComposeMessageActivity.this, b);
      if (isFinishing())
      {
        runOnUiThread(new tr(this));
        if (localUri != null) {
          new Thread(new ts(this, localUri), "WorkingMessage.asyncDelete").start();
        }
      }
      do
      {
        return;
        c = hb.a(ComposeMessageActivity.this, localUri, c, false);
        if (!isFinishing()) {
          break;
        }
        runOnUiThread(new tt(this));
      } while (c == null);
      c.p();
      return;
      if (c != null) {
        c.a(a);
      }
      p = true;
      runOnUiThread(new tu(this));
    }
  }
  
  class l
    implements afa
  {
    private l() {}
    
    public void a() {}
    
    public void a(aey paramaey)
    {
      if (paramaey != null)
      {
        ComposeMessageActivity.a(ComposeMessageActivity.this, new acu(ComposeMessageActivity.this).a(paramaey));
        ComposeMessageActivity.an(ComposeMessageActivity.this).sendEmptyMessage(ComposeMessageActivity.C());
      }
    }
    
    public void b() {}
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ComposeMessageActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.android.mms.ui;

import abm;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.TempFileProvider;
import com.google.android.mms.MmsException;
import fk;
import ga;
import gc;
import gd;
import lj;
import ll;
import lq;
import lr;
import lu;
import pt;
import us;
import wd;
import wd.d;
import xg;
import xz;
import ya;
import yb;
import yc;
import yd;
import ye;
import yf;
import yg;
import yh;
import yi;
import yj;
import yk;
import yw;

public class SlideEditorActivity
  extends AppCompatActivity
{
  private final wd.d A = new yb(this);
  private Handler B = new Handler();
  private Runnable C = new yc(this);
  private ImageButton a;
  private ImageButton b;
  private Button c;
  private Button d;
  private Button e;
  private EditText f;
  private Button g;
  private BasicSlideEditorView h;
  private lr i;
  private yw j;
  private SlideshowPresenter k;
  private boolean l;
  private int m;
  private Uri n;
  private pt o;
  private boolean p;
  private TextView q;
  private int r;
  private final lj s = new xz(this);
  private final View.OnClickListener t = new yd(this);
  private final BasicSlideEditorView.a u = new ye(this);
  private final View.OnClickListener v = new yf(this);
  private final View.OnClickListener w = new yg(this);
  private final View.OnClickListener x = new yh(this);
  private final View.OnClickListener y = new yi(this);
  private final View.OnClickListener z = new yj(this);
  
  private String a(int paramInt, String paramString)
  {
    return getResources().getString(paramInt, new Object[] { paramString });
  }
  
  private pt a()
  {
    if (o == null) {
      o = new pt(this);
    }
    return o;
  }
  
  private void a(int paramInt)
  {
    d.setText(paramInt);
  }
  
  private void a(Bundle paramBundle, Intent paramIntent)
  {
    if (paramBundle != null)
    {
      n = ((Uri)paramBundle.getParcelable("message_uri"));
      m = paramBundle.getInt("slide_index", 0);
      return;
    }
    n = paramIntent.getData();
    m = paramIntent.getIntExtra("slide_index", 0);
  }
  
  private void a(String paramString) {}
  
  private String b(int paramInt)
  {
    return getResources().getString(paramInt);
  }
  
  private void b()
  {
    wd.a(this, n, i, a());
  }
  
  private void c()
  {
    setTitle(getString(2131493122, new Object[] { Integer.valueOf(m + 1), Integer.valueOf(i.size()) }));
  }
  
  private void d()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setIcon(2130837746);
    String str = getResources().getString(2131492951);
    localBuilder.setTitle(str + (m + 1) + "/" + i.size());
    localBuilder.setItems(2131361795, new yk(this));
    localBuilder.show();
  }
  
  private void e()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setIcon(2130837747);
    String str = getResources().getString(2131492976);
    localBuilder.setTitle(str + (m + 1) + "/" + i.size());
    localBuilder.setAdapter(new us(this), new ya(this));
    localBuilder.show();
  }
  
  private String f()
  {
    return b(2131493151);
  }
  
  private String g()
  {
    return b(2131493152);
  }
  
  private String h()
  {
    return b(2131493155);
  }
  
  private void i()
  {
    k.setLocation(m);
    k.present(null);
    c();
    if (i.a(m).e())
    {
      a(2131493085);
      return;
    }
    a(2131492879);
  }
  
  private void j()
  {
    long l1 = (i.d() - 1L) / 1024L;
    q.setText(l1 + 1L + "K/" + r + "K");
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 != -1) {
      return;
    }
    switch (paramInt1)
    {
    }
    for (;;)
    {
      j();
      return;
      j.a(m, paramIntent.getAction());
      j();
      continue;
      try
      {
        paramIntent = TempFileProvider.a(".jpg", System.currentTimeMillis() + "", this, 1);
        if (paramIntent != null) {
          break label153;
        }
        paramInt1 = 1;
      }
      catch (MmsException paramIntent)
      {
        for (;;)
        {
          try
          {
            MmsApp.c().f().a(paramIntent);
            j.a(m, paramIntent);
            a(2131493085);
            paramInt1 = 0;
          }
          catch (fk localfk2)
          {
            continue;
          }
          catch (gc localgc2)
          {
            continue;
          }
          paramIntent = paramIntent;
          Log.e("SlideEditorActivity", "add image failed", paramIntent);
          paramInt1 = 1;
        }
      }
      catch (gd paramIntent)
      {
        for (;;)
        {
          wd.a(this, b(2131493160), null);
          paramInt1 = 0;
        }
      }
      catch (gc paramIntent)
      {
        for (;;)
        {
          paramIntent = null;
          wd.a(this, paramIntent, new Handler(), A, false, 1);
          paramInt1 = 0;
        }
      }
      catch (fk paramIntent)
      {
        for (;;)
        {
          label153:
          paramIntent = null;
          wd.a(this, paramIntent, new Handler(), A, false, 1);
        }
      }
      if (paramInt1 != 0)
      {
        a("add picture failed");
        wd.a(a(2131493832, g()), this, 0, 1, true, 0);
      }
      j();
      continue;
      try
      {
        Log.i("SlideEditorActivity", "onActivityResult REQUEST_CODE_CHANGE_PICTURE data.getData() = " + paramIntent.getData());
        j.a(m, paramIntent.getData());
        a(2131493085);
        j();
      }
      catch (MmsException paramIntent)
      {
        for (;;)
        {
          Log.e("SlideEditorActivity", "add image failed", paramIntent);
          a("add picture failed");
          wd.a(a(2131493832, g()), this, 0, 1, true, 0);
        }
      }
      catch (gd paramIntent)
      {
        for (;;)
        {
          Log.i("SlideEditorActivity", "onActivityResult UnsupportContentTypeException e = " + paramIntent);
          wd.a(this, b(2131493160), null);
        }
      }
      catch (gc localgc1)
      {
        for (;;)
        {
          wd.a(this, paramIntent.getData(), new Handler(), A, false, 1);
        }
      }
      catch (fk localfk1)
      {
        for (;;)
        {
          wd.a(this, paramIntent.getData(), new Handler(), A, false, 1);
        }
      }
      paramIntent = paramIntent.getData();
      if (paramIntent != null)
      {
        try
        {
          j.b(m, paramIntent);
          j();
        }
        catch (MmsException paramIntent)
        {
          for (;;)
          {
            Log.e("SlideEditorActivity", "add audio failed", paramIntent);
            a("add music failed");
            wd.a(a(2131493832, f()), this, 0, 1, true, 0);
          }
        }
        catch (gd paramIntent)
        {
          for (;;)
          {
            wd.a(this, b(2131493160), null);
          }
        }
        catch (fk paramIntent)
        {
          for (;;)
          {
            wd.a(this, b(2131493820), a(2131493832, f()));
          }
        }
      }
      else
      {
        Log.i("SlideEditorActivity", "requestCode = " + paramInt1 + ", uri is null");
        continue;
        try
        {
          Uri localUri = TempFileProvider.a(".3gp", System.currentTimeMillis() + "", this, 2);
          if (localUri != null) {
            j.c(m, localUri);
          }
        }
        catch (MmsException paramIntent)
        {
          a("add video failed");
          wd.a(a(2131493832, h()), this, 0, 1, true, 0);
          continue;
          Log.i("SlideEditorActivity", "REQUEST_CODE_TAKE_VIDEO videoUri is null , data.getData = " + paramIntent.getData());
        }
        catch (gd paramIntent)
        {
          wd.a(this, b(2131493160), null);
        }
        catch (fk paramIntent)
        {
          wd.a(this, b(2131493820), a(2131493832, h()));
        }
        continue;
        try
        {
          j.c(m, paramIntent.getData());
          j();
        }
        catch (MmsException paramIntent)
        {
          for (;;)
          {
            Log.e("SlideEditorActivity", "add video failed", paramIntent);
            a("add video failed");
            wd.a(a(2131493832, h()), this, 0, 1, true, 0);
          }
        }
        catch (gd paramIntent)
        {
          for (;;)
          {
            wd.a(this, b(2131493160), null);
          }
        }
        catch (fk paramIntent)
        {
          for (;;)
          {
            wd.a(this, b(2131493820), a(2131493832, h()));
          }
        }
        j.a(m, Integer.valueOf(paramIntent.getAction()).intValue() * 1000);
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968657);
    getSupportActionBar().setDisplayOptions(12);
    h = ((BasicSlideEditorView)findViewById(2131886435));
    h.setOnTextChangedListener(u);
    b = ((ImageButton)findViewById(2131886440));
    b.setOnClickListener(x);
    a = ((ImageButton)findViewById(2131886441));
    a.setOnClickListener(y);
    c = ((Button)findViewById(2131886442));
    c.setOnClickListener(v);
    d = ((Button)findViewById(2131886443));
    d.setOnClickListener(w);
    e = ((Button)findViewById(2131886444));
    e.setOnClickListener(t);
    f = ((EditText)findViewById(2131886445));
    f.setFilters(new InputFilter[] { new InputFilter.LengthFilter(ga.p()) });
    g = ((Button)findViewById(2131886446));
    g.setOnClickListener(z);
    q = ((TextView)findViewById(2131886438));
    q.setVisibility(0);
    r = ga.a(false);
    a(paramBundle, getIntent());
    for (;;)
    {
      try
      {
        i = lr.b(this, n);
        if (i.size() == 0)
        {
          Log.e("SlideEditorActivity", "Loaded slideshow is empty; can't edit nothingness, exiting.");
          finish();
          return;
        }
        i.c(s);
        j = new yw(this, i);
        k = ((SlideshowPresenter)xg.a("SlideshowPresenter", this, h, i));
        if (m >= i.size())
        {
          m = Math.max(0, i.size() - 1);
          i();
          j();
          return;
        }
      }
      catch (MmsException paramBundle)
      {
        Log.e("SlideEditorActivity", "Create SlideshowModel failed!", paramBundle);
        finish();
        return;
      }
      if (m < 0) {
        m = 0;
      }
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (i != null) {
      i.d(s);
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      j();
      return true;
      onBackPressed();
      continue;
      b();
      continue;
      paramMenuItem = i.a(m);
      if (paramMenuItem != null) {
        paramMenuItem.l();
      }
      j();
      continue;
      Log.i("SlideEditorActivity", "onOptionsItemSelected MENU_ADD_PICTURE");
      wd.b(this, 1);
      continue;
      wd.b(this, 2);
      continue;
      j.c(m);
      a(2131492879);
      j();
      continue;
      wd.a(this, 3, false, new String[] { "audio/*" });
      continue;
      i.a(m);
      wd.a(this, 4, wd.b(i));
      continue;
      j.e(m);
      j();
      continue;
      wd.a(this, 5, false, new String[] { "video/*" });
      continue;
      i.a(m);
      long l1 = wd.b(i);
      if (l1 > 0L)
      {
        wd.b(this, 7, l1);
      }
      else
      {
        wd.a(2131493013, this, 0, 1, true, 0);
        continue;
        j.d(m);
        j();
        continue;
        m += 1;
        if (j.a(m))
        {
          i();
          j();
        }
        else
        {
          m -= 1;
          wd.a(2131492903, this, 0, 1, true, 0);
          continue;
          e();
          continue;
          d();
        }
      }
    }
  }
  
  /* Error */
  protected void onPause()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 627	android/support/v7/app/AppCompatActivity:onPause	()V
    //   4: aload_0
    //   5: getfield 135	com/android/mms/ui/SlideEditorActivity:o	Lpt;
    //   8: ifnull +10 -> 18
    //   11: aload_0
    //   12: getfield 135	com/android/mms/ui/SlideEditorActivity:o	Lpt;
    //   15: invokevirtual 629	pt:a	()V
    //   18: aload_0
    //   19: monitorenter
    //   20: aload_0
    //   21: getfield 186	com/android/mms/ui/SlideEditorActivity:l	Z
    //   24: istore_1
    //   25: iload_1
    //   26: ifeq +32 -> 58
    //   29: aload_0
    //   30: getfield 195	com/android/mms/ui/SlideEditorActivity:i	Llr;
    //   33: invokevirtual 632	lr:a	()Lcom/meizu/android/mms/pdu/MzPduBody;
    //   36: astore_2
    //   37: aload_0
    //   38: invokestatic 638	com/meizu/android/mms/pdu/MzPduPersister:getPduPersister	(Landroid/content/Context;)Lcom/meizu/android/mms/pdu/MzPduPersister;
    //   41: aload_0
    //   42: getfield 161	com/android/mms/ui/SlideEditorActivity:n	Landroid/net/Uri;
    //   45: aload_2
    //   46: aconst_null
    //   47: invokevirtual 642	com/meizu/android/mms/pdu/MzPduPersister:updateParts	(Landroid/net/Uri;Lcom/meizu/android/mms/pdu/MzPduBody;Ljava/util/HashMap;)V
    //   50: aload_0
    //   51: getfield 195	com/android/mms/ui/SlideEditorActivity:i	Llr;
    //   54: aload_2
    //   55: invokevirtual 645	lr:a	(Lcom/meizu/android/mms/pdu/MzPduBody;)V
    //   58: aload_0
    //   59: monitorexit
    //   60: return
    //   61: astore_2
    //   62: ldc_w 394
    //   65: new 236	java/lang/StringBuilder
    //   68: dup
    //   69: invokespecial 237	java/lang/StringBuilder:<init>	()V
    //   72: ldc_w 647
    //   75: invokevirtual 241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: aload_0
    //   79: getfield 161	com/android/mms/ui/SlideEditorActivity:n	Landroid/net/Uri;
    //   82: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   85: invokevirtual 250	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   88: aload_2
    //   89: invokestatic 401	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   92: pop
    //   93: goto -35 -> 58
    //   96: astore_2
    //   97: aload_0
    //   98: monitorexit
    //   99: aload_2
    //   100: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	101	0	this	SlideEditorActivity
    //   24	2	1	bool	boolean
    //   36	19	2	localMzPduBody	com.meizu.android.mms.pdu.MzPduBody
    //   61	28	2	localMmsException	MmsException
    //   96	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   29	58	61	com/google/android/mms/MmsException
    //   20	25	96	finally
    //   29	58	96	finally
    //   58	60	96	finally
    //   62	93	96	finally
    //   97	99	96	finally
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    if (isFinishing()) {
      return false;
    }
    paramMenu.clear();
    lq locallq = i.a(m);
    if (locallq == null) {
      return false;
    }
    paramMenu.add(0, 11, 0, 2131493068).setIcon(2130837738);
    if (!p) {
      return true;
    }
    if ((locallq.d()) && (!TextUtils.isEmpty(locallq.p().a()))) {
      paramMenu.add(0, 0, 0, 2131493082).setIcon(2130837741);
    }
    if (locallq.e())
    {
      paramMenu.add(0, 3, 0, 2131493080).setIcon(2130837739);
      if (!locallq.f()) {
        break label362;
      }
      paramMenu.add(0, 5, 0, 2131493079).setIcon(2130837740);
      label161:
      if (!locallq.g()) {
        break label455;
      }
      paramMenu.add(0, 8, 0, 2131493083).setIcon(2130837742);
      label190:
      if (i.size() < 20) {
        paramMenu.add(0, 7, 0, 2131492880).setIcon(2130837729);
      }
      paramMenu.add(0, 10, 0, getResources().getString(2131492950).replace("%s", String.valueOf(locallq.a() / 1000))).setIcon(2130837732);
      if (i.f().g() != 1) {
        break label516;
      }
    }
    label362:
    label455:
    label516:
    for (int i1 = 2131492977;; i1 = 2131492975)
    {
      paramMenu.add(0, 9, 0, i1).setIcon(2130837737);
      return true;
      if (locallq.g()) {
        break;
      }
      paramMenu.add(0, 1, 0, 2131492879).setIcon(2130837737);
      paramMenu.add(0, 2, 0, 2131492894).setIcon(2130837737);
      break;
      if (locallq.g()) {
        break label161;
      }
      if (ga.z())
      {
        SubMenu localSubMenu = paramMenu.addSubMenu(0, 13, 0, 2131492878).setIcon(2130837730);
        localSubMenu.add(0, 4, 0, 2131492893);
        localSubMenu.add(0, 12, 0, 2131492890);
        break label161;
      }
      paramMenu.add(0, 12, 0, 2131492890).setIcon(2130837730);
      break label161;
      if ((locallq.f()) || (locallq.e())) {
        break label190;
      }
      paramMenu.add(0, 6, 0, 2131492883).setIcon(2130837735);
      paramMenu.add(0, 14, 0, 2131492891).setIcon(2130837735);
      break label190;
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    j();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putInt("slide_index", m);
    paramBundle.putParcelable("message_uri", n);
  }
  
  protected void onStart()
  {
    super.onStart();
    p = ga.C();
    e.setEnabled(p);
    d.setEnabled(p);
    f.setEnabled(p);
    f.setFocusableInTouchMode(p);
    g.setEnabled(p);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideEditorActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
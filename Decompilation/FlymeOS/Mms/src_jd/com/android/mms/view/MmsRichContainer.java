package com.android.mms.view;

import aen;
import aeo;
import aep;
import aeq;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import com.android.mms.MmsApp;
import com.android.mms.ui.AttachmentEditor;
import com.android.mms.ui.ComposeMessageActivity;
import ga;
import hb;
import ma;
import vw;
import vw.a;
import wd;

public class MmsRichContainer
  extends ScrollView
{
  private EditText a;
  private ImageView b;
  private EditTextEx c;
  private AttachmentEditor d;
  private Handler e;
  private int f = 500;
  private int g = 0;
  private ma h;
  private ma i;
  private e j;
  private View k;
  private Context l;
  private a m;
  private final View.OnKeyListener n = new aeo(this);
  private d o;
  private boolean p = false;
  
  public MmsRichContainer(Context paramContext)
  {
    super(paramContext);
    l = paramContext;
  }
  
  public MmsRichContainer(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    l = paramContext;
  }
  
  public MmsRichContainer(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    l = paramContext;
  }
  
  private View c(int paramInt)
  {
    return ((ViewStub)findViewById(paramInt)).inflate();
  }
  
  private void g()
  {
    d = ((AttachmentEditor)findViewById(2131886646));
  }
  
  private void h()
  {
    c = ((EditTextEx)findViewById(2131886654));
    c.setOnClickListener(new aen(this));
  }
  
  private boolean i()
  {
    if ((d.isSelected()) && (!c.isCursorVisible()))
    {
      e.sendEmptyMessage(11);
      a(true);
      return true;
    }
    return false;
  }
  
  private ma j()
  {
    return new aeq(this);
  }
  
  public void a()
  {
    if (d != null) {
      d.b();
    }
  }
  
  public void a(int paramInt)
  {
    if (d.isSelected())
    {
      e.sendEmptyMessage(11);
      a(true);
      return;
    }
    if (d.getHeight() > 20)
    {
      a(true, paramInt);
      return;
    }
    a(true);
  }
  
  public void a(boolean paramBoolean)
  {
    boolean bool = c.isCursorVisible();
    if ((paramBoolean) && (!bool)) {
      c.setCursorVisible(true);
    }
    while ((paramBoolean) || (!bool)) {
      return;
    }
    c.setCursorVisible(false);
  }
  
  public void a(boolean paramBoolean, int paramInt)
  {
    if ((!paramBoolean) && (!((ComposeMessageActivity)l).q())) {}
    for (boolean bool = true;; bool = false)
    {
      a(bool);
      if (d != null) {
        d.setSelected(paramBoolean);
      }
      return;
    }
  }
  
  public boolean a(hb paramhb)
  {
    int i1 = d.a(paramhb);
    boolean bool;
    if (i1 != -1)
    {
      bool = true;
      if (!bool) {
        break label65;
      }
      d.setOnClickListener(new c(i1));
      label36:
      paramhb = d;
      if (!bool) {
        break label76;
      }
    }
    label65:
    label76:
    for (i1 = 0;; i1 = 8)
    {
      paramhb.setVisibility(i1);
      a(false, 0);
      return bool;
      bool = false;
      break;
      d.setOnClickListener(null);
      break label36;
    }
  }
  
  public void b()
  {
    d.setVisibility(0);
    if (!MmsApp.a)
    {
      a.setVisibility(8);
      if (j != null) {
        j.a();
      }
    }
    c.setVisibility(8);
  }
  
  public void b(int paramInt)
  {
    if (paramInt == 0)
    {
      k.setPadding(0, 0, g, 0);
      return;
    }
    k.setPadding(0, 0, 0, 0);
  }
  
  public void b(boolean paramBoolean)
  {
    if (b == null)
    {
      if (paramBoolean)
      {
        b = ((ImageView)c(2131886653));
        b.setOnClickListener(new aep(this));
        b.setVisibility(0);
        p = true;
        ((ComposeMessageActivity)l).i();
      }
      return;
    }
    ImageView localImageView = b;
    if (paramBoolean) {}
    for (int i1 = 0;; i1 = 8)
    {
      localImageView.setVisibility(i1);
      p = paramBoolean;
      ((ComposeMessageActivity)l).i();
      return;
    }
  }
  
  public void c()
  {
    c.setVisibility(0);
  }
  
  public void d()
  {
    d.setVisibility(8);
    d.a();
  }
  
  public boolean e()
  {
    return (d != null) && (d.isSelected());
  }
  
  public boolean f()
  {
    return p;
  }
  
  public AttachmentEditor getAttachmentEditor()
  {
    return d;
  }
  
  public EditTextEx getBodyContent()
  {
    i = j();
    i.a(c);
    i.a(false);
    c.setOnKeyListener(n);
    return c;
  }
  
  public EditText getSubjectText()
  {
    a = ((EditText)c(2131886645));
    if (MmsApp.a) {
      a.setFilters(new InputFilter[] { new vw(ga.A(), new b(null), 2) });
    }
    for (;;)
    {
      h = j();
      h.a(a);
      h.a(false);
      return a;
      a.setFilters(new InputFilter[] { new InputFilter.LengthFilter(ga.A()) });
    }
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    f = getResources().getDimensionPixelSize(2131559268);
    setOverScrollMode(2);
    inflate(getContext(), 2130968767, this);
    g();
    h();
    k = findViewById(2131886644);
    g = getResources().getDimensionPixelSize(2131558776);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (getMeasuredHeight() > f) {
      setMeasuredDimension(getMeasuredWidth(), f);
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (o != null) {
      o.a(paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
  
  public void requestChildFocus(View paramView1, View paramView2)
  {
    super.requestChildFocus(paramView1, paramView2);
    if (paramView2 == a) {
      a(false, 0);
    }
  }
  
  public void setAfteronKeyPreImeListener(a parama)
  {
    m = parama;
  }
  
  public void setHandler(Handler paramHandler)
  {
    e = paramHandler;
    d.setHandler(paramHandler);
  }
  
  public void setOnSizeChangedListener(d paramd)
  {
    o = paramd;
  }
  
  public void setSubjectVisibility(boolean paramBoolean)
  {
    EditText localEditText = a;
    if (paramBoolean) {}
    for (int i1 = 0;; i1 = 8)
    {
      localEditText.setVisibility(i1);
      return;
    }
  }
  
  public void setSubjectVisibilityListener(e parame)
  {
    j = parame;
  }
  
  public static abstract interface a
  {
    public abstract boolean a(View paramView, int paramInt, KeyEvent paramKeyEvent);
  }
  
  class b
    implements vw.a
  {
    private b() {}
    
    public void a()
    {
      wd.a(2131493833, MmsRichContainer.a(MmsRichContainer.this), 0, 1, true, 0);
    }
  }
  
  class c
    implements View.OnClickListener
  {
    private int b;
    
    public c(int paramInt)
    {
      b = paramInt;
    }
    
    public void onClick(View paramView)
    {
      if (paramView.isSelected())
      {
        a(false, 0);
        return;
      }
      Message.obtain(MmsRichContainer.b(MmsRichContainer.this), b).sendToTarget();
    }
  }
  
  public static abstract interface d
  {
    public abstract void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  }
  
  public static abstract interface e
  {
    public abstract void a();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MmsRichContainer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
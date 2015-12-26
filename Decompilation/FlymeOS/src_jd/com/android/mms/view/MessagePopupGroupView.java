package com.android.mms.view;

import aee;
import aee.a;
import aej.a;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.android.mms.transaction.MessagePopupService;
import com.android.mms.transaction.MessagePopupService.b;

public class MessagePopupGroupView
  extends LinearLayout
  implements aee.a
{
  private View a;
  private MessagePopupTipView b;
  private MessagePopupReplyView c;
  private MessagePopupVeriCodeView d;
  private b e;
  private aej.a f = aej.a.a;
  private a g;
  private aee h;
  private boolean i = false;
  private boolean j = true;
  private Context k;
  
  public MessagePopupGroupView(Context paramContext)
  {
    super(paramContext);
    k = paramContext;
  }
  
  public MessagePopupGroupView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    k = paramContext;
  }
  
  public MessagePopupGroupView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    k = paramContext;
  }
  
  public void a(long paramLong, String paramString)
  {
    c.a(paramLong, paramString);
  }
  
  public void a(MessagePopupService.b paramb)
  {
    if (paramb.a())
    {
      a.setVisibility(8);
      d.setVisibility(0);
      d.a(paramb);
      return;
    }
    a.setVisibility(0);
    d.setVisibility(8);
    b.a(paramb);
    c.a(paramb);
  }
  
  public void a(MessagePopupService.b paramb, boolean paramBoolean, String paramString)
  {
    b.a(paramb, paramBoolean, paramString);
    c.a(paramb, paramBoolean, paramString);
  }
  
  public void a(a parama)
  {
    MessagePopupService.a(false, 3, "MessagePopupService.MessagePopupGroupView", "onBeginDrag()-->");
    requestDisallowInterceptTouchEvent(true);
    if (parama != null)
    {
      parama.e();
      e.f();
    }
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2, String paramString)
  {
    c.a(paramBoolean1, paramBoolean2, paramString);
  }
  
  public boolean a()
  {
    return i;
  }
  
  public void b(a parama)
  {
    MessagePopupService.a(false, 3, "MessagePopupService.MessagePopupGroupView", "onDragCancelled()-->");
    if (parama != null) {
      parama.f();
    }
  }
  
  public boolean b()
  {
    return true;
  }
  
  public void c()
  {
    MessagePopupService.a(false, 3, "MessagePopupService.MessagePopupGroupView", "onChildDismissed()-->");
    if (e == null) {
      return;
    }
    e.e();
  }
  
  public void d() {}
  
  public a getAnimatorObject()
  {
    return g;
  }
  
  public final String getEditText()
  {
    if ((c == null) || (c.getVisibility() == 8)) {
      return null;
    }
    return c.getEditText();
  }
  
  public View getEidtTextView()
  {
    return c.getEidtTextView();
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    i = false;
  }
  
  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    float f1 = getResourcesgetDisplayMetricsdensity;
    h.a(f1);
    f1 = ViewConfiguration.get(k).getScaledPagingTouchSlop();
    h.b(f1);
    if (g != null) {
      g.a(paramConfiguration);
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    i = false;
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    inflate(getContext(), 2130968751, this);
    a = findViewById(2131886613);
    b = ((MessagePopupTipView)findViewById(2131886614));
    c = ((MessagePopupReplyView)findViewById(2131886615));
    d = ((MessagePopupVeriCodeView)findViewById(2131886616));
    float f1 = getResourcesgetDisplayMetricsdensity;
    float f2 = ViewConfiguration.get(k).getScaledPagingTouchSlop();
    h = new aee(0, this, f1, f2);
    MessagePopupService.a(true, 3, "MessagePopupService.MessagePopupGroupView", "onFinishInflate-->densityScale = " + f1 + ", pagingTouchSlop = " + f2);
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool2 = false;
    i = true;
    Rect localRect = new Rect();
    EditText localEditText = c.getEidtTextView();
    localEditText.getGlobalVisibleRect(localRect);
    if (paramMotionEvent.getAction() == 0)
    {
      if ((localRect.contains((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())) && (localEditText.getLineCount() > 3)) {
        j = false;
      }
    }
    else {
      if (!j) {
        break label118;
      }
    }
    label118:
    for (boolean bool1 = h.a(paramMotionEvent);; bool1 = false)
    {
      boolean bool3 = super.onInterceptTouchEvent(paramMotionEvent);
      if (!bool1)
      {
        bool1 = bool2;
        if (!bool3) {}
      }
      else
      {
        bool1 = true;
      }
      return bool1;
      j = true;
      break;
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return (h.b(paramMotionEvent)) || (super.onTouchEvent(paramMotionEvent));
  }
  
  public void setAnimatorObjectCallback(a parama)
  {
    g = parama;
  }
  
  public void setGroupViewCallback(b paramb)
  {
    e = paramb;
    b.setTipCallback(e);
    c.setReplyCallback(e);
    d.setGroupCallback(e);
  }
  
  public void setUserTouch(boolean paramBoolean)
  {
    i = paramBoolean;
  }
  
  public static abstract interface a
  {
    public abstract int a();
    
    public abstract void a(float paramFloat);
    
    public abstract void a(Configuration paramConfiguration);
    
    public abstract int b();
    
    public abstract void b(float paramFloat);
    
    public abstract float c();
    
    public abstract void c(float paramFloat);
    
    public abstract float d();
    
    public abstract void e();
    
    public abstract void f();
  }
  
  public static abstract interface b
    extends MessagePopupReplyView.a, MessagePopupTipView.a
  {}
}

/* Location:
 * Qualified Name:     com.android.mms.view.MessagePopupGroupView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.android.mms.view;

import aaa;
import acw;
import acx;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.mms.MmsApp;
import gm;
import gm.b;
import gq;
import gr;
import java.util.Iterator;
import wd;
import wd.a;

public class ConversationListItem
  extends MmsGridLayout
  implements gm.b
{
  private static int A = 0;
  public static final StyleSpan a = new StyleSpan(1);
  private static int z = 0;
  private gr B;
  private boolean C = true;
  private Context D;
  a b;
  
  public ConversationListItem(Context paramContext)
  {
    super(paramContext);
    D = paramContext;
  }
  
  public ConversationListItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet, aaa.a(0), 2131886241);
    D = paramContext;
  }
  
  private CharSequence b()
  {
    Object localObject = B.h();
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
    int j = ((gq)localObject).size();
    localObject = ((gq)localObject).iterator();
    int i = 0;
    for (;;)
    {
      if (((Iterator)localObject).hasNext())
      {
        localSpannableStringBuilder.append(((gm)((Iterator)localObject).next()).g());
        if (i < j - 1) {
          localSpannableStringBuilder.append("，");
        }
        i += 1;
        if (i < 8) {}
      }
      else
      {
        return localSpannableStringBuilder;
      }
    }
  }
  
  private void b(boolean paramBoolean)
  {
    if (!a(h)) {
      return;
    }
    b = new a(null);
    b.a = paramBoolean;
    b.execute(new Void[0]);
  }
  
  private void c(boolean paramBoolean)
  {
    d.setText(b());
    b(paramBoolean);
  }
  
  public final void a()
  {
    if (Log.isLoggable("Mms:contact", 3)) {
      Log.v("ConversationListItem", "unbind: contacts.removeListeners " + this);
    }
    gm.b(this);
    if (b != null)
    {
      b.cancel(true);
      b = null;
    }
  }
  
  public final void a(Context paramContext, gr paramgr)
  {
    B = paramgr;
    Object localObject;
    if ((B.n()) && (MmsApp.c().k() != B.e()))
    {
      a(B.o());
      d.setText(b());
      if (Log.isLoggable("Mms:contact", 3)) {
        Log.v("ConversationListItem", "bind: contacts.addListeners " + this);
      }
      gm.a(this);
      localObject = wd.d(getContext(), B.e());
      long l = paramgr.j();
      if (localObject == null) {
        break label188;
      }
      a(c);
      label128:
      f.setText(wd.a(paramContext, l));
      b(true);
      paramContext = k;
      if (!B.p()) {
        break label242;
      }
    }
    label188:
    label242:
    for (int i = 0;; i = 8)
    {
      paramContext.setVisibility(i);
      a(B);
      return;
      j.setVisibility(4);
      break;
      localObject = B.m();
      paramgr = (gr)localObject;
      if (localObject == null)
      {
        paramgr = B.l();
        B.a(B.l());
      }
      a(B.r(), paramgr);
      break label128;
    }
  }
  
  public void a(gm paramgm)
  {
    if (Log.isLoggable("Mms:contact", 3)) {
      Log.v("ConversationListItem", "onUpdate: " + this + " contact: " + paramgm);
    }
    if (B.h().contains(paramgm)) {
      y.post(new acw(this));
    }
  }
  
  public void a(boolean paramBoolean)
  {
    if (B != null)
    {
      Iterator localIterator = B.h().iterator();
      while (localIterator.hasNext()) {
        gm.a(((gm)localIterator.next()).d(), paramBoolean);
      }
    }
  }
  
  public gr getConversation()
  {
    return B;
  }
  
  public View getSimView()
  {
    return l;
  }
  
  public long getThreadId()
  {
    return B.e();
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    gm.b(this);
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    if (z == 0) {
      z = getResources().getDimensionPixelSize(2131559498);
    }
    if (A == 0) {
      A = getResources().getDimensionPixelSize(2131559497);
    }
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (C) {
      return super.onInterceptTouchEvent(paramMotionEvent);
    }
    return true;
  }
  
  public void setAvatarClickable(boolean paramBoolean)
  {
    C = paramBoolean;
  }
  
  public class a
    extends AsyncTask<Void, Void, Bitmap>
  {
    boolean a;
    
    private a() {}
    
    protected Bitmap a(Void... paramVarArgs)
    {
      return aaa.a(ConversationListItem.a(ConversationListItem.this), ConversationListItem.b(ConversationListItem.this).h(), x, x);
    }
    
    protected void a(Bitmap paramBitmap)
    {
      h.setImageBitmap(paramBitmap);
      if (!a) {
        return;
      }
      h.setVisibility(0);
      i.setLongClickable(false);
      i.setOnClickListener(new acx(this));
      b = null;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.ConversationListItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
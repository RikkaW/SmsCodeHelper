package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import com.android.mms.MmsApp;
import hb;
import lq;
import lr;
import pv;
import xf;
import xg;

public class AttachmentEditor
  extends LinearLayout
{
  private final Context a;
  private Handler b;
  private SlideViewInterface c;
  private lr d;
  private xf e;
  private boolean f;
  private ImageButton g;
  private int h;
  private ImageView i;
  private int j;
  
  public AttachmentEditor(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a = paramContext;
    j = getResources().getColor(2131820868);
  }
  
  private View a(int paramInt1, int paramInt2)
  {
    View localView2 = findViewById(paramInt2);
    View localView1 = localView2;
    if (localView2 == null) {
      localView1 = ((ViewStub)findViewById(paramInt1)).inflate();
    }
    return localView1;
  }
  
  private SlideViewInterface a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    LinearLayout localLinearLayout = (LinearLayout)a(paramInt1, paramInt2);
    localLinearLayout.setVisibility(0);
    h = paramInt3;
    if ((paramBoolean) && (paramInt4 != 0)) {
      i = ((ImageView)localLinearLayout.findViewById(paramInt4));
    }
    return (SlideViewInterface)localLinearLayout;
  }
  
  private void c()
  {
    if (g != null)
    {
      g.setEnabled(f);
      g.setFocusable(f);
    }
  }
  
  private SlideViewInterface d()
  {
    if (d.size() > 1) {
      return e();
    }
    lq locallq = d.a(0);
    if (locallq.e()) {
      return a(2131886647, 2131886477, 9, 2131886479, true);
    }
    if (locallq.g()) {
      return a(2131886648, 2131886773, 7, 2131886774, true);
    }
    if (locallq.f()) {
      return a(2131886649, 2131886215, 8, 2131886216, false);
    }
    if (locallq.i()) {
      return a(2131886650, 2131886771, 10, 2131886772, false);
    }
    if (locallq.j()) {
      return a(2131886651, 2131886464, 13, 2131886465, false);
    }
    throw new IllegalArgumentException();
  }
  
  private SlideViewInterface e()
  {
    LinearLayout localLinearLayout = (LinearLayout)a(2131886652, 2131886738);
    localLinearLayout.setVisibility(0);
    h = 3;
    if (MmsApp.a)
    {
      View localView = localLinearLayout.findViewById(2131886478);
      localView.setOnLongClickListener(new a(1, localView));
    }
    return (SlideViewInterface)localLinearLayout;
  }
  
  public int a(hb paramhb)
  {
    a();
    c = null;
    i = null;
    if ((!paramhb.h()) || (paramhb.i() == null))
    {
      h = -1;
      return h;
    }
    d = paramhb.i();
    try
    {
      c = d();
      if (c == null)
      {
        if (b != null) {
          b.sendEmptyMessage(12);
        }
        h = -1;
        return h;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        c = null;
        h = -1;
        Log.e("AttachmentEditor", "createView()----" + localIllegalArgumentException.getMessage());
      }
      if (e == null) {
        break label154;
      }
    }
    if (!d.equals(e.getModel()))
    {
      label154:
      e = xg.a("MmsThumbnailPresenter", a, c, d);
      e.present(null);
      if ((c instanceof SlideshowAttachmentView)) {
        if (!paramhb.l()) {
          break label247;
        }
      }
    }
    label247:
    for (paramhb = paramhb.k().toString();; paramhb = "")
    {
      ((SlideshowAttachmentView)c).a(paramhb, d.e());
      return h;
      e.setView(c);
      break;
    }
  }
  
  public void a()
  {
    if (c != null) {
      ((View)c).setVisibility(8);
    }
  }
  
  public void b()
  {
    if (((d != null) && (d.size() > 1)) || (c == null)) {
      return;
    }
    c.a(d.d());
  }
  
  public View getAttachmentView()
  {
    return (View)c;
  }
  
  public void setCanSend(boolean paramBoolean)
  {
    if (f != paramBoolean)
    {
      f = paramBoolean;
      c();
    }
  }
  
  public void setHandler(Handler paramHandler)
  {
    b = paramHandler;
  }
  
  public void setSelected(boolean paramBoolean)
  {
    super.setSelected(paramBoolean);
    if (i != null)
    {
      if (paramBoolean) {
        i.setColorFilter(j);
      }
    }
    else {
      return;
    }
    i.clearColorFilter();
  }
  
  public class a
    implements View.OnLongClickListener
  {
    private int b;
    private View c;
    
    public a(int paramInt, View paramView)
    {
      b = paramInt;
      c = paramView;
    }
    
    public boolean onLongClick(View paramView)
    {
      paramView = new PopupMenu(AttachmentEditor.a(AttachmentEditor.this), c);
      paramView.getMenuInflater().inflate(2131951622, paramView.getMenu());
      paramView.setOnMenuItemClickListener(new pv(this));
      paramView.show();
      return true;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentEditor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
package com.android.mms.view;

import aau;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import arj;
import com.android.mms.ui.MeizuSlideshowActivity;
import lm;
import vp.a;
import wd;

public class MmsSlidePartitionItemLayout
  extends FrameLayout
  implements View.OnClickListener, View.OnLongClickListener
{
  protected vp.a a;
  private long b;
  private int c;
  private TextView d;
  private FrameLayout e;
  private ImageView f;
  private ImageView g;
  private TextView h;
  private TextView i;
  private ImageView j;
  private arj k;
  private View l;
  private Context m;
  private View n;
  
  public MmsSlidePartitionItemLayout(Context paramContext)
  {
    super(paramContext);
    m = paramContext;
  }
  
  public MmsSlidePartitionItemLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    m = paramContext;
  }
  
  public MmsSlidePartitionItemLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    m = paramContext;
  }
  
  private final String a(long paramLong)
  {
    paramLong = 1023L + paramLong;
    if (paramLong <= 1048576L) {
      return wd.a(paramLong, 1024L, "KB", null);
    }
    return wd.a(paramLong, 1048576L, "MB", null);
  }
  
  private void a(int paramInt)
  {
    View localView = getChildAt(0);
    if (localView == null) {
      return;
    }
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
    topMargin = getResources().getDimensionPixelSize(2131559284);
    localView.setLayoutParams(localLayoutParams);
  }
  
  private void b()
  {
    if (a == null) {
      return;
    }
    d.setText(a.b);
  }
  
  private void c()
  {
    if (a == null) {
      return;
    }
    h.setText(a.c.n());
    i.setText(a(a.c.p()));
    g.setOnClickListener(this);
    n.setOnClickListener(this);
  }
  
  private void d()
  {
    if (a == null) {
      return;
    }
    h.setText(a.c.n());
    i.setText(a(a.c.p()));
    g.setOnClickListener(this);
    n.setOnClickListener(this);
  }
  
  private void e()
  {
    if ((a == null) || (a.c == null)) {
      return;
    }
    h.setText(a.c.n());
    i.setText(a(a.c.p()));
    wd.a(a.c.n(), g, false);
    g.setOnClickListener(this);
    n.setOnClickListener(this);
  }
  
  private ContentResolver getContentResolver()
  {
    return m.getContentResolver();
  }
  
  public void a()
  {
    a = null;
    if (e != null) {
      e.setOnLongClickListener(null);
    }
    if (j != null)
    {
      j.setOnClickListener(null);
      j.setOnLongClickListener(null);
    }
    if (g != null) {
      g.setOnClickListener(null);
    }
    if (n != null) {
      n.setOnClickListener(null);
    }
    a(-1);
  }
  
  public void a(int paramInt1, int paramInt2, String paramString, long paramLong)
  {
    b = paramLong;
    c = paramInt2;
    inflate(m, paramInt1, this);
    switch (c)
    {
    default: 
      return;
    case 1: 
      d = ((TextView)findViewById(2131886213));
      d.setTextIsSelectable(true);
      return;
    case 2: 
      e = ((FrameLayout)findViewById(2131886593));
      f = ((ImageView)findViewById(2131886594));
      return;
    case 4: 
      j = ((ImageView)findViewById(2131886594));
      return;
    }
    g = ((ImageView)findViewById(2131886594));
    h = ((TextView)findViewById(2131886217));
    i = ((TextView)findViewById(2131886218));
    n = findViewById(2131886593);
  }
  
  public void a(int paramInt, vp.a parama, Bitmap paramBitmap)
  {
    a = parama;
    a(paramInt);
    switch (c)
    {
    default: 
      return;
    case 1: 
      b();
      return;
    case 2: 
      a(paramBitmap);
      return;
    case 3: 
      c();
      return;
    case 4: 
      b(paramBitmap);
      return;
    case 5: 
      d();
      return;
    }
    e();
  }
  
  public void a(Bitmap paramBitmap)
  {
    if ((a == null) || (a.c == null)) {
      return;
    }
    Uri localUri = a.c.k();
    if ((!a.e) || (localUri == null)) {
      f.setImageResource(2130838201);
    }
    for (;;)
    {
      e.setOnLongClickListener(this);
      return;
      if (localUri != null) {
        if (paramBitmap == null) {
          f.setImageResource(2130838201);
        } else {
          f.setImageBitmap(paramBitmap);
        }
      }
    }
  }
  
  public void a(View paramView)
  {
    boolean bool2 = true;
    l = paramView;
    if ((paramView == null) || (paramView.getWindowToken() == null))
    {
      StringBuilder localStringBuilder = new StringBuilder().append("---(v == null) = ");
      if (paramView == null)
      {
        bool1 = true;
        localStringBuilder = localStringBuilder.append(bool1).append("-- Token = ");
        if (paramView.getWindowToken() != null) {
          break label81;
        }
      }
      label81:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        Log.e("MmsSlidePartitionItemLayout", bool1);
        return;
        bool1 = false;
        break;
      }
    }
    if (k == null)
    {
      k = new arj(m);
      k.setOutsideTouchable(true);
      k.setAnimationStyle(aau.e("Animation_DropDownUp"));
    }
    k.a(paramView, new a(a));
    k.a(paramView, new RectF(0.0F, 0.0F, paramView.getWidth(), paramView.getHeight()));
  }
  
  public void b(Bitmap paramBitmap)
  {
    int i1 = 300;
    if (a == null) {
      return;
    }
    if (paramBitmap == null)
    {
      if (a.d == null) {
        a.d = new int[] { 300, 300 };
      }
      paramBitmap = (FrameLayout.LayoutParams)j.getLayoutParams();
      width = a.d[0];
      height = a.d[1];
      j.setLayoutParams(paramBitmap);
      j.setBackground(m.getResources().getDrawable(2130837691));
      j.setOnLongClickListener(this);
      j.setOnClickListener(this);
      return;
    }
    int i2;
    float f1;
    if (a.d == null)
    {
      i2 = (int)(paramBitmap.getWidth() * 2.5F);
      f1 = (int)(paramBitmap.getHeight() * 2.5F) / i2;
      if (i2 > 300) {
        break label287;
      }
    }
    for (;;)
    {
      i2 = (int)(i1 * f1);
      a.d = new int[] { i1, i2 };
      FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)j.getLayoutParams();
      width = a.d[0];
      height = a.d[1];
      j.setLayoutParams(localLayoutParams);
      j.setBackgroundDrawable(new BitmapDrawable(getResources(), paramBitmap));
      j.setOnLongClickListener(this);
      j.setOnClickListener(this);
      return;
      label287:
      if (i2 >= 520) {
        i1 = 520;
      } else {
        i1 = i2;
      }
    }
  }
  
  public int getSlideType()
  {
    return c;
  }
  
  public void onClick(View paramView)
  {
    if ((a == null) || (a.c == null)) {
      Log.e("MmsSlidePartitionItemLayout", "onClick(), mData is null or mModel is null");
    }
    if (!a.e) {
      wd.a(2131493296, m, 0, 1, true, 0);
    }
    do
    {
      return;
      if ((paramView == g) || (paramView == j))
      {
        wd.a(m, a.c, b);
        return;
      }
    } while (paramView != n);
    ((MeizuSlideshowActivity)m).a(a.c);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (l != null) {
      l.getViewTreeObserver().addOnPreDrawListener(new b(null));
    }
  }
  
  public boolean onLongClick(View paramView)
  {
    if (paramView == null) {
      return false;
    }
    a(paramView);
    return true;
  }
  
  class a
    implements ActionMode.Callback
  {
    private vp.a b;
    
    public a(vp.a parama)
    {
      b = parama;
    }
    
    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      switch (paramMenuItem.getItemId())
      {
      default: 
        return true;
      case 2: 
        if (b.e)
        {
          wd.a(MmsSlidePartitionItemLayout.c(MmsSlidePartitionItemLayout.this), b.c, MmsSlidePartitionItemLayout.d(MmsSlidePartitionItemLayout.this));
          return true;
        }
        wd.a(2131493296, MmsSlidePartitionItemLayout.c(MmsSlidePartitionItemLayout.this), 0, 1, true, 0);
        return true;
      }
      if (b.e)
      {
        ((MeizuSlideshowActivity)MmsSlidePartitionItemLayout.c(MmsSlidePartitionItemLayout.this)).a(b.c);
        return true;
      }
      wd.a(2131493296, MmsSlidePartitionItemLayout.c(MmsSlidePartitionItemLayout.this), 0, 1, true, 0);
      return true;
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      if (b == null)
      {
        Log.e("MmsSlidePartitionItemLayout", "onCreateActionMode(), mPressData is null...");
        return true;
      }
      if (("img".equals(b.a)) || ("video".equals(b.a)))
      {
        paramMenu.add(2, 2, 0, 2131493360);
        paramMenu.add(3, 3, 0, 2131493361);
        return true;
      }
      paramMenu.add(3, 3, 0, 2131493361);
      return true;
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode) {}
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return true;
    }
  }
  
  class b
    implements ViewTreeObserver.OnPreDrawListener
  {
    private b() {}
    
    public boolean onPreDraw()
    {
      MmsSlidePartitionItemLayout.a(MmsSlidePartitionItemLayout.this).getViewTreeObserver().removeOnPreDrawListener(this);
      if ((MmsSlidePartitionItemLayout.a(MmsSlidePartitionItemLayout.this) != null) && (MmsSlidePartitionItemLayout.b(MmsSlidePartitionItemLayout.this).isShowing())) {
        MmsSlidePartitionItemLayout.b(MmsSlidePartitionItemLayout.this).a(MmsSlidePartitionItemLayout.a(MmsSlidePartitionItemLayout.this), new RectF(0.0F, 0.0F, MmsSlidePartitionItemLayout.a(MmsSlidePartitionItemLayout.this).getWidth(), MmsSlidePartitionItemLayout.a(MmsSlidePartitionItemLayout.this).getHeight()));
      }
      return true;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MmsSlidePartitionItemLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
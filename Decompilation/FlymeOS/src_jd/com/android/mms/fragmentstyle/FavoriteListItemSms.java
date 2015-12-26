package com.android.mms.fragmentstyle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.android.mms.ui.MessageListView;
import com.android.mms.view.MessageListItemSms;
import com.android.mms.view.MmsFoldableTextView;
import com.meizu.common.widget.AnimCheckBox;
import com.meizu.common.widget.AnimCheckBox.UpdateListener;
import gm;
import vv;

public class FavoriteListItemSms
  extends MessageListItemSms
  implements AnimCheckBox.UpdateListener
{
  private static int l = 0;
  private static int m = 0;
  private static int n = 0;
  private static int o = 0;
  private static int p = 20;
  private AnimCheckBox ak;
  private View al;
  private int am;
  private int an;
  private boolean ao = false;
  private FavoriteListItemCommon k;
  
  public FavoriteListItemSms(Context paramContext)
  {
    super(paramContext);
  }
  
  public FavoriteListItemSms(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private void e(vv paramvv)
  {
    Bundle localBundle = new Bundle();
    localBundle.putLong("thread_id", f);
    localBundle.putLong("select_id", e);
    localBundle.putString("msg_type", d);
    localBundle.putString("group_msg_id", O);
    setTag(localBundle);
  }
  
  protected void a() {}
  
  protected void a(vv paramvv) {}
  
  public void a(vv paramvv, boolean paramBoolean1, int paramInt, MessageListView paramMessageListView, boolean paramBoolean2)
  {
    super.a(paramvv, paramBoolean1, paramInt, paramMessageListView, paramBoolean2);
    k.a(paramvv);
    e(paramvv);
    gm.a(k);
  }
  
  protected void b() {}
  
  public void c() {}
  
  protected void d()
  {
    if (w == null) {
      return;
    }
    h = true;
  }
  
  public void e()
  {
    super.e();
    gm.b(k);
  }
  
  public View getDragView()
  {
    if ((g == null) || (g.getVisibility() != 0)) {
      d();
    }
    for (;;)
    {
      s.setDragView(this);
      return getChildAt(0);
      k_();
    }
  }
  
  public void getHitRect(Rect paramRect)
  {
    super.a(paramRect);
  }
  
  public void getUpdateTransition(float paramFloat)
  {
    boolean bool = ak.isChecked();
    if ((bool) && (!ao))
    {
      ao = true;
      i = getResources().getDimensionPixelOffset(2131559021);
      if (z != null) {
        z.setMaxWidth(i);
      }
      if (y != null) {
        y.setMaxWidth(i);
      }
      localLayoutParams = (FrameLayout.LayoutParams)al.getLayoutParams();
      rightMargin = (am + ak.getMeasuredWidth() + an);
      al.setLayoutParams(localLayoutParams);
    }
    while ((bool) || (!ao))
    {
      int i;
      return;
    }
    ao = false;
    if (z != null) {
      z.setMaxWidth(Integer.MAX_VALUE);
    }
    if (y != null) {
      y.setMaxWidth(Integer.MAX_VALUE);
    }
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)al.getLayoutParams();
    rightMargin = am;
    al.setLayoutParams(localLayoutParams);
  }
  
  protected void k_()
  {
    if ((w == null) || (z == null)) {
      return;
    }
    w.setPadding(p, 0, 0, 0);
    z.setPadding(l - p, n, m, z.getPaddingBottom());
    h = true;
  }
  
  public boolean needBackground()
  {
    return true;
  }
  
  public void onClick(View paramView)
  {
    super.onClick(paramView);
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    k = ((FavoriteListItemCommon)findViewById(2131886453));
    al = findViewById(2131886452);
    am = getResources().getDimensionPixelOffset(2131559008);
    ak = ((AnimCheckBox)findViewById(16908289));
    ak.setUpdateListner(this);
    an = getResources().getDimensionPixelSize(2131558830);
    if (l == 0)
    {
      l = getResources().getDimensionPixelSize(2131558991);
      m = getResources().getDimensionPixelSize(2131558992);
      n = getResources().getDimensionPixelSize(2131558993);
      o = getResources().getDimensionPixelSize(2131558990);
      p = getResources().getDimensionPixelSize(2131559876);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.FavoriteListItemSms
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
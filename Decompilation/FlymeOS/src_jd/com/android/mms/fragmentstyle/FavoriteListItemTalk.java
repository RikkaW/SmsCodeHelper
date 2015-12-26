package com.android.mms.fragmentstyle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.mms.ui.MessageListView;
import com.android.mms.view.MessageListItemTalk;
import com.meizu.common.widget.AnimCheckBox;
import com.meizu.common.widget.AnimCheckBox.UpdateListener;
import gm;
import jx;
import vv;

public class FavoriteListItemTalk
  extends MessageListItemTalk
  implements AnimCheckBox.UpdateListener
{
  private static int e = 0;
  private static int f = 0;
  private static int g = 0;
  private static int h = 0;
  private FavoriteListItemCommon d;
  private AnimCheckBox i;
  private View j;
  private int k;
  private int l;
  private boolean m = false;
  
  public FavoriteListItemTalk(Context paramContext)
  {
    super(paramContext);
  }
  
  public FavoriteListItemTalk(Context paramContext, AttributeSet paramAttributeSet)
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
    d.a(paramvv);
    e(paramvv);
    d();
    gm.a(d);
  }
  
  protected void b() {}
  
  protected void d()
  {
    if (w == null) {}
  }
  
  public void e()
  {
    super.e();
    gm.b(d);
  }
  
  protected void f()
  {
    super.f();
    setOnClickListener(M);
    View localView = findViewById(2131886457);
    ImageView localImageView = I;
    if (M.B()) {}
    for (int n = 2130837681;; n = 2130837680)
    {
      localImageView.setImageResource(n);
      if (!M.B()) {
        break;
      }
      localView.setBackgroundResource(2131820854);
      H.setTextColor(getResources().getColor(2131820854));
      J.setProgressDrawable(getResources().getDrawable(2130838682));
      return;
    }
    localView.setBackgroundResource(2131820841);
    H.setTextColor(getResources().getColor(2131820841));
    J.setProgressDrawable(getResources().getDrawable(2130838681));
  }
  
  public View getDragView()
  {
    d();
    s.setDragView(this);
    return getChildAt(0);
  }
  
  public void getHitRect(Rect paramRect)
  {
    super.a(paramRect);
  }
  
  public void getUpdateTransition(float paramFloat)
  {
    boolean bool = i.isChecked();
    if ((bool) && (!m))
    {
      m = true;
      localLayoutParams = (FrameLayout.LayoutParams)j.getLayoutParams();
      rightMargin = (k + i.getMeasuredWidth() + l);
      j.setLayoutParams(localLayoutParams);
    }
    while ((bool) || (!m)) {
      return;
    }
    m = false;
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)j.getLayoutParams();
    rightMargin = k;
    j.setLayoutParams(localLayoutParams);
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
    d = ((FavoriteListItemCommon)findViewById(2131886453));
    j = findViewById(2131886452);
    k = getResources().getDimensionPixelOffset(2131559008);
    i = ((AnimCheckBox)findViewById(16908289));
    i.setUpdateListner(this);
    l = getResources().getDimensionPixelSize(2131558830);
    if (e == 0)
    {
      e = getResources().getDimensionPixelSize(2131558991);
      f = getResources().getDimensionPixelSize(2131558992);
      g = getResources().getDimensionPixelSize(2131558993);
      h = getResources().getDimensionPixelSize(2131558990);
    }
  }
  
  protected void setOnClickListener(vv paramvv)
  {
    w.setOnClickListener(new jx(this));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.FavoriteListItemTalk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
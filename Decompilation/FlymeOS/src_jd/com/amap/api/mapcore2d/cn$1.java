package com.amap.api.mapcore2d;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

class cn$1
  implements View.OnClickListener
{
  cn$1(cn paramcn) {}
  
  public void onClick(View paramView)
  {
    cn.b(a).setImageBitmap(cn.a(a));
    if (cn.c(a).f() > (int)cn.c(a).h() - 2) {
      cn.e(a).setImageBitmap(cn.d(a));
    }
    for (;;)
    {
      a.a(cn.c(a).f() + 1.0F);
      cn.g(a).c();
      return;
      cn.e(a).setImageBitmap(cn.f(a));
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cn.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
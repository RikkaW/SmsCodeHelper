package com.meizu.commonwidget;

import android.text.TextUtils;
import android.widget.AbsoluteLayout;
import java.util.ArrayList;
import java.util.HashMap;

public final class RecipientEdit$n
  implements Runnable
{
  private String b;
  private String c;
  
  public RecipientEdit$n(RecipientEdit paramRecipientEdit, String paramString1, String paramString2)
  {
    b = paramString1;
    c = paramString2;
  }
  
  public void run()
  {
    int i = RecipientEdit.i(a).indexOf(b);
    String str;
    if (i > -1)
    {
      RecipientEdit.h(a).put(b, c);
      str = c;
      if (!TextUtils.isEmpty(c)) {
        break label94;
      }
      str = b;
    }
    label94:
    for (;;)
    {
      ((RecipientEdit.ItemView)RecipientEdit.j(a).getChildAt(i + 1)).a(str);
      a.b(a.hasFocus());
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.commonwidget.RecipientEdit.n
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */